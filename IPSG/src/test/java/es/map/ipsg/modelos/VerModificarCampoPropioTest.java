package es.map.ipsg.modelos;

import static org.mockito.Mockito.mock;

import java.util.List;

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
import es.map.ips.model.CamposPropiosQuery;
import es.map.ipsg.action.modelos.VerModificarCampoPropioSpring;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerModificarCampoPropioTest.
 */
public class VerModificarCampoPropioTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver modificar campo propio spring. */
	private VerModificarCampoPropioSpring verModificarCampoPropioSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarCampoPropioTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verModificarCampoPropio", VerModificarCampoPropioSpring.class);
		
		//Se cargan los managers
		verModificarCampoPropioSpring = new VerModificarCampoPropioSpring();
		verModificarCampoPropioSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		verModificarCampoPropioSpring.setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));	
		verModificarCampoPropioSpring.setModeloManager((ModeloManager) getBean ("modelosManager"));
		verModificarCampoPropioSpring.setCampoPropioManager((CamposPropiosManager) getBean("camposPropiosManager"));

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
			
	        
			System.out.println("Iniciando test verModificarCampoPropio");
			ModeloGestion790Form formulario = new ModeloGestion790Form();
			String idCampo = "13";
			formulario.setId(idCampo);
			//Introducir los parametros de entrada para el test
			
			CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
			camposPropiosQuery.setIdCampo(Integer.parseInt(idCampo));
			List<CamposPropiosBean> camposPropiosBean = verModificarCampoPropioSpring.getCampoPropioManager().buscarCamposPropiosbyCampo(camposPropiosQuery);
			if(camposPropiosBean.isEmpty()) {
				logger.error("No existe el campo propio");
				return;
			}
			
			SpringForward rtrn = verModificarCampoPropioSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verModificarCampoPropio");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verModificarCampoPropio con errores");
			fail();
		}
		
		
	}
	
	
}
