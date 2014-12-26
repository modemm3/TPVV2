package com.edu.tpv.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edu.tpv.jdbc.connection.dao.ConnectionJDBC;
import com.edu.tpv.login.jdbc.dto.MappingTO;

public class MappingDAO {
	public static Logger LOGGER = LoggerFactory.getLogger(MappingDAO.class.getName());

	public MappingDAO() {

	}

	public List<MappingTO> getMappingByGroupSystem(int group) throws SQLException {
		// TODO: solo obtener los mappings activos, en espera de cambio final.
		String sql = "select fiid,fvcnombre,figruposistemasid from tbcmapeos where figruposistemasid=? and ftiactivo = 1";
		LOGGER.info("STARTING selectAll(empresa) ");
		ResultSet rs = null;
		List<MappingTO> lstMapping = new ArrayList<MappingTO>();
		PreparedStatement sentencia = null;
		Connection connection = null;
		try {
			ConnectionJDBC connectionJDBC = new ConnectionJDBC();
			connection = connectionJDBC.getConexion();
			sentencia = connection.prepareStatement(sql);
			sentencia.setInt(1, group);
			rs = sentencia.executeQuery();
			if (rs != null) {
				rs.beforeFirst();
				while (rs.next()) {
					MappingTO mapeo = new MappingTO();
					mapeo.setId(rs.getInt("fiid"));
					mapeo.setName(rs.getString("fvcnombre"));
					mapeo.setDescription(rs.getString("figruposistemasid"));
					lstMapping.add(mapeo);
				}
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
		return lstMapping;

	}
}
