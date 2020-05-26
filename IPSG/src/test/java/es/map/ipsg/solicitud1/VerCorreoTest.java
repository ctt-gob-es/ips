package es.map.ipsg.solicitud1;

import static org.mockito.Mockito.mock;

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

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsg.action.solicitud.VerCorreoSpring;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.form.ContactarCiudadanoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CorreoElectronicoManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;


/**
 * El Class VerCorreoTest.
 */
public class VerCorreoTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver correo spring. */
	private VerCorreoSpring verCorreoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCorreoTest.class);
	
	
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
		mapping = springMappingManager.getMapping("/spring/verCorreo", VerCorreoSpring.class);
		
		//Se cargan los managers
		verCorreoSpring = new VerCorreoSpring();
		verCorreoSpring.setCorreoElectronicoManager((CorreoElectronicoManager)getBean("correoElectronicoManager"));
		verCorreoSpring.setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		verCorreoSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));


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
			
	        
			System.out.println("Iniciando test verCorreo");
			
			//Introducir los parametros de entrada para el test
			ContactarCiudadanoForm formulario = new ContactarCiudadanoForm();
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idCorreo = "1751";
			request.setParameter("correo", idCorreo);			
	        this.request = request; 
	        
	        try {
	        	CorreoElectronicoBean correoElectronicoBean = verCorreoSpring.getCorreoElectronicoManager().obtenerCorreoElectronico(Long.valueOf(idCorreo));
	        }catch(NullPointerException ex) {
	        	logger.error("No existe el correo electronico");
	        	return;
	        }
			
			        
			SpringForward rtrn = verCorreoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verCorreo");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verCorreo con errores");
			fail();
		} 
		
		
	}
	
	
}
