package br.ufes.inf.lprm.ciclovix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufes.inf.lprm.ciclovix.entities.Entidade;

public abstract class EntidadeDAO<T extends Entidade> {

	public abstract EntityManager getEntityManager();

	public T salvar(T obj) throws Exception {
		try {
			getEntityManager().getTransaction().begin();
			if (obj.getId() == null) {
				getEntityManager().persist(obj);
			} else {
				obj = getEntityManager().merge(obj);
			}
			getEntityManager().getTransaction().commit();
		} catch (Exception ex) {
			throw ex;
		}
		return obj;
	}

	public void excluir(T obj) throws Exception {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(obj);
			getEntityManager().getTransaction().commit();
		} catch (Exception ex) {
			throw ex;
		}
	}

	public T consultar(T obj) throws Exception {
		try {
			return (T) getEntityManager().find(obj.getClass(), obj.getId());
		} catch (Exception ex) {
			throw ex;
		}
	}

	public List<T> buscar(T obj) throws Exception {
		try {
			Query q = getEntityManager().createQuery(
					"SELECT t FROM " + obj.getClass().getSimpleName() + " t ");
			return q.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
	}
}
