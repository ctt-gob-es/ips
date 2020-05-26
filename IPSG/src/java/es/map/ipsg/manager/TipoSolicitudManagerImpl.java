package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TipoSolicitudDAO;
import es.map.ips.model.TipoSolicitud;
import es.map.ips.model.TipoSolicitudQuery;
import es.map.ipsg.bean.TipoSolicitudBean;


/**
 *  Clase que implementa el TipoSolicitudManager.
 *
 * @author amartinl
 */
public class TipoSolicitudManagerImpl implements TipoSolicitudManager {

	/** el tipo solicitud DAO. */
	//Variables
	private TipoSolicitudDAO tipoSolicitudDAO;
	
	/** el arr tipo solicitud. */
	private ArrayList<TipoSolicitudBean> arrTipoSolicitud;

	/**
	 * Buscar tipo solicitud combo.
	 *
	 * @param tipoSolicitudQuery TipoSolicitudQuery
	 * @return arrTituloOficial ArrayList
	 */
	public ArrayList<TipoSolicitudBean> buscarTipoSolicitudCombo(TipoSolicitudQuery tipoSolicitudQuery){
		
		SearchResult<TipoSolicitud> tipoSolicitud = buscarTipoSolicitud(tipoSolicitudQuery);
		arrTipoSolicitud= new ArrayList<TipoSolicitudBean>();
		for(int i=0; i < tipoSolicitud.getResults().size(); i++)
		{
			TipoSolicitudBean aux;
			aux = toTipoSolicitudComboBean(tipoSolicitud.getResults().get(i));
			if(aux != null){
				arrTipoSolicitud.add(aux);
			}
		}	
		return arrTipoSolicitud;		
	}
	
	/**
	 * To tipo solicitud combo bean.
	 *
	 * @param tipoSolicitud el tipo solicitud
	 * @return el tipo solicitud bean
	 */
	private TipoSolicitudBean toTipoSolicitudComboBean(TipoSolicitud tipoSolicitud) {
		
		TipoSolicitudBean auxTipoSolicitud = new TipoSolicitudBean();
		auxTipoSolicitud.setCodigo(tipoSolicitud.getCodigo());
		auxTipoSolicitud.setDescripcion(tipoSolicitud.getDescripcion());
		auxTipoSolicitud.setId(tipoSolicitud.getId());
		
		return auxTipoSolicitud;
	}
	
	/**
	 * Buscar tipo solicitud.
	 *
	 * @param tipoSolicitudQuery el tipo solicitud query
	 * @return el search result
	 */
	private SearchResult<TipoSolicitud> buscarTipoSolicitud(TipoSolicitudQuery tipoSolicitudQuery) {
			ApplicationException.assertNotNull(tipoSolicitudQuery, "tituloOficialQuery no puede ser null");
		
		return tipoSolicitudDAO.search(tipoSolicitudQuery);
	}

	
	/**
	 * Buscar tipo solicitud all.
	 *
	 * @param tipoSolicitudQuery TipoSolicitudQuery
	 * @return arrTiposolicitud ArrayList
	 */
	public ArrayList<TipoSolicitudBean> buscarTipoSolicitudAll(TipoSolicitudQuery tipoSolicitudQuery){		
		SearchResult<TipoSolicitud> tipoSolicitud = buscarTipoSolicitud(tipoSolicitudQuery);
		Integer numTotal = tipoSolicitud.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		arrTipoSolicitud = new ArrayList<TipoSolicitudBean>();
		for(int i=0;i<tipoSolicitud.getResults().size();i++){
			TipoSolicitudBean aux;
			aux = toTipoSolicitudBean(tipoSolicitud.getResults().get(i), iNumTotal);
			if(aux != null){
				arrTipoSolicitud.add(aux);
			}
		}	
		return arrTipoSolicitud;		
	}

	/**
	 * Obtiene el ID y la descripción de un Tipo de Solicitud pasándole el ID.
	 * @param idTipoSolicitud  Integer El ID del título que se desea obtener
	 * @return tipoSolicitudBean tipoSolicitud
	 */
	public TipoSolicitudBean obtenerTipoSolicitud(Integer idTipoSolicitud) {
		TipoSolicitud tipoSolicitud = tipoSolicitudDAO.get(idTipoSolicitud);
		TipoSolicitudBean tipoSolicitudBean = this.toTipoSolicitudBean(tipoSolicitud,0);
		
		return tipoSolicitudBean;
	}
	
	/**
	 * Obtiene el ID y la descripción de un Tipo de Solicitud pasándole el ID.
	 * @param idTipoSolicitud  Integer El ID del título que se desea obtener
	 * @return tipoSolicitudBean tipoSolicitud
	 */
	public TipoSolicitud conseguirTipoSolicitud(Integer idTipoSolicitud) {
		TipoSolicitud tipoSolicitud = tipoSolicitudDAO.get(idTipoSolicitud);
		return tipoSolicitud;
	}
	

	/**
	 * Pasa de TipoSolicitud a TipoSolicitudBean.
	 *
	 * @param tipoSolicitud  TipoSolicitud
	 * @param numTotal int
	 * @return auxTipoSolicitud TipoSolicitudBean
	 */
	private TipoSolicitudBean toTipoSolicitudBean(TipoSolicitud tipoSolicitud, int numTotal) {
	
		TipoSolicitudBean auxTipoSolicitud = new TipoSolicitudBean();
		auxTipoSolicitud.setCodigo(tipoSolicitud.getCodigo());
		auxTipoSolicitud.setDescripcion(tipoSolicitud.getDescripcion());
		auxTipoSolicitud.setId(tipoSolicitud.getId());
		
		return auxTipoSolicitud;
	}

	/**
	 * Obtiene el tipo solicitud DAO.
	 *
	 * @return tipoSolicitudDAO TipoSolicitudDAO
	 */
	public TipoSolicitudDAO getTipoSolicitudDAO() {
		return tipoSolicitudDAO;
	}

	/**
	 * Establece el tipo solicitud DAO.
	 *
	 * @param tipoSolicitudDAO TipoSolicitudDAO
	 */
	public void setTipoSolicitudDAO(TipoSolicitudDAO tipoSolicitudDAO) {
		this.tipoSolicitudDAO = tipoSolicitudDAO;
	}
}