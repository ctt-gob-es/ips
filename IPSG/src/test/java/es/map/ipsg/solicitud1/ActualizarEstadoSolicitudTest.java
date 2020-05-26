package es.map.ipsg.solicitud1;

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
import es.map.ipsg.action.solicitud.ActualizarEstadoSolicitudSpring;
import es.map.ipsg.form.ActualizarEstadoSolicitudForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CorreoElectronicoManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.EstadoSolicitudManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.UsuarioManager;

import static org.mockito.Mockito.*;


/**
 * El Class ActualizarEstadoSolicitudTest.
 */
public class ActualizarEstadoSolicitudTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el actualizar estado solicitud spring. */
	private ActualizarEstadoSolicitudSpring actualizarEstadoSolicitudSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/actualizarEstadoSolicitud", ActualizarEstadoSolicitudSpring.class);
		
		//Se cargan los managers
		actualizarEstadoSolicitudSpring = new ActualizarEstadoSolicitudSpring();
		actualizarEstadoSolicitudSpring.setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		actualizarEstadoSolicitudSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		actualizarEstadoSolicitudSpring.setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		actualizarEstadoSolicitudSpring.setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
		actualizarEstadoSolicitudSpring.setCorreoElectronicoManager((CorreoElectronicoManager)getBean("correoElectronicoManager"));
		actualizarEstadoSolicitudSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		actualizarEstadoSolicitudSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		actualizarEstadoSolicitudSpring.setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
		actualizarEstadoSolicitudSpring.setPagoSolicitudManager((PagoSolicitudManager)getBean("pagoSolicitudManager"));
		actualizarEstadoSolicitudSpring.setSolicitudesPropiosManager((SolicitudesPropiosManager) getBean("solicitudesPropiosManager"));
		actualizarEstadoSolicitudSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));		
				
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
			
			System.out.println("Iniciando test actualizarEstadoSolicitud");
	        
			ActualizarEstadoSolicitudForm formulario = new ActualizarEstadoSolicitudForm();
			//Introducir los parametros de entrada para el test
			//formulario.setAccion("BUSCAR");
			formulario.setIdSolicitud(Long.valueOf(40307));
			formulario.setEstadoActual("NO REGISTRADA");
			formulario.setIdNuevoEstado(3);
			formulario.setSubmit("779,Actualizar");
			
			
			SpringForward rtrn = actualizarEstadoSolicitudSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test actualizarEstadoSolicitud");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test actualizarEstadoSolicitud con errores");
			fail();
		}
		
		
	}
	
	
}
