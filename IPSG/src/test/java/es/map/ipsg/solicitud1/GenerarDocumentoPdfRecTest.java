package es.map.ipsg.solicitud1;

import static org.mockito.Mockito.mock;

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
import es.map.ipsg.action.solicitud.GenerarDocumentoPdfRecSpring;
import es.map.ipsg.form.GestionarJustificanteForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class GenerarDocumentoPdfRecTest.
 */
public class GenerarDocumentoPdfRecTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el generar documento pdf rec spring. */
	private GenerarDocumentoPdfRecSpring generarDocumentoPdfRecSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/generarDocPdfRec", GenerarDocumentoPdfRecSpring.class);
		
		//Se cargan los managers
		generarDocumentoPdfRecSpring = new GenerarDocumentoPdfRecSpring();
	
		generarDocumentoPdfRecSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		generarDocumentoPdfRecSpring.setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
		generarDocumentoPdfRecSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		generarDocumentoPdfRecSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
		generarDocumentoPdfRecSpring.setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		generarDocumentoPdfRecSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		generarDocumentoPdfRecSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        
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
		
			System.out.println("Iniciando test generarDocumentoPdfRec");
	        
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("idRegistro", "38480");

	        this.request = request;
	        
	        GestionarJustificanteForm formulario = new GestionarJustificanteForm();
			
			SpringForward rtrn = generarDocumentoPdfRecSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			
			System.out.println("Finalizando test generarDocumentoPdfRec");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test generarDocumentoPdfRec con errores");
			fail();
		}
		
		
	}
	
	
}
