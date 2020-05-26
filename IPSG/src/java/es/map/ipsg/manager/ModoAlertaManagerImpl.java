package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ModoAlertaDAO;
import es.map.ips.model.ModoAlerta;
import es.map.ips.model.ModoAlertaQuery;
import es.map.ipsg.bean.ModoAlertaBean;



/**
 * El Class ModoAlertaManagerImpl.
 */
public class ModoAlertaManagerImpl implements ModoAlertaManager {

	/** el modo alerta DAO. */
	//Variables
	private ModoAlertaDAO modoAlertaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModoAlertaManagerImpl.class);
	
	
	/**
	 * Buscar modo alertas all.
	 *
	 * @param modoAlertaQuery el modo alerta query
	 * @return el list
	 */
	public List<ModoAlertaBean> buscarModoAlertasAll(
			ModoAlertaQuery modoAlertaQuery) {
		List<ModoAlertaBean> list = new ArrayList<ModoAlertaBean>();
		SearchResult<ModoAlerta> alertas = modoAlertaDAO.search(modoAlertaQuery);
		int numTotal = 0;

		if(alertas != null){
			numTotal = alertas.getNumResults();
		}

		for(ModoAlerta u:alertas.getResults()){
			ModoAlertaBean alertaBean = new ModoAlertaBean();
			try{
				alertaBean = toModoAlertaBean(u,numTotal);
			}catch(Exception e){
				logger.error("Error  ModoAlertaManagerImpl - buscarModoAlertasAll.",e);
			}
			list.add(alertaBean);
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModoAlertaManager#buscarModoAlertaCombo(es.map.ips.model.ModoAlertaQuery)
	 */
	public ArrayList<ModoAlertaBean> buscarModoAlertaCombo(ModoAlertaQuery modoAlertaQuery){
		modoAlertaQuery.addOrder(ModoAlertaQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<ModoAlerta> modoAlerta = buscarModoAlertas(modoAlertaQuery);
		
		ArrayList<ModoAlertaBean> arrModoAlerta= new ArrayList<ModoAlertaBean>();
		for(int i=0;i<modoAlerta.getResults().size();i++){
			ModoAlertaBean aux;
			aux = toModoAlertaComboBean(modoAlerta.getResults().get(i));
			if(aux != null){
				arrModoAlerta.add(aux);
			}
		}	
		return arrModoAlerta;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModoAlertaManager#buscarModoAlertaAll(es.map.ips.model.ModoAlertaQuery)
	 */
	public ArrayList<ModoAlertaBean> buscarModoAlertaAll(ModoAlertaQuery modoAlertaQuery){
		modoAlertaQuery.addOrder(ModoAlertaQuery.DESCRIPCION, OrderType.ASC);
		System.out.println("Entra en buscarAlertaAll");
		SearchResult<ModoAlerta> modoAlerta = buscarModoAlertas(modoAlertaQuery);
		int numTotal;
		if ( modoAlerta.getNumResults()==null){
			numTotal=0;
		}else{
			numTotal= modoAlerta.getNumResults();
		}
		
		ArrayList<ModoAlertaBean> arrModoAlerta = new ArrayList<ModoAlertaBean>();
		for(int i=0;i<modoAlerta.getResults().size();i++){
			ModoAlertaBean aux;
			aux = toModoAlertaBean(modoAlerta.getResults().get(i),numTotal);
			if(aux != null){
				arrModoAlerta.add(aux);
			}
		}	
		return arrModoAlerta;		
	}
	
	/**
	 * To modo alerta bean.
	 *
	 * @param modoAlertaAux el modo alerta aux
	 * @param numTotal el num total
	 * @return el modo alerta bean
	 */
	private ModoAlertaBean toModoAlertaBean(ModoAlerta modoAlertaAux, int numTotal) {
		ModoAlertaBean aux = new ModoAlertaBean();
		aux.setId(modoAlertaAux.getId());
		aux.setDescripcion(modoAlertaAux.getDescripcion());
		return aux;
	}
	
	/**
	 * To modo alerta combo bean.
	 *
	 * @param modoAlertaAux el modo alerta aux
	 * @return el modo alerta bean
	 */
	private ModoAlertaBean toModoAlertaComboBean(ModoAlerta modoAlertaAux) {
		ModoAlertaBean aux = new ModoAlertaBean();
		aux.setId(modoAlertaAux.getId());
		aux.setDescripcion(modoAlertaAux.getDescripcion());
		return aux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModoAlertaManager#buscarModoAlerta(es.map.ips.model.ModoAlertaQuery)
	 */
	public ModoAlertaBean buscarModoAlerta(ModoAlertaQuery modoAlertaQuery) {
		ModoAlerta modoAlerta;
		modoAlerta = modoAlertaDAO.searchUnique(modoAlertaQuery);
		ModoAlertaBean modoAlertaBean = new ModoAlertaBean();
		try{
			modoAlertaBean = toModoAlertaBean(modoAlerta,0);
		}catch(Exception e){
			logger.error("Error  ModoAlertaManagerImpl - buscarModoAlerta.",e);
		}
		return modoAlertaBean;
	}

	/**
	 * Buscar modo alertas.
	 *
	 * @param modoAlertaQuery el modo alerta query
	 * @return el search result
	 */
	private SearchResult<ModoAlerta> buscarModoAlertas (ModoAlertaQuery modoAlertaQuery) {
		ApplicationException.assertNotNull(modoAlertaQuery, "alertaQuery no puede ser null");
	
		return modoAlertaDAO.search(modoAlertaQuery);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModoAlertaManager#obtenerModoAlerta(java.lang.Integer)
	 */
	public ModoAlertaQuery obtenerModoAlerta(Integer idModoAlerta) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Obtiene el modo alerta DAO.
	 *
	 * @return the modoAlertaDAO
	 */
	public ModoAlertaDAO getModoAlertaDAO() {
		return modoAlertaDAO;
	}

	/**
	 * Establece el modo alerta DAO.
	 *
	 * @param modoAlertaDAO the modoAlertaDAO to set
	 */
	public void setModoAlertaDAO(ModoAlertaDAO modoAlertaDAO) {
		this.modoAlertaDAO = modoAlertaDAO;
	}


	}
