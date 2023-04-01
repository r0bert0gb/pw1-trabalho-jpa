package testes;

import javax.persistence.EntityManager;

import pessoas.Passageiro;
import util.JpaUtil;
import util.Situacao;
import voos.Passagem;

public class PassagemSemDao {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		var psgm1 = new Passagem();

		psgm1.setNumero(150);
		psgm1.setPoltrona(45);
		psgm1.setValor(2007.89);
		psgm1.setSituacao(Situacao.VENDIDA);
		psgm1.setPassageiro(new Passageiro());
//		psgm1.setId_passagem(1L);

		psgm1.getPassageiro().setNome("Roberto");
		psgm1.getPassageiro().setEndereco("Rua do Roberto, 405");
		psgm1.getPassageiro().setCpf("cpf do Roberto");
		psgm1.getPassageiro().getTelefones().add("9999-9999");

		var psgro1 = new Passageiro("Fulano", "End. de Fulano", "Cpf de Fulano");

		try {
			em.getTransaction().begin();

			em.persist(psgm1);

			em.persist(psgro1);

			em.getTransaction().commit();

			System.out.println("Chegou no fim de \"PassagemSemDao\"");

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
