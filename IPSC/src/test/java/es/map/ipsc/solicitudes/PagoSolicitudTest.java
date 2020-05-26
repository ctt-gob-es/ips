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
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.logs.LogServiciosManager;
import es.map.ipsc.manager.registroAuditoria.RegistroAuditoriaManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudCcaaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.spring.solicitudes.PagoSolicitudSpring;


/**
 * El Class PagoSolicitudTest.
 */
public class PagoSolicitudTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el pago solicitud spring. */
	private PagoSolicitudSpring pagoSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PagoSolicitudTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/pagoSolicitud", PagoSolicitudSpring.class);
		
		//Se cargan los managers
		pagoSolicitudSpring = new PagoSolicitudSpring();
		pagoSolicitudSpring.setPagoSolicitudesManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		pagoSolicitudSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		pagoSolicitudSpring.setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
		pagoSolicitudSpring.setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));
		pagoSolicitudSpring.setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
		pagoSolicitudSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		pagoSolicitudSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		pagoSolicitudSpring.setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
		pagoSolicitudSpring.setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		pagoSolicitudSpring.setLogServiciosManager((LogServiciosManager) getBean("logServiciosManager"));
		pagoSolicitudSpring.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
		pagoSolicitudSpring.setRegistroAuditoriaManager((RegistroAuditoriaManager) getBean("registroAuditoriaManager"));
				
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
			
	        
			System.out.println("Iniciando test pagoSolicitud");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			String idSolicitud = "7900079127376";
			PagoSolicitudForm formulario = new PagoSolicitudForm();
			formulario.setNumeroSolicitud(idSolicitud);
			formulario.setNif("11111111H");
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("accion", "pago");				
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
					
	        	        
	        SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
	        solicitudComunQuery.setNumeroSolicitud(idSolicitud);
	        solicitudComunQuery.setNif(formulario.getNif());
	        SolicitudBean solicitudBean = pagoSolicitudSpring.getSolicitudesManager().buscarSolicitudById(solicitudComunQuery); 
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
	       
	    	SpringForward rtrn = pagoSolicitudSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test pagoSolicitud");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test pagoSolicitud con errores");
			fail();
		} 
		
		
	}
	
	
}
