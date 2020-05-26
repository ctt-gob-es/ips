package es.map.ipsg.manager;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CamposPropiosDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.Modelo;
import es.map.ipsg.bean.CamposPropiosBean;

/**
 * El Class CamposPropiosManagerImpl.
 */
public class CamposPropiosManagerImpl  implements CamposPropiosManager{
	
	/** el campos propios DAO. */
	//Variables
		private CamposPropiosDAO camposPropiosDAO;
		
		/** La constante logger. */
		private static final Logger logger = Logger.getLogger(CamposPropiosManagerImpl.class);
		
		
		/* (non-Javadoc)
		 * @see es.map.ipsg.manager.CamposPropiosManager#buscarCamposPropiosbyModelo(es.map.ips.model.CamposPropiosQuery)
		 */
		public ArrayList<CamposPropiosBean> buscarCamposPropiosbyModelo(
				CamposPropiosQuery camposPropiosQuery) {
			SearchResult<CamposPropios> camposPropios = busquedaCamposPropiosByModelo(camposPropiosQuery);
			int numTotal = 0;
			if(camposPropios.getNumResults() != null){
				numTotal = camposPropios.getNumResults();
			}

			ArrayList<CamposPropiosBean> arrCamposPropios= new ArrayList<CamposPropiosBean>();
			for(int i=0;i<camposPropios.getResults().size();i++){
				CamposPropiosBean aux;
				aux = toCamposPropiosBean(camposPropios.getResults().get(i),numTotal);
				if(aux != null){
					arrCamposPropios.add(aux);
				}
			}	
			return arrCamposPropios;		
		}
		
		/**
		 * Busqueda campos propios by modelo.
		 *
		 * @param camposPropiosQuery el campos propios query
		 * @return el search result
		 */
		private SearchResult<CamposPropios> busquedaCamposPropiosByModelo(CamposPropiosQuery camposPropiosQuery) {
			ApplicationException.assertNotNull(camposPropiosQuery, "camposPropiosQuery no puede ser null");
			return camposPropiosDAO.search(camposPropiosQuery);
		}
		
		
		/**
		 * To campos propios bean.
		 *
		 * @param camposPropios el campos propios
		 * @param numTotal el num total
		 * @return el campos propios bean
		 */
		private CamposPropiosBean toCamposPropiosBean(CamposPropios camposPropios, int numTotal) {					
			CamposPropiosBean auxCamposPropios = new CamposPropiosBean();
			auxCamposPropios.setDescripcion(camposPropios.getDescripcion());
			auxCamposPropios.setIdModelo(Integer.toString(camposPropios.getModelo().getIdModelo()));
			auxCamposPropios.setId(Long.valueOf(Integer.toString(camposPropios.getIdCampo())));
			auxCamposPropios.setTituloCampo(camposPropios.getCampo());
			auxCamposPropios.setCampo(camposPropios.getCampo());
			auxCamposPropios.setDescripcion(camposPropios.getDescripcion());
			auxCamposPropios.setTituloCampo_ca(camposPropios.getCampo_ca());
			auxCamposPropios.setTituloCampo_eu(camposPropios.getCampo_eu());
			auxCamposPropios.setTituloCampo_gl(camposPropios.getCampo_gl());
			auxCamposPropios.setTituloCampo_vl(camposPropios.getCampo_vl());
			return auxCamposPropios;
		}
		
		
		/* (non-Javadoc)
		 * @see es.map.ipsg.manager.CamposPropiosManager#guardarCampoPropio(es.map.ipsg.bean.CamposPropiosBean)
		 */
		@Override
		public Integer guardarCampoPropio(CamposPropiosBean campoPropioBean) {
			CamposPropios campoPropio = toCamposPropios(campoPropioBean);
			Integer idCampoPropio = camposPropiosDAO.insert(campoPropio);		
			return idCampoPropio;
		}
		
