package es.map.ipsg.action.solicitudPresencial;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.PaisQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.ProvinciaQuery;
import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudPropiosAuxiliarQuery;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ips.model.TipoPagoQuery;
import es.map.ips.model.TituloOficialQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ComunidadesBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.MotivoExencionTasaBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.PaisBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudComunAuxiliarBean;
import es.map.ipsg.bean.TipoDiscapacidadBean;
import es.map.ipsg.bean.TipoDocumentoBean;
import es.map.ipsg.bean.TipoPagoBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ComunidadesManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PaisManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudPropioAuxiliarManager;
import es.map.ipsg.manager.TipoDiscapacidadManager;
import es.map.ipsg.manager.TipoDocumentoManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class VerAltaSolicitudPresencialSpring.
 *
 * @author amartinl
 */
public class VerAltaSolicitudPresencialSpring extends AbstractSpring {

	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el provincia examen manager. */
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el pais manager. */
	private PaisManager paisManager;
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el tipo discapacidad manager. */
	private TipoDiscapacidadManager tipoDiscapacidadManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el campo propios manager. */
	private CamposPropiosManager campoPropiosManager;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** el solicitud propio auxiliar manager. */
	private SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager;
	
	/** el solicitud ccaa auxiliar manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager;
	
	/** el comunidades manager. */
	private ComunidadesManager comunidadesManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
	/** el tipo documento manager. */
	private TipoDocumentoManager tipoDocumentoManager;


	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaSolicitudPresencialSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ORG_APACHE_SPRING_ERROR. */
	private static final String STRING_ORG_APACHE_SPRING_ERROR = "org.apache.spring.ERROR";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_MOTIVOSCOMPLETOS. */
	private static final String STRING_MOTIVOSCOMPLETOS = "motivosCompletos";
	
	/** La constante STRING_LISTACAMPROSPROPIOSBEAN. */
	private static final String STRING_LISTACAMPROSPROPIOSBEAN = "listaCamposPropiosBean";
	
