package es.map.ipsg.action.solicitud;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

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
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.SistemaFicheros;

/**
 * El Class DescargarDocumentoZipSpring.
 */
public class DescargarDocumentoZipSpring extends AbstractSpring {
	
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoZipSpring.class);

	/**
	 * Crea una nueva descargar documento zip spring.
	 */
	public DescargarDocumentoZipSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error DescargarDocumentoZipSpring",e);
			
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */

	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("DescargarDocumentoZipSpring -start");
		String resupesta = "success";
		File zipFile = null;
	try{	
		//Para crar rutas temporales distintas segun tiempo
		Long temporal = System.currentTimeMillis();	
		String rutaZip = properties.getProperty("gestDocumental.rutaDocsTemp");
		rutaZip = rutaZip + "documentos_"+ temporal.toString()+".zip";
		
		SpringMessages messages = new SpringMessages();
		
		
		HttpServletRequest req = this.getRequest();
		String entorno = req.getParameter("ent");
		logger.info("parametro ent:" + entorno.toString());
		
		//Cogemos el form del jsp
		BuscarSolicitudesForm formulario = new BuscarSolicitudesForm();
		formulario = (BuscarSolicitudesForm) form;
		
		String[] solicitudesSel=null;
		String checkBox = formulario.getMarcarTodo();
		String campoOrden = (!StringUtils.isEmpty(formulario.getCampo()))?formulario.getCampo():null;
		String campoOrdenExcel =(!StringUtils.isEmpty(formulario.getCampoOrdenacion()))?formulario.getCampoOrdenacion():null;
		
		
		if ("TodoOk".equals(checkBox)) {
			ArrayList<SolicitudBean> aSolicitudes = new ArrayList<SolicitudBean>();
			SolComunRegistradasViewQuery solicitudQuery = new SolComunRegistradasViewQuery();
			//Creamos la query para mostrar todas las solicitudes
		
			try{
				campoOrden=cargarCamposSolicitudesRegistradas(campoOrden);
			}catch(Exception e){	
				logger.error("Error en cargarCamposSolicitudesRegistradas para el zip", e);
			}
			
			solicitudQuery = crearQuerySolicitudRegistrada(formulario, campoOrden);
			//Recuperamos todos los datos de las solicitudes seleccionadas
			aSolicitudes = solicitudesRegistradasManager.buscarSolicitudesRegistradasVista(solicitudQuery);
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
		
	}catch(Exception eGenerico){
		logger.error("Error DescargarDocumentoZipSpring",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}finally {
    	if(zipFile.delete()) {
	    	logger.info("Fichero Borrado correctamente de la ruta: "+zipFile.getPath());
	    } else {
	    	logger.error("No se ha podido borrar el fichero de la ruta: "+zipFile.getPath());
	    }
    	getLogger().debug("DescargarDocumentoZipSpring -end");
	}
	return resupesta;
	}
	
	private void cargarErrorAdjuntos(SpringMessages messages) {
		SpringMessage mensaje = new SpringMessage("field.exporta.errores.noExistenDocumentos");
		messages.add("mensajeDocumentos", mensaje);
		saveMessages(this.getRequest(), messages);
	}
	
	/**
	 * Crear query solicitud registrada.
	 *
	 * @param formulario el formulario
	 * @param campoOrden el campo orden
	 * @return el sol comun registradas view query
	 */
	private SolComunRegistradasViewQuery crearQuerySolicitudRegistrada (BuscarSolicitudesForm formulario, String campoOrden)	{

		SolComunRegistradasViewQuery solicitudQuery = new SolComunRegistradasViewQuery();

		String sNifFormulario = formulario.getNif();
		String sNumSolicitud = formulario.getNumSolicitud();
		Integer iIdMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		String sIdCuerpoEscala = formulario.getCuerpoEscala();
		Integer iIdTipo = formulario.getIdTipo();
		String sIdEspecialidad = formulario.getIdEspecialidad();
		String campo = campoOrden;
		String direccion = formulario.getDireccion();
		String solExencion = formulario.getSolExencion();
		Integer idTipoAcceso = formulario.getIdTipoAcceso();

		//Comprobar los filtros para realizar la busqueda

		//Nif
		if (sNifFormulario != null && !sNifFormulario.equals("") ){
			solicitudQuery.setNif(sNifFormulario);
		}
		
		//Número de solicitud
		if (sNumSolicitud != null && !sNumSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(sNumSolicitud);
		}
		
		//Ministerio
		if (iIdMinisterio != null && iIdMinisterio.intValue() != 0){
			solicitudQuery.setIdMinisterio(iIdMinisterio.shortValue());
		}

		//Centro Gestor
		if (sIdCentroGestor != null && !sIdCentroGestor.equals("")){
			solicitudQuery.setIdCentroGestor(Integer.valueOf(sIdCentroGestor));
		}
		
		//Cuerpo y Escala
		if (sIdCuerpoEscala != null && !sIdCuerpoEscala.equals("")){
			solicitudQuery.setIdCuerpoEscala(Integer.valueOf(sIdCuerpoEscala));
		}

		//Especialidad
		if (sIdEspecialidad != null && !sIdEspecialidad.equals("")){
			solicitudQuery.setIdEspecialidad(Short.valueOf(sIdEspecialidad));
		}

		//Tipo Solicitud HASTA AQUI!!
		if(iIdTipo != null && iIdTipo.intValue() != 0){
			solicitudQuery.setIdTipoSolicitud(iIdTipo);
		}
		
		//Tipo de acceso
		if(idTipoAcceso != null && idTipoAcceso.intValue() != 0) {
			solicitudQuery.setIdFormaAcceso(idTipoAcceso);
		}
		
		//Fecha Desde
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaRegistroMin(dFechaDesde);

			} catch (java.text.ParseException e) {
				logger.error(" Error DescargarDocumentoZipSpring - crearQuerySolicitudRegistrada - parsear fechadesde"+sFechaDesde,e);
			}
		}
		
		//Fecha Hasta
		if (sFechaHasta != null && !sFechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaRegistroMax(dFechaHasta);

			} catch (java.text.ParseException e) {
				logger.error(" Error DescargarDocumentoZipSpring - crearQuerySolicitudRegistrada - parsear fechaHasta"+sFechaHasta,e);
			}
		}
		
		if (solExencion != null && !solExencion.equals("")) {
			solicitudQuery.setSolExencion(solExencion.charAt(0));
		}
		
		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if (SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA.equals(campo)) {
				solicitudQuery.addOrder(SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA, orden);
			}
			else if (SolComunRegistradasViewQuery.DESCENTROGESTOR.equals(campo)) {
				solicitudQuery.addOrder(SolComunRegistradasViewQuery.DESCENTROGESTOR, orden);
			}
			else {
				solicitudQuery.addOrder(campo, orden);
			}
		}
		
		return solicitudQuery;
	}
	
	/**
	 * Cargar campos solicitudes registradas.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCamposSolicitudesRegistradas(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){}
		
		if (campo != null && !"".equals(campo)){
			if(!campo.equals("null")){
				switch(codCampo){
				//Los campos de ordenacion del jsp
					case 1:auxCampo = SolComunRegistradasViewQuery.NUMEROSOLICITUD;break;
					case 2:auxCampo = SolComunRegistradasViewQuery.NIF;break;
					case 3:auxCampo = SolComunRegistradasViewQuery.NOMBRE;break;
					case 4:auxCampo = SolComunRegistradasViewQuery.PRIMERAPELLIDO;break;	
					case 5:auxCampo = SolComunRegistradasViewQuery.SEGUNDOAPELLIDO;break;
					case 6:auxCampo = SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA;break;
					case 7:auxCampo = SolComunRegistradasViewQuery.IDCENTROGESTOR;break;
					case 8:auxCampo = SolComunRegistradasViewQuery.IDCONVOCATORIA;break;
					case 9:auxCampo = SolComunRegistradasViewQuery.DESCTIPOSOLICITUD;break;
					case 10:auxCampo = SolComunRegistradasViewQuery.IDCONSENTIMIENTO;break;
					case 11:auxCampo = SolComunRegistradasViewQuery.EDADVERIFICADA;break;
					case 12:auxCampo = SolComunRegistradasViewQuery.FECHANACIMIENTOVERIFICADA;break;
					case 13:auxCampo = SolComunRegistradasViewQuery.TITULOVERIFICADO;break;
					case 14:auxCampo = SolComunRegistradasViewQuery.DESEMPLEOVERIFICADO;break;
					case 15:auxCampo = SolComunRegistradasViewQuery.IDESPECIALIDAD;break;
					case 16:auxCampo = SolComunRegistradasViewQuery.FNUMEROSAVERIFICADO;break;
					case 17:auxCampo = SolComunRegistradasViewQuery.DISCAPACIDADVERIFICADO;break;
					}
				}
			}
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
