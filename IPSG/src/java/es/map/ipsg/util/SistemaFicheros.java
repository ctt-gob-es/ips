package es.map.ipsg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.Modelo;
import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudCcaa;
import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoPago;
import es.map.ips.model.TipoPagoQuery;
import es.map.ips.model.TipoSolicitud;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudComunAuxiliarBean;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.EstadoSolicitudManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.TipoSolicitudManager;

/**
 * El Class SistemaFicheros.
 */
public class SistemaFicheros {


	/** La constante logger. */
	// Sistema de ficheros
	private static final Logger logger = Logger.getLogger(SistemaFicheros.class);
	
	/** La constante MENSAJES. */
	private static final String[] MENSAJES = {"Nº Justificante Solicitud",
											  "Fecha Pago Solicitud",
											  "NRC",
											  "Fecha Registro Solicitud",
											  "Nº Registro Solicitud"
											  };

	/** el url inicial. */
	private String urlInicial;
	
	/** el separador. */
	private String separador;
	
	/** el url final. */
	private String urlFinal;
	
	/** el url inicial struts. */
	private String urlInicialStruts;
	
	/** el url final struts. */
	private String urlFinalStruts;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el solicitud ccaa auxiliar manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el tipo solicitud manager. */
	private TipoSolicitudManager tipoSolicitudManager;
	
	/** el modelos manager. */
	private ModeloManager modelosManager;
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	
	/** el properties. */
	private static Properties properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
	
	/** La constante STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO. */
	private static final String STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO = "insertarContenido - Error al crear el objeto: ";
	
	/** La constante STRING_INSERTARCONTENIDO_ERROR. */
	private static final String STRING_INSERTARCONTENIDO_ERROR = "insertarContenido - Error: ";
	
	/** La constante STRING_ERROR_INSERTAR_REGISTRO_CSV_BASEDEDATOS. */
	private static final String STRING_ERROR_INSERTAR_REGISTRO_CSV_BASEDEDATOS = "Error insertarRegistroCsvBaseDeDatos: ";
	
	/** La constante JUSTIFICANTE_REGISTRO. */
	private static final String JUSTIFICANTE_REGISTRO = "JustificanteRegistroPdfCsv";
	
	/** La constante STRING_BORRARDIRECTORIO_ERROR. */
	private static final String STRING_BORRARDIRECTORIO_ERROR = "Se ha producido un error al borrar el directorio.";
	
	/**
	 * Crea una nueva sistema ficheros.
	 */
	public SistemaFicheros() {
		this.urlInicial = properties.getProperty("sistemaficheros.url.escritura");
		this.urlFinal = properties.getProperty("sistemaficheros.url.final");
		
		this.urlInicialStruts = properties.getProperty("sistemaficheros.url.struts.escritura");
		this.urlFinalStruts = properties.getProperty("sistemaficheros.url.struts.final");
		this.separador = File.separator;
		try {
			setSolicitudComunAuxiliarManager( (SolicitudComunAuxiliarManager) ApplicationContextProvider.getInstance().getBean("solicitudComunAuxiliarManager"));
			setSolicitudesManager( (SolicitudesManager) ApplicationContextProvider.getInstance().getBean("solicitudesManager"));
			setSolicitudCcaaAuxiliarManager( (SolicitudCcaaAuxiliarManager) ApplicationContextProvider.getInstance().getBean("solicitudCcaaAuxiliarManager"));
			setSolicitudCcaaManager( (SolicitudCcaaManager) ApplicationContextProvider.getInstance().getBean("solicitudCcaaManager"));
			setPagoSolicitudManager( (PagoSolicitudManager) ApplicationContextProvider.getInstance().getBean("pagoSolicitudManager"));
			setRegistroSolicitudManager( (RegistroSolicitudManager) ApplicationContextProvider.getInstance().getBean("registroSolicitudManager"));
			setEstadoSolicitudManager((EstadoSolicitudManager) ApplicationContextProvider.getInstance().getBean("estadoSolicitudManager"));
			setTipoSolicitudManager((TipoSolicitudManager) ApplicationContextProvider.getInstance().getBean("tipoSolicitudManager"));
			setModelosManager((ModeloManager) ApplicationContextProvider.getInstance().getBean("modelosManager"));
			setTipoPagoManager((TipoPagoManager) ApplicationContextProvider.getInstance().getBean("tipoPagoManager"));
			setDocumentoManager((DocumentoManager) ApplicationContextProvider.getInstance().getBean("documentoManager"));
		} catch (Exception e) {
			logger.error("Error Sistema Ficheros: ",e);
		}
	}

	/**
	 * Metodo que inserta documentos en el sistema de ficheros.
	 * Crea los directorios si no existen.
	 *
	 * @param documento el documento
	 * @param rutaFinal el ruta final
	 * @return boolean
	 * @throws Exception el exception
	 */
	public boolean insertarContenido(DocumentoBean documento, String rutaFinal) throws Exception {
		
		logger.info("insertarContenido - start");
        try {        	
        	StringBuffer sbRuta = new StringBuffer();
        	
        	// En el  caso de insercion del justificante generado en IPSG, 
        	// se indica la ubicacion final del documento.
        	if(null != rutaFinal){
        		sbRuta.append(rutaFinal);
        	}else{
        		sbRuta.append(urlInicial);
        	}
        	
        	sbRuta.append(documento.getUbicacion()).append(separador).append(documento.getNombreAlfresco());
        	
        	File salida = new File(sbRuta.toString());
        	
        	if (!salida.getParentFile().exists()) {
        		salida.getParentFile().mkdirs();
        	}
			logger.info("guardarDocumento - ruta de carpeta IPS: " + salida.getAbsolutePath());

	        try(OutputStream outputStream = new FileOutputStream (salida)){ 
	        	outputStream.write(documento.getContenidoDocumento());		    
	        }
		    if(!salida.getAbsoluteFile().exists()){
				logger.info(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salida.getAbsoluteFile());
				throw new ApplicationException(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salida.getAbsoluteFile());
		    }
			
		    logger.info("insertarContenido - end");
	        return true;
		} catch (IOException e) {
			logger.error("insertarContenido - Error:",e);
			
			return false;
		}
	}
	
