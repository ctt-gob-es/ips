package es.map.ipsg.action.solicitud;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.form.VerificarDesempleoForm;
import es.map.ipsg.manager.DatosDesempleoManager;


/**
 * El Class VerificarDesempleoSpring.
 */
public class VerificarDesempleoSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERROR_VERIFICARDESEMPLEO. */
	private static final String STRING_ERROR_VERIFICARDESEMPLEO = " Error VerificarDesempleoSpring:";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";	

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarDesempleoSpring.class);
	
		/** el datos desempleo manager. */
		private DatosDesempleoManager datosDesempleoManager;
	
	/**
	 * Crea una nueva verificar desempleo spring.
	 */
	public VerificarDesempleoSpring() {
		try{
			setDatosDesempleoManager((DatosDesempleoManager) getBean("datosDesempleoManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error(STRING_ERROR_VERIFICARDESEMPLEO ,e);
		}
	}

	/**
	 * doExecute  de VerificarDesempleoAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		String resultado = null;
		String mensaje = null;
		
		try{
			logger.debug("VerificarDesempleoSpring -start");			
			
			VerificarDesempleoForm formulario = (VerificarDesempleoForm) form;			
			Long idSolicitud = formulario.getIdSolicitud();
			String auxiliar = "";
			
			if (this.getRequest().getParameter("auxiliar") != null) {
				auxiliar = (String)this.getRequest().getParameter("auxiliar");
			}
			
			String accion = formulario.getAccion();
			if(idSolicitud!=null && accion!=null){
				if(accion.equals("Aprobar")){
					if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
						datosDesempleoManager.aprobarDesempleoVerificadoAuxiliar(idSolicitud);
					}else {
						datosDesempleoManager.aprobarDesempleoVerificado(idSolicitud);
					}
					
				}else{
					if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
						datosDesempleoManager.rechazarDesempleoVerificadoAuxiliar(idSolicitud);
					}else {
						datosDesempleoManager.rechazarDesempleoVerificado(idSolicitud);
					}
				}
				this.getRequest().setAttribute("accion",accion);
				resultado = "success";
			}else{
				resultado = STRING_ERROR;
				mensaje = RESOURCE_BUNDLE.getString("svdsepe.error.numeroSolicitud");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
				logger.error("Numero de solicitud obtenido es null");
			}
			
			logger.debug("VerificarDesempleoSpring -end");
			return resultado;
		}catch (BusinessException ex){
			logger.warn(ex);
			logger.error(STRING_ERROR_VERIFICARDESEMPLEO ,ex);
			resultado = STRING_ERROR;
			mensaje = ex.getMessage();			
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}catch (Exception ex){
			logger.warn(ex);
			logger.error(STRING_ERROR_VERIFICARDESEMPLEO ,ex);
			resultado = STRING_ERROR;
			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}
		
		return resultado;
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




}

