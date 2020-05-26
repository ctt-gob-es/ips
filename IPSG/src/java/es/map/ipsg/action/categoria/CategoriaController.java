package es.map.ipsg.action.categoria;

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
import es.map.ipsg.form.CategoriaForm;

/**
 * El Class CategoriaController.
 */
@Controller
@RequestMapping("/spring")
public class CategoriaController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CategoriaController.class);
	
	/** La constante STRING_CATEGORIA_FORM. */
	private static final String STRING_CATEGORIA_FORM = "categoriaForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar categoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param categoriaForm el categoria form
	 * @return el string
	 */
	@RequestMapping("/buscarCategoria")
	public String buscarCategoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoriaForm categoriaForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		categoriaForm = formSessionMapper.resolveForm(categoriaForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BuscarCategoriaSpring.class);
		try {
			BuscarCategoriaSpring buscarCategoriaSpring = new BuscarCategoriaSpring();
			SpringForward rtrn = buscarCategoriaSpring.execute(mapping, categoriaForm, request, response);
			model.addAttribute(STRING_CATEGORIA_FORM, categoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error buscarCategoria:",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear categoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param categoriaForm el categoria form
	 * @return el string
	 */
	@RequestMapping("/crearCategoria")
	public String crearCategoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoriaForm categoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), CrearCategoriaSpring.class);
		try {
			CrearCategoriaSpring crearCategoriaSpring = new CrearCategoriaSpring();
			SpringForward rtrn = crearCategoriaSpring.execute(mapping, categoriaForm, request, response);
			model.addAttribute(STRING_CATEGORIA_FORM, categoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error crearCategoria:",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar categoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param categoriaForm el categoria form
	 * @return el string
	 */
	@RequestMapping("/eliminarCategoria")
	public String eliminarCategoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoriaForm categoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarCategoriaSpring.class);
		try {
			EliminarCategoriaSpring eliminarCategoriaSpring = new EliminarCategoriaSpring();
			SpringForward rtrn = eliminarCategoriaSpring.execute(mapping, categoriaForm, request, response);
			model.addAttribute(STRING_CATEGORIA_FORM, categoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error eliminarCategoria:",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar categoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param categoriaForm el categoria form
	 * @return el string
	 */
	@RequestMapping("/modificarCategoria")
	public String modificarCategoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoriaForm categoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarCategoriaSpring.class);
		try {
			ModificarCategoriaSpring modificarCategoriaSpring = new ModificarCategoriaSpring();
			SpringForward rtrn = modificarCategoriaSpring.execute(mapping, categoriaForm, request, response);
			model.addAttribute(STRING_CATEGORIA_FORM, categoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error modificarCategoria:",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear categoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param categoriaForm el categoria form
	 * @return el string
	 */
	@RequestMapping("/verCrearCategoria")
	public String verCrearCategoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoriaForm categoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearCategoriaSpring.class);
		try {
			VerCrearCategoriaSpring verCrearCategoriaSpring = new VerCrearCategoriaSpring();
			SpringForward rtrn = verCrearCategoriaSpring.execute(mapping, categoriaForm, request, response);
			model.addAttribute(STRING_CATEGORIA_FORM, categoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error verCrearCategoria:",e );
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar categoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param categoriaForm el categoria form
	 * @return el string
	 */
	@RequestMapping("/verModificarCategoria")
	public String verModificarCategoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoriaForm categoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarCategoriaSpring.class);
		try {
			VerModificarCategoriaSpring verModificarCategoriaSpring = new VerModificarCategoriaSpring();
			SpringForward rtrn = verModificarCategoriaSpring.execute(mapping, categoriaForm, request, response);
			model.addAttribute(STRING_CATEGORIA_FORM, categoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error verModificarCategoria:",e );
			throw new RuntimeException(e);
		}
	}
}
