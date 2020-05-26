package es.map.ipsg.solicitud1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Properties;

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
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.action.solicitud.DocumentosJustificanteSolicitudSpring;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class DocumentosJustificanteSolicitudTest.
 */
public class DocumentosJustificanteSolicitudTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el documentos justificante solicitud spring. */
	private DocumentosJustificanteSolicitudSpring documentosJustificanteSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosJustificanteSolicitudTest.class);
	
	/** el properties. */
	private static Properties properties;
	
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
		mapping = springMappingManager.getMapping("/spring/documentosJustificanteSolicitud", DocumentosJustificanteSolicitudSpring.class);
		
		//Se cargan los managers
		documentosJustificanteSolicitudSpring = new DocumentosJustificanteSolicitudSpring();
		documentosJustificanteSolicitudSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		documentosJustificanteSolicitudSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		properties = (Properties) getBean("propertiesBean");
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
		
			System.out.println("Iniciando test documentosJustificanteSolicitud");
	        
			CrearDocumentoForm formulario = new CrearDocumentoForm();
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idDocumento = "41444";
			request.setParameter("id", idDocumento);			
	        this.request = request; 
//	        formulario.setId
	        
	        ArrayList<DocumentoBean> documentosList;
	        
	        DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.addSolicitudIn(Long.valueOf(idDocumento));
			Integer tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF;
			documentoQuery.addTipoDocumentoIn(tpDocumento);
			tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_CATALAN;
			documentoQuery.addTipoDocumentoIn(tpDocumento);
			tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_EUSKERA;
			documentoQuery.addTipoDocumentoIn(tpDocumento);
			tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_GALLEGO;
			documentoQuery.addTipoDocumentoIn(tpDocumento);
			tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_VALENCIANO;
			documentoQuery.addTipoDocumentoIn(tpDocumento);
			
	        documentosList = documentosJustificanteSolicitudSpring.getDocumentoManager().buscarDocumentoCombo(documentoQuery);
	        if(documentosList.isEmpty()) {
	        	logger.error("No se encuentra el documento");
	        	return;
	        }else {
	        	String ruta = properties.getProperty("sistemaficheros.url.final")+documentosList.get(0).getUbicacion()+documentosList.get(0).getNombreAlfresco();
				if(!documentosJustificanteSolicitudSpring.getDocumentoManager().existeDocumento(ruta)) {
					logger.error("No existe el documento en el servidor actual");
					return;
				}
	        }
	        
			SpringForward rtrn = documentosJustificanteSolicitudSpring.execute(mapping, formulario, request, response);
			
			if (rtrn.getPath() == null) {
				System.out.println("Finalizando test documentosJustificanteSolicitud");
			}		
			else throw new Exception("Error");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test documentosJustificanteSolicitud con errores");
			fail();
		}
		
		
	}
	
	
}
