package es.map.ipsc.spring.solicitudes;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.spring.modelo790.GenerarFormulario790Spring;
import es.map.ipsc.utils.SistemaFicheros;

/**
 * El Class EliminarArchivoAjax.
 */
public class EliminarArchivoAjax extends AbstractSecureSpring {
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarArchivoAjax.class);
	
	/**
	 * Crea una nueva eliminar archivo ajax.
	 */
	public EliminarArchivoAjax(){
		try {
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));				
		}
		catch (Exception e) {
			logger.warn(e);
			logger.error("Error eliminar archivo",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String resultado = "ERROR";
		
		try(BufferedReader buffered = new BufferedReader(new InputStreamReader(getRequest().getInputStream()))) {
			String param = buffered.readLine();
			String[] parametros = param.split("=");
			String idDocumento = parametros[1];
			
			CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
			
					if(usuActual != null)
					{
						DocumentoQuery documentoPathQuery = new DocumentoQuery();
						DocumentoBean documentoBuscado = new DocumentoBean();
						
						
				        if(idDocumento!=null && !idDocumento.equals(""))
				        {
				        	documentoPathQuery.setId(Long.parseLong(idDocumento));
				        	documentoBuscado = documentoConvocatoriasManager.obtenerDocumento(documentoPathQuery);
				        	if(documentoBuscado!=null)
				        	{
				        		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
				    			solicitudQuery.setIdSolicitud(documentoBuscado.getIdSolicitud());
				    			SolicitudBean solicitud = solicitudesManager.buscarSolicitudById(solicitudQuery);
				    			if(solicitud.getNif().equals(usuActual.getNif())){
						        	documentoConvocatoriasManager.eliminarDocumentoById(Long.parseLong(idDocumento));
									SistemaFicheros ges= new SistemaFicheros();
									ges.borrarContenido(documentoBuscado);
									resultado = "OK";
				    			}
				        	}
				        }
					 	
					}
				
			}catch(Exception ex) {
				logger.error("Error eliminando archivo",ex);
			}
		return resultado;
				
	}
	

	/**
	 * Obtiene el documento convocatorias manager.
	 *
	 * @return el documento convocatorias manager
	 */
	public DocumentosConvocatoriaManager getDocumentoConvocatoriasManager() {
		return documentoConvocatoriasManager;
	}

	/**
	 * Establece el documento convocatorias manager.
	 *
	 * @param documentoConvocatoriasManager el nuevo documento convocatorias manager
	 */
	public void setDocumentoConvocatoriasManager(
			DocumentosConvocatoriaManager documentoConvocatoriasManager) {
		this.documentoConvocatoriasManager = documentoConvocatoriasManager;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}


}
