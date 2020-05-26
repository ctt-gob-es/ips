package es.map.ipsg.action.solicitud;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
//import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolComunAuxiliarViewQuery;
import es.map.ips.model.SolComunIncidenciasViewQuery;
import es.map.ips.model.SolComunPresencialesViewQuery;
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosAuxiliarQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.UsuarioQuery;
//import es.map.ips.model.TipoSolicitudQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudPropiosAuxiliarBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EstadoSolicitudManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudPropioAuxiliarManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class ExportarExcelSolicitudesSpring.
 *
 * @author amartinl
 */
public class ExportarExcelSolicitudesSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante STRING_EXPORTARCONINCIDENCIAS. */
	private static final String STRING_EXPORTARCONINCIDENCIAS = "ExportarConIncidencias";
	
	/** La constante STRING_EXPORTARPRESENCIALES. */
	private static final String STRING_EXPORTARPRESENCIALES = "ExportarPresenciales";
	
	/** La constante STRING_EXPORTARAUXILIARES. */
	private static final String STRING_EXPORTARAUXILIARES = "ExportarAuxiliares";
	
	/** La constante STRING_SIMPLEDATEFORMATHORA. */
	private static final String STRING_SIMPLEDATEFORMATHORA = "yyyy-MM-dd HH:mm:ss";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** La constante STRING_ERROREXPORTAR. */
	private static final String STRING_ERROREXPORTAR = "errorExportar";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ExportarExcelSolicitudesSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);

	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el campo propios manager. */
	private CamposPropiosManager campoPropiosManager;
	
	/** el solicitud propios manager. */
	private SolicitudesPropiosManager solicitudPropiosManager;
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el solicitud propio auxiliar manager. */
	private SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
	/** el solicitud ccaa auxiliar manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager;

	/**
	 * Crea una nueva exportar excel solicitudes spring.
	 */
	public ExportarExcelSolicitudesSpring() {
		try {
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			setModeloManager((ModeloManager)getBean("modelosManager"));
			setCampoPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
			setSolicitudPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
			setSolicitudPropioAuxiliarManager((SolicitudPropioAuxiliarManager)getBean("solicitudPropioAuxiliarManager"));
			setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager) getBean("motivoReduccionTasaManager"));
			setSolicitudCcaaAuxiliarManager((SolicitudCcaaAuxiliarManager) getBean("solicitudCcaaAuxiliarManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error ExportarExcelSolicitudesSpring:",e);
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
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(MESSAGE_RESOUCE);	
		String menu_solicitudes = RESOURCE_BUNDLE_2.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		//******************************************************************
		try{
			//Cogemos el form del jsp
			BuscarSolicitudesForm formulario;
			formulario = (BuscarSolicitudesForm) form;
			String checkBox = formulario.getMarcarTodo();
			SpringMessages errors = new SpringMessages();

			/*
			 * En caso de marcar todo, se exportará toda la búsqueda,
			 * sino sólo las solicitudes seleccionadas
			 * 
			 */
			ArrayList<SolicitudBean> aSolicitudes;
			ArrayList<SolicitudBean> aSolicitudesDatosCompletos = new ArrayList<SolicitudBean>();
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
			RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
			SolComunRegistradasViewQuery solicitudesRegistradasQuery = new SolComunRegistradasViewQuery();
			SolComunIncidenciasViewQuery solicitudesIncidenciasQuery = new SolComunIncidenciasViewQuery();
			SolComunPresencialesViewQuery solicitudesPresencialesQuery = new SolComunPresencialesViewQuery();
			SolComunAuxiliarViewQuery solicitudesAuxiliaresQuery = new SolComunAuxiliarViewQuery();

			String campoOrden =null;
			if ("TodoOk".equals(checkBox)) {
				//Creamos la query para mostrar todas las solicitudes
				if (STRING_EXPORTARCONINCIDENCIAS.equals(formulario.getAccion())) {
					logger.info("Exportar Excel Total de Solicitudes Con Incidencias");
					if(formulario.getCampo()!= null){			
						try{
							campoOrden=cargarCamposSolicitudesIncidencias(formulario.getCampo());
						}catch(Exception e){	
						logger.error("Error en cargarCamposSolicitudesIncidencias", e);
						}
					}
					logger.info("Se va a crear la query");			
					solicitudesIncidenciasQuery= crearQuerySolicitudesIncidenciasView(formulario, campoOrden);
				}else if (STRING_EXPORTARPRESENCIALES.equals(formulario.getAccion())) {
					logger.info("Exportar Excel Total de Solicitudes Presenciales");
					if(formulario.getCampo()!= null){			
						try{
							campoOrden=cargarCamposSolicitudesPresenciales(formulario.getCampo());
						}catch(Exception e){	
						logger.error("Error en cargarCamposSolicitudesPresenciales", e);
						}
					}
					logger.info("Se va a crear la query");
					solicitudesPresencialesQuery = crearQuerySolicitudPresencial(formulario, campoOrden);
				}else if (STRING_EXPORTARAUXILIARES.equals(formulario.getAccion())) {
					logger.info("Exportar Excel Total de Solicitudes Auxiliares");
					if(formulario.getCampo()!= null){			
						try{
							campoOrden=cargarCamposSolicitudesAuxiliares(formulario.getCampo());
						}catch(Exception e){	
						logger.error("Error en cargarCamposSolicitudesAuxiliares", e);
						}
					}
					logger.info("Se va a crear la query");
					solicitudesAuxiliaresQuery = crearQuerySolicitudAuxiliar(formulario, campoOrden);
				}else {
					logger.info("Exportar Excel Total de Solicitudes");
					if(formulario.getCampo()!= null){			
						try{
							campoOrden=cargarCamposSolicitudesRegistradas(formulario.getCampo());
						}catch(Exception e){	
						logger.error("Error en cargarCamposSolicitudesRegistradas", e);
						}
					}
					logger.info("Se va a crear la query");
					solicitudesRegistradasQuery = crearQuerySolicitudRegistrada(formulario, campoOrden);
				}

			}else {
				//Recuperamos las solicitudes que ha marcado
				String[] aSolicitudesSel =  formulario.getSolicitudesSel();

				for(int i=0; i < aSolicitudesSel.length; i++)
				{
					Long idSolicitudSel = Long.parseLong(aSolicitudesSel[i]);
					if (STRING_EXPORTARCONINCIDENCIAS.equals(formulario.getAccion())) {
						logger.info("Exportar Excel de Solicitudes Con Incidencias");
						solicitudesIncidenciasQuery.addIdSolicitudIn(idSolicitudSel);
					}else if (STRING_EXPORTARPRESENCIALES.equals(formulario.getAccion())) {
						logger.info("Exportar Excel Total de Solicitudes Presenciales");
						solicitudesPresencialesQuery.addIdSolicitudIn(idSolicitudSel);
					}else if (STRING_EXPORTARAUXILIARES.equals(formulario.getAccion())) {
						logger.info("Exportar Excel Total de Solicitudes Auxiliares");
						solicitudesAuxiliaresQuery.addIdSolicitudIn(idSolicitudSel);
					}else {
						logger.info("Exportar Excel Total de Solicitudes");
						solicitudesRegistradasQuery.addIdSolicitudIn(idSolicitudSel);
					}

				}
			}

			//Recuperamos todos los datos de las solicitudes seleccionadas
			if(STRING_EXPORTARCONINCIDENCIAS.equals(formulario.getAccion()))
			{	
				// Si se trata de solicitudes con incidencias, se consulta la vista
				aSolicitudes = solicitudesRegistradasManager.buscarSolicitudesIncidenciasVista(solicitudesIncidenciasQuery);

			}
			else if(STRING_EXPORTARPRESENCIALES.equals(formulario.getAccion()))
			{	
				aSolicitudes = solicitudesRegistradasManager.buscarSolicitudesPresencialesVista(solicitudesPresencialesQuery);
			}
			else if(STRING_EXPORTARAUXILIARES.equals(formulario.getAccion()))
			{	
				aSolicitudes = solicitudComunAuxiliarManager.buscarSolicitudesAuxiliarVista(solicitudesAuxiliaresQuery);
			}
			else
			{	
				// Si se trata de solicitudes registradas, se consulta la vista
				aSolicitudes =solicitudesRegistradasManager.buscarSolicitudesRegistradasVista(solicitudesRegistradasQuery);
				aSolicitudes = eliminarDuplicados(aSolicitudes);
				
			}


			Iterator<SolicitudBean> it = aSolicitudes.iterator();
			//Recogemos cada solicitud completa para insertar en la excel 
			//Variables para insertar en la Solicitud
			ArrayList<PagoSolicitudBean> aPagoSolicitud; 
			ArrayList<RegistroSolicitudBean> aRegistrosolicitud;
			solicitudQuery = new SolicitudComunQuery();
			
			//Tomamos el usuario que se ha logueado
			String sUsernameLogin = recuperarUsuario();

			//Obtenemos el Usuario que esta logueado en la aplicacion para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que esta logueado

			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			String sPerfilUsuario = usuarioManager.comprobarPerfilUsuario(usuarioBean.getIdPerfil());
			
			while (it.hasNext())
			{
				SolicitudBean solicitudBean = (SolicitudBean) it.next();
				solicitudQuery.setIdSolicitud(solicitudBean.getId());
				//Completamos los datos de PagoSolicitud
				pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
				pagoSolicitudQuery.setResultado("OK");
				//pagoSolicitudQuery.addOrder(PagoSolicitudQuery.ID, OrderType.DESC);
				pagoSolicitudQuery.setJoin_motivoReduccionTasa(true);
				aPagoSolicitud = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
				if(aPagoSolicitud != null && aPagoSolicitud.size() > 0) {
					//Obtenemos el ultimo pagoSolicitud
					PagoSolicitudBean pagoSolicitud = obtenerPagoSolicitudCorrecto(aPagoSolicitud);
					
					if(pagoSolicitud != null && pagoSolicitud.getResultado().equals("OK")) {
						//Tipo de Pago
						solicitudBean.setDescripcionTipoPago(pagoSolicitud.getTipo().toString());
						//Fecha de Pago
						SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMATHORA);
						String fechaPago= "";
						if (pagoSolicitud.getFechaIntento() != null) {
							fechaPago = formatoFecha.format(pagoSolicitud.getFechaIntento());
							if(fechaPago != null){
								solicitudBean.setFechaPago(fechaPago);
							}
						}
						solicitudBean.setNrc(pagoSolicitud.getNrc());
						solicitudBean.setImporte(pagoSolicitud.getImporte());
						if (pagoSolicitud.getMotivoReduccionTasa()!=null && pagoSolicitud.getMotivoReduccionTasa().getId() != null) {
							solicitudBean.setIdMotivoExencion(pagoSolicitud.getMotivoReduccionTasa().getId());
							if (!StringUtils.isEmpty(pagoSolicitud.getMotivoReduccionTasa().getDescripcion())) {
								solicitudBean.setMotivoExencionDesc(pagoSolicitud.getMotivoReduccionTasa().getDescripcion());
							}
							if(pagoSolicitud.getMotivoReduccionTasa().getId()==2){
								solicitudBean.setEsDesempleo(true);
							}
							if(pagoSolicitud.getMotivoReduccionTasa().getId()==3 
									|| pagoSolicitud.getMotivoReduccionTasa().getId()==5){
								solicitudBean.setEsFNumerosa(true);
							}
							if(pagoSolicitud.getMotivoReduccionTasa().getId()==1){
								solicitudBean.setEsDiscapacidad(true);
							}
						}

					}
				}
				if((STRING_EXPORTARPRESENCIALES.equals(formulario.getAccion()) == false) && (STRING_EXPORTARAUXILIARES.equals(formulario.getAccion()) == false)) {
					//FUN 3.5 - INI
					solicitudBean.setAdmitido(obtieneAdmitido(solicitudBean));
					solicitudBean.setEstadoPID(obtieneEstadoPID(solicitudBean));
					//FUN 3.5 - FIN
				}
				// Tipo Exencion
				if(STRING_EXPORTARAUXILIARES.equals(formulario.getAccion()) && solicitudBean.getMotivoReduccion() != null  && solicitudBean.getMotivoReduccion() > 0) {
					cargarCamposFNAux(solicitudBean);
				}
				solicitudBean.setPagoSolicitudes(aPagoSolicitud);
				//Completamos los datos de RegistroSolicitud
				registroSolicitudQuery.setSolicitudComun(solicitudQuery);
				registroSolicitudQuery.setResultado("OK");
				aRegistrosolicitud = registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);
				Iterator<RegistroSolicitudBean> itRegistroSolicitud = aRegistrosolicitud.iterator();
				while(itRegistroSolicitud.hasNext())
				{
					RegistroSolicitudBean registroSolicitudBean = (RegistroSolicitudBean) itRegistroSolicitud.next();
					if(registroSolicitudBean.getResultado().equals("OK"))
					{
						//Fecha de Registro
						SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMATHORA);
						String fechaRegistro= "";
						if (registroSolicitudBean.getFechaRegistro() != null) {
							fechaRegistro = formatoFecha.format(registroSolicitudBean.getFechaRegistro());
							if(fechaRegistro != null){
								solicitudBean.setFechaRegistro(fechaRegistro);
							}
						}
						//Número de Registro
						if(registroSolicitudBean.getNumeroRegistro() != null)
						{
							solicitudBean.setNumeroRegistro(registroSolicitudBean.getNumeroRegistro());
						}
					}
				}
				
				//Buscamos campos CCAA y NUM.Titulo solo para F.Numerosa INI
				cargarCamposFN(solicitudQuery, solicitudBean);
				//Buscamos campos CCAA y NUM.Titulo solo para F.Numerosa FIN
				
				solicitudBean.setRegistroSolicitudes(aRegistrosolicitud);

				//Introducimos el Bean de Solicitud al Array con las solicitudes completas
				aSolicitudesDatosCompletos.add(solicitudBean);
			}

			//Creación de Excel con las solicitudes recuperadas
			if(null != aSolicitudesDatosCompletos && aSolicitudesDatosCompletos.size()>0 ){
				generarExcel(aSolicitudesDatosCompletos, formulario, sPerfilUsuario);
				return null;
			}else{
				EstadoSolicitud estadoSolicitud = estadoSolicitudManager.obtenerEstadoSolicitud(formulario.getIdEstado().intValue());

				if(formulario.getIdEstado().intValue() == Constantes.ESTADO_SOLICITUD_SIN_INTENTO_PAGO ){	
					String mensaje = RESOURCE_MESSAGE.getString("field.solicitud.EstadoSolicitud8");
					SpringMessage error = new SpringMessage("field.solicitudIncidencia.errorExportar1", mensaje);
					errors.add(STRING_ERROREXPORTAR, error);	
				}else if( formulario.getIdEstado().intValue() == Constantes.ESTADO_SOLICITUD_SIN_INTENTO_REGISTRO){	
					String mensaje = RESOURCE_MESSAGE.getString("field.solicitud.EstadoSolicitud9");
					SpringMessage error = new SpringMessage("field.solicitudIncidencia.errorExportar1", mensaje);
					errors.add(STRING_ERROREXPORTAR, error);	
				}else if((formulario.getIdEstado().intValue() == Constantes.ESTADO_SOLICITUD_NO_PAGADO || formulario.getIdEstado().intValue() == Constantes.ESTADO_SOLICITUD_NO_REGISTRADO) && null!=estadoSolicitud){
					SpringMessage error = new SpringMessage("field.solicitudIncidencia.errorExportar2", estadoSolicitud.getDescripcion());
					errors.add(STRING_ERROREXPORTAR, error);		
				}else if(null!=estadoSolicitud){
					SpringMessage error = new SpringMessage("field.solicitudIncidencia.errorExportar3",  estadoSolicitud.getDescripcion());
					errors.add(STRING_ERROREXPORTAR, error);
				}else{
					SpringMessage error = new SpringMessage("field.solicitudIncidencia.errorExportar3",  formulario.getIdEstado().intValue());
					errors.add(STRING_ERROREXPORTAR, error);
				}
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
				return "error";
			}

		}catch(Exception eGenerico){
			logger.error("Error ExportarExcelSolicitudesSpring - doExecute:",eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
			return "errorGenerico";
		}	

	}

	/**
	 * Obtiene el ultimo pago de la solicitud
	 * 
	 * @param aPagoSolicitud
	 * @return
	 */
	private PagoSolicitudBean obtenerPagoSolicitudCorrecto(ArrayList<PagoSolicitudBean> aPagoSolicitud) {
		ArrayList<Long> listaIdsPago = new ArrayList<Long>();
		for (PagoSolicitudBean pagoSolicitudBean : aPagoSolicitud) {
			listaIdsPago.add(pagoSolicitudBean.getId());
		}
		Collections.sort(listaIdsPago, Collections.reverseOrder());
		PagoSolicitudBean pagoSolicitud = null;
		
		for (PagoSolicitudBean pagoSolBean : aPagoSolicitud) {
			if (pagoSolBean.getId().equals(listaIdsPago.get(0))) {
				pagoSolicitud = (PagoSolicitudBean) pagoSolBean;
			}
		}
		return pagoSolicitud;
	}

	/**
	 * Cargar campos FN aux.
	 *
	 * @param solicitudBean el solicitud bean
	 */
	private void cargarCamposFNAux(SolicitudBean solicitudBean) {
		MotivoReduccionTasaBean motivoReduccionBen = motivoReduccionTasaManager.obtenerMotivoReduccionTasa(solicitudBean.getMotivoReduccion());
		if (motivoReduccionBen != null && !StringUtils.isEmpty(motivoReduccionBen.getDescripcion())) {
			solicitudBean.setMotivoExencionDesc(motivoReduccionBen.getDescripcion());
		}
		if (solicitudBean.getId() != null && solicitudBean.getId() > 0) {
			SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean = null;
			try {
				solicitudCcaaAuxiliarBean = solicitudCcaaAuxiliarManager.obtenerSolicitudCcaaAuxById(solicitudBean.getId());
			} catch (Exception e) {
				logger.error("Error al obtener en la tabla SOLICITUD_CCAA_AUXILIAR Con id: "+solicitudBean.getId());
			}
			
			if (solicitudCcaaAuxiliarBean != null) {
				if (solicitudCcaaAuxiliarBean.getComunidad() != null && !StringUtils.isEmpty(solicitudCcaaAuxiliarBean.getComunidad().getDescripcion())) {
					solicitudBean.setComunidadDesc(solicitudCcaaAuxiliarBean.getComunidad().getDescripcion());
				}
				if (!StringUtils.isEmpty(solicitudCcaaAuxiliarBean.getTituloFamnumerosa())) {
					solicitudBean.setTituloFN(solicitudCcaaAuxiliarBean.getTituloFamnumerosa());
				}
			}
		}
	}

	/**
	 * Cargar campos FN.
	 *
	 * @param solicitudQuery el solicitud query
	 * @param solicitudBean el solicitud bean
	 */
	private void cargarCamposFN(SolicitudComunQuery solicitudQuery, SolicitudBean solicitudBean) {
		if (solicitudBean.getIdMotivoExencion() != null
				&& (solicitudBean.getIdMotivoExencion() == 1 || solicitudBean.getIdMotivoExencion() == 3 || solicitudBean.getIdMotivoExencion() == 5) && solicitudQuery.getIdSolicitud() > 0) {
			SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
			solicitudCcaaQuery.setSolicitudComun(solicitudQuery);
			solicitudCcaaQuery.addOrder(SolicitudCcaaQuery.IDSOLICITUDCCAA,OrderType.ASC);
			solicitudCcaaQuery.setJoin_comunidades(true);
			List<SolicitudCcaaBean> solicitudCCAAList = solicitudCcaaManager.buscarSolicitudAll(solicitudCcaaQuery);
			if (solicitudCCAAList != null && solicitudCCAAList.size() > 0) {
				if (solicitudCCAAList.get(0) != null && solicitudCCAAList.get(0).getComunidad() != null 
						&& !StringUtils.isEmpty(solicitudCCAAList.get(0).getComunidad().getDescripcion())) {
					solicitudBean.setComunidadDesc(solicitudCCAAList.get(0).getComunidad().getDescripcion());
				}
				if (!StringUtils.isEmpty(solicitudCCAAList.get(0).getTituloFamnumerosa())) {
					solicitudBean.setTituloFN(solicitudCCAAList.get(0).getTituloFamnumerosa());
				}
			}
		}
	}

	/**
	 * Metodo que suprime solicitudes con registros duplicados.
	 *
	 * @param lSolicitudBean el l solicitud bean
	 * @return listaSolcitudesAux
	 */
	private ArrayList<SolicitudBean> eliminarDuplicados(List<SolicitudBean> lSolicitudBean) {
		ArrayList<SolicitudBean> listaSolcitudesAux = new ArrayList<SolicitudBean>();
		ArrayList<Long> listaIdSolicitudes = new ArrayList<Long>();

		for (int i=0; i<lSolicitudBean.size(); i++){
			if(!listaIdSolicitudes.contains(lSolicitudBean.get(i).getId())){
				listaIdSolicitudes.add(lSolicitudBean.get(i).getId());
				listaSolcitudesAux.add(lSolicitudBean.get(i));
			}else{
				logger.error("La solicitud "+lSolicitudBean.get(i).getId()+ " está duplicada en la vista SOL_COMUN_REGISTRADAS_VIEW");
			}
		}
		return listaSolcitudesAux;
	}
	
	
	/**
	 * Generación del excel.
	 *
	 * @param aSolicitudesDatosCompletos ArrayList<SolicitudBean>
	 * @param formulario el formulario
	 * @param sPerfilUsuario el s perfil usuario
	 */
	public void generarExcel(ArrayList<SolicitudBean> aSolicitudesDatosCompletos,BuscarSolicitudesForm formulario, String sPerfilUsuario) { // throws Exception{

		int iFila = 1;
		int iCol = 0;
		// Proceso la informaci?n y genero el xls
		XSSFWorkbook objLibro = new XSSFWorkbook();
		//Establecer el tipo de fuente 
		XSSFFont fuente = objLibro.createFont();
		fuente.setColor(IndexedColors.BLUE.getIndex());
		fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// Luego creamos el objeto que se encargar? de aplicar el estilo a la celda
		XSSFCellStyle estiloCelda = objLibro.createCellStyle();
		estiloCelda.setAlignment(HSSFCellStyle. ALIGN_JUSTIFY);
		estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloCelda.setFont(fuente);
		estiloCelda.setWrapText(true);

		XSSFDataFormat format = objLibro.createDataFormat();
		XSSFCellStyle cellStyle = objLibro.createCellStyle();
		String numberFormat = "###.00";
		cellStyle.setDataFormat(format.getFormat(numberFormat));

		SolicitudBean solicitudBeanAux = (SolicitudBean)aSolicitudesDatosCompletos.get(0);
		String idModeloActual = null;
		if(formulario.getIdModelo()!=null && formulario.getIdModelo()!=0){
			idModeloActual = formulario.getIdModelo().toString();
		} else if (solicitudBeanAux.getIdModelo() != null && !solicitudBeanAux.getIdModelo().equals("0")) {
			idModeloActual = solicitudBeanAux.getIdModelo();
		} else {
			//Forzamos el modelo al 790001 para las solicitudes presenciales
			idModeloActual = Integer.toString(Constantes.ID_MODELO_790001);
		}
		solicitudBeanAux.getIdModelo();
		ModeloQuery modeloQuery = new ModeloQuery();
		modeloQuery.setIdModelo(Integer.parseInt(idModeloActual));
		List<ModeloBean> modeloBean = modeloManager.buscarModeloCombo(modeloQuery);
		String numModelo="";
		if(modeloBean!=null){
			numModelo=modeloBean.get(0).getNumModelo();
		}
		// Se crea la hoja del Libro de la Excel
		XSSFSheet hoja1 = objLibro.createSheet(properties.getProperty("conf.nombreHojaExcelModelo") +"790"); //Hoja modelo numModelo
		//PRIMERA FILA QUE CONTENDR? LOS NOMBRES DE LOS CAMPOS
		// Proceso la informaci?n y genero el xls. N?mero de la Fila
		XSSFRow fila = hoja1.createRow((short)iFila);

		//Se usa para ajustar el ancho de las columnas
		Sheet sheet = objLibro.getSheetAt(0);

		// Creamos la celda, aplicamos el estilo y definimos el tipo de dato que contendrá la celda
		//ID //0
		XSSFCell celda = fila.createCell(iCol);	//N?mero de la columna comenzando por 0
		celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Estilo de la Celda
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.id")); //Valor a incluir
		sheet.autoSizeColumn((short)iCol); //ajusta el ancho de la primera columna
		iCol++;
		//NUM.SOLICITUD //1
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.numeroSolicitud"));
		sheet.autoSizeColumn((short)iCol); //ajusta el ancho de la segunda columna
		iCol++;
		if (!formulario.getAccion().equals("ExportarAuxiliares")) {
		//TIPO SOLICITUD //2
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoSolicitud"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		}
		//ESPECIALIDAD //3
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.especialidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CONVOCATORIA //4
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.convocatoria"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CUERPO ESCALA //4-2
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.cuerpoEscala"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NIF //5
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nif"));
		iCol++;
		//NOMBRE //6
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nombre"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//PRIMER APELLIDO /7
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.primerApellido"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//SEGUNDO APELLIDO /8
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.segundoApellido"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//FECHA NACIMIENTO /9
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fechaNacimiento"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//SEXO /10
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.sexo"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NACIONALIDAD /13
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nacionalidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TELEFONO /14
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.telefono"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CORREO ELECTRÓNICO /15
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.correoElectronico"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CALLE /16
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.calle"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CÓDIGO POSTAL /17
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.codigoPostal"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//MUNICIPIO DOMICILIO /18
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.municipioDomicilio"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//PROVINCIA DOMICILIO //19
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.provinciaDomicilio"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NACION DOMICILIO //20
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nacionDomicilio"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//PROVINCIA DE EXAMEN /21
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.provinciaExamen"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TIPO DISCAPACIDAD /22
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoDiscapacidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//% DISCAPACIDAD /23
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.porcentajeDiscapacidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//ADAPTACION /24
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.adaptacionDiscapacidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//RESERVA PLAZA DISCAPACIDAD /25
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.reservaPlazaDiscapacidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//OBSERVACIONES /26
		if (!formulario.getAccion().equals("ExportarAuxiliares")) {
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.observaciones"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		}
		//TITULO /27
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.titulo"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//OTROS TITULOS /28
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.otrosTitulos"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;

		//Campos segun modelo
		String secretariosJudiciales = RESOURCE_MESSAGE.getString("codigos.secretarios.judiciales");
		List<String> listaSecretariosJudiciales = Arrays.asList(secretariosJudiciales.split("\\s*,\\s*"));
		
		CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
		camposPropiosQuery.setModelo(modeloQuery);
		camposPropiosQuery.addOrder(CamposPropiosQuery.IDCAMPO,OrderType.ASC);
		ArrayList<CamposPropiosBean> camposPropiosBean=campoPropiosManager.buscarCamposPropiosbyModelo(camposPropiosQuery);
		if(camposPropiosBean.size()>0){
			for(int i=0; i<camposPropiosBean.size();i++){
				//añadimos las columnas a la excel
				if(!idModeloActual.equals("2")) {
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue((camposPropiosBean.get(i).getCampo()).toUpperCase());
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				} else if(listaSecretariosJudiciales.contains(solicitudBeanAux.getCodigoCuerpoEscala())) {
					if (camposPropiosBean.get(i).getId() != Long.valueOf(properties.getProperty("codigo.4")) 
							&& camposPropiosBean.get(i).getId() != Long.valueOf(properties.getProperty("codigo.5"))) {
						celda = fila.createCell(iCol);
						celda.setCellType(HSSFCell.CELL_TYPE_STRING);
						celda.setCellStyle(estiloCelda);
						celda.setCellValue((camposPropiosBean.get(i).getCampo()).toUpperCase());
						sheet.autoSizeColumn((short)iCol);
						iCol++;
					}
				} else if (camposPropiosBean.get(i).getId() != Long.valueOf(properties.getProperty("codigo.7")) 
						&& camposPropiosBean.get(i).getId() != Long.valueOf(properties.getProperty("codigo.8"))) {
						celda = fila.createCell(iCol);
						celda.setCellType(HSSFCell.CELL_TYPE_STRING);
						celda.setCellStyle(estiloCelda);
						celda.setCellValue((camposPropiosBean.get(i).getCampo()).toUpperCase());
						sheet.autoSizeColumn((short)iCol);
						iCol++;
				}
			}
		}
		
		//IMPORTE /32
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.importe"));	
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//EJERCICIO /33
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.ejercicio"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CENTRO GESTOR /34
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.centroGestor"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TIPO PAGO /35
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoPago"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		if (!formulario.getAccion().equals("ExportarAuxiliares")) {
			//FECHA PAGO /36
			celda = fila.createCell(iCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellStyle(estiloCelda);
			celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fechaPago"));
			sheet.autoSizeColumn((short)iCol);
			iCol++;
			//NRC /37
			celda = fila.createCell(iCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellStyle(estiloCelda);
			celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nrc"));
			sheet.autoSizeColumn((short)iCol);
			iCol++;
			//FECHA REGISTRO /38
			celda = fila.createCell(iCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellStyle(estiloCelda);
			celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fechaRegistro"));
			sheet.autoSizeColumn((short)iCol);
			iCol++;
			//NUMERO REGISTRO /39
			celda = fila.createCell(iCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellStyle(estiloCelda);
			celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.numeroRegistro"));
			sheet.autoSizeColumn((short)iCol);
			iCol++;
		}
		//CONSENTIMIENTO /40
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.consentimiento"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		if (!formulario.getAccion().equals("ExportarAuxiliares")) {
			//EDAD VERIFICADA /41
			celda = fila.createCell(iCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellStyle(estiloCelda);
			celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.edadVerif"));
			sheet.autoSizeColumn((short)iCol);
			iCol++;
		}
		//FECHA NAC. VERIFICADA/42
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fechaNacimVerif"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TITULO VERIFICADO /43
//		celda = fila.createCell(iCol);
//		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
//		celda.setCellStyle(estiloCelda);
//		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tituloVerif"));
//		sheet.autoSizeColumn((short)iCol);
//		iCol++;
		//DESEMPLEO VERIFICADO/44
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.desempleoVerif"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//FNUMEROSA VERIFICADO/45
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fnumerosaVerif"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//DISCAPACIDAD VERIFICADO/46
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.discapacidadVerif"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//Victimas VERIFICADO/47
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.victimasVerif"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//FECHA SOLICITUD /47
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fechaSolicitud"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//SOLICITA EXENCION
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.solicitaExencion"));	
		sheet.autoSizeColumn((short)iCol);
        iCol++;
        if (!formulario.getAccion().equals("ExportarPresenciales")) {
			//ADMITIDO
			celda = fila.createCell(iCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellStyle(estiloCelda);
			celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.admitido"));	
			sheet.autoSizeColumn((short)iCol);
			iCol++;
			//ESTADO PID
			celda = fila.createCell(iCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellStyle(estiloCelda);
			celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.estadoPID"));	
			sheet.autoSizeColumn((short)iCol);		
			iCol++;
        }
		//TIPO EXENCION
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoExencion"));	
		sheet.autoSizeColumn((short)iCol);
        iCol++;
		//CCAA ASOCIADA
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.ccaaFNumerosa"));	
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NºTITULO FN
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tituloFNumerosa"));	
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CONSENTIMIENTO AEAT
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.consentimientoAEAT"));	
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//MOTIVO OPOSICION
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.motivoOposicion"));	
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		
		Iterator<SolicitudBean> it = aSolicitudesDatosCompletos.iterator();
		//Recogemos cada solicitud completa para insertar cada fila de la excel 

		while (it.hasNext())
		{
			//Creamos nueva Fila
			iFila++;
			fila = hoja1.createRow((short)iFila);
			int numCol = 0;

			SolicitudBean solicitudBean = (SolicitudBean) it.next();
			
			//ID
			celda = fila.createCell(numCol);	//Número de la columna comenzando por 0
			celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Tipo de la celda
			if(solicitudBean.getId() != null)
				celda.setCellValue(solicitudBean.getId()); //Valor a incluir
			numCol++;
			//NUM.SOLICITUD
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNumeroSolicitud() != null)
				celda.setCellValue(solicitudBean.getNumeroSolicitud());
			numCol++;
			if (!formulario.getAccion().equals("ExportarAuxiliares")) {
				//TIPO SOLICITUD
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(solicitudBean.getTipoDescripcion() != null)
					celda.setCellValue(solicitudBean.getTipoDescripcion());
				numCol++;
			}
			//ESPECIALIDAD
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionEspecialidad() != null)
				celda.setCellValue(solicitudBean.getDescripcionEspecialidad());
			numCol++;
			//CONVOCATORIA
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getIdConvocatoria() != null)
				celda.setCellValue(solicitudBean.getIdConvocatoria());
			numCol++;
			//CUERPO ESCALA
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getIdConvocatoria() != null)
				celda.setCellValue(solicitudBean.getDesCuerpoEscala());
			numCol++;
			//NIF
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNif() != null)
				celda.setCellValue(solicitudBean.getNif());
			numCol++;
			//NOMBRE
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNombre() != null)
				celda.setCellValue(solicitudBean.getNombre());
			numCol++;	
			//PRIMER APELLIDO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getPrimerApellido()!= null)
				celda.setCellValue(solicitudBean.getPrimerApellido());
			numCol++;
			//SEGUNDO APELLIDO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getSegundoApellido() != null)
				celda.setCellValue(solicitudBean.getSegundoApellido());
			numCol++;
			//FECHA NACIMIENTO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);

			if(solicitudBean.getFechaNacimiento() != null)
			{
				//Fecha de Nacimiento Date a String
				SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				String fechaNacimiento= "";
				fechaNacimiento = formatoFecha.format(solicitudBean.getFechaNacimiento());
				celda.setCellValue(fechaNacimiento);
			}
			numCol++;
			//SEXO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getSexo() != null)
				celda.setCellValue(solicitudBean.getSexo().toString());
			numCol++;
			//NACIONALIDAD
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNacionalidad() != null)
				celda.setCellValue(solicitudBean.getNacionalidad());
			numCol++;
			//TELEFONO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getTelefono() != null)
				celda.setCellValue(solicitudBean.getTelefono());
			numCol++;
			//EMAIL
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getEmail() != null)
				celda.setCellValue(solicitudBean.getEmail()); 
			numCol++;
			//CALLE
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getCalleDomicilio() != null)
				celda.setCellValue(solicitudBean.getCalleDomicilio());
			numCol++;
			//CÓDIGO POSTAL
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getCodigoPostalDomicilio() != null)
				celda.setCellValue(solicitudBean.getCodigoPostalDomicilio());
			numCol++;
			//MUNICIPIO DOMICILIO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getMunicipioDomicilio() != null)
				celda.setCellValue(solicitudBean.getMunicipioDomicilio());
			numCol++;
			//PROVINCIA DOMICILIO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionIdProvinciaDomicilio() != null)
				celda.setCellValue(solicitudBean.getDescripcionIdProvinciaDomicilio());
			numCol++;
			//NACION DOMICILIO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNacionPaisDomicilio() != null) 
				celda.setCellValue(solicitudBean.getNacionPaisDomicilio()); 
			numCol++;
			//PROVINCIA DE EXAMEN
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionIdProvinciaExamen() != null)
				celda.setCellValue(solicitudBean.getDescripcionIdProvinciaExamen());
			numCol++;
			//TIPO DISCAPACIDAD
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionTipoDiscapacidad()!= null)
				celda.setCellValue(solicitudBean.getDescripcionTipoDiscapacidad());
			numCol++;
			//% DISCAPACIDAD
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getPorcentajeDiscapacidad() != null)
				celda.setCellValue(solicitudBean.getPorcentajeDiscapacidad());
			numCol++;
			//ADAPTACION
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDetalleDiscapacidad() != null)
				celda.setCellValue(solicitudBean.getDetalleDiscapacidad());
			numCol++;
			//RESERVA PLAZA
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getReservaDiscapacidad() != null)
				celda.setCellValue(solicitudBean.getReservaDiscapacidad().toString());
			numCol++;
			if (!formulario.getAccion().equals("ExportarAuxiliares")) {
				//OBSERVACIONES / COMENTARIOS
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(solicitudBean.getComentarios() != null) 
					celda.setCellValue(solicitudBean.getComentarios());
				numCol++;
			}
			//TITULO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionTituloOficial() != null)
				celda.setCellValue(solicitudBean.getDescripcionTituloOficial());
			numCol++;
			//OTROS TITULOS
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getOtrosTitulos() != null)
				celda.setCellValue(solicitudBean.getOtrosTitulos());
			numCol++;
