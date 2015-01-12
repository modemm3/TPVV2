package com.edu.tpv.busines;

import com.edu.tpv.dao.BdConstant;
import com.edu.tpv.dao.OperationsDAOIfz;
import com.edu.tpv.dao.PackageDAO;
import com.edu.tpv.dao.TransaccionHibernateIfz;
import com.edu.tpv.dao.TransactionHibernateQueryIfz;
import com.edu.tpv.login.entity.HistoricoPassword;
import com.edu.tpv.login.entity.Usuarios;
import com.edu.tpv.login.to.CambioPasswordUsuarioVO;
import com.edu.tpv.login.to.HistoricoPasswordVO;
import com.edu.tpv.login.to.PreguntasSecretasVO;
import com.edu.tpv.login.to.UsuariosVO;


public class CambioPasswordUsuarioBO implements TransactionHibernateQueryIfz, TransaccionHibernateIfz {
	private PackageDAO packageDAO;
	private CambioPasswordUsuarioBO cambioPwdBO=this;
	private CambioPasswordUsuarioVO cambioPasswordUsuarioVO=new CambioPasswordUsuarioVO();
	private Usuarios usuario=new Usuarios();
	private HistoricoPassword historicoPass=new HistoricoPassword();
	private int tipoTransaccion=0;

	public CambioPasswordUsuarioBO() {
		packageDAO=new PackageDAO("01");
	}
	
	public TransaccionHibernateIfz Transaction(OperationsDAOIfz unParcelSession) throws Exception {
		if (tipoTransaccion==3){
			unParcelSession.modifySinTx(historicoPass);
			unParcelSession.modifySinTx(usuario);
		}
		if(tipoTransaccion==4){
			HistoricoPassword ultimoHistoricoPwd = new HistoricoPassword();
			ultimoHistoricoPwd = (HistoricoPassword) unParcelSession.getLastIdSintTx(ultimoHistoricoPwd);
			historicoPass.setId(ultimoHistoricoPwd.getId());
			unParcelSession.saveSinTx(historicoPass);
			unParcelSession.modifySinTx(usuario);
		}
		
		return this;
	}

	@Override
	public TransactionHibernateQueryIfz QueryTransaction(OperationsDAOIfz unParcelSession) throws Exception {
		// TODO Auto-generated method stub
		return null;
	} 

	/**
	 * Metodo que permite la actualizacion del password del usuario, verificando la pregunta y respuesta del usuario antes de poder cambiarla, y por tanto
	 * posteriormente verifica su en historicopassword ya existe, si existiese si activo es igual a 1 se puede reutilizar, si es 0 no puede ser reutilizada
	 * @param cambioPassUsuarioVO
	 * @return cambioPassUsuarioVO con los mensajes correspondientes en caso de guardarse correctamente o no
	 * @throws Exception
	 */
	public CambioPasswordUsuarioVO actualizarCambioPasswordUsuario(CambioPasswordUsuarioVO cambioPassUsuarioVO/*, HistoricoPasswordVO historicoPwdVO*/) throws Exception{
		try{
		tipoTransaccion=3;
		this.cambioPasswordUsuarioVO=cambioPassUsuarioVO;
		
		HistoricoPasswordVO historicoPwdVO=new HistoricoPasswordVO();
		HistoricoPasswordBO historicoPwdBO=new HistoricoPasswordBO();
		PreguntasSecretasVO pregSecretVO=new PreguntasSecretasVO();
		PreguntasSecretasBO pregSecretBO=new PreguntasSecretasBO();
		UsuariosVO usuarioVO=new UsuariosVO();
		UsuariosBO usuarioBO=new UsuariosBO();

		usuarioVO.setId(cambioPasswordUsuarioVO.getId());
		usuarioVO=(UsuariosVO) usuarioBO.buscarUsuarioId(usuarioVO);
		pregSecretVO.setId(cambioPassUsuarioVO.getPreguntasSecretasVO().getId());
		pregSecretVO=(PreguntasSecretasVO) pregSecretBO.buscarPreguntaSecreta(pregSecretVO);
		
		cambioPassUsuarioVO.setPreguntasSecretasVO(pregSecretVO);
		
		historicoPwdVO.setUsuariosVO(usuarioVO);
		if(usuarioVO.getStatus()==BdConstant.EXITO)
		{
			if(!usuarioVO.getPassword().equals(cambioPasswordUsuarioVO.getPasswordNuevo())){
			if(usuarioVO.getPreguntasSecretasVO().getNombre().equals(cambioPasswordUsuarioVO.getPreguntasSecretasVO().getNombre())
					&& usuarioVO.getRespuestaSecreta().equals(cambioPasswordUsuarioVO.getRespuestaSecreta()))
			{
				historicoPwdVO.setPassword(cambioPasswordUsuarioVO.getPasswordNuevo());
				historicoPwdVO=(HistoricoPasswordVO) historicoPwdBO.buscarHistoricoPasswordIdUsuarioPwd(historicoPwdVO); 
				if(historicoPwdVO.getStatus()==BdConstant.EXITO && !historicoPwdVO.isActivo())
				{
					/*Si el password en historicopassword es 0, correspondiente al usuario no puede ser utilizado*/
					cambioPasswordUsuarioVO.setMsgView("El password ya fue utilizado por el usuario, introduzca otro...");
				}
				else if(historicoPwdVO.getStatus()==BdConstant.EXITO && historicoPwdVO.isActivo()){
					/*Si activo es 1 en historicopassword la contraseña se utilizar y cambia el estatus a 0 para q ya no pueda ser utilizada y actualiza la
					 * del usuario*/
					historicoPwdVO.setActivo("0");
					historicoPwdVO.setPassword(usuarioVO.getPassword());
					usuarioVO.setPassword(cambioPasswordUsuarioVO.getPasswordNuevo());
					cambioPasswordUsuarioVO.setUsuarioVO(usuarioVO);
					this.usuario=new Usuarios(cambioPasswordUsuarioVO.getUsuarioVO());
					this.historicoPass=new HistoricoPassword(historicoPwdVO);
					cambioPwdBO=(CambioPasswordUsuarioBO) packageDAO.launchesTransaction(this);
					cambioPasswordUsuarioVO.setMsgView("El password ha sido cambiado correctamente...");
					}
				else if(historicoPwdVO.getStatus()==BdConstant.OBJETO_VACIO){
					/*guarda en historicopwd la contraseña del usuario y al usuario le cambia la contraseña posteriormente*/
					tipoTransaccion=4;
					this.usuario=new Usuarios(usuarioVO);
					historicoPass=new HistoricoPassword(0, usuario, usuario.getPassword(), false);
					this.usuario.setPassword(cambioPasswordUsuarioVO.getPasswordNuevo());
					cambioPwdBO=(CambioPasswordUsuarioBO) packageDAO.launchesTransaction(this);
					cambioPasswordUsuarioVO.setMsgView("El password ha sido cambiado correctamente...");
					//guardar en historico pwd  la contraseña del usuario a cambiar y cambiar contraseña en usuario
				}
			}
			else{
				cambioPasswordUsuarioVO.setMsgView("La pregunta y/o respuesta secretas son incorrectas...");
			}
			}
			else{
				cambioPasswordUsuarioVO.setMsgView("Introduzca un password diferente para el usuario...");
			}
		}
		}catch(Exception e)
		{
			cambioPasswordUsuarioVO.setStatus(0);
			new Exception(e);
		}
		
		return cambioPwdBO.cambioPasswordUsuarioVO;
	}


}
