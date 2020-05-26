package es.map.ipsg.plantilla;

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
import es.map.ipsg.action.plantilla.ActualizarCamposPlantillaPrincipalSpring;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;

/**
 * El Class ActualizarCamposPlantillaPrincipalTest.
 */
public class ActualizarCamposPlantillaPrincipalTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el actualizar campos plantilla principal spring. */
	private ActualizarCamposPlantillaPrincipalSpring actualizarCamposPlantillaPrincipalSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/actualizarPlantilla", ActualizarCamposPlantillaPrincipalSpring.class);
		
		//Se cargan los managers
		actualizarCamposPlantillaPrincipalSpring = new ActualizarCamposPlantillaPrincipalSpring();
		actualizarCamposPlantillaPrincipalSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		actualizarCamposPlantillaPrincipalSpring.setPlantillaPropiosManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
		
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
			
			System.out.println("Iniciando test actualizarCamposPlantillaPrincipal");
	        
			PlantillaForm formulario = new PlantillaForm();
			//Introducir los parametros de entrada para el test
		
			
			SpringForward rtrn = actualizarCamposPlantillaPrincipalSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			
			System.out.println("Finalizando test actualizarCamposPlantillaPrincipal");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test actualizarCamposPlantillaPrincipal con errores");
			fail();
		}
		
		
	}
	
	
}
