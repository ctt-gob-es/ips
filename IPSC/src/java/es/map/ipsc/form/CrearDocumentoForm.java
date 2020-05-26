package es.map.ipsc.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;


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
	
	/** el entorno. */
	private String entorno;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el tamanio total. */
	private String tamanioTotal;
	
	/** el signature. */
	private String signature;
	
	/** el signer cert. */
	private String signerCert;
	
	/** el id doc. */
	private String idDoc;
	
	/** el id documento. */
	private String idDocumento;
	
	/** el aceptar. */
	private String aceptar;
	
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearDocumentoForm.class);
	
		
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
	 * Obtiene el aceptar.
	 *
	 * @return el aceptar
	 */
	public String getAceptar() {
		return aceptar;
	}
	
	/**
	 * Establece el aceptar.
	 *
	 * @param aceptar el nuevo aceptar
	 */
	public void setAceptar(String aceptar) {
		this.aceptar = aceptar;
	}
	
	/**
	 * Obtiene el signature.
	 *
	 * @return el signature
	 */
	public String getSignature() {
		return signature;
	}
	
	/**
	 * Establece el signature.
	 *
	 * @param signature el nuevo signature
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	/**
	 * Obtiene el signer cert.
	 *
	 * @return el signer cert
	 */
	public String getSignerCert() {
		return signerCert;
	}
	
	/**
	 * Establece el signer cert.
	 *
	 * @param signerCert el nuevo signer cert
	 */
	public void setSignerCert(String signerCert) {
		this.signerCert = signerCert;
	}
	
	/**
	 * Obtiene el tamanio total.
	 *
	 * @return el tamanio total
	 */
	public String getTamanioTotal() {
		return tamanioTotal;
	}
	
	/**
	 * Establece el tamanio total.
	 *
	 * @param tamanioTotal el nuevo tamanio total
	 */
	public void setTamanioTotal(String tamanioTotal) {
		this.tamanioTotal = tamanioTotal;
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
		SpringErrors actionErrors = new SpringErrors();
		
		
		
		if(nombre==null||nombre.equalsIgnoreCase("")){
			request.setAttribute("errorNombreDocumento", "errorNombreDocumento");
			actionErrors.add("dsErrorNombreDocumento", new SpringMessage(
					"field.documento.errorNombre"));
		}
		
		if(descripcion==null||descripcion.equalsIgnoreCase("")){
			request.setAttribute("errorDescripcionDocumento", "errorDescripcionDocumento");
			actionErrors.add("dsErrorDescripcionDocumento", new SpringMessage(
					"field.documento.errorDescripcion"));
		}
		
		if(file.getSize()<=0){
			request.setAttribute("errorFicheroDocumento", "errorFicheroDocumento");
			actionErrors.add("dsErrorFicheroDocumento", new SpringMessage(
					"field.documento.errorFile"));
		}
	
		return actionErrors;
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	
}
