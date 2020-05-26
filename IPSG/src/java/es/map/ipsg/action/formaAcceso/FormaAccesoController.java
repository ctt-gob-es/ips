package es.map.ipsg.action.formaAcceso;

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
import es.map.ipsg.form.FormasAccesoForm;

/**
 * El Class FormaAccesoController.
 */
@Controller
@RequestMapping("/spring")
public class FormaAccesoController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(FormaAccesoController.class);
	
	/** La constante STRING_FORMASACCESOFORM. */
	private static final String STRING_FORMASACCESOFORM = "formasAccesoForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar formas acceso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param formasAccesoForm el formas acceso form
	 * @return el string
	 */
	@RequestMapping("/buscarFormasAcceso")
	public String buscarFormasAcceso(Model model, HttpServletRequest request, HttpServletResponse response,
			FormasAccesoForm formasAccesoForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		formasAccesoForm = formSessionMapper.resolveForm(formasAccesoForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarFormasAccesoSpring.class);
		try {
			BuscarFormasAccesoSpring buscarFormasAccesoSpring = new BuscarFormasAccesoSpring();
			SpringForward rtrn = buscarFormasAccesoSpring.execute(mapping, formasAccesoForm, request, response);
			model.addAttribute(STRING_FORMASACCESOFORM, formasAccesoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error FormaAccesoController -  buscarFormasAcceso:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear formas acceso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param formasAccesoForm el formas acceso form
	 * @return el string
	 */
	@RequestMapping("/crearFormasAcceso")
	public String crearFormasAcceso(Model model, HttpServletRequest request, HttpServletResponse response,
			FormasAccesoForm formasAccesoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearFormasAccesoSpring.class);
		try {
			CrearFormasAccesoSpring crearFormasAccesoSpring = new CrearFormasAccesoSpring();
			SpringForward rtrn = crearFormasAccesoSpring.execute(mapping, formasAccesoForm, request, response);
			model.addAttribute(STRING_FORMASACCESOFORM, formasAccesoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error FormaAccesoController - crearFormasAcceso:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar formas acceso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param formasAccesoForm el formas acceso form
	 * @return el string
	 */
	@RequestMapping("/eliminarFormasAcceso")
	public String eliminarFormasAcceso(Model model, HttpServletRequest request, HttpServletResponse response,
			FormasAccesoForm formasAccesoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarFormasAccesoSpring.class);
		try {
			EliminarFormasAccesoSpring eliminarFormasAccesoSpring = new EliminarFormasAccesoSpring();
			SpringForward rtrn = eliminarFormasAccesoSpring.execute(mapping, formasAccesoForm, request, response);
			model.addAttribute(STRING_FORMASACCESOFORM, formasAccesoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error FormaAccesoController - eliminarFormasAcceso:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar formas acceso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param formasAccesoForm el formas acceso form
	 * @return el string
	 */
	@RequestMapping("/modificarFormasAcceso")
	public String modificarFormasAcceso(Model model, HttpServletRequest request, HttpServletResponse response,
			FormasAccesoForm formasAccesoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarFormasAccesoSpring.class);
		try {
			ModificarFormasAccesoSpring modificarFormasAccesoSpring = new ModificarFormasAccesoSpring();
			SpringForward rtrn = modificarFormasAccesoSpring.execute(mapping, formasAccesoForm, request, response);
			model.addAttribute(STRING_FORMASACCESOFORM, formasAccesoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error FormaAccesoController - modificarFormasAcceso:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear formas acceso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param formasAccesoForm el formas acceso form
	 * @return el string
	 */
	@RequestMapping("/verCrearFormasAcceso")
	public String verCrearFormasAcceso(Model model, HttpServletRequest request, HttpServletResponse response,
			FormasAccesoForm formasAccesoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearFormasAccesoSpring.class);
		try {
			VerCrearFormasAccesoSpring verCrearFormasAccesoSpring = new VerCrearFormasAccesoSpring();
			SpringForward rtrn = verCrearFormasAccesoSpring.execute(mapping, formasAccesoForm, request, response);
			model.addAttribute(STRING_FORMASACCESOFORM, formasAccesoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error FormaAccesoController - verCrearFormasAcceso:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar formas acceso.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param formasAccesoForm el formas acceso form
	 * @return el string
	 */
	@RequestMapping("/verModificarFormasAcceso")
	public String verModificarFormasAcceso(Model model, HttpServletRequest request, HttpServletResponse response,
			FormasAccesoForm formasAccesoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarFormasAccesoSpring.class);
		try {
			VerModificarFormasAccesoSpring verModificarFormasAccesoSpring = new VerModificarFormasAccesoSpring();
			SpringForward rtrn = verModificarFormasAccesoSpring.execute(mapping, formasAccesoForm, request, response);
			model.addAttribute(STRING_FORMASACCESOFORM, formasAccesoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error FormaAccesoController - verModificarFormasAcceso:",e);
			throw new RuntimeException(e);
		}
	}
}
