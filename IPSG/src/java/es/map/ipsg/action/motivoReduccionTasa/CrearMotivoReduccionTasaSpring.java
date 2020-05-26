package es.map.ipsg.action.motivoReduccionTasa;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.MotivoReduccionTasaForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class CrearMotivoReduccionTasaSpring.
 */
public class CrearMotivoReduccionTasaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearMotivoReduccionTasaSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';

	/** el motivo manager. */
	private MotivoReduccionTasaManager motivoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/**
	 * Crea una nueva crear motivo reduccion tasa spring.
	 */
	public CrearMotivoReduccionTasaSpring() {
		try {
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
			
		} catch (Exception e) {
			logger.error("Error CrearMotivoReduccionTasaSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_motivos = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.motivoReduccion");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_motivos);
		
		MotivoReduccionTasaForm formulario = (MotivoReduccionTasaForm) form;			
		MotivoReduccionTasaBean motivoBean= new MotivoReduccionTasaBean();
		String resultado;
	try{		
		

		
		//Alta de CuerposEscala
		motivoBean.setDescripcion(formulario.getDescripcion());
		motivoBean.setTextoExplicativo(formulario.getTextoExplicativo());
		motivoBean.setPorcentajeDescuento(Integer.parseInt(formulario.getPorcentajeDescuento()));
		motivoBean.setEstado(ESTADO_ACTIVO);
		motivoBean.setCodigo(formulario.getCodigo());
		motivoBean.setVisibilidad(formulario.getVisibilidad());
		
		int result=0;
					
		result=motivoManager.guardarMotivoReduccionTasa(motivoBean);
			
		String mensaje;
		
		
		if(result>0){
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(),motivoBean.getDescripcion(), Long.valueOf(result));
			resultado="success";
			mensaje = RESOURCE_BUNDLE.getString("field.motivoReduccionTasa.crearMotivoConfirmacion");
		}else{
			resultado="error";
			mensaje = RESOURCE_BUNDLE.getString("field.motivoReduccionTasa.crearMotivoError");
		}
		
		String titulo = RESOURCE_BUNDLE.getString("field.motivoReduccionTasa.tituloCrearMotivo");
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/buscarMotivoReduccionTasa");

	}catch(Exception eGenerico){
		logger.error("Error CrearMotivoReduccionTasaSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}
	
/**
 * Generar registro log generico.
 *
 * @param username el username
 * @param motivoDesc el motivo desc
 * @param resultado el resultado
 */
public void generarRegistroLogGenerico(String username, String motivoDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_MOTIVO_REDUCCION_TASA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.motivoReduccionTasa.detalleOperacionAlta") + " "  + motivoDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}



	/**
	 * Obtiene el motivo manager.
	 *
	 * @return the motivoManager
	 */
	public MotivoReduccionTasaManager getMotivoManager() {
		return motivoManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoManager the motivoManager to set
	 */
	public void setMotivoReduccionTasaManager(MotivoReduccionTasaManager motivoManager) {
		this.motivoManager = motivoManager;
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
