package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Alerta;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.Ministerio;
import es.map.ips.model.Plantilla;
import es.map.ips.model.Usuario;



/**
 * El Class EstadoConvocatoriaBean.
 */
public class EstadoConvocatoriaBean {
	
	/** el id. */
	private Integer id;
	
	/** el plantilla. */
	private Plantilla plantilla;
	
	/** el ministerio. */
	private Ministerio ministerio;
	
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
	 * @return el plantilla
	 */
	public Plantilla getPlantilla() {
		return plantilla;
	}
	
	/**
	 * Establece el plantilla.
	 *
	 * @param plantilla el nuevo plantilla
	 */
	public void setPlantilla(Plantilla plantilla) {
		this.plantilla = plantilla;
	}
	
	/**
	 * Obtiene el ministerio.
	 *
	 * @return el ministerio
	 */
	public Ministerio getMinisterio() {
		return ministerio;
	}
	
	/**
	 * Establece el ministerio.
	 *
	 * @param ministerio el nuevo ministerio
	 */
	public void setMinisterio(Ministerio ministerio) {
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
	 * @param siglas el nuevo siglas
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
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
	
	
}
