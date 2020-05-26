package es.map.ipsc.incidencias;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.form.BuscaConvocatoriasForm;
import es.map.ipsc.form.IncidenciasForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.ciudadano.CiudadanoManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.incidencias.IncidenciasManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.convocatorias.BuscarConvocatoriasSpring;
import es.map.ipsc.spring.incidencias.ReportarIncidenciasSpring;


/**
 * El Class ReportarIncidenciasTest.
 */
public class ReportarIncidenciasTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el reportar incidencias spring. */
	private ReportarIncidenciasSpring reportarIncidenciasSpring;
	
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
		mapping = springMappingManager.getMapping("/secure/reportarIncidencias", ReportarIncidenciasSpring.class);
		
		//Se cargan los managers
		reportarIncidenciasSpring = new ReportarIncidenciasSpring();
		reportarIncidenciasSpring.setIncidenciasManager((IncidenciasManager) getBean("incidenciasManager"));
		reportarIncidenciasSpring.setCiudadanoManager((CiudadanoManager) getBean("ciudadanoManager"));
		reportarIncidenciasSpring.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
		
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
			
	        
			System.out.println("Iniciando test reportarIncidencias");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			IncidenciasForm formulario = new IncidenciasForm();
			formulario.setTexto("PRueba");
			formulario.setAsunto("prueba");
	
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
				
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
					
			
			SpringForward rtrn = reportarIncidenciasSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test reportarIncidencias");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test reportarIncidencias con errores");
			fail();
		} 
		
		
	}
	
	
}
