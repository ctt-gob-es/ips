package es.map.ipsg.action.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.LogGenerico;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class CrearModelos790Spring.
 */
public class CrearModelos790Spring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearModelos790Spring.class);
	
	/** el tipo operacion. */
	private final Character TIPO_OPERACION = 'A';
	
	/** La constante TIPO_FUNCIONALIDAD. */
	private static final String TIPO_FUNCIONALIDAD = "CENTRO GESTOR";

	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;


	/**
	 * Crea una nueva crear modelos 790 spring.
	 */
	public CrearModelos790Spring() {
		try {
			setModeloManager((ModeloManager) getBean("modelosManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("CrearModelos790Spring:",e);
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
		
		getLogger().debug("CrearModelos790Spring -start");
		ModeloGestion790Form formulario = (ModeloGestion790Form) form;
		try{
			String resultado = "";
			ModeloBean modeloBean = new ModeloBean();
			modeloBean.setDescripcion(formulario.getDescripcion());
			
			Date fechaAlta = new Date();
			modeloBean.setFechaAlta(fechaAlta);
			modeloBean.setNumModelo(formulario.getNumModelo());
			int result = 0;
			String mensaje;	
			
			//comprobamos que el numero de modelo no existe para crearlo
			ModeloQuery modeloQuery = new ModeloQuery();
			modeloQuery.setNumModelo(formulario.getNumModelo());
			ArrayList<ModeloBean> modeloBeanAux=modeloManager.buscarModelosAll(modeloQuery);
			//si no se encuentras modelos se guarda el modelo creado
			if(modeloBeanAux!=null && modeloBeanAux.size()==0){
				result=modeloManager.guardarModelo(modeloBean);
			}else{
				//si existen modelos damos mensaje de error
				resultado = "errorDuplicidad";
				mensaje = RESOURCE_BUNDLE.getString("field.gestionModelos790.crearModelo790Error");
				String titulo = RESOURCE_BUNDLE.getString("field.gestionModelos790.tituloModelo790");
				setRequestAttribute("titulo", titulo);
				setRequestAttribute("mensaje", mensaje);
				setRequestAttribute("accion", "/spring/buscarGestionModelos790");
			}
			
			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			String username = recuperarUsuario();
			
			if (username.equals("error")) {
				return username;
			}
			
			usuarioQuery.setLogin(username);
			
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			Usuario usuario = usuarioManager.toUsuario(usuarioBean);
			
			//Registramos en el log la operacion realizada
			LogGenerico logGenerico = new LogGenerico();
			Date dHoy = new Date();
	
			logGenerico.setFecha(dHoy);
			logGenerico.setTipoOperacion(TIPO_OPERACION);
			logGenerico.setTipoFuncionalidad(TIPO_FUNCIONALIDAD);
			logGenerico.setUsuario(usuario); 
			logGenerico.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.gestionModelos790.detalleOperacionAlta") + " " + result);
			logGenerico.setIdTablaOrigen(Long.valueOf(String.valueOf(result)));			
			
			//Guardamos el log
			logGenericoManager.guardarLogGenerico(logGenerico);					

			if (result > 0) {
				resultado = "success";
				mensaje = RESOURCE_BUNDLE.getString("field.gestionModelos790.crearModelo790Confirmacion");
				String titulo = RESOURCE_BUNDLE.getString("field.gestionModelos790.tituloModelo790");
				setRequestAttribute("titulo", titulo);
				setRequestAttribute("mensaje", mensaje);
				setRequestAttribute("accion", "/spring/verModificarModelosGestion790?id="+result);
				//se redirecciona a modificar modelo para poder asignar los campos propios 
			} 
			
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
			logger.error("CrearModelos790Spring - doExecute " ,e);
			return "nosuccess";
		}
		
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			return usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("CrearModelos790Spring - recuperarUsuarioSesion:" ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
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
	 * Obtiene el message resource.
	 *
	 * @return el message resource
	 */
	public static String getMessageResource() {
		return MESSAGE_RESOURCE;
	}


	/**
	 * Obtiene el resource bundle.
	 *
	 * @return el resource bundle
	 */
	public static ResourceBundle getResourceBundle() {
		return RESOURCE_BUNDLE;
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}