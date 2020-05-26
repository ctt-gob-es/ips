package es.map.ipsc.utils;

import java.util.Hashtable;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import es.map.sgtic.tasas.gestion.GestorNumeroJustificanteEJB;
import es.map.sgtic.tasas.gestion.GestorNumeroJustificanteEJBHome;
import es.map.ips.common.spring.ApplicationContextProvider;

/**
 * El Class NumeroSolicitud.
 */
public class NumeroSolicitud {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(NumeroSolicitud.class);
	
	/** el properties. */
	private Properties properties;
	
	/**
	 * El metodo principal.
	 *
	 * @param args los argumentos
	 */
	public static void main(String[] args){
		logger.debug("Numero Justificante: "+new NumeroSolicitud().obtenerNumeroJustificanteEJB());
	}
	
	/**
	 * Obtiene el bean.
	 *
	 * @param name el name
	 * @return el bean
	 */
	private Object getBean(String name) {
		return ApplicationContextProvider.getInstance().getBean(name);
	}
	
	/**
	 * Obtener numero justificante EJB.
	 *
	 * @return el string
	 */
	public String obtenerNumeroJustificanteEJB(){
		String nJustificante = null;

		try{
			Hashtable props = new Hashtable();
			
			properties = (Properties) getBean("propertiesBean");
	
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,properties.getProperty("initial.context.factory"));
								
			String cadenaConexion  = properties.getProperty("url.context.provider");
								
			props.put(InitialContext.PROVIDER_URL, cadenaConexion); 
	
			InitialContext initialContext = new InitialContext(props);
	
			GestorNumeroJustificanteEJBHome ejbHome = (GestorNumeroJustificanteEJBHome) initialContext.lookup(es.map.sgtic.tasas.gestion.GestorNumeroJustificanteEJBHome.JNDI_NAME);
	
			GestorNumeroJustificanteEJB accesoEJB = ejbHome.create();
	
			String idModelo = "790";
			String idTasa = "001"; 
			
			nJustificante = accesoEJB.getNumJustificante(idModelo,idTasa);

		}catch(Exception ex){
			logger.warn(ex.toString());
			logger.error("Error obtener Numero Justificante EJB: ",ex);
		}

		return nJustificante;
	}
	
	/**
	 * Obtener numero justificante local.
	 *
	 * @param contador el contador
	 * @param is007 el is 007
	 * @return el string
	 */
	public String obtenerNumeroJustificanteLocal(Integer contador, boolean is007){
		
		logger.debug("obtenerNumeroJustificanteLocal - INICIO");
		
		// si nos devuelve nulo, devolvemos nulo y nos vamos
		if (contador == null || contador == -1){
			return  null;
		}
		
		String idModelo = "790";
		String idTasa = "001";
		if(is007){
			idTasa="007";
		}
		
		
		//Montamos el numero de justificante
		String numJustificante = idModelo + idTasa;	
		
		// variable numérica de apoyo
		long auxJustificante = 0;

		auxJustificante = Long.parseLong(numJustificante) * 1000000;
		auxJustificante = auxJustificante + contador;
		
		// ahora calculamos el dígito de control
	    long res = auxJustificante % 7;
	    long digito = (res==0?0:7 - res);
	        
	    auxJustificante = (auxJustificante * 10) + digito;
				
		//Devolver el numero de justificante
	    return new Long(auxJustificante).toString();
		
	}
	
	/**
	 * Método que obtiene el nº de justificante adaptado a cualquier modelo.
	 *
	 * @param contador el contador
	 * @param modelo 79000x
	 * @return el string
	 */
	public String obtenerNumeroJustificanteLocal(Integer contador, String modelo){
		
		logger.debug("obtenerNumeroJustificanteLocal - INICIO");
		
		// si nos devuelve nulo, devolvemos nulo y nos vamos
		if (contador == null || contador == -1){
			return  null;
		}
		
		String idModelo = "790";
		String idTasa = modelo.substring(3);
		
		
		//Montamos el numero de justificante
		String numJustificante = idModelo + idTasa.trim();	
		
		// variable numérica de apoyo
		long auxJustificante = 0;

		auxJustificante = Long.parseLong(numJustificante) * 1000000;
		auxJustificante = auxJustificante + contador;
		
		// ahora calculamos el dígito de control
	    long res = auxJustificante % 7;
	    long digito = (res==0?0:7 - res);
	        
	    auxJustificante = (auxJustificante * 10) + digito;
				
		//Devolver el numero de justificante
	    return new Long(auxJustificante).toString();
		
	}
	
}
