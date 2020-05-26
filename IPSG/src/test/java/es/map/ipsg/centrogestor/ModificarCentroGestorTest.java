package es.map.ipsg.centrogestor;

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

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.CentroGestorQuery;
import es.map.ipsg.action.centrogestor.ModificarCentroGestorSpring;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ModificarCentroGestorTest.
 */
public class ModificarCentroGestorTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el modificar centro gestor spring. */
	private ModificarCentroGestorSpring modificarCentroGestorSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarCentroGestorTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/modificarCentroGestor", ModificarCentroGestorSpring.class);
		
		//Se cargan los managers
		modificarCentroGestorSpring = new ModificarCentroGestorSpring();
		modificarCentroGestorSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		modificarCentroGestorSpring.setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
		modificarCentroGestorSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		modificarCentroGestorSpring.setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
		modificarCentroGestorSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		modificarCentroGestorSpring.setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));			

		
		
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
			
	        
			System.out.println("Iniciando test modificarCentroGestor");
			
			//Introducir los parametros de entrada para el test
			CentroGestorForm formulario = new CentroGestorForm();
			
			formulario.setId("12345");
			formulario.setIdMinisterio(7);
			formulario.setSiglas("SGIP");
			formulario.setDescripcion("SECRETARÍA GENERAL DE INSTITUCIONES PENITENCIARIAS");
			formulario.setEjercicio("2013");
			formulario.setCodigo("4966");
			formulario.setIdModelo("1");
			formulario.setVisibilidad(true);
			formulario.setAccion("Modificar");
			formulario.setFechaFinInhabil("");
			formulario.setFechaInicioInhabil("");
			formulario.setFechaSustitucion("");			
			formulario.setIdPlantilla(114L);
					
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.setId(Integer.valueOf(formulario.getId()));
			CentroGestorBean centroGestorAux= modificarCentroGestorSpring.getCentroGestorManager().buscarCentroGestorUnico(centroGestorQuery);
			if(centroGestorAux.getId() == null) {
				logger.error("No existe el centro gestor a modificar");
				return;				
			}
			
			SpringForward rtrn = modificarCentroGestorSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test modificarCentroGestor");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test modificarCentroGestor con errores");
			fail();
		} 
		
		
	}
	
	
}
