package es.map.ipsg.action;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import es.egoeveris.firma.impl.afirma.model.parametrosValCertificado.ParamCert;
import es.guadaltel.framework.signer.impl.util.Base64Coder;
import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CertificadoForm;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.service.ValidarCertificado;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.SignDataUtil;


/**
 * El Class CertificadoSpring.
 */
public class CertificadoSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CertificadoSpring.class);
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_ERRORNIFNOEXISTE. */
	private static final String STRING_ERRORNIFNOEXISTE = "errorNifNoExiste";
	
	/** el properties. */
	private static Properties properties;
	
	
	/**
	 * Crea una nueva certificado spring.
	 */
	public CertificadoSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error en Certificado al hacer setUsuarioManager:", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Entra en el action");
		
		
		
		try{
			
			CertificadoForm formulario = (CertificadoForm) form;
			String nif = validarCertificado(formulario);			
			
			if (nif!=null){
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				usuarioQuery.setNif(nif);				
				usuarioQuery.setEstado('1');				
				List<UsuarioBean> usuarios = usuarioManager.buscarUsuarios(usuarioQuery);
		
				if(usuarios != null){
					if(usuarios.size()==1){
						UsuarioBean usuarioBean = usuarios.get(0);
						
						
						
						this.setRequestAttribute("u", usuarioBean.getLogin());
						this.setRequestAttribute("p", usuarioBean.getPassword());
					}else if(usuarios.size()>1){
						logger.error("Se ha encontrado mas de un usuario para el NIF: "+nif);
						SpringMessages messages = new SpringMessages();
						messages.add("masDeUnNif", new SpringMessage("field.login.error.masDeUnNif",nif));
						saveErrors(this.getRequest(), messages);
						return STRING_NOSUCCESS;
					}else{
						logger.error("No se ha encontrado usuario para el NIF: "+nif);
						SpringMessages messages = new SpringMessages();
						messages.add(STRING_ERRORNIFNOEXISTE, new SpringMessage("field.login.error.nifNoExiste"));
						saveErrors(this.getRequest(), messages);
						return STRING_NOSUCCESS;
					}
				}else{
					logger.error("No se ha encontrado usuario para el NIF: "+nif);
					SpringMessages messages = new SpringMessages();
					messages.add(STRING_ERRORNIFNOEXISTE, new SpringMessage("field.login.error.nifNoExiste"));
					saveErrors(this.getRequest(), messages);
					return STRING_NOSUCCESS;
				}
			}else{
				logger.error("No se ha podido recuperar el NIF del Certificado Digital");
				SpringMessages messages = new SpringMessages();
				messages.add(STRING_ERRORNIFNOEXISTE, new SpringMessage("field.login.error.recuperarNif"));
				saveErrors(this.getRequest(), messages);
				return STRING_NOSUCCESS;
			}			
		}catch(Exception e){
			logger.error("Se ha producido un error validando el Certificado Digital",e );
			SpringMessages messages = new SpringMessages();
			messages.add(STRING_ERRORNIFNOEXISTE, new SpringMessage("field.login.error.validarCertificado"));
			saveErrors(this.getRequest(), messages);
			return STRING_NOSUCCESS;
		}
		
		return "success";
	}
	
	/**
	 * Obtener Y validar certificado.
	 *
	 * @param form el form
	 * @return el string
	 * @throws ApplicationException el application exception
	 */
	protected String obtenerYValidarCertificado(CertificadoForm form) throws ApplicationException{
		//cogemos el certificado
		// TODO 29/04/2014 - Migración MiniApplet @firma (Acceso a IPSG)
		
		// TODO MiniApplet
		String arrayCertificados = form.getNuevoCertif();
	
		String resultado = null;
		String certificado= null;
		
		try{
		if(arrayCertificados!=null){
				logger.info("Tengo mi certificado y voy a validarlo.");
				certificado = arrayCertificados;
				
				
				logger.info(certificado);
				
				ParamCert param = new ParamCert();
				
				// TODO 29/04/2014 - Migración MiniApplet @firma (Acceso a IPSG)
				Base64Coder coder = new  Base64Coder();
				byte[] aCoder = coder.decodeBase64(certificado.getBytes());
				certificado = SignDataUtil.getInstance().getCertificateFromSignXML(aCoder);
								
				param.setCertificado(certificado);
				param.setIdAplicacion(properties.getProperty("webservices.idAplicacion"));
				param.setModovalidacion(Integer.valueOf(properties.getProperty("webservices.modoValidacion")));
				param.setObtenerinfo(true);
				logger.info("Antes de validar el certificado");
				ValidarCertificado validarCertificado = new ValidarCertificado();
				resultado = validarCertificado.validar2(param);
			}else{
				logger.error("El array de certificados es nulo");
			}
		}catch(Exception e){
			logger.error(RESOURCE_BUNDLE.getString("certificados.error.validacion"));
			logger.error("Error al obtener y validar el certificado ",e);
			
		}
		
		return resultado;
	}
	
	/**
	 * Recuperar datos certificado.
	 *
	 * @param resultado el resultado
	 * @return el string
	 * @throws ApplicationException el application exception
	 */
	public String recuperarDatosCertificado(String resultado) throws ApplicationException{
		
		try{
			logger.info("Recuperando Certificados del Usuario");
			Reader validacionCertificado = new StringReader(resultado);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(validacionCertificado));
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			String tipoPersona = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"clasificacion\"]/valorCampo", XMLDoc);
			logger.debug("Tipo de Certificado: "+tipoPersona);
			//Averiguar cómo sabemos qué tipo de documento es!
			/*
			 * Campo clasificación
			 * 0 = Persona física
			 * 1 = Persona jurídica
			 */	
			
			if(tipoPersona != null && tipoPersona.equals(Constantes.TIPO_PERSONA_JURIDICA)){
				logger.error(RESOURCE_BUNDLE.getString("certificados.error.juridico"));				
			}
			else if(tipoPersona != null && (tipoPersona.equals(Constantes.TIPO_PERSONA_FISICA) 
					|| tipoPersona.equals(Constantes.TIPO_EMPLEADO_PUBLICO))){		
				
				logger.info("Certificado de Persona fisica");
			
				String nif = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"NIFResponsable\"]/valorCampo", XMLDoc);
				String nombre = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"nombreResponsable\"]/valorCampo", XMLDoc);
				String apellidos = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"ApellidosResponsable\"]/valorCampo", XMLDoc);
				String organizacionEmisora = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"OrganizacionEmisora\"]/valorCampo", XMLDoc);
				String numeroSerie = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"numeroSerie\"]/valorCampo", XMLDoc);
				String idEmisor = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"idEmisor\"]/valorCampo", XMLDoc);
				
				int nom=apellidos.lastIndexOf(' ');
				String primerApellido = apellidos.substring(0,nom);
				String segundoApellido = apellidos.substring(nom+1);
								
				logger.debug("*****NIF: "+nif);
				logger.debug("*****NOMBRE: "+nombre);
				logger.debug("*****PRIMER APELLIDO: "+primerApellido);
				logger.debug("*****SEGUNDO APELLIDO: "+segundoApellido);
				logger.debug("*****ORGANIZACION EMISORA: "+organizacionEmisora);
				logger.debug("*****NUMERO DE SERIE: "+numeroSerie);
				logger.debug("*****ID EMISOR: "+idEmisor);
					
				return nif;

			}
			else{
				// Certificado no válido
				logger.error(RESOURCE_BUNDLE.getString("certificados.error.novalido"));
			}
			return null;
			
		}catch(Exception e){
			logger.error(RESOURCE_BUNDLE.getString("certificados.error.recuperarDatos"));
			logger.error("Error en certificados al recuperar Datos ",e);
			
			return null;
		}		
	}
	
	/**
	 * Validar certificado.
	 *
	 * @param form el form
	 * @return el string
	 * @throws ApplicationException el application exception
	 */
	protected String validarCertificado(CertificadoForm form) throws ApplicationException{
		   			
		String resultado = obtenerYValidarCertificado(form);
		logger.info("Despues de validar el certificado. Resultado = "+resultado);
		
		
		if (resultado!=null){
			String nif = recuperarDatosCertificado(resultado);
			
			if(nif != null){
				return nif;
			}else{
				logger.error(RESOURCE_BUNDLE.getString("certificados.error.recuperarDatos"));
			}
		}else{
			logger.error(RESOURCE_BUNDLE.getString("certificados.error.validacion"));
		}
		
		return null;

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
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}
	
	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
	
}
