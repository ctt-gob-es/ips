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
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.EstadoConvocatoriaBean;
import es.map.ipsg.bean.EstadoSolicitudBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.SolicitudesIncidenciasForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.EstadoSolicitudManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerBuscarSolicitudesIncidenciasSpring.
 */
public class VerBuscarSolicitudesIncidenciasSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerBuscarSolicitudesIncidenciasSpring.class);
	
	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el ministerios manager. */
	private MinisterioManager ministeriosManager;	
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el estados convocatoria manager. */
	private EstadoConvocatoriaManager estadosConvocatoriaManager;

	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva ver buscar solicitudes incidencias spring.
	 */
	public VerBuscarSolicitudesIncidenciasSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));				
				setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
				setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
				setModeloManager((ModeloManager)getBean("modelosManager"));
				setEstadosConvocatoriaManager((EstadoConvocatoriaManager)getBean("estadosConvocatoriaManager"));
				setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			} catch (Exception e) {
				logger.error(" Error VerBuscarSolicitudesIncidenciasSpring:" ,e);
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
		String subMenu_SolIncidencia = RESOURCE_MESSAGE.getString("field.menuLateral.solicitudes.consultarSolIncidencias");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolIncidencia);
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
		SolicitudesIncidenciasForm formulario = (SolicitudesIncidenciasForm) form;
		
		
		
		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		String sPerfilUsuario = "";
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		/*FIN-TRA042*/
		sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		formulario.setPerfilUsuario(sPerfilUsuario);
		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR) || sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_CONSULTOR))
		{
			/*INI-TRA042*/
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());
			/*FIN-TRA042*/
			
		}	
		//Carga el Combo de los Centros Gestores
		cargarCombos(listaCentrosGestores);
		//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no 
		//y guardar la descripción para el formulario de búsqueda
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		if (sVieneMenu != null && sVieneMenu.equals("S"))
		{
			//Poner todos los campos del formulario en blanco
			formulario.setNif("");
			formulario.setNumSolicitud("");
			formulario.setIdEstado(0);
			formulario.setCuerpoEscala("");
			formulario.setDsCuerpoEscala("");
			formulario.setIdEspecialidad("");
			formulario.setDsEspecialidad("");
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");	
			formulario.setEstado("");
			formulario.setAccion("BUSCAR");
			formulario.setSubmit("");
			formulario.setMarcarTodo("");
		}
		//Si viene a null es que viene de las demás páginas que no son del menú principal
		
		//FIN Limpiado de formulario y muestra de resultados.
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
		setRequestAttribute("sVieneMenu", "S"); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.
		
	}catch(Exception eGenerico){
		logger.error(" Error VerBuscarSolicitudesIncidenciasSpring - doExecute:" ,eGenerico);
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
		String sUsernameLogin = "";;
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
			return sUsernameLogin;
		}catch(Exception e){
			logger.error(" Error VerBuscarSolicitudesIncidenciasSpring - recuperarUsuariosesion"+ sUsernameLogin ,e);
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
	 *
	 * @param listaCentrosGestores el centro gestor asociado bean
	 */
	/*INI-TRA042*/
	public void cargarCombos(List<CentroGestorBean> listaCentrosGestores) {
		MinisterioQuery  ministerioQuery = new MinisterioQuery();
		List<MinisterioBean> lMinisterioBean;
		lMinisterioBean = 	ministeriosManager.buscarMinisterioCombo(ministerioQuery);
	
		EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO);
		List<EstadoSolicitudBean> lEstadoSolicitudBean;
		lEstadoSolicitudBean = estadoSolicitudManager.buscarEstadoSolicitudCombo(estadoSolicitudQuery);
		
		List<EstadoSolicitudBean> lEstadoSolicitudBeanNuevos = new ArrayList<EstadoSolicitudBean>();

		EstadoSolicitudBean estAux = new EstadoSolicitudBean();
		// Estado SIN INTENTO DE PAGO
		estAux.setId(8);
		estAux.setDescripcion("SIN INTENTO DE PAGO");
		lEstadoSolicitudBeanNuevos.add(estAux);
		
		EstadoSolicitudBean estReg = new EstadoSolicitudBean();
		// Estado SIN INTENTO DE REGISTRO
		estReg.setId(9);
		estReg.setDescripcion("SIN INTENTO DE REGISTRO");
		lEstadoSolicitudBeanNuevos.add(estReg);
		
		// Tipo de Acceso
		List<FormaAccesoBean> lFormaAccesoBean;
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		lFormaAccesoBean=formaAccesoManager.buscarFormaAccesoCombo(formaAccesoQuery);
		
		List<ModeloBean> listaModelosBean;
		if(listaCentrosGestores != null && listaCentrosGestores.size()>0){
			ModeloQuery modeloQuery = new ModeloQuery();
//			modeloQuery.setIdModelo(listaCentrosGestores.getModelo().getIdModelo());
			for(CentroGestorBean cg: listaCentrosGestores) {
				modeloQuery.addIdModeloIn(cg.getModelo().getIdModelo());
			}
			listaModelosBean = modeloManager.buscarModeloCombo(modeloQuery);
		}else{
			listaModelosBean = modeloManager.buscarModeloComboTodos();
		}
		
		// Estados Convocatoria
		EstadoConvocatoriaQuery estadoQuery = new EstadoConvocatoriaQuery();
		estadoQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		estadoQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);
		List<EstadoConvocatoriaBean> listaEstadosConvoc = estadosConvocatoriaManager.buscarEstadoConvocatoriaCombo(estadoQuery);
		
		setRequestAttribute("tiposAcceso", lFormaAccesoBean);
		setRequestAttribute("estado", lEstadoSolicitudBean);
		setRequestAttribute("estadosNuevos", lEstadoSolicitudBeanNuevos);
		setRequestAttribute("listaModelosBean",listaModelosBean);
		setRequestAttribute("ministerio", lMinisterioBean);
		setRequestAttribute("listaEstadosConvoc", listaEstadosConvoc);
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
	 * Obtiene el estado solicitud manager.
	 *
	 * @return the estadoSolicitudManager
	 */
	public EstadoSolicitudManager getEstadoSolicitudManager() {
		return estadoSolicitudManager;
	}

	/**
	 * Establece el estado solicitud manager.
	 *
	 * @param estadoSolicitudManager the estadoSolicitudManager to set
	 */
	public void setEstadoSolicitudManager(
			EstadoSolicitudManager estadoSolicitudManager) {
		this.estadoSolicitudManager = estadoSolicitudManager;
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
	 * Obtiene el estados convocatoria manager.
	 *
	 * @return el estados convocatoria manager
	 */
	public EstadoConvocatoriaManager getEstadosConvocatoriaManager() {
		return estadosConvocatoriaManager;
	}

	/**
	 * Establece el estados convocatoria manager.
	 *
	 * @param estadosConvocatoriaManager el nuevo estados convocatoria manager
	 */
	public void setEstadosConvocatoriaManager(
			EstadoConvocatoriaManager estadosConvocatoriaManager) {
		this.estadosConvocatoriaManager = estadosConvocatoriaManager;
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
