package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.RegistroSolicitudJustificanteBean;


/**
 * El Class CrearDocumentoForm.
 */
public class CrearDocumentoForm extends SpringForm {

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
	
	/** el de. */
	private String de;

	
	/** el id justificante. */
	private Long idJustificante;
	
	/** el doc bean. */
	private DocumentoBean docBean ;
	
	/** el arr bytes. */
	private byte[] arrBytes;
	
	/** el tipo justificante. */
	private String tipoJustificante;
	
	/** el registro solicitud justificante bean. */
	private RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean;
	
	/** el documento XML. */
	private Document documentoXML;
	
	/** el id. */
	String id;
	
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearDocumentoForm.class);
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id= id;
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
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		logger.info("Validando");
		SpringErrors SpringErrors = new SpringErrors();
		
		
		
		if(nombre==null||nombre.equalsIgnoreCase("")){
			request.setAttribute("errorNombreDocumento", "errorNombreDocumento");
			SpringErrors.add("dsErrorNombreDocumento", new SpringMessage(
					"field.documento.errorNombre"));
		}
		
		if(descripcion==null||descripcion.equalsIgnoreCase("")){
			request.setAttribute("errorDescripcionDocumento", "errorDescripcionDocumento");
			SpringErrors.add("dsErrorDescripcionDocumento", new SpringMessage(
					"field.documento.errorDescripcion"));
		}
		
		if(file.getSize()<=0){
			request.setAttribute("errorFicheroDocumento", "errorFicheroDocumento");
			SpringErrors.add("dsErrorFicheroDocumento", new SpringMessage(
					"field.documento.errorFile"));
		}
	
		return SpringErrors;
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
	 * @return the idDocumento
	 */
	public String getIdDocumento() {
		return idDocumento;
	}
	
	/**
	 * Establece el id documento.
	 *
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
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
	 * Obtiene el id tipo documento.
	 *
	 * @return el id tipo documento
	 */
	public String getIdTipoDocumento() {
		return idTipoDocumento;
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
	 * Obtiene el de.
	 *
	 * @return el de
	 */
	public String getDe() {
		return de;
	}
	
	/**
	 * Establece el de.
	 *
	 * @param de el nuevo de
	 */
	public void setDe(String de) {
		this.de = de;
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
	public void setRegistroSolicitudJustificanteBean(RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean) {
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
