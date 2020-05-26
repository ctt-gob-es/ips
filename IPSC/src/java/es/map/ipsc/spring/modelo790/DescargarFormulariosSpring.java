package es.map.ipsc.spring.modelo790;


import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.ModeloBean;
import es.map.ipsc.manager.modelo.ModeloManager;

/**
 * El Class DescargarFormulariosSpring.
 */
public class DescargarFormulariosSpring extends AbstractSpring {

	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarFormulariosSpring.class);

	/**
	 * Crea una nueva descargar formularios spring.
	 */
	public DescargarFormulariosSpring() {
		try {
			setModeloManager((ModeloManager) getBean("modeloManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error Descargar Formulario",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		logger.info("DescargarFormulariosSpring - start");

		try{
			ArrayList<ModeloBean> listaModelosBean = modeloManager.buscarModeloComboTodos();
			for (ModeloBean modeloBean : listaModelosBean) {
				modeloBean.getCamposPropios();
			}
			
			// Valores específicos para mostrar y recuperar el modelo 790007 específico 
			// para Secretarios Judiciales.
			ModeloBean modeloSJ = new ModeloBean();
			modeloSJ.setId(Integer.parseInt(Constantes.COD_SECRETARIO_JUDICIAL));
			modeloSJ.setNumModelo(Constantes.MODELO79007);
			modeloSJ.setDescripcion(Constantes.DES_DESCARGA_FORM_790007_SECRETARIOS);
			listaModelosBean.add(modeloSJ);
			
			setRequestAttribute("listaModelos", listaModelosBean);
			
		}catch(Exception e){
			logger.error("Error recuperando los modelos",e);
			this.getRequest().setAttribute("errorDescripcion", "Error recuperando los modelos...");	
			return "error";		
		}

		logger.info("DescargarFormulariosSpring - end");
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_descargarForm = RESOURCE_MESSAGE.getString("field.descargarFormulario");
		this.getRequest().getSession().setAttribute("pagActiva", menu_descargarForm);
		//****************************************************************** 

		return "success";
	}

	/**
	 * Obtiene el modelo manager.
	 *
	 * @return el modelo manager
	 */
	public ModeloManager getModeloManager() {
		return modeloManager;
	}

	/**
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
	}

}
