package es.map.ipsg.action.adaptacionesDiscapacidad;

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
import es.map.ipsg.form.DiscapacidadForm;

/**
 * El Class DiscapacidadController.
 */
@Controller
@RequestMapping("/spring")
public class DiscapacidadController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DiscapacidadController.class);
	
	/** La constante STRINGDISCAPACIDADFORM. */
	private static final String STRINGDISCAPACIDADFORM = "discapacidadForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Alta discapacidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param discapacidadForm el discapacidad form
	 * @return el string
	 */
	@RequestMapping("/altaDiscapacidad")
	public String altaDiscapacidad(Model model, HttpServletRequest request, HttpServletResponse response,
			DiscapacidadForm discapacidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				AltaDiscapacidadSpring.class);
		try {
			AltaDiscapacidadSpring altaDiscapacidadSpring = new AltaDiscapacidadSpring();
			SpringForward rtrn = altaDiscapacidadSpring.execute(mapping, discapacidadForm, request, response);
			model.addAttribute(STRINGDISCAPACIDADFORM, discapacidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-altaDiscapacidad:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar discapacidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param discapacidadForm el discapacidad form
	 * @return el string
	 */
	@RequestMapping("/buscarDiscapacidad")
	public String buscarDiscapacidad(Model model, HttpServletRequest request, HttpServletResponse response,
			DiscapacidadForm discapacidadForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		discapacidadForm = formSessionMapper.resolveForm(discapacidadForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarDiscapacidadSpring.class);
		try {
			BuscarDiscapacidadSpring buscarDiscapacidadSpring = new BuscarDiscapacidadSpring();
			SpringForward rtrn = buscarDiscapacidadSpring.execute(mapping, discapacidadForm, request, response);
			model.addAttribute(STRINGDISCAPACIDADFORM, discapacidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-buscarDiscapacidad:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar discapacidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param discapacidadForm el discapacidad form
	 * @return el string
	 */
	@RequestMapping("/eliminarDiscapacidad")
	public String eliminarDiscapacidad(Model model, HttpServletRequest request, HttpServletResponse response,
			DiscapacidadForm discapacidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarDiscapacidadSpring.class);
		try {
			EliminarDiscapacidadSpring eliminarDiscapacidadSpring = new EliminarDiscapacidadSpring();
			SpringForward rtrn = eliminarDiscapacidadSpring.execute(mapping, discapacidadForm, request, response);
			model.addAttribute(STRINGDISCAPACIDADFORM, discapacidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-eliminarDiscapacidad:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar discapacidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param discapacidadForm el discapacidad form
	 * @return el string
	 */
	@RequestMapping("/modificarDiscapacidad")
	public String modificarDiscapacidad(Model model, HttpServletRequest request, HttpServletResponse response,
			DiscapacidadForm discapacidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarDiscapacidadSpring.class);
		try {
			ModificarDiscapacidadSpring modificarDiscapacidadSpring = new ModificarDiscapacidadSpring();
			SpringForward rtrn = modificarDiscapacidadSpring.execute(mapping, discapacidadForm, request, response);
			model.addAttribute(STRINGDISCAPACIDADFORM, discapacidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-modificarDiscapacidad:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta discapacidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param discapacidadForm el discapacidad form
	 * @return el string
	 */
	@RequestMapping("/verAltaDiscapacidad")
	public String verAltaDiscapacidad(Model model, HttpServletRequest request, HttpServletResponse response,
			DiscapacidadForm discapacidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerAltaDiscapacidadSpring.class);
		try {
			VerAltaDiscapacidadSpring verAltaDiscapacidadSpring = new VerAltaDiscapacidadSpring();
			SpringForward rtrn = verAltaDiscapacidadSpring.execute(mapping, discapacidadForm, request, response);
			model.addAttribute(STRINGDISCAPACIDADFORM, discapacidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-verAltaDiscapacidad:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar discapacidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param discapacidadForm el discapacidad form
	 * @return el string
	 */
	@RequestMapping("/verModificarDiscapacidad")
	public String verModificarDiscapacidad(Model model, HttpServletRequest request, HttpServletResponse response,
			DiscapacidadForm discapacidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarDiscapacidadSpring.class);
		try {
			VerModificarDiscapacidadSpring verModificarDiscapacidadSpring = new VerModificarDiscapacidadSpring();
			SpringForward rtrn = verModificarDiscapacidadSpring.execute(mapping, discapacidadForm, request, response);
			model.addAttribute(STRINGDISCAPACIDADFORM, discapacidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController- verModificarDiscapacidad:",e);
			throw new RuntimeException(e);
		}
	}
}
