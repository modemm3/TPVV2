package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.Empresas;
import com.edu.tpv.login.to.EmpresasVO;

public class EmpresasBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	PackageDAO packageDAO;
	private EmpresasBO empresasBO = this;
	private int tipoTransaccion = 0;
	private Empresas empresas = null;
	private ArrayList<EmpresasVO> lstEmpresasVO = new ArrayList<EmpresasVO>();

	public EmpresasBO() {
		packageDAO = new PackageDAO("01");
	}

	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		switch(tipoTransaccion)
		{
			case 1:
				unParcelSession.saveSinTx(empresas);
				break;
			case 2:
				unParcelSession.modifySinTx(empresas);
				break;
			case 3:
				unParcelSession.deleteSintTx(empresas);
		}
		return this;
	}

	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		switch(tipoTransaccion)
		{
			case 5:
				Vector<InterfaceEntityBase> empresasV = packageDAO.listDataSin(this.empresas);
				Iterator<InterfaceEntityBase> empresasIt = empresasV.iterator();
				while (empresasIt.hasNext()) {
					empresas = (Empresas) empresasIt.next();
					lstEmpresasVO.add(new EmpresasVO(empresas));
				}
				break;
			case 6:
				empresas = (Empresas) packageDAO.getTotalRegSinTx(this.empresas);
			}
		return this;
	}

	public InterfaceEntityBase guardarEmpresas(InterfaceEntityBase empresasVO) throws Exception {
		tipoTransaccion = 1;
		this.empresas = new Empresas((EmpresasVO) empresasVO);
		empresasBO = (EmpresasBO) packageDAO.launchesTransaction(this);
		return new EmpresasVO(empresasBO.empresas);
	}

	public InterfaceEntityBase modificarEmpresas(InterfaceEntityBase empresasVO) throws Exception {
		tipoTransaccion = 2;
		this.empresas = new Empresas((EmpresasVO) empresasVO);
		empresasBO = (EmpresasBO) packageDAO.launchesTransaction(this);
		return new EmpresasVO(empresasBO.empresas);
	}

	public InterfaceEntityBase eliminarEmpresas(InterfaceEntityBase empresasVO) throws Exception {
		tipoTransaccion = 3;
		this.empresas = new Empresas((EmpresasVO) empresasVO);
		empresasBO = (EmpresasBO) packageDAO.launchesTransaction(this);
		return new EmpresasVO(empresasBO.empresas);
	}

	public InterfaceEntityBase buscarEmpresa(InterfaceEntityBase empresaVO) throws Exception {
		empresas = (Empresas) packageDAO.findByIdC(new Empresas((EmpresasVO) empresaVO));

		return new EmpresasVO(empresas);
	}

	public ArrayList<EmpresasVO> obtenerEmpresasRangoList(EmpresasVO empresasVO) throws Exception {
		this.empresas = new Empresas((EmpresasVO) empresasVO);
		this.empresas.setQuery("from com.mx.coram.login.entity.Empresas emp order by emp.id");

		ArrayList<InterfaceEntityBase> empresasV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.empresas);
		Iterator<InterfaceEntityBase> empresasIt = empresasV.iterator();
		while (empresasIt.hasNext()) {
			empresas = (Empresas) empresasIt.next();
			lstEmpresasVO.add(new EmpresasVO(empresas));
		}

		return empresasBO.lstEmpresasVO;
	}

	public ArrayList<EmpresasVO> obtenerEmpresasList(EmpresasVO empresaVO) throws Exception {
		tipoTransaccion = 5;
		this.empresas = new Empresas(empresaVO);
		empresasBO = (EmpresasBO) packageDAO.launchesTransactionQuery(this);
		return empresasBO.lstEmpresasVO;
	}

	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase empresasVO) throws Exception {
		tipoTransaccion = 6;
		this.empresas = new Empresas((EmpresasVO) empresasVO);
		empresasBO = (EmpresasBO) packageDAO.launchesTransactionQuery(this);
		return (new EmpresasVO(empresasBO.empresas));
	}

}