		/**
		 * To campos propios.
		 *
		 * @param campoPropioBean el campo propio bean
		 * @return el campos propios
		 */
		public CamposPropios toCamposPropios(CamposPropiosBean campoPropioBean){
			CamposPropios campoPropio = new CamposPropios();
			
			Modelo modelo = new Modelo();
			modelo.setIdModelo(Integer.parseInt(campoPropioBean.getIdModelo()));
			campoPropio.setModelo(modelo);
			campoPropio.setCampo(campoPropioBean.getTituloCampo());
			campoPropio.setCampo_ca(campoPropioBean.getTituloCampo_ca());
			campoPropio.setCampo_eu(campoPropioBean.getTituloCampo_eu());
			campoPropio.setCampo_gl(campoPropioBean.getTituloCampo_gl());
			campoPropio.setCampo_vl(campoPropioBean.getTituloCampo_vl());
			campoPropio.setDescripcion(campoPropioBean.getDescripcion());
			if(campoPropioBean.getId()!=null){
				campoPropio.setIdCampo(Integer.valueOf(String.valueOf(campoPropioBean.getId())));
			}
			return campoPropio;
		}
		
		/* (non-Javadoc)
		 * @see es.map.ipsg.manager.CamposPropiosManager#buscarCamposPropiosbyCampo(es.map.ips.model.CamposPropiosQuery)
		 */
		@Override
		public ArrayList<CamposPropiosBean> buscarCamposPropiosbyCampo(CamposPropiosQuery camposPropiosQuery) {
			SearchResult<CamposPropios> camposPropios = busquedaCamposPropiosByCampo(camposPropiosQuery);
			int numTotal = 0;
			if(camposPropios.getNumResults() != null){
				numTotal = camposPropios.getNumResults();
			}

			ArrayList<CamposPropiosBean> arrCamposPropios= new ArrayList<CamposPropiosBean>();
			for(int i=0;i<camposPropios.getResults().size();i++){
				CamposPropiosBean aux;
				aux = toCamposPropiosBean(camposPropios.getResults().get(i),numTotal);
				if(aux != null){
					arrCamposPropios.add(aux);
				}
			}	
			return arrCamposPropios;		
		}
		
		/**
		 * Busqueda campos propios by campo.
		 *
		 * @param camposPropiosQuery el campos propios query
		 * @return el search result
		 */
		private SearchResult<CamposPropios> busquedaCamposPropiosByCampo(CamposPropiosQuery camposPropiosQuery) {
			ApplicationException.assertNotNull(camposPropiosQuery, "camposPropiosQuery no puede ser null");
			return camposPropiosDAO.search(camposPropiosQuery);
		}
		
		

		/* (non-Javadoc)
		 * @see es.map.ipsg.manager.CamposPropiosManager#modificarCampoPropio(es.map.ipsg.bean.CamposPropiosBean)
		 */
		@Override
		public void modificarCampoPropio(CamposPropiosBean campoPropioBean) {
			CamposPropios camposPropios =  toCamposPropios(campoPropioBean);
			camposPropiosDAO.update(camposPropios);
			
		}
		
		/* (non-Javadoc)
		 * @see es.map.ipsg.manager.CamposPropiosManager#buscarCamposPropiosUnico(es.map.ips.model.CamposPropiosQuery)
		 */
		@Override
		public CamposPropiosBean buscarCamposPropiosUnico(CamposPropiosQuery camposPropiosQuery) {
			CamposPropios campoPropio = camposPropiosDAO.searchUnique(camposPropiosQuery);		
			CamposPropiosBean camposPropiosBean = new CamposPropiosBean();
			try{
				camposPropiosBean = toCamposPropiosBean(campoPropio,0);
			}catch(Exception e){
				logger.warn(e);
				logger.error("ErrorCamposPropiosManagerImpl - buscarCamposPropiosUnico- camposPropios",e);
			}
			return camposPropiosBean;
		}
		
		/* (non-Javadoc)
		 * @see es.map.ipsg.manager.CamposPropiosManager#buscaCamposPropiosUnico(es.map.ips.model.CamposPropiosQuery)
		 */
		@Override
		public CamposPropios buscaCamposPropiosUnico(CamposPropiosQuery camposPropiosQuery) {
			return camposPropiosDAO.searchUnique(camposPropiosQuery);		
		}		
		
		
		/**
		 * Obtiene el campos propios DAO.
		 *
		 * @return el campos propios DAO
		 */
		public CamposPropiosDAO getCamposPropiosDAO() {
			return camposPropiosDAO;
		}

		/**
		 * Establece el campos propios DAO.
		 *
		 * @param camposPropiosDAO el nuevo campos propios DAO
		 */
		public void setCamposPropiosDAO(CamposPropiosDAO camposPropiosDAO) {
			this.camposPropiosDAO = camposPropiosDAO;
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
