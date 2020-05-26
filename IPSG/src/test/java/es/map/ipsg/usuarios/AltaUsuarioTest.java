package es.map.ipsg.usuarios;

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
import es.map.ipsg.action.usuarios.AltaUsuarioSpring;
import es.map.ipsg.form.UsuarioForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.PerfilManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class AltaUsuarioTest.
 */
public class AltaUsuarioTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el alta usuario spring. */
	private AltaUsuarioSpring altaUsuarioSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/altaUsuario", AltaUsuarioSpring.class);
		
		//Se cargan los managers
		altaUsuarioSpring = new AltaUsuarioSpring();
		altaUsuarioSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		altaUsuarioSpring.setPerfilManager((PerfilManager) getBean("perfilManager"));
		altaUsuarioSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		altaUsuarioSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		
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
			
			System.out.println("Iniciando test altaUsuario");
	        
			UsuarioForm formulario = new UsuarioForm();
			//Introducir los parametros de entrada para el test
		
			formulario.setAccion("Paginar");
			formulario.setNif("15068642P");
			formulario.setNombre("Prueba");
			formulario.setPrimerApellido("Hola");
			formulario.setSegundoApellido("Prueba");
			formulario.setEmail("dsfs@gmail.com");
			formulario.setEmail("968131313");
			formulario.setLogin("probando563");
			formulario.setPassword("hola");
			formulario.setIdPerfil("4");
			

			
			SpringForward rtrn = altaUsuarioSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test altaUsuario");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test altaUsuario con errores");
			fail();
		}
		
		
	}
	
	
}
