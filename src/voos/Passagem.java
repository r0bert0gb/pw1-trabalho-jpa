package voos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pessoas.Passageiro;
import util.Situacao;
import util.Validador;

@Entity
@Table(name = "passagem")
public class Passagem implements Validador, Serializable, Comparable<Passagem> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_passagem")
	private Long id_passagem;

	@Column(name = "valor", precision = 2, columnDefinition = "FLOAT(7, 2) UNSIGNED")
	private double valor;

	@Column(name = "poltrona", columnDefinition = "TINYINT UNSIGNED")
	private int poltrona;

	@Column(name = "numero", columnDefinition = "BIGINT UNSIGNED")
	private int numero;

	/*
	 * No cenário imaginado:
	 *
	 * 1. passageiro pode ser criado por persistência da
	 * passagem, mas não excluído. (CascadeType.PERSIST)
	 * 2. Não pode receber uma id de passageiro que já possua uma passagem.
	 * 3. Se o atributo passageiro for alterado na entidade
	 * persistida, será criada uma nova passagem.
	 */
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_passageiro", unique = true, nullable = false)
	private Passageiro passageiro;

	/**
	 * Questão 2:
	 *
	 * O nome das constantes de uma passagem, a princípio, não
	 * deveriam mudar. Me fez mais sentido manter as strings como
	 * imutáveis, visto que as palavras serão sempre as mesmas.
	 *
	 * @author Roberto
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", columnDefinition = "VARCHAR(15)")
	private Situacao situacao;

	public Passagem() {
		super();
		this.situacao = Situacao.DISPONIVEL;
	}
	
	public Passagem(double valor, int poltrona, int numero) {
		
		this.valor = valor;
		this.setPoltrona(poltrona);
		this.numero = numero;
	}

	public Passagem(double valor, int poltrona, int numero, Passageiro passageiro, Situacao situacao) {
		super();
		this.valor = valor;
		this.setPoltrona(poltrona);
		this.numero = numero;
		this.passageiro = passageiro;
		this.setSituacao(situacao);
	}

	@Override
	public int compareTo(Passagem p) {

		if (numero > p.getNumero()) return 1;

		if (numero < p.getNumero()) return -1;

		return 0;
	}

	@Override
	public boolean validaPoltrona(int poltrona) {

		return (poltrona >= 0 && poltrona <= 100) ? true : false;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {

		this.situacao = (situacao == null) ? Situacao.DISPONIVEL : situacao;
	}

	@Override
	public String toString() {

		return """
				Passagem [
					id_passagem = %d
					valor = %.2f
					poltrona = %d
					numero = %d
					%s
					situacao = %s
				] // Passagem""".formatted(id_passagem, valor, poltrona, numero,
				(passageiro == null) ? "passageiro = Sem passageiro atribuído à passagem!" : passageiro, situacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero, passageiro, poltrona, situacao, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Passagem other = (Passagem) obj;
		return numero == other.numero && Objects.equals(passageiro, other.passageiro) && poltrona == other.poltrona
				&& situacao == other.situacao && Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}

	public Long getId_passagem() {
		return id_passagem;
	}

	public void setId_passagem(Long id_passagem) {
		this.id_passagem = id_passagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(int poltrona) {

		this.poltrona = validaPoltrona(poltrona) ? poltrona : 0;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro psgro) {
		this.passageiro = psgro;
	}
}
