package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.BatchNivelRentaAuxQuery;
import es.map.ipsg.bean.BatchNivelRentaAuxBean;

public interface BatchNivelRentaAuxManager {

	public ArrayList<BatchNivelRentaAuxBean> buscarPeticionesAll(BatchNivelRentaAuxQuery batchNivelRentaAuxQuery);
	public void actualizarNivelRentaAux(BatchNivelRentaAuxBean batchNivelRentaAux, int i);
	public Integer guardarBatchNivelRentaAux(BatchNivelRentaAuxBean batchNivelRentaAuxBean);
}
