package com.edu.tpv.login.to;

import java.sql.Timestamp;

import com.edu.tpv.login.jdbc.dto.DTO;

public class UsuarioDetalleTO extends DTO{
	
	private String nombreCorto;
	private String nombreCompleto;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String telefono;
	private String direccion;
	private String curp;
	private String rfc;
	private Timestamp fechaAcceso;
	private String correo;

	public UsuarioDetalleTO() {
		super();
	}

	public UsuarioDetalleTO(int id, String nombreCorto, String nombreCompleto, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono,
			String direccion, String curp, String rfc, Timestamp fechaAcceso, String correo) {
		this.id = id;
		this.nombreCorto = nombreCorto;
		this.nombreCompleto = nombreCompleto;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.telefono = telefono;
		this.direccion = direccion;
		this.curp = curp;
		this.rfc = rfc;
		this.fechaAcceso = fechaAcceso;
		this.correo = correo;
	}

//	public UsuarioDetalleVO(UsuarioDetalle usuarioDetalle) {
//		this.id = usuarioDetalle.getId();
//		this.nombreCorto = usuarioDetalle.getNombreCorto();
//		this.nombreCompleto = usuarioDetalle.getNombreCompleto();
//		this.nombres = usuarioDetalle.getNombres();
//		this.apellidoPaterno = usuarioDetalle.getApellidoPaterno();
//		this.apellidoMaterno = usuarioDetalle.getApellidoMaterno();
//		this.telefono = usuarioDetalle.getTelefono();
//		this.direccion = usuarioDetalle.getDireccion();
//		this.curp = usuarioDetalle.getCurp();
//		this.rfc = usuarioDetalle.getRfc();
//		this.fechaAcceso = usuarioDetalle.getFechaAcceso();
//		this.correo = usuarioDetalle.getCorreo();
//		this.setStatus(usuarioDetalle.getStatus());
//		this.setInicialReg(usuarioDetalle.getInicialReg());
//		this.setFinalReg(usuarioDetalle.getFinalReg());
//		this.setTotalRegistros(usuarioDetalle.getTotalRegistros());
//	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Timestamp getFechaAcceso() {
		return fechaAcceso;
	}

	public void setFechaAcceso(Timestamp fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
