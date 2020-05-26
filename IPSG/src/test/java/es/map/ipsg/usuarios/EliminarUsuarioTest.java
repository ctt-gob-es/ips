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

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.usuarios.EliminarUsuarioSpring;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class EliminarUsuarioTest.
 */
public class EliminarUsuarioTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar usuario spring. */
	private EliminarUsuarioSpring eliminarUsuarioSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarUsuarioTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarUsuario", EliminarUsuarioSpring.class);
		
		//Se cargan los managers
		eliminarUsuarioSpring = new EliminarUsuarioSpring();	
		eliminarUsuarioSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarUsuarioSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
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
			
			System.out.println("Iniciando test eliminarUsuario");
	        
			SpringForm formulario = new SpringForm();
			//Introducir los parametros de entrada para el test
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");	
			String idUsuario = "73";
			request.setParameter("id", idUsuario);			
	        this.request = request; 
			
	        UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setId(Integer.parseInt(idUsuario));
			UsuarioBean usuarioBean ;
			usuarioBean = eliminarUsuarioSpring.getUsuarioManager().buscarUsuario(usuarioQuery);
			if(usuarioBean == null) {
				logger.error("No existe el id del usuario para cambiar la contraseña");
				return;
			}
	        
			SpringForward rtrn = eliminarUsuarioSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test eliminarUsuario");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarUsuario con errores");
			fail();
		}
		
		
	}
	
	
}
