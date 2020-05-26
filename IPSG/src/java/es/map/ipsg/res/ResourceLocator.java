package es.map.ipsg.res;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * El Class ResourceLocator.
 */
public class ResourceLocator {

	/** La constante s_logger. */
	private static final org.apache.log4j.Logger s_logger = 
         org.apache.log4j.Logger.getLogger(ResourceLocator.class);
	
	/** La constante STRING_RESOURCES. */
	private static final String STRING_RESOURCES = "resources/";
	
	/** el instance. */
	private static ResourceLocator instance = new ResourceLocator();

	/**
	 * Crea una nueva resource locator.
	 */
	private ResourceLocator() {

	}

	/**
	 * Obtiene la instancia unica de ResourceLocator.
	 *
	 * @return instancia unica de ResourceLocator
	 */
	public static ResourceLocator getInstance() {
		return instance;
	}
	
	/**
	 * Obtiene el resource as stream.
	 *
	 * @param p_absoluteName el p absolute name
	 * @return el resource as stream
	 */
	public InputStream getResourceAsStream(String p_absoluteName) {
		
		InputStream result = getClass().getResourceAsStream(STRING_RESOURCES + p_absoluteName);
		
		if (result == null) {
			result = getClass().getResourceAsStream("/" + p_absoluteName);
		}

		if (result == null) {
			result = getClass().getResourceAsStream("/resources/" + p_absoluteName);
		}

		if (result == null) {
			throw new RuntimeException("Could not find resource " + p_absoluteName);
		}

		return result;
		
	}

	/**
	 * Obtiene el jasper template.
	 *
	 * @param p_name el p name
	 * @return el jasper template
	 */
	public URL getJasperTemplate(String p_name) {
		return getResource(p_name, ResourceType.JASPER_TEMPLATE);
	}
	
	/**
	 * Obtiene el jasper image.
	 *
	 * @param p_name el p name
	 * @return el jasper image
	 */
	public URL getJasperImage(String p_name) {
		return getResource(p_name, ResourceType.JASPER_IMAGE);
	}
	
	/**
	 * Obtiene el velocity template.
	 *
	 * @param p_name el p name
	 * @return el velocity template
	 */
	public URL getVelocityTemplate(String p_name) {
		return getResource(p_name, ResourceType.VELOCITY_TEMPLATE);
	}	
	
	/**
	 * Obtiene el ws message template.
	 *
	 * @param p_name el p name
	 * @return el ws message template
	 */
	public URL getWsMessageTemplate(String p_name) {
		return getResource(p_name, ResourceType.WS_MESSAGE_TEMPLATE);
	}	
	
	/**
	 * Obtiene el schema template.
	 *
	 * @param p_name el p name
	 * @return el schema template
	 */
	public URL getSchemaTemplate(String p_name) {
		return getResource(p_name, ResourceType.SCHEMA_TEMPLATE);
	}

	/**
	 * Obtiene el jasper template as stream.
	 *
	 * @param p_name el p name
	 * @return el jasper template as stream
	 */
	public InputStream getJasperTemplateAsStream(String p_name) {
		return getResourceAsStream(p_name, ResourceType.JASPER_TEMPLATE);
	}
	
	/**
	 * Obtiene el jasper image as stream.
	 *
	 * @param p_name el p name
	 * @return el jasper image as stream
	 */
	public InputStream getJasperImageAsStream(String p_name) {
		return getResourceAsStream(p_name, ResourceType.JASPER_IMAGE);
	}
	
	/**
	 * Obtiene el velocity template as stream.
	 *
	 * @param p_name el p name
	 * @return el velocity template as stream
	 */
	public InputStream getVelocityTemplateAsStream(String p_name) {
		return getResourceAsStream(p_name, ResourceType.VELOCITY_TEMPLATE);
	}	
	
	/**
	 * Obtiene el ws message template as stream.
	 *
	 * @param p_name el p name
	 * @return el ws message template as stream
	 */
	public InputStream getWsMessageTemplateAsStream(String p_name) {
		return getResourceAsStream(p_name, ResourceType.WS_MESSAGE_TEMPLATE);
	}	
	
	/**
	 * Obtiene el schema template as stream.
	 *
	 * @param p_name el p name
	 * @return el schema template as stream
	 */
	public InputStream getSchemaTemplateAsStream(String p_name) {
		return getResourceAsStream(p_name, ResourceType.SCHEMA_TEMPLATE);
	}
	
	
	/**
	 * Obtiene el resource.
	 *
	 * @param resourceName el resource name
	 * @param type el type
	 * @return el resource
	 */
	private URL getResource(String resourceName, ResourceType type) {
		URL result = null;
		
		String path = dirByResourceType(type) + "/" + resourceName;
		
		// Caso exploded
		File f = new File(STRING_RESOURCES + path);
		if (f.exists()) {
			try {
				result = f.toURL();
			} catch (MalformedURLException e) {
				s_logger.error("No se pudo leer el fichero " + path, e);
			}
		}
		
		// Caso compacto
		if (result == null) {
			result = getClass().getResource("/" + path);
		}

		if (result == null) {
			throw new RuntimeException("No se pudo encontrar el archivo: " + path);
		}
		
		return result;
	}

	/**
	 * Obtiene el resource as stream.
	 *
	 * @param resourceName el resource name
	 * @param type el type
	 * @return el resource as stream
	 */
	private InputStream getResourceAsStream(String resourceName, ResourceType type) {

		String path = dirByResourceType(type) + "/" + resourceName;

		InputStream result = getClass().getResourceAsStream(STRING_RESOURCES + path);
		
		if (result == null) {
			result = getClass().getResourceAsStream("/" + path);
		}

		if (result == null) {
			result = getClass().getResourceAsStream("/resources/" + path);
		}

		if (result == null) {
			throw new RuntimeException("Could not find resource " + path);
		}

		return result;
	}
	
	
	/**
	 * Dir by resource type.
	 *
	 * @param type el type
	 * @return el string
	 */
	private String dirByResourceType(ResourceType type) {
		String result = null;
		switch (type) {
		case JASPER_TEMPLATE:
			result = "jasper";
			break;
		case JASPER_IMAGE:
			result = "jasper/img";
			break;
		case VELOCITY_TEMPLATE:
			result = "velocity";
			break;
		case WS_MESSAGE_TEMPLATE:
			result = "ws-msg";
			break;
		case SCHEMA_TEMPLATE:
			result = "schemas";
			break;
		default:
			break;
		}
		return result;
	}
	
}
