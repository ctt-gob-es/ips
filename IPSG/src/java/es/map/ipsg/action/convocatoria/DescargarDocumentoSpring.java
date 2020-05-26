package es.map.ipsg.action.convocatoria;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class DescargarDocumentoSpring.
 */
public class DescargarDocumentoSpring extends AbstractSpring {
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoSpring.class);
	
	/** el properties. */
	private static Properties properties;

	/**
	 * Crea una nueva descargar documento spring.
	 */
	public DescargarDocumentoSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error DescargarDocumentoSpring(): ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		getLogger().debug("DescargarDocumentoSpring -start");
		try{
			
			HttpServletRequest req = this.getRequest();
			String idDocumento = req.getParameter("id");
			String entorno = req.getParameter("ent");

			
			if(entorno.equals(Constantes.ENTORNO_AYUDA)){
				DocumentoBean doc = new DocumentoBean();
				doc.setNombreAlfresco(properties.getProperty("ipsg.config.manual.ayuda.nombre"));
				File manual = new File(properties.getProperty("ipsg.config.manuales.ruta")+doc.getNombreAlfresco());
				enviarSalida(doc, FileUtils.readFileToByteArray(manual), manual);
				return "successAyuda";			
			}else if(entorno.equals(Constantes.ENTORNO_AYUDA_GESTOR)){
				DocumentoBean doc = new DocumentoBean();
				doc.setNombreAlfresco(properties.getProperty("ipsg.config.manual.ayuda.gestor.nombre"));
				File manual = new File(properties.getProperty("ipsg.config.manuales.ruta")+doc.getNombreAlfresco());
				enviarSalida(doc, FileUtils.readFileToByteArray(manual), manual);
				return "successAyuda";
			}else if(entorno.equals(Constantes.ENTORNO_AYUDA_REGISTRO)){
				DocumentoBean doc = new DocumentoBean();
				doc.setNombreAlfresco(properties.getProperty("ipsg.config.manual.ayuda.registro.nombre"));
				File manual = new File(properties.getProperty("ipsg.config.manuales.ruta")+doc.getNombreAlfresco());
				enviarSalida(doc, FileUtils.readFileToByteArray(manual), manual);
				return "successAyuda";
			}
	
			DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setId(Long.parseLong(idDocumento));
			DocumentoBean doc = documentoManager.obtenerDocumento(documentoQuery);
	
			
			byte[] salida = obtenerDocumentoSalida(doc,entorno);
			logger.info("depues de la descarga, exito= " + salida!=null);
	
			if (salida!=null) {
				enviarSalida(doc,salida,null);
				getLogger().debug("DescargarDocumentoSpring -end");
				return "success";
			} else {
				this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("error.alfresco.descargaDocumento"));
				return "nosuccess";
			}
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("error.alfresco.descargaDocumento"));
			logger.error("Error DescargarDocumentoSpring() - doExecute: ",e);
			return "nosuccess";
			
		}

	}

	/**
	 * Obtener documento salida.
	 *
	 * @param doc el doc
	 * @param entorno el entorno
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public byte[] obtenerDocumentoSalida(DocumentoBean doc, String entorno) throws Exception {

		byte[] salida=null;
		try {
			
			salida = documentoManager.obtenerContenidoDocumento(doc);
			return salida;
		} catch (NumberFormatException e) {
			// throw new
			logger.error("Error- El Identificador del Documento seleccionado no tiene un numero valido.: ",e);
			throw new ApplicationException("El Identificador del Documento seleccionado no tiene un numero valido.");

		} catch (Exception e) {
			logger.error("Error obtenerDocumentoSalida,No se ha podido recuperar el Documento seleccionado: ",e);
			throw new Exception("No se ha podido recuperar el Documento seleccionado");
			
		} finally {
			logger.debug("doGet ObtenerDocumentoAction - end");
		}

	}

	/**
	 * Enviar salida.
	 *
	 * @param doc el doc
	 * @param salida el salida
	 * @param manual 
	 */
	public void enviarSalida(DocumentoBean doc,byte[] salida, File manual) {
		String idAlfresco = doc.getNombreAlfresco();
		javax.servlet.ServletOutputStream stream = null;
		try {
			stream = this.getResponse().getOutputStream();
			String contentDisposition = "attachment; filename=\"" + idAlfresco + "\"";
			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition);
			if(manual != null) {
				FileInputStream fis = new FileInputStream(manual);
				IOUtils.copy(fis, stream);
			}
			stream.write(salida);
			stream.flush();
		} catch (IOException e) {
			logger.error("Error enviarSalida: ",e);
		} 
		//Se tiene que crear un finally para cerrar la conexion
		finally {
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
