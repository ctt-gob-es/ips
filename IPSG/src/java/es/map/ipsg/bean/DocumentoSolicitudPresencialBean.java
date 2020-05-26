package es.map.ipsg.bean;

import org.springframework.web.multipart.MultipartFile;

/**
 * El Class DocumentoSolicitudPresencialBean.
 */
public class DocumentoSolicitudPresencialBean {
	
	/** el file. */
	private MultipartFile file;
	
	/** el nombre. */
	private String nombre;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el id tipo documento. */
	private String idTipoDocumento;
	
	/** el entorno. */
	private String entorno;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el id documento. */
	private String idDocumento;
	
	/** el id doc. */
	private String idDoc;
	
	/** el resultado. */
	private String resultado;
	
	/**
	 * Obtiene el file.
	 *
	 * @return el file
	 */
	public MultipartFile getFile() {
		return file;
	}
	
	/**
	 * Establece el file.
	 *
	 * @param file el nuevo file
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public String getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	
	/**
	 * Obtiene el id tipo documento.
	 *
	 * @return el id tipo documento
	 */
	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}
	
	/**
	 * Establece el id tipo documento.
	 *
	 * @param idTipoDocumento el nuevo id tipo documento
	 */
	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	
	/**
	 * Obtiene el entorno.
	 *
	 * @return el entorno
	 */
	public String getEntorno() {
		return entorno;
	}
	
	/**
	 * Establece el entorno.
	 *
	 * @param entorno el nuevo entorno
	 */
	public void setEntorno(String entorno) {
		this.entorno = entorno;
	}
	
	/**
	 * Obtiene el id solicitud.
	 *
	 * @return el id solicitud
	 */
	public String getIdSolicitud() {
		return idSolicitud;
	}
	
	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	/**
	 * Obtiene el id documento.
	 *
	 * @return el id documento
	 */
	public String getIdDocumento() {
		return idDocumento;
	}
	
	/**
	 * Establece el id documento.
	 *
	 * @param idDocumento el nuevo id documento
	 */
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	
	/**
	 * Obtiene el id doc.
	 *
	 * @return el id doc
	 */
	public String getIdDoc() {
		return idDoc;
	}
	
	/**
	 * Establece el id doc.
	 *
	 * @param idDoc el nuevo id doc
	 */
	public void setIdDoc(String idDoc) {
		this.idDoc = idDoc;
	}
	
	/**
	 * Obtiene el resultado.
	 *
	 * @return el resultado
	 */
	public String getResultado() {
		return resultado;
	}
	
	/**
	 * Establece el resultado.
	 *
	 * @param resultado el nuevo resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	
	
}
