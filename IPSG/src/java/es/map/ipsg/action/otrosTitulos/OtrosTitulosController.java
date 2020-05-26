package es.map.ipsg.action.otrosTitulos;

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
import es.map.ipsg.form.OtrosTitulosForm;

/**
 * El Class OtrosTitulosController.
 */
@Controller
@RequestMapping("/spring")
public class OtrosTitulosController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(OtrosTitulosController.class);
	
	/** La constante STRINGOTROSTITULOSFORM. */
	private static final String STRINGOTROSTITULOSFORM = "otrosTitulosForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Alta otros titulos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param otrosTitulosForm el otros titulos form
	 * @return el string
	 */
	@RequestMapping("/altaOtrosTitulos")
	public String altaOtrosTitulos(Model model, HttpServletRequest request, HttpServletResponse response,
			OtrosTitulosForm otrosTitulosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				AltaOtrosTitulosSpring.class);
		try {
			AltaOtrosTitulosSpring altaOtrosTitulosSpring = new AltaOtrosTitulosSpring();
			SpringForward rtrn = altaOtrosTitulosSpring.execute(mapping, otrosTitulosForm, request, response);
			model.addAttribute(STRINGOTROSTITULOSFORM, otrosTitulosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-altaOtrosTitulos:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar otros titulos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param otrosTitulosForm el otros titulos form
	 * @return el string
	 */
	@RequestMapping("/buscarOtrosTitulos")
	public String buscarOtrosTitulos(Model model, HttpServletRequest request, HttpServletResponse response,
			OtrosTitulosForm otrosTitulosForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		otrosTitulosForm = formSessionMapper.resolveForm(otrosTitulosForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarOtrosTitulosSpring.class);
		try {
			BuscarOtrosTitulosSpring buscarOtrosTitulosSpring = new BuscarOtrosTitulosSpring();
			SpringForward rtrn = buscarOtrosTitulosSpring.execute(mapping, otrosTitulosForm, request, response);
			model.addAttribute(STRINGOTROSTITULOSFORM, otrosTitulosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-buscarOtrosTitulos:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar otros titulos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param otrosTitulosForm el otros titulos form
	 * @return el string
	 */
	@RequestMapping("/eliminarOtrosTitulos")
	public String eliminarOtrosTitulos(Model model, HttpServletRequest request, HttpServletResponse response,
			OtrosTitulosForm otrosTitulosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarOtrosTitulosSpring.class);
		try {
			EliminarOtrosTitulosSpring eliminarOtrosTitulosSpring = new EliminarOtrosTitulosSpring();
			SpringForward rtrn = eliminarOtrosTitulosSpring.execute(mapping, otrosTitulosForm, request, response);
			model.addAttribute(STRINGOTROSTITULOSFORM, otrosTitulosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-eliminarOtrosTitulos:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar otros titulos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param otrosTitulosForm el otros titulos form
	 * @return el string
	 */
	@RequestMapping("/modificarOtrosTitulos")
	public String modificarOtrosTitulos(Model model, HttpServletRequest request, HttpServletResponse response,
			OtrosTitulosForm otrosTitulosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarOtrosTitulosSpring.class);
		try {
			ModificarOtrosTitulosSpring modificarOtrosTitulosSpring = new ModificarOtrosTitulosSpring();
			SpringForward rtrn = modificarOtrosTitulosSpring.execute(mapping, otrosTitulosForm, request, response);
			model.addAttribute(STRINGOTROSTITULOSFORM, otrosTitulosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-modificarOtrosTitulos:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta otros titulos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param otrosTitulosForm el otros titulos form
	 * @return el string
	 */
	@RequestMapping("/verAltaOtrosTitulos")
	public String verAltaOtrosTitulos(Model model, HttpServletRequest request, HttpServletResponse response,
			OtrosTitulosForm otrosTitulosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerAltaOtrosTitulosSpring.class);
		try {
			VerAltaOtrosTitulosSpring verAltaOtrosTitulosSpring = new VerAltaOtrosTitulosSpring();
			SpringForward rtrn = verAltaOtrosTitulosSpring.execute(mapping, otrosTitulosForm, request, response);
			model.addAttribute(STRINGOTROSTITULOSFORM, otrosTitulosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController-verAltaOtrosTitulos:",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar otros titulos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param otrosTitulosForm el otros titulos form
	 * @return el string
	 */
	@RequestMapping("/verModificarOtrosTitulos")
	public String verModificarOtrosTitulos(Model model, HttpServletRequest request, HttpServletResponse response,
			OtrosTitulosForm otrosTitulosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarOtrosTitulosSpring.class);
		try {
			VerModificarOtrosTitulosSpring verModificarOtrosTitulosSpring = new VerModificarOtrosTitulosSpring();
			SpringForward rtrn = verModificarOtrosTitulosSpring.execute(mapping, otrosTitulosForm, request, response);
			model.addAttribute(STRINGOTROSTITULOSFORM, otrosTitulosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error CuerpoEscalaController- verModificarOtrosTitulos:",e);
			throw new RuntimeException(e);
		}
	}
}
