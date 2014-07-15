package br.ufes.inf.lprm.ciclovix.mb;

import java.io.Serializable;
import java.net.URI;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

@ManagedBean
@SessionScoped
public class DbpediaMB implements Serializable {

	String queryString;
	String resultString;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public String snippet() {
		String geo = this.geocode(this.queryString);
		String snippet = dbpedia(geo);

		this.resultString = snippet;

		return "dbpedia";
	}

	private String geocode(String latlng) {

		String cidade = null;
		String estado = null;

		try {

			/*
			 * HTTP REQUEST
			 */

			URI url = (new URIBuilder(
					"http://maps.googleapis.com/maps/api/geocode/json"))
					.addParameter("latlng", this.queryString).build();

			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			get.addHeader("Content-Type", "application/json");

			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();

			/*
			 * JSON PARSING
			 */

			String input = EntityUtils.toString(entity);
			JSONParser parser = new JSONParser();
			JSONObject root = (JSONObject) parser.parse(input);
			JSONArray results = (JSONArray) root.get("results");
			JSONObject result_0 = (JSONObject) results.get(0);
			JSONArray address_components = (JSONArray) result_0
					.get("address_components");
			for (Object obj : address_components) {
				JSONObject address = (JSONObject) obj;
				JSONArray types = (JSONArray) address.get("types");

				if (types.get(0).equals("administrative_area_level_2")) {
					cidade = (String) address.get("long_name");
					cidade = cidade.split(",")[0];
				} else if (types.get(0).equals("administrative_area_level_1")) {
					estado = (String) address.get("long_name");
					estado = estado.split(",")[0];
				}

				if (cidade != null && estado != null) {
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cidade + ";" + estado;
	}

	private String dbpedia(String geo) {

		String[] local = geo.split(";");
		String cidade = local[0];
		String estado = local[1];

		String snippet = null;

		String format = "PREFIX schema: <http://schema.org/>"
				+ "  PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
				+ "  PREFIX dbpprop: <http://dbpedia.org/property/>"
				+ "  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "  SELECT DISTINCT ?cidade ?pop"
				+ "  WHERE {"
				+ "    ?cidade dbpedia-owl:populationTotal ?pop;"
				+ "      rdf:type dbpedia-owl:Place;"
				+ "      rdf:type dbpedia-owl:Settlement;"
				+ "      dbpedia-owl:abstract ?abstract;"
				+ "      dbpprop:mapCaption ?state;"
				+ "      rdfs:label ?city"
				+ "      FILTER ( regex(?city, \"%s\", \"i\" ) && langMatches( lang(?abstract), \"PT\" ) && regex(?state, \"%s\", \"i\") )"
				+ "}";
		String queryString = String.format(format, cidade, estado);

		// now creating query object
		Query query = QueryFactory.create(queryString);
		// initializing queryExecution factory with remote service.
		// **this actually was the main problem I couldn't figure out.**
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);

		// after it goes standard query execution and result processing which
		// can
		// be found in almost any Jena/SPARQL tutorial.
		try {
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				// Result processing is done here.
				break;
			}

			snippet = ResultSetFormatter.asText(results);
		} finally {
			qexec.close();
		}

		return snippet;
	}
}
