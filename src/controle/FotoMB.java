package controle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;

import modelo.Album;
import modelo.Foto;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import dao.AlbumDAO;
import dao.FotoDAO;

@ManagedBean(name="FotoMB")
@SessionScoped
public class FotoMB implements Serializable{
	private static final long serialVersionUID = -275335211801099493L;
	private List<Foto> fotos = new ArrayList<Foto>();
	private FotoDAO DAOFoto = new FotoDAO();
	private Integer idAlbum;
	private String descricao;
	private AlbumDAO DAOAlbum = new AlbumDAO();
	private List<Album> albuns = new ArrayList<Album>();
	private List<Album> albunsLista = new ArrayList<Album>();
	private Foto foto = new Foto();
	private Integer id;
	
	public FotoMB(){
		this.setAlbuns(DAOAlbum.listarTodos());
		preencherDados();
	}
	
	public DefaultStreamedContent getImagem(){
		if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			String idParam = ctx.getRequestParameterMap().get("id");
			
			for(Foto foto : fotos){
				if((Integer.valueOf(idParam)).equals(foto.getId())){
					return new DefaultStreamedContent(new ByteArrayInputStream(foto.getImagem()), "image/png");
				}
			}
		}
		
		return new DefaultStreamedContent();
	}
	
	public void fileUpload(FileUploadEvent event) throws IOException{
		boolean isPrimeira = true;
		try{
			Foto ft = new Foto();
			//Cria um arquivo UploadFile, para receber o arquivo do evento
			UploadedFile arq = event.getFile();
			//Transformar a imagem em bytes para salvar em banco de dados
			byte[] imagem = arq.getContents();
			ft.setNome(arq.getFileName());
			ft.getAlbum().setId(idAlbum);
			ft.setData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ft.setImagem(imagem);
			fotos.add(ft);
			DAOFoto.inserir(ft);
	 
			if(isPrimeira){
				FacesMessage msg = new FacesMessage("Upload da(s) imagem(s) executado com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			isPrimeira = false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		preencherDados();
	 }
	
	public String remover() throws SQLException{
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			String idParam = ctx.getRequestParameterMap().get("id");
			foto.setId(Integer.valueOf(idParam));
			foto.setAlbum(null);
			DAOFoto.remover(foto);
			FacesMessage msg = new FacesMessage("Remoção da Foto executada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setFotos(DAOFoto.listarTodasFotos());
		return "/adm/foto/lista.jsf?faces-redirect=true";
	}
	
	public String listarFotos(){
		if(idAlbum == 0){
			this.setFotos(DAOFoto.listarTodasFotos());
		}else{
			fotos = (List<Foto>) this.getFotoPorAlbum(idAlbum);
		}
		return "";
	}
	
	public List<Foto> getFotoPorAlbum(int parseInt) {
		for (Foto foto : fotos) {
			if(foto.getAlbum().getId() != parseInt){
				fotos.remove(foto);
			}
		}
		return fotos;
	}
	
	public String getNomeAlbumPorId(int parseInt) {
		for (Album album : albunsLista) {
			if(album.getId() == parseInt){
				return album.getNome();
			}
		}
		return null;
	}
	
	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
	public void recuperarDados(AjaxBehaviorEvent event){
		idAlbum = (Integer) event.getSource();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setAlbuns(List<Album> list) {
		Collection toReturn = new ArrayList();
		for (Album album : list) {
			toReturn.add(new SelectItem(album.getId(), album.getNome()));
		}
		this.albuns.addAll(toReturn);
	}
	
	public void preencherDados(){
		this.setFotos(DAOFoto.listarTodasFotos());
		this.setAlbunsLista(DAOAlbum.listarTodos());
		
		for(Foto foto : fotos){
			foto.getAlbum().setNome(this.getNomeAlbumPorId(foto.getAlbum().getId()));
		}
	}
	
	public List<Album> getAlbuns(){
		return this.albuns;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Integer getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Album> getAlbunsLista() {
		return albunsLista;
	}

	public void setAlbunsLista(List<Album> albunsLista) {
		this.albunsLista = albunsLista;
	}

}