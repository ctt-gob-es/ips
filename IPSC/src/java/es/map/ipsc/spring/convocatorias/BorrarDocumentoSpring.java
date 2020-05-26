package es.map.ipsc.spring.convocatorias;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;

/**
 * El Class BorrarDocumentoSpring.
 */
public class BorrarDocumentoSpring extends AbstractSpring {
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BorrarDocumentoSpring.class);

	/**
	 * Crea una nueva borrar documento spring.
	 */
	public BorrarDocumentoSpring() {
		try {
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error Borrar documento",e);
		}

		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BorrarDocumentoSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		try {
			
			HttpServletRequest req = this.getRequest();
			String idDocumento = req.getParameter("doc");
			String entorno = Constantes.ENTORNO_SOLICITUDES;
			logger.info("IdDocumento: "+idDocumento);
			//Obtienes el id del documento que qieres borrar
			DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setId(Long.parseLong(idDocumento));
			logger.info("requestAttribute: "+req.getParameter("id"));
			
			
			//Obtienes el documento
			DocumentoBean doc = documentoConvocatoriasManager.obtenerDocumento(documentoQuery);
			
			
			
			
			doc.setEntorno(entorno);
			//Eliminas el documento
			documentoConvocatoriasManager.borrarDocumento(doc);
			getLogger().debug("BorrarDocumentoSpring -end");
			String aux = String.valueOf(doc.getIdSolicitud());
			logger.info("Aux: "+aux);
			this.setRequestAttribute("registro",aux);
			theForm.setIdSolicitud(aux);
			return "success";
		} catch (Exception e) {
			logger.warn(e);
			this.getRequest().setAttribute("errorDescripcion", e.getMessage());			
			logger.error("Error borrar documento",e);
			return "nosuccess";
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

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
