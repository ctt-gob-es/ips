package es.map.ipsc.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.InvalidParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.SOAPPart;
import org.apache.axis.handlers.BasicHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.ws.security.WSConstants;
import org.apache.ws.security.components.crypto.CredentialException;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecSignature;
import org.apache.ws.security.message.WSSecTimestamp;
import org.apache.ws.security.message.WSSecUsernameToken;
import org.apache.xml.security.c14n.Canonicalizer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.map.ips.common.spring.ApplicationContextProvider;

/**
 * El Class ClientRepresentaRequestHandler.
 */
public class ClientRepresentaRequestHandler extends BasicHandler {
	
	/** el logger. */
	private static Log logger = LogFactory.getLog(ClientRepresentaRequestHandler.class);
   
   /**  Opciï¿½n de seguridad UserNameToken. */
   public static final String USERNAMEOPTION = WSConstants.USERNAME_TOKEN_LN;
   
   /**  Opciï¿½n de seguridad BinarySecurityToken. */
   public static final String CERTIFICATEOPTION = WSConstants.BINARY_TOKEN_LN;
   
   /** La constante STRING_XMLNS. */
   private static final String STRING_XMLNS = "xmlns";
   
   /**  Sin seguridad. */
   public static final String NONEOPTION = "none";
   
   /** La constante DIGESTPASS. */
   public static final String DIGESTPASS = "DIGEST";
   
   /** La constante TEXTPASS. */
   public static final String TEXTPASS = "TEXT";
   
   /** La constante serialVersionUID. */
   private static final long serialVersionUID = 1L;
   // Opciones de seguridad
   /** el security option. */
   // Opcion de seguridad del objeto actual
   private String securityOption = null;
   
   /** el username token name. */
   // Usuario para el token de seguridad UserNameToken.
   private String usernameTokenName = null;
   
   /** el username token password. */
   // Password para el token de seguridad UserNameToken
   private String usernameTokenPassword = null;
   
   /** el username token password type. */
   // Tipo de password para el UserNameTokenPassword
   private String usernameTokenPasswordType = null;
   
   /** el keystore location. */
   // Localizacion del keystore con certificado y clave privada de usuario
   private String keystoreLocation = null;
   
   /** el keystore type. */
   // Tipo de keystore
   private String keystoreType = null;
   
   /** el keystore password. */
   // Clave del keystore
   private String keystorePassword = null;
   
   /** el keystore cert alias. */
   // Alias del certificado usado para firmar el tag soapBody de la peticion y que seria alojado en el token BinarySecurityToken
   private String keystoreCertAlias = null;
   
   /** el keystore cert password. */
   // Password del certificado usado para firmar el tag soapBody de la peticion y que seria alojado en el token BinarySecurityToken
   private String keystoreCertPassword = null; 

   /**
    * Inicializa el atributo securityOption.
    *
    * @throws AxisFault el axis fault
    */
   public ClientRepresentaRequestHandler() throws AxisFault {
	   Properties config = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
   
	   try {
		   securityOption = config.getProperty("security.mode").toUpperCase();
		   keystoreLocation = config.getProperty("security.keystore.location");
		   keystoreType = config.getProperty("security.keystore.type");
		   keystorePassword = config.getProperty("security.keystore.password");
		   keystoreCertAlias = config.getProperty("security.keystore.cert.alias");
		   keystoreCertPassword = config.getProperty("security.keystore.cert.password");
	   } catch (Exception e) {
		   logger.error("Error leyendo el fichero de configuracion de securizaciï¿½n", e);
		   System.exit(-1);
      }
      if (!securityOption.equals(USERNAMEOPTION.toUpperCase()) && !securityOption.equals(CERTIFICATEOPTION.toUpperCase()) && !securityOption.equals(NONEOPTION.toUpperCase())) {
         logger.error("Opcion de seguridad no valida: " + securityOption);
         AxisFault.makeFault(new InvalidParameterException("Opcion de seguridad no valida: " + securityOption));
      }
   }

