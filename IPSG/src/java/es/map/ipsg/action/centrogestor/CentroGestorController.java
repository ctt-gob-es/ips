package es.map.ipsg.action.centrogestor;

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
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.form.PlantillaForm;

/**
 * El Class CentroGestorController.
 */
@Controller
@RequestMapping("/spring")
public class CentroGestorController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CentroGestorController.class);
	
	/** La constante STRING_CENTRO_GESTOR_FORM. */
	private static final String STRING_CENTRO_GESTOR_FORM = "centroGestorForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar centro gestor.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/buscarCentroGestor")
	public String buscarCentroGestor(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		centroGestorForm = formSessionMapper.resolveForm(centroGestorForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarCentroGestorSpring.class);
		try {
			BuscarCentroGestorSpring buscarCentroGestorSpring = new BuscarCentroGestorSpring();
			SpringForward rtrn = buscarCentroGestorSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Cerrar ejercicio.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/cerrarEjercicio")
	public String cerrarEjercicio(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), CerrarEjercicioSpring.class);
		try {
			CerrarEjercicioSpring cerrarEjercicioSpring = new CerrarEjercicioSpring();
			SpringForward rtrn = cerrarEjercicioSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - cerrarEjercicio",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear centro gestor.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/crearCentroGestor")
	public String crearCentroGestor(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearCentroGestorSpring.class);
		try {
			CrearCentroGestorSpring crearCentroGestorSpring = new CrearCentroGestorSpring();
			SpringForward rtrn = crearCentroGestorSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - crearCentroGestor",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar centro gestor.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/eliminarCentroGestor")
	public String eliminarCentroGestor(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarCentroGestorSpring.class);
		try {
			EliminarCentroGestorSpring eliminarCentroGestorSpring = new EliminarCentroGestorSpring();
			SpringForward rtrn = eliminarCentroGestorSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - eliminarCentroGestor",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Listar centro gestor.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/listarCentroGestor")
	public String listarCentroGestor(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		centroGestorForm = formSessionMapper.resolveForm(centroGestorForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ListarCentroGestorSpring.class);
		try {
			ListarCentroGestorSpring listarCentroGestorSpring = new ListarCentroGestorSpring();
			SpringForward rtrn = listarCentroGestorSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - listarCentroGestor",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar centro gestor.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/modificarCentroGestor")
	public String modificarCentroGestor(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarCentroGestorSpring.class);
		try {
			ModificarCentroGestorSpring modificarCentroGestorSpring = new ModificarCentroGestorSpring();
			SpringForward rtrn = modificarCentroGestorSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - modificarCentroGestor",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Reabrir ejercicio.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/reabrirEjercicio")
	public String reabrirEjercicio(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ReabrirEjercicioSpring.class);
		try {
			ReabrirEjercicioSpring reabrirEjercicioSpring = new ReabrirEjercicioSpring();
			SpringForward rtrn = reabrirEjercicioSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - reabrirEjercicio",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear centro gestor.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/verCrearCentroGestor")
	public String verCrearCentroGestor(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearCentroGestorSpring.class);
		try {
			VerCrearCentroGestorSpring verCrearCentroGestorSpring = new VerCrearCentroGestorSpring();
			SpringForward rtrn = verCrearCentroGestorSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - vercrearCentroGestor",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver formulario gestion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param plantillaForm el plantilla form
	 * @return el string
	 */
	@RequestMapping("/verFormularioGestion")
	public String verFormularioGestion(Model model, HttpServletRequest request, HttpServletResponse response,
			PlantillaForm plantillaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerFormularioGestionSpring.class);
		try {
			VerFormularioGestionSpring verFormularioGestionSpring = new VerFormularioGestionSpring();
			SpringForward rtrn = verFormularioGestionSpring.execute(mapping, plantillaForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, plantillaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - verFormularioGestion",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar centro gestor.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param centroGestorForm el centro gestor form
	 * @return el string
	 */
	@RequestMapping("/verModificarCentroGestor")
	public String verModificarCentroGestor(Model model, HttpServletRequest request, HttpServletResponse response,
			CentroGestorForm centroGestorForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarCentroGestorSpring.class);
		try {
			VerModificarCentroGestorSpring verModificarCentroGestorSpring = new VerModificarCentroGestorSpring();
			SpringForward rtrn = verModificarCentroGestorSpring.execute(mapping, centroGestorForm, request, response);
			model.addAttribute(STRING_CENTRO_GESTOR_FORM, centroGestorForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CentroGestorController - verModificarCentroGestor",e );
			throw new RuntimeException(e);
		}
	}
}
