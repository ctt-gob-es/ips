package es.map.ipsc.spring.solicitudes;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class MostrarAvisoPaginaSpring.
 */
public class MostrarAvisoPaginaSpring extends AbstractSecureSpring {

	

	
	/** La constante BUNDLE_MESSAGE. */
	private static final String BUNDLE_MESSAGE = "MessageResources";
	

	
	/**
	 * Crea una nueva mostrar aviso pagina spring.
	 */
	public MostrarAvisoPaginaSpring() { //Metodo vacio
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String accion = (String) this.getRequest().getParameter("accion");
		if(accion == null || accion.equals(""))
		{	
			return "nosuccess";
		}
		else
		{	
			if(accion.equals("Pago"))
			{
				setRequestAttribute("avisoPago", true);
			}	
			else if(accion.equals("Registro"))
			{
				setRequestAttribute("avisoRegistro", true);
			}	
				
			return "success";
		}	
	}


}
