package es.map.ipsc.solicitudes;

import static org.mockito.Mockito.mock;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.form.SolicitudForm;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.solicitudes.VerAltaSolicitudSpring;


/**
 * El Class VerAltaSolicitudTest.
 */
public class VerAltaSolicitudTest extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver alta solicitud spring. */
	private VerAltaSolicitudSpring verAltaSolicitudSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaSolicitudTest.class);
	
	
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
		mapping = springMappingManager.getMapping("/secure/verAltaSolicitud", VerAltaSolicitudSpring.class);
		
		//Se cargan los managers
		verAltaSolicitudSpring = new VerAltaSolicitudSpring();
		verAltaSolicitudSpring.setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
		verAltaSolicitudSpring.setTablasBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
		verAltaSolicitudSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		verAltaSolicitudSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		verAltaSolicitudSpring.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
	
				
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
			
	        
			System.out.println("Iniciando test verAltaSolicitud");
			
			//Introducir los parametros de entrada
			
			
			SolicitudForm formulario = new SolicitudForm();
			String idConvocatoria = "9101";
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
			 request.getSession().setAttribute("usuario",ciudadano);
			 
		
	        this.request = request; 
	        	        
	        ConvocatoriaBean convocatoriaBean = verAltaSolicitudSpring.getConvocatoriaManager().recuperarConvocatoria(Long.valueOf(idConvocatoria));
			if(convocatoriaBean != null)
			{						
				Date today = new Date();	
				if (convocatoriaBean.getIdEstado() != 5) {
					logger.error("La convocatoria no tiene el estado publicado");
					return;
				}
				if (convocatoriaBean.getFechaFin() != null && today.after(convocatoriaBean.getFechaFin()) && !DateUtils.isSameDay(today, convocatoriaBean.getFechaFin()) ) {
					logger.error("La convocatoria esta cerrada");
					return;
				}
			}else {
				logger.error("La convocatoria no existe");
				return;
			}
			
					
	    	SpringForward rtrn = verAltaSolicitudSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");			
			System.out.println("Finalizando test verAltaSolicitud");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verAltaSolicitud con errores");
			fail();
		} 
		
		
	}
	
	
}
