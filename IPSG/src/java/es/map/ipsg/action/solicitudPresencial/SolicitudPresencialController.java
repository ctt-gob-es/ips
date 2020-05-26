package es.map.ipsg.action.solicitudPresencial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.action.FormSessionMapper;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.form.BuscarModelo790Form;
import es.map.ipsg.form.BuscarSolicitudesPresencialesForm;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.form.Modelo790MasivoForm;
import es.map.ipsg.form.SolicitudPresencialForm;

/**
 * El Class SolicitudPresencialController.
 */
@Controller
@RequestMapping("/spring")
public class SolicitudPresencialController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudPresencialController.class);
	
	/** La constante STRING_SOLICITUDPRESENCIALFORM. */
	private static final String STRING_SOLICITUDPRESENCIALFORM = "solicitudPresencialForm";
	
	/** La constante STRING_ALTASOLICITUDPRESENCIALFORM. */
	private static final String STRING_ALTASOLICITUDPRESENCIALFORM = "altaSolicitudPresencialForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Alta solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/altaSolicitudPresencial")
	public String altaSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				AltaSolicitudPresencialSpring.class);
		try {
			AltaSolicitudPresencialSpring altaSolicitudPresencialSpring = new AltaSolicitudPresencialSpring();
			SpringForward rtrn = altaSolicitudPresencialSpring.execute(mapping, solicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - altaSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Registro alta solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/registroAltaSolicitudPresencial")
	public String registroAltaSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				RegistroAltaSolicitudPresencialSpring.class);
		try {
			FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
			altaSolicitudPresencialForm = formSessionMapper.resolveForm(altaSolicitudPresencialForm, request);
			
			RegistroAltaSolicitudPresencialSpring registroAltaSolicitudPresencialSpring = new RegistroAltaSolicitudPresencialSpring();
			SpringForward rtrn = registroAltaSolicitudPresencialSpring.execute(mapping, altaSolicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_ALTASOLICITUDPRESENCIALFORM, altaSolicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - altaSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Borrar documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/borrarDocumentoSolicitudPresencial")
	public String borrarDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudPresencialForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BorrarDocumentoSpring.class);
		try {
			BorrarDocumentoSpring borrarDocumentoSpring = new BorrarDocumentoSpring();
			SpringForward rtrn = borrarDocumentoSpring.execute(mapping, solicitudPresencialForm, request, response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - borrarDocumentoSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesPresencialesForm el buscar solicitudes presenciales form
	 * @return el string
	 */
	@RequestMapping("/buscarSolicitudPresencial")
	public String buscarSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesPresencialesForm buscarSolicitudesPresencialesForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarSolicitudesPresencialesForm = formSessionMapper.resolveForm(buscarSolicitudesPresencialesForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarSolicitudPresencialSpring.class);
		try {
			BuscarSolicitudPresencialSpring buscarSolicitudPresencialSpring = new BuscarSolicitudPresencialSpring();
			SpringForward rtrn = buscarSolicitudPresencialSpring.execute(mapping, buscarSolicitudesPresencialesForm, request,
					response);
			model.addAttribute("buscarSolicitudesPresencialesForm", buscarSolicitudesPresencialesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - buscarSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Buscar modelo 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarModelo790Form el buscar modelo 790 form
	 * @return el string
	 */
	@RequestMapping("/buscarModelo790")
	public String buscarModelo790(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarModelo790Form buscarModelo790Form) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarModelo790Form = formSessionMapper.resolveForm(buscarModelo790Form, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarModelo790Spring.class);
		try {
			BuscarModelo790Spring buscarModelo790Spring = new BuscarModelo790Spring();
			SpringForward rtrn = buscarModelo790Spring.execute(mapping, buscarModelo790Form, request,
					response);
			model.addAttribute("buscarModelo790Form", buscarModelo790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - buscarModelo790 ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Cargar datos solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/cargarDatosSolicitudPresencial")
	public String cargarDatosSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CargarDatosSolicitudPresencialSpring.class);
		try {
			CargarDatosSolicitudPresencialSpring cargarDatosSolicitudPresencialSpring = new CargarDatosSolicitudPresencialSpring();
			SpringForward rtrn = cargarDatosSolicitudPresencialSpring.execute(mapping, altaSolicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_ALTASOLICITUDPRESENCIALFORM, altaSolicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - cargarDatosSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/crearSolicitudPresencial")
	public String crearSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudPresencialForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearSolicitudPresencialSpring.class);
		try {
			CrearSolicitudPresencialSpring crearSolicitudPresencialSpring = new CrearSolicitudPresencialSpring();
			SpringForward rtrn = crearSolicitudPresencialSpring.execute(mapping, solicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - crearSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/descargarDocumentoSolicitudPresencial")
	public String descargarDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarDocumentoSpring.class);
		try {
			DescargarDocumentoSpring descargarDocumentoSpring = new DescargarDocumentoSpring();
			SpringForward rtrn = descargarDocumentoSpring.execute(mapping, solicitudPresencialForm, request, response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - descargarDocumentoSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Documentos solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/documentosSolicitudPresencial")
	public String documentosSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DocumentosSolicitudPresencialSpring.class);
		try {
			DocumentosSolicitudPresencialSpring documentosSolicitudPresencialSpring = new DocumentosSolicitudPresencialSpring();
			SpringForward rtrn = documentosSolicitudPresencialSpring.execute(mapping, solicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - documentosSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/eliminarSolicitudPresencial")
	public String eliminarSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudPresencialForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarSolicitudPresencialSpring.class);
		try {
			EliminarSolicitudPresencialSpring eliminarSolicitudPresencialSpring = new EliminarSolicitudPresencialSpring();
			SpringForward rtrn = eliminarSolicitudPresencialSpring.execute(mapping, solicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - eliminarSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Exp excel solicitudes pres.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/expExcelSolicitudesPres")
	public String expExcelSolicitudesPres(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudPresencialForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ExpExcelSolicitudesPresSpring.class);
		try {
			ExpExcelSolicitudesPresSpring expExcelSolicitudesPresSpring = new ExpExcelSolicitudesPresSpring();
			SpringForward rtrn = expExcelSolicitudesPresSpring.execute(mapping, solicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - expExcelSolicitudesPres ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Generar modelo 790 masivo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/generarModelo790Masivo")
	public String generarModelo790Masivo(Model model, HttpServletRequest request, HttpServletResponse response,
			Modelo790MasivoForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				GenerarModelo790MasivoSpring.class);
		try {
			GenerarModelo790MasivoSpring generarModelo790MasivoSpring = new GenerarModelo790MasivoSpring();
			SpringForward rtrn = generarModelo790MasivoSpring.execute(mapping, solicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - generarModelo790Masivo ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/modificarSolicitudPresencial")
	public String modificarSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarSolicitudPresencialSpring.class);
		try {
			FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
			altaSolicitudPresencialForm = formSessionMapper.resolveForm(altaSolicitudPresencialForm, request);
			
			ModificarSolicitudPresencialSpring modificarSolicitudPresencialSpring = new ModificarSolicitudPresencialSpring();
			SpringForward rtrn = modificarSolicitudPresencialSpring.execute(mapping, altaSolicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_ALTASOLICITUDPRESENCIALFORM, altaSolicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - modificarSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Subir documento solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/subirDocumentoSolicitudPresencial")
	public String subirDocumentoSolicitudPresencial(Model model, HttpServletRequest request,
			HttpServletResponse response, CrearDocumentoForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				SubirDocumentoSolicitudPresencialSpring.class);
		try {
			SubirDocumentoSolicitudPresencialSpring subirDocumentoSolicitudPresencialSpring = new SubirDocumentoSolicitudPresencialSpring();
			mapping.setValidate(true);
			SpringForward rtrn = subirDocumentoSolicitudPresencialSpring.execute(mapping, solicitudPresencialForm,
					request, response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - subirDocumentoSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/verAltaSolicitudPresencial")
	public String verAltaSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerAltaSolicitudPresencialSpring.class);
		try {
			VerAltaSolicitudPresencialSpring verAltaSolicitudPresencialSpring = new VerAltaSolicitudPresencialSpring();
			SpringForward rtrn = verAltaSolicitudPresencialSpring.execute(mapping, altaSolicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_ALTASOLICITUDPRESENCIALFORM, altaSolicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - verAltaSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesPresencialesForm el buscar solicitudes presenciales form
	 * @return el string
	 */
	@RequestMapping("/verBuscarSolicitudPresencial")
	public String verBuscarSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesPresencialesForm buscarSolicitudesPresencialesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarSolicitudPresencialSpring.class);
		try {
			
			
			VerBuscarSolicitudPresencialSpring verBuscarSolicitudPresencialSpring = new VerBuscarSolicitudPresencialSpring();
			SpringForward rtrn = verBuscarSolicitudPresencialSpring.execute(mapping, buscarSolicitudesPresencialesForm, request,
					response);
			model.addAttribute("buscarSolicitudesPresencialesForm", buscarSolicitudesPresencialesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - verBuscarSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Ver buscar modelo 790.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarModelo790Form el buscar modelo 790 form
	 * @return el string
	 */
	@RequestMapping("/verBuscarModelo790")
	public String verBuscarModelo790(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarModelo790Form buscarModelo790Form) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarModelo790Spring.class);
		try {
			
			
			VerBuscarModelo790Spring verBuscarModelo790Spring = new VerBuscarModelo790Spring();
			SpringForward rtrn = verBuscarModelo790Spring.execute(mapping, buscarModelo790Form, request,
					response);
			model.addAttribute("buscarModelo790Form", buscarModelo790Form);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - verBuscarModelo790 ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/verCrearSolicitudPresencial")
	public String verCrearSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearSolicitudPresencialSpring.class);
		try {
			FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
			altaSolicitudPresencialForm = formSessionMapper.resolveForm(altaSolicitudPresencialForm, request);
			formSessionMapper.put("/spring/modificarSolicitudPresencial", altaSolicitudPresencialForm);
			formSessionMapper.put("/spring/registroAltaSolicitudPresencial", altaSolicitudPresencialForm);
			
			VerCrearSolicitudPresencialSpring verCrearSolicitudPresencialSpring = new VerCrearSolicitudPresencialSpring();
			SpringForward rtrn = verCrearSolicitudPresencialSpring.execute(mapping, altaSolicitudPresencialForm, request,
					response);
			model.addAttribute("altaSolicitudPresencialForm", altaSolicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - verCrearSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver generar modelo 790 masivo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudPresencialForm el solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/verGenerarModelo790Masivo")
	public String verGenerarModelo790Masivo(Model model, HttpServletRequest request, HttpServletResponse response,
			Modelo790MasivoForm solicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerGenerarModelo790MasivoSpring.class);
		try {
			VerGenerarModelo790MasivoSpring verGenerarModelo790MasivoSpring = new VerGenerarModelo790MasivoSpring();
			SpringForward rtrn = verGenerarModelo790MasivoSpring.execute(mapping, solicitudPresencialForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDPRESENCIALFORM, solicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - verGenerarModelo790Masivo ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar solicitud presencial.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/verModificarSolicitudPresencial")
	public String verModificarSolicitudPresencial(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarSolicitudPresencialSpring.class);
		try {
			
			VerModificarSolicitudPresencialSpring verModificarSolicitudPresencialSpring = new VerModificarSolicitudPresencialSpring();
			SpringForward rtrn = verModificarSolicitudPresencialSpring.execute(mapping, altaSolicitudPresencialForm,
					request, response);
			model.addAttribute(STRING_ALTASOLICITUDPRESENCIALFORM, altaSolicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error SolicitudPresencialController - verModificarSolicitudPresencial ",e);
			throw new RuntimeException(e);
		}
	}
}
