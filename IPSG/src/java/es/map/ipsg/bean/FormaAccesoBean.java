package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.TipoAcceso;


/**
 * El Class FormaAccesoBean.
 */
public class FormaAccesoBean {
	
	/** el id. */
	private Integer id;
	
	/** el tipo acceso. */
	private TipoAcceso tipoAcceso;
	
	/** el id tipo acceso. */
	private int idTipoAcceso;
	
	/** el des tipo acceso. */
	private String desTipoAcceso;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private Character estado;
	
	/** el convocatorias. */
	private Set<Convocatoria> convocatorias = new HashSet<Convocatoria>(0);
	
	/** el num total. */
	private int numTotal;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
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
	 * Obtiene el tipo acceso.
	 *
	 * @return el tipo acceso
	 */
	public TipoAcceso getTipoAcceso() {
		return tipoAcceso;
	}
	
	/**
	 * Establece el tipo acceso.
	 *
	 * @param tipoAcceso el nuevo tipo acceso
	 */
	public void setTipoAcceso(TipoAcceso tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
	
	/**
	 * Obtiene el id tipo acceso.
	 *
	 * @return the idTipoAcceso
	 */
	public int getIdTipoAcceso() {
		return idTipoAcceso;
	}
	
	/**
	 * Establece el id tipo acceso.
	 *
	 * @param idTipoAcceso the idTipoAcceso to set
	 */
	public void setIdTipoAcceso(int idTipoAcceso) {
		this.idTipoAcceso = idTipoAcceso;
	}
	
	/**
	 * Obtiene el des tipo acceso.
	 *
	 * @return the desTipoAcceso
	 */
	public String getDesTipoAcceso() {
		return desTipoAcceso;
	}
	
	/**
	 * Establece el des tipo acceso.
	 *
	 * @param desTipoAcceso the desTipoAcceso to set
	 */
	public void setDesTipoAcceso(String desTipoAcceso) {
		this.desTipoAcceso = desTipoAcceso;
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
	 * Obtiene el convocatorias.
	 *
	 * @return el convocatorias
	 */
	public Set<Convocatoria> getConvocatorias() {
		return convocatorias;
	}
	
	/**
	 * Establece el convocatorias.
	 *
	 * @param convocatorias el nuevo convocatorias
	 */
	public void setConvocatorias(Set<Convocatoria> convocatorias) {
		this.convocatorias = convocatorias;
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
