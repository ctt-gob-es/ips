package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.solicitudes.ReanudarSolicitudSpring;


/**
 * El Class ReanudarSolicitudTest.
 */
public class ReanudarSolicitudTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el reanudar solicitud spring. */
	private ReanudarSolicitudSpring reanudarSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ReanudarSolicitudTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/reanudarSolicitud", ReanudarSolicitudSpring.class);
		
		//Se cargan los managers
		reanudarSolicitudSpring = new ReanudarSolicitudSpring();
		reanudarSolicitudSpring.setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));

	
				
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
			
	        
			System.out.println("Iniciando test reanudarSolicitud");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			SpringForm formulario = new SpringForm();
	
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idSolicitud = "39280";
			request.setParameter("registro", idSolicitud);				
			//Carga del usuario
			 CiudadanoBean ciudadano = new CiudadanoBean();
			 ciudadano.setNif("111111411H");
			 ciudadano.setNombre("JUAN");
			 ciudadano.setPrimerApellido("ESPAÑOL");
			 ciudadano.setSegundoApellido("ESPAÑOL");
			 ciudadano.setEmail("lala@lala.es");
			 ciudadano.setTipoAutenticacion("aFirma");
			 ciudadano.setTipoPersona("C");
			 request.getSession().setAttribute("usuario",ciudadano);			 		
	        this.request = request; 
				        
	        SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
	        solicitudComunQuery.setIdSolicitud(Long.valueOf(idSolicitud));	        
	        SolicitudBean solicitudBean = reanudarSolicitudSpring.getSolicitudManager().buscarSolicitudById(solicitudComunQuery); 
	        if(solicitudBean != null)
			{				
				if(!solicitudBean.getNif().equals(ciudadano.getNif())) {
					logger.error("El nif de la solicitud no corresponde con el usuario actual");
					return;
				}
			}else {
				logger.error("La solicitud no existe");
				return;
			}	        
	        
	    	SpringForward rtrn = reanudarSolicitudSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test reanudarSolicitud");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test reanudarSolicitud con errores");
			fail();
		} 
		
		
	}
	
	
}
