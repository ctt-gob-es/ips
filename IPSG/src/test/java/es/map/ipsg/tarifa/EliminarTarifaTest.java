package es.map.ipsg.tarifa;

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
import es.map.ipsg.action.tarifa.EliminarTarifaSpring;
import es.map.ipsg.bean.TarifaBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class EliminarTarifaTest.
 */
public class EliminarTarifaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar tarifa spring. */
	private EliminarTarifaSpring eliminarTarifaSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarTarifaTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarTarifa", EliminarTarifaSpring.class);
		
		//Se cargan los managers
		eliminarTarifaSpring = new EliminarTarifaSpring();	
		eliminarTarifaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarTarifaSpring.setTarifaManager((TarifaManager) getBean("tarifaManager"));
		eliminarTarifaSpring.setGrupoManager((GrupoManager) getBean("grupoManager"));
		eliminarTarifaSpring.setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
		eliminarTarifaSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
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
			
			System.out.println("Iniciando test eliminarTarifa");
	        
			CrearDocumentoForm formulario = new CrearDocumentoForm();
			//Introducir los parametros de entrada para el test
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");		
			String idTarifa = "364";
			request.setParameter("id", idTarifa);			
	        this.request = request; 

			try{
				TarifaBean tarifaBean = eliminarTarifaSpring.getTarifaManager().obtenerTarifa(Integer.valueOf(idTarifa));
			}catch(NullPointerException ex) {
				logger.error("No se encuentra el id de la tarifa a eliminar");
				return;
			}
			SpringForward rtrn = eliminarTarifaSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test eliminarTarifa");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarTarifa con errores");
			fail();
		}
		
		
	}
	
	
}
