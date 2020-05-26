package es.map.ipsg.action.plantilla;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;

/**
 * El Class PlantillaPrincipalSpring.
 */
public class PlantillaPrincipalSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PlantillaPrincipalSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el campo propio manager. */
	private CamposPropiosManager campoPropioManager;
	
	/** La constante ADMINISTRACION. */
	private static final Character ADMINISTRACION = 'A';


	/**
	 * Crea una nueva plantilla principal spring.
	 */
	public PlantillaPrincipalSpring() {	
		try {
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setModeloManager((ModeloManager)getBean("modelosManager"));
			setCampoPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error PlantillaPrincipalSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_plantilla = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.fomularioBaseAdministrador");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_plantilla);
			
		getLogger().debug("PlantillaPrincipalSpring -start");
				
		PlantillaForm formulario;
		formulario = (PlantillaForm) form;
		formulario.setLocalidadNacimiento(false);
		formulario.setProvinciaNacimiento(false);

		try{
			//Coger el usuario de la sesion
			PlantillaBean plantilla;
	
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setTipoPlantilla('A');
			plantilla = plantillaManager.buscarPlantilla(plantillaQuery);
			formulario.setId(plantilla.getId());
			
			//Buscamos los modelos para tener sus campos propios
			ModeloQuery modeloQuery = new ModeloQuery();
			ArrayList<ModeloBean> listaModelosBean=modeloManager.buscarModelosAll(modeloQuery);
			if(listaModelosBean!=null){
				plantilla.setListaModelosBean(listaModelosBean);
			}
			// Jugar con plantillas Propios tipo A y ordenados por modelo
			PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
			plantillaPropiosQuery.setTipoPlantilla(ADMINISTRACION);
			plantillaPropiosQuery.addOrder(PlantillaPropiosQuery.MODELO,OrderType.ASC);
			ArrayList<PlantillaPropiosBean> listaPlantillasPropiosBean=plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
			plantilla.setListaPlantillaPropiosBean(listaPlantillasPropiosBean);
			
			if(plantilla != null){
				formulario = cargarFormulario(formulario, plantilla);
			}
			
			
			logger.info(plantilla.getNacionalidad());
			setRequestAttribute("plantilla", plantilla);
			getLogger().debug("PlantillaPrincipalSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error PlantillaPrincipalSpring - doExecute:",e);
			return "nosuccess";
		}
	}


	/**
	 * Cargar formulario.
	 *
	 * @param formulario el formulario
	 * @param plantilla el plantilla
	 * @return el plantilla form
	 */
	private PlantillaForm cargarFormulario(PlantillaForm formulario, PlantillaBean plantilla) {
		
		formulario.setId(plantilla.getId());
		formulario.setNif('S'==plantilla.getNif()?true:false);
		formulario.setPrimerApellido('S'==plantilla.getPrimerApellido()?true:false);
		formulario.setSegundoApellido('S'==plantilla.getSegundoApellido()?true:false);
		formulario.setNombre('S'==plantilla.getNombre()?true:false);
		formulario.setFechaNacimiento('S'==plantilla.getFechaNacimiento()?true:false);
		formulario.setSexo('S'==plantilla.getSexo()?true:false);
		formulario.setNacionalidad('S'==plantilla.getNacionalidad()?true:false);
		formulario.setCorreoElectronico('S'==plantilla.getCorreoElectronico()?true:false);
		formulario.setTelefono('S'==plantilla.getTelefono()?true:false);
		formulario.setVia('S'==plantilla.getVia()?true:false);
		formulario.setCodigoPostal('S'==plantilla.getCodigoPostal()?true:false);
		formulario.setMunicipio('S'==plantilla.getMunicipio()?true:false);
		formulario.setProvincia('S'==plantilla.getProvincia()?true:false);
		formulario.setPais('S'==plantilla.getPais()?true:false);
		formulario.setCuerpo('S'==plantilla.getCuerpo()?true:false);
		formulario.setEspecialidad('S'==plantilla.getEspecialidad()?true:false);
		formulario.setFormaacceso('S'==plantilla.getFormaacceso()?true:false);
		formulario.setEntidadConvocante('S'==plantilla.getEntidadConvocante()?true:false);
		formulario.setFechaBoe('S'==plantilla.getFechaBoe()?true:false);
		formulario.setProvinciaExamen('S'==plantilla.getProvinciaExamen()?true:false);
		formulario.setPorcentajeDiscapacidad('S'==plantilla.getPorcentajeDiscapacidad()?true:false);
		formulario.setTipoDiscapacidad('S'==plantilla.getTipoDiscapacidad()?true:false);
		formulario.setDetalleDiscapacidad('S'==plantilla.getDetalleDiscapacidad()?true:false);
		formulario.setTitulosExigidos('S'==plantilla.getTitulosExigidos()?true:false);
		formulario.setOtrosTitulos('S'==plantilla.getOtrosTitulos()?true:false);
		formulario.setDatosA('S'==plantilla.getDatosA()?true:false);
		formulario.setDatosB('S'==plantilla.getDatosB()?true:false);
		formulario.setDatosC('S'==plantilla.getDatosC()?true:false);
		formulario.setReservaDiscapacidad('S'==plantilla.getReservaDiscapacidad()?true:false);
		formulario.setCodigoCuerpoEscala('S'==plantilla.getCodigoCuerpoEscala()?true:false);
		formulario.setCodigoEspecialidad('S'==plantilla.getCodigoEspecialidad()?true:false);
		formulario.setCodigoMinisterio('S'==plantilla.getCodigoMinisterio()?true:false);
		formulario.setCodigoPaisDomicilio('S'==plantilla.getCodigoPaisDomicilio()?true:false);
		formulario.setCodigoProvinciaDomicilio('S'==plantilla.getCodigoProvinciaDomicilio()?true:false);
		formulario.setCodigoProvinciaExamen('S'==plantilla.getCodigoProvinciaExamen()?true:false);
		formulario.setCodigoTituloOficial('S'==plantilla.getCodigoTituloOficial()?true:false);
		formulario.setListaModelosBean(plantilla.getListaModelosBean());
		formulario.setListaPlantillasPropiosBean(plantilla.getListaPlantillaPropiosBean());
		
		return formulario;
	}



	/**
	 * Obtiene el plantilla manager.
	 *
	 * @return el plantilla manager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager el nuevo plantilla manager
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
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
	
	/**
	 * Obtiene el campo propios manager.
	 *
	 * @return el campo propios manager
	 */
	public CamposPropiosManager getCampoPropiosManager() {
		return campoPropioManager;
	}

	/**
	 * Establece el campo propios manager.
	 *
	 * @param campoPropioManager el nuevo campo propios manager
	 */
	public void setCampoPropiosManager(CamposPropiosManager campoPropioManager) {
		this.campoPropioManager = campoPropioManager;
	}
	
	/**
	 * Obtiene el plantilla propios manager.
	 *
	 * @return el plantilla propios manager
	 */
	public PlantillaPropiosManager getPlantillaPropiosManager() {
		return plantillaPropiosManager;
	}

	/**
	 * Establece el plantilla propios manager.
	 *
	 * @param plantillaPropiosManager el nuevo plantilla propios manager
	 */
	public void setPlantillaPropiosManager(
			PlantillaPropiosManager plantillaPropiosManager) {
		this.plantillaPropiosManager = plantillaPropiosManager;
	}
	
}
