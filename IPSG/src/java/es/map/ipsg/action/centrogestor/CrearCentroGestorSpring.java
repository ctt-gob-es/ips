package es.map.ipsg.action.centrogestor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.util.DateUtil;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.LogGenerico;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.Plantilla;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class CrearCentroGestorSpring.
 */
public class CrearCentroGestorSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearCentroGestorSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';
	
	/** La constante ADMINISTRACION. */
	private static final Character ADMINISTRACION = 'A';
	
	/** La constante CENTROGESTOR. */
	private static final Character CENTROGESTOR = 'G';
	
	/** La constante TIPO_OPERACION. */
	private static final Character TIPO_OPERACION = 'A';
	
	/** La constante TIPO_FUNCIONALIDAD. */
	private static final String TIPO_FUNCIONALIDAD = "CENTRO GESTOR";
	
	/** La constante STRING_DATE_FORMAT. */
	private static final String STRING_DATE_FORMAT = "dd/MM/yyyy";

	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;

	/**
	 * Crea una nueva crear centro gestor spring.
	 */
	public CrearCentroGestorSpring() {
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearCentroGestorSpring()",e );
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
		
		getLogger().debug("CrearCentroGestorSpring -start");
		CentroGestorForm formulario = (CentroGestorForm) form;
		try{
			
			MinisterioQuery ministerioQuery = new MinisterioQuery();
			List<MinisterioBean> ministerio = ministerioManager.buscarMinisterioCombo(ministerioQuery);
			
			this.setRequestAttribute("ministerio", ministerio);
			
			CentroGestorBean centroGestorBean = new CentroGestorBean();
	
			// Alta de Especialidad
			String fechaForm = formulario.getFechaSustitucion();
			if(fechaForm != null && !"".equals(fechaForm)){
				Date fecha = DateUtil.parse(fechaForm,STRING_DATE_FORMAT);
				centroGestorBean.setFechaSustitucion(fecha);
			}
			
			centroGestorBean.setIdMinisterio(Integer.valueOf(formulario.getIdMinisterio()));
			centroGestorBean.setSiglas(formulario.getSiglas());
			centroGestorBean.setDescripcion(formulario.getDescripcion());
			centroGestorBean.setEjercicio(formulario.getEjercicio());
			centroGestorBean.setCodigo(formulario.getCodigo());
			centroGestorBean.setEstado(ESTADO_ACTIVO);
			centroGestorBean.setIdCentroGestorSust(formulario.getIdCentroGestorSust());
			centroGestorBean.setVisibilidad(formulario.getVisibilidad());
			centroGestorBean.setidModelo(Integer.parseInt(formulario.getIdModelo()));
			
			String fechaInicioInhabil = formulario.getFechaInicioInhabil();
			if(fechaInicioInhabil != null && !"".equals(fechaInicioInhabil)){
				Date fechaFInicio = DateUtil.parse(fechaInicioInhabil,STRING_DATE_FORMAT);
				centroGestorBean.setFechaInicioInhabil(fechaFInicio);
			}
			
			String fechaFinInhabil = formulario.getFechaFinInhabil();
			if(StringUtils.isNotEmpty(fechaFinInhabil)){
				Date fechaFin = DateUtil.parse(fechaFinInhabil,STRING_DATE_FORMAT);
				centroGestorBean.setFechaFinInhabil(fechaFin);
			}
			
	
			// Obtenemos un objeto plantilla, cuyo tipo plantilla sea A
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setTipoPlantilla(ADMINISTRACION);
	
			ArrayList<PlantillaBean> listaPlantilla = plantillaManager.buscarPlantillaAll(plantillaQuery);
	
			String resultado = "";
			
			// Comprobamos que listaPlantilla solo tenga un registro
			if (listaPlantilla.size() == 1) {
				// Obtenemos el primer registro
				PlantillaBean plantillaBean = listaPlantilla.get(0);
				// Le modificamos el campo tipo plantilla
				plantillaBean.setTipoPlantilla(CENTROGESTOR);
				// Guardamos la plantilla y recogemos el id para obtener una
				// plantilla por id
				Long idPlantilla = plantillaManager.guardarPlantilla(plantillaBean);
	
				// Obtenemos la plantilla con el id que nos ha devuelto
				plantillaBean = plantillaManager.obtenerPlantilla(idPlantilla);
				Plantilla plantilla = plantillaManager.toPlantilla(plantillaBean);
	
				// Asignamos la plantilla al Centro Gestor
				centroGestorBean.setPlantilla(plantilla);
				
				// Guardamos el Centro Gestor
				int result = centroGestorManager.guardarCentroGestor(centroGestorBean);	
				CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
				centroGestorQuery.setId(result);
				CentroGestorBean getorAux=centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
				
				// Obtenemos un objeto plantillaPropios, cuyo tipo plantilla sea A
				PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
				plantillaPropiosQuery.setTipoPlantilla(ADMINISTRACION);
				//Seteamos el modelo pasodo en el formulario
				ModeloQuery modeloQuery = new ModeloQuery();
				modeloQuery.setIdModelo(Integer.parseInt(formulario.getIdModelo()));
				plantillaPropiosQuery.setModelo(modeloQuery);
				
				ArrayList<PlantillaPropiosBean> listaPlantillaPropios = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
			
				//Aqui seteamos el id del centro gestor,del modelo y los campos propios a las plantillas propias y las guardamos
				for(int i=0; i<listaPlantillaPropios.size();i++){
					listaPlantillaPropios.get(i).setCentroGestorBean(getorAux);
					PlantillaPropiosBean plantillaPropiosBean = listaPlantillaPropios.get(i);
					plantillaPropiosBean.setTipoPlantilla(CENTROGESTOR);
					plantillaPropiosManager.guardarPlantillaPropios(plantillaPropiosBean);
				}
	
				//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				try{
					UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					String username = usuarioBean.getLogin();
					usuarioQuery.setLogin(username);
				}catch(Exception e){
					logger.error("Error CrearCentroGestorSpring - recuperarUsuarioSesion",e );
					new BusinessException("exception.recuperarUsuarioSesion");
					return "error";
				}
				
				UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
				Usuario usuario = usuarioManager.toUsuario(usuarioBean);
				
				//Registramos en el log la operacion realizada
				LogGenerico logGenerico = new LogGenerico();
				Date dHoy = new Date();
		
				logGenerico.setFecha(dHoy);
				logGenerico.setTipoOperacion(TIPO_OPERACION);
				logGenerico.setTipoFuncionalidad(TIPO_FUNCIONALIDAD);
				logGenerico.setUsuario(usuario); 
				logGenerico.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.centroGestor.detalleOperacionAlta") + " " + result);
				logGenerico.setIdTablaOrigen(Long.valueOf(String.valueOf(result)));			
				
				//Guardamos el log
				logGenericoManager.guardarLogGenerico(logGenerico);			
				
				String mensaje;			
	
				if (result > 0) {
					resultado = "success";
					mensaje = RESOURCE_BUNDLE.getString("field.centroGestor.crearCentroGestorConfirmacion");
				} else {
					resultado = "error";
					mensaje = RESOURCE_BUNDLE.getString("field.centroGestor.crearCentroGestorError");
				}
	
				String titulo = RESOURCE_BUNDLE.getString("field.centroGestor.tituloCrearCentroGestor");
	
				setRequestAttribute("titulo", titulo);
				setRequestAttribute("mensaje", mensaje);
				setRequestAttribute("accion", "/spring/buscarCentroGestor");
				
			} else {
				//Mostrar mensaje de error informando que no puede haber 2 tipo plantilla = A
				
			}
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error CrearCentroGestorSpring() - doExecute",e );
			return "nosuccess";
		}
	}

	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return el ministerio manager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
	}

	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CentroGestorManager getCuerpoEscalaManager() {
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
	 * Obtiene el plantilla manager.
	 *
	 * @return the plantillaManager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager            the plantillaManager to set
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
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

	/**
	 * Obtiene el log generico manager.
	 *
	 * @return the logGenericoManager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager the logGenericoManager to set
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return the centroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
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
