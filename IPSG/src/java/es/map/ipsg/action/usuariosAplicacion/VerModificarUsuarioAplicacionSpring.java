package es.map.ipsg.action.usuariosAplicacion;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.UsuarioAplicacionBean;
import es.map.ipsg.form.UsuariosAplicacionForm;
import es.map.ipsg.manager.UsuarioAplicacionManager;

/**
 * El Class VerModificarUsuarioAplicacionSpring.
 */
public class VerModificarUsuarioAplicacionSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarUsuarioAplicacionSpring.class);
	
	/** el usuario aplicacion manager. */
	private UsuarioAplicacionManager usuarioAplicacionManager;
	
	/**
	 * Crea una nueva ver modificar usuario aplicacion spring.
	 */
	public VerModificarUsuarioAplicacionSpring() {
		try {
			setUsuarioAplicacionManager((UsuarioAplicacionManager) getBean("usuarioAplicacionManager"));
		}catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarUsuarioSpring - crear constructor",e);
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
		
		getLogger().debug("VerModificarUsuarioAplicacionSpring -start");
		logger.info("Entra en el Action VerModificarUsuarioAplicacionAction");
		String resultado;
		try {			
			UsuariosAplicacionForm formulario = (UsuariosAplicacionForm) form;
			
			String idUsuario = this.getRequest().getParameter("id");
			
			if(idUsuario!=null) {
				logger.info("Usuario a modificar/consultar: "+idUsuario);
				UsuarioAplicacionBean usuarioAplicacionBean = new UsuarioAplicacionBean();
				UsuarioAplicacionBean usuarioAplicacionLogin = new UsuarioAplicacionBean();
				if(!"Modificar".equals(formulario.getAccion())){
					usuarioAplicacionBean = usuarioAplicacionManager.obtenerUsuarioAplicacion(Integer.valueOf(idUsuario));		
					
					logger.info("Usuario: "+usuarioAplicacionBean.getUsuario());
					doExecuteAux(formulario,usuarioAplicacionBean);
				}else {
					int codUsuario = 0;
					try{
						codUsuario = formulario.getId();
					}catch(Exception e){
						logger.error("Error VerModificarUsuarioSpring - parsear codUsuario"+ codUsuario,e);
					}

					usuarioAplicacionBean.setId(codUsuario);
					usuarioAplicacionBean.setUsuario(formulario.getUsuario());
					usuarioAplicacionBean.setNombre(formulario.getNombre());
					usuarioAplicacionBean.setDescripcion(formulario.getDescripcion());
					usuarioAplicacionBean.setEstado(formulario.getEstado().charAt(0));
										
				}
				//this.setRequestAttribute("usuario", usuarioAplicacionLogin);	
			}else {
				resultado = "error";
			}
			
			resultado = "success";
			getLogger().debug("VerModificarUsuarioAplicacionSpring -end");
			return resultado;
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarUsuarioSpring - doExecute",e);
			return "nosuccess";
			}
	}
	
	/**
	 * Do execute aux.
	 *
	 * @param formulario el formulario
	 * @param usuarioAplicacionBean el usuario aplicacion bean
	 */
	private void doExecuteAux(UsuariosAplicacionForm formulario, UsuarioAplicacionBean usuarioAplicacionBean) {
		formulario.setUsuario(usuarioAplicacionBean.getUsuario().toString());
		formulario.setNombre(usuarioAplicacionBean.getNombre().toString());
		formulario.setDescripcion(usuarioAplicacionBean.getDescripcion().toString());
		formulario.setEstado(usuarioAplicacionBean.getEstado().toString());
		
	}



	/**
	 * Obtiene el usuario aplicacion manager.
	 *
	 * @return the especialidadManager
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

	
}
