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

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.convocatoria.BuscarConvocatoriasSpring;
import es.map.ipsg.form.BuscaConvocatoriasForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class BuscarConvocatoriasTest.
 */
public class BuscarConvocatoriasTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el buscar convocatorias spring. */
	private BuscarConvocatoriasSpring buscarConvocatoriasSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/buscarConvocatorias", BuscarConvocatoriasSpring.class);
		
		//Se cargan los managers
		buscarConvocatoriasSpring = new BuscarConvocatoriasSpring();
		buscarConvocatoriasSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		buscarConvocatoriasSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		buscarConvocatoriasSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		buscarConvocatoriasSpring.setGrupoManager((GrupoManager) getBean("grupoManager"));
		buscarConvocatoriasSpring.setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		buscarConvocatoriasSpring.setEstadoConvocatoriaManager((EstadoConvocatoriaManager) getBean("estadosConvocatoriaManager"));
		buscarConvocatoriasSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		buscarConvocatoriasSpring.setModeloManager((ModeloManager)getBean("modelosManager"));
		
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
			
	        
			System.out.println("Iniciando test buscarConvocatorias");
			
			//Introducir los parametros de entrada para el test
			BuscaConvocatoriasForm formulario = new BuscaConvocatoriasForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("accion", "Paginar");
			request.setParameter("numRegistro", "10");
			
	        this.request = request; 
					
			
			SpringForward rtrn = buscarConvocatoriasSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test buscarConvocatorias");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test buscarConvocatorias con errores");
			fail();
		} 
		
		
	}
	
	
}
