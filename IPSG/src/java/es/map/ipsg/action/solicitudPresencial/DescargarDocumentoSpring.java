package es.map.ipsg.action.solicitudPresencial;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.manager.DocumentoManager;

/**
 * El Class DescargarDocumentoSpring.
 */
public class DescargarDocumentoSpring extends AbstractSpring {
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoSpring.class);
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);

	/**
	 * Crea una nueva descargar documento spring.
	 */
	public DescargarDocumentoSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error DescargarDocumentoSpring - cargar datos documento: ", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
	
		try{
			getLogger().debug("DescargarDocumentoSpring -start");
			boolean exito = false;
			HttpServletRequest req = this.getRequest();
			String idDocumento = req.getParameter("doc");
			
			DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setId(Long.parseLong(idDocumento));
			DocumentoBean doc = documentoManager.obtenerDocumento(documentoQuery);
	
			byte[] salida = obtenerDocumentoSalida(doc);
			exito = salida != null;
			logger.info("depues de la descarga, exito= " + exito);
			
	
			if (exito) {
				enviarSalida(doc,salida);
				getLogger().debug("DescargarDocumentoSpring -end");
				return "success";
			} else {
				this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("error.alfresco.descargaDocumento"));
				return "nosuccess";
			}
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("error.alfresco.descargaDocumento"));
			logger.error("Error DescargarDocumentoSpring - error descargar Documento alfresco: ", e);
			return "nosuccess";
			
		}
	}

	/**
	 * Obtener documento salida.
	 *
	 * @param doc el doc
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public byte[] obtenerDocumentoSalida(DocumentoBean doc) throws Exception {

		byte[] salida=null;
		try {
			salida = documentoManager.obtenerContenidoDocumento(doc);
			return salida;
		} catch (NumberFormatException e) {
			logger.error(" Error DescargarDocumentoSpring - Exception DescargarDocumentoServlet",e);
			throw new ApplicationException("NumberFormatException DescargarDocumentoServlet");

		} catch (ApplicationException e) {
			logger.error(" Error DescargarDocumentoSpring - exception obtenerContenidoDocumento en ACTION",e);
			return salida;

		} catch (Exception e) {
			logger.error(" Error DescargarDocumentoSpring - Exception DescargarDocumentoAction",e);
			throw new Exception("Exception DescargarDocumentoAction");
		} finally {
			logger.debug("doGet ObtenerDocumentoAction - end");
		}

	}

	/**
	 * Enviar salida.
	 *
	 * @param doc el doc
	 * @param salida el salida
	 */
	public void enviarSalida(DocumentoBean doc,byte[] salida) {
		String idAlfresco = doc.getNombreAlfresco();
		javax.servlet.ServletOutputStream stream;
		try {
			stream = this.getResponse().getOutputStream();
			String contentDisposition = "attachment; filename=\"" + idAlfresco + "\"";
			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition);
			IOUtils.write(salida,stream);
		} catch (IOException e) {
			logger.error(" Error DescargarDocumentoSpring - enviarSalida",e);
		}
	}	

	/**
	 * Obtiene el documentos manager.
	 *
	 * @return el documentos manager
	 */
	public DocumentoManager getDocumentosManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param pDocumentosManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentoManager pDocumentosManager) {
		this.documentoManager = pDocumentosManager;
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
