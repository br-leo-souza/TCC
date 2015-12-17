package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import modelo.Administrador;
import dao.AdministradorDAO;

@ManagedBean(name = "AdministradorMB")
@RequestScoped
public class AdministradorMB {

	private AdministradorDAO dao = new AdministradorDAO();
	private List<Administrador> administradores = new ArrayList<Administrador>();
	private Administrador administrador = new Administrador();
	private UsuarioMBImpl usuarioMBImpl = new UsuarioMBImpl();
	private String email;
	private String login;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String senha;

	public AdministradorMB() {
		this.setAdministradores(dao.listarTodos());

//		if (administrador == null) {
//			// obtem parametro
//			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
//			String idParam = ctx.getRequestParameterMap().get("id");
//
//			if (idParam != null && !idParam.equals("")) {
//				try {
//					this.administrador = this.getAdministradorPorId(Integer.parseInt(idParam));
//				} catch (NumberFormatException e) {
//					// log
//				}
//			}
//
//			if (this.administrador == null) {
//				this.administrador = new Administrador();
//			}
//		}
	}
	
	private Administrador getAdministradorPorId(int id) {
		for (Administrador administrador : administradores) {
			if (administrador.getId() == id) {
				return administrador;
			}
		}
		return null;
	}

	public String salvar() {
		try{
			administrador.setNome(this.getNome());
			administrador.setEmail(this.getEmail());
			administrador.setSobrenome(this.getSobrenome());
			administrador.setLogin(this.getLogin());
			administrador.setSenha(usuarioMBImpl.convertStringToMd5(this.getSenha()));
			administrador.setTelefone(this.getTelefone());
			dao.inserir(administrador);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setAdministradores(dao.listarTodos());
		
		FacesMessage msg = new FacesMessage("Sucesso! Alteração do administrador ", administrador.getNome() + " conluida!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return "/adm/administrador/lista.xhtml?faces-redirect=true";
	}
	
	public String editar()throws SQLException{
		try{
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			String idParam = ctx.getRequestParameterMap().get("id");
			administrador = getAdministradorPorId(Integer.parseInt(idParam));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editar.jsf";
	}

	public String atualizar() {
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	     	administrador.setId(Integer.valueOf(idParam));
	     	administrador.setSenha(usuarioMBImpl.convertStringToMd5(administrador.getSenha()));
			dao.atualizar(administrador);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setAdministradores(dao.listarTodos());

		FacesMessage msg = new FacesMessage("Sucesso! Alteração do administrador ", administrador.getNome() + " conluida!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return "/adm/administrador/lista.xhtml?faces-redirect=true";
	}

	public String remover() {
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	        administrador.setId(Integer.valueOf(idParam));
			dao.remover(administrador);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setAdministradores(dao.listarTodos());

		FacesMessage msg = new FacesMessage("Sucesso! Remoção do administrador ", administrador.getNome() + " conluida!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return "/adm/administrador/lista.xhtml?faces-redirect=true";
	}
	
	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public AdministradorDAO getDao() {
		return dao;
	}

	public void setDao(AdministradorDAO dao) {
		this.dao = dao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
}