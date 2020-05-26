package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.spring.solicitudes.DocumentosSolicitudSpring;
import es.map.ipsc.spring.solicitudes.InformacionAdicional;


/**
 * El Class InformacionAdicionalTest.
 */
public class InformacionAdicionalTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el informacion adicional. */
	private InformacionAdicional informacionAdicional;
	
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
		mapping = springMappingManager.getMapping("/secure/informacionAdicional", InformacionAdicional.class);
		
		//Se cargan los managers
		informacionAdicional = new InformacionAdicional();
				
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
			
	        
			System.out.println("Iniciando test informacionAdicional");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			SpringForm formulario = new SpringForm();
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("llamada", "A");				
					
	        this.request = request; 
					
	    	SpringForward rtrn = informacionAdicional.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test informacionAdicional");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test informacionAdicional con errores");
			fail();
		} 
		
		
	}
	
	
}
