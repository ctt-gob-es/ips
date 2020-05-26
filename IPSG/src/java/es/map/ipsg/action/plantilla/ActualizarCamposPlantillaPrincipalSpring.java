package es.map.ipsg.action.plantilla;



import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;


/**
 * El Class ActualizarCamposPlantillaPrincipalSpring.
 */
public class ActualizarCamposPlantillaPrincipalSpring extends AbstractSpring {
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarCamposPlantillaPrincipalSpring.class);
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante ADMINISTRACION. */
	private static final Character ADMINISTRACION = 'A';
	
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	

	/**
	 * Crea una nueva actualizar campos plantilla principal spring.
	 */
	public ActualizarCamposPlantillaPrincipalSpring() {		
		try {
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
		} catch (Exception e) {
			logger.debug(e);
			logger.error("Error ActualizarCamposPlantillaPrincipalSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("ActualizarCamposPlantillaPrincipalSpring -start");
		PlantillaForm formulario;
		formulario = (PlantillaForm) form;
		String[] listaIDs = this.getRequest().getParameterValues("listacheckbox");
		String idModelo;
		String idCampoPropio;
		String cadena;
		//Si hay alguno campo propio chequeado
		if(listaIDs!=null){
			//Primero reseteamos todos a false
			//si no hay ninguno chequeado se ponen todos a false para tipo Admin
			PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
			plantillaPropiosQuery.setTipoPlantilla(ADMINISTRACION);
			
			ArrayList<PlantillaPropiosBean> listaPlantillasPropiosBean = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
			
			if(listaPlantillasPropiosBean!=null && listaPlantillasPropiosBean.size()>0){
				for(int i=0;i<listaPlantillasPropiosBean.size();i++){
					//Para los tipo Administracion
					listaPlantillasPropiosBean.get(i).setObligatorio(false);
					PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
					plantillaPropiosBean.setTipoPlantilla(ADMINISTRACION);
					plantillaPropiosBean.setObligatorio(false);
					plantillaPropiosBean.setModeloBean(listaPlantillasPropiosBean.get(i).getModeloBean());
					CamposPropiosBean campoAux = listaPlantillasPropiosBean.get(i).getCampoPropioBean();
					campoAux.setId(Long.valueOf(listaPlantillasPropiosBean.get(i).getCampoPropioBean().getId()));
					plantillaPropiosBean.setCampoPropioBean(campoAux);
					plantillaPropiosBean.setId(listaPlantillasPropiosBean.get(i).getId());
					plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
				}
			}
			

			//luego chequeamos los seleccinados
			for(int i=0;i<listaIDs.length;i++){
				cadena=listaIDs[i];
				int tam = cadena.length();
				int position=cadena.indexOf('_');
				idModelo=cadena.substring(0, position);
				idCampoPropio=cadena.substring(position+1,tam);
				//Para modificar las Plantillas tipo Administrador
				PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
				plantillaPropiosBean.setTipoPlantilla(ADMINISTRACION);
				plantillaPropiosBean.setObligatorio(true);
				ModeloBean modeloBean = new ModeloBean();
				modeloBean.setId(Integer.parseInt(idModelo));
				plantillaPropiosBean.setModeloBean(modeloBean);
				
				//antes de actualizar buscamos que ID tienen para no duplicar valores en BBDD
				 plantillaPropiosQuery = new PlantillaPropiosQuery();
				CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
				camposPropiosQuery.setIdCampo(Integer.parseInt(idCampoPropio));
				plantillaPropiosQuery.setCamposPropios(camposPropiosQuery);
				ModeloQuery modeloQuery = new ModeloQuery();
				modeloQuery.setIdModelo(Integer.parseInt(idModelo));
				plantillaPropiosQuery.setModelo(modeloQuery);
				plantillaPropiosQuery.setTipoPlantilla(ADMINISTRACION);
				ArrayList<PlantillaPropiosBean> listaPlantillaPropiosBean=plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);
				
				plantillaPropiosBean.setId(listaPlantillaPropiosBean.get(0).getId());
				
				CamposPropiosBean campoPropioBean = new CamposPropiosBean();
				campoPropioBean.setId(Long.valueOf(Integer.parseInt(idCampoPropio)));
				plantillaPropiosBean.setCampoPropioBean(campoPropioBean);
				plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);				
				

			}
		}else{
			//si no hay ninguno chequeado se ponen todos a false para tipo Admin
			PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
			plantillaPropiosQuery.setTipoPlantilla(ADMINISTRACION);
			
			ArrayList<PlantillaPropiosBean> listaPlantillasPropiosBean = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
			
			if(listaPlantillasPropiosBean!=null && listaPlantillasPropiosBean.size()>0){
				for(int i=0;i<listaPlantillasPropiosBean.size();i++){
					//Para los tipo Administracion
					listaPlantillasPropiosBean.get(i).setObligatorio(false);
					PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
					plantillaPropiosBean.setTipoPlantilla(ADMINISTRACION);
					plantillaPropiosBean.setObligatorio(false);
					plantillaPropiosBean.setModeloBean(listaPlantillasPropiosBean.get(i).getModeloBean());
					CamposPropiosBean campoAux = listaPlantillasPropiosBean.get(i).getCampoPropioBean();
					campoAux.setId(Long.valueOf(listaPlantillasPropiosBean.get(i).getCampoPropioBean().getId()));
					plantillaPropiosBean.setCampoPropioBean(campoAux);
					plantillaPropiosBean.setId(listaPlantillasPropiosBean.get(i).getId());
					plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
				}
			}
			

		}
		formulario.setLocalidadNacimiento(false);
		formulario.setProvinciaNacimiento(false);
		String submit = formulario.getSubmit();
		try{
			PlantillaBean plantillaBean;
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setTipoPlantilla('A');
			
			plantillaBean = plantillaManager.buscarPlantilla(plantillaQuery);
			Long idPlantillaAdministrador = plantillaBean.getId();
			
			
			// Jugar con plantillas Propios tipo A y ordenados por modelo ya modificados
			PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
			plantillaPropiosQuery.setTipoPlantilla(ADMINISTRACION);
			ArrayList<PlantillaPropiosBean> listaPlantillasPropiosBean=plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
			plantillaBean.setListaPlantillaPropiosBean(listaPlantillasPropiosBean);
			//deberia ser guardar
			if("Guardar".equals(submit)){
				plantillaBean = cargarPlantilla(formulario);
				plantillaBean.setId(idPlantillaAdministrador);
				try{
					plantillaManager.actualizarPlantilla(plantillaBean);
					setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.plantilla.actualizacionCorrecta"));
				}catch(Exception e){
					logger.error("Error ActualizarCamposPlantillaPrincipalSpring - plantilla - error actualizar:",e);
					SpringMessages messages = new SpringMessages();
					messages.add("errorFechaActual", new SpringMessage(
							"field.plantilla.errorActualizar"));
					saveErrors(this.getRequest(), messages);
				}
			}
			getLogger().debug("ActualizarCamposPlantillaPrincipalSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", "Se ha producido un error al actualizar los datos del Formulario");
			logger.error("Error ActualizarCamposPlantillaPrincipalSpring - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Cargar plantilla.
	 *
	 * @param formulario el formulario
	 * @return el plantilla bean
	 */
	private PlantillaBean cargarPlantilla(PlantillaForm formulario) {
		PlantillaBean plantillaAux = new PlantillaBean();
		
		//Se asignan los campos que no aparecen en el formulario
		plantillaAux.setLocalidadNacimiento('N');
		plantillaAux.setProvinciaNacimiento('N');
		plantillaAux.setTipoPlantilla(formulario.getTipoPlantilla().charAt(0));
		
		plantillaAux.setTipoPlantilla('A');
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

	/**
	 * Obtiene el plantilla manager.
	 *
	 * @return el plantilla manager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager el nuevo plantilla manager
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * Obtiene el plantilla propios manager.
	 *
	 * @return el plantilla propios manager
	 */
	public PlantillaPropiosManager getPlantillaPropiosManager() {
		return plantillaPropiosManager;
	}

	/**
	 * Establece el plantilla propios manager.
	 *
	 * @param plantillaPropiosManager el nuevo plantilla propios manager
	 */
	public void setPlantillaPropiosManager(
			PlantillaPropiosManager plantillaPropiosManager) {
		this.plantillaPropiosManager = plantillaPropiosManager;
	}

}
