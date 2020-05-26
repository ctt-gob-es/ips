package es.map.ipsc.manager.descargaDocumento;

import es.map.ipsc.bean.DescargaModeloBean;


/**
 * El Interface DescargaDocumentoManager.
 */
public interface DescargaDocumentoManager {

	/**
	 * Insertar descarga modelo.
	 *
	 * @param descargaModeloBean el descarga modelo bean
	 * @return el int
	 */
	int insertarDescargaModelo(DescargaModeloBean descargaModeloBean);


	
}
