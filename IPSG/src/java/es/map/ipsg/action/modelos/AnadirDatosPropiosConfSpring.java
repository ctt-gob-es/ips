package es.map.ipsg.action.modelos;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ipsg.bean.DatosPropiosConfigBean;
import es.map.ipsg.form.DatosPropiosConfForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.DatosPropiosConfiguracionManager;

/**
 * El Class AnadirDatosPropiosConfSpring.
 */
public class AnadirDatosPropiosConfSpring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AnadirDatosPropiosConfSpring.class);
	
	/** La constante STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO. */
	private static final String STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO = "field.gestionModelos790.tituloCampoPropio";
	
	/** La constante STRING_TITULO. */
	private static final String STRING_TITULO = "titulo";
	
	/** La constante STRING_MENSAJE. */
	private static final String STRING_MENSAJE = "mensaje";
	
	/** La constante STRING_ACCION. */
	private static final String STRING_ACCION = "accion";
	
	/** La constante STRING_VERMODIFICARMODELOSGESTION790ID. */
	private static final String STRING_VERMODIFICARMODELOSGESTION790ID = "/spring/verModificarCampoPropio?id=";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";

	//Para insertar en el LOG
	
	
	

	/** el campo propio manager. */
	private CamposPropiosManager campoPropioManager;
	
	/** el datos propios manager. */
	private DatosPropiosConfiguracionManager datosPropiosManager;



	/**
	 * Crea una nueva anadir datos propios conf spring.
	 */
	public AnadirDatosPropiosConfSpring() {
		try {
			setCampoPropioManager((CamposPropiosManager) getBean("camposPropiosManager"));
			setDatosPropiosManager((DatosPropiosConfiguracionManager) getBean("datosPropiosConfiguracionManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("AnadirCampoPropioModeloSpring:",e);
		}
	}
	

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_centroGestor = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.gestionModelos");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
		
		getLogger().debug("AnadirDatosPropiosConfSpring -start");
		String resultado;
		
		DatosPropiosConfForm formulario = (DatosPropiosConfForm) form;
		String accion = formulario.getAccion();
		try{	

			if("Guardar Campo".equals(accion) && this.getRequest().getAttribute("org.apache.spring.ERROR") == null){
				String mensaje;
				CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
				DatosPropiosConfigBean datosPropiosConfBean = new DatosPropiosConfigBean();
				
				camposPropiosQuery.setIdCampo(Integer.parseInt(formulario.getId()));
				datosPropiosConfBean.setCampo(campoPropioManager.buscaCamposPropiosUnico(camposPropiosQuery));
				datosPropiosConfBean.setDescripcion(formulario.getDescripcion());
				
				//Guardamos el campo propio en Campos_Propios
				int result = datosPropiosManager.guardarDatosPropiosConf(datosPropiosConfBean);
				
				
				if (result > 0) {
					resultado = "crearPropio";
					mensaje = RESOURCE_BUNDLE.getString("field.camposPropios.anadirCampoPropio");
					String titulo = RESOURCE_BUNDLE.getString(STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO);
					setRequestAttribute(STRING_TITULO,titulo);
					setRequestAttribute(STRING_MENSAJE,mensaje);
					setRequestAttribute(STRING_ACCION,STRING_VERMODIFICARMODELOSGESTION790ID+formulario.getId());
				} else {
					resultado = STRING_ERROR;
					mensaje = RESOURCE_BUNDLE.getString("field.camposPropios.guardarDatoPropio.mensajeError");
					String titulo = RESOURCE_BUNDLE.getString(STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO);
					setRequestAttribute(STRING_TITULO,titulo);
					setRequestAttribute(STRING_MENSAJE,mensaje);
					setRequestAttribute(STRING_ACCION,STRING_VERMODIFICARMODELOSGESTION790ID+formulario.getId());
				}
			}else{
				resultado = "success";
			}
			
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("AnadirCampoPropioModeloSpring - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Obtiene el campo propio manager.
	 *
	 * @return el campo propio manager
	 */
	public CamposPropiosManager getCampoPropioManager() {
		return campoPropioManager;
	}


	/**
	 * Establece el campo propio manager.
	 *
	 * @param campoPropioManager el nuevo campo propio manager
	 */
	public void setCampoPropioManager(CamposPropiosManager campoPropioManager) {
		this.campoPropioManager = campoPropioManager;
	}

	/**
	 * Obtiene el datos propios manager.
	 *
	 * @return el datos propios manager
	 */
	public DatosPropiosConfiguracionManager getDatosPropiosManager() {
		return datosPropiosManager;
	}


	/**
	 * Establece el datos propios manager.
	 *
	 * @param datosPropiosManager el nuevo datos propios manager
	 */
	public void setDatosPropiosManager(DatosPropiosConfiguracionManager datosPropiosManager) {
		this.datosPropiosManager = datosPropiosManager;
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
