package es.map.ipsg.cuadroMando;

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
import es.map.ipsg.action.cuadroMando.BuscarCuadroMandoSpring;
import es.map.ipsg.form.ConsultarCuadroMandoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasViewManager;
import es.map.ipsg.manager.DescargaModelo790Manager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class BuscarCuadroMandoTest.
 */
public class BuscarCuadroMandoTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el buscar cuadro mando spring. */
	private BuscarCuadroMandoSpring buscarCuadroMandoSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/buscarCuadroMando", BuscarCuadroMandoSpring.class);
		
		//Se cargan los managers
		buscarCuadroMandoSpring = new BuscarCuadroMandoSpring();
		buscarCuadroMandoSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		buscarCuadroMandoSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		buscarCuadroMandoSpring.setEstadosConvocatoriaManager((EstadoConvocatoriaManager) getBean("estadosConvocatoriaManager"));
		buscarCuadroMandoSpring.setConvocatoriasViewManager((ConvocatoriasViewManager) getBean("convocatoriasViewManager"));
		buscarCuadroMandoSpring.setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
		buscarCuadroMandoSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		buscarCuadroMandoSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean ("registroSolicitudManager"));
		buscarCuadroMandoSpring.setDescargaModelo790Manager((DescargaModelo790Manager) getBean("descargaModelo790Manager"));
		buscarCuadroMandoSpring.setMinisteriosManager((MinisterioManager) getBean("ministeriosManager"));
		buscarCuadroMandoSpring.setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
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
			
	        
			System.out.println("Iniciando test buscarCuadroMando");
			
			//Introducir los parametros de entrada para el test
			ConsultarCuadroMandoForm formulario = new ConsultarCuadroMandoForm();
			formulario.setAccion("BUSCAR");	
			formulario.setDsCentroGestor("1");
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("perfilUsuario", "admin");
	        this.request = request; 
			
			
			SpringForward rtrn = buscarCuadroMandoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test buscarCuadroMando");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test buscarCuadroMando con errores");
			fail();
		} 
		
		
	}
	
	
}
