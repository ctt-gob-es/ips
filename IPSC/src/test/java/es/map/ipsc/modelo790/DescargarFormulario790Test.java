package es.map.ipsc.modelo790;

import static org.mockito.Mockito.mock;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.form.Formulario790Form;
import es.map.ipsc.junit.AbstractSpringCommonTestCaseIPSC;
import es.map.ipsc.manager.camposPropios.CamposPropiosManager;
import es.map.ipsc.manager.comunidades.ComunidadesManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.especialidades.EspecialidadManager;
import es.map.ipsc.manager.formulario790.Formulario790Manager;
import es.map.ipsc.manager.ministerio.MinisterioManager;
import es.map.ipsc.manager.modelo.ModeloManager;
import es.map.ipsc.manager.pais.PaisManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.plantilla.PlantillaPropiosManager;
import es.map.ipsc.manager.provincia.ProvinciaExamenManager;
import es.map.ipsc.manager.provincia.ProvinciaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.manager.tituloOficial.TituloOficialManager;
import es.map.ipsc.spring.modelo790.DescargarFormulario790Spring;
import org.apache.log4j.Logger;


/**
 * El Class DescargarFormulario790Test.
 */
public class DescargarFormulario790Test extends AbstractSpringCommonTestCaseIPSC {

	/** el mapping. */
	private SpringMapping mapping;
	
	/** el request. */
	private HttpServletRequest request;
	
	/** el response. */
	private HttpServletResponse response;
	
	/** el descargar formulario 790 spring. */
	private DescargarFormulario790Spring descargarFormulario790Spring;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarFormulario790Test.class);
	
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
		mapping = springMappingManager.getMapping("/modelo790enBlanco", DescargarFormulario790Spring.class);
		
		//Se cargan los managers
		descargarFormulario790Spring = new DescargarFormulario790Spring();
		descargarFormulario790Spring.setTablaBaseManager((TablaBaseManager) getBean ("tablasBaseManager"));
		descargarFormulario790Spring.setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		descargarFormulario790Spring.setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));
		descargarFormulario790Spring.setFormulario790Manager((Formulario790Manager) getBean("formulario790Manager"));
		descargarFormulario790Spring.setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
		descargarFormulario790Spring.setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
		descargarFormulario790Spring.setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerpoEscalaManager"));
		descargarFormulario790Spring.setEspecialidadManager((EspecialidadManager) getBean("especialidadesManager"));
		descargarFormulario790Spring.setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
		descargarFormulario790Spring.setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
		descargarFormulario790Spring.setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		descargarFormulario790Spring.setMinisterioManager((MinisterioManager) getBean("ministerioManager"));
		descargarFormulario790Spring.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
		descargarFormulario790Spring.setPaisManager((PaisManager) getBean("paisManager"));
		descargarFormulario790Spring.setCamposPropiosManager((CamposPropiosManager) getBean("camposPropiosManager"));
		descargarFormulario790Spring.setModeloManager((ModeloManager) getBean("modeloManager"));
		descargarFormulario790Spring.setComunidadesManager((ComunidadesManager) getBean("comunidadesManager"));
		descargarFormulario790Spring.setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));		
		//Se carga simulacion request response y session
		MockHttpServletRequest request = new MockHttpServletRequest();
        this.request = request;  
        this.response = mock(HttpServletResponse.class);   
        
	}

	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
	        
			System.out.println("Iniciando test descargarFormulario790");
			
			//Introducir los parametros de entrada para el testcomprobarSesionClave
			
			String idConvocatoria = "9059";
			Formulario790Form formulario = new Formulario790Form();
			formulario.setId(Long.valueOf(idConvocatoria));
			MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
			request.setParameter("id", idConvocatoria);

	        this.request = request;
	        
	       
	        ConvocatoriaBean convocatoriaBean = descargarFormulario790Spring.getConvocatoriasManager().recuperarConvocatoria(formulario.getId());
			if(convocatoriaBean != null)
			{							
				Date today = new Date();	
				if (convocatoriaBean.getIdEstado() != 5) {
					logger.error("La convocatoria no tiene el estado publicada");
					return;
				}
				if (convocatoriaBean.getFechaFin() != null && today.after(convocatoriaBean.getFechaFin()) && !DateUtils.isSameDay(today, convocatoriaBean.getFechaFin()) ) {
					logger.error("La convocatoria esta cerrada");
					return;
				}
			}else {
				logger.error("La convocatoria no existe");
				return;
			}
			
			
			SpringForward rtrn = descargarFormulario790Spring.execute(mapping, formulario, request, response);
			if (rtrn != null && !StringUtils.isEmpty(rtrn.getPath())) {
				System.out.println("RESULTADO: "+rtrn.getPath());
				if (rtrn.getPath().contains("error")) throw new Exception("Error");
			}
			else throw new Exception("Error");
			System.out.println("Finalizando test descargarFormulario790");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Finalizando test descargarFormulario790 con errores");
			fail();
		} 
		
		
	}
	
	
}
