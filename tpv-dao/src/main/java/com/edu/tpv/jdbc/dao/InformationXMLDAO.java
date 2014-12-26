package com.edu.tpv.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edu.tpv.jdbc.connection.dao.ConnectionJDBC;
import com.edu.tpv.login.jdbc.dto.ConfigurationXMLTO;

public class InformationXMLDAO {
	public static Logger LOGGER = LoggerFactory.getLogger(InformationXMLDAO.class
			.getName());

	public InformationXMLDAO() {
	}

	public List<ConfigurationXMLTO> selectAll() {

		String sql = "select *from tbcconfiguracionxml";
		LOGGER.info("STARTING selectAll() ");
		ResultSet rs = null;
		List<ConfigurationXMLTO> lstPropertyConfiguration = new ArrayList<ConfigurationXMLTO>();
		Statement sentencia = null;
		Connection connection = null;
		try {
			ConnectionJDBC connectionJDBC = new ConnectionJDBC();
			connection = connectionJDBC.getConexion();
			sentencia = connection.createStatement();
			rs = sentencia.executeQuery(sql);
			if (rs != null) {
				rs.beforeFirst();
				while (rs.next()) {
					ConfigurationXMLTO cnf = new ConfigurationXMLTO();
					cnf.setIdEnterprise(rs.getString("fvcid"));
					cnf.setDriverClass(rs.getString("fvcdriver_class"));
					cnf.setPassword(rs.getString("fvcpassword"));
					cnf.setUrl(rs.getString("fvcurl"));
					cnf.setUsername(rs.getString("fvcusername"));
					cnf.setDialect(rs.getString("fvcdialect"));
					cnf.setGroupSystems(rs.getInt("figruposistemasid"));
					lstPropertyConfiguration.add(cnf);
				}
				rs.close();

			}

		} catch (SQLException e) {
			LOGGER.error("ERROR TO EXECUTE QUERY [{}]", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lstPropertyConfiguration;
	}

	public List<ConfigurationXMLTO> selectAll(String idEmpresa) {
		String sql = "select *from tbcconfiguracionxml where figruposistemasid="
				+ idEmpresa;
		LOGGER.info("STARTING selectAll(empresa) [{}]",sql);
		ResultSet rs = null;
		List<ConfigurationXMLTO> lstPropertyConfiguration = new ArrayList<ConfigurationXMLTO>();
		Statement sentencia = null;
		Connection connection = null;
		try {
			ConnectionJDBC connectionJDBC = new ConnectionJDBC();
			connection = connectionJDBC.getConexion();
			sentencia = connection.createStatement();
			rs = sentencia.executeQuery(sql);
			if (rs != null) {
				rs.beforeFirst();
				while (rs.next()) {
					ConfigurationXMLTO cnf = new ConfigurationXMLTO();
					cnf.setIdEnterprise(rs.getString("fvcid"));
					cnf.setDriverClass(rs.getString("fvcdriver_class"));
					cnf.setPassword(rs.getString("fvcpassword"));
					cnf.setUrl(rs.getString("fvcurl"));
					cnf.setUsername(rs.getString("fvcusername"));
					cnf.setDialect(rs.getString("fvcdialect"));
					cnf.setDatasource(rs.getString("fvcdatasource"));
					cnf.setJndiClass(rs.getString("fvcjndiclass"));
					cnf.setJndiUrl(rs.getString("fvcjndiurl"));
					lstPropertyConfiguration.add(cnf);
				}
				rs.close();
			}

		} catch (SQLException e) {
			LOGGER.error("ERROR TO EXECUTE QUERY [{}]", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lstPropertyConfiguration;
	}

}
