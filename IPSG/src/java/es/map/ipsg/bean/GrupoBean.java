package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.Tarifa;


/**
 * El Class GrupoBean.
 */
public class GrupoBean {
	
	/** el id. */
	private Integer id;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private Character estado;
	
	/** el siglas. */
	private String siglas;
	
	/** el tarifas. */
	private Set<Tarifa> tarifas = new HashSet<Tarifa>(0);
	
	/** el cuerpo escalas. */
	private Set<CuerpoEscala> cuerpoEscalas = new HashSet<CuerpoEscala>(0);
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el num total. */
	private int numTotal;	
	
	/** el num registros. */
	private String numRegistros;
	
	
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
	 * Obtiene el tarifas.
	 *
	 * @return el tarifas
	 */
	public Set<Tarifa> getTarifas() {
		return tarifas;
	}
	
	/**
	 * Establece el tarifas.
	 *
	 * @param tarifas el nuevo tarifas
	 */
	public void setTarifas(Set<Tarifa> tarifas) {
		this.tarifas = tarifas;
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
	 * Obtiene el num total.
	 *
	 * @return el num total
	 */
	public int getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal el nuevo num total
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
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
	 * Establece el siglas.
	 *
	 * @param siglas el nuevo siglas
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
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
	
	
}
