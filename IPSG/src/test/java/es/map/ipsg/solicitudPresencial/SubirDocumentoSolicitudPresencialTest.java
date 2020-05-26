package es.map.ipsg.solicitudPresencial;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.solicitudPresencial.SubirDocumentoSolicitudPresencialSpring;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoDocumentoManager;


/**
 * El Class SubirDocumentoSolicitudPresencialTest.
 */
public class SubirDocumentoSolicitudPresencialTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el subir documento solicitud presencial spring. */
	private SubirDocumentoSolicitudPresencialSpring subirDocumentoSolicitudPresencialSpring;
	
	/** el solicitudes registradas manager. */
	SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubirDocumentoSolicitudPresencialTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/subirDocumentoSolicitudPresencial", SubirDocumentoSolicitudPresencialSpring.class);
		
		//Se cargan los managers
		subirDocumentoSolicitudPresencialSpring = new SubirDocumentoSolicitudPresencialSpring();	
		subirDocumentoSolicitudPresencialSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		subirDocumentoSolicitudPresencialSpring.setTipoDocumentoManager((TipoDocumentoManager) getBean("tipoDocumentoManager"));

		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        
        //Carga del usuario
        GrantedAuthority[] authorities = new GrantedAuthority[1];
        authorities[0] = new GrantedAuthorityImpl("ROLE_ADMIN");
        User user = new User("admin", "4bf2b1832c785d31ffbcb5f8c10f5772", true, true, true, true, authorities);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "4bf2b1832c785d31ffbcb5f8c10f5772", authorities));
        
        setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
			System.out.println("Iniciando test subirDocumentoSolicitudPresencial");
	        
			CrearDocumentoForm formulario = new CrearDocumentoForm();
			//Introducir los parametros de entrada para el test
			//formulario.setAccion("BUSCAR");
			Path path = Paths.get("C:/Users/fbalsalo/Documents/prueba.csv");
			String name = "prueba.csv";
			String originalFileName = "prueba.csv";
			String contentType = "application/vnd.ms-excel";
			byte[] content = null;
			try {
			    content = Files.readAllBytes(path);
			} catch (final IOException e) {
			}
			MultipartFile result = new MockMultipartFile(name,
			                     originalFileName, contentType, content);
			formulario.setFile(result);
			formulario.setIdSolicitud("41488");
			formulario.setIdTipoDocumento("1");
			formulario.setEntorno("Solicitudes");
			formulario.setDescripcion("123");
			formulario.setNombre("123");
			ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
		
			try{
				SolicitudBean solicitudBean  = solicitudesRegistradasManager.obtenerSolicitud(Long.valueOf(formulario.getIdSolicitud()));
			}catch(NullPointerException ex) {			
				logger.error("No se encuentra la solicitud presencial para subir el documento");
				return;
			}
			
			
			
			
			SpringForward rtrn = subirDocumentoSolicitudPresencialSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test subirDocumentoSolicitudPresencial");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test subirDocumentoSolicitudPresencial con errores");
			fail();
		}
		
		
	}
	
	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager el nuevo solicitudes registradas manager
	 */
	public void setSolicitudesRegistradasManager(SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}
	
	
}
