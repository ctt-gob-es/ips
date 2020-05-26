package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.form.CargaJustificanteAjaxForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.solicitudes.SolicitudComunAuxiliarManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.solicitudes.CargaJustificanteAjaxSpring;


/**
 * El Class CargaJustificanteAjaxTest.
 */
public class CargaJustificanteAjaxTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el carga justificante ajax spring. */
	private CargaJustificanteAjaxSpring cargaJustificanteAjaxSpring;
	
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
		mapping = springMappingManager.getMapping("/secure/cargaJustificanteAjax", CargaJustificanteAjaxSpring.class);
		
		//Se cargan los managers
		cargaJustificanteAjaxSpring = new CargaJustificanteAjaxSpring();
		cargaJustificanteAjaxSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		cargaJustificanteAjaxSpring.setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
		
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
	        
			System.out.println("Iniciando test cargaJustificanteAjax");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			CargaJustificanteAjaxForm formulario = new CargaJustificanteAjaxForm();
			formulario.setNumJustificantePresencial("7900019081911");
			formulario.setIdConvocatoria("8422");
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			MockHttpServletResponse mockResponse = new MockHttpServletResponse();
			mockResponse.setWriterAccessAllowed(true);
			mockResponse.getWriter();
			this.response = mockResponse;
			request.setParameter("subit", "Buscar");			
			//Carga del usuario
			 CiudadanoBean ciudadano = new CiudadanoBean();
			 ciudadano.setNif("11111111H");
			 ciudadano.setNombre("JUAN");
			 ciudadano.setPrimerApellido("ESPAÑOL");
			 ciudadano.setSegundoApellido("ESPAÑOL");
			 ciudadano.setEmail("lala@lala.es");
			 ciudadano.setTipoAutenticacion("aFirma");
			 ciudadano.setTipoPersona("C");
			 request.getSession().setAttribute("usuarioClave",ciudadano);
			
	        this.request = request; 
					
			
			SpringForward rtrn = cargaJustificanteAjaxSpring.execute(mapping, formulario, request, response);
			if (rtrn.getPath() == null) {
				System.out.println("Finalizando test cargaJustificanteAjax");			
			}
			else if (rtrn.getPath()!=null && rtrn.getPath().contains("error")) throw new Exception("Error");
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test cargaJustificanteAjax con errores");
			fail();
		} 
		
		
	}
	
	
}
