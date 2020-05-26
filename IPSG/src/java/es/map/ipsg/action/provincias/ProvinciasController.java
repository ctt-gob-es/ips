package es.map.ipsg.action.provincias;

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
import es.map.ipsg.form.BuscaProvinciasExamenForm;
import es.map.ipsg.form.BuscaProvinciasForm;
import es.map.ipsg.form.CrearProvinciasExamenForm;
import es.map.ipsg.form.CrearProvinciasForm;
import es.map.ipsg.form.ModificarProvinciasExamenForm;
import es.map.ipsg.form.ModificarProvinciasForm;

/**
 * El Class ProvinciasController.
 */
@Controller
@RequestMapping("/spring")
public class ProvinciasController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ProvinciasController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar provincias examen.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscaProvinciasExamenForm el busca provincias examen form
	 * @return el string
	 */
	@RequestMapping("/buscarProvinciasExamen")
	public String buscarProvinciasExamen(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaProvinciasExamenForm buscaProvinciasExamenForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscaProvinciasExamenForm = formSessionMapper.resolveForm(buscaProvinciasExamenForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarProvinciasExamenSpring.class);
		try {
			BuscarProvinciasExamenSpring buscarProvinciasExamenSpring = new BuscarProvinciasExamenSpring();
			SpringForward rtrn = buscarProvinciasExamenSpring.execute(mapping, buscaProvinciasExamenForm, request, response);
			model.addAttribute("buscaProvinciasExamenForm", buscaProvinciasExamenForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - buscarProvinciasExamen:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar provincias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscaProvinciasForm el busca provincias form
	 * @return el string
	 */
	@RequestMapping("/buscarProvincias")
	public String buscarProvincias(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaProvinciasForm buscaProvinciasForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscaProvinciasForm = formSessionMapper.resolveForm(buscaProvinciasForm, request);
		//Se borran los errores
		request.removeAttribute("org.apache.spring.ERROR");
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BuscarProvinciasSpring.class);
		try {
			BuscarProvinciasSpring buscarProvinciasSpring = new BuscarProvinciasSpring();
			SpringForward rtrn = buscarProvinciasSpring.execute(mapping, buscaProvinciasForm, request, response);
			model.addAttribute("buscaProvinciasForm", buscaProvinciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - buscarProvincias:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear provincia examen.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearProvinciasExamenForm el crear provincias examen form
	 * @return el string
	 */
	@RequestMapping("/crearProvinciaExamen")
	public String crearProvinciaExamen(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearProvinciasExamenForm crearProvinciasExamenForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearProvinciaExamenSpring.class);
		try {
			CrearProvinciaExamenSpring crearProvinciaExamenSpring = new CrearProvinciaExamenSpring();
			SpringForward rtrn = crearProvinciaExamenSpring.execute(mapping, crearProvinciasExamenForm, request, response);
			model.addAttribute("crearProvinciasExamenForm", crearProvinciasExamenForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - crearProvinciaExamen:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear provincia.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearProvinciasForm el crear provincias form
	 * @return el string
	 */
	@RequestMapping("/crearProvincia")
	public String crearProvincia(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearProvinciasForm crearProvinciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), CrearProvinciaSpring.class);
		try {
			CrearProvinciaSpring crearProvinciaSpring = new CrearProvinciaSpring();
			SpringForward rtrn = crearProvinciaSpring.execute(mapping, crearProvinciasForm, request, response);
			model.addAttribute("crearProvinciasForm", crearProvinciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - crearProvincia:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar provincias examen.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/eliminarProvinciaExamen")
	public String eliminarProvinciasExamen(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarProvinciasExamenSpring.class);
		try {
			EliminarProvinciasExamenSpring eliminarProvinciasExamenSpring = new EliminarProvinciasExamenSpring();
			SpringForward rtrn = eliminarProvinciasExamenSpring.execute(mapping, springForm, request, response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - eliminarProvinciaExamen:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar provincias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/eliminarProvincia")
	public String eliminarProvincias(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarProvinciasSpring.class);
		try {
			EliminarProvinciasSpring eliminarProvinciasSpring = new EliminarProvinciasSpring();
			SpringForward rtrn = eliminarProvinciasSpring.execute(mapping, springForm, request, response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - eliminarProvincia:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar provincia examen.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modificarProvinciasExamenForm el modificar provincias examen form
	 * @return el string
	 */
	@RequestMapping("/modificarProvinciaExamen")
	public String modificarProvinciaExamen(Model model, HttpServletRequest request, HttpServletResponse response,
			ModificarProvinciasExamenForm modificarProvinciasExamenForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarProvinciaExamenSpring.class);
		try {
			ModificarProvinciaExamenSpring modificarProvinciaExamenSpring = new ModificarProvinciaExamenSpring();
			SpringForward rtrn = modificarProvinciaExamenSpring.execute(mapping, modificarProvinciasExamenForm, request, response);
			model.addAttribute("modificarProvinciasExamenForm", modificarProvinciasExamenForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - modificarProvinciaExamen:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar provincia.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modificarProvinciasForm el modificar provincias form
	 * @return el string
	 */
	@RequestMapping("/modificarProvincia")
	public String modificarProvincia(Model model, HttpServletRequest request, HttpServletResponse response,
			ModificarProvinciasForm modificarProvinciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarProvinciaSpring.class);
		try {
			ModificarProvinciaSpring modificarProvinciaSpring = new ModificarProvinciaSpring();
			SpringForward rtrn = modificarProvinciaSpring.execute(mapping, modificarProvinciasForm, request, response);
			model.addAttribute("modificarProvinciasForm", modificarProvinciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - modificarProvincia:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar provincia examen.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modificarProvinciasExamenForm el modificar provincias examen form
	 * @return el string
	 */
	@RequestMapping("/verModificarProvinciaExamen")
	public String verModificarProvinciaExamen(Model model, HttpServletRequest request, HttpServletResponse response,
			ModificarProvinciasExamenForm modificarProvinciasExamenForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarProvinciaExamenSpring.class);
		try {
			VerModificarProvinciaExamenSpring verModificarProvinciaExamenSpring = new VerModificarProvinciaExamenSpring();
			SpringForward rtrn = verModificarProvinciaExamenSpring.execute(mapping, modificarProvinciasExamenForm, request, response);
			model.addAttribute("modificarProvinciasExamenForm", modificarProvinciasExamenForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - verModificarProvinciaExamen:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar provincia.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modificarProvinciasForm el modificar provincias form
	 * @return el string
	 */
	@RequestMapping("/verModificarProvincia")
	public String verModificarProvincia(Model model, HttpServletRequest request, HttpServletResponse response,
			ModificarProvinciasForm modificarProvinciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarProvinciaSpring.class);
		try {
			VerModificarProvinciaSpring verModificarProvinciaSpring = new VerModificarProvinciaSpring();
			SpringForward rtrn = verModificarProvinciaSpring.execute(mapping, modificarProvinciasForm, request, response);
			model.addAttribute("modificarProvinciasForm", modificarProvinciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ProvinciasController - verModificarProvincia:",e);
			throw new RuntimeException(e);
		}
	}
}
