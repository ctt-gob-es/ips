package es.map.ipsc.bean;

/**
 * El Class SolicitudPropiosBean.
 */
public class SolicitudPropiosBean {

	/** el id. */
	private Long id;
	
	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el id campo. */
	private Long idCampo;
	
	/** el valor. */
	private String valor;
	
	/** el campos propios bean. */
	private CamposPropiosBean camposPropiosBean;
	
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
	 * Obtiene el id campo.
	 *
	 * @return el id campo
	 */
	public Long getIdCampo() {
		return idCampo;
	}
	
	/**
	 * Establece el id campo.
	 *
	 * @param idCampo el nuevo id campo
	 */
	public void setIdCampo(Long idCampo) {
		this.idCampo = idCampo;
	}
	
	/**
	 * Obtiene el valor.
	 *
	 * @return el valor
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * Establece el valor.
	 *
	 * @param valor el nuevo valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	/**
	 * Obtiene el campos propios bean.
	 *
	 * @return el campos propios bean
	 */
	public CamposPropiosBean getCamposPropiosBean() {
		return camposPropiosBean;
	}
	
	/**
	 * Establece el campos propios bean.
	 *
	 * @param camposPropiosBean el nuevo campos propios bean
	 */
	public void setCamposPropiosBean(CamposPropiosBean camposPropiosBean) {
		this.camposPropiosBean = camposPropiosBean;
	}

}
