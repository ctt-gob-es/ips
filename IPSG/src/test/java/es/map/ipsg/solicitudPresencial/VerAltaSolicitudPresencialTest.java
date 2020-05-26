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
import es.map.ipsg.action.solicitudPresencial.VerAltaSolicitudPresencialSpring;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ComunidadesManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PaisManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudPropioAuxiliarManager;
import es.map.ipsg.manager.TipoDiscapacidadManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerAltaSolicitudPresencialTest.
 */
public class VerAltaSolicitudPresencialTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver alta solicitud presencial spring. */
	private VerAltaSolicitudPresencialSpring verAltaSolicitudPresencialSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/verAltaSolicitudPresencial", VerAltaSolicitudPresencialSpring.class);
		
		//Se cargan los managers
		verAltaSolicitudPresencialSpring = new VerAltaSolicitudPresencialSpring();	
		verAltaSolicitudPresencialSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		verAltaSolicitudPresencialSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		verAltaSolicitudPresencialSpring.setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
		verAltaSolicitudPresencialSpring.setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		verAltaSolicitudPresencialSpring.setPaisManager((PaisManager) getBean("paisManager"));
		verAltaSolicitudPresencialSpring.setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		verAltaSolicitudPresencialSpring.setTipoDiscapacidadManager((TipoDiscapacidadManager) getBean("tipoDiscapacidadManager"));
		verAltaSolicitudPresencialSpring.setTituloOficialManager( (TituloOficialManager) getBean("tituloOficialManager"));
		verAltaSolicitudPresencialSpring.setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
		verAltaSolicitudPresencialSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		verAltaSolicitudPresencialSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verAltaSolicitudPresencialSpring.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
		verAltaSolicitudPresencialSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verAltaSolicitudPresencialSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		verAltaSolicitudPresencialSpring.setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
		verAltaSolicitudPresencialSpring.setCampoPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
		verAltaSolicitudPresencialSpring.setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager)getBean("solicitudComunAuxiliarManager"));
		verAltaSolicitudPresencialSpring.setSolicitudPropioAuxiliarManager((SolicitudPropioAuxiliarManager)getBean("solicitudPropioAuxiliarManager"));
		verAltaSolicitudPresencialSpring.setSolicitudCcaaAuxiliarManager((SolicitudCcaaAuxiliarManager)getBean("solicitudCcaaAuxiliarManager"));
		verAltaSolicitudPresencialSpring.setComunidadesManager((ComunidadesManager)getBean("comunidadesManager"));
		verAltaSolicitudPresencialSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
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
			
			System.out.println("Iniciando test verAltaSolicitudPresencial");
	        
			AltaSolicitudPresencialForm formulario = new AltaSolicitudPresencialForm();
			formulario.setNumeroSolicitud("7900019043341");
			formulario.setAccion("ALTA");
			formulario.setDatosSolicitud("");
			formulario.setMenu("S");
			formulario.setSubmit("");
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("menu", "S");
	        this.request = request;   
			
			SpringForward rtrn = verAltaSolicitudPresencialSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test verAltaSolicitudPresencial");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verAltaSolicitudPresencial con errores");
			fail();
		}
		
		
	}
	
	
}
