package es.map.ipsg.solicitud1;

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
import es.map.ipsg.action.solicitud.VerVerificarEdadSolicitantesSpring;
import es.map.ipsg.action.solicitud.VerificarTitulosSpring;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.form.VerificarTitulosForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DatosTitulosManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;


/**
 * El Class VerificarTitulosTest.
 */
public class VerificarTitulosTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el verificar titulos spring. */
	private VerificarTitulosSpring verificarTitulosSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarTitulosTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verificarDatosTitulos", VerificarTitulosSpring.class);
		
		//Se cargan los managers
		verificarTitulosSpring = new VerificarTitulosSpring();
		verificarTitulosSpring.setDatosTitulosManager((DatosTitulosManager) getBean("datosTitulosManager"));

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
			
	        
			System.out.println("Iniciando test verificarTitulos");
			
			//Introducir los parametros de entrada para el test
			VerificarTitulosForm formulario = new VerificarTitulosForm();
			Long idSolicitud = 35400L;
			formulario.setIdSolicitud(idSolicitud);
			formulario.setAccion("Aprobar");	
			
			try {
				verificarTitulosSpring.getDatosTitulosManager().aprobarTituloVerificado(idSolicitud);
			}catch(NullPointerException ex) {
				logger.error("No existe el id de la solicitud para la verificacion de titulos");
				return;
			}
			SpringForward rtrn = verificarTitulosSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verificarTitulos");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verificarTitulos con errores");
			fail();
		} 
		
		
	}
	
	
}
