package br.ufes.inf.lprm.ciclovix.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.ufes.inf.lprm.ciclovix.dao.AnotacaoDAO;
import br.ufes.inf.lprm.ciclovix.dao.CategoriaDAO;
import br.ufes.inf.lprm.ciclovix.entities.Anotacao;
import br.ufes.inf.lprm.ciclovix.entities.Categoria;

@ManagedBean
@SessionScoped
public class AnotacaoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * DAOs
	 */
	@EJB
	AnotacaoDAO daoAnotacao;
	@EJB
	CategoriaDAO daoCategoria;

	Anotacao anotacao = new Anotacao();
	long categoria;
	List<SelectItem> categorias;
	List<Anotacao> markers;
	DataModel<Anotacao> listaAnotacoes;
	
	private Marker marker;
	private MapModel simpleModel = new DefaultMapModel();;

	public Anotacao getAnotacao() {
		return this.anotacao;
	}

	public void setAnotacao(Anotacao anotacao) {
		this.anotacao = anotacao;
	}

	public long getCategoria() {
		return categoria;
	}

	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}

	public List<SelectItem> getCategorias() {
		this.categorias = new ArrayList<SelectItem>();
		try {
			List<Categoria> categorias = this.daoCategoria.listar();
			for (Categoria categoria : categorias) {
				this.categorias.add(new SelectItem(categoria.getId(), categoria
						.getNome()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.categorias;
	}

	public DataModel<Anotacao> getListarAnotacoes() {
		try {
			this.listaAnotacoes = new ListDataModel(daoAnotacao.listar());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listaAnotacoes;
	}
	
	public MapModel getMarkers() {
		
		try {
			markers = daoAnotacao.listar();			
		} catch (Exception e) {
			e.printStackTrace();
		}							

		for(Anotacao a : markers){
			simpleModel.addOverlay( new Marker(new LatLng(a.getLongitude(), a.getLatitude()), a.getNome(), a ));
		}
		
        return simpleModel;
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
	
	public Marker getMarker() {
        return marker;
    }

	public String prepararAdicionarAnotacao() {
		this.anotacao = new Anotacao();
		return "visualizar_anotacao";
	}
	
	public String prepararAlterarAnotacao() {
		this.anotacao = (Anotacao) (this.listaAnotacoes.getRowData());
		this.categoria = this.anotacao.getCategoria().getId();
		return "visualizar_anotacao";
	}

	public String salvarAnotacao() {
		try {
			System.out.println("SALVAR ANOTAÇÂO");
			this.anotacao.setCategoria(daoCategoria.obter(this.categoria));
			this.daoAnotacao.salvar(this.anotacao);
			anotacao = new Anotacao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	public String excluirAnotacao() {
		Long idAnotacao = ((Anotacao) (this.listaAnotacoes.getRowData()))
				.getId();
		try {
			this.daoAnotacao.excluir(idAnotacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listar_anotacoes";
	}

}
