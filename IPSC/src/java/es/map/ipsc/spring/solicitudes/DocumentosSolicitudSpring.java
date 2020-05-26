package es.map.ipsc.spring.solicitudes;



import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.bean.TamanioDocumentoBean;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;

/**
 * El Class DocumentosSolicitudSpring.
 */
public class DocumentosSolicitudSpring extends AbstractSpring {

	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosSolicitudSpring.class);
	
	/** La constante BUNDLE_NAME. */
	private static final String BUNDLE_NAME = "file";
	
	/** La constante RESOURCE_BUNDLE_NAME. */
	private static final ResourceBundle RESOURCE_BUNDLE_NAME = ResourceBundle.getBundle(BUNDLE_NAME);
	
	
	/**
	 * Crea una nueva documentos solicitud spring.
	 */
	public DocumentosSolicitudSpring() {
		try {
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error documentos solicitud",e);
		}
		
		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		CrearDocumentoForm formulario;
		formulario = (CrearDocumentoForm) form;
		logger.info("Entra en el action documentosSolicitud");
		String idSolicitud= this.getRequest().getParameter("registro");
			if(idSolicitud == null || "".equals(idSolicitud)){
				idSolicitud = formulario.getIdSolicitud();
				formulario.setDescripcion("");
				formulario.setNombre("");
			}
			if(idSolicitud == null || "".equals(idSolicitud)){
				idSolicitud = (String) this.getRequest().getAttribute("registro");
				formulario.setDescripcion("");
				formulario.setNombre("");
				formulario.setFile(null);
			}
			
			
			if(idSolicitud != null && !"".equals(idSolicitud)){
				
				long idSol = Long.parseLong(idSolicitud);
				logger.info("Id Parametro: "+idSolicitud);
				DocumentoQuery documentosQuery = new DocumentoQuery();
				SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
				solicitudQuery.setIdSolicitud(idSol);
				documentosQuery.setSolicitudComun(solicitudQuery);
				//Busca los documentos asignados a la convocatoria
				ArrayList<DocumentoBean> documentos = documentoConvocatoriasManager.buscarDocumentosConvocatoria(documentosQuery);
				Long tamanioTotal = 0L;
		        if(documentos != null){
		        	for(int i=0;i<documentos.size();i++){
		        		tamanioTotal = tamanioTotal + documentos.get(i).getTamano();
		        	}
		        }
		        TamanioDocumentoBean tamanioDocumentoBean = new TamanioDocumentoBean();
		        tamanioDocumentoBean.setTamanioTotal(tamanioTotal);
				SolicitudBean solicitudAux = new SolicitudBean();
				solicitudAux.setId(Long.parseLong(idSolicitud));
				logger.info("Llega al action");
				setRequestAttribute("documentos", documentos);
				setRequestAttribute("solicitud", solicitudAux);
				setRequestAttribute("idSolicitud", idSolicitud);
				formulario.setIdSolicitud(idSolicitud);
				
				//Recuperamos las posibles extensiones
		        Enumeration<String> listaPropiedades;
				List <String> listExt= new ArrayList<String>();
				listaPropiedades=RESOURCE_BUNDLE_NAME.getKeys();
				ApplicationException.assertNotNull(listaPropiedades,"isExtensionValida, listaPropiedades");
				
				
				for (Enumeration<String> propiedades = listaPropiedades; propiedades.hasMoreElements();){
					String propiedadExtension= propiedades.nextElement();
					String extensionAux=RESOURCE_BUNDLE_NAME.getString(propiedadExtension);
					if (propiedadExtension.endsWith("."+extensionAux.toLowerCase())){
						listExt.add(extensionAux);
					}
				}
				setRequestAttribute("extensiones", listExt);
				
				this.setRequestAttribute("tamanio", tamanioDocumentoBean);
				return "anadirDocumento";
			}else{
				
				this.getRequest().setAttribute("errorDescripcion", "En este momento no puede gestionar los documentos");
				return "error";
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
