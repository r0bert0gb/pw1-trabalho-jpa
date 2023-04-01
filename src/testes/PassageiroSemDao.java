package testes;

import javax.persistence.EntityManager;

import pessoas.Passageiro;
import util.JpaUtil;

public class PassageiroSemDao {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		var p1 = new Passageiro();
		p1.setNome("Nome de p1");
		p1.setCpf("cpf de p1");
		p1.setEndereco("Rua de p1");

		p1.getTelefones().add("1111-1111");
		p1.getTelefones().add("1111-2222");
		p1.getTelefones().add("1111-3333");

		var p2 = new Passageiro("Nome de p2", "Rua de p2", "cpf de p2");

		p2.getTelefones().add("2222-5555");
		p2.getTelefones().add("2222-7777");

		try {

			em.getTransaction().begin();

			em.persist(p1);

			em.persist(p2);

			em.getTransaction().commit();

			System.out.println("Gravou os passageiros!");

		} catch (RuntimeException e) {

			System.out.println("caiu no catch");

			if (em.getTransaction().isActive()) {

				em.getTransaction().rollback();
			}

		} finally {
			em.close();
			JpaUtil.close();
		}
	}
}
