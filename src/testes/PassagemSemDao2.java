package testes;

import javax.persistence.EntityManager;

import pessoas.Passageiro;
import util.JpaUtil;
import voos.Passagem;

public class PassagemSemDao2 {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		var psgm1 = new Passagem();

		try {

			em.getTransaction().begin();

			psgm1.setNumero(380);

			psgm1.setPoltrona(99);

			psgm1.setValor(5000.2);

			var psgro1 = new Passageiro("Passageiro qualquer", "Rua 11", "111.111.111-11");

			psgro1.getTelefones().add("7897-7897");

			psgm1.setPassageiro(psgro1);

			em.persist(psgm1);

			em.flush();

			em.getTransaction().commit();

			System.out.println("Chegou no fim!");

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

}
