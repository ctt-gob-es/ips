package es.map.ipsg.convocatoria;

import static org.mockito.Mockito.mock;

import java.util.List;

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
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ipsg.action.convocatoria.ModificarConvocatoriaSpring;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.FestivoManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.LogConvocatoriaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ModificarConvocatoriaTest.
 */
public class ModificarConvocatoriaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el modificar convocatoria spring. */
	private ModificarConvocatoriaSpring modificarConvocatoriaSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarConvocatoriaTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/modificarConvocatoria", ModificarConvocatoriaSpring.class);
		
		//Se cargan los managers
		modificarConvocatoriaSpring = new ModificarConvocatoriaSpring();
		modificarConvocatoriaSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		modificarConvocatoriaSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		modificarConvocatoriaSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		modificarConvocatoriaSpring.setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		modificarConvocatoriaSpring.setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
		modificarConvocatoriaSpring.setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		modificarConvocatoriaSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager) getBean("motivoReduccionTasaManager"));
		modificarConvocatoriaSpring.setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		modificarConvocatoriaSpring.setFestivoManager((FestivoManager) getBean("festivoManager"));
		modificarConvocatoriaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		modificarConvocatoriaSpring.setLogConvocatoriaManager((LogConvocatoriaManager) getBean("logConvocatoriaManager"));
		modificarConvocatoriaSpring.setMinisteriosManager((MinisterioManager) getBean("ministeriosManager"));
		
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
			
	        
			System.out.println("Iniciando test modificarConvocatoria");
			
			//Introducir los parametros de entrada para el test
			CrearConvocatoriaForm formulario = new CrearConvocatoriaForm();
			String idConvocatoria = "3605";
			String idCentroGestor = "1";
			String idCuerpoEscala = "8";
			
			formulario.setAccion("alta");
			formulario.setIdConvocatoria(idConvocatoria);
			formulario.setCentroGestor(idCentroGestor);
			formulario.setCuerpoEscala(idCuerpoEscala);
			formulario.setMinisterioConvocante("14");
			formulario.setNumPlazas("20");
			formulario.setNumPlazasDisc("5");
			formulario.setFormaAcceso("1");
			formulario.setCodigoFormaAcceso("1");
			formulario.setImporte("2.64");
			formulario.setDirigidoA("Prueba");
			formulario.setTipoDocumentacion("C");
			String titulos = "2393";
			formulario.setTitulosSeleccionadosInput(titulos);
			formulario.setFechaBoe("05/03/2018");
			formulario.setFechaInicio("14/06/2018");
			formulario.setFechaTermino("05/09/2018");
			String[] provinciasExamen = {"15"};
			formulario.setProvinciaExamenSeleccionados(provinciasExamen);
			String[] motivosExencion = {"2"};
			formulario.setMotivosExencionSeleccionados(motivosExencion);
			formulario.setSiglasMinisterioBoe("MPR");
			formulario.setAnioBoe("2018");
			formulario.setEjercicio("2014");
			
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			convocatoriaQuery.setId(Long.valueOf(idConvocatoria));
			ConvocatoriasBean convocatoriasBean = modificarConvocatoriaSpring.getConvocatoriasManager().buscarConvocatoriaById(convocatoriaQuery);
			if(convocatoriasBean == null) fail("No existe la convocatoria a modificar");
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.setId(Integer.valueOf(idCentroGestor));
			CentroGestorBean centroGestorBean = modificarConvocatoriaSpring.getCentroGestorManager().buscarCentroGestorUnico(centroGestorQuery);
			if(centroGestorBean.getId() == null) fail("No existe el centro gestor");
						
			CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			cuerpoEscalaQuery.setId(Integer.valueOf(idCuerpoEscala));
			List<CuerpoEscalaBean> cuerpoEscalaBean = modificarConvocatoriaSpring.getCuerpoEscalaManager().buscarCuerpoEscalaAll(cuerpoEscalaQuery);
			for(CuerpoEscalaBean cuerpoEscala: cuerpoEscalaBean) {
				if(cuerpoEscala.getId() == Integer.valueOf(idCuerpoEscala)) {
					logger.error("No existe el cuerpoEscala");
					return;
				}
			}
			
			
			SpringForward rtrn = modificarConvocatoriaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test crearConvocatoria");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test modificarConvocatoria con errores");
			fail();
		} 
		
		
	}
	
	
}
