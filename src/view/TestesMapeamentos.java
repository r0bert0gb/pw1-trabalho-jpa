package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.VooDAO;
import pessoas.Passageiro;
import util.Situacao;
import voos.Aeroporto;
import voos.Passagem;
import voos.Voo;

public class TestesMapeamentos {

	public static void main(String[] args) {

		short opcao;

		var vooDao = new VooDAO();

		do {
			// @formatter:off
			String menu = """
					---- MENU ----
					1 - Cadastrar vôo
					2 - Atualizar
					3 - Remover
					4 - Listar todos os voos
					5 - Pesquisar por cpf de passageiro
					9 - Sair

					opção: """;

			try {
				opcao = Short.parseShort(
					input(menu, "MENU", JOptionPane.PLAIN_MESSAGE
				));
			} catch (NumberFormatException e) {

				popUp("Valor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
				opcao = 0;
			}

			// @formatter:on
			switch (opcao) {

			case 1 -> {

				// Inserindo valores quaisquer para os relacionamentos
				var psgro = new Passageiro("Fulano", "Endereco", "Cpf");

				psgro.getTelefones().add("(51) 1111-1111");
				psgro.getTelefones().add("(51) 2222-2222");
				psgro.getTelefones().add("(51) 3333-3333");

				var psgm = new Passagem();
				psgm.setNumero(99);
				psgm.setPoltrona(79);
				psgm.setSituacao(Situacao.VENDIDA);
				psgm.setValor(6999.89);
				psgm.setPassageiro(psgro);

				var voo = new Voo();

				voo.setData(LocalDateTime.of(2022, 3, 4, 7, 59));
				voo.getAeroportos().add(new Aeroporto());
				voo.setNumero("99");
				voo.setOrigem("Origem do voo");
				voo.setDestino("Destino do voo");

				voo.getPassagens().add(psgm);

				if (vooDao.salvar(voo)) {

					popUp("Vôo salvo.", "Salvo no banco", JOptionPane.INFORMATION_MESSAGE);

				} else {
					System.out.println("Vôo não salvo.");
				}

			} // case 1

			case 2 -> {
				// @formatter:off
				long id = Long.parseLong(
					input(
						"Informe a id do vôo a ser alterado"
						, "id do vôo"
						, JOptionPane.PLAIN_MESSAGE
					)
				);
				// @formatter:on

				Voo vooTemp = vooDao.buscaId(id);

				vooTemp.setDestino("Destino alterado");

				if (vooDao.atualizar(vooTemp)) {

					popUp("Entidade atualizada!", "Atualizado", JOptionPane.INFORMATION_MESSAGE);

				} else {
					popUp("Vôo não atualizado", "Erro!", JOptionPane.ERROR_MESSAGE);
				}

			} // case 2

			case 3 -> {

				// @formatter:off
				long id = Long.parseLong(
					input(
						"Informe a id do vôo a ser removido"
						, "id do vôo"
						, JOptionPane.PLAIN_MESSAGE
				));
				// @formatter:on

				if (vooDao.remover(id)) {

					popUp("Vôo removido!", "Removido!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					popUp("Vôo não removido", "Erro!", JOptionPane.ERROR_MESSAGE);
				}

			} // case 3

			case 4 -> {

				/*
				 * Está dando overflow tentando printar todos os voos.
				 */

				List<Voo> voos = vooDao.buscarTudo();
				// @formatter:off

				popUp(
					voos.size() + " voos retornados"
					, "Lista voos"
					, JOptionPane.INFORMATION_MESSAGE
				);


			} // case 4

			case 5 -> {
				String cpf = input(
					"Informe o cpf do passageiro a ser pesquisado"
					, "CPF"
					, JOptionPane.PLAIN_MESSAGE
				);
				// @formatter:on

				List<Voo> lista = vooDao.buscarTudo();

				List<String> numeros = new ArrayList<>();

				boolean achou = false;

				for (Voo v : lista) {

					if (!v.getPassagens().isEmpty()) {

						for (Passagem psgm : v.getPassagens()) {

							if (cpf.equals(psgm.getPassageiro().getCpf())) {

								achou = true;
								numeros.add(v.getNumero());
							}
						}
					}
				}

				String resposta;
				if (achou) {

					String concat = "";

					for (String numero : numeros) {
						concat += numero + "\n";
					}

					resposta = """
								O passageiro de cpf %s está nos voos:
								%s
							""".formatted(cpf, concat);

				} else {
					resposta = "Não foi encontrado um passageiro entre os voos.";
				}

				popUp(resposta, "Resultado", JOptionPane.INFORMATION_MESSAGE);

			} // case 5

			case 9 -> popUp("Saindo...", "Tchau", JOptionPane.PLAIN_MESSAGE);

			} // switch

		} while (opcao != 9);

	} // main()

	public static String input(String msg, String titulo, int tipo) {

		return JOptionPane.showInputDialog(null, msg, titulo, tipo);

	}

	public static void popUp(String msg, String titulo, int tipo) {

		JOptionPane.showMessageDialog(null, msg, titulo, tipo, null);
	}

} // classe
