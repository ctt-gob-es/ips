package es.map.ipsg.action;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;


/**
 * El Class RedireccionadorSpring.
 */
public class RedireccionadorSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RedireccionadorSpring.class);
	
	/** La constante STRING_LLAMADA. */
	private static final String STRING_LLAMADA = "llamada";
	
	/** La constante STRING_IDREGISTRO. */
	private static final String STRING_IDREGISTRO = "idRegistro";
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Llega a redireccionar");
		String llamada = this.getRequest().getParameter(STRING_LLAMADA);
		if(llamada != null){
			if(llamada.equals("ModificacionRegistroREC")){
				logger.info("Modificacion Registro Rec");
				String numeroSolicitud = this.getRequest().getParameter("numeroSolicitud");
					
				this.setRequestAttribute("numeroSolicitud", numeroSolicitud);
				this.setRequestAttribute(STRING_LLAMADA, "Registrar");
				return "modificacionRegistrar";

			}else if(llamada.equals("RegistroREC")){
				logger.info("Registro Rec");
				String numeroSolicitud = this.getRequest().getParameter("numeroSolicitud");
					
				this.setRequestAttribute("numeroSolicitud", numeroSolicitud);
				this.setRequestAttribute(STRING_LLAMADA, "Registrar");
				return "registrar";

			}else if(llamada.equals("VerificarRec")){
				logger.info("Redireccionar a Rec");
				String idRegistro = this.getRequest().getParameter(STRING_IDREGISTRO);
				if(idRegistro != null)
					{logger.info("IdRegistro : "+idRegistro);
					
					this.setRequestAttribute(STRING_IDREGISTRO, idRegistro);
					this.setRequestAttribute(STRING_LLAMADA, "Verificar");
					return "verificar";
				}
				
			}else{
				if(llamada.equals("ReintentarEnvio")){
					logger.info("Redireccionar a ReintentarEnvio");
					String idRegistro = this.getRequest().getParameter(STRING_IDREGISTRO);
					if(idRegistro != null)
						{logger.info("IdRegistro : "+idRegistro);
						
						this.setRequestAttribute(STRING_IDREGISTRO, idRegistro);
						this.setRequestAttribute(STRING_LLAMADA, "Reintentar");
						return "reintentar";
					}
				}
			}
		}
		return "success";
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	
}
