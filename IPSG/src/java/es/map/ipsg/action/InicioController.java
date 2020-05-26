package es.map.ipsg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.form.ValidarUsuarioSpringForm;

/**
 * El Class InicioController.
 */
@Controller
@RequestMapping("/spring/login")
public class InicioController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger( InicioController.class);
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/**
	 * Iniciar.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @return el string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String iniciar(Model model, HttpServletRequest request, HttpServletResponse response) {
		SpringForm springForm = new SpringForm();
		SpringMapping mapping = springMappingManager.getMapping("/springPub/inicio", InicioSpring.class);
		SpringForward rtrn = new SpringForward();
		try {
			InicioSpring inicioSpring = new InicioSpring();
			
			//Carga sesion usuario login normal
			comprobarUsuarioLogin(request);
			
			rtrn = inicioSpring.execute(mapping, springForm, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en  InicioController metodo iniciar:", e);
		}
		model.addAttribute(springForm);
		return rtrn.getPath();
	}
	
	/**
	 * Denie.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @return el string
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String denie(Model model, HttpServletRequest request, HttpServletResponse response) {
		SpringForm springForm = new SpringForm();
		SpringMapping mapping = springMappingManager.getMapping("/spring/denied", InicioSpring.class);
		SpringForward rtrn = new SpringForward();
		try {
			InicioSpring inicioSpring = new InicioSpring();
			rtrn = inicioSpring.execute(mapping, springForm, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en  InicioController metodo iniciar:", e);
		}
//		model.addAttribute(springForm);
		return rtrn.getPath();
	}
	
	/**
	 * Comprueba usuario.
	 *
	 * @param validarUsuarioSpringForm el validar usuario spring form
	 * @param request el request
	 * @param response el response
	 * @return el string
	 */
	@RequestMapping(params={"compruebaUsuario"}, method = RequestMethod.POST)
	public String compruebaUsuario(ValidarUsuarioSpringForm validarUsuarioSpringForm, HttpServletRequest request, HttpServletResponse response) {
		SpringMapping mapping = springMappingManager.getMapping("/springPub/compruebaUsuario", CompruebaUsuarioSpring.class);
		SpringForward rtrn = new SpringForward();
		try {
			CompruebaUsuarioSpring compruebaUsuarioSpring = new CompruebaUsuarioSpring();
			
			rtrn = compruebaUsuarioSpring.execute(mapping, validarUsuarioSpringForm, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en  InicioController metodo compruebaUsuario:", e);
		}		
		
		return rtrn.getPath();		
	}
	
	/**
	 * Metodo que carga en sesion el usuario del login por usurio y password.
	 *
	 * @param request el request
	 */
	private void comprobarUsuarioLogin(HttpServletRequest request) {
		String nif = (String)request.getSession().getAttribute("NIF");
		request.getSession().setAttribute("NIF", null);
		
		String login = (String)request.getSession().getAttribute("LOGIN");
		request.getSession().setAttribute("LOGIN", null);
		
		if (!StringUtils.isEmpty(nif) && !StringUtils.isEmpty(login)) {
			AutenticaAuthenticationToken authRequest = new AutenticaAuthenticationToken(nif, login, true);
			
			AuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

			ApplicationContextProvider applicationContext = ApplicationContextProvider.getInstance();
			AuthenticationManager delegate = (AuthenticationManager) applicationContext.getBean(BeanIds.AUTHENTICATION_MANAGER);
			
		    Authentication authentication = delegate.authenticate(authRequest);
		    SecurityContext securityContext = SecurityContextHolder.getContext();
		    securityContext.setAuthentication(authentication);
		    HttpSession session = request.getSession();
		    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		}
	}
}
