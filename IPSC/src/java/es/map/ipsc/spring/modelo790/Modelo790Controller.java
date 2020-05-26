package es.map.ipsc.spring.modelo790;

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
import es.map.ipsc.form.Formulario790Form;

/**
 * El Class Modelo790Controller.
 */
@Controller
@RequestMapping("/secure")
public class Modelo790Controller {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(Modelo790Controller.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;

	/**
	 * Descargar formulario 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param formulario790Form el formulario 790 form
	 * @return el string
	 */
	@RequestMapping("/descargarFormulario790")
	public String descargarFormulario790(Model model, HttpServletRequest request, HttpServletResponse response,
			Formulario790Form formulario790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarFormulario790Spring.class);
		try {
			DescargarFormulario790Spring descargarFormulario790Spring = new DescargarFormulario790Spring();
			SpringForward rtrn = descargarFormulario790Spring.execute(mapping, formulario790Form, request, response);
			model.addAttribute("formulario790Form", formulario790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error Modelo790Controller - descargarFormulario790",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar formularios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/descargarFormularios")
	public String descargarFormularios(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarFormulariosSpring.class);
		try {
			DescargarFormulariosSpring descargarFormulariosSpring = new DescargarFormulariosSpring();
			SpringForward rtrn = descargarFormulariosSpring.execute(mapping, springForm, request, response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error Modelo790Controller - descargarFormularios",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Generar formulario 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param formulario790Form el formulario 790 form
	 * @return el string
	 */
	@RequestMapping("/generarFormulario790")
	public String generarFormulario790(Model model, HttpServletRequest request, HttpServletResponse response,
			Formulario790Form formulario790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				GenerarFormulario790Spring.class);
		try {
			GenerarFormulario790Spring generarFormulario790Spring = new GenerarFormulario790Spring();
			SpringForward rtrn = generarFormulario790Spring.execute(mapping, formulario790Form, request, response);
			model.addAttribute("formulario790Form", formulario790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error Modelo790Controller - generarFormulario790",e);
			throw new RuntimeException(e);
		}
	}
}
