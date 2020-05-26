package es.map.ipsg.action.ministerio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.MinisterioQuery;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.form.MinisterioForm;
import es.map.ipsg.manager.MinisterioManager;

/**
 * El Class ModificarMinisterioSpring.
 */
public class ModificarMinisterioSpring extends AbstractSpring {
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarMinisterioSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/**
	 * Crea una nueva modificar ministerio spring.
	 */
	public ModificarMinisterioSpring() {
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarMinisterioSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_ministerio = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.ministerio");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ministerio);
		
		getLogger().debug("ModificarMinisterioSpring -start");
		MinisterioForm formulario;
		formulario = (MinisterioForm) form;
		try{
			String id = this.getRequest().getParameter("id").trim();
			
			MinisterioQuery ministerioQuery = new MinisterioQuery();
			int codMinisterio = Integer.parseInt(id);
			MinisterioBean ministerioBean = new MinisterioBean();
			logger.info(formulario.getSubmit());
			if(!"Modificar".equals(formulario.getSubmit())){
				ministerioQuery.setId(codMinisterio);		
				ministerioBean = ministerioManager.buscarMinisterio(ministerioQuery);
			}else{
				ministerioBean.setCodigo(formulario.getCodigo());
				ministerioBean.setDescripcion(formulario.getDescripcion());
				ministerioBean.setId(formulario.getId());
				ministerioBean.setSiglas(formulario.getSiglas());
				ministerioBean.setUrl(formulario.getUrl());
				ministerioBean.setIdMinisterioPrevio(formulario.getIdMinisterioPrevio());
				if(formulario.getVisibilidad() != null)
				{	
					ministerioBean.setVisibilidad(formulario.getVisibilidad());
				}
				else
				{	
					ministerioBean.setVisibilidad(false);
				}
					
			}
			
			if(ministerioBean.getFechaSustitucion()!=null && !"".equals(ministerioBean.getFechaSustitucion().toString())){
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaS = ministerioBean.getFechaSustitucion();
				
				String fechaSust = formatoFecha.format(fechaS);
				
				formulario.setFechaSustitucion(fechaSust);
			}
			formulario.setIdMinisterioPrevio(ministerioBean.getIdMinisterioPrevio() != null?ministerioBean.getIdMinisterioPrevio():0);
			formulario.setDescripcion(!StringUtils.isEmpty(ministerioBean.getDescripcion())?ministerioBean.getDescripcion():"");
			formulario.setCodigo(!StringUtils.isEmpty(ministerioBean.getCodigo())?ministerioBean.getCodigo():"");
			formulario.setUrl(!StringUtils.isEmpty(ministerioBean.getUrl())?ministerioBean.getUrl():"");
			formulario.setSiglas(!StringUtils.isEmpty(ministerioBean.getSiglas())?ministerioBean.getSiglas():"");
			formulario.setVisibilidad(ministerioBean.getVisibilidad()?true:false);
			
			setRequestAttribute("ministerio", ministerioBean);
			List<MinisterioBean> ministerioLista = ministerioManager.buscarMinisterioComboTodos();
			
			this.setRequestAttribute("ministerioLista", ministerioLista);
			getLogger().debug("ModificarMinisterioSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarMinisterioSpring - doExecute:",e);
			return "nosuccess";
		}
	}

	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return el ministerio manager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
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