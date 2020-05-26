package es.map.ipsg.cuerpoescala;

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
import es.map.ipsg.action.cuerpoescala.AltaCuerposEscalaSpring;
import es.map.ipsg.form.CuerpoEscalaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class AltaCuerposEscalaTest.
 */
public class AltaCuerposEscalaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el alta cuerpos escala spring. */
	private AltaCuerposEscalaSpring altaCuerposEscalaSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/altaCuerposEscala", AltaCuerposEscalaSpring.class);
		
		//Se cargan los managers
		altaCuerposEscalaSpring = new AltaCuerposEscalaSpring();
		altaCuerposEscalaSpring.setCuerposEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		altaCuerposEscalaSpring.setGrupoManager((GrupoManager) getBean("grupoManager"));
		altaCuerposEscalaSpring.setCategoriaManager((CategoriaManager) getBean("categoriaManager"));
		altaCuerposEscalaSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		altaCuerposEscalaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		altaCuerposEscalaSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		
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
			
	        
			System.out.println("Iniciando test altaCuerposEscala");
			
			//Introducir los parametros de entrada para el test
			CuerpoEscalaForm formulario = new CuerpoEscalaForm();

			formulario.setDescripcion("Prueba");
			formulario.setCodigo("123");
			formulario.setIdCategoria("1");
			formulario.setIdGrupo("86");
			formulario.setVisibilidad(true);
			formulario.setAccion("Guardar");
			formulario.setCentroGestor("1");
			
					
			
			SpringForward rtrn = altaCuerposEscalaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test altaCuerposEscala");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test altaCuerposEscala con errores");
			fail();
		} 
		
		
	}
	
	
}
