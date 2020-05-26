package es.map.ipsg.action.convocatoria;

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
import es.map.ipsg.action.FormSessionMapper;
import es.map.ipsg.form.BuscaConvocatoriasForm;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.form.CrearDocumentoForm;

/**
 * El Class ConvocatoriaController.
 */
@Controller
@RequestMapping("/spring")
public class ConvocatoriaController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger( ConvocatoriaController.class);
	
	/** La constante STRING_CONVOCATORIA_FORM. */
	private static final String STRING_CONVOCATORIA_FORM = "convocatoriaForm";
	
	/** La constante STRING_CREAR_CONVOCATORIA_FORM. */
	private static final String STRING_CREAR_CONVOCATORIA_FORM = "crearConvocatoriaForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Actualizar estado convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscaConvocatoriasForm el busca convocatorias form
	 * @return el string
	 */
	@RequestMapping("/ActualizarEstadoConvocatoria")
	public String actualizarEstadoConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm buscaConvocatoriasForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscaConvocatoriasForm = formSessionMapper.resolveForm(buscaConvocatoriasForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarEstadoConvocatoriaSpring.class);
		try {
			ActualizarEstadoConvocatoriaSpring actualizarEstadoConvocatoriaSpring = new ActualizarEstadoConvocatoriaSpring();
			SpringForward rtrn = actualizarEstadoConvocatoriaSpring.execute(mapping, buscaConvocatoriasForm, request,
					response);
			model.addAttribute("buscaConvocatoriasForm", buscaConvocatoriasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - actualizarEstadoConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Aprobar convocatoria seleccionada.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param convocatoriaForm el convocatoria form
	 * @return el string
	 */
	@RequestMapping("/aprobarConvocatoriaSeleccionada")
	public String aprobarConvocatoriaSeleccionada(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm convocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				AprobarConvocatoriaSeleccionadaSpring.class);
		try {
			AprobarConvocatoriaSeleccionadaSpring aprobarConvocatoriaSeleccionadaSpring = new AprobarConvocatoriaSeleccionadaSpring();
			SpringForward rtrn = aprobarConvocatoriaSeleccionadaSpring.execute(mapping, convocatoriaForm, request,
					response);
			model.addAttribute(STRING_CONVOCATORIA_FORM, convocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - aprobarConvocatoriaSeleccionada: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Borrar documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param convocatoriaForm el convocatoria form
	 * @return el string
	 */
	@RequestMapping("/borrarDocumento")
	public String borrarDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm convocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), BorrarDocumentoSpring.class);
		try {
			BorrarDocumentoSpring borrarDocumentoSpring = new BorrarDocumentoSpring();
			SpringForward rtrn = borrarDocumentoSpring.execute(mapping, convocatoriaForm, request, response);
			model.addAttribute(STRING_CONVOCATORIA_FORM, convocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - borrarDocumento: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Buscar convocatorias.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscaConvocatoriasForm el busca convocatorias form
	 * @return el string
	 */
	@RequestMapping("/buscarConvocatorias")
	public String buscarConvocatorias(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm buscaConvocatoriasForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscaConvocatoriasForm = formSessionMapper.resolveForm(buscaConvocatoriasForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarConvocatoriasSpring.class);
		try {
			BuscarConvocatoriasSpring buscarConvocatoriasSpring = new BuscarConvocatoriasSpring();
			SpringForward rtrn = buscarConvocatoriasSpring.execute(mapping, buscaConvocatoriasForm, request, response);
			model.addAttribute("buscaConvocatoriasForm", buscaConvocatoriasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - buscarConvocatorias: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Copiar convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param convocatoriaForm el convocatoria form
	 * @return el string
	 */
	@RequestMapping("/copiarConvocatoria")
	public String copiarConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm convocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CopiarConvocatoriaSpring.class);
		try {
			CopiarConvocatoriaSpring copiarConvocatoriaSpring = new CopiarConvocatoriaSpring();
			SpringForward rtrn = copiarConvocatoriaSpring.execute(mapping, convocatoriaForm, request, response);
			model.addAttribute(STRING_CONVOCATORIA_FORM, convocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - copiarConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Crear convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearConvocatoriaForm el crear convocatoria form
	 * @return el string
	 */
	@RequestMapping("/crearConvocatoria")
	public String crearConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearConvocatoriaForm crearConvocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearConvocatoriaSpring.class);
		try {
			CrearConvocatoriaSpring crearConvocatoriaSpring = new CrearConvocatoriaSpring();
			SpringForward rtrn = crearConvocatoriaSpring.execute(mapping, crearConvocatoriaForm, request, response);
			model.addAttribute(STRING_CREAR_CONVOCATORIA_FORM, crearConvocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - crearConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Desactivar convocatorias seleccionadas.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param convocatoriaForm el convocatoria form
	 * @return el string
	 */
	@RequestMapping("/desactivarConvocatoriasSeleccionadas")
	public String desactivarConvocatoriasSeleccionadas(Model model, HttpServletRequest request,
			HttpServletResponse response, BuscaConvocatoriasForm convocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DesactivarConvocatoriasSeleccionadasSpring.class);
		try {
			DesactivarConvocatoriasSeleccionadasSpring desactivarConvocatoriasSeleccionadasSpring = new DesactivarConvocatoriasSeleccionadasSpring();
			SpringForward rtrn = desactivarConvocatoriasSeleccionadasSpring.execute(mapping, convocatoriaForm, request,
					response);
			model.addAttribute(STRING_CONVOCATORIA_FORM, convocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - desactivarConvocatoriasSeleccionadas: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Descargar documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param convocatoriaForm el convocatoria form
	 * @return el string
	 */
	@RequestMapping("/descargarDocumentoAdjunto")
	public String descargarDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm convocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DescargarDocumentoSpring.class);
		try {
			DescargarDocumentoSpring descargarDocumentoSpring = new DescargarDocumentoSpring();
			SpringForward rtrn = descargarDocumentoSpring.execute(mapping, convocatoriaForm, request, response);
			model.addAttribute(STRING_CONVOCATORIA_FORM, convocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - descargarDocumento: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Detalle convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscarConvocatoriaForm el buscar convocatoria form
	 * @return el string
	 */
	@RequestMapping("/detalleConvocatoria")
	public String detalleConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm buscarConvocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DetalleConvocatoriaSpring.class);
		try {
			DetalleConvocatoriaSpring detalleConvocatoriaSpring = new DetalleConvocatoriaSpring();
			SpringForward rtrn = detalleConvocatoriaSpring.execute(mapping, buscarConvocatoriaForm, request, response);
			model.addAttribute("buscarConvocatoriaForm", buscarConvocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - detalleConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Documentos convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/documentosConvocatoria")
	public String documentosConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				DocumentosConvocatoriaSpring.class);
		try {
			DocumentosConvocatoriaSpring documentosConvocatoriaSpring = new DocumentosConvocatoriaSpring();
			SpringForward rtrn = documentosConvocatoriaSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute("crearDocumentoForm", crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - documentosConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Listar convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearConvocatoriaForm el crear convocatoria form
	 * @return el string
	 */
	@RequestMapping("/listarConvocatoria")
	public String listarConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearConvocatoriaForm crearConvocatoriaForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		crearConvocatoriaForm = formSessionMapper.resolveForm(crearConvocatoriaForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ListarConvocatoriaSpring.class);
		try {
			ListarConvocatoriaSpring listarConvocatoriaSpring = new ListarConvocatoriaSpring();
			SpringForward rtrn = listarConvocatoriaSpring.execute(mapping, crearConvocatoriaForm, request, response);
			model.addAttribute(STRING_CREAR_CONVOCATORIA_FORM, crearConvocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - listarConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Modificar convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearConvocatoriaForm el crear convocatoria form
	 * @return el string
	 */
	@RequestMapping("/modificarConvocatoria")
	public String modificarConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearConvocatoriaForm crearConvocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarConvocatoriaSpring.class);
		try {
			ModificarConvocatoriaSpring modificarConvocatoriaSpring = new ModificarConvocatoriaSpring();
			SpringForward rtrn = modificarConvocatoriaSpring.execute(mapping, crearConvocatoriaForm, request, response);
			model.addAttribute(STRING_CREAR_CONVOCATORIA_FORM, crearConvocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - modificarConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Obtener tarifa convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearConvocatoriaForm el crear convocatoria form
	 * @return el string
	 */
	@RequestMapping("/obtenerTarifaConvocatoria")
	public String obtenerTarifaConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearConvocatoriaForm crearConvocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ObtenerTarifaConvocatoriaSpring.class);
		try {
			ObtenerTarifaConvocatoriaSpring obtenerTarifaConvocatoriaSpring = new ObtenerTarifaConvocatoriaSpring();
			SpringForward rtrn = obtenerTarifaConvocatoriaSpring.execute(mapping, crearConvocatoriaForm, request, response);
			model.addAttribute(STRING_CREAR_CONVOCATORIA_FORM, crearConvocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - obtenerTarifaConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Subir documento.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearDocumentoForm el crear documento form
	 * @return el string
	 */
	@RequestMapping("/subirDocumento")
	public String subirDocumento(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearDocumentoForm crearDocumentoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), SubirDocumentoSpring.class);
		try {
			SubirDocumentoSpring subirDocumentoSpring = new SubirDocumentoSpring();
			SpringForward rtrn = subirDocumentoSpring.execute(mapping, crearDocumentoForm, request, response);
			model.addAttribute("crearDocumentoForm", crearDocumentoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - subirDocumento: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver crear convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearConvocatoriaForm el crear convocatoria form
	 * @return el string
	 */
	@RequestMapping("/verCrearConvocatoria")
	public String verCrearConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearConvocatoriaForm crearConvocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearConvocatoriaSpring.class);
		try {
			VerCrearConvocatoriaSpring verCrearConvocatoriaSpring = new VerCrearConvocatoriaSpring();
			SpringForward rtrn = verCrearConvocatoriaSpring.execute(mapping, crearConvocatoriaForm, request, response);
			model.addAttribute(STRING_CREAR_CONVOCATORIA_FORM, crearConvocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - verCrearConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver modificar convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param crearConvocatoriaForm el crear convocatoria form
	 * @return el string
	 */
	@RequestMapping("/verModificarConvocatoria")
	public String verModificarConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			CrearConvocatoriaForm crearConvocatoriaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarConvocatoriaSpring.class);
		try {
			VerModificarConvocatoriaSpring verModificarConvocatoriaSpring = new VerModificarConvocatoriaSpring();
			SpringForward rtrn = verModificarConvocatoriaSpring.execute(mapping, crearConvocatoriaForm, request, response);
			model.addAttribute(STRING_CREAR_CONVOCATORIA_FORM, crearConvocatoriaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error  ConvocatoriaController - verModificarConvocatoria: " ,e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Publicar subsanar.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscaConvocatoriasForm el busca convocatorias form
	 * @return el string
	 */
	@RequestMapping("/publicarSubsanar")
	public String publicarSubsanar(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm buscaConvocatoriasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				PublicarSubsanarConvocatoriaSpring.class);
		try {
			PublicarSubsanarConvocatoriaSpring publicarSubsanarSpring = new PublicarSubsanarConvocatoriaSpring();
			SpringForward rtrn = publicarSubsanarSpring.execute(mapping, buscaConvocatoriasForm, request, response);
			model.addAttribute("buscaConvocatoriasForm", buscaConvocatoriasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConvocatoriasController - buscarConvocatorias ",e);
			throw new RuntimeException(e);
		}
	}
}
