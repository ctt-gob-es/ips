package es.map.ipsg.action.parametrosConfiguracion;

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
import es.map.ipsg.form.ParametrosConfiguracionForm;

/**
 * El Class ParametrosConfiguracionController.
 */
@Controller
@RequestMapping("/spring")
public class ParametrosConfiguracionController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ParametrosConfiguracionController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar parametros configuracion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param parametrosConfiguracionForm el parametros configuracion form
	 * @return el string
	 */
	@RequestMapping("/buscarParametrosConfiguracion")
	public String buscarParametrosConfiguracion(Model model, HttpServletRequest request, HttpServletResponse response,
			ParametrosConfiguracionForm parametrosConfiguracionForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		parametrosConfiguracionForm = formSessionMapper.resolveForm(parametrosConfiguracionForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarParametrosConfiguracionSpring.class);
		try {
			BuscarParametrosConfiguracionSpring buscarParametrosConfiguracionSpring = new BuscarParametrosConfiguracionSpring();
			SpringForward rtrn = buscarParametrosConfiguracionSpring.execute(mapping, parametrosConfiguracionForm,
					request, response);
			model.addAttribute("parametrosConfiguracionForm", parametrosConfiguracionForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ParametrosConfiguracionController - buscarParametrosConfiguracion :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar parametros configuracion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param parametrosConfiguracionForm el parametros configuracion form
	 * @return el string
	 */
	@RequestMapping("/modificarParametrosConfiguracion")
	public String modificarParametrosConfiguracion(Model model, HttpServletRequest request,
			HttpServletResponse response, ParametrosConfiguracionForm parametrosConfiguracionForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarParametrosConfiguracionSpring.class);
		try {
			ModificarParametrosConfiguracionSpring modificarParametrosConfiguracionSpring = new ModificarParametrosConfiguracionSpring();
			SpringForward rtrn = modificarParametrosConfiguracionSpring.execute(mapping, parametrosConfiguracionForm,
					request, response);
			model.addAttribute("parametrosConfiguracionForm", parametrosConfiguracionForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ParametrosConfiguracionController - modificarParametrosConfiguracion :",e);
			throw new RuntimeException(e);
		}
	}
}
