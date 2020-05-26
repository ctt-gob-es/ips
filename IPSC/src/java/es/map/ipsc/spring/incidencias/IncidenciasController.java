package es.map.ipsc.spring.incidencias;

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
import es.map.ipsc.form.IncidenciasForm;
import es.map.ipsc.spring.convocatorias.SubirDocumentoSpring;

/**
 * El Class IncidenciasController.
 */
@Controller
@RequestMapping("/secure")
public class IncidenciasController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(IncidenciasController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;

	/**
	 * Reportar incidencias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param incidenciasForm el incidencias form
	 * @return el string
	 */
	@RequestMapping("/reportarIncidencias")
	public String reportarIncidencias(Model model, HttpServletRequest request, HttpServletResponse response,
			IncidenciasForm incidenciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ReportarIncidenciasSpring.class);
		try {
			ReportarIncidenciasSpring reportarIncidenciasSpring = new ReportarIncidenciasSpring();
			SpringForward rtrn = reportarIncidenciasSpring.execute(mapping, incidenciasForm, request, response);
			model.addAttribute("incidenciasForm", incidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error IncidenciasController - reportarIncidencias  ",e);
			throw new RuntimeException(e);
		}
	}
}
