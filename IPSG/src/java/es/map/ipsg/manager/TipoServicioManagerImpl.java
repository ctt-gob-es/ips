package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TipoServicioDAO;
import es.map.ips.model.TipoServicio;
import es.map.ips.model.TipoServicioQuery;
import es.map.ipsg.bean.TipoServicioBean;


/**
 *  Clase que implementa el TipoServicioManager.
 *
 * @author amartinl
 */
public class TipoServicioManagerImpl implements TipoServicioManager {

	/** el tipo servicio DAO. */
	//Variables
	private TipoServicioDAO tipoServicioDAO;
	
	/** el arr tipo servicio. */
	private ArrayList<TipoServicioBean> arrTipoServicio;

	/**
	 * Buscar tipo servicio combo.
	 *
	 * @param tipoServicioQuery TipoServicioQuery
	 * @return arrTituloOficial ArrayList
	 */
	public ArrayList<TipoServicioBean> buscarTipoServicioCombo(TipoServicioQuery tipoServicioQuery){
		
		SearchResult<TipoServicio> tipoServicio = buscarTipoServicio(tipoServicioQuery);
		arrTipoServicio= new ArrayList<TipoServicioBean>();
		for(int i=0; i < tipoServicio.getResults().size(); i++)
		{
			TipoServicioBean aux;
			aux = toTipoServicioComboBean(tipoServicio.getResults().get(i));
			if(aux != null){
				arrTipoServicio.add(aux);
			}
		}	
		return arrTipoServicio;		
	}
	
	/**
	 * To tipo servicio combo bean.
	 *
	 * @param tipoServicio el tipo servicio
	 * @return el tipo servicio bean
	 */
	private TipoServicioBean toTipoServicioComboBean(TipoServicio tipoServicio) {
		
		TipoServicioBean auxTipoServicio = new TipoServicioBean();
		auxTipoServicio.setCodigo(tipoServicio.getCodigo());
		auxTipoServicio.setDescripcion(tipoServicio.getDescripcion());
		auxTipoServicio.setId(tipoServicio.getId());
		
		return auxTipoServicio;
	}
	
	/**
	 * Buscar tipo servicio.
	 *
	 * @param tipoServicioQuery el tipo servicio query
	 * @return el search result
	 */
	private SearchResult<TipoServicio> buscarTipoServicio(TipoServicioQuery tipoServicioQuery) {
			ApplicationException.assertNotNull(tipoServicioQuery, "tituloOficialQuery no puede ser null");
		
		return tipoServicioDAO.search(tipoServicioQuery);
	}

	
	/**
	 * Buscar tipo servicio all.
	 *
	 * @param tipoServicioQuery TipoServicioQuery
	 * @return arrTiposolicitud ArrayList
	 */
	public ArrayList<TipoServicioBean> buscarTipoServicioAll(TipoServicioQuery tipoServicioQuery){		
		SearchResult<TipoServicio> tipoServicio = buscarTipoServicio(tipoServicioQuery);
		Integer numTotal = tipoServicio.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		arrTipoServicio = new ArrayList<TipoServicioBean>();
		for(int i=0;i<tipoServicio.getResults().size();i++){
			TipoServicioBean aux;
			aux = toTipoServicioBean(tipoServicio.getResults().get(i), iNumTotal);
			if(aux != null){
				arrTipoServicio.add(aux);
			}
		}	
		return arrTipoServicio;		
	}

	/**
	 * Obtiene el ID y la descripcion de un Tipo de Servicio pasAndole el ID.
	 * @param idTipoServicio  Integer El ID del titulo que se desea obtener
	 * @return tipoServicioBean tipoServicio
	 */
	public TipoServicioBean obtenerTipoServicioBean (Integer idTipoServicio) {
		TipoServicio tipoServicio = tipoServicioDAO.get(idTipoServicio);
		TipoServicioBean tipoServicioBean = this.toTipoServicioBean(tipoServicio,0);
		
		return tipoServicioBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoServicioManager#obtenerTipoServicio(java.lang.Integer)
	 */
	public TipoServicio obtenerTipoServicio (Integer idTipoServicio) {
		TipoServicio tipoServicio = tipoServicioDAO.get(idTipoServicio);;
		
		return tipoServicio;
	}

	/**
	 * Pasa de TipoServicio a TipoServicioBean.
	 *
	 * @param tipoServicio  TipoServicio
	 * @param numTotal int
	 * @return auxTipoServicio TipoServicioBean
	 */
	private TipoServicioBean toTipoServicioBean(TipoServicio tipoServicio, int numTotal) {
	
		TipoServicioBean auxTipoServicio = new TipoServicioBean();
		auxTipoServicio.setCodigo(tipoServicio.getCodigo());
		auxTipoServicio.setDescripcion(tipoServicio.getDescripcion());
		auxTipoServicio.setId(tipoServicio.getId());
		
		return auxTipoServicio;
	}

	/**
	 * Obtiene el tipo servicio DAO.
	 *
	 * @return tipoServicioDAO TipoServicioDAO
	 */
	public TipoServicioDAO getTipoServicioDAO() {
		return tipoServicioDAO;
	}

	/**
	 * Establece el tipo servicio DAO.
	 *
	 * @param tipoServicioDAO TipoServicioDAO
	 */
	public void setTipoServicioDAO(TipoServicioDAO tipoServicioDAO) {
		this.tipoServicioDAO = tipoServicioDAO;
	}
	

}