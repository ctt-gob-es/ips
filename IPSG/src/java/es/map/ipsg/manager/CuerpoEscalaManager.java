package es.map.ipsg.manager;


import java.util.ArrayList;

import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.EspecialidadBean;




/**
 * El Interface CuerpoEscalaManager.
 */
public interface CuerpoEscalaManager {

	/**
	 * Buscar cuerpos escala combo.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el array list
	 */
	public ArrayList<CuerpoEscalaBean> buscarCuerposEscalaCombo(CuerpoEscalaQuery cuerpoEscalaQuery);
	
	/**
	 * Buscar cuerpos escala combo visibilidad.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el array list
	 */
	public ArrayList<CuerpoEscalaBean> buscarCuerposEscalaComboVisibilidad(CuerpoEscalaQuery cuerpoEscalaQuery);		
	
	/**
	 * Obtener especialidades cuerpo escala.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el array list
	 */
	public ArrayList<EspecialidadBean> obtenerEspecialidadesCuerpoEscala(CuerpoEscalaQuery cuerpoEscalaQuery);
	
	/**
	 * Buscar cuerpo escala all.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el array list
	 */
	public ArrayList<CuerpoEscalaBean> buscarCuerpoEscalaAll(CuerpoEscalaQuery cuerpoEscalaQuery);
	
	/**
	 * Guardar cuerpos escala.
	 *
	 * @param cuerpoEscalaBean el cuerpo escala bean
	 * @return el integer
	 */
	public Integer guardarCuerposEscala(CuerpoEscalaBean cuerpoEscalaBean);
	
	/**
	 * Obtener cuerpo escala.
	 *
	 * @param idCuerpoEscala el id cuerpo escala
	 * @return el cuerpo escala bean
	 */
	public CuerpoEscalaBean obtenerCuerpoEscala(Integer idCuerpoEscala);
	
	/**
	 * Modificar cuerpos escala.
	 *
	 * @param cuerpoEscalaBean el cuerpo escala bean
	 */
	public void modificarCuerposEscala(CuerpoEscalaBean cuerpoEscalaBean);
}
