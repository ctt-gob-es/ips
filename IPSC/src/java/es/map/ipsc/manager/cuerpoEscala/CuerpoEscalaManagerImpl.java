package es.map.ipsc.manager.cuerpoEscala;

import java.util.ArrayList;


import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CuerpoEscalaDAO;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.Grupo;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CuerpoEscalaBean;


/**
 * El Class CuerpoEscalaManagerImpl.
 */
public class CuerpoEscalaManagerImpl implements CuerpoEscalaManager {
	
	/** el cuerpo escala DAO. */
	private CuerpoEscalaDAO cuerpoEscalaDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager#buscarCuerpoEscala()
	 */
	public ArrayList<CuerpoEscalaBean> buscarCuerpoEscala() {
		//Cramos la query
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		// TODO Incidencia: añadir restricción de cuerpo/escala Eliminado, 
		// ya que al eliminar un cuerpo escala, no cambia la visibilidad.
		cuerpoEscalaQuery.setEstado(Constantes.CUERPOESCALA_ESTADO_ACTIVO);
		cuerpoEscalaQuery.addOrder(CuerpoEscalaQuery.DESCRIPCION, OrderType.ASC);
		//Realizamos la busqueda
		SearchResult<CuerpoEscala> lista= cuerpoEscalaDAO.search(cuerpoEscalaQuery);		
		ArrayList<CuerpoEscalaBean> arrCuerpoEscala = new ArrayList<CuerpoEscalaBean>();		
		for(int i=0;i<lista.getResults().size();i++){
			
			// Sólo se mostrarán los registros visibles
			if(lista.getResults().get(i).getVisible() == 'S' || lista.getResults().get(i).getVisible() == 's')
			{
				CuerpoEscalaBean aux;
				//Convierte el model obtenido a bean
				aux = toCuerpoEscalaBean(lista.getResults().get(i));
				if(aux != null){
					//Si la transformacion es correcta se añade a la lista
					arrCuerpoEscala.add(aux);
				}
			}	
		}
		
		return arrCuerpoEscala;
	}

	/**
	 * To cuerpo escala bean.
	 *
	 * @param cuerpoEscala el cuerpo escala
	 * @return el cuerpo escala bean
	 */
	public CuerpoEscalaBean toCuerpoEscalaBean(CuerpoEscala cuerpoEscala) {
		
		long id = cuerpoEscala.getId();
		String descripcion = cuerpoEscala.getDescripcion();
		String codigo = cuerpoEscala.getCodigo();
		char estado = cuerpoEscala.getEstado();
		
		CuerpoEscalaBean cuerpoEscalaBean= new CuerpoEscalaBean();
		cuerpoEscalaBean.setCodigo(codigo);
		cuerpoEscalaBean.setDescripcion(descripcion);
		cuerpoEscalaBean.setId(id);
		cuerpoEscalaBean.setEstado(estado);

	    return cuerpoEscalaBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager#buscarCuerpoEscalaId2(es.map.ips.model.CuerpoEscalaQuery)
	 */
	public CuerpoEscalaBean buscarCuerpoEscalaId2(
			CuerpoEscalaQuery cuerpoEscalaQuery) {
		CuerpoEscala cuerpoEscalaAux = cuerpoEscalaDAO.searchUnique(cuerpoEscalaQuery);
		CuerpoEscalaBean cuerpoEscalaBean = toCuerpoEscalaBean(cuerpoEscalaAux);
		if(cuerpoEscalaBean == null){
			return null;
		}
		return cuerpoEscalaBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager#obtenerGrupoByIdGrupoEscala(es.map.ips.model.CuerpoEscalaQuery)
	 */
	public Grupo obtenerGrupoByIdGrupoEscala(CuerpoEscalaQuery cuerpoEscalaQuery) {
		Grupo grupo = null;
		CuerpoEscala cuerpoEscalaAux = cuerpoEscalaDAO.searchUnique(cuerpoEscalaQuery);
		if(cuerpoEscalaAux == null){
			return null;
		} else {
			if (cuerpoEscalaAux.getGrupo()!=null) {
				grupo = cuerpoEscalaAux.getGrupo();
			}
		}
		return grupo;
	}

	/**
	 * Obtiene el cuerpo escala DAO.
	 *
	 * @return el cuerpo escala DAO
	 */
	public CuerpoEscalaDAO getCuerpoEscalaDAO() {
		return cuerpoEscalaDAO;
	}

	/**
	 * Establece el cuerpo escala DAO.
	 *
	 * @param cuerpoEscalaDAO el nuevo cuerpo escala DAO
	 */
	public void setCuerpoEscalaDAO(CuerpoEscalaDAO cuerpoEscalaDAO) {
		this.cuerpoEscalaDAO = cuerpoEscalaDAO;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager#buscarCuerpoEscalaId(es.map.ips.model.CuerpoEscalaQuery)
	 */
	public SearchResult<CuerpoEscala> buscarCuerpoEscalaId(
			CuerpoEscalaQuery cuerpoEscalaQuery) {
		SearchResult<CuerpoEscala> cuerpoEscalaAux = cuerpoEscalaDAO.search(cuerpoEscalaQuery);
		if(cuerpoEscalaAux != null){
			return cuerpoEscalaAux;
		}
		return null;
	}
	

}	
	