//			int tam=camposPropiosBean.size();
//			SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(solicitudBean.getId());
			boolean completo = false;

			//En caso de que NO sean solicitudes de la tabla SOLICITUD COMUN AUXILIAR
			if (!formulario.getAccion().equals("ExportarAuxiliares")) { 
				
			SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();
			solicitudPropiosQuery.setSolicitudComun(solicitudComunQuery);
		
			ArrayList<SolicitudPropiosBean> solicitudPropiosBean = solicitudPropiosManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);
			
			if(solicitudPropiosBean.size()>0){
				int j= 0;
				for(int i=0; i<camposPropiosBean.size();i++){
					//añadimos las columnas a la excel
					celda = fila.createCell(numCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					if(j<solicitudPropiosBean.size() && solicitudPropiosBean.get(j).getCamposPropiosBean().getCampo().equals(camposPropiosBean.get(i).getCampo())){
						celda.setCellValue(solicitudPropiosBean.get(j).getValor());
						j++;
					}else{
						celda.setCellValue("");
					}
					sheet.autoSizeColumn((short)numCol);
					numCol++;
				}					
					
					completo = true;
				}
			}else{
				
				SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
				SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery = new SolicitudPropiosAuxiliarQuery();

				solicitudComunAuxiliarQuery.setIdSolicitudAuxiliar(solicitudBean.getId());
				solicitudPropiosAuxiliarQuery.setSolicitudComunAuxiliar(solicitudComunAuxiliarQuery);
				
				ArrayList<SolicitudPropiosAuxiliarBean> solicitudPropiosAuxiliarBean = solicitudPropioAuxiliarManager.obtenerCamposPropiosAuxByIdSolicitud(solicitudPropiosAuxiliarQuery);
				
				if(solicitudPropiosAuxiliarBean.size()>0){
					int j= 0;
					for(int i=0; i<camposPropiosBean.size();i++){
						//añadimos las columnas a la excel
						celda = fila.createCell(numCol);
						celda.setCellType(HSSFCell.CELL_TYPE_STRING);
						if(j<solicitudPropiosAuxiliarBean.size() && solicitudPropiosAuxiliarBean.get(j).getCamposPropiosBean().getCampo().equals(camposPropiosBean.get(i).getCampo())){
							celda.setCellValue(solicitudPropiosAuxiliarBean.get(j).getValor());
							j++;
						}else{
							celda.setCellValue("");
						}
						sheet.autoSizeColumn((short)numCol);
						numCol++;
					}		
					completo = true;
				}
				
			}
			
			// Si no se han compeltado los campos porque no hay datos
			if (!completo){
				if(camposPropiosBean.size()>0){
					for(int i=0; i<camposPropiosBean.size();i++){
						//añadimos las columnas a la excel
						celda = fila.createCell(numCol);
						celda.setCellType(HSSFCell.CELL_TYPE_STRING);
						celda.setCellValue("");
						sheet.autoSizeColumn((short)numCol);
						numCol++;
					}
				}
			}
			
			PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
			pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
			try {
				solicitudBean = pagoSolicitudManager.completaDatosPagoSolicitud(pagoSolicitudQuery, solicitudBean);
			} catch (Exception e) {
				logger.error("Generar excel",e);
			}
			

			//IMPORTE
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			logger.info("Id: "+solicitudBean.getId()+" -> Importe: '"+solicitudBean.getImporte()+"'");
			if(solicitudBean.getImporte() != null && solicitudBean.getImporte()>0){
				celda.setCellValue(solicitudBean.getImporte());
				celda.setCellStyle(cellStyle);
			}
			numCol++;
			//EJERCICIO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			if(solicitudBean.getEjercicio() != null)	
				celda.setCellValue(solicitudBean.getEjercicio());
			numCol++;
			//CENTRO GESTOR
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getCentroGestor() != null)
				celda.setCellValue(solicitudBean.getCentroGestor());
			numCol++;
			//TIPO PAGO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionTipoPago() != null)
			{
				if(solicitudBean.getDescripcionTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_CUENTA_CODIGO)))
				{
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoPago.cuentaMay"));
				}
				else if(solicitudBean.getDescripcionTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_TARJETA_CODIGO)))
				{
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoPago.tarjetaMay"));
				}
				// Solicitud Telemática + E = EXENTO
				else if(solicitudBean.getDescripcionTipoPago().equals("E") && solicitudBean.getTipoDescripcion().equals(Constantes.TIPO_SOLICITUD_TELEMATICA_DESC))
				{
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoPago.exentoMay"));
				}
				else if(solicitudBean.getDescripcionTipoPago().equals("N"))
				{
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoPago.ninguno"));
				}
				// Solicitud Presencial + E = EFECTIVO
				else if(solicitudBean.getDescripcionTipoPago().equals("E") && !solicitudBean.getTipoDescripcion().equals(Constantes.TIPO_SOLICITUD_TELEMATICA_DESC))
				{
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoPago.efectivo"));
				}	
			}else{
				// Si no figura Descripción del pago la Solicitud presencial está exenta
				celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoPago.exentoMay"));
			}
			numCol++;
			if (!formulario.getAccion().equals("ExportarAuxiliares")) {
				//FECHA PAGO
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(solicitudBean.getFechaPago() != null)
					celda.setCellValue(solicitudBean.getFechaPago());
				numCol++;
				//NRC
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(solicitudBean.getNrc() != null)
					celda.setCellValue(solicitudBean.getNrc());
				numCol++;
				//FECHA REGISTRO
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(solicitudBean.getFechaRegistro() != null)
					celda.setCellValue(solicitudBean.getFechaRegistro()); 
				numCol++;
				//NUMERO REGISTRO
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(solicitudBean.getNumeroRegistro() != null)
					celda.setCellValue(solicitudBean.getNumeroRegistro()); 
				numCol++;
			}
			//CONSENTIMIENTO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getIdConsentimiento() != null) {
				celda.setCellValue((solicitudBean.getIdConsentimiento()) ? "SI" : "NO");
			}
			numCol++;
			if (!formulario.getAccion().equals("ExportarAuxiliares")) {
				//EDAD VERIFICADA
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				if(solicitudBean.getEdadVerificada() != null) {
					celda.setCellValue(obtenerLiteralVerificacion(solicitudBean.getEdadVerificada().toString()));
				}		
				numCol++;
			}
			//FECHA NACIMIENTO VERIFICADA
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getFechaNacimientoVerificada() != null) {
				String nacimientoVerif = obtenerLiteralVerificacion(solicitudBean.getFechaNacimientoVerificada().toString());
				if (nacimientoVerif.equals(RESOURCE_BUNDLE.getString("field.solicitudRegistrada.verificada.No")) && solicitudBean.getFechaNacimientoSvdi() != null) {
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
					nacimientoVerif += " - "+formatoDelTexto.format(solicitudBean.getFechaNacimientoSvdi());		
				}
				celda.setCellValue(nacimientoVerif);
			}
			numCol++;
			//TITULO VERIFICADO
