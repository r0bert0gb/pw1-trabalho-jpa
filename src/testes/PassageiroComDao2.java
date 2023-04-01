package testes;

import dao.PassageiroDAO;

public class PassageiroComDao2 {

	public static void main(String[] args) {

		// cria um dao
		var psgroDao = new PassageiroDAO();

		// Passageiro psgroId = psgroDao.buscaId(6);
		//
		// System.out.println(psgroId);

		// for (Passageiro p : psgroDao.buscarTudo()) {
		//
		// System.out.println(p);
		// }

		// Cria um registro
		// var psgro1 = new Passageiro();
		//
		// psgro1.setNome("Com Dao 2");
		//
		// psgro1.setEndereco("End 2");
		//
		// psgro1.setCpf("cpf 2");
		//
		// psgro1.getTelefones().add("Telefone 1");
		// psgro1.getTelefones().add("Telefone 2");
		// psgro1.getTelefones().add("Telefone 3");
		// // ----
		//
		// // salva esse registro
		// if (psgroDao.salvar(psgro1)) {
		// System.out.println("Salvou o passageiro!");
		// } else {
		// System.out.println("Não salvou o passageiro!");
		// }
		// ----

		// busca um registro e altera os dados no banco (merge)
		/*
		 * Vai dar errado, eu acho. "psgro2" não está persistido.
		 * 
		 * Não deu errado.
		 * lembrar: "merge" cria um registro novo, caso não existe
		 * um anterior!
		 */
		// var psgro2 = psgroDao.buscaId(3);
		//
		// psgro2.setNome("nome alterado");
		//
		// if (psgroDao.atualizar(psgro2)) {
		//
		// System.out.println("Alterou o passageiro!");
		// } else {
		// System.out.println("Não alterou o passageiro!");
		// }
		// ------

		// remove alguém do banco
		if (psgroDao.remover(10)) {
			System.out.println("Removeu o passageiro");
		} else {
			System.out.println("não removeu o passageiro");
		}

	}
}
