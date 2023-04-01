package pessoas;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.io.Serializable;
import java.util.Objects;

/**
 * Quest�o 4:
 * A anota��o "MappedSuperclass" foi a anota��o escolhida.
 *
 * Essa anota��o mant�m a rela��o entre as classes apenas
 * no contexto de POO. A tabela "pessoa" n�o ir� existir
 * no banco de dados.
 *
 * A escolha foi feita porque uma tabela "pessoa" �
 * irrelevante para o cen�rio do trabalho.
 *
 * A classe Pessoa serve apenas como base para a
 * classe Passageiro.
 *
 * @author Roberto
 */

@MappedSuperclass
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long id_pessoa;

	/**
	 * N�o consigo fazer o EclipseLink respeitar o "nullable"
	 * na heran�a com a classe Passageiro()
	 */
	@Column(name = "nome", columnDefinition = "VARCHAR(50)", nullable = false)
	private String nome;

	public Pessoa() {
		super();
	}

	public Pessoa(String nome) {
		super();
		this.nome = nome;
	}

	public Pessoa(Long id_pessoa, String nome) {
		super();
		this.id_pessoa = id_pessoa;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Pessoa [ id_pessoa = " + id_pessoa + ", nome = " + nome + " ]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_pessoa, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id_pessoa, other.id_pessoa) && Objects.equals(nome, other.nome);
	}

	public Long getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Long id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
