package es.map.ipsc.spring;

import java.io.Reader;
import java.io.StringReader;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.errors.AuthenticationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriasAbiertasViewQuery;
import es.map.ips.model.ConvocatoriasSubsanarViewQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.AuditoriaBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.exception.RepresentaSoapSuccessException;
import es.map.ipsc.form.BuscaConvocatoriasForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.registroAuditoria.RegistroAuditoriaManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.minhap.seap.representa.Colectivo;
import es.minhap.seap.representa.ColectivoRequest;
import es.minhap.seap.representa.Error;
import es.minhap.seap.representa.Representa;
import es.minhap.seap.representa.RepresentaProxy;
import es.minhap.seap.representa.RepresentaRequest;
import es.minhap.seap.representa.RepresentaResponse;
import es.minhap.seap.representa.TipoColectivoEnum;
import eu.stork.peps.auth.commons.IPersonalAttributeList;
import eu.stork.peps.auth.commons.PEPSUtil;
import eu.stork.peps.auth.commons.PersonalAttribute;
import eu.stork.peps.auth.commons.PersonalAttributeList;
import eu.stork.peps.auth.commons.STORKAuthnRequest;
import eu.stork.peps.auth.commons.STORKAuthnResponse;
import eu.stork.peps.auth.engine.STORKSAMLEngine;
import eu.stork.peps.exceptions.STORKSAMLEngineException;

/**
 * El Class ClaveSpring.
 */
public class ClaveSpring extends AbstractSecureSpring {

	/** La constante SUBSANAR. */
	private static final String SUBSANAR = "Subsanar";
	
