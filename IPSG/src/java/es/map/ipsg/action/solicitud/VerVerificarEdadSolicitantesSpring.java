package es.map.ipsg.action.solicitud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoSolicitudQuery;
import es.map.ipsg.action.convocatoria.BuscarConvocatoriasSpring;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerVerificarEdadSolicitantesSpring.
 *
 * @author amartinl
 */
public class VerVerificarEdadSolicitantesSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante STRING_SOLSEL. */
	private static final String STRING_SOLSEL= "solSel";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarConvocatoriasSpring.class);

	/** el convocatoria manager. */
	//Declaracion de Manager
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/**
	 * Crea una nueva ver verificar edad solicitantes spring.
	 */
	public VerVerificarEdadSolicitantesSpring() {
		try {
				setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			} catch (Exception e) {
				logger.error(" Error VerVerificarEdadSolicitantesSpring ", e);
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
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(MESSAGE_RESOUCE);	
		String menu_solicitudes = RESOURCE_BUNDLE_2.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		//******************************************************************
	try{	
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals(STRING_ERROR)) {
			return sUsernameLogin;
		}

		//Cogemos el form del jsp
		BuscarSolicitudesForm formulario;
		formulario = (BuscarSolicitudesForm) form;
		
		
		
		//INICIO1 Paginación 
		//Paginación
		String cambios;
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		
		if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
			int pagActu = Integer.parseInt(paginaActual);		
			int pagTotales = Integer.parseInt(paginaTotal);

			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagActu-1);
			}
		}
		
		//FIN1 Paginación
		
		
		String[] aSolicitudesSel = null;	
		//Comprobamos si viene desde una validación
		String error = this.getRequest().getParameter(STRING_ERROR);
		if(error != null && error.equalsIgnoreCase("S")){
			aSolicitudesSel = (String[]) getSessionAttribute(STRING_SOLSEL);
		}else{
			String checkBox = formulario.getMarcarTodo();
			if ("TodoOk".equals(checkBox)) {
				ArrayList<SolicitudBean> aSolicitudes;
				SolComunRegistradasViewQuery solicitudQuery;
				//Creamos la query para mostrar todas las solicitudes
				solicitudQuery = crearQuerySolicitudRegistrada(formulario);
				//Recuperamos todos los datos de las solicitudes seleccionadas
				aSolicitudes = solicitudesRegistradasManager.buscarSolicitudesRegistradasVista(solicitudQuery);
				if (aSolicitudes != null && !aSolicitudes.isEmpty()) {
					aSolicitudesSel = new String[aSolicitudes.size()];
					for (int i=0; i<aSolicitudes.size(); i++) {
						aSolicitudesSel[i] = aSolicitudes.get(i).getId().toString();
					}
					setSessionAttribute(STRING_SOLSEL, null);
					setSessionAttribute(STRING_SOLSEL, aSolicitudesSel);
				}
			}else {
				//Recuperamos las solicitudes que ha marcado
				setSessionAttribute(STRING_SOLSEL, null);
				aSolicitudesSel = this.getRequest().getParameterValues("sol");
				setSessionAttribute(STRING_SOLSEL, aSolicitudesSel);				
			}

		}
		ArrayList aSolicitudes;
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		for(int i=0; aSolicitudesSel!=null && i < aSolicitudesSel.length; i++)
		{
			Long idSolicitudSel = Long.parseLong(aSolicitudesSel[i]);
			solicitudQuery.addIdSolicitudIn(idSolicitudSel);
		}
		//Recuperamos todos los datos de las solicitudes seleccionadas
		aSolicitudes = solicitudesRegistradasManager.buscarSolicitudAll(solicitudQuery);
		
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		ArrayList<ConvocatoriasBean> aConvocatorias;

		Iterator it = aSolicitudes.iterator();
		ArrayList<SolicitudBean> solicitudes = new ArrayList<SolicitudBean>();
		//Con el ID de cada convocatoria, recogemos el convocatoriaBean realizando la busqueda por IDConvocatoria.
		while (it.hasNext())
		{
			SolicitudBean solicitudBean = (SolicitudBean) it.next();
			solicitudes.add(solicitudBean);
			convocatoriaQuery.addIdIn(solicitudBean.getIdConvocatoria());
		}
		
		aConvocatorias = convocatoriaManager.buscarConvocatoriasAll(convocatoriaQuery);
		//Paginación. cambios
		cambios = "Correcto";
		
		//INICIO2 PAGINACIÓN
		int pagTotal = 0, numPaginas=0;
		String numReg=null;
		if(formulario.getNumRegistro() == null && numReg == null){
			numReg=Constantes.NUMERO_REGISTROS_PAGINA;
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro("5");
		}
		int numRegistros = 0;
		if(numReg != null){ 
			numRegistros = Integer.parseInt(numReg);
		}else{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
		}
		//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
		if(aConvocatorias != null && aConvocatorias.size()!= 0){
			pagTotal= aConvocatorias.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(aConvocatorias.size()>numRegistros){
				aConvocatorias.remove(aConvocatorias.size()-1);
			}
		}	
		
		setRequestAttribute("cambios", cambios);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);	
		//FIN2 PAGINACIÓN
		
		//Inicialización de los campos
		formulario.setFechaMax("");
		formulario.setFechaMin("");
		
		setRequestAttribute("solicitudes", solicitudes);
		setRequestAttribute("convocatorias", aConvocatorias);
		
	}catch(Exception eGenerico){
		logger.error(" Error VerVerificarEdadSolicitantesSpring - doExecute", eGenerico);
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
		logger.error(" Error VerVerificarEdadSolicitantesSpring - recuperar UsuarioSesion"+ sUsernameLogin, e);
		new BusinessException("exception.recuperarUsuarioSesion");
		return STRING_ERROR;
	}
}

