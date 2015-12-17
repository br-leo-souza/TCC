package controle;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import modelo.Album;
import dao.AlbumDAO;

@ManagedBean(name="AlbumMB")
@RequestScoped
public class AlbumMB {
	
	private List<Album> albuns = new ArrayList<Album>();
	private AlbumDAO dao = new AlbumDAO();
	private int id;
	private String nome;
	private String descricao;
	private Date data;
	private Album album = new Album();

	public AlbumMB() {
		this.setAlbuns(dao.listarTodos());
	}

	public Album getAlbumPorId(int parseInt) {
		for (Album album : albuns) {
			if(album.getId() == parseInt){
				return album;
			}
		}
		return null;
	}

	public String salvar(){
		try {			
			album.setNome(this.getNome());
			album.setDescricao(getDescricao());
			album.setData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			dao.inserir(album);
			FacesMessage msg = new FacesMessage("Inclusão do Álbum realizada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.setAlbuns(dao.listarTodos());
		return "/adm/album/lista.xhtml?faces-redirect=true";
	}
	
	public String editar()throws SQLException{
		try{
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			String idParam = ctx.getRequestParameterMap().get("id");
			this.album = this.getAlbumPorId(Integer.parseInt(idParam));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editar.jsf";
	}
	
	public String atualizar() throws SQLException{
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	     	album.setId(Integer.valueOf(idParam));
			dao.atualizar(album);
			FacesMessage msg = new FacesMessage("Atualização do Álbum executada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		this.setAlbuns(dao.listarTodos());
//		return "/adm/album/lista.xhtml?faces-redirect=true";
		return "";
	}
	
	public String remover() throws SQLException{
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");
	     	album.setId(Integer.valueOf(idParam));
			dao.remover(album);
			FacesMessage msg = new FacesMessage("Remoção do Álbum executada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setAlbuns(dao.listarTodos());
		return "/adm/album/lista.xhtml?faces-redirect=true";
	}
	
	public List<Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<Album> albuns) {
		this.albuns = albuns;
	}
	
	public AlbumDAO getDAOAlbum() {
		return dao;
	}

	public void setDAOAlbum(AlbumDAO dAOAlbum) {
		this.dao = dAOAlbum;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
}
