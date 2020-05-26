package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;




/**
 * El Interface MotivoReduccionTasaManager.
 */
public interface MotivoReduccionTasaManager {
	
	/**
	 * Buscar motivo reduccion tasa combo.
	 *
	 * @param motivosReduccionTasaQuery el motivos reduccion tasa query
	 * @return el array list
	 */
	public ArrayList<MotivoReduccionTasaBean> buscarMotivoReduccionTasaCombo(MotivoReduccionTasaQuery motivosReduccionTasaQuery);
	
	/**
	 * Buscar motivo reduccion tasa combo visibilidad.
	 *
	 * @param motivoReduccionTasaQuery el motivo reduccion tasa query
	 * @return el array list
	 */
	public ArrayList<MotivoReduccionTasaBean> buscarMotivoReduccionTasaComboVisibilidad(MotivoReduccionTasaQuery motivoReduccionTasaQuery);
	
	/**
	 * Buscar motivo reduccion tasa all.
	 *
	 * @param motivosReduccionTasaQuery el motivos reduccion tasa query
	 * @return el array list
	 */
	public ArrayList<MotivoReduccionTasaBean> buscarMotivoReduccionTasaAll(MotivoReduccionTasaQuery motivosReduccionTasaQuery);
	
	/**
	 * Guardar motivo reduccion tasa.
	 *
	 * @param motivoBean el motivo bean
	 * @return el integer
	 */
	public Integer guardarMotivoReduccionTasa(MotivoReduccionTasaBean motivoBean);
	
	/**
	 * Obtener motivo reduccion tasa.
	 *
	 * @param idMotivo el id motivo
	 * @return el motivo reduccion tasa bean
	 */
	public MotivoReduccionTasaBean obtenerMotivoReduccionTasa(Integer idMotivo);
	
	/**
	 * Modificar motivo reduccion tasa.
	 *
	 * @param motivoBean el motivo bean
	 */
	public void modificarMotivoReduccionTasa(MotivoReduccionTasaBean motivoBean);
	
	/**
	 * Comprueba convocatoria.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param motivoReduccionQuery el motivo reduccion query
	 * @return el array list
	 */
	public ArrayList<ConvocatoriasBean> compruebaConvocatoria (ConvocatoriaQuery convocatoriaQuery, MotivoReduccionTasaQuery motivoReduccionQuery);
	
	/**
	 * To motivo reduccion tasa.
	 *
	 * @param motivoBean el motivo bean
	 * @return el motivo reduccion tasa
	 */
	public MotivoReduccionTasa toMotivoReduccionTasa(MotivoReduccionTasaBean motivoBean);

}
