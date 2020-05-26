package es.map.ipsc.convocatorias;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.spring.convocatorias.DescargarDocumentoSpring;


/**
 * El Class DescargarDocumentoTest.
 */
public class DescargarDocumentoTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el descargar documento spring. */
	private DescargarDocumentoSpring descargarDocumentoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/descargarDocumento", DescargarDocumentoSpring.class);
		
		//Se cargan los managers
		descargarDocumentoSpring = new DescargarDocumentoSpring();
		descargarDocumentoSpring.setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
				
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
			
	        Boolean existeDocumento = true;
			System.out.println("Iniciando test descargarDocumento");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
//			String idDocumento = "24653";
			String idDocumento = "24305";
			
			SpringForm formulario = new SpringForm();
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("id", idDocumento);
			request.setParameter("ent", "Convocatorias");
			
		
			
	        this.request = request; 
					
	        DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setId(Long.valueOf(idDocumento));
			DocumentoBean documentos = descargarDocumentoSpring.getDocumentoConvocatoriasManager().obtenerEstado(documentoQuery);
			
			if(documentos == null) {
				logger.error("No se ha podido ejecutar el junit porque no existe el documento en el servidor");
				return;
			}			
			
			request.setParameter("llamada", "Buscar");
			request.setParameter("doc", idDocumento.toString());			
	        this.request = request; 
	    	SpringForward rtrn = descargarDocumentoSpring.execute(mapping, formulario, request, response);
	    	if (rtrn.getPath() == null) {
	    		System.out.println("Finalizando test descargarDocumento");
	    	}
	    	else {
	    		throw new Exception("Error"); //Este junits solamente da error si contiene error.
	    	}			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test descargarDocumento con errores");
			fail();
		} 
		
		
	}
	
	
}
