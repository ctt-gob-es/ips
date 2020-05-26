package es.map.ipsc.utils;

import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

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
	 * Obtiene el tipo de documento 'MIME' a partir del archivo.
	 *
	 * @param datos el datos
	 * @return el tipo 'MIME' del fichero
	 * @throws Exception el exception
	 */
	@SuppressWarnings("static-access")
	public String getMime (byte[] datos) throws Exception{
		Magic parser = new Magic() ;
		MagicMatch match;
		String mime = null;
		try {
			match = parser.getMagicMatch(datos);
			mime = match.getMimeType();
		} catch(Exception e){
			logger.error("Error  getMime: ",e);
		}

		return mime;
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
	 * Método que sustituye caracteres especiales.
	 *
	 * @param sNomFichero el s nom fichero
	 * @return cadenaLimpia
	 */
	public static String transformaNomFichOriginal (String sNomFichero)
	{

		String sNuevoNomFich = "";
		int i = 0;

		while (i < sNomFichero.length()){

			//Valores ASCII no permitidos.
			if ((int)sNomFichero.charAt(i)<=32 || (int)sNomFichero.charAt(i)>122 || 
					(int)sNomFichero.charAt(i)==94 || (int)sNomFichero.charAt(i)==96 ||
					(int)sNomFichero.charAt(i)==37)
			{
				sNuevoNomFich = sNuevoNomFich + "_";
				
			}else{
				sNuevoNomFich = sNuevoNomFich + sNomFichero.charAt(i);
			}
				
			i++;
		}
		
		return sNuevoNomFich;
	}
	
	/**
	 * Comprobar appellido compuesto.
	 *
	 * @param nombre el nombre
	 * @param primerApellido el primer apellido
	 * @return el string[]
	 */
	public static String[] comprobarAppellidoCompuesto (String nombre, String primerApellido){
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		String[] nombreyapellido= new String[2];
		String nombreStr=nombre;
		String primerApellidoStr="";
		int i = 0;
		String[] splitApellidosAux = primerApellido.replaceAll("\\s+", " ").split(" ");
		if(splitApellidosAux.length>1){
			String splitApellidosProp = " " + properties.getProperty("apellido.compuesto").replaceAll(";", " ; ") + " ";		
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
