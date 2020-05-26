package es.map.ipsg.action.solicitud;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.form.VerificarFNumerosaForm;
import es.map.ipsg.manager.DatosFNumerosaManager;




/**
 * El Class VerificarFNumerosaSpring.
 */
public class VerificarFNumerosaSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarFNumerosaSpring.class);
	
		/** el datos F numerosa manager. */
		private DatosFNumerosaManager datosFNumerosaManager;
	
	/**
	 * Crea una nueva verificar F numerosa spring.
	 */
	public VerificarFNumerosaSpring() {
		try{
			setDatosFNumerosaManager((DatosFNumerosaManager) getBean("datosFNumerosaManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error VerificarFNumerosaSpring -  datos familia numerosa:" ,e);
		}
	}

	/**
	 * doExecute  de VerificarFNumerosaAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		String resultado = null;
		String mensaje = null;
		
		try{
			logger.debug("VerificarFNumerosaSpring -start");			
			
			VerificarFNumerosaForm formulario = (VerificarFNumerosaForm) form;			
			Long idSolicitud = formulario.getIdSolicitud();
			String auxiliar = "";
			
			if (this.getRequest().getParameter("auxiliar") != null) {
				auxiliar = (String)this.getRequest().getParameter("auxiliar");
			}
			
			String accion = formulario.getAccion();
			if(idSolicitud!=null && accion!=null){
				if(accion.equals("Aprobar")){
					if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
						datosFNumerosaManager.aprobarFNumerosaVerificadoAuxiliar(idSolicitud);
					}else {
						datosFNumerosaManager.aprobarFNumerosaVerificado(idSolicitud);
					}
				}else{
					if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
						datosFNumerosaManager.rechazarFNumerosaVerificadoAuxiliar(idSolicitud);
					}else {
						datosFNumerosaManager.rechazarFNumerosaVerificado(idSolicitud);
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
			
			logger.debug("VerificarFNumerosaSpring -end");
			return resultado;
		}catch (BusinessException ex){
			logger.error("Error VerificarFNumerosaSpring - doExecute:" ,ex);
			resultado = STRING_ERROR;
			mensaje = ex.getMessage();			
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}catch (Exception ex){
			logger.warn(ex);
			logger.error("Error VerificarFNumerosaSpring - doExecute:" ,ex);
			resultado = STRING_ERROR;
			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}
		
		return resultado;
	}

	/**
	 * Obtiene el datos F numerosa manager.
	 *
	 * @return el datos F numerosa manager
	 */
	public DatosFNumerosaManager getDatosFNumerosaManager() {
		return datosFNumerosaManager;
	}

	/**
	 * Establece el datos F numerosa manager.
	 *
	 * @param datosFNumerosaManager el nuevo datos F numerosa manager
	 */
	public void setDatosFNumerosaManager(DatosFNumerosaManager datosFNumerosaManager) {
		this.datosFNumerosaManager = datosFNumerosaManager;
	}






}

