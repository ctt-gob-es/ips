package es.map.ipsg.quartz;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.util.AlfrescoUtils;

/**
 * El Class ProcesoMigracionAlfresco.
 */
public class ProcesoMigracionAlfresco {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ProcesoMigracionAlfresco.class);
	
	/** La constante STRING_ULTIMO_ID_MIGRADO. */
	private static final String STRING_ULTIMO_ID_MIGRADO = "ULTIMO_ID_MIGRADO";

	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;

	/** el properties. */
	private Properties properties;

	/** el url gestor. */
	private String urlGestor;
	
	/** el user gestor. */
	private String userGestor;
	
	/** el password gestor. */
	private String passwordGestor;
	
	/** el ruta temporales. */
	private String rutaTemporales;
	
	/** el space oficina registro. */
	private String spaceOficinaRegistro;
	
	/** el space aplicacion. */
	private String spaceAplicacion = "";
	
	/** el existe gestor documental. */
	private boolean existeGestorDocumental;
	
	/** el space home alfresco. */
	private String spaceHomeAlfresco = "";
	
	/** el alfresco utils. */
	private AlfrescoUtils alfrescoUtils;
	
	/** el ruta directorio final. */
	private String rutaDirectorioFinal = "";
	
	/** el num docs recuperados alfresco. */
	private int numDocsRecuperadosAlfresco = 0;
	
	/** el num docs migrados. */
	private int numDocsMigrados = 0;
	
	/** el num docs erroneos. */
	private int numDocsErroneos = 0;
	
	/** el numero docs A migrar. */
	private int numeroDocsAMigrar = 3000;

	/**
	 * Execute test.
	 *
	 * @param convocatoriasManager el convocatorias manager
	 * @param documentoManager el documento manager
	 * @param solicitudesManager el solicitudes manager
	 * @param parametroConfiguracionManager el parametro configuracion manager
	 */
	public void executeTest(ConvocatoriasManager convocatoriasManager, DocumentoManager documentoManager, SolicitudesManager solicitudesManager, ParametroConfiguracionManager parametroConfiguracionManager){
		setConvocatoriasManager(convocatoriasManager);
		setDocumentoManager(documentoManager);
		setSolicitudesManager(solicitudesManager);
		setParametroConfiguracionManager(parametroConfiguracionManager);
	}

	/**
	 * Metodo que realiza la migración de documentos de Alfresco al Sistema de ficheros de IPS.
	 * Producción: +- 165000 / convocatorias: 3659
	 * 4 nodos: 1000 docs cada hora por cada nodo: 
	 * Pre:		   +- 16000
	 * Desarrollo: +- 4000
	 */
	public void execute() {

		try {
			convocatoriasManager = (ConvocatoriasManager) ApplicationContextProvider.getInstance().getBean("convocatoriaManager");
			documentoManager = (DocumentoManager) ApplicationContextProvider.getInstance().getBean("documentoManager");
			solicitudesManager = (SolicitudesManager) ApplicationContextProvider.getInstance().getBean("solicitudesManager");
			parametroConfiguracionManager = (ParametroConfiguracionManager) ApplicationContextProvider.getInstance().getBean("parametroConfiguracionManager");
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		} catch (Exception e) {
			logger.info("Contexto cargado por ejecucion de Test.",e);
		}
		
		// Ultimo identificado de documento
		// procesado en la presente ejecucion
		long idMinActual = 0;

		// Identificador del ultimo ID de documento migrado
		long ultimoId = 0;

		// Recuperamos el ultimo identificador procesado.
		ParametrosConfiguracionQuery paramConfiguracionQuery = new ParametrosConfiguracionQuery();
		paramConfiguracionQuery.setNombreCampo(STRING_ULTIMO_ID_MIGRADO);
		ParametrosConfiguracionBean paramBean = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfiguracionQuery);
		
		if(null != paramBean && null != paramBean.getValorCampo()){
			ultimoId = Long.parseLong(paramBean.getValorCampo());
		}else{
			
			// Primera ejecucion
			DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setMaxResults(1);
			documentoQuery.addOrder("id",OrderType.DESC);
			DocumentoBean docBean = documentoManager.obtenerDocumento(documentoQuery);
			ultimoId = docBean.getId();
		}
		
		// Recuperamos el id del ultimo documento.
		DocumentoQuery docQuery = new DocumentoQuery();
		docQuery.setIdMin(ultimoId-numeroDocsAMigrar);
		docQuery.setIdMax(ultimoId);
		Set<DocumentoBean> listaDocs = documentoManager.buscarDocumentos(docQuery);
		
		if(listaDocs.size()>0){
			
			long fechaInicio = new Date().getTime();
			logger.info("Batch Migracion Alfresco - Sistema Ficheros - inicio: "+new Date());

			try {

				alfrescoUtils = new AlfrescoUtils();
				rutaDirectorioFinal = properties.getProperty("sistemaficheros.url.final");

				// 1. Obtener documentos de CONVOCATORIAS
				DocumentoQuery documentoQuery = new DocumentoQuery();
				TipoDocumentoQuery tipoDocumento = new TipoDocumentoQuery();
				tipoDocumento.setId(2);
				documentoQuery.setTipoDocumento(tipoDocumento);
				documentoQuery.addOrder("id", OrderType.DESC);

				documentoQuery.setIdMin(ultimoId-numeroDocsAMigrar);
				documentoQuery.setIdMax(ultimoId);

	

				Set<DocumentoBean> listaDocsConvocatoria = documentoManager.buscarDocumentos(documentoQuery);
				logger.info("Numero de convocatorias obtenidas: "+listaDocsConvocatoria.size());

				for (DocumentoBean documentoBean : listaDocsConvocatoria) {
					try {
						
						migrarDocumento2(documentoBean, documentoBean.getIdConvocatoria());

					} catch (Exception e) {
						logger.error("Se ha producido un error migrando el documento:",e);
						
					}

					// Guardamos el identificador menor
					idMinActual = documentoBean.getId();
				}

				listaDocsConvocatoria.clear();

				// 2. Obtener documentos de SOLICITUDES
				Set<DocumentoBean> listaDocsSolicitudes = new HashSet<DocumentoBean>();

				// Buscar documentos agrupados por tipos
				for(int i=1; i<28; i++){

					logger.info("Pausa...");
					Thread.sleep(i*100);
					logger.info("Reanuda...");

					if(i==2 || i==11){
						// Excluimos 2-Convocatorias y 11-Pruebas
					}else{

						logger.info("Consulta Tipo Documento "+i);
						tipoDocumento = new TipoDocumentoQuery();
						tipoDocumento.setId(i);
						documentoQuery = new DocumentoQuery();
						documentoQuery.setTipoDocumento(tipoDocumento);
						documentoQuery.addOrder("id", OrderType.DESC);

						documentoQuery.setIdMin(ultimoId-numeroDocsAMigrar);
						documentoQuery.setIdMax(ultimoId);

			

						listaDocsSolicitudes.clear();
						listaDocsSolicitudes = documentoManager.buscarDocumentos(documentoQuery);
						logger.info("Numero de documentos obtenidos: "+listaDocsSolicitudes.size());

						for (DocumentoBean documentoBean : listaDocsSolicitudes) {
							try {

								logger.info("Fichero a tratar: "+documentoBean.getNombreAlfresco());
								migrarDocumento2(documentoBean, documentoBean.getIdSolicitud());

							} catch (Exception e) {
								logger.error("Se ha producido un error migrando el documento:",e);
								
							}

							if(idMinActual == 0 || documentoBean.getId() < idMinActual){
								idMinActual = documentoBean.getId();
							}
						}
					}
				}

				logger.info("________________________ RESULTADOS __________________________");
				logger.info("");
				logger.info("________________________ NUM DOCS RECUPERADOS DE ALFRESCO ____ "+numDocsRecuperadosAlfresco);
				logger.info("________________________ NUM DOCS MIGRADOS COMPLETAMENTE _____ "+numDocsMigrados);
				logger.info("________________________ NUM DOCS NO EXISTENTES EN ALFRESCO __ "+numDocsErroneos);
				logger.info("________________________ ULTIMO ID PROCESADO _________________ "+idMinActual);
				logger.info("");

				long fechaFin = new Date().getTime();
				logger.info("Batch Mantenimiento Sistema Ficheros - fin: "+new Date());

				obtenerTiempoTotal(new Date(fechaInicio), new Date(fechaFin));

			}catch (Exception e) {
				logger.error("Error en proceso Migracion alfresco:",e);
				
			}finally{

				// En el caso en que sea el ultimo documento
				if(idMinActual == ultimoId){
					ParametrosConfiguracionBean parametrosConfiguracionBean = new ParametrosConfiguracionBean();
					parametrosConfiguracionBean.setNombreCampo(STRING_ULTIMO_ID_MIGRADO);
					parametrosConfiguracionBean.setValorCampo(String.valueOf(0));
					parametrosConfiguracionBean.setDescripcion("Proceso Migracion Alfresco Finalizado");
					parametrosConfiguracionBean.setId(15);
					parametroConfiguracionManager.modificarParametroConfiguracion(parametrosConfiguracionBean);
					
				}else if(idMinActual > 0){
					// Guardamos en BBDD el ultimo ID procesado 
					// para tomarlo de referencia en la siguiente ejecucion
					ParametrosConfiguracionBean parametrosConfiguracionBean = new ParametrosConfiguracionBean();
					parametrosConfiguracionBean.setNombreCampo(STRING_ULTIMO_ID_MIGRADO);
					parametrosConfiguracionBean.setValorCampo(String.valueOf(idMinActual));
					parametrosConfiguracionBean.setDescripcion("Proceso Migracion Alfresco ");
					parametrosConfiguracionBean.setId(15);
					parametrosConfiguracionBean.setVisible(true);
					
					
					// Si ha sido la primera ejecucion
					if(null == paramBean || null == paramBean.getId()){
						parametroConfiguracionManager.guardarParametroConfiguracion(parametrosConfiguracionBean);
					}else{
						parametroConfiguracionManager.modificarParametroConfiguracion(parametrosConfiguracionBean);
					}
				}
				
				numDocsRecuperadosAlfresco=0;
				numDocsMigrados=0;
				numDocsErroneos=0;
			}
		}else{
			logger.info("El proceso ya no se ejecuta mas...");
		}

		logger.info("OK");
	}

	
	/**
	 * Pruebas Rdto.
	 *
	 * @param documentoBean el documento bean
	 * @param id el id
	 * @throws Exception el exception
	 */
	private void migrarDocumento2(DocumentoBean documentoBean, long id) throws Exception {

		String ruta = obtenerRutaFichero(documentoBean, id);
		String rutaFinal = rutaDirectorioFinal + ruta;

		if(obtenerContenidoDocumento2(documentoBean, rutaFinal+documentoBean.getNombreAlfresco())){
			// Actualizar registro en BBDD
			documentoBean.setUbicacion(ruta);
			documentoManager.modificarDocumento(documentoBean);
			logger.info("Fichero procesado correctamente"); 
		}else{
			logger.info("No se actualiza el documento de la convocatoria o solicitud: "+id);
		}

	}

	/**
	 * Método que genera la estructura del directorio del fichero 
	 * según su fecha de creación. Si esta no consta, se toma la fecha actual.
	 *
	 * @param docBean el doc bean
	 * @param id el id
	 * @return el string
	 */
	private String obtenerRutaFichero(DocumentoBean docBean, long id) {

		StringBuffer rutaDocumento = new StringBuffer();
		Calendar cal = Calendar.getInstance();

		if(docBean.getFechaCreacion()!=null){
			cal.setTime(docBean.getFechaCreacion());
		}

		final String separador = File.separator;

		rutaDocumento
		.append(cal.get(Calendar.YEAR)).append(separador)
		.append(cal.get(Calendar.MONTH)+1).append(separador)
		.append(cal.get(Calendar.DAY_OF_MONTH)).append(separador)
		.append(cal.get(Calendar.HOUR_OF_DAY)).append(separador)
		.append(id).append(separador);

		return rutaDocumento.toString();
	}



	/**
	 * Obtener contenido documento 2.
	 *
	 * @param documentoBean el documento bean
	 * @param rutaFinal el ruta final
	 * @return verdadero, si tiene exito
	 * @throws Exception el exception
	 */
	public boolean obtenerContenidoDocumento2(DocumentoBean documentoBean, String rutaFinal)  throws Exception{

		boolean resultado = false;

		try {

			//Ruta del fichero
			StringBuilder space = new StringBuilder();

			if(documentoBean.getIdSolicitud() != null){
				space.append("/Espacios personales de usuario/IPS/Solicitudes/").append(documentoBean.getIdSolicitud()).append("/").append(documentoBean.getNombreAlfresco());
			}else{
				space.append("/Espacios personales de usuario/IPS/Convocatorias/").append(documentoBean.getNombreAlfresco());
			}

			logger.info("obtenerContenidoDocumento - Ruta del fichero: " + space.toString());

			CmisObject object = alfrescoUtils.getObjectByPath(space.toString());

			if (object!=null){
				if (object instanceof Document){
					numDocsRecuperadosAlfresco++;

					Document doc = (Document)object;
					File salida = new File(rutaFinal.toString());

					if (!salida.getParentFile().exists()) {
						salida.getParentFile().mkdirs();
					}

					if(alfrescoUtils.convertStreamToFile(doc.getContentStream().getStream(), rutaFinal)>0){
						logger.info("Fichero migrado: "+rutaFinal);
						numDocsMigrados++;
						resultado=true;
					}else{
						numDocsErroneos++;
					}

				}else{
					logger.info("ObtenerContenidoDocumento - Error: no es un documento: " + space);
					numDocsErroneos++;
				}
			}else{
				logger.info("obtenerContenidoDocumento - Info: no se ha encontrado el documento: " + space);
				numDocsErroneos++;
			}

		} catch (Exception e) {
			logger.error("obtenerContenidoDocumento - Error: ", e);
			
		}

		return resultado;
	}

	/**
	 * Obtener tiempo total.
	 *
	 * @param fecha1 el fecha 1
	 * @param fecha2 el fecha 2
	 */
	private void obtenerTiempoTotal(Date fecha1, Date fecha2) {

		java.util.Date fechaMayor = null;
		java.util.Date fechaMenor = null;

		// Verificamos cual es la mayor de las dos fechas.
		if (fecha1.compareTo(fecha2) > 0){
			fechaMayor = fecha1;
			fechaMenor = fecha2;
		}else{
			fechaMayor = fecha2;
			fechaMenor = fecha1;
		}

		//los milisegundos
		long diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();

		//obtenemos los segundos
		long segundos = diferenciaMils / 1000;

		//obtenemos las horas
		long horas = segundos / 3600;

		//restamos las horas para continuar con minutos
		segundos -= horas*3600;

		//igual que el paso anterior
		long minutos = segundos /60;
		segundos -= minutos*60;

		logger.info("Total: "+Long.toString(horas)+" horas, "+Long.toString(minutos)+" minutos, "+Long.toString(segundos)+" segundos.");
	}

	

	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
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
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudesManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el url gestor.
	 *
	 * @return el url gestor
	 */
	public String getUrlGestor() {
		return urlGestor;
	}

	/**
	 * Establece el url gestor.
	 *
	 * @param urlGestor el nuevo url gestor
	 */
	public void setUrlGestor(String urlGestor) {
		this.urlGestor = urlGestor;
	}

	/**
	 * Obtiene el user gestor.
	 *
	 * @return el user gestor
	 */
	public String getUserGestor() {
		return userGestor;
	}

	/**
	 * Establece el user gestor.
	 *
	 * @param userGestor el nuevo user gestor
	 */
	public void setUserGestor(String userGestor) {
		this.userGestor = userGestor;
	}

	/**
	 * Obtiene el password gestor.
	 *
	 * @return el password gestor
	 */
	public String getPasswordGestor() {
		return passwordGestor;
	}

	/**
	 * Establece el password gestor.
	 *
	 * @param passwordGestor el nuevo password gestor
	 */
	public void setPasswordGestor(String passwordGestor) {
		this.passwordGestor = passwordGestor;
	}

	/**
	 * Obtiene el ruta temporales.
	 *
	 * @return el ruta temporales
	 */
	public String getRutaTemporales() {
		return rutaTemporales;
	}

	/**
	 * Establece el ruta temporales.
	 *
	 * @param rutaTemporales el nuevo ruta temporales
	 */
	public void setRutaTemporales(String rutaTemporales) {
		this.rutaTemporales = rutaTemporales;
	}

	/**
	 * Obtiene el space oficina registro.
	 *
	 * @return el space oficina registro
	 */
	public String getSpaceOficinaRegistro() {
		return spaceOficinaRegistro;
	}

	/**
	 * Establece el space oficina registro.
	 *
	 * @param spaceOficinaRegistro el nuevo space oficina registro
	 */
	public void setSpaceOficinaRegistro(String spaceOficinaRegistro) {
		this.spaceOficinaRegistro = spaceOficinaRegistro;
	}

	/**
	 * Obtiene el space aplicacion.
	 *
	 * @return el space aplicacion
	 */
	public String getSpaceAplicacion() {
		return spaceAplicacion;
	}

	/**
	 * Establece el space aplicacion.
	 *
	 * @param spaceAplicacion el nuevo space aplicacion
	 */
	public void setSpaceAplicacion(String spaceAplicacion) {
		this.spaceAplicacion = spaceAplicacion;
	}	

	/**
	 * Comprueba si es existe gestor documental.
	 *
	 * @return verdadero, si es existe gestor documental
	 */
	public boolean isExisteGestorDocumental() {
		return existeGestorDocumental;
	}

	/**
	 * Establece el existe gestor documental.
	 *
	 * @param existeGestorDocumental el nuevo existe gestor documental
	 */
	public void setExisteGestorDocumental(boolean existeGestorDocumental) {
		this.existeGestorDocumental = existeGestorDocumental;
	}

	/**
	 * Obtiene el space home alfresco.
	 *
	 * @return el space home alfresco
	 */
	public String getSpaceHomeAlfresco() {
		return spaceHomeAlfresco;
	}

	/**
	 * Establece el space home alfresco.
	 *
	 * @param spaceHomeAlfresco el nuevo space home alfresco
	 */
	public void setSpaceHomeAlfresco(String spaceHomeAlfresco) {
		this.spaceHomeAlfresco = spaceHomeAlfresco;
	}

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}


}
