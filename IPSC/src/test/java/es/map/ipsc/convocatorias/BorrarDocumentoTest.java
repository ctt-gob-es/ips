package es.map.ipsc.convocatorias;

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
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.spring.convocatorias.BorrarDocumentoSpring;


/**
 * El Class BorrarDocumentoTest.
 */
public class BorrarDocumentoTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el borrar documento spring. */
	private BorrarDocumentoSpring borrarDocumentoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BorrarDocumentoTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/borrarDocumento", BorrarDocumentoSpring.class);
		
		//Se cargan los managers
		borrarDocumentoSpring = new BorrarDocumentoSpring();
		borrarDocumentoSpring.setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));		
						
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
			
			System.out.println("Iniciando test borrarDocumento");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			CrearDocumentoForm formulario = new CrearDocumentoForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
//			String idDocumento = "24653";
			String idDocumento = "24305";
			
			DocumentoQuery documentosQuery = new DocumentoQuery();
			documentosQuery.setId(Long.valueOf(idDocumento));
			ArrayList<DocumentoBean> documentos = borrarDocumentoSpring.getDocumentoConvocatoriasManager().buscarDocumentosConvocatoria(documentosQuery);
			if(documentos.isEmpty()) {
				logger.error("No se ha podido ejecutar el junit porque no existe el documento en el servidor");
				return;
			}			
			
				request.setParameter("llamada", "Buscar");
				request.setParameter("doc", idDocumento.toString());			
		        this.request = request; 
				SpringForward rtrn = borrarDocumentoSpring.execute(mapping, formulario, request, response);
				if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
					System.out.println("RESULTADO: "+rtrn.getPath());
					if (rtrn.getPath().contains("error")) throw new Exception("Error");
				}
				else throw new Exception("Error");
				System.out.println("Finalizando test borrarDocumento");
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test borrarDocumento con errores");
			fail();
		} 
		
		
	}
	
	
}
