package voos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "voo")
public class Voo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_voo")
	private Long id_voo;

	@Column(name = "origem", columnDefinition = "VARCHAR(100)")
	private String origem;

	@Column(name = "destino", columnDefinition = "VARCHAR(100)")
	private String destino;

	// Por que numero � string?
	@Column(name = "numero_voo")
	private String numero;

	// OneToMany -> Um v�o para v�rias passagens
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_voo")
	private Set<Passagem> passagens = new HashSet<>();

	// @formatter:off
	/**
	 * Voo pode apenas persistir aeroportos. N�o pode alterar os dados
	 * dos aeroportos nem remov�-los.
	 *
	 * Haver� uma tabela entre Voo e Aeroporto no modelo relacional.
	 * A tabela ir� conter as ids de Voo e Aeroporto
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
		name = "voo_aeroporto"
		, joinColumns = { @JoinColumn(name = "id_voo") }
		, inverseJoinColumns = { @JoinColumn(name = "id_aeroporto") }
	)
	// @formatter:on
	private Set<Aeroporto> aeroportos = new HashSet<>();

	/**
	 * Quest�o 1
	 * O atributo data ir� conter o dia e hora do v�o.
	 * N�o faz sentido um dia de embarque sem um hor�rio
	 * definido junto.
	 *
	 * A classe escolhida foi a "LocalDateTime".
	 *
	 * A classe java.util.Date tem problemas com o
	 * "leap second". O segundo que � adicionado � por
	 * quest�es astron�micas � ao �ltimo minuto de
	 * junho ou dezembro (varia de ano para ano).
	 */
	@Column(name = "data")
	private LocalDateTime data;

	public Voo() {
		super();
		this.numero = "0";
	}

	public Voo(String origem, String destino, String numero) {

		super();
		this.origem = origem;
		this.destino = destino;

		try {
			this.setNumero(numero);
		} catch (NumberFormatException e) {
			this.numero = "0";
			// e.printStackTrace();
		}
	}

	@Override
	public String toString() {

		String psgns = "\n\t";
		String aeros = "\n\t";

		if (passagens.isEmpty()) {
			psgns = "N�o h� passagens neste V�o.";
		} else {
			for (Passagem psgm : passagens) {
				psgns += psgm.toString() + "\n\t";
			}
		}

		if (aeroportos.isEmpty()) {
			aeros = "N�o h� aeroportos neste Voo.";
		} else {
			for (Aeroporto aero : aeroportos) {
				aeros += aero.toString() + "\n\t";
			}
		}
		// @formatter:off
		return """
				Voo {
					id_voo = %d
					origem = %s
					destino = %s
					numero = %s
					%s
					%s
					%s
				} // Voo""".formatted(
						id_voo
						, origem
						, destino
						, numero
						, psgns
						, (data == null) ? "Sem data definida" : data
						, aeros
				);
		// @formatter:on
	}

	/**
	 * O a atributo n�mero da classe Voo est� definido como string
	 * no diagrama. Tentei garantir que a string informada seja no
	 * formato de um n�mero inteiro.
	 *
	 * @param numero A string que deve ser interpretada como inteiro.
	 * @throws NumberFormatException Se a string informada n�o contiver
	 *                               um n�mero inteiro v�lido.
	 */
	public void setNumero(String numero) throws NumberFormatException {

		if (numero == null) {
			this.numero = "0";
		} else if (!numero.matches("\\d+")) {
			/*
			 * regex "\\d+" -> string contendo APENAS
			 * 1 ou mais d�gitos.
			 */
			throw new NumberFormatException("A string informada n�o � um inteiro v�lido!");
		} else {
			this.numero = numero;
		}
	}

	public Set<Aeroporto> getAeroportos() {
		return aeroportos;
	}

	public void setAeroportos(Set<Aeroporto> aeroportos) {

		if (aeroportos == null) {
			this.aeroportos.clear();
		} else {
			this.aeroportos = aeroportos;
		}
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Long getId_voo() {
		return id_voo;
	}

	public void setId_voo(Long id_voo) {
		this.id_voo = id_voo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Passagem> getPassagens() {
		return passagens;
	}

	public void setPassagens(Set<Passagem> passagens) {

		if (passagens == null) {
			this.passagens.clear();
		} else {
			this.passagens = passagens;
		}
	}

	public String getNumero() {
		return numero;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
}
