package es.map.ipsg.formaAcceso;

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
import es.map.ipsg.action.formaAcceso.EliminarFormasAccesoSpring;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.form.FormasAccesoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class EliminarFormasAccesoTest.
 */
public class EliminarFormasAccesoTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar formas acceso spring. */
	private EliminarFormasAccesoSpring eliminarFormasAccesoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarFormasAccesoTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarFormasAcceso", EliminarFormasAccesoSpring.class);
		
		//Se cargan los managers
		eliminarFormasAccesoSpring = new EliminarFormasAccesoSpring();
		eliminarFormasAccesoSpring.setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		eliminarFormasAccesoSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		eliminarFormasAccesoSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarFormasAccesoSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));

	
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
			
	        
			System.out.println("Iniciando test eliminarFormasAcceso");
				
			FormasAccesoForm formulario = new FormasAccesoForm();
			
			//Introducir los parametros de entrada para el test
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idFormaAcceso = "86";
			request.setParameter("id", idFormaAcceso);
	        this.request = request;   
	        
	        FormaAccesoBean formaAccesoBean;
	        try {
	        	formaAccesoBean = eliminarFormasAccesoSpring.getFormaAccesoManager().obtenerFormaAcceso(Integer.valueOf(idFormaAcceso));
	        }catch(NullPointerException ex) {
	        	logger.error("La forma de acceso no existe");
	        	return;
	        }
	        
			SpringForward rtrn = eliminarFormasAccesoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test eliminarFormasAcceso");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarFormasAcceso con errores");
			fail();
		} 
		
		
	}
	
	
}
