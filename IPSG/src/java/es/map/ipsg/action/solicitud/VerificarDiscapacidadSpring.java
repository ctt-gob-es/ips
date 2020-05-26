package es.map.ipsg.action.solicitud;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.form.VerificarDiscapacidadForm;
import es.map.ipsg.manager.DatosDiscapacidadManager;




/**
 * El Class VerificarDiscapacidadSpring.
 */
public class VerificarDiscapacidadSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERROR_VERIFICARDISCAPACIDAD. */
	private static final String STRING_ERROR_VERIFICARDISCAPACIDAD = " Error VerificarDiscapacidadSpring:";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarDiscapacidadSpring.class);
	
		/** el datos discapacidad manager. */
		private DatosDiscapacidadManager datosDiscapacidadManager;
	
	/**
	 * Crea una nueva verificar discapacidad spring.
	 */
	public VerificarDiscapacidadSpring() {
		try{
			setDatosDiscapacidadManager((DatosDiscapacidadManager) getBean("datosDiscapacidadManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error(STRING_ERROR_VERIFICARDISCAPACIDAD ,e);
		}
	}

	/**
	 * doExecute  de VerificarDiscapacidadAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		String resultado = null;
		String mensaje = null;
		
		try{
			logger.debug("VerificarDiscapacidadSpring -start");			
			
			VerificarDiscapacidadForm formulario = (VerificarDiscapacidadForm) form;			
			Long idSolicitud = formulario.getIdSolicitud();
			String auxiliar = "";
			
			if (this.getRequest().getParameter("auxiliar") != null) {
				auxiliar = (String)this.getRequest().getParameter("auxiliar");
			}
			
			String accion = formulario.getAccion();
			if(idSolicitud!=null && accion!=null){
				if(accion.equals("Aprobar")){
					if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
						datosDiscapacidadManager.aprobarDiscapacidadVerificadoAuxiliar(idSolicitud);
					}else {
						datosDiscapacidadManager.aprobarDiscapacidadVerificado(idSolicitud);
					}
				}else{
					if(auxiliar.equalsIgnoreCase("true") && auxiliar != null) {
						datosDiscapacidadManager.rechazarDiscapacidadVerificadoAuxiliar(idSolicitud);
					}else {
						datosDiscapacidadManager.rechazarDiscapacidadVerificado(idSolicitud);
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
			
			logger.debug("VerificarDiscapacidadSpring -end");
			return resultado;
		}catch (BusinessException ex){
			logger.warn(ex);
			logger.error(STRING_ERROR_VERIFICARDISCAPACIDAD ,ex);
			resultado = STRING_ERROR;
			mensaje = ex.getMessage();			
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}catch (Exception ex){
			logger.warn(ex);
			logger.error(STRING_ERROR_VERIFICARDISCAPACIDAD ,ex);
			resultado = STRING_ERROR;
			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR,mensaje);
		}
		
		return resultado;
	}

	/**
	 * Obtiene el datos discapacidad manager.
	 *
	 * @return el datos discapacidad manager
	 */
	public DatosDiscapacidadManager getDatosDiscapacidadManager() {
		return datosDiscapacidadManager;
	}

	/**
	 * Establece el datos discapacidad manager.
	 *
	 * @param datosDiscapacidadManager el nuevo datos discapacidad manager
	 */
	public void setDatosDiscapacidadManager(DatosDiscapacidadManager datosDiscapacidadManager) {
		this.datosDiscapacidadManager = datosDiscapacidadManager;
	}
}

