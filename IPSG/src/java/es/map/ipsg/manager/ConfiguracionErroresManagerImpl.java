package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConfiguracionErroresDAO;
import es.map.ips.model.ConfiguracionErrores;
import es.map.ips.model.ConfiguracionErroresQuery;
import es.map.ipsg.bean.ConfiguracionErroresBean;


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
	
	public List<ConfiguracionErroresBean> buscarConfiguracionErroresAll(ConfiguracionErroresQuery configuracionErroresQuery) {
		List<ConfiguracionErroresBean> list = new ArrayList<ConfiguracionErroresBean>();
		SearchResult<ConfiguracionErrores> configuracionErrores = configuracionErroresDAO.search(configuracionErroresQuery);
		int numTotal = 0;

		if(configuracionErrores != null){
			numTotal = configuracionErrores.getNumResults();
		}

		for(ConfiguracionErrores u:configuracionErrores.getResults()){
			ConfiguracionErroresBean configuracionErroresBean = new ConfiguracionErroresBean();
			try{
				configuracionErroresBean = toConfiguracionErrorBean(u,numTotal);
			}catch(Exception e){
				logger.error("Error ConfiguracionErroresManagerImpl - buscarConfiguracionErroresAll- toConfiguracionErrorBean",e);
			}
			list.add(configuracionErroresBean);
		}
		
		return list;
	}

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
	
	public ArrayList<ConfiguracionErroresBean> buscarConfiguracionErrorAll(ConfiguracionErroresQuery configuracionErroresQuery){	
		System.out.println("Entra en buscarConfiguracionErrorAll");
		SearchResult<ConfiguracionErrores> configuracionErrores = buscarConfiguracionErrores(configuracionErroresQuery);
		int numTotal ;
		if(configuracionErrores.getNumResults()==null){
			
			numTotal=0;;
		}else{
			numTotal=configuracionErrores.getNumResults();
		}
		ArrayList<ConfiguracionErroresBean> arrConfiguracionErrores = new ArrayList<ConfiguracionErroresBean>();
		for(int i=0;i<configuracionErrores.getResults().size();i++){
			ConfiguracionErroresBean aux;
			aux = toConfiguracionErrorBean(configuracionErrores.getResults().get(i),numTotal);
			if(aux != null){
				arrConfiguracionErrores.add(aux);
			}
		}	
		return arrConfiguracionErrores;		
	}
	private ConfiguracionErroresBean toConfiguracionErrorBean(ConfiguracionErrores configuracionErroresAux, int numTotal) {
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
		aux.setNumTotal(numTotal);
		return aux;
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
	
	public ConfiguracionErroresBean buscarConfiguracionError(ConfiguracionErroresQuery configuracionErroresQuery) {
		ConfiguracionErrores configuracionErrores;
		configuracionErrores = configuracionErroresDAO.searchUnique(configuracionErroresQuery);
		ConfiguracionErroresBean configuracionErroresBean = new ConfiguracionErroresBean();
		try{
			configuracionErroresBean = toConfiguracionErrorBean(configuracionErrores,0);
		}catch(Exception e){
			logger.error("Error ConfiguracionErroresManagerImpl - buscarConfiguracionError- toConfiguracionErrorBean",e);
		}
		return configuracionErroresBean;
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

	private ConfiguracionErrores toConfiguracionError(ConfiguracionErroresBean configuracionErroresBean) {
		ConfiguracionErrores configuracionErroresAux = new ConfiguracionErrores();
		if (configuracionErroresBean.getId()!=null){
			configuracionErroresAux.setId(configuracionErroresBean.getId());
		}
		configuracionErroresAux.setDescripcion(configuracionErroresBean.getDescripcion());
		if(configuracionErroresBean.getVisibilidad() != null)
		{
			if(configuracionErroresBean.getVisibilidad() == true)
			{
				configuracionErroresAux.setVisibilidad('S');
			}	
			else if(configuracionErroresBean.getVisibilidad() == false)
			{
				configuracionErroresAux.setVisibilidad('N');
			}
		}
		else
		{
			configuracionErroresAux.setVisibilidad('N');
		}	
		
		return configuracionErroresAux;
	}
	
	public int guardarConfiguracionError(ConfiguracionErroresBean configuracionErroresBean) {
		ConfiguracionErrores configuracionErrores;
		configuracionErrores = toConfiguracionError(configuracionErroresBean);
		int idConfiguracionErrores = configuracionErroresDAO.insert(configuracionErrores);
		
		return idConfiguracionErrores;
	}

	public void actualizarConfiguracionError(ConfiguracionErroresBean configuracionErroresBean) {
		ConfiguracionErrores configuracionErrores;
		configuracionErrores = toConfiguracionError(configuracionErroresBean);
		configuracionErroresDAO.update(configuracionErrores);
	}

	public ConfiguracionErroresBean obtenerConfiguracionError(Integer idConfiguracionErrores){
		ConfiguracionErrores configuracionErrores = configuracionErroresDAO.get(idConfiguracionErrores);
		return toConfiguracionErrorBean(configuracionErrores);
	}
	
	public ConfiguracionErroresBean toConfiguracionErrorBean(ConfiguracionErrores configuracionErrores){
		ConfiguracionErroresBean configuracionErroresBean = new ConfiguracionErroresBean();
		configuracionErroresBean.setId(configuracionErrores.getId());
		configuracionErroresBean.setDescripcion(configuracionErrores.getDescripcion());
		if(configuracionErrores.getVisibilidad() != null && !configuracionErrores.getVisibilidad().equals(""))
		{	
			if(configuracionErrores.getVisibilidad().equals('S'))
			{
				configuracionErroresBean.setVisibilidad(true);
			}	
			else if(configuracionErrores.getVisibilidad().equals('N'))
			{
				configuracionErroresBean.setVisibilidad(false);
			}
		}
		else
		{
			configuracionErroresBean.setVisibilidad(false);
		}		
		
		return configuracionErroresBean;
	}

	public void modificarConfiguracionError(ConfiguracionErroresBean configuracionErroresBean){
		ConfiguracionErrores configuracionErrores = toConfiguracionError(configuracionErroresBean);
		configuracionErroresDAO.update(configuracionErrores);
	}
	
	public static Logger getLogger() {
		return logger;
	}

}