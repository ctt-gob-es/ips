package es.map.ipsg.action.configuracionErrores;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ConfiguracionErroresBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ConfiguracionErroresForm;
import es.map.ipsg.manager.ConfiguracionErroresManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

public class CrearConfiguracionErroresSpring extends AbstractSpring {

	private static final String MESSAGE_RESOURCE = "MessageResources";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	private static final Logger logger = Logger.getLogger(CrearConfiguracionErroresSpring.class);
	
	private ConfiguracionErroresManager configuracionErroresManager;
	private UsuarioManager usuarioManager;
	private LogGenericoManager logGenericoManager;
		
	public CrearConfiguracionErroresSpring() {
		try {
			setConfiguracionErroresManager((ConfiguracionErroresManager) getBean("configuracionErroresManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearConfiguracionErroresSpring() :",e);
		}
	}

	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_ConfiguracionErrores = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.configuracionErrores");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ConfiguracionErrores);
		
		String mensaje;
		String resultado;
		int result = 0;
		SpringMessages messages = new SpringMessages();
		
		getLogger().debug("CrearConfiguracionErroresSpring -start");
	try{
		
		ConfiguracionErroresForm formulario = (ConfiguracionErroresForm) form;		
		ConfiguracionErroresBean configuracionErroresBean = new ConfiguracionErroresBean();

		//Alta
		configuracionErroresBean.setDescripcion(formulario.getDescripcion());
		if(formulario.getVisibilidad() != null)
		{	
			configuracionErroresBean.setVisibilidad(formulario.getVisibilidad());
		}
		else
		{
			configuracionErroresBean.setVisibilidad(false);
		}
					
		result = configuracionErroresManager.guardarConfiguracionError(configuracionErroresBean);
		
		if(result > 0){
			resultado="success";
			AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
			generarRegistroLogGenerico(user.getIdentificador(), Long.valueOf(result));
			mensaje = RESOURCE_BUNDLE.getString("field.configuracionErrores.crearConfiguracionErroresConfirmacion");
		}else{
			resultado="error";
			mensaje = RESOURCE_BUNDLE.getString("field.configuracionErrores.crearConfiguracionErroresError");
		}
		
		String titulo = RESOURCE_BUNDLE.getString("field.configuracionErrores.tituloAltaConfiguracionErrores");
			
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/buscarConfiguracionErrores");
		getLogger().debug("CrearConfiguracionErroresSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error CrearConfiguracionErroresSpring() - doExecute :",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
	
		return resultado;
	}
	
	public void generarRegistroLogGenerico(String username, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CONFIGURACION_ERRORES);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.configuracionErrores.detalleOperacionAlta") + " "  + resultado);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}
	
	public ConfiguracionErroresManager getConfiguracionErroresManager() {
		return configuracionErroresManager;
	}

	public void setConfiguracionErroresManager(ConfiguracionErroresManager configuracionErroresManager) {
		this.configuracionErroresManager = configuracionErroresManager;
	}	

	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}

	public static Logger getLogger() {
		return logger;
	}
	
	
}
