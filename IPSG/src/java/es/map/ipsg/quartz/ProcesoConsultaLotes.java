package es.map.ipsg.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolComunClotesViewQuery;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoConsulta;
import es.map.ips.model.TipoConsultaQuery;
import es.map.ips.model.Usuario;
import es.map.ipsg.bean.BatchIntermediacionBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.BatchConsultaLotesManager;
import es.map.ipsg.manager.BatchIntermediacionManager;
import es.map.ipsg.manager.DatosDesempleoManager;
import es.map.ipsg.manager.DatosDiscapacidadManager;
import es.map.ipsg.manager.DatosFNumerosaManager;
import es.map.ipsg.manager.DatosVictimasTerrorismoManager;
//import es.map.ipsg.manager.DatosTitulosManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoConsultaManager;
import es.map.ipsg.util.Constantes;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;


/**
 * El Class ProcesoConsultaLotes.
 */
public class ProcesoConsultaLotes {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ProcesoConsultaLotes.class);

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el batch consulta lotes manager. */
	private BatchConsultaLotesManager batchConsultaLotesManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el properties. */
	private Properties properties;
	
	/** el tipo consulta manager. */
	private TipoConsultaManager tipoConsultaManager;
	
	/** el batch intermediacion manager. */
	private BatchIntermediacionManager batchIntermediacionManager;

