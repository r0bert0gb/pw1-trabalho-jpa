package testes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import pessoas.Passageiro;
import util.JpaUtil;
import voos.Aeroporto;
import voos.Passagem;
import voos.Voo;

public class AeroSemDao {

	public static void main(String[] args) {

		EntityManager em = JpaUtil.getEntityManager();

		// Aeroportos
		var aero1 = new Aeroporto("Poa", "Aero de Poa");
		var aero2 = new Aeroporto("Floripa", "Aero de Floripa");

		Set<Aeroporto> aeros = new HashSet<>();

		aeros.add(aero1);
		aeros.add(aero2);

		// Passageiros
		var psgro1 = new Passageiro();
		psgro1.setNome("Fulano");
		psgro1.setEndereco("Rua do Fulano");
		psgro1.getTelefones().add("(51) 3333-3333");
		psgro1.getTelefones().add("(51) 7777-7777");
		psgro1.setCpf("123.456.789-01");

		var psgro2 = new Passageiro();
		psgro2.setNome("PsgroX :D");
		psgro2.setEndereco("Rua de psgroX");
		psgro2.getTelefones().add("(51) 2222-2222");
		psgro2.getTelefones().add("(51) 1111-1111");
		psgro2.getTelefones().add("(51) 1010-1010");
		psgro2.setCpf("098.765.432-10");

		// Passagens
		var psgm1 = new Passagem(3799.89F, 99, 700);
		psgm1.setPassageiro(psgro1);

		var psgm2 = new Passagem(5678.79F, 95, 702);
		psgm2.setPassageiro(psgro2);

		Set<Passagem> psgns = new HashSet<>();

		psgns.add(psgm1);
		psgns.add(psgm2);

		// Passageiro

		// Joga no vôo

		var v1 = new Voo();

		v1.setNumero("787");
		v1.setOrigem("origem de v1");
		v1.setDestino("destino de v1");

		v1.setAeroportos(aeros);
		v1.setPassagens(psgns);

		v1.setData(LocalDateTime.now());

		try {

			em.getTransaction().begin();

			if (em.getTransaction().isActive()) {

				System.out.println("Conectou!");
			}

			em.persist(v1);
			System.out.println("depois de persist");

			em.flush();

			em.getTransaction().commit();

			System.out.println("chegou no fim");

		} catch (RuntimeException e) {

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
