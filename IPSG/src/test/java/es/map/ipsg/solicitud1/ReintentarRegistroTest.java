package es.map.ipsg.solicitud1;

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
import es.map.ipsg.action.solicitud.ReintentarRegistroSpring;
import es.map.ipsg.form.GestionarJustificanteForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ReintentarRegistroTest.
 */
public class ReintentarRegistroTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el reintentar registro spring. */
	private ReintentarRegistroSpring reintentarRegistroSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/reintentarRegistro", ReintentarRegistroSpring.class);
		
		//Se cargan los managers
		reintentarRegistroSpring = new ReintentarRegistroSpring();
		reintentarRegistroSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		reintentarRegistroSpring.setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
		reintentarRegistroSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		reintentarRegistroSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
		reintentarRegistroSpring.setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		reintentarRegistroSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		reintentarRegistroSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		reintentarRegistroSpring.setSolicitudesPropiosManager((SolicitudesPropiosManager) getBean("solicitudesPropiosManager"));
	
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
		
			System.out.println("Iniciando test reintentarRegistro");
	        
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("idRegistro", "38480");

	        this.request = request;
	        
	        GestionarJustificanteForm formulario = new GestionarJustificanteForm();
			formulario.setTipoJustificante("solicitud");
	        
			SpringForward rtrn = reintentarRegistroSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			
			System.out.println("Finalizando test reintentarRegistro");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test reintentarRegistro con errores");
			fail();
		}
		
		
	}
	
	
}
