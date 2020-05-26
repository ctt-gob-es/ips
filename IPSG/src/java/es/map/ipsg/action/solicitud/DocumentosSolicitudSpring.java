package es.map.ipsg.action.solicitud;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class DocumentosSolicitudSpring.
 */
public class DocumentosSolicitudSpring extends AbstractSpring {

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosSolicitudSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);	
	
	/**
	 * Crea una nueva documentos solicitud spring.
	 */
	public DocumentosSolicitudSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error DocumentosSolicitudSpring:",e);
		}

	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("DocumentosSolicitudSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		
		logger.info("Entra en el Action");
	try{
		
			//Este es el que viene informado por defecto (URL)
			String idSolicitud = this.getRequest().getParameter("id");
			
			logger.info("requestAttribute: "+this.getRequest().getParameter("id"));
		
		
		logger.info("DocumentosSolicitud REQUEST ID: "+idSolicitud);
		

		if(idSolicitud==null||idSolicitud.equalsIgnoreCase("")){
			//Para cuando se Sube un documento se almacena el idSolicitud en el FORM
			idSolicitud=theForm.getIdSolicitud();
		}
		long idSol = Long.parseLong(idSolicitud);
		
		
		DocumentoQuery documentoQuery= new DocumentoQuery();
		
		documentoQuery.addSolicitudIn(idSol);
		Integer tpDocumentoAnexoSolicitud = Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD;
		Integer tpDocumentoJustificanteReduccionTasa = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA;
		Integer tpDocumentoJustificanteDiscapacidad = Constantes.TIPO_DOCUMENTO_DISCAPACIDAD;
		
		documentoQuery.addTipoDocumentoIn(tpDocumentoAnexoSolicitud);
		documentoQuery.addTipoDocumentoIn(tpDocumentoJustificanteReduccionTasa);
		documentoQuery.addTipoDocumentoIn(tpDocumentoJustificanteDiscapacidad);		
		
		List<DocumentoBean> documentosList;
		documentosList=documentoManager.buscarDocumentoCombo(documentoQuery);
        

		setRequestAttribute("documentos", documentosList);
		theForm.setEntorno("Solicitudes");
		theForm.setIdSolicitud(idSolicitud);
		
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setHeader("Pragma", "no-cache");
		this.getResponse().setDateHeader("Expires", 0);
		getLogger().debug("DocumentosSolicitudSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error DocumentosSolicitudSpring - doExecute :",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";

	}

	/**
	 * Obtiene el documento manager.
	 *
	 * @return el documento manager
	 */
	public DocumentoManager getDocumentoManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param documentoManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentoManager documentoManager) {
		this.documentoManager = documentoManager;
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
