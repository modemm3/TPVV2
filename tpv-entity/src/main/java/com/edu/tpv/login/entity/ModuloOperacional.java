package com.edu.tpv.login.entity;

import java.sql.Timestamp;

public class ModuloOperacional extends EntityBase {

	private String llave;
	private String bd;
	private boolean activo;
	private Timestamp fechaCreacion;
	private Timestamp fechaModificacion;
	private Usuarios usuario;
	private boolean esPadre;
	private String clase;
	private String titulo;
	private TipoAplicacion tipoAplicacion;
	private NivelOperacion nivelOperacion;
	private boolean esModulo;
	private boolean abreVentana;
	private String url;
	private boolean acumulativa;
	public ModuloOperacional() {
		super();
	}

//	public ModuloOperacional(ModuloOperacionalVO moduloOperacionalVO) {
//		this.idC = moduloOperacionalVO.getIdC();
//		this.nombre = moduloOperacionalVO.getNombre();
//		this.descripcion = moduloOperacionalVO.getDescripcion();
//		this.llave = moduloOperacionalVO.getLlave();
//		this.bd = moduloOperacionalVO.getBd();
//		this.activo = moduloOperacionalVO.isActivo();
//		this.fechaCreacion = moduloOperacionalVO.getFechaCreacion();
//		this.fechaModificacion = moduloOperacionalVO.getFechaModificacion();
//		this.esPadre = moduloOperacionalVO.isEsPadre();
//		this.clase = moduloOperacionalVO.getClase();
//		this.titulo = moduloOperacionalVO.getTitulo();
//		this.usuario = new Usuarios(moduloOperacionalVO.getUsuariosVO() == null ? new UsuariosVO() : moduloOperacionalVO.getUsuariosVO());
//		this.tipoAplicacion = new TipoAplicacion(moduloOperacionalVO.getTipoAplicacionVO() == null ? new TipoAplicacionVO()
//				: moduloOperacionalVO.getTipoAplicacionVO());
//		this.nivelOperacion = new NivelOperacion(moduloOperacionalVO.getNivelOperacionVO() == null ? new NivelOperacionVO()
//				: moduloOperacionalVO.getNivelOperacionVO());
//		this.esModulo = moduloOperacionalVO.isEsModulo();
//		this.abreVentana = moduloOperacionalVO.isAbreVentana();
//		this.setStatus(moduloOperacionalVO.getStatus());
//		this.setInicialReg(moduloOperacionalVO.getInicialReg());
//		this.setFinalReg(moduloOperacionalVO.getFinalReg());
//		this.setTotalRegistros(moduloOperacionalVO.getTotalRegistros());
//		this.url=moduloOperacionalVO.getUrl();
//		this.setAcumulativa(moduloOperacionalVO.isAcumulativa());
//	}

	public ModuloOperacional(String idC, String nombre, String descripcion, String llave, String bd, boolean activo, Timestamp fechaCreacion,
			Timestamp fechaModificacion, Usuarios usuario, boolean esPadre, String clase, String titulo, TipoAplicacion tipoAplicacion,
			NivelOperacion nivelOperacion,String url) {
		this.idC = idC;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.llave = llave;
		this.bd = bd;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuario = usuario;
		this.esPadre = esPadre;
		this.clase = clase;
		this.titulo = titulo;
		this.tipoAplicacion = tipoAplicacion;
		this.nivelOperacion = nivelOperacion;
		this.url=url;
	}

	public String getLlave() {
		return llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public boolean isEsPadre() {
		return esPadre;
	}

	public void setEsPadre(boolean esPadre) {
		this.esPadre = esPadre;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoAplicacion getTipoAplicacion() {
		return tipoAplicacion;
	}

	public void setTipoAplicacion(TipoAplicacion tipoAplicacion) {
		this.tipoAplicacion = tipoAplicacion;
	}

	public NivelOperacion getNivelOperacion() {
		return nivelOperacion;
	}

	public void setNivelOperacion(NivelOperacion nivelOperacion) {
		this.nivelOperacion = nivelOperacion;
	}

	public boolean isEsModulo() {
		return esModulo;
	}

	public void setEsModulo(boolean esModulo) {
		this.esModulo = esModulo;
	}

    public boolean isAbreVentana() {
        return abreVentana;
    }

	public boolean isAcumulativa() {
		return acumulativa;
	}
    public void setAbreVentana(boolean abreVentana) {
        this.abreVentana = abreVentana;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public void setAcumulativa(boolean acumulativa) {
		this.acumulativa = acumulativa;
	}
}
