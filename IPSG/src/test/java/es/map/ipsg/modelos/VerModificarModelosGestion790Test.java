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
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ipsg.action.modelos.VerModificarModelosGestion790Spring;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PlantillaManager;


/**
 * El Class VerModificarModelosGestion790Test.
 */
public class VerModificarModelosGestion790Test extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver modificar modelos gestion 790 spring. */
	private VerModificarModelosGestion790Spring verModificarModelosGestion790Spring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarModelosGestion790Test.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verModificarModelosGestion790", VerModificarModelosGestion790Spring.class);
		
		//Se cargan los managers
		verModificarModelosGestion790Spring = new VerModificarModelosGestion790Spring();
		verModificarModelosGestion790Spring.setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
		verModificarModelosGestion790Spring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		verModificarModelosGestion790Spring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		verModificarModelosGestion790Spring.setModeloManager((ModeloManager)getBean("modelosManager"));
		verModificarModelosGestion790Spring.setCampoPropioManager((CamposPropiosManager)getBean("camposPropiosManager"));
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
			
	        
			System.out.println("Iniciando test verModificarModelosGestion790");
			ModeloGestion790Form formulario = new ModeloGestion790Form();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idModelo = "13";
			request.setParameter("id", idModelo);
	        this.request = request;   
			//Introducir los parametros de entrada para el test
	        CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
			ModeloQuery modeloQuery = new ModeloQuery();
			modeloQuery.setIdModelo(Integer.parseInt(idModelo));
			camposPropiosQuery.setModelo(modeloQuery);
			List<CamposPropiosBean> camposPropiosBean = verModificarModelosGestion790Spring.getCampoPropioManager().buscarCamposPropiosbyModelo(camposPropiosQuery);
			if(camposPropiosBean.isEmpty()) {
				logger.error("No existe el modelo");
				return;
			}
			
			SpringForward rtrn = verModificarModelosGestion790Spring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verModificarModelosGestion790");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verModificarModelosGestion790 con errores");
			fail();
		}
		
		
	}
	
	
}
