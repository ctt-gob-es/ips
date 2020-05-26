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
import es.map.ipsg.action.solicitud.BuscarSolicitudesIncidenciasSpring;
import es.map.ipsg.form.SolicitudesIncidenciasForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.EstadoSolicitudManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoSolicitudManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class BuscarSolicitudesIncidenciasTest.
 */
public class BuscarSolicitudesIncidenciasTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el buscar solicitudes incidencias spring. */
	private BuscarSolicitudesIncidenciasSpring buscarSolicitudesIncidenciasSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/buscarSolicitudesIncidencias", BuscarSolicitudesIncidenciasSpring.class);
		
		//Se cargan los managers
		buscarSolicitudesIncidenciasSpring = new BuscarSolicitudesIncidenciasSpring();
		buscarSolicitudesIncidenciasSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		buscarSolicitudesIncidenciasSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		buscarSolicitudesIncidenciasSpring.setMinisterioManager ((MinisterioManager) getBean("ministeriosManager"));
		buscarSolicitudesIncidenciasSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		buscarSolicitudesIncidenciasSpring.setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		buscarSolicitudesIncidenciasSpring.setSolicitudesRegistradasManager ((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		buscarSolicitudesIncidenciasSpring.setTipoSolicitudManager((TipoSolicitudManager)getBean("tipoSolicitudManager"));
		buscarSolicitudesIncidenciasSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		buscarSolicitudesIncidenciasSpring.setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
		buscarSolicitudesIncidenciasSpring.setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
		buscarSolicitudesIncidenciasSpring.setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		buscarSolicitudesIncidenciasSpring.setModeloManager((ModeloManager)getBean("modelosManager"));
		buscarSolicitudesIncidenciasSpring.setEstadosConvocatoriaManager((EstadoConvocatoriaManager)getBean("estadosConvocatoriaManager"));
		
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
			
			System.out.println("Iniciando test buscarSolicitudesIncidencias");
	        
			SolicitudesIncidenciasForm formulario = new SolicitudesIncidenciasForm();
			//Introducir los parametros de entrada para el test
			//formulario.setAccion("BUSCAR");
			
			SpringForward rtrn = buscarSolicitudesIncidenciasSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
			}
			
			System.out.println("Finalizando test buscarSolicitudesIncidencias");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test buscarSolicitudesIncidencias con errores");
			fail();
		}
		
		
	}
	
	
}
