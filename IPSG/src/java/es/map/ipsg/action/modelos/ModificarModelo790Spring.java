package es.map.ipsg.action.modelos;

import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.LogGenerico;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.centrogestor.ModificarCentroGestorSpring;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.UsuarioManager;

public class ModificarModelo790Spring extends AbstractSpring{
	private static final String MESSAGE_RESOURCE = "MessageResources";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	private static final Logger logger = Logger.getLogger(ModificarCentroGestorSpring.class);
	//Para insertar en el LOG
	
	
	private static final Character TIPO_OPERACION = 'M';
	private static final String TIPO_FUNCIONALIDAD = "GESTION MODELO";
	private static final String STRING_ERROR = "error";
	
	private LogGenericoManager logGenericoManager;
	private UsuarioManager usuarioManager;	
	private ModeloManager modeloManager;

	public ModificarModelo790Spring() {
		try {
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));	
			setModeloManager((ModeloManager) getBean ("modelosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarModelo790Spring:" ,e);
		}
	}

	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_centroGestor = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.gestionModelos");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
		
		getLogger().debug("ModificarModelo790Spring -start");
		String resultado;
		
		ModeloGestion790Form formulario = (ModeloGestion790Form) form;
		String idModelo= formulario.getId();
		String accion = formulario.getAccion();
		try{	
			//detectamos si se ha pulsado en "Cancelar", "Añadir Campo" o "modificar"
			if("VOLVER".equalsIgnoreCase(accion)){
				resultado = "list";
			}else if(idModelo!=null){//Modificacion del Modelo
				ModeloBean modeloBean = new ModeloBean();
				modeloBean.setDescripcion(formulario.getDescripcion());
				modeloBean.setNumModelo(formulario.getNumModelo());
				modeloBean.setId(Integer.parseInt(formulario.getId()));
				ModeloBean modeloBeanAux= modeloManager.obtenerModelo790ById(Integer.parseInt(formulario.getId()));
				modeloBean.setFechaAlta(modeloBeanAux.getFechaAlta());
				//Finalmente guardamos las modificaciones del modelo
				modeloManager.modificarModelo790(modeloBean);
	
				//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				String username = recuperarUsuario();
				if (username.equals(STRING_ERROR)) {
					return username;
				}
				usuarioQuery.setLogin(username);
					
				UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
				Usuario usuario = usuarioManager.toUsuario(usuarioBean);
	
				//Creamos un logGenerico y lo cargamos con los datos correspondientes
				LogGenerico logGenerico = new LogGenerico();
				Date dHoy = new Date();
					
				logGenerico.setFecha(dHoy);
				logGenerico.setTipoOperacion(TIPO_OPERACION);
				logGenerico.setTipoFuncionalidad(TIPO_FUNCIONALIDAD);
				logGenerico.setUsuario(usuario); 
				logGenerico.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.centroGestor.detalleOperacionModificacion") + " " + modeloBean.getId());
				logGenerico.setIdTablaOrigen(Long.valueOf(modeloBean.getId()));
					
				//Guardamos el log
				logGenericoManager.guardarLogGenerico(logGenerico);			
					
				if (STRING_ERROR.equals(accion)){
					String mensaje = RESOURCE_BUNDLE.getString("field.gestionModelos790.modificarModelo790.mensajeError");
					String titulo = RESOURCE_BUNDLE.getString("field.gestionModelos790.tituloMantenimientoModelo790");
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					setRequestAttribute("accion","/spring/verModificarModelosGestion790?id="+formulario.getId());
				}else{
					String mensaje = RESOURCE_BUNDLE.getString("field.gestionModelos790.modificarModeloConfirmacion");
					String titulo = RESOURCE_BUNDLE.getString("field.gestionModelos790.tituloMantenimientoModelo790");
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					setRequestAttribute("accion","/spring/buscarGestionModelos790");
				}
					
				resultado = "success";
			}else{
				resultado = STRING_ERROR;
			}
	
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarModelo790Spring - doExecute:" ,e);
			return "nosuccess";
		}
	}
	
	private String recuperarUsuario() {
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			return usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error ModificarModelo790Spring - recuperarUsuarioSesion:" ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return STRING_ERROR;
		}
	}
	
	/**
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
	public ModeloManager getModeloManager() {
		return modeloManager;
	}
	
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
	}

	/**
	 * @return the logGenericoManager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * @param logGenericoManager the logGenericoManager to set
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}

	public static Logger getLogger() {
		return logger;
	}
	
}
