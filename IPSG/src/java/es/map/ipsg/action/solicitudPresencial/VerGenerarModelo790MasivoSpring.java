package es.map.ipsg.action.solicitudPresencial;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.form.Modelo790MasivoForm;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerGenerarModelo790MasivoSpring.
 */
public class VerGenerarModelo790MasivoSpring extends AbstractSpring<Modelo790MasivoForm> {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerGenerarModelo790MasivoSpring.class);	
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** el modelos manager. */
	private ModeloManager modelosManager;
	
	/**
	 * Accion VerGenerarModelo790MasivolAction.
	 */
	public VerGenerarModelo790MasivoSpring() {
		try {
			setModelosManager((ModeloManager) getBean("modelosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerGenerarModelo790MasivoSpring- crear constructor",e);
		}
	}

	/**
	 * Metodo doExecute de VerGenerarModelo790MasivolAction.
	 *
	 * @param form SpringForm
	 * @return resultado String si no tiene errores nos devuelve success
	 * @throws Exception Exception
	 */
	protected String doExecute(Modelo790MasivoForm form) throws Exception {
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.generarModelo790Masivo");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		//******************************************************************
		
		String menu = this.getRequest().getParameter("menu");
		
		if(menu!=null && menu.equals("S")){
			form.setNumModelos("");
		}
		
		// Recuperamos la lista de modelos existentes.
		try{
			ArrayList<ModeloBean> listaModelosBean = modelosManager.buscarModeloComboTodos();
			
			// Valores especificos para mostrar y recuperar el modelo 790007 especifico 
			// para Secretarios Judiciales.
			ModeloBean modeloSJ = new ModeloBean();
			modeloSJ.setId(Constantes.COD_SECRETARIO_JUDICIAL);
			modeloSJ.setNumModelo(Constantes.MODELO79007);
			modeloSJ.setDescripcion(Constantes.DES_DESCARGA_FORM_790007_SECRETARIOS);
			listaModelosBean.add(modeloSJ);
			
			setSessionAttribute("listaModelos", listaModelosBean);
			
			
		}catch(Exception e){
			logger.error("Error VerGenerarModelo790MasivoSpring- Error recuperando los modelos ..",e);
			this.getRequest().setAttribute("errorDescripcion", "Error recuperando los modelos...");	
			return "error";		
		}
		
		return "success";	
	}

	/**
	 * Obtiene el modelos manager.
	 *
	 * @return el modelos manager
	 */
	public ModeloManager getModelosManager() {
		return modelosManager;
	}

	/**
	 * Establece el modelos manager.
	 *
	 * @param modelosManager el nuevo modelos manager
	 */
	public void setModelosManager(ModeloManager modelosManager) {
		this.modelosManager = modelosManager;
	}
}
