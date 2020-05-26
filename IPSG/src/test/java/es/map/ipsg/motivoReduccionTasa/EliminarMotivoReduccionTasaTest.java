package es.map.ipsg.motivoReduccionTasa;

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

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.motivoReduccionTasa.EliminarMotivoReduccionTasaSpring;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class EliminarMotivoReduccionTasaTest.
 */
public class EliminarMotivoReduccionTasaTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el eliminar motivo reduccion tasa spring. */
	private EliminarMotivoReduccionTasaSpring eliminarMotivoReduccionTasaSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarMotivoReduccionTasaTest.class);
	
	/** La constante ESTADO_CONFIGURACION. */
	private static final int ESTADO_CONFIGURACION=4;
	
	/** La constante ESTADO_PUBLICADO. */
	private static final int ESTADO_PUBLICADO=5;
	
	/** La constante ESTADO_APROBADO. */
	private static final int ESTADO_APROBADO=7;
	
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
		mapping = springMappingManager.getMapping("/spring/eliminarMotivoReduccionTasa", EliminarMotivoReduccionTasaSpring.class);
		
		//Se cargan los managers
		eliminarMotivoReduccionTasaSpring = new EliminarMotivoReduccionTasaSpring();
		eliminarMotivoReduccionTasaSpring.setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
		eliminarMotivoReduccionTasaSpring.setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		eliminarMotivoReduccionTasaSpring.setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		
		
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
			
	        
			System.out.println("Iniciando test eliminarMotivoReduccionTasa");
			SpringForm formulario = new SpringForm();
			//Introducir los parametros de entrada para el test
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idMotivo = "246";
			request.setParameter("id", idMotivo);
	        this.request = request;   
	        MotivoReduccionTasaBean motivoBean;
	        try {
	        	motivoBean=	eliminarMotivoReduccionTasaSpring.getMotivoManager().obtenerMotivoReduccionTasa(Integer.parseInt(idMotivo));
	        }catch(NullPointerException ex) {
	        	logger.error("No existe el motivo de reduccion");
	        	return;
	        }        
			
			SpringForward rtrn = eliminarMotivoReduccionTasaSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test eliminarMotivoReduccionTasa");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test eliminarMotivoReduccionTasa con errores");
			fail();
		}
		
		
	}
	
	
}
