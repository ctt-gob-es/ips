package es.map.ipsg.alerta;

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
import es.map.ips.model.AlertaQuery;
import es.map.ipsg.action.alerta.EliminarAlertaSpring;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.form.AlertaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class EliminarAlertaTest.
 */
public class EliminarAlertaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar alerta spring. */
	private EliminarAlertaSpring eliminarAlertaSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarAlertaTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarAlerta", EliminarAlertaSpring.class);
		
		//Se cargan los managers
		eliminarAlertaSpring = new EliminarAlertaSpring();
		eliminarAlertaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarAlertaSpring.setAlertaManager((AlertaManager) getBean("alertaManager"));
		eliminarAlertaSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		
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
			
	        
			System.out.println("Iniciando test eliminarAlerta");
			
			//Introducir los parametros de entrada para el test
			AlertaForm formulario = new AlertaForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idAlerta = "1835";
			request.setParameter("id", idAlerta);
	        this.request = request; 
					
	        Boolean existeAlerta = false;
	        AlertaQuery alertaQuery = new AlertaQuery();
	        alertaQuery.setId(Integer.valueOf(idAlerta));
	        try {
	        	AlertaBean alertaBean = eliminarAlertaSpring.getAlertaManager().obtenerAlerta(Integer.valueOf(idAlerta));
	        }
	        catch(Exception ex){
	        	logger.error("No existe la alerta que se va a borrar");
	        	return;	        	
	        }
	        
	        
			SpringForward rtrn = eliminarAlertaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test eliminarAlerta");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarAlerta con errores");
			fail();
		} 
		
		
	}
	
	
}
