package es.map.ipsg.action.centrogestor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

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
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.MinisterioBean;
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
 * El Class ModificarCentroGestorSpring.
 */
public class ModificarCentroGestorSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarCentroGestorSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	//Para insertar en el LOG
	private static final Character ESTADO_ACTIVO = 'A';
	
	/** La constante ADMINISTRACION. */
	private static final Character ADMINISTRACION = 'A';
	
	/** La constante CENTRO_GESTOR. */
	private static final Character CENTRO_GESTOR = 'G';
	
	/** La constante TIPO_OPERACION. */
	private static final Character TIPO_OPERACION = 'M';
	
	/** La constante TIPO_FUNCIONALIDAD. */
	private static final String TIPO_FUNCIONALIDAD = "CENTRO GESTOR";
	
	/** La constante STRING_DATE_FORMAT. */
	private static final String STRING_DATE_FORMAT = "dd/MM/yyyy";
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;	
		
	/**
	 * Crea una nueva modificar centro gestor spring.
	 */
	public ModificarCentroGestorSpring() {
		try {
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarCentroGestorSpring()",e );
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
		
		getLogger().debug("ModificarCentroGestorSpring -start");
		String resultado;
		
		CentroGestorForm formulario = (CentroGestorForm) form;
		String idCentroGestor= formulario.getId();
		String accion = formulario.getAccion();
	try{	
		if("VOLVER".equalsIgnoreCase(accion)){
			resultado = "list";
		}else if(idCentroGestor!=null){//Modificacion de Centro Gestor
			
			MinisterioQuery ministerioQuery = new MinisterioQuery();
			List<MinisterioBean> ministerios = ministerioManager.buscarMinisterioCombo(ministerioQuery);
			
			this.setRequestAttribute("ministerio", ministerios);
			
			CentroGestorBean centroGestorBean = new CentroGestorBean();

				centroGestorBean.setId(Integer.valueOf(formulario.getId()));
				centroGestorBean.setIdMinisterio(Integer.valueOf(formulario.getIdMinisterio()));
				centroGestorBean.setSiglas(formulario.getSiglas());
				centroGestorBean.setDescripcion(formulario.getDescripcion());
				centroGestorBean.setIdPlantilla(formulario.getIdPlantilla());
				centroGestorBean.setidModelo(Integer.parseInt(formulario.getIdModelo()));
	
				Plantilla plantilla = new Plantilla();
				plantilla.setId(formulario.getIdPlantilla());
				
				centroGestorBean.setPlantilla(plantilla);
				
				centroGestorBean.setEjercicio(formulario.getEjercicio());
				centroGestorBean.setCodigo(formulario.getCodigo());
				centroGestorBean.setEstado(ESTADO_ACTIVO);
				centroGestorBean.setIdCentroGestorSust(formulario.getIdCentroGestorSust());
				if(formulario.getVisibilidad() != null){	
					centroGestorBean.setVisibilidad(formulario.getVisibilidad());
				}else{
					centroGestorBean.setVisibilidad(false);
				}	
				
				String fechaInicioInhabil = formulario.getFechaInicioInhabil();
				if(fechaInicioInhabil != null && !"".equals(fechaInicioInhabil)){
					Date fechaFInicio = DateUtil.parse(fechaInicioInhabil,STRING_DATE_FORMAT);
					centroGestorBean.setFechaInicioInhabil(fechaFInicio);
				}
				
				String fechaFinInhabil = formulario.getFechaFinInhabil();
				if(fechaFinInhabil != null && !"".equals(fechaFinInhabil)){
					Date fechaFin = DateUtil.parse(fechaFinInhabil,STRING_DATE_FORMAT);
					centroGestorBean.setFechaFinInhabil(fechaFin);
				}				
				
				String fechaForm = formulario.getFechaSustitucion();				
				if(fechaForm!=null && !"".equals(fechaForm)){
					Date fecha = DateUtil.parse(fechaForm,STRING_DATE_FORMAT);
					centroGestorBean.setFechaSustitucion(fecha);
				}		
				
				//consultamos el modelo para ver si ha cambiado
				CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
				centroGestorQuery.setId(Integer.valueOf(formulario.getId()));
				CentroGestorBean centroGestorAux= centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
		
				String idModeloAux=Integer.toString(centroGestorAux.getModelo().getIdModelo());
				//Si ha cambiado el modelo, borramos en plantillas propios y volvemos a guardar los datos en plantillas propios, plantillas y centro gesto
				if(!idModeloAux.equals(formulario.getIdModelo())){
					//Borramos plantillas propios con el id del cento gestor
					PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
					plantillaPropiosQuery.setTipoPlantilla('G');
					CentroGestorQuery centroGestorQueryAux = new CentroGestorQuery();
					centroGestorQueryAux.setId(Integer.parseInt(formulario.getId()));
					plantillaPropiosQuery.setCentroGestor(centroGestorQueryAux);
					
					//buscamos las plantillas propias y las borramos
					ArrayList<PlantillaPropiosBean> listaPlantillaPropios = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
					for(int i=0; i<listaPlantillaPropios.size();i++){
						plantillaPropiosQuery.setIdPlantillaPropios(Long.valueOf(listaPlantillaPropios.get(i).getId()));
						plantillaPropiosManager.borrarPlantillasPropios(plantillaPropiosQuery);
					}
					
					//insertamos en plantillas propios las nuevas plantillas del nuevo modelo asociado
					// Obtenemos un objeto plantillaPropios, cuyo tipo plantilla sea A
					plantillaPropiosQuery = new PlantillaPropiosQuery();
					plantillaPropiosQuery.setTipoPlantilla(ADMINISTRACION);
					//Seteamos el modelo pasodo en el formulario
					ModeloQuery modeloQuery = new ModeloQuery();
					modeloQuery.setIdModelo(Integer.parseInt(formulario.getIdModelo()));
					plantillaPropiosQuery.setModelo(modeloQuery);
					//buscamos las plantillas propias del administrador en el modelo seteado
					listaPlantillaPropios = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
					//Aqui seteamos el centro gestor y el modelo a las plantillas propias y las guardamos
					for(int i=0; i<listaPlantillaPropios.size();i++){
						listaPlantillaPropios.get(i).setCentroGestorBean(centroGestorBean);
						listaPlantillaPropios.get(i).getModeloBean().setId(centroGestorBean.getidModelo());
						PlantillaPropiosBean plantillaPropiosBean = listaPlantillaPropios.get(i);
						plantillaPropiosBean.setTipoPlantilla(CENTRO_GESTOR);
						plantillaPropiosManager.guardarPlantillaPropios(plantillaPropiosBean);
					}
				}
				//Finalmente guardamos las modificaciones del centro gestor
				centroGestorManager.modificarCentroGestor(centroGestorBean);
				
				//Obtenemos el Usuario que esta logueado en la aplicacion para insertarlo en el log
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				
				try{
					UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					String username = usuarioBean.getLogin();
					usuarioQuery.setLogin(username);
				}catch(Exception e){
					logger.error("Error ModificarCentroGestorSpring() - doExecute -recuperarUsuarioSesion",e );
					new BusinessException("exception.recuperarUsuarioSesion");
					return "error";
				}
				
				UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
				Usuario usuario = usuarioManager.toUsuario(usuarioBean);

				//Creamos un logGenerico y lo cargamos con los datos correspondientes
				LogGenerico logGenerico = new LogGenerico();
				Date dHoy = new Date();
					
				logGenerico.setFecha(dHoy);
				logGenerico.setTipoOperacion(TIPO_OPERACION);
				logGenerico.setTipoFuncionalidad(TIPO_FUNCIONALIDAD);
				logGenerico.setUsuario(usuario); 
				logGenerico.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.centroGestor.detalleOperacionModificacion") + " " + centroGestorBean.getId());
				logGenerico.setIdTablaOrigen(centroGestorBean.getId().longValue());
				
				//Guardamos el log
				logGenericoManager.guardarLogGenerico(logGenerico);			
				
			if ("Error".equals(accion)){
				String mensaje = RESOURCE_BUNDLE.getString("field.centroGestor.modificarCentroGestor.mensajeError");
				String titulo = RESOURCE_BUNDLE.getString("field.centroGestor.tituloMantenimientoCentroGestor");
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/verModificarCentroGestor?id="+formulario.getId());
			}
			else{
				String mensaje = RESOURCE_BUNDLE.getString("field.centroGestor.modificarCentroGestorConfirmacion");
				String titulo = RESOURCE_BUNDLE.getString("field.centroGestor.tituloMantenimientoCentroGestor");
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarCentroGestor");
			}
				
			resultado = "success";
		}else{
			resultado = "error";
		}

		return resultado;
	}catch(Exception e){
		logger.warn(e);
		logger.error("Error ModificarCentroGestorSpring() - doExecute",e );
		this.getRequest().setAttribute("descripcionError", e.getMessage());
		return "nosuccess";
	}
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
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager the centroGestorManager to set
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return the ministerioManager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager the ministerioManager to set
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
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
	 * @return the plantillaManager
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
