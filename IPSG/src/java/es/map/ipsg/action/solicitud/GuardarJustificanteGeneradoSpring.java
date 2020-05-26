package es.map.ipsg.action.solicitud;

import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.GestionarJustificanteForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class GuardarJustificanteGeneradoSpring.
 */
public class GuardarJustificanteGeneradoSpring extends AbstractSpring {

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosJustificanteSolicitudSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERRORGENERICO. */
	private static final String STRING_ERRORGENERICO = "errorGenerico";
	
	/** el properties. */
	private static Properties properties;
	
	
	/**
	 * Crea una nueva guardar justificante generado spring.
	 */
	public GuardarJustificanteGeneradoSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			properties = (Properties) getBean("propertiesBean");

		} catch (Exception e) {
			logger.warn(e);
			logger.error(" Error GuardarJustificanteGeneradoSpring:",e);
		}

	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("GuardarJustificanteGeneradoSpring -start");
			
		logger.info("Entra en el Action");
	
		GestionarJustificanteForm formulario = (GestionarJustificanteForm)form;
		DocumentoBean docBean =formulario.getDocBean();
		
		if(formulario.getTipoJustificante().equals("solicitud")){	
			ArrayList<DocumentoBean> listaDocumentosBean = new ArrayList<DocumentoBean>();
			listaDocumentosBean.add(docBean);
			
			if(documentoManager.copiarFicheros(listaDocumentosBean, properties.getProperty("sistemaficheros.url.final"))){	
				logger.info("Documento insertado correctamente");
				return "success";
			}else{
				logger.error("No se ha podido insertar el documento PDF");
				return STRING_ERRORGENERICO;
			}	
		}
		else if(formulario.getTipoJustificante().equals("Xml")){
			try{
	        	Long id = documentoManager.guardarDocumentoXML(docBean);
	        	if(id != null){	
	        		logger.info("Tupla insertada en Documento XML:"+id);
					return "success";
				}else{
					logger.error("No se ha podido insertar el documento XML");
					return STRING_ERRORGENERICO;
				}	
	        }catch(Exception e){
	        	logger.error(" Error GuardarJustificanteGeneradoSpring - tipo justificante: "+ formulario.getTipoJustificante() ,e);
	        	this.getRequest().setAttribute("errorDescripcion",RESOURCE_BUNDLE.getString("certificados.error.gestorDocumental"));
	 			return STRING_ERRORGENERICO;
	        }
		}else{
			return STRING_ERRORGENERICO;
		}	
	}	
	
	
	/**
	 * Obtiene el documento manager.
	 *
	 * @return el documento manager
	 */
	public DocumentoManager getDocumentoManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param documentoManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentoManager documentoManager) {
		this.documentoManager = documentoManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

}
