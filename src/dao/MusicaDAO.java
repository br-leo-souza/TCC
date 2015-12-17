package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Musica;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class MusicaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Session session;

	public MusicaDAO() {
		this.session = HibernateUtil.getSession();
	}

	@SuppressWarnings("unchecked")
	public List<Musica> ListarTodas() {
		return session.createCriteria(Musica.class).addOrder(Order.desc("id")).list();
	}
	
	public String inserir(Musica musica){
		session.beginTransaction();
		try {
			session.save(musica);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Salvo com sucesso!";
	}

	public String atualizar(Musica musica) {
		session.beginTransaction();
		try {
			session.merge(musica);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Atualizado com sucesso!";
	}

	public String remover(Musica musica) {
		session.beginTransaction();
		try {
			session.clear();
			session.delete(musica);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Removido com sucesso!";
	}
}
