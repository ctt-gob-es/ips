package es.map.ipsg.action.solicitud;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolComunIncidenciasViewQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.EstadoConvocatoriaBean;
import es.map.ipsg.bean.EstadoSolicitudBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.SolicitudesIncidenciasForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.EstadoSolicitudManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoSolicitudManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * BuscarSolicitudesIncidenciasAction.
 *
 * @author amartinl
 */
@SuppressWarnings("rawtypes")
public class BuscarSolicitudesIncidenciasSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";

	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarSolicitudesIncidenciasSpring.class);
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/** La constante TODO_NO. */
	private static final String TODO_NO = "TodoNo";
	
	/** La constante TODO_OK. */
	private static final String TODO_OK = "TodoOk";
	
	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el ministerios manager. */
	private MinisterioManager ministeriosManager;
	
	/** el cuerpos escala manager. */
	private CuerpoEscalaManager cuerposEscalaManager;
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el tipo solicitud manager. */
	private TipoSolicitudManager tipoSolicitudManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
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
	 * Crea una nueva buscar solicitudes incidencias spring.
	 */
	public BuscarSolicitudesIncidenciasSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
				setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
				setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
				setSolicitudesRegistradasManager ((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setTipoSolicitudManager((TipoSolicitudManager)getBean("tipoSolicitudManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
				setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
				setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
				setModeloManager((ModeloManager)getBean("modelosManager"));
				setEstadosConvocatoriaManager((EstadoConvocatoriaManager)getBean("estadosConvocatoriaManager"));
				setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			} catch (Exception e) {
				logger.warn(e);
				logger.error("Error BuscarSolicitudesIncidenciasSpring ",e);
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
		getLogger().debug("BuscarSolicitudesIncidenciasSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_solicitudes = RESOURCE_MESSAGE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		String subMenu_SolIncidencia = RESOURCE_MESSAGE.getString("field.menuLateral.solicitudes.consultarSolIncidencias");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolIncidencia);
		//******************************************************************
		
	try{	
		String numReg=null;
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

		String busqueda = formulario.getSubmit();
		//Paginación
		String cambios = this.getRequest().getParameter("cambios");
		String scroll = this.getRequest().getParameter("scroll");
		numReg = this.getRequest().getParameter("numRegistro");
		
		/*INI-TRA042*/
		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		String sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		formulario.setPerfilUsuario(sPerfilUsuario);
		
		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR) || sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_CONSULTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());

		}
		//Carga los Combos de la página
		cargarCombos(listaCentrosGestores);
		/*FIN-TRA042*/
		
		// Vemos si viene del link de Consulta log Errores
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		String sSolicitud = this.getRequest().getParameter("nSolicitud"); 
		
		if (sSolicitud != null )
		{
			//Poner todos los campos del formulario en blanco excepto el numero de solicitud informado
			formulario.setNif("");
			formulario.setNumSolicitud(sSolicitud);
			formulario.setIdEstado(0);
			formulario.setCuerpoEscala("");
			formulario.setDsCuerpoEscala("");
			formulario.setIdEspecialidad("");
			formulario.setDsEspecialidad("");
			formulario.setDsCuerpoEscala("");
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");	
			formulario.setSolExencion("");
			formulario.setEstado("");
			formulario.setAccion("BUSCAR");
			formulario.setSubmit("");
			formulario.setMarcarTodo("");
			sVieneMenu = "N";
		}
		//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no 
		//y guardar la descripción para el formulario de búsqueda
		else if (sVieneMenu != null && sVieneMenu.equals("S"))
		{
			//Poner todos los campos del formulario en blanco
			formulario.setNif("");
			formulario.setNumRegistro("");
			formulario.setIdCentroGestor("");
			formulario.setIdEspecialidad("");
			formulario.setDsEspecialidad("");
			formulario.setMinisterio("");
			formulario.setIdMinisterio(null);
			formulario.setModelo("");//añadido
			formulario.setIdModelo(null);//añadido
			formulario.setMarcarTodo("");
			formulario.setCuerpoEscala("");
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");	
			formulario.setSolExencion("");
			formulario.setAccion("BUSCAR");
		}else //Si viene a null es que viene de las demás páginas que no son del menú principal
		{
			sVieneMenu = "N";
		}
		//FIN Limpiado de formulario y muestra de resultados.
		
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
			comprobarMarcarTodos(formulario);
		}
		else
		{
			if(sSolicitud != null)
			{
				busqueda=STRING_BUSCAR;
			}	
		}	
		
		//Ordenación
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
	// INI - CPL - IPS-146 - Ordenacion
		String campoOrden=null;
		if(formulario.getCampo()!= null){
			try{
				campoOrden=cargarCampos(formulario.getCampo());
	
	// FIN - CPL - IPS-146 - Ordenacion	
			}catch(Exception e){
				logger.error("Error BuscarSolicitudesIncidenciasSpring- campoOrden "+campoOrden,e);
			}			
		}

		//INICIO1 Paginación 

		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		
		if((paginaActual==null || paginaActual.equals("")) && formulario.getPaginaActual()!=null && !formulario.getPaginaActual().equals("")){
			
			paginaActual=formulario.getPaginaActual();
			
		}
		
		if(formulario.isPulsaIr() == true)
		{
			paginaActual = formulario.getNumeroPaginaIr().toString();
			logger.info("PaginaActualIr: "+ paginaActual);
			formulario.setPulsaIr(false);
			formulario.setNumeroPaginaIr(null);
		}
		else
		{
			formulario.setNumeroPaginaIr(null);
			formulario.setPulsaIr(false);
		}
		
		if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
			int pagActu = Integer.parseInt(paginaActual);		
			int pagTotales = Integer.parseInt(paginaTotal);
			
			comprobarMarcarTodos(formulario);
			
			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagTotales);
			}
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		
		
		//FIN1 Paginación
			
		
		//Creamos la query para mostrar todas las solicitudes
		/*INI-TRA042*/
		SolComunIncidenciasViewQuery solicitudQuery = crearQuerySolicitudesIncidenciasView(formulario,paginaActual,numReg,campoOrden,listaCentrosGestores);
		/*FIN-TRA042*/
		//Coger la lista de solicitudes a mostrar
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery ();
		List<SolicitudBean> lSolicitudBean = new ArrayList<SolicitudBean>();
		List<SolicitudBean> lSolicitudBeanConExencion = new ArrayList<SolicitudBean>();
		//Quita los check marcados si se pincha en Buscar
		if(STRING_BUSCAR.equals(busqueda))
		{
			formulario.setSolicitudesSel(new String[1]); 
		}
		if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)
				|| "Exportar".equals(busqueda)){
			lSolicitudBean = solicitudesRegistradasManager.buscarSolicitudesIncidenciasVista(solicitudQuery);
			//Error sin resultados
			if (lSolicitudBean != null && lSolicitudBean.size() == 0) {
				SpringMessages errors = new SpringMessages();
				errors.add("errorSinResultados", new SpringMessage("field.solicitudIncidencia.error"));
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
			}
			//Rellenar el campo de Solicita Exención
			Iterator<SolicitudBean> it = lSolicitudBean.iterator();
			while (it.hasNext())
			{
				SolicitudBean solicitudBean = (SolicitudBean) it.next();
				SolicitudComunQuery solicitudQueryAux= new SolicitudComunQuery();
				solicitudQueryAux.setIdSolicitud(solicitudBean.getId());
				pagoSolicitudQuery.setSolicitudComun(solicitudQueryAux);
				solicitudBean = pagoSolicitudManager.completaDatosPagoSolicitud(pagoSolicitudQuery, solicitudBean);
				
				lSolicitudBeanConExencion.add(solicitudBean);
			}
			//Paginación. cambios
			cambios = "Correcto";
		}
				
		//INICIO2 PAGINACIÓN
		int pagTotal = 0, numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			numReg = Constantes.NUMERO_REGISTROS_PAGINA;
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			numRegistros = comprobarEntero(numReg);
		}else{
			numRegistros = comprobarEntero(formulario.getNumRegistro());
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
		if(lSolicitudBean != null && lSolicitudBean.size()!= 0){
			pagTotal= lSolicitudBean.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(lSolicitudBean.size()>numRegistros){
				lSolicitudBean.remove(lSolicitudBean.size()-1);
			}
		}	
		if(paginaActual==null||"".equals(paginaActual)){
			paginaActual="1";
		}
		
		if(formulario.getIdModelo()!=null && formulario.getIdModelo()!=0){
			setRequestAttribute("idModelo", formulario.getIdModelo());
		}
		
		setRequestAttribute("cambios", cambios);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);	
		formulario.setPaginasTotales(String.valueOf(numPaginas));
		//FIN2 PAGINACIÓN
		//Pasar la solicitud y la pagina al jsp
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
		setRequestAttribute("solicitudes", lSolicitudBeanConExencion);
		setRequestAttribute("sVieneMenu", sVieneMenu); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.
		logger.info("lSolicitudBean");
		setRequestAttribute("scroll", scroll);	
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarSolicitudesIncidenciasSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}

	/**
	 * Comprobar marcar todos.
	 *
	 * @param formulario el formulario
	 */
	private void comprobarMarcarTodos(SolicitudesIncidenciasForm formulario) {
		if(this.getRequest().getParameter("marcarTodo") != null && TODO_NO.equals(this.getRequest().getParameter("marcarTodo"))) {
			formulario.setMarcarTodo(TODO_NO);
		}else if(TODO_OK.equals(this.getRequest().getParameter("marcarTodo"))){
			formulario.setMarcarTodo(TODO_OK);
		}else {
			formulario.setMarcarTodo("");
		}
	}

	/**
	 * Comprobar entero.
	 *
	 * @param numero el numero
	 * @return el int
	 */
	private int comprobarEntero(String numero) {
		int resultado = 0;
		try{
			resultado = Integer.parseInt(numero);
		}catch(Exception e){
			logger.error("Error BuscarSolicitudesIncidenciasSpring - comprobarEntero() - numRegistros"+ resultado,e);
			resultado = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return resultado;
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
			logger.warn(e);
			logger.error("Error BuscarSolicitudesIncidenciasSpring - recuperarUsuarioSesion "+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Cargar campos.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCampos(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){
			logger.error("Error BuscarSolicitudesIncidenciasSpring -  cargarCampos- error parsear campo"+ codCampo,e);
		}
		if (campo != null && !"".equals(campo) && !campo.equals("null")){
			
				switch(codCampo){
						//Los campos de ordenacion del jsp
						case 1:auxCampo = SolComunIncidenciasViewQuery.NUMEROSOLICITUD;break;
						case 2:auxCampo = SolComunIncidenciasViewQuery.NIFSOLICITUD;break;
						case 3:auxCampo = SolComunIncidenciasViewQuery.NOMBRESOLICITUD;break;
						case 4:auxCampo = SolComunIncidenciasViewQuery.PRIMERAPELLIDOSOLICITUD;break;	//Si es Gestor o Consultor se carga el campo
						case 5:auxCampo = SolComunIncidenciasViewQuery.SEGUNDOAPELLIDOSOLICITUD;break;
						case 6:auxCampo = SolComunIncidenciasViewQuery.EMAILSOLICITUD;break;
						case 7:auxCampo = SolComunIncidenciasViewQuery.TELEFONOSOLICITUD;break;
						case 8:auxCampo = SolComunIncidenciasViewQuery.EJERCICIOCONVOCATORIA;break;
						case 9:auxCampo = SolComunIncidenciasViewQuery.IDCONVOCATORIA;break;
						case 10:auxCampo = SolComunIncidenciasViewQuery.IDCENTROGESTOR;break;
						case 11:auxCampo = SolicitudComunQuery.FECHASOLICITUD;break;
						default:break;
					}
				
			}
		return auxCampo;
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
	 * Crear query solicitudes incidencias view.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param campoOrden el campo orden
	 * @param listaCentrosGestores 
	 * @return el sol comun incidencias view query
	 */
	// INI - CPL - IPS-146 - Ordenacion
	/*INI-TRA042*/
	private SolComunIncidenciasViewQuery crearQuerySolicitudesIncidenciasView (SolicitudesIncidenciasForm formulario,String paginaActual,String numReg, String campoOrden, List<CentroGestorBean> listaCentrosGestores)
	{
		String campo=campoOrden;
		String direccion = formulario.getDireccion();		
		
		SolComunIncidenciasViewQuery solicitudQuery = new SolComunIncidenciasViewQuery();
		EstadoSolicitudQuery  estadoSolicitudQuery = new EstadoSolicitudQuery();
		
		String nif = formulario.getNif();
		String numSolicitud = formulario.getNumSolicitud();		
		Integer idMinisterio = formulario.getIdMinisterio();
		Integer idModelo = formulario.getIdModelo();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String fechaDesde = formulario.getFechaDesde();
		String fechaHasta = formulario.getFechaHasta();
		Integer idEstado = formulario.getIdEstado();
		Integer idTipoAcceso = formulario.getIdTipoAcceso();
		Integer idEstadoConvoc = formulario.getIdEstadoConvoc();
		String solExencion = formulario.getSolExencion();
	// FIN - CPL - IPS-146 - Ordenacion	
		//INICIO PAGINACIÓNQuery

		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarSolicitudesIncidenciasSpring - crearQuerySolicitudesIncidenciasView - error parsear numReg"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarSolicitudesIncidenciasSpring - crearQuerySolicitudesIncidenciasView - error parsear numReg"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		
		//Si la pagina actual no esta definida, se establece a 1
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal; //+1
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
				
		if(paginaActual != null){
			solicitudQuery.setMaxResults(tamanyoPaginacion);
			solicitudQuery.setFirstResult(primerRegistro);
		}
		
		solicitudQuery.setCalculateNumResults(true);
		//FIN PAGINACIÓNQuery
			
		//Comprobar los filtros para realizar la busqueda
		//Añadimos los valores distintos al estado Registrado menos el eliminado
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO);
				
		//Nif
		if (nif != null && !nif.equals("") ){
			solicitudQuery.setNifSolicitud(nif);
		}
		
		//Número de solicitud
		if (numSolicitud != null && !numSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(numSolicitud);
		}
		
		//Ministerio
		if (idMinisterio != null && idMinisterio.intValue() != 0){
			
			solicitudQuery.setIdMinisterio(idMinisterio.shortValue());
		}
		
		//Modelo
		if(idModelo!=null && idModelo.intValue()!=0){
			solicitudQuery.setIdModelo(idModelo.shortValue());
		}
		
		// Centro Gestor
		if(sIdCentroGestor != null && !sIdCentroGestor.equals("")){	
			Integer idCentroGestor = Integer.valueOf(sIdCentroGestor);
			solicitudQuery.setIdCentroGestor(idCentroGestor);
		} else if(listaCentrosGestores.size() > 0){
			for(CentroGestorBean cg: listaCentrosGestores){
				solicitudQuery.addIdCentroGestorIn(cg.getId());
			}
		}
		
		// Cuerpo Escala
		if(formulario.getCuerpoEscala() != null && !formulario.getCuerpoEscala().equals("")){
			Integer idCuerpoEscala = Integer.valueOf(formulario.getCuerpoEscala());
			solicitudQuery.setIdCuerpoEscala(idCuerpoEscala);
		}	
		

		// Especialidad		
		if (formulario.getIdEspecialidad() != null && !formulario.getIdEspecialidad().equals("")) {
			Integer idEspecialidad = Integer.valueOf(formulario.getIdEspecialidad());
			solicitudQuery.setIdEspecialidad(idEspecialidad.shortValue());
		}	
		
		//Fecha Desde
		if (fechaDesde != null && !fechaDesde.equals("")){
			try{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaSolicitudMin(dFechaDesde);
				
			} catch (java.text.ParseException e) {
				logger.error("Error BuscarSolicitudesIncidenciasSpring - crearQuerySolicitudesIncidenciasView - error parsear fechaDesde"+ fechaDesde,e);
			}
		
		}
		//Fecha Hasta
		if (fechaHasta != null && !fechaHasta.equals("")){
			try{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaSolicitudMax(dFechaHasta);
				
			} catch (java.text.ParseException e) {
				logger.error("Error BuscarSolicitudesIncidenciasSpring - crearQuerySolicitudesIncidenciasView - error parsear fechaHasta"+ fechaHasta,e);
			}		
		}
		// Tipo Acceso
		if(idTipoAcceso != null && idTipoAcceso!=0){
			solicitudQuery.setIdFormaAcceso(idTipoAcceso);
		}	
		//Estado
		if(idEstado!=null && idEstado.intValue() != 0){
			if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_SIN_INTENTO_PAGO){
				// Solicitudes en estado NO PAGADO con 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO.byteValue());
				solicitudQuery.setIntentosPagoSolicitud(new BigDecimal(0));
					
			}else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_SIN_INTENTO_REGISTRO){
				// Solicitudes en estado NO REGISTRADO con 0 intentos de registro
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO.byteValue());
				solicitudQuery.setIntentosRegistroSolicitud(new BigDecimal(0));
			}else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_NO_PAGADO){
				// Solicitudes en estado NO PAGADO o PROCESO PAGO con más de 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO.byteValue());
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_NO_REGISTRADO){
				// Solicitudes en estado NO REGISTRADO con más de 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO.byteValue());
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO){
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO ||
					idEstado.intValue() == Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO){
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}	
		}
		
		// Estado de la convocatoria
		if(idEstadoConvoc != null && idEstadoConvoc != 0){
			solicitudQuery.setIdEstadoConvoc(idEstadoConvoc.byteValue());
		}
		
		if (solExencion != null && !solExencion.equals("")) {
			solicitudQuery.setSolExencion(solExencion.charAt(0));
		}
		
		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			
			if(campo.equals("nif")){
				solicitudQuery.addOrder("nifSolicitud",orden);
			}else if(campo.equals("nombre")){
				solicitudQuery.addOrder("nombreSolicitud",orden);
			}else if(campo.equals("email")){
				solicitudQuery.addOrder("emailSolicitud",orden);
			}else if(campo.equals("primerApellido")){
				solicitudQuery.addOrder("primerApellidoSolicitud",orden);
			}else if(campo.equals("segundoApellido")){
				solicitudQuery.addOrder("segundoApellidoSolicitud",orden);
			}else if(campo.equals("telefono")){
				solicitudQuery.addOrder("telefonoSolicitud",orden);
			}else if(campo.equals("ejercicio")){
				solicitudQuery.addOrder("ejercicioConvocatoria",orden);
			}else if(campo.equals("convocatoria")){
				solicitudQuery.addOrder("idConvocatoria",orden);
			}else if(campo.equals("centroGestor")){
				solicitudQuery.addOrder("siglasCentroGestor",orden);
			}else{
				solicitudQuery.addOrder(campo, orden);
			}
		}
		getLogger().debug("solicitudQuery creada");
		return solicitudQuery;
	}
	/*FIN-TRA042*/
	
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
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO);
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
			for(CentroGestorBean cg: listaCentrosGestores) {
				modeloQuery.addIdModeloIn(cg.getModelo().getIdModelo());
			}
			listaModelosBean = modeloManager.buscarModeloCombo(modeloQuery);
		}else{
			ModeloQuery modeloQuery = new ModeloQuery();
			listaModelosBean = modeloManager.buscarModeloCombo(modeloQuery);
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
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
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudesManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return el registro solicitud manager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager el nuevo registro solicitud manager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
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
	 * Obtiene el especialidad manager.
	 *
	 * @return el especialidad manager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager el nuevo especialidad manager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
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