//			celda = fila.createCell(numCol);
//			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
//			if(solicitudBean.getTituloVerificado() != null)
//				celda.setCellValue(solicitudBean.getTituloVerificado().toString());
//			numCol++;
			//DESEMPLEO VERIFICADO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDesempleoVerificado() != null && solicitudBean.getIdConsentimiento() && solicitudBean.isEsDesempleo()
					&& ((sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR))) {
				celda.setCellValue(obtenerLiteralVerificacion(solicitudBean.getDesempleoVerificado().toString()));
			} else {
				celda.setCellValue(RESOURCE_BUNDLE.getString("field.solicitudRegistrada.motivoReduccion.NoAplica"));
			}
			numCol++;
			//FAMILIA NUMEROSA VERIFICADO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getFnumerosaVerificado() != null && solicitudBean.getIdConsentimiento() && solicitudBean.isEsFNumerosa()
					&& ((sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR))) {
				celda.setCellValue(obtenerLiteralVerificacion(solicitudBean.getFnumerosaVerificado().toString()));
			} else {
				celda.setCellValue(RESOURCE_BUNDLE.getString("field.solicitudRegistrada.motivoReduccion.NoAplica"));
			}
			numCol++;
			//DISCAPACIDAD VERIFICADO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDiscapacidadVerificado() != null && solicitudBean.getIdConsentimiento() && solicitudBean.isEsDiscapacidad()
					&& ((sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR))) {
				celda.setCellValue(obtenerLiteralVerificacion(solicitudBean.getDiscapacidadVerificado().toString()));
			} else {
				celda.setCellValue(RESOURCE_BUNDLE.getString("field.solicitudRegistrada.motivoReduccion.NoAplica"));
			}
			numCol++;
			//VICTIMAS VERIFICADO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getVictimasVerificado() != null && solicitudBean.getIdConsentimiento() && solicitudBean.isEsVictimaTerrorismo()
					&& ((sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR))) {
				celda.setCellValue(obtenerLiteralVerificacion(solicitudBean.getVictimasVerificado().toString()));
			} else {
				celda.setCellValue(RESOURCE_BUNDLE.getString("field.solicitudRegistrada.motivoReduccion.NoAplica"));
			}
			numCol++;
			//FECHA SOLICITUD
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getFechaSolicitud() != null)
			{
				celda.setCellValue(solicitudBean.getFechaSolicitud());
			}
			numCol++;
			// SOLICITA EXENCION
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if (!formulario.getAccion().equals("ExportarAuxiliares")) {
				celda.setCellValue(solicitudBean.getSolExencion() ? "SÍ" : "NO");
			}else {
				//Para solicitudes auxiliares hay que comprobar si el motivo de la reduccion es null
				if (solicitudBean.getMotivoReduccion() == null) {
					celda.setCellValue("NO");
				}else {
					celda.setCellValue("SÍ");
				}
			}
			numCol++;
			if (!formulario.getAccion().equals("ExportarPresenciales")) {	
				// ADMITIDO
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				celda.setCellValue(solicitudBean.getAdmitido());
				numCol++;
				// ESTADO COMPROBACIÓN PID
				celda = fila.createCell(numCol);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING);
				celda.setCellValue(solicitudBean.getEstadoPID());
				numCol++;
			}
			// TIPO EXENCION
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(STRING_EXPORTARAUXILIARES.equals(formulario.getAccion())) {
				celda.setCellValue(!StringUtils.isEmpty(solicitudBean.getMotivoExencionDesc()) ? solicitudBean.getMotivoExencionDesc() : "");
			} else {
				celda.setCellValue(!StringUtils.isEmpty(solicitudBean.getMotivoExencionDesc()) && solicitudBean.getSolExencion() ? solicitudBean.getMotivoExencionDesc() : "");
			}
			numCol++;
			// CCAA ASOCIADA
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellValue(!StringUtils.isEmpty(solicitudBean.getComunidadDesc()) ? solicitudBean.getComunidadDesc() : "");
			numCol++;
			// NºTITULO FN
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			celda.setCellValue(!StringUtils.isEmpty(solicitudBean.getTituloFN()) ? solicitudBean.getTituloFN() : "");
			numCol++;
			// CONSENTIMIENTO AEAT
			//CONSENTIMIENTO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getIdConsentimientoAeat() != null)
				celda.setCellValue((solicitudBean.getIdConsentimientoAeat()) ? "SI" : "NO");
			else
				celda.setCellValue("SI");
			numCol++;
			// MOTIVO OPOSICION CONSENTIMIENTO
			celda = fila.createCell(numCol);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getMotivoOposicion() != null)
				celda.setCellValue(solicitudBean.getMotivoOposicion()); //getMotivoOposicion() anadir a las vistas
			numCol++;
