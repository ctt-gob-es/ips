package es.map.ipsg.action.solicitud;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.ContactarCiudadanoForm;
import es.map.ipsg.manager.DocumentoManager;


/**
 * El Class AdjuntarDocumentosSpring.
 */
public class AdjuntarDocumentosSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AdjuntarDocumentosSpring.class);
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/**
	 * ActualizarEstadoSolicitudAction.
	 */
	public AdjuntarDocumentosSpring() {
		try{
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error AdjuntarDocumentosSpring:",e);
		}
	}

	/**
	 * Do execute.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		ArrayList<Long> listaIdDocumentos = (ArrayList<Long>) getSessionAttribute("listaIdDocumentos");
	try{
		if(listaIdDocumentos!=null && listaIdDocumentos.size()>0) {
			
			DocumentoQuery documentoQuery= new DocumentoQuery();
			
			Iterator<Long> itListaIdDocumentos = listaIdDocumentos.iterator();
			
			while (itListaIdDocumentos.hasNext()){
				Long id = (Long) itListaIdDocumentos.next();
				documentoQuery.addIdIn(id);
			}
			
			ArrayList<DocumentoBean> listadoDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery);
			setRequestAttribute("documentos", listadoDocumentos);			
		}
		
	}catch(Exception eGenerico){
		logger.error("Error AdjuntarDocumentosSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";		
	}

	/**
	 * Obtiene el documento manager.
	 *
	 * @return the documentoManager
	 */
	public DocumentoManager getDocumentoManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param documentoManager the documentoManager to set
	 */
	public void setDocumentoManager(DocumentoManager documentoManager) {
		this.documentoManager = documentoManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}
