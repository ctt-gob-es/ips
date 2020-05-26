package es.map.ipsc.manager.solicitudes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.PagoSolicitudDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.LogServicio;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.PagoSolicitudBean;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;

/**
 * El Class PagoSolicitudManagerImpl.
 *
 * @author everis
 */
public class PagoSolicitudManagerImpl implements PagoSolicitudManager {
	
	/** el pago solicitud DAO. */
	private PagoSolicitudDAO pagoSolicitudDAO;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el motivo reduccion manager. */
	private MotivoReduccionManager motivoReduccionManager;
	
	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PagoSolicitudManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#buscarPagoSolicitud(es.map.ips.model.PagoSolicitudQuery)
	 */
	public ArrayList<PagoSolicitudBean> buscarPagoSolicitud(PagoSolicitudQuery pagoSolicitudQuery) {
		
		ArrayList<PagoSolicitudBean> pagoBean = new ArrayList<PagoSolicitudBean>();
		SearchResult<PagoSolicitud> pagos = pagoSolicitudDAO.search(pagoSolicitudQuery);
		
		for(int i=0;i<pagos.getResults().size();i++){
			PagoSolicitudBean aux;
			aux = toPagoBean(pagos.getResults().get(i));
			if(aux != null){
				pagoBean.add(aux);
			}
		}
		return pagoBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#buscarDetallePagoSolicitud(es.map.ips.model.PagoSolicitudQuery)
	 */
	public ArrayList<DetallePagoSolicitudBean> buscarDetallePagoSolicitud(PagoSolicitudQuery pagoSolicitudQuery) {
		
		ArrayList<DetallePagoSolicitudBean> pagoBean = new ArrayList<DetallePagoSolicitudBean>();
		SearchResult<PagoSolicitud> pagos = pagoSolicitudDAO.search(pagoSolicitudQuery);
		
		for(int i=0;i<pagos.getResults().size();i++){
			DetallePagoSolicitudBean aux;
			aux = toDetalleBean(pagos.getResults().get(i));
			if(aux != null){
				pagoBean.add(aux);
			}
		}
		return pagoBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#buscarPagoSol(es.map.ips.model.PagoSolicitudQuery)
	 */
	public ArrayList<PagoSolicitud> buscarPagoSol(PagoSolicitudQuery pagoSolicitudQuery) {
		
		SearchResult<PagoSolicitud> pagos = pagoSolicitudDAO.search(pagoSolicitudQuery);		
		return (ArrayList<PagoSolicitud>) pagos.getResults();
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#buscarPagoSolicitudIdSolicitu(es.map.ips.model.PagoSolicitudQuery)
	 */
	public DetallePagoSolicitudBean buscarPagoSolicitudIdSolicitu(PagoSolicitudQuery pagoSolicitudQuery) {
		
		PagoSolicitud pagoSolicitud = new PagoSolicitud();
		
		try{
			pagoSolicitud = pagoSolicitudDAO.searchUnique(pagoSolicitudQuery);
		}catch(Exception e){
			logger.error("Error buscar pago solicitud ",e);
		}
		
		if(pagoSolicitud == null){
			return null;
		}
		
		DetallePagoSolicitudBean detalle;
		detalle = toDetalleBean(pagoSolicitud);
		
		return detalle;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#buscarPagoSolicitudIdSolicituPruebas(es.map.ips.model.PagoSolicitudQuery)
	 */
	public DetallePagoSolicitudBean buscarPagoSolicitudIdSolicituPruebas(PagoSolicitudQuery pagoSolicitudQuery) {
		
		SearchResult<PagoSolicitud> pagoSolicitud = null;
		
		try{
			pagoSolicitud = pagoSolicitudDAO.search(pagoSolicitudQuery);
		}catch(Exception e){
			logger.error("Error Buscar pago solicitud pruebas",e);
		}
		
		if(pagoSolicitud.size()==0 || pagoSolicitud==null){
			return null;
		}
		
		DetallePagoSolicitudBean detalle = new DetallePagoSolicitudBean();
		
		if(pagoSolicitud.getResults()!=null && pagoSolicitud.getResults().size()>0){
			detalle = toDetalleBean(pagoSolicitud.getResults().get(0));
		}		
		
		return detalle;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#buscarUltimoPagoSolicitudIdSolicitu(es.map.ips.model.PagoSolicitudQuery)
	 */
	public DetallePagoSolicitudBean buscarUltimoPagoSolicitudIdSolicitud(PagoSolicitudQuery pagoSolicitudQuery) {
		
		SearchResult<PagoSolicitud> pagoSolicitud = null;
		pagoSolicitudQuery.setMaxResults(1);
		pagoSolicitudQuery.addOrder(PagoSolicitudQuery.ID, OrderType.DESC);
		try{
			pagoSolicitud = pagoSolicitudDAO.search(pagoSolicitudQuery);
		}catch(Exception e){
			logger.error("Error buscar ultimo pago solicitud ",e);
		}
		
		if(pagoSolicitud.size()==0 || pagoSolicitud==null){
			return null;
		}

		DetallePagoSolicitudBean detalle = new DetallePagoSolicitudBean();
		if(pagoSolicitud.getResults()!=null && pagoSolicitud.getResults().size()>0){
			detalle = toDetalleBean(pagoSolicitud.getResults().get(0));
		}	
		
		return detalle;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#buscarUltimoPagoSolicitudIdSolicituOK(es.map.ips.model.PagoSolicitudQuery)
	 */
	public DetallePagoSolicitudBean buscarUltimoPagoSolicitudIdSolicituOK(PagoSolicitudQuery pagoSolicitudQuery) {
		
		SearchResult<PagoSolicitud> pagoSolicitud = null;
		pagoSolicitudQuery.addOrder(PagoSolicitudQuery.ID, OrderType.DESC);
		try{
			pagoSolicitud = pagoSolicitudDAO.search(pagoSolicitudQuery);
		}catch(Exception e){
			logger.error("Error buscar ultimo pago solicitud ",e);
		}
		
		if(pagoSolicitud.size()==0 || pagoSolicitud==null){
			return null;
		}

		DetallePagoSolicitudBean detalle = new DetallePagoSolicitudBean();
		if(pagoSolicitud.getResults()!=null && pagoSolicitud.getResults().size()>0 && pagoSolicitud.getResults().get(0) != null){
			// Cogemos el ultimo
			// Lo devolvemos solo si es correcto
			if(pagoSolicitud.getResults().get(0).getResultado() != null && pagoSolicitud.getResults().get(0).getResultado().equals("OK")){
				detalle = toDetalleBean(pagoSolicitud.getResults().get(0));
			}else{
				detalle = null;
			}	
		}	
		
		return detalle;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#guardarPagoSolicitud(es.map.ipsc.bean.PagoSolicitudBean)
	 */
	public void guardarPagoSolicitud(PagoSolicitudBean pagoSolicitudBean) {
		
		PagoSolicitud pagoSolicitud = toPagoSolicitudSolicitud(pagoSolicitudBean);
		
		if(pagoSolicitudBean.getIdLogServicio()>0){
			LogServicio logServicio = new LogServicio();
			logServicio.setId(pagoSolicitudBean.getIdLogServicio());
			pagoSolicitud.setLogServicio(logServicio);
		}
		
		pagoSolicitudDAO.insert(pagoSolicitud);	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#guardarPagoSolicitudModif(es.map.ipsc.bean.PagoSolicitudBean)
	 */
	public void guardarPagoSolicitudModif(PagoSolicitudBean pagoSolicitudBean) {
		
		PagoSolicitud pagoSolicitud = toPagoSolicitudSolicitudModif(pagoSolicitudBean);
		
		if(pagoSolicitudBean.getIdLogServicio()>0){
			LogServicio logServicio = new LogServicio();
			logServicio.setId(pagoSolicitudBean.getIdLogServicio());
			pagoSolicitud.setLogServicio(logServicio);
		}
		
		pagoSolicitudDAO.insert(pagoSolicitud);	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#actualizarEstadoSolicitud(es.map.ipsc.bean.PagoSolicitudBean)
	 */
	public void actualizarEstadoSolicitud(PagoSolicitudBean pagoSolicitudBean){
		PagoSolicitud pagoAux = toPagoSolicitudSolicitud(pagoSolicitudBean);
		pagoAux.setId(pagoSolicitudBean.getId());
		pagoSolicitudDAO.update(pagoAux);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.PagoSolicitudManager#buscarPago(es.map.ips.model.PagoSolicitudQuery)
	 */
	public Boolean buscarPago(PagoSolicitudQuery pagoSolicitudQuery) {

		Boolean retorno = true;
		SearchResult<PagoSolicitud> busqueda = this.pagoSolicitudDAO.search(pagoSolicitudQuery);
		
		if(null==busqueda || null==busqueda.getResults() || busqueda.getResults().size()==0){
			retorno = null;
		}
		
		return retorno;
	}

	/**
	 * To pago solicitud solicitud.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 * @return el pago solicitud
	 */
	private PagoSolicitud toPagoSolicitudSolicitud(PagoSolicitudBean pagoSolicitudBean) {
		
		PagoSolicitud aux = new PagoSolicitud();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		String idSolicitud = pagoSolicitudBean.getIdSolicitud();
		solicitudQuery.setIdSolicitud(Long.parseLong(idSolicitud));
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
	
		if(solicitud != null){
			aux.setSolicitudComun(solicitud);
		}

		if(pagoSolicitudBean.getFechaIntento() != null){
			aux.setFechaIntento(pagoSolicitudBean.getFechaIntento());
		}
		
		aux.setTipo(pagoSolicitudBean.getTipo());
		
		if(pagoSolicitudBean.getImporte() != null){
			aux.setImporte(pagoSolicitudBean.getImporte());
		}
		
		if(pagoSolicitudBean.getResultado() != null){
			aux.setResultado(pagoSolicitudBean.getResultado());
		}
		
		if(pagoSolicitudBean.getReduccionPago() != null){
			aux.setSolicitaReduccion(pagoSolicitudBean.getReduccionPago().charAt(0));
		}
		
		if(pagoSolicitudBean.getNrc() != null){
			aux.setNrc(pagoSolicitudBean.getNrc().trim());
		}
		
		if(pagoSolicitudBean.getNrcCiudadano() != null){
			aux.setNrc(pagoSolicitudBean.getNrcCiudadano().trim());
		}
		
		MotivoReduccionTasaQuery motivoReduccionQuery = new MotivoReduccionTasaQuery();
		
		if(pagoSolicitudBean.getMotivo() != null && !"".equals(pagoSolicitudBean.getMotivo())){
			motivoReduccionQuery.setId(Integer.parseInt(pagoSolicitudBean.getMotivo()));
			MotivoReduccionTasa motivoReduccion = motivoReduccionManager.buscarMotivoReduccionById(motivoReduccionQuery);
			
			if(motivoReduccion != null){
				aux.setMotivoReduccionTasa(motivoReduccion);
			}
			aux.setSolicitaReduccion(Constantes.REDUCCION_PAGO_SI.charAt(0));
		}else{
			aux.setSolicitaReduccion(Constantes.REDUCCION_PAGO_NO.charAt(0));
		}
		
		if(pagoSolicitudBean.getReduccionPago() != null && !"".equals(pagoSolicitudBean.getReduccionPago())){
			aux.setSolicitaReduccion(pagoSolicitudBean.getReduccionPago().charAt(0));
		}
		
		if(pagoSolicitudBean.getEntidad() != null && !"0".equals(pagoSolicitudBean.getEntidad()) 
				&& !"".equals(pagoSolicitudBean.getEntidad())){
			EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
			entidadFinancieraQuery.setCodigo(pagoSolicitudBean.getEntidad());
			EntidadFinanciera entidadFinanciera = entidadFinancieraManager.buscarEntidadById(entidadFinancieraQuery);
			if(entidadFinanciera != null){
				aux.setEntidadFinanciera(entidadFinanciera);
			}
		}
		
		return aux;
	}
	
	/**
	 * To pago solicitud solicitud modif.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 * @return el pago solicitud
	 */
	private PagoSolicitud toPagoSolicitudSolicitudModif(PagoSolicitudBean pagoSolicitudBean) {
		
		PagoSolicitud aux = new PagoSolicitud();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		String idSolicitud = pagoSolicitudBean.getIdSolicitud();
		solicitudQuery.setIdSolicitud(Long.parseLong(idSolicitud));
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
	
		if(solicitud != null){
			aux.setSolicitudComun(solicitud);
		}

		if(pagoSolicitudBean.getFechaIntento() != null){
			aux.setFechaIntento(pagoSolicitudBean.getFechaIntento());
		}
		
		aux.setTipo(pagoSolicitudBean.getTipo());
		
		if(pagoSolicitudBean.getImporte() != null){
			aux.setImporte(pagoSolicitudBean.getImporte());
		}
		
		if(pagoSolicitudBean.getResultado() != null){
			aux.setResultado(pagoSolicitudBean.getResultado());
		}
		
		if(pagoSolicitudBean.getReduccionPago() != null){
			aux.setSolicitaReduccion(pagoSolicitudBean.getReduccionPago().charAt(0));
		}
		
		if(pagoSolicitudBean.getNrc() != null){
			aux.setNrc(pagoSolicitudBean.getNrc().trim());
		}
		
		if(pagoSolicitudBean.getNrcCiudadano() != null){
			aux.setNrc(pagoSolicitudBean.getNrcCiudadano().trim());
		}
		
		MotivoReduccionTasaQuery motivoReduccionQuery = new MotivoReduccionTasaQuery();
		
		if(pagoSolicitudBean.getMotivo() != null && !"".equals(pagoSolicitudBean.getMotivo())){
			motivoReduccionQuery.setId(Integer.parseInt(pagoSolicitudBean.getMotivo()));
			MotivoReduccionTasa motivoReduccion = motivoReduccionManager.buscarMotivoReduccionById(motivoReduccionQuery);
			
			if(motivoReduccion != null){
				aux.setMotivoReduccionTasa(motivoReduccion);
			}
			aux.setSolicitaReduccion(Constantes.REDUCCION_PAGO_SI.charAt(0));
		}else{
			aux.setSolicitaReduccion(Constantes.REDUCCION_PAGO_NO.charAt(0));
		}
		
		if(pagoSolicitudBean.getReduccionPago() != null && !"".equals(pagoSolicitudBean.getReduccionPago())){
			aux.setSolicitaReduccion(pagoSolicitudBean.getReduccionPago().charAt(0));
		}
		
		if(pagoSolicitudBean.getCodigoEntidad() != null && !"0".equals(pagoSolicitudBean.getCodigoEntidad()) 
				&& !"".equals(pagoSolicitudBean.getCodigoEntidad())){
			EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
			entidadFinancieraQuery.setCodigo(pagoSolicitudBean.getCodigoEntidad());
			EntidadFinanciera entidadFinanciera = entidadFinancieraManager.buscarEntidadById(entidadFinancieraQuery);
			if(entidadFinanciera != null){
				aux.setEntidadFinanciera(entidadFinanciera);
			}
		}
		
		return aux;
	}

	/**
	 * To detalle bean.
	 *
	 * @param pagoSolicitud el pago solicitud
	 * @return el detalle pago solicitud bean
	 */
	private DetallePagoSolicitudBean toDetalleBean(PagoSolicitud pagoSolicitud) {
		DetallePagoSolicitudBean aux = new DetallePagoSolicitudBean();
		
		if(pagoSolicitud.getId() != null){
			aux.setIdPagoSolicitud(String.valueOf(pagoSolicitud.getId()));
		}
		if(pagoSolicitud.getSolicitudComun() != null && pagoSolicitud.getSolicitudComun().getIdSolicitud() != null){
			aux.setIdSolicitud(String.valueOf(pagoSolicitud.getSolicitudComun().getIdSolicitud()));
		}
		if(pagoSolicitud.getFechaIntento() != null){
			SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String fechaAux = formatoFecha.format(pagoSolicitud.getFechaIntento());
			aux.setFechaIntento(fechaAux);
		}
		if(pagoSolicitud.getTipo() != null){
			if(Constantes.FORMA_PAGO_EXENTO == (pagoSolicitud.getTipo())){
				aux.setTipo(Constantes.PAGO_TIPO_EXENTO.toUpperCase());
			}else{
				if(Constantes.FORMA_PAGO_TARJETA ==(pagoSolicitud.getTipo())){
					aux.setTipo(Constantes.PAGO_TIPO_TARJETA.toUpperCase());
				}else if(Constantes.FORMA_PAGO_ADEUDO ==(pagoSolicitud.getTipo())){
					aux.setTipo(Constantes.PAGO_TIPO_ADEUDO.toUpperCase());
				}else if(Constantes.FORMA_PAGO_NINGUNO ==(pagoSolicitud.getTipo())){
					aux.setTipo(Constantes.PAGO_TIPO_NINGUNO.toUpperCase());
				}
			}
		}
		if(pagoSolicitud.getImporte() != null){
			aux.setImporte(String.valueOf(pagoSolicitud.getImporte()));
		}
		if(pagoSolicitud.getResultado() != null){
			aux.setResultado(pagoSolicitud.getResultado());
		}
		if(pagoSolicitud.getSolicitaReduccion() != null){
			aux.setReduccion(String.valueOf(pagoSolicitud.getSolicitaReduccion()));
		}
		if(pagoSolicitud.getMotivoReduccionTasa() != null){
			aux.setMotivoReduccion(String.valueOf(pagoSolicitud.getMotivoReduccionTasa().getId()).toUpperCase());
			aux.setDesMotivoReduccion(pagoSolicitud.getMotivoReduccionTasa().getDescripcion().toUpperCase());
		}
		if(pagoSolicitud.getEntidadFinanciera() != null){
			aux.setEntidad(String.valueOf(pagoSolicitud.getEntidadFinanciera()).toUpperCase());
			aux.setDesEntidad(pagoSolicitud.getEntidadFinanciera().getDescripcion().toUpperCase());
		}
		if(pagoSolicitud.getNrc() != null){
			aux.setNrc(pagoSolicitud.getNrc().toUpperCase());
		}
		if(pagoSolicitud.getMotivoReduccionTasa() != null){
			aux.setMotivoReduccionTasa(pagoSolicitud.getMotivoReduccionTasa());
		}
		return aux;
	}

	/**
	 * To pago bean.
	 *
	 * @param pagoSolicitud el pago solicitud
	 * @return el pago solicitud bean
	 */
	private PagoSolicitudBean toPagoBean(PagoSolicitud pagoSolicitud) {
		PagoSolicitudBean aux = new PagoSolicitudBean();
		aux.setId(pagoSolicitud.getId());
		aux.setImporte(pagoSolicitud.getImporte());
		aux.setLogServicio(pagoSolicitud.getLogServicio());
		aux.setFechaIntento(pagoSolicitud.getFechaIntento());
		aux.setResultado(pagoSolicitud.getResultado());
		aux.setSolicitud(pagoSolicitud.getSolicitudComun());
		aux.setTipo(pagoSolicitud.getTipo());
		aux.setNrc(pagoSolicitud.getNrc());
		if(pagoSolicitud.getMotivoReduccionTasa() != null){
			aux.setMotivo(pagoSolicitud.getMotivoReduccionTasa().getId().toString());
		}
		if(pagoSolicitud.getEntidadFinanciera() != null){
			aux.setEntidad(pagoSolicitud.getEntidadFinanciera().getCodigo());
		}
		return aux;
	}


	/**
	 * Obtiene el pago solicitud DAO.
	 *
	 * @return el pago solicitud DAO
	 */
	public PagoSolicitudDAO getPagoSolicitudDAO() {
		return pagoSolicitudDAO;
	}

	/**
	 * Establece el pago solicitud DAO.
	 *
	 * @param pagoSolicitudDAO el nuevo pago solicitud DAO
	 */
	public void setPagoSolicitudDAO(PagoSolicitudDAO pagoSolicitudDAO) {
		this.pagoSolicitudDAO = pagoSolicitudDAO;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
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
	 * Obtiene el entidad financiera manager.
	 *
	 * @return el entidad financiera manager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager el nuevo entidad financiera manager
	 */
	public void setEntidadFinancieraManager(
			EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
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

}
