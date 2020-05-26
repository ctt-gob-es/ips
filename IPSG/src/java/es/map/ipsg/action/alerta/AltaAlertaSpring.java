package es.map.ipsg.action.alerta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.AlertaQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ModoAlertaQuery;
import es.map.ips.model.TipoAlertaQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.ModoAlertaBean;
import es.map.ipsg.bean.TipoAlertaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AlertaForm;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModoAlertaManager;
import es.map.ipsg.manager.TipoAlertaManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class AltaAlertaSpring.
 */
public class AltaAlertaSpring extends AbstractSpring {
	
	/** el alerta manager. */
	private AlertaManager alertaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el modo alerta manager. */
	private ModoAlertaManager modoAlertaManager;
	
	/** el tipo alerta manager. */
	private TipoAlertaManager tipoAlertaManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaAlertaSpring.class);
	
	/** La constante STRING_ERROR_ALERTA. */
	private static final String STRING_ERROR_ALERTA = "errorAlerta";
	
	/** La constante STRING_TERMINA_ALTA_ALERTA_ACCION. */
	private static final String STRING_TERMINA_ALTA_ALERTA_ACCION = "Termina Alta Alerta Accion";
	
	/** La constante STRING_FIELD_ALERTA_ALTA_TITULO. */
	private static final String STRING_FIELD_ALERTA_ALTA_TITULO = "field.alerta.alta.titulo";
	
	/** La constante STRING_ACCION. */
	private static final String STRING_ACCION = "accion";
	
	/** La constante STRING_TITULO. */
	private static final String STRING_TITULO = "titulo";
	
	/** La constante STRING_MENSAJE. */
	private static final String STRING_MENSAJE = "mensaje";
	
	/** La constante STRING_SUCCESS_MENSAJE. */
	private static final String STRING_SUCCESS_MENSAJE = "successMensaje";
	
		
	/**
	 * Crea una nueva alta alerta spring.
	 */
	public AltaAlertaSpring() {	
		try {
			setAlertaManager((AlertaManager) getBean("alertaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		    setModoAlertaManager((ModoAlertaManager) getBean("modoAlertaManager"));
		    setTipoAlertaManager((TipoAlertaManager) getBean("tipoAlertaManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error en AltaAlerta:", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Comienza Alta Alerta Accion");
	try{
		String menu_alerta = RESOURCE_BUNDLE.getString("field.menu.alertas");
		this.getRequest().getSession().setAttribute("pagActiva", menu_alerta);
		String subMenu_alerta = RESOURCE_BUNDLE.getString("field.menuLateral.alertas.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_alerta);
		
		getLogger().debug("AltaAlertasSpring -start");
		AlertaForm formulario = (AlertaForm) form;		
		AlertaBean alertaBean = new AlertaBean();
		String[] usuarios=formulario.getIdUsuarioSeleccionados();
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
		
		String perfilUsuario=usuarioBean.getIdPerfil().toString();
		if (perfilUsuario.equals(Constantes.PERFIL_GESTOR)){
			formulario.setListaCentrosGestores(usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId()));
			formulario.setIdPerfil(Constantes.PERFIL_GESTOR_INT);
		}
		String busqueda = formulario.getSubmit();
		if (busqueda==null){
			busqueda="Limpiar";
		}
		String titulo = "";
		String mensaje = "";
		cargarCombos(perfilUsuario,listaCentrosGestores,usuarios);
		/*FIN-TRA042*/		
		if("Guardar".equals(busqueda)){
			alertaBean.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
			
			
				AlertaQuery alertaQuery = new AlertaQuery();
				CentroGestorQuery centroGestorQuery= new CentroGestorQuery();
				alertaQuery.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
				centroGestorQuery.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
				
				//Informamos el Centro Gestor.
				if (formulario.getIdCentroGestor()!=null && !"".equals(formulario.getIdCentroGestor().toString())){
					alertaQuery.addCentroGestorIn(Integer.valueOf(formulario.getIdCentroGestor()));
					alertaBean.setIdCentroGestor(Integer.valueOf(formulario.getIdCentroGestor()));
				}else{
					alertaQuery.setCentroGestorIsNull(true);
				}
				//Añadimos el Tipo de alerta
				if (formulario.getIdTipo()!=null){
					alertaQuery.addTipoAlertaIn(formulario.getIdTipo());
					alertaBean.setIdTipo(formulario.getIdTipo());
				}
				//Añadimos el Modo de alerta
				if (formulario.getIdModo()!=null){
					alertaQuery.addModoAlertaIn(formulario.getIdModo());
					alertaBean.setIdModo(formulario.getIdModo());
				}
				//Añadimos los usuarios a los cuales se les informa la alerta
				if (usuarios != null) {
					Set<Usuario> usuariosSet=new HashSet<Usuario>();
					for (int i = 0; i < usuarios.length; i++) {
						UsuarioBean usuarioBean2 = usuarioManager.obtenerUsuario(Integer.parseInt(usuarios[i]));
						usuariosSet.add(usuarioManager.toUsuario(usuarioBean2));
					}
					alertaBean.setUsuarios(usuariosSet);
				}
				//Añadimos el perfil de la alerta
				alertaBean.setIdPerfil(usuarioBean.getIdPerfil());
				alertaQuery.addPerfilIn(usuarioBean.getIdPerfil());
				
				List <AlertaBean> alertaBeanList;
				try{
					
					//Buscamos si ya existe una alerta  para los valores introducidos
					int idAlerta =0;
						alertaBeanList = alertaManager.buscarAlertaAll(alertaQuery);
						if (alertaBeanList.size()>0){
							SpringMessages errors = new SpringMessages();
							SpringMessage error = new SpringMessage("field.alerta.alta.ErrorDuplicado",alertaBeanList.get(0).getId(),formulario.getIdModo(),formulario.getIdTipo());
							errors.add(STRING_ERROR_ALERTA,error);
							saveErrors(this.getRequest(),errors);							
							logger.info(STRING_TERMINA_ALTA_ALERTA_ACCION);
							return STRING_ERROR_ALERTA;
						}else{
								idAlerta = alertaManager.guardarAlerta(alertaBean);
								if (idAlerta>0){
									usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
									generarRegistroLogGenerico(usuarioBean.getLogin(),String.valueOf(idAlerta),Long.valueOf(idAlerta));
									mensaje = RESOURCE_BUNDLE.getString("field.alerta.alta.mensaje")+idAlerta;
									titulo = RESOURCE_BUNDLE.getString(STRING_FIELD_ALERTA_ALTA_TITULO);
									setRequestAttribute(STRING_ACCION ,"/spring/buscarAlerta");
									setRequestAttribute(STRING_TITULO,titulo);
									setRequestAttribute(STRING_MENSAJE,mensaje);
									//Dejamos el formulario vacio para buscar solo por el campo de busqueda
									formulario.setIdModo(null);
									formulario.setIdPerfil(null);
									formulario.setIdTipo(null);
									formulario.setIdUsuario(null);
									formulario.setIdUsuarioSeleccionados(null);
									formulario.setIdCentroGestor(null);
									formulario.setSubmit("Buscar");
									logger.info(STRING_TERMINA_ALTA_ALERTA_ACCION);
									return STRING_SUCCESS_MENSAJE;
								}
							}
					
				}catch(Exception e){
					SpringMessages errors = new SpringMessages();
					SpringMessage error = new SpringMessage("field.alerta.alta.mensajeError");
					errors.add(STRING_ERROR_ALERTA,error);
					saveErrors(this.getRequest().getSession(),errors);
					logger.info(STRING_TERMINA_ALTA_ALERTA_ACCION);
					logger.error("Error Alta Alerta:", e);
					return STRING_ERROR_ALERTA;
				}
					
		}
		
		
		if("Guardar".equals(busqueda)){
			mensaje = RESOURCE_BUNDLE.getString("field.alerta.alta.mensaje");
			titulo = RESOURCE_BUNDLE.getString(STRING_FIELD_ALERTA_ALTA_TITULO);
			setRequestAttribute(STRING_ACCION ,"/spring/buscarAlerta");
			setRequestAttribute(STRING_TITULO,titulo);
			setRequestAttribute(STRING_MENSAJE,mensaje);
			logger.info(STRING_TERMINA_ALTA_ALERTA_ACCION);
			return STRING_SUCCESS_MENSAJE;
		}else{
			if("Error".equals(busqueda)){
				mensaje = RESOURCE_BUNDLE.getString("field.alerta.alta.mensajeError");
				titulo = RESOURCE_BUNDLE.getString(STRING_FIELD_ALERTA_ALTA_TITULO);
				setRequestAttribute(STRING_ACCION ,"/spring/altaAlerta");
				setRequestAttribute(STRING_TITULO,titulo);
				setRequestAttribute(STRING_MENSAJE,mensaje);
				logger.info(STRING_TERMINA_ALTA_ALERTA_ACCION);
				return STRING_SUCCESS_MENSAJE;
			}			
		}
		
		setRequestAttribute("perfilUsuario", perfilUsuario);
		getLogger().debug("AltaAlertasSpring -end");
		logger.info(STRING_TERMINA_ALTA_ALERTA_ACCION);
	}catch(Exception eGenerico){
		logger.error("Error alta alerta accion: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			String sUsernameLogin=usuarioBean.getLogin();
			logger.info("sUsernameLogin: "+sUsernameLogin);
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error en recuperarUsuarioSesion:", e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Cargar combos.
	 *
	 * @param perfilUsuario el perfil usuario
	 * @param idCentroGestorUsuario el id centro gestor usuario
	 * @param usuarios el usuarios
	 */
 	/*INI-TRA042*/
	public void cargarCombos(String perfilUsuario, List<CentroGestorBean> listaCentrosGestores, String[] usuarios) {
		ModoAlertaQuery modoAlertaQuery = new ModoAlertaQuery();
		List<ModoAlertaBean> modosAlerta;
		modosAlerta = modoAlertaManager.buscarModoAlertaAll(modoAlertaQuery);		
		setRequestAttribute("modosAlerta", modosAlerta);
		
		TipoAlertaQuery tipoAlertaQuery=new TipoAlertaQuery();
		List<TipoAlertaBean> tiposAlerta ;
		tiposAlerta=tipoAlertaManager.buscarTipoAlertaAll(tipoAlertaQuery);
		setRequestAttribute("tiposAlerta", tiposAlerta);
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		//Si es Gestor Recuperamos el Centro Gestor.
		if( perfilUsuario.equals(Constantes.PERFIL_GESTOR)){
			List<Integer> listaIdsUsuarios = usuarioCentrogestorManager.buscarUsuariosByIdCg(listaCentrosGestores);
			usuarioQuery.addPerfilIn(Constantes.PERFIL_GESTOR_INT);
			usuarioQuery.addPerfilIn(Constantes.PERFIL_RECEPTOR_INT);
			for(Integer idUsu: listaIdsUsuarios) {
				usuarioQuery.addIdIn(idUsu);
			}
			setRequestAttribute("listaCentrosGestores", listaCentrosGestores);
		}else{
			if( perfilUsuario.equals(Constantes.PERFIL_ADMINISTRADOR)){
				usuarioQuery.addPerfilIn(Constantes.PERFIL_ADMINISTRADOR_INT);
				usuarioQuery.addPerfilIn(Constantes.PERFIL_RECEPTOR_INT);
				
			}
		}
		
		//cargar Combos NO selecionados de usuarios.
		List<UsuarioBean>  usuarioList = usuarioManager.buscarUsuarios(usuarioQuery);
		if (usuarios != null) {
			for (int i = 0; i < usuarios.length; i++) {
				boolean noEncontrado = true;
				int j = 0;
				while (j < usuarioList.size() && noEncontrado) {
					int idUsuarioSel = Integer.parseInt(usuarios[i]);
					int idUsuario = usuarioList.get(j).getId()
							.intValue();
					if (idUsuarioSel == idUsuario) {
						noEncontrado = false;
						usuarioList.remove(j);
					} else {
						j++;
					}
				}
			}
		}
		//cargar Combos  selecionados de usuarios.
		List<UsuarioBean> usuariosSel = new ArrayList<UsuarioBean>();
		if (usuarios != null) {
			for (int i = 0; i < usuarios.length; i++) {
				usuarioQuery.setId(Integer.parseInt(usuarios[i]));
				List<UsuarioBean> usuarioBeanList;
				usuarioBeanList = usuarioManager.buscarUsuarios(usuarioQuery);
				usuariosSel.add(usuarioBeanList.get(0));
			}
		}
		setRequestAttribute("usuarioList", usuarioList);
		setRequestAttribute("usuariosSel", usuariosSel);
		setRequestAttribute("idPerfil", perfilUsuario);
	}
	/*FIN-TRA042*/
		
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param alertaDesc el alerta desc
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String alertaDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_ALERTA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.alerta.detalleOperacionAlta")+ " " +alertaDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}
	
	/**
	 * Obtiene el alerta manager.
	 *
	 * @return el alerta manager
	 */
	public AlertaManager getAlertaManager() {
		return alertaManager;
	}

	/**
	 * Establece el alerta manager.
	 *
	 * @param alertaManager el nuevo alerta manager
	 */
	public void setAlertaManager(AlertaManager alertaManager) {
		this.alertaManager = alertaManager;
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
	 * Obtiene el modo alerta manager.
	 *
	 * @return the modoAlertaManager
	 */
	public ModoAlertaManager getModoAlertaManager() {
		return modoAlertaManager;
	}

	/**
	 * Establece el modo alerta manager.
	 *
	 * @param modoAlertaManager the modoAlertaManager to set
	 */
	public void setModoAlertaManager(ModoAlertaManager modoAlertaManager) {
		this.modoAlertaManager = modoAlertaManager;
	}

	/**
	 * Obtiene el tipo alerta manager.
	 *
	 * @return the tipoAlertaManager
	 */
	public TipoAlertaManager getTipoAlertaManager() {
		return tipoAlertaManager;
	}

	/**
	 * Establece el tipo alerta manager.
	 *
	 * @param tipoAlertaManager the tipoAlertaManager to set
	 */
	public void setTipoAlertaManager(TipoAlertaManager tipoAlertaManager) {
		this.tipoAlertaManager = tipoAlertaManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return the centroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager the centroGestorManager to set
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/*INI-TRA042*/
	/**
	 * @return the usuarioCentrogestorManager
	 */
	public UsuarioCentrogestorManager getUsuarioCentrogestorManager() {
		return usuarioCentrogestorManager;
	}

	/**
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/	
	
}
