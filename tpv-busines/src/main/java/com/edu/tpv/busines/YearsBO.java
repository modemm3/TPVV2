package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.Years;
import com.edu.tpv.login.to.AsigPermisosVO;
import com.edu.tpv.login.to.YearsVO;

/**
 * 
 * @author ERWIN ALONSO NAVA
 * 
 */

public class YearsBO implements TransaccionHibernateIfz, TransactionHibernateQueryIfz {

	private static final String PK_QUERY = "from com.mx.coram.login.entity.Years y where y.claveEmpresa ='";
	private PackageDAO packageDAO;
	private int tipoTransaccion = 0;
	private YearsBO yearsBO = this;
	private Years years = new Years();
	private final ArrayList<YearsVO> lstYearsVO = new ArrayList<YearsVO>();
	private Vector<InterfaceEntityBase> v = null;
	private String empresa;

	public YearsBO(AsigPermisosVO permisoVO) {
		packageDAO = new PackageDAO("01");
		empresa = permisoVO.getEmpresasVO().getIdC();
	}

	@Override
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {

		switch (tipoTransaccion) {
		case GUARDAR:
			unParcelSession.saveSinTx(years);
			break;
		case MODIFICAR:
			unParcelSession.modifySinTx(years);
			break;
		case ELIMINAR:
			unParcelSession.deleteSintTx(years);
			break;
		case 7:
			unParcelSession.modifySinTx(years);
			break;
		}
		return this;
	}

	@Override
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {

		switch (tipoTransaccion) {
		case BUSCAR_PK:
			v = packageDAO.findByQuerySinTx(years);
			if (v.size() > 0) {
				years = (Years) v.get(0);
			} else {
				years = new Years();
			}
			break;
		case BUSCAR_LIST:
			v = packageDAO.findByQuerySinTx(years);
			for (final InterfaceEntityBase ieb : v) {
				lstYearsVO.add(new YearsVO((Years) ieb));
			}
			break;
		case 6:
			years = (Years) packageDAO.getTotalRegByQuerySinTx(this.years);
			break;
		}
		return this;
	}

	public YearsVO guardarYears(YearsVO yearsVO) throws Exception {
		tipoTransaccion = 1;
		this.years = new Years(yearsVO);
		yearsBO = (YearsBO) packageDAO.launchesTransaction(this);
		return new YearsVO(yearsBO.years);
	}

	public YearsVO buscarYears(YearsVO yearsVO) throws Exception {
		tipoTransaccion = 4;
		final String query = PK_QUERY;
		years.setQuery(query);
		packageDAO.launchesTransactionQuery(yearsBO);
		return new YearsVO(years);
	}

	public ArrayList<YearsVO> buscarYearsList(YearsVO yearsVO) throws Exception {
		tipoTransaccion = 5;
		this.years = new Years((YearsVO) yearsVO);
		this.years.setQuery(PK_QUERY + empresa + "'");
		yearsBO = (YearsBO) packageDAO.launchesTransactionQuery(yearsBO);
		return yearsBO.lstYearsVO;
	}

	public YearsVO obtieneTotalRegistros(YearsVO yearsVO) throws Exception {
		tipoTransaccion = 6;
		this.years = new Years((YearsVO) yearsVO);
		this.years.setQuery("select count(*) from com.mx.coram.contabilidad.entity.Years p where p.year= order by p.clave");
		yearsBO = (YearsBO) packageDAO.launchesTransactionQuery(yearsBO);
		return (new YearsVO(yearsBO.years));
	}

    public YearsVO obtenerUltimoYear(YearsVO yearsVO) throws Exception {
        tipoTransaccion = BUSCAR_PK;
        String query = PK_QUERY + empresa + "' order by y.year desc";
        years.setQuery(query);
        packageDAO.launchesTransactionQuery(yearsBO);
        return new YearsVO(years);
    }

}
