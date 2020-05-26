package es.map.ipsg.action.configuracionErrores;

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
import es.map.ipsg.action.centrogestor.ListarCentroGestorSpring;
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.form.ConfiguracionErroresForm;

@Controller
@RequestMapping("/spring")
public class ConfiguracionErroresController {
	private static final Logger logger = Logger.getLogger(ConfiguracionErroresController.class);
	@Autowired
	SpringMappingManager springMappingManager;
	
	@Autowired
	ApplicationContext applicationContext;

//	@RequestMapping("/actualizarEntidadFinanciera")
//	public String actualizarEntidadFinanciera(Model model, HttpServletRequest request, HttpServletResponse response,
//			SpringForm SpringForm) {
//		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
//				ActualizarEntidadFinancieraSpring.class);
//		try {
//			ActualizarEntidadFinancieraSpring actualizarEntidadFinancieraSpring = new ActualizarEntidadFinancieraSpring();
//			SpringForward rtrn = actualizarEntidadFinancieraSpring.execute(mapping, SpringForm, request,
//					response);
//			model.addAttribute("SpringForm", SpringForm);
//			return rtrn.getPath();
//		} catch (Exception e) {
//			logger.error("Error EntidadFinancieraController - actualizarEntidadFinanciera  :",e);
//			throw new RuntimeException(e);
//		}
//	}

	@RequestMapping("/buscarConfiguracionErrores")
	public String buscarConfiguracionErrores(Model model, HttpServletRequest request, HttpServletResponse response,
			ConfiguracionErroresForm buscarConfiguracionErroresForm) {
		
		FormSessionMapper formSessionMapper = applicationContext.getBean(FormSessionMapper.class);
		buscarConfiguracionErroresForm = formSessionMapper.resolveForm(buscarConfiguracionErroresForm, request);
		
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				BuscarConfiguracionErroresSpring.class);
		try {
			BuscarConfiguracionErroresSpring buscarConfiguracionErroresSpring = new BuscarConfiguracionErroresSpring();
			SpringForward rtrn = buscarConfiguracionErroresSpring.execute(mapping, buscarConfiguracionErroresForm, request,
					response);
			model.addAttribute("buscarConfiguracionErroresForm", buscarConfiguracionErroresForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConfiguracionErroresController - buscarConfiguracionErrores :",e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/crearConfiguracionErrores")
	public String crearConfiguracionErrores(Model model, HttpServletRequest request, HttpServletResponse response,
			ConfiguracionErroresForm configuracionErroresForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				CrearConfiguracionErroresSpring.class);
		try {
			CrearConfiguracionErroresSpring crearConfiguracionErroresSpring = new CrearConfiguracionErroresSpring();
			SpringForward rtrn = crearConfiguracionErroresSpring.execute(mapping, configuracionErroresForm, request,
					response);
			model.addAttribute("configuracionErroresForm", configuracionErroresForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConfiguracionErroresController - crearConfiguracionErrores :",e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/eliminarConfiguracionErrores")
	public String eliminarConfiguracionErrores(Model model, HttpServletRequest request, HttpServletResponse response,
			SpringForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				EliminarConfiguracionErroresSpring.class);
		try {
			EliminarConfiguracionErroresSpring eliminarConfiguracionErroresSpring = new EliminarConfiguracionErroresSpring();
			SpringForward rtrn = eliminarConfiguracionErroresSpring.execute(mapping, springForm, request,
					response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConfiguracionErroresController - eliminarConfiguracionErrores :",e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/modificarConfiguracionErrores")
	public String modificarConfiguracionErrores(Model model, HttpServletRequest request, HttpServletResponse response,
			ConfiguracionErroresForm configuracionErroresForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ModificarConfiguracionErroresSpring.class);
		try {
			ModificarConfiguracionErroresSpring modificarConfiguracionErroresSpring = new ModificarConfiguracionErroresSpring();
			SpringForward rtrn = modificarConfiguracionErroresSpring.execute(mapping, configuracionErroresForm, request,
					response);
			model.addAttribute("configuracionErroresForm", configuracionErroresForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConfiguracionErroresController - modificarConfiguracionErrores :",e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/verCrearConfiguracionErrores")
	public String verCrearConfiguracionErrores(Model model, HttpServletRequest request, HttpServletResponse response,
			ConfiguracionErroresForm springForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerCrearConfiguracionErroresSpring.class);
		try {
			VerCrearConfiguracionErroresSpring verCrearConfiguracionErroresSpring = new VerCrearConfiguracionErroresSpring();
			SpringForward rtrn = verCrearConfiguracionErroresSpring.execute(mapping, springForm, request,
					response);
			model.addAttribute("springForm", springForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConfiguracionErroresController - verCrearConfiguracionErroresSpring :",e);
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/verModificarConfiguracionErrores")
	public String verModificarConfiguracionErrores(Model model, HttpServletRequest request, HttpServletResponse response,
			ConfiguracionErroresForm configuracionErroresForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				VerModificarConfiguracionErroresSpring.class);
		try {
			VerModificarConfiguracionErroresSpring verModificarConfiguracionErroresSpring = new VerModificarConfiguracionErroresSpring();
			SpringForward rtrn = verModificarConfiguracionErroresSpring.execute(mapping, configuracionErroresForm, request,
					response);
			model.addAttribute("configuracionErroresForm", configuracionErroresForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error ConfiguracionErroresController - verModificarConfiguracionErrores :",e);
			throw new RuntimeException(e);
		}
	}
	
}
