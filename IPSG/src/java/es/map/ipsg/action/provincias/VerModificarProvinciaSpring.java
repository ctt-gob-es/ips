package es.map.ipsg.action.provincias;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ProvinciaQuery;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.form.ModificarProvinciasForm;
import es.map.ipsg.manager.ProvinciaManager;

/**
 * El Class VerModificarProvinciaSpring.
 */
public class VerModificarProvinciaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarProvinciaSpring.class);
	
	/** el provincia manager. */
	//Declaracion de manager
	private ProvinciaManager provinciaManager;
	
	
	/**
	 * Crea una nueva ver modificar provincia spring.
	 */
	public VerModificarProvinciaSpring() {
		try {
			setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarProvinciaSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_provincia = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.provincia");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_provincia);
		
		getLogger().debug("VerModificarProvinciaSpring -start");
		String idProvincia = this.getRequest().getParameter("registro");
		ModificarProvinciasForm formulario = (ModificarProvinciasForm) form;
		
		try{
			List<ProvinciaBean> provincias = null;
			ProvinciaQuery provinciaQuery = new ProvinciaQuery();
			provinciaQuery.setId(Integer.valueOf(idProvincia));
			
			provincias = provinciaManager.buscarProvinciaCombo(provinciaQuery);
			logger.info(formulario.getSubmit());
			if(!"Modificar".equals(formulario.getSubmit())){
				ProvinciaBean provinciaBean = provincias.get(0);
				formulario.setId(provinciaBean.getId());
				formulario.setCodigo(provinciaBean.getCodigo());
				formulario.setDescripcion(provinciaBean.getDescripcion());
				formulario.setEstado(provinciaBean.getEstado());
				if(provinciaBean.getVisibilidad() != null)
				{	
					formulario.setVisibilidad(provinciaBean.getVisibilidad());
				}
				else
				{
					formulario.setVisibilidad(false);
				}	
			}
			getLogger().debug("VerModificarProvinciaSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarProvinciaSpring - doExecute:",e);
			return "nosuccess";
		}
		
	}

	/**
	 * Obtiene el provincia manager.
	 *
	 * @return el provincia manager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager el nuevo provincia manager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
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
