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
import es.map.ipsc.form.SolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.funcionarioHabilitado.FuncionarioHabilitadoManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.provincia.ProvinciaManager;
import es.map.ipsc.manager.registroAuditoria.RegistroAuditoriaManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudCcaaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudPropioManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.solicitudes.SolicitudSpring;


/**
 * El Class SolicitudTest.
 */
public class SolicitudTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el solicitud spring. */
	private SolicitudSpring solicitudSpring;
	
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
		mapping = springMappingManager.getMapping("/secure/iniciarSolicitud", SolicitudSpring.class);
		
		//Se cargan los managers
		solicitudSpring = new SolicitudSpring();
		solicitudSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		solicitudSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		solicitudSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		solicitudSpring.setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
		solicitudSpring.setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
		solicitudSpring.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
		solicitudSpring.setPagoSolicitudesManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		solicitudSpring.setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
		solicitudSpring.setSolicitudPropioManager((SolicitudPropioManager) getBean("solicitudPropioManager"));
		solicitudSpring.setRegistroAuditoriaManager((RegistroAuditoriaManager) getBean("registroAuditoriaManager"));
		solicitudSpring.setFuncionarioHabilitadoManager((FuncionarioHabilitadoManager) getBean("funcionarioHabilitadoManager"));
		solicitudSpring.setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
	
				
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
			
	        
			System.out.println("Iniciando test solicitud");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			SolicitudForm formulario = new SolicitudForm();
			formulario.setIdConvocatoria("8844");
			formulario.setNif("11111111H");
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("registro", "40699");				
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
			 request.getSession().setAttribute("ciudadanoInscrito",ciudadano);
	        this.request = request; 
					
	    	SpringForward rtrn = solicitudSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test solicitud");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test solicitud con errores");
			fail();
		} 
		
		
	}
	
	
}
