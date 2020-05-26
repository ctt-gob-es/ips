package es.map.ipsg.util;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;

/**
 * El Class Util.
 */
public class Util {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(Util.class);
	
	/** el instance. */
	private static Util instance;
	
	/** el properties. */
	private static Properties properties;

	/**
	 * Obtiene una instancia del objeto actual.
	 * @return la instancia del objeto actual.
	 */
	public static synchronized Util getInstance() {
		if (instance == null) {
			instance = new Util();
		}
		return instance;

	}
	
	/**
	 * Método que comprueba la existencia de un directorio.
	 * En caso negativo, lo crea.
	 *
	 * @param ruta el ruta
	 */
	public static void verificarDirectorio (String ruta){
		try {
			File carpeta = new File(ruta);
			if (!carpeta.exists()) {
				carpeta.mkdir();
			}
		} catch (Exception e) {
			logger.error("Error creando directorio: ",e);
		}
	}
	
	/**
	 * Método que evalua si un documento de identidad es NIF o NIE.
	 *
	 * @param numDoc el num doc
	 * @return true si es NIE.
	 */
	public static boolean esNie (String numDoc){
		
		return numDoc.charAt(0)=='X' || numDoc.charAt(0)=='Y' || numDoc.charAt(0)=='Z';
	}

 
	/**
	 * Comprobar appellido compuesto.
	 *
	 * @param nombre el nombre
	 * @param primerApellido el primer apellido
	 * @return el string[]
	 */
	public static String[] comprobarAppellidoCompuesto (String nombre, String primerApellido){
		
		String[] nombreyapellido= new String[2];
		String nombreStr=nombre;
		String primerApellidoStr="";
		int i = 0;
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		String[] splitApellidosAux = primerApellido.replaceAll("\\s+", " ").split(" ");
		if(splitApellidosAux.length>1){
			String splitApellidosProp = " " +properties.getProperty("apellido.compuesto").replaceAll(";", " ; ")+ " ";
			for (String componentes : splitApellidosAux) {
				 String apellidosSeparados = " " +componentes+" ";
				if(splitApellidosProp.contains(apellidosSeparados) && i==0){
						nombreStr =  nombreStr+ " " +apellidosSeparados;
				}else{
						primerApellidoStr = primerApellidoStr+ " " +apellidosSeparados;
						i=1;
				}
			}
		}else{
			primerApellidoStr = primerApellido;
		}
			
		 nombreyapellido[0]=nombreStr.replaceAll("\\s+", " ").trim();
		 nombreyapellido[1]=primerApellidoStr.replaceAll("\\s+", " ").trim();
		 return nombreyapellido;
		
	}
}
