package br.ufes.inf.lprm.ciclovix.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.ufes.inf.lprm.ciclovix.dao.AnotacaoDAO;
import br.ufes.inf.lprm.ciclovix.dao.CategoriaDAO;
import br.ufes.inf.lprm.ciclovix.dao.LocalDAO;
import br.ufes.inf.lprm.ciclovix.entities.Anotacao;
import br.ufes.inf.lprm.ciclovix.entities.Categoria;
import br.ufes.inf.lprm.ciclovix.entities.Local;
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
	@EJB
	CategoriaDAO daoCategoria;

	Anotacao anotacao;
	long categoria;
	List<SelectItem> categorias;
	DataModel<Anotacao> listaAnotacoes;

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
			this.anotacao.setCategoria(daoCategoria.obter(this.categoria));
			this.daoAnotacao.salvar(this.anotacao);
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
