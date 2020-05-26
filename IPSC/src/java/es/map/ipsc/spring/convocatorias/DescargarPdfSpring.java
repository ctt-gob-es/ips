package es.map.ipsc.spring.convocatorias;

import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;

/**
 * Esta clase originalmente mostraba la pagina para descargar el modelo 790007
 * de Justicia en diferentes idiomas.
 * Por peticion de Justicia se deshabilita y redirige, en caso de invocacion, a su Sede Electronica.
 * @author everis
 *
 */
public class DescargarPdfSpring extends AbstractSpring {
	

	
	/** el properties. */
	private static Properties properties;
	
	/**
	 * Crea una nueva descargar pdf spring.
	 */
	public DescargarPdfSpring() {
		properties = (Properties) getBean("propertiesBean");		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form)  {
		
		HttpServletResponse resp = this.getResponse();

		
		return null;	
	}
}
