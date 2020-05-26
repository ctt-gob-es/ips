package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TipoAlertaDAO;
import es.map.ips.model.TipoAlerta;
import es.map.ips.model.TipoAlertaQuery;
import es.map.ipsg.bean.TipoAlertaBean;



/**
 * El Class TipoAlertaManagerImpl.
 */
public class TipoAlertaManagerImpl implements TipoAlertaManager {

	/** el tipo alerta DAO. */
	//Variables
	private TipoAlertaDAO tipoAlertaDAO;
	
	/** el arr tipo alerta. */
	private ArrayList<TipoAlertaBean> arrTipoAlerta;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(TipoAlertaManagerImpl.class);
	
	
	/**
	 * Buscar tipo alertas all.
	 *
	 * @param tipoAlertaQuery el tipo alerta query
	 * @return el list
	 */
	public List<TipoAlertaBean> buscarTipoAlertasAll(
			TipoAlertaQuery tipoAlertaQuery) {
		List<TipoAlertaBean> list = new ArrayList<TipoAlertaBean>();
		SearchResult<TipoAlerta> alertas = tipoAlertaDAO.search(tipoAlertaQuery);
		int numTotal = 0;

		if(alertas != null){
			numTotal = alertas.getNumResults();
		}

		for(TipoAlerta u:alertas.getResults()){
			TipoAlertaBean alertaBean = new TipoAlertaBean();
			try{
				alertaBean = toTipoAlertaBean(u,numTotal);
			}catch(Exception e){
				logger.error("No se ha buscar tipo de alertas",e);	
			}
			list.add(alertaBean);
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoAlertaManager#buscarTipoAlertaCombo(es.map.ips.model.TipoAlertaQuery)
	 */
	public ArrayList<TipoAlertaBean> buscarTipoAlertaCombo(TipoAlertaQuery tipoAlertaQuery){
		tipoAlertaQuery.addOrder(TipoAlertaQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<TipoAlerta> tipoAlerta = buscarTipoAlertas(tipoAlertaQuery);
		
		arrTipoAlerta= new ArrayList<TipoAlertaBean>();
		for(int i=0;i<tipoAlerta.getResults().size();i++){
			TipoAlertaBean aux;
			aux = toTipoAlertaComboBean(tipoAlerta.getResults().get(i));
			if(aux != null){
				arrTipoAlerta.add(aux);
			}
		}	
		return arrTipoAlerta;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoAlertaManager#buscarTipoAlertaAll(es.map.ips.model.TipoAlertaQuery)
	 */
	public ArrayList<TipoAlertaBean> buscarTipoAlertaAll(TipoAlertaQuery tipoAlertaQuery){
		tipoAlertaQuery.addOrder(TipoAlertaQuery.DESCRIPCION, OrderType.ASC);
		System.out.println("Entra en buscarTipoAlertaAll");
		SearchResult<TipoAlerta> tipoAlerta = buscarTipoAlertas(tipoAlertaQuery);
		int numTotal;
		if ( tipoAlerta.getNumResults()==null){
			numTotal=0;
		}else{
			numTotal= tipoAlerta.getNumResults();
		}
		
		arrTipoAlerta = new ArrayList<TipoAlertaBean>();
		for(int i=0;i<tipoAlerta.getResults().size();i++){
			TipoAlertaBean aux;
			aux = toTipoAlertaBean(tipoAlerta.getResults().get(i),numTotal);
			if(aux != null){
				arrTipoAlerta.add(aux);
			}
		}	
		return arrTipoAlerta;		
	}
	
	/**
	 * To tipo alerta bean.
	 *
	 * @param tipoAlertaAux el tipo alerta aux
	 * @param numTotal el num total
	 * @return el tipo alerta bean
	 */
	private TipoAlertaBean toTipoAlertaBean(TipoAlerta tipoAlertaAux, int numTotal) {
		TipoAlertaBean aux = new TipoAlertaBean();
		aux.setId(tipoAlertaAux.getId());
		aux.setDescripcion(tipoAlertaAux.getDescripcion());
		return aux;
	}
	
	/**
	 * To tipo alerta combo bean.
	 *
	 * @param tipoAlertaAux el tipo alerta aux
	 * @return el tipo alerta bean
	 */
	private TipoAlertaBean toTipoAlertaComboBean(TipoAlerta tipoAlertaAux) {
		TipoAlertaBean aux = new TipoAlertaBean();
		aux.setId(tipoAlertaAux.getId());
		aux.setDescripcion(tipoAlertaAux.getDescripcion());
		return aux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoAlertaManager#buscarTipoAlerta(es.map.ips.model.TipoAlertaQuery)
	 */
	public TipoAlertaBean buscarTipoAlerta(TipoAlertaQuery tipoAlertaQuery) {
		TipoAlerta tipoAlerta;
		tipoAlerta = tipoAlertaDAO.searchUnique(tipoAlertaQuery);
		TipoAlertaBean tipoAlertaBean = new TipoAlertaBean();
		try{
			tipoAlertaBean = toTipoAlertaBean(tipoAlerta,0);
		}catch(Exception e){
			logger.error("No se ha podido  buscar tipo de alerta",e);	
		}
		return tipoAlertaBean;
	}

	/**
	 * Buscar tipo alertas.
	 *
	 * @param tipoAlertaQuery el tipo alerta query
	 * @return el search result
	 */
	private SearchResult<TipoAlerta> buscarTipoAlertas (TipoAlertaQuery tipoAlertaQuery) {
		ApplicationException.assertNotNull(tipoAlertaQuery, "alertaQuery no puede ser null");
	
		return tipoAlertaDAO.search(tipoAlertaQuery);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoAlertaManager#obtenerTipoAlerta(java.lang.Integer)
	 */
	public TipoAlertaQuery obtenerTipoAlerta(Integer idTipoAlerta) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtiene el tipo alerta DAO.
	 *
	 * @return the tipoAlertaDAO
	 */
	public TipoAlertaDAO getTipoAlertaDAO() {
		return tipoAlertaDAO;
	}

	/**
	 * Establece el tipo alerta DAO.
	 *
	 * @param tipoAlertaDAO the tipoAlertaDAO to set
	 */
	public void setTipoAlertaDAO(TipoAlertaDAO tipoAlertaDAO) {
		this.tipoAlertaDAO = tipoAlertaDAO;
	}

	/**
	 * Obtiene el arr tipo alerta.
	 *
	 * @return the arrTipoAlerta
	 */
	public ArrayList<TipoAlertaBean> getArrTipoAlerta() {
		return arrTipoAlerta;
	}

	/**
	 * Establece el arr tipo alerta.
	 *
	 * @param arrTipoAlerta the arrTipoAlerta to set
	 */
	public void setArrTipoAlerta(ArrayList<TipoAlertaBean> arrTipoAlerta) {
		this.arrTipoAlerta = arrTipoAlerta;
	}


	}
