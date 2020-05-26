package es.map.ipsg.action.usuarios;

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
import es.map.ipsg.form.ActualizarContrasenaForm;
import es.map.ipsg.form.BuscarUsuariosForm;
import es.map.ipsg.form.CambiarContrasenaForm;
import es.map.ipsg.form.CambiarContrasenaUsuariosForm;
import es.map.ipsg.form.UsuarioForm;

/**
 * El Class UsuariosController.
 */
@Controller
@RequestMapping("/spring")
public class UsuariosController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(UsuariosController.class);
	
	/** La constante STRING_USUARIOFORM. */
	private static final String STRING_USUARIOFORM = "usuarioForm";
	
	/** La constante STRING_BUSCARUSUARIOSFORM. */
	private static final String STRING_BUSCARUSUARIOSFORM = "buscarUsuariosForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Ver cambiar contrasena usuarios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cambiarContrasenaUsuariosForm el cambiar contrasena usuarios form
	 * @return el string
	 */
	@RequestMapping("/verCambiarContrasenaUsuarios")
	public String verCambiarContrasenaUsuarios(Model model, HttpServletRequest request, HttpServletResponse response,
			CambiarContrasenaUsuariosForm cambiarContrasenaUsuariosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarContrasenaSpring.class);
		try {
			SpringForward rtrn = mapping.findForward("success");
			model.addAttribute("cambiarContrasenaUsuariosForm", cambiarContrasenaUsuariosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - verCambiarContrasenaUsuarios",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Actualizar contrasena.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param actualizarContrasenaForm el actualizar contrasena form
	 * @return el string
	 */
	@RequestMapping("/actualizarContrasena")
	public String actualizarContrasena(Model model, HttpServletRequest request, HttpServletResponse response,
			ActualizarContrasenaForm actualizarContrasenaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarContrasenaSpring.class);
		try {
			ActualizarContrasenaSpring actualizarContrasenaSpring = new ActualizarContrasenaSpring();
			SpringForward rtrn = actualizarContrasenaSpring.execute(mapping, actualizarContrasenaForm, request, response);
			model.addAttribute("actualizarContrasenaForm", actualizarContrasenaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - actualizarContrasena",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Alta usuario.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuarioForm el usuario form
	 * @return el string
	 */
	@RequestMapping("/altaUsuario")
	public String altaUsuario(Model model, HttpServletRequest request, HttpServletResponse response,
			UsuarioForm usuarioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), AltaUsuarioSpring.class);
		try {
			AltaUsuarioSpring altaUsuarioSpring = new AltaUsuarioSpring();
			SpringForward rtrn = altaUsuarioSpring.execute(mapping, usuarioForm, request, response);
			model.addAttribute(STRING_USUARIOFORM, usuarioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - altaUsuario",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar usuarios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarUsuariosForm el buscar usuarios form
	 * @return el string
	 */
	@RequestMapping("/buscarUsuarios")
	public String buscarUsuarios(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarUsuariosForm buscarUsuariosForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarUsuariosForm = formSessionMapper.resolveForm(buscarUsuariosForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BuscarUsuariosSpring.class);
		try {
			BuscarUsuariosSpring buscarUsuariosSpring = new BuscarUsuariosSpring();
			SpringForward rtrn = buscarUsuariosSpring.execute(mapping, buscarUsuariosForm, request, response);
			model.addAttribute("buscarUsuariosForm", buscarUsuariosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - buscarUsuarios",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Exportar excel usuarios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarUsuariosForm el buscar usuarios form
	 * @return el string
	 */
	@RequestMapping("/exportarExcelUsuarios")
	public String exportarExcelUsuarios(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarUsuariosForm buscarUsuariosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ExportarExcelUsuariosSpring.class);
		try {
			ExportarExcelUsuariosSpring exportarExcelUsuariosSpring = new ExportarExcelUsuariosSpring();
			SpringForward rtrn = exportarExcelUsuariosSpring.execute(mapping, buscarUsuariosForm, request, response);
			model.addAttribute(STRING_BUSCARUSUARIOSFORM, buscarUsuariosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error UsuariosController - exportarExcelUsuarios : ",e);
			throw new RuntimeException(e);
		}
	}


	/**
	 * Cambiar contrasena.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cambiarContrasenaForm el cambiar contrasena form
	 * @return el string
	 */
	@RequestMapping("/cambiarContrasena")
	public String cambiarContrasena(Model model, HttpServletRequest request, HttpServletResponse response,
			CambiarContrasenaForm cambiarContrasenaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CambiarContrasenaSpring.class);
		try {
			CambiarContrasenaSpring cambiarContrasenaSpring = new CambiarContrasenaSpring();
			SpringForward rtrn = cambiarContrasenaSpring.execute(mapping, cambiarContrasenaForm, request, response);
			model.addAttribute("cambiarContrasenaForm", cambiarContrasenaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - cambiarContrasena",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Cambiar contrasena usuarios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cambiarContrasenaUsuariosForm el cambiar contrasena usuarios form
	 * @return el string
	 */
	@RequestMapping("/cambiarContrasenaUsuarios")
	public String cambiarContrasenaUsuarios(Model model, HttpServletRequest request, HttpServletResponse response,
			CambiarContrasenaUsuariosForm cambiarContrasenaUsuariosForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CambiarContrasenaUsuariosSpring.class);
		try {
			CambiarContrasenaUsuariosSpring cambiarContrasenaUsuariosSpring = new CambiarContrasenaUsuariosSpring();
			SpringForward rtrn = cambiarContrasenaUsuariosSpring.execute(mapping, cambiarContrasenaUsuariosForm, request, response);
			model.addAttribute("cambiarContrasenaUsuariosForm", cambiarContrasenaUsuariosForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - cambiarContrasenaUsuarios",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar usuario.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/eliminarUsuario")
	public String eliminarUsuario(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), EliminarUsuarioSpring.class);
		try {
			EliminarUsuarioSpring eliminarUsuarioSpring = new EliminarUsuarioSpring();
			SpringForward rtrn = eliminarUsuarioSpring.execute(mapping, springForm, request, response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - eliminarUsuario",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar usuario.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuarioForm el usuario form
	 * @return el string
	 */
	@RequestMapping("/modificarUsuario")
	public String modificarUsuario(Model model, HttpServletRequest request, HttpServletResponse response,
			UsuarioForm usuarioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ModificarUsuarioSpring.class);
		try {
			ModificarUsuarioSpring modificarUsuarioSpring = new ModificarUsuarioSpring();
			mapping.setValidate(true);
			SpringForward rtrn = modificarUsuarioSpring.execute(mapping, usuarioForm, request, response);
			model.addAttribute(STRING_USUARIOFORM, usuarioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - modificarUsuario",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta usuario.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuarioForm el usuario form
	 * @return el string
	 */
	@RequestMapping("/verAltaUsuario")
	public String verAltaUsuario(Model model, HttpServletRequest request, HttpServletResponse response,
			UsuarioForm usuarioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerAltaUsuarioSpring.class);
		try {
			VerAltaUsuarioSpring verAltaUsuarioSpring = new VerAltaUsuarioSpring();
			SpringForward rtrn = verAltaUsuarioSpring.execute(mapping, usuarioForm, request, response);
			model.addAttribute(STRING_USUARIOFORM, usuarioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - verAltaUsuario",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar usuario.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param usuarioForm el usuario form
	 * @return el string
	 */
	@RequestMapping("/verModificarUsuario")
	public String verModificarUsuario(Model model, HttpServletRequest request, HttpServletResponse response,
			UsuarioForm usuarioForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarUsuarioSpring.class);
		try {
			VerModificarUsuarioSpring verModificarUsuarioSpring = new VerModificarUsuarioSpring();
			SpringForward rtrn = verModificarUsuarioSpring.execute(mapping, usuarioForm, request, response);
			model.addAttribute(STRING_USUARIOFORM, usuarioForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error UsuariosController - verModificarUsuario",e);
			throw new RuntimeException(e);
		}
	}
}