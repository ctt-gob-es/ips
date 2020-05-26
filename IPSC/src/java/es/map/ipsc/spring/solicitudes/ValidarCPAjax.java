package es.map.ipsc.spring.solicitudes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.ProvinciaQuery;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.manager.provincia.ProvinciaManager;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.utils.Validacion;

/**
 * El Class ValidarCPAjax.
 */
public class ValidarCPAjax extends AbstractSecureSpring {

	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ValidarCPAjax.class);
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/**
	 * Crea una nueva validar CP ajax.
	 */
	public ValidarCPAjax(){
		setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		try(BufferedReader buffered = new BufferedReader(new InputStreamReader(getRequest().getInputStream()))){
		
		String param = buffered.readLine();
		String[] parametros = param.split("&");
		String[] parametro1 = parametros[0].split("=");
		String idProvincia = parametro1[1];
		String[] parametro2 = parametros[1].split("=");
		String codigoPostal = parametro2[1];
				
		Validacion validacion = new Validacion();
		
		
	 	boolean validateCodPostal = validacion.validateNumero(codigoPostal);
		if(validateCodPostal == true){
			this.setRequestAttribute(STRING_ERROR,new SpringMessage("field.solicitud.alta.validate.errorCodPostal"));
		}else{					
			String digitosProvincia =  codigoPostal.substring(0, 2);
			ProvinciaQuery provinciaQuery = new ProvinciaQuery();
			provinciaQuery.setCodigo(digitosProvincia);
			provinciaQuery.setId(Integer.parseInt(idProvincia));
			
			ProvinciaBean provincia = provinciaManager.buscarProviciaId(provinciaQuery);
			 
			 if(provincia == null){
				 this.getRequest().setAttribute(STRING_ERROR,RESOURCE_BUNDLE.getString("field.formulario790.javascript.codigoPostalError"));
			}else if(!provincia.getId().toString().equals(idProvincia)){
				this.getRequest().setAttribute(STRING_ERROR,RESOURCE_BUNDLE.getString("field.formulario790.javascript.codigoPostalError"));
			}				
		}
		}catch(Exception e) {
			logger.error("Error validando CP Ajax",e);
		}
		
		return "success";
	}
	
	/**
	 * Obtiene el provincia manager.
	 *
	 * @return el provincia manager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager el nuevo provincia manager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
	}
		
		

}
