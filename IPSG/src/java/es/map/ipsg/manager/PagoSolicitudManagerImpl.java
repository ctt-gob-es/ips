package es.map.ipsg.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EntidadFinancieraDAO;
import es.map.ips.dao.PagoSolicitudDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.CorreoElectronico;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.action.solicitud.ExportarExcelSolicitudesSpring;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.util.Constantes;


/**
 *  Clase que implementa el PagoSolicitudManager.
 *
 * @author amartinl
 */
public class PagoSolicitudManagerImpl implements PagoSolicitudManager {

	/** el pago solicitud DAO. */
	//Variables
	private PagoSolicitudDAO pagoSolicitudDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el entidad financiera DAO. */
	private EntidadFinancieraDAO entidadFinancieraDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PagoSolicitudManagerImpl.class);
	
	
	/**
	 * Buscar pago solicitud.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el search result
	 */
	private SearchResult<PagoSolicitud> buscarPagoSolicitud(PagoSolicitudQuery pagoSolicitudQuery) {
			ApplicationException.assertNotNull(pagoSolicitudQuery, "PagoSolicitudQuery no puede ser null");
		
		return pagoSolicitudDAO.search(pagoSolicitudQuery);
	}


	/**
	 * 	Obtiene si Solicita Exención la solicitud y la Fecha de pago
	 * 	Si tiene un OK en Resultado de la tabla PAGO_SOLICITUD y el campo Solicita Reducción está a S, quiere decir que Solicita Exención.
	 *
	 * @param pagoSolicitudQuery PagoSolicitudQuery
	 * @param solicitudBean  SolicitudBean
	 * @return PagoSolicitudBean PagoSolicitudQuery
	 * @throws Exception Exception
	 */
	public SolicitudBean completaDatosPagoSolicitud (PagoSolicitudQuery pagoSolicitudQuery, SolicitudBean solicitudBean) throws Exception 
	{
		//pagoSolicitudQuery.setResultado("OK");
		pagoSolicitudQuery.addOrder("id",OrderType.DESC);

		SearchResult<PagoSolicitud> pagoSolicitud = pagoSolicitudDAO.search(pagoSolicitudQuery); 

		
		if(pagoSolicitud.getResults().size() > 0 && pagoSolicitud.getResults().get(0) != null){
			// Se comprueba si se ha solicitado exención de pago
			PagoSolicitudBean pagoSolicitudBean = toPagoSolicitudBean(pagoSolicitud.getResults().get(0));
			
			char solicitaReduccion = 'A';
			if (pagoSolicitudBean != null && pagoSolicitudBean.getSolicitaReduccion() != null){
				solicitaReduccion = pagoSolicitudBean.getSolicitaReduccion().charValue();
			}
			
			if(solicitaReduccion == 'S'){
				 solicitudBean.setSolExencion(true);
			}
			
			// Si es desempleado
			if(pagoSolicitud.getResults().get(0).getMotivoReduccionTasa()!=null && pagoSolicitud.getResults().get(0).getMotivoReduccionTasa().getId()==2){
				
				solicitudBean.setEsDesempleo(true);
			
			}	
			// Si es familiaNumerosa
			if(pagoSolicitud.getResults().get(0).getMotivoReduccionTasa()!=null && pagoSolicitud.getResults().get(0).getMotivoReduccionTasa().getId()==3){
				
				solicitudBean.setEsFNumerosa(true);
				solicitudBean.setFamNumerosa("E");
			
			}
				// Si es familiaNumerosa
				if(pagoSolicitud.getResults().get(0).getMotivoReduccionTasa()!=null && pagoSolicitud.getResults().get(0).getMotivoReduccionTasa().getId()==5){
					
					solicitudBean.setEsFNumerosa(true);
					solicitudBean.setFamNumerosa("G");
				
			}
			// Si es Discapacidad
			if(pagoSolicitud.getResults().get(0).getMotivoReduccionTasa()!=null && pagoSolicitud.getResults().get(0).getMotivoReduccionTasa().getId()==1){
				
				solicitudBean.setEsDiscapacidad(true);
			 
			}
			// Si es Victima Terrorismo
			if(pagoSolicitud.getResults().get(0).getMotivoReduccionTasa()!=null && pagoSolicitud.getResults().get(0).getMotivoReduccionTasa().getId()==6){
				
				solicitudBean.setEsVictimaTerrorismo(true);
			 
			}
			if(pagoSolicitud.getResults().get(0).getResultado().equals("OK")){	
				// Obtenemos la Fecha de Pago	
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				String fechaPago= null;
				if (pagoSolicitudBean.getFechaIntento() != null) {
					fechaPago = formatoFecha.format(pagoSolicitudBean.getFechaIntento());	
					solicitudBean.setFechaPago(fechaPago);
				}
				
				//Importe
				float importe = 0;
				for (PagoSolicitud pagoSol : pagoSolicitud.getResults()) {
					if (pagoSol.getResultado().equals("OK")) {
						importe += pagoSol.getImporte();
					}
				}
				solicitudBean.setImporte(importe);
			}
		}
		
		
		//Carga de campos FUN-3.5 - INI
		solicitudBean.setAdmitido(ExportarExcelSolicitudesSpring.obtieneAdmitido(solicitudBean));
		solicitudBean.setEstadoPID(ExportarExcelSolicitudesSpring.obtieneEstadoPID(solicitudBean));
		//Carga de campos FUN-3.5 - FIN
		
		return solicitudBean;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PagoSolicitudManager#buscarPagoSolicitudByIdSolicitud(es.map.ips.model.PagoSolicitudQuery)
	 */
	public PagoSolicitudBean buscarPagoSolicitudByIdSolicitud(
			PagoSolicitudQuery pagoSolicitudQuery) {
		PagoSolicitud pagoSolicitud = pagoSolicitudDAO.searchUnique(pagoSolicitudQuery);
		if(pagoSolicitud != null){
			return toPagoSolicitudBean(pagoSolicitud);
		}
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PagoSolicitudManager#buscarUltimoPagoSolicitudByIdSolicitud(es.map.ips.model.PagoSolicitudQuery)
	 */
	public PagoSolicitudBean buscarUltimoPagoSolicitudByIdSolicitud(PagoSolicitudQuery pagoSolicitudQuery) {
		
		SearchResult<PagoSolicitud> pagoSolicitud = null;
		pagoSolicitudQuery.setMaxResults(1);
		pagoSolicitudQuery.addOrder("id", OrderType.DESC);
		try{
			pagoSolicitud = pagoSolicitudDAO.search(pagoSolicitudQuery);
		}catch(Exception e){
			logger.error("Error buscar ultimo pago solicitud ",e);
		}
		
		if(pagoSolicitud != null && pagoSolicitud.getResults() != null && pagoSolicitud.getResults().size() > 0){
			return toPagoSolicitudBean(pagoSolicitud.getResults().get(0));
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PagoSolicitudManager#buscarUltimoPagoSolicitudIdSolicituOK(es.map.ips.model.PagoSolicitudQuery)
	 */
	public PagoSolicitudBean buscarUltimoPagoSolicitudIdSolicituOK(PagoSolicitudQuery pagoSolicitudQuery) {
		
		SearchResult<PagoSolicitud> pagoSolicitud = null;
		pagoSolicitudQuery.addOrder("id", OrderType.DESC);
		try{
			pagoSolicitud = pagoSolicitudDAO.search(pagoSolicitudQuery);
		}catch(Exception e){
			logger.error("Error buscar ultimo pago solicitud ",e);
		}
		
		if(pagoSolicitud==null || pagoSolicitud.size()==0){
			return null;
		}


		PagoSolicitudBean detalle = new PagoSolicitudBean();
		if(pagoSolicitud.getResults()!=null && pagoSolicitud.getResults().size()>0 && pagoSolicitud.getResults().get(0) != null){
			// Cogemos el ultimo
			// Lo devolvemos solo si es correcto
			if(pagoSolicitud.getResults().get(0).getResultado() != null && pagoSolicitud.getResults().get(0).getResultado().equals("OK")){
				detalle = toPagoSolicitudBean(pagoSolicitud.getResults().get(0));
			}else{
				detalle = null;
			}	
		}	
		
		return detalle;
	}

	/**
	 * Busca todos los Pagos Solicitud .
	 *
	 * @param pagoSolicitudQuery PagoSolicitudQuery
	 * @return arrPagoSolicitud  ArrayList<PagoSolicitudBean>
	 */
	public ArrayList<PagoSolicitudBean> buscarPagoSolicitudAll(PagoSolicitudQuery pagoSolicitudQuery){		
		SearchResult<PagoSolicitud> pagoSolicitud = buscarPagoSolicitud(pagoSolicitudQuery);
		
		ArrayList<PagoSolicitudBean> arrPagoSolicitud = new ArrayList<PagoSolicitudBean>();
		for(int i=0;i<pagoSolicitud.getResults().size();i++){
			PagoSolicitudBean aux;
			aux = toPagoSolicitudBean(pagoSolicitud.getResults().get(i));
			if(aux != null){
				arrPagoSolicitud.add(aux);
			}
		}	
		return arrPagoSolicitud;		
	}
	
	/**
	 * Guarda un Pago de Solicitud.
	 *
	 * @param pagoSolicitudBean PagoSolicitudBean
	 * @return IidPagoSolicitud Integer
	 */
	public Integer guardarPagoSolicitudBean(PagoSolicitudBean pagoSolicitudBean){

		PagoSolicitud pagoSolicitud =  toPagoSolicitud(pagoSolicitudBean);
		Long idPagoSolicitud = pagoSolicitudDAO.insert(pagoSolicitud);
		
		Integer IidPagoSolicitud = null;
		if(idPagoSolicitud != null)
		{
			IidPagoSolicitud = new Integer(idPagoSolicitud.toString());
		}
		return IidPagoSolicitud;
	}
	
	/**
	 * Guarda un Pago de Solicitud Bean.
	 *
	 * @param pagoSolicitudBean PagoSolicitudBean
	 */
	public void guardarPagoSolicitud(PagoSolicitudBean pagoSolicitudBean){

		PagoSolicitud pagoSolicitud =  toPagoSolicitud(pagoSolicitudBean);
		pagoSolicitudDAO.insert(pagoSolicitud);
	}
	
	/**
	 * Guarda un Pago de Solicitud entidad.
	 *
	 * @param pagoSolicitud PagoSolicitud
	 */
	public void almacenarPagoSolicitud(PagoSolicitud pagoSolicitud){
		pagoSolicitudDAO.insert(pagoSolicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PagoSolicitudManager#guardarPagoSolicitudConsulta(es.map.ipsg.bean.PagoSolicitudBean)
	 */
	public void guardarPagoSolicitudConsulta(PagoSolicitudBean pagoSolicitudBean) {
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(pagoSolicitudBean.getIdSolicitud());
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
		
		pagoSolicitudBean.setSolicitud(solicitud);
		
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		entidadFinancieraQuery.setCodigo(pagoSolicitudBean.getCodEntidadFinanciera());
		EntidadFinanciera entidadFinanciera = entidadFinancieraDAO.searchUnique(entidadFinancieraQuery);
		
		pagoSolicitudBean.setEntidadFinanciera(entidadFinanciera);
		PagoSolicitud pagoSolicitud =  toPagoSolicitud(pagoSolicitudBean);
		pagoSolicitudDAO.insert(pagoSolicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PagoSolicitudManager#modificarPagoSolicitudBean(es.map.ipsg.bean.PagoSolicitudBean)
	 */
	public void modificarPagoSolicitudBean(PagoSolicitudBean pagoSolicitudBean) {
		
		PagoSolicitud pagoSolicitud =  toPagoSolicitud(pagoSolicitudBean);
		pagoSolicitudDAO.update(pagoSolicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PagoSolicitudManager#eliminarPagoSolicitud(java.lang.Long)
	 */
	public void eliminarPagoSolicitud(Long id) {
		
		pagoSolicitudDAO.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PagoSolicitudManager#buscarPagoSolicitudIdSolicituPruebas(es.map.ips.model.PagoSolicitudQuery)
	 */
	public PagoSolicitudBean buscarPagoSolicitudIdSolicituPruebas(PagoSolicitudQuery pagoSolicitudQuery) {
		
		SearchResult<PagoSolicitud> pagoSolicitud = null;
		
		try{
			pagoSolicitud = pagoSolicitudDAO.search(pagoSolicitudQuery);
		}catch(Exception e){
			logger.error("Error Buscar pago solicitud pruebas",e);
		}
		
		if(pagoSolicitud==null || pagoSolicitud.size()==0){
			return null;
		}
		
		PagoSolicitudBean detalle = new PagoSolicitudBean();
		
		if(pagoSolicitud.getResults()!=null && pagoSolicitud.getResults().size()>0){
			detalle = toPagoSolicitudBean(pagoSolicitud.getResults().get(0));
		}		
		
		return detalle;
	}
	
	/**
	 * Pasa de PagoSolicitudBean a PagoSolicitud.
	 *
	 * @param pagoSolicitudBean PagoSolicitudBean
	 * @return pagoSolicitud PagoSolicitud
	 */
	private PagoSolicitud toPagoSolicitud (PagoSolicitudBean pagoSolicitudBean)
	{
		PagoSolicitud pagoSolicitud = new PagoSolicitud();
		
		if(pagoSolicitudBean.getSolicitud()!= null){
		pagoSolicitud.setSolicitudComun(pagoSolicitudBean.getSolicitud());
		}else{
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			solicitudQuery.setIdSolicitud(pagoSolicitudBean.getIdSolicitud());
			SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
			if(solicitud != null){
			pagoSolicitud.setSolicitudComun(solicitud);
			}
		}
	
		if(pagoSolicitudBean.getEntidadFinanciera()!=null){
		pagoSolicitud.setEntidadFinanciera(pagoSolicitudBean.getEntidadFinanciera());
		}
		
		pagoSolicitud.setFechaIntento(pagoSolicitudBean.getFechaIntento());
		
		pagoSolicitud.setId(pagoSolicitudBean.getId());
		
		if(pagoSolicitudBean.getLogservicio() != null){
			pagoSolicitud.setLogServicio(pagoSolicitudBean.getLogservicio());
		}
		
		if(pagoSolicitudBean.getMotivoReduccionTasa() != null){
			pagoSolicitud.setMotivoReduccionTasa(pagoSolicitudBean.getMotivoReduccionTasa());
		}

		pagoSolicitud.setImporte(pagoSolicitudBean.getImporte());
		
		if(pagoSolicitudBean.getNrc() !=null){
			pagoSolicitud.setNrc(pagoSolicitudBean.getNrc());	
		}
		if(pagoSolicitudBean.getResultado()!= null){
			pagoSolicitud.setResultado(pagoSolicitudBean.getResultado());
		}
		
		if(pagoSolicitudBean.getSolicitaReduccion() != null){
			pagoSolicitud.setSolicitaReduccion(pagoSolicitudBean.getSolicitaReduccion());
		}
		
		if(pagoSolicitudBean.getTipo() != null && pagoSolicitudBean.getTipo().equals(Constantes.TIPO_PAGO_CUENTA_INT)){
			pagoSolicitud.setTipo(Constantes.TIPO_PAGO_CUENTA_CODIGO);
		}
		
		if(pagoSolicitudBean.getTipo() != null && pagoSolicitudBean.getTipo().equals(Constantes.TIPO_PAGO_TARJETA_INT)){
			pagoSolicitud.setTipo(Constantes.TIPO_PAGO_TARJETA_CODIGO);
		}
		
		if(pagoSolicitudBean.getTipo() != null && pagoSolicitudBean.getTipo().equals(Constantes.TIPO_PAGO_EFECTIVO_INT)){
			pagoSolicitud.setTipo(Constantes.TIPO_PAGO_EFECTIVO_CODIGO);
		}
		
		if(pagoSolicitudBean.getTipo() != null && pagoSolicitudBean.getTipo().equals(Constantes.TIPO_PAGO_VERIFICADO_CODIGO)){
			pagoSolicitud.setTipo(Constantes.TIPO_PAGO_VERIFICADO_CODIGO);
		}
		////Si es exento que lo ponga como pago en efectivo
		if(pagoSolicitudBean.getTipo() != null && pagoSolicitudBean.getTipo().equals(Constantes.TIPO_PAGO_EXENTO_INT)){
			pagoSolicitud.setTipo(Constantes.TIPO_PAGO_EFECTIVO_CODIGO);
		}
		
		if(pagoSolicitudBean.getTipo() != null && pagoSolicitudBean.getTipo().equals(Constantes.TIPO_PAGO_NINGUNO_INT)){
			pagoSolicitud.setTipo(Constantes.TIPO_PAGO_NINGUNO_CODIGO);
		}
		
		return pagoSolicitud;
	}
	
	/**
	 * Pasa de PagoSolicitud a PagoSolicitudBean.
	 *
	 * @param pagoSolicitud PagoSolicitud
	 * @return pagoSolicitudBean PagoSolicitudBean
	 */
	private PagoSolicitudBean toPagoSolicitudBean (PagoSolicitud pagoSolicitud)
	{
		PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
		pagoSolicitudBean.setEntidadFinanciera(pagoSolicitud.getEntidadFinanciera());
		pagoSolicitudBean.setFechaIntento(pagoSolicitud.getFechaIntento());
		pagoSolicitudBean.setId(pagoSolicitud.getId());
		if(pagoSolicitud.getSolicitudComun() != null)
		{	
			pagoSolicitudBean.setIdSolicitud(pagoSolicitud.getSolicitudComun().getIdSolicitud());
		}	
		pagoSolicitudBean.setLogservicio(pagoSolicitud.getLogServicio());
		pagoSolicitudBean.setMotivoReduccionTasa(pagoSolicitud.getMotivoReduccionTasa());
		pagoSolicitudBean.setSolicitud(pagoSolicitud.getSolicitudComun());
		pagoSolicitudBean.setImporte(pagoSolicitud.getImporte());
		pagoSolicitudBean.setNrc(pagoSolicitud.getNrc());
		pagoSolicitudBean.setResultado(pagoSolicitud.getResultado());
		pagoSolicitudBean.setSolicitaReduccion(pagoSolicitud.getSolicitaReduccion());
		pagoSolicitudBean.setTipo(pagoSolicitud.getTipo());
		if(pagoSolicitud.getMotivoReduccionTasa() != null){
			pagoSolicitudBean.setDesMotivoReduccionTasa(pagoSolicitud.getMotivoReduccionTasa().getDescripcion());
		}
		if(pagoSolicitud.getEntidadFinanciera() != null){
			pagoSolicitudBean.setDesEntidadFinanciera(pagoSolicitud.getEntidadFinanciera().getDescripcion());
			pagoSolicitudBean.setCodEntidadFinanciera(pagoSolicitud.getEntidadFinanciera().getCodigo());
		}
		if(pagoSolicitud.getResultado()!=null && pagoSolicitud.getResultado().equals(Constantes.RESULTADO_ER) && pagoSolicitud.getLogServicio()!=null){
			pagoSolicitudBean.setCodError(pagoSolicitud.getLogServicio().getCodigoError());
			pagoSolicitudBean.setDesError(pagoSolicitud.getLogServicio().getDescripcionError());
		}
		
		return pagoSolicitudBean;
	}

	/**
	 * To correo electronico bean.
	 *
	 * @param correoElectronico el correo electronico
	 * @return el correo electronico bean
	 */
	public CorreoElectronicoBean toCorreoElectronicoBean(CorreoElectronico correoElectronico){
		
		CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
		
		if(correoElectronico.getId()!=null){
			correoElectronicoBean.setId(correoElectronico.getId());
		}
		correoElectronicoBean.setDe(correoElectronico.getDe());
		correoElectronicoBean.setPara(correoElectronico.getPara());
		correoElectronicoBean.setMensaje(correoElectronico.getMensaje());
		correoElectronicoBean.setAsunto(correoElectronico.getAsunto());
		correoElectronicoBean.setFechaEnvio(correoElectronico.getFechaEnvio());
		correoElectronicoBean.setEstado(correoElectronico.getEstado());
		correoElectronicoBean.setSolicituds(correoElectronico.getSolicituds());
		correoElectronicoBean.setAlertas(correoElectronico.getAlertas());
				
		return correoElectronicoBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PagoSolicitudManager#borrarSolicitudPago(java.lang.Long)
	 */
	public void borrarSolicitudPago(Long idSolicitud){
		try{
			PagoSolicitudQuery pagoQuery = new PagoSolicitudQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(idSolicitud);
			pagoQuery.setSolicitudComun(solicitudComunQuery);
			// Buscamos todos los registros que contienen ese id de Solicitud
			SearchResult<PagoSolicitud> solicitudPago = pagoSolicitudDAO.search(pagoQuery);
			if(solicitudPago != null && solicitudPago.size()>0){
				for (int i = 0; i < solicitudPago.size(); i++) {
					// Eliminamos la solicitud
					Long idPagoSolicitud = solicitudPago.getResults().get(i).getId();
					pagoSolicitudDAO.delete(idPagoSolicitud);
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de pago_solicitud con ID_SOLICITUD=" + idSolicitud);
			logger.error("Error: " + e);
		}
	}
	
	/**
	 * Obtiene el pago solicitud DAO.
	 *
	 * @return pagoSolicitudDAO PagoSolicitudDAO
	 */
	public PagoSolicitudDAO getPagoSolicitudDAO() {
		return pagoSolicitudDAO;
	}


	/**
	 * Establece el pago solicitud DAO.
	 *
	 * @param pagoSolicitudDAO PagoSolicitudDAO
	 */
	public void setPagoSolicitudDAO(PagoSolicitudDAO pagoSolicitudDAO) {
		this.pagoSolicitudDAO = pagoSolicitudDAO;
	}


	/**
	 * Obtiene el solicitud comun DAO.
	 *
	 * @return el solicitud comun DAO
	 */
	public SolicitudComunDAO getSolicitudComunDAO() {
		return solicitudComunDAO;
	}


	/**
	 * Establece el solicitud comun DAO.
	 *
	 * @param solicitudComunDAO el nuevo solicitud comun DAO
	 */
	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
		this.solicitudComunDAO = solicitudComunDAO;
	}


	/**
	 * Obtiene el entidad financiera DAO.
	 *
	 * @return el entidad financiera DAO
	 */
	public EntidadFinancieraDAO getEntidadFinancieraDAO() {
		return entidadFinancieraDAO;
	}


	/**
	 * Establece el entidad financiera DAO.
	 *
	 * @param entidadFinancieraDAO el nuevo entidad financiera DAO
	 */
	public void setEntidadFinancieraDAO(EntidadFinancieraDAO entidadFinancieraDAO) {
		this.entidadFinancieraDAO = entidadFinancieraDAO;
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
