package es.map.ipsc.manager.especialidades;

import java.util.ArrayList;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EspecialidadDAO;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EspecialidadQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.EspecialidadBean;

/**
 * El Class EspecialidadManagerImpl.
 */
public class EspecialidadManagerImpl implements EspecialidadManager {
	
	/** el especialidad DAO. */
	private EspecialidadDAO especialidadDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.especialidades.EspecialidadManager#buscarEspecialidades()
	 */
	public ArrayList<EspecialidadBean> buscarEspecialidades() {
		
		EspecialidadQuery especialidadQuery = new EspecialidadQuery();
		especialidadQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
		especialidadQuery.addOrder(EspecialidadQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<Especialidad> especialidad = especialidadDAO.search(especialidadQuery);
		ArrayList<EspecialidadBean> arrEspecialidad= new ArrayList<EspecialidadBean>();
		for(int i=0;i<especialidad.getResults().size();i++){
			// Sólo se mostrarán los registros visibles
			if(especialidad.getResults().get(i).getVisible() == 'S' || especialidad.getResults().get(i).getVisible() == 's')
			{	
				EspecialidadBean aux;
				aux = toEspecialidadComboBean(especialidad.getResults().get(i));
				if(aux != null){
					arrEspecialidad.add(aux);
				}
			}	
		}	
		return arrEspecialidad;
	}
	
	/**
	 * To especialidad combo bean.
	 *
	 * @param especialidad el especialidad
	 * @return el especialidad bean
	 */
	private EspecialidadBean toEspecialidadComboBean(Especialidad especialidad) {
		int id = especialidad.getId();
		String descripcion = especialidad.getDescripcion();
		String codigo = especialidad.getCodigo();
		
		EspecialidadBean auxEspecialidad = new EspecialidadBean();
		auxEspecialidad.setId(id);
		auxEspecialidad.setDescripcion(descripcion);
		auxEspecialidad.setCodigo(codigo);

		return auxEspecialidad;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.especialidades.EspecialidadManager#buscarEspecialidadId(es.map.ips.model.EspecialidadQuery)
	 */
	public EspecialidadBean buscarEspecialidadId(
			EspecialidadQuery especialidadQuery) {
		Especialidad especialidadAux = especialidadDAO.searchUnique(especialidadQuery);
		EspecialidadBean especialidadBean = toEspecialidadComboBean(especialidadAux);
		if(especialidadBean == null){
			return null;
		}
		return especialidadBean;
	}
	
	/**
	 * Obtiene el especialidad DAO.
	 *
	 * @return el especialidad DAO
	 */
	public EspecialidadDAO getEspecialidadDAO() {
		return especialidadDAO;
	}

	/**
	 * Establece el especialidad DAO.
	 *
	 * @param especialidadDAO el nuevo especialidad DAO
	 */
	public void setEspecialidadDAO(EspecialidadDAO especialidadDAO) {
		this.especialidadDAO = especialidadDAO;
	}

	
	
	
}
