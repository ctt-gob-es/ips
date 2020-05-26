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
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.TarifaQuery;
import es.map.ipsg.action.convocatoria.ObtenerTarifaConvocatoriaSpring;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.TituloOficialManager;


/**
 * El Class ObtenerTarifaConvocatoriaTest.
 */
public class ObtenerTarifaConvocatoriaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el obtener tarifa convocatoria spring. */
	private ObtenerTarifaConvocatoriaSpring obtenerTarifaConvocatoriaSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ObtenerTarifaConvocatoriaTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/obtenerTarifaConvocatoriaSpring", ObtenerTarifaConvocatoriaSpring.class);
		
		//Se cargan los managers
		obtenerTarifaConvocatoriaSpring = new ObtenerTarifaConvocatoriaSpring();
		obtenerTarifaConvocatoriaSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		obtenerTarifaConvocatoriaSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		obtenerTarifaConvocatoriaSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		obtenerTarifaConvocatoriaSpring.setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		obtenerTarifaConvocatoriaSpring.setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
				
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
			
	        
			System.out.println("Iniciando test obtenerTarifaConvocatoria");
			
			//Introducir los parametros de entrada para el test
			CrearConvocatoriaForm formulario = new CrearConvocatoriaForm();
			String idCentroGestor = "415";
			String idCuerpoEscala = "689";
			String ejercicio = "2019";
			formulario.setCentroGestor(idCentroGestor);
			formulario.setEjercicio(ejercicio);
			formulario.setCuerpoEscala(idCuerpoEscala);
			formulario.setFormaAcceso("1");
					
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.setId(Integer.valueOf(idCentroGestor));
			CentroGestorBean centroGestorBean = obtenerTarifaConvocatoriaSpring.getCentroGestorManager().buscarCentroGestorUnico(centroGestorQuery);
			if(centroGestorBean.getId() == null) fail("No existe el centro gestor");
			
			Boolean existeCuerpoEscala=false;
			CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			cuerpoEscalaQuery.setId(Integer.valueOf(idCuerpoEscala));
			List<CuerpoEscalaBean> cuerpoEscalaBean = obtenerTarifaConvocatoriaSpring.getCuerpoEscalaManager().buscarCuerpoEscalaAll(cuerpoEscalaQuery);
			for(CuerpoEscalaBean cuerpoEscala: cuerpoEscalaBean) {
				if(cuerpoEscala.getId() == Integer.parseInt(idCuerpoEscala)) existeCuerpoEscala=true;
			}
			assertEquals("No existe el cuerpoEscala",(Boolean)true,existeCuerpoEscala);
			TarifaQuery tarifaQuery = new TarifaQuery();
			tarifaQuery.setEjercicio(ejercicio);
			try {
				obtenerTarifaConvocatoriaSpring.getConvocatoriasManager().buscarImporte(Integer.valueOf(idCuerpoEscala),1, tarifaQuery);
			}
			catch(NullPointerException ex) {
				logger.error("No existe importe para esa convocatoria o centro gestor");
				return;
			}
			
			
			SpringForward rtrn = obtenerTarifaConvocatoriaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test obtenerTarifaConvocatoria");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test obtenerTarifaConvocatoria con errores");
			fail();
		} 
		
		
	}
	
	
}
