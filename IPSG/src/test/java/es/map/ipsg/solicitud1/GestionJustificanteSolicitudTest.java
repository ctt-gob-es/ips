package es.map.ipsg.solicitud1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import es.map.ipsg.action.solicitud.GestionJustificanteSolicitudSpring;
import es.map.ipsg.bean.RegistroSolicitudJustificanteBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class GestionJustificanteSolicitudTest.
 */
public class GestionJustificanteSolicitudTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el gestion justificante solicitud spring. */
	private GestionJustificanteSolicitudSpring gestionJustificanteSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GestionJustificanteSolicitudTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/gestionJustificanteSolicitud", GestionJustificanteSolicitudSpring.class);
		
		//Se cargan los managers
		gestionJustificanteSolicitudSpring = new GestionJustificanteSolicitudSpring();
	
		gestionJustificanteSolicitudSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		gestionJustificanteSolicitudSpring.setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
		gestionJustificanteSolicitudSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		gestionJustificanteSolicitudSpring.setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
		gestionJustificanteSolicitudSpring.setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		gestionJustificanteSolicitudSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
		gestionJustificanteSolicitudSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
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
		
			System.out.println("Iniciando test gestionJustificanteSolicitud");
	        
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("idRegistro", "38480");

	        this.request = request;
	        
	        CrearDocumentoForm formulario = new CrearDocumentoForm();
			formulario.setTipoJustificante("solicitud");
			RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean = new RegistroSolicitudJustificanteBean();
			registroSolicitudJustificanteBean.setIdSolicitud("38480");
			formulario.setRegistroSolicitudJustificanteBean(registroSolicitudJustificanteBean);
			
			Path path = Paths.get("C:/Users/fbalsalo/Documents/prueba123.csv");
			String name = "prueba.csv";
			String originalFileName = "prueba.csv";
			String contentType = "application/vnd.ms-excel";
			byte[] content = null;
			try {
			    content = Files.readAllBytes(path);			    
			} catch (final IOException e) {
			}
			if(content==null) {
		    	logger.error("No se encuentra el fichero");
		    	return;
		    }
			
			formulario.setArrBytes(content);
	        
			SpringForward rtrn = gestionJustificanteSolicitudSpring.execute(mapping, formulario, request, response);
			
			if (rtrn.getPath() == null) {
				System.out.println("Finalizando test gestionJustificanteSolicitud");
			}
			else throw new Exception("Error");
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test gestionJustificanteSolicitud con errores");
			fail();
		}
		
		
	}
	
	
}
