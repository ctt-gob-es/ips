package es.map.ipsg.usuarios;

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
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.usuarios.ModificarUsuarioSpring;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.UsuarioForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ModificarUsuarioTest.
 */
public class ModificarUsuarioTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el modificar usuario spring. */
	private ModificarUsuarioSpring modificarUsuarioSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarUsuarioTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/modificarUsuario", ModificarUsuarioSpring.class);
		
		//Se cargan los managers
		modificarUsuarioSpring = new ModificarUsuarioSpring();
		modificarUsuarioSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		modificarUsuarioSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		
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
			
			System.out.println("Iniciando test modificarUsuario");
	        
			UsuarioForm formulario = new UsuarioForm();
			//Introducir los parametros de entrada para el test
			String idUsuario = "73";
		
			formulario.setAccion("MODIFICAR");
			formulario.setNif("97705911W");
			formulario.setNombre("Prueba");
			formulario.setPrimerApellido("Hola");
			formulario.setSegundoApellido("Prueba");
			formulario.setEmail("email@email.es");		
			formulario.setLogin("probando563");
			formulario.setPassword("hola");
			formulario.setIdPerfil("3");
			formulario.setId(idUsuario);
			formulario.setEstado("1");
			formulario.setIdCentroGestor("1");
			formulario.setTelefono("600000000");
			
	        this.request = request;
	        

			SpringForward rtrn = modificarUsuarioSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test modificarUsuario");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test modificarUsuario con errores");
			fail();
		}
		
		
	}
	
	
}
