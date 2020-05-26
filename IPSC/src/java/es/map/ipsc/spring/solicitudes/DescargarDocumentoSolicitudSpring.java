package es.map.ipsc.spring.solicitudes;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.utils.SistemaFicheros;

/**
 * El Class DescargarDocumentoSolicitudSpring.
 *
 * @author everis
 */
public class DescargarDocumentoSolicitudSpring extends AbstractSpring {
	
	/** el documentos convocatoria manager. */
	private DocumentosConvocatoriaManager documentosConvocatoriaManager;
	
	/** el solicitud manager. */
	private SolicitudManager solicitudManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoSolicitudSpring.class);
	
	/** La constante BUNDLE_MESSAGES. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
    
    /** La constante STRING_ERRORUSUARIO. */
    private static final String STRING_ERRORUSUARIO = "errorUsuario";
    
    /** La constante STRING_SUCCESS. */
    private static final String STRING_SUCCESS = "success";	

	/**
	 * Crea una nueva descargar documento solicitud spring.
	 */
	public DescargarDocumentoSolicitudSpring() {
		try {
			setDocumentosConvocatoriaManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error descargar docuemtno solicitud",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		getLogger().debug("DescargarDocumentoSolicitudSpring -start");
		boolean exito = false;
		HttpServletRequest req = this.getRequest();
		String idSolicitud= req.getParameter("idSol");
		String idDocumento = req.getParameter("id");
		String entorno = req.getParameter("ent");
        CiudadanoBean usuActual=null;

		// Comprobar que el idDocumento sea un numero
		try{
			Long.parseLong(idDocumento);
		}catch(Exception e){
			logger.error("El id de documento no tiene el formato numerico",e);
			
			this.setRequestAttribute("error", RESOURCE_MESSAGES.getString("error.descargarDocumento.validarIdDocumento"));
			return "errorJustificante";
		}
		
		// Comprobar si venimos de Cl@ve, la session esta en usuarioClave		
        usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
        if(usuActual == null){
              this.getRequest().setAttribute(STRING_ERRORUSUARIO,RESOURCE_MESSAGES.getString("solicitud.error.usuario"));
              return STRING_SUCCESS;
        }else{
              if(usuActual.getNif() == null){
                    this.getRequest().setAttribute(STRING_ERRORUSUARIO,RESOURCE_MESSAGES.getString("solicitud.error.usuario"));
                   return STRING_SUCCESS;
              }
        }
              	
		DocumentoQuery documentoQuery = new DocumentoQuery();
		
		if(idSolicitud==null||idSolicitud.equals("")) {
			documentoQuery.setId(Long.parseLong(idDocumento));
			
		}else{
			documentoQuery.addSolicitudIn(Long.parseLong(idSolicitud));
			documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO);
			List<DocumentoBean> documentosList;
			documentosList=documentosConvocatoriaManager.buscarDocumentosConvocatoria(documentoQuery);
			
			if (documentosList.size()==0){
				return "sinJustificante";
			}
		
		}
		DocumentoBean doc = documentosConvocatoriaManager.obtenerDocumento(documentoQuery);
			byte[] salida = null;
			boolean correcto = false;
			
			if (!usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
				correcto = comprobarDocumento(doc,usuActual);
			} else {
				correcto = true;
			}
				
			if(correcto != false){
				salida = obtenerDocumentoSolicitudSalida(doc,entorno);
				exito= salida!=null;
				logger.info("depues de la descarga, exito= " + exito);
			}else{
				exito = false;
				this.setRequestAttribute(STRING_ERRORUSUARIO,RESOURCE_MESSAGES.getString("error.descargarDocumento.errorUsuario"));
				return "errorJustificante";
			}
		if (exito) {
			enviarSalida(doc,salida);
			getLogger().debug("DescargarDocumentoSolicitudSpring -end");
			return STRING_SUCCESS;
		} else {
			this.setRequestAttribute("error",RESOURCE_MESSAGES.getString("error.descargarDocumento.errorDocumento"));
			return "errorJustificante";
		}
	}

	/**
	 * Comprobar documento.
	 *
	 * @param doc el doc
	 * @param usuActual el usu actual
	 * @return verdadero, si tiene exito
	 */
	private boolean comprobarDocumento(DocumentoBean doc,CiudadanoBean usuActual) {

		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(doc.getIdSolicitud());
		SolicitudBean solicitud = solicitudManager.buscarSolicitudById(solicitudQuery);
		
		
		if(solicitud != null && solicitud.getNif().equals(usuActual.getNif())){
			return true;
		}
		
		return false;
	}

	/**
	 * Obtener documento solicitud salida.
	 *
	 * @param doc el doc
	 * @param entorno el entorno
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public byte[] obtenerDocumentoSolicitudSalida(DocumentoBean doc,String entorno) throws Exception {
		byte[] salida = null;
		
		try {
			SistemaFicheros sistema = new SistemaFicheros();
			salida = sistema.obtenerContenido(doc);
			return salida;
			
		} catch (NumberFormatException e) {
			logger.error("Error descargar socumento solicitud Servlet",e);
			throw new ApplicationException("NumberFormatException DescargarDocumentoSolicitudServlet");
		} catch (ApplicationException e) {
			logger.error("Error - exception obtenerContenidoDocumentoSolicitud en ACTION",e);
			return salida;
		} catch (Exception e) {
			logger.error("Error descargar docuemtno solicitud",e);
			throw new Exception("Exception DescargarDocumentoSolicitudSpring");
		} finally {
			logger.debug("doGet ObtenerDocumentoSolicitudAction - end");
		}

	}

	/**
	 * Enviar salida.
	 *
	 * @param doc el doc
	 * @param salida el salida
	 */
	private void enviarSalida(DocumentoBean doc,byte[] salida) {
		String idDocumento = doc.getNombreAlfresco();
		javax.servlet.ServletOutputStream stream;
		try {
			stream = this.getResponse().getOutputStream();
			StringBuffer contentDisposition = new StringBuffer();
			contentDisposition.append("attachment; filename=\"").append(idDocumento).append("\"");

			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition.toString());
			stream.write(salida);
			stream.flush();
			stream.close();
			
		} catch (IOException e) {
			logger.error("Error enviarSalida",e);
		}

	}

	/**
	 * Obtiene el documentos convocatoria manager.
	 *
	 * @return el documentos convocatoria manager
	 */
	public DocumentosConvocatoriaManager getDocumentosConvocatoriaManager() {
		return documentosConvocatoriaManager;
	}

	/**
	 * Establece el documentos convocatoria manager.
	 *
	 * @param documentosConvocatoriaManager el nuevo documentos convocatoria manager
	 */
	public void setDocumentosConvocatoriaManager(
			DocumentosConvocatoriaManager documentosConvocatoriaManager) {
		this.documentosConvocatoriaManager = documentosConvocatoriaManager;
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
	 * Obtiene el solicitud manager.
	 *
	 * @return el solicitud manager
	 */
	public SolicitudManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager el nuevo solicitud manager
	 */
	public void setSolicitudManager(SolicitudManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}
	
}
