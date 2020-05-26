package es.map.ipsg.action.entidadFinanciera;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.TipoPagoQuery;
import es.map.ipsg.bean.TipoPagoBean;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TipoPagoManager;

/**
 * El Class VerCrearEntidadFinancieraSpring.
 */
public class VerCrearEntidadFinancieraSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearEntidadFinancieraSpring.class);

	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva ver crear entidad financiera spring.
	 */
	public VerCrearEntidadFinancieraSpring() {
		try {
			setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerCrearEntidadFinancieraSpring() - doExecute:",e);
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

		//Cargamos el combo del tipo de pago
		TipoPagoQuery  tipoPagoQuery = new TipoPagoQuery();
		List<TipoPagoBean> lTipoPagoBean;
		lTipoPagoBean = tipoPagoManager.buscarTipoPagoCombo(tipoPagoQuery);
				
		// Devolvemos el valor del combo
		setRequestAttribute("tipoPago", lTipoPagoBean);
		
		return "success";
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