	/** La constante STRING_FORMULARIO. */
	private static final String STRING_FORMULARIO = "formulario";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/**
	 * Crea una nueva ver alta solicitud presencial spring.
	 */
	public VerAltaSolicitudPresencialSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
				setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
				setPaisManager((PaisManager) getBean("paisManager"));
				setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
				setTipoDiscapacidadManager((TipoDiscapacidadManager) getBean("tipoDiscapacidadManager"));
				setTituloOficialManager( (TituloOficialManager) getBean("tituloOficialManager"));
				setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
				setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
				setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
				setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
				setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
				setCampoPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
				setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager)getBean("solicitudComunAuxiliarManager"));
				setSolicitudPropioAuxiliarManager((SolicitudPropioAuxiliarManager)getBean("solicitudPropioAuxiliarManager"));
				setSolicitudCcaaAuxiliarManager((SolicitudCcaaAuxiliarManager)getBean("solicitudCcaaAuxiliarManager"));
				setComunidadesManager((ComunidadesManager)getBean("comunidadesManager"));
				setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
				setTipoDocumentoManager((TipoDocumentoManager) getBean("tipoDocumentoManager"));
		} catch (Exception e) {
			logger.error("Error VerAltaSolicitudPresencialSpring",e);
		}
		
	}

	/**
	 * Do execute.
	 *
	 * @param form  SpringForm
	 * @return resultado
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);	
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		
		String idPagoEfectivo = String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_INTEGER);
		this.getRequest().setAttribute("idTipoPagoEfectivo", idPagoEfectivo);
		//******************************************************************
		boolean convocatoriaEncontrada = false;
		logger.info("--------Entramos en VerAltaSolicitudPresencial");
	try{
		//Cogemos el form del jsp
		AltaSolicitudPresencialForm formulario;
		formulario = (AltaSolicitudPresencialForm) form;
		
		ConvocatoriasBean convocatoriaBean = new ConvocatoriasBean();
		SolicitudComunAuxiliarBean solicitudComunAuxiliarBean = null;
		String idConvocatoria =null;
		SpringMessages errors = new SpringMessages();
		
		checkRolUsuario();
		// Comprobamos si se ha rellenado el campo del número del justificante y no hay nada del OCR
		if((!"790001".equals(formulario.getNumeroSolicitud()) && 
				!"790007".equals(formulario.getNumeroSolicitud()) && 
				!"".equals(formulario.getNumeroSolicitud())) && 
				!formulario.getAccion().equals("GUARDAR") &&
				(formulario.getDatosSolicitud() == null || "".equals(formulario.getDatosSolicitud()))){

			SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
			solicitudComunAuxiliarQuery.setNumeroSolicitud(formulario.getNumeroSolicitud());
			
			// Obtenemos los datos de la tabla Solicitud Común Auxiliar asociado a ese número de justificante
			solicitudComunAuxiliarBean = solicitudComunAuxiliarManager.buscarSolicitudComunAuxiliarById(solicitudComunAuxiliarQuery);
			
			setRequestAttribute("tipoPagoPresencial", (solicitudComunAuxiliarBean != null && solicitudComunAuxiliarBean.getIdTipoPago() != null)?solicitudComunAuxiliarBean.getIdTipoPago().toString():"");
			
			if (solicitudComunAuxiliarBean != null && solicitudComunAuxiliarBean.getConvocatoria()!=null){
				idConvocatoria = solicitudComunAuxiliarBean.getConvocatoria().getId().toString();
				
			}else if (solicitudComunAuxiliarBean == null) {
				SpringMessage error = new SpringMessage("field.solicitudPresencial.error.numJustificanteNoExiste", formulario.getNumeroSolicitud());
				errors.add("errorNJustificante", error);
				this.setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
				return STRING_NOSUCCESS;
			}

		}else{
		// Obtenemos el id de Convocatoria del formulario
		idConvocatoria = formulario.getIdConvocatoria();
		}
		
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		if(sVieneMenu != null && sVieneMenu.equals("S"))
		{
			limpiarCamposFormulario(formulario);
		    formulario.setListaSolicitudPropiosBean(null);
		    formulario.setListaCamposPropiosBean(null);
		}
		
		if(idConvocatoria != null && !idConvocatoria.equals(""))
		{	
			convocatoriaBean=cargarDatosConvocatoria(form,idConvocatoria,formulario);						
		}
		else
		{
			//Cargamos el motivo de exención de discapacidad por defecto
			ArrayList<MotivoExencionTasaBean> arrayExenBean=cargarMotivosExencion(null);									
			this.setRequestAttribute(STRING_MOTIVOSCOMPLETOS, arrayExenBean);
		}
		
		
		logger.info("DatosFormulario: "+formulario.getDatosSolicitud());
		
		cargarCombos(formulario);
		
		//Permitimos que los datos de la solicitud puedan venir vacios
		if ((formulario.getDatosSolicitud() == null || "".equals(formulario.getDatosSolicitud())) && solicitudComunAuxiliarBean == null) {
			ArrayList<CamposPropiosBean> listaCamposPropiosBean;
			if  (formulario.getListaCamposPropiosBean() != null && !"".equals(formulario.getListaCamposPropiosBean())){
				listaCamposPropiosBean = formulario.getListaCamposPropiosBean();
			}else{
				// Por defecto se cargan los campos propios correspondientes al modelo genérico 790001
				ModeloQuery modeloQuery = new ModeloQuery();
				CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
				modeloQuery.setIdModelo(Constantes.ID_MODELO_790001);
				camposPropiosQuery.setModelo(modeloQuery);
				camposPropiosQuery.addOrder(CamposPropiosQuery.IDCAMPO, OrderType.ASC);
				listaCamposPropiosBean = campoPropiosManager.buscarCamposPropiosbyModelo(camposPropiosQuery);
				formulario.setListaCamposPropiosBean(listaCamposPropiosBean);
			}
			setRequestAttribute(STRING_LISTACAMPROSPROPIOSBEAN, listaCamposPropiosBean);
			this.setRequestAttribute(STRING_FORMULARIO,formulario);
			
			logger.info("--------Salimos de VerAltaSolicitudPresencial con formulario en blanco");
			
			return STRING_SUCCESS;
		}else if(solicitudComunAuxiliarBean != null){

			// Obtenemos los campos propios de la tabla solicitud propios auxiliar
			SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery = new SolicitudPropiosAuxiliarQuery();
			SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery = new SolicitudCcaaAuxiliarQuery();
			SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
			solicitudComunAuxiliarQuery.setIdSolicitudAuxiliar(solicitudComunAuxiliarBean.getIdSolicitud());
			solicitudPropiosAuxiliarQuery.setSolicitudComunAuxiliar(solicitudComunAuxiliarQuery);
			solicitudCcaaAuxiliarQuery.setSolicitudComunAuxiliar(solicitudComunAuxiliarQuery);
			ArrayList<CamposPropiosBean> listaCamposPropiosBean = solicitudPropioAuxiliarManager.obtenerCamposPropiosAuxiliarByIdSolicitud(solicitudPropiosAuxiliarQuery);
			SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean = solicitudCcaaAuxiliarManager.obtenerSolicitudCcaaAuxiliarByIdSolicitud(solicitudCcaaAuxiliarQuery);
			//Comprobamos si tenemos datos almacenados en la tabla de Solicitud Propio Auxiliar
			if(listaCamposPropiosBean==null){
				ModeloQuery modeloQuery = new ModeloQuery();
				CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();				
				modeloQuery.setIdModelo(Constantes.ID_MODELO_790001);
				camposPropiosQuery.setModelo(modeloQuery);
				camposPropiosQuery.addOrder(CamposPropiosQuery.IDCAMPO, OrderType.ASC);
				ArrayList<CamposPropiosBean> listaCamposPropiosBeanModelo = campoPropiosManager.buscarCamposPropiosbyModelo(camposPropiosQuery);
				formulario.setListaCamposPropiosBean(listaCamposPropiosBeanModelo);
				setRequestAttribute(STRING_LISTACAMPROSPROPIOSBEAN, listaCamposPropiosBeanModelo);
			}else{
				formulario.setListaCamposPropiosBean(listaCamposPropiosBean);
				setRequestAttribute(STRING_LISTACAMPROSPROPIOSBEAN, listaCamposPropiosBean);
			}		
			this.setRequestAttribute(STRING_FORMULARIO,cargarDatosNumeroJustificante(solicitudComunAuxiliarBean,solicitudCcaaAuxiliarBean,formulario, convocatoriaBean));
			
			
		}else{
		//Convierto los $ en Ñ
		String cadenaAux = "";
		for(int x = 0 ;x<formulario.getDatosSolicitud().length();x++){
			if(formulario.getDatosSolicitud().charAt(x) == '$'){
				cadenaAux = cadenaAux + 'Ñ';
			}else{
				cadenaAux = cadenaAux + formulario.getDatosSolicitud().charAt(x);
			}			
		}
		
		//Caracteres especiales
		cadenaAux = cadenaAux.replace("$", "Ñ");
		cadenaAux = cadenaAux.replace("%A%", "\\");
		cadenaAux = cadenaAux.replace("%B%", "#");
		cadenaAux = cadenaAux.replace("%C%", "]");
		cadenaAux = cadenaAux.replace("%D%", "[");
		cadenaAux = cadenaAux.replace("%E%", "}");
		cadenaAux = cadenaAux.replace("%F%", "{");
		cadenaAux = cadenaAux.replace("%G%", "<");
		cadenaAux = cadenaAux.replace("%H%", ">");
		cadenaAux = cadenaAux.replace("%I%", "@");
		cadenaAux = cadenaAux.replace("%J%", "¬");
		cadenaAux = cadenaAux.replace("%K%", "€");
		cadenaAux = cadenaAux.replace("%L%", "|");
		cadenaAux = cadenaAux.replace("%M%", "Á");
		cadenaAux = cadenaAux.replace("%N%", "É");
		cadenaAux = cadenaAux.replace("%O%", "Í");
		cadenaAux = cadenaAux.replace("%P%", "Ó");
		cadenaAux = cadenaAux.replace("%Q%", "Ú");
		cadenaAux = cadenaAux.replace("%R%", "À");
		cadenaAux = cadenaAux.replace("%S%", "È");
		cadenaAux = cadenaAux.replace("%T%", "Ì");
		cadenaAux = cadenaAux.replace("%U%", "Ò");
		cadenaAux = cadenaAux.replace("%V%", "Ù");
		cadenaAux = cadenaAux.replace("%W%", "Ä");
		cadenaAux = cadenaAux.replace("%X%", "Ë");
		cadenaAux = cadenaAux.replace("%Y%", "Ï");
		cadenaAux = cadenaAux.replace("%Z%", "Ö");
		cadenaAux = cadenaAux.replace("%A1%", "Ü");		
	
		
		//Me creo un array que contendra cada uno de los codigos de la nube de puntos	
		StringTokenizer tokens = new StringTokenizer(cadenaAux,"*");   
		String arraytokens[] = new String[56];
		try{
		int i=0;
		//Separo en un array los datos introducidos por el delimitador
		while(tokens.hasMoreTokens()){
			arraytokens[i] = tokens.nextToken();
			if(arraytokens[i]!= null)
				arraytokens[i]=arraytokens[i].trim();			
				
			i++;
		}
		logger.info("NumTokens: " + i);
		if(i != 56){
			
			SpringMessage error = new SpringMessage("field.solicitudPresencial.error.formatoCodigoPuntos");
			errors.add("errorNJustificante", error);
			this.setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
			return STRING_NOSUCCESS;
		}
		
		CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
		ModeloQuery modeloQuery = new ModeloQuery();
		
		// Carga presencial Multi-modelo
		try {
			String numModelo = arraytokens[4].substring(0, 6);
			
			if(numModelo!=null && !"".equals(numModelo)){
				modeloQuery.setNumModelo(numModelo);
			}else{
				modeloQuery.setNumModelo(Constantes.MODELO_ASOCIADO_GENERACION_EJB); // 790001
			}
			
		} catch (Exception e) {
			logger.error("Error VerAltaSolicitudPresencialSpring -error modelo solicitud presencial",e);
			this.setRequestAttribute("errorDescripcion", RESOURCE_BUNDLE.getString("field.solicitudPresencial.error.modelo"));
			return STRING_NOSUCCESS;
		}
		
		camposPropiosQuery.setModelo(modeloQuery);
		camposPropiosQuery.addOrder(CamposPropiosQuery.IDCAMPO, OrderType.ASC);
		ArrayList<CamposPropiosBean> listaCamposPropiosBean = campoPropiosManager.buscarCamposPropiosbyModelo(camposPropiosQuery);
		formulario.setListaCamposPropiosBean(listaCamposPropiosBean);
		
		setRequestAttribute(STRING_LISTACAMPROSPROPIOSBEAN, listaCamposPropiosBean);
		
		//Busco la convocatoria
		DateFormat df = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT); 
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		
		try{
			cuerpoEscalaQuery.setId(Integer.parseInt(arraytokens[22]));
		}catch(Exception e){
			logger.error("Error VerAltaSolicitudPresencialSpring - error parsear cuerpoEscala",e);
			SpringMessage error = new SpringMessage("field.solicitudPresencial.error.cuerpoEscalaFormat");			
			errors.add("errorCuerpoEscala", error);			
		
		}
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		try{
			formaAccesoQuery.setId(Integer.parseInt(arraytokens[26]));
		}catch(Exception e){
			logger.error("Error VerAltaSolicitudPresencialSpring - error forma de acceso",e);
			SpringMessage error = new SpringMessage("field.solicitudPresencial.error.formaAccesoFormat");			
			errors.add("errorFormaAcceso", error);						
			
		}
		if(errors != null){
			this.setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
		}
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		boolean correcto = true;
		if(cuerpoEscalaQuery.getId() != null){
			convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
		}else{
			correcto = false;
		}
		if(formaAccesoQuery.getId() != null){
			convocatoriaQuery.setFormaAcceso(formaAccesoQuery);
		}else{
			correcto = false;
		}

		convocatoriaQuery.setEjercicio(arraytokens[5]);
		Date fechaBoe = null;
		try{
			fechaBoe = df.parse(arraytokens[30]);
		}catch(Exception e){
			try{
				DateFormat dfAux = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT); 
				fechaBoe = dfAux.parse(arraytokens[30]);
			}catch(Exception ef){
				logger.error("Error VerAltaSolicitudPresencialSpring -error parsear  fechaBoe"+  fechaBoe,ef);
				fechaBoe = null;
			}
		}
		logger.info("FechaBoe: "+fechaBoe);
		convocatoriaQuery.setFechaBoeMin(fechaBoe);
		convocatoriaQuery.setFechaBoeMax(fechaBoe);
		
		// Corrección incidencia no se puede volcar por OCR los datos de una convocatoria que no este publicada

		
		ArrayList<ConvocatoriasBean> auxConvocatoria = new ArrayList<ConvocatoriasBean>();
		
		if(correcto == true)
		{
			auxConvocatoria = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
			convocatoriaEncontrada = auxConvocatoria.size()!=0;
		}	
			
			//Comprobar los id de los combos
			ProvinciaQuery provinciaQuery = new ProvinciaQuery();
			try{
				provinciaQuery.setId(Integer.parseInt(arraytokens[19]));
			}catch(Exception e){
				logger.error("Error VerAltaSolicitudPresencialSpring -error parsear provincia ",e);
				provinciaQuery.setId(0);
			}
			ProvinciaBean provinciaDomicilio = provinciaManager.buscarProvinciaUnique(provinciaQuery);
			ProvinciaExamenQuery provinciaQueryExamen = new ProvinciaExamenQuery();
			try{
				provinciaQueryExamen.setId(Integer.parseInt(arraytokens[31]));
			}catch(Exception e){
				logger.error("Error VerAltaSolicitudPresencialSpring -error  parsear provinciaExamen",e);
				provinciaQueryExamen.setId(0);
			}
			ProvinciaExamenBean provinciaExamen = provinciaExamenManager.buscarProvinciaExamenUnique(provinciaQueryExamen);
			EspecialidadQuery especialidadQuery = new EspecialidadQuery();
			try{
				especialidadQuery.setId(Integer.parseInt(arraytokens[24]));
			}catch(Exception e){
				logger.error("Error VerAltaSolicitudPresencialSpring -error  parsear especialidad",e);
				especialidadQuery.setId(0);
			}
			EspecialidadBean especialidad = especialidadManager.buscarEspecialidadUnique(especialidadQuery);
			
			if(!"GUARDAR".equals(formulario.getAccion())){
				//Cargo los datos en el formulario
				if(auxConvocatoria!=null && auxConvocatoria.size()!=0){
					ConvocatoriasBean convocatoria = auxConvocatoria.get(0);
					if(auxConvocatoria!=null){
						formulario.setIdConvocatoria(convocatoria.getIdConvocatoria());
						String desConvocatoria = "";
						formulario.setDsConvocatoria(desConvocatoria);
						
						// COMPROBACION DATOS OBLIGATORIOS CONVOCATORIA
						PlantillaQuery plantillaQuery = new PlantillaQuery();
						plantillaQuery.setId(convocatoria.getIdPlantilla());
						PlantillaBean plantilla = plantillaManager.buscarPlantilla(plantillaQuery);

						if(plantilla != null){
						formulario.setObCorreoElectronico(plantilla.getCorreoElectronico());
						formulario.setObDatosA(plantilla.getDatosA());
						formulario.setObDatosB(plantilla.getDatosB());
						formulario.setObDatosC(plantilla.getDatosC());
						formulario.setObDetalleDiscapacidad(plantilla.getDetalleDiscapacidad());
						formulario.setObEspecialidad(plantilla.getEspecialidad());
						formulario.setObFechaNacimiento(plantilla.getFechaNacimiento());
						formulario.setObMunicipio(plantilla.getMunicipio());
						formulario.setObNacionalidad(plantilla.getNacionalidad());
						formulario.setObNif(plantilla.getNif());
						formulario.setObNombre(plantilla.getNombre());
						formulario.setObOtrosTitulos(plantilla.getOtrosTitulos());
						formulario.setObPais(plantilla.getPais());
						formulario.setObPorcentajeDiscapacidad(plantilla.getPorcentajeDiscapacidad());
						formulario.setObPrimerApellido(plantilla.getPrimerApellido());
						formulario.setObProvinciaExamen(plantilla.getProvinciaExamen());
						formulario.setObProvincia(plantilla.getProvincia());
						formulario.setObReservaDiscapacidad(plantilla.getReservaDiscapacidad());
						formulario.setObSegundoApellido(plantilla.getSegundoApellido());
						formulario.setObSexo(plantilla.getSexo());
						formulario.setObTelefono(plantilla.getTelefono());
						formulario.setObTipoDiscapacidad(plantilla.getTipoDiscapacidad());
						formulario.setObTitulosExigidos(plantilla.getTitulosExigidos());
						formulario.setObVia(plantilla.getVia());
						
						setRequestAttribute("plantilla",plantilla);
						
						form = formulario; 
						}
					}
				}
				formulario.setNif(arraytokens[6]);
				formulario.setNombre(arraytokens[9]);
				formulario.setPrimerApellido(arraytokens[7]);
				formulario.setSegundoApellido(arraytokens[8]);
				if((arraytokens[45].toUpperCase()).equals("X"))
				{	
					// si viene marcado es porque no consiente la divulagación de sus datos
					formulario.setCkConsentimiento(false);
				}
				else
				{
					formulario.setCkConsentimiento(true);
				}	
				
				try{
					String fechaNacimiento = arraytokens[10];
					if(fechaNacimiento != null && !"//".equals(fechaNacimiento)) {
						int index = fechaNacimiento.indexOf("/");
						if(index == 1){//Si el dia solo tiene un caracter, se añade un 0 para que se ajuste al formato dd/mm/yyyy
							fechaNacimiento = "0"+fechaNacimiento;
						}						 
						formulario.setFechaNacimiento(fechaNacimiento);
					}					
				}catch(Exception e){
					logger.error("Error VerAltaSolicitudPresencialSpring -error  fechaNacimiento",e);
				}
				formulario.setLocalidadNacimiento(arraytokens[18]);
				formulario.setNacionalidad(arraytokens[13]);
				if("".equals(arraytokens[11]) && "".equals(arraytokens[12])){
					formulario.setSexo("");
				}else{
					if("X".equals(arraytokens[11])){
						formulario.setSexo("H");
					}else{
						formulario.setSexo("M");
					}
				}
				String fdistribuciontemp = arraytokens[14].replace('"','@');
				formulario.setEmail(fdistribuciontemp);
				String calle = arraytokens[16].replace('-','\\');
				formulario.setCalleDomicilio(calle);
				formulario.setMunicipioDomicilio(arraytokens[18]);
				String codPais = arraytokens[21];
				if(codPais != null && !"".equals(codPais)){
					PaisQuery paisQuery = new PaisQuery();
					paisQuery.setCodigo(codPais);
					PaisBean paisBean = paisManager.buscarPaisUnique(paisQuery);
					if(paisBean != null){
						formulario.setIdPais(String.valueOf(paisBean.getId()));
					}
				}
				if(provinciaDomicilio != null){
					formulario.setIdProvinciaDomicilio(String.valueOf(provinciaDomicilio.getId()));
				}
				
				if (!arraytokens[15].equals("")) {
					StringTokenizer telefonoToken = new StringTokenizer(
							arraytokens[15], "/");
					formulario.setTelefono1(telefonoToken.nextToken());
					if (telefonoToken.hasMoreTokens())
						formulario.setTelefono2(telefonoToken.nextToken());
				}
				formulario.setCodigoPostal(arraytokens[17]);
			
				formulario.setNumeroSolicitud(arraytokens[4]);
				if(especialidad != null){
					formulario.setIdEspecialidad(String.valueOf(especialidad.getId()));
				}
				if(provinciaExamen != null){
					formulario.setIdProvinciaExamen(String.valueOf(provinciaExamen.getId()));
				}
			
				try{
					String fechaSolicitud = arraytokens[43];
					if(fechaSolicitud != null) {
						int index = fechaSolicitud.indexOf("/");
						if(index == 1){//Si el dia solo tiene un caracter, se añade un 0 para que se ajuste al formato dd/mm/yyyy
							fechaSolicitud = "0"+fechaSolicitud;
						}
						formulario.setFechaSolicitud(fechaSolicitud);
					}
					
				}catch(Exception e){
					logger.error("Error VerAltaSolicitudPresencialSpring - error fechasolicitud",e);
				}			
				formulario.setIdTipoDiscapacidad(arraytokens[34]);
				String porcentaje = arraytokens[33];
				try{
					int porcentajeNum = Integer.parseInt(porcentaje);
					formulario.setPorcentajeDiscapacidad(arraytokens[33]);
					ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
					paramConfQuery.setNombreCampo(Constantes.PARAMETROS_PORCENTAJE_DISCAPACIDAD);
					ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);
					int porcentajeConf = Integer.parseInt(parametrosConfiguracion.getValorCampo());
					if(porcentajeConf <= porcentajeNum){
						formulario.setCkReservaDiscapacidad(true);
					}else{
						formulario.setCkReservaDiscapacidad(false);
					}
				}catch(Exception e){
					logger.error("Error VerAltaSolicitudPresencialSpring -error  parsear porcentaje Discapacidad",e);
					formulario.setCkReservaDiscapacidad(false);
				}
				formulario.setAdaptacionDiscapacidad(arraytokens[36]);
			
				formulario.setIdTitulo(String.valueOf(arraytokens[37]));
				formulario.setOtrosTitulos(arraytokens[39]);
			
				ArrayList<String> listaDatos = new ArrayList<String>();
				formulario.setDatosA(arraytokens[40]);
				listaDatos.add(formulario.getDatosA());
				formulario.setDatosB(arraytokens[41]);
				listaDatos.add(formulario.getDatosB());
				formulario.setDatosC(arraytokens[42]);
				listaDatos.add(formulario.getDatosC());
						
				// Corrección incidencia (cargar campos propios tras lectura de código de puntos)
				for(int j=0; j<listaCamposPropiosBean.size(); j++){
					if(null!=listaCamposPropiosBean.get(j)){
						listaCamposPropiosBean.get(j).setValorVista(listaDatos.get(j));
					}					
				}
				
				String importe = arraytokens[44];
				String[]importeAux = {"0","0"};
				if(importe!=null && !"".equals(importe) && !" ".equals(importe)){			
					importe = importe.replace(",", ".");
					importeAux = importe.split("[.]");
				}
				
				formulario.setImporte(String.valueOf(importeAux[0]));
				formulario.setImporteDecimal(String.valueOf(importeAux[1]));
				
				//Tipo Pago Efectivo
				if((arraytokens[46].toUpperCase()).equals("X")){
					formulario.setIdTipoPago(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_INT));
				}else if((arraytokens[47].toUpperCase()).equals("X")){
					formulario.setIdTipoPago(String.valueOf(Constantes.TIPO_PAGO_CUENTA_INT));				
				}else if(formulario.getImporte().equals("0") && (formulario.getImporteDecimal().equals("00") || formulario.getImporteDecimal().equals("0"))){
					formulario.setIdTipoPago(String.valueOf(Constantes.TIPO_PAGO_EXENTO_INT));
				}					
							
				//Tipo Pago Cuenta

				
				//Discapacidad
				if((arraytokens[48].toUpperCase()).equals("X")){
					formulario.setIdMotivosEx(Constantes.MOTIVO_DISCAPACIDAD);	
					//Comunidad Autónoma
					if(arraytokens[52]!=null && !arraytokens[52].equals("")){
						formulario.setComunidadDD(arraytokens[52]);				
					}
					//Tipo Pago Exento
					if(!(arraytokens[46].toUpperCase()).equals("X") && !(arraytokens[47].toUpperCase()).equals("X")){
						formulario.setIdTipoPago(String.valueOf(Constantes.TIPO_PAGO_EXENTO_INT));					
					}				
				}
				//Desempleo
				if((arraytokens[49].toUpperCase()).equals("X")){
					formulario.setIdMotivosEx(Constantes.MOTIVO_DESEMPLEO);				
				}
				//Familia Numerosa General
				if((arraytokens[50].toUpperCase()).equals("X")){
					formulario.setIdMotivosRed(Constantes.MOTIVO_FNUMEROSAGENERAL);	
					//Comunidad Autónoma
					if(arraytokens[53]!=null && !arraytokens[53].equals("")){
						formulario.setComunidadFN(arraytokens[53]);				
					}
				}
				
				//Familia Numerosa Especial
				if((arraytokens[51].toUpperCase()).equals("X")){
					formulario.setIdMotivosEx(Constantes.MOTIVO_FNUMEROSAESPECIAL);	
					//Comunidad Autónoma
					if(arraytokens[53]!=null && !arraytokens[53].equals("")){
						formulario.setComunidadFN(arraytokens[53]);				
					}
				}
				
				//Victima de Terrorismo
				if((arraytokens[52].toUpperCase()).equals("X")){
					formulario.setIdMotivosRed(Constantes.MOTIVO_VICTIMATERRORISMO);				
				}
				
				//Número Título Familia Numerosa
				if(arraytokens[54]!=null && !arraytokens[54].equals("")){
					formulario.setNumeroTituloFN(arraytokens[54]);				
				}
				
				
				this.setRequestAttribute(STRING_FORMULARIO,formulario);
			}
		
			logger.info("--------Salimos de VerAltaSolicitudPresencial");
			if(correcto == false)
			{
					SpringMessage error = new SpringMessage("field.solicitudPresencial.error.convocatoria", arraytokens[22],"no válida",arraytokens[5],arraytokens[30]);
					errors.add("errorConvocatoria", error);
					this.setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
					return STRING_SUCCESS;
			}	
			else if(!convocatoriaEncontrada)
			{		
				SpringMessage error = new SpringMessage("field.solicitudPresencial.error.convocatoria", arraytokens[22],arraytokens[26],arraytokens[5],arraytokens[30]);		
				
				errors.add("errorConvocatoria", error);
				this.setRequestAttribute(STRING_ORG_APACHE_SPRING_ERROR, errors);
				return STRING_SUCCESS;
			}
			
		}catch(Exception e){
			logger.error("Error VerAltaSolicitudPresencialSpring - No se reconoce el formato introducido",e);
			this.setRequestAttribute("errorDescripcion", "No se reconoce el formato introducido");
			return STRING_NOSUCCESS;
		}
	}
		
	}catch(Exception eGenerico){
		logger.error("Error VerAltaSolicitudPresencialSpring",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return STRING_SUCCESS;
	}
	

	/**
	 * Limpiar campos formulario.
	 *
	 * @param formulario el formulario
	 */
	private void limpiarCamposFormulario(AltaSolicitudPresencialForm formulario)
	{
		//Datos Convocatoria
		formulario.setIdConvocatoria("");
		formulario.setJustificantePago("");
		formulario.setDsConvocatoria("");
		
		//Datos Personales
		formulario.setNif("");
		formulario.setNombre("");
		formulario.setPrimerApellido("");
		formulario.setSegundoApellido("");
		formulario.setFechaNacimiento("");
		formulario.setLocalidadNacimiento("");
		formulario.setNacionalidad("");
		formulario.setIdProvinciaNacimiento("");
		formulario.setSexo("");
		formulario.setEmail("");
		formulario.setCkConsentimiento(true);
		
		//Datos del Domicilio
		formulario.setCalleDomicilio("");
		formulario.setMunicipioDomicilio("");
		formulario.setIdPais("");
		formulario.setIdProvinciaDomicilio("");
		formulario.setTelefono1("");
		formulario.setTelefono2("");
		formulario.setCodigoPostal("");
				
		//Datos de la Solicitud
		formulario.setNumeroSolicitud("");
		formulario.setIdEspecialidad("");
		formulario.setIdProvinciaExamen("");
		formulario.setFechaSolicitud("");
		formulario.setEjercicio("");
		formulario.setIdTipoDiscapacidad("");
		formulario.setPorcentajeDiscapacidad("");
		formulario.setCkReservaDiscapacidad(false);
		formulario.setAdaptacionDiscapacidad("");
		
		//Datos de Títulos
		formulario.setIdTitulo("");
		formulario.setOtrosTitulos("");

				
		//Datos a Consignar
		formulario.setDatosA("");
		formulario.setDatosB("");
		formulario.setDatosC("");
				
		//Datos de Pago
		formulario.setIdTipoPago("");
		formulario.setIdMotivosEx("");
		formulario.setIdMotivosRed("");
		formulario.setFechaPago("");
		formulario.setImporte("");
		formulario.setImporteDecimal("");
		formulario.setNrcPago("");
			
		//Datos de Registro
		formulario.setIdEntidadBancaria("");
		formulario.setNumeroRegistro("");
		formulario.setFechaRegistro("");
		formulario.setOficinaRegistro("");
		
	}
	
	/**
	 * Cargar datos convocatoria.
	 *
	 * @param form el form
	 * @param idConvocatoria el id convocatoria
	 * @param formulario el formulario
	 * @return el convocatorias bean
	 */
	private ConvocatoriasBean cargarDatosConvocatoria(SpringForm form, String idConvocatoria, AltaSolicitudPresencialForm formulario){
		ConvocatoriasBean convocatoriaBean;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(new Long(idConvocatoria));
		convocatoriaBean = convocatoriaManager.buscarConvocatoriaById(convocatoriaQuery);
		if ( convocatoriaBean != null)
		{	
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setId(convocatoriaBean.getIdPlantilla());
			PlantillaBean plantilla = plantillaManager.buscarPlantilla(plantillaQuery);
			
			// obtencion de las plantillas propias que usa el registro, para poder poder setear
			// los campos propios que son obligatorios ObDatosA,ObDatosB,ObDatosC
			PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
			plantillaPropiosQuery.setConvocatoria(convocatoriaQuery);
			ArrayList<PlantillaPropiosBean> plantillasPropiasBean = plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);
			
			if(plantilla != null){
				// seteo de los campos propios que son obligatorios ObDatosA,ObDatosB,ObDatosC
				if (plantillasPropiasBean!=null && !plantillasPropiasBean.isEmpty()) {
					for(int i=0;i<plantillasPropiasBean.size();i++) {
						Long idCampoPropio = plantillasPropiasBean.get(i).getCampoPropioBean().getId();
						Character obligatorio='N';
						if (plantillasPropiasBean.get(i).isObligatorio()) {
							obligatorio = 'S';
						} else {
							obligatorio = 'N';
						}
						if (idCampoPropio!=null) {
							Integer idCampoPropioInt = (int) (long) idCampoPropio;
							switch (idCampoPropioInt) {
							case 1:
								formulario.setObDatosA(obligatorio);
								break;
							case 2:
								formulario.setObDatosB(obligatorio);
								break;
							case 3:
								formulario.setObDatosC(obligatorio);
								break;
							default:
								break;
							}
						}
						
					}
				}
			
				formulario.setObCorreoElectronico(plantilla.getCorreoElectronico());
				formulario.setObDetalleDiscapacidad(plantilla.getDetalleDiscapacidad());
				formulario.setObEspecialidad(plantilla.getEspecialidad());
				formulario.setObFechaNacimiento(plantilla.getFechaNacimiento());
				formulario.setObMunicipio(plantilla.getMunicipio());
				formulario.setObNacionalidad(plantilla.getNacionalidad());
				formulario.setObNif(plantilla.getNif());
				formulario.setObNombre(plantilla.getNombre());
				formulario.setObOtrosTitulos(plantilla.getOtrosTitulos());
				formulario.setObPais(plantilla.getPais());
				formulario.setObPorcentajeDiscapacidad(plantilla.getPorcentajeDiscapacidad());
				formulario.setObPrimerApellido(plantilla.getPrimerApellido());
				formulario.setObProvinciaExamen(plantilla.getProvinciaExamen());
				formulario.setObProvincia(plantilla.getProvincia());
				formulario.setObReservaDiscapacidad(plantilla.getReservaDiscapacidad());
				formulario.setObSegundoApellido(plantilla.getSegundoApellido());
				formulario.setObSexo(plantilla.getSexo());
				formulario.setObTelefono(plantilla.getTelefono());
				formulario.setObTipoDiscapacidad(plantilla.getTipoDiscapacidad());
				formulario.setObTitulosExigidos(plantilla.getTitulosExigidos());
				formulario.setObVia(plantilla.getVia());
				
				setRequestAttribute("plantilla",plantilla);
			
				form = formulario; 								
	            // Obtenemos los motivos de reducción de tasa y de exención y lo enviamos por la request para el jsp

			if(convocatoriaBean.getMotivoReduccionTasasIncompleto() != null){
				
				this.setRequestAttribute("motivos", convocatoriaBean.getMotivoReduccionTasasIncompleto());
				
			}else{
				ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
				this.setRequestAttribute("motivos", arrayTasas);
			}				
			ArrayList<MotivoExencionTasaBean> arrayExenBean;
			arrayExenBean=cargarMotivosExencion(convocatoriaBean.getMotivoReduccionTasasCompleto());
			
			if(arrayExenBean != null){						
				this.setRequestAttribute(STRING_MOTIVOSCOMPLETOS, arrayExenBean);

			}else{
				ArrayList<MotivoExencionTasaBean> arrayExenBean2 = new ArrayList<MotivoExencionTasaBean>();
				this.setRequestAttribute(STRING_MOTIVOSCOMPLETOS, arrayExenBean2);
			}
		}
	}
		return convocatoriaBean;
}

	/**
	 * Cargar combos.
	 *
	 * @param formulario el formulario
	 */
	private void cargarCombos (AltaSolicitudPresencialForm formulario)
	{
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		ArrayList<ProvinciaBean> lProvinciaBean;
		ArrayList<ProvinciaExamenBean> lProvinciaExamenBean;
		provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		provinciaExamenQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		lProvinciaBean = provinciaManager.buscarProvinciaCombo(provinciaQuery);
		lProvinciaExamenBean = provinciaExamenManager.buscarProvinciaExamenCombo(provinciaExamenQuery);
		
		PaisQuery paisQuery = new PaisQuery(); 
		ArrayList<PaisBean> lPaisBean;
		paisQuery.addOrder(PaisQuery.DESCRIPCION, OrderType.ASC);
		lPaisBean = paisManager.buscarPaisCombo2(paisQuery);
		
		EspecialidadQuery especialidadQuery = new EspecialidadQuery();
		ArrayList<EspecialidadBean> lEspecialidadBean;
		especialidadQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
		especialidadQuery.addOrder(EspecialidadQuery.DESCRIPCION, OrderType.ASC);
		lEspecialidadBean = especialidadManager.buscarEspecialidadCombo(especialidadQuery);
		
		TipoDiscapacidadQuery tipoDiscapacidadQuery = new TipoDiscapacidadQuery();
		ArrayList<TipoDiscapacidadBean> lTipoDiscapacidadBean;
		lTipoDiscapacidadBean = tipoDiscapacidadManager.buscarTipoDiscapacidadCombo(tipoDiscapacidadQuery);
		
		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		ArrayList<TituloOficialBean> lTituloOficialBean;
		tituloOficialQuery.setEstado(Constantes.TITULOOFICIAL_ESTADO_ACTIVO);
		tituloOficialQuery.addOrder(TituloOficialQuery.DESCRIPCION, OrderType.ASC);
		lTituloOficialBean = tituloOficialManager.buscarTituloOficialCombo(tituloOficialQuery);
		 
		TipoPagoQuery tipoPagoQuery = new TipoPagoQuery();
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_CUENTA_INTEGER);
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_TARJETA_INTEGER);
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_EFECTIVO_INTEGER);
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_NINGUNO_INTEGER);		
		
		ArrayList<TipoPagoBean> lTipoPagoBean;
		lTipoPagoBean = tipoPagoManager.buscarTipoPagoCombo(tipoPagoQuery);
		// Petición incluir Tipo de pago
		TipoPagoBean tipoPagoExento = new TipoPagoBean();
		tipoPagoExento.setId(0);
		tipoPagoExento.setDescripcion("EXENTO");
		tipoPagoExento.setCodigo('X');
		lTipoPagoBean.add(tipoPagoExento);
		setRequestAttribute("tipoPagos", lTipoPagoBean);
		
		ArrayList<ComunidadesBean> comunidadesFN;
		ComunidadesQuery comunidadesQueryFN = new ComunidadesQuery();
		comunidadesQueryFN.setServicioFamnumerosa(true);
		comunidadesFN = comunidadesManager.buscarComunidadesCombo(comunidadesQueryFN);
		setRequestAttribute("listcomunidadesFN", comunidadesFN);
		
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
		setRequestAttribute("listcomunidadesDiscapacidad", comunidadesDiscapacidad);
		
		
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		ArrayList<EntidadFinancieraBean> lEntidadFinancieraBean;
		entidadFinancieraQuery.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		entidadFinancieraQuery.addOrder(EntidadFinancieraQuery.DESCRIPCION, OrderType.ASC);
		lEntidadFinancieraBean = entidadFinancieraManager.buscarEntidadFinancieraCombo(entidadFinancieraQuery);
		
		setRequestAttribute("entidadesBancarias", lEntidadFinancieraBean);

		//Cargamos el combo del tipo de documento
		TipoDocumentoQuery  tipoDocumentoQuery = new TipoDocumentoQuery();	
		List<TipoDocumentoBean> lTipoDocumentoBean;
		tipoDocumentoQuery.addOrder(TipoDocumentoQuery.DESCRIPCION,OrderType.ASC);
		lTipoDocumentoBean = tipoDocumentoManager.buscarTipoDocumentoCombo(tipoDocumentoQuery);
		TipoDocumentoBean tipoDocumento;
		int cont = lTipoDocumentoBean.size();
		String c;
		while (cont > 0) {
			tipoDocumento = lTipoDocumentoBean.get(cont-1);
			c = tipoDocumento.getCodigo();
			// Si no es un documentos admitido los descartamos del combo
			if (!"AS".equals(c) && !"JP".equals(c) && !"JR".equals(c) && !"JE".equals(c) && !"JX".equals(c)) {
				lTipoDocumentoBean.remove(cont-1);
			}
			cont--;			
		}	
		
		// Devolvemos el valor del combo
		setRequestAttribute("tipoDocumento", lTipoDocumentoBean);		
		
		
		setRequestAttribute("titulos", lTituloOficialBean);
		setRequestAttribute("tipoDiscapacidades", lTipoDiscapacidadBean);
		setRequestAttribute("especialidades", lEspecialidadBean);
		setRequestAttribute("provincias", lProvinciaBean);
		setRequestAttribute("provinciasExamen", lProvinciaExamenBean);
		setRequestAttribute("paises", lPaisBean);
		
	}
	
	/**
	 * Método que transforma una lista que contiene los motivos de reduccion completos en una lista de motivos de exención.
	 *
	 * @param motivoReduccionTasasCompleto el motivo reduccion tasas completo
	 * @return ArrayList<MotivoExencionTasaBean>
	 */
	private ArrayList<MotivoExencionTasaBean> cargarMotivosExencion(ArrayList<MotivoReduccionTasa> motivoReduccionTasasCompleto) {
	
		ArrayList<MotivoExencionTasaBean> arrayExenBean = new ArrayList<MotivoExencionTasaBean>();
		Boolean tieneDiscapacidad=false;
		if(motivoReduccionTasasCompleto != null){
			for(int i=0;i<motivoReduccionTasasCompleto.size();i++){
				if(motivoReduccionTasasCompleto.get(i).getId().toString().equals(Constantes.MOTIVO_DISCAPACIDAD)){
					tieneDiscapacidad=true;
				}
				MotivoExencionTasaBean motivoExencionTasaBean= toMotivoExencionTasaBean(motivoReduccionTasasCompleto.get(i));
				arrayExenBean.add(motivoExencionTasaBean);			
				}
		}
		//Si no se encuentra la discapacidad en los motivos de exención en la convocatoria o no tiene motivos de exención lo volcamos por defecto
		if(motivoReduccionTasasCompleto == null || tieneDiscapacidad==false){
			MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
			motivoReduccionTasaQuery.setId(Integer.valueOf(Constantes.MOTIVO_DISCAPACIDAD));
			ArrayList<MotivoReduccionTasaBean> arrayMotivoTasaBean = motivoReduccionTasaManager.buscarMotivoReduccionTasaAll(motivoReduccionTasaQuery);
			MotivoReduccionTasa motivoBean=motivoReduccionTasaManager.toMotivoReduccionTasa(arrayMotivoTasaBean.get(0));
			MotivoExencionTasaBean motivoExencionTasaBean= toMotivoExencionTasaBean(motivoBean);
			arrayExenBean.add(motivoExencionTasaBean);
			
		}
						
		return arrayExenBean;
	
}

	/**
	 * To motivo exencion tasa bean.
	 *
	 * @param motivoBean el motivo bean
	 * @return el motivo exencion tasa bean
	 */
	private MotivoExencionTasaBean toMotivoExencionTasaBean(MotivoReduccionTasa motivoBean) {
		
		MotivoExencionTasaBean motivoExencionTasaBean =new MotivoExencionTasaBean();
		motivoExencionTasaBean.setIdMotivoEx(motivoBean.getId());
		motivoExencionTasaBean.setDescripcionMoEx(motivoBean.getDescripcion());
		motivoExencionTasaBean.setTextoExplicativo(motivoBean.getTextoExplicativo());
		motivoExencionTasaBean.setPorcentajeDescuento(motivoBean.getPorcentajeDescuento());
		motivoExencionTasaBean.setEstadoMoEx(motivoBean.getEstado());
		motivoExencionTasaBean.setCodigoMoEx(motivoBean.getCodigo());
		motivoExencionTasaBean.setConvocatoriasMoEx(motivoBean.getConvocatorias());
		motivoExencionTasaBean.setPagoSolicitudsMoEx(motivoBean.getPagoSolicituds());
		
		return motivoExencionTasaBean;
	}
	
	
	/**
	 * Método que carga los datos del formulario con los obtendios de la solicitud almacenada en base de datos.
	 *
	 * @return AltaSolicitudPresencialForm
	 */

	
	private void checkRolUsuario(){
		UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		String username = usuarioBean.getNif();

		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(username);
		usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
		setRequestAttribute("user", usuarioBean.getIdPerfil().toString());
	}
	
	
	/**
	 * Cargar datos numero justificante.
	 *
	 * @param solicitudComunAuxiliarBean el solicitud comun auxiliar bean
	 * @param solicitudCcaaAuxiliarBean el solicitud ccaa auxiliar bean
	 * @param formulario el formulario
	 * @param convocatoriaBean el convocatoria bean
	 * @return el alta solicitud presencial form
	 */
	private AltaSolicitudPresencialForm cargarDatosNumeroJustificante(
			SolicitudComunAuxiliarBean solicitudComunAuxiliarBean,SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean,
			AltaSolicitudPresencialForm formulario, ConvocatoriasBean convocatoriaBean) {

		SimpleDateFormat formatoFecha= new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		//Datos Convocatoria
		// IdConvocatoria
		if(solicitudComunAuxiliarBean.getConvocatoria()!=null){
			formulario.setIdConvocatoria(solicitudComunAuxiliarBean.getConvocatoria().getId().toString());
		}
		
		//Nif
		if(solicitudComunAuxiliarBean.getNif()!=null){
			formulario.setNif(solicitudComunAuxiliarBean.getNif());
		}
		//Nombre
		if(solicitudComunAuxiliarBean.getNombre()!=null){
			formulario.setNombre(solicitudComunAuxiliarBean.getNombre());
		}
		
		//Primer Apellido
		if(solicitudComunAuxiliarBean.getPrimerApellido()!=null){
			formulario.setPrimerApellido(solicitudComunAuxiliarBean.getPrimerApellido());
		}
		
		//Segundo Apellido
		if(solicitudComunAuxiliarBean.getSegundoApellido()!=null){
			formulario.setSegundoApellido(solicitudComunAuxiliarBean.getSegundoApellido());
		}
		//Fecha Nacimiento
		if(solicitudComunAuxiliarBean.getFechaNacimiento()!=null){
			formulario.setFechaNacimiento(formatoFecha.format(solicitudComunAuxiliarBean.getFechaNacimiento()));
		}
		//Segundo Apellido
		if(solicitudComunAuxiliarBean.getNacionalidad()!=null){
			formulario.setNacionalidad(solicitudComunAuxiliarBean.getNacionalidad());
		}		
		//Sexo
		if(solicitudComunAuxiliarBean.getSexo()!=null){
			formulario.setSexo(solicitudComunAuxiliarBean.getSexo().toString());
		}
		//Email
		if(solicitudComunAuxiliarBean.getEmail()!=null){
			formulario.setEmail(solicitudComunAuxiliarBean.getEmail());
		}
		//Consentimiento
		if(solicitudComunAuxiliarBean.getIdConsentimiento()!=null){
			formulario.setCkConsentimiento(solicitudComunAuxiliarBean.getIdConsentimiento());
		}
		//Calle Domicilio
		if(solicitudComunAuxiliarBean.getCalleDomicilio()!=null){
			formulario.setCalleDomicilio(solicitudComunAuxiliarBean.getCalleDomicilio());
		}
		//Municipio Domicilio
		if(solicitudComunAuxiliarBean.getMunicipioDomicilio()!=null){
			formulario.setMunicipioDomicilio(solicitudComunAuxiliarBean.getMunicipioDomicilio());
		}
		
		//Pais
		if(solicitudComunAuxiliarBean.getPais()!=null){
			formulario.setIdPais(solicitudComunAuxiliarBean.getPais().getId().toString());
		}		
		//Provincia Domicilio
		if(solicitudComunAuxiliarBean.getProvincia()!=null){
			formulario.setIdProvinciaDomicilio(solicitudComunAuxiliarBean.getProvincia().getId().toString());
		}
		//Telefono
		if(solicitudComunAuxiliarBean.getTelefono()!=null){
			formulario.setTelefono1(solicitudComunAuxiliarBean.getTelefono());
		}
		//Telefono Auxiliar
		if(solicitudComunAuxiliarBean.getTelefonoAux()!=null){
			formulario.setTelefono2(solicitudComunAuxiliarBean.getTelefonoAux());
		}
		
		//Código Postal
		if(solicitudComunAuxiliarBean.getCodigoPostalDomicilio()!=null){
			formulario.setCodigoPostal(solicitudComunAuxiliarBean.getCodigoPostalDomicilio());
		}
		
		//Número Solicitud
		if(solicitudComunAuxiliarBean.getNumeroSolicitud()!=null){
			formulario.setNumeroSolicitud(solicitudComunAuxiliarBean.getNumeroSolicitud());
		}
		
		//Especialidad
		if(solicitudComunAuxiliarBean.getEspecialidad()!=null){
			formulario.setIdEspecialidad(solicitudComunAuxiliarBean.getEspecialidad().getId().toString());
		}
		
		//Provincia Examen
		if(solicitudComunAuxiliarBean.getProvinciaExamen()!=null){
			formulario.setIdProvinciaExamen(solicitudComunAuxiliarBean.getProvinciaExamen().getId().toString());
		}
		
		//Fecha Solicitud
		if(solicitudComunAuxiliarBean.getFechaSolicitud()!=null){
			formulario.setFechaSolicitud(formatoFecha.format(solicitudComunAuxiliarBean.getFechaSolicitud()));
		}
		
		//Tipo Discapacidad
		if(solicitudComunAuxiliarBean.getTipoDiscapacidad()!=null){
			formulario.setIdTipoDiscapacidad(solicitudComunAuxiliarBean.getTipoDiscapacidad().getId().toString());
		}
		
		//Porcentaje Discapacidad
		if(solicitudComunAuxiliarBean.getPorcentajeDiscapacidad()!=null){
			formulario.setPorcentajeDiscapacidad(solicitudComunAuxiliarBean.getPorcentajeDiscapacidad().toString());
		}
		
		//Reserva Discapacidad
		if(solicitudComunAuxiliarBean.getReservaDiscapacidad()!=null){
			if(solicitudComunAuxiliarBean.getReservaDiscapacidad()== Constantes.RESERVA_DISCAPACIDAD_S){
				formulario.setCkReservaDiscapacidad(true);
			}else{
				formulario.setCkReservaDiscapacidad(false);
			}
		}
		
		//Detalle Discapacidad
		if(solicitudComunAuxiliarBean.getDetalleDiscapacidad()!=null){
			formulario.setAdaptacionDiscapacidad(solicitudComunAuxiliarBean.getDetalleDiscapacidad());
		}
		
		//Título Oficial
		if(solicitudComunAuxiliarBean.getTituloOficial()!=null){
			formulario.setIdTitulo(solicitudComunAuxiliarBean.getTituloOficial().getId().toString());
		}
		
		//Otros Títulos
		if(solicitudComunAuxiliarBean.getOtrosTitulos()!=null){
			formulario.setOtrosTitulos(solicitudComunAuxiliarBean.getOtrosTitulos());
		}
		
		
		//Motivo Reducción Tasa
		if(solicitudComunAuxiliarBean.getMotivoReduccionTasa()!=null){
			// Si el usuario pulso discapacidad este o no en la convocatoria, se pone en el formulario
			if(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString().equals(Constantes.MOTIVO_DISCAPACIDAD)){
				formulario.setIdMotivosEx(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString());
			}
			else{
			if(convocatoriaBean.getMotivoReduccionTasasIncompleto()!=null){
				for(int i=0; i < convocatoriaBean.getMotivoReduccionTasasIncompleto().size(); i++){
					if(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().equals(convocatoriaBean.getMotivoReduccionTasasIncompleto().get(i).getId())){
						formulario.setIdMotivosRed(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString());
						break;
					}
				}
			}
			if(formulario.getIdMotivosRed().equals("") && convocatoriaBean.getMotivoReduccionTasasCompleto()!=null){
				
					for(int i=0; i < convocatoriaBean.getMotivoReduccionTasasCompleto().size(); i++){
						if(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().equals(convocatoriaBean.getMotivoReduccionTasasCompleto().get(i).getId())){
							formulario.setIdMotivosEx(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString());
							break;
						}
					}
				
			}
		}
		}
		
		// Si la convocatoria no cuenta con los motivos de exención/reducción que se indico en el formulario790 no actualizamos la parte relacionada con el pago
		if(!formulario.getIdMotivosRed().equals("") || !formulario.getIdMotivosEx().equals("")){
			
			logger.info("El motivo de exención/reducción de la solicitud no esta habilitado en la convocatoria");
			//Tipo Pago
			if(solicitudComunAuxiliarBean.getIdTipoPago()!=null){
				if(solicitudComunAuxiliarBean.getIdTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_INT)) && solicitudComunAuxiliarBean.getImporte()!=null
						&& (solicitudComunAuxiliarBean.getImporte().equals("0.0")||solicitudComunAuxiliarBean.getImporte().equals("0"))){
					formulario.setIdTipoPago(String.valueOf(Constantes.TIPO_PAGO_EXENTO_INT));	
				}else{
					formulario.setIdTipoPago(solicitudComunAuxiliarBean.getIdTipoPago());	
				}
			}
			else if(solicitudComunAuxiliarBean.getIdTipoPago()==null
					&& solicitudComunAuxiliarBean.getImporte()!=null
							&& (solicitudComunAuxiliarBean.getImporte().equals("0.0")||solicitudComunAuxiliarBean.getImporte().equals("0"))){
				

					formulario.setIdTipoPago(String.valueOf(Constantes.TIPO_PAGO_EXENTO_INT));	
				
			}
		
		if(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString().equals(Constantes.MOTIVO_DISCAPACIDAD)){
			formulario.setIdMotivosEx(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString());
		}
		//Comunidad autónoma
		if(solicitudCcaaAuxiliarBean.getIdComunidad()!="" && solicitudCcaaAuxiliarBean.getIdComunidad()!=null){
			if(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString().equals(Constantes.MOTIVO_DISCAPACIDAD)){
				formulario.setComunidadDD(solicitudCcaaAuxiliarBean.getIdComunidad().toString());
			}
			else if(solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString().equals(Constantes.MOTIVO_FNUMEROSAGENERAL) ||
					solicitudComunAuxiliarBean.getMotivoReduccionTasa().getId().toString().equals(Constantes.MOTIVO_FNUMEROSAESPECIAL)){
				formulario.setComunidadFN(solicitudCcaaAuxiliarBean.getIdComunidad().toString());				
			}
		}
		//Número Titulo Familia Numerosa
		if(solicitudCcaaAuxiliarBean!=null && solicitudCcaaAuxiliarBean.getTituloFamnumerosa()!=null && solicitudCcaaAuxiliarBean.getTituloFamnumerosa()!=""){
			formulario.setNumeroTituloFN(solicitudCcaaAuxiliarBean.getTituloFamnumerosa());
		}

		}else {
			
		//El tipo pago e importe se reflejan en el formulario haya o no motivo de exencion/reducción
			
			//Tipo Pago
			if(solicitudComunAuxiliarBean.getIdTipoPago()!=null){
				formulario.setIdTipoPago(solicitudComunAuxiliarBean.getIdTipoPago());
			}
		}		
		//Importe
		if(solicitudComunAuxiliarBean.getImporte()!=null){
			String[]importeAux = {"",""};
			String importe = solicitudComunAuxiliarBean.getImporte().replace(",", ".");
			importeAux = importe.split("[.]");
			formulario.setImporte(String.valueOf(importeAux[0]));
			formulario.setImporteDecimal(String.valueOf(importeAux[1]));
		}
		
		return formulario;
	}
	
	
	/**
	 * Obtiene el usuario manager.
	 *
	 * @return usuarioManager UsuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager UsuarioManager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return centroGestorManager CentroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager CentroGestorManager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el provincia manager.
	 *
	 * @return provinciaManager ProvinciaManager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager ProvinciaManager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
	}
	
	/**
	 * Obtiene el provincia examen manager.
	 *
	 * @return provinciaExamenManager ProvinciaExamenManager
	 */
	public ProvinciaExamenManager getProvinciaExamenManager() {
		return provinciaExamenManager;
	}
	
	/**
	 * Establece el provincia examen manager.
	 *
	 * @param provinciaExamenManager ProvinciaExamenManager
	 */
	public void setProvinciaExamenManager(
			ProvinciaExamenManager provinciaExamenManager) {
		this.provinciaExamenManager = provinciaExamenManager;
	}

	/**
	 * Obtiene el pais manager.
	 *
	 * @return paisManager PaisManager
	 */
	public PaisManager getPaisManager() {
		return paisManager;
	}

	/**
	 * Establece el pais manager.
	 *
	 * @param paisManager PaisManager
	 */
	public void setPaisManager(PaisManager paisManager) {
		this.paisManager = paisManager;
	}

	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return especialidadManager EspecialidadManager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager EspecialidadManager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
	}

	/**
	 * Obtiene el tipo discapacidad manager.
	 *
	 * @return tipoDiscapacidadManager TipoDiscapacidadManager
	 */
	public TipoDiscapacidadManager getTipoDiscapacidadManager() {
		return tipoDiscapacidadManager;
	}

	/**
	 * Establece el tipo discapacidad manager.
	 *
	 * @param tipoDiscapacidadManager TipoDiscapacidadManager
	 */
	public void setTipoDiscapacidadManager(
			TipoDiscapacidadManager tipoDiscapacidadManager) {
		this.tipoDiscapacidadManager = tipoDiscapacidadManager;
	}

	/**
	 * Obtiene el titulo oficial manager.
	 *
	 * @return tituloOficialManager TituloOficialManager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el titulo oficial manager.
	 *
	 * @param tituloOficialManager TituloOficialManager
	 */
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
	}

	/**
	 * Obtiene el tipo pago manager.
	 *
	 * @return tipoPagoManager TipoPagoManager
	 */
	public TipoPagoManager getTipoPagoManager() {
		return tipoPagoManager;
	}

	/**
	 * Establece el tipo pago manager.
	 *
	 * @param tipoPagoManager TipoPagoManager
	 */
	public void setTipoPagoManager(TipoPagoManager tipoPagoManager) {
		this.tipoPagoManager = tipoPagoManager;
	}

	/**
	 * Obtiene el entidad financiera manager.
	 *
	 * @return entidadFinancieraManager EntidadFinancieraManager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager EntidadFinancieraManager
	 */
	public void setEntidadFinancieraManager(
			EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
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
	 * Obtiene el convocatoria manager.
	 *
	 * @return el convocatoria manager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager el nuevo convocatoria manager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
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
	public void setPlantillaPropiosManager(PlantillaPropiosManager plantillaPropiosManager) {
		this.plantillaPropiosManager = plantillaPropiosManager;
	}
		
	/**
	 * Obtiene el campo propios manager.
	 *
	 * @return el campo propios manager
	 */
	public CamposPropiosManager getCampoPropiosManager() {
		return campoPropiosManager;
	}

	/**
	 * Establece el campo propios manager.
	 *
	 * @param campoPropiosManager el nuevo campo propios manager
	 */
	public void setCampoPropiosManager(CamposPropiosManager campoPropiosManager) {
		this.campoPropiosManager = campoPropiosManager;
	}

	/**
	 * Obtiene el solicitud comun auxiliar manager.
	 *
	 * @return el solicitud comun auxiliar manager
	 */
	public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
		return solicitudComunAuxiliarManager;
	}

	/**
	 * Establece el solicitud comun auxiliar manager.
	 *
	 * @param solicitudComunAuxiliarManager el nuevo solicitud comun auxiliar manager
	 */
	public void setSolicitudComunAuxiliarManager(
			SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
	}

	/**
	 * Obtiene el solicitud propio auxiliar manager.
	 *
	 * @return el solicitud propio auxiliar manager
	 */
	public SolicitudPropioAuxiliarManager getSolicitudPropioAuxiliarManager() {
		return solicitudPropioAuxiliarManager;
	}

	/**
	 * Establece el solicitud propio auxiliar manager.
	 *
	 * @param solicitudPropioAuxiliarManager el nuevo solicitud propio auxiliar manager
	 */
	public void setSolicitudPropioAuxiliarManager(
			SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager) {
		this.solicitudPropioAuxiliarManager = solicitudPropioAuxiliarManager;
	}

	/**
	 * Obtiene el solicitud ccaa auxiliar manager.
	 *
	 * @return el solicitud ccaa auxiliar manager
	 */
	public SolicitudCcaaAuxiliarManager getSolicitudCcaaAuxiliarManager() {
		return solicitudCcaaAuxiliarManager;
	}

	/**
	 * Establece el solicitud ccaa auxiliar manager.
	 *
	 * @param solicitudCcaaAuxiliarManager el nuevo solicitud ccaa auxiliar manager
	 */
	public void setSolicitudCcaaAuxiliarManager(
			SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager) {
		this.solicitudCcaaAuxiliarManager = solicitudCcaaAuxiliarManager;
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
	 * Obtiene el motivo reduccion tasa manager.
	 *
	 * @return el motivo reduccion tasa manager
	 */
	public MotivoReduccionTasaManager getMotivoReduccionTasaManager() {
		return motivoReduccionTasaManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoReduccionTasaManager el nuevo motivo reduccion tasa manager
	 */
	public void setMotivoReduccionTasaManager(
			MotivoReduccionTasaManager motivoReduccionTasaManager) {
		this.motivoReduccionTasaManager = motivoReduccionTasaManager;
	}

	/**
	 * Obtiene el tipo documento manager.
	 *
	 * @return el tipo documento manager
	 */
	public TipoDocumentoManager getTipoDocumentoManager() {
		return tipoDocumentoManager;
	}

	/**
	 * Establece el tipo documento manager.
	 *
	 * @param tipoDocumentoManager el nuevo tipo documento manager
	 */
	public void setTipoDocumentoManager(TipoDocumentoManager tipoDocumentoManager) {
		this.tipoDocumentoManager = tipoDocumentoManager;
	}

	
}
