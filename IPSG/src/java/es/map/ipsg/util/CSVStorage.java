package es.map.ipsg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.Handler;

import org.apache.log4j.Logger;

import es.map.ipsg.util.Constantes;
import es.ws.WSSecurityHeader;
import es.ws.client.csv.storage.CSVDocumentWSService;
import es.ws.client.csv.storage.CSVDocumentWSService_Service;
import es.ws.client.csv.storage.ContenidoInfo;
import es.ws.client.csv.storage.GuardarDocumento;
import es.ws.client.csv.storage.GuardarDocumentoRequest;
import es.ws.client.csv.storage.GuardarDocumentoResponse;

/**
 * El Class CSVStorage.
 */
public class CSVStorage {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CSVStorage.class);
	
	/** La constante STRING_ERROR_ALMACENAMIENO_CSVSTORAGE. */
	private static final String STRING_ERROR_ALMACENAMIENO_CSVSTORAGE = "Error en el almacenamiento en CSVStorage";
	
	/**
	 * Crea una nueva CSV storage.
	 */
	private CSVStorage() {
	    throw new IllegalAccessError("Utility class");
	}
	
	/**
	 * Guardar documento.
	 *
	 * @param codigoCsv el codigo csv
	 * @param byteJutificante el byte jutificante
	 * @param csvProperties el csv properties
	 * @return el string
	 */
	public static String guardarDocumento(String codigoCsv, byte[] byteJutificante, Properties csvProperties) {
		
		//INI - TRA-052
		String resultado = "";
		
		try {
			
			logger.info("Invoking guardarDocumento...");
			
			//Se informa la cabecera con el token de seguridad
			CSVDocumentWSService_Service service = new CSVDocumentWSService_Service(new URL(csvProperties.getProperty("url.ws.csv.storage")));
			CSVDocumentWSService csvService = service.getPort(CSVDocumentWSService.class);
			
			
			BindingProvider bindingProvider = (BindingProvider) csvService;
	        @SuppressWarnings("rawtypes")
			List<Handler> handlerChain = new ArrayList<Handler>();
	        handlerChain.add(new WSSecurityHeader(csvProperties.getProperty("ws.csv_storage.idAplicacion"), csvProperties.getProperty("ws.csv_storage.password")));
	        bindingProvider.getBinding().setHandlerChain(handlerChain);
			
			ContenidoInfo contenidoInfo = new ContenidoInfo();
			contenidoInfo.setTipoMIME(csvProperties.getProperty("ws.csv.mime"));
			contenidoInfo.setContenido(byteJutificante);
			
			GuardarDocumentoRequest guardarDocumentoRequest = new GuardarDocumentoRequest();
			guardarDocumentoRequest.setDir3(csvProperties.getProperty("ws.csv.dir3"));
			guardarDocumentoRequest.setCsv(codigoCsv);
			guardarDocumentoRequest.setContenido(contenidoInfo);
			
			GuardarDocumento parametros =  new GuardarDocumento();
			parametros.setGuardarDocumentoRequest(guardarDocumentoRequest);
			GuardarDocumentoResponse respuesta = csvService.guardarDocumento(parametros);
			resultado = Constantes.RESULTADO_OK;
			
	        logger.info("-- RESPUESTA CSV STORAGE --");
        	logger.info("codigo: " + respuesta.getResponse().getCodigo());
	        logger.info("descripcion: " + respuesta.getResponse().getDescripcion());
	        	        			
		} catch (WebServiceException we) {
			logger.info(STRING_ERROR_ALMACENAMIENO_CSVSTORAGE,we);
		} catch (Exception e) {
			logger.info(STRING_ERROR_ALMACENAMIENO_CSVSTORAGE,e);
		}
		return resultado;
		//FIN - TRA-052
	}
}
