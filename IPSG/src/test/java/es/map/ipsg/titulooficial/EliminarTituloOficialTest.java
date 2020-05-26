package es.map.ipsg.titulooficial;

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
import es.map.ipsg.action.titulooficial.EliminarTituloOficialSpring;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class EliminarTituloOficialTest.
 */
public class EliminarTituloOficialTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar titulo oficial spring. */
	private EliminarTituloOficialSpring eliminarTituloOficialSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarTituloOficialTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarTituloOficial", EliminarTituloOficialSpring.class);
		
		//Se cargan los managers
		eliminarTituloOficialSpring = new EliminarTituloOficialSpring();	
		eliminarTituloOficialSpring.setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
		eliminarTituloOficialSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarTituloOficialSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
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
			
			System.out.println("Iniciando test eliminarTituloOficial");
	        
			SpringForm formulario = new SpringForm();
			//Introducir los parametros de entrada para el test
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idTitulo = "5922";
			request.setParameter("id", idTitulo);			
	        this.request = request; 
			
	        TituloOficialBean tituloOficialBean;
			try {
				tituloOficialBean = eliminarTituloOficialSpring.getTituloOficialManager().obtenerTituloOficial(Integer.valueOf(idTitulo));	
			}catch(NullPointerException ex) {
				logger.error("No existe el id del titulo oficial a eliminar");
				return;
			}
			
	        
			SpringForward rtrn = eliminarTituloOficialSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test eliminarTituloOficial");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarTituloOficial con errores");
			fail();
		}
		
		
	}
	
	
}
