package es.map.ipsg.action.usuariosAplicacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.model.UsuarioAplicacionQuery;
import es.map.ipsg.bean.UsuarioAplicacionBean;
import es.map.ipsg.form.UsuariosAplicacionForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioAplicacionManager;

/**
 * El Class AltaUsuarioAplicacionSpring.
 */
public class AltaUsuarioAplicacionSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaUsuarioAplicacionSpring.class);
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** el usuario aplicacion manager. */
	private UsuarioAplicacionManager usuarioAplicacionManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/**
	 * Crea una nueva alta usuario aplicacion spring.
	 */
	public AltaUsuarioAplicacionSpring() {
		try {
			setUsuarioAplicacionManager((UsuarioAplicacionManager) getBean("usuarioAplicacionManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		}catch (Exception e) {
			logger.warn(e);
			logger.error("Error AltaUsuarioAplicacionSpring - crear constructor",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_usuario = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.usuario");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_usuario);
		
		getLogger().debug("AltaUsuarioAplicacionSpring -start");
		
		String mensaje = null;
		
		UsuarioAplicacionBean usuarioAplicacionBean = new UsuarioAplicacionBean();
		
		String resultado;
		
		try {
			UsuariosAplicacionForm formulario = (UsuariosAplicacionForm) form;
			String usuario = formulario.getUsuario();
			String accion = formulario.getAccion();
			boolean existeUsuario = false;
			existeUsuario = existeUsuario(usuario);
			
			if("VOLVER".equalsIgnoreCase(accion)) {
				resultado = "list";
			}else if(!existeUsuario) {				
				usuarioAplicacionBean.setUsuario(formulario.getUsuario());
				usuarioAplicacionBean.setNombre(formulario.getNombre().toUpperCase());
				usuarioAplicacionBean.setDescripcion(formulario.getDescripcion().toUpperCase());
				usuarioAplicacionBean.setEstado(formulario.getEstado().charAt(0));
				usuarioAplicacionBean.setFecha(new Date());
				
			try {
				int result=0;			
				result = usuarioAplicacionManager.guardarUsuarioAplicacion(usuarioAplicacionBean);
				
				if(result>0) {
					resultado="success";
					mensaje = RESOURCE_MESSAGE.getString("field.usuariosaplicacion.altausuarioaplicacionConfirmacion");
				}else{
					resultado=STRING_ERROR;
					mensaje = RESOURCE_MESSAGE.getString("field.usuariosaplicacion.tituloalta");
			}
			
				String titulo = RESOURCE_MESSAGE.getString("field.usuariosaplicacion.tituloAltaUsuarioAplicacion");
			
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarUsuariosAplicacion");
			}catch(BusinessException be){
				logger.error("Error AltaUsuarioSpring - error guardando usuario",be);
				resultado = STRING_ERROR;
				mensaje = be.getMessage();
			}

			}else {
				SpringMessages messages = new SpringMessages();
				messages.add("errorExisteUsuario",new SpringMessage("usuario.error.Usuario",usuario));
				saveErrors(this.getRequest(),messages);
				
				resultado = "show";
			}
			getLogger().debug("AltaCuerposEscalaSpring -end");
			return resultado;
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error AltaUsuarioAplicacionSpring()- doExecute:",e);
			return "nosucess";
		}
	}
	
	/**
	 * Existe usuario.
	 *
	 * @param usuario el usuario
	 * @return verdadero, si tiene exito
	 */
	private boolean existeUsuario(String usuario) {
		UsuarioAplicacionQuery usuarioAplicacionQuery = new UsuarioAplicacionQuery();
		usuarioAplicacionQuery.setUsuarioAplicacion(usuario);
		ArrayList<UsuarioAplicacionBean> usuAplicacionAux = (ArrayList<UsuarioAplicacionBean>) usuarioAplicacionManager.buscarUsuariosAplicacion(usuarioAplicacionQuery);
		if(usuAplicacionAux!= null && usuAplicacionAux.size()>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Obtiene el usuario aplicacion manager.
	 *
	 * @return the UsuarioAplicacionManager
	 */
	public UsuarioAplicacionManager getUsuarioAplicacionManager() {
		return usuarioAplicacionManager;
	}

	/**
	 * Establece el usuario aplicacion manager.
	 *
	 * @param usuarioAplicacionManager el nuevo usuario aplicacion manager
	 */
	public void setUsuarioAplicacionManager(
			UsuarioAplicacionManager usuarioAplicacionManager) {
		this.usuarioAplicacionManager = usuarioAplicacionManager;
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
	
}
