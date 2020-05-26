package es.map.ipsg.action.configuracionErrores;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ConfiguracionErroresBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.ConfiguracionErroresManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * 
 * Clase que implemente EliminarCentroGestorAction
 * @author amartinl
 *
 */
public class EliminarConfiguracionErroresSpring extends AbstractSpring {

	private static final String MESSAGE_RESOURCE = "MessageResources";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	private static final Logger logger = Logger.getLogger(EliminarConfiguracionErroresSpring.class);
	
	
	//Declaracion de manager
	private ConfiguracionErroresManager configuracionErroresManager;
	private UsuarioManager usuarioManager;
	private LogGenericoManager logGenericoManager;
	
	public EliminarConfiguracionErroresSpring() {
		try {
			setConfiguracionErroresManager((ConfiguracionErroresManager) getBean("configuracionErroresManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarConfiguracionErroresSpring() :",e);
		}
	}

/**
 * Metodo doExecute de EliminarConfiguracionErroresAction
 * @param form SpringForm
 * @return resultado String Si no tiene errores nos devuelve success
 * @throws Exception Exception
 * 
 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_configuracionErrores = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.configuracionErrores");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_configuracionErrores);
		
		getLogger().debug("EliminarConfiguracionErroresSpring -start");
		String resultado;		
	try{	
		String idConfiguracionErrores=  this.getRequest().getParameter("id");
		
		if(idConfiguracionErrores != null){
			//Obtenemos la forma de acceso que deseamos eliminar
			ConfiguracionErroresBean configuracionErroresBean;
			configuracionErroresBean = configuracionErroresManager.obtenerConfiguracionError(Integer.valueOf(idConfiguracionErrores));
			
			//if (configuracionErroresBean.getVisibilidad() == true){
			configuracionErroresBean.setVisibilidad(!configuracionErroresBean.getVisibilidad());
//			}
//			else{
//				configuracionErroresBean.setVisibilidad(true);
//			}
			configuracionErroresManager.modificarConfiguracionError(configuracionErroresBean); //Guardamos el cambio de estado en la BBDD
			AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
			generarRegistroLogGenerico(user.getIdentificador(), Long.valueOf(configuracionErroresBean.getId()), configuracionErroresBean.getVisibilidad());
			
			String mensaje = RESOURCE_BUNDLE.getString("field.configuracionErrores.eliminarConfiguracionErroresConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.configuracionErrores.titulo.EliminarConfiguracionErrores");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarConfiguracionErrores");
			
			getLogger().debug("EliminarConfiguracionErroresSpring -end");
			resultado = "success";
		}else{
			resultado = "error";
		}
		
	}catch(Exception eGenerico){
		logger.error("Error EliminarConfiguracionErroresSpring() :",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return resultado;
	}
	
public void generarRegistroLogGenerico(String username, Long idConfiguracionErrores, Boolean visibilidad){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CONFIGURACION_ERRORES);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.configuracionErrores.detalleOperacionBaja") + " " + idConfiguracionErrores.toString() + RESOURCE_BUNDLE.getString("field.configuracionErrores.detalleOperacionEstado") + visibilidad);
		logGenericoBean.setIdTablaOrigen(idConfiguracionErrores);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * @return the configuracionErroresManager
	 */
	public ConfiguracionErroresManager getConfiguracionErroresManager() {
		return configuracionErroresManager;
	}


	/**
	 * @param configuracionErroresManager the configuracionErroresManager to set
	 */
	public void setConfiguracionErroresManager(ConfiguracionErroresManager configuracionErroresManager) {
		this.configuracionErroresManager = configuracionErroresManager;
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
