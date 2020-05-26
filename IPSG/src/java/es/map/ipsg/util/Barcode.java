package es.map.ipsg.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BarcodePDF417;

import es.map.ips.common.spring.ApplicationContextProvider;

/**
 * El Class Barcode.
 */
public class Barcode{
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(Barcode.class);
	
	/** el properties. */
	private static Properties properties;

	/**
	 * Generar barcode datos solicitud.
	 *
	 * @param ruta el ruta
	 * @param contenido el contenido
	 * @throws Exception el exception
	 */
	public static void generarBarcodeDatosSolicitud(String ruta,String contenido) throws Exception{
		try{
			String contenidoNuevo = transformarCaracteres(contenido);
			BarcodePDF417 pdf417_1 = new BarcodePDF417();			
	        pdf417_1.setText(contenidoNuevo);        
	        
	        java.awt.Image image = pdf417_1.createAwtImage(Color.BLACK, Color.WHITE);
	        BufferedImage bi = ImageUtil.toBufferedImage(image);
	        ImageIO.write(bi, "PNG", new File(ruta));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error generar Barcode Datos solicitud", e);
			throw e;
		}
	}
	
	/**
	 * Generar barcode numero justificante.
	 *
	 * @param ruta el ruta
	 * @param numeroJustificante el numero justificante
	 * @throws Exception el exception
	 */
	public static void generarBarcodeNumeroJustificante(String ruta, String numeroJustificante) throws Exception{
		try{
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			String barcodeJustificanteInicio = properties.getProperty("barcode.numeroJustificante.inicio");
			String barcodeJustificanteFinal = properties.getProperty("barcode.numeroJustificante.fin");
			
			String barcode1 = barcodeJustificanteInicio+" "+numeroJustificante+" "+barcodeJustificanteFinal;
			
			Barcode128 shipBarCode = new Barcode128();//usamos la clase Barcode128 de iText para generar la imagen
            shipBarCode.setX(1f);//puedes modificar estas medidas para que veas como queda tu codigo de barras (mas grande, mas ancho, etcetera)
            shipBarCode.setN(1f);
            shipBarCode.setChecksumText(false);
            shipBarCode.setGenerateChecksum(true);
            shipBarCode.setSize(0.1f);
            shipBarCode.setBaseline(0f);
            shipBarCode.setCode(barcode1);
            shipBarCode.setBarHeight(35f);//altura del codigo de barras      
	        
	        java.awt.Image image = shipBarCode.createAwtImage(Color.BLACK, Color.WHITE);
	        BufferedImage bi = ImageUtil.toBufferedImage(image);
	        ImageIO.write(bi, "PNG", new File(ruta));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error generar Barcode Numero Justificante", e);
			throw e;
		}
	}
	
	/**
	 * Transformar caracteres.
	 *
	 * @param original el original
	 * @return el string
	 */
	public static String transformarCaracteres(String original){
		String result = "";
		
		for(int i =0; i<original.length();i++){
			char ch = original.charAt(i);
			String resultCh = transformarCaracter(ch);		

			//inserto el caracter nuevo en el string
			result = result + resultCh;
		}
		logger.info("Cadena luego de transformacion: "+result);
		return result;
	}
	
	/**
	 * Transformar caracter.
	 *
	 * @param caracter el caracter
	 * @return el string
	 */
	public static String transformarCaracter(char caracter){
		String result = "";
		
		switch (caracter){
			case 'ç' : 	result = String.valueOf('\\'); break;
			case 'Ç' : 	result = String.valueOf('|'); break;
			case '¨' : 	result = String.valueOf('"'); break;
			case '·' : 	result = String.valueOf('#'); break;			
			case '/' : 	result = String.valueOf('&'); break;
			case '-' : 	result = String.valueOf('/'); break;
			case ')' : 	result = String.valueOf('('); break;
			case '=' : 	result = String.valueOf(')'); break;
			case '¡' : 	result = String.valueOf('='); break;
			case '_' : 	result = String.valueOf('?'); break;
			case '´' : 	result = String.valueOf('\''); break;
			case '(' : 	result = String.valueOf('*'); break;			
			case '¿' : 	result = String.valueOf('+'); break;
			case '+' : 	result = String.valueOf(']'); break;
			case '&' : 	result = String.valueOf('^'); break;
			case 'º' : 	result = String.valueOf('`'); break;
			case '`' : 	result = String.valueOf('['); break;
			case '^' : 	result = String.valueOf('{'); break;
			case '*' : 	result = String.valueOf('}'); break;
			case '\'' : result = String.valueOf('-'); break;
			case '?' : 	result = String.valueOf('_'); break;
			case 'Ñ' : 	result = String.valueOf(':'); break;
			case 'ñ' : 	result = String.valueOf(';'); break;
			case ';' : 	result = String.valueOf('<'); break;
			case ':' : 	result = String.valueOf('>'); break;
			case '"' : 	result = String.valueOf('@'); break;
			
			//casos especiales
			case '\\' :	result = "%A%"; break;
			case '#' :	result = "%B%"; break;
			case ']' :	result = "%C%"; break;
			case '[' :	result = "%D%"; break;
			case '}' :	result = "%E%"; break;
			case '{' :	result = "%F%"; break;
			case '<' :	result = "%G%"; break;
			case '>' :	result = "%H%"; break;
			case '@' :	result = "%I%"; break;
			case '¬' :	result = "%J%"; break;
			case '€' :	result = "%K%"; break;
			case '|' :	result = "%L%"; break;
			case 'Á' : 	result = "%M%"; break;
			case 'É' : 	result = "%N%"; break;
			case 'Í' : 	result = "%O%"; break;
			case 'Ó' : 	result = "%P%"; break;
			case 'Ú' : 	result = "%Q%"; break;
			case 'À' : 	result = "%R%"; break;
			case 'È' : 	result = "%S%"; break;
			case 'Ì' : 	result = "%T%"; break;
			case 'Ò' : 	result = "%U%"; break;
			case 'Ù' : 	result = "%V%"; break;
			case 'Ä' : 	result = "%W%"; break;
			case 'Ë' : 	result = "%X%"; break;
			case 'Ï' : 	result = "%Y%"; break;
			case 'Ö' : 	result = "%Z%"; break;
			case 'Ü' : 	result = "%A1%"; break;
			
			default : 	result = String.valueOf(caracter); break;
		}
		
		return result;		
	}
	
}