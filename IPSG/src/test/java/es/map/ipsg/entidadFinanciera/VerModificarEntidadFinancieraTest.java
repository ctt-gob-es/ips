package es.map.ipsg.entidadFinanciera;

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
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ipsg.action.entidadFinanciera.VerModificarEntidadFinancieraSpring;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.form.ModificarEntidadFinancieraForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.TipoPagoManager;


/**
 * El Class VerModificarEntidadFinancieraTest.
 */
public class VerModificarEntidadFinancieraTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el ver modificar entidad financiera spring. */
	private VerModificarEntidadFinancieraSpring verModificarEntidadFinancieraSpring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarEntidadFinancieraTest.class);
	
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
		mapping = springMappingManager.getMapping("/spring/verModificarEntidadFinanciera", VerModificarEntidadFinancieraSpring.class);
		
		//Se cargan los managers
		verModificarEntidadFinancieraSpring = new VerModificarEntidadFinancieraSpring();
		verModificarEntidadFinancieraSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		verModificarEntidadFinancieraSpring.setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));


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
			
	        
			System.out.println("Iniciando test verModificarEntidadFinanciera");
				
			ModificarEntidadFinancieraForm formulario = new ModificarEntidadFinancieraForm();
			
			//Introducir los parametros de entrada para el test
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			String idEntidadFinanciera = "648";
			request.setParameter("id", idEntidadFinanciera);
	        this.request = request;  
	        List<EntidadFinancieraBean> lEntidadFinancieraBean = null;
    		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
    		entidadFinancieraQuery.setId(Integer.valueOf(idEntidadFinanciera));
	        lEntidadFinancieraBean = verModificarEntidadFinancieraSpring.getEntidadFinancieraManager().buscarEntidadFinancieraCombo(entidadFinancieraQuery);
    		if(lEntidadFinancieraBean.isEmpty()) {
    			logger.error("No existe la entidad financiera");
    			return;
    		}
	        
			SpringForward rtrn = verModificarEntidadFinancieraSpring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test verModificarEntidadFinanciera");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test verModificarEntidadFinanciera con errores");
			fail();
		} 
		
		
	}
	
	
}
