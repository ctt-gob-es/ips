package es.map.ipsg.action.tarifa;

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
import es.map.ipsg.form.TarifaForm;

/**
 * El Class TarifaController.
 */
@Controller
@RequestMapping("/spring")

public class TarifaController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(TarifaController.class);
	
	/** La constante STRING_TARIFAFORM. */
	private static final String STRING_TARIFAFORM = "tarifaForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;

	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar tarifa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tarifaForm el tarifa form
	 * @return el string
	 */
	@RequestMapping("/buscarTarifa")
	public String buscarTarifa(Model model, HttpServletRequest request, HttpServletResponse response,
			TarifaForm tarifaForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		tarifaForm = formSessionMapper.resolveForm(tarifaForm, request);

		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BuscarTarifaSpring.class);
		try {
			BuscarTarifaSpring buscarTarifaSpring = new BuscarTarifaSpring();
			SpringForward rtrn = buscarTarifaSpring.execute(mapping, tarifaForm, request, response);
			model.addAttribute(STRING_TARIFAFORM, tarifaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ModificarTarifaSpring - buscarTarifa",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear tarifa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tarifaForm el tarifa form
	 * @return el string
	 */
	@RequestMapping("/crearTarifa")
	public String crearTarifa(Model model, HttpServletRequest request, HttpServletResponse response,
			TarifaForm tarifaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), CrearTarifaSpring.class);
		try {
			CrearTarifaSpring crearTarifaSpring = new CrearTarifaSpring();
			SpringForward rtrn = crearTarifaSpring.execute(mapping, tarifaForm, request, response);
			model.addAttribute(STRING_TARIFAFORM, tarifaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ModificarTarifaSpring - crearTarifa",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar tarifa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tarifaForm el tarifa form
	 * @return el string
	 */
	@RequestMapping("/eliminarTarifa")
	public String eliminarTarifa(Model model, HttpServletRequest request, HttpServletResponse response,
			TarifaForm tarifaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), EliminarTarifaSpring.class);
		try {
			EliminarTarifaSpring eliminarTarifaSpring = new EliminarTarifaSpring();
			SpringForward rtrn = eliminarTarifaSpring.execute(mapping, tarifaForm, request, response);
			model.addAttribute(STRING_TARIFAFORM, tarifaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ModificarTarifaSpring - eliminarTarifa",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar tarifa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tarifaForm el tarifa form
	 * @return el string
	 */
	@RequestMapping("/modificarTarifa")
	public String modificarTarifa(Model model, HttpServletRequest request, HttpServletResponse response,
			TarifaForm tarifaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ModificarTarifaSpring.class);
		try {
			ModificarTarifaSpring modificarTarifaSpring = new ModificarTarifaSpring();
			SpringForward rtrn = modificarTarifaSpring.execute(mapping, tarifaForm, request, response);
			model.addAttribute(STRING_TARIFAFORM, tarifaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ModificarTarifaSpring - modificarTarifa",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar tarifa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tarifaForm el tarifa form
	 * @return el string
	 */
	@RequestMapping("/verBuscarTarifa")
	public String verBuscarTarifa(Model model, HttpServletRequest request, HttpServletResponse response,
			TarifaForm tarifaForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		tarifaForm = formSessionMapper.resolveForm(tarifaForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerBuscarTarifaSpring.class);
		try {
			VerBuscarTarifaSpring verBuscarTarifaSpring = new VerBuscarTarifaSpring();
			SpringForward rtrn = verBuscarTarifaSpring.execute(mapping, tarifaForm, request, response);
			model.addAttribute(STRING_TARIFAFORM, tarifaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ModificarTarifaSpring - verBuscarTarifa",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear tarifa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tarifaForm el tarifa form
	 * @return el string
	 */
	@RequestMapping("/verCrearTarifa")
	public String verCrearTarifa(Model model, HttpServletRequest request, HttpServletResponse response,
			TarifaForm tarifaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerCrearTarifaSpring.class);
		try {
			VerCrearTarifaSpring verCrearTarifaSpring = new VerCrearTarifaSpring();
			SpringForward rtrn = verCrearTarifaSpring.execute(mapping, tarifaForm, request, response);
			model.addAttribute(STRING_TARIFAFORM, tarifaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ModificarTarifaSpring - verCrearTarifa",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar tarifa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param tarifaForm el tarifa form
	 * @return el string
	 */
	@RequestMapping("/verModificarTarifa")
	public String verModificarTarifa(Model model, HttpServletRequest request, HttpServletResponse response,
			TarifaForm tarifaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarTarifaSpring.class);
		try {
			VerModificarTarifaSpring verModificarTarifaSpring = new VerModificarTarifaSpring();
			SpringForward rtrn = verModificarTarifaSpring.execute(mapping, tarifaForm, request, response);
			model.addAttribute(STRING_TARIFAFORM, tarifaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ModificarTarifaSpring - verModificarTarifa",e);
			throw new RuntimeException(e);
		}
	}
}
