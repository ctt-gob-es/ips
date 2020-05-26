package es.map.ipsg.action.solicitud;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.IntermediacionVictimasTerrorismoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DatosVictimasTerrorismoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;

public class ConsultarVictimasTerrorismoSpring extends AbstractSpring {

	private Properties properties;
	private static final String MESSAGE_RESOURCE = "MessageResources";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);

	private static final Logger logger = Logger.getLogger(ConsultarVictimasTerrorismoSpring.class);
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";
	private static final String STRING_ERROR = "error";
	
	private SolicitudesRegistradasManager solicitudManager;
	private ConvocatoriasManager convocatoriaManager;
	private DatosVictimasTerrorismoManager datosVictimasTerrorismoManager;
	private UsuarioManager usuarioManager;
	
	public ConsultarVictimasTerrorismoSpring() {
		try{
			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setDatosVictimasTerrorismoManager((DatosVictimasTerrorismoManager) getBean("datosVictimasTerrorismoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error consultarVictimasTerrorismoSpring: ",e);
		}
	}

	/**
	 * doExecute  de VerificarDiscapacidadAction
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		String resultado = null;
		String mensaje = null;
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		try{
			logger.debug("VerificarVictimasTerrorismoSpring -start");
			
			//Tomamos el usuario que se ha logueado
			String sUsernameLogin = recuperarUsuario();
			
			if (sUsernameLogin.equals(STRING_ERROR)) {
				return sUsernameLogin;
			}

			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado
			
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			
			String idSolicitud = (String)this.getRequest().getParameter("idSolicitud");		
			
			
			if(idSolicitud!=null){
				SolicitudBean solicitudBean;
				try{
					solicitudBean = solicitudManager.obtenerSolicitud(Long.valueOf(idSolicitud));
				}catch(Exception e){
					resultado = STRING_ERROR;
					mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
					logger.error("Numero de solicitud erroneo: "+idSolicitud,e);
					return resultado;
				}
				
				// Se valida si tiene nif
				if(solicitudBean != null){
					if(solicitudBean.getNif() == null || solicitudBean.getNif().isEmpty()){
						logger.error("Error  VerificarFechaNacimientoSpring - error al obtener la fecha de nacimiento");
						resultado = STRING_ERROR;
						mensaje = RESOURCE_BUNDLE.getString("svdi.error.nif");
						this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
						return resultado;
					}
				}
				
				IntermediacionVictimasTerrorismoBean victimasTerrorismoBean = null;
				boolean errorWs = false;
				
				try {
					String nifFuncionario = usuarioBean.getNif();
					String nombreFuncionario = usuarioBean.getNombreCompleto();
					String unidadTramitadora = properties.getProperty("VICTIMAS_UNIDAD_TRAMITADORA");
					victimasTerrorismoBean = datosVictimasTerrorismoManager.consultaVictimaTerrorismo(solicitudBean, nifFuncionario, nombreFuncionario,unidadTramitadora);
				} catch (Exception e) {
					logger.error("Error ConsultarVictimasTerrorismoSpring:",e);
					errorWs = true;		
				}
				
				if (errorWs) {
					//Si falla por el WS se pone en estado E (Ptd.Error al Comprobar)
					//datosVictimasTerrorismoManager.guardarEstadoFalloWsSolicitud(solicitudBean);
					throw new BusinessException("intermediacion.svdi.errorConexion");
				} else {
					//Si no falla por el WS se pone en estado N (Ptd)
					if (solicitudBean.getId() != null) {
						//datosVictimasTerrorismoManager.guardarEstadoPendiente(solicitudBean.getId());
					}	
				}
				
				this.getRequest().setAttribute("victimaTerrorismo", victimasTerrorismoBean);
				resultado = "success";
				
			}else{
				resultado = STRING_ERROR;
				mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
				logger.error("Numero de solicitud obtenido es null");
			}
			
			logger.debug("VerificarVictimasTerrorismoSpring -end");
			return resultado;
		}catch (BusinessException ex){
			logger.warn(ex);
			logger.error("Error ConsultarVictimasTerrorismoSpring:"  ,ex);
			SpringErrors errors = new SpringErrors();
			errors.add("", new SpringMessage(ex.getMessage(),false));			
			resultado = STRING_ERROR;
			mensaje = ex.getMessage();			
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}catch (Exception ex){
			logger.warn(ex);
			logger.error("Error ConsultarDiscapacidadSpring:"  ,ex);
			resultado = STRING_ERROR;
			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}
		
		return resultado;
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			String username = usuarioBean.getLogin();
			return username;
		}catch(Exception e){
			logger.error("Error ContactarVictimasTerrorismoSpring - recuperarUsuarioSesion" ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	public SolicitudesRegistradasManager getSolicitudManager() {
		return solicitudManager;
	}

	public void setSolicitudManager(SolicitudesRegistradasManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}

	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriaManager;
	}

	public void setConvocatoriasManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

	public DatosVictimasTerrorismoManager getDatosVictimasTerrorismoManager() {
		return datosVictimasTerrorismoManager;
	}

	public void setDatosVictimasTerrorismoManager(DatosVictimasTerrorismoManager datosVictimasTerrorismoManager) {
		this.datosVictimasTerrorismoManager = datosVictimasTerrorismoManager;
	}

}
