package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.provincia.ProvinciaManager;
import es.map.ipsc.spring.solicitudes.ValidarCPAjax;


/**
 * El Class ValidarCPAjaxTest.
 */
public class ValidarCPAjaxTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el validar CP ajax. */
	private ValidarCPAjax validarCPAjax;
	
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
		mapping = springMappingManager.getMapping("/secure/validarCP", ValidarCPAjax.class);
		
		//Se cargan los managers
		validarCPAjax = new ValidarCPAjax();
		validarCPAjax.setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
	
				
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
			
	        
			System.out.println("Iniciando test validarCPAjax");
			
			//Introducir los parametros de entrada
			
			
			SpringForm formulario = new SpringForm();
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
		
			byte[] content = "idProvincia=31&codigoPostal=30530".getBytes();
			request.setContent(content);

		
	        this.request = request; 
					
	    	SpringForward rtrn = validarCPAjax.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test validarCPAjax");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test validarCPAjax con errores");
			fail();
		} 
		
		
	}
	
	
}
