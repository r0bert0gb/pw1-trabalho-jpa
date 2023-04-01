package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import pessoas.Passageiro;
import util.JpaUtil;

public class PassageiroDAO extends AbstractDAO<Passageiro> {

	public PassageiroDAO() {
		super();
	}

	@Override
	public boolean remover(long id) {

		// remover buscando pela id
		try {

			em = JpaUtil.getEntityManager();

			em.getTransaction().begin();

			Passageiro psgro = em.find(Passageiro.class, id);

			em.remove(psgro);

			em.flush();

			em.getTransaction().commit();

		} catch (RuntimeException e) {

			rollback();
			return false;
		}
		return true;
	} // .remover()
	
	@Override
	public List<Passageiro> buscarTudo() {

		try {

			em = JpaUtil.getEntityManager();
			// @formatter:off
			TypedQuery<Passageiro> query = em.createQuery(
								"SELECT obj FROM Passageiro obj"
								, Passageiro.class
					);
			// @formatter:on

			return query.getResultList();

		} catch (RuntimeException e) {

			e.printStackTrace();

			return null;
		}
	}

	@Override
	public Passageiro buscaId(long id) {

		try {

			em = JpaUtil.getEntityManager();

			return em.find(Passageiro.class, id);
		} catch (RuntimeException e) {

			// System.out.println("buscaId de passageiro");
			// e.printStackTrace();
			return null;
		}
	}

	// Se eu fechar o em e a factory aqui, vai permanecar fechada
	// na instância
	// private void finaliza() {
	// em.close();
	// JpaUtil.close();
	// }
}
