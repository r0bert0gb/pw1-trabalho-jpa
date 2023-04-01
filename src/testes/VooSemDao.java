package testes;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import pessoas.Passageiro;
import util.JpaUtil;
import util.Situacao;
import voos.Passagem;
import voos.Voo;

public class VooSemDao {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		// passageiro
		var psgro1 = new Passageiro();

		psgro1.setNome("VooSemDao");
		psgro1.setEndereco("Rua SemDao");
		psgro1.setCpf("Cpf do VooSemDao");
		psgro1.getTelefones().add("+55 (51) 7777-8888");
		psgro1.getTelefones().add("+55 (51) 9999-6666");

		// passagem
		var psgm1 = new Passagem();

		psgm1.setNumero(380);
		psgm1.setPoltrona(89);
		psgm1.setSituacao(Situacao.VENDIDA);
		psgm1.setValor(658.99F);
		psgm1.setPassageiro(psgro1);

		// voo
		var v1 = new Voo();

		v1.setOrigem("origem de v1");
		v1.setDestino("destino de v1");
		v1.setNumero("99");
		v1.setData(LocalDateTime.now());
		v1.getPassagens().add(psgm1);

		System.out.println(v1);

		var psgm2 = new Passagem();

		var psgro2 = new Passageiro();

		psgm2.setPassageiro(psgro2);

		try {

			em.getTransaction().begin();

			em.persist(v1);

			em.persist(psgm2);

			em.flush();

			em.getTransaction().commit();

			System.out.println("Chegou no fim");

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
