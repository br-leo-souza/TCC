package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "album")
public class Album {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nome;
	private String descricao;
	private String data;
	@Transient
	@OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	private List<Foto> fotos;
	@Transient
	private Foto capa;

	public Album() {
	}

	public Album(int id, String nome, String descricao, String data) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String string) {
		this.data = string;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Foto getCapa() {
		return capa;
	}

	public void setCapa(Foto capa) {
		this.capa = capa;
	}

	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} 
		if (o instanceof Album)  {
			Album  album = (Album) o;
			if(album.getNome().equals(this.nome) && (album.hashCode() == this.hashCode())) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return this.nome.hashCode();
	}

}