   /* (non-Javadoc)
    * @see org.apache.axis.Handler#invoke(org.apache.axis.MessageContext)
    */
   public void invoke(MessageContext msgContext) throws AxisFault {
      SOAPMessage msg,secMsg;
      Document doc = null;
      secMsg = null;
     
      try {
         //Obtencion del documento XML que representa la peticion SOAP
         msg = msgContext.getCurrentMessage();
         doc = ((org.apache.axis.message.SOAPEnvelope) msg.getSOAPPart().getEnvelope()).getAsDocument();
         eliminarXMLNSVacios(doc.getDocumentElement());
         doc = ((org.apache.axis.message.SOAPEnvelope)toAxisMessage(doc).getSOAPPart().getEnvelope()).getAsDocument();
         //Securizacion de la peticion SOAP segun la opcion de seguridad configurada
         if (this.securityOption.equals(USERNAMEOPTION.toUpperCase())) {
            secMsg = this.createUserNameToken(doc);
            logger.info("ClientHandler - invoke. secMsg=" + secMsg);
         } else if (this.securityOption.equals(CERTIFICATEOPTION.toUpperCase())) {
        	secMsg = this.createBinarySecurityToken(doc, msgContext);
         } else {
            secMsg = msgContext.getMessage();
         }

         if (!this.securityOption.equals(NONEOPTION.toUpperCase())) {
            // Modificacion de la peticion SOAP
        	System.out.println(toString(secMsg));
            ((SOAPPart) msgContext.getRequestMessage().getSOAPPart()).setCurrentMessage(toString(secMsg), SOAPPart.FORM_STRING);
         }
      } catch (Exception e) {
    	  logger.error("ClientHandler - invoke. Se ha producido un error -> ",e);
       
         AxisFault.makeFault(e);
      }
   }

   /**
    * Securiza, mediante el tag userNameToken, una peticiï¿½n SOAP no securizada.
    *
    * @param soapEnvelopeRequest el soap envelope request
    * @return Un mensaje SOAP que contiene la peticiï¿½n SOAP de entrada securizada
    * mediante el tag userNameToken.
    * @throws TransformerConfigurationException el transformer configuration exception
    * @throws TransformerException el transformer exception
    * @throws TransformerFactoryConfigurationError el transformer factory configuration error
    * @throws IOException Señala que se ha producido una excepción de I/O.
    * @throws SOAPException el SOAP exception
    */
   private SOAPMessage createUserNameToken(Document soapEnvelopeRequest) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError, IOException, SOAPException {
      ByteArrayOutputStream baos;
      Document secSOAPReqDoc;
      DOMSource source;
      Element element;
      SOAPMessage res;
      StreamResult streamResult;
      String secSOAPReq;
      WSSecUsernameToken wsSecUsernameToken;
      WSSecHeader wsSecHeader;
      
      logger.info("INICIO createUserNameToken...");
      // Insercion del tag wsse:Security y userNameToken
      wsSecHeader = new WSSecHeader(null, false);
      wsSecUsernameToken = new WSSecUsernameToken();
      if (TEXTPASS.equalsIgnoreCase(usernameTokenPasswordType)) {
         wsSecUsernameToken.setPasswordType(WSConstants.PASSWORD_TEXT);
      } else if (DIGESTPASS.equalsIgnoreCase(usernameTokenPasswordType)) {
         wsSecUsernameToken.setPasswordType(WSConstants.PASSWORD_DIGEST);
      } else {
         System.err.println("Tipo de password no valido: " + usernameTokenPasswordType);
         throw new SOAPException("No se ha especificado un tipo de password valido");
      }
      wsSecUsernameToken.setUserInfo(this.usernameTokenName, this.usernameTokenPassword);
      wsSecHeader.insertSecurityHeader(soapEnvelopeRequest);
      wsSecUsernameToken.prepare(soapEnvelopeRequest);
      // Añadimos una marca de tiempo inidicando la fecha de creacion del tag
      wsSecUsernameToken.addCreated();
      wsSecUsernameToken.addNonce();
      // Modificacion de la peticion
      secSOAPReqDoc = wsSecUsernameToken.build(soapEnvelopeRequest, wsSecHeader);
      element = secSOAPReqDoc.getDocumentElement();
      // Transformacion del elemento DOM a String
      source = new DOMSource(element);
      baos = new ByteArrayOutputStream();
      streamResult = new StreamResult(baos);
      TransformerFactory.newInstance().newTransformer().transform(source, streamResult);
      secSOAPReq = new String(baos.toByteArray());
      // Creacion de un nuevo mensaje SOAP a partir del mensaje SOAP securizado formado
      MessageFactory mf = new org.apache.axis.soap.MessageFactoryImpl();
      res = mf.createMessage(null, new ByteArrayInputStream(secSOAPReq.getBytes()));
      logger.info("FIN createUserNameToken. res=" + res);
      return res;
   }

