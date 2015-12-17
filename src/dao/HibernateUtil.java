package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static SessionFactory factory;
	
	@SuppressWarnings("static-access")
	private HibernateUtil(){
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("../hibernate-cfg.xml");
		this.factory = cfg.buildSessionFactory();
	}
	
	public static Session getSession(){
		if(factory == null){
			@SuppressWarnings("unused")
			HibernateUtil util = new HibernateUtil();
		}
		return factory.openSession();
	}

}