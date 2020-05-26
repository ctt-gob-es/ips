package es.map.ipsg.convocatoria;

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

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsg.action.convocatoria.CopiarConvocatoriaSpring;
import es.map.ipsg.action.convocatoria.VerCrearConvocatoriaSpring;
import es.map.ipsg.action.convocatoria.VerModificarConvocatoriaSpring;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.FestivoManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.LogConvocatoriaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerModificarConvocatoriaTest.
 */
public class VerModificarConvocatoriaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver modificar convocatoria spring. */
	private VerModificarConvocatoriaSpring verModificarConvocatoriaSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/verModificarConvocatoria", VerModificarConvocatoriaSpring.class);
		
		//Se cargan los managers
		verModificarConvocatoriaSpring = new VerModificarConvocatoriaSpring();
		verModificarConvocatoriaSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verModificarConvocatoriaSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		verModificarConvocatoriaSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		verModificarConvocatoriaSpring.setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		verModificarConvocatoriaSpring.setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
		verModificarConvocatoriaSpring.setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		verModificarConvocatoriaSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager) getBean("motivoReduccionTasaManager"));
		verModificarConvocatoriaSpring.setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		verModificarConvocatoriaSpring.setFestivoManager((FestivoManager) getBean("festivoManager"));
		verModificarConvocatoriaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		verModificarConvocatoriaSpring.setMinisteriosManager((MinisterioManager) getBean("ministeriosManager"));
		
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
			
	        
			System.out.println("Iniciando test verModificarConvocatoria");
			
			//Introducir los parametros de entrada para el test
			CrearConvocatoriaForm formulario = new CrearConvocatoriaForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("idConvocatoria", "36053605");
			
	        this.request = request; 
	        formulario.setIdConvocatoria("3605");
			
			SpringForward rtrn = verModificarConvocatoriaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verModificarConvocatoria");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verModificarConvocatoria con errores");
			fail();
		} 
		
		
	}
	
	
}
