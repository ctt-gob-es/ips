package es.map.ipsg.action.plantilla;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.map.ips.common.spring.SpringForward;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.form.PlantillaForm;

/**
 * El Class PlantillaController.
 */
@Controller
@RequestMapping("/spring")
public class PlantillaController {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PlantillaController.class);
	
	/** La constante STRING_PLANTILLAFORM. */
	private static final String STRING_PLANTILLAFORM = "plantillaForm";
	
	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;

	/**
	 * Actualizar campos plantilla gestores.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param plantillaForm el plantilla form
	 * @return el string
	 */
	@RequestMapping("/actualizarPlantillaGestion")
	public String actualizarCamposPlantillaGestores(Model model, HttpServletRequest request,
			HttpServletResponse response, PlantillaForm plantillaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarCamposPlantillaGestoresSpring.class);
		try {
			ActualizarCamposPlantillaGestoresSpring actualizarCamposPlantillaGestoresSpring = new ActualizarCamposPlantillaGestoresSpring();
			SpringForward rtrn = actualizarCamposPlantillaGestoresSpring.execute(mapping, plantillaForm, request,
					response);
			model.addAttribute(STRING_PLANTILLAFORM, plantillaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error PlantillaController - actualizarPlantillaGestion :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Actualizar campos plantilla principal.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param plantillaForm el plantilla form
	 * @return el string
	 */
	@RequestMapping("/actualizarPlantilla")
	public String actualizarCamposPlantillaPrincipal(Model model, HttpServletRequest request,
			HttpServletResponse response, PlantillaForm plantillaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarCamposPlantillaPrincipalSpring.class);
		try {
			ActualizarCamposPlantillaPrincipalSpring actualizarCamposPlantillaPrincipalSpring = new ActualizarCamposPlantillaPrincipalSpring();
			SpringForward rtrn = actualizarCamposPlantillaPrincipalSpring.execute(mapping, plantillaForm, request,
					response);
			model.addAttribute(STRING_PLANTILLAFORM, plantillaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error PlantillaController - actualizarPlantilla :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Actualizar plantilla convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param plantillaForm el plantilla form
	 * @return el string
	 */
	@RequestMapping("/actualizarPlantillaConvocatoria")
	public String actualizarPlantillaConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			PlantillaForm plantillaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				ActualizarPlantillaConvocatoriaSpring.class);
		try {
			ActualizarPlantillaConvocatoriaSpring actualizarPlantillaConvocatoriaSpring = new ActualizarPlantillaConvocatoriaSpring();
			SpringForward rtrn = actualizarPlantillaConvocatoriaSpring.execute(mapping, plantillaForm, request,
					response);
			model.addAttribute(STRING_PLANTILLAFORM, plantillaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error PlantillaController - actualizarPlantillaConvocatoria :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Plantilla convocatoria.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param plantillaForm el plantilla form
	 * @return el string
	 */
	@RequestMapping("/plantillaConvocatoria")
	public String plantillaConvocatoria(Model model, HttpServletRequest request, HttpServletResponse response,
			PlantillaForm plantillaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				PlantillaConvocatoriaSpring.class);
		try {
			PlantillaConvocatoriaSpring plantillaConvocatoriaSpring = new PlantillaConvocatoriaSpring();
			SpringForward rtrn = plantillaConvocatoriaSpring.execute(mapping, plantillaForm, request, response);
			model.addAttribute(STRING_PLANTILLAFORM, plantillaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error PlantillaController - plantillaConvocatoria :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Plantilla gestion.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param plantillaForm el plantilla form
	 * @return el string
	 */
	@RequestMapping("/plantillaGestion")
	public String plantillaGestion(Model model, HttpServletRequest request, HttpServletResponse response,
			PlantillaForm plantillaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(), PlantillaGestionSpring.class);
		try {
			PlantillaGestionSpring plantillaGestionSpring = new PlantillaGestionSpring();
			SpringForward rtrn = plantillaGestionSpring.execute(mapping, plantillaForm, request, response);
			model.addAttribute(STRING_PLANTILLAFORM, plantillaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error PlantillaController - plantillaGestion :",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Plantilla principal.
	 *
	 * @param model el model
	 * @param request el request
	 * @param response el response
	 * @param plantillaForm el plantilla form
	 * @return el string
	 */
	@RequestMapping("/plantilla")
	public String plantillaPrincipal(Model model, HttpServletRequest request, HttpServletResponse response,
			PlantillaForm plantillaForm) {
		SpringMapping mapping = springMappingManager.getMapping(request.getServletPath(),
				PlantillaPrincipalSpring.class);
		try {
			PlantillaPrincipalSpring plantillaPrincipalSpring = new PlantillaPrincipalSpring();
			SpringForward rtrn = plantillaPrincipalSpring.execute(mapping, plantillaForm, request, response);
			model.addAttribute(STRING_PLANTILLAFORM, plantillaForm);
			return rtrn.getPath();
		} catch (Exception e) {
			logger.error("Error PlantillaController - plantilla :",e);
			throw new RuntimeException(e);
		}
	}
}