	/**
	 * Borrar contenido.
	 *
	 * @param documento el documento
	 * @return verdadero, si tiene exito
	 */
	public boolean borrarContenido(DocumentoBean documento){
		
		
		logger.debug("borrarContenido - start");		
        try {
        	StringBuffer sbRuta = new StringBuffer();
        	sbRuta.append(urlInicial).append(documento.getUbicacion())
        		.append(separador).append(documento.getNombreAlfresco());
        	
        	File salida = new File(sbRuta.toString());
        	
        	if (salida.exists()) {
        		salida.delete();
        		
        		// Finalmente, si el directorio esta vacio, se elimina tambien.
				try{
					File directorio = new File(urlInicial + documento.getUbicacion());
					File lista[] = directorio.listFiles();
					if(lista.length == 0){
						directorio.delete();
					}
				}catch (Exception e){
					logger.error(STRING_BORRARDIRECTORIO_ERROR,e);
				}
        		
        	}else{
				logger.error("borrarContenido - Error: no se ha encontrado el documento: " + salida.getAbsolutePath());
        	}
		} catch (Exception e) {
			logger.error("borrarContenido - Error: ",e);
			
			return false;
		}
		logger.debug("borrarContenido - end");
    	
        return true;
			
	}
	
	/**
	 * Borrar contenido lectura escritura.
	 *
	 * @param documento el documento
	 * @return verdadero, si tiene exito
	 */
	public boolean borrarContenidoLecturaEscritura(DocumentoBean documento){
		
		
		logger.debug("borrarContenido - start");		
        try {
        	StringBuffer sbRuta = new StringBuffer();
        	sbRuta.append(urlInicial).append(documento.getUbicacion())
        		.append(separador).append(documento.getNombreAlfresco());
        	
        	File salida = new File(sbRuta.toString());
        	
        	if (salida.exists()) {
        		salida.delete();
        		
        		// Finalmente, si el directorio esta vacio, se elimina tambien.
				try{
					File directorio = new File(urlInicial + documento.getUbicacion());
					File lista[] = directorio.listFiles();
					if(lista.length == 0){
						directorio.delete();
					}
				}catch (Exception e){
					logger.error(STRING_BORRARDIRECTORIO_ERROR,e);
				}
        		
        	}else{
        		salida.delete();
        		
        		// Finalmente, si el directorio esta vacio, se elimina tambien.
				try{
					File directorio = new File(urlFinal + documento.getUbicacion());
					File lista[] = directorio.listFiles();
					if(lista.length == 0){
						directorio.delete();
					}
				}catch (Exception e){
					logger.error(STRING_BORRARDIRECTORIO_ERROR,e);
				}
        	}
		} catch (Exception e) {
			logger.error("borrarContenido - Error: ",e);
			
			return false;
		}
		logger.debug("borrarContenido - end");
    	
        return true;
			
	}
	
	/**
	 * Borrar directorio.
	 *
	 * @param directorio el directorio
	 * @return verdadero, si tiene exito
	 */
	public boolean borrarDirectorio(String directorio){
		
		logger.debug("borrarDirectorio - start");		
        try {        
        	StringBuffer sbRuta = new StringBuffer();
        	sbRuta.append(urlInicial).append(directorio).append(separador);
        	File salida = new File(sbRuta.toString());
        	
        	if (salida.exists()) {
        		if(salida.list().length==0)
        			salida.delete();
        		else{
        		   String files[] = salida.list();
        		   File salidaTemp = null;
              	   
        		   for (String temp : files) {
        			   StringBuffer sbRutaAux = new StringBuffer();
        			   sbRutaAux.append(urlInicial).append(separador).append(temp);
              		   salidaTemp = new File(sbRutaAux.toString());
              		   salidaTemp.delete();
              	   }
              	   salida.delete();
        		}
        	}else{
				logger.error("borrarDirectorio - Error: no se ha encontrado el directorio: " + salida.getAbsolutePath());
        	}
		} catch (Exception e) {
			logger.error("borrarDirectorio - Error: ",e);
			return false;
		}
		logger.debug("borrarDirectorio - end");
    	
        return true;
			
	}
	
