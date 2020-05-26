package es.map.ipsg.action.alerta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.AlertaQuery;
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
 * El Class ActualizarAlertaSpring.
 */
@SuppressWarnings("rawtypes")
public class ActualizarAlertaSpring extends AbstractSpring {
	
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
	private static final Logger logger = Logger.getLogger(ActualizarAlertaSpring.class);
	
	/** La constante STRING_CARGAR. */
	private static final String STRING_CARGAR = "Cargar";
	
	/** La constante STRING_TERMINA_ACTUALIZAR_ALERTA_ACCION. */
	private static final String STRING_TERMINA_ACTUALIZAR_ALERTA_ACCION = "Termina Actualizar Alerta Accion";
	
	/** La constante STRING_ERRORALERTA. */
	private static final String STRING_ERRORALERTA = "errorAlerta";
	
		
	/**
	 * Crea una nueva actualizar alerta spring.
	 */
	public ActualizarAlertaSpring() {	
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
			logger.error("Error Actualizar alerta:", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Comienza Actualizar Alerta Accion");
		
	try{
		String menu_alerta = RESOURCE_BUNDLE.getString("field.menu.alertas");
		this.getRequest().getSession().setAttribute("pagActiva", menu_alerta);
		String subMenu_alerta = RESOURCE_BUNDLE.getString("field.menuLateral.alertas.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_alerta);
		
		getLogger().debug("ActualizarAlertasSpring -start");
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		/*FIN-TRA042*/
		Integer idAlerta=null;	
		AlertaBean alertaBean = new AlertaBean();
		AlertaForm formulario = (AlertaForm) form;
		String busqueda = formulario.getSubmit();
		if (formulario.getId()!=null){
			idAlerta=formulario.getId();
		}
		String[] usuarios=formulario.getIdUsuarioSeleccionados();
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que esta logueado
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		
		String perfilUsuario=usuario.getPerfil().getId().toString();
		/*INI-TRA042*/
		if (perfilUsuario.equals(Constantes.PERFIL_GESTOR)){
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());
		} else if (formulario.getIdCentroGestor()!=null && formulario.getIdCentroGestor()!=""){
			CentroGestorBean cg = new CentroGestorBean();
			cg.setId(Integer.parseInt(formulario.getIdCentroGestor()));
			listaCentrosGestores.add(cg);
		}
		/*FIN-TRA042*/
		if (busqueda==null){
			busqueda=STRING_CARGAR;
		}
		String titulo = "";
		String mensaje = "";
		
		if("Guardar".equals(busqueda)){
			cargarCombos(formulario,listaCentrosGestores,idAlerta,usuario,busqueda);
				try{
					//Buscamos Si ya existe un registro con los nuevos valores
					  String resultado= buscarRegistro(alertaBean,formulario,usuarios,idAlerta,usuario,busqueda);
					
						if (resultado.equals("0")){
							//Si no existe Actualizamos la actual a los nuevos valores
							alertaBean.setId(idAlerta);
							try{
								if(alertaBean.getIdModo()==Constantes.MODO_ALERTA_POPUP){
									alertaBean.setUsuarios(null);
								}
								alertaManager.actualizarAlerta(alertaBean);
								
								//Creamos el Log generico
								usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
								generarRegistroLogGenerico(usuarioBean.getLogin(),String.valueOf(idAlerta),Long.valueOf(idAlerta));
								mensaje = RESOURCE_BUNDLE.getString("field.alerta.actualizacion.mensaje")+ " " +idAlerta;
								titulo = RESOURCE_BUNDLE.getString("field.alerta.actualizacion.titulo");
								setRequestAttribute("accion","/spring/buscarAlerta");
								setRequestAttribute("titulo",titulo);
								setRequestAttribute("mensaje",mensaje);
								//Dejamos el formulario vacio para buscar solo por el campo de busqueda
								formulario.setIdModo(null);
								formulario.setIdPerfil(null);
								formulario.setIdTipo(null);
								formulario.setIdUsuario(null);
								formulario.setIdUsuarioSeleccionados(null);
								formulario.setIdCentroGestor(null);
								formulario.setSubmit("Buscar");
								logger.info(STRING_TERMINA_ACTUALIZAR_ALERTA_ACCION);
								return "successMensaje";
							}catch(Exception e){
								SpringMessages errors = new SpringMessages();
								SpringMessage error = new SpringMessage("field.alerta.actualizacion.mensajeError");
								errors.add(STRING_ERRORALERTA,error);
								saveErrors(this.getRequest().getSession(),errors);
								formulario.setId(idAlerta);
								formulario.setIdCentroGestor(formulario.getIdCentroGestor().toString());
								logger.info(STRING_TERMINA_ACTUALIZAR_ALERTA_ACCION);
								logger.error("Error actualizar alerta Accion:", e);
								return STRING_ERRORALERTA;
							}
						}else{
							//Si  existe una alerta  para los valores introducidos devolvemos error
							SpringMessages errors = new SpringMessages();
							SpringMessage error = new SpringMessage("field.alerta.actualizacion.ErrorDuplicado",resultado,formulario.getIdModo(),formulario.getIdTipo());
							errors.add(STRING_ERRORALERTA,error);
							saveErrors(this.getRequest(),errors);
							logger.info(STRING_TERMINA_ACTUALIZAR_ALERTA_ACCION);
							return STRING_ERRORALERTA;
							
						}
				}catch(Exception e){
					SpringMessages errors = new SpringMessages();
					SpringMessage error = new SpringMessage("field.alerta.actualizacion.mensajeError");
					errors.add(STRING_ERRORALERTA,error);
					saveErrors(this.getRequest().getSession(),errors);
					logger.info(STRING_TERMINA_ACTUALIZAR_ALERTA_ACCION);
					logger.error("Error buscando registro de Alerta:", e);
					return STRING_ERRORALERTA;
				}
					
		}else{
			if(STRING_CARGAR.equals(busqueda)){
				/*INI-TRA042*/
				cargarCombos(formulario,listaCentrosGestores,idAlerta,usuario,busqueda);
				/*FIN-TRA042*/
			}
		}
		
		
		getLogger().debug("ActualizarAlertasSpring -end");
	}catch(Exception eGenerico){
		eGenerico.printStackTrace();
		logger.error("Error actualizar alerta Action: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		logger.info(STRING_TERMINA_ACTUALIZAR_ALERTA_ACCION);
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
			String sUsernameLogin = usuarioBean.getLogin();
			logger.info("sUsernameLogin: " + sUsernameLogin);
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error recuperar UsuarioSesion:", e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Cargar combos.
	 *
	 * @param formulario el formulario
	 * @param listaCentrosGestores el id centro gestor usuario
	 * @param idAlerta el id alerta
	 * @param usuario el usuario
	 * @param busqueda el busqueda
	 */
	/*INI-TRA042*/
	public void cargarCombos(AlertaForm formulario, List<CentroGestorBean> listaCentrosGestores, Integer idAlerta, Usuario usuario, String busqueda) {
		//Combo modos Alerta
		ModoAlertaQuery modoAlertaQuery = new ModoAlertaQuery();
		String[] usuarios = null;
		List<ModoAlertaBean> modosAlerta;
		modosAlerta = modoAlertaManager.buscarModoAlertaAll(modoAlertaQuery);		
		setRequestAttribute("modosAlerta", modosAlerta);
		//Combo tipos Alerta
		TipoAlertaQuery tipoAlertaQuery=new TipoAlertaQuery();
		List<TipoAlertaBean> tiposAlerta ;
		tiposAlerta=tipoAlertaManager.buscarTipoAlertaAll(tipoAlertaQuery);
		setRequestAttribute("tiposAlerta", tiposAlerta);
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		//Si es Gestor Recuperamos el Centro Gestor.
		String perfilUsuario=usuario.getPerfil().getId().toString();
		if( perfilUsuario.equals(Constantes.PERFIL_GESTOR)){
			List<Integer> listaIdsUsuarios = usuarioCentrogestorManager.buscarUsuariosByIdCg(listaCentrosGestores);
			usuarioQuery.addPerfilIn(Constantes.PERFIL_GESTOR_INT);
			usuarioQuery.addPerfilIn(Constantes.PERFIL_RECEPTOR_INT);
			for(Integer idUsu: listaIdsUsuarios) {
				usuarioQuery.addIdIn(idUsu);
			}
			
		}else{
			if( perfilUsuario.equals(Constantes.PERFIL_ADMINISTRADOR)){
				usuarioQuery.addPerfilIn(Constantes.PERFIL_ADMINISTRADOR_INT);
				usuarioQuery.addPerfilIn(Constantes.PERFIL_RECEPTOR_INT);
				
			}
		}
		//Buscamos los usuarios que estan anyadidos en la alerta
		List<UsuarioBean> usuariosSel=new ArrayList<UsuarioBean>();
		//cargar Combos  selecionados de usuarios.
		if(STRING_CARGAR.equals(busqueda)){
			AlertaBean alertaBeanAux;
			alertaBeanAux=alertaManager.obtenerAlerta(idAlerta);
			usuarios=new String[alertaBeanAux.getUsuariosList().size()];
			for (int i=0;i<alertaBeanAux.getUsuariosList().size();i++){
				usuarios[i]=alertaBeanAux.getUsuariosList().get(i);
			}
			if (alertaBeanAux.getIdCentroGestor()!=null){
				formulario.setIdCentroGestor(alertaBeanAux.getIdCentroGestor().toString());
			}
			formulario.setIdTipo(alertaBeanAux.getIdTipo());
			formulario.setIdModo(alertaBeanAux.getIdModo());
		}
		if (usuarios != null) {
			for (int i = 0; i < usuarios.length; i++) {
				usuarioQuery.setId(Integer.parseInt(usuarios[i]));
				List<UsuarioBean> usuarioBeanList;
				usuarioBeanList = usuarioManager.buscarUsuarios(usuarioQuery);
				usuariosSel.add(usuarioBeanList.get(0));
			}
		}
		
		
		//cargar Combos NO selecionados de usuarios.
		List<UsuarioBean>  usuarioList;
		usuarioQuery.setId(null);
		usuarioList = usuarioManager.buscarUsuarios(usuarioQuery);
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
		
		setRequestAttribute("usuarioList", usuarioList);
		setRequestAttribute("usuariosSel", usuariosSel);
	}
	/*FIN-TRA042*/
	
    /**
     * Buscar registro.
     *
     * @param alertaBean el alerta bean
     * @param formulario el formulario
     * @param usuarios el usuarios
     * @param idAlerta el id alerta
     * @param usuario el usuario
     * @param busqueda el busqueda
     * @return el string
     */
    public String buscarRegistro(AlertaBean alertaBean , AlertaForm formulario, String[] usuarios, Integer idAlerta, Usuario usuario, String busqueda){
		
	    alertaBean.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
		AlertaQuery alertaQuery = new AlertaQuery();
		alertaQuery.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
		//Si estamos actualizando buscamos si existe alguna Alerta ya existente con los nuevos campos
		if("Guardar".equals(busqueda)){
			//Informamos el Centro Gestor.
			if (formulario.getIdCentroGestor()!=null && !"".equals(formulario.getIdCentroGestor().toString())){
				alertaQuery.addCentroGestorIn(Integer.valueOf(formulario.getIdCentroGestor()));
				alertaBean.setIdCentroGestor(Integer.valueOf(formulario.getIdCentroGestor()));
			}else{
				alertaQuery.setCentroGestorIsNull(true);
			}
			//Anyadimos el Tipo de alerta
			if (formulario.getIdTipo()!=null){
				alertaQuery.addTipoAlertaIn(formulario.getIdTipo());
				alertaBean.setIdTipo(formulario.getIdTipo());
			}
			//Anyadimos el Modo de alerta
			if (formulario.getIdModo()!=null){
				alertaQuery.addModoAlertaIn(formulario.getIdModo());
				alertaBean.setIdModo(formulario.getIdModo());
			}
			//Anyadimos los usuarios a los cuales se les informa la alerta
			if (usuarios != null) {
				Set<Usuario> usuariosSet=new HashSet<Usuario>();
				for (int i = 0; i < usuarios.length; i++) {
					UsuarioBean usuarioBean2 = usuarioManager.obtenerUsuario(Integer.parseInt(usuarios[i]));
					usuariosSet.add(usuarioManager.toUsuario(usuarioBean2));
				}
				alertaBean.setUsuarios(usuariosSet);
			}
			//Anyadimos el perfil de la alerta
			alertaBean.setIdPerfil(usuario.getPerfil().getId());
			alertaQuery.addPerfilIn(usuario.getPerfil().getId());
		}else{
			alertaQuery.setId(idAlerta);
		}
		alertaQuery.addOrder("id",OrderType.ASC);
		
		List <AlertaBean> alertaBeanList;
		
		//Buscamos si ya existe una alerta  para los valores introducidos
		alertaBeanList = alertaManager.buscarAlertaAll(alertaQuery);
		
		String idAlertaExistente="0";
		for (int i=0;i<alertaBeanList.size();i++){
			if(!alertaBeanList.get(i).getId().toString().equals(idAlerta.toString())){
				//Si ya existe una alerta  para los nuevos valores introducidos devolvemos el ID de la alerta existente
				idAlertaExistente=alertaBeanList.get(i).getId().toString();
				break;
			}	
		}
			return idAlertaExistente;
	}
	
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
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_MODIFICACION  );
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.alerta.detalleOperacionActualizacion") + " " + alertaDesc);
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
