package es.map.ipsg.action.titulooficial;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.manager.TituloOficialManager;

/**
 * Acción VerCrearTituloOficialAction.
 *
 * @author amartinl
 */
public class VerCrearTituloOficialSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearTituloOficialSpring.class);
	
	/** el titulo oficial manager. */
	//Declaracion de manager
	private TituloOficialManager tituloOficialManager;
		
	/**
	 * Método VerCrearTituloOficialAction.
	 */
	public VerCrearTituloOficialSpring() {
		try {
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerCrearTituloOficialSpring - crear constructor",e);
		}
	}


	/**
	 * Método doExecute de VerCrearTituloOficialAction.
	 *
	 * @param form SpringForm
	 * @return resultado String
	 * @throws Exception Exception
	 */
	
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("VerCrearTituloOficialSpring -start");
		try{
			//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
			String menu_tablabase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
			this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
			String subMenu_tituloOficial = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.tituloOficial");
			this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tituloOficial);
			//****************************************************************** 
	
			
			String resultado = "";
			TituloOficialBean tituloOficialBean = new TituloOficialBean();
			tituloOficialBean.setDescripcion("");//Va a dar de Alta debe estar vacío.
			tituloOficialBean.setCodigo("");	//Va a dar de Alta debe estar vacío.
			tituloOficialBean.setEstado(ESTADO_ACTIVO); //Todos los títulos que se crean van a ir Activos 'A'
			
			//Pasar el titulo y la pagina al jsp
			setRequestAttribute("titulo", tituloOficialBean);
			
			resultado = "success";
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerCrearTituloOficialSpring - doExecute",e);
			return "nosuccess";}
	}
			
	//Métodos set y get
	/**
	 * Método get de tituloOficialManager.
	 *
	 * @return tituloOficialManager TituloOficialManager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el valor de tituloOficialManager.
	 *
	 * @param tituloOficialManager TituloOficialManager
	 */
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
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
