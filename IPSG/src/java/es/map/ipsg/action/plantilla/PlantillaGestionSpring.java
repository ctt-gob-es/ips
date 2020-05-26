package es.map.ipsg.action.plantilla;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class PlantillaGestionSpring.
 */
@SuppressWarnings("rawtypes")
public class PlantillaGestionSpring extends AbstractSpring {

	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PlantillaGestionSpring.class);
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;

	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva plantilla gestion spring.
	 */
	public PlantillaGestionSpring() {	
		try {
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error PlantillaGestionSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("PlantillaGestionSpring -start");
		try{
			//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
			String menu_formBaseCG = RESOURCE_BUNDLE.getString("field.menu.formBaseCentroGestor");
			this.getRequest().getSession().setAttribute("pagActiva",menu_formBaseCG);
			//****************************************************************** 
			
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
		
			/*INI-TRA042*/
			List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			this.setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			if(formulario.getIdCentroGestor() != null) {
				centroGestorQuery.setId(formulario.getIdCentroGestor());
			} else {
				centroGestorQuery.setId(listaCentrosGestores.get(0).getId());
				formulario.setIdCentroGestor(listaCentrosGestores.get(0).getId());
			}
			
			CentroGestorBean centroGestorBean = centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
			formulario.setDesCentroGestor(centroGestorBean.getDescripcion());
			/*FIN-TRA042*/
			
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
			}
			logger.info(plantillaBean.getNacionalidad());
			logger.info("formularioNacioliadad: "+formulario.isNacionalidad());
			logger.info("formulario: "+formulario.isFechaNacimiento());
			getLogger().debug("PlantillaGestionSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error PlantillaGestionSpring -  doExecute :",e);
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
			username = usuarioBean.getLogin();
			return username;
		}catch(Exception e){
			logger.error("Error PlantillaGestionSpring - recuperarUsuarioSesion:"+ username,e);
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/*INI-TRA042*/
	/**
	 * @return the usuarioCentrogestorManager
	 */
	public UsuarioCentrogestorManager getUsuarioCentrogestorManager() {
		return usuarioCentrogestorManager;
	}

	/**
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/
	
	
}
