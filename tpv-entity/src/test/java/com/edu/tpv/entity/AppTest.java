package com.edu.tpv.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	Context ctx = null;
	Hashtable ht = new Hashtable();
	DataSource datasource;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @throws Exception 
     */
    public AppTest( String testName ) throws Exception
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
        assertTrue( true );
    }

	@Override
	protected void setUp() throws Exception {

		  ht.put(Context.INITIAL_CONTEXT_FACTORY,
		         "weblogic.jndi.WLInitialContextFactory");
		  ht.put(Context.PROVIDER_URL,
		         "t3://localhost:7001");

		  try {
		    ctx = new InitialContext(ht);
		    datasource= (DataSource) ctx.lookup("tesis.tpv");
		    Connection con=datasource.getConnection();
		    PreparedStatement pst= con.prepareStatement("insert into test(id,name) values(1,'test');");
		    pst.execute();
		    System.out.println("todo bien");
		  }
		  catch (NamingException e) {
			  e.printStackTrace();
		  }
		  finally {
		    try {ctx.close();}
		    catch (Exception e) {
		    }
		  }
	}
    
}
