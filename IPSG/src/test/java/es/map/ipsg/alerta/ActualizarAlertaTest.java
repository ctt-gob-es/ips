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
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.alerta.ActualizarAlertaSpring;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AlertaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModoAlertaManager;
import es.map.ipsg.manager.TipoAlertaManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ActualizarAlertaTest.
 */
public class ActualizarAlertaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el actualizar alerta spring. */
	private ActualizarAlertaSpring actualizarAlertaSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarAlertaTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/ActualizarAlerta", ActualizarAlertaSpring.class);
		
		//Se cargan los managers
		actualizarAlertaSpring = new ActualizarAlertaSpring();
		actualizarAlertaSpring.setAlertaManager((AlertaManager) getBean("alertaManager"));
		actualizarAlertaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		actualizarAlertaSpring.setModoAlertaManager((ModoAlertaManager) getBean("modoAlertaManager"));
		actualizarAlertaSpring.setTipoAlertaManager((TipoAlertaManager) getBean("tipoAlertaManager"));
		actualizarAlertaSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		actualizarAlertaSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		
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
			
	        
			System.out.println("Iniciando test actualizarAlerta");
			
			//Introducir los parametros de entrada para el test
			AlertaForm formulario = new AlertaForm();
			formulario.setId(2313);
			formulario.setIdCentroGestor("5");
			formulario.setIdModo(1);
			formulario.setIdTipo(4);
			String[] usuariosSelec = {};
			formulario.setIdUsuarioSeleccionados(usuariosSelec);
			formulario.setSubmit("Guardar");
			
			Boolean comprobacionAlerta = false;
			AlertaBean alertaBean = new AlertaBean();
			String[] usuarios=formulario.getIdUsuarioSeleccionados();
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin("admin"); //Le pasamos el login del que esta logueado
			UsuarioBean usuarioBean = actualizarAlertaSpring.getUsuarioManager().buscarUsuario(usuarioQuery);
			Usuario usuario = actualizarAlertaSpring.getUsuarioManager().toUsuario(usuarioBean);
			 String resultado= actualizarAlertaSpring.buscarRegistro(alertaBean,formulario,usuarios,formulario.getId(),usuario,"Guardar");
				if (resultado!=null && resultado.equals("0")){
					logger.error("No existe la alerta o ya existe una alerta con esos campos");
					return;
				}
			
			SpringForward rtrn = actualizarAlertaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test actualizarAlerta");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test actualizarAlerta con errores");
			fail();
		} 
		
		
	}
	
	
}
