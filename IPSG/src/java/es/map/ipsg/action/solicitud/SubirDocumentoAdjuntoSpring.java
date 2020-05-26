package es.map.ipsg.action.solicitud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.DocumentoManager;

/**
 * El Class SubirDocumentoAdjuntoSpring.
 */
public class SubirDocumentoAdjuntoSpring extends AbstractSpring {
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubirDocumentoAdjuntoSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);


	/**
	 * Crea una nueva subir documento adjunto spring.
	 */
	public SubirDocumentoAdjuntoSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error(" Error SubirDocumentoAdjuntoSpring: ",e);
		}

	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("SubirDocumentoAdjuntoSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		try{
			MultipartFile theFile = theForm.getFile();
	        String contentType = theFile.getContentType();
	        String fileName = theFile.getOriginalFilename();
	        long fileSize = theFile.getSize();
	        byte[] fileData = theFile.getBytes();
	        logger.info("Tipo: "  + contentType);
	        logger.info("Nombre: " + fileName);
	        logger.info("Tamano (KByte): " + fileSize/1024);
	        logger.info("Entorno: "+theForm.getEntorno());
	       
	        
	        
	        DocumentoBean docBean= new DocumentoBean();
	        docBean.setContenidoDocumento(fileData);
	        
	        String[] arrNombre=fileName.split("\\.");
	        logger.info("ArrNombre"+Arrays.toString(arrNombre)+"\nArrNombre length:"+arrNombre.length);
	        String extension="";
	        if(arrNombre.length>1){
	        	extension=arrNombre[arrNombre.length-1];
	        }
	        logger.info("Extension: "+extension);
	        docBean.setExtension(extension);
	        docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
	        docBean.setNombre(theForm.getNombre());
	        docBean.setDescripcion(theForm.getDescripcion());
	        
	        Calendar c = Calendar.getInstance();
	        docBean.setFechaCreacion(c.getTime());
	        
	        docBean.setEntorno(theForm.getEntorno());
	        
	        Long idDocumento = documentoManager.insertarDocumentoAdjunto(docBean);
	        
	        logger.info("Documento Insertado id:"+idDocumento);
	        
	        //Recuperamos de session la lista con los id de los documentos
	        ArrayList<Long> listaIdDocumentos = (ArrayList<Long>) getSessionAttribute("listaIdDocumentos");
	        listaIdDocumentos.add(idDocumento);
	        
	        setSessionAttribute("listaIdDocumentos", listaIdDocumentos);
	        
	        
	        //Insertar en tabla DOCUMENTOS, 
	        //Insertar en Alfresco
	        getLogger().debug("SubirDocumentoAdjuntoSpring -end");
	        setRequestAttribute("accion", "../spring/verContactarCiudadano?menu=N");
	        setRequestAttribute("resultado", "Correcto");
	        theForm.setResultado("Correcto");
			return "success";
		}catch(Exception e){
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("error.alfresco.subirDocumento"));
			logger.error(" Error SubirDocumentoAdjuntoSpring - error la subir Documento Alfresco: ",e);
			theForm.setResultado("InCorrecto");
			setRequestAttribute("resultado", "InCorrecto");
			return "nosuccess";
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
