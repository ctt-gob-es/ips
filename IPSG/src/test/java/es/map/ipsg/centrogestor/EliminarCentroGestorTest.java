package es.map.ipsg.centrogestor;

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
import es.map.ips.model.AlertaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.centrogestor.EliminarCentroGestorSpring;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class EliminarCentroGestorTest.
 */
public class EliminarCentroGestorTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar centro gestor spring. */
	private EliminarCentroGestorSpring eliminarCentroGestorSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarCentroGestorTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarCentroGestor", EliminarCentroGestorSpring.class);
		
		//Se cargan los managers
		eliminarCentroGestorSpring = new EliminarCentroGestorSpring();
		eliminarCentroGestorSpring.setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		eliminarCentroGestorSpring.setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
		eliminarCentroGestorSpring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		eliminarCentroGestorSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		eliminarCentroGestorSpring.setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));
		eliminarCentroGestorSpring.setAlertaManager((AlertaManager)getBean ("alertaManager"));
		
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
			
	        
			System.out.println("Iniciando test eliminarCentroGestor");
			
			//Introducir los parametros de entrada para el test
			CentroGestorForm formulario = new CentroGestorForm();
		
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idCentroGestor = "554";
			request.setParameter("id", idCentroGestor);
	        this.request = request; 
			
	        CentroGestorBean centroGestorBean = null;
	        try {	        	
				centroGestorBean = eliminarCentroGestorSpring.getCentroGestorManager().obtenerCentroGestor(Integer.valueOf(idCentroGestor));
	        }
	        catch(NullPointerException ex) {
	        	logger.error("No existe el centro gestor a eliminar");
	        	return;	        	
	        }	        
	        
	        	        
	        UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.addCentroGestorIn(centroGestorBean.getId());
			usuarioQuery.setEstado('1');
			List<UsuarioBean> usuario = null;
			usuario = eliminarCentroGestorSpring.getUsuarioManager().buscarUsuarios(usuarioQuery);
			if (usuario.size() > 0 ) fail("Existen campos asociados al centro gestor");
			
			CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			cuerpoEscalaQuery.addCentroGestorIn(centroGestorBean.getId());
			cuerpoEscalaQuery.setEstado('A');
			List<CuerpoEscalaBean> lCuerpoEscala = null;
			lCuerpoEscala = eliminarCentroGestorSpring.getCuerpoEscalaManager().buscarCuerpoEscalaAll(cuerpoEscalaQuery);
			if (lCuerpoEscala.size() > 0 ) fail("Existen campos asociados al centro gestor");
			
			AlertaQuery alertaQuery = new AlertaQuery();
			alertaQuery.addCentroGestorIn(centroGestorBean.getId());
			alertaQuery.setEstado('A');			
			List<AlertaBean> lAlerta = null;
			lAlerta = eliminarCentroGestorSpring.getAlertaManager().buscarAlertaAll(alertaQuery);
			if (lAlerta.size() > 0 ) fail("Existen campos asociados al centro gestor");
			
			
			
			SpringForward rtrn = eliminarCentroGestorSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test eliminarCentroGestor");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarCentroGestor con errores");
			fail();
		} 
		
		
	}
	
	
}
