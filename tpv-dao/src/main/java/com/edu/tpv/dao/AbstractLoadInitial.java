package com.edu.tpv.dao;

import com.edu.tpv.dao.util.ConnectionManager;


public abstract class AbstractLoadInitial
{
	public static ConnectionManager conexion= new ConnectionManager();
	
	public AbstractLoadInitial()
	{
		loadInitial(conexion);
	}
	public abstract void loadInitial(ConnectionManager conexion);
}
