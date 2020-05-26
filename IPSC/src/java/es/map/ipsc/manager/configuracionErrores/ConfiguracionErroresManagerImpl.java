package es.map.ipsc.manager.configuracionErrores;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConfiguracionErroresDAO;
import es.map.ips.model.ConfiguracionErrores;
import es.map.ips.model.ConfiguracionErroresQuery;
import es.map.ipsc.bean.ConfiguracionErroresBean;


/**
 *  Clase que implementa el ConfiguracionErroresManager
 * 
 * @author acarrroc
 *
 */
public class ConfiguracionErroresManagerImpl implements ConfiguracionErroresManager {

	//Variables
	private ConfiguracionErroresDAO configuracionErroresDAO;
	private static final Logger logger = Logger.getLogger(ConfiguracionErroresManagerImpl.class);
	

	public ArrayList<ConfiguracionErroresBean> buscarConfiguracionErrorCombo(ConfiguracionErroresQuery configuracionErroresQuery){
		
		SearchResult<ConfiguracionErrores> configuracionErrores = buscarConfiguracionErrores(configuracionErroresQuery);
		ArrayList<ConfiguracionErroresBean> arrConfiguracionErrores = new ArrayList<ConfiguracionErroresBean>();
		for(int i=0;i<configuracionErrores.getResults().size();i++){
			ConfiguracionErroresBean aux;
			aux = toConfiguracionErrorComboBean(configuracionErrores.getResults().get(i));
			if(aux != null){
				arrConfiguracionErrores.add(aux);
			}
		}	
		return arrConfiguracionErrores;		
	}
	
	private ConfiguracionErroresBean toConfiguracionErrorComboBean(ConfiguracionErrores configuracionErroresAux) {
		ConfiguracionErroresBean aux = new ConfiguracionErroresBean();
		aux.setId(configuracionErroresAux.getId());
		aux.setDescripcion(configuracionErroresAux.getDescripcion());
		if(configuracionErroresAux.getVisibilidad() != null && !configuracionErroresAux.getVisibilidad().equals(""))
		{	
			if(configuracionErroresAux.getVisibilidad().equals('S'))
			{
				aux.setVisibilidad(true);
			}	
			else if(configuracionErroresAux.getVisibilidad().equals('N'))
			{
				aux.setVisibilidad(false);
			}
		}
		else
		{
			aux.setVisibilidad(false);
		}
		
		return aux;
	}
	
	
	private SearchResult<ConfiguracionErrores> buscarConfiguracionErrores (ConfiguracionErroresQuery configuracionErroresQuery) {
		ApplicationException.assertNotNull(configuracionErroresQuery, "configuracionErroresQuery no puede ser null");
	
		return configuracionErroresDAO.search(configuracionErroresQuery);
	}

	public ConfiguracionErroresDAO getConfiguracionErroresDAO() {
		return configuracionErroresDAO;
	}

	public void setConfiguracionErroresDAO(ConfiguracionErroresDAO configuracionErroresDAO) {
		this.configuracionErroresDAO = configuracionErroresDAO;
	}

	
	public static Logger getLogger() {
		return logger;
	}

}