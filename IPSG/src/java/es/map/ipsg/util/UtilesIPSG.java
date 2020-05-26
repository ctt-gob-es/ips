package es.map.ipsg.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.UsuarioManager;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * UtilesIPSG
 * Clase que implementará las funciones más genéricas que se utilizan en la aplicación.
 * Conversiones de tipo
 * @author amartinl
 *
 */
public class UtilesIPSG {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(UtilesIPSG.class);
	
	/** el base 64 code. */
	public static String base64code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
			"abcdefghijklmnopqrstuvwxyz" + "0123456789" + "+/";
	
	/** el split lines at. */
	public static int splitLinesAt = 76;
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_SIMPLE_DATE_FORMAT. */
	private static final String STRING_SIMPLE_DATE_FORMAT = "dd/MM/yyyy";
	
	/** La constante STRING_ERROR_PRIVATEKEYENTRY. */
	private static final String STRING_ERROR_PRIVATEKEYENTRY = "Error PrivateKeyEntry: ";


	/**
	 * Funciones Útiles de conversión de Datos.
	 *
	 * @param doc el doc
	 * @return el string
	 * @throws Exception el exception
	 */

	public static String obtenerStringDoc(Document doc)throws Exception{ 
		TransformerFactory tFactory = TransformerFactory.newInstance(); 
		Transformer transformer = tFactory.newTransformer(); 
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		DOMSource source = new DOMSource(doc); 
		StringWriter sw=new StringWriter(); 
		StreamResult result = new StreamResult(sw); 
		transformer.transform(source, result); 
		String xmlString=sw.toString(); 
		return xmlString; 

	} 

	/**
	 * Crea una nueva utiles IPSG.
	 */
	public UtilesIPSG() { //Metodo vacio
	}

	/**
	 * String a Date
	 * Pasa de tipo String a java.util.Date
	 * @param sFechaEntrada String
	 * @return dFechaSalida Date
	 */
	public Date stringToDate(String sFechaEntrada)
	{
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLE_DATE_FORMAT);
		Date dFechaSalida = null;

		try
		{
			if(sFechaEntrada != null && !sFechaEntrada.equals(""))
			{
				dFechaSalida = formatoDelTexto.parse(sFechaEntrada);
			}
		}catch (ParseException ex) {
			logger.error("Error parsear fechaEntrada: "+sFechaEntrada,ex);
		}

