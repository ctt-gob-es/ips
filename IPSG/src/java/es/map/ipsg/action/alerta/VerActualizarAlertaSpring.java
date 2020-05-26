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
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.CentroGestorBean;
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
 * El Class VerActualizarAlertaSpring.
 */
public class VerActualizarAlertaSpring extends AbstractSpring {
	
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
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerActualizarAlertaSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
		
	/**
	 * Crea una nueva ver actualizar alerta spring.
	 */
	public VerActualizarAlertaSpring() {	
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
			logger.error("Error ver actualizar alerta:", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Comienza Ver Actualizar Alerta Accion");
	try{
		String menu_alerta = RESOURCE_BUNDLE.getString("field.menu.alertas");
		this.getRequest().getSession().setAttribute("pagActiva", menu_alerta);
		String subMenu_alerta = RESOURCE_BUNDLE.getString("field.menuLateral.alertas.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_alerta);
		
		getLogger().debug("VerActualizarAlertaSpring -start");
		/*INI-TRA042*/		
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		/*FIN-TRA042*/
		String[] usuarios = null;
		Integer idAlerta = null;
		AlertaForm formulario = (AlertaForm) form;
		String busqueda = formulario.getSubmit();
		AlertaBean alertaBean;
		
		if (formulario.getId()!=null){
			idAlerta=formulario.getId();
		}
		
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado
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
			alertaBean=alertaManager.obtenerAlerta(idAlerta);
			formulario.setId(alertaBean.getId());
			if (alertaBean.getIdCentroGestor()!=null){
				formulario.setIdCentroGestor(alertaBean.getIdCentroGestor().toString());
			}
			formulario.setIdModo(alertaBean.getIdModo());
			formulario.setIdPerfil(alertaBean.getIdPerfil());
			formulario.setIdTipo(alertaBean.getIdTipo());
			if (formulario.getIdUsuarioSeleccionados()!=null){
				usuarios=formulario.getIdUsuarioSeleccionados();
			}else{
				usuarios=new String[alertaBean.getUsuariosList().size()];
				for (int i=0;i<alertaBean.getUsuariosList().size();i++){
					usuarios[i]=alertaBean.getUsuariosList().get(i).toString();
				}
			}
			/*INI-TRA042*/
			cargarCombos(formulario,perfilUsuario,listaCentrosGestores,usuarios,idAlerta,busqueda);
			/*FIN-TRA042*/
		getLogger().debug("VerActualizarAlertaSpring -end");
		logger.info("Termina Ver Actualizar Alerta Accion");
		
	}catch(Exception eGenerico){
		eGenerico.printStackTrace();
		logger.error("Error VerActualizarAlerta - doExecute: "+ eGenerico);
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
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin=usuarioBean.getLogin();
			logger.info("sUsernameLogin: "+sUsernameLogin);
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error recuperarUsuarioSesion:" + sUsernameLogin , e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Cargar combos.
	 *
	 * @param formulario el formulario
	 * @param perfilUsuario el perfil usuario
	 * @param idCentroGestorUsuario el id centro gestor usuario
	 * @param usuarios el usuarios
	 * @param idAlerta el id alerta
	 * @param busqueda el busqueda
	 */
	/*INI-TRA042*/
	public void cargarCombos(AlertaForm formulario, String perfilUsuario, List<CentroGestorBean> listaCentrosGestores, String[] usuarios, Integer idAlerta, String busqueda) {
		//Combo modos Alerta
		ModoAlertaQuery modoAlertaQuery = new ModoAlertaQuery();
		List<ModoAlertaBean> modosAlerta;
		modosAlerta = modoAlertaManager.buscarModoAlertaAll(modoAlertaQuery);		
		setRequestAttribute("modosAlerta", modosAlerta);
		//Combo tipos Alerta
		TipoAlertaQuery tipoAlertaQuery=new TipoAlertaQuery();
		List<TipoAlertaBean> tiposAlerta ;
		tiposAlerta=tipoAlertaManager.buscarTipoAlertaAll(tipoAlertaQuery);
		setRequestAttribute("tiposAlerta", tiposAlerta);
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setEstado(Constantes.USUARIO_ESTADO_ACTIVO);
		
		//Si es Gestor Recuperamos el Centro Gestor.
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
		//Buscamos los usuarios que estan añadidos en la alerta
		List<UsuarioBean> usuariosSel=new ArrayList<UsuarioBean>();
		//cargar Combos  selecionados de usuarios.
		if("Cargar".equals(busqueda)){
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
		combosNoSeleccionados(usuarios,usuarioList);
	
		
		setRequestAttribute("usuarioList", usuarioList);
		setRequestAttribute("usuariosSel", usuariosSel);
	}
	/*FIN-TRA042*/
	
   /**
    * Combos no seleccionados.
    *
    * @param usuarios el usuarios
    * @param usuarioList el usuario list
    */
   private void combosNoSeleccionados(String[] usuarios, List<UsuarioBean> usuarioList) {
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
