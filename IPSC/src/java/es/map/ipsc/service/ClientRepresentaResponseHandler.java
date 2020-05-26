package es.map.ipsc.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.SOAPPart;
import org.apache.axis.handlers.BasicHandler;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.WSSecurityEngine;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.message.WSSignEnvelope;
import org.apache.xml.security.c14n.Canonicalizer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ipsc.exception.RepresentaSoapSuccessException;

/**
 * El Class ClientRepresentaResponseHandler.
 */
public class ClientRepresentaResponseHandler extends BasicHandler implements CallbackHandler {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ClientRepresentaResponseHandler.class);
	
	/** La constante STRING_ERROR_CLIENTREPRESENTARESPONSEHANDLE. */
	private static final String STRING_ERROR_CLIENTREPRESENTARESPONSEHANDLE = "Error ClientRepresentaResponseHandler";
	
	/** La constante STRING_XMLNS. */
	private static final String STRING_XMLNS = "xmlns";
	
	/** el props. */
	Properties props = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
	
	/** el keystore cert alias. */
	private String keystoreCertAlias;
	
	/** el keystore password. */
	private String keystorePassword;
	
	/** el keystore cert password. */
	private String keystoreCertPassword;
	
	/** el keystore. */
	private KeyStore keystore;
	
	/** el keystore type. */
	private String keystoreType;
	
	/** el keystore location. */
	private String keystoreLocation;

	/**
	 * Crea una nueva client representa response handler.
	 */
	public ClientRepresentaResponseHandler(){
		
		keystoreCertAlias = props.getProperty("security.keystore.cert.alias");
		keystorePassword = props.getProperty("security.keystore.password");
		keystoreCertPassword = props.getProperty("security.keystore.cert.password");
		keystoreType = props.getProperty("security.keystore.type");
		keystoreLocation = props.getProperty("security.keystore.location");
		
		try (FileInputStream fileKS = new FileInputStream(props.getProperty("security.keystore.location"))
		){			
			keystore = KeyStore.getInstance(props.getProperty("security.keystore.type"));
			keystore.load(fileKS, keystorePassword.toCharArray());
		} catch (KeyStoreException e) {
			 logger.error(STRING_ERROR_CLIENTREPRESENTARESPONSEHANDLE, e);
		} catch (FileNotFoundException e) {
			 logger.error(STRING_ERROR_CLIENTREPRESENTARESPONSEHANDLE, e);
		} catch (NoSuchAlgorithmException e) {
			 logger.error(STRING_ERROR_CLIENTREPRESENTARESPONSEHANDLE, e);
		} catch (CertificateException e) {
			 logger.error(STRING_ERROR_CLIENTREPRESENTARESPONSEHANDLE, e);
		} catch (IOException e) {
			 logger.error(STRING_ERROR_CLIENTREPRESENTARESPONSEHANDLE, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.axis.Handler#invoke(org.apache.axis.MessageContext)
	 */
	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {

		SOAPMessage msg;
		Document doc = null;
		Document result = null;
		
		try {
			msg = messageContext.getCurrentMessage();
			doc = ((org.apache.axis.message.SOAPEnvelope) msg.getSOAPPart().getEnvelope()).getAsDocument();			
 			printDocument(doc,System.out);
 			
 			WSSecurityEngine secEngine = new WSSecurityEngine();
 	        WSSignEnvelope signer = new WSSignEnvelope();
 	        signer.setUserInfo(keystoreCertAlias, keystoreCertPassword);
 	        Crypto crypto = CryptoFactory.getInstance("org.apache.ws.security.components.crypto.Merlin", this.initializateCryptoProperties());
 			
 			Vector v = secEngine.processSecurityHeader(doc, null, this, crypto);
 			if (v == null) {
 	            throw new Exception("Acceso no concedido.");
 	        }
 			 			
 			// eliminar cabecera
 			Element cabecera = (Element) doc.getElementsByTagName("soap:Header").item(0);
 			cabecera.getParentNode().removeChild(cabecera);
 			printDocument(doc,System.out);
 			
 			SOAPMessage mensaje =  toSOAPMessage(doc);
 			((SOAPPart) messageContext.getCurrentMessage().getSOAPPart()).setCurrentMessage(toString(mensaje), SOAPPart.FORM_STRING);
 			
 			// pasar respuesta decodificada a String
 			String respuestaDecodificada = toString(updateSOAPMessage(doc, msg));
 			// almacenar en filesystem la respuesta
			almacenaRespuestaRepresenta(respuestaDecodificada);
			
			// seteo resultado en excepcion
			result = ((org.apache.axis.message.SOAPEnvelope) msg.getSOAPPart().getEnvelope()).getAsDocument();

		} catch (SOAPException e) {
			 logger.error("Error invoke", e);
		} catch (Exception e) {
			 logger.error("Error invoke", e);
		}
		
		if(result != null){
			RepresentaSoapSuccessException exp = new RepresentaSoapSuccessException();
			String id = null;
			Boolean pertenece = null;
			String estado = null;
			String codigo = null;
			String descripcion = null;
			
			// no hay errores
			if (doc.getElementsByTagName("error").item(0)!=null) {
				codigo = doc.getElementsByTagName("codigo").item(0).getChildNodes().item(0).getNodeValue();	
				descripcion = doc.getElementsByTagName("descripcion").item(0).getChildNodes().item(0).getNodeValue();
			} else {
				// si hay errores
				id = doc.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue();
				pertenece =  Boolean.valueOf(doc.getElementsByTagName("pertenece").item(0).getChildNodes().item(0).getNodeValue());
				if(pertenece){
					estado = doc.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
				}
			}
			exp.setIdRepresenta(id);
			exp.setPertenece(pertenece);
			exp.setEstado(estado);
			exp.setCodigo(codigo);
			exp.setDescripcion(descripcion);
			throw exp;
		}
	}
	
	/**
	 * Almacena en el filesystem el resultado de la respuesta del WS Representa.
	 *
	 * @param respuesta el respuesta
	 */
	private void almacenaRespuestaRepresenta(String respuesta) {
		final String separador = File.separator;
		String urlFinal =  props.getProperty("sistemaficheros.url.final");
		Calendar cal = Calendar.getInstance();
		int mes = cal.get(Calendar.MONTH)+1;
		 
		// calculo de ubicacion
	    StringBuilder rutaDocumento = new StringBuilder();
		rutaDocumento
			.append(cal.get(Calendar.YEAR)).append(separador)
			.append(mes).append(separador)
			.append(cal.get(Calendar.DAY_OF_MONTH)).append(separador)
			.append(cal.get(Calendar.HOUR_OF_DAY)).append(separador);
		
		String rutaXml = urlFinal + rutaDocumento.toString();
		
		// directorio almacenamiento
		File directorioXml = new File(rutaXml);
		if (!directorioXml.exists()) {
			directorioXml.mkdirs();
		}
   	
		// calculo nombre fichero
		String nombreXml = "respuesta_representa_0.xml";
		File salida = new File(directorioXml.getPath() + separador + nombreXml);
		int i = 0;
		while (salida.exists()) {
			i++;
			nombreXml = "respuesta_representa_"+ i +".xml";
			salida =  new File(directorioXml.getPath() + separador + nombreXml);
		}
   	
	   	// creacion del contenido de fichero y almacenamiento	   	
	   	String textoDocumento = respuesta;
	   	try (OutputStream outputStream = new FileOutputStream (salida)){
	   		
	      	byte[] documento = textoDocumento.getBytes();
	      		
			outputStream.write(documento);
			outputStream.flush();
			outputStream.close();
			    
			logger.info("Respuesta almacenada");
	
		} catch (IOException e) {
			logger.info("error en el fichero", e);
		} 
	}
	
	/**
	 * Elinina el atributo xmlns.
	 *
	 * @param elemento el elemento
	 */
	public void eliminarXMLNSVacios(Element elemento){
		NodeList lista = elemento.getChildNodes();
		
		for(int i=0; i<lista.getLength(); i++){
			Node nodo = lista.item(i);
			if(nodo instanceof Element){
				Element elementoHijo = (Element) nodo;
				if(elementoHijo.getAttribute(STRING_XMLNS) != null && elementoHijo.getAttribute(STRING_XMLNS).equals("")){
					elementoHijo.removeAttribute(STRING_XMLNS);
				}
				eliminarXMLNSVacios(elementoHijo);
			}
		}
	}
	
	/**
	 * Imprime por pantalla.
	 *
	 * @param doc el doc
	 * @param out el out
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 * @throws TransformerException el transformer exception
	 */
	public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	    transformer.transform(new DOMSource(doc), 
	         new StreamResult(new OutputStreamWriter(out, "ISO-8859-1")));
	}
	
	/**
	 * Transforma a documento.
	 *
	 * @param soapMsg el soap msg
	 * @return el document
	 * @throws SOAPException el SOAP exception
	 * @throws TransformerException el transformer exception
	 */	
	public static Document toDocument(SOAPMessage soapMsg)  throws SOAPException, TransformerException {

        Source src = soapMsg.getSOAPPart().getContent();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMResult result = new DOMResult();

        transformer.transform(src, result);
        return (Document) result.getNode();

    }
	
	/**
	 * Transforma documento xml en mensaje soap.
	 *
	 * @param doc el doc
	 * @param message el message
	 * @return el SOAP message
	 * @throws SOAPException el SOAP exception
	 */
	private static SOAPMessage updateSOAPMessage(Document doc, SOAPMessage message) throws SOAPException {
        DOMSource domSource = new DOMSource(doc);
        message.getSOAPPart().setContent(domSource);
        return message;
    }
	
	/**
	 * Convert a DOM Document into a soap message. <p/>
	 *
	 * @param doc el doc
	 * @return el SOAP message
	 * @throws Exception el exception
	 */
	public static SOAPMessage toSOAPMessage(Document doc) throws Exception {
		Canonicalizer c14n = Canonicalizer
				.getInstance(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS);
		byte[] canonicalMessage = c14n.canonicalizeSubtree(doc);
		ByteArrayInputStream in = new ByteArrayInputStream(canonicalMessage);
		MessageFactory factory = new org.apache.axis.soap.MessageFactoryImpl();
		return factory.createMessage(null, in);
	}

	/**
	 * Callback que recupera el password del certificado.
	 *
	 * @param callbacks el callbacks
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 * @throws UnsupportedCallbackException el unsupported callback exception
	 */
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		String password;
        for (Callback cb : callbacks) {
            if (cb instanceof WSPasswordCallback) {
                 WSPasswordCallback pc = (WSPasswordCallback) cb;
                try {
                    password = this.keystoreCertPassword;
                } catch (Exception e) {
                	 logger.error("Error fallo recuperando la clave en las properties", e);
                    throw new UnsupportedCallbackException(pc,"fallo recuperando la clave en las properties");
                }
                if (pc.getIdentifer() != null) {
                    pc.setPassword(password);
                }
           }
        }
	}
	
	/**
	    * Establece el conjunto de propiedades con el que seria inicializado el gestor criptografico de WSS4J.
	    * @return Devuelve el conjunto de propiedades con el que seria inicializado el gestor criptografico de WSS4J.
	    */
	   private Properties initializateCryptoProperties() {
	      Properties res = new Properties();
	      res.setProperty("org.apache.ws.security.crypto.provider", "org.apache.ws.security.components.crypto.Merlin");
	      res.setProperty("org.apache.ws.security.crypto.merlin.keystore.password", this.keystorePassword);
	      res.setProperty("org.apache.ws.security.crypto.merlin.keystore.alias", this.keystoreCertAlias);
	      res.setProperty("org.apache.ws.security.crypto.merlin.alias.password", this.keystoreCertPassword);
	      res.setProperty("org.apache.ws.security.crypto.merlin.file", this.keystoreLocation);
	      res.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", this.keystoreType);
	      return res;
	   }
	   
	   /**
   	 * Transformar mensaje soap a String.
   	 *
   	 * @param soapMsg el soap msg
   	 * @return el string
   	 * @throws Exception el exception
   	 */
	   public static String toString(SOAPMessage soapMsg) throws Exception {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			soapMsg.writeTo(out);
			String soapMsgStr = new String(out.toByteArray());
			return soapMsgStr;
		}

}
