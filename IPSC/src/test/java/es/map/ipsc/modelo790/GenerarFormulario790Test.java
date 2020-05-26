package es.map.ipsc.modelo790;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.form.Formulario790Form;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.descargaDocumento.DescargaDocumentoManager;
import es.map.ipsc.manager.formulario790.Formulario790Manager;
import es.map.ipsc.manager.solicitudes.SolicitudCcaaAuxiliarManager;
import es.map.ipsc.manager.solicitudes.SolicitudComunAuxiliarManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudPropioAuxiliarManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.spring.modelo790.GenerarFormulario790Spring;


/**
 * El Class GenerarFormulario790Test.
 */
public class GenerarFormulario790Test extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el generar formulario 790 spring. */
	private GenerarFormulario790Spring generarFormulario790Spring;
	
	/** el app. */
	private ApplicationContext app;
	
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
		mapping = springMappingManager.getMapping("/secure/generarFormulario790", GenerarFormulario790Spring.class);
		
		//Se cargan los managers
		generarFormulario790Spring = new GenerarFormulario790Spring();
		generarFormulario790Spring.setFormulario790Manager((Formulario790Manager) getBean("formulario790Manager"));
		generarFormulario790Spring.setDescargaDocumentoManager((DescargaDocumentoManager) getBean("descargaDocumentoManager"));
		generarFormulario790Spring.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
		generarFormulario790Spring.setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));
		generarFormulario790Spring.setSolicitudPropioAuxiliarManager((SolicitudPropioAuxiliarManager) getBean("solicitudPropioAuxiliarManager"));
		generarFormulario790Spring.setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
		generarFormulario790Spring.setSolicitudCcaaAuxiliarManager((SolicitudCcaaAuxiliarManager) getBean("solicitudCcaaAuxiliarManager"));
		
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;         
        this.response = mock(HttpServletResponse.class);   
        
        ServletOutputStream servletOutputStream;
        servletOutputStream = mock(ServletOutputStream.class);
       
        when(response.getOutputStream()).thenReturn(servletOutputStream);
       
        app=mock(ApplicationContext.class);
        Properties pro = new Properties();
        pro = (Properties) this.applicationContext.getBean("propertiesBean");
        pro.remove("jasper.rutaLogo");
        pro.put("jasper.rutaLogo", "WebContent/images/logo_izq_2.png");
        when(app.getBean("propertiesBean")).thenReturn(pro);
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
	        
			System.out.println("Iniciando test generarFormulario790");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			
			Formulario790Form formulario = new Formulario790Form();
			formulario.setPorcentajeMinDiscapacidad("30");
			formulario.setPorcentajeDiscapacidad(0L);
			formulario.setCodigoTasa("");
			formulario.setIdCuerpoEscala("189");
			formulario.setTituloOficial(0L);
			formulario.setIdFormaAcceso("1");
			formulario.setCodigoPostalDomicilio("30530");
			formulario.setProvinciaDomicilio(30L);
			formulario.setProvinciaExamen(15L);
			formulario.setCodigoPaisDomicilio("");
			formulario.setPais(1L);
			formulario.setNumModelo("790001");
			
			 String realPath = "file:C:\\Migracion\\apache-tomcat-7.0.78\\webapps\\IPSC";
			 MockServletContext context = new MockServletContext(realPath);
			 
			MockHttpServletRequest request = new MockHttpServletRequest(context,"POST", "");
			request.setContextPath(realPath);
			request.setParameter("id", "9059");

			CamposPropiosBean campo = new CamposPropiosBean();
			campo.setCampo("Datos A");
			campo.setCampoCa("Dades A");
			campo.setCampoEu("Datu A");
			campo.setCampoGl("Datos A");
			campo.setCampoVl("Dades A");
			campo.setDescripcion("DATOS A PARA SECRETARIOS JUDICIALES");
			campo.setIdModelo("2");
			campo.setTituloCampo("Datos A");
			campo.setId(48L);
			
			CamposPropiosBean campo1 = new CamposPropiosBean();
			campo1.setCampo("Datos B");
			campo1.setCampoCa("Dades B");
			campo1.setCampoEu("datu B");
			campo1.setCampoGl("Datos B");
			campo1.setCampoVl("Dades B");
			campo1.setDescripcion("DATOS B PARA SECRETARIOS JUDICIALES");
			campo1.setIdModelo("2");
			campo1.setTituloCampo("Datos B");
			campo1.setId(49L);
			
			ArrayList<CamposPropiosBean> listaCampos = new ArrayList();
			listaCampos.add(campo);
			
//			 request.setContextPath(realPath);
			 request.getSession().setAttribute("camposPropios",listaCampos);
	        this.request = request; 
	       

			SpringForward rtrn = generarFormulario790Spring.execute(mapping, formulario, request, response);
			if (rtrn.getPath() == null) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath() != null && rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test generarFormulario790");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test generarFormulario790 con errores");
			fail();
		} 
		
		
	}
	
	
}