	/**
	 * Metodo que obtiene el contenido de un documento de solicitud.
	 * Primero busca en el directorio definitivo de Solo Lectura.
	 * Sino, se intenta localizar en el de Escritura.
	 *
	 * @param documento el documento
	 * @return el byte[]
	 */
	public byte [] obtenerContenido(DocumentoBean documento){

        try {  
        	//Primero buscamos en el FTP Spring
        	// 1. Busqueda en el directorio definitvo.
        	StringBuffer sbRuta = new StringBuffer();
        	sbRuta.append(urlFinal).append(documento.getUbicacion()).append(separador).append(documento.getNombreAlfresco());
        	logger.info("URL primer intento FTP Spring: "+sbRuta);
        	File salida = new File(sbRuta.toString());	

	    	if(salida.exists()){	
		        	byte fileContent[] = FileUtils.readFileToByteArray(salida);	        	
		        	return fileContent;
	    	}else{
	    		// 2. Busqueda en directorio de Lectura-Escritura.
	    		sbRuta = new StringBuffer();
	    		sbRuta.append(urlInicial).append(documento.getUbicacion()).append(separador).append(documento.getNombreAlfresco());
	        	logger.info("URL segundo intento FTP Spring: "+sbRuta);
	        	salida = new File(sbRuta.toString());
	        	if(salida.exists()){	        	   		
		        	byte fileContent[] = FileUtils.readFileToByteArray(salida);	        	
		        	return fileContent;
		    	}
	    	}
	    	
	    	//Segundo buscamos en el FTP de Struts
        	// 1. Busqueda en el directorio definitvo.
        	StringBuffer sbRutaStruts = new StringBuffer();
        	sbRutaStruts.append(urlFinalStruts).append(documento.getUbicacion()).append(separador).append(documento.getNombreAlfresco());
        	logger.info("URL primer intento FTP Struts: "+sbRutaStruts);
        	File salidaStruts = new File(sbRutaStruts.toString());	

	    	if(salidaStruts.exists()){	        	   		
	        	byte fileContent[] = FileUtils.readFileToByteArray(salidaStruts);	        	
	        	return fileContent;
	        	
	    	}else{
	    		// 2. Busqueda en directorio de Lectura-Escritura.
	    		sbRutaStruts = new StringBuffer();
	    		sbRutaStruts.append(urlInicialStruts).append(documento.getUbicacion()).append(separador).append(documento.getNombreAlfresco());
	        	logger.info("URL segundo intento FTP Struts: "+sbRutaStruts);
	        	salidaStruts = new File(sbRutaStruts.toString());
	        	if(salidaStruts.exists()){	        	   		
		        	byte fileContent[] = FileUtils.readFileToByteArray(salidaStruts);	        	
		        	return fileContent;
		    	}
	    	}
	    	
			logger.error("obtenerContenido - Error: no se ha encontrado el documento: " + salida.getAbsolutePath());
			
			return null;
			
	    } catch (Exception e) {
			logger.error("obtenerContenido - Error:",e);
			return null;
		}
	}
	

	/**
	 * 
	 * @param zipFile
	 * @param listaDoc
	 * @param campoOrdenExcel
	 * @return
	 */
	public File descargarDocumentosTroceados(List<DocumentoBean> listaDoc, String campoOrdenExcel) {

		ZipOutputStream zipOS = null;
		File tempFile = null;
		
		try {
			
			tempFile = crearFicheroTemporal();
			zipOS = new ZipOutputStream(new FileOutputStream(tempFile));
			
		} catch (IOException e1) {
			logger.error("Fichero temporal no encontrado", e1);
		}
		
		if(zipOS != null) {
			
			for (DocumentoBean doc : listaDoc) {
	        	
	    		//Se coge la Url de salida
	        	StringBuffer sbRuta = new StringBuffer();
	        	
	        	sbRuta.append(urlFinal).append(doc.getUbicacion()).append(separador).append(doc.getNombreAlfresco());
	        	logger.info("URL primer intento FTP Spring: "+sbRuta);
	        	
	            File fichero = new File(sbRuta.toString());
	            
	            //Si no se encuentra en la ruta de lectura
		    	if(!fichero.exists()){	
		    		// 2. Busqueda en directorio de Escritura.
		    		sbRuta = new StringBuffer();
		    		sbRuta.append(urlInicial).append(doc.getUbicacion()).append(separador).append(doc.getNombreAlfresco());
		        	logger.info("URL segundo intento FTP Spring: "+sbRuta);
		        	fichero = new File(sbRuta.toString());
		    	}	
	            
	            
	            try(FileInputStream fis = new FileInputStream(fichero)) {
	                   //prueba con dos archivos
	                   ZipEntry entrada = new ZipEntry((!StringUtils.isEmpty(campoOrdenExcel)?(documentoManager.obtieneNombreCarpeta(campoOrdenExcel, doc)+"/"+fichero.getName()):fichero.getName()));
	                   zipOS.putNextEntry(entrada);
	                   
	                   IOUtils.copy(fis, zipOS);
	                   zipOS.closeEntry();
	                   
	            } catch (IOException e) {
	            	logger.error("Error en el fichero o no existe: ", e);
	            }
			}
			
			try {
				zipOS.close();
			} catch (IOException e) {
				logger.error("Error al cerrar ZIP: ", e);
			}
			
			return tempFile;
			
		} else {
			// Devolver error de que no se puede descargar
			throw new ApplicationException("No se ha podido crear el fichero ZIP");
		}

	}
	
	private File crearFicheroTemporal() throws IOException {
		return File.createTempFile("exportacion", null);
	}

	public void descargarZipTroceado(File zipFile, HttpServletResponse response) {
        
        // Descargar fichero
        if(zipFile != null) {
               
               try(FileInputStream zipFis = new FileInputStream(zipFile)) {
            	   	  Long temporal = System.currentTimeMillis();	
                      response.setContentType("application/octet-stream");
                      String contentDisposition = "inline; filename=\"" + "documentos_"+ temporal.toString() + ".zip"	+ "\"";
                      response.setHeader("Content-Disposition", contentDisposition);
                      response.setContentLength((int) zipFile.length());
                      
                      IOUtils.copy(zipFis, response.getOutputStream());
                      
                      response.flushBuffer();
                      
               } catch (IOException e) {
            	   logger.error("SitemaFicheros - descargarDocumentosTroceados- Error al descargar el fichero",e); 
               } 
        }
	}
	
