package es.map.ipsg.manager;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.LogServicioDAO;
import es.map.ips.model.LogServicio;
import es.map.ips.model.LogServicioQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoServicio;
import es.map.ips.model.TipoServicioQuery;
import es.map.ipsg.bean.ConsultarServiciosExternosBean;
import es.map.ipsg.bean.LogServicioBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudLogErroresBean;
import es.map.ipsg.util.Constantes;

/**
 *  Clase que implementa el LogServicioManager.
 *
 * @author djimenezg
 */
public class LogServicioManagerImpl implements LogServicioManager {

	/** el log servicio DAO. */
	//Variables
	private LogServicioDAO logServicioDAO;
	
	/** el tipo servicio manager. */
	private TipoServicioManager tipoServicioManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogServicioManagerImpl.class);
	
	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/**
	 * Método que se guarda en la tabla LogGenerico el registro insertado.
	 *
	 * @param logServicioBean el log servicio bean
	 * @return idTitulo Integer El ID del Título que se ha guardado
	 */
	public Integer guardarLogServicioBean (LogServicioBean logServicioBean){
		LogServicio logServicio = toLogServicio(logServicioBean);
		Integer idLogServicio = logServicioDAO.insert(logServicio);
		return idLogServicio;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogServicioManager#guardarLogServicio(es.map.ipsg.bean.LogServicioBean)
	 */
	public void guardarLogServicio(LogServicioBean logServicioBean) {
		
		LogServicio logServicio = toLogServicio(logServicioBean);
		logServicioDAO.insert(logServicio);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogServicioManager#generarRegistroLog(es.map.ipsg.bean.LogServicioBean)
	 */
	public void generarRegistroLog(LogServicioBean logServicioBean){	
		LogServicio registroLog = null;
		Date date = new Date();
		registroLog = toLogServicio(logServicioBean);
		registroLog.setFecha(date);
		
		logServicioDAO.insert(registroLog);
	}
	
	/**
	 * To log servicio.
	 *
	 * @param logServicioBean el log servicio bean
	 * @return el log servicio
	 */
	public LogServicio toLogServicio(LogServicioBean logServicioBean){
		LogServicio logServicio = new LogServicio();
		
		logServicio.setId(logServicioBean.getId());
		logServicio.setFecha(logServicioBean.getFecha());
		if(logServicioBean.getIdTipoError() != '\0'){
			logServicio.setTipoError(logServicioBean.getIdTipoError());
		}
		
		logServicio.setCodigoError(logServicioBean.getCodigoError());
		logServicio.setDescripcionError(logServicioBean.getDesTipoError());
		logServicio.setResultado(logServicioBean.getResultado());
		logServicio.setTiempoRespuesta(logServicioBean.getTiempoRespuesta());
		
		String isPrueba = logServicioBean.getPrueba();
		
		if(isPrueba != null && isPrueba.equals("S")){
			logServicio.setPruebaServicio('S');
		}else{
			logServicio.setPruebaServicio('N');
		}	
		
		TipoServicio tipoServicio=tipoServicioManager.obtenerTipoServicio(logServicioBean.getIdTipoServicio());
		logServicio.setTipoServicio(tipoServicio);
		
		return logServicio;
	}
	
	/**
	 * To log servicio bean.
	 *
	 * @param logServicio el log servicio
	 * @param numTotal el num total
	 * @return el log servicio bean
	 */
	public LogServicioBean toLogServicioBean (LogServicio logServicio,Integer numTotal){
		
		try
		{
			LogServicioBean logServicioBean = new LogServicioBean();
			logServicioBean.setId(logServicio.getId());
			logServicioBean.setFecha(logServicio.getFecha());
			logServicioBean.setIdTipoServicio(logServicio.getTipoServicio().getId());
			logServicioBean.setDesTipoServicio(logServicio.getTipoServicio().getDescripcion());
			logServicioBean.setIdTipoError(logServicio.getTipoError());
			if (logServicio.getTipoError().toString().equals( Constantes.LOG_ERROR_LOGICO)){
				logServicioBean.setDesTipoError(RESOURCE_MESSAGE.getString("field.logErrores.logico"));
			}else{
				if (logServicio.getTipoError().toString().equals( Constantes.LOG_ERROR_FISICO)){
					logServicioBean.setDesTipoError(RESOURCE_MESSAGE.getString("field.logErrores.fisico"));
					}
			}
			logServicioBean.setCodigoError(logServicio.getCodigoError());
			logServicioBean.setDescripcionError(logServicio.getDescripcionError());
			// CAMPO NUEVO:  RECUPERAR NUMERO DE SOLICITUD
			LogServicioQuery logQuery = new LogServicioQuery();
			logQuery.setId(logServicio.getId());
			
			SolicitudBean solicitudBean = new SolicitudBean();
			ArrayList<String> numerosSolicitud;
			
			if(logServicioBean.getIdTipoServicio() == 3)
			{
				// PAGO DE TASAS
				PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
				pagoSolicitudQuery.setLogServicio(logQuery);
				ArrayList<PagoSolicitudBean> resultadoPago = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
				ArrayList<SolicitudLogErroresBean> solicitudesAsociadas = new ArrayList<SolicitudLogErroresBean>();
				
				// se obtiene el id de solicitud a apartir del log_servicio
				for(PagoSolicitudBean pagoAux :resultadoPago)
				{	
					Long idSolicitud = pagoAux.getSolicitud().getIdSolicitud();
					if(idSolicitud != null)
					{
						// se obtiene el numero de solicitud a partir del id
						SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						solicitudQuery.setIdSolicitud(idSolicitud);
						solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
					}	
				
					if(solicitudBean != null)
					{	
						SolicitudLogErroresBean solicitudErrorBean = new SolicitudLogErroresBean();
						solicitudErrorBean.setIdEstadoSolicitud(solicitudBean.getIdEstadoSolicitud());
						solicitudErrorBean.setId(solicitudBean.getId());
						solicitudErrorBean.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
						solicitudesAsociadas.add(solicitudErrorBean);
						
					}
					
					logServicioBean.setSolicitudesAsociadas(solicitudesAsociadas);
				}
				
			}
			else if(logServicioBean.getIdTipoServicio() == 4)
			{
				toLogServicioBean2(logQuery,solicitudBean,logServicioBean);
			}
				
			logServicioBean.setNumTotal(numTotal);
			return logServicioBean;
		}
		catch(Exception e)
		{
			logger.error("Error LogServicioManagerImpl - toLogServicioBean.",e);
			return null;
		}
	}
	
	/**
	 * To log servicio bean 2.
	 *
	 * @param logQuery el log query
	 * @param solicitudBean el solicitud bean
	 * @param logServicioBean el log servicio bean
	 */
	public void toLogServicioBean2(LogServicioQuery logQuery, SolicitudBean solicitudBean, LogServicioBean logServicioBean) {
		//REGISTRAR SOLICITUD EN REC
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		registroSolicitudQuery.setLogServicio(logQuery);
		ArrayList<RegistroSolicitudBean> resultadoRegistro = registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);
		ArrayList<SolicitudLogErroresBean> solicitudesAsociadas = new ArrayList<SolicitudLogErroresBean>();
		
		// se obtiene el id de solicitud a apartir del log_servicio
		for(RegistroSolicitudBean registroAux :resultadoRegistro)
		{
			if(registroAux.getSolicitud() != null)
			{	
				Long idSolicitud = registroAux.getSolicitud().getIdSolicitud();
				if(idSolicitud != null)
				{
					// se obtiene el numero de solicitud a partir del id
					SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
					solicitudQuery.setIdSolicitud(idSolicitud);
					solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
				}
			
				if(solicitudBean != null)
				{	
					SolicitudLogErroresBean solicitudErrorBean = new SolicitudLogErroresBean();
					solicitudErrorBean.setIdEstadoSolicitud(solicitudBean.getIdEstadoSolicitud());
					solicitudErrorBean.setId(solicitudBean.getId());
					solicitudErrorBean.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
					solicitudesAsociadas.add(solicitudErrorBean);
					
				}
				logServicioBean.setSolicitudesAsociadas(solicitudesAsociadas);
			}	
		}	
	
	}
	
	/**
	 * To log servicio bean.
	 *
	 * @param logServicio el log servicio
	 * @return el log servicio bean
	 */
	public LogServicioBean toLogServicioBean (LogServicio logServicio){
		LogServicioBean logServicioBean = new LogServicioBean();
		logServicioBean.setId(logServicio.getId());
		logServicioBean.setFecha(logServicio.getFecha());
		logServicioBean.setIdTipoServicio(logServicio.getTipoServicio().getId());
		
		if(logServicio.getTipoError() != null)
			logServicioBean.setIdTipoError(logServicio.getTipoError());
		
		logServicioBean.setCodigoError(logServicio.getCodigoError());
		logServicioBean.setResultado(logServicio.getResultado());
		logServicioBean.setTiempoRespuesta(logServicio.getTiempoRespuesta());
		logServicioBean.setDesTipoError(logServicio.getDescripcionError());
		
		return logServicioBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogServicioManager#obtenerLogErrores(java.lang.Integer)
	 */
	public LogServicioBean obtenerLogErrores(Integer idLogError){
		
		return toLogServicioBean(logServicioDAO.get(idLogError));
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogServicioManager#buscarLogErroresAll(es.map.ips.model.LogServicioQuery)
	 */
	public List<LogServicioBean> buscarLogErroresAll(LogServicioQuery logServicioQuery) {
		logServicioQuery.setResultado(Constantes.SERVICIO_ESTADO_ERROR);
		List<LogServicioBean> list = new ArrayList<LogServicioBean>();
		SearchResult<LogServicio> LogErrores = logServicioDAO.search(logServicioQuery);
		int numTotal = 0;

		if(LogErrores != null){
			numTotal = LogErrores.getNumResults();
		}
		for(LogServicio u:LogErrores.getResults()){
			LogServicioBean logServicioBean = new LogServicioBean();
			try{
				logServicioBean = toLogServicioBean(u,numTotal);
			}catch(Exception e)
			{
				logger.error("Error LogServicioManagerImpl - buscarLogErroresAll.",e);	
			}
			list.add(logServicioBean);
		}
		return list;
	} 
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogServicioManager#consultarEstadisticasLogServicio(es.map.ipsg.bean.ConsultarServiciosExternosBean)
	 */
	public List<LogServicioBean> consultarEstadisticasLogServicio(ConsultarServiciosExternosBean consultaBean){
		List<LogServicioBean> list = new ArrayList<LogServicioBean>();
		LogServicioQuery logServicioQuery = toLogServicioQuery(consultaBean);		
		
		SearchResult<LogServicio> result = logServicioDAO.search(logServicioQuery);
		
		int numTotal = 0;

		if(result != null){
			numTotal = result.getNumResults();
		}
		
		for(LogServicio u: result.getResults()){
			LogServicioBean logServicioBean = new LogServicioBean();
			try{
				logServicioBean = toLogServicioBean(u);
				logServicioBean.setNumTotal(numTotal);
			}catch(Exception e){
				logger.error("Error LogServicioManagerImpl - consultarEstadisticasLogServicio.",e);
			}
			list.add(logServicioBean);
		}
		
		return list;
	}
	
	/**
	 * To log servicio query.
	 *
	 * @param consultaBean el consulta bean
	 * @return el log servicio query
	 */
	public LogServicioQuery toLogServicioQuery(ConsultarServiciosExternosBean consultaBean){
		LogServicioQuery logServicioQuery = new LogServicioQuery();
		TipoServicioQuery tipoServicioQuery = new TipoServicioQuery();		
		
		if(consultaBean.getIdServicio() != null && !consultaBean.getIdServicio().equals(0)){
			tipoServicioQuery.setId(consultaBean.getIdServicio());
		}
		
		logServicioQuery.setFechaMin(consultaBean.getFechaDesde());
		logServicioQuery.setFechaMax(consultaBean.getFechaHasta());
		logServicioQuery.setResultado(consultaBean.getResultado());
		logServicioQuery.setPruebaServicio(consultaBean.getPrueba());
		logServicioQuery.setTipoServicio(tipoServicioQuery);
		
		//Calcular el numero de lineas de cada paginacion
		if(consultaBean.getPaginaActual() != null){
			int numRegistros = Integer.valueOf(consultaBean.getNumRegistros());
			int paginaActual = Integer.valueOf(consultaBean.getPaginaActual());
			
			int tamanyoPaginacionReal = numRegistros;
			int tamanyoPaginacion = tamanyoPaginacionReal+1;
			int primerRegistro = (paginaActual*tamanyoPaginacionReal)-tamanyoPaginacionReal;
			
			logServicioQuery.setMaxResults(tamanyoPaginacion);
			logServicioQuery.setFirstResult(primerRegistro);
		}
		
		String direccion = consultaBean.getDireccion();
		String campo = consultaBean.getCampo();
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)) {
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			logServicioQuery.addOrder(campo, orden);	
		}
		
		logServicioQuery.setCalculateNumResults(true);
		
		return logServicioQuery;
	}
	
	/**
	 * Obtiene el log servicio DAO.
	 *
	 * @return the logServicioDAO
	 */
	public LogServicioDAO getLogServicioDAO() {
		return logServicioDAO;
	}
	
	/**
	 * Establece el log servicio DAO.
	 *
	 * @param logServicioDAO the logServicioDAO to set
	 */
	public void setLogServicioDAO(LogServicioDAO logServicioDAO) {
		this.logServicioDAO = logServicioDAO;
	}
	
	/**
	 * Obtiene el tipo servicio manager.
	 *
	 * @return el tipo servicio manager
	 */
	public TipoServicioManager getTipoServicioManager() {
		return tipoServicioManager;
	}

	/**
	 * Establece el tipo servicio manager.
	 *
	 * @param tipoServicioManager el nuevo tipo servicio manager
	 */
	public void setTipoServicioManager(TipoServicioManager tipoServicioManager) {
		this.tipoServicioManager = tipoServicioManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return el pago solicitud manager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager el nuevo pago solicitud manager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return el registro solicitud manager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager el nuevo registro solicitud manager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudesManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	


}	