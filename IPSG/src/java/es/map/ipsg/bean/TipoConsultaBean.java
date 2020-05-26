package es.map.ipsg.bean;

import java.util.Date;

import es.map.ips.model.TipoConsulta;
import es.map.ips.model.Usuario;

/**
 * El Class TipoConsultaBean.
 */
public class TipoConsultaBean {

	/** el id consulta. */
	private Integer idConsulta;
	
	/** el id peticion. */
	private String idPeticion;
	
	/** el id tipo consulta. */
	private TipoConsulta idTipoConsulta;
	
	/** el respuesta. */
	private Boolean respuesta;
	
	/** el fh inicio consulta. */
	private Date fhInicioConsulta;
	
	/** el fh fin consulta. */
	private Date fhFinConsulta;
	
	/** el reintentos. */
	private Integer reintentos;
	
	/** el id usuario consulta. */
	private Usuario idUsuarioConsulta;
	
	
	/**
	 * Obtiene el id consulta.
	 *
	 * @return el id consulta
	 */
	public Integer getIdConsulta() {
		return idConsulta;
	}
	
	/**
	 * Establece el id consulta.
	 *
	 * @param idConsulta el nuevo id consulta
	 */
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}
	
	/**
	 * Obtiene el id peticion.
	 *
	 * @return el id peticion
	 */
	public String getIdPeticion() {
		return idPeticion;
	}
	
	/**
	 * Establece el id peticion.
	 *
	 * @param idPeticion el nuevo id peticion
	 */
	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}
	
	/**
	 * Obtiene el id tipo consulta.
	 *
	 * @return el id tipo consulta
	 */
	public TipoConsulta getIdTipoConsulta() {
		return idTipoConsulta;
	}
	
	/**
	 * Establece el id tipo consulta.
	 *
	 * @param idTipoConsulta el nuevo id tipo consulta
	 */
	public void setIdTipoConsulta(TipoConsulta idTipoConsulta) {
		this.idTipoConsulta = idTipoConsulta;
	}
	
	/**
	 * Obtiene el respuesta.
	 *
	 * @return el respuesta
	 */
	public Boolean getRespuesta() {
		return respuesta;
	}
	
	/**
	 * Establece el respuesta.
	 *
	 * @param respuesta el nuevo respuesta
	 */
	public void setRespuesta(Boolean respuesta) {
		this.respuesta = respuesta;
	}
	
	/**
	 * Obtiene el fh inicio consulta.
	 *
	 * @return el fh inicio consulta
	 */
	public Date getFhInicioConsulta() {
		return fhInicioConsulta;
	}
	
	/**
	 * Establece el fh inicio consulta.
	 *
	 * @param fhInicioConsulta el nuevo fh inicio consulta
	 */
	public void setFhInicioConsulta(Date fhInicioConsulta) {
		this.fhInicioConsulta = fhInicioConsulta;
	}
	
	/**
	 * Obtiene el fh fin consulta.
	 *
	 * @return el fh fin consulta
	 */
	public Date getFhFinConsulta() {
		return fhFinConsulta;
	}
	
	/**
	 * Establece el fh fin consulta.
	 *
	 * @param fhFinConsulta el nuevo fh fin consulta
	 */
	public void setFhFinConsulta(Date fhFinConsulta) {
		this.fhFinConsulta = fhFinConsulta;
	}
	
	/**
	 * Obtiene el reintentos.
	 *
	 * @return el reintentos
	 */
	public Integer getReintentos() {
		return reintentos;
	}
	
	/**
	 * Establece el reintentos.
	 *
	 * @param reintentos el nuevo reintentos
	 */
	public void setReintentos(Integer reintentos) {
		this.reintentos = reintentos;
	}
	
	/**
	 * Obtiene el id usuario consulta.
	 *
	 * @return el id usuario consulta
	 */
	public Usuario getIdUsuarioConsulta() {
		return idUsuarioConsulta;
	}
	
	/**
	 * Establece el id usuario consulta.
	 *
	 * @param idUsuarioConsulta el nuevo id usuario consulta
	 */
	public void setIdUsuarioConsulta(Usuario idUsuarioConsulta) {
		this.idUsuarioConsulta = idUsuarioConsulta;
	}

	
	
	
}
