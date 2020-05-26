package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.form.BuscarSolicitudesForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.solicitudes.BuscarSolicitudesSpring;


/**
 * El Class BuscarSolicitudesTest.
 */
public class BuscarSolicitudesTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el buscar solicitudes spring. */
	private BuscarSolicitudesSpring buscarSolicitudesSpring;
	
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
		mapping = springMappingManager.getMapping("/secure/buscarSolicitudes", BuscarSolicitudesSpring.class);
		
		//Se cargan los managers
		buscarSolicitudesSpring = new BuscarSolicitudesSpring();
		buscarSolicitudesSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		buscarSolicitudesSpring.setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
		buscarSolicitudesSpring.setPagoSolicitudManagar((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		buscarSolicitudesSpring.setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
				
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
			
	        
			System.out.println("Iniciando test buscarSolicitudes");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			BuscarSolicitudesForm formulario = new BuscarSolicitudesForm();
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("submit", "Buscar");			
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
					
			
			SpringForward rtrn = buscarSolicitudesSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test buscarSolicitudes");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test buscarSolicitudes con errores");
			fail();
		} 
		
		
	}
	
	
}