	/** La constante PRUEBAS_LOCAL. */
	private static final String PRUEBAS_LOCAL = "true";
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ClaveSpring.class);
	
	/** el registro auditoria manager. */
	private RegistroAuditoriaManager registroAuditoriaManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_CONTACTAR. */
	private static final String STRING_CONTACTAR = "Contactar";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_DESCRIPCIONERRORCLAVE. */
	private static final String STRING_DESCRIPCIONERRORCLAVE = "descripcionErrorClave";
	
	/** La constante STRING_ERRORCONTACTAR. */
	private static final String STRING_ERRORCONTACTAR = "errorContactar";
	
	/** La constante STRING_SAMLRESPONSE. */
	private static final String STRING_SAMLRESPONSE = "SAMLResponse";
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;

	/**
	 * Crea una nueva clave spring.
	 */
	public ClaveSpring(){
		try{
			setRegistroAuditoriaManager((RegistroAuditoriaManager) getBean("registroAuditoriaManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.error("Error en clave.",e);
		}
	}
	

	/**
	 * Do execute.
	 *
	 * @param form el form
	 * @return el string
	 * @throws Exception el exception
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		String llamadaRequest = this.getRequest().getParameter("llamada");
		String convocatoriaRequest = this.getRequest().getParameter("convocatoria");
		String tipoPersonaRequest = this.getRequest().getParameter("tipoPersona");
		
		String retn = "";
		String destination="";
		
		BuscaConvocatoriasForm formulario = new BuscaConvocatoriasForm();

		
		//Para pruebas en local - INI
		if (properties.getProperty("attribute.pruebas.local") != null && properties.getProperty("attribute.pruebas.local").equals(PRUEBAS_LOCAL)) {
			CiudadanoBean ciudadano = new CiudadanoBean();
			ciudadano.setNif("11111111H");
			ciudadano.setNombre("Juan");
			ciudadano.setPrimerApellido("Español");
			ciudadano.setSegundoApellido("Español");
			ciudadano.setTipoAutenticacion("AFIRMA");
			ciudadano.setOrganizacionEmisora("00");
			ciudadano.setNumeroSerie("00");
			ciudadano.setIdEmisor("00");
			ciudadano.setTipoPersona("C");
			setSessionAttribute("usuarioClave",ciudadano);
		}
		//Para pruebas en local - FIN
		
		
		//Comprobamos si cl@ve nos ha enviado la respuesta
		if(this.getRequest().getParameter(STRING_SAMLRESPONSE)!=null && !("").equals(this.getRequest().getParameter(STRING_SAMLRESPONSE))){
			String SAMLResponse = this.getRequest().getParameter(STRING_SAMLRESPONSE);
															
			//recuperamos la dirección de destino (url) de la respuesta de cl@ve
			// si hemos alcanzado este punto es que el tipo de usuario esta validado y es correcto
			destination= respuestaclave(SAMLResponse);
			
			CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
			
			// Vemos si ha pulsado contactar o inscripciones realizadas para redirigirlas
			if((destination.substring(destination.indexOf("=")+1)).equals(STRING_CONTACTAR)){
				retn="successContactar";
			}else if((destination.substring(destination.indexOf("=")+1)).equals("Buscar#inscripciones")||(destination.substring(destination.indexOf("=")+1)).equals("Volver")){
				retn="successBuscar";	
			}else if((destination.contains("Inscribirse") && destination.contains("convocatoria"))||(destination.substring(destination.indexOf("=")+1)).equals("Volver")){
				retn="successInscribirse";	
			} else if((destination.contains(SUBSANAR) && destination.contains("convocatoria"))||(destination.substring(destination.indexOf("=")+1)).equals("Volver")){
				retn="successSubsanar";	
			}else{
				retn= destination;
			}	
		}else{		
			retn = iraClave(llamadaRequest,convocatoriaRequest,tipoPersonaRequest);
		}
		//Para pruebas en local - INI
		if (properties.getProperty("attribute.pruebas.local") != null && properties.getProperty("attribute.pruebas.local").equals(PRUEBAS_LOCAL)) {
			retn = "successBuscar";
		}
		numConvocatorias(formulario);
		//Para pruebas en local - FIN
		 return retn;
	}
		
	/**
	 * Metodo que recupera los parametros de configuracion para
	 * generar la peticion xml.
	 *
	 * @param llamadaRequest el llamada request
	 * @param convocatoriaRequest el convocatoria request
	 * @param tipoPersona el tipo persona
	 * @return String
	 * @throws Exception el exception
	 */
	public  String iraClave(String llamadaRequest, String convocatoriaRequest,String tipoPersona) throws Exception {
		
		String retn=null;
		
		try {	

			//Carga de datos para la llamada a CL@VE
			StringBuilder returnUrl = new StringBuilder();
			if(llamadaRequest.equals("Buscar")) {
				llamadaRequest += "#inscripciones";
			}
			returnUrl.append(properties.getProperty("sp.return")).append("?llamada=").append(llamadaRequest);
			
			//Si se entra a Subsanar
			if (llamadaRequest != null && llamadaRequest.equals(SUBSANAR)) {
				returnUrl.append("&modificar=true");
			}
			
			// Evaluar si procede del enlace de inscribirse y trae id de convocatoria
			if(convocatoriaRequest!=null && !"".equals(convocatoriaRequest)){
				returnUrl.append("&convocatoria="+convocatoriaRequest);
			}
			
			String idpList = properties.getProperty("idp.list");
			
			// la lista de exclusion es la de por defecto para un ciudadano
			String excludedIdP = properties.getProperty("idp.excludedlist");
			
			// si se trata de un funcionario habilitado la lista de exclusion es otra, al habilitarse unicamente el certificado electronico
			if (Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO.equals(tipoPersona)) {
				excludedIdP = properties.getProperty("idp.excludedlist.funcionarioHabilitado");
			}
						
			String allowLegalPerson = properties.getProperty("attribute.allowLegalPerson");
			String qaa = properties.getProperty("sp.qaalevel");						
			String pepsUrl =  properties.getProperty("country.url");
			
			// Generamos el XML para la petición a cl@ve
			String SAMLxmlQaa = generarXMLPeticionClave(qaa, idpList, returnUrl.toString(), pepsUrl);
			
			// Mandamos los atributos que necesitamos para hace el POST a cl@ve
			setRequestAttribute("pepsUrl", pepsUrl);
			setRequestAttribute("SAMLxmlQaa", SAMLxmlQaa);
			setRequestAttribute("excludedIdP",excludedIdP);
			setRequestAttribute("allowLegalPerson",allowLegalPerson);
			
			// seteo el tipo de persona que interactuara en clave
			setSessionAttribute("tipoPersonaSesionClave", tipoPersona);
						
			retn = "success";
			
		} catch (STORKSAMLEngineException e) {
			logger.error("Error ir a clave.",e);
			retn= STRING_NOSUCCESS;
			
		} catch (Exception e) {
			logger.error("Error ir a clave.",e);
			retn= STRING_NOSUCCESS;			
		}			
		
		return retn;
	}

	/**
	 * Método que genera el XML codificado necesario para mandarselo a cl@ve por via POST.
	 *
	 * @param qaa el qaa
	 * @param idpList el idp list
	 * @param returnUrl el return url
	 * @param citizenCountryCode el citizen country code
	 * @return destination
	 * @throws Exception el exception
	 */	

	public static String generarXMLPeticionClave(String qaa,String idpList,String returnUrl, String citizenCountryCode) throws Exception{

		logger.info("[SPUtil] - generarXMLPeticionClave: Inicio");

		String SAMLRequest=null;

		try {
			String spReturn = properties.getProperty("sp.return");
			String spId = properties.getProperty("provider.name");
			String providerName = properties.getProperty("provider.name");
			String spSector = properties.getProperty("sp.sector");
			String spApplication = properties.getProperty("sp.aplication");
			String spQaa= properties.getProperty("sp.qaalevel");
			String pathFileSamlEngine= properties.getProperty("sp.path.samlengine");
			String spDestination = properties.getProperty("country.url");

			if(!"".equals(returnUrl)|| returnUrl!=null){
				spReturn = returnUrl;
				logger.debug("SP_RETURN: " + spReturn);
			}

			if(!"".equals(qaa)|| qaa!=null){
				spQaa = qaa;
				logger.debug("QAA: " + spQaa);
			}
			
			if((Constantes.SP_QAA_PETICION_S).equals(properties.getProperty("sp.qaaPeticion"))){
				if(spReturn.contains("?")){
					spReturn = spReturn +"&qaa="+ spQaa;
				}else{
					spReturn = spReturn +"?qaa="+ spQaa;
				}
			}

			IPersonalAttributeList pAttList = new PersonalAttributeList();

			int numeroAtributosPersonales=0;
			int numeroObligatorioPersonales=0;
			
			if(properties.getProperty("attribute.number")!=null){
				numeroAtributosPersonales = Integer.parseInt(properties.getProperty("attribute.number"));
				logger.debug("PERSONAL_ATTRIBUTE_NUMBER: " + numeroAtributosPersonales);
				if(properties.getProperty("attribute.number.obligatory")!=null){
					numeroObligatorioPersonales = Integer.parseInt(properties.getProperty("attribute.number.obligatory"));
					logger.debug("PERSONAL_OBLIGATORY_NUMBER: " + numeroObligatorioPersonales);
				}
			}

			for (int i=1; i<=numeroAtributosPersonales;i++){
				String value ="";
				PersonalAttribute att = new PersonalAttribute();
				att.setName(properties.getProperty("attribute.name"+i));

				if (att.getName().contains(Constantes.SING_ATTRIBUTE)){
					value = String.format(value, UUID.randomUUID().toString());					
					ArrayList<String> aux = new ArrayList<String>();
					aux.add(value);
					att.setValue(aux);
				}
				
				if(i<=numeroObligatorioPersonales){
					att.setIsRequired(true);
				}
				
				att.setType(Constantes.PERSONAL_TYPE);
				logger.debug("ADDING PERSONAL ATTRIBUTE: " + att.getName() + " WITH VALUE " + att.getValue() + " IS REQUIRED " + att.isRequired());
				pAttList.add(att);
			}

			int numeroAtributosBusiness=0;
			int numeroObligatorioBusiness=0;
			
			if(properties.getProperty("businessAttribute.number")!=null){
				numeroAtributosBusiness = Integer.parseInt(properties.getProperty("businessAttribute.number"));
				if(properties.getProperty("businessAttribute.number.obligatory")!=null){
					numeroObligatorioBusiness = Integer.parseInt(properties.getProperty("businessAttribute.number.obligatory"));
				}
			}

			for (int i = 1; i <= numeroAtributosBusiness; i++) {

				PersonalAttribute att = new PersonalAttribute();
				att.setName(properties.getProperty("businessAttribute.name"+i));
				
				if(i<=numeroObligatorioBusiness){
					att.setIsRequired(true);
				}
				att.setType(Constantes.BUSINESS_TYPE);
				pAttList.add(att);
			}


			int numeroAtributosLegal=0;
			int numeroObligatorioLegal=0;
			
			if(properties.getProperty("legalAttribute.number")!=null){
				numeroAtributosLegal = Integer.parseInt(properties.getProperty("legalAttribute.number"));
				if(properties.getProperty("legalAttribute.number.obligatory")!=null){
					numeroObligatorioLegal = Integer.parseInt(properties.getProperty("legalAttribute.number.obligatory"));
				}
			}

			for (int i = 1; i <= numeroAtributosLegal; i++) {
				PersonalAttribute att = new PersonalAttribute();
				att.setName(properties.getProperty("legalAttribute.name"+i));
				
				if(i<=numeroObligatorioLegal){
					att.setIsRequired(true);
				}
				att.setType(Constantes.LEGAL_TYPE);

				pAttList.add(att);
			}	

			STORKAuthnRequest authnRequest = new STORKAuthnRequest();
			authnRequest.setDestination(spDestination);
			authnRequest.setProviderName(providerName);
			authnRequest.setQaa(Integer.parseInt(spQaa));
			authnRequest.setAssertionConsumerServiceURL(spReturn);
			authnRequest.setPersonalAttributeList(pAttList);
			logger.debug("PERSONAL ATTRIBUTES ADDED: " + pAttList.size());

			if(!"".equals(citizenCountryCode)|| citizenCountryCode!=null){
				authnRequest.setCitizenCountryCode(citizenCountryCode);
			}

			// new parameters
			authnRequest.setSpSector(spSector);
			authnRequest.setSpApplication(spApplication);

			// V-IDP parameters
			authnRequest.setSPID(spId);
			
			STORKSAMLEngine engine = STORKSAMLEngine.getInstance(Constantes.SP_CONF,pathFileSamlEngine);

			authnRequest = engine.generateSTORKAuthnRequest(authnRequest);
new String(authnRequest.getTokenSaml());
			byte[] token = authnRequest.getTokenSaml();
			SAMLRequest = PEPSUtil.encodeSAMLToken(token);

		} catch (STORKSAMLEngineException e) {
			logger.error("Error generarXMLPeticionClave.",e);
			throw e;
		}

		logger.info("[SPUtil] - generarXMLPeticionClave: Fin");

		return SAMLRequest;
	}	

	/**
	 * Método que obtiene los atributos y la dirección de destino de la respuesta proporcionada por Cl@ve.
	 *
	 * @param SAMLResponse el SAML response
	 * @return destination: url de destino tras Cl@ve
	 * @throws AuthenticationException el authentication exception
	 */
	public String respuestaclave(String SAMLResponse) throws AuthenticationException{  	

		CiudadanoBean ciudadano = new CiudadanoBean();
		String retn="";
		String destination="";
		String nombre="", apellido1="",apellido2="",apellidos="",inheritedFamilyName="", id="", email="", qaa="", idp="", certificado="";

		//returnClave y creo el token				
		try {					

			String pathFileSamlEngine = properties.getProperty("sp.path.samlengine");

			// Decodes SAML Response
			byte[] decSamlToken = PEPSUtil.decodeSAMLToken(SAMLResponse);

			// Get SAMLEngine instance
			STORKSAMLEngine engine = STORKSAMLEngine.getInstance(Constantes.SP_CONF,pathFileSamlEngine);
			STORKAuthnResponse authnResponse = null;

			Document d = loadXMLFromString(new String(decSamlToken));
			Node node = d.getFirstChild();
			//Obtenemos la dirección de destino tras la autenticación en cl@ve
			destination = node.getAttributes().getNamedItem("Destination").getNodeValue();
			// validate SAML Token
			authnResponse = engine.validateSTORKAuthnResponse(decSamlToken,(String) this.getRequest().getRemoteHost());

			if(authnResponse!=null){
				if(authnResponse.isFail()){
					throw new Exception(authnResponse.getMessage());	

				}else{	
					if(authnResponse.getAssertions()!=null && authnResponse.getAssertions().size()>0)
						idp = authnResponse.getAssertions().get(0).getIssuer().getValue().toUpperCase();

					//QAA request
					if((Constantes.SP_QAA_PETICION_S).equals(properties.getProperty("sp.qaaPeticion"))){
						qaa = destination.substring(destination.lastIndexOf("=")+1);
					}

					//Get attributes
					IPersonalAttributeList personalAttributeList = authnResponse.getPersonalAttributeList();		
					ArrayList<PersonalAttribute> attrList = new ArrayList<PersonalAttribute>(personalAttributeList.values());

					if(!attrList.isEmpty()){
						for (int i = 0; i < attrList.size(); i++) {
							PersonalAttribute pA = attrList.get(i);

							if (pA.getName().equals(Constantes.ATTR_EIDENTIFIER)) {
								if (!pA.isEmptyValue()) {
									// Tratamiento parametro
									String[] identificador = pA.getValue().get(0).split("\\/");
									if(identificador.length>0){
										id=identificador[identificador.length-1];
									}
								}
							} else if (pA.getName().equals(Constantes.ATTR_SURNAME)) {
								if (!pA.isEmptyValue()) {
									// Tratamiento parametro
									apellidos = pA.getValue().get(0);

								}
							} else if (pA.getName().equals(Constantes.ATTR_INHERITEDFAMILYNAME)) {
								if (!pA.isEmptyValue()) {
									// Tratamiento parametro
									inheritedFamilyName = pA.getValue().get(0);
								}
							} else if (pA.getName().equals(Constantes.ATTR_GIVENNAME)) {
								if (!pA.isEmptyValue()) {
									// Tratamiento parametro
									nombre = (String)pA.getValue().get(0);
								}
							}else if (pA.getName().equals(Constantes.ATTR_EMAIL)) {
								if (!pA.isEmptyValue()) {
									// Tratamiento parametro
									email = (String)pA.getValue().get(0);
								}
							}else if (pA.getName().equals(Constantes.ATTR_AFIRMARESPONSE)) {
								if (!pA.isEmptyValue()) {
									// Tratamiento parametro
									certificado = (String)pA.getValue().get(0);
								}
							}		
						}					
					// Tratamos los apellidos para separarlos

						if(inheritedFamilyName!=null && !inheritedFamilyName.equals("")){

							logger.info("ClaveAuthenticationFilter -attemptAuthentication- Apellidos por inheritedFamilyName");

							int posicion = inheritedFamilyName.length(); 
							apellido1 = apellidos.substring(0, posicion);
							if(apellidos.length() > posicion){
								apellido2 = apellidos.substring(posicion+1);
							}

						}else{

						logger.debug("ClaveAuthenticationFilter -attemptAuthentication- Apellidos por split");
						String[] splitApellidosAux = apellidos.split(" ");
						String[] splitApellidoAdaptado = new String[2];

						String apellidoAux="";
						int i=0;
						
						if(splitApellidosAux.length>2){
							String splitApellidosProp = properties.getProperty("separacion.apellidos.compuestos");

							for (String apellidosSeparados : splitApellidosAux) {
								if(splitApellidosProp.contains(apellidosSeparados)){
									if(apellidoAux.equals("")){
										apellidoAux = apellidoAux + apellidosSeparados;
									}else{
										apellidoAux =  apellidoAux + " " +apellidosSeparados;
									}
								}else{
									if(apellidoAux.equals("")){
										splitApellidoAdaptado[i] = apellidoAux + apellidosSeparados;
									}else{
										splitApellidoAdaptado[i] = apellidoAux + " " + apellidosSeparados;
									}
									apellidoAux="";
									i++;
								}
							}
						}
						if(splitApellidoAdaptado[0]!=null){
							apellido1 = splitApellidoAdaptado [0];
							apellido2 = splitApellidoAdaptado [1];
						}else{

							if(splitApellidosAux [0]!=null && !("").equals(splitApellidosAux [0])){
								apellido1 = splitApellidosAux [0];
							}
							if(splitApellidosAux.length>1 && splitApellidosAux [1]!=null && !("").equals(splitApellidosAux [1])){
								apellido2 = splitApellidosAux [1];	
							}

						}
					  }
					}
				}
			}	
					
			// tipo de persona que viene de clave
			String tipoUsuarioFinal = (String) getSessionAttribute("tipoPersonaSesionClave");
			
			String mensaje = "";	
			if (!tipoUsuarioFinal.equals(Constantes.TIPO_PERSONA_CIUDADANO) && !tipoUsuarioFinal.equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
				// se ha intentado romper la aplicación con una nomenclatura de tipo de persona que no existe
				mensaje = RESOURCE_BUNDLE.getString("certificados.error.usuario.invalido");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,mensaje);
				return STRING_NOSUCCESS;
			} else if (tipoUsuarioFinal.equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
				// validamos que la persona que solicita entrar en clave es un funcionario habilitado
				boolean respuesta = validaFuncionarioHabilitado(id);
				if (!respuesta) {
					return STRING_NOSUCCESS;
				}
			}
			
			// se almacena en sesion el usuario logueado en clave
			ciudadano.setNif(id);
			ciudadano.setNombre(nombre);
			ciudadano.setPrimerApellido(apellido1);
			ciudadano.setSegundoApellido(apellido2);
			ciudadano.setTipoAutenticacion(idp);
			ciudadano.setOrganizacionEmisora("00");
			ciudadano.setNumeroSerie("00");
			ciudadano.setIdEmisor("00");
			ciudadano.setTipoPersona(tipoUsuarioFinal);
			setSessionAttribute("usuarioClave",ciudadano);

			// se registra el acceso en auditoria
			AuditoriaBean auditoriaBean = new AuditoriaBean();
			
			// Obtenemos la IP del usuario y lo guardamos en el Bean
			String ipSolicitante=(String) this.getRequest().getRemoteAddr();
			auditoriaBean.setIpSolicitante(ipSolicitante);
			// Obtenemos los datode del navegador del usuario y lo guardamos en el Bean
			String datosNavegador=(String) this.getRequest().getHeader("User-agent");
			auditoriaBean.setDatosNavegador(datosNavegador);
			logger.info("La IP del solicitante es:" +ipSolicitante);
			logger.info("Los datos del navegador son:" +datosNavegador);
			// Obtenemos la fecha en la cual se ha realizado la autenticación y lo guardamos en el Bean
			Date fechaAutenticacion = new Date();
			auditoriaBean.setFechaAutenticacion(fechaAutenticacion);
			// Guardamos el DNI
			if(id != null){
				auditoriaBean.setNif(id);
			}
			// Guardamos el Nombre
			if(nombre != null){
				auditoriaBean.setNombre(nombre);
			}
			// Guardamos el Primer Apellido
			if(apellido1 != null){
				auditoriaBean.setPrimerApellido(apellido1);
			}
			// Guardamos el Segundo Apellido
			if(apellido2 != null){
				auditoriaBean.setSegundoApellido(apellido2);
			}
			// Guardamos el tipo de usuario final
			if(tipoUsuarioFinal != null){
				auditoriaBean.setTipoPersona(tipoUsuarioFinal);
			}
			// Guardamos la respuesta de Cl@ve
			if(decSamlToken != null){
				auditoriaBean.setRespuestaClave(decSamlToken);
			}
			
			// Guardamos el proveedor de identidad utilizado para la autenticación
			if(idp != null){
				auditoriaBean.setProveedorIdentidad(idp);
				if (Constantes.AUTENTICACION_CERTIFICADOE.equals(idp)) {
					// Extraemos del certificado el serailNumber y los datos del emisor y lo guardamos en el Bean
					extraerInfoCertificado(ciudadano, certificado, auditoriaBean);
				} else {
					auditoriaBean.setNumeroSerie(" ");
					auditoriaBean.setOrganizacionEmisora(" ");
				}
			}
			
			//Guardamos los datos de la autenticación en BBDD
			registroAuditoriaManager.guardarRegistroAuditoria(auditoriaBean);
			
			retn= destination;

		} catch (STORKSAMLEngineException e) {
			logger.error("Error en la respuesta de Cl@ve",e);
			
			if(null != e.getMessage()){
				this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,e.getMessage());
			}else{
				String mensaje = RESOURCE_BUNDLE.getString("certificados.error.clave");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,mensaje);
			}
			
			if((destination.substring(destination.indexOf("=")+1)).equals(STRING_CONTACTAR)){
				setRequestAttribute(STRING_ERRORCONTACTAR , STRING_ERRORCONTACTAR);
				retn= "nosuccessContactar";
			}else
				retn= STRING_NOSUCCESS;

		} catch (Exception e) {
			logger.error("Excepcion en la respuesta de Cl@ve",e);
			
			if(null != e.getMessage()){
				this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,e.getMessage());
			}else{
				String mensaje = RESOURCE_BUNDLE.getString("certificados.error.clave");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,mensaje);
			}
			if((destination.substring(destination.indexOf("=")+1)).equals(STRING_CONTACTAR)){
				setRequestAttribute(STRING_ERRORCONTACTAR , STRING_ERRORCONTACTAR);
				retn= "nosuccessContactar";
			}else
				retn= STRING_NOSUCCESS;
		}
		return retn;
	}

	
	
	/**
	 * Consume el WS Representa, para conocer si el usuario se trata de un F.H o no 
	 *
	 * @param id el id
	 * @return String tipoUsuario - el tipo de usuario
	 */
	private boolean validaFuncionarioHabilitado(String id) {
		boolean respuestaFinal = false;

		// WS REPRESENTA
		
		// se monta peticion
		Representa representa = new RepresentaProxy(properties.getProperty("webservice.representa.comprobarcolectivos"));
		
		RepresentaRequest representaRequest = new RepresentaRequest();
		// monto colectivo RFH
		ColectivoRequest[] colectivos = new ColectivoRequest[1];
		ColectivoRequest colectivoRFH = new ColectivoRequest();
		colectivoRFH.setId_colectivo(TipoColectivoEnum.RFH);
		colectivos[0] = colectivoRFH;
		representaRequest.setNif(id);
		representaRequest.setColectivos(colectivos);
		
		// se realiza peticion
		RepresentaResponse respuestaRepresenta = null;
		String mensaje = "";
		try {
			respuestaRepresenta = representa.comprobarColectivos(representaRequest);
		} catch (RemoteException e) {
			logger.error("Error representa", e);
			
			// respuesta xml
			if (e instanceof RepresentaSoapSuccessException) {
				String codigoRepresenta = ((RepresentaSoapSuccessException) e).getCodigo();
				String descripcionRepresenta = ((RepresentaSoapSuccessException) e).getDescripcion();
				
				// hay codigo de error en la respuesta de representa
				if (codigoRepresenta != null && descripcionRepresenta != null) {
					es.minhap.seap.representa.Error error = new  Error();
					error.setCodigo(codigoRepresenta);
					error.setDescripcion(descripcionRepresenta);
					respuestaRepresenta = new RepresentaResponse();
					respuestaRepresenta.setError(error);
					
				} else {
					// no hay codigo de error en la respueta de representa
					Colectivo colectivoRFHRepresenta = new Colectivo();
					colectivoRFHRepresenta.setId(((RepresentaSoapSuccessException) e).getIdRepresenta());
					colectivoRFHRepresenta.setPertenece(((RepresentaSoapSuccessException) e).getPertenece());
					colectivoRFHRepresenta.setEstado(((RepresentaSoapSuccessException) e).getEstado());
					Colectivo[] colectivosRepresenta = new Colectivo[1];
					colectivosRepresenta[0] = colectivoRFHRepresenta;
					respuestaRepresenta = new RepresentaResponse();
					respuestaRepresenta.setColectivos(colectivosRepresenta);
				}
			} else {
				// fallo en la conexión al WS
				mensaje = RESOURCE_BUNDLE.getString("certificados.error.funcionarioHabilitado.ws");
				this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,mensaje);
				return respuestaFinal;
			}
		}

		if (respuestaRepresenta != null) {
			es.minhap.seap.representa.Error error = respuestaRepresenta.getError();
			Colectivo[] colectivo = respuestaRepresenta.getColectivos();
			// hay error
			if (error!=null) {
				String codigoError = error.getCodigo();
				String descripcionError = error.getDescripcion();
				logger.info("ERROR REPRESENTA CODIGO " + codigoError + " - " + descripcionError);
				mensaje = "ERROR REPRESENTA CODIGO " + codigoError + " - " + descripcionError;
				this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,mensaje);
			// no hay error
			} else if (colectivo!=null && colectivo.length>0) {
				String idRepresenta = colectivo[0].getId();
				Boolean pertenece = colectivo[0].getPertenece();
				String estado = colectivo[0].getEstado();
				if (idRepresenta.equals("RFH") && pertenece.equals(true) && estado.equals("01")) {
					respuestaFinal = true;
				} else {
					// no es valido el F.H aunque exista colectivo
					mensaje = RESOURCE_BUNDLE.getString("certificados.error.funcionarioHabilitado.invalido");
					this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,mensaje);
				}
				
			}
		// la respuesta es null y no se obtiene
		} else {
			mensaje = RESOURCE_BUNDLE.getString("certificados.error.funcionarioHabilitado.respuesta");
			this.getRequest().setAttribute(STRING_DESCRIPCIONERRORCLAVE,mensaje);
		}
		return respuestaFinal;
	}

	
	/**
	 * Obtiene el XML.
	 *
	 * @param xml el xml
	 * @return el document
	 * @throws Exception el exception
	 */
	private static Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		
		return builder.parse(is);
	}
	

	
	/**
	 * Metodo que extrae datos del certificado del ciudadano para agregarselo
	 * a su objeto de sesion. 
	 *
	 * @param ciudadano CiudadanoBean
	 * @param certificado String
	 * @param auditoriaBean el auditoria bean
	 */
	private void extraerInfoCertificado(CiudadanoBean ciudadano, String certificado, AuditoriaBean auditoriaBean) {
		
		try {

			byte[] aCoder = Base64.decodeBase64(certificado);
			
			String xml = new String(aCoder);
			
			// 3. Extraer los datos del xml
			Reader validacionCertificado = new StringReader(xml);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(validacionCertificado));
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			String numeroSerie;
			String idEmisor;
			
			// Código introduccido para evaluar si se ha producido el cambio en el formato del xml de respuesta de AFIRMA
			
			ParametrosConfiguracionBean parametros = parametroConfiguracionManager.obtenerParametrosConfiguracion(Constantes.PARAMETRO_CONFIGURACION_CAMBIO_AFIRMA);
			// Si no hay cambio en el formato del xml, seguimos realizando el mismo procedimiento para obtener el número de serie del certificado e idEmisor
			if(parametros.getValorCampo().equals("0")){
			 numeroSerie = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"numeroSerie\"]/valorCampo", XMLDoc);
			 idEmisor = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"idEmisor\"]/valorCampo", XMLDoc);
			}else{
				numeroSerie = xpath.evaluate("VerifyResponse/OptionalOutputs/ReadableCertificateInfo/ReadableField[FieldIdentity=\"numeroSerie\"]/FieldValue",XMLDoc);	
				idEmisor = xpath.evaluate("VerifyResponse/OptionalOutputs/ReadableCertificateInfo/ReadableField[FieldIdentity=\"idEmisor\"]/FieldValue",XMLDoc);
			}
			// En el caso de tratarse de un DNIe, no se fuerza al miniapplet 
			// a filtrar por serial number, ya que el de Autenticacion 
			// será diferente al de Firma.			
			if(null != idEmisor){
				auditoriaBean.setOrganizacionEmisora(idEmisor);

				if(!idEmisor.contains(Constantes.EMISOR_CERTIFICADO_DNIE)){
					if(null != numeroSerie && StringUtils.isNumeric(numeroSerie) && !StringUtils.isEmpty(numeroSerie)){
						ciudadano.setNumeroSerie(new BigInteger(numeroSerie).toString(16));
						auditoriaBean.setNumeroSerie(new BigInteger(numeroSerie).toString(16));
					}	
				}else{
					logger.info("Acceso con DNIe. No se guarda el serial number.");
					auditoriaBean.setNumeroSerie(" ");
				}
			}
			
		} catch (Exception e) {
			logger.error("Error obteniendo decodificacion del certificado.",e);
		
		}
	}
	
	/**
	 * Num convocatorias.
	 *
	 * @param formulario el formulario
	 */
	private void numConvocatorias(BuscaConvocatoriasForm formulario) {
		 ConvocatoriasSubsanarViewQuery convocatoriaQuerySub = new ConvocatoriasSubsanarViewQuery();
		 ConvocatoriasAbiertasViewQuery convocatoriaQueryMain = new ConvocatoriasAbiertasViewQuery();
		 
		 boolean verTodoSub = formulario.isVerTodoSub();
		 boolean verTodoMain = formulario.isVerTodo();
		 
		 Integer convocatoriasMain = 0;
		 Integer convocatoriasSub = 0;
		 
		 convocatoriasSub = convocatoriasManager.recuperarNumConvocatoriaSubsanadas(convocatoriaQuerySub);
		 convocatoriasMain = convocatoriasManager.recuperarNumConvocatoriaAbiertas(convocatoriaQueryMain);
		 
		 if (verTodoMain && convocatoriasMain != null && convocatoriasMain>0){
				this.setRequestAttribute("verTodo", 0);
				this.setRequestAttribute("numTotalConvocatorias", convocatoriasMain);
			}else{
				this.setRequestAttribute("verTodo", 1);
				this.setRequestAttribute("numTotalConvocatorias", convocatoriasMain);
			}
		 if (verTodoSub && convocatoriasSub != null && convocatoriasSub>0){
				this.setRequestAttribute("verTodoSub", 0);
				this.setRequestAttribute("numTotalConvocatoriasSub", convocatoriasSub);
			}else{
				this.setRequestAttribute("verTodoSub", 1);
				this.setRequestAttribute("numTotalConvocatoriasSub", convocatoriasSub);
			}
	}
	
	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}

	/**
	 * Obtiene el registro auditoria manager.
	 *
	 * @return el registro auditoria manager
	 */
	public RegistroAuditoriaManager getRegistroAuditoriaManager() {
		return registroAuditoriaManager;
	}


	/**
	 * Establece el registro auditoria manager.
	 *
	 * @param registroAuditoriaManager el nuevo registro auditoria manager
	 */
	public void setRegistroAuditoriaManager(
			RegistroAuditoriaManager registroAuditoriaManager) {
		this.registroAuditoriaManager = registroAuditoriaManager;
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



}
