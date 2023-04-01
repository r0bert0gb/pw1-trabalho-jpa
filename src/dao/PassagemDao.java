package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import util.JpaUtil;
import voos.Passagem;

public class PassagemDao extends AbstractDAO<Passagem> {

	@Override
	public Passagem buscaId(long id) {

		try {

			em = JpaUtil.getEntityManager();

			return em.find(Passagem.class, id);

		} catch (RuntimeException e) {

			// System.out.println("buscaId de passagem");
			// e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Passagem> buscarTudo() {

		try {

			em = JpaUtil.getEntityManager();

		// @formatter:off
		TypedQuery<Passagem> query = em.createQuery(
						"SELECT obj FROM Passagem obj"
						, Passagem.class
			);
		// @formatter:on

			return query.getResultList();

		} catch (RuntimeException e) {
			// e.printStackTrace();

			return null;
		}

	}

	public boolean remover(long id) {

		try {

			em = JpaUtil.getEntityManager();

			em.getTransaction().begin();
			Passagem psgm = em.find(Passagem.class, id);

			em.remove(psgm);

			em.flush();

			em.getTransaction().commit();

		} catch (RuntimeException e) {

			rollback();
			return false;
		}
		return true;
	}

}
