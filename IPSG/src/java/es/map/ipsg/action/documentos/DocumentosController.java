package es.map.ipsg.action.documentos;

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
import es.map.ipsg.form.CrearCSVDocumentoForm;

/**
 * El Class DocumentosController.
 */
@Controller
@RequestMapping("/spring")
public class DocumentosController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;

	/**
	 * Subir CSV documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearCSVDocumentoForm el crear CSV documento form
	 * @return el string
	 */
	@RequestMapping("/subirCSVDocumento")
	public String subirCSVDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearCSVDocumentoForm crearCSVDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				SubirCSVDocumentoSpring.class);
		try {
			SubirCSVDocumentoSpring subirCSVDocumentoSpring = new SubirCSVDocumentoSpring();
			SpringForward rtrn = subirCSVDocumentoSpring.execute(mapping, crearCSVDocumentoForm, request, response);
			model.addAttribute("crearCSVDocumentoForm", crearCSVDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error DocumentosController - subirCSVDocumento  :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver subir documentos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearCSVDocumentoForm el crear CSV documento form
	 * @return el string
	 */
	@RequestMapping("/verSubirDocumentos")
	public String verSubirDocumentos(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearCSVDocumentoForm crearCSVDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerSubirDocumentosSpring.class);
		try {
			VerSubirDocumentosSpring verSubirDocumentosSpring = new VerSubirDocumentosSpring();
			SpringForward rtrn = verSubirDocumentosSpring.execute(mapping, crearCSVDocumentoForm, request, response);
			model.addAttribute("crearCSVDocumentoForm", crearCSVDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error DocumentosController - verSubirDocumentos  :",e);
			throw new RuntimeException(e);
		}
	}
}