/**
 * Crear query solicitud registrada.
 *
 * @param formulario el formulario
 * @return el sol comun registradas view query
 */
private SolComunRegistradasViewQuery crearQuerySolicitudRegistrada (BuscarSolicitudesForm formulario)	{
		
	SolComunRegistradasViewQuery solicitudQuery = new SolComunRegistradasViewQuery();
		
		String sNifFormulario = formulario.getNif();
		String sNumSolicitud = formulario.getNumSolicitud();
		Integer iIdMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		String sIdCuerpoEscala = formulario.getCuerpoEscala();
		Integer iIdTipo = formulario.getIdTipo();
		
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();

		
		//Comprobar los filtros para realizar la busqueda
		
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
		if (sIdCentroGestor != null && !sIdCentroGestor.equals(""))
		{
			solicitudQuery.setIdCentroGestor(Integer.valueOf(sIdCentroGestor));
		}
		//Cuerpo y Escala
		if (sIdCuerpoEscala != null && !sIdCuerpoEscala.equals(""))
		{
			solicitudQuery.setIdCuerpoEscala(Integer.valueOf(sIdCuerpoEscala));
		}
		//Tipo Solicitud HASTA AQUI!!
		if(iIdTipo != null && iIdTipo.intValue() != 0)
		{
			solicitudQuery.setIdTipoSolicitud(iIdTipo);
			
		}
		//Fecha Desde
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaRegistroMin(dFechaDesde);
				
			} catch (java.text.ParseException e) {
				logger.error(" Error VerVerificarEdadSolicitantesSpring - crearQuerySolicitudRegistrada - parsear fechaDesde"+ formulario.getFechaDesde(), e);
			}
		
		}
		//Fecha Hasta
		if (sFechaHasta != null && !sFechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaRegistroMax(dFechaHasta);
				
			} catch (java.text.ParseException e) {
				logger.error(" Error VerVerificarEdadSolicitantesSpring - crearQuerySolicitudRegistrada - parsear fechaHasta"+ formulario.getFechaHasta(), e);
			}
		}
		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if (SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA.equals(campo)) {
				solicitudQuery.addOrder(SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA, orden);
			}
			else if (SolComunRegistradasViewQuery.DESCENTROGESTOR.equals(campo)) {
				solicitudQuery.addOrder(SolComunRegistradasViewQuery.DESCENTROGESTOR, orden);
			}
			else {
				solicitudQuery.addOrder(campo, orden);
			}

		}
		return solicitudQuery;
	}

	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return convocatoriaManager ConvocatoriasManager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager ConvocatoriasManager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
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

	
}
