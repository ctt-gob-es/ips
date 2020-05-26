package es.map.ipsg.grupos;

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
import es.map.ips.model.GrupoQuery;
import es.map.ipsg.action.grupos.EliminarGrupoSpring;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.form.GrupoForm;
import es.map.ipsg.formaAcceso.EliminarFormasAccesoTest;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class EliminarGrupoTest.
 */
public class EliminarGrupoTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar grupo spring. */
	private EliminarGrupoSpring eliminarGrupoSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarFormasAccesoTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarGrupo", EliminarGrupoSpring.class);
		
		//Se cargan los managers
		eliminarGrupoSpring = new EliminarGrupoSpring();
		eliminarGrupoSpring.setCuerposEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		eliminarGrupoSpring.setTarifaManager((TarifaManager) getBean("tarifaManager"));
		eliminarGrupoSpring.setGrupoManager((GrupoManager) getBean("grupoManager"));
		eliminarGrupoSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarGrupoSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		
	
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
			
	        
			System.out.println("Iniciando test eliminarGrupo");
				
			GrupoForm formulario = new GrupoForm();
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idGrupo = "186";
			request.setParameter("grupo", idGrupo);
	        this.request = request;   
		
	       
	        GrupoQuery grupoQuery = new GrupoQuery();
	        grupoQuery.setId(Integer.parseInt(idGrupo));
	        GrupoBean grupoBean;	      
			grupoBean = eliminarGrupoSpring.getGrupoManager().buscarGrupoUnico(grupoQuery);
			if(grupoBean.getId() == null) {
				logger.error("No existe el grupo a eliminar");
				return;
			}
			
		
			SpringForward rtrn = eliminarGrupoSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test eliminarGrupo");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarGrupo con errores");
			fail();
		}
		
		
	}
	
	
}
