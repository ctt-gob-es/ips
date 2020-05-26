package es.map.ipsg.action.solicitudPresencial;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.TipoDocumento;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.TipoDocumentoBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.TipoDocumentoManager;

/**
 * El Class SubirDocumentoSolicitudPresencialSpring.
 */
public class SubirDocumentoSolicitudPresencialSpring extends AbstractSpring {
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el tipo documento manager. */
	private TipoDocumentoManager tipoDocumentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubirDocumentoSolicitudPresencialSpring.class);


	/**
	 * Crea una nueva subir documento solicitud presencial spring.
	 */
	public SubirDocumentoSolicitudPresencialSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setTipoDocumentoManager((TipoDocumentoManager) getBean("tipoDocumentoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error SubirDocumentoSolicitudPresencialSpring - settear documento y tipo de documento",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("SubirDocumentoSolicitudPresencialSpring -start");
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
	        logger.info("IdSolicitud: "+theForm.getIdSolicitud());	        
	        
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
	        
	        Calendar c = Calendar.getInstance();
	        docBean.setFechaCreacion(c.getTime());  
	        docBean.setIdSolicitud(Long.parseLong(theForm.getIdSolicitud()));
			TipoDocumentoQuery  tipoDocumentoQuery = new TipoDocumentoQuery();
			tipoDocumentoQuery.setId(Integer.parseInt(theForm.getIdTipoDocumento()));
			List<TipoDocumentoBean> lTipoDocumentoBean;
			
			lTipoDocumentoBean = tipoDocumentoManager.buscarTipoDocumentoCombo(tipoDocumentoQuery);	        
	        
	        docBean.setTipoDocumento(convertir(lTipoDocumentoBean.get(0)));
	        docBean.setEntorno(theForm.getEntorno());
	        
	        Long idDocumentoInsertado = documentoManager.insertarDocumentoAsociado(docBean);
	        
	        // Limpiamos los campos para que no se muestren
	        theForm.setNombre("");
	        theForm.setDescripcion("");
	        logger.info("Documento Insertado id:" + idDocumentoInsertado);
	        
	        //Insertar en tabla DOCUMENTOS, 
	        getLogger().debug("SubirDocumentoSolicitudPresencialSpring -end");
			return "success";
			
		}catch(Exception e){
			logger.debug(e);
			logger.error("Error SubirDocumentoSolicitudPresencialSpring - insertar documento",e);
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
	 * Convertir.
	 *
	 * @param tipoDocumentoBean el tipo documento bean
	 * @return el tipo documento
	 */
	public TipoDocumento convertir(TipoDocumentoBean tipoDocumentoBean) {
		
		TipoDocumento tipoDocumento = new TipoDocumento();
		
		tipoDocumento.setCodigo(tipoDocumentoBean.getCodigo());
		tipoDocumento.setDescripcion(tipoDocumentoBean.getDescripcion());
		tipoDocumento.setId(Integer.parseInt(tipoDocumentoBean.getId()));
		
		return tipoDocumento;
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
