package es.map.ipsc.spring.solicitudes;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.DetalleRegistroSolicitudBean;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.RegistroSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class DetalleRegistroSolicitudesSpring.
 */
public class DetalleRegistroSolicitudesSpring extends AbstractSecureSpring {

	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DetalleRegistroSolicitudesSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	/** La constante STRING_ERROR_DE_USUARIO. */
	private static final String STRING_ERROR_DE_USUARIO = "Error de usuario";
	
	/** La constante STRING_ERRORREGISTRO. */
	private static final String STRING_ERRORREGISTRO = "errorRegistro";
	
	/** La constante STRING_REGISTROSOLICITUD. */
	private static final String STRING_REGISTROSOLICITUD = "registrosolicitud";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** La constante STRING_FIELD_SOLICITUD_REGISTRO_SOLICITUD_ERROR. */
	private static final String STRING_FIELD_SOLICITUD_REGISTRO_SOLICITUD_ERROR = "field.solicitud.registroSolicitudError";
	
	

	
	
	/** el properties. */
	private static Properties properties;
	
	/**
	 * Crea una nueva detalle registro solicitudes spring.
	 */
	public DetalleRegistroSolicitudesSpring() {
		try {
			setSolicitudesManager((SolicitudManager) getBean ("solicitudesManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean ("registroSolicitudesManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean ("pagoSolicitudesManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error detalle registro solicitudes",e);
		}

		
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String codSolicitud = (String) this.getRequest().getParameter("registro");
		
		if(codSolicitud == null){
			codSolicitud = (String) this.getRequest().getAttribute("registro");
		}
		logger.info(codSolicitud);
		
		//Variable para pruebas de estress
		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");
		
		//Comprueba que el usuario este en sesion
		CiudadanoBean usuActual = null;
		if(convocatoriaRepetida_Unico.equals("U")){
			usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
		}else{
			usuActual = new CiudadanoBean();
			usuActual.setNif("12345678Z");
		}
		
		if(usuActual == null){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
			logger.error(STRING_ERROR_DE_USUARIO);
			return STRING_ERRORUSUARIO;
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
				logger.error(STRING_ERROR_DE_USUARIO);
				return STRING_ERRORUSUARIO;
			}
		}
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(Long.parseLong(codSolicitud));
		
		//Busca los datos de la solicitud
		DetalleSolicitudBean solicitudes= solicitudesManager.detalleSolicitud(solicitudComunQuery);
		if(solicitudes != null){
			
			if(solicitudes.getNif().equals(usuActual.getNif()) || usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)){
				int idEstadoSolicitud = solicitudes.getIdEstadoSolicitud().intValue();
				
				//Comprueba que el estado de la solicitud no sea, No pagado ni no Registrado
				if(Constantes.SOLICITUD_ID_NOPAGADO == idEstadoSolicitud){
					//Si el estado es no pagado se direcciona a pagar
					this.setRequestAttribute("id", String.valueOf(codSolicitud));
					return "pagarSolicitud";
				}else if(Constantes.SOLICITUD_ID_NOREGISTRADO == idEstadoSolicitud){
					//Si el estado es no registrado se redicciona a registrar
					this.setRequestAttribute("id", String.valueOf(codSolicitud));
					return "registrarSolicitud";
				}else if(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO == idEstadoSolicitud){
					//Si el estado es pendiente registro se muestra el mensaje
					setRequestAttribute(STRING_ERRORREGISTRO , RESOURCE_BUNDLE.getString("field.solicitud.mensajeRegistroPendiente"));
					DetalleRegistroSolicitudBean registroSolicitud = new DetalleRegistroSolicitudBean();
					registroSolicitud.setIdSolicitud(codSolicitud);
					setRequestAttribute(STRING_REGISTROSOLICITUD, registroSolicitud);
					return STRING_SUCCESS;
				}else if(Constantes.SOLICITUD_ID_PROCESO_REGISTRO == idEstadoSolicitud){
					//Si el estado es pendiente registro se muestra el mensaje
					setRequestAttribute(STRING_ERRORREGISTRO , RESOURCE_BUNDLE.getString("field.solicitud.mensajeProcesoRegistro"));
					DetalleRegistroSolicitudBean registroSolicitud = new DetalleRegistroSolicitudBean();
					registroSolicitud.setIdSolicitud(codSolicitud);
					setRequestAttribute(STRING_REGISTROSOLICITUD, registroSolicitud);
					return STRING_SUCCESS;
				}else{
					RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
					registroSolicitudQuery.setResultado(Constantes.REGISTRO_RESULTADO_OK);
					registroSolicitudQuery.setSolicitudComun(solicitudComunQuery);
					//Busca los datos del registro
					DetalleRegistroSolicitudBean registroSolicitud = null;
					try{
						registroSolicitud = registroSolicitudManager.buscarRegistroSolicitudIdSolicitud(registroSolicitudQuery);
					}catch(Exception e){		
						logger.error("Error registro: ", e);
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_SOLICITUD_ERROR));
						return "nosuccess";
					}
					if(registroSolicitud == null){
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_SOLICITUD_ERROR));
						DetalleRegistroSolicitudBean registroAux = new DetalleRegistroSolicitudBean();
						registroAux.setIdSolicitud(codSolicitud);
						setRequestAttribute(STRING_REGISTROSOLICITUD, registroAux);
						return STRING_SUCCESS;
					}
					if(registroSolicitud.getNumeroRegistro() == null || "".equals(registroSolicitud.getNumeroRegistro())){
						setRequestAttribute(STRING_ERRORREGISTRO , RESOURCE_BUNDLE.getString("field.solicitud.mensajeRegistroPendiente"));
						
						setRequestAttribute(STRING_REGISTROSOLICITUD, registroSolicitud);
						return STRING_SUCCESS;
					}
					PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
					pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);
					pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
					
					//Busca los datos del pago de la solicitud
					DetallePagoSolicitudBean pagoSolicitud=null;
					if(convocatoriaRepetida_Unico.equals("U")){
						pagoSolicitud= pagoSolicitudManager.buscarUltimoPagoSolicitudIdSolicitud(pagoSolicitudQuery);
					}else{
						pagoSolicitud= pagoSolicitudManager.buscarPagoSolicitudIdSolicituPruebas(pagoSolicitudQuery);
					}
					
					if(pagoSolicitud == null){
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_SOLICITUD_ERROR));
						return "nosuccess";
					}
					registroSolicitud.setNif(solicitudes.getNif());
					String nombreCompleto = solicitudes.getNombre() + " " +solicitudes.getPrimerApellido() + " " + solicitudes.getSegundoApellido();
					registroSolicitud.setNombre(nombreCompleto);
					registroSolicitud.setNumJustificante(solicitudes.getNumJustificante());
					registroSolicitud.setEjercicio(solicitudes.getEjercicioSolicitud());
					registroSolicitud.setMinisterio(solicitudes.getMinisterio());
					registroSolicitud.setFormaPago(pagoSolicitud.getTipo());
					registroSolicitud.setImporte(pagoSolicitud.getImporte());
					registroSolicitud.setNrc(pagoSolicitud.getNrc());
					registroSolicitud.setFechaPago(pagoSolicitud.getFechaIntento());
					registroSolicitud.setConsentimiento(solicitudes.getConsentimiento());
					
					//Comprobar si tiene un justificante de pago
					String tipoPago = pagoSolicitud.getTipo();
				
					if(tipoPago != null && !tipoPago.equals(Constantes.PAGO_TIPO_EXENTO)){
						this.setRequestAttribute("certPago", true);
					}
						
					
					setRequestAttribute(STRING_REGISTROSOLICITUD, registroSolicitud);
					setSessionAttribute("ultimoCiudadanoInscrito", registroSolicitud.getNif());
				}
			}else{
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.solicitud.pagoSolicitud.usuarioError"));
				logger.error(STRING_ERROR_DE_USUARIO);
				return STRING_ERRORUSUARIO;
			}
		}else{
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.solicitud.errorCodSolicitud"));
			return STRING_ERRORUSUARIO;
		}
		
		return STRING_SUCCESS;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param pSolicitudManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager pSolicitudManager) {
		this.solicitudesManager = pSolicitudManager;
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

	
}
