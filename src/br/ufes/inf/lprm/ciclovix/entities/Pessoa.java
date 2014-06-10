package br.ufes.inf.lprm.ciclovix.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Pessoa extends Entidade {
	String nome;
	String sobrenome;
	String contaFace;
	String contaTwitter;
	@ManyToMany(mappedBy="autores")
	List<Anotacao> anotacoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getContaFace() {
		return contaFace;
	}

	public void setContaFace(String contaFace) {
		this.contaFace = contaFace;
	}

	public String getContaTwitter() {
		return contaTwitter;
	}

	public void setContaTwitter(String contaTwitter) {
		this.contaTwitter = contaTwitter;
	}

	public List<Anotacao> getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(List<Anotacao> anotacoes) {
		this.anotacoes = anotacoes;
	}
}
