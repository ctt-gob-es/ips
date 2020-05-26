package es.map.ipsg.aviso;

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
import es.map.ipsg.action.aviso.EliminarAvisoSpring;
import es.map.ipsg.bean.AvisoBean;
import es.map.ipsg.form.AvisoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.AvisoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class EliminarAvisoTest.
 */
public class EliminarAvisoTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar aviso spring. */
	private EliminarAvisoSpring eliminarAvisoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarAvisoTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verEliminarAviso", EliminarAvisoSpring.class);
		
		//Se cargan los managers
		eliminarAvisoSpring = new EliminarAvisoSpring();
		eliminarAvisoSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarAvisoSpring.setAvisoManager((AvisoManager) getBean("avisoManager"));
		eliminarAvisoSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		
		
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
			
	        
			System.out.println("Iniciando test eliminarAviso");
			
			//Introducir los parametros de entrada para el test
			AvisoForm formulario = new AvisoForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idAviso = "157";
			request.setParameter("id", idAviso);
	        this.request = request; 
			
					
	        Boolean existeAviso = false;
	        try {
	        	AvisoBean avisoBean = eliminarAvisoSpring.getAvisoManager().obtenerAviso(Integer.valueOf(idAviso));	        	
	        }
	        catch(Exception ex){
	        	logger.error("No existe el aviso que se va a borrar");
	        	return;	        	
	        }
			SpringForward rtrn = eliminarAvisoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test eliminarAviso");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarAviso con errores");
			fail();
		} 
		
		
	}
	
	
}
