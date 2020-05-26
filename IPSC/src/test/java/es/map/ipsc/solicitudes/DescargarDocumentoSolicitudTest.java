package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.solicitudes.DescargarDocumentoSolicitudSpring;
import es.map.ipsc.utils.SistemaFicheros;


/**
 * El Class DescargarDocumentoSolicitudTest.
 */
@ContextConfiguration({"classpath:spring-forward-config.xml"})
public class DescargarDocumentoSolicitudTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el descargar documento solicitud spring. */
	private DescargarDocumentoSolicitudSpring descargarDocumentoSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoSolicitudTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/descargarDocumentoSolicitud", DescargarDocumentoSolicitudSpring.class);
		
		//Se cargan los managers
		descargarDocumentoSolicitudSpring = new DescargarDocumentoSolicitudSpring();
		descargarDocumentoSolicitudSpring.setDocumentosConvocatoriaManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		descargarDocumentoSolicitudSpring.setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));
		
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        ServletOutputStream servletOutputStream;
        servletOutputStream = mock(ServletOutputStream.class);
       
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
	        
			System.out.println("Iniciando test descargarDocumentoSolicitud");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			SpringForm formulario = new SpringForm();
			String idDocumento = "25110";
//			String idDocumento = "24305";
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("id", idDocumento);				
			//Carga del usuario
			 CiudadanoBean ciudadano = new CiudadanoBean();
			 ciudadano.setNif("11111111H");
			 ciudadano.setNombre("JUAN");
			 ciudadano.setPrimerApellido("ESPAÑOL");
			 ciudadano.setSegundoApellido("ESPAÑOL");
			 ciudadano.setEmail("lala@lala.es");
			 ciudadano.setTipoAutenticacion("aFirma");
			 ciudadano.setTipoPersona("FH");
			 request.getSession().setAttribute("usuarioClave",ciudadano);
			
	        this.request = request; 
	        
	        DocumentoBean doc;
	        DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setId(Long.parseLong(idDocumento));
	        doc = descargarDocumentoSolicitudSpring.getDocumentosConvocatoriaManager().obtenerEstado(documentoQuery);
	        if(doc ==null) {	        	
	        	logger.error("No se ha podido ejecutar el junit porque no existe el documento en el servidor");
	        	return;
	        }else {
	        	byte[] salida = null;
	        	SistemaFicheros sistema = new SistemaFicheros();
				salida = sistema.obtenerContenido(doc);
				if(salida == null) {
					logger.error("El documento no se encuentra en el servidor");
					return;
				}
	        }	        
	        
			SpringForward rtrn = descargarDocumentoSolicitudSpring.execute(mapping, formulario, request, response);
			if (rtrn.getPath() == null) {
	    		System.out.println("Finalizando test descargarDocumentoSolicitud");
	    	}
	    	else {
	    		throw new Exception("Error"); //Este junits solamente da error si contiene error.
	    	}			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test descargarDocumentoSolicitud con errores");
			fail();
		} 
		
		
	}
	
	
}
