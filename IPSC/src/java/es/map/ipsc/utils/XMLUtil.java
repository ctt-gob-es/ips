package es.map.ipsc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * El Class XMLUtil.
 */
public class XMLUtil {

	/** La constante logger. */
	protected static final Log logger = LogFactory.getLog(XMLUtil.class);
	
	/** La constante STRING_MIMETYPE. */
	private static final String STRING_MIMETYPE = "MimeType";

	/**
	 * Obtiene el Ã¡rbol XML de una fuente, que puede ser File, InputStream o array de bytes.
	 *
	 * @param source fuente de la que se quiere obtener el árbol XML.
	 * @param namespaceAware el namespace aware
	 * @return árbol XML.
	 * @throws ParserConfigurationException el parser configuration exception
	 * @throws SAXException el SAX exception
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 */
	public static Document getDOMDocument(Object source, boolean namespaceAware) throws ParserConfigurationException, SAXException, IOException{
		logger.debug ("getDOMDocument init");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(namespaceAware);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = null;
		if (source instanceof File) {
			File f = (File) source;
			doc = db.parse(f);			
		} else if (source instanceof InputStream) {
			InputStream is = (InputStream) source;
			doc = db.parse(is);
		} else if (source instanceof byte[]) {
			byte[] bytes = (byte[]) source;
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			doc = db.parse(bis);
		}
		logger.debug ("getDOMDocument end");
		return doc;

	}

	/**
	 * Comprueba si se cumplen una serie de expresiones xpath.
	 * @param doc Documento DOM
	 * @param xpathExpressions array de expresiones xpath.
	 * @param xpath instancia de xpath
	 * @return true si se cumplen todas, false en caso contrario.
	 * @throws XPathExpressionException si alguna de las expresiones no es correcta.
	 */
	public static boolean seCumplen (Document doc, String[] xpathExpressions, javax.xml.xpath.XPath xpath) throws XPathExpressionException {
		Boolean seCumplen = true;

		int i=0; 

		while (i < xpathExpressions.length && seCumplen) {
			String expr = xpathExpressions[i];
			seCumplen = (Boolean) xpath.evaluate(expr, doc.getDocumentElement(), javax.xml.xpath.XPathConstants.BOOLEAN);
			xpath.reset();
			i++;
		}

		return seCumplen;
	}

	/**
	 * Comprueba si es XMLD sig signature.
	 *
	 * @param doc el doc
	 * @param xpath el xpath
	 * @return verdadero, si es XMLD sig signature
	 * @throws XPathFactoryConfigurationException el x path factory configuration exception
	 * @throws XPathExpressionException el x path expression exception
	 */
	public static boolean isXMLDSigSignature (Document doc, javax.xml.xpath.XPath xpath) throws XPathFactoryConfigurationException, XPathExpressionException {
		//Existe el nodo Signature, que tiene como hijo SignedInfo. La primera es para XMLDSig Detached y Enveloped, la segunda para Enveloping
		// Existe el nodo Signature, que tiene como hijo SignedValue. La primera es para XMLDSig Detached y Enveloped, la segunda para Enveloping

		//Existe el nodo Signature, que tiene como hijo SignedInfo, y SignedValue
		String[] xpathExpressions = new String[]{"//Signature/SignedInfo", "//Signature/SignatureValue"};

		return XMLUtil.seCumplen(doc, xpathExpressions, xpath);
	}

	/**
	 * Comprueba si es XMLD sig enveloped or detached.
	 *
	 * @param doc el doc
	 * @param xpath el xpath
	 * @return verdadero, si es XMLD sig enveloped or detached
	 * @throws XPathFactoryConfigurationException el x path factory configuration exception
	 * @throws XPathExpressionException el x path expression exception
	 */
	public static boolean isXMLDSigEnvelopedOrDetached (Document doc, javax.xml.xpath.XPath xpath) throws XPathFactoryConfigurationException, XPathExpressionException {

		// Nodo SignedInfo y SignatureValue, hijo de Signature, que no tiene por quÃ© ser root del XML.
		String[] xpathExpressions = new String[]{"Signature/SignedInfo",
		"Signature/SignatureValue"};

		return XMLUtil.seCumplen(doc, xpathExpressions, xpath);
	}