	/**
	 * Metodo que comprueba si existe ya un justificante de registro
	 * Si existe, lo borra
	 * Primero busca en el directorio definitivo de Solo Lectura.
	 * Sino, se intenta localizar en el de Escritura.
	 *
	 * @param documento el documento
	 * @return verdadero, si tiene exito
	 */
	public boolean comprobarBorrarJustificante(DocumentoBean documento){

        try {  
        	// 1. Busqueda en el directorio definitvo.
        	StringBuffer sbRuta = new StringBuffer();
        	sbRuta.append(urlFinal).append(documento.getUbicacion()).append(separador);
        	File salida = new File(sbRuta.toString());	

	    	if(salida.exists()){	
	    		for (final File ficheroEntrada : salida.listFiles()) {
	    			if(ficheroEntrada.getName().contains(JUSTIFICANTE_REGISTRO)){	    	
	    				// Obtenemos el id de documento
	    				String idDocumento = ficheroEntrada.getName().substring(JUSTIFICANTE_REGISTRO.length(), ficheroEntrada.getName().length());
	    				String id = idDocumento.split(".pdf")[0];
	    				
	    				logger.info("IdDocumento: "+id);
	    				DocumentoQuery documentoQuery = new DocumentoQuery();
	    				documentoQuery.setId(Long.parseLong(id));
	    				
	    				DocumentoBean doc = documentoManager.obtenerDocumento(documentoQuery);
	    				ficheroEntrada.delete();
	    				documentoManager.borrarDocumentoLecturaEscritura(doc);
	    				logger.info("idConvocatoria-Borrar: "+doc.getIdConvocatoria());
	    			}
	    		}	        	
	        	return true;
	        	
	    	}else{
	    		// 2. Busqueda en directorio de Lectura-Escritura.
	    		sbRuta = new StringBuffer();
	        	sbRuta.append(urlInicial).append(documento.getUbicacion()).append(separador);
	        	salida = new File(sbRuta.toString());
	        	if(salida.exists()){	
		    		for (final File ficheroEntrada : salida.listFiles()) {
		    			if(ficheroEntrada.getName().contains("JustificanteRegistroPdfCsv")){		    				
		    				Long idDocumento = documento.getId();
		    				logger.info("IdDocumento: "+idDocumento);
		    				DocumentoQuery documentoQuery = new DocumentoQuery();
		    				documentoQuery.setId(idDocumento);
		    				
		    				DocumentoBean doc = documentoManager.obtenerDocumento(documentoQuery);
		    				documentoManager.borrarDocumento(doc);
		    				logger.info("idConvocatoria-Borrar: "+doc.getIdConvocatoria());
		    			}
		    		}	        	
		        	return true;
		        	
		    	}
	    	}
	    	
			logger.error("obtenerContenido - Error: no se han encontrado justificantes." );
			
			return false;
			
	    } catch (Exception e) {
			logger.error("obtenerContenido - Error:",e);
			return false;
		}
	}
	
	
	/**
	 * Metodo que copia un fichero a su ubicacion definitva.
	 *
	 * @param rutaFichero el ruta fichero
	 * @param ficheroOrigen el fichero origen
	 * @return verdadero, si tiene exito
	 */
	public boolean copiarFichero(String rutaFichero, File ficheroOrigen){

		try {
			Util.verificarDirectorio(urlFinal+rutaFichero);

			File destino = new File(urlFinal+rutaFichero);
			FileUtils.copyFileToDirectory(ficheroOrigen, destino);
			logger.info("Fichero copiado correctamente "+ficheroOrigen.getName());
			
		} catch (IOException e) {
			logger.error("Error copiando fichero",e);
			return false;
		}
		return true;
	}
	
	/**
	 * Insertar contenido migracion.
	 *
	 * @param documento el documento
	 * @param rutaFinal el ruta final
	 * @return verdadero, si tiene exito
	 * @throws Exception el exception
	 */
	public boolean insertarContenidoMigracion(DocumentoBean documento, String rutaFinal) throws Exception {

        try {        	
        	StringBuffer sbRuta = new StringBuffer();
        	
        	// En el  caso de insercion del justificante generado en IPSG, 
        	// se indica la ubicacion final del documento.
        
        		sbRuta.append(rutaFinal);
        	
        		// Proceso de Migracion de Alfresco.
        		sbRuta.append(documento.getNombreAlfresco());
        	
        	
        	File salida = new File(sbRuta.toString());
        	
        	if (!salida.getParentFile().exists()) {
        		salida.getParentFile().mkdirs();
        	}
			logger.info("GuardarDocumento - ruta de carpeta IPS: " + salida.getAbsolutePath());

	        try(OutputStream outputStream = new FileOutputStream (salida)){ 
	        	outputStream.write(documento.getContenidoDocumento());
	        }
		    
		    if(!salida.getAbsoluteFile().exists()){
				logger.info(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salida.getAbsoluteFile());
				throw new ApplicationException(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salida.getAbsoluteFile());
		    }else{
		    	logger.info("OK - Guardado");
		    }
			
	        return true;
		} catch (IOException e) {
			logger.error(STRING_INSERTARCONTENIDO_ERROR,e);
			return false;
		}
	}

