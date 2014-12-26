package com.edu.tpv.login.to;

import java.sql.Timestamp;

import com.edu.tpv.login.jdbc.dto.DTO;

public class ModuloOperacionalTO extends DTO{
	
	private String llave;
	private String bd;
	private boolean activo;
	private Timestamp fechaCreacion;
	private Timestamp fechaModificacion;
	private UsuariosTO usuariosVO;
	private boolean esPadre;
	private String clase;
	private String titulo;
	private TipoAplicacionTO tipoAplicacionVO;
	private NivelOperacionTO nivelOperacionVO;
	private boolean esModulo;
	private boolean abreVentana;
	private String url;
	private boolean acumulativa;
	public ModuloOperacionalTO() {
		super();
	}

//	public ModuloOperacionalVO(ModuloOperacional moduloOperacional) {
//		this.idC = moduloOperacional.getIdC();
//		this.nombre = moduloOperacional.getNombre();
//		this.descripcion = moduloOperacional.getDescripcion();
//		this.llave = moduloOperacional.getLlave();
//		this.bd = moduloOperacional.getBd();
//		this.activo = moduloOperacional.isActivo();
//		this.fechaCreacion = moduloOperacional.getFechaCreacion();
//		this.fechaModificacion = moduloOperacional.getFechaModificacion();
//		this.esPadre = moduloOperacional.isEsPadre();
//		this.clase = moduloOperacional.getClase();
//		this.titulo = moduloOperacional.getTitulo();
//		this.usuariosVO = new UsuariosVO(moduloOperacional.getUsuario() == null ? new Usuarios() : moduloOperacional.getUsuario());
//		this.tipoAplicacionVO = new TipoAplicacionVO(moduloOperacional.getTipoAplicacion() == null ? new TipoAplicacion()
//				: moduloOperacional.getTipoAplicacion());
//		this.nivelOperacionVO = new NivelOperacionVO(moduloOperacional.getNivelOperacion() == null ? new NivelOperacion()
//				: moduloOperacional.getNivelOperacion());
//		this.esModulo = moduloOperacional.isEsModulo();
//		this.abreVentana = moduloOperacional.isAbreVentana();
//		this.setStatus(moduloOperacional.getStatus());
//		this.setInicialReg(moduloOperacional.getInicialReg());
//		this.setFinalReg(moduloOperacional.getFinalReg());
//		this.setTotalRegistros(moduloOperacional.getTotalRegistros());
//		this.url=moduloOperacional.getUrl();
//		this.setAcumulativa(moduloOperacional.isAcumulativa());
//	}

	public ModuloOperacionalTO(String idC, String nombre, String descripcion, String llave, String bd, boolean activo, Timestamp fechaCreacion,
			Timestamp fechaModificacion, UsuariosTO usuariosVO, boolean esPadre, String clase, String titulo, TipoAplicacionTO tipoAplicacionVO,
			NivelOperacionTO nivelOperacionVO,String url) {
		this.idC = idC;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.llave = llave;
		this.bd = bd;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuariosVO = usuariosVO;
		this.esPadre = esPadre;
		this.clase = clase;
		this.titulo = titulo;
		this.tipoAplicacionVO = tipoAplicacionVO;
		this.nivelOperacionVO = nivelOperacionVO;
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

	public void setActivo(String activo) {
		this.activo = Boolean.parseBoolean(activo);
		if (activo.equals("on"))
			this.activo = true;
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

	public boolean isEsPadre() {
		return esPadre;
	}

	public void setEsPadre(String esPadre) {
		this.esPadre = Boolean.parseBoolean(esPadre);
		if (esPadre.equals("on"))
			this.esPadre = true;
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

	public boolean isEsModulo() {
		return esModulo;
	}

	public void setEsModulo(String esModulo) {
		this.esModulo = Boolean.parseBoolean(esModulo);
		if (esModulo.equals("on"))
			this.esModulo = true;
	}

	public UsuariosTO getUsuariosVO() {
		return usuariosVO;
	}

	public void setUsuariosVO(UsuariosTO usuariosVO) {
		this.usuariosVO = usuariosVO;
	}

	public TipoAplicacionTO getTipoAplicacionVO() {
		return tipoAplicacionVO;
	}

	public void setTipoAplicacionVO(TipoAplicacionTO tipoAplicacionVO) {
		this.tipoAplicacionVO = tipoAplicacionVO;
	}

	public NivelOperacionTO getNivelOperacionVO() {
		return nivelOperacionVO;
	}

	public void setNivelOperacionVO(NivelOperacionTO nivelOperacionVO) {
		this.nivelOperacionVO = nivelOperacionVO;
	}

	public boolean isAcumulativa() {
		return acumulativa;
	}

	public void setAcumulativa(boolean acumulativa) {
		this.acumulativa = acumulativa;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setEsPadre(boolean esPadre) {
		this.esPadre = esPadre;
	}

	public void setEsModulo(boolean esModulo) {
		this.esModulo = esModulo;
	}

    public boolean isAbreVentana() {
        return abreVentana;
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

}
