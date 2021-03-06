package es.map.ipsg.solicitudPresencial;

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
import es.map.ipsg.action.solicitudPresencial.VerBuscarSolicitudPresencialSpring;
import es.map.ipsg.form.BuscarSolicitudesPresencialesForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoSolicitudManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerBuscarSolicitudPresencialTest.
 */
public class VerBuscarSolicitudPresencialTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver buscar solicitud presencial spring. */
	private VerBuscarSolicitudPresencialSpring verBuscarSolicitudPresencialSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/verBuscarSolicitudPresencial", VerBuscarSolicitudPresencialSpring.class);
		
		//Se cargan los managers
		verBuscarSolicitudPresencialSpring = new VerBuscarSolicitudPresencialSpring();	
		verBuscarSolicitudPresencialSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		verBuscarSolicitudPresencialSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		verBuscarSolicitudPresencialSpring.setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
		verBuscarSolicitudPresencialSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		verBuscarSolicitudPresencialSpring.setSolicitudesRegistradasManager ((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		verBuscarSolicitudPresencialSpring.setTipoSolicitudManager((TipoSolicitudManager)getBean("tipoSolicitudManager"));
		verBuscarSolicitudPresencialSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		verBuscarSolicitudPresencialSpring.setModeloManager((ModeloManager)getBean("modelosManager"));
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
			
			System.out.println("Iniciando test verBuscarSolicitudPresencial");
	        
			BuscarSolicitudesPresencialesForm formulario = new BuscarSolicitudesPresencialesForm();
			
			SpringForward rtrn = verBuscarSolicitudPresencialSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test verBuscarSolicitudPresencial");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verBuscarSolicitudPresencial con errores");
			fail();
		}
		
		
	}
	
	
}
