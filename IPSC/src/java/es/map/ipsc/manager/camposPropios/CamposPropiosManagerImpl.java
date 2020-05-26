package es.map.ipsc.manager.camposPropios;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CamposPropiosDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ipsc.bean.CamposPropiosBean;


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
		 * @see es.map.ipsc.manager.camposPropios.CamposPropiosManager#buscarCamposPropiosbyModelo(es.map.ips.model.CamposPropiosQuery)
		 */
		public ArrayList<CamposPropiosBean> buscarCamposPropiosbyModelo(
				CamposPropiosQuery camposPropiosQuery) {
			SearchResult<CamposPropios> camposPropios = busquedaCamposPropiosByModelo(camposPropiosQuery);

			ArrayList<CamposPropiosBean> arrCamposPropios= new ArrayList<CamposPropiosBean>();
			for(int i=0;i<camposPropios.getResults().size();i++){
				CamposPropiosBean aux;
				aux = toCamposPropiosBean(camposPropios.getResults().get(i));
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
			ApplicationException.assertNotNull(camposPropiosQuery, "centroGestorQuery no puede ser null");
			return camposPropiosDAO.search(camposPropiosQuery);
		}
		
		
		/**
		 * To campos propios bean.
		 *
		 * @param camposPropios el campos propios
		 * @return el campos propios bean
		 */
		private CamposPropiosBean toCamposPropiosBean(CamposPropios camposPropios) {					
			CamposPropiosBean auxCamposPropios = new CamposPropiosBean();
			auxCamposPropios.setDescripcion(camposPropios.getDescripcion());
			auxCamposPropios.setIdModelo(Integer.toString(camposPropios.getModelo().getIdModelo()));
			auxCamposPropios.setCampo(camposPropios.getCampo());
			auxCamposPropios.setCampoCa(camposPropios.getCampo_ca());
			auxCamposPropios.setCampoGl(camposPropios.getCampo_gl());
			auxCamposPropios.setCampoVl(camposPropios.getCampo_vl());
			auxCamposPropios.setCampoEu(camposPropios.getCampo_eu());
			auxCamposPropios.setId(Long.valueOf(camposPropios.getIdCampo()));
			// Guardamos la descripción en castellano para formularios 790007 bilingues
			auxCamposPropios.setTituloCampo(camposPropios.getCampo());
			return auxCamposPropios;
		}
				
		/* (non-Javadoc)
		 * @see es.map.ipsc.manager.camposPropios.CamposPropiosManager#buscarCampoPropioBeanById(es.map.ips.model.CamposPropiosQuery)
		 */
		public CamposPropiosBean buscarCampoPropioBeanById(CamposPropiosQuery camposPropiosQuery) {
			CamposPropios campoPropioAux = camposPropiosDAO.searchUnique(camposPropiosQuery);
			if (campoPropioAux!=null){
				CamposPropiosBean campoPropioBean = toCamposPropiosBean(campoPropioAux);
				return campoPropioBean;
			} else {
				return null;
			}
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
