package es.map.ipsc.manager.configuracionErrores;

import java.util.ArrayList;

import es.map.ips.model.ConfiguracionErroresQuery;
import es.map.ipsc.bean.ConfiguracionErroresBean;




/**
 * @author acarrroc
 *
 */
public interface ConfiguracionErroresManager {
	public ArrayList<ConfiguracionErroresBean> buscarConfiguracionErrorCombo(ConfiguracionErroresQuery configuracionErroresQuery);
}
