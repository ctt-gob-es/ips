package es.map.ipsg.solicitud1;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

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
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.action.solicitud.VerVerificarEdadSolicitantesSpring;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;


/**
 * El Class VerVerificarEdadSolicitantesTest.
 */
public class VerVerificarEdadSolicitantesTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver verificar edad solicitantes spring. */
	private VerVerificarEdadSolicitantesSpring verVerificarEdadSolicitantesSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerVerificarEdadSolicitantesTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verVerificarEdadSolicitantes", VerVerificarEdadSolicitantesSpring.class);
		
		//Se cargan los managers
		verVerificarEdadSolicitantesSpring = new VerVerificarEdadSolicitantesSpring();
		verVerificarEdadSolicitantesSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verVerificarEdadSolicitantesSpring.setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		
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
			
	        
			System.out.println("Iniciando test verVerificarEdadSolicitantes");
			
			//Introducir los parametros de entrada para el test
			BuscarSolicitudesForm formulario = new BuscarSolicitudesForm();
								
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idSolicitud = "35498";
			String[] solicitudes = {idSolicitud};
			request.setParameter("sol", solicitudes);
	        this.request = request;   
	        
	        ArrayList aSolicitudes;
	        SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
	        solicitudQuery.addIdSolicitudIn(Long.valueOf(idSolicitud));
	        aSolicitudes = verVerificarEdadSolicitantesSpring.getSolicitudesRegistradasManager().buscarSolicitudAll(solicitudQuery);
	        if(aSolicitudes.isEmpty()) {
	        	logger.error("No existe la solicitud");
	        	return;
	        }
	        
			SpringForward rtrn = verVerificarEdadSolicitantesSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verVerificarEdadSolicitantes");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verVerificarEdadSolicitantes con errores");
			fail();
		} 
		
		
	}
	
	
}
