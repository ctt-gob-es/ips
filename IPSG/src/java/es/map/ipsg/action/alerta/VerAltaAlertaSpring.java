package es.map.ipsg.action.alerta;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ModoAlertaQuery;
import es.map.ips.model.TipoAlertaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ModoAlertaBean;
import es.map.ipsg.bean.TipoAlertaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AlertaForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModoAlertaManager;
import es.map.ipsg.manager.TipoAlertaManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerAltaAlertaSpring.
 */
public class VerAltaAlertaSpring extends AbstractSpring {
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el modo alerta manager. */
	private ModoAlertaManager modoAlertaManager;
	
	/** el tipo alerta manager. */
	private TipoAlertaManager tipoAlertaManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaAlertaSpring.class);

	/**
	 * Crea una nueva ver alta alerta spring.
	 */
	public VerAltaAlertaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setModoAlertaManager((ModoAlertaManager) getBean("modoAlertaManager"));
			setTipoAlertaManager((TipoAlertaManager) getBean("tipoAlertaManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerAltaAlerta:", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Comienza Ver Alta Alerta Accion");
	try{
		String manu_alerta = RESOURCE_BUNDLE.getString("field.menu.alertas");
		this.getRequest().getSession().setAttribute("pagActiva", manu_alerta);
		String subMenu_alerta = RESOURCE_BUNDLE.getString("field.menuLateral.alertas.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_alerta);
		
		getLogger().debug("VerAltaAlertaSpring -start");
		AlertaForm formulario = (AlertaForm) form;
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		/*FIN-TRA042*/
		String[]usuarios = formulario.getIdUsuarioSeleccionados();
		if(this.getRequest().getParameter("menu")!=null && this.getRequest().getParameter("menu").equals("S")){
			
				formulario.setIdCentroGestor(null);
				formulario.setDsCentroGestor(null);
				formulario.setIdModo(null);
				formulario.setIdPerfil(null);
				formulario.setIdTipo(null);
				formulario.setIdUsuario(null);
				formulario.setIdUsuarioSeleccionados(null);
	
		}
		// Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();

		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); 
		// Le pasamos el login del que está logueado
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		
		String perfilUsuario = usuarioBean.getIdPerfil().toString();
		
		/*INI-TRA042*/
		if (perfilUsuario.equals(Constantes.PERFIL_GESTOR)) {
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			
			formulario.setListaCentrosGestores(listaCentrosGestores);
			
			formulario.setIdPerfil(Constantes.PERFIL_GESTOR_INT);
			
		}
		/*FIN-TRA042*/
		String busqueda = formulario.getSubmit();
		if (busqueda == null) {
			busqueda = "Limpiar";
		}
		String titulo = "";
		String mensaje = "";
		/*INI-TRA042*/
		cargarCombos(perfilUsuario,listaCentrosGestores,usuarios);
		/*FIN-TRA042*/
		if ("Error".equals(busqueda)) {
			mensaje = RESOURCE_BUNDLE.getString("field.alerta.alta.mensajeError");
			titulo = RESOURCE_BUNDLE.getString("field.alerta.alta.titulo");
			setRequestAttribute("accion", "/spring/altaAlerta");
			setRequestAttribute("titulo", titulo);
			setRequestAttribute("mensaje", mensaje);
			logger.info("Termina Ver Alta Alerta Accion");
			return "errorAlerta";
		}

		setRequestAttribute("perfilUsuario", perfilUsuario);
		getLogger().debug("VerAltaAlertaSpring -end");
		logger.info("Termina Ver Alta Alerta Accion");
		
	}catch(Exception eGenerico){
		logger.error("Error VerAltaAlerta - doExecute: ",eGenerico);
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
		try {
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			String sUsernameLogin = usuarioBean.getLogin();
			logger.info("sUsernameLogin: " + sUsernameLogin);
			return sUsernameLogin;
		} catch (Exception e) {
			logger.error("Error recuperarUsuarioSesion:", e);
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

		TipoAlertaQuery tipoAlertaQuery = new TipoAlertaQuery();
		List<TipoAlertaBean> tiposAlerta;
		tiposAlerta = tipoAlertaManager.buscarTipoAlertaAll(tipoAlertaQuery);
		setRequestAttribute("tiposAlerta", tiposAlerta);

		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setEstado(Constantes.USUARIO_ESTADO_ACTIVO);
		
		// Si es Gestor Recuperamos el Centro Gestor.
		if (perfilUsuario.equals(Constantes.PERFIL_GESTOR)) {
			List<Integer> listaIdsUsuarios = usuarioCentrogestorManager.buscarUsuariosByIdCg(listaCentrosGestores);
			usuarioQuery.addPerfilIn(Constantes.PERFIL_GESTOR_INT);
			usuarioQuery.addPerfilIn(Constantes.PERFIL_RECEPTOR_INT);
			for(Integer idUsu: listaIdsUsuarios) {
				usuarioQuery.addIdIn(idUsu);
			}
			
			setRequestAttribute("listaCentrosGestores", listaCentrosGestores);
		} else {
			if (perfilUsuario.equals(Constantes.PERFIL_ADMINISTRADOR)) {
				usuarioQuery.addPerfilIn(Constantes.PERFIL_ADMINISTRADOR_INT);
				usuarioQuery.addPerfilIn(Constantes.PERFIL_RECEPTOR_INT);

			}
		}

		// cargar Combos NO selecionados de usuarios.
		List<UsuarioBean> usuarioList;
		usuarioList = usuarioManager.buscarUsuarios(usuarioQuery);
		cargarCombosNoSeleccionados(usuarios,usuarioList);
		
		// cargar Combos selecionados de usuarios.
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
	 * Cargar combos no seleccionados.
	 *
	 * @param usuarios el usuarios
	 * @param usuarioList el usuario list
	 */
	private void cargarCombosNoSeleccionados(String[] usuarios, List<UsuarioBean> usuarioList) {
		if (usuarios != null) {
			for (int i = 0; i < usuarios.length; i++) {
				boolean noEncontrado = true;
				int j = 0;
				while (j < usuarioList.size() && noEncontrado) {
					int idUsuarioSel = Integer.parseInt(usuarios[i]);
					int idUsuario = usuarioList.get(j).getId().intValue();
					if (idUsuarioSel == idUsuario) {
						noEncontrado = false;
						usuarioList.remove(j);
					} else {
						j++;
					}
				}
			}
		}
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
	 * @param modoAlertaManager            the modoAlertaManager to set
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
	 * @param tipoAlertaManager            the tipoAlertaManager to set
	 */
	public void setTipoAlertaManager(TipoAlertaManager tipoAlertaManager) {
		this.tipoAlertaManager = tipoAlertaManager;
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

	/**
	 * @return the centroGestorManager
	 */
	/*FIN-TRA042*/
	
}
