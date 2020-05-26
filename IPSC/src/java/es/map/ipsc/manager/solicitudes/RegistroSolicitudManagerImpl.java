package es.map.ipsc.manager.solicitudes;

import java.text.SimpleDateFormat;
import java.util.Date;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.RegistroSolicitudDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.LogServicio;
import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.DetalleRegistroSolicitudBean;
import es.map.ipsc.bean.RegistroSolicitudBean;
import org.apache.log4j.Logger;

/**
 * El Class RegistroSolicitudManagerImpl.
 *
 * @author everis
 */
public class RegistroSolicitudManagerImpl implements RegistroSolicitudManager {

	/** el registro solicitud DAO. */
	private RegistroSolicitudDAO registroSolicitudDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RegistroSolicitudManagerImpl.class);

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.RegistroSolicitudManager#buscarRegistrosDuplicados(es.map.ips.model.RegistroSolicitudQuery)
	 */
	public Integer buscarRegistrosDuplicados (RegistroSolicitudQuery registroSolicitudQuery){

		SearchResult<RegistroSolicitud> registros = registroSolicitudDAO.search(registroSolicitudQuery);
		return registros.getNumResults();
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.RegistroSolicitudManager#buscarRegistroSolicitudIdSolicitud(es.map.ips.model.RegistroSolicitudQuery)
	 */
	public DetalleRegistroSolicitudBean buscarRegistroSolicitudIdSolicitud(RegistroSolicitudQuery registroSolicitudQuery) {

		try{
			registroSolicitudQuery.addOrder("id", OrderType.DESC);
			RegistroSolicitud registroSolicitud = registroSolicitudDAO.search(registroSolicitudQuery).getResults().get(0);

			if(registroSolicitud == null){
				return null;
			}
			
			DetalleRegistroSolicitudBean registro;
			registro = toDetalleRegistro(registroSolicitud);
			SolicitudComun sol =solicitudComunDAO.get(registroSolicitud.getSolicitudComun().getIdSolicitud());
			
			if(sol.getIdConsentimiento() != null){
				if(sol.getIdConsentimiento()){
					registro.setConsentimiento(Constantes.NO);
				}	
				else if(sol.getIdConsentimiento() == false){
					registro.setConsentimiento(Constantes.SI);
				}
			}else{
				registro.setConsentimiento("");
			}	

			return registro;

		}catch(Exception e){
			logger.error("buscarRegistroSolicitudIdSolicitud - Error:",e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.RegistroSolicitudManager#guardarRegistro(es.map.ipsc.bean.RegistroSolicitudBean)
	 */
	public Long guardarRegistro(RegistroSolicitudBean registroSolicitudBean) {
		RegistroSolicitud registroSolicitud;
		registroSolicitud = toRegistroSolicitud(registroSolicitudBean);

		Long id = registroSolicitudDAO.insert(registroSolicitud);

		if(id != null){
			return id;
		}

		return null;
	}


	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.RegistroSolicitudManager#buscarRegistro(es.map.ips.model.RegistroSolicitudQuery)
	 */
	public Boolean buscarRegistro(RegistroSolicitudQuery registroSolicitudQuery) {

		Boolean retorno = true;
		SearchResult<RegistroSolicitud> busqueda = this.registroSolicitudDAO.search(registroSolicitudQuery);

		if(null==busqueda || null==busqueda.getResults() || busqueda.getResults().size()==0){
			retorno = null;
		}

		return retorno;
	}

	/**
	 * To registro solicitud.
	 *
	 * @param registroSolicitudBean el registro solicitud bean
	 * @return el registro solicitud
	 */
	private RegistroSolicitud toRegistroSolicitud(RegistroSolicitudBean registroSolicitudBean){
		
		RegistroSolicitud aux = new RegistroSolicitud();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		Long idSolicitud = Long.parseLong(registroSolicitudBean.getIdSolicitud());
		solicitudQuery.setIdSolicitud(idSolicitud);
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
		solicitud.setMotivoOposicion(registroSolicitudBean.getMotivoOposicion());
		solicitud.setIdConsentimientoAEAT(registroSolicitudBean.getIdConsentimientoAEAT());
		aux.setSolicitudComun(solicitud);
		aux.setFechaIntento(registroSolicitudBean.getFechaIntento());
		
		if(registroSolicitudBean.getFechaRegistro() != null && !"".equals(registroSolicitudBean.getFechaRegistro())){
			aux.setFechaRegistro(registroSolicitudBean.getFechaRegistro());
		}

		aux.setResultado(registroSolicitudBean.getResultado());
		
		if(registroSolicitudBean.getNumRegistro() != null && !"".equals(registroSolicitudBean.getNumRegistro())){
			aux.setNumeroRegistro(registroSolicitudBean.getNumRegistro());
		}
		
		aux.setSolicitante(Constantes.REGISTRO_SOLICITANTE_CIUDADANO.charAt(0));
		
		if (registroSolicitudBean.getSolicitante()!=null && !"".equals(registroSolicitudBean.getSolicitante())){
			aux.setSolicitante(Constantes.REGISTRO_SOLICITANTE_FUNCIONARIO.charAt(0));
		}
		
		if(registroSolicitudBean.getOficinaRegistro() != null && !"".equals(registroSolicitudBean.getOficinaRegistro())){
			aux.setOficinaRegistro(registroSolicitudBean.getOficinaRegistro());
		}

		if(registroSolicitudBean.getIdLogServicio() > 0){
			int idLogServicio = registroSolicitudBean.getIdLogServicio();
			LogServicio logServicio = new LogServicio();
			logServicio.setId(idLogServicio);
			aux.setLogServicio(logServicio);
		}
		
		return aux;
	}

	/**
	 * To detalle registro.
	 *
	 * @param registroSolicitud el registro solicitud
	 * @return el detalle registro solicitud bean
	 */
	private DetalleRegistroSolicitudBean toDetalleRegistro(RegistroSolicitud registroSolicitud) {
		
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		DetalleRegistroSolicitudBean aux = new DetalleRegistroSolicitudBean();
		
		if(registroSolicitud.getId() != null){
			aux.setIdRegistro(String.valueOf(registroSolicitud.getId()));
		}
		if(registroSolicitud.getSolicitudComun().getIdSolicitud() != null){
			aux.setIdSolicitud(String.valueOf(registroSolicitud.getSolicitudComun().getIdSolicitud()));
		}
		if(registroSolicitud.getFechaIntento() != null){
			aux.setFechaIntento(String.valueOf(registroSolicitud.getFechaIntento()));
		}
		if(registroSolicitud.getResultado() != null){
			aux.setResultado(registroSolicitud.getResultado());
		}
		if(registroSolicitud.getFechaRegistro() != null){
			aux.setFechaRegistro(String.valueOf(registroSolicitud.getFechaRegistro()));
		}
		if(registroSolicitud.getNumeroRegistro() != null){
			aux.setNumeroRegistro(registroSolicitud.getNumeroRegistro());
		}
		if(registroSolicitud.getSolicitante() != null){
			aux.setSolicitante(String.valueOf(registroSolicitud.getSolicitante()));
		}
		if(registroSolicitud.getOficinaRegistro() != null){
			aux.setOficinaRegistro(registroSolicitud.getOficinaRegistro());
		}
		if(registroSolicitud.getNumeroRegistro() != null){
			aux.setNumeroRegistro(registroSolicitud.getNumeroRegistro());
		}
		if(registroSolicitud.getFechaRegistro() != null){
			aux.setFechaRegistro(formatoFecha.format(registroSolicitud.getFechaRegistro()));
		}

		return aux;
	}

	/**
	 * Obtiene el registro solicitud DAO.
	 *
	 * @return el registro solicitud DAO
	 */
	public RegistroSolicitudDAO getRegistroSolicitudDAO() {
		return registroSolicitudDAO;
	}

	/**
	 * Establece el registro solicitud DAO.
	 *
	 * @param registroSolicitudDAO el nuevo registro solicitud DAO
	 */
	public void setRegistroSolicitudDAO(RegistroSolicitudDAO registroSolicitudDAO) {
		this.registroSolicitudDAO = registroSolicitudDAO;
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
