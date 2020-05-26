package es.map.ipsg.action.incidencia;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.IncidenciasBean;
import es.map.ipsg.form.IncidenciasForm;
import es.map.ipsg.manager.IncidenciasManager;

/**
 * DetalleIncidenciasAction.
 */
public class DetalleIncidenciasSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DetalleIncidenciasSpring.class);
	
	/** el incidencias manager. */
	private IncidenciasManager incidenciasManager;

		
	/**
	 * DetalleIncidenciasAction.
	 */
	public DetalleIncidenciasSpring() {
		try {
			setIncidenciasManager((IncidenciasManager) getBean("incidenciasManager"));
			
		} catch (Exception e) {
			logger.error("Error DetalleIncidenciasSpring():",e);
		}
	}

	/**
	 * doExecute de DetalleIncidenciasAction.
	 *
	 * @param form  SpringForm
	 * @return resultado String
	 * @throws Exception  Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);		
		String subMenu_incidencias = RESOURCE_BUNDLE.getString("field.menuLateral.consultas.incidencias");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_incidencias);
		
		String resultado;
	try{	
		IncidenciasForm formulario = (IncidenciasForm) form;
			
		
		Integer idIncidencias = formulario.getId();
		
		if(idIncidencias>0){		
			IncidenciasBean incidenciasBean = incidenciasManager.obtenerIncidencia(idIncidencias.longValue());		
			formulario.setId(incidenciasBean.getId().intValue());
			detalleIncidencias(incidenciasBean,formulario);
		
			this.setRequestAttribute("incidencias",incidenciasBean);
			
			}
		resultado = "success";	
		
	}catch(Exception eGenerico){
		logger.error("Error DetalleIncidenciasSpring() - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return resultado;
	}
	
	/**
	 * Detalle incidencias.
	 *
	 * @param incidenciasBean el incidencias bean
	 * @param formulario el formulario
	 */
	private void detalleIncidencias(IncidenciasBean incidenciasBean, IncidenciasForm formulario) {
		if (incidenciasBean.getNif()!=null){
			formulario.setNif(incidenciasBean.getNif());
		}
		if(incidenciasBean.getNombre()!=null){
			formulario.setNombre(incidenciasBean.getNombre());
		}
		if(incidenciasBean.getPrimerApellido() != null){
			formulario.setPrimerApellido(incidenciasBean.getPrimerApellido());
		}
		if(incidenciasBean.getSegundoApellido()!=null){
			formulario.setSegundoApellido(incidenciasBean.getSegundoApellido());
		}
		if(incidenciasBean.getFechaEnvio()!=null){
			formulario.setFecha(incidenciasBean.getFechaEnvio());
		}
		if(incidenciasBean.getEmail()!=null){
			formulario.setEmail(incidenciasBean.getEmail());
		}
		if(incidenciasBean.getMensaje()!=null){
			String mensaje = incidenciasBean.getMensaje();
			mensaje = transformarMensaje(mensaje);
			formulario.setMensaje(mensaje);
		}
		if(incidenciasBean.getTelefono()!=null){
			formulario.setTelefono(incidenciasBean.getTelefono());
		}	
	}
	
	/**
	 * Obtiene el incidencias manager.
	 *
	 * @return incidenciasManager IncidenciasManager
	 */
	public IncidenciasManager getIncidenciasManager() {
		return incidenciasManager;
	}

	/**
	 * Establece el incidencias manager.
	 *
	 * @param incidenciasManager IncidenciasManager
	 */
	public void setIncidenciasManager(IncidenciasManager incidenciasManager) {
		this.incidenciasManager = incidenciasManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Transformar mensaje.
	 *
	 * @param mensaje el mensaje
	 * @return el string
	 */
	public String transformarMensaje(String mensaje){
		String result = mensaje;
		
		result = result.replace("<BR>", "\r\n");
		
		result = result.replace("&aACUTE;", "á");
		result = result.replace("&eACUTE;", "é");
		result = result.replace("&iACUTE;", "í");
		result = result.replace("&oACUTE;", "ó");
		result = result.replace("&uACUTE;", "ú");
		
		result = result.replace("&AACUTE;", "Á");
		result = result.replace("&EACUTE;", "É");
		result = result.replace("&IACUTE;", "Í");
		result = result.replace("&OACUTE;", "Ó");
		result = result.replace("&UACUTE;", "Ú");
		
		return result;
		
	}


}
