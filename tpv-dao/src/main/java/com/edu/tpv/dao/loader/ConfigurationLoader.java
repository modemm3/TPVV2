package com.edu.tpv.dao.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationLoader {
	
	private static Logger LOGGER= LoggerFactory.getLogger(ConfigurationLoader.class.getName());
	/**
	 * instance utilized to recovery information to connect db
	 */
	private static ConfigurationLoader instance;
	/**
	 * file to read data information db
	 */
	private static String pathProperties="/DatosConexion.properties";
	
	private String host;
	private String port;
	private String user;
	private String password;
	private String db;
	private String initialDBId;
	
	public static ConfigurationLoader getInstance(){
		LOGGER.info("creation instance ConfigurationLoader");
		if(instance==null){
			instance= new ConfigurationLoader();
			InputStream archPropiedades=null;
			Properties properties =null;
			try {	
		    	 properties=new Properties();
				archPropiedades=ConfigurationLoader.class.getResourceAsStream(pathProperties);
				properties.load(archPropiedades);
				
					instance.setHost(properties.getProperty("host"));
					instance.setPort(properties.getProperty("port"));
					instance.setDb(properties.getProperty("db"));
					instance.setUser(properties.getProperty("user"));
					instance.setPassword(properties.getProperty("password"));
					instance.setInitialDBId(properties.getProperty("initialdbid"));
			} catch (FileNotFoundException e) {
				LOGGER.error("NOT LOADED FILE NOT FOUND "+pathProperties,e);
			} catch (IOException e) {
				LOGGER.error("ERROR TO LOAD configurationLoader ",e);
				e.printStackTrace();
			}
		}
		return instance;
	}

	public String getPathProperties() {
		return pathProperties;
	}

	public void setPathProperties(String pathProperties) {
		this.pathProperties = pathProperties;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getInitialDBId() {
		return initialDBId;
	}

	public void setInitialDBId(String initialDBId) {
		this.initialDBId = initialDBId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigurationLoader [pathProperties=")
				.append(pathProperties).append(", host=").append(host)
				.append(", port=").append(port).append(", user=").append(user)
				.append(", password=").append(password).append(", db=")
				.append(db).append(", initialDDId=").append(initialDBId)
				.append("]");
		return builder.toString();
	}


	

}
