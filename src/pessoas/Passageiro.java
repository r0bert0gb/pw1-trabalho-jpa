package pessoas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "passageiro")
@AttributeOverrides({
	@AttributeOverride(name = "id_pessoa", column = @Column(name = "id_passageiro")),
	@AttributeOverride(name = "nome", column = @Column(
			name = "nome_passageiro"
			, nullable = false
			, columnDefinition = "VARCHAR(50)"
		)
	)
})
public class Passageiro extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "cpf", columnDefinition = "VARCHAR(20)")
	private String cpf;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "passageiro_telefone", joinColumns = @JoinColumn(name = "id_passageiro"))
	@Column(name = "telefone", columnDefinition = "VARCHAR(20)")
	private List<String> telefones = new ArrayList<>();

	public Passageiro() {
		super();
	}

	public Passageiro(String nome, String endereco, String cpf) {

		super(nome);

		this.endereco = endereco;
		this.cpf = cpf;
	}

	@Override
	public String toString() {

		String strFones = "telefones {";

		if (telefones.isEmpty()) {
			strFones += " Não há telefones cadastrados. }";
		} else {
			strFones += "\n\t\tQuantidade: " + telefones.size();

			for (int i = 0; i < telefones.size(); i++) {

				strFones += "\n\t\t%d: %s".formatted((i + 1), telefones.get(i));
			}

			strFones += "\n\t} // telefones";
		}

		return """
			Passageiro [
				%s
				endereco = %s
				cpf = %s
				%s
				] // Passageiro
			"""
				.formatted(super.toString(), getEndereco(), getCpf(), strFones);
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	/**
	 * Impede que a classe não possua uma lista.
	 */
	public void setTelefones(List<String> telefones) {

		if (telefones == null) {
			this.telefones.clear();
		} else {
			this.telefones = telefones;
		}
	}
}