		return dFechaSalida;

	}

	/**
	 * String to date formato completo.
	 *
	 * @param sFechaEntrada el s fecha entrada
	 * @return el date
	 */
	public Date stringToDateFormatoCompleto(String sFechaEntrada)
	{
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dFechaSalida = null;

		try
		{
			if(sFechaEntrada != null && !sFechaEntrada.equals(""))
			{
				dFechaSalida = formatoDelTexto.parse(sFechaEntrada);
			}
		}catch (ParseException ex) {
			logger.error("Error parsear fechaEntrada: "+sFechaEntrada,ex);
		}

		return dFechaSalida;

	}

	/**
	 * Metodo que suma o resta los días recibidos a una fecha dada.  
	 *
	 * @param fecha el fecha
	 * @param dias el dias
	 * @return el date
	 */
	public static Date sumarRestarDiasFecha(Date fecha, int dias){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); 						// Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias);  		// numero de días a añadir, o restar en caso de días<0

		return calendar.getTime(); 						// Devuelve el objeto Date con los nuevos días añadidos
	}

	/**
	 * Obtener fecha actual.
	 *
	 * @return el date
	 */
	public static Date obtenerFechaActual(){

		SimpleDateFormat fmt = new SimpleDateFormat(STRING_SIMPLE_DATE_FORMAT);
		Date feSistema = null;
		try {
			feSistema = new Date();
			feSistema = fmt.parse(fmt.format(feSistema));			
		} catch (Exception e) {
			logger.error("Obtener Fecha actual - Error - ",e);
		
		}
		return feSistema;
	}

	/**
	 * Zero pad.
	 *
	 * @param length el length
	 * @param bytes el bytes
	 * @return el byte[]
	 */
	public static byte[] zeroPad(int length, byte[] bytes) {
		byte[] padded = new byte[length]; // initialized to zero by JVM
		System.arraycopy(bytes, 0, padded, 0, bytes.length);
		return padded;
	}

	/**
	 * Date a String .
	 *
	 * @param sFechaEntrada  Date
	 * @return sFechaSalida String
	 */
	public String dateToString(Date sFechaEntrada)
	{
		SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLE_DATE_FORMAT);
		String sFechaSalida = "";
		if (sFechaEntrada != null) {
			sFechaSalida = formatoFecha.format(sFechaEntrada);
		}
		return sFechaSalida;
	}

	/**
	 * Date to string completo.
	 *
	 * @param sFechaEntrada el s fecha entrada
	 * @return el string
	 */
	public String dateToStringCompleto(Date sFechaEntrada)
	{
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String sFechaSalida = "";
		if (sFechaEntrada != null) {
			sFechaSalida = formatoFecha.format(sFechaEntrada);
		}
		return sFechaSalida;
	}

	/**
	 * Devuelve el primer Caracter de la cadena en tipo char
	 * Ejemplo:
	 *         cadena: "Primero"
	 *         pos: 0
	 *         Resultado: 'P'.
	 *
	 * @param cadena String
	 * @param pos int Posición a devolver. Comienza por 0
	 * @return caracterPos char Devuelve el primer caracter de la cadena
	 */
	public char stringToCharPos(String cadena, int pos)
	{
		char caracterPos = ' ';
		if(cadena != null && !cadena.equals(""))
		{
			caracterPos = (char)cadena.charAt(pos);

		}
		return caracterPos;
	}

	/**
	 * Borra la carpeta fisica enviando la ruta .
	 *
	 * @param path String
	 */
	public static void borraCarpeta(String path){
		File f1 = new File(path);
		File[] files = f1.listFiles();

		if(files!=null){
			for(int i=0;i<files.length;i++){
				if(files[i].isDirectory()){
					try {
						borraCarpeta(files[i].getCanonicalPath());
					}catch (Exception e) {
						logger.error("Error borrar carpeta: ",e);
					}

					files[i].delete();
				}else{
					files[i].delete();
				}
			}
			f1.delete();
		}
	}

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
	 * Encode.
	 *
	 * @param string el string
	 * @return el string
	 */
	public static String encode(String string) {

		String encoded = "";
		byte[] stringArray;
		try {
			stringArray = string.getBytes("UTF-8");  // use appropriate encoding string!
		} catch (Exception ignored) {
			stringArray = string.getBytes();  // use locale default rather than croak
		}
		// determine how many padding bytes to add to the output
		int paddingCount = (3 - (stringArray.length % 3)) % 3;
		// add any necessary padding to the input
		stringArray = zeroPad(stringArray.length + paddingCount, stringArray);
		// process 3 bytes at a time, churning out 4 output bytes
		// worry about CRLF insertions later
		for (int i = 0; i < stringArray.length; i += 3) {
			int j = (stringArray[i] << 16) + (stringArray[i + 1] << 8) + 
					stringArray[i + 2];
			encoded = encoded + base64code.charAt((j >> 18) & 0x3f) +
					base64code.charAt((j >> 12) & 0x3f) +
					base64code.charAt((j >> 6) & 0x3f) +
					base64code.charAt(j & 0x3f);
		}
		// replace encoded padding nulls with "="
		return splitLines(encoded.substring(0, encoded.length() -
				paddingCount) + "==".substring(0, paddingCount));

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
			logger.error("Error decodeBase64: ",e);
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
			logger.error("Error construir documento: ",e);
			throw e;
		}

		return result;
	}

	/**
	 * String to dom.
	 *
	 * @param xmlSource el xml source
	 * @return el document
	 * @throws SAXException el SAX exception
	 * @throws ParserConfigurationException el parser configuration exception
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 */
	public static Document stringToDom(String xmlSource) throws SAXException, ParserConfigurationException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream iS = null;
		StringBuffer sB=new StringBuffer(xmlSource);
		iS=new ByteArrayInputStream(sB.toString().getBytes("ISO-8859-1"));
		InputSource inSource = new InputSource(new InputStreamReader(iS));
		inSource.setEncoding("ISO-8859-1");
		iS.close();
		return builder.parse(inSource);
	}

	/**
	 * Split lines.
	 *
	 * @param string el string
	 * @return el string
	 */
	public static String splitLines(String string) {

		String lines = "";
		for (int i = 0; i < string.length(); i += splitLinesAt) {

			lines += string.substring(i, Math.min(string.length(), i + splitLinesAt));
			lines += "\r\n";

		}
		return lines;

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
			trans.transform(new DOMSource(documento), new StreamResult(os));
		} catch (Exception e) {
			logger.error("Error convertToBytes: ",e);
			throw new RuntimeException(e);
		}
		return os.toByteArray();
	}

	/**
	 * Replace intros.
	 *
	 * @param original el original
	 * @return el string
	 */
	public static String replaceIntros(String original){
		String oldChar = "\r\n";
		String newChar = "<br>";
		String nuevo = original.replace(oldChar, newChar);
		return nuevo;
	}

	/**
	 * Metodo que devuelve la parte privada de un certificado 
	 * para firmar.
	 * @return PrivateKeyEntry
	 */
	public static PrivateKeyEntry getCertificatePrivateKey() {
		PrivateKeyEntry certificatePrivateKey = null;
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");	
		if (certificatePrivateKey == null) {
			KeyStore.Entry key = null;
			try (InputStream is = new FileInputStream(properties.getProperty("INTEGRAPATH"))){
				KeyStore ks = KeyStore.getInstance(properties.getProperty("KEYSTOREINSTANCE"));
				char [] passwordKS = properties.getProperty("PASSWORDKS").toCharArray();
				char [] passwordCert = properties.getProperty("PASSWORDCERT").toCharArray();
				ks.load(is, passwordKS);
				key = ks.getEntry(properties.getProperty("IPSFIRMA"), new KeyStore.PasswordProtection(passwordCert));
			} catch (NoSuchAlgorithmException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			} catch (CertificateException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			} catch (IOException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			} catch (KeyStoreException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			} catch (UnrecoverableEntryException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			}
			certificatePrivateKey = (KeyStore.PrivateKeyEntry) key;
		}
		return certificatePrivateKey;
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @param nif el nif
	 * @param request el request
	 * @param response el response
	 * @param usuarioManager el usuario manager
	 * @return el usuario bean
	 */
	public UsuarioBean recuperarUsuario(String nif,HttpServletRequest request, HttpServletResponse response,
			UsuarioManager usuarioManager) {
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		
		usuarioQuery.setNif(nif);	
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		
		return usuarioBean;
	}
	
	//INI - TRA-060
	/**
	 * 
	 * @return cadena con la fecha actual formato dd/mm/yyyy
	 */
	public static String getFechaActual() {
		Date fechaActual = obtenerFechaActual();
		SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLE_DATE_FORMAT);
		String sFechaSalida = formatoFecha.format(fechaActual);
		
		return sFechaSalida;
	}
	//FIN - TRA-060
}

