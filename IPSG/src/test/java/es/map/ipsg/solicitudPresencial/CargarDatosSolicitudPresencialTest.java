package es.map.ipsg.solicitudPresencial;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ipsg.action.solicitudPresencial.CargarDatosSolicitudPresencialSpring;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ComunidadesManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PaisManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.manager.TipoDiscapacidadManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.TituloOficialManager;


/**
 * El Class CargarDatosSolicitudPresencialTest.
 */
public class CargarDatosSolicitudPresencialTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el cargar datos solicitud presencial spring. */
	private CargarDatosSolicitudPresencialSpring cargarDatosSolicitudPresencialSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CargarDatosSolicitudPresencialTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/cargarDatosSolicitudPresencial", CargarDatosSolicitudPresencialSpring.class);
		
		//Se cargan los managers
		cargarDatosSolicitudPresencialSpring = new CargarDatosSolicitudPresencialSpring();	
		cargarDatosSolicitudPresencialSpring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		cargarDatosSolicitudPresencialSpring.setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		cargarDatosSolicitudPresencialSpring.setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
		cargarDatosSolicitudPresencialSpring.setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		cargarDatosSolicitudPresencialSpring.setPaisManager((PaisManager) getBean("paisManager"));
		cargarDatosSolicitudPresencialSpring.setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		cargarDatosSolicitudPresencialSpring.setTipoDiscapacidadManager((TipoDiscapacidadManager) getBean("tipoDiscapacidadManager"));
		cargarDatosSolicitudPresencialSpring.setTituloOficialManager( (TituloOficialManager) getBean("tituloOficialManager"));
		cargarDatosSolicitudPresencialSpring.setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
		cargarDatosSolicitudPresencialSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		cargarDatosSolicitudPresencialSpring.setComunidadesManager((ComunidadesManager) getBean("comunidadesManager"));
		cargarDatosSolicitudPresencialSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
		cargarDatosSolicitudPresencialSpring.setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
		
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        
        //Carga del usuario
        GrantedAuthority[] authorities = new GrantedAuthority[1];
        authorities[0] = new GrantedAuthorityImpl("ROLE_ADMIN");
        User user = new User("admin", "4bf2b1832c785d31ffbcb5f8c10f5772", true, true, true, true, authorities);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, "4bf2b1832c785d31ffbcb5f8c10f5772", authorities));
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
			System.out.println("Iniciando test cargarDatosSolicitudPresencial");
	        
			AltaSolicitudPresencialForm formulario = new AltaSolicitudPresencialForm();
			//Introducir los parametros de entrada para el test
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idConvocatoria = "7400";
			request.setParameter("idConv", idConvocatoria);			
	        this.request = request; 
	        
	        ConvocatoriasBean convocatoriaBean;
	    	ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			convocatoriaQuery.setId(new Long(idConvocatoria));
			convocatoriaBean = cargarDatosSolicitudPresencialSpring.getConvocatoriaManager().buscarConvocatoriaById(convocatoriaQuery);	
			if(convocatoriaBean == null) {
				logger.error("No existe el id de la convocatoria");
				return;
			}
			
			SpringForward rtrn = cargarDatosSolicitudPresencialSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test cargarDatosSolicitudPresencial");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test cargarDatosSolicitudPresencial con errores");
			fail();
		}
		
		
	}
	
	
}
