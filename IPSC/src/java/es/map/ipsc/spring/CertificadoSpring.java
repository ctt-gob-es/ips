package es.map.ipsc.spring;

import java.io.Reader;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import es.egoeveris.firma.impl.afirma.model.parametrosValCertificado.ParamCert;
import es.guadaltel.framework.signer.impl.util.Base64Coder;
import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.web.util.ContextHolder;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.form.BuscaConvocatoriasForm;
import es.map.ipsc.manager.ciudadano.CiudadanoManager;
import es.map.ipsc.service.ValidarCertificado;
import es.map.ipsc.utils.IpsUtils;
import es.map.ipsc.utils.SHA0;
import es.map.ipsc.utils.SignDataUtil;

/**
 * El Class CertificadoSpring.
 *
 * @author everis
 */
public class CertificadoSpring extends AbstractSecureSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CertificadoSpring.class);
	
	/** el ciudadano manager. */
	private CiudadanoManager ciudadanoManager;
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_USUARIOCLAVE. */
	private static final String STRING_USUARIOCLAVE = "usuarioClave";
	
	/** La constante STRING_CERTIFICADOSERRORVALIDACION. */
	private static final String STRING_CERTIFICADOSERRORVALIDACION = "certificados.error.validacion";
	
	/** La constante STRING_CERTIFICADOSERRORRECUPERARDATOS. */
	private static final String STRING_CERTIFICADOSERRORRECUPERARDATOS= "certificados.error.recuperarDatos";
	
	/** el properties. */
	private static Properties properties;
	
	/**
	 * Crea una nueva certificado spring.
	 */
	public CertificadoSpring() {
		try{
			setCiudadanoManager((CiudadanoManager) getBean("ciudadanoManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("error - Certificado: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String llamadaRequest = this.getRequest().getParameter("llamada");
		String retn = "";

		Locale locale = (Locale) getSessionAttribute(Globals.LOCALE_KEY);
		if (locale == null) {
			locale = Locale.getDefault();
			setSessionAttribute(Globals.LOCALE_KEY, locale);
		}
		ContextHolder.setCurrentLocale(locale);

		try {
			
			// TODO PRUEBAS LOCAL
			CiudadanoBean ciudadano;
			BuscaConvocatoriasForm formulario = (BuscaConvocatoriasForm) form;
			
			// Si la autenticacion se hace a traves de Clave no valida certificado
			if(formulario.getCertificado()==null && formulario.getCertificadoNuevo()==null){
				// Recuperamos sesion de Clave
				ciudadano = (CiudadanoBean) getSessionAttribute(STRING_USUARIOCLAVE);

				if(ciudadano!=null){
					setSessionAttribute(STRING_USUARIOCLAVE, ciudadano);
				}else{
					throw new Exception();
				}
			}else{
				// Metodo original de validacion del certificado con el miniapplet.
				ciudadano = validarCertificadoCiudadano(formulario);
			}
				
			
			String nif = ciudadano.getNif();

			
		    if (nif != null && llamadaRequest.equals("Inscribirse")) {
				retn = "success";
			}

			// Si ha pulsado modificar, se redirige a ModificarSolicitudUnificado
			String modificar = this.getRequest().getParameter("modificar");
			if(modificar != null && modificar.equals("true")){
				retn = "successModificar";
			}
			
		    if (nif != null && llamadaRequest.equals("Subsanar")) {
				retn = "successSubsanar";
			}
			
		} catch (Exception e) {
			logger.error("Se ha producido un error validando el Certificado Digital",e);
			this.setRequestAttribute("errorNifNoExiste", RESOURCE_BUNDLE.getString(STRING_CERTIFICADOSERRORVALIDACION));
		    retn = "nosuccess";			
		}

		return retn;
	}
	

	/**
	 * Obtener Y validar certificado.
	 *
	 * @param formulario el formulario
	 * @return el string
	 * @throws ApplicationException el application exception
	 */
	protected String obtenerYValidarCertificado(BuscaConvocatoriasForm formulario) throws ApplicationException{
		//cogemos el certificado
		String arrayCertificados=  formulario.getCertificado();
		String arrayCertificadosNuevo=  formulario.getCertificadoNuevo();
		String certificado=null;
		String resultado = null;

		try{
			if(arrayCertificados!=null ){
				logger.debug("Tengo mi certificado y voy a validarlo.");

				certificado=arrayCertificados;
				logger.debug(certificado);
				ParamCert param = new ParamCert();
				Base64Coder coder = new  Base64Coder ();
				byte[] aCoder = coder.decodeBase64(certificado.getBytes());
				certificado = SignDataUtil.getInstance().getCertificateFromSignXML(aCoder);
				param.setCertificado(certificado);
				param.setIdAplicacion(properties.getProperty("webservices.idAplicacion"));
				param.setModovalidacion(Integer.valueOf(properties.getProperty("webservices.modoValidacion")));
				param.setObtenerinfo(true);
				
				logger.debug("Antes de validar el certificado");
				ValidarCertificado validarCertificado = new ValidarCertificado();
				resultado = validarCertificado.validar2(param);
			}else if( arrayCertificadosNuevo!=null){

				logger.debug("Tengo mi certificado y voy a validarlo.");
				certificado=arrayCertificadosNuevo;
				logger.debug(certificado);
				ParamCert param = new ParamCert();
				Base64Coder coder = new  Base64Coder ();
				byte[] aCoder = coder.decodeBase64(certificado.getBytes());
				certificado = SignDataUtil.getInstance().getCertificateFromSignXML(aCoder);

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
			logger.error(RESOURCE_BUNDLE.getString(STRING_CERTIFICADOSERRORVALIDACION));
			logger.error("Error  certificados validacion",e);
			throw new ApplicationException(STRING_CERTIFICADOSERRORVALIDACION);
		}

		return resultado;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#recuperarDatosCertificado(java.lang.String)
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

			}else if(tipoPersona != null && (tipoPersona.equals(Constantes.TIPO_PERSONA_FISICA) 
					|| tipoPersona.equals(Constantes.TIPO_EMPLEADO_PUBLICO))){		
				ciudadano = new CiudadanoBean();

				logger.debug("Certificado de Persona fisica");

				String nif = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"NIFResponsable\"]/valorCampo", XMLDoc);
				String nombre = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"nombreResponsable\"]/valorCampo", XMLDoc);
				String organizacionEmisora = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"OrganizacionEmisora\"]/valorCampo", XMLDoc);
				String numeroSerie = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"numeroSerie\"]/valorCampo", XMLDoc);
				String idEmisor = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"idEmisor\"]/valorCampo", XMLDoc);

				// Evaluación de apellidos si llegan por separado 
				String primerApellido = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"primerApellidoResponsable\"]/valorCampo", XMLDoc);
				String segundoApellido = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"segundoApellidoResponsable\"]/valorCampo", XMLDoc);
	
				// Control segundo apellido vacio
				if(!"".equals(primerApellido)){
					if("".equals(segundoApellido)){
						segundoApellido = " ";
					}
				// Evaluación de apellidos si llegan juntos  (metodo original)
				}else{
					String apellidos = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"ApellidosResponsable\"]/valorCampo", XMLDoc);
					int nom=apellidos.lastIndexOf(" ");
					primerApellido = apellidos.substring(0,nom);
					segundoApellido = apellidos.substring(nom+1);
				}
					
				ciudadano.setNif(nif);
				ciudadano.setNombre(nombre);
				ciudadano.setPrimerApellido(primerApellido);
				ciudadano.setSegundoApellido(segundoApellido);
				ciudadano.setOrganizacionEmisora(organizacionEmisora);
				ciudadano.setIdEmisor(idEmisor);
				
				// Transformacion del numero de serie a hexadecimal para el filtro del miniapplet
				// que permite no volver a solicitar el certificado para las acciones de pago y registro.
				if(null != numeroSerie && StringUtils.isNumeric(numeroSerie)){
					try {
						ciudadano.setNumeroSerie(new BigInteger(numeroSerie).toString(16));
					} catch (Exception e) {
						logger.error("Error obteniendo Numero de serie del certificado. El proceso de inscripcion solicitara el certificado para el pago y el registro.",e);
						
					}
				}else{
					ciudadano.setNumeroSerie(null);
				}
				
				Integer numeroSecuencial=(int)(Math.random()*99999999);
				ciudadano.setAleatorio(numeroSecuencial);

				logger.debug("*****NIF: "+ciudadano.getNif());
				logger.debug("*****NOMBRE: "+ciudadano.getNombre());
				logger.debug("*****PRIMER APELLIDO: "+ciudadano.getPrimerApellido());
				logger.debug("*****SEGUNDO APELLIDO: "+ciudadano.getSegundoApellido());
				logger.debug("*****ORGANIZACION EMISORA: "+ciudadano.getOrganizacionEmisora());
				logger.debug("*****NUMERO DE SERIE ORIGINAL: "+numeroSerie);
				logger.debug("*****NUMERO DE SERIE HEX: "+ciudadano.getNumeroSerie());
				logger.debug("*****ID EMISOR: "+ciudadano.getIdEmisor());

				SHA0 hash = new SHA0();

				if (ciudadano.getNif()!= null || ciudadano.getNif()!= ""){
					String tokenUsu = nif+""+numeroSecuencial;
					byte[] tokenByte = IpsUtils.decodeBase64(tokenUsu);
					String tokenEncrip = hash.getHash(tokenByte);
					ciudadano.setTokenUsuario(tokenEncrip);
				}
			}else{
				// Certificado no válido
				logger.error(RESOURCE_BUNDLE.getString("certificados.error.novalido"));
				throw new ApplicationException("certificados.error.novalido");
			}

		}catch(Exception e){
			logger.error(RESOURCE_BUNDLE.getString(STRING_CERTIFICADOSERRORRECUPERARDATOS));
			logger.error("Error en certificado al recueprar datos.",e);
			throw new ApplicationException(STRING_CERTIFICADOSERRORRECUPERARDATOS);
		}
		
		return ciudadano;
	}

	/**
	 * Validar certificado ciudadano.
	 *
	 * @param formulario el formulario
	 * @return el ciudadano bean
	 * @throws ApplicationException el application exception
	 */
	protected CiudadanoBean validarCertificadoCiudadano(BuscaConvocatoriasForm formulario) throws ApplicationException{

		String resultado = obtenerYValidarCertificado(formulario);
		logger.info("Despues de validar el certificado. Resultado = "+resultado);

		if (resultado!=null){
			CiudadanoBean ciudadanoBean = recuperarDatosCertificado(resultado);
			
			if(ciudadanoBean != null){					
				setSessionAttribute("usuario",ciudadanoBean);
				return ciudadanoBean;
			}else{
				logger.error(RESOURCE_BUNDLE.getString(STRING_CERTIFICADOSERRORRECUPERARDATOS));
				throw new ApplicationException(STRING_CERTIFICADOSERRORRECUPERARDATOS);
			}
		}else{
			logger.error(RESOURCE_BUNDLE.getString(STRING_CERTIFICADOSERRORVALIDACION));
			throw new ApplicationException(STRING_CERTIFICADOSERRORVALIDACION);
		}
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
}