   /**
    * Securiza, mediante el tag BinarySecurityToken y firma, una peticion SOAP no securizada.
    *
    * @param soapEnvelopeRequest Documento xml que representa la peticion SOAP sin securizar.
    * @param msgContext el msg context
    * @return Un mensaje SOAP que contiene la peticion SOAP de entrada securizada
    * mediante el tag BinarySecurityToken.
    * @throws TransformerConfigurationException el transformer configuration exception
    * @throws TransformerException el transformer exception
    * @throws TransformerFactoryConfigurationError el transformer factory configuration error
    * @throws IOException Señala que se ha producido una excepción de I/O.
    * @throws SOAPException el SOAP exception
    * @throws KeyStoreException el key store exception
    * @throws CredentialException el credential exception
    * @throws NoSuchAlgorithmException el no such algorithm exception
    * @throws CertificateException el certificate exception
    */
   private SOAPMessage createBinarySecurityToken(Document soapEnvelopeRequest, MessageContext msgContext ) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError, IOException, SOAPException, KeyStoreException, CredentialException, NoSuchAlgorithmException, CertificateException {
	  eliminarXMLNSVacios(soapEnvelopeRequest.getDocumentElement());
      Crypto crypto;
      Document secSOAPReqDoc;
      WSSecSignature wsSecSignature;
      WSSecHeader wsSecHeader;
      crypto = null;
      wsSecHeader = null;
      wsSecSignature = null;
      
      
      // Insercion del tag wsse:Security y BinarySecurityToken
      wsSecHeader = new WSSecHeader(null, false);
      wsSecSignature = new WSSecSignature();
      crypto = CryptoFactory.getInstance("org.apache.ws.security.components.crypto.Merlin", this.initializateCryptoProperties());
      // Indicacion para que inserte el tag BinarySecurityToken
      wsSecSignature.setKeyIdentifierType(WSConstants.BST_DIRECT_REFERENCE);
      wsSecSignature.setUserInfo(this.keystoreCertAlias, this.keystoreCertPassword);
      wsSecHeader.insertSecurityHeader(soapEnvelopeRequest);
            
      // Modificacion y firma de la peticion
      secSOAPReqDoc = wsSecSignature.build(soapEnvelopeRequest, crypto, wsSecHeader);
     
      WSSecTimestamp timestamp = new WSSecTimestamp();
      timestamp.setTimeToLive(3600);
      secSOAPReqDoc = timestamp.build(secSOAPReqDoc, wsSecHeader);
      
	  SOAPMessage soapm = null;
      try{
          soapm = toSOAPMessage(secSOAPReqDoc);
      }catch (Exception e) {
    	  logger.error("Error createBinarySecurityToken", e);
      }
      
      return soapm;
   }

   /**
    * Establece el conjunto de propiedades con el que seria inicializado el gestor criptografico de WSS4J.
    * @return Devuelve el conjunto de propiedades con el que seria inicializado el gestor criptografico de WSS4J.
    */
   private Properties initializateCryptoProperties() {
      Properties res = new Properties();
      res.setProperty("org.apache.ws.security.crypto.provider", "org.apache.ws.security.components.crypto.Merlin");
      res.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", this.keystoreType);
      res.setProperty("org.apache.ws.security.crypto.merlin.keystore.password", this.keystorePassword);
      res.setProperty("org.apache.ws.security.crypto.merlin.keystore.alias", this.keystoreCertAlias);
      res.setProperty("org.apache.ws.security.crypto.merlin.alias.password", this.keystoreCertPassword);
      res.setProperty("org.apache.ws.security.crypto.merlin.file", this.keystoreLocation);
      return res;
   }
   
   /**
    * Eliminar XMLNS vacios.
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
	 * Convert a DOM Document into an Axis message. <p/>
	 *
	 * @param doc el doc
	 * @return el message
	 * @throws Exception el exception
	 */
	public static Message toAxisMessage(Document doc) throws Exception {
		Canonicalizer c14n = Canonicalizer
				.getInstance(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS);
		byte[] canonicalMessage = c14n.canonicalizeSubtree(doc);
		ByteArrayInputStream in = new ByteArrayInputStream(canonicalMessage);
		return new Message(in);
	}
	
	/**
	 * To string.
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
	
	/**
	 * Imprime el document.
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
	
}