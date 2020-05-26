package es.map.ipsg.action.solicitudPresencial;

import java.util.ArrayList;
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
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarSolicitudesPresencialesForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoSolicitudManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class VerBuscarSolicitudPresencialSpring.
 *
 * @author amartinl
 */
public class VerBuscarSolicitudPresencialSpring extends AbstractSpring {

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
	
	/** el modelo manager. */
	private ModeloManager modeloManager;

	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerBuscarSolicitudPresencialSpring.class);
	
	/**
	 * Crea una nueva ver buscar solicitud presencial spring.
	 */
	public VerBuscarSolicitudPresencialSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
				setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
				setSolicitudesRegistradasManager ((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setTipoSolicitudManager((TipoSolicitudManager)getBean("tipoSolicitudManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setModeloManager((ModeloManager)getBean("modelosManager"));
				setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			} catch (Exception e) {
				logger.error("Error VerBuscarSolicitudPresencialSpring- crear constructor",e);
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
		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		//******************************************************************
		
		SpringMessages messages;
	try{	
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error VerBuscarSolicitudPresencialSpring - recuperar usuarioSesion"+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}

		//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();
		
		//Cogemos el form del jsp
		BuscarSolicitudesPresencialesForm formulario;
		formulario = (BuscarSolicitudesPresencialesForm) form;
		
		String sVieneMenu = this.getRequest().getParameter("menu");
		if(sVieneMenu != null && sVieneMenu.equals("S")){
			formulario.setDsCentroGestor("");
			formulario.setIdCentroGestor("");
			formulario.setIdCuerpoEscala(null);
			formulario.setDsCuerpoEscala("");
			formulario.setIdMinisterio(null);
			formulario.setIdModelo(null);//añadido
			formulario.setNif("");
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");
			formulario.setNumeroSolicitud("");
			formulario.setMarcarTodo("");
		}
		
		
		
	
		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		String sPerfilUsuario = "";
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		formulario.setPerfilUsuario(sPerfilUsuario);
		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR) || sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_OPERADOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());
		}		
		//Carga los Combos de la página
		cargarCombos(listaCentrosGestores);
		/*FIN-TRA042*/
		
		//Poner todos los campos del formulario en blanco			
			formulario.setNif("");			
			formulario.setNumSolicitud("");
			
			formulario.setCuerpoEscala("");
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");
			formulario.setCkFNacimiento("");
			formulario.setCkFFormacionOficial("");
			formulario.setAccion("BUSCAR");
		
			
		
		setRequestAttribute("sVieneMenu", "S");
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);

	}catch(Exception eGenerico){
		logger.error("Error VerBuscarSolicitudPresencialSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
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
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_OPERADOR))
		{
			sPerfil = Constantes.ROL_OPERADOR;
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
	 * Carga los Combos: Centro Gestor, Ministerio, Cuerpo_Escala.
	 * @param listaCentrosGestores 
	 */
	/*INI-TRA042*/
	public void cargarCombos(List<CentroGestorBean> listaCentrosGestores) {
		MinisterioQuery  ministerioQuery = new MinisterioQuery();
		if(listaCentrosGestores.size() > 0) {
			for(CentroGestorBean cg: listaCentrosGestores) {
				ministerioQuery.addIdIn(cg.getIdMinisterio());
			}
		}
		List<MinisterioBean> lMinisterioBean = 	ministeriosManager.buscarMinisterioCombo(ministerioQuery);

		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		List<CuerpoEscalaBean> lCuerpoEscalaBean = cuerposEscalaManager.buscarCuerposEscalaCombo(cuerpoEscalaQuery);
		
		ModeloQuery modeloQuery = new ModeloQuery();
		List<ModeloBean> listaModelosBean = modeloManager.buscarModeloCombo(modeloQuery);
	
		setRequestAttribute("cuerpoEscala", lCuerpoEscalaBean);
		setRequestAttribute("ministerio", lMinisterioBean);
		setRequestAttribute("listaModelosBean",listaModelosBean);
		setRequestAttribute("listaCentrosGestores", listaCentrosGestores);
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
