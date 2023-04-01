package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import util.JpaUtil;
import voos.Aeroporto;

public class AeroportoDAO extends AbstractDAO<Aeroporto> {

	@Override
	public boolean remover(long id) {

		try {
			em = JpaUtil.getEntityManager();

			em.getTransaction().begin();

			Aeroporto aero = em.find(Aeroporto.class, id);

			em.remove(aero);

			em.flush();

			em.getTransaction().commit();

		} catch (RuntimeException e) {

			rollback();
			return false;
		}
		return true;
	}

	@Override
	public Aeroporto buscaId(long id) {

		try {

			em = JpaUtil.getEntityManager();

			return em.find(Aeroporto.class, id);
		} catch (RuntimeException e) {

			// System.out.println("buscaId de aeroporto");
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Aeroporto> buscarTudo() {

		try {

			em = JpaUtil.getEntityManager();
			// @formatter:off
			TypedQuery<Aeroporto> query = em.createQuery(
								"SELECT obj FROM Aeroporto obj"
								, Aeroporto.class
					);
			// @formatter:on

			return query.getResultList();

		} catch (RuntimeException e) {

			e.printStackTrace();

			return null;
		}

	}

}
