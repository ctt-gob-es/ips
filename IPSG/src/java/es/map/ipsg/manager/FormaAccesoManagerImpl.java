package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.FormaAccesoDAO;
import es.map.ips.model.FormaAcceso;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.util.Constantes;

/**
 * El Class FormaAccesoManagerImpl.
 */
public class FormaAccesoManagerImpl implements FormaAccesoManager {

	/** el forma acceso DAO. */
	//Variables
	private FormaAccesoDAO formaAccesoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(FormaAccesoManagerImpl.class);
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.FormaAccesoManager#buscarFormaAccesoAll(es.map.ips.model.FormaAccesoQuery)
	 */
	public ArrayList<FormaAccesoBean> buscarFormaAccesoAll(FormaAccesoQuery formaAccesoQuery){		
		formaAccesoQuery.setEstado(Constantes.FORMAACCESO_ESTADO_ACTIVO);
		SearchResult<FormaAcceso> formaAcceso = buscarFormaAcceso(formaAccesoQuery);
		int numTotal = 0;
		if (formaAcceso.getNumResults()!=null){
			numTotal=formaAcceso.getNumResults();
		}
		ArrayList<FormaAccesoBean> arrFormaAcceso= new ArrayList<FormaAccesoBean>();
		for(int i=0;i<formaAcceso.getResults().size();i++){
			FormaAccesoBean aux;
			aux = toFormaAccesoBean(formaAcceso.getResults().get(i),numTotal);
			if(aux != null){
				arrFormaAcceso.add(aux);
			}
		}	
		return arrFormaAcceso;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.FormaAccesoManager#buscarFormaAccesoCombo(es.map.ips.model.FormaAccesoQuery)
	 */
	public ArrayList<FormaAccesoBean> buscarFormaAccesoCombo(FormaAccesoQuery formaAccesoQuery){
		formaAccesoQuery.setEstado(Constantes.FORMAACCESO_ESTADO_ACTIVO);
		formaAccesoQuery.setVisible(Constantes.VISIBILIDAD_SI);
		formaAccesoQuery.addOrder(FormaAccesoQuery.DESCRIPCION,OrderType.ASC);
		SearchResult<FormaAcceso> formaAcceso = buscarFormaAcceso(formaAccesoQuery);
		 ArrayList<FormaAccesoBean> arrFormaAcceso= new ArrayList<FormaAccesoBean>();
		for(int i=0;i<formaAcceso.getResults().size();i++){
			FormaAccesoBean aux;
			aux = toFormaAccesoBean(formaAcceso.getResults().get(i));
			if(aux != null){
				arrFormaAcceso.add(aux);
			}
		}	
			
		return arrFormaAcceso;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.FormaAccesoManager#buscarFormaAccesoComboVisibilidad(es.map.ips.model.FormaAccesoQuery)
	 */
	public ArrayList<FormaAccesoBean> buscarFormaAccesoComboVisibilidad(FormaAccesoQuery formaAccesoQuery){
		formaAccesoQuery.setEstado(Constantes.FORMAACCESO_ESTADO_ACTIVO);
		formaAccesoQuery.addOrder(FormaAccesoQuery.DESCRIPCION,OrderType.ASC);
		SearchResult<FormaAcceso> formaAcceso = buscarFormaAcceso(formaAccesoQuery);
		ArrayList<FormaAccesoBean> arrFormaAcceso= new ArrayList<FormaAccesoBean>();
		for(int i=0;i<formaAcceso.getResults().size();i++){
		//Sólo se mostrarán registros visibles	
			if(formaAcceso.getResults().get(i).getVisible() == 'S' || formaAcceso.getResults().get(i).getVisible() == 's' )
			{	
				FormaAccesoBean aux;
				aux = toFormaAccesoBean(formaAcceso.getResults().get(i));
				if(aux != null){
					arrFormaAcceso.add(aux);
				}
			}	
		}	
		return arrFormaAcceso;		
	}
	
	/**
	 * To forma acceso bean.
	 *
	 * @param formaAcceso el forma acceso
	 * @return el forma acceso bean
	 */
	private FormaAccesoBean toFormaAccesoBean(FormaAcceso formaAcceso) {		
		
		FormaAccesoBean auxFormaAcceso = new FormaAccesoBean();
		auxFormaAcceso.setId(formaAcceso.getId());
		auxFormaAcceso.setCodigo(formaAcceso.getCodigo());
		auxFormaAcceso.setDescripcion(formaAcceso.getDescripcion());	
		if(formaAcceso.getVisible() != null && !formaAcceso.getVisible().equals(""))
		{	
			if(formaAcceso.getVisible().equals('S'))
			{
				auxFormaAcceso.setVisibilidad(true);
			}	
			else if(formaAcceso.getVisible().equals('N'))
			{
				auxFormaAcceso.setVisibilidad(false);
			}
		}
		else
		{
			auxFormaAcceso.setVisibilidad(false);
		}
		auxFormaAcceso.setTipoAcceso(formaAcceso.getTipoAcceso());
		
		return auxFormaAcceso;
	}
	
	/**
	 * To forma acceso bean.
	 *
	 * @param formaAcceso el forma acceso
	 * @param numTotal el num total
	 * @return el forma acceso bean
	 */
	private FormaAccesoBean toFormaAccesoBean(FormaAcceso formaAcceso, int numTotal) {
		
		FormaAccesoBean auxFormaAcceso = new FormaAccesoBean();
		auxFormaAcceso.setId(formaAcceso.getId());
		auxFormaAcceso.setCodigo(formaAcceso.getCodigo());
		auxFormaAcceso.setDescripcion(formaAcceso.getDescripcion());
		auxFormaAcceso.setDesTipoAcceso(formaAcceso.getTipoAcceso().getCodigo().toString());
		auxFormaAcceso.setNumTotal(numTotal);
		if(formaAcceso.getVisible() != null && !formaAcceso.getVisible().equals(""))
		{	
			if(formaAcceso.getVisible().equals('S'))
			{
				auxFormaAcceso.setVisibilidad(true);
			}	
			else if(formaAcceso.getVisible().equals('N'))
			{
				auxFormaAcceso.setVisibilidad(false);
			}
		}
		else
		{
			auxFormaAcceso.setVisibilidad(false);
		}
			
		
		return auxFormaAcceso;
	}

	/**
	 * Buscar forma acceso.
	 *
	 * @param formaAccesoQuery el forma acceso query
	 * @return el search result
	 */
	private SearchResult<FormaAcceso> buscarFormaAcceso(FormaAccesoQuery formaAccesoQuery) {
			ApplicationException.assertNotNull(formaAccesoQuery, "convocatoriaQuery no puede ser null");
		
		return formaAccesoDAO.search(formaAccesoQuery);
	}
	
	/**
	 * Metodo que almacenará un objeto Forma Acceso.
	 *
	 * @param formaAccesoBean el forma acceso bean
	 * @return un entero con el id del objeto almacenado
	 */
	public Integer guardarFormaAcceso(FormaAccesoBean formaAccesoBean){
		FormaAcceso formaAcceso = toFormaAcceso(formaAccesoBean);
		Integer idCentroGestor = formaAccesoDAO.insert(formaAcceso);		
		return idCentroGestor;
	}
	
	/**
	 * Metodo para pasar de FormaAccesoBean a FormaAcceso.
	 *
	 * @param formaAccesoBean el forma acceso bean
	 * @return un objeto FormaAcceso
	 */
	public FormaAcceso toFormaAcceso(FormaAccesoBean formaAccesoBean){
		FormaAcceso formaAcceso = new FormaAcceso();
		
		formaAcceso.setId(formaAccesoBean.getId());				
		formaAcceso.setDescripcion(formaAccesoBean.getDescripcion());		
		formaAcceso.setCodigo(formaAccesoBean.getCodigo());		
		formaAcceso.setTipoAcceso(formaAccesoBean.getTipoAcceso());		
		formaAcceso.setEstado(formaAccesoBean.getEstado());
		if(formaAccesoBean.getVisibilidad() != null)
		{
			if(formaAccesoBean.getVisibilidad() == true)
			{
				formaAcceso.setVisible('S');
			}	
			else if(formaAccesoBean.getVisibilidad() == false)
			{
				formaAcceso.setVisible('N');
			}
		}
		else
		{
			formaAcceso.setVisible('N');
		}
		
		return formaAcceso;
	}
	
	/**
	 * Metodo que Obtiene una forma de acceso por su id.
	 *
	 * @param idFormaAcceso el id forma acceso
	 * @return el forma acceso bean
	 */
	public FormaAccesoBean obtenerFormaAcceso(Integer idFormaAcceso){
		FormaAccesoQuery formAcceso = new FormaAccesoQuery();
		formAcceso.setId(idFormaAcceso);
		formAcceso.setJoin_tipoAcceso(true);
		FormaAcceso formaAcceso = formaAccesoDAO.searchUnique(formAcceso);
		return toFormaAccesoBean(formaAcceso);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.FormaAccesoManager#modificarFormaAcceso(es.map.ipsg.bean.FormaAccesoBean)
	 */
	public void modificarFormaAcceso (FormaAccesoBean  formaAccesoBean)
	{
		FormaAcceso formaAcceso =  toFormaAcceso(formaAccesoBean);
		formaAccesoDAO.update(formaAcceso);
	}

	/**
	 * Obtiene el forma acceso DAO.
	 *
	 * @return el forma acceso DAO
	 */
	public FormaAccesoDAO getFormaAccesoDAO() {
		return formaAccesoDAO;
	}

	/**
	 * Establece el forma acceso DAO.
	 *
	 * @param formaAccesoDAO el nuevo forma acceso DAO
	 */
	public void setFormaAccesoDAO(FormaAccesoDAO formaAccesoDAO) {
		this.formaAccesoDAO = formaAccesoDAO;
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