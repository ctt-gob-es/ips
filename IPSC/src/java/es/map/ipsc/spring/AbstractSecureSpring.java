package es.map.ipsc.spring;

import java.io.Reader;
import java.io.StringReader;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import es.egoeveris.firma.impl.afirma.model.parametrosValCertificado.ParamCert;
import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.exception.ExceptionMessageUtil;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.common.web.util.ContextHolder;
import es.map.ips.common.web.util.ConversionHelper;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.exception.IpsException;
import es.map.ipsc.service.ValidarCertificado;
import es.map.ipsc.utils.IpsUtils;
import es.map.ipsc.utils.SHA0;

/**
 * El Class AbstractSecureSpring.
 *
 * @param <T> the generic type
 */
public abstract class AbstractSecureSpring<T extends SpringForm> {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AbstractSecureSpring.class);
	
	/** La constante STRING_CERTIFICADOS_ERROR_VALIDACION. */
	private static final String STRING_CERTIFICADOS_ERROR_VALIDACION = "certificados.error.validacion";
	
	/** La constante STRING_CERTIFICADOS_ERROR_RECUPERARDATOS. */
	private static final String STRING_CERTIFICADOS_ERROR_RECUPERARDATOS = "certificados.error.recuperarDatos";
	
	/** La constante STRING_ORG_APACHE_SPRING_ERROR. */
	private static final String STRING_ORG_APACHE_SPRING_ERROR = "org.apache.spring.ERROR";
	
	/** La constante STRING_ORG_APACHE_SPRING_ACTION_MESSAGE. */
	private static final String STRING_ORG_APACHE_SPRING_ACTION_MESSAGE = "org.apache.spring.ACTION_MESSAGE";
	
	/** el tl request. */
	private static ThreadLocal<HttpServletRequest> tlRequest = new ThreadLocal<HttpServletRequest>();
	
	/** el tl response. */
	private static ThreadLocal<HttpServletResponse> tlResponse = new ThreadLocal<HttpServletResponse>();
	
	/** el tl mapping. */
	private static ThreadLocal<SpringMapping> tlMapping = new ThreadLocal<SpringMapping>();
	
	/** el conversion helper. */
	private ConversionHelper conversionHelper;
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	

	
	/** el properties. */
	private  Properties properties;
	
	/** el prop. */
	private Properties prop;
	
	/**
	 * Crea una nueva abstract secure spring.
	 */
	public AbstractSecureSpring() {
		try{
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("error: ",e);
		}
	}
	
	/**
	 * Execute.
	 *
	 * @param mapping el mapping
	 * @param form el form
	 * @param request el request
	 * @param response el response
	 * @return el spring forward
	 * @throws Exception el exception
	 */
	public SpringForward execute(SpringMapping mapping, SpringForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		tlRequest.set(request);
		tlResponse.set(response);
		tlMapping.set(mapping);

		String fwd = null;
		
		if (!isUserInRole()) {
			fwd = "accessDenied";
		} else {		
			Locale locale = (Locale) getSessionAttribute(Globals.LOCALE_KEY);
			if (locale == null) {
				locale = Locale.getDefault();
				setSessionAttribute(Globals.LOCALE_KEY, locale);
			}
			ContextHolder.setCurrentLocale(locale);
			
			try {
				fwd = doExecute((T)form);
				logger.info("Forward: "+fwd);
				
			} catch (BusinessException e) {
				//Cuando se produce una excepción de negocio, el mensaje
				//internacionalizado se presenta directamente al usuario
				logger.error("Error de negocio", e);
				setRequestAttribute("exceptionMessage", e.getMessage());
				fwd = "actionErrorBusiness";
			} catch (ApplicationException e) {
				//Cuando se produce una excepción técnica, se presenta
				//al usuario un mensaje de error genérico, y el error
				//se registra en un log
				setRequestAttribute("errorDescripcion", e.getMessage());
				logger.error("Error en la aplicacion",e);
				
				fwd = "errorGenerico";
			} catch (IpsException ipse) {
				logger.error(ipse.getMessage());
			} catch (Exception e) {
				setRequestAttribute("errorDescripcion", ExceptionMessageUtil.getString(locale, "exception.application"));
				logger.error("Error generico", e);
				fwd = "errorGenerico";
				e.printStackTrace();
			}
		}
		
		tlRequest.set(null);
		tlResponse.set(null);
		tlMapping.set(null);
		
		return mapping.findForward(fwd);
		
	}
	
	/**
	 * Obtener Y validar certificado.
	 *
	 * @return el string
	 * @throws ApplicationException el application exception
	 */
	protected String obtenerYValidarCertificado() throws ApplicationException{
		
		//cogemos el certificado
		String arrayCertificados=(String)this.getRequest().getParameter("certificado");
		String resultado = null;
		String certificado = null;
				
		try{
		
			if(arrayCertificados!=null){
				logger.debug("Tengo mi certificado y voy a validarlo.");
				certificado =arrayCertificados;
				logger.debug(certificado);
				
				ParamCert param = new ParamCert();
				param.setCertificado(certificado);
				param.setIdAplicacion(properties.getProperty("webservices.idAplicacion"));
				param.setModovalidacion(Integer.valueOf(properties.getProperty("webservices.modoValidacion")));
				param.setObtenerinfo(true);
				logger.debug("Antes de validar el certificado");
				ValidarCertificado validarCertificado = new ValidarCertificado();
				resultado = validarCertificado.validar2(param);
			}else{
				logger.error("El array de certificados es nulo");
			}
		}catch(Exception e){
			logger.error(RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_VALIDACION));
			logger.error("Error validacion",e);
			
			throw new ApplicationException(STRING_CERTIFICADOS_ERROR_VALIDACION);
		}
		
		return resultado;
	}
	
	/**
	 * Recuperar datos certificado.
	 *
	 * @param resultado el resultado
	 * @return el ciudadano bean
	 * @throws ApplicationException el application exception
	 */
	public CiudadanoBean recuperarDatosCertificado(String resultado) throws ApplicationException{
		CiudadanoBean ciudadano = null;
		
		try{
			logger.debug("Recuperando Certificados del Ciudadano");
			Reader validacionCertificado = new StringReader(resultado);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(validacionCertificado));
			XPath xpath = XPathFactory.newInstance().newXPath();
			

			
			
			String tipoPersona = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"clasificacion\"]/valorCampo", XMLDoc);
			logger.debug("Tipo de Certificado: "+tipoPersona);

			/*
			 * Campo clasificación
			 * 0 = Persona física
			 * 1 = Persona jurídica
			 * 5 = Empleado público
			 */	
			
			if(tipoPersona != null && tipoPersona.equals(Constantes.TIPO_PERSONA_JURIDICA)){
				logger.error(RESOURCE_BUNDLE.getString("certificados.error.juridico"));
				throw new ApplicationException("certificados.error.juridico");
				
			}
			else if(tipoPersona != null && (tipoPersona.equals(Constantes.TIPO_PERSONA_FISICA) 
					|| tipoPersona.equals(Constantes.TIPO_EMPLEADO_PUBLICO))){		
				ciudadano = new CiudadanoBean();
				
				logger.debug("Certificado de Persona fisica");
			
				String nif = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"NIFResponsable\"]/valorCampo", XMLDoc);
				String nombre = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"nombreResponsable\"]/valorCampo", XMLDoc);
				String apellidos = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"ApellidosResponsable\"]/valorCampo", XMLDoc);
				String organizacionEmisora = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"OrganizacionEmisora\"]/valorCampo", XMLDoc);
				String numeroSerie = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"numeroSerie\"]/valorCampo", XMLDoc);
				String idEmisor = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"idEmisor\"]/valorCampo", XMLDoc);
				
				int nom=apellidos.lastIndexOf(' ');
				String primerApellido = apellidos.substring(0,nom);
				String segundoApellido = apellidos.substring(nom+1);
				
				ciudadano.setNif(nif);
				ciudadano.setNombre(nombre);
				ciudadano.setPrimerApellido(primerApellido);
				ciudadano.setSegundoApellido(segundoApellido);
				ciudadano.setOrganizacionEmisora(organizacionEmisora);
				ciudadano.setIdEmisor(idEmisor);
				ciudadano.setNumeroSerie(numeroSerie);
	
				Integer numeroSecuencial=(int)(Math.random()*99999999);
				ciudadano.setAleatorio(numeroSecuencial);
				
				logger.debug("*****NIF: "+ciudadano.getNif());
				logger.debug("*****NOMBRE: "+ciudadano.getNombre());
				logger.debug("*****PRIMER APELLIDO: "+ciudadano.getPrimerApellido());
				logger.debug("*****SEGUNDO APELLIDO: "+ciudadano.getSegundoApellido());
				logger.debug("*****ORGANIZACION EMISORA: "+ciudadano.getOrganizacionEmisora());
				logger.debug("*****NUMERO DE SERIE: "+ciudadano.getNumeroSerie());
				logger.debug("*****ID EMISOR: "+ciudadano.getIdEmisor());
				
				SHA0 hash = new SHA0();
	
				if (ciudadano.getNif()!= null || ciudadano.getNif()!= ""){

					
					
					String tokenUsu = nif+""+numeroSecuencial;
					byte[] tokenByte = IpsUtils.decodeBase64(tokenUsu);
					String tokenEncrip = hash.getHash(tokenByte);
					ciudadano.setTokenUsuario(tokenEncrip);
				}
			}
			else{
				// Certificado no válido
				logger.error(RESOURCE_BUNDLE.getString("certificados.error.novalido"));
				throw new ApplicationException("certificados.error.novalido");
			}
			
		}catch(Exception e){
			logger.error(RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_RECUPERARDATOS));
			logger.error("Error recuperar datos",e);
			
			throw new ApplicationException(STRING_CERTIFICADOS_ERROR_RECUPERARDATOS);
		}
		
		return ciudadano;
	}
	
	/**
	 * Validar certificado ciudadano.
	 *
	 * @return el ciudadano bean
	 * @throws ApplicationException el application exception
	 */
	protected CiudadanoBean validarCertificadoCiudadano() throws ApplicationException{
		HttpSession session = this.getRequest().getSession();
		
		CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuario");
		CiudadanoBean ciudadanoBean = null;
		if(usuActual == null){
			//Comprobar si el usuario ha cerrado la sesion
			if ("cerrado".equals(session.getAttribute("cierreSesion"))){
				JOptionPane.showMessageDialog(null, "Debe cerrar la ventana y abrir un explorador nuevo", "Aviso Sesison", JOptionPane.WARNING_MESSAGE);
				throw new ApplicationException("MostrarEntradaAction");
			}	
			
			prop = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
					

		   	
			
			
			String resultado = obtenerYValidarCertificado();
			logger.debug("Despues de validar el certificado. Resultado = "+resultado);
			
			
			if (resultado!=null){
				 ciudadanoBean = recuperarDatosCertificado(resultado);
				
				if(ciudadanoBean != null){
					this.setSessionAttribute("usuario", ciudadanoBean);
					
				}else{
					logger.error(RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_RECUPERARDATOS));
					throw new ApplicationException(STRING_CERTIFICADOS_ERROR_RECUPERARDATOS);
				}
			}else{
				logger.error(RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_VALIDACION));
				throw new ApplicationException(STRING_CERTIFICADOS_ERROR_VALIDACION);
			}
		}
		return ciudadanoBean;
		
	}
	
	/**
	 * Obtiene el request.
	 *
	 * @return el request
	 */
	protected HttpServletRequest getRequest() {
		return tlRequest.get();
	}
	
	/**
	 * Obtiene el response.
	 *
	 * @return el response
	 */
	protected HttpServletResponse getResponse() {
		return tlResponse.get();
	}

	/**
	 * Obtiene el mapping.
	 *
	 * @return el mapping
	 */
	protected SpringMapping getMapping() {
		return tlMapping.get();
	}
	
	/**
	 * Obtiene el session.
	 *
	 * @return el session
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * Establece el request attribute.
	 *
	 * @param name el name
	 * @param attribute el attribute
	 */
	protected void setRequestAttribute(String name, Object attribute) {
		getRequest().setAttribute(name, attribute);
	}
	
	/**
	 * Establece el session attribute.
	 *
	 * @param name el name
	 * @param attribute el attribute
	 */
	protected void setSessionAttribute(String name, Object attribute) {
		getSession().setAttribute(name, attribute);
	}

	/**
	 * Obtiene el session attribute.
	 *
	 * @param name el name
	 * @return el session attribute
	 */
	protected Object getSessionAttribute(String name) {
		return getSession().getAttribute(name);
	}
	
	/**
	 * Comprueba si es user in role.
	 *
	 * @return verdadero, si es user in role
	 */
	protected boolean isUserInRole() {
		String roles[] = getMapping().getRoleNames();
		if (roles == null || roles.length == 0) {
			//No se han definido roles sobre la acción. No existen restricciones de perfil
			return true;
		}
		
		for (int i = 0; i < roles.length; i++) {
			if (getRequest().isUserInRole(roles[i])) {
				//El rol del usuario tiene pemiso de ejcución sobre el action.
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Obtiene el bean.
	 *
	 * @param name el name
	 * @return el bean
	 */
	protected Object getBean(String name) {
		return ApplicationContextProvider.getInstance().getBean(name);
	}
	
	/**
	 * Do execute.
	 *
	 * @param form el form
	 * @return el string
	 * @throws Exception el exception
	 */
	protected abstract String doExecute(T form) throws Exception;

	// getters and setters
	
	/**
	 * Obtiene el conversion helper.
	 *
	 * @return el conversion helper
	 */
	public ConversionHelper getConversionHelper() {
		return conversionHelper;
	}

	/**
	 * Establece el conversion helper.
	 *
	 * @param conversionHelper el nuevo conversion helper
	 */
	public void setConversionHelper(ConversionHelper conversionHelper) {
		this.conversionHelper = conversionHelper;
	}
	
	/**
	 * Save errors.
	 *
	 * @param session el session
	 * @param errors el errors
	 */
	public static void saveErrors(HttpSession session, SpringMessages errors) {
		if ((errors == null) || (errors.isEmpty())) {
			session.removeAttribute(STRING_ORG_APACHE_SPRING_ERROR);
			return;
		}

		session.setAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
	}
	
	/**
	 * Elimina el errors.
	 *
	 * @param session el session
	 */
	public static void removeErrors(HttpSession session) {
		session.removeAttribute(STRING_ORG_APACHE_SPRING_ERROR);
	}

	/**
	 * Save errors.
	 *
	 * @param request el request
	 * @param errors el errors
	 */
	protected static void saveErrors(HttpServletRequest request, SpringMessages errors) {
		if ((errors == null) || (errors.isEmpty())) {
			request.removeAttribute(STRING_ORG_APACHE_SPRING_ERROR);
			return;

		}
		request.setAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
	}
	
	/**
	 * Elimina el errors.
	 *
	 * @param request el request
	 */
	protected void removeErrors(HttpServletRequest request) {
		request.removeAttribute(STRING_ORG_APACHE_SPRING_ERROR);
	}
	
	/**
	 * Save messages.
	 *
	 * @param session el session
	 * @param messages el messages
	 */
	protected void saveMessages(HttpSession session, SpringMessages messages) {
		if ((messages == null) || (messages.isEmpty())) {
			session.removeAttribute(STRING_ORG_APACHE_SPRING_ACTION_MESSAGE);
			return;

		}

		session.setAttribute(STRING_ORG_APACHE_SPRING_ACTION_MESSAGE, messages);
	}
	
	/**
	 * Obtiene el errors.
	 *
	 * @param request el request
	 * @return el errors
	 */
	protected SpringMessages getErrors(HttpServletRequest request) {
		SpringMessages errors = (SpringMessages) request.getAttribute(STRING_ORG_APACHE_SPRING_ERROR);

		if (errors == null) {
			errors = new SpringMessages();
		}
		return errors;
	}
	
	/**
	 * Save messages.
	 *
	 * @param request el request
	 * @param messages el messages
	 */
	protected void saveMessages(HttpServletRequest request, SpringMessages messages) {
		if ((messages == null) || (messages.isEmpty())) {
			request.removeAttribute(STRING_ORG_APACHE_SPRING_ACTION_MESSAGE);
			return;
		}

		request.setAttribute(STRING_ORG_APACHE_SPRING_ACTION_MESSAGE, messages);
	}
}
