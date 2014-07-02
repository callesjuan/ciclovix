package br.ufes.inf.lprm.ciclovix.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.lprm.ciclovix.entities.Pessoa;

@Stateless
public class PessoaDAO extends EntidadeDAO<Pessoa> {
	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<Pessoa> getDomainClass() {
		return Pessoa.class;
	}

}
