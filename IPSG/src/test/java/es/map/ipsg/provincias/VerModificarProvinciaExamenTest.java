package es.map.ipsg.provincias;

import static org.mockito.Mockito.mock;

import java.util.List;

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
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ipsg.action.provincias.VerModificarProvinciaExamenSpring;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.form.ModificarProvinciasExamenForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ProvinciaExamenManager;


/**
 * El Class VerModificarProvinciaExamenTest.
 */
public class VerModificarProvinciaExamenTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver modificar provincia examen spring. */
	private VerModificarProvinciaExamenSpring verModificarProvinciaExamenSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarProvinciaExamenTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verModificarProvinciaExamen", VerModificarProvinciaExamenSpring.class);
		
		//Se cargan los managers
		verModificarProvinciaExamenSpring = new VerModificarProvinciaExamenSpring();
		verModificarProvinciaExamenSpring.setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));

		
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
			
			System.out.println("Iniciando test verModificarProvinciaExamen");
	        
			ModificarProvinciasExamenForm formulario = new ModificarProvinciasExamenForm();
			//Introducir los parametros de entrada para el test
		
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idProvincia = "15";
			request.setParameter("registro", idProvincia);
	        this.request = request;   
			
	        List<ProvinciaExamenBean> provincias = null;
			ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
			provinciaExamenQuery.setId(Integer.valueOf(idProvincia));
			
			provincias = verModificarProvinciaExamenSpring.getProvinciaExamenManager().buscarProvinciaExamenCombo(provinciaExamenQuery);
			if(provincias.isEmpty()) {
				logger.error("No existe la provincia examen");
				return;
			}
			
			SpringForward rtrn = verModificarProvinciaExamenSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			
			System.out.println("Finalizando test verModificarProvinciaExamen");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verModificarProvinciaExamen con errores");
			fail();
		}
		
		
	}
	
	
}
