package es.map.ipsc.spring.solicitudes;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;

/**
 * El Class DocumentosJustificanteSolicitudSpring.
 */
public class DocumentosJustificanteSolicitudSpring extends AbstractSpring {

	/** el documentos convocatoria manager. */
	private DocumentosConvocatoriaManager documentosConvocatoriaManager;
	
	/** el solicitud manager. */
	private SolicitudManager solicitudManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosJustificanteSolicitudSpring.class);
	
	/** La constante BUNDLE_MESSAGES. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/**
	 * Crea una nueva documentos justificante solicitud spring.
	 */
	public DocumentosJustificanteSolicitudSpring() {
		try {
			setDocumentosConvocatoriaManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error documentos justificante solicitud",e);
		}
	}

	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("DocumentosJustificanteSolicitudSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		CiudadanoBean usuActual=null;
		logger.info("Entra en el Action");
		
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
		
		String idSolicitud = (String) this.getRequest().getAttribute("id");
			logger.info("requestAttribute: "+this.getRequest().getAttribute("id"));
			//Este es el que viene informado por defecto (URL)
		if(idSolicitud == null){	
			idSolicitud = this.getRequest().getParameter("id");
			logger.info("requestParameter: "+this.getRequest().getParameter("id"));
		}
		String llamada = this.getRequest().getParameter("llamada");
		logger.info("requestAttribute: "+this.getRequest().getParameter("id"));
		
		
		logger.info("DocumentosSolicitud REQUEST ID: "+idSolicitud);
		

		if(idSolicitud==null||idSolicitud.equalsIgnoreCase("")){
			//Para cuando se Sube un documento se almacena el idSolicitud en el FORM
			idSolicitud=theForm.getIdSolicitud();
		}
		
		long idSol = 0L;
		try{
			idSol = Long.parseLong(idSolicitud);
		}catch(Exception e){
			
			logger.error("No se ha podido parsear el id de solicitud"+ idSol,e);
			this.getRequest().setAttribute("error", RESOURCE_MESSAGES.getString("error.documentoJustificante.errorIdSolicitud"));
			return STRING_SUCCESS;		
		}
		DocumentoQuery documentoQuery= new DocumentoQuery();

	
		
		
		
		if(llamada != null){
			if(llamada.equals(Constantes.JUSTIFICANTE_REGISTRO_LLAMADA)){
			documentoQuery.addSolicitudIn(idSol);
			

				
				Integer tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF;
				documentoQuery.addTipoDocumentoIn(tpDocumento);
				tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML;
				documentoQuery.addTipoDocumentoIn(tpDocumento);
				tpDocumento = Constantes.JUSTIFICANTE_DE_INSCRIPCION_SOLICITUD_PDF;
				documentoQuery.addTipoDocumentoIn(tpDocumento);

				Integer tpDocumentoCatalan = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_CATALAN;
				documentoQuery.addTipoDocumentoIn(tpDocumentoCatalan);
				tpDocumentoCatalan = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_CATALAN;
				documentoQuery.addTipoDocumentoIn(tpDocumentoCatalan);

				Integer tpDocumentoEuskera = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_EUSKERA;
				documentoQuery.addTipoDocumentoIn(tpDocumentoEuskera);
				tpDocumentoEuskera = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_EUSKERA;
				documentoQuery.addTipoDocumentoIn(tpDocumentoEuskera);

				Integer tpDocumentoGallego = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_GALLEGO;
				documentoQuery.addTipoDocumentoIn(tpDocumentoGallego);
				tpDocumentoGallego = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_GALLEGO;
				documentoQuery.addTipoDocumentoIn(tpDocumentoGallego);

				Integer tpDocumentoValenciano = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_VALENCIANO;
				documentoQuery.addTipoDocumentoIn(tpDocumentoValenciano);
				tpDocumentoValenciano = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_VALENCIANO;
				documentoQuery.addTipoDocumentoIn(tpDocumentoValenciano);
				

			
			}else{
				if(llamada.equals(Constantes.JUSTIFICANTE_PAGO_LLAMADA)){
					
					documentoQuery.addSolicitudIn(idSol);
					
					
					Integer tpDocumento1 = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO;
					documentoQuery.addTipoDocumentoIn(tpDocumento1);

					Integer tpDocumentoCatalan = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_CATALAN;
					documentoQuery.addTipoDocumentoIn(tpDocumentoCatalan);

					Integer tpDocumentoEuskera = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_EUSKERA;
					documentoQuery.addTipoDocumentoIn(tpDocumentoEuskera);

					Integer tpDocumentoGallego = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_GALLEGO;
					documentoQuery.addTipoDocumentoIn(tpDocumentoGallego);

					Integer tpDocumentoValenciano = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_VALENCIANO;
					documentoQuery.addTipoDocumentoIn(tpDocumentoValenciano);
			
				}
			}
		}
		
		List<DocumentoBean> documentosList;
		documentosList=documentosConvocatoriaManager.buscarDocumentosConvocatoria(documentoQuery);
		for(int i=0;i<documentosList.size();i++){		
			boolean correcto = comprobarDocumento(documentosList.get(i),usuActual);
		if(!correcto){
				this.setRequestAttribute(STRING_ERRORUSUARIO, "El usuario no puede descargar ese documento");
				return STRING_SUCCESS;
			}
		}

		setRequestAttribute("documentos", documentosList);
		theForm.setEntorno("Solicitudes");
		theForm.setIdSolicitud(idSolicitud);
		
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setHeader("Pragma", "no-cache");
		this.getResponse().setDateHeader("Expires", 0);
		getLogger().debug("DocumentosJustificanteSolicitudSpring -end");
		return STRING_SUCCESS;

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
		if(solicitud != null && solicitud.getNif()!=null && usuActual!= null && solicitud.getNif().equals(usuActual.getNif())){
			
			return true;
			
		}
		return false;
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
