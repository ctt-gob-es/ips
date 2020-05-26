package es.map.ipsg.util;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;

/**
 * El Class AlfrescoUtils.
 */
public class AlfrescoUtils {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AlfrescoUtils.class);

	/** La constante BUNDLE_NAME. */
	private static final String BUNDLE_NAME = "gestDocumental";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	/** el host. */
	private static String host = "";
	
	/** el user. */
	private static String user = "";
	
	/** el pass. */
	private static String pass = "";
	
	/** el aplicacion. */
	private static String aplicacion = "";
	
	/** el home. */
	private static String home = "";
	
	/** el session. */
	private static Session session = null;
	
	/** La constante ALFRESCO_CHARSET. */
	public static final String ALFRESCO_CHARSET = "UTF-8";
	
	/** La constante ALFRESCO_MIME_TYPE. */
	public static final String ALFRESCO_MIME_TYPE = "application/octet-stream";
	
	/** La constante CONNECT_TIMEOUT. */
	public static final String CONNECT_TIMEOUT = "600000";	
	
	/** La constante ALFRESCO_FOLDER. */
	public static final String ALFRESCO_FOLDER = "cmis:folder";
	
	/** La constante ALFRESCO_DOCUMENT. */
	public static final String ALFRESCO_DOCUMENT = "cmis:document";
	
	/** La constante ALFRESCO_INICIO_ID. */
	private static final String ALFRESCO_INICIO_ID = "workspace://SpacesStore/";
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_GETIDFOLDERARE_END. */
	private static final String STRING_GETIDFOLDERARE_END = "getIdFolderARE - end";
	
	
	/**
	 * Crea una nueva alfresco utils.
	 *
	 * @throws Exception el exception
	 */
	public AlfrescoUtils() throws Exception {
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		host = properties.getProperty("gestDocumental.url");
		user = properties.getProperty("gestDocumental.user");
		pass = properties.getProperty("gestDocumental.password");
		aplicacion = properties.getProperty("gestDocumental.aplicacion");
		home = properties.getProperty("gestDocumental.aplicacion.home");
		
		try {
			//Crear la session solo cuando esta sea null
			if (AlfrescoUtils.session == null){
				AlfrescoUtils.session = this.createSession();
				if(AlfrescoUtils.session != null){					
					//Crear la carpeta principal ARE si no existe en gestor documental
					CmisObject object = this.getObjectByPath("/" + home + "/" + aplicacion);
					if (object==null){
						CmisObject objectHome = this.getObjectByPath("/" + home);
						String idHome = null;
						
						//Si existe la carpeta "User Homes" mirar si existe la carpeta ARE, si no existe se crea en caso de tener permisos
						if (objectHome!=null){
							idHome = objectHome.getId();
							String idARE = this.getIdFolderARE(idHome);
							if (idARE==null || "".equals(idARE)){
								idARE = this.crearObjeto(idHome, aplicacion, AlfrescoUtils.ALFRESCO_FOLDER, null, null);
							}
						}
					}
					
					AlfrescoUtils.session.clear();
				}
			}

		} catch (Exception e) {
			logger.error("AlfrescoUtils - Error:",e);
			throw e;
		}
		
	}

	/**
	 * Crea el session.
	 *
	 * @return el session
	 */
	public Session createSession() {
		
		// default factory implementation
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();
		
		// user credentials
		parameter.put(SessionParameter.USER, user);
		parameter.put(SessionParameter.PASSWORD, pass);
		

		if (host!=null && !"".equals(host) && !host.endsWith("/")){
			host = host + "/";
		}
		
		// Specify the connection settings
		parameter.put(SessionParameter.ATOMPUB_URL, host + "service/cmis"); //ORIGINAL
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// Set the alfresco object factory
		parameter.put(SessionParameter.OBJECT_FACTORY_CLASS, "org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");  //ORIGINAL
		
		try{
			Session session = null;
			logger.info("createSession - obteniendo repositorios...");
			Repository repository = factory.getRepositories(parameter).get(0);
			logger.info("createSession - repositorios obtenidos...");
			logger.info("createSession - creando sesion...");
			session = repository.createSession();
			AlfrescoUtils.session = session;
			logger.info("createSession - sesion creada correctamente");
		}
		catch(Exception e){
			logger.error("Session"+ session, e);
		}
		
		return session;
	}
	
	/**
	 * Eliminar contenido carpeta.
	 *
	 * @param folderId el folder id
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 */
	/*
	 * Elimina el contenido de una carpeta dado su identificador
	 */
	public void eliminarContenidoCarpeta(String folderId) throws IOException {
		logger.info("eliminarContenidoCarpeta - start");
		
		if (AlfrescoUtils.session==null){
			AlfrescoUtils.session = this.createSession();
		}
		
		if (folderId==null || "".equals(folderId)){
			logger.info("eliminarContenidoCarpeta - Error: el id de la carpeta no puede ser vacio");
			logger.info("eliminarContenidoCarpeta - end");
			return;
		}
		/* TODO QUITAR???? */
		if (!folderId.startsWith(AlfrescoUtils.ALFRESCO_INICIO_ID)){
			folderId = AlfrescoUtils.ALFRESCO_INICIO_ID + folderId;
		}
		/***FIN QUITAR?****/
		CmisObject object = AlfrescoUtils.session.getObject(AlfrescoUtils.session.createObjectId(folderId));
		Folder folder = (Folder) object;
		
		OperationContext operationContext = AlfrescoUtils.session.createOperationContext();
		ItemIterable<CmisObject> children = folder.getChildren(operationContext);
		folder.refresh();
		
		Iterator<CmisObject> pageItems = children.iterator();
		while(pageItems.hasNext()) {
		    CmisObject item = pageItems.next();
		    item.delete(true);

		}
		
		logger.info("eliminarContenidoCarpeta - end");
	}
	
	/**
	 * Leer carpeta.
	 *
	 * @param folderId el folder id
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 */
	/*
	 * Leer el contenido de una carpeta dado su identificador
	 */
	public void leerCarpeta(String folderId) throws IOException {
		logger.info("leerCarpeta - start");
		
		if (AlfrescoUtils.session==null){
			AlfrescoUtils.session = this.createSession();
		}
		
		if (folderId==null || "".equals(folderId)){
			logger.info("leerCarpeta - Error: el id de la carpeta no puede ser vacio");
			logger.info("leerCarpeta - end");
			return;
		}
		
		if (!folderId.startsWith(AlfrescoUtils.ALFRESCO_INICIO_ID)){
			folderId = AlfrescoUtils.ALFRESCO_INICIO_ID + folderId;
		}
		
		CmisObject object2 = AlfrescoUtils.session.getObjectByPath(ALFRESCO_INICIO_ID+"/"+folderId);
		CmisObject object = AlfrescoUtils.session.getObject(AlfrescoUtils.session.createObjectId(folderId));
		Folder folder = (Folder) object;
		
		OperationContext operationContext = AlfrescoUtils.session.createOperationContext();
		ItemIterable<CmisObject> children = folder.getChildren(operationContext);

		Iterator<CmisObject> pageItems = children.iterator();
		while(pageItems.hasNext()) {
		    CmisObject item = pageItems.next();
		    if (item instanceof Document){
		    	Document doc = (Document)item;
		    	logger.info("Name: " + doc.getName());
		    	logger.info("ID: " + doc.getId());
		    	logger.info("Datos: " + this.convertStreamToString(doc.getContentStream().getStream()));		    	
		    } 
		    
		    if (item instanceof Folder){
		    	Folder folder2 = (Folder)item;
		    	logger.info("Name: " + folder2.getName());
		    	logger.info("ID: " + folder2.getId());
		    }
		}
		
		logger.info("leerCarpeta - end");
	}
	
	/**
	 * Obtiene el id folder ARE.
	 *
	 * @param idFolderRaiz el id folder raiz
	 * @return el id folder ARE
	 */
	/*
	 * Comprobar si existe la carpeta principal ARE
	 */
	public String getIdFolderARE(String idFolderRaiz){
		logger.info("getIdFolderARE - start");
		
		if (AlfrescoUtils.session==null){
			AlfrescoUtils.session = this.createSession();
		}
		
		if (idFolderRaiz==null || "".equals(idFolderRaiz)){
			logger.info("getIdFolderARE - Error: el ID de la carpeta padre no puede ser vacio");
			logger.info(STRING_GETIDFOLDERARE_END);
			return null;
		}
		
		if (!idFolderRaiz.startsWith(AlfrescoUtils.ALFRESCO_INICIO_ID)){
			idFolderRaiz = AlfrescoUtils.ALFRESCO_INICIO_ID + idFolderRaiz;
		}
		
		CmisObject object = AlfrescoUtils.session.getObject(AlfrescoUtils.session.createObjectId(idFolderRaiz));
		Folder folder = (Folder) object;
		
		OperationContext operationContext = AlfrescoUtils.session.createOperationContext();
		ItemIterable<CmisObject> children = folder.getChildren(operationContext);

		logger.info("getIdFolderARE - buscando carpeta " + aplicacion + "...");
		
		Iterator<CmisObject> pageItems = children.iterator();
		while(pageItems.hasNext()) {
		    CmisObject item = pageItems.next();
		    if (item instanceof Folder){
		    	Folder folder2 = (Folder)item;
		    	
		    	if (folder2!=null && folder2.getName().equals(aplicacion)){
		    		logger.info("getIdFolderARE - carpeta " + aplicacion + " encontrada");
		    		logger.info(STRING_GETIDFOLDERARE_END);
		    		return folder2.getId();
		    	}
		    }
		}
		
		logger.info("getIdFolderARE - carpeta " + aplicacion + " no encontrada");
		logger.info(STRING_GETIDFOLDERARE_END);
		return null;
	}
	
	
	/**
	 * Crear objeto.
	 *
	 * @param padreId el padre id
	 * @param name el name
	 * @param objectTypeId el object type id
	 * @param content el content
	 * @param mimeType el mime type
	 * @return el string
	 */
	/*
	 * Crear un objeto: carpeta o documento.
	 * 
	 * Depende del parametro "objectTypeId".
	 */
	public String crearObjeto(String padreId, String name, String objectTypeId, byte[] content, String mimeType){
		logger.info("crearObjeto - start");
		
		if (AlfrescoUtils.session==null){
			AlfrescoUtils.session = this.createSession();
		}
		
		if (padreId==null || "".equals(padreId)){
			logger.info("crearObjeto - Error: el ID de la carpeta padre no puede ser vacio");
			logger.info("crearObjeto - end");
			return null;
		}
		
		if (!padreId.startsWith(AlfrescoUtils.ALFRESCO_INICIO_ID)){
			padreId = AlfrescoUtils.ALFRESCO_INICIO_ID + padreId;
		}
		
		// properties (minimal set: name and object type id)
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.OBJECT_TYPE_ID, objectTypeId);
		properties.put(PropertyIds.NAME, name);
		
		// create the object
		ObjectId object = null;
		ObjectId objetoPadre = AlfrescoUtils.session.createObjectId(padreId); 
		
		//Crear una carpeta
		if (objectTypeId!=null && objectTypeId.equals(AlfrescoUtils.ALFRESCO_FOLDER)){
			object = AlfrescoUtils.session.createFolder(properties, objetoPadre);
			
			logger.info("crearObjeto - carpeta creada: " + name);
		}
		//Crear un documento
		else if (objectTypeId!=null && objectTypeId.equals(AlfrescoUtils.ALFRESCO_DOCUMENT)){
			InputStream stream = new ByteArrayInputStream(content);
			ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), mimeType, stream);
			
			object = AlfrescoUtils.session.createDocument(properties, objetoPadre, contentStream, VersioningState.MAJOR);
			logger.info("crearObjeto - documento creado: " + name);
		}
		
		String idNew = null;
		if (object!=null){
			idNew = object.getId();
		}
		
		logger.info("crearObjeto - end");
		return idNew;
	}
	
	/**
	 * Obtiene el object.
	 *
	 * @param id el id
	 * @return el object
	 */
	/*
	 * Obtener un objeto a partir de su identificador
	 * 
	 * Devuelve null si no existe
	 */
	public CmisObject getObject(String id){
		logger.info("getObject - start");
		
		if (AlfrescoUtils.session==null){
			AlfrescoUtils.session = this.createSession();
		}
		
		if (id==null || "".equals(id)){
			logger.info("getObject - Error: ID vacio");
			logger.info("getObject - end");
			return null;
		}
		
		if (!id.startsWith(AlfrescoUtils.ALFRESCO_INICIO_ID)){
			id = AlfrescoUtils.ALFRESCO_INICIO_ID + id;
		}
		
		CmisObject result = null;
		
		try{
			result = session.getObject(session.createObjectId(id));
		}catch (CmisObjectNotFoundException e) {
			logger.info("getObject - el objeto no existe",e);
			result = null;
		}
		
		logger.info("getObject - end");
		return result;
	}
	
	
	/**
	 * Obtiene el object by path.
	 *
	 * @param path el path
	 * @return el object by path
	 */
	/*
	 * Obtener un objeto a traves de la ruta en la que se encuentran.
	 * Ej.: /ARE/prueba/prueba.txt
	 * 
	 * Devuelve null si no existe
	 */
	public CmisObject getObjectByPath(String path){
		
		if (AlfrescoUtils.session==null){
			AlfrescoUtils.session = this.createSession();
		}
		
		if (path==null || "".equals(path)){
			logger.info("getObjectByPath - Error: ruta vacia");
			logger.info("getObjectByPath - end");
			return null;
		}
		
		CmisObject result = null;
		
		try{
			result = session.getObjectByPath(path);
		}catch (CmisObjectNotFoundException e) {
			logger.info("getObjectByPath - el objeto no existe",e);
			result = null;
		}
		
		return result;
	}
	
	
	/**
	 * Convert stream to string.
	 *
	 * @param is el is
	 * @return el string
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 */
	/*
	 * Convierte a String los datos de un InputSream mediante el charset especificado
	 */
	public String convertStreamToString(InputStream is) throws IOException {
		
		
		if (is != null) {
			String cadena = IOUtils.toString(is);
			
			return cadena;
			
		} else {
			
			return null;
		}
	}
	
	/**
	 * Convert stream to byte.
	 *
	 * @param is el is
	 * @return el byte[]
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 */
	/*
	 * Convierte a byte[] los datos de un InputSream mediante el charset especificado
	 */
	public byte[] convertStreamToByte(InputStream is) throws IOException {
		
		if (is != null) {
			
			byte[] bytes = IOUtils.toByteArray(is);
		    
		    return bytes;
		} else {
			
			logger.info("convertStreamToByte - Error: el buffer es null");
			logger.info("convertStreamToByte - end");
			return null;
		}
	}
	
	/**
	 * Prueba Rdto.
	 *
	 * @param is el is
	 * @param rutaFinal el ruta final
	 * @return el int
	 * @throws IOException Señala que se ha producido una excepción de I/O.
	 */
	public int convertStreamToFile(InputStream is, String rutaFinal) throws IOException {
		
		if (is != null) {
			
			// TODO VICTOR
			FileOutputStream fOutPutStream = new FileOutputStream(rutaFinal);
			
		    return IOUtils.copy(is, fOutPutStream);
		} else {
			
			logger.info("convertStreamToFile - Error: el buffer es null");
			logger.info("convertStreamToFile - end");
			return -1;
		}
	}

	/**
	 * Obtiene el host.
	 *
	 * @return el host
	 */
	public static String getHost() {
		return host;
	}

	/**
	 * Establece el host.
	 *
	 * @param host el nuevo host
	 */
	public static void setHost(String host) {
		AlfrescoUtils.host = host;
	}

	/**
	 * Obtiene el user.
	 *
	 * @return el user
	 */
	public static String getUser() {
		return user;
	}

	/**
	 * Establece el user.
	 *
	 * @param user el nuevo user
	 */
	public static void setUser(String user) {
		AlfrescoUtils.user = user;
	}

	/**
	 * Obtiene el password.
	 *
	 * @return el password
	 */
	public static String getPassword() {
		return pass;
	}

	/**
	 * Establece el password.
	 *
	 * @param password el nuevo password
	 */
	public static void setPassword(String password) {
		AlfrescoUtils.pass = password;
	}

	/**
	 * Obtiene el session.
	 *
	 * @return el session
	 */
	public static Session getSession() {
		return session;
	}

	/**
	 * Establece el session.
	 *
	 * @param session el nuevo session
	 */
	public static void setSession(Session session) {
		AlfrescoUtils.session = session;
	}

	/**
	 * Obtiene el aplicacion.
	 *
	 * @return el aplicacion
	 */
	public static String getAplicacion() {
		return aplicacion;
	}

	/**
	 * Establece el aplicacion.
	 *
	 * @param aplicacion el nuevo aplicacion
	 */
	public static void setAplicacion(String aplicacion) {
		AlfrescoUtils.aplicacion = aplicacion;
	}

	/**
	 * Obtiene el home.
	 *
	 * @return el home
	 */
	public static String getHome() {
		return home;
	}

	/**
	 * Establece el home.
	 *
	 * @param home el nuevo home
	 */
	public static void setHome(String home) {
		AlfrescoUtils.home = home;
	}
	
}

