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
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.BuscaConvocatoriasForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.convocatorias.BuscarConvocatoriasSpring;
import es.map.ipsc.spring.solicitudes.ActualizarEstadoSolicitudesSpring;
import org.apache.log4j.Logger;


/**
 * El Class ActualizarEstadoSolicitudesTest.
 */
public class ActualizarEstadoSolicitudesTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el actualizar estado solicitudes spring. */
	private ActualizarEstadoSolicitudesSpring actualizarEstadoSolicitudesSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarEstadoSolicitudesTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/ActualizarEstadoSolicitud", ActualizarEstadoSolicitudesSpring.class);
		
		//Se cargan los managers
		actualizarEstadoSolicitudesSpring = new ActualizarEstadoSolicitudesSpring();
		actualizarEstadoSolicitudesSpring.setSolicitudesManager((SolicitudManager) getBean ("solicitudesManager"));
		actualizarEstadoSolicitudesSpring.setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
		actualizarEstadoSolicitudesSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		actualizarEstadoSolicitudesSpring.setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
				
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
			
	        
			System.out.println("Iniciando test actualizarEstadoSolicitudes");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			String idSolicitud = "38424";
			
			SpringForm formulario = new SpringForm();
		
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("registro", idSolicitud);						
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
	        SolicitudBean solicitudBean = actualizarEstadoSolicitudesSpring.getSolicitudesManager().buscarSolicitudById(solicitudComunQuery);
	        if(solicitudBean != null)
			{
				
				if(!solicitudBean.getNif().equals(ciudadano.getNif())) {
					logger.error("La solicitud no corresponde al usuario del NIF actual");
					return;
				}
			}else {
				logger.error("La solicitud no existe");
				return;
			}	       
	        
			SpringForward rtrn = actualizarEstadoSolicitudesSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test actualizarEstadoSolicitudes");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test actualizarEstadoSolicitudes con errores");
			fail();
		} 
		
		
	}
	
	
}
