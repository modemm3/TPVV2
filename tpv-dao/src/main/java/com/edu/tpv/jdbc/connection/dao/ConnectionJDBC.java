
package com.edu.tpv.jdbc.connection.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edu.tpv.dao.loader.ConfigurationLoader;
import com.edu.tpv.dao.util.ConnectionManager;

public class ConnectionJDBC
{
    private Connection conexion;
    public static Logger LOGGER= LoggerFactory.getLogger(ConnectionJDBC.class.getName());
    public Connection getConexion()
    {
		try
		{
			ConfigurationLoader configurationLoader= ConfigurationLoader.getInstance();
			LOGGER.info("GET CONNECTION TO DATABASE [{}]",configurationLoader);
			Class.forName("org.gjt.mm.mysql.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://" + configurationLoader.getHost() + ":"
							+ configurationLoader.getPort() + "/"
							+ configurationLoader.getDb(),
					configurationLoader.getUser(),
					configurationLoader.getPassword());
		}
		catch(Exception e)
		{
			LOGGER.error("ERROR CONNECTTING TO DATABASE [{}]",e);
		}
        return conexion;
    }

    public void setConexion(Connection conexion)
    {
        this.conexion = conexion;
    }
}
