package br.ufes.inf.lprm.ciclovix.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.lprm.ciclovix.entities.Categoria;
import br.ufes.inf.lprm.ciclovix.entities.Mapa;

@Stateless
public class CategoriaDAO extends EntidadeDAO<Categoria> {
	@PersistenceContext
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Class<Categoria> getDomainClass() {
		return Categoria.class;
	}
}
