package es.map.ipsc.manager.grupo;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.GrupoDAO;
import es.map.ips.model.Grupo;
import es.map.ips.model.GrupoQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.GrupoBean;

/**
 * El Class GrupoManagerImpl.
 */
public class GrupoManagerImpl implements GrupoManager {
	
	/** el grupo DAO. */
	private GrupoDAO grupoDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.grupo.GrupoManager#buscarGruposCombo()
	 */
	public ArrayList<GrupoBean> buscarGruposCombo() {
		GrupoQuery grupoQuery = new GrupoQuery();
		grupoQuery.setEstado(Constantes.GRUPO_ESTADO_ACTIVO);
		SearchResult<Grupo> grupo = grupoDAO.search(grupoQuery);
		ArrayList<GrupoBean> arrGrupo= new ArrayList<GrupoBean>();
		for(int i=0;i<grupo.getResults().size();i++){
			// Sólo se mostrarán registros visibles
			if(grupo.getResults().get(i).getVisible() == 'S' || grupo.getResults().get(i).getVisible() == 's')
			{	
				GrupoBean aux;
				aux = toGrupoBean(grupo.getResults().get(i));
				if(aux != null){
					arrGrupo.add(aux);
				}
			}	
		}	
		return arrGrupo;
	}
	
	/**
	 * To grupo bean.
	 *
	 * @param grupo el grupo
	 * @return el grupo bean
	 */
	private GrupoBean toGrupoBean(Grupo grupo) {
		int id = grupo.getId();
		String descripcion = grupo.getDescripcion();
		String codigo = grupo.getCodigo();
		char estado = grupo.getEstado();
		
		
		GrupoBean auxGrupo = new GrupoBean();
		auxGrupo.setId(id);
		auxGrupo.setDescripcion(descripcion);
		auxGrupo.setCodigo(codigo);
		auxGrupo.setEstado(estado);
		
		return auxGrupo;
	}

	/**
	 * Obtiene el grupo DAO.
	 *
	 * @return el grupo DAO
	 */
	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	/**
	 * Establece el grupo DAO.
	 *
	 * @param grupoDAO el nuevo grupo DAO
	 */
	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}
	
	
}
