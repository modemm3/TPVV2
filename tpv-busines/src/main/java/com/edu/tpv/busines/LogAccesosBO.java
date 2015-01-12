package com.edu.tpv.busines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.edu.tpv.dao.InterfaceEntityBase;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.LogAccesos;
import com.edu.tpv.login.entity.ModuloOperacional;
import com.edu.tpv.login.entity.Usuarios;
import com.edu.tpv.login.to.LogAccesosVO;

public class LogAccesosBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private LogAccesosBO logAccesosBO = this;
	private int tipoTransaccion = 0;
	private LogAccesos logAccesos = new LogAccesos();
	private ArrayList<LogAccesosVO> lstLogAccesosVO = new ArrayList<LogAccesosVO>();

	public LogAccesosBO() {
		packageDAO = new PackageDAO("01");
	}

	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion == 1) {
			LogAccesos ultimoLogAccesosId = new LogAccesos();
			ultimoLogAccesosId = (LogAccesos) unParcelSession.getLastIdSintTx(ultimoLogAccesosId);
			logAccesos.setId(ultimoLogAccesosId.getId());

			logAccesos.setFechaAcceso(unParcelSession.getDateCurrentSinTx());

			unParcelSession.saveSinTx(logAccesos);
		} else if (tipoTransaccion == 2) {
			unParcelSession.modifySinTx(logAccesos);
		} else if (tipoTransaccion == 3) {
			unParcelSession.deleteSintTx(logAccesos);
		}
		return this;
	}

	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		Vector<InterfaceEntityBase> logAccesosV;
		Iterator<InterfaceEntityBase> logAccesosIt;
		ModuloOperacional moduloOperacional;
		Usuarios usuarios = null;

		if (tipoTransaccion == 4) {
			logAccesosV = packageDAO.findByQuerySinTx(this.logAccesos);
			logAccesosIt = logAccesosV.iterator();
			while (logAccesosIt.hasNext()) {
				logAccesos = (LogAccesos) logAccesosIt.next();
			}
			moduloOperacional = new ModuloOperacional();
			usuarios = new Usuarios();

			moduloOperacional.setIdC(logAccesos.getModuloOperacional().getIdC());
			usuarios.setId(logAccesos.getUsuarios().getId());

			logAccesos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
			logAccesos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));
		} else if (tipoTransaccion == 5) {
			logAccesosV = packageDAO.listDataSin(this.logAccesos);
			logAccesosIt = logAccesosV.iterator();

			while (logAccesosIt.hasNext()) {
				logAccesos = (LogAccesos) logAccesosIt.next();

				moduloOperacional = new ModuloOperacional();
				usuarios = new Usuarios();

				moduloOperacional.setIdC(logAccesos.getModuloOperacional().getIdC());
				usuarios.setId(logAccesos.getUsuarios().getId());
				logAccesos.setModuloOperacional((ModuloOperacional) packageDAO.getByIdCSinTx(moduloOperacional));
				logAccesos.setUsuarios((Usuarios) packageDAO.getByIdSinTx(usuarios));

				lstLogAccesosVO.add(new LogAccesosVO(logAccesos));
			}
		} else if (tipoTransaccion == 6) {
			logAccesos = (LogAccesos) packageDAO.getTotalRegSinTx(this.logAccesos);
		}
		return this;
	}

	public InterfaceEntityBase guardarLogAccesos(InterfaceEntityBase logAccesosVO) throws Exception {
		tipoTransaccion = 1;
		this.logAccesos = new LogAccesos((LogAccesosVO) logAccesosVO);
		logAccesosBO = (LogAccesosBO) packageDAO.launchesTransaction(this);
		return new LogAccesosVO(logAccesosBO.logAccesos);
	}

	public InterfaceEntityBase modificarLogAccesos(InterfaceEntityBase logAccesosVO) throws Exception {
		tipoTransaccion = 2;
		this.logAccesos = new LogAccesos((LogAccesosVO) logAccesosVO);
		logAccesosBO = (LogAccesosBO) packageDAO.launchesTransaction(this);
		return new LogAccesosVO(logAccesosBO.logAccesos);
	}

	public InterfaceEntityBase eliminarLogAccesos(InterfaceEntityBase logAccesosVO) throws Exception {
		tipoTransaccion = 3;
		this.logAccesos = new LogAccesos((LogAccesosVO) logAccesosVO);
		logAccesosBO = (LogAccesosBO) packageDAO.launchesTransaction(this);
		return new LogAccesosVO(logAccesosBO.logAccesos);
	}

	public InterfaceEntityBase buscarLogAcceso(InterfaceEntityBase logAccesoVO) throws Exception {
		this.logAccesos = (LogAccesos) packageDAO.findById(new LogAccesos((LogAccesosVO) logAccesoVO));
		return new LogAccesosVO(logAccesos);
	}

	public ArrayList<LogAccesosVO> obtenerLogAccesosRangoList(LogAccesosVO logAccesosVO) throws Exception {
		this.logAccesos = new LogAccesos((LogAccesosVO) logAccesosVO);
		this.logAccesos.setQuery("from com.mx.coram.login.entity.LogAccesos access order by access.id");

		ArrayList<InterfaceEntityBase> logAccesosV = (ArrayList<InterfaceEntityBase>) packageDAO.listDataALPaginate(this.logAccesos);
		Iterator<InterfaceEntityBase> logAccesosIt = logAccesosV.iterator();
		while (logAccesosIt.hasNext()) {
			logAccesos = (LogAccesos) logAccesosIt.next();
			lstLogAccesosVO.add(new LogAccesosVO(logAccesos));
		}
		return logAccesosBO.lstLogAccesosVO;
	}

	public ArrayList<LogAccesosVO> obtenerLogAccesosList(LogAccesosVO logAccesoVO) throws Exception {
		tipoTransaccion = 5;
		this.logAccesos = new LogAccesos(logAccesoVO);
		logAccesosBO = (LogAccesosBO) packageDAO.launchesTransactionQuery(this);
		return logAccesosBO.lstLogAccesosVO;
	}

	public InterfaceEntityBase obtenerTotalRegistros(InterfaceEntityBase logAccesoVO) throws Exception {
		tipoTransaccion = 6;
		this.logAccesos = new LogAccesos((LogAccesosVO) logAccesoVO);
		logAccesosBO = (LogAccesosBO) packageDAO.launchesTransactionQuery(this);
		return (new LogAccesosVO(logAccesosBO.logAccesos));
	}
}
