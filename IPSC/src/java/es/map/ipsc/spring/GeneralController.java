package es.map.ipsc.spring;

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
import es.map.ipsc.form.BuscaConvocatoriasForm;
/**
 * El Class GeneralController.
 */
@Controller
@RequestMapping("/secure")
public class GeneralController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GeneralController.class);
	
	/** La constante STRING_SPRINGFORM. */
	private static final String STRING_SPRINGFORM = "springForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;


	/**
	 * Certificado.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscaConvocatoriasForm el busca convocatorias form
	 * @return el string
	 */
	@RequestMapping("/certificado")
	public String certificado(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm buscaConvocatoriasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), CertificadoSpring.class);
		try {
			CertificadoSpring certificadoSpring = new CertificadoSpring();
			SpringForward rtrn = certificadoSpring.execute(mapping, buscaConvocatoriasForm, request, response);
			model.addAttribute("buscaConvocatoriasForm", buscaConvocatoriasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error GeneralController - certificado.",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Clave.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/clave")
	public String clave(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ClaveSpring.class);
		try {
			ClaveSpring claveSpring = new ClaveSpring();
			SpringForward rtrn = claveSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error GeneralController - clave.",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Comprobar sesion clave.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param buscaConvocatoriasForm el busca convocatorias form
	 * @return el string
	 */
	@RequestMapping("/comprobarSesionClave")
	public String comprobarSesionClave(Model model, HttpServletRequest request, HttpServletResponse response,
			BuscaConvocatoriasForm buscaConvocatoriasForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), ComprobarSesionClave.class);
		try {
			ComprobarSesionClave comprobarSesionSpring = new ComprobarSesionClave();
			SpringForward rtrn = comprobarSesionSpring.execute(mapping, buscaConvocatoriasForm, request, response);
			model.addAttribute("buscaConvocatoriasForm", buscaConvocatoriasForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GeneralController - comprobarSesionClave.",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Locale.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/locale")
	public String locale(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), LocaleSpring.class);
		try {
			LocaleSpring localeSpring = new LocaleSpring();
			SpringForward rtrn = localeSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GeneralController - locale.",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Solicitud general.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/solicitudGeneral")
	public String solicitudGeneral(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), SolicitudGeneralSpring.class);
		try {
			SolicitudGeneralSpring SolicitudGeneralSpring = new SolicitudGeneralSpring();
			SpringForward rtrn = SolicitudGeneralSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error(" Error GeneralController - solicitudGeneral.",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Tabla comunidades.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param springForm el spring form
	 * @return el string
	 */
	@RequestMapping("/tablaComunidades")
	public String tablaComunidades(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), TablaComunidadesSpring.class);
		try {
			TablaComunidadesSpring tablaComunidadesSpring = new TablaComunidadesSpring();
			SpringForward rtrn = tablaComunidadesSpring.execute(mapping, springForm, request, response);
			model.addAttribute(STRING_SPRINGFORM, springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error GeneralController - tablaComunidades.",e);
			throw new RuntimeException(e);
		}
	}
}