//			celda = fila.createCell(numCol);
//			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
//			celda.setCellValue(!StringUtils.isEmpty(solicitudBean.getMotivoOposicion()) ? solicitudBean.getMotivoOposicion() : "");
//			numCol++;
		}

		try
		{
			// Volcamos la informacion a un archivo.
			String sNombreFichero= "";
			if(formulario.getAccion()!= null && formulario.getAccion().equals(STRING_EXPORTARCONINCIDENCIAS))//Para ponerle al fichero su nombre correcto
			{
				sNombreFichero = properties.getProperty("conf.nombreFicheroExcelSolicitudesConIncidencias");
			}else if(formulario.getAccion()!= null && formulario.getAccion().equals(STRING_EXPORTARPRESENCIALES))
			{
				 sNombreFichero = properties.getProperty("conf.nombreFicheroExcelSolicitudesPresenciales");
			}	
			else if(formulario.getAccion()!= null && formulario.getAccion().equals(STRING_EXPORTARAUXILIARES)){
				sNombreFichero = "Modelos790Cumplimentados";
			}else		
			{
				//Viene de Solicitudes Registradas
				 sNombreFichero = properties.getProperty("conf.nombreFicheroExcelSolicitudes");
			}

			String sExtension = properties.getProperty("conf.extensionFicheroExcelSolicitudes");

			javax.servlet.ServletOutputStream stream;
			stream = this.getResponse().getOutputStream();

			String contentDisposition = "attachment; filename=\"" + sNombreFichero + sExtension + "\"";

			HttpServletResponse resp = this.getResponse();

			resp.setContentType("application/vnd.ms-excel");

			resp.setHeader("Content-Disposition", contentDisposition);

			objLibro.write(stream);

			stream.flush();
			stream.close();

		}catch (FileNotFoundException ex)
		{
			logger.error("Error ExportarExcelSolicitudesSpring - no se ha encontrado el fichero :",ex);
			
		}catch (IOException ex)
		{
			logger.error("Error ExportarExcelSolicitudesSpring - no se ha encontrado el fichero :",ex);
		}
	}



	/**
	 * Crear query solicitud registrada.
	 *
	 * @param formulario el formulario
	 * @param campoOrden el campo orden
	 * @return el sol comun registradas view query
	 */
	private SolComunRegistradasViewQuery crearQuerySolicitudRegistrada (BuscarSolicitudesForm formulario, String campoOrden)	{

		SolComunRegistradasViewQuery solicitudQuery = new SolComunRegistradasViewQuery();

		String sNifFormulario = formulario.getNif();
		String sNumSolicitud = formulario.getNumSolicitud();
		Integer iIdMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		String sIdCuerpoEscala = formulario.getCuerpoEscala();
		String sIdEspecialidad = formulario.getIdEspecialidad();
		Integer iIdTipo = formulario.getIdTipo();
		Integer idTipoAcceso=formulario.getIdTipoAcceso();
		Integer idModelo = formulario.getIdModelo();
		String campo = campoOrden;
		String direccion = formulario.getDireccion();
		String exencion = formulario.getSolExencion();

		//Comprobar los filtros para realizar la busqueda

		//Nif
		if (sNifFormulario != null && !sNifFormulario.equals("") ){
			solicitudQuery.setNif(sNifFormulario);
		}
		//Número de solicitud
		if (sNumSolicitud != null && !sNumSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(sNumSolicitud);
		}
		//Ministerio
		if (iIdMinisterio != null && iIdMinisterio.intValue() != 0){
			solicitudQuery.setIdMinisterio(iIdMinisterio.shortValue());
		}

		//Centro Gestor
		if (sIdCentroGestor != null && !sIdCentroGestor.equals(""))
		{
			solicitudQuery.setIdCentroGestor(Integer.valueOf(sIdCentroGestor));
		}
		//Cuerpo y Escala
		if (sIdCuerpoEscala != null && !sIdCuerpoEscala.equals(""))
		{
			solicitudQuery.setIdCuerpoEscala(Integer.valueOf(sIdCuerpoEscala));
		}
		//Especialidad
		if (sIdEspecialidad != null && !sIdEspecialidad.equals(""))
		{
			solicitudQuery.setIdEspecialidad(Short.parseShort(sIdEspecialidad));
		}
		//Tipo Solicitud HASTA AQUI!!
		if(iIdTipo != null && iIdTipo.intValue() != 0)
		{
			solicitudQuery.setIdTipoSolicitud(iIdTipo);

		}
		//Fecha Desde
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaRegistroMin(dFechaDesde);

			} catch (java.text.ParseException e) {
				logger.error("Error ExportarExcelSolicitudesSpring - parsear fechaDesde :"+ sFechaDesde ,e);
			}

		}
		//Fecha Hasta
		if (sFechaHasta != null && !sFechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaRegistroMax(dFechaHasta);

			} catch (java.text.ParseException e) {
				logger.error("Error ExportarExcelSolicitudesSpring - parsear fechaHasta :"+ sFechaHasta ,e);
			}
		}

		// Tipo acceso
		if(idTipoAcceso != null && idTipoAcceso.intValue() != 0){
			solicitudQuery.setIdFormaAcceso(idTipoAcceso);
		} 
		
		// Modelo solicitud
		if(idModelo != null && idModelo.intValue() != 0){
			solicitudQuery.setIdModelo(idModelo.shortValue());
		} 
		
		// Solicitud exencion
		if(exencion != null && !exencion.equals("")){
			solicitudQuery.setSolExencion(exencion.charAt(0));
		} 
		
		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if (SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA.equals(campo)) {
				solicitudQuery.addOrder(SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA, orden);
			}
			else if (SolComunRegistradasViewQuery.DESCENTROGESTOR.equals(campo)) {
				solicitudQuery.addOrder(SolComunRegistradasViewQuery.DESCENTROGESTOR, orden);
			}
			else {
				solicitudQuery.addOrder(campo, orden);
			}
		}
		return solicitudQuery;
	}

	/**
	 * Crear query solicitudes incidencias view.
	 *
	 * @param formulario el formulario
	 * @param campoOrden el campo orden
	 * @return el sol comun incidencias view query
	 */
	private SolComunIncidenciasViewQuery crearQuerySolicitudesIncidenciasView (BuscarSolicitudesForm formulario, String campoOrden)
	{
		String campo = campoOrden;
		String direccion = formulario.getDireccion();		

		SolComunIncidenciasViewQuery solicitudQuery = new SolComunIncidenciasViewQuery();
		EstadoSolicitudQuery  estadoSolicitudQuery = new EstadoSolicitudQuery();

		String nif = formulario.getNif();
		String numSolicitud = formulario.getNumSolicitud();		
		Integer idMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String fechaDesde = formulario.getFechaDesde();
		String fechaHasta = formulario.getFechaHasta();
		Integer idEstado = formulario.getIdEstado();
		Integer idTipoAcceso = formulario.getIdTipoAcceso();
		Integer idModelo = formulario.getIdModelo();
		Integer idEstadoConvoc = formulario.getIdEstadoConvoc();
		String exencion = formulario.getSolExencion();

		solicitudQuery.setCalculateNumResults(true);

		//Comprobar los filtros para realizar la busqueda
		//Añadimos los valores distintos al estado Registrado menos el eliminado
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO);

		//Nif
		if (nif != null && !nif.equals("") ){
			solicitudQuery.setNifSolicitud(nif);
		}
		//Número de solicitud
		if (numSolicitud != null && !numSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(numSolicitud);
		}
		//Ministerio
		if (idMinisterio != null && idMinisterio.intValue() != 0){

			solicitudQuery.setIdMinisterio(idMinisterio.shortValue());
		}
		// Centro Gestor
		if(sIdCentroGestor != null && !sIdCentroGestor.equals(""))
		{	
			Integer idCentroGestor = Integer.valueOf(sIdCentroGestor);
			solicitudQuery.setIdCentroGestor(idCentroGestor);
		}
		// Cuerpo Escala
		if(formulario.getCuerpoEscala() != null && !formulario.getCuerpoEscala().equals(""))
		{
			Integer idCuerpoEscala = Integer.valueOf(formulario.getCuerpoEscala());
			solicitudQuery.setIdCuerpoEscala(idCuerpoEscala);
		}	
		// Cuerpo Escala
		if(formulario.getIdEspecialidad() != null && !formulario.getIdEspecialidad().equals(""))
		{
			Integer idEspecialidad = Integer.valueOf(formulario.getIdEspecialidad());
			solicitudQuery.setIdEspecialidad(idEspecialidad.shortValue());
		}		

		//Fecha Desde
		if (fechaDesde != null && !fechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaSolicitudMin(dFechaDesde);

			} catch (java.text.ParseException e) {
				logger.error("Error ExportarExcelSolicitudesSpring - crearQuerySolicitudesIncidenciasView - parsear fechaDesde :"+ fechaDesde ,e);
			}

		}
		//Fecha Hasta
		if (fechaHasta != null && !fechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaSolicitudMax(dFechaHasta);

			} catch (java.text.ParseException e) {
				logger.error("Error ExportarExcelSolicitudesSpring - crearQuerySolicitudesIncidenciasView - parsear fechaHasta :"+ fechaHasta,e);
			}		
		}
		// Tipo Acceso
		if(idTipoAcceso != null && idTipoAcceso!=0)
		{
			solicitudQuery.setIdFormaAcceso(idTipoAcceso);
		}	
		//Estado
		if(idEstado!=null && idEstado.intValue() != 0){
			if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_SIN_INTENTO_PAGO)
			{
				// Solicitudes en estado NO PAGADO con 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO.byteValue());
				solicitudQuery.setIntentosPagoSolicitud(new BigDecimal(0));

			}
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_SIN_INTENTO_REGISTRO)
			{
				// Solicitudes en estado NO REGISTRADO con 0 intentos de registro
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO.byteValue());
				solicitudQuery.setIntentosRegistroSolicitud(new BigDecimal(0));
			}
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_NO_PAGADO)
			{
				// Solicitudes en estado NO PAGADO o PROCESO PAGO con más de 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO.byteValue());
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}	
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_NO_REGISTRADO)
			{
				// Solicitudes en estado NO REGISTRADO con más de 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO.byteValue());
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO)
			{
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}	
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO)
			{
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}			
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO)
			{
				solicitudQuery.setIdEstadoSolicitud(idEstado.byteValue());
			}	
		}

		if(idEstadoConvoc!=null && idEstadoConvoc!=0){
			solicitudQuery.setIdEstadoConvoc(idEstadoConvoc.byteValue());
		}

		if(idModelo != null && idModelo.intValue() != 0){
			solicitudQuery.setIdModelo(idModelo.shortValue());
		}
		
		// Solicitud exencion
		if(exencion != null && !exencion.equals("")){
			solicitudQuery.setSolExencion(exencion.charAt(0));
		} 

		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo))
		{
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);

			if(campo.equals("nif"))
			{
				solicitudQuery.addOrder("nifSolicitud",orden);
			}
			else if(campo.equals("nombre"))
			{
				solicitudQuery.addOrder("nombreSolicitud",orden);
			}
			else if(campo.equals("email"))
			{
				solicitudQuery.addOrder("emailSolicitud",orden);
			}
			else if(campo.equals("primerApellido"))
			{
				solicitudQuery.addOrder("primerApellidoSolicitud",orden);
			}
			else if(campo.equals("segundoApellido"))
			{
				solicitudQuery.addOrder("segundoApellidoSolicitud",orden);
			}
			else if(campo.equals("telefono"))
			{
				solicitudQuery.addOrder("telefonoSolicitud",orden);
			}
			else if(campo.equals("ejercicio"))
			{
				solicitudQuery.addOrder("ejercicioConvocatoria",orden);
			}
			else if(campo.equals("convocatoria"))
			{
				solicitudQuery.addOrder("idConvocatoria",orden);
			}
			else if(campo.equals("centroGestor"))
			{
				solicitudQuery.addOrder("siglasCentroGestor",orden);
			}
			else {
				solicitudQuery.addOrder(campo, orden);
			}
		}

		return solicitudQuery;
	}


	/**
	 * Crear query solicitud presencial.
	 *
	 * @param formulario el formulario
	 * @param campoOrden el campo orden
	 * @return el sol comun presenciales view query
	 */
	private SolComunPresencialesViewQuery crearQuerySolicitudPresencial (BuscarSolicitudesForm formulario, String campoOrden)
	{
		SolComunPresencialesViewQuery solicitudQuery = new SolComunPresencialesViewQuery();
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
//		MinisterioQuery ministerioQuery = new MinisterioQuery();
//		CuerpoEscalaQuery  cuerpoEscalaQuery = new CuerpoEscalaQuery();
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();

		String sNifFormulario = formulario.getNif();
		String sNumSolicitud = formulario.getNumSolicitud();
		Integer iIdMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		String sIdCuerpoEscala = formulario.getCuerpoEscala(); 
		String campo = campoOrden; 
		String direccion = formulario.getDireccion();
		Integer idModelo = formulario.getIdModelo();
		String exencion = formulario.getSolExencion();

		/* EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
		estadoSolicitudQuery.setId(Constantes.ESTADO_SOLICITUD_REGISTRADO);
		solicitudQuery.setEstadoSolicitud(estadoSolicitudQuery);
		solicitudQuery.setCalculateNumResults(true); */

		//Nif
		if (sNifFormulario != null && !sNifFormulario.equals("") ){
			solicitudQuery.setNif(sNifFormulario);
		}
		//Número de solicitud
		if (sNumSolicitud != null && !sNumSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(sNumSolicitud);
		}
		//Ministerio
		if (iIdMinisterio != null && iIdMinisterio.intValue() != 0){
                        solicitudQuery.setIdMinisterio(iIdMinisterio.shortValue());
			/* ministerioQuery.setId(iIdMinisterio);
			centroGestorQuery.setMinisterio(ministerioQuery);
			cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
			convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
			solicitudQuery.setConvocatoria(convocatoriaQuery); */
		}

		//Centro Gestor
		if (sIdCentroGestor != null && !sIdCentroGestor.equals(""))
		{
			Integer idCentroGestor = Integer.valueOf(sIdCentroGestor);
			solicitudQuery.setIdCentroGestor(idCentroGestor);
			/* centroGestorQuery.setId(Integer.valueOf(sIdCentroGestor));

			cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
			convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
			solicitudQuery.setConvocatoria(convocatoriaQuery); */
		}
		//Cuerpo y Escala
		if (sIdCuerpoEscala != null && !sIdCuerpoEscala.equals(""))
		{
			Integer idCuerpoEscala = Integer.valueOf(formulario.getCuerpoEscala());
			solicitudQuery.setIdCuerpoEscala(idCuerpoEscala);
			/* cuerpoEscalaQuery.setId(Integer.valueOf(sIdCuerpoEscala));
			convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
			solicitudQuery.setConvocatoria(convocatoriaQuery); */
		}

		//Tipo Solicitud Presencial 'P'
		/* TipoSolicitudQuery  tipoSolicitudQuery = new TipoSolicitudQuery();
		tipoSolicitudQuery.setCodigo(Character.valueOf('P'));
		solicitudQuery.setTipoSolicitud(tipoSolicitudQuery); */

		//Fecha Desde
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaSolicitudMin(dFechaDesde);

			} catch (java.text.ParseException e) {
				logger.error("Error ExportarExcelSolicitudesSpring - crearQuerySolicitudPresencial - parsear fechaDesde :"+ sFechaDesde ,e);
			}

		}
		//Fecha Hasta
		if (sFechaHasta != null && !sFechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaSolicitudMax(dFechaHasta);

			} catch (java.text.ParseException e) {
				logger.error("Error ExportarExcelSolicitudesSpring - crearQuerySolicitudPresencial - parsear fechaHasta :"+ sFechaHasta ,e);
			}
		}
		
		// Solicitud exencion
		if(exencion != null && !exencion.equals("")){
			solicitudQuery.setSolExencion(exencion.charAt(0));
		} 
		
		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
	   	        if (CuerpoEscalaQuery.CENTROGESTOR.equals(campo)) {
			        solicitudQuery.addOrder("siglasCentroGestor",orden);
			}			
                       /* if (ConvocatoriaQuery.EJERCICIO.equals(campo)) {
				convocatoriaQuery.addOrder(ConvocatoriaQuery.EJERCICIO, orden);
				solicitudQuery.setConvocatoria(convocatoriaQuery);
			} 
			else if (CuerpoEscalaQuery.CENTROGESTOR.equals(campo)) {
				centroGestorQuery.addOrder(CentroGestorQuery.DESCRIPCION, orden);
				cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
				convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
				solicitudQuery.setConvocatoria(convocatoriaQuery);
			} */
			else {
				solicitudQuery.addOrder(campo, orden);
			}
		}

		if(idModelo != null && idModelo.intValue() != 0){
			ModeloQuery modeloQuery = new ModeloQuery();
			modeloQuery.setIdModelo(idModelo);
			//solicitudQuery.setModelo(modeloQuery);
			centroGestorQuery.setModelo(modeloQuery);
			convocatoriaQuery.setModelo(modeloQuery);
			solicitudQuery.setIdModelo(idModelo.shortValue());
		}
		return solicitudQuery;
	}

	/**
	 * Crear query solicitud auxiliar.
	 *
	 * @param formulario el formulario
	 * @param campoOrden el campo orden
	 * @return el sol comun auxiliar view query
	 */
	private SolComunAuxiliarViewQuery crearQuerySolicitudAuxiliar (BuscarSolicitudesForm formulario, String campoOrden)
	{
		SolComunAuxiliarViewQuery solicitudQuery = new SolComunAuxiliarViewQuery();

		String sNifFormulario = formulario.getNif();
		String sNumSolicitud = formulario.getNumSolicitud();
		Integer iIdMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		String sIdCuerpoEscala = formulario.getCuerpoEscala(); 
		String campo = campoOrden; 
		String direccion = formulario.getDireccion();
		String exencion = formulario.getSolExencion();
		Integer idModelo = formulario.getIdModelo();

		//Nif
		if (sNifFormulario != null && !sNifFormulario.equals("") ){
			solicitudQuery.setNif(sNifFormulario);
		}
		//Número de solicitud
		if (sNumSolicitud != null && !sNumSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(sNumSolicitud);
		}
		//Ministerio
		if (iIdMinisterio != null && iIdMinisterio.intValue() != 0){
             solicitudQuery.setIdMinisterio(iIdMinisterio.shortValue());
		}

		//Centro Gestor
		if (sIdCentroGestor != null && !sIdCentroGestor.equals(""))
		{
			Integer idCentroGestor = Integer.valueOf(sIdCentroGestor);
			solicitudQuery.setIdCentroGestor(idCentroGestor);
		}
		//Cuerpo y Escala
		if (sIdCuerpoEscala != null && !sIdCuerpoEscala.equals(""))
		{
			Integer idCuerpoEscala = Integer.valueOf(formulario.getCuerpoEscala());
			solicitudQuery.setIdCuerpoEscala(idCuerpoEscala);
		}

		//Fecha Desde
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaSolicitudMin(dFechaDesde);

			} catch (java.text.ParseException e) {
				logger.error("Error ExportarExcelSolicitudesSpring - crearQuerySolicitudAuxiliar - parsear fechaDesde :"+ sFechaDesde ,e);
			}

		}
		//Fecha Hasta
		if (sFechaHasta != null && !sFechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaSolicitudMax(dFechaHasta);

			} catch (java.text.ParseException e) {
				logger.error("Error ExportarExcelSolicitudesSpring - crearQuerySolicitudAuxiliar - parsear fechaHasta :"+ sFechaHasta ,e);
			}
		}
		
		// Solicitud exencion
		if (exencion != null && !exencion.equals("")) {
			if(exencion.charAt(0) == 'S') {
				solicitudQuery.setMotivoReduccionIsNotNull(true);				
			}else {
				solicitudQuery.setMotivoReduccionIsNull(true);
			}
		}
		
		// Modelo solicitud
		if(idModelo != null && idModelo.intValue() != 0){
			solicitudQuery.setIdModelo(idModelo.shortValue());
		} 
		
		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
	   	        if (CuerpoEscalaQuery.CENTROGESTOR.equals(campo)) {
			        solicitudQuery.addOrder("siglasCentroGestor",orden);
			}
			else {
				solicitudQuery.addOrder(campo, orden);
			}
		}
		return solicitudQuery;
	}

	/**
	 * Cargar campos solicitudes incidencias.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCamposSolicitudesIncidencias(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		try{
			codCampo = Integer.parseInt(campo);
			switch(codCampo){
			//Los campos de ordenacion del jsp
			case 1:auxCampo = SolComunIncidenciasViewQuery.NUMEROSOLICITUD;break;
			case 2:auxCampo = SolComunIncidenciasViewQuery.NIFSOLICITUD;break;
			case 3:auxCampo = SolComunIncidenciasViewQuery.NOMBRESOLICITUD;break;
			case 4:auxCampo = SolComunIncidenciasViewQuery.PRIMERAPELLIDOSOLICITUD;break;	//Si es Gestor o Consultor se carga el campo
			case 5:auxCampo = SolComunIncidenciasViewQuery.SEGUNDOAPELLIDOSOLICITUD;break;
			case 6:auxCampo = SolComunIncidenciasViewQuery.EMAILSOLICITUD;break;
			case 7:auxCampo = SolComunIncidenciasViewQuery.TELEFONOSOLICITUD;break;
			case 8:auxCampo = SolComunIncidenciasViewQuery.EJERCICIOCONVOCATORIA;break;
			case 9:auxCampo = SolComunIncidenciasViewQuery.IDCONVOCATORIA;break;
			case 10:auxCampo = SolComunIncidenciasViewQuery.IDCENTROGESTOR;break;
			case 11:auxCampo = SolComunIncidenciasViewQuery.FECHASOLICITUD;break;
			default:break;


			}			
		}catch(Exception e){
			logger.error("Error ExportarExcelSolicitudesSpring - cargarCampos - parsear campo:"+ codCampo ,e);
			
		}

		return auxCampo;
	}

	/**
	 * Cargar campos solicitudes registradas.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCamposSolicitudesRegistradas(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){}
		
		if (campo != null && !"".equals(campo)){
			if(!campo.equals("null")){
				switch(codCampo){
				//Los campos de ordenacion del jsp
					case 1:auxCampo = SolComunRegistradasViewQuery.NUMEROSOLICITUD;break;
					case 2:auxCampo = SolComunRegistradasViewQuery.NIF;break;
					case 3:auxCampo = SolComunRegistradasViewQuery.NOMBRE;break;
					case 4:auxCampo = SolComunRegistradasViewQuery.PRIMERAPELLIDO;break;	
					case 5:auxCampo = SolComunRegistradasViewQuery.SEGUNDOAPELLIDO;break;
					case 6:auxCampo = SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA;break;
					case 7:auxCampo = SolComunRegistradasViewQuery.IDCENTROGESTOR;break;
					case 8:auxCampo = SolComunRegistradasViewQuery.IDCONVOCATORIA;break;
					case 9:auxCampo = SolComunRegistradasViewQuery.DESCTIPOSOLICITUD;break;
					case 10:auxCampo = SolComunRegistradasViewQuery.IDCONSENTIMIENTO;break;
					case 11:auxCampo = SolComunRegistradasViewQuery.EDADVERIFICADA;break;
					case 12:auxCampo = SolComunRegistradasViewQuery.FECHANACIMIENTOVERIFICADA;break;
					case 13:auxCampo = SolComunRegistradasViewQuery.TITULOVERIFICADO;break;
					case 14:auxCampo = SolComunRegistradasViewQuery.DESEMPLEOVERIFICADO;break;
					case 15:auxCampo = SolComunRegistradasViewQuery.IDESPECIALIDAD;break;
					case 16:auxCampo = SolComunRegistradasViewQuery.FNUMEROSAVERIFICADO;break;
					case 17:auxCampo = SolComunRegistradasViewQuery.DISCAPACIDADVERIFICADO;break;
					}
				}
			}
		return auxCampo;
	}
	
	/**
	 * Cargar campos solicitudes presenciales.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCamposSolicitudesPresenciales(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){}
		
		if (campo != null && !"".equals(campo)){
			if(!campo.equals("null")){
				switch(codCampo){
				//Los campos de ordenacion del jsp
					case 1:auxCampo = SolicitudComunQuery.NUMEROSOLICITUD;break;
					case 2:auxCampo = SolicitudComunQuery.NIF;break;
					case 3:auxCampo = SolicitudComunQuery.NOMBRE;break;
					case 4:auxCampo = SolicitudComunQuery.PRIMERAPELLIDO;break;	
					case 5:auxCampo = SolicitudComunQuery.SEGUNDOAPELLIDO;break;
					}
				}
			}
		return auxCampo;
	}
	
	/**
	 * Cargar campos solicitudes auxiliares.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCamposSolicitudesAuxiliares(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){}
		
		if (campo != null && !"".equals(campo)){
			if(!campo.equals("null")){
				switch(codCampo){
				//Los campos de ordenacion del jsp
					case 1:auxCampo = SolicitudComunAuxiliarQuery.NUMEROSOLICITUD;break;
					case 2:auxCampo = SolicitudComunAuxiliarQuery.NIF;break;
					case 3:auxCampo = SolicitudComunAuxiliarQuery.NOMBRE;break;
					case 4:auxCampo = SolicitudComunAuxiliarQuery.PRIMERAPELLIDO;break;	
					case 5:auxCampo = SolicitudComunAuxiliarQuery.SEGUNDOAPELLIDO;break;
					}
				}
			}
		return auxCampo;
	}
	
	/**
	 * Obtiene admitido.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el string
	 */
	public static String obtieneAdmitido(SolicitudBean solicitudBean) {
		String admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
		
		if (!StringUtils.isEmpty(solicitudBean.getFechaNacimientoVerificada())) {
			//Si se ha rechazado la verificación de fecha nacimiento --> Excluido
			if (Constantes.FECHA_NACIMIENTO_NO_VERIFICADA == solicitudBean.getFechaNacimientoVerificada()) {
				admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
			}
			
			//Si se ha aceptado la verificación de fecha de nacimiento
			if (Constantes.FECHA_NACIMIENTO_VERIFICADA == solicitudBean.getFechaNacimientoVerificada()) {
				admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.admitido");
				if (solicitudBean.isEsDesempleo() == true) {
					
					//Si se ha rechazado la verificación de desempleo --> Excluido
					if (Constantes.DESEMPLEO_NO_VERIFICADO == solicitudBean.getDesempleoVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
						
					//Si no se ha comprobado la verificación de desempleo --> Pendiente	
					} else if (Constantes.DESEMPLEO_NO_COMPROBADO == solicitudBean.getDesempleoVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.pendiente");
					}
				} else if (solicitudBean.isEsFNumerosa() == true) {
					
					//Si se ha rechazado la verificación de familia numerosa --> Excluido
					if (Constantes.FNUMEROSA_NO_VERIFICADO == solicitudBean.getFnumerosaVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
						
					//Si no se ha comprobado la verificación de familia numerosa --> Pendiente	
					} else if (Constantes.FNUMEROSA_NO_COMPROBADO == solicitudBean.getFnumerosaVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.pendiente");
					}
				} else if (solicitudBean.isEsDiscapacidad() == true) {
					
					//Si se ha rechazado la verificación de discapacidad --> Excluido
					if (Constantes.DISCAPACIDAD_NO_VERIFICADO == solicitudBean.getDiscapacidadVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
						
					//Si no se ha comprobado la verificación de discapacidad --> Pendiente	
					} else if (Constantes.DISCAPACIDAD_NO_COMPROBADO == solicitudBean.getDiscapacidadVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.pendiente");
					}
				} else if (solicitudBean.isEsVictimaTerrorismo() == true) {
					//Si se ha rechazado la verificación de victimas --> Excluido
					if (Constantes.VICTIMA_NO_VERIFICADO == solicitudBean.getVictimasVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
						
					//Si no se ha comprobado la verificación de victima --> Pendiente	
					} else if (Constantes.VICTIMA_NO_COMPROBADO == solicitudBean.getVictimasVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.pendiente");
					}
				}
			}
			
			//Si no se ha comprobado la fecha de nacimiento
			if (Constantes.FECHA_NACIMIENTO_NO_COMPROBADA == solicitudBean.getFechaNacimientoVerificada()) {
				admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.pendiente");
				if (solicitudBean.isEsDesempleo() == true) {
					
					//Si se ha rechazado la verificación de desempleo --> Excluido
					if (Constantes.DESEMPLEO_NO_VERIFICADO == solicitudBean.getDesempleoVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
					}
				} else if (solicitudBean.isEsFNumerosa() == true) {
					
					//Si se ha rechazado la verificación de familia numerosa --> Excluido
					if (Constantes.FNUMEROSA_NO_VERIFICADO == solicitudBean.getFnumerosaVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
					} 
				} else if (solicitudBean.isEsDiscapacidad() == true) {
					
					//Si se ha rechazado la verificación de discapacidad --> Excluido
					if (Constantes.DISCAPACIDAD_NO_VERIFICADO == solicitudBean.getDiscapacidadVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");	
					}
				} else if (solicitudBean.isEsVictimaTerrorismo() == true) {
					//Si se ha rechazado la verificación de victimas --> Excluido
					if (Constantes.VICTIMA_NO_VERIFICADO == solicitudBean.getVictimasVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");	
					}
				}
			}
			
			//Si no se ha comprobado la fecha de nacimiento
			if (Constantes.FECHA_NACIMIENTO_NO_CONEXION_WS == solicitudBean.getFechaNacimientoVerificada()) {
				admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.pendiente");
				if (solicitudBean.isEsDesempleo() == true) {
					
					//Si se ha rechazado la verificación de desempleo --> Excluido
					if (Constantes.DESEMPLEO_NO_VERIFICADO == solicitudBean.getDesempleoVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
					}
				} else if (solicitudBean.isEsFNumerosa() == true) {
					
					//Si se ha rechazado la verificación de familia numerosa --> Excluido
					if (Constantes.FNUMEROSA_NO_VERIFICADO == solicitudBean.getFnumerosaVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");
					} 
				} else if (solicitudBean.isEsDiscapacidad() == true) {
					
					//Si se ha rechazado la verificación de discapacidad --> Excluido
					if (Constantes.DISCAPACIDAD_NO_VERIFICADO == solicitudBean.getDiscapacidadVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");	
					}
				} else if (solicitudBean.isEsVictimaTerrorismo() == true) {
					
					//Si se ha rechazado la verificación de victimas --> Excluido
					if (Constantes.VICTIMA_NO_VERIFICADO == solicitudBean.getVictimasVerificado()) {
						admitido = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.admitido.excluido");	
					}
				}
			}
			
		}

		return admitido;
	}	
	
	/**
	 * Obtiene estado PID.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el string
	 */
	public static String obtieneEstadoPID(SolicitudBean solicitudBean) {
		String estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
		if (!StringUtils.isEmpty(solicitudBean.getFechaNacimientoVerificada())) {
			//Comprobado
			if (Constantes.FECHA_NACIMIENTO_VERIFICADA == solicitudBean.getFechaNacimientoVerificada()) {
				if (solicitudBean.isEsDesempleo() == true) {
					//Si se ha rechazado la verificación de desempleo --> Cumple
					if (Constantes.DESEMPLEO_VERIFICADO == solicitudBean.getDesempleoVerificado()) {
						return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoCumple");	
					} else {
						//Resto de estados
						return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");	
					}
				} else if (solicitudBean.isEsFNumerosa() == true) {
					//Si se ha rechazado la verificación de FNumerosa --> Cumple
					if (Constantes.FNUMEROSA_VERIFICADO == solicitudBean.getFnumerosaVerificado()) {
						return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoCumple");	
					} else {
						//Resto de estados
						return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					}
				} else if (solicitudBean.isEsDiscapacidad() == true) {
					//Si se ha rechazado la verificación de Discapacidad --> Cumple
					if (Constantes.DISCAPACIDAD_VERIFICADO == solicitudBean.getDiscapacidadVerificado()) {
						return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoCumple");
					} else {
						//Resto de estados
						return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					}
				} else if (solicitudBean.isEsVictimaTerrorismo() == true) {
					//Si se ha rechazado la verificación de victimas --> Cumple
					if (Constantes.VICTIMA_VERIFICADO == solicitudBean.getVictimasVerificado()) {
						return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoCumple");
					} else {
						//Resto de estados
						return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					}
				} else {
					return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoCumple");	
				}
			}
			
			//No valido
			if (Constantes.FECHA_NACIMIENTO_NO_VERIFICADA == solicitudBean.getFechaNacimientoVerificada()) {
				return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
			}
			
			//Pte. No comprobado
			if (Constantes.FECHA_NACIMIENTO_NO_COMPROBADA == solicitudBean.getFechaNacimientoVerificada()) {
				if (solicitudBean.isEsDesempleo() == true) {
					//Para estados no comprobados
					if (Constantes.DESEMPLEO_VERIFICADO == solicitudBean.getDesempleoVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
					} else if (Constantes.DESEMPLEO_NO_VERIFICADO== solicitudBean.getDesempleoVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					} else if (Constantes.DESEMPLEO_NO_CONEXION_WS== solicitudBean.getDesempleoVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
					} else if (Constantes.DESEMPLEO_NO_COMPROBADO== solicitudBean.getDesempleoVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
					}
				} else if (solicitudBean.isEsFNumerosa() == true) {
					//Si se ha rechazado la verificación de FNumerosa --> Cumple
					if (Constantes.FNUMEROSA_VERIFICADO == solicitudBean.getFnumerosaVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
					} else if (Constantes.FNUMEROSA_NO_VERIFICADO== solicitudBean.getFnumerosaVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					} else if (Constantes.FNUMEROSA_NO_CONEXION_WS== solicitudBean.getFnumerosaVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
					} else if (Constantes.FNUMEROSA_NO_COMPROBADO== solicitudBean.getFnumerosaVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
					}
				} else if (solicitudBean.isEsDiscapacidad() == true) {
					//Si se ha rechazado la verificación de Discapacidad --> Cumple
					if (Constantes.FNUMEROSA_VERIFICADO == solicitudBean.getDiscapacidadVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
					} else if (Constantes.FNUMEROSA_NO_VERIFICADO== solicitudBean.getDiscapacidadVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					} else if (Constantes.FNUMEROSA_NO_CONEXION_WS== solicitudBean.getDiscapacidadVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
					} else if (Constantes.FNUMEROSA_NO_COMPROBADO== solicitudBean.getDiscapacidadVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
					}
				} else if (solicitudBean.isEsVictimaTerrorismo() == true) {
					//Si se ha rechazado la verificación de victimas --> Cumple
					if (Constantes.VICTIMA_VERIFICADO == solicitudBean.getVictimasVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
					} else if (Constantes.VICTIMA_NO_VERIFICADO== solicitudBean.getVictimasVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					} else if (Constantes.VICTIMA_NO_CONEXION_WS== solicitudBean.getVictimasVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
					} else if (Constantes.VICTIMA_NO_COMPROBADO== solicitudBean.getVictimasVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
					}
				} else {
					return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.pteComprobar");
				}
			}
			
			//Pte. Error WS
			if (Constantes.FECHA_NACIMIENTO_NO_CONEXION_WS == solicitudBean.getFechaNacimientoVerificada()) {
				if (solicitudBean.isEsDesempleo() == true) {
					if (Constantes.DESEMPLEO_NO_VERIFICADO== solicitudBean.getDesempleoVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					} else {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
					}
				} else if (solicitudBean.isEsFNumerosa() == true) {
					if (Constantes.FNUMEROSA_NO_VERIFICADO== solicitudBean.getFnumerosaVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					} else {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
					}
				} else if (solicitudBean.isEsDiscapacidad() == true) {
					if (Constantes.FNUMEROSA_NO_VERIFICADO== solicitudBean.getDiscapacidadVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					} else {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
					} 
				} else if (solicitudBean.isEsVictimaTerrorismo() == true) {
					if (Constantes.VICTIMA_NO_VERIFICADO== solicitudBean.getVictimasVerificado()) {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.comprobadoNoCumple");
					} else {
						estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
					} 
				} else {
					estado = RESOURCE_BUNDLE.getString("field.solicitudRegistrada.falloComprobar");
				}
			}
		}

		return estado;
	}	
	
	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return convocatoriaManager ConvocatoriasManager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager ConvocatoriasManager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public void setSolicitudesRegistradasManager(
			SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return pagoSolicitudManager PagoSolicitudManager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager PagoSolicitudManager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return  registroSolicitudManager RegistroSolicitudManager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager RegistroSolicitudManager
	 */
	public void setRegistroSolicitudManager(
			final RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
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
	 * Obtiene el solicitud propios manager.
	 *
	 * @return el solicitud propios manager
	 */
	public SolicitudesPropiosManager getSolicitudPropiosManager() {
		return solicitudPropiosManager;
	}

	/**
	 * Establece el solicitud propios manager.
	 *
	 * @param solicitudPropiosManager el nuevo solicitud propios manager
	 */
	public void setSolicitudPropiosManager(
			SolicitudesPropiosManager solicitudPropiosManager) {
		this.solicitudPropiosManager = solicitudPropiosManager;
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
	 * Obtiene el estado solicitud manager.
	 *
	 * @return el estado solicitud manager
	 */
	public EstadoSolicitudManager getEstadoSolicitudManager() {
		return estadoSolicitudManager;
	}

	/**
	 * Establece el estado solicitud manager.
	 *
	 * @param estadoSolicitudManager el nuevo estado solicitud manager
	 */
	public void setEstadoSolicitudManager(
			EstadoSolicitudManager estadoSolicitudManager) {
		this.estadoSolicitudManager = estadoSolicitudManager;
	}

	/**
	 * Obtiene el solicitud ccaa manager.
	 *
	 * @return el solicitud ccaa manager
	 */
	public SolicitudCcaaManager getSolicitudCcaaManager() {
		return solicitudCcaaManager;
	}

	/**
	 * Establece el solicitud ccaa manager.
	 *
	 * @param solicitudCcaaManager el nuevo solicitud ccaa manager
	 */
	public void setSolicitudCcaaManager(SolicitudCcaaManager solicitudCcaaManager) {
		this.solicitudCcaaManager = solicitudCcaaManager;
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
	public void setSolicitudComunAuxiliarManager(SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
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
	public void setMotivoReduccionTasaManager(MotivoReduccionTasaManager motivoReduccionTasaManager) {
		this.motivoReduccionTasaManager = motivoReduccionTasaManager;
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
	public void setSolicitudCcaaAuxiliarManager(SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager) {
		this.solicitudCcaaAuxiliarManager = solicitudCcaaAuxiliarManager;
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
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error BuscarSolicitudesRegistradasSpring - recuperarUsuarioSesion"+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Obtener literal verificacion.
	 *
	 * @param verificacion el verificacion
	 * @return el string
	 */
	private String obtenerLiteralVerificacion(String verificacion) {

		if (verificacion.toString().equals("A")) {
			return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.verificada.Si");
		} else if (verificacion.toString().equals("R")) {
			return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.verificada.No");
		} else if (verificacion.toString().equals("N")) {
			return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.verificada.Pdte");
		} else if (verificacion.toString().equals("E")) {
			return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.verificada.PdteErrorWs");
		}
		
		return RESOURCE_BUNDLE.getString("field.solicitudRegistrada.verificada.Pdte");
	}
}
