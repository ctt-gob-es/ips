package es.map.ipsg.serviciosExternos;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
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
import es.map.ipsg.action.serviciosExternos.ProbarServiciosExternosSpring;
import es.map.ipsg.form.ProbarServiciosExternosForm;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.TipoDocumentoManager;


/**
 * El Class ProbarServiciosExternosTest.
 */
public class ProbarServiciosExternosTest extends AbstractSpringCommonTestCaseIPSG {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el probar servicios externos spring. */
	private ProbarServiciosExternosSpring probarServiciosExternosSpring;
	
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
		mapping = springMappingManager.getMapping("/spring/probarServiciosExternos", ProbarServiciosExternosSpring.class);
		
		//Se cargan los managers
		probarServiciosExternosSpring = new ProbarServiciosExternosSpring();
		probarServiciosExternosSpring.setLogServicioManager((LogServicioManager) getBean("logServicioManager"));
		probarServiciosExternosSpring.setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		probarServiciosExternosSpring.setTipoDocumentoManager((TipoDocumentoManager) getBean("tipoDocumentoManager"));
		probarServiciosExternosSpring.setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
		probarServiciosExternosSpring.setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		probarServiciosExternosSpring.setLogServicioManager((LogServicioManager) getBean("logServicioManager"));
				
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
			
			System.out.println("Iniciando test consultarResultadosServiciosExternos");
	        
			ProbarServiciosExternosForm formulario = new ProbarServiciosExternosForm();
			//Introducir los parametros de entrada para el test
			formulario.setAccion("Aceptar");
			formulario.setServicio("3"); //Probamos uno de los 4 servicios
			
			SpringForward rtrn = probarServiciosExternosSpring.execute(mapping, formulario, request, response);
			
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			
			System.out.println("Finalizando test consultarResultadosServiciosExternos");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test consultarResultadosServiciosExternos con errores");
			fail();
		}
		
		
	}
	
	
}
