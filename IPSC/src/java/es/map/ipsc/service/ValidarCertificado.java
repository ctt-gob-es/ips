package es.map.ipsc.service;



import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Use;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import es.egoeveris.firma.impl.afirma.model.parametrosValCertificado.ParamCert;
import es.egoeveris.firma.params.VerificacionResult;
import es.egoeveris.firma.params.VerificacionResultItem;
import es.map.ips.common.exception.ApplicationException;
import es.map.ipsc.Constantes;
import es.map.ipsc.exception.IpsException;
import es.map.ipsc.res.ResourceLocator;

/**
 * El Class ValidarCertificado.
 */
public class ValidarCertificado extends AbstractAFirmaClienteWS {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ValidarCertificado.class);

	/** La constante END_POINT_VALIDARCERTIFICADO. */
	//Propiedad que indica el endpoint de ValidarCertificado
	private static final String END_POINT_VALIDARCERTIFICADO= "webservices.ValidarCertificado.endpoint";
	
	/** La constante XML_ENTRADA_VALIDARCERTIFICADO. */
	//Propiedad que indica fichero xml de entrada para el servicio web ValidarCertificado
	private static final String XML_ENTRADA_VALIDARCERTIFICADO= "webservices.ValidarCertificado";
	
		
	/**
	 * Crea una nueva validar certificado.
	 */
	public ValidarCertificado() {
		super();
	}
	
	/**
	 * Validar.
	 *
	 * @param param el param
	 * @return el verificacion result
	 */
	public VerificacionResult validar(ParamCert param) {
		
		VerificacionResult result = null;
	
		try {
			
			String ruta_trusted_cacerts= "C:/truststoreWS.jks";
		   
			System.setProperty("javax.net.ssl.trustStore",ruta_trusted_cacerts);

			//se pasará como argumentos el nombre del servicio web a invocar.
			String endpoint = getProperty(END_POINT_VALIDARCERTIFICADO);
			
		    Service  service = new Service();
		    Call     call    = (Call) service.createCall();		    
		
		    call.setTargetEndpointAddress( new java.net.URL(endpoint) );
		    call.setOperationName(new QName(endpoint, "ValidarCertificado" ));
			
			String message = buildRequestMessage(param);
            logger.info("Mensaje traducido: "+message);
			
			if (logger.isDebugEnabled()) {
				logger.debug("mensaje enviado: " + message);
			}
			
			String strResponse = (String) call.invoke( new Object[] { message } );
			
			if (logger.isDebugEnabled()) {
				logger.debug("mensaje recibido: " + strResponse);
			}
			
			result = parseResponseMessage(param, strResponse);
			logger.info(result);
			logger.info(result.getError());
			
			if(result.getError()){
				throw new ApplicationException("La validación del certificado ha devuelto error.");
			}
			
		} catch (Exception e) {
			logger.error("Error No se pudo validar el certificado", e);
			throw new ApplicationException("No se pudo validar el certificado", e);
		}
		
		
		return result;
	}
	
	
	/**
	 * Validar 2.
	 *
	 * @param param el param
	 * @return el string
	 */
	public String validar2(ParamCert param) {
		
		VerificacionResult result = null;
		String strResponse = "";
		ClientHandler handler = null;
		Date date1 = new Date();
		Date date2 = null;
		
		try {
			logger.info("entro a validar2");
			//se pasará como argumentos el nombre del servicio web a invocar.
			String endpoint = getProperty(END_POINT_VALIDARCERTIFICADO);
			
			handler = new ClientHandler(prop);

		    Service service = new Service();
		    Call call = (Call) service.createCall();
		    call.setTargetEndpointAddress( new java.net.URL(endpoint) );
		    call.setOperationName(new QName(endpoint, "ValidarCertificado" ));
		    call.setOperationUse(Use.LITERAL);
		    call.setClientHandlers(handler, null);
			
			String message = buildRequestMessage(param);
			
			if (logger.isDebugEnabled()) {
				logger.debug("mensaje enviado: " + message);
			}
			
			long inicio = System.currentTimeMillis();

			logger.debug("call    -> " + call);
			logger.debug("message -> " + message);
			strResponse = (String) call.invoke( new Object[] { message } );
			
			long fin = System.currentTimeMillis();
			long tiempoRespuesta = fin-inicio;
			tiempoRespuesta = tiempoRespuesta * 1000;

			if (logger.isDebugEnabled()) {
				logger.debug("mensaje recibido: " + strResponse);			
			}
			
			result = parseResponseMessage(param, strResponse);
			logger.info("Result Descripcion: "+result.getDescripcion());
			logger.info("Result Error: "+result.getError());
			
			if (FirmaConstantes.CERT_ESTADO_CADUCADO.equals(result.getDescripcion())){
				throw new IpsException("Certificado Caducado","Certificado Caducado","El certificado con el cual intenta logarse en la aplicación está caducado.");
			}
			if(result.getError()){
				throw new IpsException("Certificado Revocado","Certificado Revocado","El certificado con el cual intenta logarse en la aplicación ha sido revocado.");
			}
			
		} catch (IpsException a) {
			logger.info("error validar: ",a);
			throw a;
		}
		catch (Exception e) {
			logger.error("error - No se pudo validar el certificado: ",e);
			throw new ApplicationException("No se pudo validar el certificado", e);
		}finally{
			date2 = new Date();
			logger.warn("Fin Llamada @firma end: "+(date2.getTime()-date1.getTime()));
			date1=null;
			date2=null;
		}
		
		logger.debug("salgo de validar el certificado");
		return strResponse;
	}
	
	
	
	/**
	 * Construye el request message.
	 *
	 * @param param el param
	 * @return el string
	 */
	private String buildRequestMessage(ParamCert param) {
		
		String strMessageTemplate = getMessageTemplate();
		String linea = System.getProperty("line.separator");
		param.setCertificado(param.getCertificado().replaceAll(linea, ""));
		
		//Reemplazando en la plantilla los datos de entrada
		strMessageTemplate = strMessageTemplate.replace("${certificado}", param.getCertificado());
		strMessageTemplate = strMessageTemplate.replace("${idAplicacion}", param.getIdAplicacion());
		strMessageTemplate = strMessageTemplate.replace("${modoValidacion}", "" + param.getModovalidacion());
		strMessageTemplate = strMessageTemplate.replace("${obtenerInfo}", "" + param.getObtenerinfo());

		return strMessageTemplate;
	}
		
	/**
	 * Obtiene el message template.
	 *
	 * @return el message template
	 */
	private String getMessageTemplate() {
		
		String strMessageTemplate = null;
		
		try {
		    
		   	String rutaMessageTemplate = getProperty(ValidarCertificado.XML_ENTRADA_VALIDARCERTIFICADO);
		   	
		   	//Contiene la pantilla del mensaje
		   	InputStream messageTemplate = ResourceLocator.getInstance().getWsMessageTemplateAsStream(rutaMessageTemplate);

		   	//Extrayendo contenido de la plantilla
		   	ByteArrayOutputStream bos = new ByteArrayOutputStream();
		   	
			byte[] buffer = new byte[1024];
			int len;
			while ((len = messageTemplate.read(buffer, 0, 1024)) > -1) {
				bos.write(buffer, 0, len);
			}

			bos.flush();
			
			byte[] docBytes = bos.toByteArray();
		   	
			strMessageTemplate = new String(docBytes, "UTF-8");			
		} catch (Exception e) {
			logger.error("error - No se pudo obtener plantilla de mensaje de entrada: ",e);
			throw new ApplicationException("No se pudo obtener plantilla de mensaje de entrada", e);
		}
		
		return strMessageTemplate;
		
	}
	
	/**
	 * Analiza el response message.
	 *
	 * @param param el param
	 * @param strResponse el str response
	 * @return el verificacion result
	 */
	private VerificacionResult parseResponseMessage(ParamCert param, String strResponse) {

		VerificacionResult verResult = new VerificacionResult();
		
		try {
			Reader validacionCertificado = new StringReader(strResponse);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(validacionCertificado));
			
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			String certificadoOK = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/ResultadoValidacion/descripcion",XMLDoc);
			logger.debug("certificadoOK:"+certificadoOK);
			if (certificadoOK.equals(FirmaConstantes.CERT_ESTADO_OK)) {
				String estado = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/ResultadoValidacion/ValidacionSimple/descResultado",XMLDoc);
				verResult.setError(false);
				verResult.setDescripcion(certificadoOK);
				verResult.setEstadoVerifica(estado);
	
			} else if (certificadoOK.equals(FirmaConstantes.CERT_ESTADO_CADUCADO)){
				String error = xpath.evaluate("/mensajeSalida/respuesta/Excepcion/descripcion", XMLDoc);
				verResult.setError(true);
				verResult.setDescripcion(FirmaConstantes.CERT_ESTADO_CADUCADO);
				logger.error("Respuesta Error Certificado:"+error);
			}
			else{
				String error = xpath.evaluate("/mensajeSalida/respuesta/Excepcion/descripcion", XMLDoc);
				verResult.setError(true);
				verResult.setDescripcion(error);
				logger.error("Respuesta Error Certificado:"+error);
			}
			
			if (param.getObtenerinfo() && !verResult.getError()) {
				VerificacionResultItem item = new VerificacionResultItem();
				item.setFullName(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"ApellidosResponsable\"]/valorCampo",XMLDoc));
				item.setIssuer(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"idEmisor\"]/valorCampo", XMLDoc));
				item.setSubject(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"subject\"]/valorCampo",XMLDoc));
				item.setSerialNumber(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"numeroSerie\"]/valorCampo",XMLDoc));
				
				//INICIO MODIFICACIÓN - csuareza - 03/12/2009
				//Cambio del mapeo de datos de persona jurídica y física
				String tipoPersona = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"clasificacion\"]/valorCampo", XMLDoc);
				
				if(tipoPersona != null && tipoPersona.equals(Constantes.TIPO_PERSONA_JURIDICA)){
					item.setRazonSocial(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"razonSocial\"]/valorCampo", XMLDoc));
					item.setNumDocumentacion(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"NIF-CIF\"]/valorCampo", XMLDoc));
				}
				else if(tipoPersona != null && (tipoPersona.equals(Constantes.TIPO_PERSONA_FISICA) 
						|| tipoPersona.equals(Constantes.TIPO_EMPLEADO_PUBLICO))){
					item.setNombre(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"nombreResponsable\"]/valorCampo",XMLDoc));
					item.setApellidos(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"ApellidosResponsable\"]/valorCampo",XMLDoc));
					item.setNumDocumentacion(xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"NIFResponsable\"]/valorCampo", XMLDoc));
				}

				//FIN MODIFICACIÓN - csuareza - 03/12/2009
				
				verResult.setResultadoVerificacion(new VerificacionResultItem[] { item });
			}
		} catch (Exception e) {
			logger.error("error - No se pudo parsear el mensaje de salida: ",e);
			throw new ApplicationException("No se pudo parsear el mensaje de salida", e);
		}
		
		
		return verResult;		
	}
	
	
	
}
