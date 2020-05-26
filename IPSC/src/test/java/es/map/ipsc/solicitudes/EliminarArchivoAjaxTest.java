package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.solicitudes.EliminarArchivoAjax;

/**
 * El Class EliminarArchivoAjaxTest.
 */
@ContextConfiguration({"classpath:spring-forward-config.xml"})
public class EliminarArchivoAjaxTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el session. */
	private HttpSession session;
	
	/** el eliminar archivo ajax. */
	private EliminarArchivoAjax eliminarArchivoAjax;
	
	/** el id documento. */
	private String idDocumento;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarArchivoAjaxTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/eliminarArchivoAjax", EliminarArchivoAjax.class);
		
		//Se cargan los managers
		eliminarArchivoAjax = new EliminarArchivoAjax();
		eliminarArchivoAjax.setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		eliminarArchivoAjax.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		
		//Se carga simulacion request response y session
		MockHttpServletResponse response = new MockHttpServletResponse();
        this.request = mock(HttpServletRequest.class);  
        this.response = response;   
        
        MockHttpSession session = new MockHttpSession();
        this.session = mock (HttpSession.class);
        idDocumento = "25884";
        InputStream is =  IOUtils.toInputStream("idDocumento="+idDocumento);
        
        ServletInputStream servletInputStream;

        servletInputStream = mock(ServletInputStream.class);
        servletInputStream=new ServletInputStream(){
            public int read() throws IOException {
              return is.read();
            }
          };
      
        when(request.getSession()).thenReturn(session);
        
        when(request.getInputStream()).thenReturn(servletInputStream);

	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
	        
			System.out.println("Iniciando test eliminarArchivo");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			CrearDocumentoForm formulario = new CrearDocumentoForm();
			
			request.setAttribute("registro", "39280");				
//			//Carga del usuario
			 CiudadanoBean ciudadano = new CiudadanoBean();
			 ciudadano.setNif("11111111H");
			 ciudadano.setNombre("JUAN");
			 ciudadano.setPrimerApellido("ESPAÑOL");
			 ciudadano.setSegundoApellido("ESPAÑOL");
			 ciudadano.setEmail("lala@lala.es");
			 ciudadano.setTipoAutenticacion("aFirma");
			 ciudadano.setTipoPersona("C");
			 request.getSession().setAttribute("usuarioClave",ciudadano);
			
			
			 // Antes de ejecutar el test comprobamos si existe el archivo, sino existe no se ejecutará
			 DocumentoQuery documentoPathQuery = new DocumentoQuery();
			 DocumentoBean documentoBuscado = new DocumentoBean();
			 documentoPathQuery.setId(Long.parseLong(idDocumento));
	        	documentoBuscado = eliminarArchivoAjax.getDocumentoConvocatoriasManager().obtenerDocumento(documentoPathQuery); 
        	if(documentoBuscado == null) {
        		logger.error("El archivo a eliminar no existe.");
				return;
			}	
        	// Obtenemos la solicitud del documento para comprobar si coincide con el usuario actual
        	SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			solicitudQuery.setIdSolicitud(documentoBuscado.getIdSolicitud());
			SolicitudBean solicitud = eliminarArchivoAjax.getSolicitudesManager().buscarSolicitudById(solicitudQuery);
			if(solicitud == null){
				logger.error("No existe solicitud asociada al documento definido en el test. ");
				return;
			}
			if(solicitud.getNif() == null || !solicitud.getNif().equals(ciudadano.getNif())){
				logger.error("El usuario definido en el test no coincide con el usuario corrrespondiente al documento. ");
				return;
			}
        	
        	
	    	SpringForward rtrn = eliminarArchivoAjax.execute(mapping, formulario, request, response);
			if (rtrn.getPath() == null) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath() != null && rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test eliminarArchivo");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarArchivo con errores");
			fail();
		} 
		
		
	}
	
	
}
