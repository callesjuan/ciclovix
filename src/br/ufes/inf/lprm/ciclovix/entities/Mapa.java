package br.ufes.inf.lprm.ciclovix.entities;

import javax.persistence.Entity;

@Entity
public class Mapa extends Entidade {
	String nome;
	String descricao;
	String ontologia;

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
}
