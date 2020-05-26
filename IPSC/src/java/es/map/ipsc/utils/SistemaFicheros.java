package es.map.ipsc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ipsc.bean.DocumentoBean;

/**
 * El Class SistemaFicheros.
 *
 * @author everis
 */
public class SistemaFicheros {

	/** La constante logger. */
	// Sistema de ficheros
	private static final Logger logger = Logger.getLogger(SistemaFicheros.class);
	

	/** el url inicial. */
	private String urlInicial;
	
	/** el separador. */
	private String separador;
	
	/** el url final. */
	private String urlFinal;

	
	/** el properties. */
	private static Properties properties;
	
	/**
	 * Crea una nueva sistema ficheros.
	 */
	public SistemaFicheros() {
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		this.urlInicial = properties.getProperty("sistemaficheros.url.escritura");
		this.urlFinal = properties.getProperty("sistemaficheros.url.final");
		this.separador = File.separator;
	}

	/**
	 * Insertar contenido.
	 *
	 * @param documento el documento
	 * @return verdadero, si tiene exito
	 * @throws Exception el exception
	 */
	public boolean insertarContenido(DocumentoBean documento) throws Exception {
		
		logger.info("insertarContenido - start");
        try {        	
        	StringBuffer sbRuta = new StringBuffer();
        	sbRuta.append(urlInicial).append(documento.getUbicacion())
        		.append(separador).append(documento.getNombreAlfresco());
        	
        	File salida = new File(sbRuta.toString());
        	
        	if (!salida.getParentFile().exists()) {
        		salida.getParentFile().mkdirs();
        	}
			logger.info("guardarDocumento - ruta de carpeta IPS: " + salida.getAbsolutePath());

	        try(OutputStream outputStream = new FileOutputStream (salida))
	        {
	        	outputStream.write(documento.getContenidoDocumento());
	        }
		    
		    if(!salida.getAbsoluteFile().exists()){
				logger.info("insertarContenido - Error al crear el objeto: " + salida.getAbsoluteFile());
				logger.error("insertarContenido - Error al crear el objeto" +salida.getAbsoluteFile());
				throw new ApplicationException("insertarContenido - Error al crear el objeto: " + salida.getAbsoluteFile());
		    }
			
		    logger.info("insertarContenido - end");
	        return true;
		} catch (IOException e) {
			logger.error("insertarContenido - Error: ",e);
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
        	// 1. Busqueda en el directorio definitvo.
        	StringBuffer sbRuta = new StringBuffer();
        	sbRuta.append(urlFinal).append(documento.getUbicacion()).append(separador).append(documento.getNombreAlfresco());
        	File salida = new File(sbRuta.toString());	

	    	if(salida.exists()){	        	   		
	        	byte fileContent[] = FileUtils.readFileToByteArray(salida);	        	
	        	return fileContent;
	        	
	    	}else{
	    		// 2. Busqueda en directorio de Lectura-Escritura.
	    		sbRuta = new StringBuffer();
	        	sbRuta.append(urlInicial).append(documento.getUbicacion()).append(separador).append(documento.getNombreAlfresco());
	        	salida = new File(sbRuta.toString());
	        	if(salida.exists()){	        	   		
		        	byte fileContent[] = FileUtils.readFileToByteArray(salida);	        	
		        	return fileContent;
		    	}
	    	}
	    	
			logger.error("obtenerContenido - Error: no se ha encontrado el documento: " + salida.getAbsolutePath());
			
			return null;
			
	    } catch (Exception e) {
			logger.error("obtenerContenido - Error: ",e);
			return null;
		}
	}
	
	/**
	 * Método que copia un fichero a su ubicación definitva.
	 *
	 * @param rutaFichero el ruta fichero
	 * @param ficheroOrigen el fichero origen
	 * @return verdadero, si tiene exito
	 */
	public boolean copiarFichero( String rutaFichero, File ficheroOrigen){

		try {
			Util.verificarDirectorio(urlFinal+rutaFichero);

			File destino = new File(urlFinal+rutaFichero);
			FileUtils.copyFileToDirectory(ficheroOrigen, destino);
			
		} catch (IOException e) {
			logger.error("Error copiando fichero",e);
			return false;
		}
		return true;
	}
}
