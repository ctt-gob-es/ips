package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.RegistroSolicitudDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.util.Constantes;


/**
 *  Clase que implementa el RegistroSolicitudManagerImpl.
 *
 * @author amartinl
 */
public class RegistroSolicitudManagerImpl implements RegistroSolicitudManager {

	/** el registro solicitud DAO. */
	//Variables
	private RegistroSolicitudDAO registroSolicitudDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RegistroSolicitudManagerImpl.class);
	
	/**
	 * Buscar registro solicitud.
	 *
	 * @param registroSolicitudQuery el registro solicitud query
	 * @return el search result
	 */
	private SearchResult<RegistroSolicitud> buscarRegistroSolicitud(RegistroSolicitudQuery registroSolicitudQuery) {
		ApplicationException.assertNotNull(registroSolicitudQuery, "RegistroSolicitudQuery no puede ser null");	
		return registroSolicitudDAO.search(registroSolicitudQuery);
	}
	
	/**
	 * Buscar registro solicitud all.
	 *
	 * @param registroSolicitudQuery  RegistroSolicitudQuery
	 * @return  arrRegistroSolicitud ArrayList<RegistroSolicitudBean>
	 * *
	 */
	public ArrayList<RegistroSolicitudBean> buscarRegistroSolicitudAll(RegistroSolicitudQuery registroSolicitudQuery){		
		SearchResult<RegistroSolicitud> registroSolicitud = buscarRegistroSolicitud(registroSolicitudQuery);
		
		ArrayList<RegistroSolicitudBean> arrRegistroSolicitud = new ArrayList<RegistroSolicitudBean>();
		for(int i=0;i<registroSolicitud.getResults().size();i++){
			RegistroSolicitudBean aux;
			aux = toRegistroSolicitudBean(registroSolicitud.getResults().get(i));
			if(aux != null){
				arrRegistroSolicitud.add(aux);
			}
		}	
		return arrRegistroSolicitud;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RegistroSolicitudManager#buscarRegistroSolicitudByIdSolicitud(es.map.ips.model.RegistroSolicitudQuery)
	 */
	public RegistroSolicitudBean buscarRegistroSolicitudByIdSolicitud(
			RegistroSolicitudQuery registroSolicitudQuery) {
		RegistroSolicitud registro = registroSolicitudDAO.searchUnique(registroSolicitudQuery);
		if(registro!= null){
			return toRegistroSolicitudBean(registro);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RegistroSolicitudManager#buscarRegistroSolicitudByIdSolicitudCrearJustificante(es.map.ips.model.RegistroSolicitudQuery)
	 */
	public RegistroSolicitudBean buscarRegistroSolicitudByIdSolicitudCrearJustificante(
			RegistroSolicitudQuery registroSolicitudQuery) {
		SearchResult<RegistroSolicitud> registro = registroSolicitudDAO.search(registroSolicitudQuery);

		if(registro!= null && registro.getResults()!=null && !registro.getResults().isEmpty() && registro.getResults().get(0)!=null){
			return toRegistroSolicitudBeanRegistro(registro.getResults().get(0));
		}
		return null;
	}
	
	
	/**
	 * To registro solicitud bean registro.
	 *
	 * @param registro el registro
	 * @return el registro solicitud bean
	 */
	private RegistroSolicitudBean toRegistroSolicitudBeanRegistro (RegistroSolicitud registro)
	{
		RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();
		
		registroSolicitudBean.setFechaIntento(registro.getFechaIntento());
		registroSolicitudBean.setFechaRegistro(registro.getFechaRegistro());
		registroSolicitudBean.setId(registro.getId());
		registroSolicitudBean.setLogServicio(registro.getLogServicio());
		registroSolicitudBean.setNumeroRegistro(registro.getNumeroRegistro());
		registroSolicitudBean.setOficinaRegistro(registro.getOficinaRegistro());
		registroSolicitudBean.setResultado(registro.getResultado());
		registroSolicitudBean.setSolicitante(registro.getSolicitante());
		registroSolicitudBean.setSolicitud(registro.getSolicitudComun());
		
		if(registroSolicitudBean.getResultado()!=null 
				&& registroSolicitudBean.getResultado().equals(Constantes.RESULTADO_ER)
				&& registro.getLogServicio()!=null){
			registroSolicitudBean.setDescripcionError(registro.getLogServicio().getDescripcionError());
		}
		
		return registroSolicitudBean;
	}
	
	
	/**
	 * Guarda un Registro de Solicitud.
	 *
	 * @param registroSolicitudBean RegistroSolicitudBean
	 * @return IidRegistroSolicitud Integer
	 */
	public Integer guardarRegistroSolicitud(RegistroSolicitudBean registroSolicitudBean){
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		if(registroSolicitudBean.getIdSolicitud()!=null){
			solicitudQuery.setIdSolicitud(registroSolicitudBean.getIdSolicitud());
			SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
			registroSolicitudBean.setSolicitud(solicitud);
		}
		RegistroSolicitud registroSolicitud =  toRegistroSolicitud(registroSolicitudBean);
		Long idRegistroSolicitud = null;
		try{
			idRegistroSolicitud = registroSolicitudDAO.insert(registroSolicitud);
		}catch(Exception e){
			logger.error("Error RegistroSolicitudManagerImpl- guardando registro solicitud."+ idRegistroSolicitud,e);
		}
		Integer IidRegistroSolicitud = null;
		if(idRegistroSolicitud != null)
		{
			IidRegistroSolicitud = new Integer(idRegistroSolicitud.toString());
		}
		return IidRegistroSolicitud;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RegistroSolicitudManager#almacenarRegistroSolicitud(es.map.ips.model.RegistroSolicitud)
	 */
	public void almacenarRegistroSolicitud(RegistroSolicitud registroSolicitud) {
		registroSolicitudDAO.insert(registroSolicitud);	
	}
	
	/**
	 * Modifica un Registro de Solicitud.
	 *
	 * @param registroSolicitudBean RegistroSolicitudBean
	 * @return void
	 */	
	public void modificarRegistroSolicitud(RegistroSolicitudBean registroSolicitudBean) {
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		if(registroSolicitudBean.getIdSolicitud()!=null){
			solicitudQuery.setIdSolicitud(registroSolicitudBean.getIdSolicitud());
			SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
			registroSolicitudBean.setSolicitud(solicitud);
		}
		RegistroSolicitud registroSolicitud =  toRegistroSolicitud(registroSolicitudBean);
		registroSolicitud.setId(registroSolicitudBean.getId());
		registroSolicitudDAO.update(registroSolicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RegistroSolicitudManager#eliminarRegistroSolicitud(java.lang.Long)
	 */
	public void eliminarRegistroSolicitud(Long id) {
		
		registroSolicitudDAO.delete(id);
	}
	
	/**
	 * To registro solicitud.
	 *
	 * @param registroSolicitudBean el registro solicitud bean
	 * @return el registro solicitud
	 */
	private RegistroSolicitud toRegistroSolicitud (RegistroSolicitudBean registroSolicitudBean)
	{
		RegistroSolicitud registroSolicitud = new RegistroSolicitud();
		
		registroSolicitud.setFechaIntento(registroSolicitudBean.getFechaIntento());
		registroSolicitud.setFechaRegistro(registroSolicitudBean.getFechaRegistro());
		registroSolicitud.setId(registroSolicitudBean.getId());
		registroSolicitud.setLogServicio(registroSolicitudBean.getLogServicio());
		registroSolicitud.setNumeroRegistro(registroSolicitudBean.getNumeroRegistro());
		registroSolicitud.setOficinaRegistro(registroSolicitudBean.getOficinaRegistro());
		registroSolicitud.setResultado(registroSolicitudBean.getResultado());
		registroSolicitud.setSolicitante(Constantes.REGISTRO_SOLICITANTE_CHAR);
		registroSolicitud.setSolicitudComun(registroSolicitudBean.getSolicitud());
		
		return registroSolicitud;
	}
		
	/**
	 * To registro solicitud bean.
	 *
	 * @param registroSolicitud el registro solicitud
	 * @return el registro solicitud bean
	 */
	private RegistroSolicitudBean toRegistroSolicitudBean (RegistroSolicitud registroSolicitud)
	{
		RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();
		
		registroSolicitudBean.setFechaIntento(registroSolicitud.getFechaIntento());
		registroSolicitudBean.setFechaRegistro(registroSolicitud.getFechaRegistro());
		registroSolicitudBean.setId(registroSolicitud.getId());
		registroSolicitudBean.setLogServicio(registroSolicitud.getLogServicio());
		registroSolicitudBean.setNumeroRegistro(registroSolicitud.getNumeroRegistro());
		registroSolicitudBean.setOficinaRegistro(registroSolicitud.getOficinaRegistro());
		registroSolicitudBean.setResultado(registroSolicitud.getResultado());
		registroSolicitudBean.setSolicitante(registroSolicitud.getSolicitante());
		registroSolicitudBean.setSolicitud(registroSolicitud.getSolicitudComun());
		
		if(registroSolicitudBean.getResultado()!=null 
				&& registroSolicitudBean.getResultado().equals(Constantes.RESULTADO_ER)
				&& registroSolicitud.getLogServicio()!=null){
			registroSolicitudBean.setDescripcionError(registroSolicitud.getLogServicio().getDescripcionError());
		}
		
		return registroSolicitudBean;
	}
	
	/**
	 * Guardar registro.
	 *
	 * @param registroSolicitudBean el registro solicitud bean
	 * @return el long
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
	 * @see es.map.ipsg.manager.RegistroSolicitudManager#borrarSolicitudRegistro(java.lang.Long)
	 */
	public void borrarSolicitudRegistro(Long idSolicitud){
		try{
			RegistroSolicitudQuery registroQuery = new RegistroSolicitudQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(idSolicitud);
			registroQuery.setSolicitudComun(solicitudComunQuery);
			// Buscamos todos los registros que contienen ese id de Solicitud
			SearchResult<RegistroSolicitud> solicitudRegistro = registroSolicitudDAO.search(registroQuery);
			if(solicitudRegistro != null && solicitudRegistro.size()>0){
				for (int i = 0; i < solicitudRegistro.size(); i++) {
					// Eliminamos la solicitud
					Long idRegistro = solicitudRegistro.getResults().get(i).getId();
					registroSolicitudDAO.delete(idRegistro);
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de registro_solicitud con ID_SOLICITUD=" + idSolicitud);
			logger.error("Error: ", e);
		}
	}
	

	/**
	 * Obtiene el registro solicitud DAO.
	 *
	 * @return registroSolicitudDAO RegistroSolicitudDAO
	 */
	public RegistroSolicitudDAO getRegistroSolicitudDAO() {
		return registroSolicitudDAO;
	}

	/**
	 * Establece el registro solicitud DAO.
	 *
	 * @param registroSolicitudDAO RegistroSolicitudDAO
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