package es.map.ipsg.action.convocatoria;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class DocumentosConvocatoriaSpring.
 */
public class DocumentosConvocatoriaSpring extends AbstractSpring {

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosConvocatoriaSpring.class);

	/**
	 * Crea una nueva documentos convocatoria spring.
	 */
	public DocumentosConvocatoriaSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error DocumentosConvocatoriaSpring() ",e);
		}

	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("DocumentosConvocatoriaSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		String idConvocatoria;
		
		logger.info("Entra en el Action");
	try{
		//Vendra informado si viene de borrar documento (MEMORIA SESION)
		String id=(String)this.getRequest().getAttribute("id");
		logger.info("DocumentosConvocatoria: "+id);
		if(id!=null){
			
			idConvocatoria=id;
			this.getRequest().removeAttribute("id");
			//Se borra para el bucle al volver
		}else{
			//Este es el que viene informado por defecto (URL)
			idConvocatoria = this.getRequest().getParameter("id");
			logger.info("requestAttribute: "+this.getRequest().getParameter("id"));
		}
		
		logger.info("DocumentosConvocatoria REQUEST ID: "+(String)this.getRequest().getAttribute("id"));

		if(idConvocatoria==null||idConvocatoria.equalsIgnoreCase("")){
			//Para cuando se Sube un documento se almacena el IdConvocatoria en el FORM
			idConvocatoria=theForm.getIdConvocatoria();
		}
		long idCon = Long.parseLong(idConvocatoria);
		logger.info("Id Parametro: "+idConvocatoria);
		
		DocumentoQuery documentoQuery= new DocumentoQuery();
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(idCon);
		documentoQuery.setConvocatoria(convocatoriaQuery);
		List<DocumentoBean> documentosList;
		documentosList=documentoManager.buscarDocumentoCombo(documentoQuery);

		setRequestAttribute("documentos", documentosList);
		theForm.setEntorno("Convocatorias");
		theForm.setIdConvocatoria(idConvocatoria);
		
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setHeader("Pragma", "no-cache");
		this.getResponse().setDateHeader("Expires", 0);
		
		// Si la convocatoria esta cerrada, no se podran eliminar documentos.
		ConvocatoriasBean convocatoriasBean = convocatoriaManager.buscarConvocatoriaById(convocatoriaQuery);

		if(convocatoriasBean.getEstadoConvocatoria().equals(Constantes.CONVOCATORIA_ESTADO_FINALIZADA)){
			setRequestAttribute("finalizada", true);
		}
		
		getLogger().debug("DocumentosConvocatoriaSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error DocumentosConvocatoria- doExecute: "+ eGenerico);
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

	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return el convocatoria manager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager el nuevo convocatoria manager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

}
