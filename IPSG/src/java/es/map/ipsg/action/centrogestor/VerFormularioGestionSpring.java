package es.map.ipsg.action.centrogestor;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerFormularioGestionSpring.
 */
public class VerFormularioGestionSpring extends AbstractSpring {

	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerFormularioGestionSpring.class);
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	
	/** La constante CENTRO_GESTOR. */
	private static final Character CENTRO_GESTOR = 'G';

	/**
	 * Crea una nueva ver formulario gestion spring.
	 */
	public VerFormularioGestionSpring() {	
		try {
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
			setCamposPropiosManager((CamposPropiosManager) getBean("camposPropiosManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setModeloManager((ModeloManager)getBean("modelosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerFormularioGestionSpring(): ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_centroGestor = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.centroGestor");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
		
		getLogger().debug("VerFormularioGestionSpring -start");
		
		Integer idCentroGestor =  Integer.valueOf(this.getRequest().getParameter("idCentroGestor"));
		logger.info("ID--------> "+idCentroGestor);
		try{
			//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
			String menu_tablabase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
			this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
			//****************************************************************** 
			this.getRequest().getSession().setAttribute("flujoAdmin", "S");
			
			PlantillaForm formulario = (PlantillaForm) form;
			formulario.setLocalidadNacimiento(false);
			formulario.setProvinciaNacimiento(false);
			
			String username = recuperarUsuario();
			
			if (username.equals("error")) {
				return username;
			}

			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(username);
			
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			setRequestAttribute("perfilUsuario", usuarioBean.getIdPerfil());
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.setId(idCentroGestor);
			CentroGestorBean centroGestorBean;
			centroGestorBean = centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
			
			formulario.setId(centroGestorBean.getPlantilla().getId());
			logger.info("id: "+formulario.getId());
			PlantillaBean plantillaBean;
			formulario.setNacionalidad(true);
			
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setId(formulario.getId());
			plantillaBean = plantillaManager.buscarPlantilla(plantillaQuery);
			if(plantillaBean != null){
				formulario = cargarFormulario(formulario, plantillaBean);
				formulario.setId(plantillaBean.getId());
				formulario.setIdCentroGestor(idCentroGestor);
			}
			
			int modelo = centroGestorBean.getModelo().getIdModelo();
			ModeloBean modeloBean=modeloManager.obtenerModelo790ById(modelo);
			//buscamos plantillas tipo Admin
			PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
			plantillaPropiosQuery.setTipoPlantilla(CENTRO_GESTOR);
			ModeloQuery modeloQuery = new ModeloQuery();
			modeloQuery.setIdModelo(centroGestorBean.getModelo().getIdModelo());
			plantillaPropiosQuery.setModelo(modeloQuery);
			centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.setId(formulario.getIdCentroGestor());
			plantillaPropiosQuery.setCentroGestor(centroGestorQuery);
			plantillaPropiosQuery.addOrder(PlantillaPropiosQuery.CAMPOSPROPIOS,OrderType.ASC);
			ArrayList<PlantillaPropiosBean> listaPlantillasPropiosBean=plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
			
			
			setRequestAttribute("modeloBean", modeloBean);
			setRequestAttribute("listaPlantillasPropiosBean", listaPlantillasPropiosBean);
			logger.info(plantillaBean.getNacionalidad());
			logger.info("formularioNacioliadad: "+formulario.isNacionalidad());
			logger.info("formulario: "+formulario.isFechaNacimiento());
			getLogger().debug("VerFormularioGestionSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			logger.error("Error VerFormularioGestionSpring -doExecute: ",e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			return "nosuccess";
		}
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String username = null;
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			return usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error VerFormularioGestionSpring - recuperarUsuarioSesion: " + username,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
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
		
		formulario = cargarFormulario2(formulario,plantilla);
		
		formulario = cargarFormulario3(formulario,plantilla);
		
		return formulario;
	}
	
	/**
	 * Cargar formulario 2.
	 *
	 * @param formulario el formulario
	 * @param plantilla el plantilla
	 * @return el plantilla form
	 */
	private PlantillaForm cargarFormulario2(PlantillaForm formulario, PlantillaBean plantilla){
		
		formulario.setEspecialidad('S'==plantilla.getEspecialidad()?true:false);
		formulario.setFormaacceso('S'==plantilla.getFormaacceso()?true:false);
		formulario.setEntidadConvocante('S'==plantilla.getEntidadConvocante()?true:false);
		formulario.setFechaBoe('S'==plantilla.getFechaBoe()?true:false);
		formulario.setProvinciaExamen('S'==plantilla.getProvinciaExamen()?true:false);
		formulario.setPorcentajeDiscapacidad('S'==plantilla.getPorcentajeDiscapacidad()?true:false);
		formulario.setReservaDiscapacidad('S'==plantilla.getReservaDiscapacidad()?true:false);
		formulario.setTipoDiscapacidad('S'==plantilla.getTipoDiscapacidad()?true:false);
		formulario.setDetalleDiscapacidad('S'==plantilla.getDetalleDiscapacidad()?true:false);
		formulario.setTitulosExigidos('S'==plantilla.getTitulosExigidos()?true:false);
		formulario.setOtrosTitulos('S'==plantilla.getOtrosTitulos()?true:false);
		formulario.setDatosA('S'==plantilla.getDatosA()?true:false);
		formulario.setDatosB('S'==plantilla.getDatosB()?true:false);
		formulario.setDatosC('S'==plantilla.getDatosC()?true:false);
		
		return formulario;
	}

	/**
	 * Cargar formulario 3.
	 *
	 * @param formulario el formulario
	 * @param plantilla el plantilla
	 * @return el plantilla form
	 */
	private PlantillaForm cargarFormulario3(PlantillaForm formulario, PlantillaBean plantilla){
		
		formulario.setCodigoCuerpoEscala('S'==plantilla.getCodigoCuerpoEscala()?true:false);
		formulario.setCodigoEspecialidad('S'==plantilla.getCodigoEspecialidad()?true:false);
		formulario.setCodigoMinisterio('S'==plantilla.getCodigoMinisterio()?true:false);
		formulario.setCodigoPaisDomicilio('S'==plantilla.getCodigoPaisDomicilio()?true:false);
		formulario.setCodigoProvinciaDomicilio('S'==plantilla.getCodigoProvinciaDomicilio()?true:false);
		formulario.setCodigoProvinciaExamen('S'==plantilla.getCodigoProvinciaExamen()?true:false);
		formulario.setCodigoTituloOficial('S'==plantilla.getCodigoTituloOficial()?true:false);
		
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
	 * Obtiene el campos propios manager.
	 *
	 * @return el campos propios manager
	 */
	public CamposPropiosManager getCamposPropiosManager() {
		return camposPropiosManager;
	}

	/**
	 * Establece el campos propios manager.
	 *
	 * @param camposPropiosManager el nuevo campos propios manager
	 */
	public void setCamposPropiosManager(CamposPropiosManager camposPropiosManager) {
		this.camposPropiosManager = camposPropiosManager;
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
	public void setPlantillaPropiosManager(PlantillaPropiosManager plantillaPropiosManager) {
		this.plantillaPropiosManager = plantillaPropiosManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager el nuevo centro gestor manager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
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
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
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
