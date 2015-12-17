package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "administrador")
public class Administrador {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String email;
	private String nome;
	private String sobrenome;
	private String login;
	private String senha;
	private String telefone;
	
	@Transient
	public static final String FIND_BY_EMAIL_SENHA = "Administrador.findByEmailSenha"; 


	public Administrador(int id, String email, String nome, String sobrenome, String login, String senha, String telefone) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.login = login;
		this.senha = senha;
		this.telefone = telefone;
	}

	public Administrador() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} 
		if (o instanceof Administrador)  {
			Administrador  administrador = (Administrador) o;
			if(administrador.getNome().equals(this.nome) && (administrador.hashCode() == this.hashCode())) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

}
