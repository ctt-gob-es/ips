package es.map.ipsg.solicitud1;

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

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.solicitud.BuscarSolicitudesRegistradasSpring;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoSolicitudManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class BuscarSolicitudesRegistradasTest.
 */
public class BuscarSolicitudesRegistradasTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el buscar solicitudes registradas spring. */
	private BuscarSolicitudesRegistradasSpring buscarSolicitudesRegistradasSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/buscarSolicitudesRegistradas", BuscarSolicitudesRegistradasSpring.class);
		
		//Se cargan los managers
		buscarSolicitudesRegistradasSpring = new BuscarSolicitudesRegistradasSpring();
		buscarSolicitudesRegistradasSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		buscarSolicitudesRegistradasSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		buscarSolicitudesRegistradasSpring.setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
		buscarSolicitudesRegistradasSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		buscarSolicitudesRegistradasSpring.setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		buscarSolicitudesRegistradasSpring.setSolicitudesRegistradasManager ((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		buscarSolicitudesRegistradasSpring.setTipoSolicitudManager((TipoSolicitudManager)getBean("tipoSolicitudManager"));
		buscarSolicitudesRegistradasSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		buscarSolicitudesRegistradasSpring.setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		buscarSolicitudesRegistradasSpring.setModeloManager((ModeloManager)getBean("modelosManager"));
		
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
			
			System.out.println("Iniciando test buscarSolicitudesRegistradas");
	        
			BuscarSolicitudesForm formulario = new BuscarSolicitudesForm();
			//Introducir los parametros de entrada para el test
			//formulario.setAccion("BUSCAR");
			
			SpringForward rtrn = buscarSolicitudesRegistradasSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
			}
			
			System.out.println("Finalizando test buscarSolicitudesRegistradas");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test buscarSolicitudesRegistradas con errores");
			fail();
		}
		
		
	}
	
	
}
