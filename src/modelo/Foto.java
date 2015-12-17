package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "foto")
public class Foto {
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String caminho;
	private String data;
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Album album = new Album();
	private byte[] imagem;

	public Foto(Integer id, String nome, String caminho, String data, Album album, byte[] imagem) {
		this.id = id;
		this.nome = nome;
		this.caminho = caminho;
		this.data = data;
		this.album = album;
		this.imagem = imagem;
	}


	public Foto() {
	}


	public Integer getId() {
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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album_id) {
		this.album = album_id;
	}


	public byte[] getImagem() {
		return imagem;
	}


	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
