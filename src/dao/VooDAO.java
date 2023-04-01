package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import util.JpaUtil;
import voos.Voo;

public class VooDAO extends AbstractDAO<Voo> {

	@Override
	public boolean remover(long id) {

		try {

			em = JpaUtil.getEntityManager();

			em.getTransaction().begin();

			Voo voo = em.find(Voo.class, id);

			em.remove(voo);

			em.flush();

			em.getTransaction().commit();

		} catch (RuntimeException e) {

			rollback();
			return false;
		}
		return true;
	}

	@Override
	public Voo buscaId(long id) {

		try {

			em = JpaUtil.getEntityManager();

			return em.find(Voo.class, id);
		} catch (RuntimeException e) {

			// System.out.println("buscaId de voo");
			// e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Voo> buscarTudo() {

		try {

			em = JpaUtil.getEntityManager();
			// @formatter:off
			TypedQuery<Voo> query = em.createQuery(
								"SELECT obj FROM Voo obj"
								, Voo.class
					);
			// @formatter:on

			return query.getResultList();

		} catch (RuntimeException e) {

			e.printStackTrace();

			return null;
		}
	}
}
