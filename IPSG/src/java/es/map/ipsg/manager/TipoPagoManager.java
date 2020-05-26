package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.TipoPago;
import es.map.ips.model.TipoPagoQuery;
import es.map.ipsg.bean.TipoPagoBean;

/**
 * El Interface TipoPagoManager.
 */
public interface TipoPagoManager {

	/**
	 * Buscar tipo pago combo.
	 *
	 * @param tipoPagoQuery el tipo pago query
	 * @return el array list
	 */
	public ArrayList<TipoPagoBean> buscarTipoPagoCombo(TipoPagoQuery tipoPagoQuery);
	
	/**
	 * Buscar tipo pago by tipo.
	 *
	 * @param tipoPagoQuery el tipo pago query
	 * @return el tipo pago
	 */
	public TipoPago buscarTipoPagoByTipo(TipoPagoQuery tipoPagoQuery);
}
