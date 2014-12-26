package com.edu.tpv.dao.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edu.tpv.dao.loader.ConfigurationLoader;

public class ConnectionManager {
	public static final String DEFAULT = "01";
	private static final Map<String, SessionFactory> sessionFactorys = new HashMap<String, SessionFactory>();
	public static Logger LOGGER= LoggerFactory.getLogger(ConnectionManager.class.getName());
	/**
	 * Atributo unico por threat donde se guardan las sesiones para cada uno de
	 * los ficheros de configuracion.
	 */
	private static final ThreadLocal<Map<String, Session>> sessions = new ThreadLocal<Map<String, Session>>();
	static {

		sessions.set(new HashMap<String, Session>());
	}

	public void addConfigFile(org.w3c.dom.Document documento, String key)
			throws HibernateException {
		try {
			
			Configuration configuration = new Configuration().configure(documento);
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			sessionFactorys.put(key, sessionFactory);
		} catch (Exception ex) {
			LOGGER.error("Error to create session factory:"+documento.toString()+" key: "+key, ex);
			throw new HibernateException(ex.getMessage());
		}
	}

	public Session currentSession(String key) throws HibernateException {
		Session s = null;
		s = sessionFactorys.get(key).openSession();
		return s;
	}

	public void closeSession(String key) throws HibernateException {
		try
		{
			Map<String, Session> ss = sessions.get();
			Session s = ss.get(key);
			if (s != null)
			{
				s.close();
				ss.put(key, null);
			}
		} catch (Exception ex)
		{

		}
	}

	public Session currentSession() throws HibernateException {
		return currentSession(DEFAULT);
	}

	public void closeSession() throws HibernateException {
		closeSession(DEFAULT);
	}
//	public static void main(String[] args) {
//		ConfigurationLoader configurationLoader = new ConfigurationLoader().getInstance();
//		LOGGER.info("show db name: "+configurationLoader.getDb());
//		LOGGER.trace("Hello World!");
//		LOGGER.debug("How are you today?");
//		LOGGER.info("I am fine.");
//		LOGGER.warn("I love programming.");
//		LOGGER.error("I am programming.");
//	}
}
