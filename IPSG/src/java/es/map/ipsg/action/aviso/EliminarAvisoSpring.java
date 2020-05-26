package es.map.ipsg.action.aviso;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.AvisoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;

import es.map.ipsg.manager.AvisoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarAvisoSpring.
 */
public class EliminarAvisoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarAvisoSpring.class);
	
	/** La constante STRING_NUM_REGISTRO. */
	private static final String STRING_NUM_REGISTRO = "numRegistro";

	/** el aviso manager. */
	private AvisoManager avisoManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
		
	/**
	 * Crea una nueva eliminar aviso spring.
	 */
	public EliminarAvisoSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setAvisoManager((AvisoManager) getBean("avisoManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
		} catch (final Exception e) {
			logger.warn(e);
			logger.error("Error EliminarAvisoSpring():",e );
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	protected String doExecute(final SpringForm form) throws Exception {
		
		String menu_aviso = RESOURCE_BUNDLE.getString("field.menu.avisos");
		this.getRequest().getSession().setAttribute("pagActiva", menu_aviso);
		String subMenu_aviso = RESOURCE_BUNDLE.getString("field.menuLateral.avisos.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_aviso);
		
		logger.info("EliminarAvisoSpring -start");
	try{
		 SpringMessages errors = new SpringMessages();
		 String idAviso = this.getRequest().getParameter("id");
		 	
		 AvisoBean avisoBean = avisoManager.obtenerAviso(Integer.valueOf(idAviso));
		 
		 setRequestAttribute(STRING_NUM_REGISTRO, this.getRequest().getParameter(STRING_NUM_REGISTRO));	
		 if(avisoBean.getIdEstadoAviso().toString().equals(String.valueOf(Constantes.ESTADO_AVISO_PUBLICADO))){
			errors.add("dsErrorEliminar", new SpringMessage("field.aviso.errores.EliminarPublicado",idAviso));
			
			this.setRequestAttribute("org.apache.spring.ERROR", errors);
			
			getLogger().debug("EliminarAvisoAction -Error Aviso Publicado");
			
			return "errorEliminar";	
			
		}
		 avisoBean.setIdEstadoAviso(Integer.valueOf(Constantes.ESTADO_AVISO_ELIMINADO));
		 boolean bElimina = true;
		 
		
		
		 
				
		if (bElimina){
			avisoManager.modificarAviso(avisoBean);
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			 generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(avisoBean.getIdAviso()));
		}
		else{
			saveErrors(this.getRequest().getSession(),errors);
			return "errorEliminar";
		}
						
		final String mensaje = RESOURCE_BUNDLE.getString("field.aviso.eliminarAlertaConfirmacion");
		final String titulo = RESOURCE_BUNDLE.getString("field.aviso.eliminar.titulo");			
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/buscarAviso");
		logger.info("EliminarAvisoSpring -end");
		
	}catch(Exception eGenerico){
		
		logger.error("Error EliminarAviso - doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idAlerta el id alerta
	 */
	public void generarRegistroLogGenerico(final String username, final Long idAlerta){
		
		final UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		final UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		final LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_AVISO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion("Baja aviso " + idAlerta);
		logGenericoBean.setIdTablaOrigen(idAlerta);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Obtiene el aviso manager.
	 *
	 * @return the avisoManager
	 */
	public AvisoManager getAvisoManager() {
		return avisoManager;
	}

	/**
	 * Establece el aviso manager.
	 *
	 * @param avisoManager the avisoManager to set
	 */
	public void setAvisoManager(AvisoManager avisoManager) {
		this.avisoManager = avisoManager;
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
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}



}
