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
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsg.action.plantilla.PlantillaGestionSpring;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class PlantillaGestionTest.
 */
public class PlantillaGestionTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el plantilla gestion spring. */
	private PlantillaGestionSpring plantillaGestionSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/plantillaGestion", PlantillaGestionSpring.class);
		
		//Se cargan los managers
		plantillaGestionSpring = new PlantillaGestionSpring();
		plantillaGestionSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		plantillaGestionSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		plantillaGestionSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        
        //Carga del usuario
        GrantedAuthority[] authorities = new GrantedAuthority[1];
        authorities[0] = new GrantedAuthorityImpl("ROLE_ADMIN");
        User user = new User("fjbalsavi", "c0860237c16d322155ff62e740b1f458", true, true, true, true, authorities);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "c0860237c16d322155ff62e740b1f458", authorities));
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
			System.out.println("Iniciando test plantillaGestion");
	        
			PlantillaForm formulario = new PlantillaForm();
			//Introducir los parametros de entrada para el test

			
			SpringForward rtrn = plantillaGestionSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test plantillaGestion");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test plantillaGestion con errores");
			fail();
		}
		
		
	}
	
	
}
