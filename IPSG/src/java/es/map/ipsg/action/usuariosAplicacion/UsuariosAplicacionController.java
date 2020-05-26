package es.map.ipsg.action.usuariosAplicacion;

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
import es.map.ipsg.action.cuerpoescala.CuerpoEscalaController;
import es.map.ipsg.action.usuarios.EliminarUsuarioSpring;
import es.map.ipsg.form.BuscarUsuariosAplicacionForm;
import es.map.ipsg.form.UsuariosAplicacionForm;

/**
 * El Class UsuariosAplicacionController.
 */
@Controller
@RequestMapping("/spring")
public class UsuariosAplicacionController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CuerpoEscalaController.class);
	
	/** La constante STRINGUSUARIOAPLICACIONFORM. */
	private static final String STRINGUSUARIOAPLICACIONFORM = "usuariosAplicacionForm";
	
	/** La constante STRINGBUSCARUSUARIOAPLICACIONFORM. */
	private static final String STRINGBUSCARUSUARIOAPLICACIONFORM  = "buscarUsuariosAplicacionForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;
	
	/**
	 * Buscar usuarios aplicacion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuariosAplicacionForm el usuarios aplicacion form
	 * @return el string
	 */
	@RequestMapping("/buscarUsuariosAplicacion")
	public String buscarUsuariosAplicacion(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarUsuariosAplicacionForm usuariosAplicacionForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		usuariosAplicacionForm = formSessionMapper.resolveForm(usuariosAplicacionForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarUsuariosAplicacionSpring.class);
		try {
			BuscarUsuariosAplicacionSpring buscarOtrosUsuariosSpring = new BuscarUsuariosAplicacionSpring();
			SpringForward rtrn = buscarOtrosUsuariosSpring.execute(mapping, usuariosAplicacionForm, request, response);
			model.addAttribute(STRINGBUSCARUSUARIOAPLICACIONFORM, usuariosAplicacionForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosAplicacionController-buscarUsuariosAplicacion:",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Modificar usuario aplicacion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuariosAplicacionForm el usuarios aplicacion form
	 * @return el string
	 */
	@RequestMapping("/modificarUsuarioAplicacion")
	public String modificarUsuarioAplicacion(Model model, HttpServletRequest request, HttpServletResponse response,
			UsuariosAplicacionForm usuariosAplicacionForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ModificarUsuarioAplicacionSpring.class);
		try {
			ModificarUsuarioAplicacionSpring modificarUsuarioAplicacionSpring = new ModificarUsuarioAplicacionSpring();
			SpringForward rtrn = modificarUsuarioAplicacionSpring.execute(mapping, usuariosAplicacionForm, request, response);
			model.addAttribute(STRINGUSUARIOAPLICACIONFORM, usuariosAplicacionForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosAplicacionController - modificarUsuarioAplicacion",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Ver modificar usuario aplicacion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuariosAplicacionForm el usuarios aplicacion form
	 * @return el string
	 */
	@RequestMapping("/verModificarUsuarioAplicacion")
	public String verModificarUsuarioAplicacion(Model model, HttpServletRequest request, HttpServletResponse response,
			UsuariosAplicacionForm usuariosAplicacionForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarUsuarioAplicacionSpring.class);
		try {
			VerModificarUsuarioAplicacionSpring verModificarUsuarioAplicacionSpring = new VerModificarUsuarioAplicacionSpring();
			SpringForward rtrn = verModificarUsuarioAplicacionSpring.execute(mapping, usuariosAplicacionForm, request, response);
			model.addAttribute(STRINGUSUARIOAPLICACIONFORM, usuariosAplicacionForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosAplicacionController - verModificarUsuarioAplicacion",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Ver alta usuario aplicacion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuariosAplicacionForm el usuarios aplicacion form
	 * @return el string
	 */
	@RequestMapping("/verAltaUsuarioAplicacion")
	public String verAltaUsuarioAplicacion(Model model, HttpServletRequest request, HttpServletResponse response,
			UsuariosAplicacionForm usuariosAplicacionForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerAltaUsuarioAplicacionSpring.class);
		try {
			VerAltaUsuarioAplicacionSpring verAltaUsuarioAplicacionSpring = new VerAltaUsuarioAplicacionSpring();
			SpringForward rtrn = verAltaUsuarioAplicacionSpring.execute(mapping, usuariosAplicacionForm, request, response);
			model.addAttribute(STRINGUSUARIOAPLICACIONFORM, usuariosAplicacionForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosAplicacionController - verAltaUsuarioAplicacion",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Alta usuario aplicacion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuariosAplicacionForm el usuarios aplicacion form
	 * @return el string
	 */
	@RequestMapping("/altaUsuarioAplicacion")
	public String altaUsuarioAplicacion(Model model, HttpServletRequest request, HttpServletResponse response,
			UsuariosAplicacionForm usuariosAplicacionForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), AltaUsuarioAplicacionSpring.class);
		try {
			AltaUsuarioAplicacionSpring altaUsuarioAplicacionSpring = new AltaUsuarioAplicacionSpring();
			SpringForward rtrn = altaUsuarioAplicacionSpring.execute(mapping, usuariosAplicacionForm, request, response);
			model.addAttribute(STRINGUSUARIOAPLICACIONFORM, usuariosAplicacionForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosAplicacionController - altaUsuarioAplicacion",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Eliminar usuario aplicacion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/eliminarUsuarioAplicacion")
	public String eliminarUsuarioAplicacion(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), EliminarUsuarioAplicacionSpring.class);
		try {
			EliminarUsuarioAplicacionSpring eliminarUsuarioAplicacionSpring = new EliminarUsuarioAplicacionSpring();
			SpringForward rtrn = eliminarUsuarioAplicacionSpring.execute(mapping, springForm, request, response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosAplicacionController - eliminarUsuarioAplicacion",e);
			throw new RuntimeException(e);
		}
	}
	
}
