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
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.spring.solicitudes.MostrarAvisoPaginaSpring;


/**
 * El Class MostrarAvisoPaginaTest.
 */
public class MostrarAvisoPaginaTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el mostrar aviso pagina spring. */
	private MostrarAvisoPaginaSpring mostrarAvisoPaginaSpring;
	
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
		mapping = springMappingManager.getMapping("/secure/mostrarAvisoPagina", MostrarAvisoPaginaSpring.class);
		
		//Se cargan los managers
		mostrarAvisoPaginaSpring = new MostrarAvisoPaginaSpring();
				
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
			
	        
			System.out.println("Iniciando test mostrarAvisoPagina");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			SpringForm formulario = new SpringForm();
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("accion", "pago");				
					
	        this.request = request; 
					
	    	SpringForward rtrn = mostrarAvisoPaginaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test mostrarAvisoPagina");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test mostrarAvisoPagina con errores");
			fail();
		} 
		
		
	}
	
	
}
