package es.map.ipsc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import es.gob.afirma.core.AOException;
import es.gob.afirma.core.signers.AOSigner;
import es.gob.afirma.core.util.tree.AOTreeModel;
import es.gob.afirma.signers.cades.AOCAdESSigner;
import es.gob.afirma.signers.pades.AOPDFSigner;
import es.gob.afirma.signers.xades.AOXAdESSigner;
import es.gob.afirma.signers.xml.xmldsig.AOXMLDSigSigner;
import es.gob.afirma.util.signers.AOSignerFactory;
import es.map.ipsc.Constantes;

/**
 * El Class SignDataUtil.
 */
public class SignDataUtil {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SignDataUtil.class);
	
	/** La constante STRING_ERRORCHECKISSIGN. */
	private static final String STRING_ERRORCHECKISSIGN ="Error checkIsSign: ";
	
	/** La constante STRING_ERRORPARSEARFIRMA. */
	private static final String STRING_ERRORPARSEARFIRMA = "Error - No se puede parsear la firma: ";
	
	/** La constante STRING_PARSEARFIRMA. */
	private static final String STRING_PARSEARFIRMA = "No se puede parsear la firma ";
	
	/** La constante STRING_DOCUMENTOIMPLICITOFIRMA. */
	private static final String STRING_DOCUMENTOIMPLICITOFIRMA = "No se puede obtener el documento Impl�cito en la firma";

	/** el instance. */
	private static SignDataUtil instance;

	/**
	 * Obtiene una instancia del objeto actual.
	 * @return la instancia del objeto actual.
	 */
	public static synchronized SignDataUtil getInstance() {
		if (instance == null) {
			instance = new SignDataUtil();
		}
		return instance;

	}

	/**
	 * Método para detectar si un documento es, en realidad, una firma (una firma que no sea firma PDF).
	 *
	 * @param document Documento a comprobar
	 * @return Cadena con el tipo de firma, null si no es una firma
	 */
	public String checkIsSign(Object document) {
		boolean parseError = false;
		String isSigned = null;
		

		Document dom = null;
		try {
			dom = XMLUtil.getDOMDocument(document, true);
			// Si se produce alguna excepcion al parsear, significará que no es un XML y por tanto no será una firma XML.
		} catch (ParserConfigurationException pce) {
			logger.error(STRING_ERRORCHECKISSIGN,pce);
			parseError = true;
		} catch (SAXException se){
			logger.error(STRING_ERRORCHECKISSIGN,se);
			parseError = true;
		} catch (IOException ioe) 
		{
			logger.error(STRING_ERRORCHECKISSIGN,ioe);
			parseError = true;
		}

		// Si se ha podido parsear el documento, comprobamos que se trata de una firma XML.
		if (!parseError) {
			isSigned = this.isDocumentXMLSignDetached(dom);
			if (isSigned == null) {
				isSigned = this.isDocumentXMLSignEnveloped(dom);
			}
		} else {
			try {
				isSigned = isOtherSign(getBytes(document));
			} catch (IOException e) {
				logger.error("Error isSigned: ",e);
				isSigned = null;
			}
		}
		return isSigned;		
	}

	/**
	 * Obtiene un objeto SignData a partir de una firma.
	 * @param sign firma.
	 * @return un objeto que contiene el documento que se ha firmado y el mime de ese documento.
	 * @throws Exception cuando no se puedan obtener los datod e la firma.
	 */
	public SignData getDataFromSign (byte[] sign) throws Exception{

		byte[] datosFirma = null;
		String tipoFirma = null;		

		try {
			datosFirma = getDataSignedFromSignXML (sign);
			tipoFirma = Constantes.SIGN_FORMAT_XADES_IMPLICIT;

		} catch (Exception e) {
			logger.error("Error getDataFromSign: ",e);
			es.gob.afirma.core.signers.AOSigner signer = obtenerSigner(sign);

			datosFirma = getDataSignedFromSignerGenerico(signer, sign);
			tipoFirma = getTipoFirmaFromSignerGenerico(signer);
		}

		String mime = Util.getInstance().getMime(datosFirma);
		SignData signData = new SignData(mime, datosFirma);
		signData.setTipoFirma(tipoFirma);
		return signData;
	}

	/**
	 * Obtiene los bytes a partir de un objeto.
	 * @param source Objeto del que se quieren obtener los bytes.
	 * @return array de bytes
	 * @throws IOException si no se puede leer la fuente.
	 */
	private byte[] getBytes(Object source) throws IOException{
		InputStream is = null;
		if (source instanceof File) {
			File f = (File) source;
			is = new FileInputStream(f);
		} else if (source instanceof InputStream) {
			is = (InputStream) source;
		} else if (source instanceof byte[]) {
			return (byte[]) source;
		}
		ByteArrayOutputStream bout = new ByteArrayOutputStream ();
		IOUtils.copyLarge(is, bout);
		return bout.toByteArray();
	}

	/**
	 * Se obtiene un objeto para manipular la firma.
	 * @param bytes de la firma
	 * @return Instancia de un objeto para manipular la firma.
	 */
	private AOSigner obtenerSigner (byte[] bytes) {
		AOSigner signer = null;		
		signer = AOSignerFactory.getSigner(bytes);			
		return signer;
	}

	/**
	 * Comprueba si unos bytes se corresponden con los de una firma electŕonica (que no sea firma PDF).
	 *
	 * @param bytesFirma el bytes firma
	 * @return true si lo es, false en caso contrario.
	 */
	private String isOtherSign (byte[] bytesFirma) {
		boolean isSigned = false;
		byte[] bytesDatosFirmados = null;
		AOTreeModel arbolFirmantes = null;
		try {			
			AOSigner signer = obtenerSigner(bytesFirma);
			// La firma PDF se trata como un PDF normal, así que no lo consideraremos como documento firmado.
			if (signer != null && (!(signer instanceof AOPDFSigner))) {
				// Consideraremos que se trata de una firma si se pueden obtener los datos firmados y el árbol de firmantes.
				bytesDatosFirmados = signer.getData(bytesFirma);
				arbolFirmantes = signer.getSignersStructure(bytesFirma, true);
			}
		} catch (Exception e) {
			logger.error("Error - isOtherSign: ",e);
			

		}
		isSigned = (bytesDatosFirmados != null && arbolFirmantes != null /*&& arbolFirmantes.getCount() != 0*/);

		if (isSigned) {
			return "otherSign";
		} else {
			return null;
		}
	}

	/**
	 * Consideraremos que el árbol DOM representa a una firma XML con el documento implícito cuando contenga
	 * el primer nodo con el documento firmado y, al mismo nivel, un nodo Signature.
	 * @param dom árbol DOM de un documento XML.
	 * @return true si representa una firma XML Detached, false en caso contrario.
	 */
	private String isDocumentXMLSignDetached (Document dom) {
		//comprobamos que tenga el nodo con el documento firmado y el nodo Signature.
		//Estos dos nodos tienen que colgar del nodo padre del documento.
		Element elementRoot = dom.getDocumentElement();	

		// Obtenemos el primer hijo del nodo Root.
		Node child = elementRoot.getFirstChild();
		boolean lastChild = false;
		boolean tieneDocFirmado = false;
		boolean tieneSignature = false;

		boolean esXMLSign = false;

		while (!lastChild && !esXMLSign) {
			// Si no hemos encontrado el nodo que contiene al documento firmado, miramos si es el actual.
			if (!tieneDocFirmado) {
				tieneDocFirmado = 	contieneIdEncoding(child);
			}
			// Si no hemos encontrado el nodo que contiene la firma, miramos si es el actual.
			if (!tieneSignature) {
				
				tieneSignature = child.getLocalName()!= null && child.getLocalName().equals("Signature");				
			}

			if (child == elementRoot.getLastChild()) {
				lastChild = true;
			} else {
				child = child.getNextSibling();
			}
			esXMLSign = tieneDocFirmado && tieneSignature;
		}

		if (esXMLSign) {
			return "dettached";
		} else {
			return null;
		}
	}

	/**
	 * Consideraremos que el árbol DOM representa a una firma XML Enveloped cuando contenga
	 * al menos un nodo Signature cuyo padre sea el nodo raíz.
	 *
	 * @param dom árbol DOM de un documento XML.
	 * @return true si representa una firma XML Enveloped, false en caso contrario.
	 */
	private String isDocumentXMLSignEnveloped (Document dom) {
		//comprobamos que tenga el nodo con el documento firmado y el nodo Signature.
		//Estos dos nodos tienen que colgar del nodo padre del documento.
		Element elementRoot = dom.getDocumentElement();	

		boolean esXMLSignEnveloped = false;

		NodeList nodosSignature = dom.getElementsByTagName("ds:Signature");

		// Si al menos un nodo es hijo de la ra�z es XADES ENVELOPED
		int i = 0;
		while (!esXMLSignEnveloped && (i < nodosSignature.getLength())) {
			Node nodo = nodosSignature.item(i);
			if (nodo.getParentNode().getLocalName().equals(elementRoot.getLocalName())) {
				esXMLSignEnveloped = true;
			}
			i++;
		}

		if (esXMLSignEnveloped) {
			return "enveloped";
		} else {
			return null;
		}
	}

	/**
	 * Obtiene los datos firmados de una firma XML implícita.
	 * @param sign bytes de la firma
	 * @return bytes de los datos firmados de una firma XML
	 * @throws Exception si no se pueden obtener los datos firmados.
	 */
	private byte[] getDataSignedFromSignXML(byte[] sign) throws Exception{
		byte[] datosFirma = null;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {	
			logger.error(STRING_ERRORPARSEARFIRMA,e);
			throw new Exception (STRING_PARSEARFIRMA + e.getMessage(), e);
		}
		Document dom = null;
		try {
			dom = db.parse(new ByteArrayInputStream(sign));
		} catch (SAXException e) {
			logger.error(STRING_ERRORPARSEARFIRMA,e);
			throw new Exception (STRING_PARSEARFIRMA + e.getMessage(), e);			
		} catch (IOException e) {
			logger.error(STRING_ERRORPARSEARFIRMA,e);
			throw new Exception (STRING_PARSEARFIRMA + e.getMessage(), e);			
		}

		datosFirma = getContent(dom);		
		return datosFirma;
	}

	/**
	 * Obtiene el certificate from sign XML.
	 *
	 * @param sign el sign
	 * @return el certificate from sign XML
	 * @throws Exception el exception
	 */
	public String getCertificateFromSignXML (byte[] sign) throws Exception {
		String certificado = null;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {	
			logger.error(STRING_ERRORPARSEARFIRMA,e);
			throw new Exception (STRING_PARSEARFIRMA + e.getMessage(), e);
		}
		Document dom = null;
		try {
			dom = db.parse(new ByteArrayInputStream(sign));
		} catch (SAXException e) {
			logger.error(STRING_ERRORPARSEARFIRMA,e);
			throw new Exception (STRING_PARSEARFIRMA + e.getMessage(), e);			
		} catch (IOException e) {
			logger.error(STRING_ERRORPARSEARFIRMA,e);
			throw new Exception (STRING_PARSEARFIRMA + e.getMessage(), e);			
		}

		certificado = getCertificate(dom);		
		return certificado;
	}

	/**
	 * Obtiene el contenido del nodo hijo del nodo padre del documento. Este nodo debe contener
	 * los atributos "id" y "encoding".
	 * @param dom �?rbol XML del documento
	 * @return el contenido del nodo hijo del nodo padre del documento.
	 * @throws Exception si no se encuentra el nodo que cumpla estas condiciones.
	 */
	private byte[] getContent (Document dom) throws Exception{
		Element elementRoot = dom.getDocumentElement();

		Node child = elementRoot.getFirstChild();
		boolean lastChild = false;
		boolean encontrado = false;
		while (!lastChild && !encontrado) {

			encontrado = contieneIdEncoding(child);

			if (child == elementRoot.getLastChild()) {
				lastChild = true;
			} else if (!encontrado) {
				child = child.getNextSibling();
			}
		}

		String b64Content = null;
		byte[] document = null;

		if (encontrado) {
			b64Content = child.getFirstChild().getNodeValue();
			document = Base64.decodeBase64(b64Content);
		} else {
			throw new Exception ("No se encuentra el documento Implícito en la firma");
		}

		return document;

	}

	/**
	 * Obtiene el certificate.
	 *
	 * @param dom el dom
	 * @return el certificate
	 * @throws Exception el exception
	 */
	private String getCertificate (Document dom) throws Exception {
		String certificate = null;
		Element elementRoot = dom.getDocumentElement();

		NodeList list = elementRoot.getElementsByTagName("ds:X509Certificate");

		if (list == null) {
			throw new Exception ("No se ha encontrado ningún nodo ds:X509Certificate");
		} else {
			Node node = list.item(0);
			certificate = node.getFirstChild().getNodeValue();
		}
		return certificate;
	}

	/**
	 * Obtiene los datos firmados a partir de los bytes de cualquier firma.
	 * @param bytesFirma Bytes de la firma
	 * @return bytes de los datos firmados.
	 * @throws Exception si no se puede obtener el documento firmado.
	 */
	private byte[] getDataSignedFromSignGenerico (byte[] bytesFirma) throws Exception{

		byte[] bytesDatosFirmados = null;		

		try {			
			AOSigner signer = obtenerSigner(bytesFirma);
			if (signer == null) {
				throw new Exception (STRING_DOCUMENTOIMPLICITOFIRMA);
			}
			bytesDatosFirmados = signer.getData(bytesFirma);
		} catch (AOException e) {
			logger.error("Error - No se puede obtener el documento implicito en la firma: ",e);
			throw new Exception (STRING_DOCUMENTOIMPLICITOFIRMA);
		}

		return bytesDatosFirmados;
	}


	/**
	 * Obtiene el data signed from signer generico.
	 *
	 * @param signer el signer
	 * @param bytesFirma el bytes firma
	 * @return el data signed from signer generico
	 * @throws Exception el exception
	 */
	private byte[] getDataSignedFromSignerGenerico (AOSigner signer, byte[] bytesFirma) throws Exception {
		byte[] bytesDatosFirmados = null;
		try  {
			if (signer == null) {
				throw new Exception (STRING_DOCUMENTOIMPLICITOFIRMA);
			}

			bytesDatosFirmados = signer.getData(bytesFirma);
		} catch (AOException e) {
			logger.error("Error - No se puede obtener el documento implicito en la firma: ",e);
			throw new Exception (STRING_DOCUMENTOIMPLICITOFIRMA);
		}

		return bytesDatosFirmados;
	}


	/**
	 * Obtiene el tipo de firma a partir del AOSigner.
	 *
	 * @param signer AOSigner
	 * @return El tipo de firma, según el signer
	 * @throws Exception Cuando el Signer no corresponda con las firmas realizadas en Portafirmas
	 */
	private String getTipoFirmaFromSignerGenerico (AOSigner signer) throws Exception {
		String tipoFirma = null;

		if (signer instanceof AOCAdESSigner) {
			tipoFirma = Constantes.SIGN_FORMAT_CADES;
		} else if (signer instanceof AOPDFSigner) {
			tipoFirma = Constantes.SIGN_FORMAT_PDF;
		} else if (signer instanceof AOXAdESSigner) {
			tipoFirma = Constantes.SIGN_FORMAT_XADES_IMPLICIT;
		} else if (signer instanceof AOXMLDSigSigner) {
			tipoFirma = Constantes.SIGN_FORMAT_XADES_ENVELOPING;
		} else if (signer == null){
			throw new Exception ("El Signer es nulo");
		} else {
			throw new Exception ("El Signer no es conocido");
		}

		return tipoFirma;

	}

	/**
	 * Comprueba que un nodo XML tenga el atributo Id y el atributo Encoding.
	 *
	 * @param nodo el nodo
	 * @return verdadero, si tiene exito
	 */
	private boolean contieneIdEncoding (Node nodo) {

		NamedNodeMap atributos = nodo.getAttributes();

		// Comprobamos que tenga el atributo Id y el atributo Encoding:				
		int i=0;
		boolean encontrados = false;
		boolean idFound = false;
		boolean encodingFound = false;

		while (atributos != null && i < atributos.getLength() && !encontrados) {
			if (atributos.item(i).getNodeName().equalsIgnoreCase("id")){				
				idFound = true;
			} else if (atributos.item(i).getNodeName().equalsIgnoreCase("encoding")) {
				encodingFound = true;
			}

			encontrados = idFound && encodingFound;
			i++;
		}

		return encontrados;
	}		
}
