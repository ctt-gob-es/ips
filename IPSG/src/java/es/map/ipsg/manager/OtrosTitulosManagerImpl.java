package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.OtrosTitulosDAO;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.OtrosTitulos;
import es.map.ips.model.OtrosTitulosQuery;
import es.map.ipsg.bean.OtrosTitulosBean;



/**
 * El Class OtrosTitulosManagerImpl.
 */
public class OtrosTitulosManagerImpl implements OtrosTitulosManager {

	/** el otros titulos DAO. */
	//Variables
	private OtrosTitulosDAO otrosTitulosDAO;
	
	/** el convocatoria DAO. */
	private ConvocatoriaDAO convocatoriaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(OtrosTitulosManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.OtrosTitulosManager#buscarOtrosTitulosCombo(es.map.ips.model.OtrosTitulosQuery)
	 */
	public ArrayList<OtrosTitulosBean> buscarOtrosTitulosCombo(OtrosTitulosQuery otrosTitulosQuery){	
		otrosTitulosQuery.addOrder(OtrosTitulosQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<OtrosTitulos> otrosTitulos = buscarOtrosTitulos(otrosTitulosQuery);
		ArrayList<OtrosTitulosBean> arrOtrosTituloss= new ArrayList<OtrosTitulosBean>();
		for(int i=0;i<otrosTitulos.getResults().size();i++){
			OtrosTitulosBean aux;
			aux = toOtrosTitulosComboBean(otrosTitulos.getResults().get(i));
			if(aux != null){
				arrOtrosTituloss.add(aux);
			}
		}	
		return arrOtrosTituloss;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.OtrosTitulosManager#buscarOtrosTitulosComboVisibilidad(es.map.ips.model.OtrosTitulosQuery)
	 */
	public ArrayList<OtrosTitulosBean> buscarOtrosTitulosComboVisibilidad(OtrosTitulosQuery otrosTitulosQuery){		
		SearchResult<OtrosTitulos> OtrosTitulos = buscarOtrosTitulos(otrosTitulosQuery);
		ArrayList<OtrosTitulosBean> arrOtrosTituloss= new ArrayList<OtrosTitulosBean>();
		for(int i=0;i<OtrosTitulos.getResults().size();i++){
			//Sólo se mostrarán registros visibles	
			if(OtrosTitulos.getResults().get(i).getVisible() == 'S' || OtrosTitulos.getResults().get(i).getVisible() == 's')
			{	
				OtrosTitulosBean aux;
				aux = toOtrosTitulosComboBean(OtrosTitulos.getResults().get(i));
				if(aux != null){
					arrOtrosTituloss.add(aux);
				}
			}	
		}	
		return arrOtrosTituloss;		
	}
	
	
	/**
	 * Buscar otros titulos all.
	 *
	 * @param otrosTitulosQuery el otros titulos query
	 * @return el array list
	 */
	public ArrayList<OtrosTitulosBean> buscarOtrosTitulosAll(OtrosTitulosQuery otrosTitulosQuery){		
		//OtrosTitulosQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
		SearchResult<OtrosTitulos> OtrosTitulos = buscarOtrosTitulos(otrosTitulosQuery);
		int numTotal = 0;
		
		if(OtrosTitulos.getNumResults() != null){
			numTotal = OtrosTitulos.getNumResults();
		}
		
		ArrayList<OtrosTitulosBean> arrOtrosTituloss= new ArrayList<OtrosTitulosBean>();
		
		for(int i=0;i<OtrosTitulos.getResults().size();i++){
			OtrosTitulosBean aux;
			aux = toOtrosTitulosBean(OtrosTitulos.getResults().get(i),numTotal);
			if(aux != null){
				arrOtrosTituloss.add(aux);
			}
		}	
		return arrOtrosTituloss;		
	}
	
	
	/**
	 * Buscar otros titulos.
	 *
	 * @param OtrosTitulosQuery el otros titulos query
	 * @return el search result
	 */
	private SearchResult<OtrosTitulos> buscarOtrosTitulos(OtrosTitulosQuery OtrosTitulosQuery) {
			ApplicationException.assertNotNull(OtrosTitulosQuery, "OtrosTitulosQuery no puede ser null");
		return otrosTitulosDAO.search(OtrosTitulosQuery);
	}

	/**
	 * To otros titulos bean.
	 *
	 * @param otrosTitulos el otros titulos
	 * @param numTotal el num total
	 * @return el otros titulos bean
	 */
	public OtrosTitulosBean toOtrosTitulosBean(OtrosTitulos otrosTitulos, int numTotal) {
		logger.info("en toOtrosTitulosBean()");
		int id = otrosTitulos.getIdOtroTitulo();
		String descripcion = otrosTitulos.getDescripcion();
		
		CentroGestor centroGestor=otrosTitulos.getCentroGestor();
	
		OtrosTitulosBean otrosTitulosBean= new OtrosTitulosBean();
		otrosTitulosBean.setDescripcion(descripcion);
		otrosTitulosBean.setId(id);
		otrosTitulosBean.setDesCentroGestor(centroGestor.getDescripcion());
		if(!StringUtils.isEmpty(otrosTitulos.getVisible())) {
			if(otrosTitulos.getVisible().equals('S'))
			{
				otrosTitulosBean.setVisibilidad(true);
			}	
			else if(otrosTitulos.getVisible().equals('N'))
			{
				otrosTitulosBean.setVisibilidad(false);
			}		
		}
		else
		{
			otrosTitulosBean.setVisibilidad(false);
		}
		otrosTitulosBean.setNumTotal(numTotal);

	    return otrosTitulosBean;
	}
	
	/**
	 * To otros titulos combo bean.
	 *
	 * @param otrosTitulos el otros titulos
	 * @return el otros titulos bean
	 */
	public OtrosTitulosBean toOtrosTitulosComboBean(OtrosTitulos otrosTitulos) {
		int id = otrosTitulos.getIdOtroTitulo();
		String descripcion = otrosTitulos.getDescripcion();	

		OtrosTitulosBean OtrosTitulosBean= new OtrosTitulosBean();
		
		if (otrosTitulos.getCentroGestor() != null && otrosTitulos.getCentroGestor().getIdCentroGestor() != null) {
			OtrosTitulosBean.setCentroGestor(otrosTitulos.getCentroGestor().getIdCentroGestor().toString());
		}
		
		OtrosTitulosBean.setDescripcion(descripcion);
		OtrosTitulosBean.setId(id);

	    return OtrosTitulosBean;
	}

	/**
	 * Obtiene el otros titulos DAO.
	 *
	 * @return el otros titulos DAO
	 */
	public OtrosTitulosDAO getOtrosTitulosDAO() {
		return otrosTitulosDAO;
	}

	/**
	 * Establece el otros titulos DAO.
	 *
	 * @param OtrosTitulosDAO el nuevo otros titulos DAO
	 */
	public void setOtrosTitulosDAO(OtrosTitulosDAO OtrosTitulosDAO) {
		this.otrosTitulosDAO = OtrosTitulosDAO;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.OtrosTitulosManager#guardarOtrosTitulos(es.map.ipsg.bean.OtrosTitulosBean)
	 */
	public Integer guardarOtrosTitulos(OtrosTitulosBean OtrosTitulosBean){
		OtrosTitulos OtrosTitulos= toOtrosTitulos(OtrosTitulosBean);
		Integer idOtrosTitulos = otrosTitulosDAO.insert(OtrosTitulos);
		return idOtrosTitulos;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.OtrosTitulosManager#modificarOtrosTitulos(es.map.ipsg.bean.OtrosTitulosBean)
	 */
	public Boolean modificarOtrosTitulos(OtrosTitulosBean otrosTitulosBean){
		Boolean update = false;
		OtrosTitulos otrosTitulos = toOtrosTitulos(otrosTitulosBean);
		
		try {
			otrosTitulosDAO.update(otrosTitulos);
			update = true;
		} catch(Exception ex) {
			logger.error("[OtrosTitulosManagerImpl] Error al actualizar el otro titulo con id: "+
		((otrosTitulos.getIdOtroTitulo() != null)?otrosTitulos.getIdOtroTitulo().toString():"ID Desconocido"));
		}
		
		return update;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.OtrosTitulosManager#obtenerOtroTitulo(java.lang.Integer)
	 */
	public OtrosTitulosBean obtenerOtroTitulo(Integer idOtrosTitulos){
		OtrosTitulos OtrosTitulos = otrosTitulosDAO.get(idOtrosTitulos);
		return toOtrosTitulosBean(OtrosTitulos);
	}

	/**
	 * To otros titulos.
	 *
	 * @param otrosTitulosBean el otros titulos bean
	 * @return el otros titulos
	 */
	public OtrosTitulos toOtrosTitulos(OtrosTitulosBean otrosTitulosBean){
		OtrosTitulos otrosTitulos = new OtrosTitulos();
		
		otrosTitulos.setIdOtroTitulo(otrosTitulosBean.getId());
		otrosTitulos.setDescripcion(otrosTitulosBean.getDescripcion());
		if(otrosTitulosBean.getVisibilidad() != null)
		{	
			if(otrosTitulosBean.getVisibilidad() == true)
			{	
				otrosTitulos.setVisible('S');
			}
			else if(otrosTitulosBean.getVisibilidad() == false)
			{	
				otrosTitulos.setVisible('N');
			}
		}
		else
		{	
			otrosTitulos.setVisible('N');
		}	
		
		CentroGestor centroGestor = new CentroGestor();
		centroGestor.setIdCentroGestor(otrosTitulosBean.getIdCentroGestor());
		otrosTitulos.setCentroGestor(centroGestor);
				
				
		return otrosTitulos;
	}
	
	/**
	 * To otros titulos bean.
	 *
	 * @param otrosTitulos el otros titulos
	 * @return el otros titulos bean
	 */
	public OtrosTitulosBean toOtrosTitulosBean(OtrosTitulos otrosTitulos){
		OtrosTitulosBean otrosTitulosBean= new OtrosTitulosBean();
		
		otrosTitulosBean.setId(otrosTitulos.getIdOtroTitulo());
		otrosTitulosBean.setDescripcion(otrosTitulos.getDescripcion());
		
		if(otrosTitulos.getCentroGestor()!=null){
			otrosTitulosBean.setIdCentroGestor(otrosTitulos.getCentroGestor().getIdCentroGestor());
			otrosTitulosBean.setDesCentroGestor(otrosTitulos.getCentroGestor().getDescripcion());
		}		
		if(!StringUtils.isEmpty(otrosTitulos.getVisible())) {
			if(otrosTitulos.getVisible().equals('S'))
			{
				otrosTitulosBean.setVisibilidad(true);
			}	
			else if(otrosTitulos.getVisible().equals('N'))
			{
				otrosTitulosBean.setVisibilidad(false);
			}		
		}
		else
		{
			otrosTitulosBean.setVisibilidad(false);
		}
		
		if (otrosTitulos.getConvocatoria() != null && otrosTitulos.getConvocatoria().size() > 0) {
			Set<Convocatoria> convocatoria = new HashSet<Convocatoria>();
			Iterator<Convocatoria> it = otrosTitulos.getConvocatoria().iterator();
			
			while(it.hasNext()){
				logger.info("iterator Convocatorias");
				Convocatoria t=(Convocatoria)it.next();
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setJoin_estadoConvocatoria(true);
				convocatoriaQuery.setId(t.getId());
				convocatoria.add(convocatoriaDAO.searchUnique(convocatoriaQuery));
			}
			
			otrosTitulosBean.setConvocatorias(convocatoria);
		}
		
				
		return otrosTitulosBean;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.OtrosTitulosManager#eliminarOtroTitulo(es.map.ipsg.bean.OtrosTitulosBean)
	 */
	@Override
	public Boolean eliminarOtroTitulo(OtrosTitulosBean otrosTitulosBean) {
		Boolean eliminado = false;
		OtrosTitulos otroTitulo = toOtrosTitulos(otrosTitulosBean);
		try {
			otrosTitulosDAO.delete(otroTitulo.getIdOtroTitulo());
			eliminado = true;
		} catch (Exception e) {
			logger.error("[OtrosTitulosManagerImpl] Error al eliminar el otro titulo con id: "+
		((otroTitulo.getIdOtroTitulo() != null)?otroTitulo.getIdOtroTitulo().toString():"ID Desconocido"));
		}
		return eliminado;
	}

	/**
	 * Obtiene el convocatoria DAO.
	 *
	 * @return el convocatoria DAO
	 */
	public ConvocatoriaDAO getConvocatoriaDAO() {
		return convocatoriaDAO;
	}

	/**
	 * Establece el convocatoria DAO.
	 *
	 * @param convocatoriaDAO el nuevo convocatoria DAO
	 */
	public void setConvocatoriaDAO(ConvocatoriaDAO convocatoriaDAO) {
		this.convocatoriaDAO = convocatoriaDAO;
	}
	
}	
	
