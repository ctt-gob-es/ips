package es.map.ipsg.solicitud1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
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
import es.map.ipsg.action.solicitud.BuscarConsultarRegistrosRecSpring;
import es.map.ipsg.action.solicitud.ConsultarDesempleoSpring;
import es.map.ipsg.action.solicitud.ConsultarDiscapacidadSpring;
import es.map.ipsg.action.solicitud.ConsultarEmailEnviadosSpring;
import es.map.ipsg.form.ConsultarRegistrosRecForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CorreoElectronicoManager;
import es.map.ipsg.manager.DatosDesempleoManager;
import es.map.ipsg.manager.DatosDiscapacidadManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;


/**
 * El Class ConsultarEmailEnviadosTest.
 */
public class ConsultarEmailEnviadosTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el consultar email enviados spring. */
	private ConsultarEmailEnviadosSpring consultarEmailEnviadosSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/consultarEmailEnviados", ConsultarEmailEnviadosSpring.class);
		
		//Se cargan los managers
		consultarEmailEnviadosSpring = new ConsultarEmailEnviadosSpring();
		consultarEmailEnviadosSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		consultarEmailEnviadosSpring.setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		consultarEmailEnviadosSpring.setCorreoElectronicoManager((CorreoElectronicoManager)getBean("correoElectronicoManager"));
				
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
		
			System.out.println("Iniciando test consultarEmailEnviados");
	        
			SpringForm formulario = new SpringForm();

			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("solicitud", "10");
	        this.request = request;   

			SpringForward rtrn = consultarEmailEnviadosSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().equals("pages.errorModal")) throw new Exception("Error");
				if (rtrn.getPath().equals("pages.error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			
			System.out.println("Finalizando test consultarEmailEnviados");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test consultarEmailEnviados con errores");
			fail();
		}
		
		
	}
	
	
}
