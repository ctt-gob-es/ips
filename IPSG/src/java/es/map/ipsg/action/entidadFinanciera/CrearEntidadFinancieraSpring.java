package es.map.ipsg.action.entidadFinanciera;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Formatter;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.TipoPagoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.TipoPagoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CrearEntidadFinancieraForm;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class CrearEntidadFinancieraSpring.
 */
public class CrearEntidadFinancieraSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearEntidadFinancieraSpring.class);
	
	/** La constante ACTUALIZADA_N. */
	private static final Character ACTUALIZADA_N = 'N';

	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/**
	 * Crea una nueva crear entidad financiera spring.
	 */
	public CrearEntidadFinancieraSpring() {
		try {
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearEntidadFinancieraSpring() :",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_EntidadFinanciera = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.entidadFinanciera");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_EntidadFinanciera);
		
		String mensaje;
		String resultado;
		int result = 0;
		SpringMessages messages = new SpringMessages();
		
		getLogger().debug("CrearEntidadFinancieraSpring -start");
	try{
		
		CrearEntidadFinancieraForm formulario = (CrearEntidadFinancieraForm) form;		
		EntidadFinancieraBean entidadFinancieraBean = new EntidadFinancieraBean();
		
		//Cargamos el combo del tipo de pago
		TipoPagoQuery  tipoPagoQuery = new TipoPagoQuery();
		List<TipoPagoBean> lTipoPagoBean;
		lTipoPagoBean = tipoPagoManager.buscarTipoPagoCombo(tipoPagoQuery);		
		
		//Alta de Especialidad			
		entidadFinancieraBean.setDescripcion(formulario.getDescripcion());
		/*entidadFinancieraBean.setCodigo(formulario.getCodigo());*/
		entidadFinancieraBean.setActualizada(ACTUALIZADA_N);
		if (formulario.getIdTipoPago() != null) {
			entidadFinancieraBean.setIdTipoPago(Integer.valueOf(formulario.getIdTipoPago()));
		}
		entidadFinancieraBean.setDesTipoPago(formulario.getDesTipoPago());
		if (formulario.getEstado() != null) {
			entidadFinancieraBean.setEstado(formulario.getEstado());
		}
		
		if(existeCodigo(formulario.getCodigo())){
			logger.info("El codigo ya existe");
			messages.add("errorCodigoDuplicado",new SpringMessage("field.entidadFinanciera.error.codigo.duplicado"));
			saveErrors(this.getRequest(),messages);
		} else {
			String newCode = cargarCodigo(formulario.getCodigo());
			entidadFinancieraBean.setCodigo(newCode);
			result = entidadFinancieraManager.guardarEntidadFinanciera(entidadFinancieraBean);
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
		setRequestAttribute("tipoPago", lTipoPagoBean);		
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/buscarEntidadFinanciera");
		getLogger().debug("CrearEntidadFinancieraSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error CrearEntidadFinancieraSpring() - doExecute :",eGenerico);
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
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_ENTIDAD_FINANCIERA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.entidadFinanciera.detalleOperacionAlta") + " "  + resultado);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}
	
	/**
	 * Existe codigo.
	 *
	 * @param codigo el codigo
	 * @return verdadero, si tiene exito
	 */
	private boolean existeCodigo(String codigo){
		List<EntidadFinancieraBean> entidadFinancieraBean = null;
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		
		if(!StringUtils.isEmpty(codigo) && codigo.length()<4) {
			String newCode = cargarCodigo(codigo);
			entidadFinancieraQuery.setCodigo(newCode);
			
		}else {
			entidadFinancieraQuery.setCodigo(codigo);
		}
				
		entidadFinancieraBean = entidadFinancieraManager.buscarEntidadFinancieraCombo(entidadFinancieraQuery);
		return entidadFinancieraBean.size()==0 ? false:true;
	}

	/**
	 * Cargar codigo.
	 *
	 * @param codigo el codigo
	 * @return el string
	 */
	private String cargarCodigo(String codigo) {
		String code = codigo;
		String newCode = "";
		StringBuilder sb = new StringBuilder(code);
		while(sb.length()<4) {
			sb.insert(0, '0');
		}
		newCode = sb.toString();
		return newCode;
	}

	/**
	 * Obtiene el entidad financiera manager.
	 *
	 * @return el entidad financiera manager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager el nuevo entidad financiera manager
	 */
	public void setEntidadFinancieraManager(EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
	}
	
	/**
	 * Obtiene el tipo pago manager.
	 *
	 * @return el tipo pago manager
	 */
	public TipoPagoManager getTipoPagoManager() {
		return tipoPagoManager;
	}

	/**
	 * Establece el tipo pago manager.
	 *
	 * @param tipoPagoManager el nuevo tipo pago manager
	 */
	public void setTipoPagoManager(TipoPagoManager tipoPagoManager) {
		this.tipoPagoManager = tipoPagoManager;
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
