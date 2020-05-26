package es.map.ipsg.solicitudPresencial;

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
import es.map.ipsg.action.solicitudPresencial.AltaSolicitudPresencialSpring;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudPresencialManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;


/**
 * El Class AltaSolicitudPresencialTest.
 */
public class AltaSolicitudPresencialTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el alta solicitud presencial spring. */
	private AltaSolicitudPresencialSpring altaSolicitudPresencialSpring ;
	
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
		mapping = springMappingManager.getMapping("/spring/altaSolicitudPresencial", AltaSolicitudPresencialSpring.class);
		
		//Se cargan los managers
		altaSolicitudPresencialSpring = new AltaSolicitudPresencialSpring();	
		altaSolicitudPresencialSpring.setSolicitudPresencialManager((SolicitudPresencialManager) getBean("solicitudPresencialManager"));
		altaSolicitudPresencialSpring.setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
		altaSolicitudPresencialSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		altaSolicitudPresencialSpring.setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		altaSolicitudPresencialSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
		altaSolicitudPresencialSpring.setSolicitudPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
		altaSolicitudPresencialSpring.setCamposPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
		altaSolicitudPresencialSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
		
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
			
			System.out.println("Iniciando test altaSolicitudPresencial");
	        
			AltaSolicitudPresencialForm formulario = new AltaSolicitudPresencialForm();
			//Introducir los parametros de entrada para el test
			formulario.setAccion("Alta");
			formulario.setIdConvocatoria("8298");
			formulario.setCkReservaDiscapacidad(false);
			formulario.setIdMotivosEx("");
			formulario.setIdMotivosRed("");
			formulario.setNumeroRegistro("000000000000");
			formulario.setOficinaRegistro("O00000400");
			formulario.setIdRegistroSolicitud("30610");
			formulario.setJustificantePago("45354353");
			formulario.setFechaRegistro("21/02/2018");
			formulario.setCkConsentimiento(true);
			formulario.setIdPagoSolicitud("33900");
			formulario.setIdTipoPago("4");
			
			formulario.setNif("11111111X");
			formulario.setNombre("Prueba");
			formulario.setPrimerApellido("prueba");
			formulario.setSegundoApellido("Prueba");
			formulario.setIdConvocatoria("8298");
			formulario.setFechaNacimiento("10/10/1990");
			formulario.setLocalidadNacimiento("Murcia");
			formulario.setNacionalidad("ESPAÑOL");
			formulario.setCodigoPostal("30530");
			
			SpringForward rtrn = altaSolicitudPresencialSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test altaSolicitudPresencial");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test altaSolicitudPresencial con errores");
			fail();
		}
		
		
	}
	
	
}
