package es.map.ipsg.action.serviciosExternos;

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
import es.map.ipsg.form.ConsultaServiciosExternosForm;
import es.map.ipsg.form.ProbarServiciosExternosForm;

/**
 * El Class ServiciosExternosController.
 */
@Controller
@RequestMapping("/spring")
public class ServiciosExternosController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ServiciosExternosController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Consultar resultados servicios externos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultaServiciosExternosForm el consulta servicios externos form
	 * @return el string
	 */
	@RequestMapping("/consultarResultadosServiciosExternos")
	public String consultarResultadosServiciosExternos(Model model, HttpServletRequest request,
			HttpServletResponse response, ConsultaServiciosExternosForm consultaServiciosExternosForm) {
		
			
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ConsultarResultadosServiciosExternosSpring.class);
		try {
			ConsultarResultadosServiciosExternosSpring consultarResultadosServiciosExternosSpring = new ConsultarResultadosServiciosExternosSpring();
			SpringForward rtrn = consultarResultadosServiciosExternosSpring.execute(mapping, consultaServiciosExternosForm,
					request, response);
			model.addAttribute("consultaServiciosExternosForm", consultaServiciosExternosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ServiciosExternosController - consultarResultadosServiciosExternos :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Probar servicios externos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param probarServiciosExternosForm el probar servicios externos form
	 * @return el string
	 */
	@RequestMapping("/probarServiciosExternos")
	public String probarServiciosExternos(Model model, HttpServletRequest request, HttpServletResponse response,
			ProbarServiciosExternosForm probarServiciosExternosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ProbarServiciosExternosSpring.class);
		try {
			ProbarServiciosExternosSpring probarServiciosExternosSpring = new ProbarServiciosExternosSpring();
			SpringForward rtrn = probarServiciosExternosSpring.execute(mapping, probarServiciosExternosForm, request,
					response);
			model.addAttribute("probarServiciosExternosForm", probarServiciosExternosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ServiciosExternosController - probarServiciosExternos :",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Ver consultar resultados servicios externos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultaServiciosExternosForm el consulta servicios externos form
	 * @return el string
	 */
	@RequestMapping("/verConsultarResultadosServiciosExternos")
	public String verConsultarResultadosServiciosExternos(Model model, HttpServletRequest request, HttpServletResponse response,
			ConsultaServiciosExternosForm consultaServiciosExternosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerConsultarResultadosServiciosExternosSpring.class);
		try {
			VerConsultarResultadosServiciosExternosSpring verConsultarResultadosServiciosExternosSpring = new VerConsultarResultadosServiciosExternosSpring();
			SpringForward rtrn = verConsultarResultadosServiciosExternosSpring.execute(mapping, consultaServiciosExternosForm, request, response);
			model.addAttribute("consultaServiciosExternosForm", consultaServiciosExternosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error LogsController - verLogOperaciones:",e);
			throw new RuntimeException(e);
		}
	}
}
