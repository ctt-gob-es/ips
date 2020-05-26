//cpasculi - IPS-147 - Actualizacion de ePago

package es.map.ipsc.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.xml.security.Init;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.SOAPPart;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.utils.LockableHashtable;
import org.apache.axis.utils.XMLUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.SOAP11Constants;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecSignature;
import org.apache.ws.security.util.WSSecurityUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.map.ips.common.spring.ApplicationContextProvider;

/**
 * El Class ClientEPagoHandler.
 */
public class ClientEPagoHandler extends BasicHandler {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ClientEPagoHandler.class);

	/** La constante DEFAULTIDBODY. */
	private static final String DEFAULTIDBODY = "MsgBody";
	
	/** La constante ID. */
	private static final String ID = "Id";
	
	/** La constante STRING_XMLNS. */
	private static final String STRING_XMLNS = "xmlns";
	
	/** La constante STRING_ISO_8859_1. */
	private static final String STRING_ISO_8859_1 = "ISO-8859-1";
	
	/** el xpath. */
	private XPath xpath;
	
	
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
	 * Crea una nueva client E pago handler.
	 */
	public ClientEPagoHandler(){
		
		Properties props = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		this.xpath = XPathFactory.newInstance().newXPath();
		
		keystoreLocation = props.getProperty("security.keystore.location");
		keystoreType = props.getProperty("security.keystore.type");
		keystorePassword = props.getProperty("security.keystore.password");
		keystoreCertAlias = props.getProperty("security.keystore.cert.alias");
		keystoreCertPassword = props.getProperty("security.keystore.cert.password");

	}
	
	/* (non-Javadoc)
	 * @see org.apache.axis.Handler#invoke(org.apache.axis.MessageContext)
	 */
	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {

		SOAPMessage msg;
		Document doc = null;

		try {
			
			msg = messageContext.getCurrentMessage();
			doc = ((org.apache.axis.message.SOAPEnvelope) msg.getSOAPPart().getEnvelope()).getAsDocument();
			((SOAPPart) messageContext.getRequestMessage().getSOAPPart()).setCurrentMessage(this.createBinarySecurityToken(doc,messageContext), SOAPPart.FORM_SOAPENVELOPE);
			
		} catch (Exception e) {
			logger.error("Error al firmar cabecera SOAP", e);
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
	    transformer.setOutputProperty(OutputKeys.ENCODING, STRING_ISO_8859_1);
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	    transformer.transform(new DOMSource(doc), 
	         new StreamResult(new OutputStreamWriter(out, STRING_ISO_8859_1)));
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
			crypto = CryptoFactory.getInstance("org.apache.ws.security.components.crypto.Merlin",this.initializateCryptoProperties());
			
			//Indicacion para que inserte el tag BinarySecurityToken
			wsSecSignature.setKeyIdentifierType(WSConstants.BST_DIRECT_REFERENCE);
			wsSecSignature.setUserInfo(this.keystoreCertAlias, this.keystoreCertPassword);
			
			wsSecHeader.insertSecurityHeader(soapEnvelopeRequest);
			wsSecSignature.prepare(soapEnvelopeRequest,crypto,wsSecHeader);
			
			element = WSSecurityUtil.findBodyElement(soapEnvelopeRequest, SOAP11Constants.SOAP11_CONSTANTS);
			element.setAttribute(ID, DEFAULTIDBODY);
			eliminarXMLNSVacios(element);
			
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
			String str = new String(charData, STRING_ISO_8859_1);
			logger.info("XML generado para Validacion de certifica con WS Security - BinarySecurityToken");
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
