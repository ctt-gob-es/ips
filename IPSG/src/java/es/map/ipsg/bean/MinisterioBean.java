package es.map.ipsg.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.CentroGestor;


/**
 * El Class MinisterioBean.
 */
public class MinisterioBean {

	/** el id. */
	private Integer id;	
	
	/** el siglas. */
	private String siglas;
	
	/** el descripcion. */
	private String descripcion;	
	
	/** el codigo. */
	private String codigo;
	
	/** el url. */
	private String url;
	
	/** el estado. */
	private Character estado;
	
	/** el centro gestors. */
	private Set<CentroGestor> centroGestors = new HashSet<CentroGestor>(0);
	
	/** el id ministerio previo. */
	private Integer idMinisterioPrevio;
    
    /** el fecha sustitucion. */
    private Date fechaSustitucion;
    
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
	 * Obtiene el url.
	 *
	 * @return el url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Establece el url.
	 *
	 * @param url el nuevo url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Obtiene el estado.
	 *
	 * @return  estado Character
	 */
	public Character getEstado() {
		return estado;
	}

	/**
	 * Establece el estado.
	 *
	 * @param estado Character
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}

	/**
	 * Obtiene el centro gestors.
	 *
	 * @return Set<CentroGestor>
	 */
	public Set<CentroGestor> getCentroGestors() {
		return centroGestors;
	}

	/**
	 * Establece el centro gestors.
	 *
	 * @param centroGestors Set<CentroGestor>
	 */
	public void setCentroGestors(Set<CentroGestor> centroGestors) {
		this.centroGestors = centroGestors;
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
	 * Obtiene el num registros.
	 *
	 * @return numRegistros String
	 */
	public String getNumRegistros() {
		return numRegistros;
	}
	
	/**
	 * Establece el num registros.
	 *
	 * @param numRegistros String
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * Obtiene el id ministerio previo.
	 *
	 * @return idMinisterioPrevio Integer
	 */
	public Integer getIdMinisterioPrevio() {
		return idMinisterioPrevio;
	}

	/**
	 * Establece el id ministerio previo.
	 *
	 * @param idMinisterioPrevio Integer
	 */
	public void setIdMinisterioPrevio(Integer idMinisterioPrevio) {
		this.idMinisterioPrevio = idMinisterioPrevio;
	}

	/**
	 * Obtiene el fecha sustitucion.
	 *
	 * @return fechaSustitucion Date
	 */
	public Date getFechaSustitucion() {
		return fechaSustitucion;
	}

	/**
	 * Establece el fecha sustitucion.
	 *
	 * @param fechaSustitucion Date
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
	
}
