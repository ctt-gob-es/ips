package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.AvisoQuery;
import es.map.ipsg.bean.AvisoBean;




/**
 * El Interface AvisoManager.
 *
 * @author amartinl
 */
public interface AvisoManager {
	
	/**
	 * Buscar aviso all.
	 *
	 * @param avisoQuery el aviso query
	 * @return el array list
	 */
	public ArrayList<AvisoBean> buscarAvisoAll(AvisoQuery avisoQuery);
	
	/**
	 * Buscar aviso combo.
	 *
	 * @param avisoQuery el aviso query
	 * @return el array list
	 */
	public ArrayList<AvisoBean> buscarAvisoCombo(AvisoQuery avisoQuery);
	
	/**
	 * Guardar aviso.
	 *
	 * @param avisoBean el aviso bean
	 * @return el int
	 */
	int guardarAviso(AvisoBean avisoBean);
	
	/**
	 * Buscar aviso.
	 *
	 * @param avisoQuery el aviso query
	 * @return el aviso bean
	 */
	AvisoBean buscarAviso(AvisoQuery avisoQuery);
	
	/**
	 * Obtener aviso.
	 *
	 * @param idAviso el id aviso
	 * @return el aviso bean
	 */
	public AvisoBean obtenerAviso(Integer idAviso);
	
	/**
	 * Modificar aviso.
	 *
	 * @param avisoBean el aviso bean
	 */
	public void modificarAviso(AvisoBean avisoBean);
	
	/**
	 * Actualizar aviso.
	 *
	 * @param avisoBean el aviso bean
	 */
	void actualizarAviso(AvisoBean avisoBean);
}
