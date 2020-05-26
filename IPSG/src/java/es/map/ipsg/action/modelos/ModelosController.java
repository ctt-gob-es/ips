package es.map.ipsg.action.modelos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ips.common.spring.SpringMessages;
import es.map.ipsg.action.FormSessionMapper;
import es.map.ipsg.action.VerPortadaSpring;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ActualizarContrasenaForm;
import es.map.ipsg.form.DatosPropiosConfForm;
import es.map.ipsg.form.Modelo790Form;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.UtilesIPSG;
import es.minhap.common.security.util.UserSpringSecurity;

/**
 * El Class ModelosController.
 */
@Controller
@RequestMapping("/spring")
public class ModelosController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModelosController.class);
	
	/** La constante STRING_MODELOGESTION790FORM. */
	private static final String STRING_MODELOGESTION790FORM = "modeloGestion790Form";
	
	/** La constante STRING_DATOSPROPIOSCONFFORM. */
	private static final String STRING_DATOSPROPIOSCONFFORM = "datosPropiosConfForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el utiles. */
	private UtilesIPSG utiles = new UtilesIPSG();
	
	/**
	 * Ver portada.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verPortada")
	public String verPortada(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerPortadaSpring.class);
		try {
			loadManagerContext();
			AutenticaAuthenticationToken auth = UserSpringSecurity.getUserSpringSecurity(request, response);
			UsuarioBean usuario = utiles.recuperarUsuario(auth.getIdentificador(), request, response, usuarioManager);
			
			if(usuario != null){
			VerPortadaSpring verPortadaSpring = new VerPortadaSpring();
			SpringForward rtrn = verPortadaSpring.execute(mapping, springForm, request,
					response);
			model.addAttribute("springForm", springForm);
			ActualizarContrasenaForm actualizarContrasenaForm = new ActualizarContrasenaForm();
			model.addAttribute("actualizarContrasenaForm", actualizarContrasenaForm);
				request.getSession().setAttribute("usuario", usuario);
			return rtrn.getPath();
			}else{
				return "forward:/spring/login?login_error=1";
			}
		} catch (Exception e) {
			logger.error(" Error ModelosController - verPortada :" ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Load manager context.
	 */
	protected void loadManagerContext() {
		usuarioManager = (UsuarioManager) ApplicationContextProvider.getInstance().getBean("usuarioManager");
	}	
	
	/**
	 * Anadir campo propio modelo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modeloGestion790Form el modelo gestion 790 form
	 * @return el string
	 */
	@RequestMapping("/anadirCampoPropioModelo")
	public String anadirCampoPropioModelo(Model model, HttpServletRequest request, HttpServletResponse response,
			ModeloGestion790Form modeloGestion790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				AnadirCampoPropioModeloSpring.class);
		try {
			AnadirCampoPropioModeloSpring anadirCampoPropioModeloSpring = new AnadirCampoPropioModeloSpring();
			SpringForward rtrn = anadirCampoPropioModeloSpring.execute(mapping, modeloGestion790Form, request, response);
			model.addAttribute(STRING_MODELOGESTION790FORM, modeloGestion790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - anadirCampoPropioModelo :" ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear modelos 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modeloGestion790Form el modelo gestion 790 form
	 * @return el string
	 */
	@RequestMapping("/crearModelos790")
	public String crearModelos790(Model model, HttpServletRequest request, HttpServletResponse response,
			ModeloGestion790Form modeloGestion790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), CrearModelos790Spring.class);
		try {
			CrearModelos790Spring crearModelos790Spring = new CrearModelos790Spring();
			SpringForward rtrn = crearModelos790Spring.execute(mapping, modeloGestion790Form, request, response);
			model.addAttribute(STRING_MODELOGESTION790FORM, modeloGestion790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - crearModelos790 :" ,e);
			throw new RuntimeException(e);
		}
	}
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Gestion modelos 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modelo790Form el modelo 790 form
	 * @return el string
	 */
	@RequestMapping("/buscarGestionModelos790")
	public String gestionModelos790(Model model, HttpServletRequest request, HttpServletResponse response,
			Modelo790Form modelo790Form) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		modelo790Form = formSessionMapper.resolveForm(modelo790Form, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				GestionModelos790Spring.class);
		try {
			GestionModelos790Spring gestionModelos790Spring = new GestionModelos790Spring();
			SpringForward rtrn = gestionModelos790Spring.execute(mapping, modelo790Form, request, response);
			model.addAttribute("modelo790Form", modelo790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - buscarGestionModelos790 :" ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar modelo 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modeloGestion790Form el modelo gestion 790 form
	 * @return el string
	 */
	@RequestMapping("/modificarModelo790")
	public String modificarModelo790(Model model, HttpServletRequest request, HttpServletResponse response,
			ModeloGestion790Form modeloGestion790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarModelo790Spring.class);
		try {
			ModificarModelo790Spring modificarModelo790Spring = new ModificarModelo790Spring();
			SpringForward rtrn = modificarModelo790Spring.execute(mapping, modeloGestion790Form, request, response);
			model.addAttribute(STRING_MODELOGESTION790FORM, modeloGestion790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - modificarModelo790 :" ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear modelos 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modeloGestion790Form el modelo gestion 790 form
	 * @return el string
	 */
	@RequestMapping("/verCrearGestionModelos790")
	public String verCrearModelos790(Model model, HttpServletRequest request, HttpServletResponse response,
			ModeloGestion790Form modeloGestion790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearModelos790Spring.class);
		try {
			VerCrearModelos790Spring verCrearModelos790Spring = new VerCrearModelos790Spring();
			SpringForward rtrn = verCrearModelos790Spring.execute(mapping, modeloGestion790Form, request, response);
			model.addAttribute(STRING_MODELOGESTION790FORM, modeloGestion790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - verCrearGestionModelos790 :" ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar campo propio.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modeloGestion790Form el modelo gestion 790 form
	 * @return el string
	 */
	@RequestMapping("/verModificarCampoPropio")
	public String verModificarCampoPropio(Model model, HttpServletRequest request, HttpServletResponse response,
			ModeloGestion790Form modeloGestion790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarCampoPropioSpring.class);
		try {
			VerModificarCampoPropioSpring verModificarCampoPropioSpring = new VerModificarCampoPropioSpring();
			SpringForward rtrn = verModificarCampoPropioSpring.execute(mapping, modeloGestion790Form, request, response);
			model.addAttribute(STRING_MODELOGESTION790FORM, modeloGestion790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - verModificarCampoPropio :" ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar modelos gestion 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param modeloGestion790Form el modelo gestion 790 form
	 * @return el string
	 */
	@RequestMapping("/verModificarModelosGestion790")
	public String verModificarModelosGestion790(Model model, HttpServletRequest request, HttpServletResponse response,
			ModeloGestion790Form modeloGestion790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarModelosGestion790Spring.class);
		try {
			VerModificarModelosGestion790Spring verModificarModelosGestion790Spring = new VerModificarModelosGestion790Spring();
			SpringForward rtrn = verModificarModelosGestion790Spring.execute(mapping, modeloGestion790Form, request, response);
			model.addAttribute(STRING_MODELOGESTION790FORM, modeloGestion790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - verModificarModelosGestion790 :" ,e);
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Anadir datos campos propios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param datosPropiosConfForm el datos propios conf form
	 * @return el string
	 */
	@RequestMapping("/anadirDatosCamposPropios")
	public String anadirDatosCamposPropios(Model model, HttpServletRequest request, HttpServletResponse response,
			DatosPropiosConfForm datosPropiosConfForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				AnadirDatosPropiosConfSpring.class);
		try {
			SpringErrors springErrors = datosPropiosConfForm.validate(mapping, request);
			
			if (springErrors != null && springErrors.size() > 0) {
				mapping.setValidate(false);
			}
			saveErrors(request, springErrors);
			
			AnadirDatosPropiosConfSpring anadirDatosPropiosConfSpring = new AnadirDatosPropiosConfSpring();
			SpringForward rtrn = anadirDatosPropiosConfSpring.execute(mapping, datosPropiosConfForm, request, response);
			model.addAttribute(STRING_DATOSPROPIOSCONFFORM, datosPropiosConfForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - anadirDatosCamposPropios :" ,e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Modificar datos campos propios.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param datosPropiosConfForm el datos propios conf form
	 * @return el string
	 */
	@RequestMapping("/modificarDatosCamposPropios")
	public String modificarDatosCamposPropios(Model model, HttpServletRequest request, HttpServletResponse response,
			DatosPropiosConfForm datosPropiosConfForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarDatosPropiosConfSpring.class);
		try {
			SpringErrors springErrors = datosPropiosConfForm.validate(mapping, request);
			
			if (springErrors != null && springErrors.size() > 0) {
				mapping.setValidate(false);
			}
			saveErrors(request, springErrors);
			
			ModificarDatosPropiosConfSpring modificarDatosPropiosConfSpring = new ModificarDatosPropiosConfSpring();
			SpringForward rtrn = modificarDatosPropiosConfSpring.execute(mapping, datosPropiosConfForm, request, response);
			model.addAttribute(STRING_DATOSPROPIOSCONFFORM, datosPropiosConfForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - modificarDatosCamposPropios :" ,e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Eliminar datos propios configuracion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param datosPropiosConfForm el datos propios conf form
	 * @return el string
	 */
	@RequestMapping("/eliminarDatosCamposPropios")
	public String eliminarDatosPropiosConfiguracion(Model model, HttpServletRequest request, HttpServletResponse response,
			DatosPropiosConfForm datosPropiosConfForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarDatosPropiosConfSpring.class);
		try {
			datosPropiosConfForm.setAccion("ELIMINAR");
			ModificarDatosPropiosConfSpring modificarDatosPropiosConfSpring = new ModificarDatosPropiosConfSpring();
			SpringForward rtrn = modificarDatosPropiosConfSpring.execute(mapping, datosPropiosConfForm, request, response);
			model.addAttribute(STRING_DATOSPROPIOSCONFFORM, datosPropiosConfForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error ModelosController - modificarDatosCamposPropios :" ,e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Save errors.
	 *
	 * @param request el request
	 * @param errors el errors
	 */
	protected static void saveErrors(HttpServletRequest request, SpringMessages errors) {
		if ((errors == null) || (errors.isEmpty()) || (errors != null && errors.size() == 0)) {
			request.removeAttribute("org.apache.spring.ERROR");
			return;

		}
		request.setAttribute("org.apache.spring.ERROR", errors);
	}
}
