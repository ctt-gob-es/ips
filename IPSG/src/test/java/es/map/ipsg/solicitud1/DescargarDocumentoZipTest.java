package es.map.ipsg.solicitud1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.solicitud.DescargarDocumentoZipSpring;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;


/**
 * El Class DescargarDocumentoZipTest.
 */
public class DescargarDocumentoZipTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el descargar documento zip spring. */
	private DescargarDocumentoZipSpring descargarDocumentoZipSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/descargarDocumentoZip", DescargarDocumentoZipSpring.class);
		
		//Se cargan los managers
		descargarDocumentoZipSpring = new DescargarDocumentoZipSpring();
		descargarDocumentoZipSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		descargarDocumentoZipSpring.setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		
				
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
		
			System.out.println("Iniciando test descargarDocumentoZip");
	        
			BuscarSolicitudesForm formulario = new BuscarSolicitudesForm();
	 
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("ent", "Solicitudes");			
	        this.request = request; 
	        String[] solicitudes = {"41444"};
	        formulario.setSolicitudesSel(solicitudes);
	        formulario.setStrAnexo("S");
	        formulario.setStrJustificaPago("N");
	        formulario.setStrRegistroPDF("N");
	        formulario.setStrRegistroXML("N");
			SpringForward rtrn = descargarDocumentoZipSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			
			System.out.println("Finalizando test descargarDocumentoZip");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test descargarDocumentoZip con errores");
			fail();
		}
		
		
	}
	
	
}
