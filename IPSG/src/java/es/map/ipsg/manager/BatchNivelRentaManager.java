package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.BatchNivelRentaQuery;
import es.map.ipsg.bean.BatchNivelRentaBean;

public interface BatchNivelRentaManager {

	public ArrayList<BatchNivelRentaBean> buscarPeticionesAll(BatchNivelRentaQuery batchNivelRentaQuery);
	public void actualizarNivelRenta(BatchNivelRentaBean batchNivelRenta, int i);
	public Integer guardarBatchNivelRenta(BatchNivelRentaBean batchNivelRentaBean);
}
