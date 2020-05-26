package es.map.ipsg.action.solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.TipoSolicitudQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.TipoSolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoSolicitudManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class VerBuscarSolicitudesRegistradasSpring.
 *
 * @author amartinl
 */
public class VerBuscarSolicitudesRegistradasSpring extends AbstractSpring {

	//Declarar los resource
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerBuscarSolicitudesRegistradasSpring.class);
	
	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el ministerios manager. */
	private MinisterioManager ministeriosManager;
	
	/** el cuerpos escala manager. */
	private CuerpoEscalaManager cuerposEscalaManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el tipo solicitud manager. */
	private TipoSolicitudManager tipoSolicitudManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva ver buscar solicitudes registradas spring.
	 */
	public VerBuscarSolicitudesRegistradasSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
				setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
				setSolicitudesRegistradasManager ((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setTipoSolicitudManager((TipoSolicitudManager)getBean("tipoSolicitudManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
				setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			} catch (Exception e) {
				logger.error(" Error VerBuscarSolicitudesRegistradasSpring:",e);
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
		String menu_solicitudes = RESOURCE_MESSAGE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudes);
		String subMenu_SolReg = RESOURCE_MESSAGE.getString("field.menuLateral.solicitudes.consultarSolRegistradas");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolReg);
		//******************************************************************
	try{
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
		

		//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();

		
		//Cogemos el form del jsp
		BuscarSolicitudesForm formulario;
		formulario = (BuscarSolicitudesForm) form;
		
		//Creamos la Query

		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		String sPerfilUsuario = "";
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		/*FIN-TRA042*/
		sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		formulario.setPerfilUsuario(sPerfilUsuario);
		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR) || sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_CONSULTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			
		}	
		//Carga el Combo de los Centros Gestores
		/*INI-TRA042*/
		cargarCombos(listaCentrosGestores);
		/*FIN-TRA042*/
		//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no 
		//y guardar la descripción para el formulario de búsqueda
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		if (sVieneMenu != null && sVieneMenu.equals("S"))
		{
			//Poner todos los campos del formulario en blanco
			formulario.setNif("");
			formulario.setNumSolicitud("");
			formulario.setIdTipo(0);
			formulario.setTipo("");
			formulario.setCuerpoEscala("");
			formulario.setDsCuerpoEscala("");
			formulario.setIdEspecialidad("");
			formulario.setDsEspecialidad("");
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");
			formulario.setCkFNacimiento("");
			formulario.setCkFFormacionOficial("");
			formulario.setAccion("BUSCAR");
			formulario.setSubmit("");
			formulario.setMarcarTodo("");
		}
		//Si viene a null es que viene de las demás páginas que no son del menú principal
		
		//FIN Limpiado de formulario y muestra de resultados.
		
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
		setRequestAttribute("sVieneMenu", "S"); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.
		
	}catch(Exception eGenerico){
		logger.error(" Error VerBuscarSolicitudesRegistradasSpring:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
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
			sUsernameLogin = usuarioBean.getLogin();
			return sUsernameLogin;
		}catch(Exception e){
			logger.error(" Error VerBuscarSolicitudesRegistradasSpring - recuperarUsuarioSesion:"+sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
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
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR))
		{
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE))
		{
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR))
		{
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR))
		{
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;
	}

	
	/**
	 * Carga los Combos: Centro Gestor, Ministerio, Cuerpo_Escala, TipoSolicitud.
	 * @param listaCentrosGestores 
	 */
	/*INI-TRA042*/
	public void cargarCombos(List<CentroGestorBean> listaCentrosGestores) {
		MinisterioQuery  ministerioQuery = new MinisterioQuery();
		List<MinisterioBean> lMinisterioBean;
		lMinisterioBean = 	ministeriosManager.buscarMinisterioCombo(ministerioQuery);
	
		if(listaCentrosGestores.size() <= 0) {
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			listaCentrosGestores = 	centroGestorManager.buscarCentroGestorCombo(centroGestorQuery);
		}
		
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		List<CuerpoEscalaBean> lCuerpoEscalaBean;
		lCuerpoEscalaBean = cuerposEscalaManager.buscarCuerposEscalaCombo(cuerpoEscalaQuery);
		
		TipoSolicitudQuery tipoSolicitudQuery = new TipoSolicitudQuery();
		List<TipoSolicitudBean> lTipoSolicitudBean;
		lTipoSolicitudBean = tipoSolicitudManager.buscarTipoSolicitudCombo(tipoSolicitudQuery);
		
		// Tipo de Acceso
		List<FormaAccesoBean> lFormaAccesoBean;
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		lFormaAccesoBean=formaAccesoManager.buscarFormaAccesoCombo(formaAccesoQuery);
		
		
		setRequestAttribute("tiposAcceso", lFormaAccesoBean);
		
		setRequestAttribute("centrosGestores", listaCentrosGestores);
		setRequestAttribute("ministerio", lMinisterioBean);
		setRequestAttribute("cuerposEscala", lCuerpoEscalaBean);
		setRequestAttribute("tipoSolicitud", lTipoSolicitudBean);
	}
	/*FIN-TRA042*/
	
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
	 * Obtiene el centro gestor manager.
	 *
	 * @return centroGestorManager CentroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager CentroGestorManager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}


	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return ministerioManager MinisterioManager
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
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return solicitudesRegistradasManager SolicitudesRegistradasManager
	 */ 
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public void setSolicitudesRegistradasManager(
			SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}

	/**
	 * Obtiene el tipo solicitud manager.
	 *
	 * @return tipoSolicitudManager TipoSolicitudManager
	 */
	public TipoSolicitudManager getTipoSolicitudManager() {
		return tipoSolicitudManager;
	}

	/**
	 * Establece el tipo solicitud manager.
	 *
	 * @param tipoSolicitudManager TipoSolicitudManager
	 */
	public void setTipoSolicitudManager(TipoSolicitudManager tipoSolicitudManager) {
		this.tipoSolicitudManager = tipoSolicitudManager;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return pagoSolicitudManager PagoSolicitudManager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager PagoSolicitudManager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return el forma acceso manager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager el nuevo forma acceso manager
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
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
