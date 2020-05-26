package es.map.ipsg.action.solicitud;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import ePago.gob.es.schemas.DefaultDatosNRCIn;
import ePago.gob.es.schemas.DefaultDatosNRCOut;
import ePago.gob.es.schemas.PPServiceInterfaceServiceLocator;
import ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub;
import ePago.gob.es.schemas.TiposDocumento;
import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Util;
import es.map.pasarelapago.service.ServicioIntegracion;

/**
 * El Class VerificarPagoRecSpring.
 */
public class VerificarPagoRecSpring extends AbstractSpring {


	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	//WebService
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarPagoRecSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/**
	 * Crea una nueva verificar pago rec spring.
	 */
	public VerificarPagoRecSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));				
				setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
				properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error VerificarPagoRecSpring :" ,e);
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
		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		//******************************************************************

	try{	
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals(STRING_ERROR)) {
			return sUsernameLogin;
		}
		
		//Obtenemos el Usuario que esta logueado en la aplicacion para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que esta logueado

		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		Integer idUsuario = usuario.getId();

		String solicitud = this.getRequest().getParameter("solicitud");
		long idSolicitud = comprobarLong(solicitud);
		
		if (idSolicitud == 0) {
			return STRING_ERROR;
		}
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(idSolicitud);	
		SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
		
		

		setRequestAttribute("idSolicitud" ,solicitud);
		setRequestAttribute("solicitud", solicitudBean);
		try {		
			
			// Completamos la solicitud con los datos de pago
			PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
			pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
			pagoSolicitudQuery.setResultado(Constantes.RESULTADO_OK);	
			PagoSolicitudBean pagoSolicitudBean= pagoSolicitudManager.buscarUltimoPagoSolicitudByIdSolicitud(pagoSolicitudQuery);
								
			//Llamada al WebService
			PPServiceInterfaceServiceLocator locator = new PPServiceInterfaceServiceLocator();
			locator.setPPServiceInterfaceSoap11EndpointAddress(properties.getProperty("conf.consultaPago.WS.direccion"));
			
			//Objeto de pago
			DefaultDatosNRCIn datosNRCIn = new DefaultDatosNRCIn();
			DefaultDatosNRCOut datosNRCOut; 
			//Rellenamos los atributes necesarios para realizar la consulta
			datosNRCIn.setNRC(pagoSolicitudBean.getNrc());
			datosNRCIn.setNombre(solicitudBean.getNombre());
			datosNRCIn.setApellido1(solicitudBean.getPrimerApellido());
			datosNRCIn.setCodigoBanco(pagoSolicitudBean.getCodEntidadFinanciera());
			datosNRCIn.setDocumentoObligado(solicitudBean.getNif());
			if(solicitudBean.getNif() != null){
				datosNRCIn.setTipoDocumentoObligado(TiposDocumento.fromString(evaluarTipoDocumento(solicitudBean.getNif())));
			}
			
			// Sustituimos la coma del float por punto para tranformarlo a double.
			String importe = pagoSolicitudBean.getImporte().toString();
					importe = importe.replaceAll(",","\\."); 
			datosNRCIn.setImporteOperacion(Double.parseDouble(importe));
			//Fecha Operacion, tipo Calendar		 
		    try {
				Calendar datePago = Calendar.getInstance();		
				SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
				String diaPago= myformat.format(pagoSolicitudBean.getFechaIntento());
				Date diaPagoBBDD = myformat.parse(diaPago);
		    	datePago.setTime(diaPagoBBDD);
		    	datosNRCIn.setFechaOperacion(datePago);
		    } catch (ParseException e) {
		        // TODO Auto-generated catch block
		    	logger.error("Error VerificarPagoRecSpring - formatear fecha dia de pago :"+ pagoSolicitudBean.getFechaIntento() ,e);
		    }
	
		    int idOrganismo = obtenerPropertyIdOrg();
						
			PPServiceInterfaceSoap11Stub binding = (PPServiceInterfaceSoap11Stub)locator.getPPServiceInterfaceSoap11();
			
			datosNRCOut = binding.verificarNRC(datosNRCIn, idOrganismo);

					
			setRequestAttribute("verificarNRC", datosNRCOut);
		

		}catch (RemoteException e) {
			logger.error(" Error VerificarPagoRecSpring - Error en llamada a WebService Consulta Pago AEAT (excepcion normal)",e);
			this.setRequestAttribute("errors",RESOURCE_BUNDLE.getString("field.consultarPagoAeat.errores.falloWS"));			
			return "success";
		}
		
	}catch(Exception eGenerico){
		logger.error("Error VerificarPagoRecSpring - doExecute ",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));		
		return "errorGenerico";
	}	
		
		return "success";
	}
	
	/**
	 * Obtener property id org.
	 *
	 * @return el int
	 */
	private int obtenerPropertyIdOrg() {
		int idOrganismo = 0;
		
		try {
			idOrganismo = Integer.parseInt(properties.getProperty("conf.idOrganismo"));
		} catch (NumberFormatException e) {
			logger.error("  Error VerificarPagoRecSpring - La propiedad idOrganismo no se encuentra en el fichero " + RESOURCE_BUNDLE + " o no tiene un formato valido.",e);
			logger.error(" Error VerificarPagoRecSpring - Se va a indicar como idOrganismo el valor 0 por defecto.", e);
		}
		return idOrganismo;
	}
	
	/**
	 * Comprobar long.
	 *
	 * @param numero el numero
	 * @return el long
	 */
	private long comprobarLong(String numero) {
		long resultado = 0;
		
		try{
			resultado = Long.parseLong(numero);
		}catch(Exception e){
			logger.error("Error VerificarPagoRecSpring - parsear idSolicitud :"+ resultado ,e);
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
			logger.error("Error VerificarPagoRecSpring - recuperar UsuarioSesion :"+ sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return STRING_ERROR;
		}
	}
	
	/**
	 *  Metodo que evalua el tipo de documento pasado por parametro.
	 *
	 * @param documento el documento
	 * @return String NIF o NIE
	 */
	private String evaluarTipoDocumento(String documento){
		
		String tipoDoc = Constantes.TIPO_DOCUMENTO_NIF_INTEGER;
		
		if(Util.esNie(documento)){
			tipoDoc = Constantes.TIPO_DOCUMENTO_NIE_INTEGER;
		}
		
		return tipoDoc;
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
	 * Obtiene el log solicitud manager.
	 *
	 * @return el log solicitud manager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager el nuevo log solicitud manager
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return el pago solicitud manager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager el nuevo pago solicitud manager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el entidad financiera manager.
	 *
	 * @return el entidad financiera manager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager el nuevo entidad financiera manager
	 */
	public void setEntidadFinancieraManager(
			EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
	}	
}
