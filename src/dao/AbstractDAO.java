package dao;

import java.util.List;

import javax.persistence.EntityManager;

import util.JpaUtil;

public abstract class AbstractDAO<T> {

	protected EntityManager em;

	public AbstractDAO() {
		super();
	}

	protected void rollback() {
		// @formatter:off
		if (em.getTransaction().isActive())
			em.getTransaction().rollback();
		// @formatter:on
	}

	public abstract boolean remover(long id);

	public abstract T buscaId(long id);

	public abstract List<T> buscarTudo();

	public final boolean salvar(T entidade) {

		try {
			em = JpaUtil.getEntityManager();

			em.getTransaction().begin();

			em.persist(entidade);

			em.flush();

			em.getTransaction().commit();

		} catch (RuntimeException e) {

			rollback();

			return false;
		}
		return true;
	} // .salvar()

	public final boolean atualizar(T entidade) {

		try {
			em = JpaUtil.getEntityManager();

			em.getTransaction().begin();

			em.merge(entidade);

			em.flush();
			em.getTransaction().commit();

		} catch (RuntimeException e) {

			rollback();
			return false;
		}
		return true;
	} // .atualizar()
}
