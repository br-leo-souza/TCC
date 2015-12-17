package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String email;
	private String conteudo;
	private String status;
	private Date data;
	@Transient
	private int dia;
	@Transient
	private int mes;
	@Transient
	private int ano;
	@Transient
	private int hora;
	@Transient
	private int minuto;
	@Transient
	private String mesExtenso;

	public Post(String nome, String email, String conteudo, String status, Date data) {
		this.nome = nome;
		this.email = email;
		this.conteudo = conteudo;
		this.status = status;
		this.data = data;
	}

	public Post() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public String getMesExtenso() {
		return mesExtenso;
	}

	public void setMesExtenso(String mesExtenso) {
		this.mesExtenso = mesExtenso;
	}
}
