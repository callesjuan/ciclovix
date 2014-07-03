package br.ufes.inf.lprm.ciclovix.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Categoria extends Entidade {
	String nome;
	int tipo; // PONTO, LINHA, AREA
	@ManyToOne
	Mapa mapa;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
}
