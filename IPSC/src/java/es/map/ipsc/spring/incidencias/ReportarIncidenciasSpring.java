package es.map.ipsc.spring.incidencias;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.CorreoElectronicoBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.form.IncidenciasForm;
import es.map.ipsc.manager.ciudadano.CiudadanoManager;
import es.map.ipsc.manager.incidencias.IncidenciasManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.utils.EnvioMails;
import es.map.ipsc.utils.Validacion;

/**
 * El Class ReportarIncidenciasSpring.
 *
 * @author everis
 */
public class ReportarIncidenciasSpring extends AbstractSecureSpring {
	
	/** el incidencias manager. */
	IncidenciasManager incidenciasManager;
	
	/** el ciudadano manager. */
	CiudadanoManager ciudadanoManager;
	
	/** el parametro configuracion manager. */
	ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante STRING_ENVIO. */
	private static final String STRING_ENVIO= "envio";
	

	
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ReportarIncidenciasSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/**
	 * Crea una nueva reportar incidencias spring.
	 */
	public ReportarIncidenciasSpring() {
		try{
			setIncidenciasManager((IncidenciasManager) getBean("incidenciasManager"));
			setCiudadanoManager((CiudadanoManager) getBean("ciudadanoManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error reportar Incidencias  ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("ReportarIncidenciasSpring -start");
		this.getRequest().getSession().setAttribute("pagActiva", null);
				
		try{
			IncidenciasForm formulario = (IncidenciasForm) form;		
			
			//Comprobar si el usuario esta en la sesion
			CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
			if(usuActual == null){
				this.getRequest().setAttribute("errorDescripcion",RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
				return "errorUsuario";
			}else{
				if(usuActual.getNif() == null){
					this.getRequest().setAttribute("errorDescripcion",RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
					return "errorUsuario";
				}
			}
			//Validar formulario
			String llamada = this.getRequest().getParameter("resul");
			if(formulario.getAccion() != null && formulario.getAccion().equals("Enviar") && llamada == null){
			SpringMessages errores = new SpringMessages();
			Validacion validate = new Validacion();
				//Añadido get para asunto
				String asuntoValidacion = formulario.getAsunto();
				String emailValidacion = formulario.getEmail();
				String textoValidacion = formulario.getTexto();
				String telefonoValidacion = formulario.getTelefono();

				if(telefonoValidacion == null || "".equals(telefonoValidacion)){
					errores.add("dsErrorTelefonoRelleno", new SpringMessage("field.incidencias.jsp.errorTelefono"));
				}else{
					boolean result = validate.validateNumero(telefonoValidacion);
					if(result){
						errores.add("dsErrorTelefonoFormato", new SpringMessage("field.incidencias.jsp.errorTelefonoFormato"));
					}
				}
				if(emailValidacion == null || "".equals(emailValidacion)){
					errores.add("dsErrorEmailRelleno", new SpringMessage("field.incidencias.jsp.errorEmail"));
				}else{		
					boolean result = validate.validateEmail(emailValidacion);
					if(result){
						errores.add("dsErrorEmailFormato", new SpringMessage("field.incidencias.jsp.errorEmailFormato"));
					}				
				}
				if(asuntoValidacion == null || "".equals(asuntoValidacion)) {
					errores.add("dsErrorTextoRelleno", new SpringMessage("field.incidencias.jsp.errorAsunto"));
				}
				if(textoValidacion == null || "".equals(textoValidacion)){
					errores.add("dsErrorTextoRelleno", new SpringMessage("field.incidencias.jsp.errorTexto"));
				}
				
				saveErrors(this.getRequest(), errores);
				
				if(errores.size()>0){
					formulario.setAccion("");
					this.setRequestAttribute("validacion", true);
					return "nosuccess";
				}
			}
			
			String nombreCompleto = usuActual.getNombre() + " " + usuActual.getPrimerApellido() + " " + usuActual.getSegundoApellido();
			formulario.setNombre(nombreCompleto);
			formulario.setNif(usuActual.getNif());
			formulario.setTelefono(formulario.getTelefono());
			formulario.setEmail(formulario.getEmail());//Aqui cambia el email introducido por el que viene en el certificado digital				
			formulario.setPrimerApellido(usuActual.getPrimerApellido());
			formulario.setSegundoApellido(usuActual.getSegundoApellido());
			String asunto = formulario.getAsunto();
			String texto = formulario.getTexto();
			
			if(texto != null && !"".equals(texto) && llamada == null){
				ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
				if(usuActual != null){
					//Si hay usario en sesion se desde la aplicacion de ciudadano ciudadano
					paramConfQuery.setNombreCampo(Constantes.CORREO_INCIDENCIA_CIUDADANO);
				}
				//Recuperas los parametros de envio
				ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);
				
				CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
				
				correoElectronicoBean.setDe(properties.getProperty("mail.de"));
				correoElectronicoBean.setPara(parametrosConfiguracion.getValorCampo());
				
				//Añadido asunto
				correoElectronicoBean.setAsunto(RESOURCE_BUNDLE.getString("field.incidencias.enviarMail.asunto") + asunto);
				
				String texto1 			= RESOURCE_BUNDLE.getString("field.incidencias.cuerpo1");
				String br 				= RESOURCE_BUNDLE.getString("texto.br");
				String labelNombre 		= RESOURCE_BUNDLE.getString("field.incidencia.nombre");
				String labelMail 		= RESOURCE_BUNDLE.getString("field.incidencia.mail");
				String labelTelefono 	= RESOURCE_BUNDLE.getString("field.incidencia.telefono");
				String labelNif 		= RESOURCE_BUNDLE.getString("field.incidencia.nif");
				
				String nombreIncidencia = labelNombre+ " " +usuActual.getNombre() + " " + usuActual.getPrimerApellido() + " " + usuActual.getSegundoApellido()+br;
				String nif = labelNif+ " " +usuActual.getNif()+br;
				String email = labelMail+ " " +formulario.getEmail()+br;
				String telefono = labelTelefono+ " " +formulario.getTelefono()+br+br;
				String texto2 = RESOURCE_BUNDLE.getString("field.incidencias.cuerpo2");
				String texto3 = formulario.getTexto();
				String textoFinal = texto1 + br + nombreIncidencia + nif + email + telefono + texto2 + br + texto3;				
				
				correoElectronicoBean.setMensaje(textoFinal);
				correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_PENDIENTE);
				boolean result = false;
				if(correoElectronicoBean.getDe() != null){
					//Se envia el mail
					 result = EnvioMails.envioMail(correoElectronicoBean);
				}
				if(result == false){
					//Si no se ha podido enviar el mensaje
					correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_PENDIENTE);
					setRequestAttribute("mensajeError" , RESOURCE_BUNDLE.getString("field.incidencias.enviarMail.incorrecto"));
					setRequestAttribute(STRING_ENVIO , "false");
					logger.error("No se ha podido mandar el mensaje");
				}else{
					// Si todo ha ido bien se elimina el usuario de la sesion
					this.getRequest().getSession().removeAttribute("usuarioClave");
					//Si se ha enviado correctamente
					correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_ENVIADO);
					setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.incidencias.enviarMail.correcto"));
					setRequestAttribute(STRING_ENVIO , "true");
					logger.info("El mensaje se envio de manera satisfactoriamente");
				}
				usuActual.setEmail(formulario.getEmail());
				usuActual.setTelefono(formulario.getTelefono());
				//Guardo en la tabla incidencias el envio
				incidenciasManager.guardarIncidencias(usuActual,correoElectronicoBean);
				
			}else{
				setRequestAttribute(STRING_ENVIO , "false");
			}
			setRequestAttribute("incidencia", formulario);	
			getLogger().debug("ReportarIncidenciasSpring -end");
			
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error reportar Incidencias - doExecute ",e);
			getLogger().debug("ReportarIncidenciasSpring -end");
			return "nosuccess";
		}
	}

	

	/**
	 * Obtiene el incidencias manager.
	 *
	 * @return el incidencias manager
	 */
	public IncidenciasManager getIncidenciasManager() {
		return incidenciasManager;
	}

	/**
	 * Establece el incidencias manager.
	 *
	 * @param incidenciasManager el nuevo incidencias manager
	 */
	public void setIncidenciasManager(IncidenciasManager incidenciasManager) {
		this.incidenciasManager = incidenciasManager;
	}

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

	/**
	 * Obtiene el ciudadano manager.
	 *
	 * @return el ciudadano manager
	 */
	public CiudadanoManager getCiudadanoManager() {
		return ciudadanoManager;
	}

	/**
	 * Establece el ciudadano manager.
	 *
	 * @param ciudadanoManager el nuevo ciudadano manager
	 */
	public void setCiudadanoManager(CiudadanoManager ciudadanoManager) {
		this.ciudadanoManager = ciudadanoManager;
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
