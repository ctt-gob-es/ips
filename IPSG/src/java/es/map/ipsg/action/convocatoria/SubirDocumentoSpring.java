package es.map.ipsg.action.convocatoria;

import java.io.File;
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
 * El Class SubirDocumentoSpring.
 */
public class SubirDocumentoSpring extends AbstractSpring {
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubirDocumentoSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);

	
	/**
	 * Crea una nueva subir documento spring.
	 */
	public SubirDocumentoSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error SubirDocumentoSpring(): ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("SubirDocumentoSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		try{
			MultipartFile theFile = theForm.getFile();
	        String contentType = theFile.getContentType();
	        String fileName = theFile.getOriginalFilename().toString();
	        long fileSize = theFile.getSize();
	        byte[] fileData = theFile.getBytes();
	        logger.info("Tipo: "  + contentType);
	        logger.info("Nombre: " + fileName);
	        logger.info("Tamano (KByte): " + fileSize/1024);
	        logger.info("Entorno: "+theForm.getEntorno());
	        logger.info("IdConvocatoria: "+theForm.getIdConvocatoria());
	        logger.info("Descripcion: "+theForm.getDescripcion());
	        
	        DocumentoBean docBean= new DocumentoBean();
	        docBean.setContenidoDocumento(fileData);
	        
	        String[] arrNombre=fileName.split("\\.");
	        String extension="";
	        if(arrNombre.length>1){
	        	extension=arrNombre[arrNombre.length-1];
	        }
	        logger.info("Extension: "+extension);
	        docBean.setExtension(extension);
	        docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
	        docBean.setNombre(theForm.getNombre());
	        docBean.setDescripcion(theForm.getDescripcion());
	        
	        Calendar cal = Calendar.getInstance();
	        docBean.setFechaCreacion(cal.getTime());
	        
	        int mes = cal.get(Calendar.MONTH)+1;
	        final String separador = File.separator;
			
	        // Montar ubicacion
	        StringBuilder rutaDocumento = new StringBuilder();
			rutaDocumento
				.append(cal.get(Calendar.YEAR)).append(separador)
				.append(mes).append(separador)
				.append(cal.get(Calendar.DAY_OF_MONTH)).append(separador)
				.append(cal.get(Calendar.HOUR_OF_DAY)).append(separador)
				.append(theForm.getIdConvocatoria()).append(separador);
	        
			docBean.setUbicacion(rutaDocumento.toString());
	        docBean.setIdConvocatoria(Long.parseLong(theForm.getIdConvocatoria()));
	        docBean.setEntorno(theForm.getEntorno());
	        
	        logger.info("Documento Insertado id:"+documentoManager.insertarDocumento(docBean));
	        
	        //Insertar en tabla DOCUMENTOS, 
	        getLogger().debug("SubirDocumentoSpring -end");
	        setRequestAttribute("resultado", "Correcto");
	        theForm.setResultado("Correcto");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("error.alfresco.subirDocumento"));
			logger.error("Error SubirDocumentoSpring() - doExecute: ",e);
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
