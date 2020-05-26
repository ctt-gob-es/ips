package es.map.ipsg.action.ministerio;

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
import es.map.ipsg.form.MinisterioForm;

/**
 * El Class MinisterioController.
 */
@Controller
@RequestMapping("/spring")
public class MinisterioController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(MinisterioController.class);
	
	/** La constante STRING_MINISTERIOFORM. */
	private static final String STRING_MINISTERIOFORM= "ministerioForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Actualizar ministerio.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param ministerioForm el ministerio form
	 * @return el string
	 */
	@RequestMapping("/actualizarMinisterio")
	public String actualizarMinisterio(Model model, HttpServletRequest request, HttpServletResponse response,
			MinisterioForm ministerioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarMinisterioSpring.class);
		try {
			ActualizarMinisterioSpring actualizarMinisterioSpring = new ActualizarMinisterioSpring();
			SpringForward rtrn = actualizarMinisterioSpring.execute(mapping, ministerioForm, request, response);
			model.addAttribute(STRING_MINISTERIOFORM, ministerioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MinisterioController - actualizarMinisterio:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Alta ministerios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param ministerioForm el ministerio form
	 * @return el string
	 */
	@RequestMapping("/altaMinisterio")
	public String altaMinisterios(Model model, HttpServletRequest request, HttpServletResponse response,
			MinisterioForm ministerioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), AltaMinisteriosSpring.class);
		try {
			AltaMinisteriosSpring altaMinisteriosSpring = new AltaMinisteriosSpring();
			SpringForward rtrn = altaMinisteriosSpring.execute(mapping, ministerioForm, request, response);
			model.addAttribute(STRING_MINISTERIOFORM, ministerioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MinisterioController - altaMinisterio:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar ministerios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param ministerioForm el ministerio form
	 * @return el string
	 */
	@RequestMapping("/buscarMinisterios")
	public String buscarMinisterios(Model model, HttpServletRequest request, HttpServletResponse response,
			MinisterioForm ministerioForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		ministerioForm = formSessionMapper.resolveForm(ministerioForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarMinisteriosSpring.class);
		try {
			BuscarMinisteriosSpring buscarMinisteriosSpring = new BuscarMinisteriosSpring();
			SpringForward rtrn = buscarMinisteriosSpring.execute(mapping, ministerioForm, request, response);
			model.addAttribute(STRING_MINISTERIOFORM, ministerioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MinisterioController - buscarMinisterios:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar ministerio.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param ministerioForm el ministerio form
	 * @return el string
	 */
	@RequestMapping("/eliminarMinisterio")
	public String eliminarMinisterio(Model model, HttpServletRequest request, HttpServletResponse response,
			MinisterioForm ministerioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarMinisterioSpring.class);
		try {
			EliminarMinisterioSpring eliminarMinisterioSpring = new EliminarMinisterioSpring();
			SpringForward rtrn = eliminarMinisterioSpring.execute(mapping, ministerioForm, request, response);
			model.addAttribute(STRING_MINISTERIOFORM, ministerioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MinisterioController - eliminarMinisterio:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar ministerio.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param ministerioForm el ministerio form
	 * @return el string
	 */
	@RequestMapping("/modificarMinisterio")
	public String modificarMinisterio(Model model, HttpServletRequest request, HttpServletResponse response,
			MinisterioForm ministerioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarMinisterioSpring.class);
		try {
			ModificarMinisterioSpring modificarMinisterioSpring = new ModificarMinisterioSpring();
			SpringForward rtrn = modificarMinisterioSpring.execute(mapping, ministerioForm, request, response);
			model.addAttribute(STRING_MINISTERIOFORM, ministerioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MinisterioController - modificarMinisterio:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta ministerio.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param ministerioForm el ministerio form
	 * @return el string
	 */
	@RequestMapping("/verAltaMinisterio")
	public String verAltaMinisterio(Model model, HttpServletRequest request, HttpServletResponse response,
			MinisterioForm ministerioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerAltaMinisterioSpring.class);
		try {
			VerAltaMinisterioSpring verAltaMinisterioSpring = new VerAltaMinisterioSpring();
			SpringForward rtrn = verAltaMinisterioSpring.execute(mapping, ministerioForm, request, response);
			model.addAttribute(STRING_MINISTERIOFORM, ministerioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MinisterioController - verAltaMinisterio:",e);
			throw new RuntimeException(e);
		}
	}
}
