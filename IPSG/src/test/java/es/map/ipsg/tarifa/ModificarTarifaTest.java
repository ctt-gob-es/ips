package es.map.ipsg.tarifa;

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
import es.map.ipsg.action.tarifa.ModificarTarifaSpring;
import es.map.ipsg.form.TarifaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ModificarTarifaTest.
 */
public class ModificarTarifaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el modificar tarifa spring. */
	private ModificarTarifaSpring modificarTarifaSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/modificarTarifa", ModificarTarifaSpring.class);
		
		//Se cargan los managers
		modificarTarifaSpring = new ModificarTarifaSpring();	
		modificarTarifaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		modificarTarifaSpring.setTarifaManager((TarifaManager) getBean("tarifaManager"));
		modificarTarifaSpring.setGrupoManager((GrupoManager) getBean("grupoManager"));
		modificarTarifaSpring.setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
		modificarTarifaSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
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
			
			System.out.println("Iniciando test modificarTarifa");
	        
			TarifaForm formulario = new TarifaForm();
			//Introducir los parametros de entrada para el test
			formulario.setId(364);
			formulario.setAccion("modificar");
			formulario.setDescripcion("Prueba");
			formulario.setEjercicio("2018");
			formulario.setIdGrupo("86");
			formulario.setImporte("13");
			formulario.setIdTipoAcceso("1");
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");			
			request.setParameter("id", "364");			
	        this.request = request; 
			
			SpringForward rtrn = modificarTarifaSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test modificarTarifa");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test modificarTarifa con errores");
			fail();
		}
		
		
	}
	
	
}
