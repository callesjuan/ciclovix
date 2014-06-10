package br.ufes.inf.lprm.ciclovix.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SemanticaAnotacao extends Entidade {
	String nome;
	@ManyToOne
	Mapa mapa;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
