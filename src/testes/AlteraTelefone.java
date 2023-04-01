package testes;

import java.util.Scanner;

import javax.persistence.EntityManager;

import pessoas.Passageiro;
import util.JpaUtil;

public class AlteraTelefone {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		em.getTransaction().begin();

		Passageiro psgro = em.find(Passageiro.class, 5L);

		Scanner teclado = new Scanner(System.in);

		for (String fone : psgro.getTelefones()) {

			System.out.println(fone);
		}

		System.out.print("índice: ");
		int i = teclado.nextInt();
		teclado.nextLine();

		// System.out.print("valor: ");

		// psgro.getTelefones().set(i, teclado.nextLine());

		psgro.getTelefones().remove(i);

		teclado.close();

		em.getTransaction().commit();

		em.close();

		JpaUtil.close();

	}

}
