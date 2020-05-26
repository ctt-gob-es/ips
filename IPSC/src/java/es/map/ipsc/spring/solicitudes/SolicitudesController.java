package es.map.ipsc.spring.solicitudes;

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
import es.map.ipsc.form.BuscarSolicitudesForm;
import es.map.ipsc.form.CargaJustificanteAjaxForm;
import es.map.ipsc.form.CrearDocumentoForm;
import es.map.ipsc.form.DetalleSolicitudForm;
import es.map.ipsc.form.FileUploadForm;
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.form.RegistroSolicitudForm;
import es.map.ipsc.form.SolicitudForm;

/**
 * El Class SolicitudesController.
 */
@Controller
@RequestMapping("/secure")
public class SolicitudesController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudesController.class);
	
	/** La constante STRING_SPRINGFORM. */
	private static final String STRING_SPRINGFORM = "springForm";
	
	/** La constante STRING_PAGOSOLICITUDFORM. */
	private static final String STRING_PAGOSOLICITUDFORM = "pagoSolicitudForm";
	
	/** La constante STRING_DETALLESOLICITUDFORM. */
	private static final String STRING_DETALLESOLICITUDFORM = "detalleSolicitudForm";
	
	/** La constante STRING_CREARDOCUMENTOFORM. */
	private static final String STRING_CREARDOCUMENTOFORM = "crearDocumentoForm";
	
	/** La constante STRING_REGISTROSOLICITUDFORM. */
	private static final String STRING_REGISTROSOLICITUDFORM = "registroSolicitudForm";
	
	/** La constante STRING_SOLICITUDFORM. */
	private static final String STRING_SOLICITUDFORM = "solicitudForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;

	/**
	 * Actualizar estado solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/ActualizarEstadoSolicitud")
	public String actualizarEstadoSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarEstadoSolicitudesSpring.class);
		try {
			ActualizarEstadoSolicitudesSpring actualizarEstadoSolicitudesSpring = new ActualizarEstadoSolicitudesSpring();
			SpringForward rtrn = actualizarEstadoSolicitudesSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - actualizarEstadoSolicitudes: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar solicitudes cerradas.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/buscarSolicitudesCerradas")
	public String buscarSolicitudesCerradas(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarSolicitudesCerradasSpring.class);
		try {
			BuscarSolicitudesCerradasSpring buscarSolicitudesCerradasSpring = new BuscarSolicitudesCerradasSpring();
			SpringForward rtrn = buscarSolicitudesCerradasSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute("buscarSolicitudesForm", buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - buscarSolicitudesCerradas: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarSolicitudesForm el buscar solicitudes form
	 * @return el string
	 */
	@RequestMapping("/buscarSolicitudes")
	public String buscarSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscarSolicitudesForm buscarSolicitudesForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarSolicitudesSpring.class);
		try {
			BuscarSolicitudesSpring buscarSolicitudesSpring = new BuscarSolicitudesSpring();
			SpringForward rtrn = buscarSolicitudesSpring.execute(mapping, buscarSolicitudesForm, request, response);
			model.addAttribute("buscarSolicitudesForm", buscarSolicitudesForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - buscarSolicitudes: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Carga justificante ajax.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param cargaJustificanteAjaxForm el carga justificante ajax form
	 * @return el string
	 */
	@RequestMapping("/cargaJustificanteAjax")
	public String cargaJustificanteAjax(Model model, HttpServletRequest request, HttpServletResponse response,
			CargaJustificanteAjaxForm cargaJustificanteAjaxForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CargaJustificanteAjaxSpring.class);
		try {
			CargaJustificanteAjaxSpring cargaJustificanteAjaxSpring = new CargaJustificanteAjaxSpring();
			SpringForward rtrn = cargaJustificanteAjaxSpring.execute(mapping, cargaJustificanteAjaxForm, request, response);
			model.addAttribute("cargaJustificanteAjaxForm", cargaJustificanteAjaxForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - cargaJustificanteAjax: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Consulta pago.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param pagoSolicitudForm el pago solicitud form
	 * @return el string
	 */
	@RequestMapping("/consultaPago")
	public String consultaPago(Model model, HttpServletRequest request, HttpServletResponse response,
			PagoSolicitudForm pagoSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ConsultaPagoSpring.class);
		try {
			ConsultaPagoSpring consultaPagoSpring = new ConsultaPagoSpring();
			SpringForward rtrn = consultaPagoSpring.execute(mapping, pagoSolicitudForm, request, response);
			model.addAttribute(STRING_PAGOSOLICITUDFORM, pagoSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - consultaPago: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar documento solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/descargarDocumentoSolicitud")
	public String descargarDocumentoSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarDocumentoSolicitudSpring.class);
		try {
			DescargarDocumentoSolicitudSpring descargarDocumentoSolicitudSpring = new DescargarDocumentoSolicitudSpring();
			SpringForward rtrn = descargarDocumentoSolicitudSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - descargarDocumentoSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Detalle pago solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param detalleSolicitudForm el detalle solicitud form
	 * @return el string
	 */
	@RequestMapping("/DetallePagoSolicitud")
	public String detallePagoSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			DetalleSolicitudForm detalleSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DetallePagoSolicitudesSpring.class);
		try {
			DetallePagoSolicitudesSpring detallePagoSolicitudesSpring = new DetallePagoSolicitudesSpring();
			SpringForward rtrn = detallePagoSolicitudesSpring.execute(mapping, detalleSolicitudForm, request, response);
			model.addAttribute(STRING_DETALLESOLICITUDFORM, detalleSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - detallePagoSolicitudes: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Detalle registro solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/DetalleRegistroSolicitud")
	public String detalleRegistroSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DetalleRegistroSolicitudesSpring.class);
		try {
			DetalleRegistroSolicitudesSpring detalleRegistroSolicitudesSpring = new DetalleRegistroSolicitudesSpring();
			SpringForward rtrn = detalleRegistroSolicitudesSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - detalleRegistroSolicitudes: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Detalle solicitudes.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param detalleSolicitudForm el detalle solicitud form
	 * @return el string
	 */
	@RequestMapping("/DetalleSolicitud")
	public String detalleSolicitudes(Model model, HttpServletRequest request, HttpServletResponse response,
			DetalleSolicitudForm detalleSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DetalleSolicitudesSpring.class);
		try {
			DetalleSolicitudesSpring detalleSolicitudesSpring = new DetalleSolicitudesSpring();
			SpringForward rtrn = detalleSolicitudesSpring.execute(mapping, detalleSolicitudForm, request, response);
			model.addAttribute(STRING_DETALLESOLICITUDFORM, detalleSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - detalleSolicitudes: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Documentos anexados solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/documentosAnexadosSolicitud")
	public String documentosAnexadosSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DocumentosAnexadosSolicitudSpring.class);
		try {
			DocumentosAnexadosSolicitudSpring documentosAnexadosSolicitudSpring = new DocumentosAnexadosSolicitudSpring();
			SpringForward rtrn = documentosAnexadosSolicitudSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute(STRING_CREARDOCUMENTOFORM, crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - documentosAnexadosSolicitud: ",e);
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
			logger.error("Error  SolicitudesController - documentosJustificanteSolicitud: ",e);
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
			logger.error("Error  SolicitudesController - documentosSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Eliminar archivo.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/eliminarArchivoAjax")
	public String eliminarArchivo(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), EliminarArchivoAjax.class);
		try {
			EliminarArchivoAjax eliminarArchivoSpring = new EliminarArchivoAjax();
			SpringForward rtrn = eliminarArchivoSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - eliminarArchivo: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * File upload.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param fileUploadForm el file upload form
	 * @return el string
	 */
	@RequestMapping("/fileUpload")
	public String fileUpload(Model model, HttpServletRequest request, HttpServletResponse response,
			FileUploadForm fileUploadForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), FileUploadSpring.class);
		try {
			FileUploadSpring fileUploadSpring = new FileUploadSpring();
			SpringForward rtrn = fileUploadSpring.execute(mapping, fileUploadForm, request, response);
			model.addAttribute("fileUploadForm", fileUploadForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - fileUpload: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Informacion adi.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/informacionAdi")
	public String informacionAdi(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), InformacionAdicional.class);
		try {
			InformacionAdicional informacionAdicional = new InformacionAdicional();
			SpringForward rtrn = informacionAdicional.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - informacionAdi: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Mostrar aviso pagina.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/mostrarAvisoPagina")
	public String mostrarAvisoPagina(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				MostrarAvisoPaginaSpring.class);
		try {
			MostrarAvisoPaginaSpring mostrarAvisoPaginaSpring = new MostrarAvisoPaginaSpring();
			SpringForward rtrn = mostrarAvisoPaginaSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - mostrarAvisoPagina: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Pago solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param pagoSolicitudForm el pago solicitud form
	 * @return el string
	 */
	@RequestMapping("/pagoSolicitud")
	public String pagoSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			PagoSolicitudForm pagoSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), PagoSolicitudSpring.class);
		try {
			PagoSolicitudSpring pagoSolicitudSpring = new PagoSolicitudSpring();
			SpringForward rtrn = pagoSolicitudSpring.execute(mapping, pagoSolicitudForm, request, response);
			model.addAttribute(STRING_PAGOSOLICITUDFORM, pagoSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - pagoSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Reanudar solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/reanudarSolicitud")
	public String reanudarSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ReanudarSolicitudSpring.class);
		try {
			ReanudarSolicitudSpring reanudarSolicitudSpring = new ReanudarSolicitudSpring();
			SpringForward rtrn = reanudarSolicitudSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - reanudarSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Registro solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param registroSolicitudForm el registro solicitud form
	 * @return el string
	 */
	@RequestMapping("/registroSolicitud")
	public String registroSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			RegistroSolicitudForm registroSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				RegistroSolicitudSpring.class);
		try {
			RegistroSolicitudSpring registroSolicitudSpring = new RegistroSolicitudSpring();
			SpringForward rtrn = registroSolicitudSpring.execute(mapping, registroSolicitudForm, request, response);
			model.addAttribute(STRING_REGISTROSOLICITUDFORM, registroSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - registroSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudForm el solicitud form
	 * @return el string
	 */
	@RequestMapping("/iniciarSolicitud")
	public String solicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudForm solicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), SolicitudSpring.class);
		try {
			SolicitudSpring solicitudSpring = new SolicitudSpring();
			SpringForward rtrn = solicitudSpring.execute(mapping, solicitudForm, request, response);
			model.addAttribute(STRING_SOLICITUDFORM, solicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - iniciarSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Validar.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/validarCP")
	public String validar(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ValidarCPAjax.class);
		try {
			ValidarCPAjax validarCPAjax = new ValidarCPAjax();
			SpringForward rtrn = validarCPAjax.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - validarCP: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver alta solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudForm el solicitud form
	 * @return el string
	 */
	@RequestMapping("/verAltaSolicitud")
	public String verAltaSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudForm solicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerAltaSolicitudSpring.class);
		try {
			VerAltaSolicitudSpring verAltaSolicitudSpring = new VerAltaSolicitudSpring();
			SpringForward rtrn = verAltaSolicitudSpring.execute(mapping, solicitudForm, request, response);
			model.addAttribute(STRING_SOLICITUDFORM, solicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - verAltaSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver pago solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param detalleSolicitudForm el detalle solicitud form
	 * @return el string
	 */
	@RequestMapping("/verPagoSolicitud")
	public String verPagoSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			DetalleSolicitudForm detalleSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerPagoSolicitudSpring.class);
		try {
			VerPagoSolicitudSpring verPagoSolicitudSpring = new VerPagoSolicitudSpring();
			SpringForward rtrn = verPagoSolicitudSpring.execute(mapping, detalleSolicitudForm, request, response);
			model.addAttribute(STRING_DETALLESOLICITUDFORM, detalleSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - verPagoSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver pasarela pago.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param pagoSolicitudForm el pago solicitud form
	 * @return el string
	 */
	@RequestMapping("/verPasarelaPago")
	public String verPasarelaPago(Model model, HttpServletRequest request, HttpServletResponse response,
			PagoSolicitudForm pagoSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerPasarelaPagoSpring.class);
		try {
			VerPasarelaPagoSpring verPasarelaPagoSpring = new VerPasarelaPagoSpring();
			SpringForward rtrn = verPasarelaPagoSpring.execute(mapping, pagoSolicitudForm, request, response);
			model.addAttribute(STRING_PAGOSOLICITUDFORM, pagoSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - verPasarelaPago: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver registro solicitud.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param registroSolicitudForm el registro solicitud form
	 * @return el string
	 */
	@RequestMapping("/verRegistroSolicitud")
	public String verRegistroSolicitud(Model model, HttpServletRequest request, HttpServletResponse response,
			RegistroSolicitudForm registroSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerRegistroSolicitudSpring.class);
		try {
			VerRegistroSolicitudSpring verRegistroSolicitudSpring = new VerRegistroSolicitudSpring();
			SpringForward rtrn = verRegistroSolicitudSpring.execute(mapping, registroSolicitudForm, request, response);
			model.addAttribute(STRING_REGISTROSOLICITUDFORM, registroSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - verRegistroSolicitud: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver solicitud uni.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudForm el solicitud form
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param registroSolicitudForm el registro solicitud form
	 * @return el string
	 */
	@RequestMapping("/verSolicitudUnificado")
	public String verSolicitudUni(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudForm solicitudForm, PagoSolicitudForm pagoSolicitudForm, RegistroSolicitudForm registroSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), VerSolicitudUnificado.class);
		try {
			VerSolicitudUnificado verSolicitudUnificado = new VerSolicitudUnificado();
			SpringForward rtrn = verSolicitudUnificado.execute(mapping, solicitudForm, request, response);
			model.addAttribute(STRING_SOLICITUDFORM, solicitudForm);
			model.addAttribute(STRING_PAGOSOLICITUDFORM, pagoSolicitudForm);
			model.addAttribute(STRING_REGISTROSOLICITUDFORM, registroSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - verSolicitudUnificado: ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Modificar solicitud uni.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudForm el solicitud form
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param registroSolicitudForm el registro solicitud form
	 * @return el string
	 */
	@RequestMapping("/modificarSolicitudUnificado")
	public String modificarSolicitudUni(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudForm solicitudForm, PagoSolicitudForm pagoSolicitudForm, RegistroSolicitudForm registroSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ModificarSolicitudUnificado.class);
		try {
			ModificarSolicitudUnificado modificarSolicitudUnificado = new ModificarSolicitudUnificado();
			SpringForward rtrn = modificarSolicitudUnificado.execute(mapping, solicitudForm, request, response);
			model.addAttribute(STRING_SOLICITUDFORM, solicitudForm);
			model.addAttribute(STRING_PAGOSOLICITUDFORM, pagoSolicitudForm);
			model.addAttribute(STRING_REGISTROSOLICITUDFORM, registroSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - modificarSolicitudUni: ",e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Subsanar solicitud uni.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param solicitudForm el solicitud form
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param registroSolicitudForm el registro solicitud form
	 * @return el string
	 */
	@RequestMapping("/subsanarSolicitudUnificado")
	public String subsanarSolicitudUni(Model model, HttpServletRequest request, HttpServletResponse response,
			SolicitudForm solicitudForm, PagoSolicitudForm pagoSolicitudForm, RegistroSolicitudForm registroSolicitudForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), SubsanarSolicitudUnificado.class);
		try {
			SubsanarSolicitudUnificado subsanarSolicitudUnificado = new SubsanarSolicitudUnificado();
			SpringForward rtrn = subsanarSolicitudUnificado.execute(mapping, solicitudForm, request, response);
			model.addAttribute(STRING_SOLICITUDFORM, solicitudForm);
			model.addAttribute(STRING_PAGOSOLICITUDFORM, pagoSolicitudForm);
			model.addAttribute(STRING_REGISTROSOLICITUDFORM, registroSolicitudForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  SolicitudesController - subsanarSolicitudUni: ",e);
			throw new RuntimeException(e);
		}
	}	
}
