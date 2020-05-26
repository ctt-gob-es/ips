package es.map.ipsg.action.incidencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.FormSessionMapper;
import es.map.ipsg.form.BuscarIncidenciasForm;
import es.map.ipsg.form.IncidenciasForm;

/**
 * El Class IncidenciaController.
 */
@Controller
@RequestMapping("/spring")
public class IncidenciaController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(IncidenciaController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar incidencias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarIncidenciasForm el buscar incidencias form
	 * @return el string
	 */
	@RequestMapping("/buscarIncidencias")
	public String buscarIncidencias(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarIncidenciasForm buscarIncidenciasForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarIncidenciasForm = formSessionMapper.resolveForm(buscarIncidenciasForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarIncidenciasSpring.class);
		try {
			BuscarIncidenciasSpring buscarIncidenciasSpring = new BuscarIncidenciasSpring();
			SpringForward rtrn = buscarIncidenciasSpring.execute(mapping, buscarIncidenciasForm, request, response);
			model.addAttribute("buscarIncidenciasForm", buscarIncidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error IncidenciaController - buscarIncidencias:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Detalle incidencias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param incidenciasForm el incidencias form
	 * @return el string
	 */
	@RequestMapping("/detalleIncidencias")
	public String detalleIncidencias(Model model, HttpServletRequest request, HttpServletResponse response,
			IncidenciasForm incidenciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DetalleIncidenciasSpring.class);
		try {
			DetalleIncidenciasSpring detalleIncidenciasSpring = new DetalleIncidenciasSpring();
			SpringForward rtrn = detalleIncidenciasSpring.execute(mapping, incidenciasForm, request, response);
			model.addAttribute("incidenciasForm", incidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error IncidenciaController - detalleIncidencias:",e);
			throw new RuntimeException(e);
		}
	}

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
			logger.error("Error IncidenciaController - reportarIncidencias:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar incidencias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarIncidenciasForm el buscar incidencias form
	 * @return el string
	 */
	@RequestMapping("/verBuscarIncidencias")
	public String verBuscarIncidencias(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarIncidenciasForm buscarIncidenciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarIncidenciasSpring.class);
		try {
			VerBuscarIncidenciasSpring verBuscarIncidenciasSpring = new VerBuscarIncidenciasSpring();
			SpringForward rtrn = verBuscarIncidenciasSpring.execute(mapping, buscarIncidenciasForm, request, response);
			model.addAttribute("buscarIncidenciasForm", buscarIncidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error IncidenciaController - verBuscarIncidencias:",e);
			throw new RuntimeException(e);
		}
	}
}
