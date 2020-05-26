package es.map.ipsg.action.entidadFinanciera;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.TipoPagoQuery;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.TipoPagoBean;
import es.map.ipsg.form.ModificarEntidadFinancieraForm;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TipoPagoManager;

/**
 * El Class VerModificarEntidadFinancieraSpring.
 */
public class VerModificarEntidadFinancieraSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarEntidadFinancieraSpring.class);

	/** el entidad financiera manager. */
	//Declaracion de los manager
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva ver modificar entidad financiera spring.
	 */
	public VerModificarEntidadFinancieraSpring() {
		try {
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarEntidadFinancieraSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_EntidadFinanciera = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.entidadFinanciera");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_EntidadFinanciera);

		getLogger().debug("VerModificarEntidadFinancieraSpring -start");
	try{
		// Cargamos el combo del tipo de pago
		TipoPagoQuery  tipoPagoQuery = new TipoPagoQuery();
		List<TipoPagoBean> lTipoPagoBean;
		lTipoPagoBean = tipoPagoManager.buscarTipoPagoCombo(tipoPagoQuery);
				
		// Cargamos los datos del formulario
		List<EntidadFinancieraBean> listEntidadFinancieraBean = null;
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		
		// Buscamos la entidad financiera según su id
		String idEntidadFinanciera = this.getRequest().getParameter("id");
		entidadFinancieraQuery.setId(Integer.valueOf(idEntidadFinanciera));		
		listEntidadFinancieraBean = entidadFinancieraManager.buscarEntidadFinancieraCombo(entidadFinancieraQuery);
		
		// Obtenemos la entidad financiera buscada
		EntidadFinancieraBean entidadFinancieraBean = listEntidadFinancieraBean.get(0);
		
		// Cargamos los valores en el formulario que se van a mostrar en la página
		ModificarEntidadFinancieraForm formulario = (ModificarEntidadFinancieraForm) form;
		
		formulario.setId(entidadFinancieraBean.getId());
		formulario.setCodigo(entidadFinancieraBean.getCodigo());
		formulario.setDescripcion(entidadFinancieraBean.getDescripcion());
		formulario.setActualizada(entidadFinancieraBean.getActualizada());
		formulario.setIdTipoPago(entidadFinancieraBean.getIdTipoPago());
		formulario.setDesTipoPago(entidadFinancieraBean.getDesTipoPago());
		formulario.setEstado(entidadFinancieraBean.getEstado());
		
		// Devolvemos el valor del combo
		setRequestAttribute("tipoPago", lTipoPagoBean);
		
		getLogger().debug("VerModificarEntidadFinancieraSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error VerModificarEntidadFinancieraSpring() - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return "success";
	}
	
	/**
	 * Obtiene el entidad financiera manager.
	 *
	 * @return el entidad financiera manager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager el nuevo entidad financiera manager
	 */
	public void setEntidadFinancieraManager(EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
	}
	
	/**
	 * Obtiene el tipo pago manager.
	 *
	 * @return el tipo pago manager
	 */
	public TipoPagoManager getTipoPagoManager() {
		return tipoPagoManager;
	}

	/**
	 * Establece el tipo pago manager.
	 *
	 * @param tipoPagoManager el nuevo tipo pago manager
	 */
	public void setTipoPagoManager(TipoPagoManager tipoPagoManager) {
		this.tipoPagoManager = tipoPagoManager;
	}	

	/**
	 * Obtiene el log generico manager.
	 *
	 * @return el log generico manager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager el nuevo log generico manager
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
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
