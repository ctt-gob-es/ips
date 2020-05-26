package es.map.ipsg.action.especialidad;

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
import es.map.ipsg.form.EspecialidadForm;

/**
 * El Class EspecialidadController.
 */
@Controller
@RequestMapping("/spring")
public class EspecialidadController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EspecialidadController.class);
	
	/** La constante STRING_ESPECIALIDADFORM. */
	private static final String STRING_ESPECIALIDADFORM = "especialidadForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar especialidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param especialidadForm el especialidad form
	 * @return el string
	 */
	@RequestMapping("/buscarEspecialidad")
	public String buscarEspecialidad(Model model, HttpServletRequest request, HttpServletResponse response,
			EspecialidadForm especialidadForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		especialidadForm = formSessionMapper.resolveForm(especialidadForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarEspecialidadSpring.class);
		try {
			BuscarEspecialidadSpring buscarEspecialidadSpring = new BuscarEspecialidadSpring();
			SpringForward rtrn = buscarEspecialidadSpring.execute(mapping, especialidadForm, request, response);
			model.addAttribute(STRING_ESPECIALIDADFORM, especialidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EspecialidadController - buscarEspecialidad :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear especialidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param especialidadForm el especialidad form
	 * @return el string
	 */
	@RequestMapping("/crearEspecialidad")
	public String crearEspecialidad(Model model, HttpServletRequest request, HttpServletResponse response,
			EspecialidadForm especialidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearEspecialidadSpring.class);
		try {
			CrearEspecialidadSpring crearEspecialidadSpring = new CrearEspecialidadSpring();
			SpringForward rtrn = crearEspecialidadSpring.execute(mapping, especialidadForm, request, response);
			model.addAttribute(STRING_ESPECIALIDADFORM, especialidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EspecialidadController - crearEspecialidad :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar especialidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param especialidadForm el especialidad form
	 * @return el string
	 */
	@RequestMapping("/eliminarEspecialidad")
	public String eliminarEspecialidad(Model model, HttpServletRequest request, HttpServletResponse response,
			EspecialidadForm especialidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarEspecialidadSpring.class);
		try {
			EliminarEspecialidadSpring eliminarEspecialidadSpring = new EliminarEspecialidadSpring();
			SpringForward rtrn = eliminarEspecialidadSpring.execute(mapping, especialidadForm, request, response);
			model.addAttribute(STRING_ESPECIALIDADFORM, especialidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EspecialidadController - eliminarEspecialidad :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Listar especialidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param especialidadForm el especialidad form
	 * @return el string
	 */
	@RequestMapping("/listarEspecialidad")
	public String listarEspecialidad(Model model, HttpServletRequest request, HttpServletResponse response,
			EspecialidadForm especialidadForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		especialidadForm = formSessionMapper.resolveForm(especialidadForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ListarEspecialidadSpring.class);
		try {
			ListarEspecialidadSpring listarEspecialidadSpring = new ListarEspecialidadSpring();
			SpringForward rtrn = listarEspecialidadSpring.execute(mapping, especialidadForm, request, response);
			model.addAttribute(STRING_ESPECIALIDADFORM, especialidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EspecialidadController - listarEspecialidad :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar especialidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param especialidadForm el especialidad form
	 * @return el string
	 */
	@RequestMapping("/modificarEspecialidad")
	public String modificarEspecialidad(Model model, HttpServletRequest request, HttpServletResponse response,
			EspecialidadForm especialidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarEspecialidadSpring.class);
		try {
			ModificarEspecialidadSpring modificarEspecialidadSpring = new ModificarEspecialidadSpring();
			SpringForward rtrn = modificarEspecialidadSpring.execute(mapping, especialidadForm, request, response);
			model.addAttribute(STRING_ESPECIALIDADFORM, especialidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EspecialidadController - modificarEspecialidad :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear especialidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param especialidadForm el especialidad form
	 * @return el string
	 */
	@RequestMapping("/verCrearEspecialidad")
	public String verCrearEspecialidad(Model model, HttpServletRequest request, HttpServletResponse response,
			EspecialidadForm especialidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearEspecialidadSpring.class);
		try {
			VerCrearEspecialidadSpring verCrearEspecialidadSpring = new VerCrearEspecialidadSpring();
			SpringForward rtrn = verCrearEspecialidadSpring.execute(mapping, especialidadForm, request, response);
			model.addAttribute(STRING_ESPECIALIDADFORM, especialidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EspecialidadController - verCrearEspecialidad :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar especialidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param especialidadForm el especialidad form
	 * @return el string
	 */
	@RequestMapping("/verModificarEspecialidad")
	public String verModificarEspecialidad(Model model, HttpServletRequest request, HttpServletResponse response,
			EspecialidadForm especialidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarEspecialidadSpring.class);
		try {
			VerModificarEspecialidadSpring verModificarEspecialidadSpring = new VerModificarEspecialidadSpring();
			SpringForward rtrn = verModificarEspecialidadSpring.execute(mapping, especialidadForm, request, response);
			model.addAttribute(STRING_ESPECIALIDADFORM, especialidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EspecialidadController - verModificarEspecialidad :",e);
			throw new RuntimeException(e);
		}
	}
}
