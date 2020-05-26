package es.map.ipsg.action.cuadroMando;

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
import es.map.ipsg.form.ConsultarCuadroMandoForm;

/**
 * El Class CuadroMandoController.
 */
@Controller
@RequestMapping("/spring")
public class CuadroMandoController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CuadroMandoController.class);
	
	/** La constante STRINGCONSULTARCUADROMANDOFORM. */
	private static final String STRINGCONSULTARCUADROMANDOFORM = "consultarCuadroMandoForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
	/** el application context. */
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Buscar cuadro mando.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultarCuadroMandoForm el consultar cuadro mando form
	 * @return el string
	 */
	@RequestMapping("/buscarCuadroMando")
	public String buscarCuadroMando(Model model, HttpServletRequest request, HttpServletResponse response,
			ConsultarCuadroMandoForm consultarCuadroMandoForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		consultarCuadroMandoForm = formSessionMapper.resolveForm(consultarCuadroMandoForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarCuadroMandoSpring.class);
		try {
			BuscarCuadroMandoSpring buscarCuadroMandoSpring = new BuscarCuadroMandoSpring();
			SpringForward rtrn = buscarCuadroMandoSpring.execute(mapping, consultarCuadroMandoForm, request, response);
			model.addAttribute(STRINGCONSULTARCUADROMANDOFORM, consultarCuadroMandoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error buscarCuadroMando: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Exportar excel cuadro mando.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultarCuadroMandoForm el consultar cuadro mando form
	 * @return el string
	 */
	@RequestMapping("/exportarExcelCuadroMando")
	public String exportarExcelCuadroMando(Model model, HttpServletRequest request, HttpServletResponse response,
			ConsultarCuadroMandoForm consultarCuadroMandoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ExportarExcelCuadroMandoSpring.class);
		try {
			ExportarExcelCuadroMandoSpring exportarExcelCuadroMandoSpring = new ExportarExcelCuadroMandoSpring();
			SpringForward rtrn = exportarExcelCuadroMandoSpring.execute(mapping, consultarCuadroMandoForm, request, response);
			model.addAttribute(STRINGCONSULTARCUADROMANDOFORM, consultarCuadroMandoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error exportarExcelCuadroMando: ",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Ver consultar cuadro mando.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param consultarCuadroMandoForm el consultar cuadro mando form
	 * @return el string
	 */
	@RequestMapping("/verConsultarCuadroMando")
	public String verConsultarCuadroMando(Model model, HttpServletRequest request, HttpServletResponse response,
			ConsultarCuadroMandoForm consultarCuadroMandoForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerConsultarCuadroMandoSpring.class);
		try {
			VerConsultarCuadroMandoSpring verConsultarCuadroMandoSpring = new VerConsultarCuadroMandoSpring();
			SpringForward rtrn = verConsultarCuadroMandoSpring.execute(mapping, consultarCuadroMandoForm, request, response);
			model.addAttribute(STRINGCONSULTARCUADROMANDOFORM, consultarCuadroMandoForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error verConsultarCuadroMando: ",e);
			throw new RuntimeException(e);
		}
	}
}
