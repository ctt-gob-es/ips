//package es.map.ipsg.action.solicitud;
//
//import java.util.ResourceBundle;
//
//import org.apache.log4j.Logger;
//
//import es.map.ips.common.exception.BusinessException;
//import es.map.ips.common.spring.AbstractSpring;
//import es.map.ips.common.spring.SpringForm;
//import es.map.ipsg.form.VerificarTitulosForm;
//import es.map.ipsg.manager.DatosTitulosManager;
//
//public class VerificarTitulosSpring extends AbstractSpring {
//	
//	private static final String MESSAGE_RESOURCE = "MessageResources";
//	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
//
//	private static final Logger logger = Logger.getLogger(VerificarTitulosSpring.class);
//	private static final String STRING_ERROR = "error";
//	private static final String STRING_DESCRIPCIONERROR = "descripcionError";
//	
//		private DatosTitulosManager datosTitulosManager;
//	
//	public VerificarTitulosSpring() {
//		try{
//			setDatosTitulosManager((DatosTitulosManager) getBean("datosTitulosManager"));
//		}catch(Exception e){
//			logger.warn(e);
//			logger.error(" Error VerificarTitulosSpring- datos titulos ", e);
//		}
//	}
//
//	/**
//	 * doExecute  de VerificarTitulosAction
//	 * @param form SpringForm
//	 * @return resultado   String
//	 * @throws Exception Exception
//	 */
//	protected String doExecute(SpringForm form) throws Exception {
//		String resultado = null;
//		String mensaje = null;
//		
//		try{
//			logger.debug("VerificarTitulosSpring -start");			
//			
//			VerificarTitulosForm formulario = (VerificarTitulosForm) form;			
//			Long idSolicitud = formulario.getIdSolicitud();
//			String accion = formulario.getAccion();
//			if(idSolicitud!=null && accion!=null){
//				if(accion.equals("Aprobar")){
//					datosTitulosManager.aprobarTituloVerificado(idSolicitud);
//				}else{
//					datosTitulosManager.rechazarTituloVerificado(idSolicitud);
//				}
//				this.getRequest().setAttribute("accion",accion);
//				resultado = "success";
//			}else{
//				resultado = STRING_ERROR;
//				mensaje = RESOURCE_BUNDLE.getString("svdi.error.numeroSolicitud");
//				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
//				logger.error("Numero de solicitud obtenido es null");
//			}
//			
//			logger.debug("VerificarTitulosSpring -end");
//			return resultado;
//		}catch (BusinessException ex){
//			logger.warn(ex);
//			logger.error(" Error VerificarTitulosSpring- doExecute ", ex);
//			resultado = STRING_ERROR;
//			mensaje = ex.getMessage();			
//			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
//		}catch (Exception ex){
//			logger.warn(ex);
//			logger.error(" Error VerificarTitulosSpring- doExecute ", ex);
//			resultado = STRING_ERROR;
//			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
//			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
//		}
//		
//		return resultado;
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
//
//
//}
