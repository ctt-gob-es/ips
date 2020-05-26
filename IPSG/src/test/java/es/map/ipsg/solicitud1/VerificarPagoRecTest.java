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

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.action.solicitud.VerificarPagoRecSpring;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerificarPagoRecTest.
 */
public class VerificarPagoRecTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el verificar pago rec spring. */
	private VerificarPagoRecSpring verificarPagoRecSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarPagoRecTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verificarPagoRec", VerificarPagoRecSpring.class);
		
		//Se cargan los managers
		verificarPagoRecSpring = new VerificarPagoRecSpring();
		verificarPagoRecSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		verificarPagoRecSpring.setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));				
		verificarPagoRecSpring.setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		verificarPagoRecSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		verificarPagoRecSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		
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
			
	        
			System.out.println("Iniciando test verificarPagoRec");
			
			//Introducir los parametros de entrada para el test
			SpringForm formulario = new SpringForm();
		
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idSolicitud = "35498";
			request.setParameter("solicitud", idSolicitud);
	        this.request = request;   
	        
	        SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			solicitudQuery.setIdSolicitud(Long.valueOf(idSolicitud));	
			SolicitudBean solicitudBean = verificarPagoRecSpring.getSolicitudesManager().buscarSolicitudById(solicitudQuery);
			if(solicitudBean == null) {
				logger.error("La solicitud para la verificacion del pag rec no existe");
				return;
			}
			
			SpringForward rtrn = verificarPagoRecSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verificarPagoRec");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verificarPagoRec con errores");
			fail();
		} 
		
		
	}
	
	
}
