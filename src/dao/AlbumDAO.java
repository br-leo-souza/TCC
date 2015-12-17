package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Album;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class AlbumDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Session session;
	
	public AlbumDAO() {
		this.session = HibernateUtil.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> listarTodos(){
		return session.createCriteria(Album.class).addOrder(Order.desc("id")).list();
	}
	
	public String inserir(Album album){
		session.beginTransaction();
		try {
			session.save(album);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return "Ocorreu um Erro com a Mensagem : " + e.getMessage();
		}
		return "Salvo com Sucesso!";
	}

	public String atualizar(Album album) {
		session.beginTransaction();
		try {
			session.merge(album);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return "Ocorreu um Erro com a Mensagem : " + e.getMessage();
		}
		return "Atualizado com Sucesso!";
	}
	
	public String remover(Album album) {
		session.beginTransaction();
		try {
			session.clear();
			session.delete(album);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return "Ocorreu um Erro com a Mensagem : " + e.getMessage();
		}
		return "Removido com Sucesso!";
	}
	
}
