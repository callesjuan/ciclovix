package br.ufes.inf.lprm.ciclovix.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Anotacao extends Entidade {
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = true)
	Pessoa autor;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = true)
	Categoria categoria;
	@OneToMany(mappedBy = "anotacao", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Local> locais;
	String nome;
	@Column(nullable = true)
	long timestamp;
	@Column(nullable = true)
	int positivo;
	@Column(nullable = true)
	int negativo;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getPositivo() {
		return positivo;
	}

	public void setPositivo(int positivo) {
		this.positivo = positivo;
	}

	public int getNegativo() {
		return negativo;
	}

	public void setNegativo(int negativo) {
		this.negativo = negativo;
	}
}
