package es.map.ipsc.solicitudes;

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
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.logs.LogServiciosManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.spring.solicitudes.ConsultaPagoSpring;


/**
 * El Class ConsultaPagoTest.
 */
public class ConsultaPagoTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el consulta pago spring. */
	private ConsultaPagoSpring consultaPagoSpring;
	
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
		mapping = springMappingManager.getMapping("/secure/consultaPago", ConsultaPagoSpring.class);
		
		//Se cargan los managers
		consultaPagoSpring = new ConsultaPagoSpring();
		consultaPagoSpring.setPagoSolicitudesManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		consultaPagoSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		consultaPagoSpring.setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));
		consultaPagoSpring.setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
		consultaPagoSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		consultaPagoSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		consultaPagoSpring.setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
		consultaPagoSpring.setLogServiciosManager((LogServiciosManager) getBean("logServiciosManager"));
		consultaPagoSpring.setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		
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
			
	        
			System.out.println("Iniciando test consultaPago");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			PagoSolicitudForm formulario = new PagoSolicitudForm();
			formulario.setNumeroSolicitud("34104");
			formulario.setImporte(0f);
			formulario.setFormaPago("C");
			formulario.setOrigenFirma("Prueba");
			formulario.setSignature("Prueba");
			formulario.setSignerCert("Prueba");
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
					
			
			SpringForward rtrn = consultaPagoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test consultaPago");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test consultaPago con errores");
			fail();
		} 
		
		
	}
	
	
}
