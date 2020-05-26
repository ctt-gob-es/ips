package es.map.ipsc.spring.modelo790;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.springframework.util.StringUtils;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.DatosPropiosConfiguracion;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ips.model.Grupo;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.PaisQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.CentroGestorBean;
import es.map.ipsc.bean.ComunidadesBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.CuerpoEscalaBean;
import es.map.ipsc.bean.EspecialidadBean;
import es.map.ipsc.bean.FormaAccesoBean;
import es.map.ipsc.bean.MinisterioBean;
import es.map.ipsc.bean.PaisBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.bean.PlantillaBean;
import es.map.ipsc.bean.PlantillaPropiosBean;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.bean.ProvinciaExamenBean;
import es.map.ipsc.bean.TipoDiscapacidadBean;
import es.map.ipsc.bean.TituloOficialBean;
import es.map.ipsc.form.Formulario790Form;
import es.map.ipsc.manager.camposPropios.CamposPropiosManager;
import es.map.ipsc.manager.comunidades.ComunidadesManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.datosPropiosConfiguracion.DatosPropiosConfiguracionManager;
import es.map.ipsc.manager.especialidades.EspecialidadManager;
import es.map.ipsc.manager.formulario790.Formulario790Manager;
import es.map.ipsc.manager.ministerio.MinisterioManager;
import es.map.ipsc.manager.modelo.ModeloManager;
import es.map.ipsc.manager.pais.PaisManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.plantilla.PlantillaPropiosManager;
import es.map.ipsc.manager.provincia.ProvinciaExamenManager;
import es.map.ipsc.manager.provincia.ProvinciaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.manager.tituloOficial.TituloOficialManager;

/**
 * El Class DescargarFormulario790Spring.
 */
public class DescargarFormulario790Spring extends AbstractSpring {
	
	/** el tabla base manager. */
	private TablaBaseManager tablaBaseManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el solicitud manager. */
	private SolicitudManager solicitudManager;
	
	/** el formulario 790 manager. */
	private Formulario790Manager formulario790Manager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;	
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el provincia examen manager. */
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el pais manager. */
	private PaisManager paisManager;
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el comunidades manager. */
	private ComunidadesManager comunidadesManager;
	
	/** el motivo reduccion manager. */
	private MotivoReduccionManager motivoReduccionManager;
	
