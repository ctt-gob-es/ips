package es.map.ipsg.convocatoria;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import es.map.ipsg.action.convocatoria.DescargarDocumentoSpring;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DocumentoManager;

/**
 * El Class DescargarDocumentoTest.
 */
public class DescargarDocumentoTest extends AbstractSpringCommonTestCaseIPSG {

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
		mapping = springMappingManager.getMapping("/spring/descargarDocumento", DescargarDocumentoSpring.class);
		
		//Se cargan los managers
		descargarDocumentoSpring = new DescargarDocumentoSpring();
		descargarDocumentoSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		
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
			
	        
			System.out.println("Iniciando test descargarDocumento");
			
			//Introducir los parametros de entrada para el test
			SpringForm formulario = new SpringForm();
		
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idDocumento = "24287";
			request.setParameter("ent", "Convocatorias");
			request.setParameter("id", idDocumento);
	        this.request = request; 
			
	        DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setId(Long.parseLong(idDocumento));
			try {
				DocumentoBean doc = descargarDocumentoSpring.getDocumentosManager().obtenerDocumento(documentoQuery);
				byte[] salida = descargarDocumentoSpring.obtenerDocumentoSalida(doc,"Convocatorias");
				if(salida==null) {
					logger.error("No existe el documento en el servidor local");
					return;
				}				
			}catch(NullPointerException ex) {
				logger.error("No existe el documento a descargar");
				return;
			}
						
			SpringForward rtrn = descargarDocumentoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test descargarDocumento");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test descargarDocumento con errores");
			fail();
		} 
		
		
	}
	
	
}
