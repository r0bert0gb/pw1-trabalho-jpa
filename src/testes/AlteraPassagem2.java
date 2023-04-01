package testes;

import javax.persistence.EntityManager;

import pessoas.Passageiro;
import util.JpaUtil;
import voos.Passagem;

public class AlteraPassagem2 {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			// Passagem psgm1 = em.find(Passagem.class, 1L);

			// pegaId(psgm1);

			// alterar id do passageiro para 5 (não existe no bd)
			// psgm1.getPassageiro().setId_pessoa(5L);

			// de 3 para 1
			// psgm1.getPassageiro().setId_pessoa(1L);

			// de 3 para 0
			// psgm1.getPassageiro().setId_pessoa(0L);

			// de 3 para 4
			// psgm1.getPassageiro().setId_pessoa(4L);

			Passagem psgm2 = em.find(Passagem.class, 2L);

			pegaId(psgm2);

			Passageiro psgro2 = em.find(Passageiro.class, 2L);

			psgm2.setPassageiro(psgro2);

			em.flush();

//			em.getTransaction().commit();

			pegaId(psgm2);

		} catch (RuntimeException e) {

			System.out.println("caiu no catch");
			e.printStackTrace();

			if (em.getTransaction().isActive()) {

				em.getTransaction().rollback();
			}
		} finally {

			em.close();
			JpaUtil.close();
		}

	}

	public static void pegaId(Passagem psgm) {

		System.out.println("""
				id da passagem: %d
				id do passageiro: %s
				-----------------------""".formatted(psgm.getId_passagem(),
				(psgm.getPassageiro() == null) ? "nulo" : psgm.getPassageiro().getId_pessoa()));
	}

}
