package com.edu.tpv.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import com.edu.tpv.login.entity.InterfaceEntityBase;
@SuppressWarnings("unchecked")
public class OperationsDAO extends Connection implements OperationsDAOIfz
{
    private Session currentSesion;
    public OperationsDAO(String sesionKey)
    {
        this.sesionKey = sesionKey;
    }
    @Override
    public InterfaceEntityBase save(InterfaceEntityBase entity)
    {
        Session sesion;
        if (entity != null)
        {
            sesion=this.getSession();
            if (sesion == null)
            {
                throw new HibernateException("The session is null.");
            }
            Transaction tx = null;
            try
            {
                tx = sesion.beginTransaction();
                final Object o = sesion.save(entity);
                if (o instanceof InterfaceEntityBase)
                {
                    entity = (InterfaceEntityBase) o;
                }
                else
                {
                    if (o instanceof Integer)
                    {
                        entity.setId((Integer) o);
                    }
                }
                tx.commit();
                entity.setMensajeError("Guardado exitoso");
                entity.setStatus(BdConstant.EXITO);

            }
            catch (final HibernateException e)
            {
                entity.setMensajeError(e.getMessage());
                entity.setStatus(BdConstant.ERROR);
                tratarError(tx, sesion, e);
            }
            finally
            {
                closeSession();
            }
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase saveSinTx(InterfaceEntityBase entity)
    {
        if (entity != null)
        {
            final Session sesion = currentSesion;
            if (sesion == null)
            {
                throw new HibernateException("The session is null.");
            }
            try
            {
                final Object o = sesion.save(entity);
                if (o instanceof InterfaceEntityBase)
                {
                    entity = (InterfaceEntityBase) o;
                }
                else
                {
                    if (o instanceof Integer)
                    {
                        entity.setId((Integer) o);
                    }
                }
                entity.setStatus(BdConstant.EXITO);
            }
            catch (final HibernateException e)
            {
                entity.setStatus(BdConstant.ERROR);
                e.printStackTrace();
                throw e;
            }
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase modify(InterfaceEntityBase entity)
    {
        Session sesion;
        if (entity != null)
        {
            sesion=getSession();
            if (sesion == null)
            {
                throw new HibernateException("The session is null.");
            }
            Transaction tx = null;
            try
            {
                tx = sesion.beginTransaction();
                sesion.update(entity);
                tx.commit();
                entity.setStatus(BdConstant.EXITO);
            }
            catch (final HibernateException e)
            {
                entity.setStatus(BdConstant.ERROR);
                tratarError(tx, sesion, e);
            }
            finally
            {
                closeSession();
            }
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase modifySinTx(InterfaceEntityBase entity)
    {
        if (entity != null)
        {
            final Session sesion = currentSesion;
            if (sesion == null)
            {
                throw new HibernateException("The session is null.");
            }
            try
            {
                sesion.update(entity);
                entity.setStatus(BdConstant.EXITO);
            }
            catch (final HibernateException e)
            {
                entity.setStatus(BdConstant.ERROR);

            }
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase modifyByQuerySinTx(InterfaceEntityBase entity)
    {
        if (entity != null)
        {
            final Session sesion = currentSesion;
            if (sesion == null)
            {
                throw new HibernateException("The session is null.");
            }
            try
            {
                sesion.createQuery(entity.getQuery()).executeUpdate();
                entity.setStatus(BdConstant.EXITO);
            }
            catch (final HibernateException e)
            {
                entity.setStatus(BdConstant.ERROR);
                System.out.println(e);

            }
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase delete(InterfaceEntityBase entity)
    {
        final Session sesion=getSession();
        if (sesion == null)
        {
            return null;
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            sesion.delete(entity);
            tx.commit();
            entity.setStatus(BdConstant.EXITO);
        }
        catch (final HibernateException e0)
        {
            entity.setStatus(BdConstant.ERROR);
            tratarError(tx,sesion, e0);
        }
        finally
        {
            closeSession();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase deleteSintTx(InterfaceEntityBase entity)
    {
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            return entity;
        }
        try
        {
            sesion.delete(entity);
            entity.setStatus(BdConstant.EXITO);
        }
        catch (final HibernateException e0)
        {
            entity.setStatus(BdConstant.ERROR);
            throw e0;
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase deleteByQuerySintTx(InterfaceEntityBase entity)
    {
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            return entity;
        }
        try
        {
            sesion.createQuery(entity.getQuery()).executeUpdate();
            entity.setStatus(BdConstant.EXITO);
        }
        catch (final HibernateException e0)
        {
            entity.setStatus(BdConstant.ERROR);
            throw e0;
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase findById(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        Session sesion;
        sesion=getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.id = " + entity.getId());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            final ListIterator<InterfaceEntityBase> litT = resultsT.listIterator();
            tx.rollback();
            entity.setStatus(BdConstant.OBJETO_VACIO);
            while (litT.hasNext())
            {
                entity = litT.next();
                entity.setStatus(BdConstant.EXITO);

            }
        }
        catch (final HibernateException e)
        {
            entity.setStatus(BdConstant.ERROR);
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase findByIdC(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        Session sesion;
        sesion=getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.idC = '" + entity.getIdC()+"'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            final ListIterator<InterfaceEntityBase> litT = resultsT.listIterator();
            tx.rollback();
            entity.setStatus(BdConstant.OBJETO_VACIO);
            while (litT.hasNext())
            {
                entity = litT.next();
                entity.setStatus(BdConstant.EXITO);
            }
        }
        catch (final HibernateException e)
        {
            entity.setStatus(BdConstant.ERROR);
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase findByIdSinTx(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.id = " + entity.getId());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            final ListIterator<InterfaceEntityBase> litT = resultsT.listIterator();
            entity.setStatus(BdConstant.OBJETO_VACIO);
            while (litT.hasNext())
            {
                entity.setStatus(BdConstant.EXITO);
                entity = litT.next();
            }
        }
        catch (final HibernateException e)
        {
            entity.setStatus(BdConstant.ERROR);
            e.printStackTrace();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase findByIdCSinTx(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.idC = '" + entity.getIdC()+"'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            final ListIterator<InterfaceEntityBase> litT = resultsT.listIterator();
            entity.setStatus(BdConstant.OBJETO_VACIO);
            while (litT.hasNext())
            {
                entity.setStatus(BdConstant.EXITO);
                entity = litT.next();
            }
        }
        catch (final HibernateException e)
        {
            entity.setStatus(BdConstant.ERROR);
            e.printStackTrace();
        }
        return entity;
    }
    @Override
    public LinkedList<InterfaceEntityBase> listDataLL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        //		sesion=getSession();
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final LinkedList<InterfaceEntityBase> results = new LinkedList<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public ArrayList<InterfaceEntityBase> listDataAL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        ArrayList<InterfaceEntityBase> results = new ArrayList<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            results = (ArrayList<InterfaceEntityBase>) catalogoOql.list();
            tx.rollback();
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public ArrayList<InterfaceEntityBase> listDataALPaginate(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        ArrayList<InterfaceEntityBase> results = new ArrayList<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            catalogoOql.setMaxResults(entity.getFinalReg());
            catalogoOql.setFirstResult(entity.getInicialReg());
            results = (ArrayList<InterfaceEntityBase>) catalogoOql.list();
            tx.rollback();
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public Vector<InterfaceEntityBase> listDataV(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final Vector<InterfaceEntityBase> results = new Vector<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public TreeSet<InterfaceEntityBase> listDataTS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final TreeSet<InterfaceEntityBase> results = new TreeSet<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public LinkedHashSet<InterfaceEntityBase> listDataLHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final LinkedHashSet<InterfaceEntityBase> results = new LinkedHashSet<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public HashSet<InterfaceEntityBase> listDataHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final HashSet<InterfaceEntityBase> results = new HashSet<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public Hashtable<Integer, InterfaceEntityBase> listDataHT(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final Hashtable<Integer, InterfaceEntityBase> results = new Hashtable<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public TreeMap<Integer, InterfaceEntityBase> listDataTM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final TreeMap<Integer, InterfaceEntityBase> results = new TreeMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public LinkedHashMap<Integer, InterfaceEntityBase> listDataLHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final LinkedHashMap<Integer, InterfaceEntityBase> results = new LinkedHashMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public IdentityHashMap<Integer, InterfaceEntityBase> listDataIHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final IdentityHashMap<Integer, InterfaceEntityBase> results = new IdentityHashMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public HashMap<Integer, InterfaceEntityBase> listDataHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final HashMap<Integer, InterfaceEntityBase> results = new HashMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                if (objeto instanceof SearchIfz) {
                    ((SearchIfz) objeto).despuesDeBuscar(this);
                }
                results.put(objeto.getId(), objeto);
            }
            tx.commit();
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            sesion.close();
        }
        return results;
    }
    @Override
    public WeakHashMap<Integer, InterfaceEntityBase> listDataWHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final WeakHashMap<Integer, InterfaceEntityBase> results = new WeakHashMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public LinkedList<InterfaceEntityBase> listDataSinTxLL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final LinkedList<InterfaceEntityBase> results = new LinkedList<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public ArrayList<InterfaceEntityBase> listDataSinTxAL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final ArrayList<InterfaceEntityBase> results = new ArrayList<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public Vector<InterfaceEntityBase> listDataSinTxV(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final Vector<InterfaceEntityBase> results = new Vector<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public TreeSet<InterfaceEntityBase> listDataSinTxTS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final TreeSet<InterfaceEntityBase> results = new TreeSet<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public LinkedHashSet<InterfaceEntityBase> listDataSinTxLHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final LinkedHashSet<InterfaceEntityBase> results = new LinkedHashSet<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public HashSet<InterfaceEntityBase> listDataSinTxHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final HashSet<InterfaceEntityBase> results = new HashSet<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public Hashtable<Integer, InterfaceEntityBase> listDataSinTxHT(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final Hashtable<Integer, InterfaceEntityBase> results = new Hashtable<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public TreeMap<Integer, InterfaceEntityBase> listDataSinTxTM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final TreeMap<Integer, InterfaceEntityBase> results = new TreeMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public LinkedHashMap<Integer, InterfaceEntityBase> listDataSinTxLHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final LinkedHashMap<Integer, InterfaceEntityBase> results = new LinkedHashMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public IdentityHashMap<Integer, InterfaceEntityBase> listDataSinTxIHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final IdentityHashMap<Integer, InterfaceEntityBase> results = new IdentityHashMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public HashMap<Integer, InterfaceEntityBase> listDataSinTxHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final HashMap<Integer, InterfaceEntityBase> results = new HashMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public WeakHashMap<Integer, InterfaceEntityBase> listDataSinTxWHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final WeakHashMap<Integer, InterfaceEntityBase> results = new WeakHashMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public LinkedList<InterfaceEntityBase> findByDescriptionLL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final LinkedList<InterfaceEntityBase> results = new LinkedList<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public ArrayList<InterfaceEntityBase> findByDescriptionAL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final ArrayList<InterfaceEntityBase> results = new ArrayList<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public Vector<InterfaceEntityBase> findByDescriptionV(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final Vector<InterfaceEntityBase> results = new Vector<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public TreeSet<InterfaceEntityBase> findByDescriptionTS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final TreeSet<InterfaceEntityBase> results = new TreeSet<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public LinkedHashSet<InterfaceEntityBase> findByDescriptionLHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final LinkedHashSet<InterfaceEntityBase> results = new LinkedHashSet<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public HashSet<InterfaceEntityBase> findByDescriptionHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final HashSet<InterfaceEntityBase> results = new HashSet<InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public Hashtable<Integer, InterfaceEntityBase> findByDescriptionHT(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final Hashtable<Integer, InterfaceEntityBase> results = new Hashtable<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public TreeMap<Integer, InterfaceEntityBase> findByDescriptionTM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final TreeMap<Integer, InterfaceEntityBase> results = new TreeMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public LinkedHashMap<Integer, InterfaceEntityBase> findByDescriptionLHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final LinkedHashMap<Integer, InterfaceEntityBase> results = new LinkedHashMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public IdentityHashMap<Integer, InterfaceEntityBase> findByDescriptionIHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final IdentityHashMap<Integer, InterfaceEntityBase> results = new IdentityHashMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public HashMap<Integer, InterfaceEntityBase> findByDescriptionHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final HashMap<Integer, InterfaceEntityBase> results = new HashMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public WeakHashMap<Integer, InterfaceEntityBase> findByDescriptionWHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final WeakHashMap<Integer, InterfaceEntityBase> results = new WeakHashMap<Integer, InterfaceEntityBase>();
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public LinkedList<InterfaceEntityBase> findByDescriptionSinTxLL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final LinkedList<InterfaceEntityBase> results = new LinkedList<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public ArrayList<InterfaceEntityBase> findByDescriptionSinTxAL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final ArrayList<InterfaceEntityBase> results = new ArrayList<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public Vector<InterfaceEntityBase> findByDescriptionSinTxV(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final Vector<InterfaceEntityBase> results = new Vector<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public TreeSet<InterfaceEntityBase> findByDescriptionSinTxTS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final TreeSet<InterfaceEntityBase> results = new TreeSet<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public LinkedHashSet<InterfaceEntityBase> findByDescriptionSinTxLHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final LinkedHashSet<InterfaceEntityBase> results = new LinkedHashSet<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public HashSet<InterfaceEntityBase> findByDescriptionSinTxHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final HashSet<InterfaceEntityBase> results = new HashSet<InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public Hashtable<Integer, InterfaceEntityBase> findByDescriptionSinTxHT(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final Hashtable<Integer, InterfaceEntityBase> results = new Hashtable<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public TreeMap<Integer, InterfaceEntityBase> findByDescriptionSinTxTM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final TreeMap<Integer, InterfaceEntityBase> results = new TreeMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public LinkedHashMap<Integer, InterfaceEntityBase> findByDescriptionSinTxLHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final LinkedHashMap<Integer, InterfaceEntityBase> results = new LinkedHashMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public IdentityHashMap<Integer, InterfaceEntityBase> findByDescriptionSinTxIHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final IdentityHashMap<Integer, InterfaceEntityBase> results = new IdentityHashMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public HashMap<Integer, InterfaceEntityBase> findByDescriptionSinTxHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final HashMap<Integer, InterfaceEntityBase> results = new HashMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public WeakHashMap<Integer, InterfaceEntityBase> findByDescriptionSinTxWHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        final WeakHashMap<Integer, InterfaceEntityBase> results = new WeakHashMap<Integer, InterfaceEntityBase>();
        try
        {
            catalogoOql = sesion.createQuery("select o from " + entity.getClass().getName() + " o where o.descripcion like '" + entity.getDescripcion() + "'");
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
            return results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public LinkedList<InterfaceEntityBase> findByQueryLL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final LinkedList<InterfaceEntityBase> results = new LinkedList<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public ArrayList<InterfaceEntityBase> findByQueryAL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final ArrayList<InterfaceEntityBase> results = new ArrayList<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public Vector<InterfaceEntityBase> findByQueryV(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final Vector<InterfaceEntityBase> results = new Vector<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public TreeSet<InterfaceEntityBase> findByQueryTS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final TreeSet<InterfaceEntityBase> resultados = new TreeSet<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                resultados.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return resultados;
    }
    @Override
    public LinkedHashSet<InterfaceEntityBase> findByQueryLHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final LinkedHashSet<InterfaceEntityBase> results = new LinkedHashSet<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public HashSet<InterfaceEntityBase> findByQueryHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final HashSet<InterfaceEntityBase> results = new HashSet<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public Hashtable<Integer, InterfaceEntityBase> findByQueryHT(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final Hashtable<Integer, InterfaceEntityBase> results = new Hashtable<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public TreeMap<Integer, InterfaceEntityBase> findByQueryTM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final TreeMap<Integer, InterfaceEntityBase> results = new TreeMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public LinkedHashMap<Integer, InterfaceEntityBase> findByQueryLHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final LinkedHashMap<Integer, InterfaceEntityBase> results = new LinkedHashMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public IdentityHashMap<Integer, InterfaceEntityBase> findByQueryIHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final IdentityHashMap<Integer, InterfaceEntityBase> results = new IdentityHashMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public HashMap<Integer, InterfaceEntityBase> findByQueryHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final HashMap<Integer, InterfaceEntityBase> results = new HashMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public WeakHashMap<Integer, InterfaceEntityBase> findByQueryWHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = getSession();
        final WeakHashMap<Integer, InterfaceEntityBase> results = new WeakHashMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            tx.rollback();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public LinkedList<InterfaceEntityBase> findByQuerySinTxLL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final LinkedList<InterfaceEntityBase> results = new LinkedList<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public ArrayList<InterfaceEntityBase> findByQuerySinTxAL(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final ArrayList<InterfaceEntityBase> results = new ArrayList<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public Vector<InterfaceEntityBase> findByQuerySinTxV(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final Vector<InterfaceEntityBase> results = new Vector<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();

            for (final InterfaceEntityBase objeto : resultsT)
            {
                objeto.setStatus(BdConstant.EXITO);
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public TreeSet<InterfaceEntityBase> findByQuerySinTxTS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final TreeSet<InterfaceEntityBase> results = new TreeSet<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public LinkedHashSet<InterfaceEntityBase> findByQuerySinTxLHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final LinkedHashSet<InterfaceEntityBase> results = new LinkedHashSet<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public HashSet<InterfaceEntityBase> findByQuerySinTxHS(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final HashSet<InterfaceEntityBase> results = new HashSet<InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.add(objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public Hashtable<Integer, InterfaceEntityBase> findByQuerySinTxHT(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final Hashtable<Integer, InterfaceEntityBase> results = new Hashtable<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public TreeMap<Integer, InterfaceEntityBase> findByQuerySinTxTM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final TreeMap<Integer, InterfaceEntityBase> result = new TreeMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                result.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
    @Override
    public LinkedHashMap<Integer, InterfaceEntityBase> findByQuerySinTxLHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final LinkedHashMap<Integer, InterfaceEntityBase> results = new LinkedHashMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public IdentityHashMap<Integer, InterfaceEntityBase> findByQuerySinTxIHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final IdentityHashMap<Integer, InterfaceEntityBase> results = new IdentityHashMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public HashMap<Integer, InterfaceEntityBase> findByQuerySinTxHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final HashMap<Integer, InterfaceEntityBase> results = new HashMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    @Override
    public WeakHashMap<Integer, InterfaceEntityBase> findByQuerySinTxWHM(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        final WeakHashMap<Integer, InterfaceEntityBase> results = new WeakHashMap<Integer, InterfaceEntityBase>();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            for (final InterfaceEntityBase objeto : resultsT)
            {
                results.put(objeto.getId(), objeto);
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
        return results;
    }
    public void tratarError(Transaction transaccion, Session sesion, HibernateException error) throws HibernateException
    {
        error.printStackTrace();
        try
        {
            if (transaccion != null)
            {
                try
                {
                    transaccion.rollback();
                }
                catch (final HibernateException e1)
                {
                    e1.printStackTrace();
                    throw e1;
                }
                throw error;
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public TransaccionHibernateIfz launchesTransaction(TransaccionHibernateIfz tx) throws Exception
    {
        if (tx != null)
        {
            try
            {
                currentSesion=getSession();
                if (currentSesion == null)
                {
                    throw new HibernateException("The session is null.");
                }
                currentSesion.beginTransaction();
                tx.Transaction(this);
                currentSesion.getTransaction().commit();
            } catch (final Exception e)
            {
                throw e;
            }finally{
                //closeSession();
            }

        }
        return tx;
    }
    @Override
    public TransactionHibernateQueryIfz launchesTransactionQuery(TransactionHibernateQueryIfz tx) throws Exception
    {
        if (tx != null)
        {
            try
            {
                currentSesion=getSession();
                if (currentSesion == null)
                {
                    throw new HibernateException("The session is null.");
                }
                currentSesion.beginTransaction();
                tx.QueryTransaction(this);
                currentSesion.getTransaction().rollback();
            } catch (final Exception e)
            {
                throw e;
            }finally{
                //closeSession();
            }
        }
        return tx;
    }
    @Override
    public Timestamp getDateCurrentSinTx() throws Exception
    {
        if (currentSesion == null)
        {
            System.out.println("The session is null.");
            throw new HibernateException("The session is null.");
        }
        Timestamp currentDate = new Timestamp((new Date()).getTime());
        try
        {
            final String sql = "Select current_timestamp() as fec";
            final Query query = currentSesion.createSQLQuery(sql).addScalar("fec", org.hibernate.type.TimestampType.INSTANCE);
            final Object row = query.uniqueResult();
            currentDate = (Timestamp) row;
        }
        catch (final Exception e)
        {
            System.out.println("Exception in (getDateCurrent).");
            e.printStackTrace();
            throw e;
        }
        return currentDate;
    }
    @Override
    public Timestamp getDateCurrent() throws Exception
    {
        final Session sesion=getSession();
        if (sesion == null)
        {
            System.out.println("The session is null.");
            throw new HibernateException("The session is null.");
        }
        Timestamp currentDate = new Timestamp((new Date()).getTime());
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            currentDate = getDateCurrentSinTx();
            tx.commit();
        }
        catch (final Exception e)
        {
            System.out.println("Exception in (getDateCurrent).");
            e.printStackTrace();
            if (tx != null)
            {
                try
                {
                    tx.rollback();
                    tx.commit();
                }
                catch (final HibernateException e1)
                {
                    System.out.println("HibernateException in getDateCurrent(rollback).");
                    e1.printStackTrace();
                    throw e1;
                }
            }
            throw e;
        }
        finally
        {
            try
            {
            }
            catch (final HibernateException e2)
            {
                System.out.println("HibernateException in getDateCurrent(close).");
                throw e2;
            }
        }
        return currentDate;
    }
    @Override
    public InterfaceEntityBase getById(InterfaceEntityBase entity)
    {
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            entity = (InterfaceEntityBase) sesion.load(entity.getClass(), entity.getId());
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase getByIdSinTx(InterfaceEntityBase entity)
    {
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            entity = (InterfaceEntityBase) sesion.load(entity.getClass(), entity.getId());
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase getByIdC(InterfaceEntityBase entity)
    {
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            entity = (InterfaceEntityBase) sesion.load(entity.getClass(), entity.getIdC());
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public InterfaceEntityBase getByIdCSinTx(InterfaceEntityBase entity)
    {
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            entity = (InterfaceEntityBase) sesion.load(entity.getClass(), entity.getIdC());
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase findEntityByQuery(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        Session sesion;
        sesion=getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            final ListIterator<InterfaceEntityBase> litT = resultsT.listIterator();
            tx.rollback();
            while (litT.hasNext())
            {
                entity = litT.next();
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase findEntityByQuerySinTx(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            final List<InterfaceEntityBase> resultsT = catalogoOql.list();
            final ListIterator<InterfaceEntityBase> litT = resultsT.listIterator();
            while (litT.hasNext())
            {
                entity = litT.next();
            }
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase getLastId(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        Session sesion;
        sesion=getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery("select max(o.id) from " + entity.getClass().getName() + " o '");
            entity.setId(Integer.parseInt(catalogoOql.uniqueResult().toString()));
            entity.setId(entity.getId()+1);
            tx.rollback();
        }
        catch (final NullPointerException e) {
            entity.setId(1);
            tx.rollback();
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return entity;
    }
    @Override
    public InterfaceEntityBase getLastIdSintTx(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery("select max(o.id) from " + entity.getClass().getName() + " o ");
            entity.setId(Integer.parseInt(catalogoOql.uniqueResult().toString()));
            entity.setId(entity.getId()+1);
        }
        catch (final NullPointerException e) {
            entity.setId(1);
        }
        catch (final HibernateException e)
        {
            entity.setId(1);
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public InterfaceEntityBase getTotalRegSinTx(InterfaceEntityBase entity) {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery("select count(*) from " + entity.getClass().getName() + " o ");
            entity.setTotalRegistros(Integer.parseInt(catalogoOql.uniqueResult().toString()));
            entity.setStatus(BdConstant.EXITO);
        }
        catch (final HibernateException e)
        {
            entity.setStatus(BdConstant.ERROR);
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public InterfaceEntityBase getTotalRegByQuerySinTx(InterfaceEntityBase entity) {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            entity.setTotalRegistros(Integer.parseInt(catalogoOql.uniqueResult().toString()));
            entity.setStatus(BdConstant.EXITO);
        }
        catch (final HibernateException e)
        {
            entity.setStatus(BdConstant.ERROR);
            e.printStackTrace();
        }
        return entity;
    }
    @Override
    public ArrayList<Object> findByQueryJoinSinTxAL(
            InterfaceEntityBase entity) {
        Query catalogoOql;
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        List<Object> results = new ArrayList<Object>();
        try
        {
            catalogoOql = sesion.createQuery(entity.getQuery());
            catalogoOql.setMaxResults(entity.getFinalReg());
            catalogoOql.setFirstResult(entity.getInicialReg());
            results = catalogoOql.list();
            return (ArrayList<Object>) results;
        }
        catch (final HibernateException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public ArrayList<List<Object>> listDataSQLAL(InterfaceEntityBase entity)
    {
        final Session sesion = getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final ArrayList<List<Object>> results = new ArrayList<List<Object>>();
        try
        {
            tx = sesion.beginTransaction();
            final java.sql.Connection con=((SessionImpl) sesion).connection();

            final PreparedStatement sentencia=con.prepareStatement(entity.getQuery());

            final ResultSet resultado=sentencia.executeQuery(entity.getQuery());
            while(resultado.next()){
                final ArrayList<Object> items= new ArrayList<Object>();
                for(int i=1;i<=resultado.getMetaData().getColumnCount();i++){
                    items.add(resultado.getObject(i));
                }
                results.add(items);
            }
            tx.rollback();
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            closeSession();
        }
        return results;
    }
    @Override
    public ArrayList<List<Object>> listDataSQLSinTxAL(InterfaceEntityBase entity)
    {
        final Session sesion = currentSesion;
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        final ArrayList<List<Object>> results = new ArrayList<List<Object>>();
        try
        {
            tx = sesion.beginTransaction();
            final java.sql.Connection con=((SessionImpl)sesion).connection();
            final PreparedStatement sentencia=con.prepareStatement(entity.getQuery());
            final ResultSet resultado=sentencia.executeQuery(entity.getQuery());
            while(resultado.next()){
                final ArrayList<Object> items= new ArrayList<Object>();
                for(int i=1;i<=resultado.getMetaData().getColumnCount();i++){
                    items.add(resultado.getObject(i));
                }
                results.add(items);
            }
        }
        catch (final HibernateException e)
        {
            tratarError(tx, sesion, e);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        finally
        {
        }
        return results;
    }

    @Override
    public InterfaceEntityBase executeACID(InterfaceEntityBase entity)
    {
        Query catalogoOql;
        Session sesion;
        sesion=getSession();
        if (sesion == null)
        {
            throw new HibernateException("The session is null.");
        }
        Transaction tx = null;
        int result = 0;
        try
        {
            tx = sesion.beginTransaction();
            catalogoOql = sesion.createQuery(entity.getQuery());
            result = catalogoOql.executeUpdate();
            if(result>0) {
                entity.setStatus(BdConstant.EXITO);
            } else {
                entity.setStatus(BdConstant.OBJETO_VACIO);
            }

            tx.commit();
        }
        catch (final NullPointerException e) {
            tx.rollback();
        }
        catch (final HibernateException e)
        {
            tx.rollback();
            tratarError(tx, sesion, e);
        }
        finally
        {
            closeSession();
        }
        return entity;
    }

    @Override
    public InterfaceEntityBase executeACIDSinTx(InterfaceEntityBase entity)
    {
        if (entity != null)
        {
            int result=0;
            final Session sesion = currentSesion;
            Query catalogoOql;
            if (sesion == null)
            {
                throw new HibernateException("The session is null.");
            }
            try
            {
                catalogoOql = sesion.createQuery(entity.getQuery());
                result = catalogoOql.executeUpdate();
                if(result>0) {
                    entity.setStatus(BdConstant.EXITO);
                } else {
                    entity.setStatus(BdConstant.OBJETO_VACIO);
                }
            }
            catch (final HibernateException e)
            {
                entity.setStatus(BdConstant.ERROR);

            }
        }
        return entity;
    }

}
