package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import modelo.Musica;
import dao.MusicaDAO;

@ManagedBean(name="MusicaMB")
@RequestScoped
public class MusicaMB {
	private List<Musica> musicas = new ArrayList<Musica>();
	private MusicaDAO dao = new MusicaDAO();
	private String codigo;
	private String nome;
	private Musica musica = new Musica();

	public MusicaMB() {
		this.setMusicas(dao.ListarTodas());
	}
	
	public String salvar(){
		try {
			musica.setCodigo(codigo);
			musica.setNome(nome);
			dao.inserir(musica);
			FacesMessage msg = new FacesMessage("Inclusão da Música realizada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String editar()throws SQLException{
		try{
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			String idParam = ctx.getRequestParameterMap().get("id");
			musica = this.getMusicaPorId(Integer.parseInt(idParam));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editar.jsf";
	}
	
	public String atualizar() throws SQLException{
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	     	musica.setId(Integer.valueOf(idParam));
			dao.atualizar(musica);
			FacesMessage msg = new FacesMessage("Atualização da Música executada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		this.setMusicas(dao.ListarTodas());
//		return "/adm/musica/lista.xhtml?faces-redirect=true";
		return "";
	}
	
	public String remover() throws SQLException{
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	        musica.setId(Integer.valueOf(idParam));
			dao.remover(musica);
			FacesMessage msg = new FacesMessage("Remoção da Música executada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setMusicas(dao.ListarTodas());
		return "";
	}
	
	public Musica getMusicaPorId(int id) {
		for (Musica musica : musicas) {
			if(musica.getId() == id){
				return musica;
			}			
		}
		return null;
	}
	
	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

}
