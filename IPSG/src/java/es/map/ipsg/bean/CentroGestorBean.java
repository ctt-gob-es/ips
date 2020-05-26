package es.map.ipsg.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Alerta;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.Modelo;
import es.map.ips.model.Plantilla;
import es.map.ips.model.Usuario;



/**
 * El Class CentroGestorBean.
 */
public class CentroGestorBean {
	
	/** el id. */
	private Integer id;
	
	/** el plantilla. */
	private Plantilla plantilla;
	
	/** el id plantilla. */
	private Long idPlantilla;
	
	/** el des plantilla. */
	private String desPlantilla;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el id ministerio. */
	private Integer idMinisterio;
	
	/** el siglas. */
	private String siglas;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el codigo. */
	private String codigo;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el estado. */
	private Character estado;
	
	/** el cuerpo escalas. */
	private Set<CuerpoEscala> cuerpoEscalas = new HashSet<CuerpoEscala>(0);
	
	/** el usuarios. */
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);
	
	/** el alertas. */
	private Set<Alerta> alertas = new HashSet<Alerta>(0);
	
	/** el modelo. */
	private Modelo modelo;
	
	/** el parametro. */
	private String parametro;
	
	/** el cod plantilla. */
	private Long codPlantilla;
	
	/** el num total. */
	private int numTotal;
	
	/** el num registros. */
	private String numRegistros;
	
	/** el id centro gestor sust. */
	private Integer idCentroGestorSust;
	
	/** el fecha sustitucion. */
	private Date fechaSustitucion;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el id modelo. */
	private Integer idModelo;
	
	/** el fecha inicio inhabil. */
	private Date fechaInicioInhabil;
	
	/** el fecha fin inhabil. */
	private Date fechaFinInhabil;
	
	
	/**
	 * Obtiene el modelo.
	 *
	 * @return el modelo
	 */
	public Modelo getModelo() {
		return modelo;
	}
	
	/**
	 * Establece el modelo.
	 *
	 * @param modelo el nuevo modelo
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * Obtiene el id modelo.
	 *
	 * @return el id modelo
	 */
	public Integer getidModelo() {
		return idModelo;
	}
	
	/**
	 * Establece el id modelo.
	 *
	 * @param idModelo el nuevo id modelo
	 */
	public void setidModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}
	
	/**
	 * Obtiene el num registros.
	 *
	 * @return el num registros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}
	
	/**
	 * Establece el num registros.
	 *
	 * @param numRegistros el nuevo num registros
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	
	/**
	 * Obtiene el cod plantilla.
	 *
	 * @return el cod plantilla
	 */
	public Long getCodPlantilla() {
		return codPlantilla;
	}
	
	/**
	 * Establece el cod plantilla.
	 *
	 * @param codPlantilla el nuevo cod plantilla
	 */
	public void setCodPlantilla(Long codPlantilla) {
		this.codPlantilla = codPlantilla;
	}
	
	/**
	 * Obtiene el parametro.
	 *
	 * @return el parametro
	 */
	public String getParametro() {
		return parametro;
	}
	
	/**
	 * Establece el parametro.
	 *
	 * @param parametro el nuevo parametro
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el plantilla.
	 *
	 * @return the plantilla
	 */
	public Plantilla getPlantilla() {
		return plantilla;
	}
	
	/**
	 * Establece el plantilla.
	 *
	 * @param plantilla the plantilla to set
	 */
	public void setPlantilla(Plantilla plantilla) {
		this.plantilla = plantilla;
	}
	
	/**
	 * Obtiene el id plantilla.
	 *
	 * @return the idPlantilla
	 */
	public Long getIdPlantilla() {
		return idPlantilla;
	}
	
	/**
	 * Establece el id plantilla.
	 *
	 * @param idPlantilla the idPlantilla to set
	 */
	public void setIdPlantilla(Long idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
	
	/**
	 * Obtiene el des plantilla.
	 *
	 * @return the desPlantilla
	 */
	public String getDesPlantilla() {
		return desPlantilla;
	}
	
	/**
	 * Establece el des plantilla.
	 *
	 * @param desPlantilla the desPlantilla to set
	 */
	public void setDesPlantilla(String desPlantilla) {
		this.desPlantilla = desPlantilla;
	}
	
	/**
	 * Obtiene el id ministerio.
	 *
	 * @return the idMinisterio
	 */
	public Integer getIdMinisterio() {
		return idMinisterio;
	}
	
	/**
	 * Establece el id ministerio.
	 *
	 * @param idMinisterio the idMinisterio to set
	 */
	public void setIdMinisterio(Integer idMinisterio) {
		this.idMinisterio = idMinisterio;
	}
	
	/**
	 * Obtiene el ministerio.
	 *
	 * @return el ministerio
	 */
	public String getMinisterio() {
		return ministerio;
	}
	
	/**
	 * Establece el ministerio.
	 *
	 * @param ministerio el nuevo ministerio
	 */
	public void setMinisterio(String ministerio) {
		this.ministerio = ministerio;
	}
	
	/**
	 * Obtiene el siglas.
	 *
	 * @return el siglas
	 */
	public String getSiglas() {
		return siglas;
	}
	
	/**
	 * Establece el siglas.
	 *
	 * @param siglasMinisterio el nuevo siglas
	 */
	public void setSiglas(String siglasMinisterio) {
		this.siglas = siglasMinisterio;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtiene el ejercicio.
	 *
	 * @return el ejercicio
	 */
	public String getEjercicio() {
		return ejercicio;
	}
	
	/**
	 * Establece el ejercicio.
	 *
	 * @param ejercicio el nuevo ejercicio
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public Character getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el cuerpo escalas.
	 *
	 * @return el cuerpo escalas
	 */
	public Set<CuerpoEscala> getCuerpoEscalas() {
		return cuerpoEscalas;
	}
	
	/**
	 * Establece el cuerpo escalas.
	 *
	 * @param cuerpoEscalas el nuevo cuerpo escalas
	 */
	public void setCuerpoEscalas(Set<CuerpoEscala> cuerpoEscalas) {
		this.cuerpoEscalas = cuerpoEscalas;
	}
	
	/**
	 * Obtiene el usuarios.
	 *
	 * @return el usuarios
	 */
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	
	/**
	 * Establece el usuarios.
	 *
	 * @param usuarios el nuevo usuarios
	 */
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	/**
	 * Obtiene el alertas.
	 *
	 * @return el alertas
	 */
	public Set<Alerta> getAlertas() {
		return alertas;
	}
	
	/**
	 * Establece el alertas.
	 *
	 * @param alertas el nuevo alertas
	 */
	public void setAlertas(Set<Alerta> alertas) {
		this.alertas = alertas;
	}
	
	/**
	 * Obtiene el num total.
	 *
	 * @return the numTotal
	 */
	public int getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal the numTotal to set
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}
	
	/**
	 * Obtiene el id centro gestor sust.
	 *
	 * @return el id centro gestor sust
	 */
	public Integer getIdCentroGestorSust() {
		return idCentroGestorSust;
	}
	
	/**
	 * Establece el id centro gestor sust.
	 *
	 * @param idCentroGestorSust el nuevo id centro gestor sust
	 */
	public void setIdCentroGestorSust(Integer idCentroGestorSust) {
		this.idCentroGestorSust = idCentroGestorSust;
	}
	
	/**
	 * Obtiene el fecha sustitucion.
	 *
	 * @return el fecha sustitucion
	 */
	public Date getFechaSustitucion() {
		return fechaSustitucion;
	}
	
	/**
	 * Establece el fecha sustitucion.
	 *
	 * @param fechaSustitucion el nuevo fecha sustitucion
	 */
	public void setFechaSustitucion(Date fechaSustitucion) {
		this.fechaSustitucion = fechaSustitucion;
	}
	
	/**
	 * Obtiene el visibilidad.
	 *
	 * @return el visibilidad
	 */
	public Boolean getVisibilidad() {
		return visibilidad;
	}
	
	/**
	 * Establece el visibilidad.
	 *
	 * @param visibilidad el nuevo visibilidad
	 */
	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;
	}
	
	/**
	 * Obtiene el fecha inicio inhabil.
	 *
	 * @return el fecha inicio inhabil
	 */
	public Date getFechaInicioInhabil() {
		return fechaInicioInhabil;
	}
	
	/**
	 * Establece el fecha inicio inhabil.
	 *
	 * @param fechaInicioInhabil el nuevo fecha inicio inhabil
	 */
	public void setFechaInicioInhabil(Date fechaInicioInhabil) {
		this.fechaInicioInhabil = fechaInicioInhabil;
	}
	
	/**
	 * Obtiene el fecha fin inhabil.
	 *
	 * @return el fecha fin inhabil
	 */
	public Date getFechaFinInhabil() {
		return fechaFinInhabil;
	}
	
	/**
	 * Establece el fecha fin inhabil.
	 *
	 * @param fechaFinInhabil el nuevo fecha fin inhabil
	 */
	public void setFechaFinInhabil(Date fechaFinInhabil) {
		this.fechaFinInhabil = fechaFinInhabil;
	}
	
	
	
	
}
