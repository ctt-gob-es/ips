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
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.form.RegistroSolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.camposPropios.CamposPropiosManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.logs.LogServiciosManager;
import es.map.ipsc.manager.registroAuditoria.RegistroAuditoriaManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.RegistroSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudPropioManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.usuario.UsuarioManager;
import es.map.ipsc.spring.solicitudes.RegistroSolicitudSpring;


/**
 * El Class RegistroSolicitudTest.
 */
public class RegistroSolicitudTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el registro solicitud spring. */
	private RegistroSolicitudSpring registroSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RegistroSolicitudTest.class);
	
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
		mapping = springMappingManager.getMapping("/secure/registroSolicitud", RegistroSolicitudSpring.class);
		
		//Se cargan los managers
		registroSolicitudSpring = new RegistroSolicitudSpring();
		registroSolicitudSpring.setRegistroSolicitudesManager((RegistroSolicitudManager) getBean("registroSolicitudesManager"));
		registroSolicitudSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		registroSolicitudSpring.setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
		registroSolicitudSpring.setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		registroSolicitudSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		registroSolicitudSpring.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
		registroSolicitudSpring.setLogServiciosManager((LogServiciosManager) getBean("logServiciosManager"));
		registroSolicitudSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		registroSolicitudSpring.setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		registroSolicitudSpring.setSolicitudPropioManager((SolicitudPropioManager) getBean("solicitudPropioManager"));
		registroSolicitudSpring.setRegistroAuditoriaManager((RegistroAuditoriaManager) getBean("registroAuditoriaManager"));
		registroSolicitudSpring.setCamposPropiosManager((CamposPropiosManager) getBean("camposPropiosManager"));
	
				
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
			
	        
			System.out.println("Iniciando test registroSolicitud");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			RegistroSolicitudForm formulario = new RegistroSolicitudForm();
			String idConvocatoria = "8429";
			formulario.setIdConvocatoria(idConvocatoria);
			formulario.setIdSolicitud("39302");
			formulario.setNif("11111111H");
			formulario.setFirmaExtractoRegistro("");
			
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("registro", "39280");				
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

	       
	        
	        ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			convocatoriaQuery.setId(Long.valueOf(idConvocatoria));
			ConvocatoriaBean convocatoriaBean;
			
			try {
			convocatoriaBean = registroSolicitudSpring.getConvocatoriaManager().buscarConvocatoriaId(convocatoriaQuery);
			if(convocatoriaBean.getIdEstado() != 5) {
				logger.error("La convocatoria no tiene el estado publicado");
				return;
			}
			}catch(NullPointerException ex) {
				logger.error("La convocatoria no existe");
				return;
			}	        	        
	        
	    	SpringForward rtrn = registroSolicitudSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test registroSolicitud");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test registroSolicitud con errores");
			fail();
		} 
		
		
	}
	
	
}
