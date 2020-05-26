package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.PagoSolicitud;

/**
 * El Class MotivoExencionTasaBean.
 */
public class MotivoExencionTasaBean {
	
	/** el id motivo ex. */
	private Integer idMotivoEx;
	
	/** el descripcion mo ex. */
	private String descripcionMoEx;
	
	/** el texto explicativo. */
	private String textoExplicativo;
	
	/** el porcentaje descuento. */
	private Integer porcentajeDescuento;
	
	/** el estado mo ex. */
	private Character estadoMoEx;
	
	/** el codigo mo ex. */
	private String codigoMoEx;
	
	/** el convocatorias mo ex. */
	private Set<Convocatoria> convocatoriasMoEx = new HashSet<Convocatoria>(0);
	
	/** el pago solicituds mo ex. */
	private Set<PagoSolicitud> pagoSolicitudsMoEx = new HashSet<PagoSolicitud>(0);
	
	/** el num total mo ex. */
	private Integer numTotalMoEx;
	
	/** el visibilidad mo ex. */
	private Boolean visibilidadMoEx;
	
	/**
	 * Obtiene el id motivo ex.
	 *
	 * @return el id motivo ex
	 */
	public Integer getIdMotivoEx() {
		return idMotivoEx;
	}
	
	/**
	 * Establece el id motivo ex.
	 *
	 * @param idMotivoEx el nuevo id motivo ex
	 */
	public void setIdMotivoEx(Integer idMotivoEx) {
		this.idMotivoEx = idMotivoEx;
	}

	/**
	 * Obtiene el descripcion mo ex.
	 *
	 * @return el descripcion mo ex
	 */
	public String getDescripcionMoEx() {
		return descripcionMoEx;
	}
	
	/**
	 * Establece el descripcion mo ex.
	 *
	 * @param descripcionMoEx el nuevo descripcion mo ex
	 */
	public void setDescripcionMoEx(String descripcionMoEx) {
		this.descripcionMoEx = descripcionMoEx;
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
	 * Obtiene el estado mo ex.
	 *
	 * @return el estado mo ex
	 */
	public Character getEstadoMoEx() {
		return estadoMoEx;
	}
	
	/**
	 * Establece el estado mo ex.
	 *
	 * @param estadoMoEx el nuevo estado mo ex
	 */
	public void setEstadoMoEx(Character estadoMoEx) {
		this.estadoMoEx = estadoMoEx;
	}
	
	/**
	 * Obtiene el codigo mo ex.
	 *
	 * @return el codigo mo ex
	 */
	public String getCodigoMoEx() {
		return codigoMoEx;
	}
	
	/**
	 * Establece el codigo mo ex.
	 *
	 * @param codigoMoEx el nuevo codigo mo ex
	 */
	public void setCodigoMoEx(String codigoMoEx) {
		this.codigoMoEx = codigoMoEx;
	}
	
	/**
	 * Obtiene el convocatorias mo ex.
	 *
	 * @return el convocatorias mo ex
	 */
	public Set<Convocatoria> getConvocatoriasMoEx() {
		return convocatoriasMoEx;
	}
	
	/**
	 * Establece el convocatorias mo ex.
	 *
	 * @param convocatoriasMoEx el nuevo convocatorias mo ex
	 */
	public void setConvocatoriasMoEx(Set<Convocatoria> convocatoriasMoEx) {
		this.convocatoriasMoEx = convocatoriasMoEx;
	}
	
	/**
	 * Obtiene el pago solicituds mo ex.
	 *
	 * @return el pago solicituds mo ex
	 */
	public Set<PagoSolicitud> getPagoSolicitudsMoEx() {
		return pagoSolicitudsMoEx;
	}
	
	/**
	 * Establece el pago solicituds mo ex.
	 *
	 * @param pagoSolicitudsMoEx el nuevo pago solicituds mo ex
	 */
	public void setPagoSolicitudsMoEx(Set<PagoSolicitud> pagoSolicitudsMoEx) {
		this.pagoSolicitudsMoEx = pagoSolicitudsMoEx;
	}
	
	/**
	 * Obtiene el num total mo ex.
	 *
	 * @return el num total mo ex
	 */
	public Integer getNumTotalMoEx() {
		return numTotalMoEx;
	}
	
	/**
	 * Establece el num total mo ex.
	 *
	 * @param numTotalMoEx el nuevo num total mo ex
	 */
	public void setNumTotalMoEx(Integer numTotalMoEx) {
		this.numTotalMoEx = numTotalMoEx;
	}
	
	/**
	 * Obtiene el visibilidad mo ex.
	 *
	 * @return el visibilidad mo ex
	 */
	public Boolean getVisibilidadMoEx() {
		return visibilidadMoEx;
	}
	
	/**
	 * Establece el visibilidad mo ex.
	 *
	 * @param visibilidadMoEx el nuevo visibilidad mo ex
	 */
	public void setVisibilidadMoEx(Boolean visibilidadMoEx) {
		this.visibilidadMoEx = visibilidadMoEx;
	}
	
}
