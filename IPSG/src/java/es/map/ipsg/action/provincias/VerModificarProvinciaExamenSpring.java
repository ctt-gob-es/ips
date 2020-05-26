package es.map.ipsg.action.provincias;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.form.ModificarProvinciasExamenForm;
import es.map.ipsg.manager.ProvinciaExamenManager;

/**
 * El Class VerModificarProvinciaExamenSpring.
 */
public class VerModificarProvinciaExamenSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarProvinciaExamenSpring.class);
	
	/** el provincia examen manager. */
	//Declaracion de manager
	private ProvinciaExamenManager provinciaExamenManager;
	
	
	/**
	 * Crea una nueva ver modificar provincia examen spring.
	 */
	public VerModificarProvinciaExamenSpring() {
		try {
			setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarProvinciaExamenSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_provincia = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.provinciaExamen");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_provincia);
		
		getLogger().debug("VerModificarProvinciaExamenSpring -start");
		String idProvincia = this.getRequest().getParameter("registro");
		ModificarProvinciasExamenForm formulario = (ModificarProvinciasExamenForm) form;
		
		try{
			List<ProvinciaExamenBean> provincias = null;
			ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
			provinciaExamenQuery.setId(Integer.valueOf(idProvincia));
			
			provincias = provinciaExamenManager.buscarProvinciaExamenCombo(provinciaExamenQuery);
			logger.info(formulario.getSubmit());
			if(!"Modificar".equals(formulario.getSubmit())){
				ProvinciaExamenBean provinciaExamenBean = provincias.get(0);
				formulario.setId(provinciaExamenBean.getId());
				formulario.setCodigo(provinciaExamenBean.getCodigo());
				formulario.setDescripcion(provinciaExamenBean.getDescripcion());
				formulario.setEstado(provinciaExamenBean.getEstado());
				if(provinciaExamenBean.getVisibilidad() != null)
				{	
					formulario.setVisibilidad(provinciaExamenBean.getVisibilidad());
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
			logger.error("Error VerModificarProvinciaExamenSpring - doExecute:",e);
			return "nosuccess";
		}
		
	}

	/**
	 * Obtiene el provincia examen manager.
	 *
	 * @return el provincia examen manager
	 */
	public ProvinciaExamenManager getProvinciaExamenManager() {
		return provinciaExamenManager;
	}

	/**
	 * Establece el provincia examen manager.
	 *
	 * @param provinciaExamenManager el nuevo provincia examen manager
	 */
	public void setProvinciaExamenManager(ProvinciaExamenManager provinciaExamenManager) {
		this.provinciaExamenManager = provinciaExamenManager;
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
