package br.ufes.inf.lprm.ciclovix.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.lprm.ciclovix.dao.AnotacaoDAO;
import br.ufes.inf.lprm.ciclovix.entities.Anotacao;

@Named
@SessionScoped
public class MapaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	AnotacaoDAO dao;

	public String getTest() {
		Anotacao o = new Anotacao();
		if (dao.salvar(o))
			return "Sucesso!";
		else
			return "Tela azul!";
	}
}
