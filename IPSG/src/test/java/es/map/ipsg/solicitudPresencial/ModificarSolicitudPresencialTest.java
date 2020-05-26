package es.map.ipsg.solicitudPresencial;

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
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ipsg.action.solicitudPresencial.ModificarSolicitudPresencialSpring;
import es.map.ipsg.bean.SolicitudCcaaBean;
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
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ModificarSolicitudPresencialTest.
 */
public class ModificarSolicitudPresencialTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el modificar solicitud presencial spring. */
	private ModificarSolicitudPresencialSpring modificarSolicitudPresencialSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarSolicitudPresencialTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/modificarSolicitudPresencial", ModificarSolicitudPresencialSpring.class);
		
		//Se cargan los managers
		modificarSolicitudPresencialSpring = new ModificarSolicitudPresencialSpring();	
		modificarSolicitudPresencialSpring.setSolicitudPresencialManager((SolicitudPresencialManager) getBean("solicitudPresencialManager"));
		modificarSolicitudPresencialSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		modificarSolicitudPresencialSpring.setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		modificarSolicitudPresencialSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		modificarSolicitudPresencialSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
		modificarSolicitudPresencialSpring.setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		modificarSolicitudPresencialSpring.setSolicitudPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
		modificarSolicitudPresencialSpring.setCamposPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
		modificarSolicitudPresencialSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
		modificarSolicitudPresencialSpring.setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
		
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
			
			System.out.println("Iniciando test modificarSolicitudPresencial");
	        
			AltaSolicitudPresencialForm formulario = new AltaSolicitudPresencialForm();
			//Introducir los parametros de entrada para el test
			formulario.setAccion("MODIFICAR,Modificar");
			formulario.setId("40605");
			formulario.setNif("14898682H");
			formulario.setNombre("Prueba");
			formulario.setPrimerApellido("Prueba");
			formulario.setSegundoApellido("Prueba");
			formulario.setFechaNacimiento("01/05/2002");
			formulario.setNacionalidad("Española");
			formulario.setEmail("prueba@prueba.com");
			formulario.setCkConsentimiento(true);
			formulario.setSexo("Hombre");
			formulario.setCalleDomicilio("Prueba");
			formulario.setMunicipioDomicilio("Murcia");
			formulario.setTelefono1("968732323");
			formulario.setIdPais("1");
			formulario.setIdProvinciaDomicilio("31");
			formulario.setNumeroSolicitud("7900079108652");
			formulario.setIdConvocatoria("8858");
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
			formulario.setIdMotivosEx("2");
			formulario.setImporte("0.01");
			
			
			SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();			
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(Long.valueOf(formulario.getId()));
			SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery ();
			solicitudCcaaQuery.setSolicitudComun(solicitudComunQuery);
			// Obtenemos los valores que tenemos almacenados en la tabla de Solicitud_CCAA con ese IdSolicitud
			SolicitudCcaaBean solicitudCcaaBeanAux=modificarSolicitudPresencialSpring.getSolicitudCcaaManager().obtenerSolicitudCcaaByIdSolicitud(solicitudCcaaQuery);
			if(solicitudCcaaBeanAux.getIdSolicitud() == null) {
				logger.error("No existe la solicitud presencial a modificar");
				return;
			}
			
			SpringForward rtrn = modificarSolicitudPresencialSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test modificarSolicitudPresencial");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test modificarSolicitudPresencial con errores");
			fail();
		}
		
		
	}
	
	
}
