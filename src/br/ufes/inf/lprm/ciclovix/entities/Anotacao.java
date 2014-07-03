package br.ufes.inf.lprm.ciclovix.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Anotacao extends Entidade {
	@ManyToOne(fetch = FetchType.EAGER)
	Mapa mapa;
	@ManyToMany(targetEntity = Pessoa.class, fetch = FetchType.EAGER)
	@JoinTable(name = "PessoaAnotacao", joinColumns = @JoinColumn(name = "anotacao"), inverseJoinColumns = @JoinColumn(name = "autor"))
	List<Pessoa> autores;
	@ManyToOne(fetch = FetchType.EAGER)
	Categoria categoria;
	@OneToMany(mappedBy = "anotacao", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Local> locais;
	String nome;
	long timestamp;
	int positivo;
	int negativo;
	int reanotacao;

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public List<Pessoa> getAutores() {
		return autores;
	}

	public void setAutores(List<Pessoa> autores) {
		this.autores = autores;
	}

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

	public int getReanotacao() {
		return reanotacao;
	}

	public void setReanotacao(int reanotacao) {
		this.reanotacao = reanotacao;
	}

	public void addAutor(Pessoa autor) {
		if (autores == null) {
			autores = new ArrayList<Pessoa>();
		}
		this.autores.add(autor);
	}
}
