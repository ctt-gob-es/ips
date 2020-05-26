package es.map.ipsg.centrogestor;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

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
import es.map.ipsg.action.centrogestor.ReabrirEjercicioSpring;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class ReabrirEjercicioTest.
 */
public class ReabrirEjercicioTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el reabrir ejercicio spring. */
	private ReabrirEjercicioSpring reabrirEjercicioSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ReabrirEjercicioTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/reabrirEjercicio", ReabrirEjercicioSpring.class);
		
		//Se cargan los managers
		reabrirEjercicioSpring = new ReabrirEjercicioSpring();
		reabrirEjercicioSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		reabrirEjercicioSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		reabrirEjercicioSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		reabrirEjercicioSpring.setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));
		reabrirEjercicioSpring.setAlertaManager((AlertaManager)getBean ("alertaManager"));
		reabrirEjercicioSpring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));			

		
		
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
			
	        
			System.out.println("Iniciando test reabrirEjercicio");
			
			//Introducir los parametros de entrada para el test
			CentroGestorForm formulario = new CentroGestorForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idCentroGestor = "654";
			request.setParameter("id", idCentroGestor);
	        this.request = request; 
	        CentroGestorBean centroGestorBean = null;
	        try {
	        	centroGestorBean=reabrirEjercicioSpring.getCentroGestorManager().obtenerCentroGestor(Integer.valueOf(idCentroGestor));
	        }
	        catch(NullPointerException ex) {
	        	logger.error("No existe el centro gestor para reabrir");
	        	return;
	        }
	        
	        CentroGestorQuery centroQuery = new CentroGestorQuery();
			centroQuery.setId(Integer.valueOf(centroGestorBean.getId()));
			CuerpoEscalaQuery cuerpoQuery = new CuerpoEscalaQuery();
			cuerpoQuery.setCentroGestor(centroQuery);			
			ConvocatoriaQuery convocatoriaQuery= new ConvocatoriaQuery();
			convocatoriaQuery.setCuerpoEscala(cuerpoQuery);			
			convocatoriaQuery.setCuerpoEscala(cuerpoQuery);
			convocatoriaQuery.setEjercicio(centroGestorBean.getEjercicio());			
			convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_APROBADO);
			convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_CONFIGURACION);
			convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
			convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);
			convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_CERRADO);			
			ArrayList<ConvocatoriasBean> lConvocatoria = null;			
			lConvocatoria=reabrirEjercicioSpring.getConvocatoriasManager().buscarConvocatoriasAll(convocatoriaQuery);	
			if (lConvocatoria.size() > 0 ) fail("No se puede reabrir el centro gestor porque tiene campos asociados");
			
			SpringForward rtrn = reabrirEjercicioSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test reabrirEjercicio");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test reabrirEjercicio con errores");
			fail();
		} 
		
		
	}
	
	
}
