package es.map.ipsg.action.entidadFinanciera;

import java.util.List;
import java.util.ResourceBundle;

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
import es.map.ipsg.form.ModificarEntidadFinancieraForm;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ModificarEntidadFinancieraSpring.
 */
public class ModificarEntidadFinancieraSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarEntidadFinancieraSpring.class);
	
	/** el entidad financiera manager. */
	//Declaracion de manager
	private EntidadFinancieraManager entidadFinancieraManager;	
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;		
	
	/**
	 * Crea una nueva modificar entidad financiera spring.
	 */
	public ModificarEntidadFinancieraSpring() {
		try {
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarEntidadFinancieraSpring():",e);
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
		
		getLogger().debug("ModificarEntidadFinancieraSpring -start");
		ModificarEntidadFinancieraForm formulario = (ModificarEntidadFinancieraForm) form;

		String resultado = "success";
	try{	
		// Cargamos el combo del tipo de pago
		TipoPagoQuery  tipoPagoQuery = new TipoPagoQuery();
		List<TipoPagoBean> lTipoPagoBean;
		lTipoPagoBean = tipoPagoManager.buscarTipoPagoCombo(tipoPagoQuery);		
		EntidadFinancieraBean entidadFinancieraBean = new EntidadFinancieraBean();
		SpringMessages messages = new SpringMessages();
		
		
		if(existeCodigo(formulario.getId(), formulario.getCodigo())){
			logger.info("El codigo ya existe");
			messages.add("errorCodigoDuplicado",new SpringMessage("field.entidadFinanciera.errores.codigo.duplicado"));
			resultado = "show";
		}

		if (resultado.compareTo("success") == 0) {
			// Recuperamos los datos del formulario para guardarlos.
			String newCode = cargarCombo(formulario.getCodigo());
			entidadFinancieraBean.setCodigo(newCode);
			entidadFinancieraBean.setId(formulario.getId());
			entidadFinancieraBean.setDescripcion(formulario.getDescripcion());
			entidadFinancieraBean.setActualizada(formulario.getActualizada());
			entidadFinancieraBean.setIdTipoPago(formulario.getIdTipoPago());
			entidadFinancieraBean.setEstado(formulario.getEstado());
			
			String mensaje;
			
			try{
				entidadFinancieraManager.modificarEntidadFinanciera(entidadFinancieraBean);
				mensaje = RESOURCE_BUNDLE.getString("field.entidadFinanciera.errores.guardadoCorrecto");
				String titulo = RESOURCE_BUNDLE.getString("field.entidadFinanciera.modificacion.titulo");
				
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(entidadFinancieraBean.getId()));				
				
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarEntidadFinanciera");
			}catch(Exception e){
				logger.error("Error ModificarEntidadFinancieraSpring() - doExecute:",e);
				resultado="show";
			}
		}else{
			saveErrors(this.getRequest(),messages);
		}
		
		// Devolvemos el valor del combo
		setRequestAttribute("tipoPago", lTipoPagoBean);
		
		getLogger().debug("ModificarEntidadFinancieraSpring -end");
	
	}catch(Exception eGenerico){
		logger.error("Error ModificarEntidadFinancieraSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}

	/**
	 * Existe codigo.
	 *
	 * @param id el id
	 * @param codigo el codigo
	 * @return verdadero, si tiene exito
	 */
	public boolean existeCodigo(Integer id, String codigo) {
		List<EntidadFinancieraBean> lEntidadFinancieraBean = null;
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		
		if(!StringUtils.isEmpty(codigo) && codigo.length()<4) {
			String newCode = cargarCombo(codigo);
			entidadFinancieraQuery.setCodigo(newCode);
			
		}else {
			entidadFinancieraQuery.setCodigo(codigo);
		}
				
		lEntidadFinancieraBean = entidadFinancieraManager.buscarEntidadFinancieraCombo(entidadFinancieraQuery);
		
		// Comprobamos si el código que estamos modificando es de la entidad financiera actual
		if (lEntidadFinancieraBean.size() == 0) {
			return false;
		} else if (lEntidadFinancieraBean.size() == 1) {
			if (lEntidadFinancieraBean.get(0).getId().equals(id)) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * Cargar combo.
	 *
	 * @param codigo el codigo
	 * @return el string
	 */
	private String cargarCombo(String codigo) {
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
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idProvincia el id provincia
	 */
	public void generarRegistroLogGenerico(String username, Long idProvincia){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_ENTIDAD_FINANCIERA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_MODIFICACION);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.entidadFinanciera.detalleOperacionModificacion") + " "  + idProvincia.toString());
		logGenericoBean.setIdTablaOrigen(idProvincia);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
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
