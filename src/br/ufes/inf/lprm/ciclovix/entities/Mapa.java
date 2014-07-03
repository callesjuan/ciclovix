package br.ufes.inf.lprm.ciclovix.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Mapa extends Entidade {
	String nome;
	String descricao;
	String ontologia;
	@OneToMany(mappedBy = "mapa", fetch = FetchType.EAGER)
	List<Categoria> categorias;
	@OneToMany(mappedBy = "mapa", fetch = FetchType.EAGER)
	List<Anotacao> anotacoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOntologia() {
		return ontologia;
	}

	public void setOntologia(String ontologia) {
		this.ontologia = ontologia;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Anotacao> getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(List<Anotacao> anotacoes) {
		this.anotacoes = anotacoes;
	}
}
