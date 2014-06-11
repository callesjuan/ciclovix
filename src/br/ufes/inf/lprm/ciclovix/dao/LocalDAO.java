package br.ufes.inf.lprm.ciclovix.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.lprm.ciclovix.entities.Local;

@Stateless
public class LocalDAO extends EntidadeDAO<Local> {
	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
