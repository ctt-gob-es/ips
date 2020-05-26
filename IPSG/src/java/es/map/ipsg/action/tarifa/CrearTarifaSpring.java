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
import es.map.ips.common.util.BeanFormatter;
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
 * El Class CrearTarifaSpring.
 */
public class CrearTarifaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearTarifaSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';

	/** el tarifa manager. */
	private TarifaManager tarifaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el tipo acceso. */
	private TipoAccesoManager tipoAcceso;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/**
	 * Crea una nueva crear tarifa spring.
	 */
	public CrearTarifaSpring() {
		try {
			setTarifaManager((TarifaManager) getBean("tarifaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearTarifaSpring - crear constructor",e);
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
		String resultado;
	try{	
		getLogger().debug("CrearTarifaSpring -start");
		
		TarifaForm formulario=(TarifaForm) form;
		TarifaBean tarifaBean= new TarifaBean();
		
				
		//Alta de Tarifa
		
		tarifaBean.setDescripcion(formulario.getDescripcion());
		tarifaBean.setImporte(Float.parseFloat(formulario.getImporte()));
		tarifaBean.setEjercicio(formulario.getEjercicio());
		tarifaBean.setEstado(ESTADO_ACTIVO);
		
		//Cargamos los combos
		GrupoQuery  grupoQuery = new GrupoQuery();
		List<GrupoBean> lGrupoBean;
		lGrupoBean = grupoManager.buscarGrupoCombo(grupoQuery);
	
		TipoAccesoQuery tipoAccesoQuery = new TipoAccesoQuery();
		List<TipoAccesoBean> lTipoAccesoBean;
		lTipoAccesoBean = tipoAcceso.buscarTipoAccesoCombo(tipoAccesoQuery);
				
		setRequestAttribute("grupo", lGrupoBean);
		setRequestAttribute("tipoAcceso", lTipoAccesoBean);
		
		
		
		
		
		if(formulario.getIdTipoAcceso()!=null && !formulario.getIdTipoAcceso().equals(""))
			tarifaBean.setIdTipoAcceso(Integer.valueOf(formulario.getIdTipoAcceso()));
		
		if(formulario.getIdGrupo()!=null && !formulario.getIdGrupo().equals(""))
			tarifaBean.setIdGrupo(Integer.valueOf(formulario.getIdGrupo()));
		
	
		
		int result=0;
		logger.info(new BeanFormatter().format(tarifaBean));
		
		if (compruebaTarifa(tarifaBean)){
			 result =tarifaManager.guardarTarifa(tarifaBean);
		}
		else {
			SpringMessages errors = new SpringMessages();
			SpringMessage error = new SpringMessage("field.tarifa.errores.tarifaRepetida");
			errors.add("errorTarifa",error);
			saveErrors(this.getRequest(),errors);
			return "errorTarifa";
		}
		
		String mensaje;
		
		
		if(result>0){
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(),tarifaBean.getDescripcion(),new Long(result));
			resultado="success";
			mensaje = RESOURCE_BUNDLE.getString("field.tarifa.altaTarifaConfirmacion");
		}else{
			resultado="error";
			mensaje = RESOURCE_BUNDLE.getString("field.tarifa.altaTarifaError");
		}
		
		String titulo = RESOURCE_BUNDLE.getString("field.tarifa.tituloAltaTarifa");
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/verBuscarTarifa");
		
		getLogger().debug("CrearTarifaSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error CrearTarifaSpring -  doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return resultado;
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param tarifaDesc el tarifa desc
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String tarifaDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_TARIFA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.tarifa.detalleOperacionAlta") + " "  + tarifaDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

/**
 * Comprueba tarifa.
 *
 * @param tarifaBean el tarifa bean
 * @return verdadero, si tiene exito
 */
//Compruebo que no hay otra tarifa activa con los mismos valores en "grupo, tipo acceso y ejercicio"
	private boolean compruebaTarifa(TarifaBean tarifaBean) {
		
		TarifaQuery tarifaQuery= new TarifaQuery();
	
		tarifaQuery.addGrupoIn(tarifaBean.getIdGrupo());
		tarifaQuery.addTipoAccesoIn(tarifaBean.getIdTipoAcceso());
		tarifaQuery.setEjercicio(tarifaBean.getEjercicio());
		tarifaQuery.setEstado(ESTADO_ACTIVO);
		
		List<TarifaBean> lTarifa = null;
		//Buscamos si hay alguna tarifa con los parámetros dados
		lTarifa = tarifaManager.buscarTarifaAllNumTotal(tarifaQuery);
		
		//Si la lista contiene algún elemento entonces no se puede crear la tarifa
		if (lTarifa.size()!=0)
			return false;
		else		
		return true;
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
	 * Establece el grupo manager.
	 *
	 * @param grupoManager the grupoManager to set
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
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

	/**
	 * Obtiene el tarifa manager.
	 *
	 * @return the tarifaManager
	 */
	public TarifaManager getTarifaManager() {
		return tarifaManager;
	}

	/**
	 * Obtiene el tipo acceso.
	 *
	 * @return the tipoAcceso
	 */
	public TipoAccesoManager getTipoAcceso() {
		return tipoAcceso;
	}

	/**
	 * Establece el tarifa manager.
	 *
	 * @param tarifaManager the tarifaManager to set
	 */
	public void setTarifaManager(TarifaManager tarifaManager) {
		this.tarifaManager = tarifaManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAcceso the tipoAcceso to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

}
