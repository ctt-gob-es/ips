	package es.map.ipsg.action.solicitud;
	
	import java.rmi.RemoteException;
	import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionDesempleoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DatosDesempleoManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;



/**
	 * El Class ConsultarDesempleoSpring.
	 */
	public class ConsultarDesempleoSpring extends AbstractSpring {
		
		/** La constante MESSAGE_RESOURCE. */
		private static final String MESSAGE_RESOURCE = "MessageResources";
		
		/** La constante RESOURCE_BUNDLE. */
		private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
		/** La constante logger. */
		private static final Logger logger = Logger.getLogger(ConsultarDesempleoSpring.class);
		
		/** La constante STRING_ERROR. */
		private static final String STRING_ERROR = "error";
		
		/** La constante STRING_DESCRIPCIONERROR. */
		private static final String STRING_DESCRIPCIONERROR = "descripcionError";
		
		/** el solicitud manager. */
		private SolicitudesRegistradasManager solicitudManager;
		
		/** el convocatoria manager. */
		private ConvocatoriasManager convocatoriaManager;
		
		/** el datos desempleo manager. */
		private DatosDesempleoManager datosDesempleoManager;
		
		/** el solicitud comun auxiliar manager. */
		private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
		
		/** el usuario manager. */
		private UsuarioManager usuarioManager;
		
		/**
		 * Crea una nueva consultar desempleo spring.
		 */
		public ConsultarDesempleoSpring() {
			try{
				setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
				setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setDatosDesempleoManager((DatosDesempleoManager) getBean("datosDesempleoManager"));
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			}catch(Exception e){
				logger.warn(e);
				logger.error("Error ConsultarDesempleoSpring  ",e);
			}
		}
	
		/**
		 * doExecute  de VerificarFechaNacimientoAction.
		 *
		 * @param form SpringForm
		 * @return resultado   String
		 * @throws Exception Exception
		 */
		protected String doExecute(SpringForm form) throws Exception {
			String resultado = null;
			String result = null;
			String mensaje = null;
			
			try{
				logger.debug("VerificarDesempleoSpring -start");
				
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
				String auxiliar = "";
				
				if (this.getRequest().getParameter("auxiliar") != null) {
					auxiliar = (String)this.getRequest().getParameter("auxiliar");
				}
				
				
				if(!StringUtils.isEmpty(idSolicitud)){
					SolicitudBean solicitudBean;
					ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
					ConvocatoriasBean convocatoriaBean = new ConvocatoriasBean();
					try{
						if(auxiliar.equalsIgnoreCase("true") && !StringUtils.isEmpty(auxiliar)) {
							solicitudBean = solicitudComunAuxiliarManager.obtenerSolicitud(Long.valueOf(idSolicitud));
						}else {
							solicitudBean = solicitudManager.obtenerSolicitud(Long.valueOf(idSolicitud));
						}
						convocatoriaQuery.setId(solicitudBean.getIdConvocatoria());
						convocatoriaBean =convocatoriaManager.buscarConvocatoriaById(convocatoriaQuery);
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
					
					IntermediacionDesempleoBean desempleoBean = null;
					boolean errorWs = false;
					
					try {
						/*INI-TRA042*/
						desempleoBean = datosDesempleoManager.validarDesempleoSalario(convocatoriaBean,solicitudBean,usuarioBean, false, solicitudBean.getCentroGestor());
						/*FIN-TRA042*/
					} catch (Exception e) {
						errorWs = true;		
					}
					
					if (errorWs) {
						//Si falla por el WS se pone en estado E (Ptd.Error al Comprobar)
						if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
							datosDesempleoManager.guardarEstadoFalloWsSolicitudAuxiliar(solicitudBean);
						}else {
							datosDesempleoManager.guardarEstadoFalloWsSolicitud(solicitudBean);
						}
						throw new BusinessException("intermediacion.svdi.errorConexion");
					} else {
						if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
							//Si no falla por el WS se pone en estado N (Ptd)
							if (solicitudBean.getId() != null) {
								datosDesempleoManager.guardarEstadoPendienteAuxiliar(solicitudBean.getId());
							}
						}else {
							//Si no falla por el WS se pone en estado N (Ptd)
							if (solicitudBean.getId() != null) {
								datosDesempleoManager.guardarEstadoPendiente(solicitudBean.getId());
							}
						}
					}
					
					this.getRequest().setAttribute("desempleo", desempleoBean);
					resultado = "success";
					
				}else{
					resultado = STRING_ERROR;
					mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
					logger.error("Numero de solicitud obtenido es null");
				}
				
				logger.debug("VerificarDesempleoSpring -end");
				return resultado;
			}catch (BusinessException ex){
				logger.warn(ex);
				logger.error("Error ConsultarDesempleoSpring ",ex);
				SpringErrors errors = new SpringErrors();
				errors.add("", new SpringMessage(ex.getMessage(),false));			
				resultado = STRING_ERROR;
				mensaje = ex.getMessage();			
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
			}catch (Exception ex){
				logger.warn(ex);
				logger.error("Error ConsultarDesempleoSpring ",ex);
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
			String sUsernameLogin = "";
			try{
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				sUsernameLogin = usuarioBean.getLogin();
				return sUsernameLogin;
			}catch(Exception e){
				logger.error("Error ConsultarDesempleoSpring - recuperarUsuarioSesion "+ sUsernameLogin,e);
				new BusinessException("exception.recuperarUsuarioSesion");
				return STRING_ERROR;
			}
		}
		
		/**
		 * Obtiene el solicitud manager.
		 *
		 * @return el solicitud manager
		 */
		public SolicitudesRegistradasManager getSolicitudManager() {
			return solicitudManager;
		}
	
		/**
		 * Establece el solicitud manager.
		 *
		 * @param solicitudManager el nuevo solicitud manager
		 */
		public void setSolicitudManager(SolicitudesRegistradasManager solicitudManager) {
			this.solicitudManager = solicitudManager;
		}
	
		/**
		 * Obtiene el usuario manager.
		 *
		 * @return el usuario manager
		 */
		public UsuarioManager getUsuarioManager() {
			return usuarioManager;
		}
	
		/**
		 * Establece el usuario manager.
		 *
		 * @param usuarioManager el nuevo usuario manager
		 */
		public void setUsuarioManager(UsuarioManager usuarioManager) {
			this.usuarioManager = usuarioManager;
		}
	
		/**
		 * Obtiene el datos desempleo manager.
		 *
		 * @return el datos desempleo manager
		 */
		public DatosDesempleoManager getDatosDesempleoManager() {
			return datosDesempleoManager;
		}
	
		/**
		 * Establece el datos desempleo manager.
		 *
		 * @param datosDesempleoManager el nuevo datos desempleo manager
		 */
		public void setDatosDesempleoManager(DatosDesempleoManager datosDesempleoManager) {
			this.datosDesempleoManager = datosDesempleoManager;
		}
	
		/**
		 * Obtiene el convocatorias manager.
		 *
		 * @return el convocatorias manager
		 */
		public ConvocatoriasManager getConvocatoriasManager() {
			return convocatoriaManager;
		}
	
		/**
		 * Establece el convocatorias manager.
		 *
		 * @param convocatoriaManager el nuevo convocatorias manager
		 */
		public void setConvocatoriasManager(ConvocatoriasManager convocatoriaManager) {
			this.convocatoriaManager = convocatoriaManager;
		}
		
		/**
		 * Obtiene el solicitud comun auxiliar manager.
		 *
		 * @return el solicitud comun auxiliar manager
		 */
		public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
			return solicitudComunAuxiliarManager;
		}

		/**
		 * Establece el solicitud comun auxiliar manager.
		 *
		 * @param solicitudComunAuxiliarManager el nuevo solicitud comun auxiliar manager
		 */
		public void setSolicitudComunAuxiliarManager(SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
			this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
		}
	
	}
