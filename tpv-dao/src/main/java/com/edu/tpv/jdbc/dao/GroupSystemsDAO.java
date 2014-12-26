package com.edu.tpv.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.tpv.jdbc.connection.dao.ConnectionJDBC;
import com.edu.tpv.login.jdbc.dto.GroupSystemsTO;

public class GroupSystemsDAO
{
	public GroupSystemsDAO()
	{
	}
	
	public List<GroupSystemsTO> getMappingByGroupSystem(int group) throws SQLException
	{
		String sql = "select fiid,fvcnombre,fvcdescripcion from tbcgruposistemas where fiid=?";
		ResultSet rs = null;
		List<GroupSystemsTO> lstGroupSystems = new ArrayList<GroupSystemsTO>();
		PreparedStatement sentence = null;
		Connection con=null;
		
		try
		{
			ConnectionJDBC connectionJDBC= new ConnectionJDBC();
			con=connectionJDBC.getConexion();
			sentence = con.prepareStatement(sql);
			sentence.setInt(1, group);
			rs = sentence.executeQuery(sql);
			if (rs != null)
			{
				rs.beforeFirst();
				while (rs.next())
				{
					GroupSystemsTO g = new GroupSystemsTO();
					g.setId(rs.getInt("fiid"));
					g.setName(rs.getString("fvcnombre"));
					g.setDescription(rs.getString("fvcdescripcion"));
					lstGroupSystems.add(g);
				}
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			if (con != null)
			{
				con.close();
			}
			if (rs != null)
				rs.close();
		}
		return lstGroupSystems;

	}
}
