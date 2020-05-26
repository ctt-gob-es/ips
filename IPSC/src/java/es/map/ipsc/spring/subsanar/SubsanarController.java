package es.map.ipsc.spring.subsanar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.form.BuscaConvocatoriasForm;

/**
 * El Class SubsanarController.
 */
@Controller
@RequestMapping("/secure")
public class SubsanarController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger( SubsanarController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;

//	@RequestMapping("/borrarDocumento")
//	public String borrarDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
//			CrearDocumentoForm crearDocumentoForm) {
//		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BorrarDocumentoSpring.class);
//		try {
//			BorrarDocumentoSpring borrarDocumentoSpring = new BorrarDocumentoSpring();
//			SpringForward rtrn = borrarDocumentoSpring.execute(mapping, crearDocumentoForm, request, response);
//			model.addAttribute("crearDocumentoForm", crearDocumentoForm);
//			return rtrn.getPath();
//		} catch (Exception e) {
//			logger.error("Error ConvocatoriasController - borrarDocumento ",e);
//			throw new RuntimeException(e);
//		}
//	}

	/**
 * Buscar convocatorias subsanar.
 *
 * @param model el model
 * @param request el request
 * @param response el response
 * @param buscaConvocatoriasForm el busca convocatorias form
 * @return el string
 */
@RequestMapping("/buscarConvocatoriasSubsanar")
	public String buscarConvocatoriasSubsanar(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm buscaConvocatoriasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarConvocatoriasSubSpring.class);
		try {
			BuscarConvocatoriasSubSpring buscarConvocatoriasSpring = new BuscarConvocatoriasSubSpring();
			SpringForward rtrn = buscarConvocatoriasSpring.execute(mapping, buscaConvocatoriasForm, request, response);
			model.addAttribute("buscaConvocatoriasForm", buscaConvocatoriasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConvocatoriasController - buscarConvocatorias ",e);
			throw new RuntimeException(e);
		}
	}

//	@RequestMapping("/descargarDocumento")
//	public String descargarDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
//			SpringForm springForm) {
//		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
//				DescargarDocumentoSpring.class);
//		try {
//			DescargarDocumentoSpring descargarDocumentoSpring = new DescargarDocumentoSpring();
//			SpringForward rtrn = descargarDocumentoSpring.execute(mapping, springForm, request, response);
//			model.addAttribute("springForm", springForm);
//			return rtrn.getPath();
//		} catch (Exception e) {
//			logger.error("Error ConvocatoriasController - descargarDocumento ",e);
//			throw new RuntimeException(e);
//		}
//	}

//	@RequestMapping("/descargarPdf")
//	public String descargarPdf(Model model, HttpServletRequest request, HttpServletResponse response,
//			SpringForm springForm) {
//		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), DescargarPdfSpring.class);
//		try {
//			DescargarPdfSpring descargarPdfSpring = new DescargarPdfSpring();
//			SpringForward rtrn = descargarPdfSpring.execute(mapping, springForm, request, response);
//			model.addAttribute("springForm", springForm);
//			return rtrn.getPath();
//		} catch (Exception e) {
//			logger.error("Error ConvocatoriasController - descargarPdf ",e);
//			throw new RuntimeException(e);
//		}
//	}



//	@RequestMapping("/subirDocumento")
//	public String subirDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
//			CrearDocumentoForm crearDocumentoForm) {
//		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), SubirDocumentoSpring.class);
//		try {
//			SubirDocumentoSpring subirDocumentoSpring = new SubirDocumentoSpring();
//			SpringForward rtrn = subirDocumentoSpring.execute(mapping, crearDocumentoForm, request, response);
//			model.addAttribute("crearDocumentoForm", crearDocumentoForm);
//			return rtrn.getPath();
//		} catch (Exception e) {
//			logger.error("Error ConvocatoriasController - subirDocumento ",e);
//			throw new RuntimeException(e);
//		}
//	}
}
