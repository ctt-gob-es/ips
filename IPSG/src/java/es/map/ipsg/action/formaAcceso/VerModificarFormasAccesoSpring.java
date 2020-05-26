package es.map.ipsg.action.formaAcceso;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.TipoAccesoBean;
import es.map.ipsg.form.FormasAccesoForm;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.TipoAccesoManager;

/**
 * El Class VerModificarFormasAccesoSpring.
 */
public class VerModificarFormasAccesoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarFormasAccesoSpring.class);
	
	/** el formas acceso manager. */
	private FormaAccesoManager formasAccesoManager;
	
	/** el tipo acceso manager. */
	private TipoAccesoManager tipoAccesoManager;	
		
	/**
	 * Crea una nueva ver modificar formas acceso spring.
	 */
	public VerModificarFormasAccesoSpring() {
		try {
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			setFormasAccesoManager((FormaAccesoManager) getBean ("formasAccesoManager"));
			
		} catch (Exception e) {
			logger.error("Error VerModificarFormasAccesoSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {	
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_formaAcceso = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.formaAcceso");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_formaAcceso);
		
		String resultado;
	try{
		FormasAccesoForm formulario = (FormasAccesoForm) form;
		
		TipoAccesoQuery tipoAccesoQuery = new TipoAccesoQuery();
		List<TipoAccesoBean> tipoAcceso = tipoAccesoManager.buscarTipoAccesoCombo(tipoAccesoQuery);
		this.setRequestAttribute("tipoAcceso",tipoAcceso);
		
		String idFormaAcceso = this.getRequest().getParameter("id");
		
		if(idFormaAcceso!=null && formulario.getAccion() == null){			
			FormaAccesoBean formaAccesoBean = formasAccesoManager.obtenerFormaAcceso(Integer.valueOf(idFormaAcceso));			

			formulario.setIdTipoAcceso(formaAccesoBean.getTipoAcceso().getId());
			formulario.setCodigo(!StringUtils.isEmpty(formaAccesoBean.getCodigo())?formaAccesoBean.getCodigo():"");
			formulario.setDescripcion(!StringUtils.isEmpty(formaAccesoBean.getDescripcion())?formaAccesoBean.getDescripcion():"");
			if (formaAccesoBean.getTipoAcceso() != null && formaAccesoBean.getTipoAcceso().getId() != null) {
				formulario.setIdTipoAcceso(formaAccesoBean.getTipoAcceso().getId());
			}
			
			formulario.setVisibilidad((formaAccesoBean.getVisibilidad())?true:false);
			
			formaAccesoBean.setIdTipoAcceso(formaAccesoBean.getTipoAcceso().getId());
			this.setRequestAttribute("formasAcceso", formaAccesoBean);
			

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
	
	/**
	 * Resultado.
	 *
	 * @param formulario el formulario
	 * @return el string
	 */
	private String resultado(FormasAccesoForm formulario) {
		String resultado;
		
		if (formulario.getAccion() != null){				
			resultado = "success";
		}else{
			resultado = "error";
		}
		
		return resultado;
	}

	/**
	 * Obtiene el formas acceso manager.
	 *
	 * @return the formasAccesoManager
	 */
	public FormaAccesoManager getFormasAccesoManager() {
		return formasAccesoManager;
	}

	/**
	 * Establece el formas acceso manager.
	 *
	 * @param formasAccesoManager the formasAccesoManager to set
	 */
	public void setFormasAccesoManager(FormaAccesoManager formasAccesoManager) {
		this.formasAccesoManager = formasAccesoManager;
	}

	/**
	 * Obtiene el tipo acceso manager.
	 *
	 * @return the tipoAccesoManager
	 */
	public TipoAccesoManager getTipoAccesoManager() {
		return tipoAccesoManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAccesoManager the tipoAccesoManager to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAccesoManager) {
		this.tipoAccesoManager = tipoAccesoManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	
}