	/**
	 * Comprueba si es XMLD sig enveloping.
	 *
	 * @param doc el doc
	 * @param xpath el xpath
	 * @return verdadero, si es XMLD sig enveloping
	 * @throws XPathFactoryConfigurationException el x path factory configuration exception
	 * @throws XPathExpressionException el x path expression exception
	 */
	public static boolean isXMLDSigEnveloping (Document doc, javax.xml.xpath.XPath xpath) throws XPathFactoryConfigurationException, XPathExpressionException {

		// Nodo SignedInfo y SignatureValue, hijo de Signature, que tiene que ser root del XML.
		String[] xpathExpressions = new String[]{"/Signature/SignedInfo",
		"/Signature/SignatureValue"};

		return XMLUtil.seCumplen(doc, xpathExpressions, xpath);

	}

	/**
	 * Comprueba si es XMLD sig enveloped.
	 *
	 * @param doc el doc
	 * @param xpath el xpath
	 * @return verdadero, si es XMLD sig enveloped
	 * @throws XPathFactoryConfigurationException el x path factory configuration exception
	 * @throws XPathExpressionException el x path expression exception
	 */
	public static boolean isXMLDSigEnveloped (Document doc, javax.xml.xpath.XPath xpath) throws XPathFactoryConfigurationException, XPathExpressionException {
		String[] xpathExpressions = new String[]{"//Signature/SignedInfo", "//Signature/SignatureValue", "//Transforms/Transform[(@Algorithm|@algorithm|@ALGORITHM)='http://www.w3.org/2000/09/xmldsig#enveloped-signature']" };
		return XMLUtil.seCumplen(doc, xpathExpressions, xpath);
	}


	/**
	 * Obtiene el node list by xpath expression.
	 *
	 * @param obj el obj
	 * @param xpathExpression el xpath expression
	 * @return el node list by xpath expression
	 * @throws XPathFactoryConfigurationException el x path factory configuration exception
	 * @throws XPathExpressionException el x path expression exception
	 */
	public static NodeList getNodeListByXpathExpression (Object obj, String xpathExpression) throws XPathFactoryConfigurationException, XPathExpressionException {

		javax.xml.xpath.XPath xpath = javax.xml.xpath.XPathFactory.newInstance("http://java.sun.com/jaxp/xpath/dom").newXPath();

		NodeList nodeList = (NodeList) xpath.evaluate(xpathExpression, obj, javax.xml.xpath.XPathConstants.NODESET);

		return nodeList;

	}

	/**
	 * Obtiene el node by xpath expression.
	 *
	 * @param doc el doc
	 * @param xpathExpression el xpath expression
	 * @return el node by xpath expression
	 * @throws XPathFactoryConfigurationException el x path factory configuration exception
	 * @throws XPathExpressionException el x path expression exception
	 */
	public static Node getNodeByXpathExpression (Document doc, String xpathExpression) throws XPathFactoryConfigurationException, XPathExpressionException {

		javax.xml.xpath.XPath xpath = javax.xml.xpath.XPathFactory.newInstance("http://java.sun.com/jaxp/xpath/dom").newXPath();

		Node node = (Node) xpath.evaluate(xpathExpression, doc, javax.xml.xpath.XPathConstants.NODE);

		return node;
	}



	/**
	 * Comprueba si un nodo tiene un atributo "MimeType" que contenga la cadena "hash".
	 *
	 * @param node el node
	 * @return verdadero, si es hash mime type
	 */
	public static boolean isHashMimeType (Node node) {
		
		boolean ret = false;
		if (node.getAttributes() != null &&
				node.getAttributes().getNamedItem(STRING_MIMETYPE) != null &&
				node.getAttributes().getNamedItem(STRING_MIMETYPE).getNodeValue() != null &&
				node.getAttributes().getNamedItem(STRING_MIMETYPE).getNodeValue().contains("hash")) {
			ret = true;
		}
		return ret;
	}

