package es.map.ipsg.action.solicitud;

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
import es.map.ipsg.action.RedireccionadorSpring;
import es.map.ipsg.form.ActualizarEstadoSolicitudForm;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.form.ConsultarRegistrosPagoAeatForm;
import es.map.ipsg.form.ConsultarRegistrosRecForm;
import es.map.ipsg.form.ContactarCiudadanoForm;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.form.SolicitudesIncidenciasForm;
import es.map.ipsg.form.VerificarDesempleoForm;
import es.map.ipsg.form.VerificarDiscapacidadForm;
import es.map.ipsg.form.VerificarEdadSolicitantesForm;
import es.map.ipsg.form.VerificarFNumerosaForm;
import es.map.ipsg.form.VerificarVictimasForm;
//import es.map.ipsg.form.VerificarTitulosForm;
import es.map.ipsg.action.convocatoria.BorrarDocumentoSpring;

/**
 * El Class SolicitudController.
 */
@Controller
@RequestMapping("/spring")
public class SolicitudController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudController.class);
	
	/** La constante STRING_CONTACTARCIUDADANOFORM. */
	private static final String STRING_CONTACTARCIUDADANOFORM = "contactarCiudadanoForm";
	
	/** La constante STRING_SOLICITUDESINCIDENCIASFORM. */
	private static final String STRING_SOLICITUDESINCIDENCIASFORM = "solicitudesIncidenciasForm";
	
	/** La constante STRING_BUSCARSOLICITUDESFORM. */
	private static final String STRING_BUSCARSOLICITUDESFORM = "buscarSolicitudesForm";
	
	/** La constante STRING_SPRINGFORM. */
	private static final String STRING_SPRINGFORM = "springForm";
	
	/** La constante STRING_CREARDOCUMENTOFORM. */
	private static final String STRING_CREARDOCUMENTOFORM = "crearDocumentoForm";
	
	/** La constante STRING_SPRING_BUSCARSOLICITUDESREGISTRADAS. */
	private static final String STRING_SPRING_BUSCARSOLICITUDESREGISTRADAS = "/spring/buscarSolicitudesRegistradas";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Actualizar estado solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param actualizarEstadoSolicitudForm el actualizar estado solicitud form
	 * @return el string
	 */
	@RequestMapping("/actualizarEstadoSolicitud")
	public String actualizarEstadoSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			ActualizarEstadoSolicitudForm actualizarEstadoSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarEstadoSolicitudSpring.class);
		try {
			ActualizarEstadoSolicitudSpring actualizarEstadoSolicitudSpring = new ActualizarEstadoSolicitudSpring();
			SpringForward rtrn = actualizarEstadoSolicitudSpring.execute(mapping, actualizarEstadoSolicitudForm, request, response);
			model.addAttribute("actualizarEstadoSpringForm", actualizarEstadoSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - actualizarEstadoSolicitud : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adjuntar documentos.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param contactarCiudadanoForm el contactar ciudadano form
	 * @return el string
	 */
	@RequestMapping("/adjuntarDocumentos")
	public String adjuntarDocumentos(Model model, HttpServletRequest request, HttpServletResponse response,
			ContactarCiudadanoForm contactarCiudadanoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				AdjuntarDocumentosSpring.class);
		try {
			AdjuntarDocumentosSpring adjuntarDocumentosSpring = new AdjuntarDocumentosSpring();
			SpringForward rtrn = adjuntarDocumentosSpring.execute(mapping, contactarCiudadanoForm, request, response);
			model.addAttribute(STRING_CONTACTARCIUDADANOFORM, contactarCiudadanoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - adjuntarDocumentos : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar consultar pagos aeat.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultarRegistrosPagoAeatForm el consultar registros pago aeat form
	 * @return el string
	 */
	@RequestMapping("/buscarConsultarPagosAeat")
	public String buscarConsultarPagosAeat(Model model, HttpServletRequest request, HttpServletResponse response,
			ConsultarRegistrosPagoAeatForm consultarRegistrosPagoAeatForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		consultarRegistrosPagoAeatForm = formSessionMapper.resolveForm(consultarRegistrosPagoAeatForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarConsultarPagosAeatSpring.class);
		try {
			BuscarConsultarPagosAeatSpring buscarConsultarPagosAeatSpring = new BuscarConsultarPagosAeatSpring();
			SpringForward rtrn = buscarConsultarPagosAeatSpring.execute(mapping, consultarRegistrosPagoAeatForm, request, response);
			model.addAttribute("consultarRegistrosPagoAeatForm", consultarRegistrosPagoAeatForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - buscarConsultarPagosAeat : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar consultar registros rec.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultarRegistrosRecForm el consultar registros rec form
	 * @return el string
	 */
	@RequestMapping("/buscarConsultarRegistrosRec")
	public String buscarConsultarRegistrosRec(Model model, HttpServletRequest request, HttpServletResponse response,
			ConsultarRegistrosRecForm consultarRegistrosRecForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		consultarRegistrosRecForm = formSessionMapper.resolveForm(consultarRegistrosRecForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarConsultarRegistrosRecSpring.class);
		try {
			BuscarConsultarRegistrosRecSpring buscarConsultarRegistrosRecSpring = new BuscarConsultarRegistrosRecSpring();
			SpringForward rtrn = buscarConsultarRegistrosRecSpring.execute(mapping, consultarRegistrosRecForm, request, response);
			model.addAttribute("consultarRegistrosRecForm", consultarRegistrosRecForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - buscarConsultarRegistrosRec : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar solicitudes incidencias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudesIncidenciasForm el solicitudes incidencias form
	 * @return el string
	 */
	@RequestMapping("/buscarSolicitudesIncidencias")
	public String buscarSolicitudesIncidencias(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudesIncidenciasForm solicitudesIncidenciasForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		solicitudesIncidenciasForm = formSessionMapper.resolveForm(solicitudesIncidenciasForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarSolicitudesIncidenciasSpring.class);
		try {
			BuscarSolicitudesIncidenciasSpring buscarSolicitudesIncidenciasSpring = new BuscarSolicitudesIncidenciasSpring();
			SpringForward rtrn = buscarSolicitudesIncidenciasSpring.execute(mapping, solicitudesIncidenciasForm, request, response);
			model.addAttribute(STRING_SOLICITUDESINCIDENCIASFORM, solicitudesIncidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - buscarSolicitudesIncidencias : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar solicitudes registradas.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/buscarSolicitudesRegistradas")
	public String buscarSolicitudesRegistradas(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarSolicitudesForm = formSessionMapper.resolveForm(buscarSolicitudesForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarSolicitudesRegistradasSpring.class);
		try {
			BuscarSolicitudesRegistradasSpring buscarSolicitudesRegistradasSpring = new BuscarSolicitudesRegistradasSpring();
			SpringForward rtrn = buscarSolicitudesRegistradasSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute(STRING_BUSCARSOLICITUDESFORM, buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - buscarSolicitudesRegistradas : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Consultar desempleo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param verificarDesempleoForm el verificar desempleo form
	 * @return el string
	 */
	@RequestMapping("/consultarDesempleo")
	public String consultarDesempleo(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarDesempleoForm verificarDesempleoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ConsultarDesempleoSpring.class);
		try {
			ConsultarDesempleoSpring consultarDesempleoSpring = new ConsultarDesempleoSpring();
			SpringForward rtrn = consultarDesempleoSpring.execute(mapping, verificarDesempleoForm, request, response);
			model.addAttribute("verificarDesempleoForm", verificarDesempleoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - consultarDesempleo : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Consultar discapacidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param verificarDiscapacidadForm el verificar discapacidad form
	 * @return el string
	 */
	@RequestMapping("/consultarDiscapacidad")
	public String consultarDiscapacidad(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarDiscapacidadForm verificarDiscapacidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ConsultarDiscapacidadSpring.class);
		try {
			ConsultarDiscapacidadSpring consultarDiscapacidadSpring = new ConsultarDiscapacidadSpring();
			SpringForward rtrn = consultarDiscapacidadSpring.execute(mapping, verificarDiscapacidadForm, request, response);
			model.addAttribute("verificarDiscapacidadForm", verificarDiscapacidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - consultarDiscapacidad : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Consultar email enviados.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/consultarEmailEnviados")
	public String consultarEmailEnviados(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ConsultarEmailEnviadosSpring.class);
		try {
			ConsultarEmailEnviadosSpring consultarEmailEnviadosSpring = new ConsultarEmailEnviadosSpring();
			SpringForward rtrn = consultarEmailEnviadosSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - consultarEmailEnviados : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Consultar F numerosa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/consultarFNumerosa")
	public String consultarFNumerosa(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarFNumerosaForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ConsultarFNumerosaSpring.class);
		try {
			ConsultarFNumerosaSpring consultarFNumerosaSpring = new ConsultarFNumerosaSpring();
			SpringForward rtrn = consultarFNumerosaSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - consultarFNumerosa : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Consultar lotes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/consultarLotes")
	public String consultarLotes(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ConsultarLotesSpring.class);
		try {
			ConsultarLotesSpring consultarLotesSpring = new ConsultarLotesSpring();
			SpringForward rtrn = consultarLotesSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute(STRING_BUSCARSOLICITUDESFORM, buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - consultarLotes : ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Consultar lotes aux.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/consultarLotesAux")
	public String consultarLotesAux(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ConsultarLotesAuxSpring.class);
		try {
			ConsultarLotesAuxSpring consultarLotesAuxSpring = new ConsultarLotesAuxSpring();
			SpringForward rtrn = consultarLotesAuxSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute(STRING_BUSCARSOLICITUDESFORM, buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - consultarLotes : ",e);
			throw new RuntimeException(e);
		}
	}

//	@RequestMapping("/consultarDatosTitulos")
//	public String consultarTitulos(Model model, HttpServletRequest request, HttpServletResponse response,
//			VerificarTitulosForm verificarTitulosForm) {
//		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ConsultarTitulosSpring.class);
//		try {
//			ConsultarTitulosSpring consultarTitulosSpring = new ConsultarTitulosSpring();
//			SpringForward rtrn = consultarTitulosSpring.execute(mapping, verificarTitulosForm, request, response);
//			model.addAttribute("verificarTitulosForm", verificarTitulosForm);
//			return rtrn.getPath();
//		} catch (Exception e) {
//			logger.error(" Error SolicitudController - consultarDatosTitulos : ",e);
//			throw new RuntimeException(e);
//		}
//	}

	/**
 * Contactar ciudadano.
 *
 * @param model el model
 * @param request el request
 * @param response el response
 * @param contactarCiudadanoForm el contactar ciudadano form
 * @return el string
 */
@RequestMapping("/contactarCiudadano")
	public String contactarCiudadano(Model model, HttpServletRequest request, HttpServletResponse response,
			ContactarCiudadanoForm contactarCiudadanoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ContactarCiudadanoSpring.class);
		try {
			ContactarCiudadanoSpring contactarCiudadanoSpring = new ContactarCiudadanoSpring();
			SpringForward rtrn = contactarCiudadanoSpring.execute(mapping, contactarCiudadanoForm, request, response);
			model.addAttribute(STRING_CONTACTARCIUDADANOFORM, contactarCiudadanoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - contactarCiudadano : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar documento solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/descargarDocumentoSolicitud")
	public String descargarDocumentoSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarDocumentoSolicitudSpring.class);
		try {
			DescargarDocumentoSolicitudSpring descargarDocumentoSolicitudSpring = new DescargarDocumentoSolicitudSpring();
			SpringForward rtrn = descargarDocumentoSolicitudSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - descargarDocumentoSolicitud : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar documento zip incidencias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudesIncidenciasForm el solicitudes incidencias form
	 * @return el string
	 */
	@RequestMapping("/descargarDocumentoZipIncidencias")
	public String descargarDocumentoZipIncidencias(Model model, HttpServletRequest request,
			HttpServletResponse response, SolicitudesIncidenciasForm solicitudesIncidenciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarDocumentoZipIncidenciasSpring.class);
		try {
			
			DescargarDocumentoZipIncidenciasSpring descargarDocumentoZipIncidenciasSpring = new DescargarDocumentoZipIncidenciasSpring();
			SpringForward rtrn = descargarDocumentoZipIncidenciasSpring.execute(mapping, solicitudesIncidenciasForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDESINCIDENCIASFORM, solicitudesIncidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - descargarDocumentoZipIncidencias : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar documento zip.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/descargarDocumentoZip")
	public String descargarDocumentoZip(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarDocumentoZipSpring.class);
		try {
			DescargarDocumentoZipSpring descargarDocumentoZipSpring = new DescargarDocumentoZipSpring();
			SpringForward rtrn = descargarDocumentoZipSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute(STRING_BUSCARSOLICITUDESFORM, buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - descargarDocumentoZip : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Detalle pago registro solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudesIncidenciasForm el solicitudes incidencias form
	 * @return el string
	 */
	@RequestMapping("/detallePagoRegistroSolicitud")
	public String detallePagoRegistroSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudesIncidenciasForm solicitudesIncidenciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DetallePagoRegistroSolicitudSpring.class);
		try {
			DetallePagoRegistroSolicitudSpring detallePagoRegistroSolicitudSpring = new DetallePagoRegistroSolicitudSpring();
			SpringForward rtrn = detallePagoRegistroSolicitudSpring.execute(mapping, solicitudesIncidenciasForm, request, response);
			model.addAttribute(STRING_SOLICITUDESINCIDENCIASFORM, solicitudesIncidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - detallePagoRegistroSolicitud : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Documentos justificante solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/documentosJustificanteSolicitud")
	public String documentosJustificanteSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DocumentosJustificanteSolicitudSpring.class);
		try {
			DocumentosJustificanteSolicitudSpring documentosJustificanteSolicitudSpring = new DocumentosJustificanteSolicitudSpring();
			SpringForward rtrn = documentosJustificanteSolicitudSpring.execute(mapping, crearDocumentoForm, request,
					response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - documentosJustificanteSolicitud : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Documentos solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/documentosSolicitud")
	public String documentosSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DocumentosSolicitudSpring.class);
		try {
			DocumentosSolicitudSpring documentosSolicitudSpring = new DocumentosSolicitudSpring();
			SpringForward rtrn = documentosSolicitudSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - documentosSolicitud : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Exportar excel solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/exportarExcelSolicitudes")
	public String exportarExcelSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ExportarExcelSolicitudesSpring.class);
		try {
			ExportarExcelSolicitudesSpring exportarExcelSolicitudesSpring = new ExportarExcelSolicitudesSpring();
			SpringForward rtrn = exportarExcelSolicitudesSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute(STRING_BUSCARSOLICITUDESFORM, buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - exportarExcelSolicitudes : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Exportar sigp solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/exportarSigpSolicitudes")
	public String exportarSigpSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ExportarSigpSolicitudesSpring.class);
		
		if(buscarSolicitudesForm.getAccion() == null) {
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarSolicitudesForm = (BuscarSolicitudesForm) formSessionMapper.get(STRING_SPRING_BUSCARSOLICITUDESREGISTRADAS);
		}
		else {
			String accion = buscarSolicitudesForm.getAccion();
			FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
			buscarSolicitudesForm = (BuscarSolicitudesForm) formSessionMapper.get(STRING_SPRING_BUSCARSOLICITUDESREGISTRADAS);
			buscarSolicitudesForm.setAccion(accion);
			
		}
		try {
			ExportarSigpSolicitudesSpring exportarSigpSolicitudesSpring = new ExportarSigpSolicitudesSpring();
			SpringForward rtrn = exportarSigpSolicitudesSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute(STRING_BUSCARSOLICITUDESFORM, buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - exportarSigpSolicitudes : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Generar documento pdf rec.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/generarDocPdfRec")
	public String generarDocumentoPdfRec(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				GenerarDocumentoPdfRecSpring.class);
		try {
			GenerarDocumentoPdfRecSpring generarDocumentoPdfRecSpring = new GenerarDocumentoPdfRecSpring();
			SpringForward rtrn = generarDocumentoPdfRecSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - generarDocumentoPdfRec : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Generar documento pdf.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/generarDocumentoPdf")
	public String generarDocumentoPdf(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				GenerarDocumentoPdfSpring.class);
		try {
			GenerarDocumentoPdfSpring generarDocumentoPdfSpring = new GenerarDocumentoPdfSpring();
			SpringForward rtrn = generarDocumentoPdfSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - generarDocumentoPdf : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gestion justificante solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/gestionJustificanteSolicitud")
	public String gestionJustificanteSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				GestionJustificanteSolicitudSpring.class);
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		crearDocumentoForm = (CrearDocumentoForm) formSessionMapper.get("crearDocumentoForm");
		try {
			GestionJustificanteSolicitudSpring gestionJustificanteSolicitudSpring = new GestionJustificanteSolicitudSpring();
			SpringForward rtrn = gestionJustificanteSolicitudSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);			
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - gestionJustificanteSolicitud : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Guardar justificante generado.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/guardarJustificanteGenerado")
	public String guardarJustificanteGenerado(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				GuardarJustificanteGeneradoSpring.class);
		try {
			GuardarJustificanteGeneradoSpring guardarJustificanteGeneradoSpring = new GuardarJustificanteGeneradoSpring();
			SpringForward rtrn = guardarJustificanteGeneradoSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - guardarJustificanteGenerado : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Reintentar registro.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/reintentarRegistro")
	public String reintentarRegistro(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ReintentarRegistroSpring.class);
		try {
			ReintentarRegistroSpring reintentarRegistroSpring = new ReintentarRegistroSpring();
			SpringForward rtrn = reintentarRegistroSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - reintentarRegistro : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Subir documento adjunto.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/subirDocumentoAdjunto")
	public String subirDocumentoAdjunto(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				SubirDocumentoAdjuntoSpring.class);
		try {
			SubirDocumentoAdjuntoSpring subirDocumentoAdjuntoSpring = new SubirDocumentoAdjuntoSpring();
			SpringForward rtrn = subirDocumentoAdjuntoSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - subirDocumentoAdjunto : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver actualizar estado solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param actualizarEstadoSolicitudForm el actualizar estado solicitud form
	 * @return el string
	 */
	@RequestMapping("/verActualizarEstadoSolicitud")
	public String verActualizarEstadoSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			ActualizarEstadoSolicitudForm actualizarEstadoSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerActualizarEstadoSolicitudSpring.class);
		try {
			VerActualizarEstadoSolicitudSpring verActualizarEstadoSolicitudSpring = new VerActualizarEstadoSolicitudSpring();
			SpringForward rtrn = verActualizarEstadoSolicitudSpring.execute(mapping, actualizarEstadoSolicitudForm, request, response);
			model.addAttribute("actualizarEstadoSolicitudForm", actualizarEstadoSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verActualizarEstadoSolicitud : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar consultar pagos aeat.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultarRegistrosPagoAeatForm el consultar registros pago aeat form
	 * @return el string
	 */
	@RequestMapping("/verBuscarConsultarPagosAeat")
	public String verBuscarConsultarPagosAeat(Model model, HttpServletRequest request, HttpServletResponse response,
			ConsultarRegistrosPagoAeatForm consultarRegistrosPagoAeatForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarConsultarPagosAeatSpring.class);
		try {
			VerBuscarConsultarPagosAeatSpring verBuscarConsultarPagosAeatSpring = new VerBuscarConsultarPagosAeatSpring();
			SpringForward rtrn = verBuscarConsultarPagosAeatSpring.execute(mapping, consultarRegistrosPagoAeatForm, request, response);
			model.addAttribute("consultarRegistrosPagoAeatForm", consultarRegistrosPagoAeatForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verBuscarConsultarPagosAeat : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar consultar registros rec.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultarRegistrosRecForm el consultar registros rec form
	 * @return el string
	 */
	@RequestMapping("/verBuscarConsultarRegistrosRec")
	public String verBuscarConsultarRegistrosRec(Model model, HttpServletRequest request, HttpServletResponse response,
			ConsultarRegistrosRecForm consultarRegistrosRecForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarConsultarRegistrosRecSpring.class);
		try {
			VerBuscarConsultarRegistrosRecSpring verBuscarConsultarRegistrosRecSpring = new VerBuscarConsultarRegistrosRecSpring();
			SpringForward rtrn = verBuscarConsultarRegistrosRecSpring.execute(mapping, consultarRegistrosRecForm, request,
					response);
			model.addAttribute("consultarRegistrosRecForm", consultarRegistrosRecForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verBuscarConsultarRegistrosRec : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar solicitudes incidencias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudesIncidenciasForm el solicitudes incidencias form
	 * @return el string
	 */
	@RequestMapping("/verBuscarSolicitudesIncidencias")
	public String verBuscarSolicitudesIncidencias(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudesIncidenciasForm solicitudesIncidenciasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarSolicitudesIncidenciasSpring.class);
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		solicitudesIncidenciasForm = formSessionMapper.resolveForm(solicitudesIncidenciasForm, request);
		
		try {
			VerBuscarSolicitudesIncidenciasSpring verBuscarSolicitudesIncidenciasSpring = new VerBuscarSolicitudesIncidenciasSpring();
			SpringForward rtrn = verBuscarSolicitudesIncidenciasSpring.execute(mapping, solicitudesIncidenciasForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDESINCIDENCIASFORM, solicitudesIncidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verBuscarSolicitudesIncidencias : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver buscar solicitudes registradas.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verBuscarSolicitudesRegistradas")
	public String verBuscarSolicitudesRegistradas(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerBuscarSolicitudesRegistradasSpring.class);
		try {
			VerBuscarSolicitudesRegistradasSpring verBuscarSolicitudesRegistradasSpring = new VerBuscarSolicitudesRegistradasSpring();
			SpringForward rtrn = verBuscarSolicitudesRegistradasSpring.execute(mapping, springForm, request,
					response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verBuscarSolicitudesRegistradas : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver contactar ciudadano.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param contactarCiudadanoForm el contactar ciudadano form
	 * @return el string
	 */
	@RequestMapping("/verContactarCiudadano")
	public String verContactarCiudadano(Model model, HttpServletRequest request, HttpServletResponse response,
			ContactarCiudadanoForm contactarCiudadanoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerContactarCiudadanoSpring.class);

		try {
			VerContactarCiudadanoSpring verContactarCiudadanoSpring = new VerContactarCiudadanoSpring();
			SpringForward rtrn = verContactarCiudadanoSpring.execute(mapping, contactarCiudadanoForm, request, response);
			model.addAttribute(STRING_CONTACTARCIUDADANOFORM, contactarCiudadanoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verContactarCiudadano : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver correo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verCorreo")
	public String verCorreo(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerCorreoSpring.class);
		try {
			VerCorreoSpring verCorreoSpring = new VerCorreoSpring();
			SpringForward rtrn = verCorreoSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verCorreo : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver documento adjunto.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verDocumentoAdjunto")
	public String verDocumentoAdjunto(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerDocumentoAdjuntoSpring.class);
		try {
			VerDocumentoAdjuntoSpring verDocumentoAdjuntoSpring = new VerDocumentoAdjuntoSpring();
			SpringForward rtrn = verDocumentoAdjuntoSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verDocumentoAdjunto : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver exporta documento incidencias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudesIncidenciasForm el solicitudes incidencias form
	 * @return el string
	 */
	@RequestMapping("/verExportaDocumentoIncidencias")
	public String verExportaDocumentoIncidencias(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudesIncidenciasForm solicitudesIncidenciasForm) {
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerExportaDocumentoIncidenciasSpring.class);
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		solicitudesIncidenciasForm = (SolicitudesIncidenciasForm) formSessionMapper.get("/spring/buscarSolicitudesIncidencias");
		
		try {
			VerExportaDocumentoIncidenciasSpring verExportaDocumentoIncidenciasSpring = new VerExportaDocumentoIncidenciasSpring();
			SpringForward rtrn = verExportaDocumentoIncidenciasSpring.execute(mapping, solicitudesIncidenciasForm, request,
					response);
			model.addAttribute(STRING_SOLICITUDESINCIDENCIASFORM, solicitudesIncidenciasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verExportaDocumentoIncidencias : ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver exporta documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/verExportaDocumento")
	public String verExportaDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerExportaDocumentoSpring.class);
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarSolicitudesForm = (BuscarSolicitudesForm) formSessionMapper.get(STRING_SPRING_BUSCARSOLICITUDESREGISTRADAS);
		
		try {
			VerExportaDocumentoSpring verExportaDocumentoSpring = new VerExportaDocumentoSpring();
			SpringForward rtrn = verExportaDocumentoSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute(STRING_BUSCARSOLICITUDESFORM, buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verExportaDocumento : ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Borrar documento adjunto.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/borrarDocumentoAdjunto")
	public String borrarDocumentoAdjunto(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BorrarDocumentoSpring.class);
		
		
		try {
			BorrarDocumentoSpring borrarDocumentoSpring = new BorrarDocumentoSpring();
			SpringForward rtrn = borrarDocumentoSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - borrarDocumentoAdjunto: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verificar desempleo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param verificarDesempleoForm el verificar desempleo form
	 * @return el string
	 */
	@RequestMapping("/verificarDesempleo")
	public String verificarDesempleo(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarDesempleoForm verificarDesempleoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerificarDesempleoSpring.class);
		try {
			VerificarDesempleoSpring verificarDesempleoSpring = new VerificarDesempleoSpring();
			SpringForward rtrn = verificarDesempleoSpring.execute(mapping,verificarDesempleoForm, request, response);
			model.addAttribute("verificarDesempleoForm", verificarDesempleoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verificarDesempleo: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verificar discapacidad.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param verificarDiscapacidadForm el verificar discapacidad form
	 * @return el string
	 */
	@RequestMapping("/verificarDiscapacidad")
	public String verificarDiscapacidad(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarDiscapacidadForm verificarDiscapacidadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerificarDiscapacidadSpring.class);
		try {
			VerificarDiscapacidadSpring verificarDiscapacidadSpring = new VerificarDiscapacidadSpring();
			SpringForward rtrn = verificarDiscapacidadSpring.execute(mapping, verificarDiscapacidadForm, request, response);
			model.addAttribute("verificarDiscapacidadForm", verificarDiscapacidadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verificarDiscapacidad: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verificar edad solicitantes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param verificarEdadSolicitantesForm el verificar edad solicitantes form
	 * @return el string
	 */
	@RequestMapping("/verificarEdadSolicitantes")
	public String verificarEdadSolicitantes(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarEdadSolicitantesForm verificarEdadSolicitantesForm) {
		request.getAttribute("solicitudes");
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerificarEdadSolicitantesSpring.class);
		try {
			VerificarEdadSolicitantesSpring verificarEdadSolicitantesSpring = new VerificarEdadSolicitantesSpring();
			SpringForward rtrn = verificarEdadSolicitantesSpring.execute(mapping, verificarEdadSolicitantesForm, request, response);
			model.addAttribute("verificarEdadSolicitantesForm", verificarEdadSolicitantesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verificarEdadSolicitantes: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verificar fecha nacimiento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verificarFechaNacimiento")
	public String verificarFechaNacimiento(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerificarFechaNacimientoSpring.class);
		try {
			VerificarFechaNacimientoSpring verificarFechaNacimientoSpring = new VerificarFechaNacimientoSpring();
			SpringForward rtrn = verificarFechaNacimientoSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verificarFechaNacimiento: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verificar F numerosa.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param verificarFNumerosaForm el verificar F numerosa form
	 * @return el string
	 */
	@RequestMapping("/verificarFNumerosa")
	public String verificarFNumerosa(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarFNumerosaForm verificarFNumerosaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerificarFNumerosaSpring.class);
		try {
			VerificarFNumerosaSpring verificarFNumerosaSpring = new VerificarFNumerosaSpring();
			SpringForward rtrn = verificarFNumerosaSpring.execute(mapping, verificarFNumerosaForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, verificarFNumerosaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verificarFNumerosa: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verificar pago rec.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verificarPagoRec")
	public String verificarPagoRec(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerificarPagoRecSpring.class);
		try {
			VerificarPagoRecSpring verificarPagoRecSpring = new VerificarPagoRecSpring();
			SpringForward rtrn = verificarPagoRecSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verificarPagoRec: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verificar registro rec.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verificarRegistroRec")
	public String verificarRegistroRec(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerificarRegistroRecSpring.class);
		try {
			VerificarRegistroRecSpring verificarRegistroRecSpring = new VerificarRegistroRecSpring();
			SpringForward rtrn = verificarRegistroRecSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verificarRegistroRec: ",e);
			throw new RuntimeException(e);
		}
	}

//	@RequestMapping("/verificarDatosTitulos")
//	public String verificarTitulos(Model model, HttpServletRequest request, HttpServletResponse response,
//			VerificarTitulosForm verificarTitulosForm) {
//		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerificarTitulosSpring.class);
//		try {
//			VerificarTitulosSpring verificarTitulosSpring = new VerificarTitulosSpring();
//			SpringForward rtrn = verificarTitulosSpring.execute(mapping, verificarTitulosForm, request, response);
//			model.addAttribute(STRING_SPRINGFORM, verificarTitulosForm);
//			return rtrn.getPath();
//		} catch (Exception e) {
//			logger.error(" Error SolicitudController - verificarDatosTitulos: ",e);
//			throw new RuntimeException(e);
//		}
//	}

	/**
 * Ver verificar edad solicitantes.
 *
 * @param model el model
 * @param request el request
 * @param response el response
 * @param buscarSolicitudesForm el buscar solicitudes form
 * @return el string
 */
@RequestMapping("/verVerificarEdadSolicitantes")
	public String verVerificarEdadSolicitantes(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerVerificarEdadSolicitantesSpring.class);
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarSolicitudesForm = formSessionMapper.resolveForm(buscarSolicitudesForm, request);

		try {
			VerVerificarEdadSolicitantesSpring verVerificarEdadSolicitantesSpring = new VerVerificarEdadSolicitantesSpring();
			SpringForward rtrn = verVerificarEdadSolicitantesSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute(STRING_BUSCARSOLICITUDESFORM, buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verVerificarEdadSolicitantes: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver verificar registro.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/verVerificarRegistro")
	public String verVerificarRegistro(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerVerificarRegistroSpring.class);
		try {
			VerVerificarRegistroSpring verVerificarRegistroSpring = new VerVerificarRegistroSpring();
			SpringForward rtrn = verVerificarRegistroSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verVerificarRegistro: ",e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping("/verificarVictimasTerrorismo")
	public String verificarVictimasTerrorismo(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarVictimasForm verificarVictimasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerificarVictimasSpring.class);
		try {
			VerificarVictimasSpring verificarVictimasSpring = new VerificarVictimasSpring();
			SpringForward rtrn = verificarVictimasSpring.execute(mapping,verificarVictimasForm, request, response);
			model.addAttribute("verificarVictimasForm", verificarVictimasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - verificarDesempleo: ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Documentos anexo solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/documentosAnexoSolicitud")
	public String documentosAnexoSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DocumentosSolicitudSpring.class);
		try {
			DocumentosSolicitudSpring documentosAnexoSolicitud = new DocumentosSolicitudSpring();
			SpringForward rtrn = documentosAnexoSolicitud.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - documentosAnexoSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Redireccionador.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/redireccionadorSpring")
	public String redireccionador(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				RedireccionadorSpring.class);
		try {
			RedireccionadorSpring redireccionadorSpring = new RedireccionadorSpring();
			SpringForward rtrn = redireccionadorSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - documentosAnexoSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Redireccionador registro.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/redireccionadorRegistroSpring")
	public String redireccionadorRegistro(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				RedireccionadorSpring.class);
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		altaSolicitudPresencialForm = formSessionMapper.resolveForm(altaSolicitudPresencialForm, request);
		try {
			RedireccionadorSpring redireccionadorSpring = new RedireccionadorSpring();
			SpringForward rtrn = redireccionadorSpring.execute(mapping, altaSolicitudPresencialForm, request, response);
			model.addAttribute("altaSolicitudPresencialForm", altaSolicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - redireccionadorRegistro: ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Redireccionador modificacion registro.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el string
	 */
	@RequestMapping("/redireccionadorModificacionRegistroSpring")
	public String redireccionadorModificacionRegistro(Model model, HttpServletRequest request, HttpServletResponse response,
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				RedireccionadorSpring.class);
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		altaSolicitudPresencialForm = formSessionMapper.resolveForm(altaSolicitudPresencialForm, request);
		try {
			RedireccionadorSpring redireccionadorSpring = new RedireccionadorSpring();
			SpringForward rtrn = redireccionadorSpring.execute(mapping, altaSolicitudPresencialForm, request, response);
			model.addAttribute("altaSolicitudPresencialForm", altaSolicitudPresencialForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - redireccionadorRegistro: ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Generar doc pdf.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/generarDocPdf")
	public String generarDocPdf(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				GenerarDocumentoPdfSpring.class);
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		try {
			GenerarDocumentoPdfSpring generarDocumentoPdfSpring = new GenerarDocumentoPdfSpring();
			SpringForward rtrn = generarDocumentoPdfSpring.execute(mapping, crearDocumentoForm, request, response);				
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);		
			formSessionMapper.put(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - documentosAnexoSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping("/consultarVictimasTerrorismo")
	public String consultarVictimasTerrorismo(Model model, HttpServletRequest request, HttpServletResponse response,
			VerificarVictimasForm consultarVictimasTerrorismoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ConsultarVictimasTerrorismoSpring.class);
		try {
			ConsultarVictimasTerrorismoSpring consultarVictimasTerrorismoSpring = new ConsultarVictimasTerrorismoSpring();
			SpringForward rtrn = consultarVictimasTerrorismoSpring.execute(mapping, consultarVictimasTerrorismoForm, request, response);
			model.addAttribute("consultarVictimasTerrorismoForm", consultarVictimasTerrorismoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error SolicitudController - consultarVictimasTerrorismo : ",e);
			throw new RuntimeException(e);
		}
	}
}
