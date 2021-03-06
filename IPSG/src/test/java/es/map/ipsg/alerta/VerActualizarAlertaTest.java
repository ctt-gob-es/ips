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

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.alerta.VerActualizarAlertaSpring;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.form.AlertaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModoAlertaManager;
import es.map.ipsg.manager.TipoAlertaManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerActualizarAlertaTest.
 */
public class VerActualizarAlertaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver actualizar alerta spring. */
	private VerActualizarAlertaSpring verActualizarAlertaSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerActualizarAlertaSpring.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verActualizarAlerta", VerActualizarAlertaSpring.class);
		
		//Se cargan los managers
		verActualizarAlertaSpring = new VerActualizarAlertaSpring();
		verActualizarAlertaSpring.setAlertaManager((AlertaManager) getBean("alertaManager"));
		verActualizarAlertaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		verActualizarAlertaSpring.setModoAlertaManager((ModoAlertaManager) getBean("modoAlertaManager"));
		verActualizarAlertaSpring.setTipoAlertaManager((TipoAlertaManager) getBean("tipoAlertaManager"));
		verActualizarAlertaSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		verActualizarAlertaSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		
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
			
	        
			System.out.println("Iniciando test verActualizarAlerta");
			Boolean existeAlerta = false;
			//Introducir los parametros de entrada para el test
			AlertaForm formulario = new AlertaForm();
			int idAlerta = 1835;
			formulario.setId(idAlerta);
					
			try {
	        	AlertaBean alertaBean = verActualizarAlertaSpring.getAlertaManager().obtenerAlerta(idAlerta);
	        }
	        catch(Exception ex){
	        	logger.error("No existe la alerta");
	        	return;	        	
	        }
			SpringForward rtrn = verActualizarAlertaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verActualizarAlerta");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verActualizarAlerta con errores");
			fail();
		} 
		
		
	}
	
	
}
