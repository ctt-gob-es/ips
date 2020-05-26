package es.map.ipsg.util;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;

import es.map.ipsg.bean.CiudadanoBean;
import es.mpt.dsic.inside.ws.service.ApplicationLogin;
import es.mpt.dsic.inside.ws.service.CSVInfoAmbito;
import es.mpt.dsic.inside.ws.service.CopiaInfo;
import es.mpt.dsic.inside.ws.service.EeUtilService;


/**
 * El Class CSVInside.
 */
public class CSVInside {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CSVInside.class);
	
	/** La constante STRING_ERROR_GENERACION_CSV. */
	private static final String STRING_ERROR_GENERACION_CSV = "Error en la generacion del CSV";
	
	/** La constante STRING_ERROR_GENERACION_JUSTIFICANTE. */
	private static final String STRING_ERROR_GENERACION_JUSTIFICANTE = "Error en la generacion del Justificante ";
	
	/**
	 * Crea una nueva CSV inside.
	 */
	private CSVInside() {
	    throw new IllegalAccessError("Utility class");
	}

	/**
	 * Generar codigo CSV.
	 *
	 * @param byteJutificante el byte jutificante
	 * @param csvProperties el csv properties
	 * @param csvEeUtilService el csv ee util service
	 * @return el string
	 */
	public static String generarCodigoCSV(byte[] byteJutificante, Properties csvProperties, EeUtilService csvEeUtilService) {
		
		String codigoCSV  = "";
		ApplicationLogin login = new ApplicationLogin();
		login.setIdaplicacion(csvProperties.getProperty("ws.csv_creator.idAplicacion"));
		login.setPassword(csvProperties.getProperty("ws.csv_creator.password"));
			
		CSVInfoAmbito csvInfoAmbito = new CSVInfoAmbito();
		byte[] contenidoFichero = byteJutificante;
		csvInfoAmbito.setContenido(contenidoFichero);
		csvInfoAmbito.setMime(csvProperties.getProperty("ws.csv.mime"));
		csvInfoAmbito.setAmbito(csvProperties.getProperty("ws.csv.ambito"));
				
		try {
			logger.info("Invoking generandoCSV...");
			codigoCSV = csvEeUtilService.generarCSVAmbito(login, csvInfoAmbito);
			logger.info("Salida de GenerarCSVAmbito: " + codigoCSV);
				
		} catch (RemoteException re) {
			logger.info(STRING_ERROR_GENERACION_CSV,re);
			logger.error(STRING_ERROR_GENERACION_CSV,re);
		
		} catch (WebServiceException we) {
			logger.info(STRING_ERROR_GENERACION_CSV,we);
			logger.error(STRING_ERROR_GENERACION_CSV,we);
		}
		return codigoCSV;
	}
	
	
	/**
	 * Generar copia CSV.
	 *
	 * @param bytePDF el byte PDF
	 * @param usuarioActual el usuario actual
	 * @param csvProperties el csv properties
	 * @param csvEeUtilService el csv ee util service
	 * @param codigoCSV el codigo CSV
	 * @param fechaRegistro el fecha registro
	 * @return el byte[]
	 */
	public static byte[] generarCopiaCSV(byte[] bytePDF, CiudadanoBean usuarioActual, Properties csvProperties,
			EeUtilService csvEeUtilService, String codigoCSV, Date fechaRegistro) {
		
		CopiaInfo copiaPDFconCSVIncrustado = null;
		byte[] ficheroConCSV = null;
		
		// Formato del PDF
		Boolean simpleFormat = false;
		if (csvProperties.getProperty("ws.csv.formato")!=null && String.valueOf(Boolean.TRUE).equals(csvProperties.getProperty("ws.csv.formato"))){
			simpleFormat = true; 
		}
	        
		ApplicationLogin login = new ApplicationLogin();
		login.setIdaplicacion(csvProperties.getProperty("ws.csv_creator.idAplicacion"));
		login.setPassword(csvProperties.getProperty("ws.csv_creator.password"));
		
		CopiaInfo copiaInfo = new CopiaInfo();      
	        
	    // Contenido
		es.mpt.dsic.inside.ws.service.ContenidoInfo contenido = new es.mpt.dsic.inside.ws.service.ContenidoInfo();
		contenido.setContenido(bytePDF);
	    contenido.setTipoMIME(csvProperties.getProperty("ws.csv.mime"));
		copiaInfo.setContenido(contenido);
			
	    // Informacion a mostrar
		copiaInfo.setCsv(codigoCSV);
			
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fechaGenFormateada = df.format(fechaRegistro);
			
		copiaInfo.setFecha(fechaGenFormateada);
		copiaInfo.setIdAplicacion(csvProperties.getProperty("ws.csv.aplicacion"));
		copiaInfo.setUrlSede(csvProperties.getProperty("ws.csv.urlSede"));
		copiaInfo.setExpediente(usuarioActual.getNombre() + " " + usuarioActual.getPrimerApellido() + " " + usuarioActual.getSegundoApellido());
		copiaInfo.setNif(usuarioActual.getNif());
			
		// Textos
		copiaInfo.setTituloAplicacion(csvProperties.getProperty("ws.csv.tituloAplicacion"));
		copiaInfo.setTituloExpediente(csvProperties.getProperty("ws.csv.tituloExpediente"));
		copiaInfo.setTituloFecha(csvProperties.getProperty("ws.csv.tituloFecha"));
		copiaInfo.setTituloCSV(csvProperties.getProperty("ws.csv.tituloCSV"));
		copiaInfo.setTituloNif(csvProperties.getProperty("ws.csv.tituloNIF"));
		copiaInfo.setTituloURL(csvProperties.getProperty("ws.csv.tituloURL"));
		copiaInfo.setLateral(csvProperties.getProperty("ws.csv.lateral"));
									
		try {
			logger.info("Invoking generarCopia...");
			copiaPDFconCSVIncrustado = csvEeUtilService.generarCopia(login, copiaInfo, simpleFormat);
						
			if(copiaPDFconCSVIncrustado != null && copiaPDFconCSVIncrustado.getContenido() != null && copiaPDFconCSVIncrustado.getContenido().getContenido() != null){
	        	ficheroConCSV = copiaPDFconCSVIncrustado.getContenido().getContenido();
	        }
			
		} catch (WebServiceException we) {
			logger.info(STRING_ERROR_GENERACION_JUSTIFICANTE + codigoCSV + " : ",we);
			logger.error(STRING_ERROR_GENERACION_JUSTIFICANTE + codigoCSV + " : ",we);
		} catch (Exception e) {
			logger.info(STRING_ERROR_GENERACION_JUSTIFICANTE + codigoCSV + " : ",e);
			logger.error(STRING_ERROR_GENERACION_JUSTIFICANTE + codigoCSV + " : ",e);
		} 
		logger.info("Salida de GenerarCopiaCSV");
		return ficheroConCSV;
	}

}
