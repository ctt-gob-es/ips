package es.map.ipsg.action.grupos;

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
import es.map.ipsg.form.GrupoForm;

/**
 * El Class GruposController.
 */
@Controller
@RequestMapping("/spring")
public class GruposController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GruposController.class);
	
	/** La constante STRING_GRUPOFORM. */
	private static final String STRING_GRUPOFORM = "grupoForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Actualizar grupo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param grupoForm el grupo form
	 * @return el string
	 */
	@RequestMapping("/actualizarGrupo")
	public String actualizarGrupo(Model model, HttpServletRequest request, HttpServletResponse response,
			GrupoForm grupoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ActualizarGrupoSpring.class);
		try {
			ActualizarGrupoSpring actualizarGrupoSpring = new ActualizarGrupoSpring();
			SpringForward rtrn = actualizarGrupoSpring.execute(mapping, grupoForm, request, response);
			model.addAttribute(STRING_GRUPOFORM, grupoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GruposController - actualizarGrupo:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Alta grupo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param grupoForm el grupo form
	 * @return el string
	 */
	@RequestMapping("/altaGrupo")
	public String altaGrupo(Model model, HttpServletRequest request, HttpServletResponse response,
			GrupoForm grupoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), AltaGrupoSpring.class);
		try {
			AltaGrupoSpring altaGrupoSpring = new AltaGrupoSpring();
			SpringForward rtrn = altaGrupoSpring.execute(mapping, grupoForm, request, response);
			model.addAttribute(STRING_GRUPOFORM, grupoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GruposController - altaGrupo:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar grupo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param grupoForm el grupo form
	 * @return el string
	 */
	@RequestMapping("/buscarGrupo")
	public String buscarGrupo(Model model, HttpServletRequest request, HttpServletResponse response,
			GrupoForm grupoForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		grupoForm = formSessionMapper.resolveForm(grupoForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BuscarGruposSpring.class);
		try {
			BuscarGruposSpring buscarGruposSpring = new BuscarGruposSpring();
			SpringForward rtrn = buscarGruposSpring.execute(mapping, grupoForm, request, response);
			model.addAttribute(STRING_GRUPOFORM, grupoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GruposController - buscarGrupo:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar grupo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param grupoForm el grupo form
	 * @return el string
	 */
	@RequestMapping("/eliminarGrupo")
	public String eliminarGrupo(Model model, HttpServletRequest request, HttpServletResponse response,
			GrupoForm grupoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), EliminarGrupoSpring.class);
		try {
			EliminarGrupoSpring eliminarGrupoSpring = new EliminarGrupoSpring();
			SpringForward rtrn = eliminarGrupoSpring.execute(mapping, grupoForm, request, response);
			model.addAttribute(STRING_GRUPOFORM, grupoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GruposController - eliminarGrupo:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar grupo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param grupoForm el grupo form
	 * @return el string
	 */
	@RequestMapping("/modificarGrupo")
	public String modificarGrupo(Model model, HttpServletRequest request, HttpServletResponse response,
			GrupoForm grupoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ModificarGruposSpring.class);
		try {
			ModificarGruposSpring modificarGruposSpring = new ModificarGruposSpring();
			SpringForward rtrn = modificarGruposSpring.execute(mapping, grupoForm, request, response);
			model.addAttribute(STRING_GRUPOFORM, grupoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GruposController - modificarGrupo:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta grupo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param grupoForm el grupo form
	 * @return el string
	 */
	@RequestMapping("/verAltaGrupo")
	public String verAltaGrupo(Model model, HttpServletRequest request, HttpServletResponse response,
			GrupoForm grupoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerAltaGrupoSpring.class);
		try {
			VerAltaGrupoSpring verAltaGrupoSpring = new VerAltaGrupoSpring();
			SpringForward rtrn = verAltaGrupoSpring.execute(mapping, grupoForm, request, response);
			model.addAttribute(STRING_GRUPOFORM, grupoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GruposController - verAltaGrupo:",e);
			throw new RuntimeException(e);
		}
	}
}
