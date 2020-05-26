package es.map.ipsc.manager.solicitudes;

import org.apache.log4j.Logger;

import es.map.ips.dao.ComunidadesDAO;
import es.map.ips.dao.SolicitudCcaaAuxiliarDAO;
import es.map.ips.dao.SolicitudComunAuxiliarDAO;
import es.map.ips.model.Comunidades;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.SolicitudCcaaAuxiliar;
import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ipsc.bean.Formulario790Bean;
import es.map.ipsc.bean.SolicitudCcaaAuxiliarBean;

/**
 * El Class SolicitudCcaaAuxiliarManagerImpl.
 */
public class SolicitudCcaaAuxiliarManagerImpl implements SolicitudCcaaAuxiliarManager{

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudCcaaAuxiliarManagerImpl.class);
	
	/** el solicitud ccaa auxiliar DAO. */
	private SolicitudCcaaAuxiliarDAO solicitudCcaaAuxiliarDAO;
	
	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
	
	/** el comunidades DAO. */
	private ComunidadesDAO comunidadesDAO;

	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudCcaaAuxiliarManager#guardarSolicitudCcaaAuxiliarFormulario790(es.map.ipsc.bean.Formulario790Bean, java.lang.Long)
	 */
	public void guardarSolicitudCcaaAuxiliarFormulario790(Formulario790Bean formulario790Bean, Long idSolicitud){
		
		SolicitudCcaaAuxiliar solicitudCcaaAuxiliar = toSolicitudCcaaAuxiliar(formulario790Bean,idSolicitud);
		solicitudCcaaAuxiliarDAO.insert(solicitudCcaaAuxiliar);
	}
	
	/**
	 * To solicitud ccaa auxiliar.
	 *
	 * @param formulario790Bean el formulario 790 bean
	 * @param idSolicitud el id solicitud
	 * @return el solicitud ccaa auxiliar
	 */
	private SolicitudCcaaAuxiliar toSolicitudCcaaAuxiliar(Formulario790Bean formulario790Bean, Long idSolicitud) {
		
		SolicitudCcaaAuxiliar aux = new SolicitudCcaaAuxiliar();
		SolicitudComunAuxiliarQuery solicitudAuxiliarQuery = new SolicitudComunAuxiliarQuery();
		solicitudAuxiliarQuery.setIdSolicitudAuxiliar(idSolicitud);
		SolicitudComunAuxiliar solicitudComunAuxiliar = solicitudComunAuxiliarDAO.searchUnique(solicitudAuxiliarQuery);

			if(solicitudComunAuxiliar != null){
				aux.setSolicitudComunAuxiliar(solicitudComunAuxiliar);
			}
			
			if(formulario790Bean.getCcaa()!=null && formulario790Bean.getCcaa()!=" "){
				ComunidadesQuery comunidadQuery = new ComunidadesQuery();				
				Integer idComunidad = formulario790Bean.getIdCcaa().intValue();
				comunidadQuery.setIdComunidad(idComunidad);
				Comunidades comunidad = comunidadesDAO.searchUnique(comunidadQuery);
				aux.setComunidades(comunidad);
			}
			
			if(formulario790Bean.getDiscapacidad()!=" " && formulario790Bean.getProvinciaDomicilio()!=null && formulario790Bean.getIdProvinciaDomicilio()!=null && formulario790Bean.getIdProvinciaDomicilio()!=0 ){
				aux.setProvincia(solicitudComunAuxiliar.getProvincia());
				}	
			
			
			if(formulario790Bean.getNumeroTituloFN()!=null && !(formulario790Bean.getNumeroTituloFN().equals(""))){
				aux.setTituloFamnumerosa(formulario790Bean.getNumeroTituloFN());
				}
			
		return aux;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudCcaaAuxiliarManager#obtenerSolicitudCcaaAuxiliar(es.map.ips.model.SolicitudCcaaAuxiliarQuery)
	 */
	public SolicitudCcaaAuxiliarBean obtenerSolicitudCcaaAuxiliar(SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery) {
		try {
			SolicitudCcaaAuxiliar solicitudCcaaAuxiliar  = solicitudCcaaAuxiliarDAO.searchUnique(solicitudCcaaAuxiliarQuery);
			SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean;
			solicitudCcaaAuxiliarBean= toSolicitudCcaaAuxiliarBean(solicitudCcaaAuxiliar);
			return solicitudCcaaAuxiliarBean;
		} catch (Exception e) {
			logger.error("Error obteniendo CC.AA de la exencion",e);
			return null;
		}
	}
	
	/**
	 * To solicitud ccaa auxiliar bean.
	 *
	 * @param solicitudCcaaAuxiliar el solicitud ccaa auxiliar
	 * @return el solicitud ccaa auxiliar bean
	 */
	public SolicitudCcaaAuxiliarBean toSolicitudCcaaAuxiliarBean(SolicitudCcaaAuxiliar solicitudCcaaAuxiliar){
		SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean = new SolicitudCcaaAuxiliarBean();

		if(solicitudCcaaAuxiliar.getIdSolicitudCcaaAuxiliar() != null) {	
			solicitudCcaaAuxiliarBean.setIdSolicitudCcaa(solicitudCcaaAuxiliar.getIdSolicitudCcaaAuxiliar());
		}		
		if(solicitudCcaaAuxiliar.getSolicitudComunAuxiliar() != null) {	
			solicitudCcaaAuxiliarBean.setSolicitud(solicitudCcaaAuxiliar.getSolicitudComunAuxiliar());
		}	
		if(solicitudCcaaAuxiliar.getComunidades() !=null){
			solicitudCcaaAuxiliarBean.setComunidad(solicitudCcaaAuxiliar.getComunidades());
		}
		if(solicitudCcaaAuxiliar.getProvincia() !=null){
			solicitudCcaaAuxiliarBean.setProvincia(solicitudCcaaAuxiliar.getProvincia());
		}
		if(solicitudCcaaAuxiliar.getTituloFamnumerosa() !=null){
			solicitudCcaaAuxiliarBean.setTituloFamnumerosa(solicitudCcaaAuxiliar.getTituloFamnumerosa());
		}				
		return solicitudCcaaAuxiliarBean;
	}
	
	/**
	 * Obtiene el solicitud comun auxiliar DAO.
	 *
	 * @return el solicitud comun auxiliar DAO
	 */
	public SolicitudComunAuxiliarDAO getSolicitudComunAuxiliarDAO() {
		return solicitudComunAuxiliarDAO;
	}

	/**
	 * Establece el solicitud comun auxiliar DAO.
	 *
	 * @param solicitudComunAuxiliarDAO el nuevo solicitud comun auxiliar DAO
	 */
	public void setSolicitudComunAuxiliarDAO(
			SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO) {
		this.solicitudComunAuxiliarDAO = solicitudComunAuxiliarDAO;
	}


	/**
	 * Obtiene el solicitud ccaa auxiliar DAO.
	 *
	 * @return el solicitud ccaa auxiliar DAO
	 */
	public SolicitudCcaaAuxiliarDAO getSolicitudCcaaAuxiliarDAO() {
		return solicitudCcaaAuxiliarDAO;
	}

	/**
	 * Establece el solicitud ccaa auxiliar DAO.
	 *
	 * @param solicitudCcaaAuxiliarDAO el nuevo solicitud ccaa auxiliar DAO
	 */
	public void setSolicitudCcaaAuxiliarDAO(SolicitudCcaaAuxiliarDAO solicitudCcaaAuxiliarDAO) {
		this.solicitudCcaaAuxiliarDAO = solicitudCcaaAuxiliarDAO;
	}

	/**
	 * Obtiene el comunidades DAO.
	 *
	 * @return el comunidades DAO
	 */
	public ComunidadesDAO getComunidadesDAO() {
		return comunidadesDAO;
	}

	/**
	 * Establece el comunidades DAO.
	 *
	 * @param comunidadesDAO el nuevo comunidades DAO
	 */
	public void setComunidadesDAO(ComunidadesDAO comunidadesDAO) {
		this.comunidadesDAO = comunidadesDAO;
	}








}
