package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.ConfiguracionErroresQuery;
import es.map.ipsg.bean.ConfiguracionErroresBean;




/**
 * @author acarrroc
 *
 */
public interface ConfiguracionErroresManager {
	public ArrayList<ConfiguracionErroresBean> buscarConfiguracionErrorAll(ConfiguracionErroresQuery configuracionErroresQuery);
	public ArrayList<ConfiguracionErroresBean> buscarConfiguracionErrorCombo(ConfiguracionErroresQuery configuracionErroresQuery);
	int guardarConfiguracionError(ConfiguracionErroresBean configuracionErroresBean);
	ConfiguracionErroresBean buscarConfiguracionError(ConfiguracionErroresQuery configuracionErroresQuery);
	public ConfiguracionErroresBean obtenerConfiguracionError(Integer idConfiguracionErrores);
	public void modificarConfiguracionError(ConfiguracionErroresBean configuracionErroresBean);
	void actualizarConfiguracionError(ConfiguracionErroresBean configuracionErroresBean);
}
