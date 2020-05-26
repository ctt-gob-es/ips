package es.map.ipsg.form;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.RegistroSolicitudJustificanteBean;


/**
 * El Class GestionarJustificanteForm.
 */
public class GestionarJustificanteForm extends SpringForm {

	/** el nombre. */
	private String nombre;
	
	/** el id justificante. */
	private Long idJustificante;
	
	/** el doc bean. */
	private DocumentoBean docBean ;
	
	/** el arr bytes. */
	private byte[] arrBytes;
	
	/** el tipo justificante. */
	private String tipoJustificante;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el registro solicitud justificante bean. */
	private RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean;
	
	/** el documento XML. */
	private Document documentoXML;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GestionarJustificanteForm.class);
	
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * Obtiene el doc bean.
	 *
	 * @return el doc bean
	 */
	public DocumentoBean getDocBean() {
		return docBean;
	}
	
	/**
	 * Establece el doc bean.
	 *
	 * @param docBean el nuevo doc bean
	 */
	public void setDocBean(DocumentoBean docBean) {
		this.docBean = docBean;
	}
	
	/**
	 * Obtiene el tipo justificante.
	 *
	 * @return el tipo justificante
	 */
	public String getTipoJustificante() {
		return tipoJustificante;
	}
	
	/**
	 * Establece el tipo justificante.
	 *
	 * @param tipoJustificante el nuevo tipo justificante
	 */
	public void setTipoJustificante(String tipoJustificante) {
		this.tipoJustificante = tipoJustificante;
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
	 * Obtiene el id justificante.
	 *
	 * @return el id justificante
	 */
	public Long getIdJustificante() {
		return idJustificante;
	}
	
	/**
	 * Establece el id justificante.
	 *
	 * @param idJustificante el nuevo id justificante
	 */
	public void setIdJustificante(Long idJustificante) {
		this.idJustificante = idJustificante;
	}
	
	/**
	 * Obtiene el arr bytes.
	 *
	 * @return el arr bytes
	 */
	public byte[] getArrBytes() {
		return arrBytes;
	}
	
	/**
	 * Establece el arr bytes.
	 *
	 * @param arrBytes el nuevo arr bytes
	 */
	public void setArrBytes(byte[] arrBytes) {
		this.arrBytes = arrBytes;
	}
	
	/**
	 * Obtiene el registro solicitud justificante bean.
	 *
	 * @return el registro solicitud justificante bean
	 */
	public RegistroSolicitudJustificanteBean getRegistroSolicitudJustificanteBean() {
		return registroSolicitudJustificanteBean;
	}
	
	/**
	 * Establece el registro solicitud justificante bean.
	 *
	 * @param registroSolicitudJustificanteBean el nuevo registro solicitud justificante bean
	 */
	public void setRegistroSolicitudJustificanteBean(
			RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean) {
		this.registroSolicitudJustificanteBean = registroSolicitudJustificanteBean;
	}
	
	/**
	 * Obtiene el documento XML.
	 *
	 * @return el documento XML
	 */
	public Document getDocumentoXML() {
		return documentoXML;
	}
	
	/**
	 * Establece el documento XML.
	 *
	 * @param documentoXML el nuevo documento XML
	 */
	public void setDocumentoXML(Document documentoXML) {
		this.documentoXML = documentoXML;
	}
	
	
}
