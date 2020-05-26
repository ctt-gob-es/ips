package es.map.ipsg.action.solicitudPresencial;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.TipoPagoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.TipoPagoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CrearEntidadFinancieraForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class CrearSolicitudPresencialSpring.
 */
public class CrearSolicitudPresencialSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearSolicitudPresencialSpring.class);
	
	/** La constante ACTUALIZADA_N. */
	private static final Character ACTUALIZADA_N = 'N';

	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva crear solicitud presencial spring.
	 */
	public CrearSolicitudPresencialSpring() {
		try {
			setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearSolicitudPresencialSpring: ", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		
		String mensaje, resultado;
		int result = 0;
		SpringMessages messages;
	try{	
		getLogger().debug("CrearSolicitudPresencialSpring -start");
		CrearEntidadFinancieraForm formulario = (CrearEntidadFinancieraForm) form;		
		EntidadFinancieraBean entidadFinancieraBean = new EntidadFinancieraBean();
		
		//Cargamos el combo del tipo de pago
		TipoPagoQuery  tipoPagoQuery;
		List<TipoPagoBean> lTipoPagoBean;
	
		
		//Alta de Especialidad			
		entidadFinancieraBean.setCodigo(formulario.getCodigo());
		entidadFinancieraBean.setDescripcion(formulario.getDescripcion());
		entidadFinancieraBean.setActualizada(ACTUALIZADA_N);
		if (formulario.getIdTipoPago() != null) {
			entidadFinancieraBean.setIdTipoPago(Integer.valueOf(formulario.getIdTipoPago()));
		}
		entidadFinancieraBean.setDesTipoPago(formulario.getDesTipoPago());
		if (formulario.getEstado() != null) {
			entidadFinancieraBean.setEstado(formulario.getEstado());
		}		
		

		
		if(result > 0){
			resultado="success";
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(), Long.valueOf(result));
			mensaje = RESOURCE_BUNDLE.getString("field.entidadFinanciera.crearEntidadFinancieraConfirmacion");
		}else{
			resultado="error";
			mensaje = RESOURCE_BUNDLE.getString("field.entidadFinanciera.crearEntidadFinancieraError");
		}
		
		String titulo = RESOURCE_BUNDLE.getString("field.entidadFinanciera.tituloAltaEntidadFinanciera");
		
		// Devolvemos el valor del combo	
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/buscarEntidadFinanciera.do");
		getLogger().debug("CrearSolicitudPresencialSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error CrearSolicitudPresencialSpring - doExecute: ", eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_SOLICITUD);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion("Alta de la solicitud presencial " + resultado);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return el solicitudes registradas manager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudManager el nuevo solicitudes registradas manager
	 */
	public void setSolicitudesRegistradasManager(SolicitudesRegistradasManager solicitudManager) {
		this.solicitudManager = solicitudManager;
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
