package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.ComunidadesQuery;
import es.map.ipsg.bean.ComunidadesBean;

/**
 * El Interface ComunidadesManager.
 */
public interface ComunidadesManager {
	
 /**
  * Buscar comunidades combo.
  *
  * @param comunidadesQuery el comunidades query
  * @return el array list
  */
 public ArrayList<ComunidadesBean> buscarComunidadesCombo (ComunidadesQuery comunidadesQuery);
 
 /**
  * Buscar comunidades id.
  *
  * @param comunidaesQuery el comunidaes query
  * @return el comunidades bean
  */
 public ComunidadesBean buscarComunidadesId(ComunidadesQuery comunidaesQuery);

}
