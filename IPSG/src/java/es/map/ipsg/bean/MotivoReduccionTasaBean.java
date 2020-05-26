package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.PagoSolicitud;

/**
 * El Class MotivoReduccionTasaBean.
 */
public class MotivoReduccionTasaBean {
	
	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el texto explicativo. */
	private String textoExplicativo;
	
	/** el porcentaje descuento. */
	private Integer porcentajeDescuento;
	
	/** el estado. */
	private Character estado;
	
	/** el codigo. */
	private String codigo;
	
	/** el convocatorias. */
	private Set<Convocatoria> convocatorias = new HashSet<Convocatoria>(0);
	
	/** el pago solicituds. */
	private Set<PagoSolicitud> pagoSolicituds = new HashSet<PagoSolicitud>(0);
	
	/** el num total. */
	private Integer numTotal;
	
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
	 * Obtiene el texto explicativo.
	 *
	 * @return el texto explicativo
	 */
	public String getTextoExplicativo() {
		return textoExplicativo;
	}
	
	/**
	 * Establece el texto explicativo.
	 *
	 * @param textoExplicativo el nuevo texto explicativo
	 */
	public void setTextoExplicativo(String textoExplicativo) {
		this.textoExplicativo = textoExplicativo;
	}
	
	/**
	 * Obtiene el porcentaje descuento.
	 *
	 * @return el porcentaje descuento
	 */
	public Integer getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	
	/**
	 * Establece el porcentaje descuento.
	 *
	 * @param porcentajeDescuento el nuevo porcentaje descuento
	 */
	public void setPorcentajeDescuento(Integer porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
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
	 * Obtiene el pago solicituds.
	 *
	 * @return el pago solicituds
	 */
	public Set<PagoSolicitud> getPagoSolicituds() {
		return pagoSolicituds;
	}
	
	/**
	 * Establece el pago solicituds.
	 *
	 * @param pagoSolicituds el nuevo pago solicituds
	 */
	public void setPagoSolicituds(Set<PagoSolicitud> pagoSolicituds) {
		this.pagoSolicituds = pagoSolicituds;
	}
	
	/**
	 * Obtiene el num total.
	 *
	 * @return the numTotal
	 */
	public Integer getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal the numTotal to set
	 */
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
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
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public String getCodigo() {
		return codigo;
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
