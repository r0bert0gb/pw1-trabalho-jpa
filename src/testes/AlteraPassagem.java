package testes;

import javax.persistence.EntityManager;

import util.JpaUtil;
import voos.Passagem;

public class AlteraPassagem {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		try {

			em.getTransaction().begin();

			// Tentar deixar o passageiro como null
			// usando setPassageiro()
			// Passagem psgm1 = em.find(Passagem.class, 1L);
			// psgm1.setPassageiro(null); // funcionou!

			// Tentar setar o passageiro de id2 com setPassageiro()
			// (id 2 já tem passagem!)

			// Passagem psgm1 = em.find(Passagem.class, 1L);

			// Passageiro psgro2 = em.find(Passageiro.class, 2L);

			// erro de chave duplicada!
			// psgm1.setPassageiro(psgro2);

			em.flush();

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

	public static void idPsgro(Passagem psgm) {

		System.out.println("""
				id da passagem: %d
				id do passageiro: %s
				""".formatted(psgm.getId_passagem(),
				(psgm.getPassageiro() == null) ? "passageiro nulo" : psgm.getPassageiro().getId_pessoa()));
	}
}
