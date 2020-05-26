package es.map.ipsg.action.solicitud;

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
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoSolicitudQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.TipoSolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoSolicitudManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class BuscarSolicitudesRegistradasSpring.
 *
 * @author amartinl
 */
public class BuscarSolicitudesRegistradasSpring extends AbstractSpring {

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
	
	/** el tipo solicitud manager. */
	private TipoSolicitudManager tipoSolicitudManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;	
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarSolicitudesRegistradasSpring.class);	
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/** La constante TODO_NO. */
	private static final String TODO_NO = "TodoNo";
	
	/** La constante TODO_OK. */
	private static final String TODO_OK = "TodoOk";
	
	/**
	 * Crea una nueva buscar solicitudes registradas spring.
	 */
	public BuscarSolicitudesRegistradasSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
				setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
				setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
				setSolicitudesRegistradasManager ((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setTipoSolicitudManager((TipoSolicitudManager)getBean("tipoSolicitudManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
				setModeloManager((ModeloManager)getBean("modelosManager"));
				setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			} catch (Exception e) {
				logger.error("Error BuscarSolicitudesRegistradasSpring",e);
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
		getLogger().debug("BuscarSolicitudesRegistradasSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		String subMenu_SolReg = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudes.consultarSolRegistradas");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolReg);
		//******************************************************************
	try{
		String numReg = null;
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}

		//Obtenemos el Usuario que esta logueado en la aplicacion para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que esta logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();
		
		//Cogemos el form del jsp
		BuscarSolicitudesForm formulario;
		formulario = (BuscarSolicitudesForm) form;
		String busqueda = formulario.getSubmit();
		
		//Paginacion
		String cambios = this.getRequest().getParameter("cambios");
		numReg = this.getRequest().getParameter("numRegistro");
		
		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		String sPerfilUsuario = "";
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
		formulario.setPerfilUsuario(sPerfilUsuario);
		
		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR) 
				|| sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_CONSULTOR)){
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());
		}		
		
		//Carga los Combos de la pagina
		cargarCombos(listaCentrosGestores);
		/*FIN-TRA042*/
		// Vemos si viene del link de Consulta log Errores
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		String sSolicitud = this.getRequest().getParameter("nSolicitud"); 
		
		if (sSolicitud != null){
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
		
		//Indicamos si viene desde el enlace del menu para mostrar los resultados de busqueda o no 
		//y guardar la descripcion para el formulario de busqueda 
		if (sVieneMenu != null && sVieneMenu.equals("S")){
			//Poner todos los campos del formulario en blanco			
			formulario.setNif("");			
			formulario.setNumSolicitud("");
			formulario.setIdCentroGestor("");
			formulario.setDsCentroGestor("");
			formulario.setMinisterio("");
			formulario.setIdMinisterio(null);
			formulario.setModelo("");//anyadido
			formulario.setIdModelo(null);//anyadido
			formulario.setMarcarTodo("");			
			formulario.setIdTipo(0);
			formulario.setTipo("");
			formulario.setCuerpoEscala("");
			formulario.setDsCuerpoEscala("");
			formulario.setIdEspecialidad("");
			formulario.setDsEspecialidad("");
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");
			formulario.setSolExencion("");
			formulario.setCkFNacimiento("");
			formulario.setCkFFormacionOficial("");
			formulario.setAccion("BUSCAR");
		//Si viene a null es que viene de las demas paginas que no son del menu principal
		}else{
			sVieneMenu = "N";
		}
		//FIN Limpiado de formulario y muestra de resultados.
		
		//Ordenacion
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		
		// INI - CPL - IPS-146 - Ordenacion
		String campoOrden =null;
		if(formulario.getCampo()!= null){
		
			try{
				campoOrden=cargarCampos(formulario.getCampo());
			
		// FIN - CPL - IPS-146 - Ordenacion	
			}catch(Exception e){
				logger.error("Error BuscarSolicitudesRegistradasSpring - campoOrden"+ campoOrden,e);
			}			
		}

		//INICIO1 Paginacion 

	    String paginaActual = this.getRequest().getParameter("paginaActual");
	    String paginaTotal = this.getRequest().getParameter("paginasTotales");

		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
			comprobarMarcarTodos(formulario);
		}else {
			if(sSolicitud != null){
				busqueda=STRING_BUSCAR;
			}
		}
		if(formulario.isPulsaIr() == true){
			paginaActual = formulario.getNumeroPaginaIr().toString();
			logger.info("PaginaActualIr: "+ paginaActual);
			formulario.setPulsaIr(false);
			formulario.setNumeroPaginaIr(null);
		}else{
			formulario.setNumeroPaginaIr(null);
			formulario.setPulsaIr(false);
		}
		if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
			int pagActu = Integer.parseInt(paginaActual);		
			int pagTotales = Integer.parseInt(paginaTotal);

			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagTotales);
			}
		}
			
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		
		//FIN1 Paginacion
			
		//Creamos la query para mostrar todas las solicitudes
		/*INI-TRA042*/
		SolComunRegistradasViewQuery solicitudQuery = crearQuerySolicitudesRegistradasVista(formulario,numReg,paginaActual,campoOrden, listaCentrosGestores);
		/*FIN-TRA042*/
		
		//Coger la lista de solicitudes a mostrar
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery ();
		List<SolicitudBean> lSolicitudBean = new ArrayList<SolicitudBean>();
		List<SolicitudBean> lSolicitudBeanAux;
		List<SolicitudBean> lSolicitudBeanConExencion = new ArrayList<SolicitudBean>();
		
		//Quita los check marcados si se pincha en Buscar
		if(STRING_BUSCAR.equals(busqueda)){
			formulario.setSolicitudesSel(new String[1]); 
		}
		
		if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda) || "Verificar".equals(busqueda)
				|| "Exportar".equals(busqueda)){
			lSolicitudBeanAux = solicitudesRegistradasManager.buscarSolicitudesRegistradasVista(solicitudQuery);
			
			// Ordenar resultado y suprimir duplicados en caso de existir
			lSolicitudBean = eliminarDuplicados(lSolicitudBeanAux);
			
			//Error sin resultados
			if (lSolicitudBean != null && lSolicitudBean.size() == 0) {
				SpringMessages errors = new SpringMessages();
				errors.add("errorSinResultados", new SpringMessage("field.solicitudRegistrada.error"));
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
			}
			//Rellenar el campo de Solicita Exencion
			Iterator<SolicitudBean> it = lSolicitudBean.iterator();
			while (it.hasNext()){
				SolicitudBean solicitudBean = (SolicitudBean) it.next();
				SolicitudComunQuery solicitudQueryAux= new SolicitudComunQuery();
				solicitudQueryAux.setIdSolicitud(solicitudBean.getId());
				pagoSolicitudQuery.setSolicitudComun(solicitudQueryAux);
				solicitudBean = pagoSolicitudManager.completaDatosPagoSolicitud(pagoSolicitudQuery, solicitudBean);
				lSolicitudBeanConExencion.add(solicitudBean);
			}
		
			//Paginacion. cambios
			cambios = "Correcto";
		}
				
		//INICIO2 PAGINACIoN
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
		//FIN2 PAGINACIoN
		//Pasar la solicitud y la pagina al jsp
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
		setRequestAttribute("solicitudes", lSolicitudBeanConExencion);
		setRequestAttribute("sVieneMenu", sVieneMenu); //S = Viene del menu principal / N = Viene de cualquier otra pantalla.
		getLogger().debug("BuscarSolicitudesRegistradasSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarSolicitudesRegistradasSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}

	/**
	 * Comprobar marcar todos.
	 *
	 * @param formulario el formulario
	 */
	private void comprobarMarcarTodos(BuscarSolicitudesForm formulario) {
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
			logger.error("Error BuscarSolicitudesRegistradasSpring - parsear numRegistros"+ resultado,e);
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
			logger.error("Error BuscarSolicitudesRegistradasSpring - recuperarUsuarioSesion"+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}

	/**
	 * Metodo que suprime solicitudes con registros OK duplicados.
	 *
	 * @param lSolicitudBean el l solicitud bean
	 * @return listaSolcitudesAux
	 */
	private List<SolicitudBean> eliminarDuplicados(List<SolicitudBean> lSolicitudBean) {
		
		ArrayList<SolicitudBean> listaSolcitudesAux = new ArrayList<SolicitudBean>();
		ArrayList<Long> listaIdSolicitudes = new ArrayList<Long>();

		for (int i=0; i<lSolicitudBean.size(); i++){
			if(!listaIdSolicitudes.contains(lSolicitudBean.get(i).getId())){
				listaIdSolicitudes.add(lSolicitudBean.get(i).getId());
				listaSolcitudesAux.add(lSolicitudBean.get(i));
			}else{
				logger.error("La solicitud "+lSolicitudBean.get(i).getId()+ " tiene duplicados en REGISTRO_SOLICITUD");
			}
		}
		
		return listaSolcitudesAux;
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
		
	
		
		if (campo != null && !"".equals(campo) && !campo.equals("null")){
			
			try{
				codCampo = Integer.parseInt(campo);
			}catch(Exception e){
				logger.error("Error BuscarSolicitudesRegistradasSpring - cargarcampos - parsear campo"+ codCampo,e);
			}
			
				switch(codCampo){
				//Los campos de ordenacion del jsp
					case 1:auxCampo = SolComunRegistradasViewQuery.NUMEROSOLICITUD;break;
					case 2:auxCampo = SolComunRegistradasViewQuery.NIF;break;
					case 3:auxCampo = SolComunRegistradasViewQuery.NOMBRE;break;
					case 4:auxCampo = SolComunRegistradasViewQuery.PRIMERAPELLIDO;break;	
					case 5:auxCampo = SolComunRegistradasViewQuery.SEGUNDOAPELLIDO;break;
					case 6:auxCampo = SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA;break;
					case 7:auxCampo = SolComunRegistradasViewQuery.IDCENTROGESTOR;break;
					case 8:auxCampo = SolComunRegistradasViewQuery.IDCONVOCATORIA;break;
					case 9:auxCampo = SolComunRegistradasViewQuery.DESCTIPOSOLICITUD;break;
					case 10:auxCampo = SolComunRegistradasViewQuery.IDCONSENTIMIENTO;break;
					case 11:auxCampo = SolComunRegistradasViewQuery.EDADVERIFICADA;break;
					case 12:auxCampo = SolComunRegistradasViewQuery.FECHANACIMIENTOVERIFICADA;break;
					case 13:auxCampo = SolComunRegistradasViewQuery.TITULOVERIFICADO;break;
					case 14:auxCampo = SolComunRegistradasViewQuery.DESEMPLEOVERIFICADO;break;
					case 15:auxCampo = SolComunRegistradasViewQuery.IDESPECIALIDAD;break;
					case 16:auxCampo = SolComunRegistradasViewQuery.FNUMEROSAVERIFICADO;break;
					case 17:auxCampo = SolComunRegistradasViewQuery.DISCAPACIDADVERIFICADO;break;
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
		if(usuarioBean.getIdPerfil() != null 
				&& usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR)){
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		
		if(usuarioBean.getIdPerfil() != null 
				&& usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE)){
			sPerfil = Constantes.ROL_SOPORTE;
		}
		
		if(usuarioBean.getIdPerfil() != null 
				&& usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR)){
			sPerfil = Constantes.ROL_GESTOR;
		}
		
		if(usuarioBean.getIdPerfil() != null 
				&& usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR)){
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;
	}
	
	/**
	 * Crear query solicitudes registradas vista.
	 *
	 * @param formulario el formulario
	 * @param numReg el num reg
	 * @param paginaActual el pagina actual
	 * @param campoOrden el campo orden
	 * @param listaCentrosGestores 
	 * @return el sol comun registradas view query
	 */
	// INI - CPL - IPS-146 - Ordenacion
	/*INI-TRA042*/
	private SolComunRegistradasViewQuery crearQuerySolicitudesRegistradasVista (BuscarSolicitudesForm formulario,String numReg,String paginaActual,String campoOrden, List<CentroGestorBean> listaCentrosGestores)
	{
		SolComunRegistradasViewQuery solicitudQuery = new SolComunRegistradasViewQuery();
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		ModeloQuery modeloQuery = new ModeloQuery();
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		Integer idTipoAcceso = formulario.getIdTipoAcceso();
		String sNifFormulario = formulario.getNif();
		String sNumSolicitud = formulario.getNumSolicitud();
		Integer iIdModelo = formulario.getIdModelo();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		Integer iIdTipo = formulario.getIdTipo();
		String campo=campoOrden;
		String direccion = formulario.getDireccion();
		String solExencion = formulario.getSolExencion();
	// FIN - CPL - IPS-146 - Ordenacion		
		//INICIO PAGINACIoNQuery

		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		
		int numRegistros = 0;
		
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarSolicitudesRegistradasSpring - crearQuerySolicitudesRegistradasVista - parsear numRegistros"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarSolicitudesRegistradasSpring - crearQuerySolicitudesRegistradasVista - parsear numRegistros"+ numRegistros,e);
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
			comprobarMarcarTodos(formulario);
		}
		solicitudQuery.setCalculateNumResults(true);
		//FIN PAGINACIoNQuery
	
		//Comprobar los filtros para realizar la busqueda
		//Estado de solicitudes Registradas 3
		// No es necesario setear el estado puesto que la vista filtra por solicitudes registradas
		
		//Nif
		if (sNifFormulario != null && !sNifFormulario.equals("") ){
			solicitudQuery.setNif(sNifFormulario);
		}
		
		//Numero de solicitud
		if (sNumSolicitud != null && !sNumSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(sNumSolicitud);
		}
		
		//Ministerio
		Integer idMinisterio = formulario.getIdMinisterio();
		if (idMinisterio != null && idMinisterio.intValue() != 0){
			
			solicitudQuery.setIdMinisterio(idMinisterio.shortValue());
		}
		
		//Modelo anyadido
		Integer idModelo = formulario.getIdModelo();
		if(idModelo!=null && idModelo.intValue()!=0){
			modeloQuery.setIdModelo(iIdModelo);
			centroGestorQuery.setModelo(modeloQuery);
			convocatoriaQuery.setModelo(modeloQuery);
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
		if (formulario.getIdEspecialidad() != null && !formulario.getIdEspecialidad().equals("")){
			Integer idEspecialidad = Integer.valueOf(formulario.getIdEspecialidad());
			solicitudQuery.setIdEspecialidad(idEspecialidad.shortValue());
		}
				
		//Tipo Solicitud
		if(iIdTipo != null && iIdTipo.intValue() != 0){
			solicitudQuery.setIdTipoSolicitud(iIdTipo);
		}
		
		//Fecha Desde (FECHA REGISTRO)
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaRegistroMin(dFechaDesde);
				
			} catch (java.text.ParseException e) {
				logger.error("Error BuscarSolicitudesRegistradasSpring - crearQuerySolicitudesRegistradasVista - parsear fechaDesde"+ sFechaDesde,e);
			}
		
		}
		
		//Fecha Hasta (FECHA REGISTRO)
		if (sFechaHasta != null && !sFechaHasta.equals("")){
			try	{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaRegistroMax(dFechaHasta);
				
			} catch (java.text.ParseException e) {
				logger.error("Error BuscarSolicitudesRegistradasSpring - crearQuerySolicitudesRegistradasVista - parsear fechaHasta"+ sFechaHasta,e);
			}
		}
		
		if (solExencion != null && !solExencion.equals("")) {
			solicitudQuery.setSolExencion(solExencion.charAt(0));
		}
		
		// Tipo Acceso
		if(idTipoAcceso != null && idTipoAcceso!=0){
			solicitudQuery.setIdFormaAcceso(idTipoAcceso);
		}
		
		//Ordenacion
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			
			if(campo.equals("convocatoria")){
				solicitudQuery.addOrder("idConvocatoria",orden);
			}else if(campo.equals("centroGestor")){
				solicitudQuery.addOrder("siglasCentroGestor",orden);
			}else if(campo.equals("tipoSolicitud")){
				solicitudQuery.addOrder("descTipoSolicitud",orden);
			}else  if(campo.equals("idConsentimiento")){
				if("up".equals(direccion)){
				solicitudQuery.addOrder(campo,orden); 
				}else{
				solicitudQuery.addOrder(campo,orden);
				}
			}else{
				solicitudQuery.addOrder(campo, orden);
			}
		}
		return solicitudQuery;
	}
	
	/**
	 * Carga los Combos: Centro Gestor, Ministerio, Cuerpo_Escala, TipoSolicitud, Modelos.
	 *
	 * @param listaCentrosGestores el centro gestor asociado bean
	 */
	public void cargarCombos(List<CentroGestorBean> listaCentrosGestores) {
		MinisterioQuery  ministerioQuery = new MinisterioQuery();
		if(listaCentrosGestores != null && listaCentrosGestores.size()>0){
			for(CentroGestorBean cg: listaCentrosGestores) {
				ministerioQuery.addIdIn(cg.getIdMinisterio());
			}
		}
		List<MinisterioBean> lMinisterioBean = 	ministeriosManager.buscarMinisterioCombo(ministerioQuery);
		
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		List<CuerpoEscalaBean> lCuerpoEscalaBean = cuerposEscalaManager.buscarCuerposEscalaCombo(cuerpoEscalaQuery);
		
		TipoSolicitudQuery tipoSolicitudQuery = new TipoSolicitudQuery();
		List<TipoSolicitudBean> lTipoSolicitudBean = tipoSolicitudManager.buscarTipoSolicitudCombo(tipoSolicitudQuery);
		
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
			listaModelosBean = modeloManager.buscarModeloComboTodos();
		}
		
		setRequestAttribute("tiposAcceso", lFormaAccesoBean);
		setRequestAttribute("cuerpoEscala", lCuerpoEscalaBean);
		setRequestAttribute("tipoSolicitud", lTipoSolicitudBean);
		setRequestAttribute("ministerio", lMinisterioBean);
		setRequestAttribute("listaModelosBean", listaModelosBean);
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
