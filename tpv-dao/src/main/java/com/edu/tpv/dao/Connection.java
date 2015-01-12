package com.edu.tpv.dao;

import org.hibernate.Session;


public class Connection implements ConnectionIfz
{
	protected String sesionKey;
	@Override
	public Session getSession()
	{
		Session sesion;
		try
		{
			sesion = AbstractLoadInitial.conexion.currentSession(sesionKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return sesion;
	}
	@Override
	public void closeSession()
	{
		AbstractLoadInitial.conexion.closeSession(sesionKey);
	}

}
