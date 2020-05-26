package es.map.ipsg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ipsg.form.ValidarUsuarioSpringForm;


/**
 * El Class OtherController.
 */
@Controller
@RequestMapping("/springPub")
public class OtherController {
	
	/**
	 * Acceso denegado.
	 *
	 * @param validarUsuarioSpringForm el validar usuario spring form
	 * @param request el request
	 * @param response el response
	 * @return el string
	 */
	@RequestMapping("/accesoDenegado")
	public String accesoDenegado(ValidarUsuarioSpringForm validarUsuarioSpringForm, HttpServletRequest request, HttpServletResponse response) {

		return "pages.accesoDenegado";		
	}
}