/** el pago solicitud manager. */
//	private DatosTitulosManager datosTitulosManager;
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el datos F numerosa manager. */
	private DatosFNumerosaManager datosFNumerosaManager;
	
	/** el datos discapacidad manager. */
	private DatosDiscapacidadManager datosDiscapacidadManager;
	
	/** el datos desempleo manager. */
	private DatosDesempleoManager datosDesempleoManager;
	
	/** el datos victimas de terrorismo manager. */
	private DatosVictimasTerrorismoManager datosVictimasTerrorismoManager;
	
	/**
	 * Execute test.
	 *
	 * @param documentoManager el documento manager
	 * @param batchConsultaLotesManager el batch consulta lotes manager
	 */
	public void executeTest(DocumentoManager documentoManager, BatchConsultaLotesManager batchConsultaLotesManager){
		setDocumentoManager(documentoManager);
		setBatchConsultaLotesManager(batchConsultaLotesManager);
	}

	/**
	 * Metodo que realiza las siguientes tareas de mantenimiento
	 * 1. Consultar lotes
	 */
	public void execute() {

		boolean resultado = true;
		StringBuilder desResultado = new StringBuilder();
		String result = null;
		logger.info("Batch Proceso Consultar lotes - inicio");

		try {
			batchConsultaLotesManager = (BatchConsultaLotesManager) ApplicationContextProvider.getInstance().getBean("batchConsultaLotesManager");
			documentoManager = (DocumentoManager) ApplicationContextProvider.getInstance().getBean("documentoManager");
			solicitudesManager = (SolicitudesManager) ApplicationContextProvider.getInstance().getBean("solicitudesManager");
			solicitudesRegistradasManager = (SolicitudesRegistradasManager) ApplicationContextProvider.getInstance().getBean("solicitudesRegistradasManager");
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			tipoConsultaManager = (TipoConsultaManager) ApplicationContextProvider.getInstance().getBean("tipoConsultaManager");
			batchIntermediacionManager = (BatchIntermediacionManager) ApplicationContextProvider.getInstance().getBean("batchIntermediacionManager");
//			datosTitulosManager = (DatosTitulosManager) ApplicationContextProvider.getInstance().getBean("datosTitulosManager");
			pagoSolicitudManager = (PagoSolicitudManager) ApplicationContextProvider.getInstance().getBean("pagoSolicitudManager");
			solicitudCcaaManager = (SolicitudCcaaManager) ApplicationContextProvider.getInstance().getBean("solicitudCcaaManager");
			datosFNumerosaManager = (DatosFNumerosaManager) ApplicationContextProvider.getInstance().getBean("datosFNumerosaManager");
			datosDiscapacidadManager = (DatosDiscapacidadManager) ApplicationContextProvider.getInstance().getBean("datosDiscapacidadManager");
			datosDesempleoManager = (DatosDesempleoManager) ApplicationContextProvider.getInstance().getBean("datosDesempleoManager");
			datosVictimasTerrorismoManager = (DatosVictimasTerrorismoManager) ApplicationContextProvider.getInstance().getBean("datosVictimasTerrorismoManager");
			
		} catch (Exception e) {
			logger.info("Contexto cargado por ejecucion de Test.");
			logger.error("Contexto cargado por ejecucion de Test.",e);
			resultado = false;
		}
		
		try {
			
			BuscarSolicitudesForm buscarSolicitudesForm = new BuscarSolicitudesForm();
			Date fechaDesde = batchConsultaLotesManager.buscarResultado();
			
			buscarSolicitudesForm.setFechaDesde(fechaDesde.toString());	
			
			//Generamos la parte comun que va a ser igual en cada una de las peticiones			
			Funcionario funcionario = new Funcionario();
			funcionario.setNifFuncionario("11111111H");
			funcionario.setNombreCompletoFuncionario("JUAN ESPAÑOL ESPAÑOL");			
			
			Solicitante solicitante = new Solicitante();
			solicitante.setIdentificadorSolicitante(properties.getProperty("SVTO_IDENTIFICADOR_SOLICITANTE"));
			solicitante.setNombreSolicitante(properties.getProperty("SVTO_NOMBRE_SOLICITANTE"));
			solicitante.setUnidadTramitadora(properties.getProperty("SVTO_UNIDAD_TRAMITADORA"));
			solicitante.setFinalidad(properties.getProperty("SVTO_FINALIDAD"));
			solicitante.setConsentimiento(Consentimiento.fromString(properties.getProperty("SVTO_CONSENTIMIENTO")));
			solicitante.setFuncionario(funcionario);
						
			Procedimiento procedimiento = new Procedimiento();
			procedimiento.setCodProcedimiento(properties.getProperty("SVTO_COD_PROCEDIMIENTO"));
			procedimiento.setNombreProcedimiento(properties.getProperty("SVTO_NOMBRE_PROCEDIMIENTO"));
			solicitante.setProcedimiento(procedimiento);
						
			// Recogemos todos los códigos de certificado de todas las consultas
//			String codCertTitUniversitario = properties.getProperty("SVTO_CODIGO_CERTIFICADO_TITULOS_UNIV");
//			String codCertTitUniversitario1990 = properties.getProperty("SVTO_CODIGO_CERTIFICADO_TITULOS_UNIV_1990");
//			String codCertTitNoUniversitario = properties.getProperty("SVTO_CODIGO_CERTIFICADO_TITULOS_NO_UNIV");
//			String codigoCertTitNoUniversitario1990 = properties.getProperty("SVTO_CODIGO_CERTIFICADO_TITULOS_NO_UNIV_1990");
			String codigoFamiliaNumerosa = properties.getProperty("CCAAFN_CODIGO_CERTIFICADO");
			String codigoDiscapacidad = properties.getProperty("CCAADD_CODIGO_CERTIFICADO");
			String codigoDesempleoDias = properties.getProperty("SVDSEPE_CODIGO_CERTIFICADO");
			String codigoDesempleoImporte = properties.getProperty("SVDSEPEIMP_CODIGO_CERTIFICADO");
			String codigoVVTerrorismo = properties.getProperty("VICTIMAS_CODIGO_CERTIFICADO");
			
			SolComunClotesViewQuery solicitudQueryTitulosVista;
			solicitudQueryTitulosVista = crearQueryTitulosTodos(buscarSolicitudesForm);	
			List<SolicitudBean> listaSolicitudesTitulos = solicitudesRegistradasManager.buscarSolicitudesClotesVista(solicitudQueryTitulosVista);
//			int numElementosTitulos = listaSolicitudesTitulos.size();
			
//			String titulosUniversitarios = datosTitulosManager.consultarTitulosUniversitariosAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesTitulos,codCertTitUniversitario,true);			
//			if(titulosUniversitarios != null){				
//				guardarBatchIntermediacion(titulosUniversitarios,codCertTitUniversitario,numElementosTitulos);
//				
//			}
//			
//			String titulosNoUniversitarios = datosTitulosManager.consultarTitulosUniversitariosAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesTitulos,codCertTitNoUniversitario,false);			
//			if(titulosNoUniversitarios != null){				
//				guardarBatchIntermediacion(titulosNoUniversitarios,codCertTitNoUniversitario,numElementosTitulos);
//				
//			}
//			
//			String titulosUniversitarios1990 = datosTitulosManager.consultarTitulosUniversitarios1990Asincrona(funcionario,procedimiento,solicitante,listaSolicitudesTitulos,codCertTitUniversitario1990,true);			
//			if(titulosUniversitarios1990 != null){				
//				guardarBatchIntermediacion(titulosUniversitarios1990,codCertTitUniversitario1990,numElementosTitulos);
//				
//			}
//			
//			String titulosNoUniversitarios1990 = datosTitulosManager.consultarTitulosUniversitarios1990Asincrona(funcionario,procedimiento,solicitante,listaSolicitudesTitulos,codigoCertTitNoUniversitario1990,false);			
//			if(titulosNoUniversitarios1990 != null){				
//				guardarBatchIntermediacion(titulosNoUniversitarios1990,codigoCertTitNoUniversitario1990,numElementosTitulos);
//				
//			}
			
			
			// Añadimos las solicitudes seleccionadas
			
			String[] idSolicitudes = new String[listaSolicitudesTitulos.size()];
			int i = 0;
			for(SolicitudBean solicitud:listaSolicitudesTitulos){
				idSolicitudes[i] = solicitud.getId().toString();
				i++;
			}
			buscarSolicitudesForm.setSolicitudesSel(idSolicitudes);
			
			// FamiliaNumerosa
			
			// saco las solicitudes checkeadas por el funcionario para la consulta por lotes, relacionados con la familia numerosa
			List<SolicitudCcaaBean> listaSolicitudesCcaaFNumerosa = crearListaSolicitudesFNumerosa(buscarSolicitudesForm);
			
			
			// si dicha lista no es null y tiene elementos ...
			if(null != listaSolicitudesCcaaFNumerosa && !listaSolicitudesCcaaFNumerosa.isEmpty()){
				
				// hago una lista de solicitudes genéricas 
				List<SolicitudBean> listaSolicitudesFNumerosa = solicitudesRegistradasManager.buscarSolicitudes(listaSolicitudesCcaaFNumerosa);
				int numElementosFnumerosa = listaSolicitudesFNumerosa.size();
				
				// consumo el web service de familia numerosa , devolviendome un idpeticionfamilia numerosa
				String famNumerosa = datosFNumerosaManager.consultarFNumerosaAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesFNumerosa,listaSolicitudesCcaaFNumerosa,codigoFamiliaNumerosa);			
				// guardo un "ticket"
				if(famNumerosa != null){				
					guardarBatchIntermediacion(famNumerosa,codigoFamiliaNumerosa,numElementosFnumerosa);
				}
							
			}
				
			// Discapacidad
			
			List<SolicitudCcaaBean> listaSolicitudesCcaaDiscapacidad = crearListaSolicitudesDiscapacidad(buscarSolicitudesForm);
			
			
			if(null != listaSolicitudesCcaaDiscapacidad && !listaSolicitudesCcaaDiscapacidad.isEmpty()){
				
				List<SolicitudBean> listaSolicitudesDiscapacidad = solicitudesRegistradasManager.buscarSolicitudes(listaSolicitudesCcaaDiscapacidad);
				int numElementosDiscapacidad = listaSolicitudesDiscapacidad.size();
				String discapacidad = datosDiscapacidadManager.consultarDiscapacidadAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesDiscapacidad,listaSolicitudesCcaaDiscapacidad,codigoDiscapacidad);			
				if(discapacidad != null){				
					guardarBatchIntermediacion(discapacidad,codigoDiscapacidad, numElementosDiscapacidad);
				}
				
			}
			
			// Desempleo
			
			SolicitudComunQuery solicitudQueryDesempleo = crearQueryDesempleo(buscarSolicitudesForm);	
			if(null != solicitudQueryDesempleo){
				List<SolicitudBean> listaSolicitudesDesempleo = solicitudesRegistradasManager.buscarSolicitudAll(solicitudQueryDesempleo);
				int numElementosDesempleo = listaSolicitudesDesempleo.size();

				if(null != listaSolicitudesDesempleo && !listaSolicitudesDesempleo.isEmpty()){

					String desempleoDias = datosDesempleoManager.consultarDesempleoDiasAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesDesempleo,codigoDesempleoDias);			
					if(desempleoDias != null){				
						guardarBatchIntermediacion(desempleoDias,codigoDesempleoDias, numElementosDesempleo);
					}
					String desempleoImporte = datosDesempleoManager.consultarDesempleoImporteAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesDesempleo,codigoDesempleoImporte);			
					if(desempleoImporte != null){				
						guardarBatchIntermediacion(desempleoImporte,codigoDesempleoImporte, numElementosDesempleo);
					}
				}

			}
			
			// Victimas del terrorismo
			
			SolicitudComunQuery solicitudQueryVVTerrorismo = crearQueryVVTerrorismo(buscarSolicitudesForm);	
			if(null != solicitudQueryVVTerrorismo){
				List<SolicitudBean> listaSolicitudesVVTerrorismo = solicitudesRegistradasManager.buscarSolicitudAll(solicitudQueryVVTerrorismo);
				int numElementosVVTerrorismo = listaSolicitudesVVTerrorismo.size();

				if(null != listaSolicitudesVVTerrorismo && !listaSolicitudesVVTerrorismo.isEmpty()){

					String vVTerrorismo = datosVictimasTerrorismoManager.consultarVictimasAsincrona(procedimiento,solicitante,listaSolicitudesVVTerrorismo,codigoVVTerrorismo);			
					if(vVTerrorismo != null){				
						guardarBatchIntermediacion(vVTerrorismo,codigoVVTerrorismo, numElementosVVTerrorismo);
					}
				}

			}
	
		}catch (Exception e) {
			logger.error("Error proceso CONSULTA LOTES:", e);
			desResultado.append("- Error en execute:" + e);
			resultado = false;
		}
		
		Calendar fecha = Calendar.getInstance();
		if (desResultado.toString().length()>490) {
			result = desResultado.substring(0, 490);
		}else{
			result = desResultado.toString();
		}
		if (resultado){
			batchConsultaLotesManager.guardarResultado(fecha.getTime(), "OK", result);
		}	else {
			batchConsultaLotesManager.guardarResultado(fecha.getTime(), "ER", result);
		}

		logger.info("Batch Proceso Consultar lotes - fin");
		
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
	 * Obtiene el batch consulta lotes manager.
	 *
	 * @return el batch consulta lotes manager
	 */
	public BatchConsultaLotesManager getBatchConsultaLotesManager() {
		return batchConsultaLotesManager;
	}

	/**
	 * Establece el batch consulta lotes manager.
	 *
	 * @param batchConsultaLotesManager el nuevo batch consulta lotes manager
	 */
	public void setBatchConsultaLotesManager(BatchConsultaLotesManager batchConsultaLotesManager) {
		this.batchConsultaLotesManager = batchConsultaLotesManager;
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
	 * Crear query titulos todos.
	 *
	 * @param formulario el formulario
	 * @return el sol comun clotes view query
	 */
	private SolComunClotesViewQuery crearQueryTitulosTodos(BuscarSolicitudesForm formulario) {
		SolComunClotesViewQuery solicitudQuery = new SolComunClotesViewQuery();

		String sFechaDesde = formulario.getFechaDesde();
		
				
		//Fecha Desde (FECHA REGISTRO)
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaRegistroMin(dFechaDesde);
				
			} catch (java.text.ParseException e) {
				logger.error("Error BuscarSolicitudesRegistradasSpring - crearQuerySolicitudesRegistradasVista - parsear fechaDesde"+ sFechaDesde,e);
			}
		
		}
	
		solicitudQuery.setIdConsentimiento(true);

		return solicitudQuery;
	}
	
	/**
	 * Guardar batch intermediacion.
	 *
	 * @param idPeticion el id peticion
	 * @param codigoCertificado el codigo certificado
	 * @param numElementos el num elementos
	 */
	private void guardarBatchIntermediacion(String idPeticion, String codigoCertificado, int numElementos) {
				
		TipoConsultaQuery tipoConsultaQuery = new TipoConsultaQuery();
		tipoConsultaQuery.setCodConsulta(codigoCertificado);
		TipoConsulta idTipoConsulta = tipoConsultaManager.buscarTipoConsulta(tipoConsultaQuery);
				
		BatchIntermediacionBean batchIntermediacionBean = new BatchIntermediacionBean();
		batchIntermediacionBean.setIdPeticion(idPeticion);
		batchIntermediacionBean.setIdTipoConsulta(idTipoConsulta);
		batchIntermediacionBean.setFhInicioConsulta(new Date());
		
		// Establecemos un usuario por defecto
		Usuario usuario = new Usuario();
		usuario.setId(78);
		
		batchIntermediacionBean.setIdUsuarioConsulta(usuario);
		batchIntermediacionBean.setRespuesta(false);
		batchIntermediacionBean.setReintentos(0);
		batchIntermediacionBean.setNumElementos(numElementos);
		
		batchIntermediacionManager.guardarBatchIntermediacion(batchIntermediacionBean);

	}
	
	/**
	 * Método que genera un listado de solicitudes con familia numerosa marcado.
	 *
	 * @param form - Formulario de búsqueda de solicitudes
	 * @return - Listado de solicitudes de familia numerosa de CCAA
	 */
	private List<SolicitudCcaaBean> crearListaSolicitudesFNumerosa(BuscarSolicitudesForm form){
		
	SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
	SolicitudComunQuery solicitudComunQuery2 = new SolicitudComunQuery();
	SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
	PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
	ArrayList<SolicitudCcaaBean> listaSolicitudCcaaBean = new ArrayList<SolicitudCcaaBean>();
	MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		
		String[] idSolicitudes = form.getSolicitudesSel();
		
		for (String idSolicitud : idSolicitudes) {
			
			solicitudComunQuery.addIdSolicitudIn(Long.parseLong(idSolicitud));
		}

		solicitudComunQuery.setIdConsentimiento(true);
				
		motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_FNUMEROSAGENERAL));
		motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_FNUMEROSAESPECIAL));
		
		pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
		pagoSolicitudQuery.setMotivoReduccionTasa(motivoReduccionTasaQuery);
		
		
		List<PagoSolicitudBean> lista = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
		
		for (PagoSolicitudBean pagoSolicitudBean : lista) {
			
			solicitudComunQuery2.addIdSolicitudIn(pagoSolicitudBean.getIdSolicitud());
			
		}
		
		if(null != lista && !lista.isEmpty()){
		
		solicitudComunQuery2.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.ASC);
		solicitudCcaaQuery.setSolicitudComun(solicitudComunQuery2);
		solicitudCcaaQuery.setComunidadesIsNotNull(true);
		
		listaSolicitudCcaaBean=solicitudCcaaManager.buscarSolicitudAll(solicitudCcaaQuery);
		
		}

		return listaSolicitudCcaaBean;
	}
	
	/**
	 * Crear lista solicitudes discapacidad.
	 *
	 * @param form el form
	 * @return el list
	 */
	private List<SolicitudCcaaBean> crearListaSolicitudesDiscapacidad(BuscarSolicitudesForm form){
		
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		SolicitudComunQuery solicitudComunQuery2 = new SolicitudComunQuery();
		SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		ArrayList<SolicitudCcaaBean> listaSolicitudCcaaBean = new ArrayList<SolicitudCcaaBean>();
			
			String[] idSolicitudes = form.getSolicitudesSel();
			
			for (String idSolicitud : idSolicitudes) {
				
				solicitudComunQuery.addIdSolicitudIn(Long.parseLong(idSolicitud));
			}

			solicitudComunQuery.setIdConsentimiento(true);
					
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_DISCAPACIDAD));
			
			pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
			pagoSolicitudQuery.setMotivoReduccionTasa(motivoReduccionTasaQuery);
			
			
			List<PagoSolicitudBean> lista = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
			
			for (PagoSolicitudBean pagoSolicitudBean : lista) {
				
				solicitudComunQuery2.addIdSolicitudIn(pagoSolicitudBean.getIdSolicitud());
				
			}
			
			if(null != lista && !lista.isEmpty()){
				
				solicitudComunQuery2.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.ASC);
				solicitudCcaaQuery.setSolicitudComun(solicitudComunQuery2);
				solicitudCcaaQuery.setComunidadesIsNotNull(true);
				
				listaSolicitudCcaaBean=solicitudCcaaManager.buscarSolicitudAll(solicitudCcaaQuery);
				
				}


			return listaSolicitudCcaaBean;
		}
	
	/**
	 * Crear query desempleo.
	 *
	 * @param form el form
	 * @return el solicitud comun query
	 */
	private SolicitudComunQuery crearQueryDesempleo(BuscarSolicitudesForm form){
		
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		SolicitudComunQuery solicitudComunQuery2 = new SolicitudComunQuery();
		SolicitudComunQuery resultado = null;
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
			
			String[] idSolicitudes = form.getSolicitudesSel();
			
			for (String idSolicitud : idSolicitudes) {
				
				solicitudComunQuery.addIdSolicitudIn(Long.parseLong(idSolicitud));
			}

			solicitudComunQuery.setIdConsentimiento(true);
					
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_DESEMPLEO));
			
			pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
			pagoSolicitudQuery.setMotivoReduccionTasa(motivoReduccionTasaQuery);
			
			
			List<PagoSolicitudBean> lista = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
			
			if (null != lista && !lista.isEmpty()){

				for (PagoSolicitudBean pagoSolicitudBean : lista) {

					solicitudComunQuery2.addIdSolicitudIn(pagoSolicitudBean.getIdSolicitud());

				}
				solicitudComunQuery2.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.ASC);
				
				resultado = solicitudComunQuery2;
			}
				
			
			return resultado;
		}
	
	/**
	 * Crear query desempleo.
	 *
	 * @param form el form
	 * @return el solicitud comun query
	 */
	private SolicitudComunQuery crearQueryVVTerrorismo(BuscarSolicitudesForm form){
		
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		SolicitudComunQuery solicitudComunQuery2 = new SolicitudComunQuery();
		SolicitudComunQuery resultado = null;
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
			
			String[] idSolicitudes = form.getSolicitudesSel();
			
			for (String idSolicitud : idSolicitudes) {
				
				solicitudComunQuery.addIdSolicitudIn(Long.parseLong(idSolicitud));
			}

			solicitudComunQuery.setIdConsentimiento(true);
					
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_VICTIMATERRORISMO));
			
			pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
			pagoSolicitudQuery.setMotivoReduccionTasa(motivoReduccionTasaQuery);
			
			
			List<PagoSolicitudBean> lista = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
			
			if (null != lista && !lista.isEmpty()){

				for (PagoSolicitudBean pagoSolicitudBean : lista) {

					solicitudComunQuery2.addIdSolicitudIn(pagoSolicitudBean.getIdSolicitud());

				}
				solicitudComunQuery2.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.ASC);
				
				resultado = solicitudComunQuery2;
			}
				
			
			return resultado;
		}
}
