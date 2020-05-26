package es.map.ipsg.action.alerta;

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

import es.map.ipsg.form.AlertaForm;

/**
 * El Class AlertaController.
 */
@Controller
@RequestMapping("/spring")
public class AlertaController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AlertaController.class);
	
	/** La constante STRING_ALERTAFORM. */
	private static final String STRING_ALERTAFORM = "alertaForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Actualizar alerta.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param alertaForm el alerta form
	 * @return el string
	 */
	@RequestMapping("/ActualizarAlerta")
	public String actualizarAlerta(Model model, HttpServletRequest request, HttpServletResponse response,
			AlertaForm alertaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ActualizarAlertaSpring.class);
		try {
			ActualizarAlertaSpring actualizarAlertaSpring = new ActualizarAlertaSpring();
			SpringForward rtrn = actualizarAlertaSpring.execute(mapping, alertaForm, request, response);
			model.addAttribute(STRING_ALERTAFORM, alertaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error en actualizar alerta:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Alta alerta.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param alertaForm el alerta form
	 * @return el string
	 */
	@RequestMapping("/AltaAlerta")
	public String altaAlerta(Model model, HttpServletRequest request, HttpServletResponse response,
			AlertaForm alertaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), AltaAlertaSpring.class);
		try {
			AltaAlertaSpring altaAlertaSpring = new AltaAlertaSpring();
			SpringForward rtrn = altaAlertaSpring.execute(mapping, alertaForm, request, response);
			model.addAttribute(STRING_ALERTAFORM, alertaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error en alta Alerta:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar alerta.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param alertaForm el alerta form
	 * @return el string
	 */
	@RequestMapping("/buscarAlerta")
	public String buscarAlerta(Model model, HttpServletRequest request, HttpServletResponse response,
			AlertaForm alertaForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		alertaForm = formSessionMapper.resolveForm(alertaForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BuscarAlertaSpring.class);
		try {
			BuscarAlertaSpring buscarAlertaSpring = new BuscarAlertaSpring();
			SpringForward rtrn = buscarAlertaSpring.execute(mapping, alertaForm, request, response);
			model.addAttribute(STRING_ALERTAFORM, alertaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error en buscar alerta:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar alerta.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param alertaForm el alerta form
	 * @return el string
	 */
	@RequestMapping("/eliminarAlerta")
	public String eliminarAlerta(Model model, HttpServletRequest request, HttpServletResponse response,
			AlertaForm alertaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), EliminarAlertaSpring.class);
		try {
			EliminarAlertaSpring eliminarAlertaSpring = new EliminarAlertaSpring();
			SpringForward rtrn = eliminarAlertaSpring.execute(mapping, alertaForm, request, response);
			model.addAttribute(STRING_ALERTAFORM, alertaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error en eliminar alerta:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar alerta.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param alertaForm el alerta form
	 * @return el string
	 */
	@RequestMapping("/modificarAlerta")
	public String modificarAlerta(Model model, HttpServletRequest request, HttpServletResponse response,
			AlertaForm alertaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ModificarAlertaSpring.class);
		try {
			ModificarAlertaSpring modificarAlertaSpring = new ModificarAlertaSpring();
			SpringForward rtrn = modificarAlertaSpring.execute(mapping, alertaForm, request, response);
			model.addAttribute(STRING_ALERTAFORM, alertaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error en modificar alerta:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver actualizar alerta.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param alertaForm el alerta form
	 * @return el string
	 */
	@RequestMapping("/verActualizarAlerta")
	public String verActualizarAlerta(Model model, HttpServletRequest request, HttpServletResponse response,
			AlertaForm alertaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerActualizarAlertaSpring.class);
		try {
			VerActualizarAlertaSpring verActualizarAlertaSpring = new VerActualizarAlertaSpring();
			SpringForward rtrn = verActualizarAlertaSpring.execute(mapping, alertaForm, request, response);
			model.addAttribute(STRING_ALERTAFORM, alertaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error en ver actualizar alerta:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta alerta.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param alertaForm el alerta form
	 * @return el string
	 */
	@RequestMapping("/verAltaAlerta")
	public String verAltaAlerta(Model model, HttpServletRequest request, HttpServletResponse response,
			AlertaForm alertaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerAltaAlertaSpring.class);
		try {
			VerAltaAlertaSpring verAltaAlertaSpring = new VerAltaAlertaSpring();
			SpringForward rtrn = verAltaAlertaSpring.execute(mapping, alertaForm, request, response);
			model.addAttribute(STRING_ALERTAFORM, alertaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error en ver alta alerta:", e);
			throw new RuntimeException(e);
		}
	}
}
