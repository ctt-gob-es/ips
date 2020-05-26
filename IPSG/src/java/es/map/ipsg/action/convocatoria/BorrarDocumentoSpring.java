package es.map.ipsg.action.convocatoria;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.manager.DocumentoManager;

/**
 * El Class BorrarDocumentoSpring.
 */
public class BorrarDocumentoSpring extends AbstractSpring {
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
		
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BorrarDocumentoSpring.class);
	

	/**
	 * Crea una nueva borrar documento spring.
	 */
	public BorrarDocumentoSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BorrarDocumentoSpring(): ",e);
		}
		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
				
		getLogger().debug("BorrarDocumentoSpring -start");
		try {
			
			HttpServletRequest req = this.getRequest();
			String idDocumento = req.getParameter("doc");
			String entorno = req.getParameter("ent");
			logger.info("IdDocumento: "+idDocumento);
			DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setId(Long.parseLong(idDocumento));
			logger.info("requestAttribute: "+req.getParameter("id"));
			
			DocumentoBean doc = documentoManager.obtenerDocumento(documentoQuery);
			logger.info("idConvocatoria-Borrar: "+doc.getIdConvocatoria());
			
			req.setAttribute("id", doc.getIdConvocatoria()+"");
			doc.setEntorno(entorno);
			documentoManager.borrarDocumento(doc);
			this.getRequest().setAttribute("eliminado", "Eliminado");
			getLogger().debug("BorrarDocumentoSpring -end");
			return "success";
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BorrarDocumentoSpring() - doExecute: ",e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			return "nosuccess";
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
