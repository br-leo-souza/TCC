package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Post;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class PostDAO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Session session;
	
	public PostDAO() {
		this.session = HibernateUtil.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> listarTodos(){
		return session.createCriteria(Post.class).addOrder(Order.desc("id")).list();
	}
	
	public String inserir(Post post){
		session.beginTransaction();
		try {
			session.save(post);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Salvo com sucesso!";
	}
	
	public String atualizar(Post post){
		session.beginTransaction();
		try {
			session.update(post);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Atualizado com sucesso!";
	}
	
	public String excluir(Post post){
		session.beginTransaction();
		try {
			session.delete(post);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Excluído com sucesso!";
	}
}
