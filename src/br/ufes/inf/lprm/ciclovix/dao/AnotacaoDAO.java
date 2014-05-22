package br.ufes.inf.lprm.ciclovix.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.lprm.ciclovix.entities.Anotacao;

@Stateless
public class AnotacaoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public boolean salvar(Anotacao o) {
		try {
			em.persist(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
