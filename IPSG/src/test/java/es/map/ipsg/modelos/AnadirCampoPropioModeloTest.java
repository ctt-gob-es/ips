package es.map.ipsg.modelos;

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
import es.map.ipsg.action.modelos.AnadirCampoPropioModeloSpring;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class AnadirCampoPropioModeloTest.
 */
public class AnadirCampoPropioModeloTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el anadir campo propio modelo spring. */
	private AnadirCampoPropioModeloSpring anadirCampoPropioModeloSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AnadirCampoPropioModeloTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/anadirCampoPropioModelo", AnadirCampoPropioModeloSpring.class);
		
		//Se cargan los managers
		anadirCampoPropioModeloSpring = new AnadirCampoPropioModeloSpring();
		anadirCampoPropioModeloSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		anadirCampoPropioModeloSpring.setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));	
		anadirCampoPropioModeloSpring.setModeloManager((ModeloManager) getBean ("modelosManager"));
		anadirCampoPropioModeloSpring.setCampoPropioManager((CamposPropiosManager) getBean("camposPropiosManager"));
		anadirCampoPropioModeloSpring.setPlantillaPropioManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
		
		
		
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
			
	        
			System.out.println("Iniciando test anadirCampoPropioModelo");
			ModeloGestion790Form formulario = new ModeloGestion790Form();
			//Introducir los parametros de entrada para el test
			String idModelo = "40";
			formulario.setId(idModelo);
		
			ModeloBean modeloBean = new ModeloBean();
			modeloBean.setId(Integer.parseInt(formulario.getId()));
			try {
				ModeloBean modeloBeanAux=anadirCampoPropioModeloSpring.getModeloManager().obtenerModelo790ById(Integer.parseInt(formulario.getId()));
			}catch(NullPointerException ex) {
				logger.error("No existe el modelo");
				return;
			}
			
			SpringForward rtrn = anadirCampoPropioModeloSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test anadirCampoPropioModelo");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test anadirCampoPropioModelo con errores");
			fail();
		}
		
		
	}
	
	
}
