package es.map.ipsg.action.entidadFinanciera;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.FormSessionMapper;
import es.map.ipsg.form.BuscarEntidadFinancieraForm;
import es.map.ipsg.form.CrearEntidadFinancieraForm;
import es.map.ipsg.form.ModificarEntidadFinancieraForm;

/**
 * El Class EntidadFinancieraController.
 */
@Controller
@RequestMapping("/spring")
public class EntidadFinancieraController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EntidadFinancieraController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Actualizar entidad financiera.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param SpringForm el spring form
	 * @return el string
	 */
	@RequestMapping("/actualizarEntidadFinanciera")
	public String actualizarEntidadFinanciera(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm SpringForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarEntidadFinancieraSpring.class);
		try {
			ActualizarEntidadFinancieraSpring actualizarEntidadFinancieraSpring = new ActualizarEntidadFinancieraSpring();
			SpringForward rtrn = actualizarEntidadFinancieraSpring.execute(mapping, SpringForm, request,
					response);
			model.addAttribute("SpringForm", SpringForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EntidadFinancieraController - actualizarEntidadFinanciera  :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar entidad financiera.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarEntidadFinancieraForm el buscar entidad financiera form
	 * @return el string
	 */
	@RequestMapping("/buscarEntidadFinanciera")
	public String buscarEntidadFinanciera(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarEntidadFinancieraForm buscarEntidadFinancieraForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarEntidadFinancieraForm = formSessionMapper.resolveForm(buscarEntidadFinancieraForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarEntidadFinancieraSpring.class);
		try {
			BuscarEntidadFinancieraSpring buscarEntidadFinancieraSpring = new BuscarEntidadFinancieraSpring();
			SpringForward rtrn = buscarEntidadFinancieraSpring.execute(mapping, buscarEntidadFinancieraForm, request,
					response);
			model.addAttribute("buscarEntidadFinancieraForm", buscarEntidadFinancieraForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EntidadFinancieraController - buscarEntidadFinanciera :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear entidad financiera.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearEntidadFinancieraForm el crear entidad financiera form
	 * @return el string
	 */
	@RequestMapping("/crearEntidadFinanciera")
	public String crearEntidadFinanciera(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearEntidadFinancieraForm crearEntidadFinancieraForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearEntidadFinancieraSpring.class);
		try {
			CrearEntidadFinancieraSpring crearEntidadFinancieraSpring = new CrearEntidadFinancieraSpring();
			SpringForward rtrn = crearEntidadFinancieraSpring.execute(mapping, crearEntidadFinancieraForm, request,
					response);
			model.addAttribute("crearEntidadFinancieraForm", crearEntidadFinancieraForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EntidadFinancieraController - crearEntidadFinanciera :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar entidad financiera.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/eliminarEntidadFinanciera")
	public String eliminarEntidadFinanciera(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarEntidadFinancieraSpring.class);
		try {
			EliminarEntidadFinancieraSpring eliminarEntidadFinancieraSpring = new EliminarEntidadFinancieraSpring();
			SpringForward rtrn = eliminarEntidadFinancieraSpring.execute(mapping, springForm, request,
					response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EntidadFinancieraController - eliminarEntidadFinanciera :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar entidad financiera.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modificarEntidadFinancieraForm el modificar entidad financiera form
	 * @return el string
	 */
	@RequestMapping("/modificarEntidadFinanciera")
	public String modificarEntidadFinanciera(Model model, HttpServletRequest request, HttpServletResponse response,
			ModificarEntidadFinancieraForm modificarEntidadFinancieraForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarEntidadFinancieraSpring.class);
		try {
			ModificarEntidadFinancieraSpring modificarEntidadFinancieraSpring = new ModificarEntidadFinancieraSpring();
			SpringForward rtrn = modificarEntidadFinancieraSpring.execute(mapping, modificarEntidadFinancieraForm, request,
					response);
			model.addAttribute("modificarEntidadFinancieraForm", modificarEntidadFinancieraForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EntidadFinancieraController - modificarEntidadFinanciera :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear entidad financiera.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verCrearEntidadFinanciera")
	public String verCrearEntidadFinanciera(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearEntidadFinancieraForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearEntidadFinancieraSpring.class);
		try {
			VerCrearEntidadFinancieraSpring verCrearEntidadFinancieraSpring = new VerCrearEntidadFinancieraSpring();
			SpringForward rtrn = verCrearEntidadFinancieraSpring.execute(mapping, springForm, request,
					response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EntidadFinancieraController - verCrearEntidadFinanciera :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar entidad financiera.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modificarEntidadFinancieraForm el modificar entidad financiera form
	 * @return el string
	 */
	@RequestMapping("/verModificarEntidadFinanciera")
	public String verModificarEntidadFinanciera(Model model, HttpServletRequest request, HttpServletResponse response,
			ModificarEntidadFinancieraForm modificarEntidadFinancieraForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarEntidadFinancieraSpring.class);
		try {
			VerModificarEntidadFinancieraSpring verModificarEntidadFinancieraSpring = new VerModificarEntidadFinancieraSpring();
			SpringForward rtrn = verModificarEntidadFinancieraSpring.execute(mapping, modificarEntidadFinancieraForm, request,
					response);
			model.addAttribute("modificarEntidadFinancieraForm", modificarEntidadFinancieraForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error EntidadFinancieraController - verModificarEntidadFinanciera :",e);
			throw new RuntimeException(e);
		}
	}
}
