package es.map.ipsc.manager.descargaDocumento;

import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.dao.DescargaModelo790DAO;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.DescargaModelo790;
import es.map.ipsc.bean.DescargaModeloBean;


/**
 * El Class DescargaDocumentoManagerImpl.
 */
public class DescargaDocumentoManagerImpl implements DescargaDocumentoManager {

	/** el descarga modelo 790 DAO. */
	private DescargaModelo790DAO descargaModelo790DAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargaDocumentoManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.descargaDocumento.DescargaDocumentoManager#insertarDescargaModelo(es.map.ipsc.bean.DescargaModeloBean)
	 */
	public int insertarDescargaModelo(DescargaModeloBean descargaModeloBean) {
		int id= 0;
		try{
			id = descargaModelo790DAO.insert(toDescargaModelo(descargaModeloBean));
		}catch(Exception e){
			logger.error("Error DescargaDomumentoManager - insertardescarga Modelo",e);
			logger.warn(e);
		}
		return id;
	} 

	/**
	 * To descarga modelo.
	 *
	 * @param descargaModeloBean el descarga modelo bean
	 * @return el descarga modelo 790
	 */
	private DescargaModelo790 toDescargaModelo(
			DescargaModeloBean descargaModeloBean) {
		DescargaModelo790 aux = new DescargaModelo790();
		Convocatoria auxConvococatoria = new Convocatoria();
		Date nuFechaAux = new Date();
		aux.setFecha(nuFechaAux);
		if(descargaModeloBean.getIdConvocatoria() != null && !descargaModeloBean.getIdConvocatoria().equals("0")){
			Long auxCod = null;
			try{
				auxCod = Long.parseLong(descargaModeloBean.getIdConvocatoria());
			}catch(Exception e){
				logger.warn(e);
				logger.error("Error DescargaDomumentoManager - descarga Modelo",e);
			}	
			auxConvococatoria.setId(auxCod);
			aux.setConvocatoria(auxConvococatoria);			
		}
		
		return aux;
	}

	/**
	 * Obtiene el descarga modelo 790 DAO.
	 *
	 * @return el descarga modelo 790 DAO
	 */
	public DescargaModelo790DAO getDescargaModelo790DAO() {
		return descargaModelo790DAO;
	}

	/**
	 * Establece el descarga modelo 790 DAO.
	 *
	 * @param descargaModelo790DAO el nuevo descarga modelo 790 DAO
	 */
	public void setDescargaModelo790DAO(DescargaModelo790DAO descargaModelo790DAO) {
		this.descargaModelo790DAO = descargaModelo790DAO;
	}
	
}
