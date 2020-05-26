package es.map.ipsg.action.tarifa;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.TipoAccesoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.TarifaForm;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerBuscarTarifaSpring.
 *
 * @author amartinl
 */
public class VerBuscarTarifaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerBuscarTarifaSpring.class);
	
	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el tarifa manager. */
	private TarifaManager tarifaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el tipo acceso. */
	private TipoAccesoManager tipoAcceso;
	
	/**
	 * Crea una nueva ver buscar tarifa spring.
	 */
	public VerBuscarTarifaSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setTarifaManager((TarifaManager) getBean("tarifaManager"));
				setGrupoManager((GrupoManager) getBean("grupoManager"));
				setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			} catch (Exception e) {
				logger.error("Error VerBuscarTarifaSpring - crear constructor",e);
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
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_tarifa = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_tarifa);
		String subMenu_tarifa = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.tarifa");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tarifa);
		//******************************************************************
	try{
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error VerBuscarTarifaSpring - recuperar UsuarioSesion"+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}

		//Obtenemos el Usuario que esta logueado en la aplicacion para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que esta logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		

		
		//Cogemos el form del jsp
		TarifaForm formulario;
		formulario = (TarifaForm) form;
		
		//Creamos la Query
		
		//Carga el Combo de los Centros Gestores
		cargarCombos();
		
	}catch(Exception eGenerico){
		logger.error("Error VerBuscarTarifaSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}


			
	
	/**
	 * Carga los Combos: Centro Gestor, Ministerio, Cuerpo_Escala, TipoSolicitud.
	 */
	public void cargarCombos() {
		
		GrupoQuery  grupoQuery = new GrupoQuery();
		List<GrupoBean> lGrupoBean;
		lGrupoBean = grupoManager.buscarGrupoCombo(grupoQuery);
	
		TipoAccesoQuery tipoAccesoQuery = new TipoAccesoQuery();
		List<TipoAccesoBean> lTipoAccesoBean;
		lTipoAccesoBean = 	tipoAcceso.buscarTipoAccesoCombo(tipoAccesoQuery);
				
		setRequestAttribute("grupo", lGrupoBean);
		setRequestAttribute("tipoAcceso", lTipoAccesoBean);
		
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
	 * Establece el grupo manager.
	 *
	 * @param grupoManager the grupoManager to set
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAcceso the tipoAcceso to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
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
