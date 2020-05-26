package es.map.ipsg.action.documentos;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerSubirDocumentosSpring.
 *
 * @author mromerve
 */
public class VerSubirDocumentosSpring extends AbstractSpring {

	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el ministerios manager. */
	private MinisterioManager ministeriosManager;
	
	/** el cuerpos escala manager. */
	private CuerpoEscalaManager cuerposEscalaManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerSubirDocumentosSpring.class);
	
	/** La constante STRING_ERRORGENERICO. */
	private static final String STRING_ERRORGENERICO = "errorGenerico";
	
	/**
	 * Crea una nueva ver subir documentos spring.
	 */
	public VerSubirDocumentosSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
				setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
				setModeloManager((ModeloManager)getBean("modelosManager"));
			} catch (Exception e) {
				logger.error("Error VerSubirDocumentosSpring():",e);
		}		
	}

	/**
	 * Do execute.
	 *
	 * @param form  SpringForm
	 * @return resultado
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
	
		getLogger().info("VerSubirDocumentosSpring -start");
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_documentos = RESOURCE_BUNDLE.getString("field.menu.documentos");
		this.getRequest().getSession().setAttribute("pagActiva",menu_documentos);
		String subMenu_subirDocumentos = RESOURCE_BUNDLE.getString("field.menuLateral.documentos.subirDocumento");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_subirDocumentos);
		//******************************************************************
		
		
		
		
		
	try{	
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals(STRING_ERRORGENERICO)) {
			return sUsernameLogin;
		}

		//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();
		
		
		//Comprobamos que tipo de perfil tiene el usuario 
		String sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		
		//Carga los Combos de la página
		cargarCombos();
		
		setRequestAttribute("sVieneMenu", "S");
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
		getLogger().info("VerSubirDocumentosSpring -end");

	}catch(Exception eGenerico){
		logger.error("Error VerSubirDocumentosSpring() - doExecute:" ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return STRING_ERRORGENERICO;
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
			sUsernameLogin = usuarioBean.getLogin();
			logger.info("sUsernameLogin: " + sUsernameLogin);
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error recuperarUsuario() - recuperar UsuarioSesion:" + sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return STRING_ERRORGENERICO;
		}
	}
			
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param idUsuario el id usuario
	 * @return el string
	 */
	private String comprobarPerfilUsuario(Integer idUsuario) {
		String sPerfil = "";
		UsuarioBean usuarioBean;
		usuarioBean = usuarioManager.obtenerUsuario(idUsuario);
		
		if (usuarioBean.getIdPerfil()!=null) {
			if (usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_OPERADOR)) {
				sPerfil = Constantes.ROL_OPERADOR;
			} else if (usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR)) {
				sPerfil = Constantes.ROL_GESTOR;
			} else if (usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR)) {
				sPerfil = Constantes.ROL_ADMINISTRADOR;
			} else if (usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_EMPRESA)) {
				sPerfil = Constantes.ROL_EMPRESA;
			} 
		}
		return sPerfil;
	}
	
	
	/**
	 * Carga los Combos: Centro Gestor, Ministerio, Cuerpo_Escala.
	 */
	public void cargarCombos() {
		MinisterioQuery  ministerioQuery = new MinisterioQuery();
		List<MinisterioBean> lMinisterioBean;
		lMinisterioBean = 	ministeriosManager.buscarMinisterioCombo(ministerioQuery);
		
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		List<CuerpoEscalaBean> lCuerpoEscalaBean;
		lCuerpoEscalaBean = cuerposEscalaManager.buscarCuerposEscalaCombo(cuerpoEscalaQuery);
		
		ModeloQuery modeloQuery = new ModeloQuery();
		List<ModeloBean> listaModelosBean;
		listaModelosBean = modeloManager.buscarModeloCombo(modeloQuery);
	
		setRequestAttribute("cuerpoEscala", lCuerpoEscalaBean);
		setRequestAttribute("ministerio", lMinisterioBean);
		setRequestAttribute("listaModelosBean",listaModelosBean);
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
	 * @param usuarioManager UsuarioManager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	
	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return ministeriosManager MinisterioManager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministeriosManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager MinisterioManager
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministeriosManager = ministerioManager;
	}

	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return cuerpoEscalaManager CuerpoEscalaManager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerposEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerposEscalaManager CuerpoEscalaManager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerposEscalaManager) {
		this.cuerposEscalaManager = cuerposEscalaManager;
	}

	/**
	 * Obtiene el cuerpos escala manager.
	 *
	 * @return cuerposEscalaManager CuerpoEscalaManager
	 */
	public CuerpoEscalaManager getCuerposEscalaManager() {
		return cuerposEscalaManager;
	}

	/**
	 * Establece el cuerpos escala manager.
	 *
	 * @param cuerposEscalaManager CuerpoEscalaManager
	 */ 
	public void setCuerposEscalaManager(CuerpoEscalaManager cuerposEscalaManager) {
		this.cuerposEscalaManager = cuerposEscalaManager;
	}

		
	/**
	 * Obtiene el modelo manager.
	 *
	 * @return el modelo manager
	 */
	public ModeloManager getModeloManager() {
		return modeloManager;
	}

	/**
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
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
