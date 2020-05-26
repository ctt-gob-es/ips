package es.map.ipsg.action.usuariosAplicacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioAplicacionQuery;
import es.map.ipsg.bean.UsuarioAplicacionBean;
import es.map.ipsg.form.UsuariosAplicacionForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioAplicacionManager;

/**
 * El Class ModificarUsuarioAplicacionSpring.
 */
public class ModificarUsuarioAplicacionSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarUsuarioAplicacionSpring.class);
	
	/** el usuario aplicacion manager. */
	private UsuarioAplicacionManager usuarioAplicacionManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/**
	 * Crea una nueva modificar usuario aplicacion spring.
	 */
	public ModificarUsuarioAplicacionSpring() {
		try {
			setUsuarioManager((UsuarioAplicacionManager) getBean("usuarioAplicacionManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		}catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarUsuarioAplicacionSpring - crear constructor",e);
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
		
		getLogger().debug("ModificarUsuarioAplicacionSpring -start");
		logger.info("Entra en el Action ModificarUsuarioAplicacionAction");
		String resultado;
		try{
			UsuariosAplicacionForm formulario = (UsuariosAplicacionForm) form;
			String idUsuario = formulario.getId().toString();
			String usuario = formulario.getUsuario();
			boolean existeUsuario = false;
			existeUsuario = existeUsuario(usuario, formulario);
			
			if(!existeUsuario) {
				if(idUsuario!=null) {
					UsuarioAplicacionQuery usuarioAplicacionQuery = new UsuarioAplicacionQuery();
					usuarioAplicacionQuery.setIdUsuario(Integer.valueOf(formulario.getId()));
					
					UsuarioAplicacionBean usuarioAplicacionBean = new UsuarioAplicacionBean();
					
					usuarioAplicacionBean.setId(Integer.valueOf(formulario.getId()));
					usuarioAplicacionBean.setEstado(formulario.getEstado().charAt(0));
					usuarioAplicacionBean.setUsuario(formulario.getUsuario());
					usuarioAplicacionBean.setNombre(formulario.getNombre().toUpperCase());
					usuarioAplicacionBean.setDescripcion(formulario.getDescripcion().toUpperCase());
					usuarioAplicacionBean.setFecha(new Date());
					
					try {
						usuarioAplicacionManager.modificarUsuario(usuarioAplicacionBean);
						
						String mensaje = RESOURCE_MESSAGE.getString("field.usuario.modificarUsuarioConfirmacion");
						String titulo = RESOURCE_MESSAGE.getString("field.usuario.tituloMantenimientoUsuario");			
						
						setRequestAttribute("titulo",titulo);
						setRequestAttribute("mensaje",mensaje);
						setRequestAttribute("accion","/spring/buscarUsuariosAplicacion");
						
						resultado = "success";
					}catch(BusinessException be){
						logger.error("Error ModificarUsuarioSpring - obtener usuario a modificar",be);
						SpringMessages messages = new SpringMessages();
						messages.add("errorModificarUsuario",new SpringMessage(be.getKey()));
						saveErrors(this.getRequest(),messages);
						
						resultado = "show";
					}			
				}else {
					resultado = "error";
				}
			}else {
				SpringMessages messages = new SpringMessages();
				messages.add("errorExisteUsuario",new SpringMessage("usuario.error.Usuario",usuario));
				saveErrors(this.getRequest(),messages);
				
				resultado = "show";
			}
			logger.info("resultado: "+resultado);
			getLogger().debug("ModificarUsuarioAplicacionSpring -end");
			return resultado;
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarUsuarioAplicacionSpring - doExecute ",e);
			return "nosuccess";
			}
	
	}
	
	/**
	 * Existe usuario.
	 *
	 * @param usuario el usuario
	 * @param formulario el formulario
	 * @return verdadero, si tiene exito
	 */
	private boolean existeUsuario(String usuario, UsuariosAplicacionForm formulario) {
		UsuarioAplicacionQuery usuarioAplicacionQuery = new UsuarioAplicacionQuery();
		usuarioAplicacionQuery.setUsuarioAplicacion(usuario);
		ArrayList<UsuarioAplicacionBean> usuAplicacionAux = (ArrayList<UsuarioAplicacionBean>) usuarioAplicacionManager.buscarUsuariosAplicacion(usuarioAplicacionQuery);
		if(usuAplicacionAux!= null && usuAplicacionAux.size()>0) {
			for(int i=0;i<usuAplicacionAux.size();i++) {
				if(!usuAplicacionAux.get(i).getId().toString().equals(formulario.getId().toString())) {
					return true;
				}
			}
		}
		return false;
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
	private void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
		
	}
	
	/**
	 * Obtiene el usuario aplicacion manager.
	 *
	 * @return el usuario aplicacion manager
	 */
	public UsuarioAplicacionManager getUsuarioAplicacionManager() {
		return usuarioAplicacionManager;
	}
	
	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioAplicacionManager el nuevo usuario manager
	 */
	private void setUsuarioManager(UsuarioAplicacionManager usuarioAplicacionManager) {
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
	
}
