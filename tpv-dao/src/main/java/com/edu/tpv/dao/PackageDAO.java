
package com.edu.tpv.dao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class PackageDAO
{
	private OperationsDAOIfz dao;
	public PackageDAO(String sesionKey)
	{
	dao= new OperationsDAO(sesionKey);	
	}
	public Vector<InterfaceEntityBase> listDataSin(InterfaceEntityBase entity)
	{
		return dao.listDataSinTxV(entity);
	}
	public HashMap<Integer, InterfaceEntityBase> listDataSinTxHM(InterfaceEntityBase entity)
	{
		return dao.listDataSinTxHM(entity);
	}
	public InterfaceEntityBase modifySinTx(InterfaceEntityBase entity)
	{
		return dao.modifySinTx(entity);
	}
	public InterfaceEntityBase modify(InterfaceEntityBase entity)
	{
		return dao.modify(entity);
	}
	public InterfaceEntityBase deleteSinTx(InterfaceEntityBase entity)
	{
		return dao.deleteSintTx(entity);
	}
	public InterfaceEntityBase saveSinTx(InterfaceEntityBase entity)
	{
		return dao.saveSinTx(entity);
	}
	public InterfaceEntityBase findById(InterfaceEntityBase entity)
	{
		return dao.findById(entity);
	}
	public InterfaceEntityBase findByIdSinTx(InterfaceEntityBase entity)
	{
		return dao.findByIdSinTx(entity);
	}
	public InterfaceEntityBase findByIdC(InterfaceEntityBase entity)
	{
		return dao.findByIdC(entity);
	}
	public InterfaceEntityBase findByIdCSinTx(InterfaceEntityBase entity)
	{
		return dao.findByIdCSinTx(entity);
	}
	public HashMap<Integer, InterfaceEntityBase> listDataHM(InterfaceEntityBase entity)
	{
		return dao.listDataHM(entity);
	}
	public Timestamp getFechaActualSinTx() throws Exception
	{
		return dao.getDateCurrentSinTx();
	}
	public TransactionHibernateQueryIfz launchesTransactionQuery(TransactionHibernateQueryIfz tx) throws Exception
	{
		return dao.launchesTransactionQuery(tx);
	}
	public TransaccionHibernateIfz launchesTransaction(TransaccionHibernateIfz tx) throws Exception
	{
		return dao.launchesTransaction(tx);
	}
	public List<InterfaceEntityBase> listDataAL(InterfaceEntityBase entity)
	{
		return dao.listDataAL(entity);
	}
	public List<InterfaceEntityBase> listDataALPaginate(InterfaceEntityBase entity)
	{
		return dao.listDataALPaginate(entity);
	}
	public Vector<InterfaceEntityBase> listDataV(InterfaceEntityBase entity)
	{
		return dao.listDataV(entity);
	}
	public InterfaceEntityBase getById(InterfaceEntityBase entity)
	{
		return dao.getById(entity);
	}
	public InterfaceEntityBase getByIdSinTx(InterfaceEntityBase entity)
	{
		return dao.getByIdSinTx(entity);
	}
	public InterfaceEntityBase getByIdCSinTx(InterfaceEntityBase entity)
	{
		return dao.getByIdCSinTx(entity);
	}
	public InterfaceEntityBase getByIdC(InterfaceEntityBase entity)
	{
		return dao.getByIdC(entity);
	}
	public Vector<InterfaceEntityBase> findByQuerySinTx(InterfaceEntityBase entity)
	{
		return dao.findByQuerySinTxV(entity);
	}
	public ArrayList<InterfaceEntityBase> findByQuerySinTxAL(InterfaceEntityBase entity)
	{
		return dao.findByQuerySinTxAL(entity);
	}
	public Vector<InterfaceEntityBase> findByQuery(InterfaceEntityBase entity)
	{
		return dao.findByQueryV(entity);
	}
	public ArrayList<InterfaceEntityBase> findByQueryAL(InterfaceEntityBase entity)
	{
		return dao.findByQueryAL(entity);
	}
	public InterfaceEntityBase findEntityByQuerySinTx(InterfaceEntityBase entity)
	{
		return dao.findEntityByQuerySinTx(entity);
	}
	public InterfaceEntityBase findEntityByQuery(InterfaceEntityBase entity)
	{
		return dao.findEntityByQuery(entity);
	}
	public InterfaceEntityBase getLastIdSintTx(InterfaceEntityBase entity)
	{
		return dao.getLastIdSintTx(entity);
	}
	public InterfaceEntityBase getLastId(InterfaceEntityBase entity)
	{
		return dao.getLastId(entity);
	}
	public InterfaceEntityBase getTotalRegSinTx(InterfaceEntityBase entity) {
		return dao.getTotalRegSinTx(entity);
	}
	public InterfaceEntityBase getTotalRegByQuerySinTx(InterfaceEntityBase entity) {
		return dao.getTotalRegByQuerySinTx(entity);
	}
	public ArrayList<Object> findByQueryJoinSinTxAL(InterfaceEntityBase entity){
		return dao.findByQueryJoinSinTxAL(entity);
	}
	public ArrayList<List<Object>> listDataSQLAL(InterfaceEntityBase entity){
		return dao.listDataSQLAL(entity);
	}
	public ArrayList<List<Object>> listDataSQLSinTxAL(InterfaceEntityBase entity){
		return dao.listDataSQLSinTxAL(entity);
	}
	public InterfaceEntityBase executeACID(InterfaceEntityBase entity){
		return dao.executeACID(entity);
	}
	public InterfaceEntityBase executeACIDSinTx(InterfaceEntityBase entity){
		return dao.executeACIDSinTx(entity);
	}

}
