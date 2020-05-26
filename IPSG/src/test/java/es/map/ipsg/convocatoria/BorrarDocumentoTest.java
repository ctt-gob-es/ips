package es.map.ipsg.convocatoria;

import static org.mockito.Mockito.mock;

import java.util.Properties;

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

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.action.convocatoria.BorrarDocumentoSpring;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.BuscaConvocatoriasForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DocumentoManager;

/**
 * El Class BorrarDocumentoTest.
 */
public class BorrarDocumentoTest extends AbstractSpringCommonTestCaseIPSG {

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
		mapping = springMappingManager.getMapping("/spring/borrarDocumento", BorrarDocumentoSpring.class);
		ApplicationContextProvider.getInstance().getBean("propertiesBean");
		//Se cargan los managers
		borrarDocumentoSpring = new BorrarDocumentoSpring();
		borrarDocumentoSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		

		Properties properties = (Properties) getBean("propertiesBean");		
		
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
			
			Properties properties = (Properties) getBean("propertiesBean");	
			System.out.println("Iniciando test borrarDocumento");
			
			//Introducir los parametros de entrada para el test
			BuscaConvocatoriasForm formulario = new BuscaConvocatoriasForm();
		
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idDocumento = "24986";
			request.setParameter("ent", "Convocatorias");
			request.setParameter("doc", idDocumento);
	        this.request = request; 
			
	        DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setId(Long.parseLong(idDocumento));
			try {
	    	   DocumentoBean doc = borrarDocumentoSpring.getDocumentosManager().obtenerDocumento(documentoQuery);   
			}catch(NullPointerException ex) {
				logger.error("No existe el documento a borrar");
				return;
			}
			
	        
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