	/**
	 * NO USADO.
	 *
	 * @param documento el documento
	 * @param rutaFinal el ruta final
	 * @param fichero el fichero
	 * @return verdadero, si tiene exito
	 * @throws Exception el exception
	 */
	public boolean insertarContenidoMigracion2(DocumentoBean documento, String rutaFinal, FileOutputStream fichero) throws Exception {

        try {        	
        	StringBuffer sbRuta = new StringBuffer();
        	
        	// En el  caso de insercion del justificante generado en IPSG, 
        	// se indica la ubicacion final del documento.
        	
        		sbRuta.append(rutaFinal);
        	
        		// Proceso de Migracion de Alfresco.
        		sbRuta.append(documento.getNombreAlfresco());
        	
        	
        	File salida = new File(sbRuta.toString());
        	
        	if (!salida.getParentFile().exists()) {
        		salida.getParentFile().mkdirs();
        	}
			logger.info("GuardarDocumento - ruta de carpeta IPS: " + salida.getAbsolutePath());

		    
		    if(!salida.getAbsoluteFile().exists()){
				logger.info(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salida.getAbsoluteFile());
				throw new ApplicationException(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salida.getAbsoluteFile());
		    }else{
		    	logger.info("OK - Guardado");
		    }
			
	        return true;
		} catch (Exception e) {
			logger.error(STRING_INSERTARCONTENIDO_ERROR,e);
			return false;
		}
	}
	
	
	/**
	 * Metodo que inserta documentos en el sistema de ficheros.
	 * Crea los directorios si no existen.
	 *
	 * @param documento el documento
	 * @param rutaFinal el ruta final
	 * @return String
	 * @throws Exception el exception
	 */
	public File insertarContenidoCSV(DocumentoBean documento, String rutaFinal) throws Exception {
				
		logger.info("insertarContenido - start");
        try {        	
        	StringBuffer sbRuta = new StringBuffer();
        	
        	if (null != rutaFinal){
        		sbRuta.append(rutaFinal);
        	} else {
        		sbRuta.append(urlInicial);
        	}
        	
        	// calculo del nombre csv_n_original.csv
        	File directorioCSV = new File(sbRuta.toString() + documento.getUbicacion());
        	if (!directorioCSV.exists()) {
        		directorioCSV.mkdirs();
        	}
        	
        	String nombreCSV = "csv_0_original.csv";
        	File salida = new File(directorioCSV.getPath() + separador + nombreCSV);
        	int i = 0;
        	while (salida.exists()) {
        		i++;
        		nombreCSV = "csv_" + i + "_original.csv";
        		salida =  new File(directorioCSV.getPath() + separador + nombreCSV );
        	}
        	
	       	logger.info("guardarDocumentoOriginal - ruta de carpeta IPS: " + salida.getAbsolutePath());

	        try(OutputStream outputStream = new FileOutputStream (salida))
	        {
	        	outputStream.write(documento.getContenidoDocumento());
	        }
		    
		    if(!salida.getAbsoluteFile().exists()){
				logger.info(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salida.getAbsoluteFile());
				throw new ApplicationException(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salida.getAbsoluteFile());
		    }
		    
		    logger.info("insertarContenido - end");
		    return salida;
		    		    
		} catch (IOException e) {
			logger.error(STRING_INSERTARCONTENIDO_ERROR,e);
			return null;
		}
	}
	
	
	/**
	 * Metodo que inserta documentos en el sistema de ficheros.
	 * Crea los directorios si no existen.
	 *
	 * @param documento el documento
	 * @return File
	 * @throws Exception el exception
	 */
	public File modificarContenidoCSV(DocumentoBean documento) throws Exception {
				
		logger.info("modificarContenido - start");
        try {        	
        	
        	File ficheroCsvOriginal = documento.getFichero();
        	
        	// creacion nuevo File modificado
        	File salidaFicheroCsvModificado = new File(ficheroCsvOriginal.toString().replace("original","modificado"));
        	
        	// se procede a escribir columna resutado OK en csv modificado
            try(FileWriter writer = new FileWriter(salidaFicheroCsvModificado))
            {
            
	            // primero creo una estructura de datos java consistente en una lista de listas(equivalentes a registros)
	            // dicha lista de listas equivaldra al fichero CSV original
	            List<List<String>> csv = convertirCSV(ficheroCsvOriginal);
	            
	            // escritura de registro
	            if (csv!=null && !csv.isEmpty()) {
	            	for (List<String> registro : csv){
	                	String resultado = insertarRegistroCsvBaseDeDatos(registro);
	        			registro.add(" " + resultado);
	                	CSVUtils.writeLine(writer,registro);
	        		}
	            }
	                        
	            writer.flush();
            }
        	        	
	       	logger.info("guardarDocumentoModificado - ruta de carpeta IPS: " + salidaFicheroCsvModificado.getAbsolutePath());

		    if(!salidaFicheroCsvModificado.getAbsoluteFile().exists()){
				logger.info(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salidaFicheroCsvModificado.getAbsoluteFile());
				throw new ApplicationException(STRING_INSERTARCONTENIDO_ERROR_CREAR_OBJETO + salidaFicheroCsvModificado.getAbsoluteFile());
		    }
			
		    logger.info("modificarContenido - end");
		    
		    return salidaFicheroCsvModificado;
			    
		} catch (IOException e) {
			logger.error(STRING_INSERTARCONTENIDO_ERROR,e);
			return null;
		}
	}
	
