package es.map.ipsg.action.solicitudPresencial;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

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
//import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolComunAuxiliarViewQuery;
import es.map.ips.model.SolicitudComunQuery;
//import es.map.ips.model.TipoSolicitudQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarModelo790Form;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoSolicitudManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class BuscarModelo790Spring.
 *
 * @author amartinl
 */
public class BuscarModelo790Spring extends AbstractSpring {

	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el ministerios manager. */
	private MinisterioManager ministeriosManager;
	
	/** el cuerpos escala manager. */
	private CuerpoEscalaManager cuerposEscalaManager;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
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
	private static final Logger logger = Logger.getLogger(BuscarModelo790Spring.class);
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/**
	 * Crea una nueva buscar modelo 790 spring.
	 */
	public BuscarModelo790Spring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
				setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
				setSolicitudComunAuxiliarManager ((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
				setTipoSolicitudManager((TipoSolicitudManager)getBean("tipoSolicitudManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setModeloManager((ModeloManager)getBean("modelosManager"));
				setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			} catch (Exception e) {
				logger.error("Error BuscarModelo790Spring - cargar datos ", e);
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
		getLogger().debug("BuscarModelo790Spring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.buscarModelo790");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		//******************************************************************
		String numReg=null;
		//SpringMessages messages;
	try{
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error BuscarModelo790Spring - recuperar usuarioSesion "+ sUsernameLogin, e);
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
		BuscarModelo790Form formulario;
		formulario = (BuscarModelo790Form) form;
				 
		try{
			numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
		}catch(Exception e){
			logger.error("Error BuscarModelo790Spring - numRegistros: "+ numReg, e);
		}
		
		if (numReg != null) {
			formulario.setNumRegistro(numReg);	
		}
		
		String busqueda = formulario.getSubmit();
		
		//cogemos el id del centro para verificar que pertenece al modelo 790001 y dejar exportar los documentos
		
		//Paginación
		String cambios = this.getRequest().getParameter("cambios");
		numReg = this.getRequest().getParameter("numRegistros");
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
		
		//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no
		//y guardar la descripción para el formulario de búsqueda
			
		String sVieneMenu = this.getRequest().getParameter("menu"); 		
		if (sVieneMenu != null && sVieneMenu.equals("S"))
		{
			//Poner todos los campos del formulario en blanco			
			formulario.setNif("");			
			formulario.setNumSolicitud("");
			formulario.setIdCentroGestor("");
			formulario.setDsCentroGestor("");
			formulario.setMinisterio("");
			formulario.setIdMinisterio(null);
			formulario.setModelo("");//añadido
			formulario.setIdModelo(null);//añadido
			formulario.setMarcarTodo("");
			
			formulario.setCuerpoEscala("");		
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");
			formulario.setSolExencion("");
			formulario.setCkFNacimiento("");
			formulario.setCkFFormacionOficial("");
			formulario.setAccion(STRING_BUSCAR);
		}else //Si viene a null es que viene de las demás páginas que no son del menú principal
		{
			sVieneMenu = "N";
		}
		//FIN Limpiado de formulario y muestra de resultados.

		//INICIO1 Paginación 

		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		
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

			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagTotales);
			}
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) /*|| "Buscar".equals(busqueda)*/){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
		}
		//FIN1 Paginación
			
		
		//Creamos la query para mostrar todas las solicitudes
		/*INI-TRA042*/
		SolComunAuxiliarViewQuery solicitudQuery = crearQuerySolicitudAuxiliar(formulario,paginaActual,numReg,listaCentrosGestores);
		/*FIN-TRA042*/
		//Coger la lista de solicitudes a mostrar
		//PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery ();
		List<SolicitudBean> lSolicitudBean = new ArrayList<SolicitudBean>();
		//List<SolicitudBean> lSolicitudBeanConExencion = new ArrayList<SolicitudBean>();
		//Quita los check marcados si se pincha en Buscar
		if(STRING_BUSCAR.equals(busqueda))
		{
			formulario.setSolicitudesSel(new String[1]); 
		}
		if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda) || "Verificar".equals(busqueda)
				|| "Exportar".equals(busqueda) || "Alta".equals(this.getRequest().getParameter("accion"))){
			sVieneMenu = "N";
			lSolicitudBean = solicitudComunAuxiliarManager.buscarSolicitudesAuxiliarVista(solicitudQuery);
			//Error sin resultados
			if (lSolicitudBean != null && lSolicitudBean.size() == 0) {
				SpringMessages errors = new SpringMessages();
				errors.add("errorSinResultados", new SpringMessage("field.solicitudPresencial.error"));
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
			}
			//Rellenar el campo de Solicita Exención
//			Iterator<SolicitudBean> it = lSolicitudBean.iterator();
//			while (it.hasNext())
//			{
//				SolicitudBean solicitudBean = (SolicitudBean) it.next();
//				solicitudQuery.setIdSolicitud(solicitudBean.getId());
//				//pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
//				SolicitudComunQuery solicitudQueryAux= new SolicitudComunQuery();
//				solicitudQueryAux.setIdSolicitud(solicitudBean.getId());
//				pagoSolicitudQuery.setSolicitudComun(solicitudQueryAux);
//				solicitudBean = pagoSolicitudManager.completaDatosPagoSolicitud(pagoSolicitudQuery, solicitudBean);
//				lSolicitudBeanConExencion.add(solicitudBean);
//			}
			//Paginación. cambios
			cambios = "Correcto";
		}
				
		//INICIO2 PAGINACIÓN
		int pagTotal = 0, numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			numReg=Constantes.NUMERO_REGISTROS_PAGINA;
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarModelo790Spring -  paginacion - numRegistros: "+ numRegistros, e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarModelo790Spring -  paginacion - numRegistros: "+ numRegistros, e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
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
		
		if(paginaActual==null||"".equals(paginaActual))
			paginaActual="1";
		
		setRequestAttribute("cambios", cambios);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);	
		formulario.setPaginasTotales(String.valueOf(numPaginas));
		//FIN2 PAGINACIÓN
		//Pasar la solicitud y la pagina al jsp
		setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
		setRequestAttribute("solicitudes", lSolicitudBean);
		setRequestAttribute("sVieneMenu", sVieneMenu); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.
		getLogger().debug("BuscarModelo790Spring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarModelo790Spring - doExecute: ",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
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
			switch(codCampo){
			//Los campos de ordenacion del jsp
				case 1:auxCampo = "numeroSolicitud";break;
				case 2:auxCampo = "nif";break;
				case 3:auxCampo = "nombre";break;
				case 4:auxCampo = "primerApellido";break;	
				case 5:auxCampo = "segundoApellido";break;
				case 6:auxCampo = "ejercicio";break;
				case 7:auxCampo = "centroGestor";break;
				default:break;
				}			
		}catch(Exception e){
			logger.error("Error BuscarModelo790Spring - cargarcampos - campo: "+ codCampo, e);
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
	 * Crear query solicitud auxiliar.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param listaCentrosGestores 
	 * @return el sol comun auxiliar view query
	 */
	/*INI-TRA042*/
	private SolComunAuxiliarViewQuery crearQuerySolicitudAuxiliar (BuscarModelo790Form formulario,String paginaActual,String numReg, List<CentroGestorBean> listaCentrosGestores)
	{
		SolComunAuxiliarViewQuery solicitudQuery = new SolComunAuxiliarViewQuery();
		
		//-----Redirección a busqueda tras alta solicitud---------
		String accionAlta = this.getRequest().getParameter("accion"); 
		if(accionAlta!=null && ( accionAlta.equals("Alta") || accionAlta.equals("Modificar")))
		formulario.setFechaDesde(new SimpleDateFormat(STRING_SIMPLEDATEFORMAT).format(new Date()));
		//--------------------------------------------------------
		String sNifFormulario = formulario.getNif();
		String sNumSolicitud = formulario.getNumSolicitud();
		Integer iIdMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		String sIdCuerpoEscala = formulario.getCuerpoEscala(); 
		String campo = cargarCampos(formulario.getCampo()); 
		String direccion = formulario.getDireccion();
		String solExencion = formulario.getSolExencion();
		Integer idModelo = formulario.getIdModelo();
		
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
				logger.error("Error BuscarModelo790Spring -  paginacion Query - numRegistros: "+ numRegistros, e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarModelo790Spring -  paginacion Query - numRegistros: "+ numRegistros, e);
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
		if(formulario.getIdModelo()!=null && formulario.getIdModelo()!=0){
			setRequestAttribute("idModelo", formulario.getIdModelo());
		}
		//FIN PAGINACIÓNQuery	
		
		//Nif
		if (sNifFormulario != null && !sNifFormulario.equals("") ){
			solicitudQuery.setNif(sNifFormulario);
		}
		//Número de solicitud
		if (sNumSolicitud != null && !sNumSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(sNumSolicitud);
		}
		//Ministerio
		if (iIdMinisterio != null && iIdMinisterio.intValue() != 0){
			solicitudQuery.setIdMinisterio(iIdMinisterio.shortValue());
		}

		//Centro Gestor
		if (sIdCentroGestor != null && !sIdCentroGestor.equals("")){
			Integer idCentroGestor = Integer.valueOf(sIdCentroGestor);
			solicitudQuery.setIdCentroGestor(idCentroGestor);
		} else if(listaCentrosGestores.size() > 0){
			for(CentroGestorBean cg: listaCentrosGestores){
				solicitudQuery.addIdCentroGestorIn(cg.getId());
			}
		}
		
		//Cuerpo y Escala
		if (sIdCuerpoEscala != null && !sIdCuerpoEscala.equals(""))
		{
			Integer idCuerpoEscala = Integer.valueOf(formulario.getCuerpoEscala());
			solicitudQuery.setIdCuerpoEscala(idCuerpoEscala);
		}
		
		//Fecha Desde
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaSolicitudMin(dFechaDesde);
				
				
			} catch (java.text.ParseException e) {
				logger.error("Error BuscarModelo790Spring - parsear fechaDesde: "+ sFechaDesde, e);
			}
		
		}
		//Fecha Hasta
		if (sFechaHasta != null && !sFechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaSolicitudMax(dFechaHasta);
				
			} catch (java.text.ParseException e) {
				logger.error("Error BuscarModelo790Spring - parsear fechaHasta: "+ sFechaHasta, e);
			}
		}
		
		//Solicita Exencion
		if (solExencion != null && !solExencion.equals("")) {
			if(solExencion.charAt(0) == 'S') {
				solicitudQuery.setMotivoReduccionIsNotNull(true);				
			}else {
				solicitudQuery.setMotivoReduccionIsNull(true);
			}
		}
		
		// Modelo solicitud
		if(idModelo != null && idModelo.intValue() != 0){
			solicitudQuery.setIdModelo(idModelo.shortValue());
		} 
		

		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if (CuerpoEscalaQuery.CENTROGESTOR.equals(campo)) {
				solicitudQuery.addOrder("siglasCentroGestor",orden);
			}
			else {
				solicitudQuery.addOrder(campo, orden);
			}
		}
		
		solicitudQuery.addOrder(SolComunAuxiliarViewQuery.IDSOLICITUD, OrderType.DESC);
		return solicitudQuery;
	}
	
	/**
	 * Carga los Combos: Centro Gestor, Ministerio, Cuerpo_Escala.
	 * @param listaCentrosGestores 
	 */
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
	 * Obtiene el solicitud comun auxiliar manager.
	 *
	 * @return solicitudesRegistradasManager SolicitudesRegistradasManager
	 */ 
	public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
		return solicitudComunAuxiliarManager;
	}

	/**
	 * Establece el solicitud comun auxiliar manager.
	 *
	 * @param solicitudComunAuxiliarManager el nuevo solicitud comun auxiliar manager
	 */
	public void setSolicitudComunAuxiliarManager(
			SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
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

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
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
