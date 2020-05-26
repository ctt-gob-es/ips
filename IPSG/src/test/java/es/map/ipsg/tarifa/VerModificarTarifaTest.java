package es.map.ipsg.tarifa;

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
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsg.action.tarifa.VerModificarTarifaSpring;
import es.map.ipsg.bean.TarifaBean;
import es.map.ipsg.form.TarifaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerModificarTarifaTest.
 */
public class VerModificarTarifaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver modificar tarifa spring. */
	private VerModificarTarifaSpring verModificarTarifaSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarTarifaTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verModificarTarifa", VerModificarTarifaSpring.class);
		
		//Se cargan los managers
		verModificarTarifaSpring = new VerModificarTarifaSpring();
		verModificarTarifaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		verModificarTarifaSpring.setTarifaManager((TarifaManager) getBean("tarifaManager"));
		verModificarTarifaSpring.setGrupoManager((GrupoManager) getBean("grupoManager"));
		verModificarTarifaSpring.setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
		
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
			
			System.out.println("Iniciando test verModificarTarifa");
	        
			TarifaForm formulario = new TarifaForm();
			//Introducir los parametros de entrada para el test
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idTarifa = "364";
			request.setParameter("id", idTarifa);			
	        this.request = request; 
	        TarifaBean tarifaBean= new TarifaBean();
	        try{
	        	tarifaBean = verModificarTarifaSpring.getTarifaManager().obtenerTarifa(Integer.valueOf(idTarifa));
	        }catch(NullPointerException ex) {
	        	logger.error("No existe el id de la tarifa");
	        	return;
	        }
	        
			SpringForward rtrn = verModificarTarifaSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test verModificarTarifa");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verModificarTarifa con errores");
			fail();
		}
		
		
	}
	
	
}
