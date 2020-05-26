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

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsg.action.convocatoria.CrearConvocatoriaSpring;
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
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class CrearConvocatoriaTest.
 */
public class CrearConvocatoriaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el crear convocatoria spring. */
	private CrearConvocatoriaSpring crearConvocatoriaSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/crearConvocatoria", CrearConvocatoriaSpring.class);
		
		//Se cargan los managers
		crearConvocatoriaSpring = new CrearConvocatoriaSpring();
		crearConvocatoriaSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		crearConvocatoriaSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		crearConvocatoriaSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		crearConvocatoriaSpring.setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		crearConvocatoriaSpring.setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
		crearConvocatoriaSpring.setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		crearConvocatoriaSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager) getBean("motivoReduccionTasaManager"));
		crearConvocatoriaSpring.setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		crearConvocatoriaSpring.setFestivoManager((FestivoManager) getBean("festivoManager"));
		crearConvocatoriaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		crearConvocatoriaSpring.setLogConvocatoriaManager((LogConvocatoriaManager) getBean("logConvocatoriaManager"));
		crearConvocatoriaSpring.setMinisteriosManager((MinisterioManager) getBean("ministeriosManager"));
		crearConvocatoriaSpring.setPlantillaPropiosManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
		
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
			
	        
			System.out.println("Iniciando test crearConvocatoria");
			
			//Introducir los parametros de entrada para el test
			CrearConvocatoriaForm formulario = new CrearConvocatoriaForm();
			
			formulario.setAccion("importe");
			formulario.setCentroGestor("1");
			formulario.setCuerpoEscala("8");
			formulario.setMinisterioConvocante("14");
			formulario.setNumPlazas("20");
			formulario.setNumPlazasDisc("5");
			formulario.setFormaAcceso("1");
			formulario.setCodigoFormaAcceso("1");
			formulario.setImporte("2.64");
			formulario.setDirigidoA("Prueba");
			formulario.setTipoDocumentacion("C");
			String titulos = "2393";
			formulario.setTitulosSeleccionadosInput(titulos);
			formulario.setFechaBoe("05/03/2018");
			formulario.setFechaInicio("01/01/2018");
			formulario.setFechaTermino("05/05/2018");
			String[] provinciasExamen = {"15"};
			formulario.setProvinciaExamenSeleccionados(provinciasExamen);
			String[] motivosExencion = {"2"};
			formulario.setMotivosExencionSeleccionados(motivosExencion);
			formulario.setSiglasMinisterioBoe("MPR");
			formulario.setAnioBoe("2018");
			
			
			
					
			
			SpringForward rtrn = crearConvocatoriaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test crearConvocatoria");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test crearConvocatoria con errores");
			fail();
		} 
		
		
	}
	
	
}
