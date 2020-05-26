package es.map.ipsg.form;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.Constantes;


/**
 * El Class CrearCSVDocumentoForm.
 */
public class CrearCSVDocumentoForm extends SpringForm {

	/** el file. */
	private MultipartFile file;
	

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearCSVDocumentoForm.class);
	
	/** La constante STRING_ERRORFICHERODOCUMENTO. */
	private static final String STRING_ERRORFICHERODOCUMENTO = "errorFicheroDocumento";
	
	/** La constante STRING_DSERRORFICHERODOCUMENTO. */
	private static final String STRING_DSERRORFICHERODOCUMENTO = "dsErrorFicheroDocumento";
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		logger.info("Validando");
		SpringErrors SpringErrors = new SpringErrors();
		
		
		if (file!=null) {
			// error si el fichero no tiene nombre
			if(file.getName()==null || file.getName().equalsIgnoreCase("")){
				request.setAttribute("errorNombreDocumento", "errorNombreDocumento");
				SpringErrors.add("dsErrorNombreDocumento", new SpringMessage(
						"field.documento.errorNombre"));
			}
			
			// error si el fichero esta vacio
			if(file.getSize()<=0){
				request.setAttribute(STRING_ERRORFICHERODOCUMENTO, STRING_ERRORFICHERODOCUMENTO);
				SpringErrors.add(STRING_DSERRORFICHERODOCUMENTO, new SpringMessage(
						"field.documento.errorFile"));
			} else {
				// error si el fichero no es csv
				// no es csv si no tiene el formato myme-type correcto
				// no es csv si cada linea no tiene el numero de campos preestablecidos en el csv (de 3 a 5)
				if(!(Constantes.MYME_CSV_EXCEL.equals(file.getContentType()) || Constantes.MYME_CSV_APLICATTION.equals(file.getContentType()) || Constantes.MYME_CSV_TEXT.equals(file.getContentType()))
				   || !numeroCamposCSV(file) 		
				){
					request.setAttribute(STRING_ERRORFICHERODOCUMENTO, STRING_ERRORFICHERODOCUMENTO);
					SpringErrors.add(STRING_DSERRORFICHERODOCUMENTO, new SpringMessage(
							"field.documento.errorCSV"));
				}
						
			}
		} else {
			request.setAttribute(STRING_ERRORFICHERODOCUMENTO, STRING_ERRORFICHERODOCUMENTO);
			SpringErrors.add(STRING_DSERRORFICHERODOCUMENTO, new SpringMessage(
					"field.documento.errorSesionCSV"));
		}
	
		
	return SpringErrors;
	}

	
	/**
	 * Numero campos CSV.
	 *
	 * @param file el file
	 * @return verdadero, si tiene exito
	 */
	public boolean numeroCamposCSV(MultipartFile file) {
		
		boolean numeroCamposCorrecto = true;
		String[] solicitudPresencial = null;
        String line = "";
        String cvsSplitBy = ";";
        int numeroCamposCSV = 0;
                
        try (InputStreamReader isr = new InputStreamReader(file.getInputStream());
        	 BufferedReader bufferedReader = new BufferedReader(isr))
       	{
           	while ((line = bufferedReader.readLine()) != null) {
           		line = line.trim();
            	if (!line.equals("")) {
            		solicitudPresencial = line.split(cvsSplitBy);
                	numeroCamposCSV = solicitudPresencial.length;
                	if (numeroCamposCSV>5) {
                		numeroCamposCorrecto = false;
                		break;
                	}
            	}
           	}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            numeroCamposCSV = 0;
        } catch (IOException e) {
            e.printStackTrace();
            numeroCamposCSV = 0;
        }
		return numeroCamposCorrecto;
	}

	
	/**
	 * Obtiene el file.
	 *
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * Establece el file.
	 *
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
