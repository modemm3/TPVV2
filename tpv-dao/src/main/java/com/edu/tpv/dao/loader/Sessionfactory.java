package com.edu.tpv.dao.loader;

import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edu.tpv.dao.util.ConnectionManager;
import com.edu.tpv.jdbc.dao.InformationXMLDAO;
import com.edu.tpv.jdbc.dao.MappingDAO;
import com.edu.tpv.login.jdbc.dto.ConfigurationXMLTO;
import com.edu.tpv.login.jdbc.dto.MappingTO;

public final class Sessionfactory {

	public static Logger LOGGER = LoggerFactory.getLogger(Sessionfactory.class.getName());
	private static ConnectionManager connectionManager;
	private static ConfigurationLoader configurationLoader;

	public static ConnectionManager getInstance() {
		LOGGER.info("CREATING INSTANCE Sessionfactory");
		if (connectionManager == null) {
			configurationLoader = ConfigurationLoader.getInstance();
			connectionManager = new ConnectionManager();
			LoaderSessionsFactory();
		}
		return connectionManager;
	}

	private static void LoaderSessionsFactory() {
		List<ConfigurationXMLTO> lstCnf = null;
		LOGGER.info("LOADING SESSIONS FACTORY");
		InformationXMLDAO infCargaInicial = new InformationXMLDAO();
		lstCnf = infCargaInicial.selectAll(configurationLoader.getInitialDBId());
		LOGGER.info("Configuration List size  [{}]",lstCnf.size());
		ListIterator<ConfigurationXMLTO> litT = lstCnf.listIterator();
		while (litT.hasNext()) {
			ConfigurationXMLTO cnf = new ConfigurationXMLTO();
			cnf = litT.next();
			try {
				connectionManager.addConfigFile(createDocument(cnf), cnf.getIdEnterprise());
			} catch (HibernateException e) {
				LOGGER.error("ERROR TO LOAD SESSION FACTORY [{}] [{}]",cnf,e);
			} catch (Exception e) {
				LOGGER.error("ERROR [{}]",e);
			}
		}
	}

	private static org.w3c.dom.Document createDocument(ConfigurationXMLTO cnfVO) throws Exception {

		Document documento = DocumentHelper.createDocument();
		LOGGER.info("CREATING DOCUMENT [{}] ",cnfVO);
		documento.addDocType("hibernate-configuration", "-//Hibernate/Hibernate Configuration DTD 3.0//EN", "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd");
		Element padre = documento.addElement("hibernate-configuration");
		Element hijo = padre.addElement("session-factory");
		Element nieto2 = hijo.addElement("property").addAttribute("name", "jndi.url");
		nieto2.setText(cnfVO.getJndiUrl());
		Element nieto3 = hijo.addElement("property").addAttribute("name", "jndi.class");
		nieto3.setText(cnfVO.getJndiClass());
		Element nieto4 = hijo.addElement("property").addAttribute("name", "connection.datasource");
		nieto4.setText(cnfVO.getDatasource());
		MappingDAO mapeo = new MappingDAO();
		List<MappingTO> lstMapeos = mapeo.getMappingByGroupSystem(Integer.parseInt(configurationLoader.getInitialDBId()));
		for (MappingTO m : lstMapeos) {
			hijo.addElement("mapping").addAttribute("resource", m.getName());
		}
		LOGGER.info("Document: [{}]",documento.asXML());
		return loadXMLFrom(documento.asXML());
	}

	private static org.w3c.dom.Document loadXMLFrom(String xml) throws org.xml.sax.SAXException, java.io.IOException {
		return loadXMLFrom(new java.io.ByteArrayInputStream(xml.getBytes()));
	}

	private static org.w3c.dom.Document loadXMLFrom(java.io.InputStream is) throws org.xml.sax.SAXException, java.io.IOException {
		javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(false);
		javax.xml.parsers.DocumentBuilder builder = null;
		try {
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			builder = factory.newDocumentBuilder();
		} catch (javax.xml.parsers.ParserConfigurationException ex) {
		}
		org.w3c.dom.Document doc = builder.parse(is);
		is.close();
		return doc;
	}
}
