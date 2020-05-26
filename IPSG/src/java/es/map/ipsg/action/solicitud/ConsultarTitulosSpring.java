//package es.map.ipsg.action.solicitud;
//
//import java.util.List;
//import java.util.ResourceBundle;
//
//import org.apache.log4j.Logger;
//import org.springframework.security.context.SecurityContextHolder;
//import org.springframework.security.userdetails.User;
//
//import es.map.ips.common.exception.BusinessException;
//import es.map.ips.common.spring.AbstractSpring;
//import es.map.ips.common.spring.SpringForm;
//import es.map.ips.model.UsuarioQuery;
//import es.map.ipsg.bean.IntermediacionTituloBean;
//import es.map.ipsg.bean.SolicitudBean;
//import es.map.ipsg.bean.UsuarioBean;
//import es.map.ipsg.manager.DatosTitulosManager;
//import es.map.ipsg.manager.SolicitudesRegistradasManager;
//import es.map.ipsg.manager.UsuarioManager;
//
//public class ConsultarTitulosSpring extends AbstractSpring {
//	
//	private static final String MESSAGE_RESOURCE = "MessageResources";
//	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
//	private static final String STRING_ERROR = "error";
//	private static final String STRING_DESCRIPCIONERROR = "descripcionError";
//
//	private static final Logger logger = Logger.getLogger(ConsultarTitulosSpring.class);
//	
//	private SolicitudesRegistradasManager solicitudManager;
//	private DatosTitulosManager datosTitulosManager;
//	private UsuarioManager usuarioManager;
//	
//	public ConsultarTitulosSpring() {
//		try{
//			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
//			setDatosTitulosManager((DatosTitulosManager) getBean("datosTitulosManager"));
//			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
//		}catch(Exception e){
//			logger.warn(e);
//			logger.error("Error ConsultarTitulosSpring:" ,e);
//		}
//	}
//
//	/**
//	 * doExecute  de ConsultarTitulosAction
//	 * @param form SpringForm
//	 * @return resultado   String
//	 * @throws Exception Exception
//	 */
//	protected String doExecute(SpringForm form) throws Exception {
//		String resultado = null;
//		String mensaje = null;
//		
//		try{
//			logger.debug("ConsultarTitulosSpring -start");
//			
//			//Tomamos el usuario que se ha logueado
//			String sUsernameLogin = recuperarUsuario();
//			
//			if (sUsernameLogin.equals(STRING_ERROR)) {
//				return sUsernameLogin;
//			}
//			
//			
//
//			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
//			UsuarioQuery usuarioQuery = new UsuarioQuery();
//			usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado
//
//			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
//			
//			String idSolicitud = (String)this.getRequest().getParameter("idSolicitud");			
//			
//			if(idSolicitud!=null){
//				SolicitudBean solicitudBean  = new SolicitudBean ();
//				
//				try{
//					solicitudBean = solicitudManager.obtenerSolicitud(Long.valueOf(idSolicitud));
//				}catch(Exception e){
//					resultado = STRING_ERROR;
//					mensaje = RESOURCE_BUNDLE.getString("svto.error.numeroSolicitud");
//					this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
//					logger.error(" Error ConsultarTitulosSpring Numero de solicitud erroneo: "+idSolicitud,e);
//					return resultado;
//				}
//
//				
//				List<IntermediacionTituloBean> titulosUniversitarios = datosTitulosManager.consultarTitulosUniversitarios(solicitudBean,usuarioBean);
//				List<IntermediacionTituloBean> titulosNoUniversitarios = datosTitulosManager.consultarTitulosNoUniversitarios(solicitudBean,usuarioBean);
//				
//				this.getRequest().setAttribute("titulosUniversitarios", titulosUniversitarios);
//				this.getRequest().setAttribute("titulosNoUniversitarios", titulosNoUniversitarios);
//				this.getRequest().setAttribute("idSolicitud", idSolicitud);
//				
//				if(solicitudBean.getTituloOficial()!=null){
//					this.getRequest().setAttribute("tituloSolicitud", solicitudBean.getTituloOficial().getDescripcion());
//				}
//				if(solicitudBean.getOtrosTitulos()!=null){
//					this.getRequest().setAttribute("otrosTituloSolicitud", solicitudBean.getOtrosTitulos());
//				}
//				
//				resultado = "success";
//			}else{
//				resultado = STRING_ERROR;
//				mensaje = RESOURCE_BUNDLE.getString("svto.error.numeroSolicitud");
//				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
//				logger.error("Numero de solicitud obtenido es null");
//			}
//			
//			logger.debug("ConsultarTitulosSpring -end");
//			return resultado;
//		}catch (BusinessException ex){
//			logger.error("Error ConsultarTitulosSpring" ,ex);
//			resultado = STRING_ERROR;
//			mensaje = ex.getMessage();			
//			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
//		}catch (Exception ex){
//			logger.error("Error ConsultarTitulosSpring" ,ex);
//			resultado = STRING_ERROR;
//			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
//			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
//		}
//		
//		return resultado;
//	}
//	
//	private String recuperarUsuario() {
//		String sUsernameLogin = "";
//		try{
//			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			sUsernameLogin = user.getUsername();
//			return sUsernameLogin;
//		}catch(Exception e){
//			logger.error("Error ConsultarTitulosSpring - recuperarUsuarioSesion:"+ sUsernameLogin ,e);
//			new BusinessException("exception.recuperarUsuarioSesion");
//			return STRING_ERROR;
//		}
//	}
//	
//	public SolicitudesRegistradasManager getSolicitudManager() {
//		return solicitudManager;
//	}
//
//	public void setSolicitudManager(SolicitudesRegistradasManager solicitudManager) {
//		this.solicitudManager = solicitudManager;
//	}
//
//	public DatosTitulosManager getDatosTitulosManager() {
//		return datosTitulosManager;
//	}
//
//	public void setDatosTitulosManager(DatosTitulosManager datosTitulosManager) {
//		this.datosTitulosManager = datosTitulosManager;
//	}
//
//	public UsuarioManager getUsuarioManager() {
//		return usuarioManager;
//	}
//
//	public void setUsuarioManager(UsuarioManager usuarioManager) {
//		this.usuarioManager = usuarioManager;
//	}
//
//}
