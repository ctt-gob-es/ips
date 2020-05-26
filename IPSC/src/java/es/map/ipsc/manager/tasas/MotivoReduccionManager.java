package es.map.ipsc.manager.tasas;

import java.util.ArrayList;

import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ipsc.bean.MotivoReduccionBean;

/**
 * El Interface MotivoReduccionManager.
 */
public interface MotivoReduccionManager {

	/**
	 * Buscar motivo reduccion combo.
	 *
	 * @return el array list
	 */
	ArrayList<MotivoReduccionBean> buscarMotivoReduccionCombo();

	/**
	 * Buscar motivo reduccion by id.
	 *
	 * @param motivoReduccionQuery el motivo reduccion query
	 * @return el motivo reduccion tasa
	 */
	MotivoReduccionTasa buscarMotivoReduccionById(
			MotivoReduccionTasaQuery motivoReduccionQuery);

	/**
	 * Buscar motivo reduccion bean by id.
	 *
	 * @param motivoReduccionTasaQuery el motivo reduccion tasa query
	 * @return el motivo reduccion bean
	 */
	MotivoReduccionBean buscarMotivoReduccionBeanById(
			MotivoReduccionTasaQuery motivoReduccionTasaQuery);

	/**
	 * Buscar motivo reduccion tasa all.
	 *
	 * @return el array list
	 */
	ArrayList<MotivoReduccionTasa> buscarMotivoReduccionTasaAll();

	
}
