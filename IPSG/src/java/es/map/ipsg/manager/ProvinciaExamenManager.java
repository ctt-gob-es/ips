package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ipsg.bean.ProvinciaExamenBean;

/**
 * El Interface ProvinciaExamenManager.
 */
public interface ProvinciaExamenManager {

/**
 * Buscar provincia examen combo.
 *
 * @param provinciaQuery el provincia query
 * @return el array list
 */
public ArrayList<ProvinciaExamenBean> buscarProvinciaExamenCombo(ProvinciaExamenQuery provinciaQuery);

/**
 * Buscar provincia examen combo visibilidad.
 *
 * @param provinciaQuery el provincia query
 * @return el array list
 */
public ArrayList<ProvinciaExamenBean> buscarProvinciaExamenComboVisibilidad(ProvinciaExamenQuery provinciaQuery);

/**
 * Guardar provincia examen.
 *
 * @param provinciaBean el provincia bean
 * @return el integer
 */
public Integer guardarProvinciaExamen(ProvinciaExamenBean provinciaBean);

/**
 * Modificar provincia examen.
 *
 * @param provinciaBean el provincia bean
 */
public void modificarProvinciaExamen(ProvinciaExamenBean provinciaBean);

/**
 * Buscar provincia examen unique.
 *
 * @param provinciaQuery el provincia query
 * @return el provincia examen bean
 */
public ProvinciaExamenBean buscarProvinciaExamenUnique(ProvinciaExamenQuery provinciaQuery);

}
