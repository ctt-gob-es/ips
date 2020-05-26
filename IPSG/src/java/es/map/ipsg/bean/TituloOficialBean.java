package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.SolicitudComun;

/**
 * TituloOficialBean.
 *
 * @author amartinl
 */
public class TituloOficialBean {
	
	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private Character estado;
	
	/** el codigo. */
	private String codigo;
	
	/** el cod general. */
	private String codGeneral;
	
	/** el convocatorias. */
	private Set<Convocatoria> convocatorias = new HashSet<Convocatoria>(0);
	
	/** el solicituds. */
	private Set<SolicitudComun> solicituds = new HashSet<SolicitudComun>(0);
	
	/** el num total. */
	private int numTotal;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/**
	 * Devuelve el numero total de Titulos.
	 *
	 * @return numTotal int
	 */
	public int getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el numero total de Titulos.
	 *
	 * @param numTotal int
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}
	
	/**
	 * Devuelve el id del titulo.
	 *
	 * @return id  Integer
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id del titulo.
	 *
	 * @param id Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Devuelve la descripcion del titulo.
	 *
	 * @return descripcion String
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece la descripcion del titulo.
	 *
	 * @param descripcion String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Devuelve el Estado A/D.
	 *
	 * @return estado Character
	 */
	public Character getEstado() {
		return estado;
	}
	
	/**
	 * Establece el Estado  A/D.
	 *
	 * @param estado Character
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el convocatorias.
	 *
	 * @return convocatorias
	 */
	public Set<Convocatoria> getConvocatorias() {
		return convocatorias;
	}
	
	/**
	 * Establece el convocatorias.
	 *
	 * @param convocatorias Set<Convocatoria>
	 */
	public void setConvocatorias(Set<Convocatoria> convocatorias) {
		this.convocatorias = convocatorias;
	}
	
	/**
	 * Obtiene el solicituds.
	 *
	 * @return solicituds
	 */
	public Set<SolicitudComun> getSolicituds() {
		return solicituds;
	}
	
	
	/** 
	 * @param solicituds  Set<Solicitud>
	 */
	public void setSolicituds(Set<SolicitudComun> solicituds) {
		this.solicituds = solicituds;
	}

	/**
	 * Devuelve el codigo.
	 *
	 * @return codigo String
	 */	
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo String
	 */	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Devuelve el codigo general.
	 *
	 * @return codGeneral String
	 */	
	public String getCodGeneral() {
		return codGeneral;
	}
	
	/**
	 * Establece el codigo general.
	 *
	 * @param codGeneral el nuevo cod general
	 */	
	public void setCodGeneral(String codGeneral) {
		this.codGeneral = codGeneral;
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