	/**
	 * Convertir CSV.
	 *
	 * @param file el file
	 * @return el list
	 */
	public List<List<String>> convertirCSV(File file) {
		File csvFile = file;
		
        String line = "";
        String cvsSplitBy = ";";
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile)))
        {	
        	List<List<String>> csvOriginal = new ArrayList<List<String>>();
            
            while ((line = bufferedReader.readLine()) != null) {
            	line = line.trim();
            	if (!line.equals("")) {
            		String[] solicitudPresencial = line.split(cvsSplitBy);
         	         
                    List<String> registroCsv = new ArrayList<String>();
                    for (int i=0;i<solicitudPresencial.length; i++) {
                    	registroCsv.add(solicitudPresencial[i]);
                    	logger.info(MENSAJES[i] + ": " + solicitudPresencial[i]);
           			}
                csvOriginal.add(registroCsv);
            	}
           }
           logger.info("\n");
           return csvOriginal; 
        } catch (FileNotFoundException e) {
        	logger.error("Error convertir csv: ",e);
        } catch (IOException e) {
        	logger.error("Error convertir csv: ",e);
        }
        return null;
	}
	
	/**
	 * Insertar registro csv base de datos.
	 *
	 * @param registro el registro
	 * @return el string
	 */
	public String insertarRegistroCsvBaseDeDatos(List<String> registro) {
				
		SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String numeroJustificanteSolicitud="";
		String fechaPagoSolicitud="";
		String nrc="";
		String fechaRegistroSolicitud="";
		String numeroRegistroSolicitud="";
				
		for (int i=0;i<registro.size();i++) {
			switch (i) {
				case 0:
					numeroJustificanteSolicitud = registro.get(0);
					break;
				case 1:
					fechaPagoSolicitud = registro.get(1);
					break;
				case 2:
					nrc=registro.get(2);
					break;
				case 3:
					fechaRegistroSolicitud = registro.get(3);
					break;
				case 4:
					numeroRegistroSolicitud = registro.get(4);
					break;
				default:
					break;
			}
		}
		   
		
        try {
			
			SolicitudComun solicitudComun = new SolicitudComun();
            SolicitudCcaa solicitudCcaa = new SolicitudCcaa();
            RegistroSolicitud registroSolicitud = new RegistroSolicitud();
            PagoSolicitud pagoSolicitud = new PagoSolicitud();
            SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
              
                           
            // validaciones sobre las fechas, si no existen no pueden registrarse correctamente los datos en bb.dd al 
            // ser campos not null
            Date datePagoSolicitud = null;
        	if (!"".equals(fechaPagoSolicitud)) {
        		 datePagoSolicitud = formatter.parse(fechaPagoSolicitud);
        		 pagoSolicitud.setFechaIntento(datePagoSolicitud);
           	} else {
	        	 logger.info("Falta la fecha de intento de pago de la solicitud");
	        	 return Constantes.RESULTADO_ER;
	         }
            
                        
            Date dateRegistroSolicitud = null;
           	if (!"".equals(fechaRegistroSolicitud)) {
        		dateRegistroSolicitud = formatter.parse(fechaRegistroSolicitud);
           	} else {
	        	logger.info("Falta la fecha de intento de registro de la solicitud");
	        	return Constantes.RESULTADO_ER;
	        }
           	    
            
        	// volcado datos tabla solicitud_comun_auxiliar a solicitud_comun
            SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
            solicitudComunAuxiliarQuery.setNumeroSolicitud(numeroJustificanteSolicitud);
            SolicitudComunAuxiliarBean solicitudComunAuxiliarBean = solicitudComunAuxiliarManager.buscarSolicitudComunAuxiliarById(solicitudComunAuxiliarQuery);
            
            if (solicitudComunAuxiliarBean != null) {
                
            	boolean isMod = false;
            	EstadoSolicitud estadoSolicitud = estadoSolicitudManager.obtenerEstadoSolicitud(Constantes.ESTADO_SOLICITUD_REGISTRADO);
	        	TipoSolicitud tipoSolicitud = tipoSolicitudManager.conseguirTipoSolicitud(Constantes.TIPO_SOLICITUD_PRESENCIAL);
	        	Modelo modelo = modelosManager.conseguirModelo790ById(Constantes.ID_MODELO_790007);
	        	
	        	isMod = (solicitudComunAuxiliarBean.getIdSolicitud() != null && solicitudComunAuxiliarBean.getIdSolicitud() > 0)?true:false;
	        	
	            solicitudComun = solicitudesManager.insertarSolicitudComunAuxiliar(solicitudComunAuxiliarBean, estadoSolicitud, tipoSolicitud, dateRegistroSolicitud , modelo, isMod);
	                
	            //  volcado datos tabla solicitud_ccaa_auxiliar a solicitud_ccaa
	            SolicitudCcaaAuxiliarQuery solicitudCccaAuxiliarQuery = new SolicitudCcaaAuxiliarQuery();
	            solicitudComunAuxiliarQuery.setIdSolicitudAuxiliar(solicitudComunAuxiliarBean.getIdSolicitud());
	            solicitudCccaAuxiliarQuery.setSolicitudComunAuxiliar(solicitudComunAuxiliarQuery);
	            SolicitudCcaaAuxiliarBean solicitudCccaAuxiliarBean = solicitudCcaaAuxiliarManager.obtenerSolicitudCcaaAuxiliarByIdSolicitud(solicitudCccaAuxiliarQuery);
	            
	            SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
	            SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
	            solicitudComunQuery.setIdSolicitud(solicitudCccaAuxiliarBean.getIdSolicitud());
	            solicitudCcaaQuery.setSolicitudComun(solicitudComunQuery);
	            solicitudCcaaBean = solicitudCcaaManager.obtenerSolicitudCcaaByIdSolicitud(solicitudCcaaQuery);
	            
	            if (solicitudCcaaBean != null && solicitudCcaaBean.getIdSolicitud() != null && solicitudCcaaBean.getIdSolicitud() > 0) {
	            	solicitudCcaaBean.setSolicitud(solicitudComun);
		            if (solicitudCccaAuxiliarBean != null) {
		            	solicitudCcaaBean.setComunidad(solicitudCccaAuxiliarBean.getComunidad());
		            	solicitudCcaaBean.setProvincia(solicitudCccaAuxiliarBean.getProvincia());
		            	solicitudCcaaBean.setTituloFamnumerosa(solicitudCccaAuxiliarBean.getTituloFamnumerosa());
		            }
	            	solicitudCcaaManager.modificarSolicitudCcaaBean(solicitudCcaaBean);
	            } else if (!isMod) {
		            solicitudCcaa.setSolicitudComun(solicitudComun);
		            if (solicitudCccaAuxiliarBean != null) {
		            	solicitudCcaa.setComunidades(solicitudCccaAuxiliarBean.getComunidad());
		                solicitudCcaa.setProvincia(solicitudCccaAuxiliarBean.getProvincia());
		                solicitudCcaa.setTituloFamnumerosa(solicitudCccaAuxiliarBean.getTituloFamnumerosa());
		            }
		            solicitudCcaaManager.almacenarSolicitudCcaa(solicitudCcaa);
	            }
	            
	            RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
	            registroSolicitudQuery.setSolicitudComun(solicitudComunQuery);
	            RegistroSolicitudBean registroSolicitudBean = registroSolicitudManager.buscarRegistroSolicitudByIdSolicitud(registroSolicitudQuery);  
	            
	            if (registroSolicitudBean != null && registroSolicitudBean.getId() != null && registroSolicitudBean.getId() > 0) {
	            	registroSolicitudBean.setSolicitud(solicitudComun);
	            	registroSolicitudBean.setFechaIntento(dateRegistroSolicitud);
	            	registroSolicitudBean.setResultado(Constantes.RESULTADO_OK);
	            	registroSolicitudBean.setFechaRegistro(dateRegistroSolicitud);
	            	registroSolicitudBean.setNumeroRegistro(numeroRegistroSolicitud);
		            registroSolicitudBean.setSolicitante(Constantes.CIUDADANO);
		                
		            registroSolicitudManager.modificarRegistroSolicitud(registroSolicitudBean);
	            } else if (!isMod) {
		            // volcado de datos tabla registro_solicitud
		            registroSolicitud.setSolicitudComun(solicitudComun);
		            registroSolicitud.setFechaIntento(dateRegistroSolicitud);
		            registroSolicitud.setResultado(Constantes.RESULTADO_OK);
		            registroSolicitud.setFechaRegistro(dateRegistroSolicitud);
		            registroSolicitud.setNumeroRegistro(numeroRegistroSolicitud);
		            registroSolicitud.setSolicitante(Constantes.CIUDADANO);
		                
		            registroSolicitudManager.almacenarRegistroSolicitud(registroSolicitud);
	            }
	            

	                	                
	            // volcado de datos para la tabla pago_solicitud
	            pagoSolicitud.setSolicitudComun(solicitudComun);
	            
	           
	            insertarRegistroCsvBaseDeDatos2(solicitudComunAuxiliarBean,pagoSolicitud);            
	            
	            pagoSolicitud.setNrc(nrc);
	            pagoSolicitudManager.almacenarPagoSolicitud(pagoSolicitud);
	                   
	            return Constantes.RESULTADO_OK;
	            
	         } else {
	        	 logger.info("Solicitud auxiliar no encontrada");
	        	 return Constantes.RESULTADO_ER;
	         }
                   
        } catch (ParseException e) {
        	logger.error(STRING_ERROR_INSERTAR_REGISTRO_CSV_BASEDEDATOS,e);
            return Constantes.RESULTADO_ER;
        } catch (HibernateException e) {
        	logger.error(STRING_ERROR_INSERTAR_REGISTRO_CSV_BASEDEDATOS,e);
        	return Constantes.RESULTADO_ER;        	
        } catch (NumberFormatException e) {
        	logger.error(STRING_ERROR_INSERTAR_REGISTRO_CSV_BASEDEDATOS,e);
        	return Constantes.RESULTADO_ER;        	
        } catch (Exception e) {
        	logger.error(STRING_ERROR_INSERTAR_REGISTRO_CSV_BASEDEDATOS,e);
        	return Constantes.RESULTADO_ER;        	
        } 
	}
	 
	/**
	 * Insertar registro csv base de datos 2.
	 *
	 * @param solicitudComunAuxiliarBean el solicitud comun auxiliar bean
	 * @param pagoSolicitud el pago solicitud
	 */
	public void insertarRegistroCsvBaseDeDatos2(SolicitudComunAuxiliarBean solicitudComunAuxiliarBean, PagoSolicitud pagoSolicitud) {
		
		Integer idTipoPagoSolicitud = 0;
        if (solicitudComunAuxiliarBean.getIdTipoPago()!=null) {
        	idTipoPagoSolicitud = Integer.valueOf(solicitudComunAuxiliarBean.getIdTipoPago());
        } else {
        	idTipoPagoSolicitud = Constantes.TIPO_PAGO_EFECTIVO_INTEGER;
        }
         
        
        TipoPagoQuery tipoPagoQuery = new TipoPagoQuery();
        tipoPagoQuery.setId(idTipoPagoSolicitud);
    	TipoPago tipoPagoSolicitud = tipoPagoManager.buscarTipoPagoByTipo(tipoPagoQuery);
       	pagoSolicitud.setTipo(tipoPagoSolicitud.getCodigo());
        pagoSolicitud.setImporte(Float.valueOf(solicitudComunAuxiliarBean.getImporte()));
        pagoSolicitud.setResultado(Constantes.REGISTRO_RESULTADO_OK);
        pagoSolicitud.setMotivoReduccionTasa(solicitudComunAuxiliarBean.getMotivoReduccionTasa());
        if (solicitudComunAuxiliarBean.getMotivoReduccionTasa() !=null &&  solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId() !=null) {
           	pagoSolicitud.setSolicitaReduccion(Constantes.REDUCCION_SI);
        } else {
           	pagoSolicitud.setSolicitaReduccion(Constantes.REDUCCION_NO);
        }
	}
        
    /**
     * Obtiene el url inicial.
     *
     * @return the urlInicial
     */
	public String getUrlInicial() {
		return urlInicial;
	}

	/**
	 * Establece el url inicial.
	 *
	 * @param urlInicial the urlInicial to set
	 */
	public void setUrlInicial(String urlInicial) {
		this.urlInicial = urlInicial;
	}

	/**
	 * Obtiene el separador.
	 *
	 * @return the separador
	 */
	public String getSeparador() {
		return separador;
	}

	/**
	 * Establece el separador.
	 *
	 * @param separador the separador to set
	 */
	public void setSeparador(String separador) {
		this.separador = separador;
	}

	/**
	 * Obtiene el url final.
	 *
	 * @return the urlFinal
	 */
	public String getUrlFinal() {
		return urlFinal;
	}

	/**
	 * Establece el url final.
	 *
	 * @param urlFinal the urlFinal to set
	 */
	public void setUrlFinal(String urlFinal) {
		this.urlFinal = urlFinal;
	}

	/**
	 * Obtiene el solicitud comun auxiliar manager.
	 *
	 * @return the solicitudComunAuxiliarManager
	 */
	public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
		return solicitudComunAuxiliarManager;
	}

	/**
	 * Establece el solicitud comun auxiliar manager.
	 *
	 * @param solicitudComunAuxiliarManager the solicitudComunAuxiliarManager to set
	 */
	public void setSolicitudComunAuxiliarManager(SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return the solicitudesManager
	 */
	public SolicitudesManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager the solicitudesManager to set
	 */
	public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el solicitud ccaa auxiliar manager.
	 *
	 * @return the solicitudCcaaAuxiliarManager
	 */
	public SolicitudCcaaAuxiliarManager getSolicitudCcaaAuxiliarManager() {
		return solicitudCcaaAuxiliarManager;
	}

	/**
	 * Establece el solicitud ccaa auxiliar manager.
	 *
	 * @param solicitudCcaaAuxiliarManager the solicitudCcaaAuxiliarManager to set
	 */
	public void setSolicitudCcaaAuxiliarManager(SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager) {
		this.solicitudCcaaAuxiliarManager = solicitudCcaaAuxiliarManager;
	}

	/**
	 * Obtiene el solicitud ccaa manager.
	 *
	 * @return the solicitudCcaaManager
	 */
	public SolicitudCcaaManager getSolicitudCcaaManager() {
		return solicitudCcaaManager;
	}

	/**
	 * Establece el solicitud ccaa manager.
	 *
	 * @param solicitudCcaaManager the solicitudCcaaManager to set
	 */
	public void setSolicitudCcaaManager(SolicitudCcaaManager solicitudCcaaManager) {
		this.solicitudCcaaManager = solicitudCcaaManager;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return the pagoSolicitudManager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager the pagoSolicitudManager to set
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return the registroSolicitudManager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager the registroSolicitudManager to set
	 */
	public void setRegistroSolicitudManager(RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	/**
	 * Obtiene el estado solicitud manager.
	 *
	 * @return the estadoSolicitudManager
	 */
	public EstadoSolicitudManager getEstadoSolicitudManager() {
		return estadoSolicitudManager;
	}

	/**
	 * Establece el estado solicitud manager.
	 *
	 * @param estadoSolicitudManager the estadoSolicitudManager to set
	 */
	public void setEstadoSolicitudManager(EstadoSolicitudManager estadoSolicitudManager) {
		this.estadoSolicitudManager = estadoSolicitudManager;
	}

	/**
	 * Obtiene el tipo solicitud manager.
	 *
	 * @return the tipoSolicitudManager
	 */
	public TipoSolicitudManager getTipoSolicitudManager() {
		return tipoSolicitudManager;
	}

	/**
	 * Establece el tipo solicitud manager.
	 *
	 * @param tipoSolicitudManager the tipoSolicitudManager to set
	 */
	public void setTipoSolicitudManager(TipoSolicitudManager tipoSolicitudManager) {
		this.tipoSolicitudManager = tipoSolicitudManager;
	}

	/**
	 * Obtiene el modelos manager.
	 *
	 * @return the modelosManager
	 */
	public ModeloManager getModelosManager() {
		return modelosManager;
	}

	/**
	 * Establece el modelos manager.
	 *
	 * @param modelosManager the modelosManager to set
	 */
	public void setModelosManager(ModeloManager modelosManager) {
		this.modelosManager = modelosManager;
	}

	/**
	 * Obtiene el tipo pago manager.
	 *
	 * @return the tipoPagoManager
	 */
	public TipoPagoManager getTipoPagoManager() {
		return tipoPagoManager;
	}

	/**
	 * Establece el tipo pago manager.
	 *
	 * @param tipoPagoManager the tipoPagoManager to set
	 */
	public void setTipoPagoManager(TipoPagoManager tipoPagoManager) {
		this.tipoPagoManager = tipoPagoManager;
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
	 * Obtiene el url inicial struts.
	 *
	 * @return el url inicial struts
	 */
	public String getUrlInicialStruts() {
		return urlInicialStruts;
	}

	/**
	 * Establece el url inicial struts.
	 *
	 * @param urlInicialStruts el nuevo url inicial struts
	 */
	public void setUrlInicialStruts(String urlInicialStruts) {
		this.urlInicialStruts = urlInicialStruts;
	}

	/**
	 * Obtiene el url final struts.
	 *
	 * @return el url final struts
	 */
	public String getUrlFinalStruts() {
		return urlFinalStruts;
	}

	/**
	 * Establece el url final struts.
	 *
	 * @param urlFinalStruts el nuevo url final struts
	 */
	public void setUrlFinalStruts(String urlFinalStruts) {
		this.urlFinalStruts = urlFinalStruts;
	}

}
