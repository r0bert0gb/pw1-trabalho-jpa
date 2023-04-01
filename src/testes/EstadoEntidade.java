package testes;

import javax.persistence.EntityManager;

import pessoas.Passageiro;
import util.JpaUtil;

public class EstadoEntidade {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		/*
		 * Entidade nova / transiente. Não possui
		 * registro correspondente no banco
		 */
		var psgro1 = new Passageiro();

		psgro1.setNome("Estudo Estado Entidade");
		psgro1.setCpf("CPF qualquer");
		psgro1.setEndereco("Rua X");
		psgro1.getTelefones().add("5555-8888");

		// ent. nova / transiente
		var merjador = new Passageiro();

		/*
		 * Resposta de:
		 * https://stackoverflow.com/questions/14581865/hibernate-flush-and-commit
		 *
		 * flush() will synchronize your database with the
		 * current state of object/objects held in the memory
		 * but it does not commit the transaction. So, if you
		 * get any exception after flush() is called, then the
		 * transaction will be rolled back. You can synchronize
		 * your database with small chunks of data using flush()
		 * instead of committing a large data at once using commit()
		 * and face the risk of getting an OutOfMemoryException.
		 *
		 * commit() will make data stored in the database permanent.
		 * There is no way you can rollback your transaction once
		 * the commit() succeeds.
		 *
		 * Boa prática executar "flush()" após algumas operacões.
		 */

		try {

			em.getTransaction().begin();

			// Entidade persistida
			em.persist(psgro1);

			// merjador.setNome("Merjador");

			/*
			 * entidade detached:
			 *
			 * Equivalente à exclusão. Não apenas as mudanças
			 * não são mais acompanhadas, mas a linha / registro
			 * é excluída no flush / commit
			 */
			em.detach(psgro1);
			psgro1.setNome("Nome alterado");

			psgro1 = em.merge(merjador);

			System.out.println(psgro1);

			// executa a "fila" de comandos SQL
			// Ainda não comitados!
			em.flush();

			// comitados!
			em.getTransaction().commit();

			System.out.println("chegou no fim estado entidade");

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
