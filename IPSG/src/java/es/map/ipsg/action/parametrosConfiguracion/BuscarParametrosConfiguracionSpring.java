package es.map.ipsg.action.parametrosConfiguracion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ipsg.bean.ContadorNumSolicitudBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.form.ParametrosConfiguracionForm;
import es.map.ipsg.manager.ContadorNumSolicitudManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;

/**
 * El Class BuscarParametrosConfiguracionSpring.
 */
public class BuscarParametrosConfiguracionSpring extends AbstractSpring implements Comparator<ParametrosConfiguracionBean>{
	
	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarParametrosConfiguracionSpring.class);
	
	/** el parametro configuracion manager. */
	//Declaracion de los managers
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el contador num solicitud manager. */
	private ContadorNumSolicitudManager contadorNumSolicitudManager;

	/**
	 * Crea una nueva buscar parametros configuracion spring.
	 */
	public BuscarParametrosConfiguracionSpring() {
		try {			
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			setContadorNumSolicitudManager((ContadorNumSolicitudManager) getBean("contadorNumSolicitudManager"));
		} catch (Exception e) {
			logger.warn(e);	
			logger.error("Error BuscarParametrosConfiguracionSpring:",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_parametros = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.actualizarParametrosConfiguracion");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_parametros);
	try{
		ParametrosConfiguracionForm formulario;
		formulario = (ParametrosConfiguracionForm) form;
		
		String[] valoresCampo = formulario.getValorCampos();
		
		String validacion=  this.getRequest().getParameter("validacion");
		
		String contrasenia = "";
		
		ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
		parametrosConfiguracionQuery.addOrder(parametrosConfiguracionQuery.ID, OrderType.ASC);
		parametrosConfiguracionQuery.setVisible(true);
		ArrayList<ParametrosConfiguracionBean> parametrosConfiguracionVisibleCompleto = parametroConfiguracionManager.buscarParametrosConfiguracionAll(parametrosConfiguracionQuery);
		
		if(validacion!=null && validacion.equals("S") && parametrosConfiguracionVisibleCompleto!=null && parametrosConfiguracionVisibleCompleto.size()>0){
			for (int i = 0; i < valoresCampo.length; i++) {
				ParametrosConfiguracionBean parametro =parametrosConfiguracionVisibleCompleto.get(i);	
				parametro.setValorCampo(valoresCampo[i]);
				parametrosConfiguracionVisibleCompleto.set(i, parametro);	
			}
			
		}
		
		//Busco el contador
		ContadorNumSolicitudBean contadorNumSolicitudbean = contadorNumSolicitudManager.obtenerContadorNumSolicitud();
		
		setRequestAttribute("contador", String.valueOf(contadorNumSolicitudbean.getContador()));
		setRequestAttribute("parametros", parametrosConfiguracionVisibleCompleto);
	
	}catch(Exception eGenerico){
		logger.error("Error BuscarParametrosConfiguracionSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}
	
	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return the parametroConfiguracionManager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager the parametroConfiguracionManager to set
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el contador num solicitud manager.
	 *
	 * @return el contador num solicitud manager
	 */
	public ContadorNumSolicitudManager getContadorNumSolicitudManager() {
		return contadorNumSolicitudManager;
	}

	/**
	 * Establece el contador num solicitud manager.
	 *
	 * @param contadorNumSolicitudManager el nuevo contador num solicitud manager
	 */
	public void setContadorNumSolicitudManager(
			ContadorNumSolicitudManager contadorNumSolicitudManager) {
		this.contadorNumSolicitudManager = contadorNumSolicitudManager;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ParametrosConfiguracionBean o1,
			ParametrosConfiguracionBean o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
