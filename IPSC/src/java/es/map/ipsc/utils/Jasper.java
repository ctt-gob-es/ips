package es.map.ipsc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;
import es.map.ipsc.Constantes;

/**
 * El Class Jasper.
 */
public class Jasper{
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(Jasper.class);
	
	/**
	 * Generar PDF.
	 *
	 * @param bean el bean
	 * @param jasper el jasper
	 * @param ruta2 el ruta 2
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public byte[] generarPDF(JRBeanCollectionDataSource bean, File jasper, String ruta2) throws Exception{

		JasperPrint jasperPrint = null;
		JasperReport jasperReport = null;
		byte[] pdfasbytes = null;
		
		try{
			//Cargo el informe compilado en el objeto jasperreport
			jasperReport = (JasperReport)JRLoader.loadObject(jasper);
			
			//Modifico los parametros del generador de informes de jsaperreport
			jasperReport.setWhenNoDataType((byte)3);
			jasperReport.setProperty("ireport.encoding", "UTF-8");
			jasperReport.setProperty("ireport.scriptlethandling", "0");
			
			Map<String, String> parameters = new HashMap<String, String>();
			URL jasperPath = Jasper.class.getResource(Constantes.RECURSOS_JASPER);
			parameters.put(Constantes.JASPER_PATH, jasperPath.toString());
			
			logger.info("Inicio jasper");
			jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, bean);
			logger.info("Fin jasper");
		    
			pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
			
			if (ruta2.compareTo("")!=0){
				File salida = new File(ruta2);
		        try(OutputStream outputStream = new FileOutputStream (salida))
		        {
		        	outputStream.write(pdfasbytes);
		        }
			}
		}catch(Exception e){
			logger.error("Error en  generar Jasper: ",e);
		}
		
		return pdfasbytes;
	}
}