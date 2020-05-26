package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.junit.AbstractSpringCommonTestCase;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.spring.solicitudes.VerPasarelaPagoSpring;


/**
 * El Class VerPasarelaPagoTest.
 */
public class VerPasarelaPagoTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver pasarela pago spring. */
	private VerPasarelaPagoSpring verPasarelaPagoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerPasarelaPagoTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/verPasarelaPago", VerPasarelaPagoSpring.class);
		
		//Se cargan los managers
		verPasarelaPagoSpring = new VerPasarelaPagoSpring();
		verPasarelaPagoSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		verPasarelaPagoSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verPasarelaPagoSpring.setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));
		verPasarelaPagoSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		verPasarelaPagoSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
	
				
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
			
	        
			System.out.println("Iniciando test verPasarelaPago");
			
			//Introducir los parametros de entrada
			
			String idSolicitud = "39165";
			PagoSolicitudForm formulario = new PagoSolicitudForm();
			formulario.setIdSolicitud(idSolicitud);
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("siglas", "INAP");
			request.setParameter("registro", "3820");
			//Carga del usuario
			 CiudadanoBean ciudadano = new CiudadanoBean();
			 ciudadano.setNif("11111111H");
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
			SolicitudBean solicitudBean = verPasarelaPagoSpring.getSolicitudesManager().buscarSolicitudById(solicitudComunQuery);
			if(solicitudBean == null) {
				logger.error("La solicitud no existe");
				return;
			}			
	    	SpringForward rtrn = verPasarelaPagoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test verPasarelaPago");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verPasarelaPago con errores");
			fail();
		} 
		
		
	}
	
	
}
