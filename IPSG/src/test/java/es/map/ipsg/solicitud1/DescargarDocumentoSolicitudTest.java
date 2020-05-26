package es.map.ipsg.solicitud1;

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
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.action.solicitud.DescargarDocumentoSolicitudSpring;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.provincias.EliminarProvinciasExamenTest;


/**
 * El Class DescargarDocumentoSolicitudTest.
 */
public class DescargarDocumentoSolicitudTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el descargar documento solicitud spring. */
	private DescargarDocumentoSolicitudSpring descargarDocumentoSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoSolicitudTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/descargarDocumentoSolicitud", DescargarDocumentoSolicitudSpring.class);
		
		//Se cargan los managers
		descargarDocumentoSolicitudSpring = new DescargarDocumentoSolicitudSpring();
		descargarDocumentoSolicitudSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
				
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
		
			System.out.println("Iniciando test descargarDocumentoSolicitud");
	        
			SpringForm formulario = new SpringForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idDocumento = "25152";
			request.setParameter("id", idDocumento);
			request.setParameter("ent", "Solicitudes");
	        this.request = request; 

	        DocumentoQuery documentoQuery = new DocumentoQuery();
	        documentoQuery.setId(Long.valueOf(idDocumento));
	        try{
	        	DocumentoBean doc = descargarDocumentoSolicitudSpring.getDocumentosManager().obtenerDocumento(documentoQuery);
	        	byte[] salida = descargarDocumentoSolicitudSpring.obtenerDocumentoSolicitudSalida(doc,"Solicitudes");
	        	if (salida == null) {
	        		logger.error("El archivo no se encuentra en el servidor local");
	        		return;
	        	}
	        }catch(NullPointerException ex) {
	        	logger.error("No se encuentro el id del documento");
	        	return;
	        }
			SpringForward rtrn = descargarDocumentoSolicitudSpring.execute(mapping, formulario, request, response);
			
			if (rtrn.getPath() == null) {
				System.out.println("Finalizando test descargarDocumentoSolicitud");
			}
			else throw new Exception("Error");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test descargarDocumentoSolicitud con errores");
			fail();
		}
		
		
	}
	
	
}
