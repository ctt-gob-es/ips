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

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsg.action.solicitud.VerificarFechaNacimientoSpring;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DatosPersonalesManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerificarFechaNacimientoTest.
 */
public class VerificarFechaNacimientoTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el verificar fecha nacimiento spring. */
	private VerificarFechaNacimientoSpring verificarFechaNacimientoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarFechaNacimientoTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verificarFechaNacimiento", VerificarFechaNacimientoSpring.class);
		
		//Se cargan los managers
		verificarFechaNacimientoSpring = new VerificarFechaNacimientoSpring();
		verificarFechaNacimientoSpring.setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		verificarFechaNacimientoSpring.setDatosPersonalesManager((DatosPersonalesManager) getBean("datosPersonalesManager"));
		verificarFechaNacimientoSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
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
			
	        
			System.out.println("Iniciando test verificarFechaNacimiento");
			
			//Introducir los parametros de entrada para el test
			SpringForm formulario = new SpringForm();
					
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idSolicitud = "35498";
			
			request.setParameter("idSolicitud", idSolicitud);
	        this.request = request; 
	        SolicitudBean solicitudBean  = new SolicitudBean ();
	        try {
	        	solicitudBean = verificarFechaNacimientoSpring.getSolicitudManager().obtenerSolicitud(Long.valueOf(idSolicitud));
	        }catch(NullPointerException ex) {
	        	logger.error("No exista la solicitud para verificar la fecha de nacimiento");
	        	return;
	        }
	        
			SpringForward rtrn = verificarFechaNacimientoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verificarFechaNacimiento");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verificarFechaNacimiento con errores");
			fail();
		} 
		
		
	}
	
	
}
