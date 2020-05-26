package es.map.ipsg.centrogestor;

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
import es.map.ips.model.CentroGestorQuery;
import es.map.ipsg.action.centrogestor.VerFormularioGestionSpring;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerFormularioGestionTest.
 */
public class VerFormularioGestionTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver formulario gestion spring. */
	private VerFormularioGestionSpring verFormularioGestionSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerFormularioGestionTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verFormularioGestion", VerFormularioGestionSpring.class);
		
		//Se cargan los managers
		verFormularioGestionSpring = new VerFormularioGestionSpring();
		verFormularioGestionSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		verFormularioGestionSpring.setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
		verFormularioGestionSpring.setCamposPropiosManager((CamposPropiosManager) getBean("camposPropiosManager"));
		verFormularioGestionSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		verFormularioGestionSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		verFormularioGestionSpring.setModeloManager((ModeloManager)getBean("modelosManager"));
		
		
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
			
	        
			System.out.println("Iniciando test verFormularioGestion");
			
			//Introducir los parametros de entrada para el test
			PlantillaForm formulario = new PlantillaForm();	
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idCentroGestor = "7";
			request.setParameter("idCentroGestor", idCentroGestor);
	        this.request = request; 
	    	CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.setId(Integer.valueOf(idCentroGestor));
	        CentroGestorBean centroGestorBean;
			centroGestorBean = verFormularioGestionSpring.getCentroGestorManager().buscarCentroGestorUnico(centroGestorQuery);
			if (centroGestorBean.getId() == null) {
				logger.error("No existe el centro gestor");
				return;
			}
			
			SpringForward rtrn = verFormularioGestionSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verFormularioGestion");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verFormularioGestion con errores");
			fail();
		} 
		
		
	}
	
	
}
