package es.map.ipsc.spring.convocatorias;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsc.form.BuscaConvocatoriasForm;
import es.map.ipsc.form.CrearDocumentoForm;

/**
 * El Class ConvocatoriasController.
 */
@Controller
@RequestMapping("/secure")
public class ConvocatoriasController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger( ConvocatoriasController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;

	/**
	 * Borrar documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/borrarDocumento")
	public String borrarDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BorrarDocumentoSpring.class);
		try {
			BorrarDocumentoSpring borrarDocumentoSpring = new BorrarDocumentoSpring();
			SpringForward rtrn = borrarDocumentoSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute("crearDocumentoForm", crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConvocatoriasController - borrarDocumento ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar convocatorias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscaConvocatoriasForm el busca convocatorias form
	 * @return el string
	 */
	@RequestMapping("/buscarConvocatorias")
	public String buscarConvocatorias(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm buscaConvocatoriasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarConvocatoriasSpring.class);
		try {
			BuscarConvocatoriasSpring buscarConvocatoriasSpring = new BuscarConvocatoriasSpring();
			SpringForward rtrn = buscarConvocatoriasSpring.execute(mapping, buscaConvocatoriasForm, request, response);
			model.addAttribute("buscaConvocatoriasForm", buscaConvocatoriasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConvocatoriasController - buscarConvocatorias ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/descargarDocumento")
	public String descargarDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarDocumentoSpring.class);
		try {
			DescargarDocumentoSpring descargarDocumentoSpring = new DescargarDocumentoSpring();
			SpringForward rtrn = descargarDocumentoSpring.execute(mapping, springForm, request, response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConvocatoriasController - descargarDocumento ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar pdf.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/descargarPdf")
	public String descargarPdf(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), DescargarPdfSpring.class);
		try {
			DescargarPdfSpring descargarPdfSpring = new DescargarPdfSpring();
			SpringForward rtrn = descargarPdfSpring.execute(mapping, springForm, request, response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConvocatoriasController - descargarPdf ",e);
			throw new RuntimeException(e);
		}
	}



	/**
	 * Subir documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/subirDocumento")
	public String subirDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), SubirDocumentoSpring.class);
		try {
			SubirDocumentoSpring subirDocumentoSpring = new SubirDocumentoSpring();
			SpringForward rtrn = subirDocumentoSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute("crearDocumentoForm", crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConvocatoriasController - subirDocumento ",e);
			throw new RuntimeException(e);
		}
	}
}
