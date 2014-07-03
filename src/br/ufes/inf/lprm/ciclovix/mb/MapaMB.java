package br.ufes.inf.lprm.ciclovix.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufes.inf.lprm.ciclovix.dao.CategoriaDAO;
import br.ufes.inf.lprm.ciclovix.dao.MapaDAO;
import br.ufes.inf.lprm.ciclovix.entities.Categoria;
import br.ufes.inf.lprm.ciclovix.entities.Mapa;

@ManagedBean
@SessionScoped
public class MapaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * DAOs
	 */
	@EJB
	MapaDAO daoMapa;
	@EJB
	CategoriaDAO daoCategoria;

	int exibirCategorias;

	Mapa mapa;
	Categoria categoria;
	DataModel<Mapa> listaMapas;
	DataModel<Categoria> listaCategorias;

	public Mapa getMapa() {
		return this.mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getExibirCategorias() {
		return this.exibirCategorias;
	}

	public DataModel<Mapa> getListarMapas() {
		try {
			this.listaMapas = new ListDataModel(this.daoMapa.listar());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listaMapas;
	}

	public DataModel<Categoria> getListarCategorias() {
		try {
			this.listaCategorias = new ListDataModel(mapa.getCategorias());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listaCategorias;
	}

	public String prepararAdicionarMapa() {
		this.mapa = new Mapa();
		this.exibirCategorias = 0;
		return "visualizar_mapa";
	}

	public String prepararAlterarMapa() {
		this.mapa = (Mapa) (this.listaMapas.getRowData());
		this.exibirCategorias = 1;
		return "visualizar_mapa";
	}

	public String salvarMapa() {
		try {
			this.daoMapa.salvar(mapa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listar_mapas";
	}

	public String excluirMapa() {
		Long idMapa = ((Mapa) (this.listaMapas.getRowData())).getId();
		try {
			this.daoMapa.excluir(idMapa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listar_mapas";
	}

	public String prepararAdicionarCategoria() {
		this.categoria = new Categoria();
		this.categoria.setMapa(this.mapa);
		return "visualizar_categoria";
	}

	public String prepararAlterarCategoria() {
		this.categoria = (Categoria) (this.listaCategorias.getRowData());
		return "visualizar_categoria";
	}

	public String salvarCategoria() {
		try {
			this.daoCategoria.salvar(categoria);
			this.mapa = this.daoMapa.obter(this.mapa.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "visualizar_mapa";
	}

	public String excluirCategoria() {
		Long idCategoria = ((Categoria) (this.listaCategorias.getRowData()))
				.getId();
		try {
			this.daoCategoria.excluir(idCategoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "visualizar_mapa";
	}

	public String prepararAdicionarAnotacao() {
		// AnotacaoMB anotacaoMB = (AnotacaoMB)
		// FacesContext.getCurrentInstance()
		// .getExternalContext().getRequestMap().get("anotacaoMB");
		return "listar_anotacoes";
	}

}
