package br.ufes.inf.lprm.ciclovix.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

	public String dbpedia() {
		// now creating query object
		Query query = QueryFactory.create(this.queryString);
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
			}

			resultString = ResultSetFormatter.asText(results);
		} finally {
			qexec.close();
		}

		return "dbpedia";
	}
}
