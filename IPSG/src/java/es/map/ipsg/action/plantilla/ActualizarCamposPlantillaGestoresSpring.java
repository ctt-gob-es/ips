package es.map.ipsg.action.plantilla;



import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ActualizarCamposPlantillaGestoresSpring.
 */
public class ActualizarCamposPlantillaGestoresSpring extends AbstractSpring {
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarCamposPlantillaGestoresSpring.class);
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	
	/** La constante CENTROGESTOR. */
	private static final Character CENTROGESTOR = 'G';
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
			

	/**
	 * Crea una nueva actualizar campos plantilla gestores spring.
	 */
	public ActualizarCamposPlantillaGestoresSpring() {		
		try {
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ActualizarCamposPlantillaGestoresSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("ActualizarCamposPlantillaGestoresSpring -start");
		
		String flujo = (String)this.getRequest().getSession().getAttribute("flujoAdmin");
		this.getRequest().getSession().removeAttribute("flujoAdmin");
		
		/*INI-TRA042*/
		PlantillaForm formulario = (PlantillaForm) form;
		formulario.setLocalidadNacimiento(false);
		formulario.setProvinciaNacimiento(false);
		String submit = formulario.getAccion();
		
		String[] listaIDs = this.getRequest().getParameterValues("listacheckbox");
		String idModelo;
		String idCampoPropio;
		String cadena;
		try{
			PlantillaBean plantillaBean;
			
			if("Guardar".equals(submit)){
				plantillaBean = cargarPlantilla(formulario);
				try{
					setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
					UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					usuarioBean = recuperarUsuario(usuarioBean.getNif());
					
					//Reseteamos siempre la lista
					PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
					plantillaPropiosQuery.setTipoPlantilla(CENTROGESTOR);
					CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
					centroGestorQuery.setId(formulario.getIdCentroGestor());
					plantillaPropiosQuery.setCentroGestor(centroGestorQuery);
					
					ArrayList<PlantillaPropiosBean> listaPlantillasPropiosBean = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
					
					if(listaPlantillasPropiosBean!=null && listaPlantillasPropiosBean.size()>0){
						for(int i=0;i<listaPlantillasPropiosBean.size();i++){
							listaPlantillasPropiosBean.get(i).setObligatorio(false);
							PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
							plantillaPropiosBean.setTipoPlantilla(CENTROGESTOR);
							plantillaPropiosBean.setObligatorio(false);
							plantillaPropiosBean.setModeloBean(listaPlantillasPropiosBean.get(i).getModeloBean());
							CamposPropiosBean campoAux = listaPlantillasPropiosBean.get(i).getCampoPropioBean();
							campoAux.setId(Long.valueOf(listaPlantillasPropiosBean.get(i).getCampoPropioBean().getId()));
							plantillaPropiosBean.setCampoPropioBean(campoAux);
							plantillaPropiosBean.setId(listaPlantillasPropiosBean.get(i).getId());
							CentroGestorBean centroGestorBean = new CentroGestorBean();
							centroGestorBean.setId(formulario.getIdCentroGestor());
							plantillaPropiosBean.setCentroGestorBean(centroGestorBean);
							plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
						}
					}
					plantillaManager.actualizarPlantilla(plantillaBean);
					
					
					//Si hay algun campo propio chequeado guardamos los campos propios del centro gestor
					if(listaIDs != null){
						for(int i=0;i<listaIDs.length;i++){				
							cadena=listaIDs[i];
							int tam = cadena.length();
							int position=cadena.indexOf('_');
							idModelo=cadena.substring(0, position);
							idCampoPropio=cadena.substring(position+1,tam);
							
							PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
							plantillaPropiosBean.setTipoPlantilla(CENTROGESTOR);
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
							plantillaPropiosQuery.setTipoPlantilla(CENTROGESTOR);
							centroGestorQuery = new CentroGestorQuery();
							centroGestorQuery.setId(formulario.getIdCentroGestor());
							plantillaPropiosQuery.setCentroGestor(centroGestorQuery);
							ArrayList<PlantillaPropiosBean> listaPlantillaPropiosBean=plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);
							
							plantillaPropiosBean.setId(listaPlantillaPropiosBean.get(0).getId());
							CentroGestorBean centroGestorBean = new CentroGestorBean();
							centroGestorBean.setId(formulario.getIdCentroGestor());
							plantillaPropiosBean.setCentroGestorBean(centroGestorBean);
							
							CamposPropiosBean campoPropioBean = new CamposPropiosBean();
							campoPropioBean.setId(Long.valueOf(Integer.parseInt(idCampoPropio)));
							plantillaPropiosBean.setCampoPropioBean(campoPropioBean);
							plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
						}
					}else{
						//Si no llegan ninguno, chequear todos a false
						plantillaPropiosQuery = new PlantillaPropiosQuery();
						plantillaPropiosQuery.setTipoPlantilla(CENTROGESTOR);
						centroGestorQuery = new CentroGestorQuery();
						centroGestorQuery.setId(formulario.getIdCentroGestor());
						plantillaPropiosQuery.setCentroGestor(centroGestorQuery);
						
						listaPlantillasPropiosBean = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
						
						if(listaPlantillasPropiosBean!=null && listaPlantillasPropiosBean.size()>0){
							for(int i=0;i<listaPlantillasPropiosBean.size();i++){
								listaPlantillasPropiosBean.get(i).setObligatorio(false);
								PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
								plantillaPropiosBean.setTipoPlantilla(CENTROGESTOR);
								plantillaPropiosBean.setObligatorio(false);
								plantillaPropiosBean.setModeloBean(listaPlantillasPropiosBean.get(i).getModeloBean());
								CamposPropiosBean campoAux = listaPlantillasPropiosBean.get(i).getCampoPropioBean();
								campoAux.setId(Long.valueOf(listaPlantillasPropiosBean.get(i).getCampoPropioBean().getId()));
								plantillaPropiosBean.setCampoPropioBean(campoAux);
								plantillaPropiosBean.setId(listaPlantillasPropiosBean.get(i).getId());
								CentroGestorBean centroGestorBean = new CentroGestorBean();
								centroGestorBean.setId(formulario.getIdCentroGestor());
								plantillaPropiosBean.setCentroGestorBean(centroGestorBean);
								plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
							}
						}
					}
					plantillaManager.actualizarPlantilla(plantillaBean);
				}catch(Exception e){
					logger.error("Error ActualizarCamposPlantillaGestoresSpring - plantilla - error actualizar:",e);
					SpringMessages messages = new SpringMessages();
					messages.add("errorFechaActual", new SpringMessage(
							"field.plantilla.errorActualizar"));
					saveErrors(this.getRequest(), messages);
				}
			}
			/*FIN-TRA042*/
			
			getLogger().debug("ActualizarCamposPlantillaGestoresSpring -end");
			
			if (flujo != null && flujo.equals("S")) {
				formulario.setSubmit("Buscar");
				String mensaje = RESOURCE_BUNDLE.getString("field.plantilla.actualizacionCorrecta");
				String titulo = RESOURCE_BUNDLE.getString("field.plantilla.tituloCentroGestor");
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarCentroGestor");
				return "success2";
			}
			return "success";
		}catch (Exception e){
			logger.warn(e);
			logger.error("Error ActualizarCamposPlantillaGestoresSpring - doExecute:",e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ActualizarCamposPlantillaGestoresSpring:",e);
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
	
	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Recuperar usuario.
	 *
	 * @param username el username
	 * @return el usuario bean
	 */
	private UsuarioBean recuperarUsuario(String username){
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(username);
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		return usuarioBean;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
