package es.map.ipsg.action.documentos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.CrearCSVDocumentoForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class SubirCSVDocumentoSpring.
 */
public class SubirCSVDocumentoSpring extends AbstractSpring {
	
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubirCSVDocumentoSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	
	/**
	 * Crea una nueva subir CSV documento spring.
	 */
	public SubirCSVDocumentoSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error SubirCSVDocumentoSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		getLogger().debug("SubirCSVDocumentoSpring -start");
		
		CrearCSVDocumentoForm theForm = (CrearCSVDocumentoForm) form;
		
		
        ServletOutputStream out = null;
        HttpServletResponse response = this.getResponse();
		
		try {
			MultipartFile theFile = theForm.getFile();
	        String contentType = theFile.getContentType();
	        String fileName = theFile.getName();
	        long fileSize = theFile.getSize();
	        byte[] fileData = theFile.getBytes();
	        logger.info("Tipo: "  + contentType);
	        logger.info("Nombre: " + fileName);
	        logger.info("Tamano (KByte): " + fileSize/1024);

	        DocumentoBean docBean= new DocumentoBean();
	        Calendar cal = Calendar.getInstance();
	        
	        docBean.setContenidoDocumento(fileData);
	        docBean.setNombre(Constantes.NOMBRE_CSV_JUSTICIA);
	        docBean.setFechaCreacion(cal.getTime());
	       	docBean.setIdTipoDocumento(String.valueOf(Constantes.TIPO_DOCUMENTO_CSV_JUSTICIA));
	       	docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
	    	
	        String[] arrNombre=fileName.split("\\.");
	        String extension = "";
	        if(arrNombre.length>1){
	        	extension=arrNombre[arrNombre.length-1];
	        }
	        logger.info("Extension: " + extension);
	        docBean.setExtension(extension);
	        
	        int mes = cal.get(Calendar.MONTH)+1;
	        final String separador = File.separator;
			
	        // Montar ubicacion
	        StringBuilder rutaDocumento = new StringBuilder();
			rutaDocumento
				.append(cal.get(Calendar.YEAR)).append(separador)
				.append(mes).append(separador)
				.append(cal.get(Calendar.DAY_OF_MONTH)).append(separador)
				.append(cal.get(Calendar.HOUR_OF_DAY)).append(separador)
				.append("CSV").append(separador);
	        
			docBean.setUbicacion(rutaDocumento.toString());
	       	
			// guardado en filesystem el csv original y su registro en bb.dd
			DocumentoBean docBeanOriginal = documentoManager.insertarDocumentoCSVJusticia(docBean); 
			logger.info("Documento Insertado id:" + docBeanOriginal.getId());
			
			DocumentoBean docBeanModificado = documentoManager.modificarDocumentoCSVJusticia(docBeanOriginal); 
	       	logger.info("Documento Modificado id:" + docBeanModificado.getId());
	       	logger.info("Documento Modificado nombre:" + docBeanModificado.getNombreAlfresco());
	       	
	       	// retorna un archivo de aplicacion csv
		    response.setContentType("application/vnd.ms-excel");
		    response.setHeader("Content-Disposition","attachment;filename=" + docBeanModificado.getNombreAlfresco());
			
		   	    
	       	// el archivo se recoge del filesystem
	       	try(FileInputStream in = new FileInputStream(docBeanModificado.getFichero())){
	       		
		        out = response.getOutputStream();
	
		        byte[] outputByte = new byte[4096];
		        //copy binary content to output stream
		        while(in.read(outputByte, 0, 4096) != -1){
		        	out.write(outputByte, 0, 4096);
		        }
		             	
		        getLogger().debug("SubirDocumentoCSVSpring -end");
		       
				
		        return null;
	       	}
		} catch (FileNotFoundException ex) {
			logger.warn(ex);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.documento.errorCSVSubida"));
			logger.error("Error SubirCSVDocumentoSpring() - errorCSVSubida :",ex);
			return "nosuccess";
		} catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.documento.errorCSVSubida"));
			logger.error("Error SubirCSVDocumentoSpring()- errorCSVSubida:",e);
			return "nosuccess";
		} finally {			
	        out.flush();
	        out.close();
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
