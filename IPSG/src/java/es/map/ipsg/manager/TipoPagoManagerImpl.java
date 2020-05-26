package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TipoPagoDAO;
import es.map.ips.model.TipoPago;
import es.map.ips.model.TipoPagoQuery;
import es.map.ipsg.bean.TipoPagoBean;


/**
 * El Class TipoPagoManagerImpl.
 */
public class TipoPagoManagerImpl implements TipoPagoManager {

	/** el tipo pago DAO. */
	//Variables
	private TipoPagoDAO tipoPagoDAO;
	
	/** el arr tipo pago. */
	private ArrayList<TipoPagoBean> arrTipoPago;
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoPagoManager#buscarTipoPagoCombo(es.map.ips.model.TipoPagoQuery)
	 */
	public ArrayList<TipoPagoBean> buscarTipoPagoCombo(TipoPagoQuery tipoPagoQuery){
		tipoPagoQuery.addOrder(TipoPagoQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<TipoPago> tipoPago = buscarTipoPago(tipoPagoQuery);		
		arrTipoPago= new ArrayList<TipoPagoBean>();
		for(int i=0;i<tipoPago.getResults().size();i++){
			TipoPagoBean aux;
			aux = toTipoPagoComboBean(tipoPago.getResults().get(i));
			if(aux != null){
				arrTipoPago.add(aux);
			}
		}	
		return arrTipoPago;		
	}

	/**
	 * To tipo pago combo bean.
	 *
	 * @param tipoPago el tipo pago
	 * @return el tipo pago bean
	 */
	private TipoPagoBean toTipoPagoComboBean(TipoPago tipoPago) {
		
		TipoPagoBean auxTipoPago = new TipoPagoBean();
		auxTipoPago.setId(tipoPago.getId());				
		auxTipoPago.setCodigo(tipoPago.getCodigo());
		auxTipoPago.setDescripcion(tipoPago.getDescripcion());
		
		return auxTipoPago;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoPagoManager#buscarTipoPagoByTipo(es.map.ips.model.TipoPagoQuery)
	 */
	@Override
	public TipoPago buscarTipoPagoByTipo(TipoPagoQuery tipoPagoQuery) {
		
		TipoPago tipoPago;
		tipoPago = tipoPagoDAO.searchUnique(tipoPagoQuery);
		if(tipoPago == null){
			return null;
		}
		return tipoPago;
	}

	/**
	 * Buscar tipo pago.
	 *
	 * @param tipoPagoQuery el tipo pago query
	 * @return el search result
	 */
	private SearchResult<TipoPago> buscarTipoPago(TipoPagoQuery tipoPagoQuery) {
			ApplicationException.assertNotNull(tipoPagoQuery, "tipoPagoQuery no puede ser null");
		
		return tipoPagoDAO.search(tipoPagoQuery);
	}

	/**
	 * Obtiene el tipo pago DAO.
	 *
	 * @return the tipoAccesoDAO
	 */
	public TipoPagoDAO getTipoPagoDAO() {
		return tipoPagoDAO;
	}

	/**
	 * Establece el tipo pago DAO.
	 *
	 * @param tipoPagoDAO el nuevo tipo pago DAO
	 */
	public void setTipoPagoDAO(TipoPagoDAO tipoPagoDAO) {
		this.tipoPagoDAO = tipoPagoDAO;
	}

}	
	
