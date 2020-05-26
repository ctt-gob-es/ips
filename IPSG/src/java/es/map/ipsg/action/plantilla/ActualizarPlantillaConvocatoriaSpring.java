package es.map.ipsg.action.plantilla;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.manager.PlantillaManager;

public class ActualizarPlantillaConvocatoriaSpring extends AbstractSpring{

	private PlantillaManager plantillaManager;
	private static final Logger logger = Logger.getLogger(PlantillaGestionSpring.class);
	private static final String BUNDLE_RESOURCE = "MessageResources";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	public static Logger getLogger() {
		return logger;
	}

	public ActualizarPlantillaConvocatoriaSpring(){
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
	}
	
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("ActualizarPlantillaConvocatoria -start");
		try {	        
			PlantillaBean plantillaBean;
			PlantillaForm formulario = (PlantillaForm) form;
			/*OBTENER DATOS DEL FORMULARIO Y CARGARLOS EN EL BEAN*/
			plantillaBean = this.cargarPlantilla(formulario);
			/*GUARDO LA PLANTILLA*/
			plantillaManager.actualizarPlantilla(plantillaBean);
			setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.plantilla.actualizacionCorrecta"));
			getLogger().debug("ActualizarPlantillaConvocatoria -end");
			return "success";
		} catch (Exception e) {
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ActualizarPlantillaConvocatoriaSpring - actualizar plantilla :",e);
			return "nosuccess";
		}
	}
	
	private PlantillaBean cargarPlantilla(PlantillaForm formulario) {
		PlantillaBean plantillaAux = new PlantillaBean();
		
		//Se asignan los campos que no aparecen en el formulario
		plantillaAux.setLocalidadNacimiento('N');
		plantillaAux.setProvinciaNacimiento('N');
		plantillaAux.setTipoPlantilla(formulario.getTipoPlantilla().charAt(0));
		
		plantillaAux.setId(formulario.getId());
		plantillaAux.setNif(formulario.isNif()== true?'S':'N');
		plantillaAux.setPrimerApellido(formulario.isPrimerApellido()== true?'S':'N');
		plantillaAux.setSegundoApellido(formulario.isSegundoApellido()== true?'S':'N');
		plantillaAux.setNombre(formulario.isNombre()== true?'S':'N');
		plantillaAux.setFechaNacimiento(formulario.isFechaNacimiento()== true?'S':'N');
		plantillaAux.setSexo(formulario.isSexo()== true?'S':'N');
		plantillaAux.setCorreoElectronico(formulario.isCorreoElectronico()== true?'S':'N');
		plantillaAux.setNacionalidad(formulario.isNacionalidad()== true?'S':'N');
		plantillaAux.setTelefono(formulario.isTelefono()== true?'S':'N');
		plantillaAux.setVia(formulario.isVia()== true?'S':'N');
		plantillaAux.setCodigoPostal(formulario.isCodigoPostal()== true?'S':'N');
		plantillaAux.setMunicipio(formulario.isMunicipio()== true?'S':'N');
		plantillaAux.setProvincia(formulario.isProvincia()== true?'S':'N');
		plantillaAux.setPais(formulario.isPais()== true?'S':'N');
		plantillaAux.setCuerpo(formulario.isCuerpo()== true?'S':'N');
		plantillaAux.setEspecialidad(formulario.isEspecialidad()== true?'S':'N');
		plantillaAux.setFormaacceso(formulario.isFormaacceso()== true?'S':'N');
		plantillaAux.setEntidadConvocante(formulario.isEntidadConvocante()== true?'S':'N');
		plantillaAux.setFechaBoe(formulario.isFechaBoe()== true?'S':'N');
		plantillaAux.setProvinciaExamen(formulario.isProvinciaExamen()== true?'S':'N');
		plantillaAux.setPorcentajeDiscapacidad(formulario.isPorcentajeDiscapacidad()== true?'S':'N');
		plantillaAux.setTipoDiscapacidad(formulario.isTipoDiscapacidad()== true?'S':'N');
		plantillaAux.setReservaDiscapacidad(formulario.isReservaDiscapacidad()== true?'S':'N');
		plantillaAux.setDetalleDiscapacidad(formulario.isDetalleDiscapacidad()== true?'S':'N');
		plantillaAux.setTitulosExigidos(formulario.isTitulosExigidos()== true?'S':'N');
		plantillaAux.setOtrosTitulos(formulario.isOtrosTitulos()== true?'S':'N');
		plantillaAux.setDatosA(formulario.isDatosA()== true?'S':'N');
		plantillaAux.setDatosB(formulario.isDatosB()== true?'S':'N');
		plantillaAux.setDatosC(formulario.isDatosC()== true?'S':'N');
		plantillaAux.setCodigoCuerpoEscala(formulario.isCodigoCuerpoEscala() == true?'S':'N');
		plantillaAux.setCodigoEspecialidad(formulario.isCodigoEspecialidad() == true?'S':'N');
		plantillaAux.setCodigoMinisterio(formulario.isCodigoMinisterio() == true?'S':'N');
		plantillaAux.setCodigoPaisDomicilio(formulario.isCodigoPaisDomicilio() == true?'S':'N');
		plantillaAux.setCodigoProvinciaDomicilio(formulario.isCodigoProvinciaDomicilio() == true?'S':'N');
		plantillaAux.setCodigoProvinciaExamen(formulario.isCodigoProvinciaExamen() == true?'S':'N');
		plantillaAux.setCodigoTituloOficial(formulario.isCodigoTituloOficial() == true?'S':'N');
		
		return plantillaAux;
	}

	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
	}

}
