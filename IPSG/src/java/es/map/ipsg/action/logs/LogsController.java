package es.map.ipsg.action.logs;

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
import es.map.ipsg.form.LogErroresForm;
import es.map.ipsg.form.LogOperacionesForm;
import es.map.ipsg.form.LogSolicitudesForm;

/**
 * El Class LogsController.
 */
@Controller
@RequestMapping("/spring")
public class LogsController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogsController.class);
	
	/** La constante STRING_LOGSOLICITUDESFORM. */
	private static final String STRING_LOGSOLICITUDESFORM = "logSolicitudesForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar log errores.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param logErroresForm el log errores form
	 * @return el string
	 */
	@RequestMapping("/buscarLogErrores")
	public String buscarLogErrores(Model model, HttpServletRequest request, HttpServletResponse response,
			LogErroresForm logErroresForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		logErroresForm = formSessionMapper.resolveForm(logErroresForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BuscarLogErroresSpring.class);
		try {
			BuscarLogErroresSpring buscarLogErroresSpring = new BuscarLogErroresSpring();
			SpringForward rtrn = buscarLogErroresSpring.execute(mapping, logErroresForm, request, response);
			model.addAttribute("logErroresForm", logErroresForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error LogsController - buscarLogErrores:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar log operaciones.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param logOperacionesForm el log operaciones form
	 * @return el string
	 */
	@RequestMapping("/buscarLogOperaciones")
	public String buscarLogOperaciones(Model model, HttpServletRequest request, HttpServletResponse response,
			LogOperacionesForm logOperacionesForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		logOperacionesForm = formSessionMapper.resolveForm(logOperacionesForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarLogOperacionesSpring.class);
		try {
			BuscarLogOperacionesSpring buscarLogOperacionesSpring = new BuscarLogOperacionesSpring();
			SpringForward rtrn = buscarLogOperacionesSpring.execute(mapping, logOperacionesForm, request, response);
			model.addAttribute("logOperacionesForm", logOperacionesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error LogsController - buscarLogOperaciones:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar log solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param logSolicitudesForm el log solicitudes form
	 * @return el string
	 */
	@RequestMapping("/buscarLogSolicitudes")
	public String buscarLogSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			LogSolicitudesForm logSolicitudesForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		logSolicitudesForm = formSessionMapper.resolveForm(logSolicitudesForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarLogSolicitudesSpring.class);
		try {
			BuscarLogSolicitudesSpring buscarLogSolicitudesSpring = new BuscarLogSolicitudesSpring();
			SpringForward rtrn = buscarLogSolicitudesSpring.execute(mapping, logSolicitudesForm, request, response);
			model.addAttribute(STRING_LOGSOLICITUDESFORM, logSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error LogsController - buscarLogSolicitudes:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Detalle log solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param logSolicitudesForm el log solicitudes form
	 * @return el string
	 */
	@RequestMapping("/detalleLogSolicitudes")
	public String detalleLogSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			LogSolicitudesForm logSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DetalleLogSolicitudesSpring.class);
		try {
			DetalleLogSolicitudesSpring detalleLogSolicitudesSpring = new DetalleLogSolicitudesSpring();
			SpringForward rtrn = detalleLogSolicitudesSpring.execute(mapping, logSolicitudesForm, request, response);
			model.addAttribute(STRING_LOGSOLICITUDESFORM, logSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error LogsController -detalleLogSolicitudes:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar log errores.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param logErroresForm el log errores form
	 * @return el string
	 */
	@RequestMapping("/VerbuscarLogErrores")
	public String verBuscarLogErrores(Model model, HttpServletRequest request, HttpServletResponse response,
			LogErroresForm logErroresForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarLogErroresSpring.class);
		try {
			VerBuscarLogErroresSpring verBuscarLogErroresSpring = new VerBuscarLogErroresSpring();
			SpringForward rtrn = verBuscarLogErroresSpring.execute(mapping, logErroresForm, request, response);
			model.addAttribute("logErroresForm", logErroresForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error LogsController - VerbuscarLogErrores:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar log solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param logSolicitudesForm el log solicitudes form
	 * @return el string
	 */
	@RequestMapping("/VerbuscarLogSolicitudes")
	public String verBuscarLogSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			LogSolicitudesForm logSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarLogSolicitudesSpring.class);
		try {
			VerBuscarLogSolicitudesSpring verBuscarLogSolicitudesSpring = new VerBuscarLogSolicitudesSpring();
			SpringForward rtrn = verBuscarLogSolicitudesSpring.execute(mapping, logSolicitudesForm, request, response);
			model.addAttribute(STRING_LOGSOLICITUDESFORM, logSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error LogsController - VerbuscarLogSolicitudes:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver log operaciones.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param logOperacionesForm el log operaciones form
	 * @return el string
	 */
	@RequestMapping("/verLogOperaciones")
	public String verLogOperaciones(Model model, HttpServletRequest request, HttpServletResponse response,
			LogOperacionesForm logOperacionesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerLogOperacionesSpring.class);
		try {
			VerLogOperacionesSpring verLogOperacionesSpring = new VerLogOperacionesSpring();
			SpringForward rtrn = verLogOperacionesSpring.execute(mapping, logOperacionesForm, request, response);
			model.addAttribute("logOperacionesForm", logOperacionesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error LogsController - verLogOperaciones:",e);
			throw new RuntimeException(e);
		}
	}
}
