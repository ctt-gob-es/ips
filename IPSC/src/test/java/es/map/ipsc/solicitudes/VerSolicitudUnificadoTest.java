package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.SolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.comunidades.ComunidadesManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.RegistroSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudCcaaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.TarifaManager;
import es.map.ipsc.spring.solicitudes.VerRegistroSolicitudSpring;
import es.map.ipsc.spring.solicitudes.VerSolicitudUnificado;


/**
 * El Class VerSolicitudUnificadoTest.
 */
@ContextConfiguration({"classpath:spring-forward-config.xml"})
public class VerSolicitudUnificadoTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver solicitud unificado. */
	private VerSolicitudUnificado verSolicitudUnificado;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerSolicitudUnificadoTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/verRegistroSolicitud", VerRegistroSolicitudSpring.class);
		
		//Se cargan los managers
		verSolicitudUnificado = new VerSolicitudUnificado();
		verSolicitudUnificado.setRegistroSolicitudManager((RegistroSolicitudManager) getBean ("registroSolicitudesManager"));
		verSolicitudUnificado.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		verSolicitudUnificado.setTablasBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
		verSolicitudUnificado.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verSolicitudUnificado.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		verSolicitudUnificado.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		verSolicitudUnificado.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
		verSolicitudUnificado.setDocumentosConvocatoriaManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		verSolicitudUnificado.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		verSolicitudUnificado.setTarifaManager((TarifaManager) getBean("tarifaManager"));
		verSolicitudUnificado.setComunidadesManager((ComunidadesManager) getBean("comunidadesManager"));
		verSolicitudUnificado.setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
		verSolicitudUnificado.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerpoEscalaManager"));
				
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
			
	        
			System.out.println("Iniciando test verSolicitudUnificado");
			
			//Introducir los parametros de entrada
			
			
			SolicitudForm formulario = new SolicitudForm();
			
			String idConvocatoria = "8918";
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");			
			request.setParameter("id", idConvocatoria);
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
	        	        
	        ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			convocatoriaQuery.setId(Long.valueOf(idConvocatoria));	

	        ConvocatoriaBean convocatoriaBean = verSolicitudUnificado.getConvocatoriaManager().recuperarConvocatoria(Long.valueOf(idConvocatoria));
	        SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			solicitudQuery.setConvocatoria(convocatoriaQuery);
			solicitudQuery.setNif(ciudadano.getNif());
			SolicitudBean solicitudRegistroBean = verSolicitudUnificado.getSolicitudesManager().buscarRegistroSolicitud(solicitudQuery);
	        																
			if(convocatoriaBean != null && solicitudRegistroBean != null) {
				if(convocatoriaBean.getIdEstado() != 5 && !solicitudRegistroBean.getIdEstadoSolicitud().equals("2")) {
					logger.error("La convocatoria no tiene el estado publicado o la solicitud no tiene el estado NO REGISTRADO");
					return;
				}								
			}else {
				logger.error("La convocatoria no existe o la solicitud de esa convocatoria no existe");
				return;
			}			
			
	    	SpringForward rtrn = verSolicitudUnificado.execute(mapping, formulario, request, response);
	    	if (rtrn.getPath() == null) {
	    		System.out.println("Finalizando test verSolicitudUnificado");			
			}
			else if (rtrn.getPath()!=null && rtrn.getPath().contains("error")) throw new Exception("Error");	
			
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verSolicitudUnificado con errores");
			fail();
		} 
		
		
	}
	
	
}
