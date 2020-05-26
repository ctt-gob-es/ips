package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TituloOficialDAO;
import es.map.ips.model.TituloOficial;
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.util.Constantes;


/**
 *  Clase que implementa el TituloOficialManager.
 *
 * @author amartinl
 */
public class TituloOficialManagerImpl implements TituloOficialManager {

	/** el titulo oficial DAO. */
	//Variables
	private TituloOficialDAO tituloOficialDAO;
	
	/** el arr titulo oficial. */
	private ArrayList<TituloOficialBean> arrTituloOficial;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(TituloOficialManagerImpl.class);
	
	/**
	 * Buscar titulo oficial combo.
	 *
	 * @param tituloOficialQuery TituloOficialQuery
	 * @return arrTituloOficial ArrayList
	 */
	public ArrayList<TituloOficialBean> buscarTituloOficialCombo(TituloOficialQuery tituloOficialQuery){
		tituloOficialQuery.setEstado(Constantes.TITULOOFICIAL_ESTADO_ACTIVO);
		SearchResult<TituloOficial> tituloOficial = buscarTituloOficial(tituloOficialQuery);
		arrTituloOficial= new ArrayList<TituloOficialBean>();
		for(int i=0;i<tituloOficial.getResults().size();i++){
			TituloOficialBean aux;
			aux = toTituloOficialComboBean(tituloOficial.getResults().get(i));
			if(aux != null){
				arrTituloOficial.add(aux);
			}
		}	
		return arrTituloOficial;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TituloOficialManager#buscarTituloOficialComboVisibilidad(es.map.ips.model.TituloOficialQuery)
	 */
	public ArrayList<TituloOficialBean> buscarTituloOficialComboVisibilidad(TituloOficialQuery tituloOficialQuery){
		tituloOficialQuery.addOrder(TituloOficialQuery.DESCRIPCION, OrderType.ASC);
		tituloOficialQuery.setEstado(Constantes.TITULOOFICIAL_ESTADO_ACTIVO);
		SearchResult<TituloOficial> tituloOficial = buscarTituloOficial(tituloOficialQuery);
		arrTituloOficial= new ArrayList<TituloOficialBean>();
		for(int i=0;i<tituloOficial.getResults().size();i++){
			//Sólo se mostrarán registros visibles	
			if(tituloOficial.getResults().get(i).getVisible() == 'S' || tituloOficial.getResults().get(i).getVisible() == 's')
			{	
				TituloOficialBean aux;
				aux = toTituloOficialComboBean(tituloOficial.getResults().get(i));
				if(aux != null){
					arrTituloOficial.add(aux);
				}
			}	
		}	
		return arrTituloOficial;		
	}
	
	/**
	 * To titulo oficial combo bean.
	 *
	 * @param tituloOficial el titulo oficial
	 * @return el titulo oficial bean
	 */
	private TituloOficialBean toTituloOficialComboBean(TituloOficial tituloOficial) {
		int id = tituloOficial.getId();
		String descripcion = tituloOficial.getDescripcion();
		String codigo = tituloOficial.getCodigo();
		
		TituloOficialBean auxTituloOficial = new TituloOficialBean();
		auxTituloOficial.setId(id);
		auxTituloOficial.setDescripcion(descripcion);;
		auxTituloOficial.setCodigo(codigo);
		
		return auxTituloOficial;
	}
	
	
	/**
	 * To titulo oficial bean.
	 *
	 * @param tituloOficial el titulo oficial
	 * @param numTotal el num total
	 * @return el titulo oficial bean
	 */
	private TituloOficialBean toTituloOficialBean(TituloOficial tituloOficial, int numTotal) {
		int id = tituloOficial.getId();
		String descripcion = tituloOficial.getDescripcion();
		String codigo = tituloOficial.getCodigo();
		String codGeneral = tituloOficial.getCodGeneral();
		
		TituloOficialBean auxTituloOficial = new TituloOficialBean();
		auxTituloOficial.setId(id);
		auxTituloOficial.setDescripcion(descripcion);
		auxTituloOficial.setNumTotal(numTotal);
		auxTituloOficial.setEstado(tituloOficial.getEstado());
		auxTituloOficial.setCodigo(codigo);
		auxTituloOficial.setCodGeneral(codGeneral);
		if(tituloOficial.getVisible() != null && !tituloOficial.getVisible().equals(""))
		{
			if(tituloOficial.getVisible().equals('S'))
			{
				auxTituloOficial.setVisibilidad(true);
			}
			else if(tituloOficial.getVisible().equals('N'))
			{
				auxTituloOficial.setVisibilidad(false);
			}
		}
		else
		{
			auxTituloOficial.setVisibilidad(false);
		}
		return auxTituloOficial;
	}

	/**
	 * Buscar titulo oficial.
	 *
	 * @param tituloOficialQuery el titulo oficial query
	 * @return el search result
	 */
	private SearchResult<TituloOficial> buscarTituloOficial(TituloOficialQuery tituloOficialQuery) {
			ApplicationException.assertNotNull(tituloOficialQuery, "tituloOficialQuery no puede ser null");
		tituloOficialQuery.addOrder("descripcion", OrderType.ASC);
		return tituloOficialDAO.search(tituloOficialQuery);
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TituloOficialManager#buscarTituloOficialAll(es.map.ips.model.TituloOficialQuery)
	 */
	public ArrayList<TituloOficialBean> buscarTituloOficialAll(TituloOficialQuery tituloOficialQuery){		
		SearchResult<TituloOficial> titulo = buscarTituloOficial(tituloOficialQuery);
		Integer numTotal = titulo.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		arrTituloOficial = new ArrayList<TituloOficialBean>();
		for(int i=0;i<titulo.getResults().size();i++){
			TituloOficialBean aux;
			aux = toTituloOficialBean(titulo.getResults().get(i), iNumTotal);
			if(aux != null){
				arrTituloOficial.add(aux);
			}
		}	
		return arrTituloOficial;		
	}

	/**
	 * To titulo oficial.
	 *
	 * @param tituloOficialBean el titulo oficial bean
	 * @return el titulo oficial
	 */
	private TituloOficial toTituloOficial(TituloOficialBean tituloOficialBean) {
		TituloOficial auxTituloOficial = null;
		
		if(tituloOficialBean.getId()!=null && tituloOficialBean.getId()>0)
			auxTituloOficial = tituloOficialDAO.get(tituloOficialBean.getId());
		else
			auxTituloOficial = new TituloOficial();
				
		
		Integer id = tituloOficialBean.getId();
		String descripcion = tituloOficialBean.getDescripcion();
		String codigo = tituloOficialBean.getCodigo();
		
		auxTituloOficial.setId(id);
		auxTituloOficial.setDescripcion(descripcion);
		auxTituloOficial.setEstado(tituloOficialBean.getEstado());
		auxTituloOficial.setCodigo(codigo);
		if( tituloOficialBean.getVisibilidad() != null)
		{
			if( tituloOficialBean.getVisibilidad() == true)
			{
				auxTituloOficial.setVisible('S');
			}	
			else if( tituloOficialBean.getVisibilidad() == false)
			{
				auxTituloOficial.setVisible('N');
			}
		}
		else
		{
			auxTituloOficial.setVisible('N');
		}
			
		
		return auxTituloOficial;
	}
	
	/**
	 * Obtiene el ID y la descripción de un título Oficial pasándole el ID.
	 * @param idTituloOficial  Integer El ID del título que se desea obtener
	 * @return tituloOficial TituloOficial
	 */
	public TituloOficialBean obtenerTituloOficial (Integer idTituloOficial) {
		TituloOficial tituloOficial = tituloOficialDAO.get(idTituloOficial);
		TituloOficialBean tituloOficialBean = this.toTituloOficialBean(tituloOficial,0);
		
		return tituloOficialBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TituloOficialManager#obtenerTituloOficialByCodigo(java.lang.String)
	 */
	public TituloOficialBean obtenerTituloOficialByCodigo(String codigo) {
		
		TituloOficialBean tituloOficialBean = null;
		
		// creacion de la Query
		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery(); 
		tituloOficialQuery.setCodigo(codigo);
		tituloOficialQuery.setVisible(Constantes.VISIBILIDAD_SI);
		
		// aplicacion de la clase TituloOficialDAO para conseguir la entidad TituloOficial segun el codigo anterior
		TituloOficial tituloOficial = tituloOficialDAO.searchUnique(tituloOficialQuery);
		
		// paso a Bean
		if (tituloOficial != null ) {
			tituloOficialBean = this.toTituloOficialBean(tituloOficial,0);
		}
		
		return tituloOficialBean;
	}
	
	/**
	 * Método que se guarda en la tabla Titulo_Oficial el registro insertado.
	 *
	 * @param tituloOficialBean  TituloOficialBean
	 * @return idTitulo Integer El ID del Título que se ha guardado
	 */
	public Integer guardarTituloOficial(TituloOficialBean tituloOficialBean){

		TituloOficial tituloOficial =  toTituloOficial(tituloOficialBean);
		Integer idTitulo = tituloOficialDAO.insert(tituloOficial);
		
		return idTitulo;
	}
	
	/**
	 * Modifica el Título Oficial.
	 *
	 * @param tituloOficialBean el titulo oficial bean
	 */
	public void modificarTitulo (TituloOficialBean  tituloOficialBean)
	{
		TituloOficial titulo =  toTituloOficial(tituloOficialBean);
		tituloOficialDAO.update(titulo);
	}
	
	/**
	 * Obtiene el titulo oficial DAO.
	 *
	 * @return el titulo oficial DAO
	 */
	public TituloOficialDAO getTituloOficialDAO() {
		return tituloOficialDAO;
	}

	/**
	 * Establece el titulo oficial DAO.
	 *
	 * @param tituloOficialDAO el nuevo titulo oficial DAO
	 */
	public void setTituloOficialDAO(TituloOficialDAO tituloOficialDAO) {
		this.tituloOficialDAO = tituloOficialDAO;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}