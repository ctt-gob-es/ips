package es.map.ipsg.action.solicitud;

import java.util.ArrayList;
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
 * El Class VerDocumentoAdjuntoSpring.
 */
public class VerDocumentoAdjuntoSpring extends AbstractSpring {

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerDocumentoAdjuntoSpring.class);
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/**
	 * Crea una nueva ver documento adjunto spring.
	 */
	public VerDocumentoAdjuntoSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error(" Error VerDocumentoAdjuntoSpring:" ,e);
		}

	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("VerDocumentosAdjuntoSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		
		logger.info("Entra en el Action");
	try{	
		
		//Este es el que viene informado por defecto (URL)
		String idCorreo = this.getRequest().getParameter("correo");

		DocumentoQuery documentoQuery= new DocumentoQuery();
		documentoQuery.addCorreoElectronicoIn(Long.valueOf(idCorreo));
		
		ArrayList<DocumentoBean> listadoDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery);
		
		setRequestAttribute("documentos",listadoDocumentos);
		theForm.setEntorno(Constantes.ENTORNO_CORREOS);
		
	}catch(Exception eGenerico){
		logger.error(" Error VerDocumentoAdjuntoSpring - doExecute:" ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
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
