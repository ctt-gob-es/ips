package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.DetalleSolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.solicitudes.DetallePagoSolicitudesSpring;


/**
 * El Class DetallePagoSolicitudesTest.
 */
public class DetallePagoSolicitudesTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el detalle pago solicitudes spring. */
	private DetallePagoSolicitudesSpring detallePagoSolicitudesSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DetallePagoSolicitudesTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/DetallePagoSolicitud", DetallePagoSolicitudesSpring.class);
		
		//Se cargan los managers
		detallePagoSolicitudesSpring = new DetallePagoSolicitudesSpring();
		detallePagoSolicitudesSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean ("pagoSolicitudesManager"));
		detallePagoSolicitudesSpring.setSolicitudesManager((SolicitudManager) getBean ("solicitudesManager"));
		
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
			
	        
			System.out.println("Iniciando test detallePagoSolicitudes");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			DetalleSolicitudForm formulario = new DetalleSolicitudForm();
			String idSolicitud = "38424";
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("registro", idSolicitud);				
			//Carga del usuario
			 CiudadanoBean ciudadano = new CiudadanoBean();
			 ciudadano.setNif("11111111H");
			 ciudadano.setNombre("JUAN");
			 ciudadano.setPrimerApellido("ESPA�OL");
			 ciudadano.setSegundoApellido("ESPA�OL");
			 ciudadano.setEmail("lala@lala.es");
			 ciudadano.setTipoAutenticacion("aFirma");
			 ciudadano.setTipoPersona("C");
			 request.getSession().setAttribute("usuario",ciudadano);
			
	        this.request = request; 
					
	        SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
	        solicitudComunQuery.setIdSolicitud(Long.valueOf(idSolicitud));
	        SolicitudBean solicitudBean = detallePagoSolicitudesSpring.getSolicitudesManager().buscarSolicitudById(solicitudComunQuery);
	        if(solicitudBean != null)
			{				
				if(!solicitudBean.getNif().equals(ciudadano.getNif())) {
					logger.error("El nif de la solicitud no corresponde con el usuario actual");
					return;
				}
			}
	        else{
	        	logger.error("La solicitud no existe");
	        	return;
	        }
			SpringForward rtrn = detallePagoSolicitudesSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test detallePagoSolicitudes");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test detallePagoSolicitudes con errores");
			fail();
		} 
		
		
	}
	
	
}
