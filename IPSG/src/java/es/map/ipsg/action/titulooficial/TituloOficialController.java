package es.map.ipsg.action.titulooficial;

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
import es.map.ipsg.form.TituloOficialForm;

/**
 * El Class TituloOficialController.
 */
@Controller
@RequestMapping("/spring")
public class TituloOficialController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(TituloOficialController.class);
	
	/** La constante STRING_TITULOOFICIALFORM. */
	private static final String STRING_TITULOOFICIALFORM = "tituloOficialForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar titulo oficial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tituloOficialForm el titulo oficial form
	 * @return el string
	 */
	@RequestMapping("/buscarTituloOficial")
	public String buscarTituloOficial(Model model, HttpServletRequest request, HttpServletResponse response,
			TituloOficialForm tituloOficialForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		tituloOficialForm = formSessionMapper.resolveForm(tituloOficialForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarTituloOficialSpring.class);
		try {
			BuscarTituloOficialSpring buscarTituloOficialSpring = new BuscarTituloOficialSpring();
			SpringForward rtrn = buscarTituloOficialSpring.execute(mapping, tituloOficialForm, request, response);
			model.addAttribute(STRING_TITULOOFICIALFORM, tituloOficialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error TituloOficialController - buscarTituloOficial",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear titulo oficial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tituloOficialForm el titulo oficial form
	 * @return el string
	 */
	@RequestMapping("/crearTituloOficial")
	public String crearTituloOficial(Model model, HttpServletRequest request, HttpServletResponse response,
			TituloOficialForm tituloOficialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearTituloOficialSpring.class);
		try {
			CrearTituloOficialSpring crearTituloOficialSpring = new CrearTituloOficialSpring();
			SpringForward rtrn = crearTituloOficialSpring.execute(mapping, tituloOficialForm, request, response);
			model.addAttribute(STRING_TITULOOFICIALFORM, tituloOficialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error TituloOficialController - crearTituloOficial",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar titulo oficial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tituloOficialForm el titulo oficial form
	 * @return el string
	 */
	@RequestMapping("/eliminarTituloOficial")
	public String eliminarTituloOficial(Model model, HttpServletRequest request, HttpServletResponse response,
			TituloOficialForm tituloOficialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarTituloOficialSpring.class);
		try {
			EliminarTituloOficialSpring eliminarTituloOficialSpring = new EliminarTituloOficialSpring();
			SpringForward rtrn = eliminarTituloOficialSpring.execute(mapping, tituloOficialForm, request, response);
			model.addAttribute(STRING_TITULOOFICIALFORM, tituloOficialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error TituloOficialController - eliminarTituloOficial",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar titulo oficial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tituloOficialForm el titulo oficial form
	 * @return el string
	 */
	@RequestMapping("/modificarTituloOficial")
	public String modificarTituloOficial(Model model, HttpServletRequest request, HttpServletResponse response,
			TituloOficialForm tituloOficialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarTituloOficialSpring.class);
		try {
			ModificarTituloOficialSpring modificarTituloOficialSpring = new ModificarTituloOficialSpring();
			SpringForward rtrn = modificarTituloOficialSpring.execute(mapping, tituloOficialForm, request, response);
			model.addAttribute(STRING_TITULOOFICIALFORM, tituloOficialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error TituloOficialController - modificarTituloOficial",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear titulo oficial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tituloOficialForm el titulo oficial form
	 * @return el string
	 */
	@RequestMapping("/verCrearTituloOficial")
	public String verCrearTituloOficial(Model model, HttpServletRequest request, HttpServletResponse response,
			TituloOficialForm tituloOficialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearTituloOficialSpring.class);
		try {
			VerCrearTituloOficialSpring verCrearTituloOficialSpring = new VerCrearTituloOficialSpring();
			SpringForward rtrn = verCrearTituloOficialSpring.execute(mapping, tituloOficialForm, request, response);
			model.addAttribute(STRING_TITULOOFICIALFORM, tituloOficialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error TituloOficialController - verCrearTituloOficial",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar titulo oficial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tituloOficialForm el titulo oficial form
	 * @return el string
	 */
	@RequestMapping("/verModificarTituloOficial")
	public String verModificarTituloOficial(Model model, HttpServletRequest request, HttpServletResponse response,
			TituloOficialForm tituloOficialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarTituloOficialSpring.class);
		try {
			VerModificarTituloOficialSpring verModificarTituloOficialSpring = new VerModificarTituloOficialSpring();
			SpringForward rtrn = verModificarTituloOficialSpring.execute(mapping, tituloOficialForm, request, response);
			model.addAttribute(STRING_TITULOOFICIALFORM, tituloOficialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error TituloOficialController - verModificarTituloOficial",e);
			throw new RuntimeException(e);
		}
	}
}
