package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.form.FileUploadForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.spring.solicitudes.DocumentosSolicitudSpring;
import es.map.ipsc.spring.solicitudes.FileUploadSpring;


/**
 * El Class FileUploadTest.
 */
public class FileUploadTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el file upload spring. */
	private FileUploadSpring fileUploadSpring;
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/**
	 * Carga de los datos para el Junit.
	 *
	 * @throws Exception el exception
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//Carga del mapping
		springMappingManager = (SpringMappingManager) getBean("springMappingManager");
		mapping = springMappingManager.getMapping("/secure/fileUpload", FileUploadSpring.class);
		
		//Se cargan los managers
		fileUploadSpring = new FileUploadSpring();
		

		
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
	        
			System.out.println("Iniciando test fileUpload");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			FileUploadForm formulario = new FileUploadForm();
			Path path = Paths.get("C:/Users/fbalsalo/Documents/prueba.csv");
			String name = "prueba.csv";
			String originalFileName = "prueba.csv";
			String contentType = "application/vnd.ms-excel";
			byte[] content = null;
			try {
			    content = Files.readAllBytes(path);
			} catch (final IOException e) {
			}
//			MultipartFile result = new MockMultipartFile(name,
//			                     originalFileName, contentType, content);
//			formulario.setFile(result);
			
								
	    	SpringForward rtrn = fileUploadSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test fileUpload");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test fileUpload con errores");
			fail();
		} 
		
		
	}
	
	
}