	/** el datos propios configuracion manager. */
	private DatosPropiosConfiguracionManager datosPropiosConfiguracionManager;
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_CAMPOSPROPIOS. */
	private static final String STRING_CAMPOSPROPIOS = "camposPropios";
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarFormulario790Spring.class);
	
	/** el param bean. */
	private static ParametrosConfiguracionBean paramBean;
	
	/**
	 * Crea una nueva descargar formulario 790 spring.
	 */
	public DescargarFormulario790Spring() {
		try {
			setTablaBaseManager((TablaBaseManager) getBean ("tablasBaseManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));
			setFormulario790Manager((Formulario790Manager) getBean("formulario790Manager"));
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerpoEscalaManager"));
			setEspecialidadManager((EspecialidadManager) getBean("especialidadesManager"));
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
			setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
			setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
			setMinisterioManager((MinisterioManager) getBean("ministerioManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			setPaisManager((PaisManager) getBean("paisManager"));
			setCamposPropiosManager((CamposPropiosManager) getBean("camposPropiosManager"));
			setModeloManager((ModeloManager) getBean("modeloManager"));
			setComunidadesManager((ComunidadesManager) getBean("comunidadesManager"));
			setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));
			setDatosPropiosConfiguracionManager((DatosPropiosConfiguracionManager) getBean("datosPropiosConfiguracionManager"));
			properties = (Properties) getBean("propertiesBean");
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error Descargar Formulario 790",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_convocatoria = RESOURCE_MESSAGE.getString("field.menu.descargarFormulario");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		//****************************************************************** 

		String url = this.getRequest().getRequestURI();
		String direccion =url.substring(url.lastIndexOf('/') + 1);
		
		String idConvocatoria = this.getRequest().getParameter("id");
		// Comprobamos si accedemos desde la url de la descarga del formulario sin id
		if(!direccion.equals(Constantes.MODELOENBLANCO) && idConvocatoria == null){
			return "errorConvocatoria";
		//Comprobamos si se ha puesto en la url modelo790enBlanco, acompañado de id, lo que será un error
		}else if(direccion.equals(Constantes.MODELOENBLANCO) && idConvocatoria != null){
			return "errorConvocatoria";
		// Entramos sólo desde la aplicación o con la url modelo790enBlanco sin id
		}else{
			try{
				logger.info("DescargarFormulario790Spring - start");
				String numeroJustificante="";
				String modelo = "";
				Formulario790Form formulario = (Formulario790Form) form;

				// TODO ACCESO EXTERNO AL FORMULARIO
				String idiomaExt = this.getRequest().getParameter("idioma");
				String modeloExt = this.getRequest().getParameter("modelo");
				if(null!=idiomaExt && !"".equals(idiomaExt) && null!=modeloExt && !"".equals(modeloExt)){

					// TODO Petición Justicia 03/12/2015


				}else{
					// Si la petición procede del enlace de descarga de formularios
					modelo = formulario.getNumModelo();

					// Si la petición procede de la búsqueda de convocatorias
					// hay que obtener el modelo a partir del id de convocatoria
					if(modelo==null){
						if(formulario.getId()!=null){
							ConvocatoriaBean convocatoriaBean = convocatoriasManager.recuperarConvocatoria(formulario.getId());
							if(convocatoriaBean!=null){
								modelo = convocatoriaBean.getNumModelo();
							}else{
								// Si la petición procede del enlace directo IPSC/Modelo790
								modelo = Constantes.MODELO790001;
							}
						}else{
							modelo = Constantes.MODELO790001;
						}		
					}
				}

				boolean is007 = false;

				try{

					// TODO (AG03-RE011) Solamente existe la opción de poder generar el nº de solicitud a través del EJB
					// para el modelo 790001. Para el resto, lo genera IPS.
					if(!modelo.equals(Constantes.MODELO_ASOCIADO_GENERACION_EJB)){
						is007 = true;
					}

					// Solo se generara el numero cuando se haya pulsado el boton de 'Generar Solicitud'
					// del formulario y no con anterioridad. Asi se evita la reutilizacion del mismo
					numeroJustificante = " ";

				}catch(Exception e){
					logger.error("Error Descargar Formulario 790 - doExecute",e);
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("field.numeroSolicitud.error"));	
					return "error";		
				}

				
				String codigoTasa = modelo.substring(3);
				this.getRequest().setAttribute("codigoTasa", codigoTasa);
				String s_codigoConvocatoria = (String)this.getRequest().getParameter("id");
				Calendar calendario = Calendar.getInstance();
				StringBuffer fechaFirma = new StringBuffer();
				Long codigoConvocatoria = 0L;
				Boolean codMinisterio = false,codCuerpoEscala = false,codEspecialidad = false;
				Boolean codTituloOficial = false,codPaisDomicilio = false,codProvinciaDomicilio = false,codProvinciaExamen = false;
				ConvocatoriaBean convocatoria = new ConvocatoriaBean();

				try{
					if(!StringUtils.isEmpty(s_codigoConvocatoria)){
						logger.info("Codigo de Convocatoria: "+s_codigoConvocatoria);
						codigoConvocatoria = Long.valueOf(s_codigoConvocatoria);
					}else{
						logger.info("Codigo de Convocatoria nulo");
					}
				}catch(Exception e){
					logger.error("Error codigo  convocatoria",e);
				}

				//Carga de los datos que se mostraran en listas de seleccion en el formulario en blanco
				formulario.setNumeroJustificante(numeroJustificante);
				formulario.setCodigoTasa(codigoTasa);
				
				formulario.setNumModelo(modelo);

				fechaFirma.append("a ").append(Integer.toString(calendario.get(Calendar.DAY_OF_MONTH)))
				.append(" de ").append(this.getMes(calendario.get(Calendar.MONTH)))
				.append(" de ").append(Integer.toString(calendario.get(Calendar.YEAR)));

				formulario.setFechaFirma(fechaFirma.toString());

				cargarCombos(modelo);

				if (codigoConvocatoria==0){
					formulario.setAnioConvocatoria(Long.parseLong(Integer.toString(calendario.get(Calendar.YEAR))));
					cargarCombosConvocatoria(modelo);
					ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
					paramConfQuery.setNombreCampo(Constantes.PARAMETROS_PORCENTAJE_DISCAPACIDAD);
					ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);
					formulario.setPorcentajeMinDiscapacidad(parametrosConfiguracion.getValorCampo());

					PlantillaBean plantilla;
					PlantillaBean noOblplantilla;
					// se busca la plantilla general
					PlantillaQuery plantillaQuery = new PlantillaQuery();
					plantillaQuery.setTipoPlantilla('A');
					plantilla = plantillaManager.buscarPlantilla(plantillaQuery);
					noOblplantilla= cambiarNoObligatorio(plantilla);
					setRequestAttribute("plantilla",noOblplantilla);
					// se obtiene la visibilidad de los codigos precargables
					if(plantilla.getCodigoCuerpoEscala().equals('S'))
					{
						codCuerpoEscala = true;
					}
					else
					{
						codCuerpoEscala = false;
					}	

					if(plantilla.getCodigoEspecialidad().equals('S'))
					{
						codEspecialidad = true;
					}
					else
					{
						codEspecialidad = false;
					}	

					if(plantilla.getCodigoMinisterio().equals('S'))
					{
						codMinisterio = true;
					}
					else
					{
						codMinisterio = false;
					}	

					if(plantilla.getCodigoPaisDomicilio().equals('S'))
					{
						codPaisDomicilio = true;
					}
					else
					{
						codPaisDomicilio = false;
					}	

					if(plantilla.getCodigoProvinciaDomicilio().equals('S'))
					{
						codProvinciaDomicilio = true;
					}
					else
					{
						codProvinciaDomicilio = false;
					}	

					if(plantilla.getCodigoProvinciaExamen().equals('S'))
					{
						codProvinciaExamen = true;
					}
					else
					{
						codProvinciaExamen = false;
					}	

					if(plantilla.getCodigoTituloOficial().equals('S'))
					{
						codTituloOficial = true;
					}
					else
					{
						codTituloOficial = false;
					}	

				}else{
					ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
					convocatoriaQuery.setId(codigoConvocatoria);
					convocatoria = convocatoriasManager.recuperarConvocatoria(convocatoriaQuery);
					String anioConv = convocatoria.getEjercicio();
					calendario.setTime(convocatoria.getFechaBoe());

					Long idPlantilla = convocatoria.getIdPlantilla();
					PlantillaBean plantilla;
					PlantillaQuery plantillaQuery = new PlantillaQuery();
					plantillaQuery.setId(idPlantilla);
					plantilla = plantillaManager.buscarPlantillaById(plantillaQuery);
					setRequestAttribute("plantilla",plantilla);
					formulario = cargarFormulario(convocatoria,formulario, plantilla);

					if(convocatoria.getCodNuevoFormaAcceso() != null && !convocatoria.getCodNuevoFormaAcceso().equals(""))
					{	
						formulario.setCodFormaAcceso(convocatoria.getCodNuevoFormaAcceso());
					}
					else
					{
						formulario.setCodFormaAcceso(convocatoria.getCodFormaAcceso());
					}
					formulario.setAnioConvocatoria(Long.parseLong(anioConv));
					String cadenaAux = "";
					cadenaAux = Integer.toString(calendario.get(Calendar.DAY_OF_MONTH));
					if (cadenaAux.length()==1){
						cadenaAux = "0"+cadenaAux;
					}
					formulario.setDiaFechaBoe(cadenaAux);
					cadenaAux = Integer.toString(calendario.get(Calendar.MONTH)+1);
					if (cadenaAux.length()==1){
						cadenaAux = "0"+cadenaAux;
					}

					//Pasar a mayusculas
					if(convocatoria.getDesMinisterio() != null && !"".equals(convocatoria.getDesMinisterio())){
						convocatoria.setDesMinisterio(convocatoria.getDesMinisterio().toUpperCase());
					}
					if(convocatoria.getDesCentroGestor() != null && !"".equals(convocatoria.getDesCentroGestor())){
						convocatoria.setDesCentroGestor(convocatoria.getDesCentroGestor().toUpperCase());
					}
					if(convocatoria.getDesCuerpoEscala() != null && !"".equals(convocatoria.getDesCuerpoEscala())){
						convocatoria.setDesCuerpoEscala(convocatoria.getDesCuerpoEscala().toUpperCase());
					}


					formulario.setMesFechaBoe(cadenaAux);
					formulario.setAnioFechaBoe(Integer.toString(calendario.get(Calendar.YEAR)));
					ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
					paramConfQuery.setNombreCampo(Constantes.PARAMETROS_PORCENTAJE_DISCAPACIDAD);
					ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);
					formulario.setPorcentajeMinDiscapacidad(parametrosConfiguracion.getValorCampo());

					convocatoria.setCodFormaAcceso(convocatoria.getCodNuevoFormaAcceso());

					// Petición Justicia convocatorias 2015: se muestran provincias de examen para el 790007, indiferentemente de si es Gestión Procesal o Secretarios Judiciales.
					
					setRequestAttribute("convocatoria",convocatoria);
					
					// se obtiene la visibilidad de los codigos precargables
					if(plantilla.getCodigoCuerpoEscala().equals('S'))
					{
						codCuerpoEscala = true;
					}
					else
					{
						codCuerpoEscala = false;
					}	

					if(plantilla.getCodigoEspecialidad().equals('S'))
					{
						codEspecialidad = true;
					}
					else
					{
						codEspecialidad = false;
					}	

					if(plantilla.getCodigoMinisterio().equals('S'))
					{
						codMinisterio = true;
					}
					else
					{
						codMinisterio = false;
					}	

					if(plantilla.getCodigoPaisDomicilio().equals('S'))
					{
						codPaisDomicilio = true;
					}
					else
					{
						codPaisDomicilio = false;
					}	

					if(plantilla.getCodigoProvinciaDomicilio().equals('S'))
					{
						codProvinciaDomicilio = true;
					}
					else
					{
						codProvinciaDomicilio = false;
					}	

					if(plantilla.getCodigoProvinciaExamen().equals('S'))
					{
						codProvinciaExamen = true;
					}
					else
					{
						codProvinciaExamen = false;
					}	

					if(plantilla.getCodigoTituloOficial().equals('S'))
					{
						codTituloOficial = true;
					}
					else
					{
						codTituloOficial = false;
					}	
					
				}
				//Seteamos que por defecto se carge como pais domicilio ESPAÑA
				formulario.setPais(1L);
				PaisQuery paisQuery = new PaisQuery();
				paisQuery.setId(Constantes.ID_PAIS_ESPANIA);
				PaisBean espaniaBean = paisManager.buscarPaisId(paisQuery);
				// sólo se setea el codigo del pais por defecto, si es precargable
				if(codPaisDomicilio == true)
				{	
					formulario.setCodigoPaisDomicilio(espaniaBean.getCodigo().substring(0,3));
				}	
				
				// se indica si se debe precargar el codigo en el formulario
				setRequestAttribute("codMinisterio",codMinisterio);
				setRequestAttribute("codCuerpoEscala",codCuerpoEscala);
				setRequestAttribute("codEspecialidad",codEspecialidad);
				setRequestAttribute("codTituloOficial",codTituloOficial);
				setRequestAttribute("codPaisDomicilio",codPaisDomicilio);
				setRequestAttribute("codProvinciaDomicilio",codProvinciaDomicilio);
				setRequestAttribute("codProvinciaExamen",codProvinciaExamen);

				//Recuperamos la obligatoriedad de los campos propios
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setId(codigoConvocatoria);
				PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
				plantillaPropiosQuery.setConvocatoria(convocatoriaQuery);
				plantillaPropiosQuery.addOrder(PlantillaPropiosQuery.CAMPOSPROPIOS,OrderType.ASC);
				plantillaPropiosQuery.setJoin_convocatoria(true);
				ArrayList<PlantillaPropiosBean> plantillaPropios = plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);


				// Recuperar campos propios según el modelo
				ModeloQuery modeloQuery = new ModeloQuery();
				modeloQuery.setNumModelo(modelo);
				CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
				camposPropiosQuery.setModelo(modeloQuery);
				camposPropiosQuery.addOrder("id", OrderType.ASC);
				ArrayList<CamposPropiosBean> camposPropios = camposPropiosManager.buscarCamposPropiosbyModelo(camposPropiosQuery);
				ArrayList<CamposPropiosBean> listaAux = new ArrayList<CamposPropiosBean>();

				// TODO BILINGUE (Campos propios de la jsp)

				if(null==idiomaExt){
					final Locale locale = (Locale) this.getSession().getAttribute(Globals.LOCALE_KEY);
					idiomaExt = locale.getLanguage();
				}

				if(null!=idiomaExt && !idiomaExt.equals("es")){
					traducirCampoPropio(idiomaExt, camposPropios);
				}

				// Si el modelo es 790007 y el código cuerpo 4041, 4043 o 4045
				// Los campos aparecerán deshabilitados con valor por defecto
				// del tipo NO APLICA.
				if(modelo != null && modelo.equals(Constantes.MODELO79007)){
					formulario.setSecretarioJud(false);
					String codSecretarios[] = properties.getProperty("codigos.secretarios.judiciales").split(",");
				
				// 1. LLamada externa a través de la url
				String secretarioJudicial = this.getRequest().getParameter("SJ");
				
				// 2. Descarga de formularios > Modelo Secretario Judicial
				String idModelo = this.getRequest().getParameter("idModelo");
				
				// Recuperar código Cuerpo escala
				if(formulario != null && formulario.getCodigoCuerpoEscala() != null && !("").equals(formulario.getCodigoCuerpoEscala())){
					for(int i=0; i<codSecretarios.length; i++){
						if(formulario.getCodigoCuerpoEscala().equals(codSecretarios[i])){
							formulario.setSecretarioJud(true);
						}
					}
				}else if(formulario.getCodigoCuerpoEscalaAux()!=null && !("").equals(formulario.getCodigoCuerpoEscalaAux())){
					for(int i=0; i<codSecretarios.length; i++){
						if(formulario.getCodigoCuerpoEscalaAux().equals(codSecretarios[i])){
							formulario.setSecretarioJud(true);
						}
					}
				}else if((null!=secretarioJudicial && secretarioJudicial.equals("true")) || (null!=idModelo && idModelo.equals(Constantes.COD_SECRETARIO_JUDICIAL))){
					formulario.setSecretarioJud(true);
					formulario.setCodigoCuerpoEscalaAux(Constantes.COD_SECRETARIO_JUDICIAL);
				}
				
				
				
				// Si es para secretarios Judiciales, incluir solo Datos A y Datos B
				// En caso de Gestión Procesal, Prueba Optativa de idioma y Prueba Optativa de Dcho Foral.
				if(formulario.isSecretarioJud()){
					listaAux.add(camposPropios.get(2));
					listaAux.add(camposPropios.get(3));
				}else{
					
					listaAux.add(camposPropios.get(0));
					listaAux.add(camposPropios.get(1));
				}
			}				
			
			//Carga los combos de datos propios configuracion
			cargarDatosPropiosConfiguracion(plantillaPropios, convocatoria);	
			
			if(plantillaPropios.size()==0){	
				ArrayList<PlantillaPropiosBean> ppropios;	
				if (listaAux.size()!=0){
					 ppropios = plantillapropiosConv(listaAux);
					 setSessionAttribute(STRING_CAMPOSPROPIOS,listaAux);
				}else{
					 ppropios = plantillapropiosConv (camposPropios);
					 setSessionAttribute(STRING_CAMPOSPROPIOS,camposPropios);
				}
	           setRequestAttribute("plantillaPropios",ppropios);
		   }else{
		       setRequestAttribute("plantillaPropios",plantillaPropios);
		       if(listaAux.size()!=0){
		    	   setSessionAttribute(STRING_CAMPOSPROPIOS,listaAux);
		       }else{
		    	   setSessionAttribute(STRING_CAMPOSPROPIOS,camposPropios);
		       }
		   }
			
			 setSessionAttribute("secretarioJud",formulario.isSecretarioJud());
			 
			 // monto los motivos de exencion para el jsp de descarga
			  ConvocatoriaBean convocatoriaBean = convocatoriasManager.recuperarConvocatoria(formulario.getId());
			  
			 // Carga el Ocultar Datos Propios
			 this.setRequestAttribute("ocultarDatosPropios", convocatoriaBean.getOcultarDatosPropios());
			 
			 // Carga de Otros Titulos
			 if (convocatoriaBean.getOtrosTitulos() != null && convocatoriaBean.getOtrosTitulos().size() > 0) {
				 formulario.setOtrosTitulosList(convocatoriaBean.getOtrosTitulos());
			 }
			 
			 // Carga discapacidad
			 if (convocatoriaBean.getDiscapacidad()!= null && convocatoriaBean.getDiscapacidad().size() > 0) {
				 formulario.setDiscapacidadList(convocatoriaBean.getDiscapacidad());
			 }
			 
			 ArrayList<MotivoReduccionTasa> motivosIncompletos = convocatoriaBean.getMotivoReduccionTasasIncompleto();
			 ArrayList<MotivoReduccionTasa> motivosCompletos = convocatoriaBean.getMotivoReduccionTasasCompleto();
						 
			 if(motivosIncompletos != null){
				Collections.sort(motivosIncompletos);
				this.setRequestAttribute("motivos", motivosIncompletos);
			 }
			 if( motivosCompletos != null){
				 Collections.sort(motivosCompletos);
				this.setRequestAttribute("motivosCompletos", motivosCompletos);
			 }
			 
			 // saco el grupo al que pertenece la convocatoaria para imprimirlo en el jsp
			 String idCuerpoEscala = convocatoriaBean.getIdCuerpoEscala();
			 CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			 cuerpoEscalaQuery.setId(Integer.valueOf(idCuerpoEscala));
			 Grupo grupo = cuerpoEscalaManager.obtenerGrupoByIdGrupoEscala(cuerpoEscalaQuery);
			 if (grupo!=null) {
				 String siglas = grupo.getSiglas();
				 this.setRequestAttribute("siglasGrupoConvocatoria", siglas);
			 }
			 
			 Date today = new Date();			 
			 //Se comprueba las fechas inicio y fin de la convocatoria o subsanacion en el caso de que vengan seteadas
			 if( (convocatoriaBean.getFechaFin() != null && today.after(convocatoriaBean.getFechaFin()) && !DateUtils.isSameDay(today, convocatoriaBean.getFechaFin()) 
					 && (StringUtils.isEmpty(convocatoriaBean.getFechaFinSub()) || StringUtils.isEmpty(convocatoriaBean.getFechaIniSub()))) 
					 || ( !StringUtils.isEmpty(convocatoriaBean.getFechaFinSub()) && !StringUtils.isEmpty(convocatoriaBean.getFechaIniSub()) && 
							 today.after(convocatoriaBean.getFechaFinSub()) && !DateUtils.isSameDay(today, convocatoriaBean.getFechaFinSub())))  {			
				 this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("field.formulario790.errorConvocatoria"));
				 return "errorConvocatoriaCaducada";					
				 }
			
		}catch(Exception e){
			logger.error("Error Formulario 790",e);
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("field.formulario790.error"));	
			return "error";			
		}

		logger.info("DescargarFormulario790Spring - end");

		return "success";
	}
	}

	/**
	 * Cargar datos propios configuracion.
	 *
	 * @param plantillaPropios el plantilla propios
	 * @param convocatoria el convocatoria
	 */
	private void cargarDatosPropiosConfiguracion(ArrayList<PlantillaPropiosBean> plantillaPropios, ConvocatoriaBean convocatoria) {
		for (PlantillaPropiosBean plantillaBean : plantillaPropios) {
			if (plantillaBean.getCampoPropioBean() != null && plantillaBean.getCampoPropioBean().getId() != null) {
				DatosPropiosConfiguracionQuery datosPropiosConfigQuery = new DatosPropiosConfiguracionQuery();
				datosPropiosConfigQuery.addCampoIn(plantillaBean.getCampoPropioBean().getId().intValue());
				List<DatosPropiosConfiguracion> listaDatosPropiosConf = null;
				
				if (convocatoria.getDatosPropiosConfiguracion() != null && convocatoria.getDatosPropiosConfiguracion().size() > 0) {
					for (DatosPropiosConfiguracion datoPropio : convocatoria.getDatosPropiosConfiguracion()) {
						datosPropiosConfigQuery.addIdDesplegableIn(datoPropio.getIdDesplegable());
					}
					listaDatosPropiosConf = datosPropiosConfiguracionManager.buscarDatosPropiosConfigbyCampo(datosPropiosConfigQuery);
				}
				
				
				if (listaDatosPropiosConf != null && listaDatosPropiosConf.size() > 0) {
					plantillaBean.getCampoPropioBean().setDatosPropiosConfiguracion(listaDatosPropiosConf);
				}
			}
		}
	}

	/**
	 * Plantillapropios conv.
	 *
	 * @param camposPropios el campos propios
	 * @return el array list
	 */
	private ArrayList<PlantillaPropiosBean> plantillapropiosConv (ArrayList<CamposPropiosBean> camposPropios){
		ArrayList<PlantillaPropiosBean> ppropios = new ArrayList<PlantillaPropiosBean>();		
		for(int i =0; i<camposPropios.size();i++){
			PlantillaPropiosBean plantillapropios = new PlantillaPropiosBean();
			plantillapropios.setCampoPropioBean(camposPropios.get(i));
			ppropios.add(plantillapropios);
			}
		return ppropios;
	}


	/**
	 * Cargar combos convocatoria.
	 *
	 * @param modelo el modelo
	 */
	private void cargarCombosConvocatoria(String modelo) {
		
		// Para convocatorias de Justicia (mod. 790007), los campos
		// Cuerpo, Especialidad y Provincia Examen se mostrarán
		// deshabilitados con valor predeterminado del tipo 'NO APLICA'
		// recuperado de bbdd.
		boolean modJusticia = false;
		if(modelo != null && !"".equals(modelo) && modelo.substring(3).equals(Constantes.CODIGO_TASA_JUSTICIA)){
			modJusticia = true;
		}
		
		if(modJusticia){
			setParamBean(recuperarValorPorDefecto(Constantes.PARAMETRO_CONFIGURACION_DEFAULT_TEXT_007));
		}
		
		
		ArrayList<EspecialidadBean> especialidades;
		ArrayList<CuerpoEscalaBean> cuerposEscala;
		ArrayList<ProvinciaExamenBean> provinciasExamen = tablaBaseManager.obtenerProvinciasExamenActivas();
		
		especialidades = tablaBaseManager.buscarEspecialidades();
		cuerposEscala = tablaBaseManager.buscarCuerpoEscala();

		
		ArrayList<MinisterioBean> ministerios = tablaBaseManager.buscarMinisteriosCombo();
		ArrayList<FormaAccesoBean> formasAcceso =  tablaBaseManager. buscarFormaAccesoCombo();
		ArrayList<TituloOficialBean> titulosOficiales = tablaBaseManager. buscarTituloOficialesCombo();	
		ArrayList<CentroGestorBean> centrosGestor = tablaBaseManager.buscarCentroGestoresCombo();
		
		setRequestAttribute("provinciasExamen",provinciasExamen);
		setRequestAttribute("especialidades",especialidades);
		setRequestAttribute("ministerios",ministerios);		
		setRequestAttribute("formasAcceso",formasAcceso);		
		setRequestAttribute("titulosOficiales",titulosOficiales);		
		setRequestAttribute("cuerposEscala",cuerposEscala);
		setRequestAttribute("centrosGestor",centrosGestor);
		
	}

	/**
	 * Cargar combos.
	 *
	 * @param modelo el modelo
	 */
	private void cargarCombos(String modelo) {
		ArrayList<ProvinciaBean> provincias = tablaBaseManager.obtenerProvinciasActivas();
		ArrayList<PaisBean> paises = tablaBaseManager.buscarPaises();
		ArrayList<TipoDiscapacidadBean> tipoDiscapacidad;
		TipoDiscapacidadQuery discapacidadQuery = new TipoDiscapacidadQuery();
		
		if(modelo.equals(Constantes.MODELO79007)){
			discapacidadQuery.addIdIn(Integer.parseInt(Constantes.TIPO_DISCAPACIDAD_SI));
			discapacidadQuery.addIdIn(Integer.parseInt(Constantes.TIPO_DISCAPACIDAD_NO));
		}else{
			discapacidadQuery.addIdIn(Integer.parseInt(Constantes.TIPO_DISCAPACIDAD_GENERAL));
			discapacidadQuery.addIdIn(Integer.parseInt(Constantes.TIPO_DISCAPACIDAD_INTELECTUAL));
		}
	
		tipoDiscapacidad = tablaBaseManager.buscarTiposDiscapacidad(discapacidadQuery);
		
		setRequestAttribute("provincias",provincias);
		setRequestAttribute("paises",paises);
		setRequestAttribute("tiposDiscapacidad",tipoDiscapacidad);
		
		ArrayList<ComunidadesBean> comunidadesFN;
		ComunidadesQuery comunidadesQueryFN = new ComunidadesQuery();
		comunidadesQueryFN.setServicioFamnumerosa(true);
		comunidadesFN = comunidadesManager.buscarComunidadesCombo(comunidadesQueryFN);
		this.setRequestAttribute("listcomunidadesFN", comunidadesFN);
		this.setRequestAttribute("numComunidadesFN",comunidadesFN.size());
		
		// Pasamos al formulario la lista de comunidades autónomas que requieren el número de titulo familia numerosa		
		ArrayList<ComunidadesBean> comunidadesReqTitulo;
		ComunidadesQuery comunidadesQueryReqTitulo = new ComunidadesQuery();
		comunidadesQueryReqTitulo.setTituloRequerido(true);
		comunidadesReqTitulo = comunidadesManager.buscarComunidadesCombo(comunidadesQueryReqTitulo);

		String comuReqTitulo="##";
		for (ComunidadesBean comunidad : comunidadesReqTitulo) {
			comuReqTitulo=comuReqTitulo+comunidad.getIdComunidad()+"##";
		} 
		this.setRequestAttribute("comunidadesReqTitulo", comuReqTitulo);


		ArrayList<ComunidadesBean> comunidadesDiscapacidad;
		ComunidadesQuery comunidadesQueryDD = new ComunidadesQuery();
		comunidadesQueryDD.setServicioDiscapacidad(true);
		comunidadesDiscapacidad = comunidadesManager.buscarComunidadesCombo(comunidadesQueryDD);
		this.setRequestAttribute("listcomunidadesDiscapacidad", comunidadesDiscapacidad);
		
		String motivoTipificado="##";
		ArrayList<MotivoReduccionTasa> motivoReduccionTasa = motivoReduccionManager.buscarMotivoReduccionTasaAll();
		for (MotivoReduccionTasa tasa : motivoReduccionTasa) {
			motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
		} 
		
		this.setRequestAttribute("motivoTipificado", motivoTipificado);
		
	}

	/**
	 * Cargar formulario.
	 *
	 * @param convocatoria el convocatoria
	 * @param formularioAux el formulario aux
	 * @param plantilla el plantilla
	 * @return el formulario 790 form
	 */
	private Formulario790Form cargarFormulario(ConvocatoriaBean convocatoria,Formulario790Form formularioAux, PlantillaBean plantilla) {
		formularioAux.setIdCuerpoEscala(convocatoria.getIdCuerpoEscala());
		formularioAux.setIdFormaAcceso(convocatoria.getIdFormaAcceso());
		formularioAux.setFormaAcceso(convocatoria.getCodNuevoFormaAcceso());
		formularioAux.setIdMinisterio(convocatoria.getIdMinisterio());
		formularioAux.setIdCentroGestor(convocatoria.getIdCentroGestor());
		formularioAux.setIdConvocatoria(convocatoria.getId());
		formularioAux.setDesCentroGestor(convocatoria.getDesCentroGestor());
		formularioAux.setCodCentroGestor(convocatoria.getCodCentroGestor());
		formularioAux.setMinisterioAutomatico(convocatoria.getDesMinisterioConvocante());
		formularioAux.setPersonaFirmante(convocatoria.getDirigidoA());
		
		// Codigo ministerio convocante
		if(convocatoria.getIdMinisterioConvocante() != null)
		{	
			MinisterioBean ministerio;
			MinisterioQuery ministerioQuery = new MinisterioQuery();
			ministerioQuery.setId(new Integer(convocatoria.getIdMinisterioConvocante()));
			ministerio = ministerioManager.buscarMinisterioId(ministerioQuery);
			if(ministerio != null && plantilla.getCodigoMinisterio().equals('S'))
			{
				formularioAux.setCodigoMinisterio(ministerio.getCodigo());
			}	
			else
			{
				formularioAux.setCodigoMinisterio("");
			}	
		}
		else
		{
			formularioAux.setCodigoMinisterio("");
		}	
		
		// Codigo cuerpo escala
		if(convocatoria.getIdCuerpoEscala() != null)
		{	
			CuerpoEscalaBean cuerpoEscala;
			CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			cuerpoEscalaQuery.setId(new Integer(convocatoria.getIdCuerpoEscala()));
			cuerpoEscala = cuerpoEscalaManager.buscarCuerpoEscalaId2(cuerpoEscalaQuery);
			if(cuerpoEscala != null)
			{
				formularioAux.setCodigoCuerpoEscala(cuerpoEscala.getCodigo());
				formularioAux.setCodigoCuerpoEscalaAux(cuerpoEscala.getCodigo());
			}	
			else
			{
				formularioAux.setCodigoCuerpoEscala("");
			}
			if(!plantilla.getCodigoCuerpoEscala().equals('S')){
				formularioAux.setCodigoCuerpoEscala("");
			}
		}
		else
		{
			formularioAux.setCodigoCuerpoEscala("");
		}
		
		Float importe =convocatoria.getImporte();
		String[] importeTotal = String.valueOf(importe).split("\\.");	
		setRequestAttribute("importe",importe);
		formularioAux.setImporteSolicitud(importeTotal[0]);
		if(importeTotal.length==1){
			formularioAux.setImporteSolicitudDecimal("00");
		}else{
			formularioAux.setImporteSolicitudDecimal(importeTotal[1]);
		}
		
		formularioAux.setEnlace(convocatoria.getEnlace());
		return formularioAux;
	}

	/**
	 * Obtiene el tabla base manager.
	 *
	 * @return el tabla base manager
	 */
	public TablaBaseManager getTablaBaseManager() {
		return tablaBaseManager;
	}

	/**
	 * Establece el tabla base manager.
	 *
	 * @param tablaBaseManager el nuevo tabla base manager
	 */
	public void setTablaBaseManager(TablaBaseManager tablaBaseManager) {
		this.tablaBaseManager = tablaBaseManager;
	}

	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}
		
	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return el solicitud manager
	 */
	public SolicitudManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager el nuevo solicitud manager
	 */
	public void setSolicitudManager(SolicitudManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}

	/**
	 * Obtiene el mes.
	 *
	 * @param mes el mes
	 * @return el mes
	 */
	public String getMes(int mes){
		String result = "";
		switch (mes) {
			case 0:{
				result = "enero";
				break;
			}
			case 1:{
				result = "febrero";
				break;
			}
			case 2:{
				result = "marzo";
				break;
			}
			case 3:{
				result = "abril";
				break;
			}
			case 4:{
				result = "mayo";
				break;
			}
			case 5:{
				result = "junio";
				break;
			}
			case 6:{
				result = "julio";
				break;
			}
			case 7:{
				result = "agosto";
				break;
			}
			case 8:{
				result = "septiembre";
				break;
			}
			case 9:{
				result = "octubre";
				break;
			}
			case 10:{
				result = "noviembre";
				break;
			}
			case 11:{
				result = "diciembre";
				break;
			}
			default:
				break;
		}
		return result;
	}

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

	/**
	 * Obtiene el formulario 790 manager.
	 *
	 * @return el formulario 790 manager
	 */
	public Formulario790Manager getFormulario790Manager() {
		return formulario790Manager;
	}

	/**
	 * Establece el formulario 790 manager.
	 *
	 * @param formulario790Manager el nuevo formulario 790 manager
	 */
	public void setFormulario790Manager(Formulario790Manager formulario790Manager) {
		this.formulario790Manager = formulario790Manager;
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
	 * Obtiene el pais manager.
	 *
	 * @return el pais manager
	 */
	public PaisManager getPaisManager() {
		return paisManager;
	}

	/**
	 * Establece el pais manager.
	 *
	 * @param paisManager el nuevo pais manager
	 */
	public void setPaisManager(PaisManager paisManager) {
		this.paisManager = paisManager;
	}

	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return el ministerio manager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
	}

	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
	}

	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return el especialidad manager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager el nuevo especialidad manager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
	}

	/**
	 * Obtiene el titulo oficial manager.
	 *
	 * @return el titulo oficial manager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el titulo oficial manager.
	 *
	 * @param tituloOficialManager el nuevo titulo oficial manager
	 */
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
	}

	/**
	 * Obtiene el provincia manager.
	 *
	 * @return el provincia manager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager el nuevo provincia manager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
	}

	/**
	 * Obtiene el provincia examen manager.
	 *
	 * @return el provincia examen manager
	 */
	public ProvinciaExamenManager getProvinciaExamenManager() {
		return provinciaExamenManager;
	}

	/**
	 * Establece el provincia examen manager.
	 *
	 * @param provinciaExamenManager el nuevo provincia examen manager
	 */
	public void setProvinciaExamenManager(
			ProvinciaExamenManager provinciaExamenManager) {
		this.provinciaExamenManager = provinciaExamenManager;
	}

	/**
	 * Obtiene el campos propios manager.
	 *
	 * @return el campos propios manager
	 */
	public CamposPropiosManager getCamposPropiosManager() {
		return camposPropiosManager;
	}

	/**
	 * Establece el campos propios manager.
	 *
	 * @param camposPropiosManager el nuevo campos propios manager
	 */
	public void setCamposPropiosManager(CamposPropiosManager camposPropiosManager) {
		this.camposPropiosManager = camposPropiosManager;
	}
	
	/**
	 * Obtiene el modelo manager.
	 *
	 * @return el modelo manager
	 */
	public ModeloManager getModeloManager() {
		return modeloManager;
	}

	/**
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
	}

	/**
	 * Obtiene el param bean.
	 *
	 * @return el param bean
	 */
	public static ParametrosConfiguracionBean getParamBean() {
		return paramBean;
	}

	/**
	 * Establece el param bean.
	 *
	 * @param paramBean el nuevo param bean
	 */
	public static void setParamBean(ParametrosConfiguracionBean paramBean) {
		DescargarFormulario790Spring.paramBean = paramBean;
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
	 * Método que recupera el valor por defecto para campos del mod. 790007
	 *
	 * @param parametro el parametro
	 * @return ParametrosConfiguracionBean
	 */
	private ParametrosConfiguracionBean recuperarValorPorDefecto(int parametro){
		
		ParametrosConfiguracionQuery paramQuery = new ParametrosConfiguracionQuery();
		paramQuery.setId(parametro);
		
		return parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramQuery);
	}

	/**
	 * Traducir campo propio.
	 *
	 * @param locale el locale
	 * @param camposPropios el campos propios
	 */
	private void traducirCampoPropio(String locale, ArrayList<CamposPropiosBean> camposPropios) {

		for(int i=0; i<camposPropios.size(); i++){
			if(locale.equals("ca")){
				camposPropios.get(i).setCampo(camposPropios.get(i).getCampoCa());
			}else if(locale.equals("gl")){
				camposPropios.get(i).setCampo(camposPropios.get(i).getCampoGl());
			}else if(locale.equals("vl")){
				camposPropios.get(i).setCampo(camposPropios.get(i).getCampoVl());
			}else if(locale.equals("eu")){
				camposPropios.get(i).setCampo(camposPropios.get(i).getCampoEu());
			}
		}
	}
	
	/**
	 * Cambiar no obligatorio.
	 *
	 * @param plantilla Método que cambia los campos de la plantilla a no obligatorios para el formulario en blanco
	 * @return el plantilla bean
	 */
	private PlantillaBean cambiarNoObligatorio(PlantillaBean plantilla) {
		PlantillaBean aux = new PlantillaBean();
		aux.setId(plantilla.getId());
		aux.setNif('N');
		aux.setPrimerApellido('N');
		aux.setSegundoApellido('N');
		aux.setNombre('N');
		aux.setFechaNacimiento('N');
		aux.setProvinciaNacimiento('N');
		aux.setSexo('N');
		aux.setLocalidadNacimiento('N');
		aux.setNacionalidad('N');
		aux.setCorreoElectronico('N');
		aux.setTelefono('N');
		aux.setVia('N');
		aux.setCodigoPostal('N');
		aux.setProvincia('N');
		aux.setMunicipio('N');
		aux.setPais('N');
		aux.setCuerpo('N');
		aux.setEspecialidad('N');
		aux.setFormaacceso('N');
		aux.setEntidadConvocante('N');
		aux.setFechaBoe('N');
		aux.setProvinciaExamen('N');
		aux.setTipoDiscapacidad('N');
		aux.setPorcentajeDiscapacidad('N');
		aux.setReservaDiscapacidad('N');
		aux.setDetalleDiscapacidad('N');
		aux.setTitulosExigidos('N');
		aux.setOtrosTitulos('N');
		aux.setDatosA('N');
		aux.setDatosB('N');
		aux.setDatosC('N');
		aux.setCodigoCuerpoEscala(plantilla.getCodigoCuerpoEscala());
		aux.setCodigoEspecialidad(plantilla.getCodigoEspecialidad());
		aux.setCodigoMinisterio(plantilla.getCodigoMinisterio());
		aux.setCodigoPaisDomicilio(plantilla.getCodigoPaisDomicilio());
		aux.setCodigoProvinciaDomicilio(plantilla.getCodigoProvinciaDomicilio());
		aux.setCodigoProvinciaExamen(plantilla.getCodigoProvinciaExamen());
		aux.setCodigoTituloOficial(plantilla.getCodigoTituloOficial());
		return aux;
	}

	/**
	 * Obtiene el comunidades manager.
	 *
	 * @return el comunidades manager
	 */
	public ComunidadesManager getComunidadesManager() {
		return comunidadesManager;
	}

	/**
	 * Establece el comunidades manager.
	 *
	 * @param comunidadesManager el nuevo comunidades manager
	 */
	public void setComunidadesManager(ComunidadesManager comunidadesManager) {
		this.comunidadesManager = comunidadesManager;
	}

	/**
	 * Obtiene el motivo reduccion manager.
	 *
	 * @return el motivo reduccion manager
	 */
	public MotivoReduccionManager getMotivoReduccionManager() {
		return motivoReduccionManager;
	}

	/**
	 * Establece el motivo reduccion manager.
	 *
	 * @param motivoReduccionManager el nuevo motivo reduccion manager
	 */
	public void setMotivoReduccionManager(
			MotivoReduccionManager motivoReduccionManager) {
		this.motivoReduccionManager = motivoReduccionManager;
	}

	/**
	 * Obtiene el datos propios configuracion manager.
	 *
	 * @return el datos propios configuracion manager
	 */
	public DatosPropiosConfiguracionManager getDatosPropiosConfiguracionManager() {
		return datosPropiosConfiguracionManager;
	}

	/**
	 * Establece el datos propios configuracion manager.
	 *
	 * @param datosPropiosConfiguracionManager el nuevo datos propios configuracion manager
	 */
	public void setDatosPropiosConfiguracionManager(DatosPropiosConfiguracionManager datosPropiosConfiguracionManager) {
		this.datosPropiosConfiguracionManager = datosPropiosConfiguracionManager;
	}
	
}
