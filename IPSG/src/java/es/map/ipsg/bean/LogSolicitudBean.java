package es.map.ipsg.bean;

import java.util.Date;



/**
 * El Class LogSolicitudBean.
 */
public class LogSolicitudBean {

	/** el id. */
	private Integer id;
	
	/** el fecha. */
	private Date fecha;
	
	/** el tipo operacion. */
	private Character tipoOperacion;
	
	/** el actor. */
	private String actor;
	
	/** el detalle operacion. */
	private String detalleOperacion;
	
	/** el id estado anterior. */
	private Integer id_estado_anterior;
	
	/** el id estado actual. */
	private Integer id_estado_actual;
	
	/** el tipo actor. */
	private Character tipoActor;
	
	/** el estado anterior. */
	private String estadoAnterior;
	
	/** el estado actual. */
	private String estadoActual;
	
	/** el dni ciudadano. */
	private String dniCiudadano;
	
	/** el numero justificante. */
	private String numeroJustificante;
	
	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el id estado actual. */
	private String idEstadoActual;
	
	/** el id estado anterior. */
	private String idEstadoAnterior;
	
	/** el num total. */
	private Integer numTotal;
	
	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el des centro gestor. */
	private String desCentroGestor;
	
	/** el des cuerpo escala. */
	private String desCuerpoEscala;

	
	
	/**
	 * Obtiene el id estado actual.
	 *
	 * @return el id estado actual
	 */
	public String getIdEstadoActual() {
		return idEstadoActual;
	}

	/**
	 * Establece el id estado actual.
	 *
	 * @param idEstadoActual el nuevo id estado actual
	 */
	public void setIdEstadoActual(String idEstadoActual) {
		this.idEstadoActual = idEstadoActual;
	}

	/**
	 * Obtiene el id estado anterior.
	 *
	 * @return el id estado anterior
	 */
	public String getIdEstadoAnterior() {
		return idEstadoAnterior;
	}

	/**
	 * Establece el id estado anterior.
	 *
	 * @param idEstadoAnterior el nuevo id estado anterior
	 */
	public void setIdEstadoAnterior(String idEstadoAnterior) {
		this.idEstadoAnterior = idEstadoAnterior;
	}

	/**
	 * Obtiene el id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Establece el id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * Obtiene el fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Establece el fecha.
	 *
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene el tipo operacion.
	 *
	 * @return the tipoOperacion
	 */
	public Character getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Establece el tipo operacion.
	 *
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(Character tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Obtiene el actor.
	 *
	 * @return the actor
	 */
	public String getActor() {
		return actor;
	}

	/**
	 * Establece el actor.
	 *
	 * @param actor the actor to set
	 */
	public void setActor(String actor) {
		this.actor = actor;
	}

	/**
	 * Obtiene el detalle operacion.
	 *
	 * @return the detalleOperacion
	 */
	public String getDetalleOperacion() {
		return detalleOperacion;
	}

	/**
	 * Establece el detalle operacion.
	 *
	 * @param detalleOperacion the detalleOperacion to set
	 */
	public void setDetalleOperacion(String detalleOperacion) {
		this.detalleOperacion = detalleOperacion;
	}

	/**
	 * Obtiene el estado anterior.
	 *
	 * @return the estadoAnterior
	 */
	public String getEstadoAnterior() {
		return estadoAnterior;
	}

	/**
	 * Establece el estado anterior.
	 *
	 * @param estadoAnterior the estadoAnterior to set
	 */
	public void setEstadoAnterior(String estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	/**
	 * Obtiene el estado actual.
	 *
	 * @return the estadoActual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}

	/**
	 * Establece el estado actual.
	 *
	 * @param estadoActual the estadoActual to set
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	/**
	 * Obtiene el dni ciudadano.
	 *
	 * @return the dniCiudadano
	 */
	public String getDniCiudadano() {
		return dniCiudadano;
	}

	/**
	 * Establece el dni ciudadano.
	 *
	 * @param dniCiudadano the dniCiudadano to set
	 */
	public void setDniCiudadano(String dniCiudadano) {
		this.dniCiudadano = dniCiudadano;
	}

	/**
	 * Obtiene el numero justificante.
	 *
	 * @return the numeroJustificante
	 */
	public String getNumeroJustificante() {
		return numeroJustificante;
	}

	/**
	 * Establece el numero justificante.
	 *
	 * @param numeroJustificante the numeroJustificante to set
	 */
	public void setNumeroJustificante(String numeroJustificante) {
		this.numeroJustificante = numeroJustificante;
	}

	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return the idConvocatoria
	 */
	public Long getIdConvocatoria() {
		return idConvocatoria;
	}

	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria the idConvocatoria to set
	 */
	public void setIdConvocatoria(Long idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
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
	 * Obtiene el id estado anterior.
	 *
	 * @return the id_estado_anterior
	 */
	public Integer getId_estado_anterior() {
		return id_estado_anterior;
	}

	/**
	 * Establece el id estado anterior.
	 *
	 * @param id_estado_anterior the id_estado_anterior to set
	 */
	public void setId_estado_anterior(Integer id_estado_anterior) {
		this.id_estado_anterior = id_estado_anterior;
	}

	/**
	 * Obtiene el id estado actual.
	 *
	 * @return the id_estado_actual
	 */
	public Integer getId_estado_actual() {
		return id_estado_actual;
	}

	/**
	 * Establece el id estado actual.
	 *
	 * @param id_estado_actual the id_estado_actual to set
	 */
	public void setId_estado_actual(Integer id_estado_actual) {
		this.id_estado_actual = id_estado_actual;
	}

	/**
	 * Obtiene el tipo actor.
	 *
	 * @return the tipoActor
	 */
	public Character getTipoActor() {
		return tipoActor;
	}

	/**
	 * Establece el tipo actor.
	 *
	 * @param tipoActor the tipoActor to set
	 */
	public void setTipoActor(Character tipoActor) {
		this.tipoActor = tipoActor;
	}

	/**
	 * Obtiene el id solicitud.
	 *
	 * @return el id solicitud
	 */
	public Long getIdSolicitud() {
		return idSolicitud;
	}

	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	/**
	 * Obtiene el des centro gestor.
	 *
	 * @return el des centro gestor
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}

	/**
	 * Establece el des centro gestor.
	 *
	 * @param desCentroGestor el nuevo des centro gestor
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}

	/**
	 * Obtiene el des cuerpo escala.
	 *
	 * @return el des cuerpo escala
	 */
	public String getDesCuerpoEscala() {
		return desCuerpoEscala;
	}

	/**
	 * Establece el des cuerpo escala.
	 *
	 * @param desCuerpoEscala el nuevo des cuerpo escala
	 */
	public void setDesCuerpoEscala(String desCuerpoEscala) {
		this.desCuerpoEscala = desCuerpoEscala;
	}
	
	
}
