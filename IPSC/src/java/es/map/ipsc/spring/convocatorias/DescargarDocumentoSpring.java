package es.map.ipsc.spring.convocatorias;

import java.io.File;
import java.io.IOException;
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
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.utils.SistemaFicheros;

/**
 * El Class DescargarDocumentoSpring.
 */
public class DescargarDocumentoSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoSpring.class);
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";

	/**
	 * Crea una nueva descargar documento spring.
	 */
	public DescargarDocumentoSpring() {
		try {
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
						properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error Descargar documento ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		try{
			boolean exito = false;
			byte[] salida = null;
			HttpServletRequest req = this.getRequest();
			String idDocumento = req.getParameter("id");
			String entorno = req.getParameter("ent");
			boolean entornoConvocatorias = entorno.equals(Constantes.ENTORNO_CONVOCATORIAS);
			boolean entornoSolicitudes = entorno.equals(Constantes.ENTORNO_SOLICITUDES);
			
			
			if(entorno.equals(Constantes.ENTORNO_AYUDA)){
				DocumentoBean doc = new DocumentoBean();
				doc.setNombreAlfresco(properties.getProperty("ipsc.config.manual.ayuda.nombre"));
				File manual = new File(properties.getProperty("ipsc.config.manuales.ruta")+doc.getNombreAlfresco());
				enviarSalida(doc, FileUtils.readFileToByteArray(manual));
				return STRING_SUCCESS;
			}else if(entorno.equals(Constantes.ENTORNO_REQUISITOS)){
				DocumentoBean doc = new DocumentoBean();
				doc.setNombreAlfresco(properties.getProperty("ipsc.config.manual.requisitos.nombre"));
				File manual = new File(properties.getProperty("ipsc.config.manuales.ruta")+doc.getNombreAlfresco());
				enviarSalida(doc, FileUtils.readFileToByteArray(manual));
				return STRING_SUCCESS;
			}else if(entorno==null || !(entornoConvocatorias || entornoSolicitudes)){
				logger.error("Entorno incorrecto. Se debe recibir 'Convocatorias' o 'Solicitudes'");
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("error.descargarDocumento.validarIdDocumento"));
				return STRING_NOSUCCESS;
			}
						
			try{

				DocumentoBean doc;
				DocumentoQuery documentoQuery = new DocumentoQuery();
				documentoQuery.setId(Long.parseLong(idDocumento));
				
				if(entornoConvocatorias){
					documentoQuery.setConvocatoriaIsNotNull(true);//Se buscan solo documentos de convocatoria		
				}else{
					documentoQuery.setSolicitudIsNotNull(true);
				}
				
				//Obtienes el documento a descargar
				doc = documentoConvocatoriasManager.obtenerEstado(documentoQuery);

				if(doc == null){
					logger.error("No se ha encontrado ningun documento con ese id");
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("error.alfresco.descargaDocumentoErrorId"));
					return STRING_NOSUCCESS;
				}else{
					
					String idSolicitud = null;
					if(doc.getIdSolicitud() != null){
						idSolicitud = doc.getIdSolicitud().toString();
					}
					
					salida = obtenerDocumentoSalida(doc,idSolicitud);
					exito = salida != null;
					logger.info("Despues de la descarga, exito= " + exito);
					
					if (exito) {			
						enviarSalida(doc,salida);
						return STRING_SUCCESS;
					} else {
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("error.alfresco.descargaDocumento"));
						return STRING_NOSUCCESS;
					}
				}
				
			}catch(Exception e){
				logger.error("Error Imposible parsear ese numero de documento",e);
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("error.descargarDocumento.validarIdDocumento"));
				return STRING_NOSUCCESS;
			}
			
		}catch(Exception e){
			logger.error("Error : ",e);
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, e.getMessage());
			return STRING_NOSUCCESS;
		}

	}

	/**
	 * Obtener documento salida.
	 *
	 * @param doc el doc
	 * @param idSolicitud el id solicitud
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public byte[] obtenerDocumentoSalida(DocumentoBean doc,String idSolicitud) throws Exception {
		byte[] salida = null;
		try {
			SistemaFicheros sistema = new SistemaFicheros();
			salida = sistema.obtenerContenido(doc);
			return salida;
		} catch (NumberFormatException e) {
			logger.error("Error obtener documento salida",e);
			throw new ApplicationException("NumberFormatException DescargarDocumentoServlet");
		} catch (ApplicationException e) {
			logger.error("exception obtenerContenido en ACTION",e);
			return salida;

		} catch (Exception e) {
			logger.error("Error Descargar documento ",e);
			throw new Exception("Exception DescargarDocumentoSpring");
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
			StringBuffer contentDisposition = new StringBuffer();
			contentDisposition.append("attachment; filename=\"").append(idAlfresco).append("\"");

			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition.toString());
			IOUtils.write(salida,stream);
		} catch (IOException e) {
			logger.error("Error Documento enviar salida ",e);
		}

	}

	/**
	 * Obtiene el documento convocatorias manager.
	 *
	 * @return el documento convocatorias manager
	 */
	public DocumentosConvocatoriaManager getDocumentoConvocatoriasManager() {
		return documentoConvocatoriasManager;
	}

	/**
	 * Establece el documento convocatorias manager.
	 *
	 * @param documentoConvocatoriasManager el nuevo documento convocatorias manager
	 */
	public void setDocumentoConvocatoriasManager(
			DocumentosConvocatoriaManager documentoConvocatoriasManager) {
		this.documentoConvocatoriasManager = documentoConvocatoriasManager;
	}
}
