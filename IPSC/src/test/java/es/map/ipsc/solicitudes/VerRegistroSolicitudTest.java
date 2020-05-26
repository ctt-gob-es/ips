package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.RegistroSolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.solicitudes.VerRegistroSolicitudSpring;


/**
 * El Class VerRegistroSolicitudTest.
 */
public class VerRegistroSolicitudTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver registro solicitud spring. */
	private VerRegistroSolicitudSpring verRegistroSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerRegistroSolicitudTest.class);
	
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
		verRegistroSolicitudSpring = new VerRegistroSolicitudSpring();
		verRegistroSolicitudSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		verRegistroSolicitudSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verRegistroSolicitudSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
				
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
			
	        
			System.out.println("Iniciando test verRegistroSolicitud");
			
			//Introducir los parametros de entrada
			
			
			RegistroSolicitudForm formulario = new RegistroSolicitudForm();
			String idSolicitud = "41343";
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");			
			request.setParameter("id", idSolicitud);
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
				        
	        SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(Long.valueOf(idSolicitud));
	        SolicitudBean solicitudBean = verRegistroSolicitudSpring.getSolicitudesManager().buscarSolicitudById(solicitudComunQuery);
			if(solicitudBean != null)
			{									
				ConvocatoriaBean convocatoriaBean = verRegistroSolicitudSpring.getConvocatoriaManager().recuperarConvocatoria(solicitudBean.getIdConvocatoria());				
				if(convocatoriaBean!=null && convocatoriaBean.getIdEstado() == 5 && solicitudBean.getIdEstadoSolicitud() == "2") {
					logger.error("La solicitud no tiene el estado NO REGISTRADO o  su convocatoria no tiene el estado publicado");				
				}
			}else {
				logger.error("La solicitud no existe");
				return;
			}			
	    	SpringForward rtrn = verRegistroSolicitudSpring.execute(mapping, formulario, request, response);
	    	if (rtrn.getPath() == null) {
	    		System.out.println("Finalizando test verRegistroSolicitud");			
			}
			else if (rtrn.getPath()!=null && rtrn.getPath().contains("error")) throw new Exception("Error");				
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verRegistroSolicitud con errores");
			fail();
		} 
		
		
	}
	
	
}
