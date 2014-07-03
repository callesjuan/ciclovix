package br.ufes.inf.lprm.ciclovix.mb;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufes.inf.lprm.ciclovix.dao.AnotacaoDAO;
import br.ufes.inf.lprm.ciclovix.dao.LocalDAO;
import br.ufes.inf.lprm.ciclovix.entities.Anotacao;
import br.ufes.inf.lprm.ciclovix.entities.Categoria;
import br.ufes.inf.lprm.ciclovix.entities.Local;
import br.ufes.inf.lprm.ciclovix.entities.Mapa;
import br.ufes.inf.lprm.ciclovix.entities.Pessoa;

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
	LocalDAO daoLocal;

	Mapa mapa;
	Anotacao anotacao;
	DataModel<Anotacao> listaAnotacoes;

	public Anotacao getAnotacao() {
		return this.anotacao;
	}

	public void setAnotacao(Anotacao anotacao) {
		this.anotacao = anotacao;
	}

	public Mapa getMapa() {
		return this.mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public DataModel<Anotacao> getListarAnotacoes() {
		try {
			this.listaAnotacoes = new ListDataModel(this.mapa.getAnotacoes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listaAnotacoes;
	}

	public String prepararAdicionarAnotacao() {
		this.anotacao = new Anotacao();
		this.anotacao.setMapa(this.mapa);
		this.anotacao.setCategoria(new Categoria());
		this.anotacao.setLocais(new ArrayList<Local>());
		this.anotacao.setAutores(new ArrayList<Pessoa>());
		return "visualizar_anotacao";
	}

	public String prepararAlterarAnotacao() {
		this.anotacao = (Anotacao) (this.listaAnotacoes.getRowData());
		return "visualizar_anotacao";
	}

	public String salvarAnotacao() {
		try {
			this.daoAnotacao.salvar(anotacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listar_anotacoes";
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
