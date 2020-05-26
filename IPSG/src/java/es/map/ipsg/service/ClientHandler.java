package es.map.ipsg.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.SOAPPart;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecSignature;
import org.apache.ws.security.message.WSSecUsernameToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * El Class ClientHandler.
 */
public class ClientHandler extends BasicHandler {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ClientHandler.class);
	
	/** La constante USERNAMEOPTION. */
	public static final String USERNAMEOPTION = WSConstants.USERNAME_TOKEN_LN;
	
	/** La constante CERTIFICATEOPTION. */
	public static final String CERTIFICATEOPTION = WSConstants.BINARY_TOKEN_LN;
	
	/** La constante NONEOPTION. */
	public static final String NONEOPTION = "None";
	
	/** el security option. */
	private String securityOption = null;
	
	/** el keystore location. */
	private String keystoreLocation = null;
	
	/** el keystore type. */
	private String keystoreType = null;
	
	/** el keystore password. */
	private String keystorePassword = null;
	
	/** el keystore cert alias. */
	private String keystoreCertAlias = null;
	
	/** el keystore cert password. */
	private String keystoreCertPassword = null;
	
	/**
	 * Crea una nueva client handler.
	 *
	 * @param config el config
	 */
	public ClientHandler(Properties config){
		if(config == null){
			logger.error("Fichero de configuracion de propiedad nulo");
		}else{
			try{
				securityOption = config.getProperty("security.mode"); 
				keystoreLocation = config.getProperty("security.keystore.location");
				keystoreType = config.getProperty("security.keystore.type");
				keystorePassword = config.getProperty("security.keystore.password");
				keystoreCertAlias = config.getProperty("security.keystore.cert.alias");
				keystoreCertPassword = config.getProperty("security.keystore.cert.password");
				
				if(!securityOption.equals(USERNAMEOPTION) && !securityOption.equals(CERTIFICATEOPTION) && !securityOption.equals(NONEOPTION)){
					logger.error("Opcion de seguridad no valida");
				}
			}catch(Exception e){
				logger.error("Error leyendo el fichero de configuracion de securizacion",e);				
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.axis.Handler#invoke(org.apache.axis.MessageContext)
	 */
	public void invoke(MessageContext msgContext) throws AxisFault{
		
		SOAPMessage msg;
		Document doc = null;

         try {  
        	msg = msgContext.getCurrentMessage();
 			doc = ((org.apache.axis.message.SOAPEnvelope) msg.getSOAPPart().getEnvelope()).getAsDocument();
 			
 			if(securityOption.equals(USERNAMEOPTION)){
 				((SOAPPart) msgContext.getRequestMessage().getSOAPPart()).setCurrentMessage(this.createUserNameToken(doc,msgContext), SOAPPart.FORM_SOAPENVELOPE);
 			}else if (securityOption.equals(CERTIFICATEOPTION)){
 				((SOAPPart) msgContext.getRequestMessage().getSOAPPart()).setCurrentMessage(this.createBinarySecurityToken(doc,msgContext), SOAPPart.FORM_SOAPENVELOPE);
 			}
    
         } catch (Exception e) {  
				logger.error("Error mensaje",e);
         }  
	}
	
	/**
	 * Crea el user name token.
	 *
	 * @param soapEnvelopeRequest el soap envelope request
	 * @param msgContext el msg context
	 * @return el SOAP envelope
	 */
	private SOAPEnvelope createUserNameToken(Document soapEnvelopeRequest, MessageContext msgContext){
		ByteArrayOutputStream baos;
		Document secSOAPReqDoc;
		DOMSource source;
		Element element;
		SOAPMessage res;
		StreamResult streamResult;
		String secSOAPReq;
		WSSecUsernameToken wsSecUsernameToken;
		WSSecHeader wsSecHeader;
		
		
		try{
			//Insercion del tag wsse:Security y userNameToken
			wsSecHeader = new WSSecHeader(null,false);
			wsSecUsernameToken = new WSSecUsernameToken();
			
			wsSecHeader.insertSecurityHeader(soapEnvelopeRequest);
			wsSecUsernameToken.prepare(soapEnvelopeRequest);
			
			//Añadimos una marca de tiempo indicando la fecha de creacion del tag
			wsSecUsernameToken.addCreated();
			wsSecUsernameToken.addNonce();
			
			//Modificacion de la peticion
			secSOAPReqDoc = wsSecUsernameToken.build(soapEnvelopeRequest, wsSecHeader);
			element = secSOAPReqDoc.getDocumentElement();
			
			//Transformacion del elemento DOM a String
			source = new DOMSource(element);
			baos = new ByteArrayOutputStream();
			streamResult = new StreamResult(baos);
			TransformerFactory.newInstance().newTransformer().transform(source, streamResult);
			secSOAPReq = new String (baos.toByteArray());
			
			//Creacion de un nuevo mensaje SOAP a partir del mensaje SOAP securizado formado
		
			
			res = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(secSOAPReq.getBytes()));		
			
		


			
			// Creación de un nuevo mensaje SOAP a partir del mensaje SOAP  
			Message axisMessage = getAxisMessage(secSOAPReq,msgContext);  

			
			//Impresion del XML en logger
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			res.writeTo(out);
			byte[] charData = out.toByteArray();
			String str = new String(charData, "ISO-8859-1");
			logger.info("XML generado para Validacion de certifica con WS Security Usuario/Password");
			logger.info(str);
			
			 return axisMessage.getSOAPEnvelope();  


		}catch(Exception e){
			logger.error("Error en createUserNameToken",e);
			return null;
		}
	}
	
	/**
	 * Obtiene el axis message.
	 *
	 * @param unsignedEnvelope el unsigned envelope
	 * @param msgContext el msg context
	 * @return el axis message
	 */
	private Message getAxisMessage(String unsignedEnvelope, MessageContext msgContext) {  
		 InputStream inStream = new ByteArrayInputStream(unsignedEnvelope.getBytes());
		 Message axisMessage = new Message(inStream);
		 axisMessage.setMessageContext(msgContext);
		 return axisMessage;  
	}
	
	/**
	 * Crea el binary security token.
	 *
	 * @param soapEnvelopeRequest el soap envelope request
	 * @param msgContext el msg context
	 * @return el SOAP envelope
	 */
	private SOAPEnvelope createBinarySecurityToken(Document soapEnvelopeRequest, MessageContext msgContext){
		ByteArrayOutputStream baos;
		Crypto crypto;
		Document secSOAPReqDoc;
		DOMSource source;
		Element element;
		SOAPMessage res;
		StreamResult streamResult;
		String secSOAPReq;
		WSSecSignature wsSecSignature;
		WSSecHeader wsSecHeader;
		
		try{
			
			//Insercion del tag wsse:Security y userNameToken
			wsSecHeader = new WSSecHeader(null,false);
			wsSecSignature = new WSSecSignature();
			//DESCOMENTAR INI
			crypto = null;//CryptoFactory.getInstance("org.apache.ws.security.components.crypto.Merlin",this.initializateCryptoProperties());
			//DESCOMENTAR FIN
			//Indicacion para que inserte el tag BinarySecurityToken
			wsSecSignature.setKeyIdentifierType(WSConstants.BST_DIRECT_REFERENCE);
			wsSecSignature.setUserInfo(this.keystoreCertAlias, this.keystoreCertPassword);
			
			wsSecHeader.insertSecurityHeader(soapEnvelopeRequest);
			wsSecSignature.prepare(soapEnvelopeRequest,crypto,wsSecHeader);
			
			//Modificacion y firma de la peticion
			secSOAPReqDoc = wsSecSignature.build(soapEnvelopeRequest, crypto, wsSecHeader);
			element = secSOAPReqDoc.getDocumentElement();
					
			//Transformacion del Elemento DOC a String
			source = new DOMSource(element);
			baos = new ByteArrayOutputStream();
			streamResult = new StreamResult(baos);
			TransformerFactory.newInstance().newTransformer().transform(source, streamResult);
			secSOAPReq = new String(baos.toByteArray());
			
			//Creacion de un nuevo mensaje SOAP a partir del SOAP securizado
			res = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(secSOAPReq.getBytes()));
			
			// Creación de un nuevo mensaje SOAP a partir del mensaje SOAP  
			Message axisMessage = getAxisMessage(secSOAPReq,msgContext);  
			
			//Impresion del XML en logger
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			res.writeTo(out);
			byte[] charData = out.toByteArray();
			String str = new String(charData, "ISO-8859-1");
			logger.info("XML generado para Validacion de certifica con WS Security Usuario/Password");
			logger.info(str);
			
			
			return axisMessage.getSOAPEnvelope();			
		}catch(Exception e){
			logger.error("Error en createBinarySecurityToken",e);
			return null;			
		}		
	}
	
	/**
	 * Initializate crypto properties.
	 *
	 * @return el properties
	 */
	/*
	 * Establece el conjunto de propieadades con el que sera inicializado el gestor criptográfico de WSS4J
	 * @return Devuelve el conjunto de propiedades con el que sera inicializado el gestor criptografico de WSS4J
	 */
	private Properties initializateCryptoProperties(){
		Properties res = new Properties();
		
		res.setProperty("org.apache.ws.security.crypto.provider", "org.apache.ws.security.crypto.Merlin");
		res.setProperty("org.apache.ws.security.crypto.merlin.keystore.type", this.keystoreType);
		res.setProperty("org.apache.ws.security.crypto.merlin.keystore.password", this.keystorePassword);
		res.setProperty("org.apache.ws.security.crypto.merlin.keystore.alias", this.keystoreCertAlias);
		res.setProperty("org.apache.ws.security.crypto.merlin.alias.password", this.keystoreCertPassword);
		res.setProperty("org.apache.ws.security.crypto.merlin.file", this.keystoreLocation);
		
		return res;
	}
}
