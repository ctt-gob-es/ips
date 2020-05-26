package es.map.ipsg.action.modelos;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class AnadirCampoPropioModeloSpring.
 */
public class AnadirCampoPropioModeloSpring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AnadirCampoPropioModeloSpring.class);
	
	/** La constante STRING_MODELOBEAN. */
	private static final String STRING_MODELOBEAN = "modeloBean";
	
	/** La constante STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO. */
	private static final String STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO = "field.gestionModelos790.tituloCampoPropio";
	
	/** La constante STRING_TITULO. */
	private static final String STRING_TITULO = "titulo";
	
	/** La constante STRING_MENSAJE. */
	private static final String STRING_MENSAJE = "mensaje";
	
	/** La constante STRING_ACCION. */
	private static final String STRING_ACCION = "accion";
	
	/** La constante STRING_VERMODIFICARMODELOSGESTION790ID. */
	private static final String STRING_VERMODIFICARMODELOSGESTION790ID = "/spring/verModificarModelosGestion790?id=";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";

	//Para insertar en el LOG
	
	/** La constante ADMINISTRACION. */
	private static final Character ADMINISTRACION = 'A';
	
	
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;	
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el campo propio manager. */
	private CamposPropiosManager campoPropioManager;
	
	/** el plantilla propio manager. */
	private PlantillaPropiosManager plantillaPropioManager;



	/**
	 * Crea una nueva anadir campo propio modelo spring.
	 */
	public AnadirCampoPropioModeloSpring() {
		try {
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));	
			setModeloManager((ModeloManager) getBean ("modelosManager"));
			setCampoPropioManager((CamposPropiosManager) getBean("camposPropiosManager"));
			setPlantillaPropioManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
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
		
		getLogger().debug("AnadirCampoPropioModeloSpring -start");
		String resultado = STRING_ERROR;
		
		ModeloGestion790Form formulario = (ModeloGestion790Form) form;
		String accion = formulario.getAccion();
		try{	
			//buscamos los datos del modelo pasado
			ModeloBean modeloBean = new ModeloBean();
			modeloBean.setId(Integer.parseInt(formulario.getId()));
			ModeloBean modeloBeanAux=modeloManager.obtenerModelo790ById(Integer.parseInt(formulario.getId()));
			String mensaje;
			//Comprobamos que el numero de campos propios asociados es menor que 4. En caso contrario, no dejamos anyadir
			CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
			ModeloQuery modeloQuery = new ModeloQuery();
			modeloQuery.setIdModelo(modeloBeanAux.getId());
			camposPropiosQuery.setModelo(modeloQuery);
			ArrayList<CamposPropiosBean> camposPropiosBean=campoPropioManager.buscarCamposPropiosbyModelo(camposPropiosQuery);

			if(camposPropiosBean.size()==4){
				//retornamos error
				this.setRequestAttribute(STRING_MODELOBEAN, modeloBeanAux);
				mensaje = RESOURCE_BUNDLE.getString("field.gestionModelos790.camposPropios.mensajeError");
				String titulo = RESOURCE_BUNDLE.getString(STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO);
				setRequestAttribute(STRING_TITULO,titulo);
				setRequestAttribute(STRING_MENSAJE,mensaje);
				setRequestAttribute(STRING_ACCION,STRING_VERMODIFICARMODELOSGESTION790ID+formulario.getId());
				return "errorCampos";
			}

			if("Guardar Campo".equals(accion)){

				CamposPropiosBean campoPropioBean = new CamposPropiosBean();
				campoPropioBean.setIdModelo(formulario.getId());
				campoPropioBean.setTituloCampo(formulario.getTituloCampo());
				campoPropioBean.setDescripcion(formulario.getDescripcionCampo());
				campoPropioBean.setTituloCampo_ca(formulario.getTituloCampo_ca());
				campoPropioBean.setTituloCampo_eu(formulario.getTituloCampo_eu());
				campoPropioBean.setTituloCampo_gl(formulario.getTituloCampo_gl());
				campoPropioBean.setTituloCampo_vl(formulario.getTituloCampo_vl());
				
				//Guardamos el campo propio en Campos_Propios
				int result = campoPropioManager.guardarCampoPropio(campoPropioBean);
				
				//Guardamos tambien en plantilla propios
				PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
				plantillaPropiosBean.setTipoPlantilla(ADMINISTRACION);
				plantillaPropiosBean.setObligatorio(false);
				campoPropioBean.setId(Long.valueOf(result));
				plantillaPropiosBean.setCampoPropioBean(campoPropioBean);
				plantillaPropiosBean.setModeloBean(modeloBeanAux);
				plantillaPropioManager.guardarPlantillaPropios(plantillaPropiosBean);
				
				if(modeloBeanAux!=null){
					this.setRequestAttribute(STRING_MODELOBEAN, modeloBeanAux);
					if (result > 0) {
						resultado = "crearPropio";
						mensaje = RESOURCE_BUNDLE.getString("field.gestionModelos790.anadirCampoPropio");
						String titulo = RESOURCE_BUNDLE.getString(STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO);
						setRequestAttribute(STRING_TITULO,titulo);
						setRequestAttribute(STRING_MENSAJE,mensaje);
						setRequestAttribute(STRING_ACCION,STRING_VERMODIFICARMODELOSGESTION790ID+formulario.getId());
					} else {
						resultado = STRING_ERROR;
						mensaje = RESOURCE_BUNDLE.getString("field.centroGestor.modificarCentroGestor.mensajeError");
						String titulo = RESOURCE_BUNDLE.getString(STRING_FIELD_GESTIONMODELOS790_TITULOCAMPOPROPIO);
						setRequestAttribute(STRING_TITULO,titulo);
						setRequestAttribute(STRING_MENSAJE,mensaje);
						setRequestAttribute(STRING_ACCION,STRING_VERMODIFICARMODELOSGESTION790ID+formulario.getId());
					}
				}
				
			}else{
				if(modeloBeanAux!=null){
					formulario.setDescripcion(!StringUtils.isEmpty(modeloBeanAux.getDescripcion())?modeloBeanAux.getDescripcion():"");
					formulario.setNumModelo(!StringUtils.isEmpty(modeloBeanAux.getNumModelo())?modeloBeanAux.getNumModelo():"");
					this.setRequestAttribute(STRING_MODELOBEAN, modeloBeanAux);
					resultado = "success";
				}
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
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
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
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
	}
	
	/**
	 * Obtiene el plantilla propio manager.
	 *
	 * @return el plantilla propio manager
	 */
	public PlantillaPropiosManager getPlantillaPropioManager() {
		return plantillaPropioManager;
	}


	/**
	 * Establece el plantilla propio manager.
	 *
	 * @param plantillaPropioManager el nuevo plantilla propio manager
	 */
	public void setPlantillaPropioManager(
			PlantillaPropiosManager plantillaPropioManager) {
		this.plantillaPropioManager = plantillaPropioManager;
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
