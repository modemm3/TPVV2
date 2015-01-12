package com.edu.tpv.login.to;

import java.sql.Timestamp;

import com.edu.tpv.login.jdbc.dto.DTO;

public class UsuariosVO extends DTO
{
	
	private int id;
	private UsuarioDetalleVO usuarioDetalleVO;
	private String nombre;
	private String password;
	private boolean activo;
	private Timestamp fechaCreacion;
	private Timestamp fechaModificacion;
	private UsuariosVO usuarioModificaVO;
	private Timestamp ultimaFechaAcceso;
	private Timestamp periodoCambioContrasenia;
	private boolean habilitarCambioContrasenia;
	private int noDiasAnticipadosCambContrasenia;
	private int noSesiones;
	private PreguntasSecretasVO preguntasSecretasVO;
	private String respuestaSecreta;
	
	
	public UsuariosVO()
	{
		super();
	}
//	public UsuariosVO(Usuarios usuario)
//	{
//		this.id = usuario.getId();
//		this.usuarioDetalleVO=new UsuarioDetalleVO(usuario.getUsuarioDetalle()==null?new UsuarioDetalle():usuario.getUsuarioDetalle());
//		this.nombre = usuario.getNombre();
//		this.password = usuario.getPassword();
//		this.activo = usuario.isActivo();
//		this.fechaCreacion = usuario.getFechaCreacion();
//		this.fechaModificacion = usuario.getFechaModificacion();
//		this.ultimaFechaAcceso=usuario.getUltimaFechaAcceso();
//		this.periodoCambioContrasenia=usuario.getPeriodoCambioContrasenia();
//		this.habilitarCambioContrasenia=usuario.getHabilitarCambioContrasenia();
//		this.noDiasAnticipadosCambContrasenia=usuario.getNoDiasAnticipadosCambContrasenia();
//		this.noSesiones=usuario.getNoSesiones();
//		this.preguntasSecretasVO=new PreguntasSecretasVO( usuario.getPreguntasSecretas()==null?new PreguntasSecretas():usuario.getPreguntasSecretas());
//		this.respuestaSecreta=usuario.getRespuestaSecreta();
//		
//		//this.usuarioModificaVO=new UsuariosVO(usuario.getUsuarioModifica()==null?new Usuarios():usuario.getUsuarioModifica());
//		
////		Usuarios user=new Usuarios();
////		user.setId(usuario.getUsuarioModifica().getId());
//		if(usuario.getUsuarioModifica()!=null){			
//			UsuariosVO userVO=new UsuariosVO();
//			userVO.setId(usuario.getUsuarioModifica().getId());
//			this.usuarioModificaVO=userVO;
//		}
//		
//		
//		this.setStatus(usuario.getStatus());
//		this.setInicialReg(usuario.getInicialReg());
//		this.setFinalReg(usuario.getFinalReg());
//		this.setTotalRegistros(usuario.getTotalRegistros());
//	}

	public UsuariosVO(int id, UsuarioDetalleVO usuarioDetalleVO, String nombre, String password, boolean activo, Timestamp fechaCreacion, Timestamp fechaModificacion, UsuariosVO usuarioModificaVO,
			Timestamp ultimaFechaAcceso, Timestamp periodoCambioContrasenia, boolean habilitarCambioContrasenia, int noDiasAnticipadosCambContrasenia, int noSesiones,
			PreguntasSecretasVO preguntasSecretasVO, String respuestaSecreta)
	{
		super();
		this.id = id;
		this.usuarioDetalleVO = usuarioDetalleVO;
		this.nombre = nombre;
		this.password = password;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		//this.usuarioModificaVO = usuarioModificaVO;
		this.ultimaFechaAcceso = ultimaFechaAcceso;
		this.periodoCambioContrasenia = periodoCambioContrasenia;
		this.habilitarCambioContrasenia = habilitarCambioContrasenia;
		this.noDiasAnticipadosCambContrasenia = noDiasAnticipadosCambContrasenia;
		this.noSesiones = noSesiones;
		this.preguntasSecretasVO = preguntasSecretasVO;
		this.respuestaSecreta = respuestaSecreta;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	
	public UsuarioDetalleVO getUsuarioDetalleVO() {
		return usuarioDetalleVO;
	}
	public void setUsuarioDetalleVO(UsuarioDetalleVO usuarioDetalleVO) {
		this.usuarioDetalleVO = usuarioDetalleVO;
	}
	
	
	public PreguntasSecretasVO getPreguntasSecretasVO() {
		return preguntasSecretasVO;
	}
	public void setPreguntasSecretasVO(PreguntasSecretasVO preguntasSecretasVO) {
		this.preguntasSecretasVO = preguntasSecretasVO;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public boolean isActivo()
	{
		return activo;
	}
	public void setActivo(boolean activo)
	{
		this.activo = activo;
	}
	public Timestamp getFechaCreacion()
	{
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion)
	{
		this.fechaCreacion = fechaCreacion;
	}
	public Timestamp getFechaModificacion()
	{
		return fechaModificacion;
	}
	public void setFechaModificacion(Timestamp fechaModificacion)
	{
		this.fechaModificacion = fechaModificacion;
	}
	
	public Timestamp getUltimaFechaAcceso()
	{
		return ultimaFechaAcceso;
	}
	public void setUltimaFechaAcceso(Timestamp ultimaFechaAcceso)
	{
		this.ultimaFechaAcceso = ultimaFechaAcceso;
	}
	public Timestamp getPeriodoCambioContrasenia()
	{
		return periodoCambioContrasenia;
	}
	public void setPeriodoCambioContrasenia(String perChangePwd)
	{
        Timestamp ts = Timestamp.valueOf(perChangePwd);
		this.periodoCambioContrasenia = ts;
	}
	
	public boolean getHabilitarCambioContrasenia() {
		return habilitarCambioContrasenia;
	}
	public void setHabilitarCambioContrasenia(boolean habilitarCambioContrasenia) {
		this.habilitarCambioContrasenia = habilitarCambioContrasenia;
	}
	public int getNoDiasAnticipadosCambContrasenia()
	{
		return noDiasAnticipadosCambContrasenia;
	}
	public void setNoDiasAnticipadosCambContrasenia(int noDiasAnticipadosCambContrasenia)
	{
		this.noDiasAnticipadosCambContrasenia = noDiasAnticipadosCambContrasenia;
	}
	public int getNoSesiones()
	{
		return noSesiones;
	}
	public void setNoSesiones(int noSesiones)
	{
		this.noSesiones = noSesiones;
	}
	
	
	public UsuariosVO getUsuarioModificaVO() {
		return usuarioModificaVO;
	}
	public void setUsuarioModificaVO(UsuariosVO usuarioModificaVO) {
		this.usuarioModificaVO = usuarioModificaVO;
	}
	public String getRespuestaSecreta()
	{
		return respuestaSecreta;
	}
	public void setRespuestaSecreta(String respuestaSecreta)
	{
		this.respuestaSecreta = respuestaSecreta;
	}
}
