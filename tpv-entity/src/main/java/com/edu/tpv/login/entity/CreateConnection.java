package com.edu.tpv.login.entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class CreateConnection {

	public static void main(String[] args) {
		SessionFactory sessionFactory;
	    ServiceRegistry serviceRegistry;

	    try
	        {
//	          Configuration configuration = new Configuration();
	            Configuration configuration = new Configuration().configure();

	            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            Session s=sessionFactory.openSession();
	            Test t= new Test();
	            t.setId(5);
	            t.setName("perita de new");
	            s.beginTransaction();
	            s.save(t);
	            s.getTransaction().commit();
	        }
	        catch (HibernateException he)
	        {
	            System.err.println("Error creating Session: " + he);
	            throw new ExceptionInInitializerError(he);
	        }
	    
	    
	}
}
