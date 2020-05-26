package es.map.ipsg.action.solicitud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;


import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.SolComunIncidenciasViewQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.form.SolicitudesIncidenciasForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.SistemaFicheros;

/**
 * El Class DescargarDocumentoZipIncidenciasSpring.
 */
public class DescargarDocumentoZipIncidenciasSpring extends AbstractSpring {
	
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** el properties. */
	private static Properties properties;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoZipIncidenciasSpring.class);

	/**
	 * Crea una nueva descargar documento zip incidencias spring.
	 */
	public DescargarDocumentoZipIncidenciasSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error DescargarDocumentoZipIncidenciasSpring " ,e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */

	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String resupesta = "success";
		File zipFile = null;
		try{
			getLogger().debug("DescargarDocumentoZipIncidenciasSpring -start");
			
			
			String path = properties.getProperty("gestDocumental.rutaZipTemp");
	
			//Para crar rutas temporales distintas segun tiempo 
			Long temporal = System.currentTimeMillis();		
			path = path + temporal.toString();
		
			String rutaZip = properties.getProperty("gestDocumental.rutaDocsTemp");
			rutaZip = rutaZip +"/" + "documentos_"+ temporal.toString()+".zip";
			
			SpringMessages messages = new SpringMessages();

			
			//Cogemos el form del jsp
			SolicitudesIncidenciasForm formulario = new SolicitudesIncidenciasForm();
			formulario = (SolicitudesIncidenciasForm) form;
					
			String[] solicitudesSel= null;
			String checkBox = formulario.getMarcarTodo();
			String campoOrden = (!StringUtils.isEmpty(formulario.getCampo()))?formulario.getCampo():null;
			String campoOrdenExcel =(!StringUtils.isEmpty(formulario.getCampoOrdenacion()))?formulario.getCampoOrdenacion():null;
			
			
			if ("TodoOk".equals(checkBox)) {
				ArrayList<SolicitudBean> aSolicitudes = new ArrayList<SolicitudBean>();
				SolComunIncidenciasViewQuery solicitudesIncidenciasQuery = new SolComunIncidenciasViewQuery();	
				if(formulario.getCampo()!= null){			
					try{
						campoOrden=cargarCamposSolicitudesIncidencias(formulario.getCampo());
					}catch(Exception e){	
						logger.error("Error en cargarCamposSolicitudesIncidencias para el zip", e);
					}
				}
				//Creamos la query para mostrar todas las solicitudes
				solicitudesIncidenciasQuery= crearQuerySolicitudesIncidenciasView(formulario, campoOrden);
				
				//Recuperamos todos los datos de las solicitudes seleccionadas
				aSolicitudes = solicitudesRegistradasManager.buscarSolicitudesIncidenciasVista(solicitudesIncidenciasQuery);
				if (aSolicitudes != null && !aSolicitudes.isEmpty()) {
					solicitudesSel = new String[aSolicitudes.size()];
					for (int i=0; i<aSolicitudes.size(); i++) {
						solicitudesSel[i] = aSolicitudes.get(i).getId().toString();
					}
				}
			}else {
				solicitudesSel= formulario.getSolicitudesSel();
				if(formulario.getSolicitudesSel() == null)
				{
					 solicitudesSel= formulario.getSolicitudesSel();
				}
			}
			int j=0;
			//Booleano para saber si ha sido seleccionado algún tipo de Documento en el formulario
			SistemaFicheros sistemaFicheros = new SistemaFicheros();
			     
			List<DocumentoBean> listaCompleta = new LinkedList<>();
			
			while (j<solicitudesSel.length){

				String idSolicitud = solicitudesSel[j];
				boolean bDocumentoSel=false;
				DocumentoQuery documentoQuery = new DocumentoQuery();
				documentoQuery.addSolicitudIn(Long.valueOf(idSolicitud));
				//Obtenemos los tipos de documentos a consultar
				if(formulario.getStrAnexo().equals("S")){
					documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
					documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA);
					documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_ADJUNTO);
					documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_DISCAPACIDAD);
					bDocumentoSel=true;
				}
				if(formulario.getStrJustificaPago().equals("S")){
					documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO);
					bDocumentoSel=true;
				}
				if(formulario.getStrRegistroPDF().equals("S")){
					documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF);
					documentoQuery.addTipoDocumentoIn(Integer.parseInt(Constantes.JUSTIFICANTE_REGISTRO_ID_CATALAN));
					documentoQuery.addTipoDocumentoIn(Integer.parseInt(Constantes.JUSTIFICANTE_REGISTRO_ID_EUSKERA));
					documentoQuery.addTipoDocumentoIn(Integer.parseInt(Constantes.JUSTIFICANTE_REGISTRO_ID_GALLEGO));
					documentoQuery.addTipoDocumentoIn(Integer.parseInt(Constantes.JUSTIFICANTE_REGISTRO_ID_VALENCIANO));
					bDocumentoSel=true;
				}
				if(formulario.getStrRegistroXML().equals("S")){
					documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML);
					bDocumentoSel=true;
				}
				
				List<DocumentoBean> listaDoc =null;
				//Pregunto si hay algún documento seleccionado
				if (bDocumentoSel){
					listaDoc = documentoManager.buscarDocumentoCombo(documentoQuery);
					listaCompleta.addAll(listaDoc);
				}
				else{
					return "success";
				}
				
				j++;
			}
			
			
			if (listaCompleta != null && !listaCompleta.isEmpty()) {
				try {
					zipFile = sistemaFicheros.descargarDocumentosTroceados(listaCompleta, campoOrdenExcel);
				} catch (ApplicationException e) {
					cargarErrorAdjuntos(messages);
				}
			}
			
			if(zipFile != null && zipFile.length() >= 30) {
				//Se descarga el zip con todos los adjuntos y se cierra el zip borrando el archivo temporal
				sistemaFicheros.descargarZipTroceado(zipFile, this.getResponse());
				resupesta = "";
			} else {
				cargarErrorAdjuntos(messages);
			}
			
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error DescargarDocumentoZipIncidenciasSpring- errorRecuperarDocumentosAnexos " ,e);
			this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.solicitud.errorRecuperarDocumentosAnexos"));
			return "nosuccess";
		}finally{
		    if (zipFile.delete()) {
		    	logger.info("Fichero Borrado correctamente de la ruta: "+zipFile.getPath());
		    } else {
		    	logger.error("No se ha podido borrar el fichero de la ruta: "+zipFile.getPath());
		    }
		
			getLogger().debug("DescargarDocumentoZipIncidenciasSpring -end");
		}
		return resupesta;
	}
	
	private void cargarErrorAdjuntos(SpringMessages messages) {
		SpringMessage mensaje = new SpringMessage("field.exporta.errores.noExistenDocumentos");
		messages.add("mensajeDocumentos", mensaje);
		saveMessages(this.getRequest(), messages);
	}

	/**
	 * Obtener documento solicitud salida.
	 *
	 * @param doc el doc
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public byte[] obtenerDocumentoSolicitudSalida(DocumentoBean doc) throws Exception {
		
		byte[] salida = null;
		
		try {
			salida = documentoManager.obtenerContenidoDocumento(doc);
		} catch (NumberFormatException e) {
			logger.error("Error DescargarDocumentoZipIncidenciasSpring- obtenerDocumentoSolicitudSalida -  DescargarDocumentoSolicitudServlet" ,e);
			throw new ApplicationException("NumberFormatException DescargarDocumentoSolicitudServlet");
		} catch (ApplicationException e) {
			logger.error("Error DescargarDocumentoZipIncidenciasSpring- obtenerDocumentoSolicitudSalida  - obtenerContenidoDocumentoSolicitud en ACTION" ,e);
			logger.error("exception obtenerContenidoDocumentoSolicitud en ACTION",e);
		} catch (Exception e) {
			logger.error("Error DescargarDocumentoZipIncidenciasSpring- obtenerDocumentoSolicitudSalida -DescargarDocumentoSolicitudAction " ,e);
			throw new Exception("Exception DescargarDocumentoSolicitudAction");
		} finally {
			logger.debug("doGet ObtenerDocumentoSolicitudAction - end");
		}
		
		return salida;
	}

	/**
	 * Guardar salida.
	 *
	 * @param doc el doc
	 * @param salida el salida
	 * @param ruta el ruta
	 */
	public void guardarSalida(DocumentoBean doc, byte[] salida, String ruta) {
		
		String idAlfresco = doc.getNombreAlfresco();
		String rutaArchivo = ruta+"/"+idAlfresco;
		File documento = new File(rutaArchivo);
		try (FileOutputStream fo = new FileOutputStream(documento)
			){			
			fo.write(salida);			
		} catch (FileNotFoundException e1) {
			logger.error("Error DescargarDocumentoZipIncidenciasSpring - guardarSalida  " ,e1);
		} catch (IOException e) {
			logger.error("Error DescargarDocumentoZipIncidenciasSpring - guardarSalida  " ,e);
		}		
	}

	/**
	 * Borra carpeta.
	 *
	 * @param path el path
	 */
	public static void borraCarpeta(String path){
		File f1 = new File(path);
		File[] files = f1.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory()){
				try {
					borraCarpeta(files[i].getCanonicalPath());
				} 
				catch (Exception e) {
					logger.error("Error DescargarDocumentoZipIncidenciasSpring - borraCarpeta  " ,e);
				}
				files[i].delete();
			}else{
				files[i].delete();
			}
		}
		f1.delete();
	} 
	
	/**
	 * Crea carpeta.
	 *
	 * @param path el path
	 */
	public static void creaCarpeta(String path){

		File fichero= new File (path);
		// A partir del objeto File creamos el fichero físicamente
		try {
			fichero.mkdirs();
		} catch (Exception e) {
			logger.error("Error DescargarDocumentoZipIncidenciasSpring - creaCarpeta  " ,e);
		}
	}

	/**
	 * Crear query solicitudes incidencias view.
	 *
	 * @param formulario el formulario
	 * @param campoOrden el campo orden
	 * @return el sol comun incidencias view query
	 */
	private SolComunIncidenciasViewQuery crearQuerySolicitudesIncidenciasView (SolicitudesIncidenciasForm formulario, String campoOrden){
		
		String campo = campoOrden;
		String direccion = formulario.getDireccion();		

		SolComunIncidenciasViewQuery solicitudQuery = new SolComunIncidenciasViewQuery();
		EstadoSolicitudQuery  estadoSolicitudQuery = new EstadoSolicitudQuery();

		String nif = formulario.getNif();
		String numSolicitud = formulario.getNumSolicitud();		
		Integer idMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String fechaDesde = formulario.getFechaDesde();
		String fechaHasta = formulario.getFechaHasta();
		Integer idEstado = formulario.getIdEstado();
		Integer idTipoAcceso = formulario.getIdTipoAcceso();
		String solExencion = formulario.getSolExencion();

		solicitudQuery.setCalculateNumResults(true);

		//Comprobar los filtros para realizar la busqueda
		//Añadimos los valores distintos al estado Registrado menos el eliminado

		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO);
		estadoSolicitudQuery.addIdIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO);

		//Nif
		if (nif != null && !nif.equals("") ){
			solicitudQuery.setNifSolicitud(nif);
		}
		//Número de solicitud
		if (numSolicitud != null && !numSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(numSolicitud);
		}
		//Ministerio
		if (idMinisterio != null && idMinisterio.intValue() != 0){

			solicitudQuery.setIdMinisterio(idMinisterio.shortValue());
		}
		// Centro Gestor
		if(sIdCentroGestor != null && !sIdCentroGestor.equals(""))
		{	
			Integer idCentroGestor = Integer.valueOf(sIdCentroGestor);
			solicitudQuery.setIdCentroGestor(idCentroGestor);
		}
		// Cuerpo Escala
		if(formulario.getCuerpoEscala() != null && !formulario.getCuerpoEscala().equals(""))
		{
			Integer idCuerpoEscala = Integer.valueOf(formulario.getCuerpoEscala());
			solicitudQuery.setIdCuerpoEscala(idCuerpoEscala);
		}	

		if (solExencion != null && !solExencion.equals("")) {
			solicitudQuery.setSolExencion(solExencion.charAt(0));
		}

		//Fecha Desde
		if (fechaDesde != null && !fechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaSolicitudMin(dFechaDesde);

			} catch (java.text.ParseException e) {
				logger.error("Error DescargarDocumentoZipIncidenciasSpring - crearQuerySolicitudesIncidenciasView - parsear fechaDesde  "+ fechaDesde ,e);
			}

		}
		//Fecha Hasta
		if (fechaHasta != null && !fechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaSolicitudMax(dFechaHasta);

			} catch (java.text.ParseException e) {
				logger.error("Error DescargarDocumentoZipIncidenciasSpring - crearQuerySolicitudesIncidenciasView - parsear fechaHasta  "+ fechaHasta ,e);
			}		
		}
		// Tipo Acceso
		if(idTipoAcceso != null && idTipoAcceso!=0)
		{
			solicitudQuery.setIdFormaAcceso(idTipoAcceso);
		}	
		//Estado
		if(idEstado!=null && idEstado.intValue() != 0){
			if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_SIN_INTENTO_PAGO)
			{
				// Solicitudes en estado NO PAGADO con 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO.byteValue());
				solicitudQuery.setIntentosPagoSolicitud(new BigDecimal(0));

			}
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_SIN_INTENTO_REGISTRO)
			{
				// Solicitudes en estado NO REGISTRADO con 0 intentos de registro
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO.byteValue());
				solicitudQuery.setIntentosRegistroSolicitud(new BigDecimal(0));
			}
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_NO_PAGADO)
			{
				// Solicitudes en estado NO PAGADO o PROCESO PAGO con más de 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO.byteValue());
				solicitudQuery.addIntentosPagoSolicitudNotIn(new BigDecimal(0));	
			}	
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_NO_REGISTRADO)
			{
				// Solicitudes en estado NO REGISTRADO con más de 0 intentos de pago
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO.byteValue());
				solicitudQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO.byteValue());
				solicitudQuery.addIntentosRegistroSolicitudNotIn(new BigDecimal(0));	
			}
			else if(idEstado.intValue() == Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO)
			{
				solicitudQuery.addIdEstadoSolicitudIn(idEstado.byteValue());
			}	
		}

		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo))
		{
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);

			if(campo.equals("nif"))
			{
				solicitudQuery.addOrder("nifSolicitud",orden);
			}
			else if(campo.equals("nombre"))
			{
				solicitudQuery.addOrder("nombreSolicitud",orden);
			}
			else if(campo.equals("email"))
			{
				solicitudQuery.addOrder("emailSolicitud",orden);
			}
			else if(campo.equals("primerApellido"))
			{
				solicitudQuery.addOrder("primerApellidoSolicitud",orden);
			}
			else if(campo.equals("segundoApellido"))
			{
				solicitudQuery.addOrder("segundoApellidoSolicitud",orden);
			}
			else if(campo.equals("telefono"))
			{
				solicitudQuery.addOrder("telefonoSolicitud",orden);
			}
			else if(campo.equals("ejercicio"))
			{
				solicitudQuery.addOrder("ejercicioConvocatoria",orden);
			}
			else if(campo.equals("convocatoria"))
			{
				solicitudQuery.addOrder("idConvocatoria",orden);
			}
			else if(campo.equals("centroGestor"))
			{
				solicitudQuery.addOrder("siglasCentroGestor",orden);
			}
			else {
				solicitudQuery.addOrder(campo, orden);
			}
		}

		return solicitudQuery;
	}

	

	/**
	 * Cargar campos solicitudes incidencias.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCamposSolicitudesIncidencias(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		try{
			codCampo = Integer.parseInt(campo);
			switch(codCampo){
			//Los campos de ordenacion del jsp
			case 1:auxCampo = SolComunIncidenciasViewQuery.NUMEROSOLICITUD;break;
			case 2:auxCampo = SolComunIncidenciasViewQuery.NIFSOLICITUD;break;
			case 3:auxCampo = SolComunIncidenciasViewQuery.NOMBRESOLICITUD;break;
			case 4:auxCampo = SolComunIncidenciasViewQuery.PRIMERAPELLIDOSOLICITUD;break;	//Si es Gestor o Consultor se carga el campo
			case 5:auxCampo = SolComunIncidenciasViewQuery.SEGUNDOAPELLIDOSOLICITUD;break;
			case 6:auxCampo = SolComunIncidenciasViewQuery.EMAILSOLICITUD;break;
			case 7:auxCampo = SolComunIncidenciasViewQuery.TELEFONOSOLICITUD;break;
			case 8:auxCampo = SolComunIncidenciasViewQuery.EJERCICIOCONVOCATORIA;break;
			case 9:auxCampo = SolComunIncidenciasViewQuery.IDCONVOCATORIA;break;
			case 10:auxCampo = SolComunIncidenciasViewQuery.IDCENTROGESTOR;break;
			case 11:auxCampo = SolComunIncidenciasViewQuery.FECHASOLICITUD;break;

			}			
		}catch(Exception e){}

		return auxCampo;
	}

	/**
	 * Obtiene el documentos manager.
	 *
	 * @return el documentos manager
	 */
	public DocumentoManager getDocumentosManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param pDocumentosManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentoManager pDocumentosManager) {
		this.documentoManager = pDocumentosManager;
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
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return el solicitudes registradas manager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager el nuevo solicitudes registradas manager
	 */
	public void setSolicitudesRegistradasManager(
			SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}
	
}
