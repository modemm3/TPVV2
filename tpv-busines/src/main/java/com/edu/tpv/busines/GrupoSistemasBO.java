package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.GrupoSistemas;
import com.edu.tpv.login.to.GrupoSistemasVO;

public class GrupoSistemasBO implements TransaccionHibernateIfz, TransactionHibernateQueryIfz {
	private PackageDAO packageDAO;
	private GrupoSistemasBO grupoSistemasBO = this;
	private int tipoTransaccion = 0;
	private GrupoSistemas grupoSistemas = null;
	private ArrayList<GrupoSistemasVO> lstGrupoSistemasVO = new ArrayList<GrupoSistemasVO>();

	public GrupoSistemasBO() {
		packageDAO = new PackageDAO("01");
	}

	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Vector<InterfaceEntityBase> grupoSistemasV = null;
		Iterator<InterfaceEntityBase> grupoSistemasIt = null;
		
		switch(tipoTransaccion)
		{
			case 4:
				grupoSistemasV = packageDAO.findByQuerySinTx(this.grupoSistemas);
				grupoSistemasIt = grupoSistemasV.iterator();
				while (grupoSistemasIt.hasNext()) {
					grupoSistemas = (GrupoSistemas) grupoSistemasIt.next();
				}
				break;
			case 5:
				grupoSistemasV = packageDAO.listDataSin(this.grupoSistemas);
				grupoSistemasIt = grupoSistemasV.iterator();
				while (grupoSistemasIt.hasNext()) {
					grupoSistemas = (GrupoSistemas) grupoSistemasIt.next();
					lstGrupoSistemasVO.add(new GrupoSistemasVO(grupoSistemas));
				}
				break;
			case 6:
				grupoSistemas = (GrupoSistemas) packageDAO.getTotalRegSinTx(this.grupoSistemas);
			}
		return this;
	}

	@Override
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		
		switch(tipoTransaccion)
		{
			case 1:
				GrupoSistemas ultimoIdGpoSistemas = new GrupoSistemas();
				ultimoIdGpoSistemas.setId(grupoSistemas.getId());
				ultimoIdGpoSistemas = (GrupoSistemas) unParcelSession.getLastIdSintTx(ultimoIdGpoSistemas);
				grupoSistemas.setId(ultimoIdGpoSistemas.getId());
				unParcelSession.saveSinTx(grupoSistemas);
				break;
			case 2:
				unParcelSession.modifySinTx(grupoSistemas);
				break;
			case 3:
				unParcelSession.deleteSintTx(grupoSistemas);
		}
		return this;
	}

	public InterfaceEntityBase guardarGrupoSistemas(InterfaceEntityBase grupoSistemasVO) throws Exception {
		tipoTransaccion = 1;
		this.grupoSistemas = new GrupoSistemas((GrupoSistemasVO) grupoSistemasVO);
		;
		grupoSistemasBO = (GrupoSistemasBO) packageDAO.launchesTransaction(this);
		return new GrupoSistemasVO(grupoSistemasBO.grupoSistemas);
	}

	public InterfaceEntityBase modificarGrupoSistemas(InterfaceEntityBase grupoSistemasVO) throws Exception {
		tipoTransaccion = 2;
		this.grupoSistemas = new GrupoSistemas((GrupoSistemasVO) grupoSistemasVO);
		grupoSistemasBO = (GrupoSistemasBO) packageDAO.launchesTransaction(this);
		return new GrupoSistemasVO(grupoSistemasBO.grupoSistemas);
	}

	public InterfaceEntityBase eliminarGrupoSistemas(InterfaceEntityBase grupoSistemasVO) throws Exception {
		tipoTransaccion = 3;
		this.grupoSistemas = new GrupoSistemas((GrupoSistemasVO) grupoSistemasVO);
		grupoSistemasBO = (GrupoSistemasBO) packageDAO.launchesTransaction(this);
		return new GrupoSistemasVO(grupoSistemasBO.grupoSistemas);
	}

	public InterfaceEntityBase buscarGrupoSistema(InterfaceEntityBase grupoSistemaVO) throws Exception {
		grupoSistemas=(GrupoSistemas)packageDAO.findById(new GrupoSistemas((GrupoSistemasVO)grupoSistemaVO));
		return new GrupoSistemasVO(grupoSistemas);
	}

	public ArrayList<GrupoSistemasVO> obtenerGrupoSistemasRangoList(GrupoSistemasVO grupoSistemasVO) throws Exception {
		this.grupoSistemas = new GrupoSistemas((GrupoSistemasVO) grupoSistemasVO);
		this.grupoSistemas.setQuery("from com.mx.coram.login.entity.GrupoSistemas gp order by gp.id");

		ArrayList<InterfaceEntityBase> grupoSistemasV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.grupoSistemas);
		Iterator<InterfaceEntityBase> grupoSistemasIt = grupoSistemasV.iterator();
		while (grupoSistemasIt.hasNext()) {
			grupoSistemas = (GrupoSistemas) grupoSistemasIt.next();
			lstGrupoSistemasVO.add(new GrupoSistemasVO(grupoSistemas));
		}

		return grupoSistemasBO.lstGrupoSistemasVO;
	}

	public ArrayList<GrupoSistemasVO> obtenerGrupoSistemasList(GrupoSistemasVO grupoSistemaVO) throws Exception {
		tipoTransaccion = 5;
		this.grupoSistemas = new GrupoSistemas(grupoSistemaVO);
		grupoSistemasBO = (GrupoSistemasBO) packageDAO.launchesTransactionQuery(this);
		return grupoSistemasBO.lstGrupoSistemasVO;
	}

	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase grupoSistemasVO) throws Exception {
		tipoTransaccion = 6;
		this.grupoSistemas = new GrupoSistemas((GrupoSistemasVO) grupoSistemasVO);
		grupoSistemasBO = (GrupoSistemasBO) packageDAO.launchesTransactionQuery(this);
		return (new GrupoSistemasVO(grupoSistemasBO.grupoSistemas));
	}
}
