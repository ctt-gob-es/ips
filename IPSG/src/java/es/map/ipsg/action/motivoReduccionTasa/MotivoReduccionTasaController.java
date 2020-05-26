package es.map.ipsg.action.motivoReduccionTasa;

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
import es.map.ipsg.form.MotivoReduccionTasaForm;

/**
 * El Class MotivoReduccionTasaController.
 */
@Controller
@RequestMapping("/spring")
public class MotivoReduccionTasaController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(MotivoReduccionTasaController.class);
	
	/** La constante STRING_MOTIVOREDUCCIONTASAFORM. */
	private static final String STRING_MOTIVOREDUCCIONTASAFORM = "motivoReduccionTasaForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar motivo reduccion tasa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param motivoReduccionTasaForm el motivo reduccion tasa form
	 * @return el string
	 */
	@RequestMapping("/buscarMotivoReduccionTasa")
	public String buscarMotivoReduccionTasa(Model model, HttpServletRequest request, HttpServletResponse response,
			MotivoReduccionTasaForm motivoReduccionTasaForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		motivoReduccionTasaForm = formSessionMapper.resolveForm(motivoReduccionTasaForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarMotivoReduccionTasaSpring.class);
		try {
			BuscarMotivoReduccionTasaSpring buscarMotivoReduccionTasaSpring = new BuscarMotivoReduccionTasaSpring();
			SpringForward rtrn = buscarMotivoReduccionTasaSpring.execute(mapping, motivoReduccionTasaForm, request,
					response);
			model.addAttribute(STRING_MOTIVOREDUCCIONTASAFORM, motivoReduccionTasaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MotivoReduccionTasaController - buscarMotivoReduccionTasa:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear motivo reduccion tasa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param motivoReduccionTasaForm el motivo reduccion tasa form
	 * @return el string
	 */
	@RequestMapping("/crearMotivoReduccionTasa")
	public String crearMotivoReduccionTasa(Model model, HttpServletRequest request, HttpServletResponse response,
			MotivoReduccionTasaForm motivoReduccionTasaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearMotivoReduccionTasaSpring.class);
		try {
			CrearMotivoReduccionTasaSpring crearMotivoReduccionTasaSpring = new CrearMotivoReduccionTasaSpring();
			SpringForward rtrn = crearMotivoReduccionTasaSpring.execute(mapping, motivoReduccionTasaForm, request,
					response);
			model.addAttribute(STRING_MOTIVOREDUCCIONTASAFORM, motivoReduccionTasaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MotivoReduccionTasaController - crearMotivoReduccionTasa:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar motivo reduccion tasa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param motivoReduccionTasaForm el motivo reduccion tasa form
	 * @return el string
	 */
	@RequestMapping("/eliminarMotivoReduccionTasa")
	public String eliminarMotivoReduccionTasa(Model model, HttpServletRequest request, HttpServletResponse response,
			MotivoReduccionTasaForm motivoReduccionTasaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarMotivoReduccionTasaSpring.class);
		try {
			EliminarMotivoReduccionTasaSpring eliminarMotivoReduccionTasaSpring = new EliminarMotivoReduccionTasaSpring();
			SpringForward rtrn = eliminarMotivoReduccionTasaSpring.execute(mapping, motivoReduccionTasaForm, request,
					response);
			model.addAttribute(STRING_MOTIVOREDUCCIONTASAFORM, motivoReduccionTasaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MotivoReduccionTasaController - eliminarMotivoReduccionTasa:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar motivo reduccion tasa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param motivoReduccionTasaForm el motivo reduccion tasa form
	 * @return el string
	 */
	@RequestMapping("/modificarMotivoReduccionTasa")
	public String modificarMotivoReduccionTasa(Model model, HttpServletRequest request, HttpServletResponse response,
			MotivoReduccionTasaForm motivoReduccionTasaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarMotivoReduccionTasaSpring.class);
		try {
			ModificarMotivoReduccionTasaSpring modificarMotivoReduccionTasaSpring = new ModificarMotivoReduccionTasaSpring();
			SpringForward rtrn = modificarMotivoReduccionTasaSpring.execute(mapping, motivoReduccionTasaForm, request,
					response);
			model.addAttribute(STRING_MOTIVOREDUCCIONTASAFORM, motivoReduccionTasaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MotivoReduccionTasaController - modificarMotivoReduccionTasa:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear motivo reduccion tasa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param motivoReduccionTasaForm el motivo reduccion tasa form
	 * @return el string
	 */
	@RequestMapping("/verCrearMotivoReduccionTasa")
	public String verCrearMotivoReduccionTasa(Model model, HttpServletRequest request, HttpServletResponse response,
			MotivoReduccionTasaForm motivoReduccionTasaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearMotivoReduccionTasaSpring.class);
		try {
			VerCrearMotivoReduccionTasaSpring verCrearMotivoReduccionTasaSpring = new VerCrearMotivoReduccionTasaSpring();
			SpringForward rtrn = verCrearMotivoReduccionTasaSpring.execute(mapping, motivoReduccionTasaForm, request,
					response);
			model.addAttribute(STRING_MOTIVOREDUCCIONTASAFORM, motivoReduccionTasaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MotivoReduccionTasaController - verCrearMotivoReduccionTasa:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar motivo reduccion tasa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param motivoReduccionTasaForm el motivo reduccion tasa form
	 * @return el string
	 */
	@RequestMapping("/verModificarMotivoReduccionTasa")
	public String verModificarMotivoReduccionTasa(Model model, HttpServletRequest request, HttpServletResponse response,
			MotivoReduccionTasaForm motivoReduccionTasaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarMotivoReduccionTasaSpring.class);
		try {
			VerModificarMotivoReduccionTasaSpring verModificarMotivoReduccionTasaSpring = new VerModificarMotivoReduccionTasaSpring();
			SpringForward rtrn = verModificarMotivoReduccionTasaSpring.execute(mapping, motivoReduccionTasaForm,
					request, response);
			model.addAttribute(STRING_MOTIVOREDUCCIONTASAFORM, motivoReduccionTasaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error MotivoReduccionTasaController - verModificarMotivoReduccionTasa:",e);
			throw new RuntimeException(e);
		}
	}
}
