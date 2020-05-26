package es.map.ipsg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;


/**
 * El Class OtherController2.
 */
@Controller
@RequestMapping("/spring")
public class OtherController2 {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger( OtherController2.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/**
	 * Ver alertas.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @return el string
	 */
	@RequestMapping("/verAlertas")
	public String verAlertas(Model model, HttpServletRequest request, HttpServletResponse response) {
		SpringForm springForm = new SpringForm();
		SpringMapping mapping = springMappingManager.getMapping("/spring/verAlertas", VerAlertaPopUpSpring.class);
		SpringForward rtrn = new SpringForward();
		try {
			VerAlertaPopUpSpring verAlertaPopUpSpring = new VerAlertaPopUpSpring();
			rtrn = verAlertaPopUpSpring.execute(mapping, springForm, request, response);
		} catch (Exception e) {
			logger.error("Error en  InicioController metodo iniciar:", e);
		}
		model.addAttribute(springForm);
		return rtrn.getPath();	
	}
}
