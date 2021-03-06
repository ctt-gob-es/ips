package es.map.ipsc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * El Class IpsUtils.
 */
public class IpsUtils {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(IpsUtils.class);
	
	/** La constante STRING_ISO88591. */
	private static final String STRING_ISO88591 = "ISO-8859-1";
	
	/**
	 * Encode base 64.
	 *
	 * @param contenido el contenido
	 * @return el string
	 */
	public static String encodeBase64(byte[] contenido){
		String encode = "";
		BASE64Encoder b64enc = new BASE64Encoder(); 
		encode = b64enc.encode(contenido);

		return encode;
	}
	
	/**
	 * Decode base 64.
	 *
	 * @param firma el firma
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public static byte[] decodeBase64(String firma) throws Exception{
		byte[] decode = null;
		try{
			BASE64Decoder b64dec = new BASE64Decoder(); 
			decode = b64dec.decodeBuffer(firma);
		}catch(Exception e){
			logger.error("Error  decodeBase64: ",e);
			throw e;
		}
		return decode;
	}
	
	/**
	 * Construir document.
	 *
	 * @param contenido el contenido
	 * @return el document
	 * @throws Exception el exception
	 */
	public static Document construirDocument(byte[] contenido) throws Exception{
		Document result = null;
		
		try {
			// first of all we request out
			// DOM-implementation:
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// then we have to create document-loader:
			DocumentBuilder loader = factory.newDocumentBuilder();
			ByteArrayInputStream bis = new ByteArrayInputStream(contenido);
			result = loader.parse(bis);
			bis.close();
			
		} catch (Exception e) {
			logger.error("Error al convertir en un fichero XML.",e);
			
			throw new Exception();
		}
		
		return result;
	}

	/**
	 * Convert to bytes.
	 *
	 * @param documento el documento
	 * @return el byte[]
	 */
	public static byte[] convertToBytes(Document documento) {

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer();
			trans.setOutputProperty(OutputKeys.ENCODING, STRING_ISO88591); 
			trans.transform(new DOMSource(documento), new StreamResult(os));
		} catch (Exception e) {
			logger.error("Error convertToBytes: ",e);
			throw new RuntimeException(e);
		}
		return os.toByteArray();
	}
	
	/**
	 * String to dom.
	 *
	 * @param xmlSource el xml source
	 * @return el document
	 * @throws SAXException el SAX exception
	 * @throws ParserConfigurationException el parser configuration exception
	 * @throws IOException Se�ala que se ha producido una excepci�n de I/O.
	 */
	public static Document stringToDom(String xmlSource) throws SAXException, ParserConfigurationException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream iS = null;
		StringBuffer sB=new StringBuffer(xmlSource);
		iS=new ByteArrayInputStream(sB.toString().getBytes(STRING_ISO88591));
		InputSource inSource = new InputSource(new InputStreamReader(iS));
		inSource.setEncoding(STRING_ISO88591);
		iS.close();
		
		return builder.parse(inSource);
	}
	
	/**
	 * Replace intros.
	 *
	 * @param original el original
	 * @return el string
	 */
	public static String replaceIntros(String original){
		String oldChar = "\r\n";
		String oldChar2 = "\n";
		String oldChar3 = "\r";
		String newChar = "<br>";
		
		String nuevo = original.replace(oldChar, newChar);//Primero se quitan los /r/n
		nuevo = nuevo.replace(oldChar2, newChar);//Luego se quitan los /n
		nuevo = nuevo.replace(oldChar3, newChar);//Finalmente se quitan los /r
		
		return nuevo;
	}
	
	/**
	 * Replace intros ignore case.
	 *
	 * @param original el original
	 * @return el string
	 */
	public static String replaceIntrosIgnoreCase(String original){
		String oldChar = "\r\n";
		String oldChar2 = "\n";
		String oldChar3 = "\r";
		String oldChar4 = "\\N";
		String oldChar5 = "\\R";
		String newChar = "<br>";
		
		String nuevo = original.replace(oldChar, newChar);//Primero se quitan los /r/n
		nuevo = nuevo.replace(oldChar2, newChar);//Luego se quitan los /n
		nuevo = nuevo.replace(oldChar3, newChar);//Finalmente se quitan los /r
		nuevo = nuevo.replace(oldChar4, newChar);//Finalmente se quitan los /r
		nuevo = nuevo.replace(oldChar5, newChar);//Finalmente se quitan los /r
		
		return nuevo;
	}
	
	/**
	 * String a Date
	 * Pasa de tipo String a java.util.Date
	 * @param sFechaEntrada String
	 * @return dFechaSalida Date
	 */
	public static Date stringToDate(String sFechaEntrada)
	{
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date dFechaSalida = null;
		
		try{
			if(sFechaEntrada != null && !sFechaEntrada.equals("")){
				dFechaSalida = formatoDelTexto.parse(sFechaEntrada);
			}
		}catch (ParseException ex) {
			logger.error( "Error stringToDate", ex);
			
		}
		
		return dFechaSalida;
	}
	
	/**
	 * Obtener string doc.
	 *
	 * @param doc el doc
	 * @return el string
	 * @throws Exception el exception
	 */
	public static String obtenerStringDoc(Document doc)throws Exception{ 
        TransformerFactory tFactory = TransformerFactory.newInstance(); 
        Transformer transformer = tFactory.newTransformer(); 
        transformer.setOutputProperty(OutputKeys.ENCODING, STRING_ISO88591);
        DOMSource source = new DOMSource(doc); 
        StringWriter sw=new StringWriter(); 
        StreamResult result = new StreamResult(sw); 
        transformer.transform(source, result); 
        String xmlString=sw.toString(); 
        
        return xmlString;   
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
     * Fijar numero.
     *
     * @param numero el numero
     * @param digitos el digitos
     * @return el double
     */
    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado/Math.pow(10, digitos);
        return resultado;
    }
}