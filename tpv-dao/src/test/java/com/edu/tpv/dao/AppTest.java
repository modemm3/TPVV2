package com.edu.tpv.dao;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edu.tpv.dao.loader.Sessionfactory;
import com.edu.tpv.dao.util.ConnectionManager;
import com.edu.tpv.login.entity.Sesiones;
import com.edu.tpv.login.entity.TipoAplicacion;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	public static Logger LOGGER = LoggerFactory.getLogger(AppTest.class.getName());
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        LOGGER.info("Starting test");
        ConnectionManager connectionManager=Sessionfactory.getInstance();
        Session session=connectionManager.currentSession();
        TipoAplicacion tipoAplicacion= new TipoAplicacion();
        tipoAplicacion.setId(20);
        tipoAplicacion.setNombre("test de datasource");
        tipoAplicacion.setDescripcion("test de datasource with hibarnate");
        session.beginTransaction();
        session.save(tipoAplicacion);
        session.getTransaction().commit();
    }
}
