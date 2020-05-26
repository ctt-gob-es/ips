package es.map.ipsg.service;



import java.util.Properties;

import es.map.ips.common.spring.ApplicationContextProvider;




/**
 * El Class AbstractAFirmaClienteWS.
 */
public abstract class AbstractAFirmaClienteWS {

	/** el prop. */
	protected Properties prop;
	
	
	/**
	 * Crea una nueva abstract A firma cliente WS.
	 */
	public AbstractAFirmaClienteWS() {
	    prop = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");

	}
	
	/**
	 * Obtiene el property.
	 *
	 * @param key el key
	 * @return el property
	 */
	protected String getProperty(String key) {
		return prop.getProperty(key);
	}
	
}
