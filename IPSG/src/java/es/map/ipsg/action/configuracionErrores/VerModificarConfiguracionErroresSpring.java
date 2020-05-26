package es.map.ipsg.action.configuracionErrores;

import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.ConfiguracionErroresBean;
import es.map.ipsg.form.ConfiguracionErroresForm;
import es.map.ipsg.manager.ConfiguracionErroresManager;

public class VerModificarConfiguracionErroresSpring extends AbstractSpring {

	private static final String MESSAGE_RESOURCE = "MessageResources";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	private static final Logger logger = Logger.getLogger(VerModificarConfiguracionErroresSpring.class);
	
	private ConfiguracionErroresManager configuracionErroresManager;
		
	public VerModificarConfiguracionErroresSpring() {
		try {
			setConfiguracionErroresManager((ConfiguracionErroresManager) getBean ("configuracionErroresManager"));
			
		} catch (Exception e) {
			logger.error("Error VerModificarConfiguracionErroresSpring:",e);
		}
	}

	@Override
	protected String doExecute(SpringForm form) throws Exception {	
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_configuracionErrores = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.configuracionErrores");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_configuracionErrores);
		
		String resultado;
	try{
		ConfiguracionErroresForm formulario = (ConfiguracionErroresForm) form;
		
		String idConfiguracionErrores = this.getRequest().getParameter("id");
		
		if(idConfiguracionErrores!=null && formulario.getAccion() == null){			
			ConfiguracionErroresBean configuracionErroresBean = configuracionErroresManager.obtenerConfiguracionError(Integer.valueOf(idConfiguracionErrores));			

			formulario.setDescripcion(!StringUtils.isEmpty(configuracionErroresBean.getDescripcion())?configuracionErroresBean.getDescripcion():"");
			formulario.setVisibilidad((configuracionErroresBean.getVisibilidad())?true:false);
			
			this.setRequestAttribute("configuracionErrores", configuracionErroresBean);
			resultado = "success";
		}else{
			resultado = resultado(formulario);			
		}
	
	}catch(Exception eGenerico){
		logger.error("Error VerModificarFormasAccesoSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}
	
	private String resultado(ConfiguracionErroresForm formulario) {
		String resultado;
		
		if (formulario.getAccion() != null){				
			resultado = "success";
		}else{
			resultado = "error";
		}
		
		return resultado;
	}

	/**
	 * @return the configuracionErroresManager
	 */
	public ConfiguracionErroresManager getConfiguracionErroresManager() {
		return configuracionErroresManager;
	}

	/**
	 * @param configuracionErroresManager the configuracionErroresManager to set
	 */
	public void setConfiguracionErroresManager(ConfiguracionErroresManager configuracionErroresManager) {
		this.configuracionErroresManager = configuracionErroresManager;
	}

	public static Logger getLogger() {
		return logger;
	}
	
	
}
