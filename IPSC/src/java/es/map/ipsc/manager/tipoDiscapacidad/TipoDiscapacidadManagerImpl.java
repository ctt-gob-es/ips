package es.map.ipsc.manager.tipoDiscapacidad;

import java.util.ArrayList;
import java.util.ResourceBundle;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TipoDiscapacidadDAO;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ipsc.bean.TipoDiscapacidadBean;


public class TipoDiscapacidadManagerImpl implements TipoDiscapacidadManager {
	private TipoDiscapacidadDAO tipoDiscapacidadDAO;
	

	
	
	public ArrayList<TipoDiscapacidadBean> buscarTiposDiscapacidad() {
		TipoDiscapacidadQuery tipoDiscapacidadQuery = new TipoDiscapacidadQuery();
		SearchResult<TipoDiscapacidad> tiposDiscapacidad = tipoDiscapacidadDAO.search(tipoDiscapacidadQuery);
		ArrayList<TipoDiscapacidadBean> arrayTipoDiscapacidad = new ArrayList<TipoDiscapacidadBean>();
		
		for(int i=0;i<tiposDiscapacidad.getResults().size();i++){
			TipoDiscapacidadBean aux;
			aux = toTipoDiscapacidadBean(tiposDiscapacidad.getResults().get(i));
			if(aux != null ){
				arrayTipoDiscapacidad.add(aux);
			}
		}	
		return arrayTipoDiscapacidad;
		
		
	}
	
	public ArrayList<TipoDiscapacidadBean> buscarTiposDiscapacidad(TipoDiscapacidadQuery tipoDiscapacidadQuery) {
		SearchResult<TipoDiscapacidad> tiposDiscapacidad = tipoDiscapacidadDAO.search(tipoDiscapacidadQuery);
		ArrayList<TipoDiscapacidadBean> arrayTipoDiscapacidad = new ArrayList<TipoDiscapacidadBean>();
		
		for(int i=0;i<tiposDiscapacidad.getResults().size();i++){
			TipoDiscapacidadBean aux;
			aux = toTipoDiscapacidadBean(tiposDiscapacidad.getResults().get(i));
			if(aux != null ){
				arrayTipoDiscapacidad.add(aux);
			}
		}	
		return arrayTipoDiscapacidad;
	}
	

	public TipoDiscapacidadBean toTipoDiscapacidadBean(TipoDiscapacidad tipoDiscapacidad) {
		TipoDiscapacidadBean tipoDiscapacidadBean = new TipoDiscapacidadBean();
		tipoDiscapacidadBean.setDescripcion(tipoDiscapacidad.getDescripcion());
		tipoDiscapacidadBean.setId(tipoDiscapacidad.getId());
		tipoDiscapacidadBean.setCodigo(tipoDiscapacidad.getCodigo());
		
		return tipoDiscapacidadBean;
	}
	
	public TipoDiscapacidadBean buscarTipoDiscapacidadId(
			TipoDiscapacidadQuery tipoDiscapacidadQuery) {
		TipoDiscapacidad tipoDiscapacidadAux = tipoDiscapacidadDAO.searchUnique(tipoDiscapacidadQuery);
		TipoDiscapacidadBean tipoDiscapacidadBean = toTipoDiscapacidadBean(tipoDiscapacidadAux);
		if(tipoDiscapacidadBean == null){
			return null;
		}
		return tipoDiscapacidadBean;
	}

	public TipoDiscapacidadDAO getTipoDiscapacidadDAO() {
		return tipoDiscapacidadDAO;
	}

	/**
	 * Establece el tipo discapacidad DAO.
	 *
	 * @param tipoDiscapacidadDAO el nuevo tipo discapacidad DAO
	 */
	public void setTipoDiscapacidadDAO(TipoDiscapacidadDAO tipoDiscapacidadDAO) {
		this.tipoDiscapacidadDAO = tipoDiscapacidadDAO;
	}

	
	
	
}
