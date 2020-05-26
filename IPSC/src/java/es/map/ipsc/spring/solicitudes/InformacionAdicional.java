package es.map.ipsc.spring.solicitudes;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ipsc.Constantes;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class InformacionAdicional.
 */
public class InformacionAdicional extends AbstractSecureSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(InformacionAdicional.class);


	/**
	 * Crea una nueva informacion adicional.
	 */
	public InformacionAdicional() { //Metodo vacio

	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		String llamada = this.getRequest().getParameter("llamada");
		logger.info("Llamada:" +llamada);
		if(llamada != null && !"".equals(llamada)){
			if(llamada.equals(Constantes.FORMA_PAGO_EXENTO_S)){
				return "exento";
			}else{
				if(llamada.equals(Constantes.FORMA_PAGO_ADEUDO_CUENTA_S)){
					return "adeudo";
				}else{
					if(llamada.equals(Constantes.FORMA_PAGO_TARJETA_S)){
						return "tarjeta";
					}else{
						if(llamada.equals("S")){
							return "adjuntos";
						}else{
							if(llamada.equals(Constantes.DISCAPACIDAD)){
								return "discapacidad";
							}else{
								if(llamada.equals(Constantes.FNUMEROSA)){
									return "fnumerosa";
								}else{
									if(llamada.equals(Constantes.NOCOMUNIDADES)){
										return "nocomunidades";
									}
								}
							}
						}
					}
				}
			}
		}
		return "nosucces";
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