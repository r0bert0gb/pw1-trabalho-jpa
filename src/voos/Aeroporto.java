package voos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aeroporto")
public class Aeroporto implements Serializable , Comparable<Aeroporto> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aeroporto")
	private Long id_aeroporto;

	@Column(name = "nome_aeroporto", columnDefinition = "VARCHAR(50)")
	private String nome;
	
	@Column(name = "cidade_aeroporto", columnDefinition = "VARCHAR(50)")
	private String cidade;
	
	// @ManyToMany
	@ManyToMany(mappedBy = "aeroportos")
	private Set<Voo> voos = new HashSet<>();

	public Aeroporto() {
		super();
	}

	public Aeroporto(String nome, String cidade) {
		super();

		this.nome = nome;
		this.cidade = cidade;
	}

	@Override
	public String toString() {

		String strVoos;

		if (voos.isEmpty()) {

			strVoos = "Não há voos registrados neste aeroporto!";

		} else {

			strVoos = "\n";

			for (Voo v : voos) {

				strVoos += "\t" + v + "\n";
			}

		}

		// @formatter:off
		return """
				id_aeroporto = %d
				nome = %s
				cidade = %s
				voos [ %s ] // voos
				""".formatted(
						id_aeroporto
						, nome
						, cidade //);
						, strVoos);
		// @formatter:on
	}


	@Override
	public int hashCode() {
		return Objects.hash(cidade, id_aeroporto, nome, voos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Aeroporto other = (Aeroporto) obj;
		return Objects.equals(cidade, other.cidade) && Objects.equals(id_aeroporto, other.id_aeroporto)
				&& Objects.equals(nome, other.nome) && Objects.equals(voos, other.voos);
	}

	@Override
	public int compareTo(Aeroporto aero) {

		return this.nome.compareTo(aero.getNome());

	}

	public Set<Voo> getVoos() {
		return voos;
	}

	public void setVoos(Set<Voo> voos) {

		if (voos == null) {
			this.voos.clear();
		} else {

			this.voos = voos;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getId_aeroporto() {
		return id_aeroporto;
	}

	public void setId_aeroporto(Long id_aeroporto) {
		this.id_aeroporto = id_aeroporto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
