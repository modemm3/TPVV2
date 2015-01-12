package com.edu.tpv.dao;

import org.hibernate.Session;

public interface ConnectionIfz
{
	public Session getSession();
	public void closeSession();
}
