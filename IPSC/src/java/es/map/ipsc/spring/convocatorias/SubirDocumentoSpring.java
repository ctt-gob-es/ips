package es.map.ipsc.spring.convocatorias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.TamanioDocumentoBean;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;

/**
 * El Class SubirDocumentoSpring.
 */
public class SubirDocumentoSpring extends AbstractSpring {
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubirDocumentoSpring.class);
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante STRING_VERDOCUMENTOS. */
	private static final String STRING_VERDOCUMENTOS = "verDocumentos";
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/**
	 * Crea una nueva subir documento spring.
	 */
	public SubirDocumentoSpring() {
		try {
			setDocumentoManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error Subir documento ",e);
		}

		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("SubirDocumentoSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		logger.info(theForm.getSignature());
		logger.info(theForm.getSignerCert());
		try{
			if(theForm.getNombre() == null || "".equals(theForm.getNombre()) ||
		        	theForm.getDescripcion() == null || "".equals(theForm.getDescripcion()) ||
		        	theForm.getFile() == null || "".equals(theForm.getFile())){

				this.setRequestAttribute(STRING_REGISTRO,theForm.getIdSolicitud());
		       	return STRING_VERDOCUMENTOS;
		    }
			MultipartFile theFile = theForm.getFile();
	        String contentType = theFile.getContentType();
	        String fileName = theFile.getName();
	        long fileSize = theFile.getSize();
	        byte[] fileData = theFile.getBytes();
	        logger.info("Tipo: "  + contentType);
	        logger.info("Nombre: " + fileName);
	        logger.info("Tamano (KByte): " + fileSize/1024);
	        logger.info("Entorno: "+theForm.getEntorno());
	        logger.info("IdConvocatoria: "+theForm.getIdConvocatoria());
	        
	        
	        DocumentoBean docBean= new DocumentoBean();
	        //Asigno los valores al bean
	        docBean.setContenidoDocumento(fileData);
	        
	        String[] arrNombre=fileName.split("\\.");
	        logger.info("ArrNombre"+arrNombre.toString()+"\nArrNombre length:"+arrNombre.length);
	        String extension="";
	        if(arrNombre.length>1){
	        	extension=arrNombre[arrNombre.length-1];
	        }
	        logger.info("Extension: "+extension);
	        docBean.setExtension(extension);
	        docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
	        docBean.setNombre(theForm.getNombre());
	        docBean.setDescripcion(theForm.getDescripcion());
	        
	        if(theForm.getNombre() == null || "".equals(theForm.getNombre()) ||
	        		theForm.getDescripcion() == null || "".equals(theForm.getDescripcion()) ||
	        		theForm.getFile() == null || "".equals(theForm.getFile())){
	        	
	        	this.setRequestAttribute(STRING_REGISTRO,theForm.getIdSolicitud());
	        	return STRING_VERDOCUMENTOS;
	        }
	        Calendar c = Calendar.getInstance();
	        docBean.setFechaCreacion(c.getTime());
	        
	        ArrayList<DocumentoBean> documentosAnteriores;
	        DocumentoQuery documentoAuxConsulta = new DocumentoQuery();
	        SolicitudComunQuery solicitudComunAux = new SolicitudComunQuery();
	        solicitudComunAux.setIdSolicitud(Long.parseLong(theForm.getIdSolicitud()));
	        documentoAuxConsulta.setSolicitudComun(solicitudComunAux);
	        documentosAnteriores = documentoConvocatoriasManager.buscarDocumentosConvocatoria(documentoAuxConsulta);
	        Long tamanioTotal = 0L;
	        if(documentosAnteriores != null){
	        	for(int i=0;i<documentosAnteriores.size();i++){
	        		tamanioTotal = tamanioTotal + documentosAnteriores.get(i).getTamano();
	        	}
	        }	        
			
	        docBean.setIdSolicitud(Long.parseLong(theForm.getIdSolicitud()));
	        docBean.setEntorno(Constantes.ENTORNO_SOLICITUDES);
	        Long id = 0L;
	        if(tamanioTotal + docBean.getTamano() < Constantes.TAMANIO_MAXIMO_DOCUMENTOS){
	        	try{
	        		id = documentoConvocatoriasManager.insertarDocumento(docBean);
	        	}catch(Exception e){
	        		this.getRequest().setAttribute("errorDescripcion",RESOURCE_MESSAGE.getString("field.documento.errorSubir"));
	        		logger.error("Error subir documento ",e);
	        		return "errorGenerico";
	        	}
	        }
	        if(id == 0){
	        	this.getRequest().setAttribute("errorDescripcion",RESOURCE_MESSAGE.getString("field.documento.errorSubir"));
	 			return "errorGenerico";
	 			
	        }
	        logger.info("Documento Insertado id:"+id);
	        
	        //Insertar en tabla DOCUMENTOS, 
	        //Insertar en Alfresco
	        TamanioDocumentoBean tamanioDocumentoBean = new TamanioDocumentoBean();
	        tamanioDocumentoBean.setTamanioTotal(tamanioTotal);
	        getLogger().debug("SubirDocumentoSpring -end");
	        this.setRequestAttribute("tamanio", tamanioDocumentoBean);
	        this.setRequestAttribute(STRING_REGISTRO,theForm.getIdSolicitud());
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			SpringMessages messages = new SpringMessages();
			messages.add("dsErrorSubir", new SpringMessage("field.documento.errorAlta"));
			saveErrors(this.getRequest(), messages);
			logger.error("Error alta documento ",e);
			this.setRequestAttribute(STRING_REGISTRO,theForm.getIdSolicitud());
        	return STRING_VERDOCUMENTOS;
		}
	
		
	}
	
	/**
	 * Safe long to int.
	 *
	 * @param l el l
	 * @return el int
	 */
	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
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
	 * Obtiene el documento manager.
	 *
	 * @return el documento manager
	 */
	public DocumentosConvocatoriaManager getDocumentoManager() {
		return documentoConvocatoriasManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param documentoManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentosConvocatoriaManager documentoManager) {
		this.documentoConvocatoriasManager = documentoManager;
	}
	
	
}
