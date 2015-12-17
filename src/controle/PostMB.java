package controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import modelo.Post;
import dao.PostDAO;

@ManagedBean(name="PostMB")
@RequestScoped
public class PostMB {

	private List<Post> posts = new ArrayList<Post>();
	private List<Post> postsPendentes = new ArrayList<Post>();
	private List<Post> postsAprovados = new ArrayList<Post>();
	private List<Post> postsRecusados = new ArrayList<Post>();
	private PostDAO dao = new PostDAO();
	private String nome;
	private String email;
	private String conteudo;
	private Post post;
	
	public PostMB() {
		this.setPosts(dao.listarTodos());
		
		if (post == null) {
			// obtem parametro
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
	        String idParam = ctx.getRequestParameterMap().get("id");

			if (idParam != null && !idParam.equals("")) {
				try {
					this.post = this.getPostPorId(Integer.parseInt(idParam));
				} catch(NumberFormatException e) {
					// log 
				}
			}
			
			if (this.post == null) {
				this.post = new Post();
			}
		}
	}

	private Post getPostPorId(int parseInt) {
		for (Post post : posts) {
			if(post.getId() == parseInt){
				return post;
			}
		}
		return null;
	}

	public List<Post> getPosts() {
		return posts;
	}

	
	public void setPosts(List<Post> posts) {
		Calendar c = Calendar.getInstance();
		
		for (Post post : posts) {
			c.setTime(post.getData());
			post.setDia(c.get(Calendar.DAY_OF_MONTH));
			post.setMes(c.get(Calendar.MONTH));
			post.setAno(c.get(Calendar.YEAR));
			post.setHora(c.get(Calendar.HOUR_OF_DAY));
			post.setMinuto(c.get(Calendar.MINUTE));
			
			switch (post.getMes()) {
			case 1: post.setMesExtenso("Janeiro"); break;
			case 2: post.setMesExtenso("Fevereiro"); break;
			case 3: post.setMesExtenso("Março"); break;
			case 4: post.setMesExtenso("Abril"); break;
			case 5: post.setMesExtenso("Maio"); break;
			case 6: post.setMesExtenso("Junho"); break;
			case 7: post.setMesExtenso("Julho"); break;
			case 8: post.setMesExtenso("Agosto"); break;
			case 9: post.setMesExtenso("Setembro"); break;
			case 10: post.setMesExtenso("Outubro"); break;
			case 11: post.setMesExtenso("Novembro"); break;
			case 12: post.setMesExtenso("Dezembro"); break;
			}
			
			this.posts.add(post);
		}
		
		for (Post post : posts) {
			if(post.getStatus().equals("PD")){
				this.postsPendentes.add(post);
			}else if (post.getStatus().equals("OK")){
				this.postsAprovados.add(post);
			}else{
				this.postsRecusados.add(post);
			}
		}
		
	}
	
	public String salvar(){
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data1 = data.format(new Date());
		Date datafinal;
		try {
			datafinal = data.parse(data1);
			Post p = new Post(this.getNome(), this.getEmail(), this.getConteudo(), "PD", datafinal);
			dao.inserir(p);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String aprovar() {
		post.setStatus("OK");
		dao.atualizar(post);
		return null;
	}
	
	public String recusar() {
		post.setStatus("CA");
		dao.atualizar(post);
		return null;
	}
	
	public String excluir(){
		dao.excluir(post);
		return null;
	}
	
	public PostDAO getDao() {
		return dao;
	}

	public void setDao(PostDAO dao) {
		this.dao = dao;
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


	public List<Post> getPostsPendentes() {
		return postsPendentes;
	}

	public void setPostsPendentes(List<Post> postsPendentes) {
		this.postsPendentes = postsPendentes;
	}

	public List<Post> getPostsAprovados() {
		return postsAprovados;
	}

	public void setPostsAprovados(List<Post> postsAprovados) {
		this.postsAprovados = postsAprovados;
	}

	public List<Post> getPostsRecusados() {
		return postsRecusados;
	}

	public void setPostsRecusados(List<Post> postsRecusados) {
		this.postsRecusados = postsRecusados;
	}

}
