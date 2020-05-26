package es.map.ipsg.ministerio;

import static org.mockito.Mockito.mock;

import java.util.List;

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
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ipsg.action.ministerio.EliminarMinisterioSpring;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.form.MinisterioForm;
import es.map.ipsg.formaAcceso.EliminarFormasAccesoTest;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class EliminarMinisterioTest.
 */
public class EliminarMinisterioTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar ministerio spring. */
	private EliminarMinisterioSpring eliminarMinisterioSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarMinisterioTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarMinisterio", EliminarMinisterioSpring.class);
		
		//Se cargan los managers
		eliminarMinisterioSpring = new EliminarMinisterioSpring();
		eliminarMinisterioSpring.setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
		eliminarMinisterioSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		eliminarMinisterioSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarMinisterioSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		
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
			
	        
			System.out.println("Iniciando test eliminarMinisterio");
			MinisterioForm formulario = new MinisterioForm();
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String codMinisterio = "14";
			request.setParameter("Ministerio", codMinisterio);
	        this.request = request;   

			//Introducir los parametros de entrada para el test
	        
	        MinisterioQuery ministerioQuery = new MinisterioQuery();
			ministerioQuery.setId(Integer.parseInt(codMinisterio));
			MinisterioBean ministerioBean = eliminarMinisterioSpring.getMinisterioManager().buscarMinisterio(ministerioQuery);
			if(ministerioBean.getId() == null) {
				logger.error("No existe el ministerio a borrar");
				return;
			}
		
			SpringForward rtrn = eliminarMinisterioSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test eliminarMinisterio");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarMinisterio con errores");
			fail();
		}
		
		
	}
	
	
}
