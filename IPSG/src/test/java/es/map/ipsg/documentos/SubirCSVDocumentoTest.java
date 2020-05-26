package es.map.ipsg.documentos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.documentos.SubirCSVDocumentoSpring;
import es.map.ipsg.form.CrearCSVDocumentoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DocumentoManager;


/**
 * El Class SubirCSVDocumentoTest.
 */
public class SubirCSVDocumentoTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el subir CSV documento spring. */
	private SubirCSVDocumentoSpring subirCSVDocumentoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubirCSVDocumentoTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/subirCSVDocumento", SubirCSVDocumentoSpring.class);
		
		//Se cargan los managers
		subirCSVDocumentoSpring = new SubirCSVDocumentoSpring();
		subirCSVDocumentoSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));


		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        ServletOutputStream servletOutputStream;
        servletOutputStream = mock(ServletOutputStream.class);
       
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        //Carga del usuario
        GrantedAuthority[] authorities = new GrantedAuthority[1];
        authorities[0] = new GrantedAuthorityImpl("ROLE_ADMIN");
        User user = new User("admin", "4bf2b1832c785d31ffbcb5f8c10f5772", true, true, true, true, authorities);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "4bf2b1832c785d31ffbcb5f8c10f5772", authorities));
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
	        
			System.out.println("Iniciando test subirCSVDocumento");
				
			CrearCSVDocumentoForm formulario = new CrearCSVDocumentoForm();
			
			
			Path path = Paths.get("C:/Users/fbalsalo/Documents/prueba.csv");
			String name = "prueba.csv";
			String originalFileName = "prueba.csv";
			String contentType = "application/vnd.ms-excel";
			byte[] content = null;
			try {
			    content = Files.readAllBytes(path);
			} catch (final IOException e) {
			}
			if ( content == null )	{
				logger.error("No se ha podido ejecutar el junit porque no existe el documento para subir");
				return;
			}
			MultipartFile result = new MockMultipartFile(name,
			                     originalFileName, contentType, content);
			
			//Introducir los parametros de entrada para el test
			formulario.setFile(result);
			
			SpringForward rtrn = subirCSVDocumentoSpring.execute(mapping, formulario, request, response);
			if (rtrn.getPath() == null) {
				System.out.println("Finalizando test subirCSVDocumento");				
			}
			else throw new Exception("Error");
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test subirCSVDocumento con errores");
			fail();
		} 
		
		
	}
	
	
}
