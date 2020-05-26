package es.map.ipsc.spring.solicitudes;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.FileUploadForm;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.utils.SHA0;

/**
 * El Class FileUploadSpring.
 */
public class FileUploadSpring extends AbstractSecureSpring {


	private static final String TIPO_DOCUMENTO_ADJUNTO = "1";
	private static final String TIPO_DISCAPACIDAD = "10";
	private static final String TIPO_EXENCION = "6";

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(FileUploadSpring.class);
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el properties. */
	private static Properties properties;
	
	/**
	 * Crea una nueva file upload spring.
	 */
	public FileUploadSpring(){
		try{
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.error("Error FileUploadSpring",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	public String doExecute(SpringForm form) throws Exception {
		logger.info("FileUploadSpring - INI PRUEBA");
		
		Long tamanioFichero = Long.parseLong(properties.getProperty("conf.tamanioFichero"));
		logger.info("Tamano maximo de ficheros es de: "+tamanioFichero);
	    FileUploadForm fileUploadForm = (FileUploadForm)form;
	    boolean limiteTamano = false;
	    
	    if (fileUploadForm != null && !StringUtils.isEmpty(fileUploadForm.getNumSolicitudFile())) {
	    	
	    	SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
	    	solicitudComunQuery.setNumeroSolicitud(fileUploadForm.getNumSolicitudFile());
	    	SolicitudBean solicitudBean =solicitudesManager.buscarRegistroSolicitud(solicitudComunQuery);
	    	
	    	if (solicitudBean != null && solicitudBean.getId() != null && solicitudBean.getId() > 0) {
	    		//Documentos justificante adjuntos
	    		if (fileUploadForm.getFile1() != null && fileUploadForm.getFile1().size() > 0) {
	    			limiteTamano = guardarAdjuntos(tamanioFichero, fileUploadForm, limiteTamano, fileUploadForm.getFile1(), solicitudBean, TIPO_DOCUMENTO_ADJUNTO);
	    		}
		    	//Documentos justificantes discapacidad
	    		if (fileUploadForm.getFile10() != null && fileUploadForm.getFile10().size() > 0) {
	    			limiteTamano = guardarAdjuntos(tamanioFichero, fileUploadForm, limiteTamano, fileUploadForm.getFile10(), solicitudBean, TIPO_DISCAPACIDAD);
	    		}
		    	if (fileUploadForm.getFile6() != null && fileUploadForm.getFile6().size() > 0) {
			    	//Documentos justificantes EXENCION
			    	limiteTamano = guardarAdjuntos(tamanioFichero, fileUploadForm, limiteTamano, fileUploadForm.getFile6(), solicitudBean, TIPO_EXENCION);
		    	}
	    	} else {
	    		//Devolver un error porque no se ha podido obtener la solicitud
	    		logger.info("El id de la solicitud esta vacio");
	    	}

	    	
	    } else {
	    	//Devolver un error de que no se puede subir los ficheros porque no se ha generado bien el numero de solicitud
	    	logger.info("El numero de solicitud esta vacio");
	    }
	    
	    if (limiteTamano) {
	    	//Error porque alguno de los archivos ha superado el tamano de 100Mb y no se ha podido subir
	    	logger.info("Algunos de los ficheros supera el tamaño minimo");
	    }
	    
	    logger.info("FileUploadSpring - FIN PRUEBA");
		return "success";
	}
	
	/**
	 * Meotodo que guarda los documentos adjuntos
	 * 
	 * @param tamanioFichero
	 * @param fileUploadForm
	 * @param limiteTamano
	 * @param files
	 * @param solicitudBean
	 * @param tipoDocumentoAdjunto
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private boolean guardarAdjuntos(Long tamanioFichero, FileUploadForm fileUploadForm, boolean limiteTamano,
			List<MultipartFile> files, SolicitudBean solicitudBean, String tipoDocumentoAdjunto) throws IOException, Exception {
		for (MultipartFile archivo : files) {
			if (archivo.getSize() != 0 && archivo.getSize() <= tamanioFichero) {
		        String contentType = archivo.getContentType();
		        String fileName = archivo.getOriginalFilename().toString();
		        long fileSize = archivo.getSize();
		        byte[] fileData = archivo.getBytes();
		        logger.info("Tipo: "  + contentType);
		        logger.info("Nombre: " + fileName);
		        logger.info("Tamano (KByte): " + fileSize/1024);
		        
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
		        docBean.setNombre(arrNombre[0].toUpperCase());
		        docBean.setDescripcion(fileUploadForm.getNumSolicitudFile());
		        
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
					.append(solicitudBean.getIdConvocatoria()).append(separador);
		        
				docBean.setUbicacion(rutaDocumento.toString());
		        docBean.setIdConvocatoria(solicitudBean.getIdConvocatoria());
		        docBean.setIdTipoDocumento(tipoDocumentoAdjunto);
		        docBean.setIdSolicitud(solicitudBean.getId());
		        
				// Generación del hash del documento para almacenarlo en bbdd.
				try {
					SHA0 hash = new SHA0();
					String hashFile=hash.getHash(fileData);
					docBean.setHashExtracto(hashFile);

				} catch (NoSuchAlgorithmException e2) {
					logger.error("Error obteniendo hash del documento: ",e2);
				} catch (Exception e) {
					logger.error("SubirDocumentos - Error ",e);
				}
				
				documentoConvocatoriasManager.insertarDocumento(docBean);
			} else {
				limiteTamano = true;
			}

		}
		return limiteTamano;
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

	public DocumentosConvocatoriaManager getDocumentoConvocatoriasManager() {
		return documentoConvocatoriasManager;
	}

	public void setDocumentoConvocatoriasManager(DocumentosConvocatoriaManager documentoConvocatoriasManager) {
		this.documentoConvocatoriasManager = documentoConvocatoriasManager;
	}

	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

}