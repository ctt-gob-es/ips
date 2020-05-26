package es.map.ipsg.action.tarifa;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.TarifaQuery;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.TarifaBean;
import es.map.ipsg.bean.TipoAccesoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.TarifaForm;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ModificarTarifaSpring.
 */
public class ModificarTarifaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarTarifaSpring.class);

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el tarifa manager. */
	private TarifaManager tarifaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el tipo acceso manager. */
	private TipoAccesoManager tipoAccesoManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;

	/**
	 * Crea una nueva modificar tarifa spring.
	 */
	public ModificarTarifaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setTarifaManager((TarifaManager) getBean("tarifaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarTarifaSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_cuerposEscala = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_cuerposEscala);
		String subMenu_tarifa = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.tarifa");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tarifa);
		
		getLogger().debug("ModificarTarifaSpring -start");
		String resultado;
	try{
		TarifaForm formulario = (TarifaForm) form;
		String idTarifa = formulario.getId().toString();
		

		GrupoQuery grupoQuery = new GrupoQuery();
		List<GrupoBean> grupo = grupoManager.buscarGrupoCombo(grupoQuery);

		TipoAccesoQuery tipoQuery = new TipoAccesoQuery();
		List<TipoAccesoBean> tipoAcceso = tipoAccesoManager
				.buscarTipoAccesoCombo(tipoQuery);

		this.setRequestAttribute("tipoAcceso", tipoAcceso);
		this.setRequestAttribute("grupo", grupo);

		if (idTarifa != null) {// Modificacion de Usuario

			TarifaBean tarifaBean = new TarifaBean();

			tarifaBean.setId(formulario.getId());
			tarifaBean.setDescripcion(formulario.getDescripcion());
			tarifaBean.setEjercicio(formulario.getEjercicio());
			if (formulario.getImporte() != null && !"".equals(formulario.getImporte()))
				tarifaBean
						.setImporte(Float.parseFloat(formulario.getImporte()));

			tarifaBean.setEstado(ESTADO_ACTIVO);

			if (formulario.getIdTipoAcceso() != null
					&& !("").equals(formulario.getIdTipoAcceso()))
				tarifaBean.setIdTipoAcceso(Integer.valueOf(formulario
						.getIdTipoAcceso()));

			if (formulario.getIdGrupo() != null
					&& !("").equals(formulario.getIdGrupo()))
				tarifaBean.setIdGrupo(Integer.valueOf(formulario.getIdGrupo()));

			if (compruebaTarifa(tarifaBean)) {
				tarifaManager.modificarTarifa(tarifaBean);
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioBean.getLogin(), tarifaBean
						.getId(), new Long(idTarifa));
			} else {
				SpringMessages errors = new SpringMessages();
				SpringMessage error = new SpringMessage(
						"field.tarifa.errores.tarifaRepetida");
				errors.add("errorCodigo", error);
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
				return "errorCodigo";
			}
			String mensaje = RESOURCE_BUNDLE
					.getString("field.tarifa.modificarTarifaConfirmacion");
			String titulo = RESOURCE_BUNDLE
					.getString("field.tarifa.tituloMantenimiento");

			setRequestAttribute("titulo", titulo);
			setRequestAttribute("mensaje", mensaje);
			setRequestAttribute("accion", "/spring/buscarTarifa");

			resultado = "success";
		} else {
			resultado = "error";
		}

		getLogger().debug("ModificarTarifaSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error ModificarTarifaSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return resultado;
	}

	// Compruebo que no hay otra tarifa activa con los mismos valores en
	/**
	 * Comprueba tarifa.
	 *
	 * @param tarifaBean el tarifa bean
	 * @return verdadero, si tiene exito
	 */
	// "grupo, tipo acceso y ejercicio"
	private boolean compruebaTarifa(TarifaBean tarifaBean) {

		TarifaQuery tarifaQuery = new TarifaQuery();

		tarifaQuery.addGrupoIn(tarifaBean.getIdGrupo());
		tarifaQuery.addTipoAccesoIn(tarifaBean.getIdTipoAcceso());
		tarifaQuery.setEjercicio(tarifaBean.getEjercicio());
		tarifaQuery.setEstado(ESTADO_ACTIVO);

		List<TarifaBean> lTarifa = null;
		// Buscamos si hay alguna tarifa con los parámetros dados
		lTarifa = tarifaManager.buscarTarifaAllNumTotal(tarifaQuery);

		// Si la lista contiene algún elemento entonces no se puede crear la
		// tarifa
		if (lTarifa.size() > 1) {
			return false;
		} else {
			if (lTarifa.size() == 1
					&& lTarifa.get(0).getId().intValue() != tarifaBean.getId()
							.intValue()) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param tarifaId el tarifa id
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, int tarifaId, Long resultado){

		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);

		// Cargo los datos en el bean del log generico que se usara para crear
		// el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_TARIFA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_MODIFICACION);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.tarifa.detalleOperacionModificacion") + " "  + tarifaId);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));

		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return usuarioManager UsuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager            UsuarioManager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el tarifa manager.
	 *
	 * @return the tarifaManager
	 */
	public TarifaManager getTarifaManager() {
		return tarifaManager;
	}

	/**
	 * Obtiene el grupo manager.
	 *
	 * @return the grupoManager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Obtiene el tipo acceso.
	 *
	 * @return the tipoAcceso
	 */
	public TipoAccesoManager getTipoAcceso() {
		return tipoAccesoManager;
	}

	/**
	 * Establece el tarifa manager.
	 *
	 * @param tarifaManager            the tarifaManager to set
	 */
	public void setTarifaManager(TarifaManager tarifaManager) {
		this.tarifaManager = tarifaManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager            the grupoManager to set
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAcceso            the tipoAcceso to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAcceso) {
		this.tipoAccesoManager = tipoAcceso;
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
	 * @return the logGenericoManager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager            the logGenericoManager to set
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}

}
