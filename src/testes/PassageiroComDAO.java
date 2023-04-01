package testes;


import dao.PassageiroDAO;
import pessoas.Passageiro;

public class PassageiroComDAO {

	public static void main(String[] args) {

		var psgroDao = new PassageiroDAO();

		var psgro1 = new Passageiro();

		psgro1.setNome("Cara com DAO");
		psgro1.setEndereco("endereco do Cara com DAO");
		psgro1.setCpf("123.cpf");
		psgro1.getTelefones().add("3555-3333");
		psgro1.getTelefones().add("0000-9999");

		System.out.println("Nome antes das opera��es: " + psgro1.getNome());

		// --------- salvar ----------
		if (psgroDao.salvar(psgro1)) {

			System.out.println("Passageiro salvo!");
		} else {
			System.out.println("Deu god� pra salvar.");
		}

		psgro1.setNome("DAO com nome alterado");

		// ---------- atualizar ---------
		if (psgroDao.atualizar(psgro1)) {
			System.out.println("Mudan�as atualizadas");

		} else {

			System.out.println("Deu god� na atualiza��o.");
		}

		System.out.println("Nome depois das atualiza��es: " + psgro1.getNome());

	} // .main()
} // classe
