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
import es.map.ipsg.action.solicitudPresencial.VerModificarSolicitudPresencialSpring;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ComunidadesManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.PaisManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoDiscapacidadManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerModificarSolicitudPresencialTest.
 */
public class VerModificarSolicitudPresencialTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver modificar solicitud presencial spring. */
	private VerModificarSolicitudPresencialSpring verModificarSolicitudPresencialSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/verModificarSolicitudPresencial", VerModificarSolicitudPresencialSpring.class);
		
		//Se cargan los managers
		verModificarSolicitudPresencialSpring = new VerModificarSolicitudPresencialSpring();	
		verModificarSolicitudPresencialSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		verModificarSolicitudPresencialSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		verModificarSolicitudPresencialSpring.setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
		verModificarSolicitudPresencialSpring.setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		verModificarSolicitudPresencialSpring.setPaisManager((PaisManager) getBean("paisManager"));
		verModificarSolicitudPresencialSpring.setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		verModificarSolicitudPresencialSpring.setTipoDiscapacidadManager((TipoDiscapacidadManager) getBean("tipoDiscapacidadManager"));
		verModificarSolicitudPresencialSpring.setTituloOficialManager( (TituloOficialManager) getBean("tituloOficialManager"));
		verModificarSolicitudPresencialSpring.setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
		verModificarSolicitudPresencialSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		verModificarSolicitudPresencialSpring.setSolicitudesRegistradaManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		verModificarSolicitudPresencialSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		verModificarSolicitudPresencialSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
		verModificarSolicitudPresencialSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verModificarSolicitudPresencialSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		verModificarSolicitudPresencialSpring.setSolicitudesPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
		verModificarSolicitudPresencialSpring.setCampoPropioManager((CamposPropiosManager)getBean("camposPropiosManager"));
		verModificarSolicitudPresencialSpring.setSolicitudCcaaManager((SolicitudCcaaManager)getBean("solicitudCcaaManager"));
		verModificarSolicitudPresencialSpring.setComunidadesManager((ComunidadesManager)getBean("comunidadesManager"));
		verModificarSolicitudPresencialSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
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
			
			System.out.println("Iniciando test verModificarSolicitudPresencial");
	        
			AltaSolicitudPresencialForm formulario = new AltaSolicitudPresencialForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");			
			request.setParameter("id", "40605");			
	        this.request = request; 
			
			SpringForward rtrn = verModificarSolicitudPresencialSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test verModificarSolicitudPresencial");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verModificarSolicitudPresencial con errores");
			fail();
		}
		
		
	}
	
	
}
