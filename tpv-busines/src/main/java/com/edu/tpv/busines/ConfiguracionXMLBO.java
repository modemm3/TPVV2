package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.BdConstant;
import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.ConfiguracionXML;
import com.edu.tpv.login.entity.Empresas;
import com.edu.tpv.login.entity.GrupoSistemas;
import com.edu.tpv.login.to.ConfiguracionXMLVO;

public class ConfiguracionXMLBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	PackageDAO packageDAO;
	private ConfiguracionXMLBO configuracionXMLBO = this;
	private int tipoTransaccion = 0;
	private ConfiguracionXML configXML = new ConfiguracionXML();
	private ArrayList<ConfiguracionXMLVO> lstConfiguracionXMLVO = new ArrayList<ConfiguracionXMLVO>();

	public ConfiguracionXMLBO() {
		packageDAO = new PackageDAO("01");
	}

	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			ConfiguracionXML ultimoConfigXML = new ConfiguracionXML();
			ultimoConfigXML = (ConfiguracionXML) unParcelSession.getLastIdSintTx(ultimoConfigXML);
			configXML.setId(ultimoConfigXML.getId());
			unParcelSession.saveSinTx(configXML);
		} else if (tipoTransaccion == 2) {
			unParcelSession.modifySinTx(configXML);
		} else if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(configXML);
		}
		return this;
	}

	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		GrupoSistemas gpoSistemas = null;
		Empresas empresas = null;
		Vector<InterfaceEntityBase> configuracionXMLV = null;
		Iterator<InterfaceEntityBase> configuracionXMLIt = null;
		if (tipoTransaccion == 4) {
			configuracionXMLV = packageDAO.findByQuerySinTx(this.configXML);
			if (configuracionXMLV != null && configuracionXMLV.size() > 0) {
				configuracionXMLIt = configuracionXMLV.iterator();
				while (configuracionXMLIt.hasNext()) {
					configXML = (ConfiguracionXML) configuracionXMLIt.next();
				}
				gpoSistemas = new GrupoSistemas();
				empresas = new Empresas();
				gpoSistemas.setId(configXML.getGpoSistemas().getId());
				empresas.setIdC(configXML.getEmpresa().getIdC());
				configXML.setGpoSistemas((GrupoSistemas) packageDAO.getByIdSinTx(gpoSistemas));
				configXML.setEmpresa((Empresas) packageDAO.getByIdCSinTx(empresas));
				configXML.setStatus(BdConstant.EXITO);
			} else
				configXML.setStatus(BdConstant.OBJETO_VACIO);
		} else if (tipoTransaccion == 5) {
			configuracionXMLV = packageDAO.listDataSin(this.configXML);
			configuracionXMLIt = configuracionXMLV.iterator();

			while (configuracionXMLIt.hasNext()) {
				configXML = (ConfiguracionXML) configuracionXMLIt.next();
				gpoSistemas = new GrupoSistemas();
				empresas = new Empresas();

				gpoSistemas.setId(configXML.getGpoSistemas().getId());
				empresas.setIdC(configXML.getEmpresa().getIdC());
				configXML.setGpoSistemas((GrupoSistemas) packageDAO.getByIdSinTx(gpoSistemas));
				configXML.setEmpresa((Empresas) packageDAO.getByIdCSinTx(empresas));
				lstConfiguracionXMLVO.add(new ConfiguracionXMLVO(configXML));
			}
		} else if (tipoTransaccion == 6) {
			configXML = (ConfiguracionXML) packageDAO.getTotalRegSinTx(this.configXML);
		}
		return this;
	}

	public InterfaceEntityBase guardarConfiguracionXML(InterfaceEntityBase configXMLVO) throws Exception {
		tipoTransaccion = 1;
		this.configXML = new ConfiguracionXML((ConfiguracionXMLVO) configXMLVO);
		configuracionXMLBO = (ConfiguracionXMLBO) packageDAO.launchesTransaction(this);
		return new ConfiguracionXMLVO(configuracionXMLBO.configXML);
	}

	public InterfaceEntityBase modificarConfiguracionXML(InterfaceEntityBase configXMLVO) throws Exception {
		tipoTransaccion = 2;
		this.configXML = new ConfiguracionXML((ConfiguracionXMLVO) configXMLVO);
		configuracionXMLBO = (ConfiguracionXMLBO) packageDAO.launchesTransaction(this);
		return new ConfiguracionXMLVO(configuracionXMLBO.configXML);
	}

	public InterfaceEntityBase eliminarConfiguracionXML(InterfaceEntityBase configXMLVO) throws Exception {
		tipoTransaccion = 3;
		this.configXML = new ConfiguracionXML((ConfiguracionXMLVO) configXMLVO);
		configuracionXMLBO = (ConfiguracionXMLBO) packageDAO.launchesTransaction(this);
		return new ConfiguracionXMLVO(configuracionXMLBO.configXML);
	}

	public InterfaceEntityBase buscarConfiguracionXML(InterfaceEntityBase configXMLVO) throws Exception {
		configXML = (ConfiguracionXML) packageDAO.findById(new ConfiguracionXML((ConfiguracionXMLVO) configXMLVO));

		return new ConfiguracionXMLVO(configXML);
	}

	public InterfaceEntityBase buscarEmpresaConfiguracionXML(InterfaceEntityBase configXMLVO) throws Exception {
		tipoTransaccion = 4;
		configXMLVO.setQuery("from com.mx.coram.login.entity.ConfiguracionXML config where config.empresa.idC='" + configXMLVO.getIdC() + "'");
		this.configXML = new ConfiguracionXML((ConfiguracionXMLVO) configXMLVO);
		this.configXML.setQuery(configXMLVO.getQuery());
		configuracionXMLBO = (ConfiguracionXMLBO) packageDAO.launchesTransactionQuery(this);
		return new ConfiguracionXMLVO(configXML);
	}

	public ArrayList<ConfiguracionXMLVO> obtenerConfiguracionXMLRangoList(ConfiguracionXMLVO configuracionXMLVO) throws Exception {
		this.configXML = new ConfiguracionXML((ConfiguracionXMLVO) configuracionXMLVO);
		this.configXML.setQuery("from com.mx.coram.login.entity.ConfiguracionXML cxml order by cxml.id");

		ArrayList<InterfaceEntityBase> configuracionXMLV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.configXML);
		Iterator<InterfaceEntityBase> configuracionXMLIt = configuracionXMLV.iterator();
		while (configuracionXMLIt.hasNext()) {
			configXML = (ConfiguracionXML) configuracionXMLIt.next();
			lstConfiguracionXMLVO.add(new ConfiguracionXMLVO(configXML));
		}

		return configuracionXMLBO.lstConfiguracionXMLVO;
	}

	public ArrayList<ConfiguracionXMLVO> obtenerConfiguracionXMLList(ConfiguracionXMLVO configuracionVO) throws Exception {
		tipoTransaccion = 5;
		this.configXML = new ConfiguracionXML(configuracionVO);
		configuracionXMLBO = (ConfiguracionXMLBO) packageDAO.launchesTransactionQuery(this);
		return configuracionXMLBO.lstConfiguracionXMLVO;
	}

	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase configXMLVO) throws Exception {
		tipoTransaccion = 6;
		this.configXML = new ConfiguracionXML((ConfiguracionXMLVO) configXMLVO);
		configuracionXMLBO = (ConfiguracionXMLBO) packageDAO.launchesTransactionQuery(this);
		return (new ConfiguracionXMLVO(configuracionXMLBO.configXML));
	}
}
