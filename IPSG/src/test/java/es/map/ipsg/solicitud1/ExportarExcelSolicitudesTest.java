package es.map.ipsg.solicitud1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.ServletOutputStream;
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
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsg.action.solicitud.ExportarExcelSolicitudesSpring;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EstadoSolicitudManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;


/**
 * El Class ExportarExcelSolicitudesTest.
 */
public class ExportarExcelSolicitudesTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el exportar excel solicitudes spring. */
	private ExportarExcelSolicitudesSpring exportarExcelSolicitudesSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/exportarExcelSolicitudes", ExportarExcelSolicitudesSpring.class);
		
		//Se cargan los managers
		exportarExcelSolicitudesSpring = new ExportarExcelSolicitudesSpring();
		exportarExcelSolicitudesSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		exportarExcelSolicitudesSpring.setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
		exportarExcelSolicitudesSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		exportarExcelSolicitudesSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
		exportarExcelSolicitudesSpring.setModeloManager((ModeloManager)getBean("modelosManager"));
		exportarExcelSolicitudesSpring.setCampoPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
		exportarExcelSolicitudesSpring.setSolicitudPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
		exportarExcelSolicitudesSpring.setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
		
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        
        ServletOutputStream servletOutputStream;
        servletOutputStream = mock(ServletOutputStream.class);
       
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        
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
		
			System.out.println("Iniciando test exportarExcelSolicitudes");
	        
			BuscarSolicitudesForm formulario = new BuscarSolicitudesForm();
	 
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");

	        this.request = request;
	        String[] solicitudes = {"1"};
	        formulario.setSolicitudesSel(solicitudes);
	        
			SpringForward rtrn = exportarExcelSolicitudesSpring.execute(mapping, formulario, request, response);
			
			if (rtrn.getPath() == null) {
				System.out.println("Finalizando test exportarExcelSolicitudes");				
			}
			else throw new Exception("Error");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test exportarExcelSolicitudes con errores");
			fail();
		}
		
		
	}
	
	
}
