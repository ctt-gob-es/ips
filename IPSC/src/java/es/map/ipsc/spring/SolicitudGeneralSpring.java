package es.map.ipsc.spring;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class SolicitudGeneralSpring.
 */
public class SolicitudGeneralSpring extends AbstractSecureSpring  {
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		System.out.println("SolicitudGeneralAction");
		return null;
	
	}
}
