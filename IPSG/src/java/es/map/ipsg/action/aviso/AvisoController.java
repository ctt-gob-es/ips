package es.map.ipsg.action.aviso;

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
import es.map.ipsg.form.AvisoForm;


/**
 * El Class AvisoController.
 */
@Controller
@RequestMapping("/spring")
public class AvisoController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AvisoController.class);
	
	/** La constante STRING_AVISO_FORM. */
	private static final String STRING_AVISO_FORM = "avisoForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Actualizar aviso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param avisoForm el aviso form
	 * @return el string
	 */
	@RequestMapping("/ActualizarAviso")
	public String actualizarAviso(Model model, HttpServletRequest request, HttpServletResponse response,
			AvisoForm avisoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ActualizarAvisoSpring.class);
		try {
			ActualizarAvisoSpring actualizarAvisoSpring = new ActualizarAvisoSpring();
			SpringForward rtrn = actualizarAvisoSpring.execute(mapping, avisoForm, request, response);
			model.addAttribute(STRING_AVISO_FORM, avisoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ActualizarAviso:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Alta aviso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param avisoForm el aviso form
	 * @return el string
	 */
	@RequestMapping("/altaAviso")
	public String altaAviso(Model model, HttpServletRequest request, HttpServletResponse response,
			AvisoForm avisoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), AltaAvisoSpring.class);
		try {
			AltaAvisoSpring altaAvisoSpring = new AltaAvisoSpring();
			SpringForward rtrn = altaAvisoSpring.execute(mapping, avisoForm, request, response);
			model.addAttribute(STRING_AVISO_FORM, avisoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error AltaAviso:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar aviso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param avisoForm el aviso form
	 * @return el string
	 */
	@RequestMapping("/buscarAviso")
	public String buscarAviso(Model model, HttpServletRequest request, HttpServletResponse response,
			AvisoForm avisoForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		avisoForm = formSessionMapper.resolveForm(avisoForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BuscarAvisoSpring.class);
		try {
			BuscarAvisoSpring buscarAvisoSpring = new BuscarAvisoSpring();
			SpringForward rtrn = buscarAvisoSpring.execute(mapping, avisoForm, request, response);
			model.addAttribute(STRING_AVISO_FORM, avisoForm);

			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error BuscarAviso:", e);
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * Eliminar aviso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param avisoForm el aviso form
	 * @return el string
	 */
	@RequestMapping("/verEliminarAviso")
	public String eliminarAviso(Model model, HttpServletRequest request, HttpServletResponse response,
			AvisoForm avisoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), EliminarAvisoSpring.class);
		try {
			EliminarAvisoSpring eliminarAvisoSpring = new EliminarAvisoSpring();
			SpringForward rtrn = eliminarAvisoSpring.execute(mapping, avisoForm, request, response);
			model.addAttribute(STRING_AVISO_FORM, avisoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EliminarAviso:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver actualizar aviso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param avisoForm el aviso form
	 * @return el string
	 */
	@RequestMapping("/verActualizarAviso")
	public String verActualizarAviso(Model model, HttpServletRequest request, HttpServletResponse response,
			AvisoForm avisoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerActualizarAvisoSpring.class);
		try {
			VerActualizarAvisoSpring verActualizarAvisoSpring = new VerActualizarAvisoSpring();
			SpringForward rtrn = verActualizarAvisoSpring.execute(mapping, avisoForm, request, response);
			model.addAttribute(STRING_AVISO_FORM, avisoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error verActualizarAviso:", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta aviso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param avisoForm el aviso form
	 * @return el string
	 */
	@RequestMapping("/verAltaAviso")
	public String verAltaAviso(Model model, HttpServletRequest request, HttpServletResponse response,
			AvisoForm avisoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerAltaAvisoSpring.class);
		try {
			VerAltaAvisoSpring verAltaAvisoSpring = new VerAltaAvisoSpring();
			SpringForward rtrn = verAltaAvisoSpring.execute(mapping, avisoForm, request, response);
			model.addAttribute(STRING_AVISO_FORM, avisoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error verAltaAviso:", e);
			throw new RuntimeException(e);
		}
	}
}
