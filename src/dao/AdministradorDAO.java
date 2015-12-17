package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Administrador;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class AdministradorDAO  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Session session;

	public AdministradorDAO() {
		this.session = HibernateUtil.getSession();
	}

	@SuppressWarnings("unchecked")
	public List<Administrador> listarTodos() {
		return session.createCriteria(Administrador.class).addOrder(Order.asc("id")).list();
	}
	
	public Administrador isUsuarioReadyToLogin(String login, String senha){
		
		SQLQuery query = session.createSQLQuery("SELECT * FROM administrador c WHERE c.login = :login AND c.senha = :senha");
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		Object[] result = (Object[]) query.uniqueResult();
		
		Administrador adm = new Administrador();
		
		if(result != null){
			adm.setId			(Integer.valueOf(result[0].toString()));
			adm.setNome			(result[1].toString());
			adm.setSobrenome	(result[2].toString());
			adm.setLogin		(result[3].toString());
			adm.setSenha		(result[4].toString());
			adm.setTelefone		(result[5].toString());
			adm.setEmail		(result[6].toString());
		}
	
		return adm;
	}
			
	public String inserir(Administrador adm){
		session.beginTransaction();		
		try {
			session.save(adm);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Salvo com Sucesso!";
	}
	
	public String atualizar(Administrador adm){
		session.beginTransaction();		
		try {
			session.merge(adm);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Atualizado com Sucesso!";
	}

	public String remover(Administrador adm) {
		session.beginTransaction();		
		try {
			session.clear();
			session.delete(adm);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return e.getMessage();
		}
		return "Removido com Sucesso!";
	}
}
