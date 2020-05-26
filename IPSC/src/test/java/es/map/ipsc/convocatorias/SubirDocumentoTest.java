package es.map.ipsc.convocatorias;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.spring.convocatorias.SubirDocumentoSpring;


/**
 * El Class SubirDocumentoTest.
 */
public class SubirDocumentoTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el subir documento spring. */
	private SubirDocumentoSpring subirDocumentoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubirDocumentoTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/subirDocumentosConvocatoria", SubirDocumentoSpring.class);
		
		//Se cargan los managers
		subirDocumentoSpring = new SubirDocumentoSpring();
		subirDocumentoSpring.setDocumentoManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
				
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
			
	        
			System.out.println("Iniciando test subirDocumento");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			CrearDocumentoForm formulario = new CrearDocumentoForm();
			
			Path path = Paths.get("C:/Users/fbalsalo/Documents/prueba.csv");
			byte[] content = null;
			try {
			    content = Files.readAllBytes(path);
			} catch (final IOException e) {
				
			}
			if ( content == null ) {
				logger.error("No se ha podido ejecutar el junit porque no existe el documento para subir");
				return;
			}
			
			String name = "prueba.csv";
			String originalFileName = "prueba.csv";
			String contentType = "application/vnd.ms-excel";
			
			MultipartFile result = new MockMultipartFile(name,
			                     originalFileName, contentType, content);
			formulario.setFile(result);
			formulario.setIdSolicitud("8383");
			formulario.setNombre("Prueba");
			formulario.setDescripcion("Prueba");
					
			
			SpringForward rtrn = subirDocumentoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test subirDocumento");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test subirDocumento con errores");
			fail();
		} 
		
		
	}
	
	
}
