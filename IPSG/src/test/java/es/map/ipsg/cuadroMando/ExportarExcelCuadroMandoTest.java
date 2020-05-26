package es.map.ipsg.cuadroMando;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import es.map.ipsg.action.cuadroMando.BuscarCuadroMandoSpring;
import es.map.ipsg.action.cuadroMando.ExportarExcelCuadroMandoSpring;
import es.map.ipsg.bean.ConvocatoriasViewBean;
import es.map.ipsg.form.ConsultarCuadroMandoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasViewManager;
import es.map.ipsg.manager.DescargaModelo790Manager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ExportarExcelCuadroMandoTest.
 */
public class ExportarExcelCuadroMandoTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el exportar excel cuadro mando spring. */
	private ExportarExcelCuadroMandoSpring exportarExcelCuadroMandoSpring;

	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el lista. */
	private List<ConvocatoriasViewBean> lista;
	
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
		mapping = springMappingManager.getMapping("/spring/exportarExcelCuadroMando", ExportarExcelCuadroMandoSpring.class);
		
		//Se cargan los managers
		exportarExcelCuadroMandoSpring = new ExportarExcelCuadroMandoSpring();
		exportarExcelCuadroMandoSpring.setDescargaModelo790Manager((DescargaModelo790Manager) getBean("descargaModelo790Manager"));
		
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
			
	        
			System.out.println("Iniciando test exportarExcelCuadroMando");
			
			//Introducir los parametros de entrada para el test
			ConsultarCuadroMandoForm formulario = new ConsultarCuadroMandoForm();
			formulario.setCkCentroGestor(true);
			List <ConvocatoriasViewBean> lista = new ArrayList<ConvocatoriasViewBean>();
			ConvocatoriasViewBean prueba = new ConvocatoriasViewBean();
			prueba.setDesCentroGestor("INSTITUO NACIONAL DE ADMINSITRACION PUBLICA");
			prueba.setDesCuerpoEscala("TECNICOS");
			lista.add(prueba);
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.getSession().setAttribute("consultaExportarCuadroMando", lista);
	        this.request = request; 
			
			SpringForward rtrn = exportarExcelCuadroMandoSpring.execute(mapping, formulario, request, response);
			if (rtrn.getPath() == null) {
				System.out.println("Finalizando test exportarExcelCuadroMando");				
			}
			else throw new Exception("Error");
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			fail();
			System.out.println("Finalizando test exportarExcelCuadroMando con errores");
		} 
		
		
	}
	
	
}
