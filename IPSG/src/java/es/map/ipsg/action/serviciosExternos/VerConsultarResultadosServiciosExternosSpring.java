/**
 * 
 */
package es.map.ipsg.action.serviciosExternos;

import java.util.ResourceBundle;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.form.ConsultaServiciosExternosForm;
import es.map.ipsg.form.LogOperacionesForm;

/**
 * El Class VerConsultarResultadosServiciosExternosSpring.
 *
 * @author jalonson
 */
public class VerConsultarResultadosServiciosExternosSpring extends AbstractSpring{

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/**
	 * VerLogOperacionesAction.
	 */
	public VerConsultarResultadosServiciosExternosSpring() { //Metodo vacio

	}

	/**
	 * doExecute de VerLogOperacionesAction.
	 *
	 * @param form SpringForm
	 * @return success String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {	
		
		String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_log = RESOURCE_BUNDLE.getString("field.menuLateral.consultas.logOperaciones");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_log);

		ConsultaServiciosExternosForm consultaServiciosExternosForm;
		
		consultaServiciosExternosForm = (ConsultaServiciosExternosForm) form;
		
		consultaServiciosExternosForm.setFechaDesde("");
		consultaServiciosExternosForm.setFechaHasta("");
		consultaServiciosExternosForm.setIdServicio("");
		consultaServiciosExternosForm.setResultado("");
		
		return "success";
	}
}
