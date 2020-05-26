package es.map.ipsg.action.solicitud;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.form.VerificarVictimasForm;
import es.map.ipsg.manager.DatosVictimasTerrorismoManager;


/**
 * El Class VerificarVictimasSpring.
 */
public class VerificarVictimasSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERROR_VERIFICARVictimas. */
	private static final String STRING_ERROR_VERIFICARVictimas = " Error VerificarVictimasSpring:";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";	

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarVictimasSpring.class);
	
		/** el datos Victimas manager. */
		private DatosVictimasTerrorismoManager datosVictimasManager;
	
	/**
	 * Crea una nueva verificar Victimas spring.
	 */
	public VerificarVictimasSpring() {
		try{
			setDatosVictimasManager((DatosVictimasTerrorismoManager) getBean("datosVictimasTerrorismoManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error(STRING_ERROR_VERIFICARVictimas ,e);
		}
	}

	/**
	 * doExecute  de VerificarVictimasAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		String resultado = null;
		String mensaje = null;
		
		try{
			logger.debug("VerificarVictimasSpring -start");			
			
			VerificarVictimasForm formulario = (VerificarVictimasForm) form;			
			Long idSolicitud = formulario.getIdSolicitud();
			String auxiliar = "";
			
			if (this.getRequest().getParameter("auxiliar") != null) {
				auxiliar = (String)this.getRequest().getParameter("auxiliar");
			}
			
			String accion = formulario.getAccion();
			if(idSolicitud!=null && accion!=null){
				if(accion.equals("Aprobar")){
					if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
						datosVictimasManager.aprobarVictimasVerificadoAuxiliar(idSolicitud);
					}else {
						datosVictimasManager.aprobarVictimasVerificado(idSolicitud);
					}
					
				}else{
					if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
						datosVictimasManager.rechazarVictimasVerificadoAuxiliar(idSolicitud);
					}else {
						datosVictimasManager.rechazarVictimasVerificado(idSolicitud);
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
			
			logger.debug("VerificarVictimasSpring -end");
			return resultado;
		}catch (BusinessException ex){
			logger.warn(ex);
			logger.error(STRING_ERROR_VERIFICARVictimas ,ex);
			resultado = STRING_ERROR;
			mensaje = ex.getMessage();			
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}catch (Exception ex){
			logger.warn(ex);
			logger.error(STRING_ERROR_VERIFICARVictimas ,ex);
			resultado = STRING_ERROR;
			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}
		
		return resultado;
	}

	public DatosVictimasTerrorismoManager getDatosVictimasManager() {
		return datosVictimasManager;
	}

	public void setDatosVictimasManager(DatosVictimasTerrorismoManager datosVictimasManager) {
		this.datosVictimasManager = datosVictimasManager;
	}


}

