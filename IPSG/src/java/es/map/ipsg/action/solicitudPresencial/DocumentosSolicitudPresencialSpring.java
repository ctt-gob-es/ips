package es.map.ipsg.action.solicitudPresencial;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.TipoDocumentoBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.TipoDocumentoManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.UtilesIPSG;

/**
 * El Class DocumentosSolicitudPresencialSpring.
 */
public class DocumentosSolicitudPresencialSpring extends AbstractSpring {

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el tipo documento manager. */
	private TipoDocumentoManager tipoDocumentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosSolicitudPresencialSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	
	/**
	 * Crea una nueva documentos solicitud presencial spring.
	 */
	public DocumentosSolicitudPresencialSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setTipoDocumentoManager((TipoDocumentoManager) getBean("tipoDocumentoManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error(" Error DocumentosSolicitudPresencialSpring - cargar datos y tipo de documento",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("DocumentosSolicitudPresencialSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		
		logger.info("Entra en el Action");
	try{	
		//Vendra informado si viene de borrar documento (MEMORIA SESION)
		String id=(String)this.getRequest().getParameter("id");
		if (id == null || "".equals(id)) {
			id = theForm.getIdSolicitud();
		}
		
		logger.info("Documentos Solicitud Presencial: " + id);
		
		//Cargamos el combo del tipo de documento
		TipoDocumentoQuery  tipoDocumentoQuery = new TipoDocumentoQuery();		
		List<TipoDocumentoBean> lTipoDocumentoBean;
		tipoDocumentoQuery.addOrder(TipoDocumentoQuery.DESCRIPCION,OrderType.ASC);
		lTipoDocumentoBean = tipoDocumentoManager.buscarTipoDocumentoCombo(tipoDocumentoQuery);
		TipoDocumentoBean tipoDocumento;
		int cont = lTipoDocumentoBean.size();
		String c;
		while (cont > 0) {
			tipoDocumento = lTipoDocumentoBean.get(cont-1);
			c = tipoDocumento.getCodigo();
			// Si no es un documentos admitido los descartamos del combo
			if (!"AS".equals(c) && !"JP".equals(c) && !"JR".equals(c) && !"JE".equals(c) && !"JX".equals(c)) {
				lTipoDocumentoBean.remove(cont-1);
			}
			cont--;			
		}	
		String idSolicitudPresencial=null;
		if(id != null) {			
			idSolicitudPresencial = id;
			//Se borra para el bucle al volver
			this.getRequest().removeAttribute("id");
		}else{
			//Este es el que viene informado por defecto (URL)
			idSolicitudPresencial = this.getRequest().getParameter("id");
			logger.info("requestAttribute: " + this.getRequest().getParameter("id"));
		}
		
		logger.info("Documentos Solicitud Presencial REQUEST ID: " + (String)this.getRequest().getAttribute("id"));

		if(idSolicitudPresencial == null || idSolicitudPresencial.equalsIgnoreCase("")){
			//Para cuando se Sube un documento se almacena el IdConvocatoria en el FORM
			idSolicitudPresencial = theForm.getIdSolicitud();
		}
		long idSol = Long.parseLong(idSolicitudPresencial);
		logger.info("Id Parametro: " + idSolicitudPresencial);
		
		// Recuperamos los documentos asociados a la solicitud presencial
		DocumentoQuery documentoQuery = new DocumentoQuery();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(idSol);

		documentoQuery.setSolicitudComun(solicitudQuery);
		List<DocumentoBean> documentosList;
		documentosList = documentoManager.buscarDocumentoCombo(documentoQuery);
		
		// Si ha pasado el plazo de 60 días tras la finalización de la convocatoria
		// se impide el borrado de los documentos.
		evaluarDocumentosSoloLectura(documentosList);
		
		// Devolvemos el valor del combo
		setRequestAttribute("tipoDocumento", lTipoDocumentoBean);		
		
		// Ponemos el valor de la variable id
		setRequestAttribute("id", id);
		setRequestAttribute("documentos", documentosList);
		theForm.setEntorno("Solicitudes");
		theForm.setIdSolicitud(idSolicitudPresencial);
		
		this.getResponse().setHeader("Cache-Control", "no-cache");
		this.getResponse().setHeader("Pragma", "no-cache");
		this.getResponse().setDateHeader("Expires", 0);
		
		getLogger().debug("DocumentosSolicitudPresencialSpring -end");
		
	}catch(Exception eGenerico){
		logger.error(" Error DocumentosSolicitudPresencialSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";

	}

	/**
	 * Evaluar documentos solo lectura.
	 *
	 * @param documentosList el documentos list
	 */
	private void evaluarDocumentosSoloLectura(List<DocumentoBean> documentosList) {
		
		Date fechaActual = UtilesIPSG.obtenerFechaActual();
		
		if(documentosList!=null && !documentosList.isEmpty()){

			// Si han pasado mas del maximo de dias configurado, se deshabilita la opción de 'borrar documentos'
			for (DocumentoBean documentoBean : documentosList) {
				Date fechaDoc = UtilesIPSG.sumarRestarDiasFecha(documentoBean.getFechaCreacion(), Constantes.NUM_DIAS_DOC_READONLY);
				if(fechaActual.getTime() > fechaDoc.getTime()){
					documentoBean.setSoloLectura(true);
				}
			}
		}
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
	 * Obtiene el tipo documento manager.
	 *
	 * @return el tipo documento manager
	 */
	public TipoDocumentoManager getTipoDocumentoManager() {
		return tipoDocumentoManager;
	}

	/**
	 * Establece el tipo documento manager.
	 *
	 * @param tipoDocumentoManager el nuevo tipo documento manager
	 */
	public void setTipoDocumentoManager(TipoDocumentoManager tipoDocumentoManager) {
		this.tipoDocumentoManager = tipoDocumentoManager;
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
