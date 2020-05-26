package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.CorreoElectronico;
import es.map.ips.model.CorreoElectronicoQuery;
import es.map.ipsg.bean.CorreoElectronicoBean;


/**
 * El Interface CorreoElectronicoManager.
 */
public interface CorreoElectronicoManager {

	/**
	 * Guardar correo electronico.
	 *
	 * @param correoElectronicoBean el correo electronico bean
	 * @return el long
	 */
	public Long guardarCorreoElectronico(CorreoElectronicoBean correoElectronicoBean);
	
	/**
	 * Buscar correo electronico.
	 *
	 * @param correoElectronicoQuery el correo electronico query
	 * @return el array list
	 */
	public ArrayList<CorreoElectronicoBean> BuscarCorreoElectronico(CorreoElectronicoQuery correoElectronicoQuery);
	
	/**
	 * To correo electronico bean.
	 *
	 * @param correoElectronico el correo electronico
	 * @return el correo electronico bean
	 */
	public CorreoElectronicoBean toCorreoElectronicoBean(CorreoElectronico correoElectronico);
	
	/**
	 * Obtener correo electronico.
	 *
	 * @param idCorreo el id correo
	 * @return el correo electronico bean
	 */
	public CorreoElectronicoBean obtenerCorreoElectronico (Long idCorreo);
}