	/**
	 * Devuelve el valor del atributo "Encoding" del nodo.
	 *
	 * @param node el node
	 * @return el encoding
	 */
	public static String getEncoding (Node node) {
		String encoding = null;
		encoding = getAttribute(node, "Encoding");
		if (encoding == null) {
			encoding = getAttribute(node, "encoding");
		}
		return encoding;
	}

	/**
	 * Obtiene el hash algorithm.
	 *
	 * @param node el node
	 * @return el hash algorithm
	 */
	public static String getHashAlgorithm (Node node) {
		return getAttribute(node, "hashAlgorithm");		
	}

	/**
	 * Obtiene el attribute.
	 *
	 * @param node el node
	 * @param attName el att name
	 * @return el attribute
	 */
	public static String getAttribute (Node node, String attName) {
		String attValue = null;

		if (node.getAttributes() != null &&
				node.getAttributes().getNamedItem(attName) != null) {
			attValue = node.getAttributes().getNamedItem(attName).getNodeValue();
			attValue = node.getAttributes().getNamedItem(attName).getNodeValue();
		}

		return attValue;
	}

	/**
	 * Comprueba si es XML mime type.
	 *
	 * @param node el node
	 * @return verdadero, si es XML mime type
	 */
	public static boolean isXMLMimeType (Node node) {
		String mime = null;
		boolean ret = false;

		if (node.getAttributes() != null &&
				node.getAttributes().getNamedItem(STRING_MIMETYPE) != null) {
			mime = node.getAttributes().getNamedItem(STRING_MIMETYPE).getNodeValue();
		}

		if ("text/xml".equalsIgnoreCase(mime) || "application/xml".equalsIgnoreCase(mime)) {
			ret = true;
		}

		return ret;
	}


	/**
	 * Convierte un documento XML de DOM a un ByteArrayOutputStream.
	 *
	 * @param node el node
	 * @param encoding el encoding
	 * @return el bytes from node
	 * @throws UnsupportedEncodingException el unsupported encoding exception
	 * @throws TransformerException el transformer exception
	 */
	public static ByteArrayOutputStream getBytesFromNode (Node node, String encoding) throws UnsupportedEncodingException, TransformerException {

		Transformer t = getGenericTransformer ("yes", encoding, "2");

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		t.transform(new DOMSource(node),  new StreamResult(new OutputStreamWriter(bout, encoding)));

		return bout;
	}

	/**
	 * Obtiene el string from node.
	 *
	 * @param node el node
	 * @param encoding el encoding
	 * @return el string from node
	 * @throws TransformerException el transformer exception
	 */
	public static String getStringFromNode(Node node, String encoding) throws TransformerException {

		Transformer t = getGenericTransformer ("yes", encoding, "2");
		StringWriter sw = new StringWriter();        

		t.transform(new DOMSource(node), new StreamResult(sw));
		return sw.toString();
	}

	/**
	 * Obtiene el generic transformer.
	 *
	 * @param indent el indent
	 * @param encoding el encoding
	 * @param indent_amount el indent amount
	 * @return el generic transformer
	 * @throws TransformerConfigurationException el transformer configuration exception
	 */
	private static Transformer getGenericTransformer (String indent, String encoding, String indent_amount) throws TransformerConfigurationException {
		TransformerFactory tf = TransformerFactory.newInstance();		
		Transformer t = tf.newTransformer();

		t.setOutputProperty(OutputKeys.INDENT, indent);
		t.setOutputProperty(OutputKeys.ENCODING, encoding);
		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", indent_amount);

		return t;

	}

	/**
	 * Elimina los nodos de un documento que cumplan una determinada expresion XPATH.
	 *
	 * @param xpathExpr el xpath expr
	 * @param doc el doc
	 * @throws XPathFactoryConfigurationException el x path factory configuration exception
	 * @throws XPathExpressionException el x path expression exception
	 */
	public static void removeNodesFromDocument (String xpathExpr, Document doc) throws XPathFactoryConfigurationException, XPathExpressionException {
		NodeList nodes = XMLUtil.getNodeListByXpathExpression(doc, xpathExpr);

		// Los eliminamos
		if (nodes != null) {
			for (int i=0; i < nodes.getLength(); i++) {
				Node n = nodes.item(i);
				n.getParentNode().removeChild(n);
			}
		}

		doc.normalize();
	}
}

