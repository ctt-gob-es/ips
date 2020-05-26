package es.map.ipsg.action.cuerpoescala;

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
import es.map.ipsg.form.CuerpoEscalaForm;

/**
 * El Class CuerpoEscalaController.
 */
@Controller
@RequestMapping("/spring")
public class CuerpoEscalaController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CuerpoEscalaController.class);
	
	/** La constante STRINGCUERPOESCALAFORM. */
	private static final String STRINGCUERPOESCALAFORM = "cuerpoEscalaForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Alta cuerpos escala.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cuerpoEscalaForm el cuerpo escala form
	 * @return el string
	 */
	@RequestMapping("/altaCuerposEscala")
	public String altaCuerposEscala(Model model, HttpServletRequest request, HttpServletResponse response,
			CuerpoEscalaForm cuerpoEscalaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				AltaCuerposEscalaSpring.class);
		try {
			AltaCuerposEscalaSpring altaCuerposEscalaSpring = new AltaCuerposEscalaSpring();
			SpringForward rtrn = altaCuerposEscalaSpring.execute(mapping, cuerpoEscalaForm, request, response);
			model.addAttribute(STRINGCUERPOESCALAFORM, cuerpoEscalaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-altaCuerposEscala:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar cuerpos escala.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cuerpoEscalaForm el cuerpo escala form
	 * @return el string
	 */
	@RequestMapping("/buscarCuerposEscala")
	public String buscarCuerposEscala(Model model, HttpServletRequest request, HttpServletResponse response,
			CuerpoEscalaForm cuerpoEscalaForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		cuerpoEscalaForm = formSessionMapper.resolveForm(cuerpoEscalaForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarCuerposEscalaSpring.class);
		try {
			BuscarCuerposEscalaSpring buscarCuerposEscalaSpring = new BuscarCuerposEscalaSpring();
			SpringForward rtrn = buscarCuerposEscalaSpring.execute(mapping, cuerpoEscalaForm, request, response);
			model.addAttribute(STRINGCUERPOESCALAFORM, cuerpoEscalaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-buscarCuerposEscala:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar cuerpos escala.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cuerpoEscalaForm el cuerpo escala form
	 * @return el string
	 */
	@RequestMapping("/eliminarCuerposEscala")
	public String eliminarCuerposEscala(Model model, HttpServletRequest request, HttpServletResponse response,
			CuerpoEscalaForm cuerpoEscalaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarCuerposEscalaSpring.class);
		try {
			EliminarCuerposEscalaSpring eliminarCuerposEscalaSpring = new EliminarCuerposEscalaSpring();
			SpringForward rtrn = eliminarCuerposEscalaSpring.execute(mapping, cuerpoEscalaForm, request, response);
			model.addAttribute(STRINGCUERPOESCALAFORM, cuerpoEscalaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-eliminarCuerposEscala:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Listar cuerpo escala.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cuerpoEscalaForm el cuerpo escala form
	 * @return el string
	 */
	@RequestMapping("/listarCuerpoEscala")
	public String listarCuerpoEscala(Model model, HttpServletRequest request, HttpServletResponse response,
			CuerpoEscalaForm cuerpoEscalaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ListarCuerpoEscalaSpring.class);
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		cuerpoEscalaForm = formSessionMapper.resolveForm(cuerpoEscalaForm, request);
		
		try {
			ListarCuerpoEscalaSpring listarCuerpoEscalaSpring = new ListarCuerpoEscalaSpring();
			SpringForward rtrn = listarCuerpoEscalaSpring.execute(mapping, cuerpoEscalaForm, request, response);
			model.addAttribute(STRINGCUERPOESCALAFORM, cuerpoEscalaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-listarCuerposEscala:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar cuerpos escala.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cuerpoEscalaForm el cuerpo escala form
	 * @return el string
	 */
	@RequestMapping("/modificarCuerposEscala")
	public String modificarCuerposEscala(Model model, HttpServletRequest request, HttpServletResponse response,
			CuerpoEscalaForm cuerpoEscalaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarCuerposEscalaSpring.class);
		try {
			ModificarCuerposEscalaSpring modificarCuerposEscalaSpring = new ModificarCuerposEscalaSpring();
			SpringForward rtrn = modificarCuerposEscalaSpring.execute(mapping, cuerpoEscalaForm, request, response);
			model.addAttribute(STRINGCUERPOESCALAFORM, cuerpoEscalaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-modificarCuerposEscala:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta cuerpos escala.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cuerpoEscalaForm el cuerpo escala form
	 * @return el string
	 */
	@RequestMapping("/verAltaCuerposEscala")
	public String verAltaCuerposEscala(Model model, HttpServletRequest request, HttpServletResponse response,
			CuerpoEscalaForm cuerpoEscalaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerAltaCuerposEscalaSpring.class);
		try {
			VerAltaCuerposEscalaSpring verAltaCuerposEscalaSpring = new VerAltaCuerposEscalaSpring();
			SpringForward rtrn = verAltaCuerposEscalaSpring.execute(mapping, cuerpoEscalaForm, request, response);
			model.addAttribute(STRINGCUERPOESCALAFORM, cuerpoEscalaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-verAltaCuerposEscala:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar cuerpos escala.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cuerpoEscalaForm el cuerpo escala form
	 * @return el string
	 */
	@RequestMapping("/verModificarCuerposEscala")
	public String verModificarCuerposEscala(Model model, HttpServletRequest request, HttpServletResponse response,
			CuerpoEscalaForm cuerpoEscalaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarCuerposEscalaSpring.class);
		try {
			VerModificarCuerposEscalaSpring verModificarCuerposEscalaSpring = new VerModificarCuerposEscalaSpring();
			SpringForward rtrn = verModificarCuerposEscalaSpring.execute(mapping, cuerpoEscalaForm, request, response);
			model.addAttribute(STRINGCUERPOESCALAFORM, cuerpoEscalaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController- verModificarCuerposEscala:",e);
			throw new RuntimeException(e);
		}
	}
}
