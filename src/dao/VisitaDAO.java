package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import modelo.Visita;

public class VisitaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Session session;
		
	public VisitaDAO(){
		session = HibernateUtil.getSession();
	}
	
	public void inserir(Visita visita){
		session.beginTransaction();
		session.save(visita);
		session.getTransaction().commit();
	}
		
	@SuppressWarnings("unused")
	private List<Visita> visitas = new ArrayList<Visita>();
			
	@SuppressWarnings("unchecked")
	public List<Visita> ListarTodas(){
		return session.createCriteria(Visita.class).list();
	}
}
