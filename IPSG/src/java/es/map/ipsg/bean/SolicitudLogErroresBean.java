package es.map.ipsg.bean;

/**
 * SolicitudBean.
 *
 * @author amartinl
 */
public class SolicitudLogErroresBean {
	
	/** el id. */
	private Long id;
	
	/** el numero solicitud. */
	private String numeroSolicitud = "";
	
	/** el id estado solicitud. */
	private Integer idEstadoSolicitud ;

	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el numero solicitud.
	 *
	 * @return el numero solicitud
	 */
	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}
	
	/**
	 * Establece el numero solicitud.
	 *
	 * @param numeroSolicitud el nuevo numero solicitud
	 */
	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	
	/**
	 * Obtiene el id estado solicitud.
	 *
	 * @return el id estado solicitud
	 */
	public Integer getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}
	
	/**
	 * Establece el id estado solicitud.
	 *
	 * @param idEstadoSolicitud el nuevo id estado solicitud
	 */
	public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}


	

}
