//package es.map.ipsg.manager;
//
//import java.rmi.RemoteException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//
//import org.apache.log4j.Logger;
//
//import es.map.ips.common.exception.BusinessException;
//import es.map.ips.common.spring.ApplicationContextProvider;
//import es.map.ips.common.util.BeanFormatter;
//import es.map.ips.dao.BatchIntermediacionDAO;
//import es.map.ips.dao.SolicitudComunDAO;
//import es.map.ips.model.SolicitudComun;
//import es.map.ipsg.bean.IntermediacionTituloBean;
//import es.map.ipsg.bean.SolicitudBean;
//import es.map.ipsg.bean.UsuarioBean;
//import es.map.ipsg.util.Constantes;
//import es.map.ipsg.util.Util;
//import es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Atributos;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.SolicitudTransmision;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.SolicitudTransmisionDatosGenericos;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitudes;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.TipoDocumentacion;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Titular;
//
//
//public class DatosTitulosManagerImpl implements DatosTitulosManager {
//	
//	private static Properties properties;
//	private static final Logger logger = Logger.getLogger(DatosTitulosManagerImpl.class);
//	private SolicitudComunDAO solicitudComunDAO;
//	
//	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
//	private static final String STRING_REALIZANDO_CONSULTA_TITULOS = "Realizando Consulta de Titulos con los siguientes datos:";
//	private static final String STRING_TITULOS_OBTENIDOS_NIF = "Titulos Obtenidos para el NIF: ";
//	private static final String STRING_INTERMEDIACION_SVT_ERRORCONEXION = "intermediacion.svto.errorConexion";
//	private static final String STRING_SIMPLEDATEFORMATMONTHYEAR = "MM/yyyy";
//	private static final String STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN = " Error DatosTitulosManagerImpl - toIntermediacionTituloBean- parsear fechaFin :";
//	private static final String STRING_INTERMEDIACION_SVTO_ERRORFECHAFINALIZACION = "intermediacion.svto.errorFechaFinalizacion";
//	private static final String STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFINALIZACION = " Error DatosTitulosManagerImpl - toIntermediacionTituloBean- parsear fechaFinalizacion :";
//	
//	public List<IntermediacionTituloBean> consultarTitulosUniversitarios(SolicitudBean solicitudBean, UsuarioBean usuarioBean){
//		
//		SolicitudBean dTitular=solicitudBean;
//		String documentacion = solicitudBean.getNif();
//		String tipoDocumentacion = evaluarTipoDocumento(documentacion);
//		String nifFuncionario = usuarioBean.getNif();
//		String nombreFuncionario = usuarioBean.getNombreCompleto().toUpperCase();
//		ParametrosConfiguracion parametrosUniv = new ParametrosConfiguracion(true);
//		String numSolicitud = solicitudBean.getNumeroSolicitud();
//		
//		List<IntermediacionTituloBean> titulosUniv = consultarDatosTitulosOficiales(documentacion, tipoDocumentacion, nifFuncionario, nombreFuncionario,parametrosUniv, numSolicitud);
//		List<IntermediacionTituloBean> titulosUniv1990 = consultarDatosTitulosOficiales1990(documentacion, tipoDocumentacion, nifFuncionario, nombreFuncionario,parametrosUniv, numSolicitud, dTitular);
//		
//		Set<IntermediacionTituloBean> titUniv = new HashSet<IntermediacionTituloBean>();
//		
//		titUniv.addAll(titulosUniv);
//		titUniv.addAll(titulosUniv1990);
//		
//		List<IntermediacionTituloBean> titulosUniversitarios = new ArrayList<IntermediacionTituloBean>(titUniv);
//		
//				
//		return titulosUniversitarios;
//		
//	}
//	
//	public List<IntermediacionTituloBean> consultarTitulosNoUniversitarios(SolicitudBean solicitudBean, UsuarioBean usuarioBean){
//		
//		SolicitudBean dTitular=solicitudBean;
//		String documentacion = solicitudBean.getNif();
//		String tipoDocumentacion = evaluarTipoDocumento(documentacion);
//		String nifFuncionario = usuarioBean.getNif();
//		String nombreFuncionario = usuarioBean.getNombreCompleto().toUpperCase();
//		ParametrosConfiguracion parametrosUniv = new ParametrosConfiguracion(false);
//		String numSolicitud = solicitudBean.getNumeroSolicitud();
//		
//		List<IntermediacionTituloBean> titulosNoUniv = consultarDatosTitulosOficiales(documentacion, tipoDocumentacion, nifFuncionario, nombreFuncionario,parametrosUniv, numSolicitud);
//		List<IntermediacionTituloBean> titulosNoUniv1990 = consultarDatosTitulosOficiales1990(documentacion, tipoDocumentacion, nifFuncionario, nombreFuncionario,parametrosUniv, numSolicitud, dTitular);
//		
//		Set<IntermediacionTituloBean> titNoUniv = new HashSet<IntermediacionTituloBean>();
//		
//		titNoUniv.addAll(titulosNoUniv);
//		titNoUniv.addAll(titulosNoUniv1990);
//		
//		
//		List<IntermediacionTituloBean> titulosNoUniversitarios = new ArrayList<IntermediacionTituloBean>(titNoUniv);
//		
//		return titulosNoUniversitarios;
//	}
//	
//	public void aprobarTituloVerificado(Long idSolicitud) {
//		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
//		solicitud.setTituloVerificado(Constantes.TITULO_VERIFICADO);
//		solicitudComunDAO.update(solicitud);
//	}
//
//	public void rechazarTituloVerificado(Long idSolicitud) {
//		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
//		solicitud.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
//		solicitudComunDAO.update(solicitud);		
//	}
//	
//	//INI - cpasculi - IPS-149 - Consulta titulos inferiores a 1990
//	
//	private List<IntermediacionTituloBean> consultarDatosTitulosOficiales1990
//	(String documentacionTitular, String tipoDocumentacionTitular, String nifFuncionario, 
//			String nombreFuncionario, ParametrosConfiguracion parametros, String numSolicitud, SolicitudBean dTitular) {
//	
//		//-------------------------------------------------------------- INICIO PETICION --------------------------------------------------------------------------//
//		
//		Atributos atributos = new Atributos();
//		atributos.setCodigoCertificado(parametros.getcodCertificado1990());
//		
//		Funcionario funcionario = new Funcionario();
//		funcionario.setNifFuncionario(nifFuncionario);
//		funcionario.setNombreCompletoFuncionario(nombreFuncionario);
//		
//		Solicitante solicitante = new Solicitante();
//		solicitante.setIdentificadorSolicitante(parametros.getIdentificadorSolicitante());
//		solicitante.setNombreSolicitante(parametros.getNombreSolicitante());
//		solicitante.setUnidadTramitadora(parametros.getUnidadTramitadora());
//		solicitante.setFinalidad(parametros.getFinalidad());
//		solicitante.setConsentimiento(Consentimiento.fromString(parametros.getConsentimiento()));
//		solicitante.setFuncionario(funcionario);
//		
//		if(null!=numSolicitud && !"".equals(numSolicitud)){
//			solicitante.setIdExpediente(numSolicitud);
//		}else{
//			solicitante.setIdExpediente(parametros.getIdExpediente());
//		}
//		
//		Procedimiento procedimiento = new Procedimiento();
//		procedimiento.setCodProcedimiento(parametros.getCodProcedimiento());
//		procedimiento.setNombreProcedimiento(parametros.getNombreProcedimiento());
//		solicitante.setProcedimiento(procedimiento);
//		
//		Titular titular = new Titular();
//		titular.setDocumentacion("");		
//		titular.setTipoDocumentacion(TipoDocumentacion.fromString(tipoDocumentacionTitular));
//		titular.setNombre(dTitular.getNombre());
//		titular.setApellido1(dTitular.getPrimerApellido());
//		
//		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
//		datosGenericos.setSolicitante(solicitante);
//		datosGenericos.setTitular(titular);	
//		
//		SolicitudTransmision solicitud = new SolicitudTransmision();
//		solicitud.setDatosGenericos(datosGenericos);
//		
//		//Formamos los datos específicos en función de si es univeritario o no
//		if(parametros.isBuscarTitulosUniv()){
//		es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.ConsultaDatosTitular datosTitular=new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.ConsultaDatosTitular();
//
//		SimpleDateFormat format = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
//		datosTitular.setFechaNacimiento(format.format(dTitular.getFechaNacimiento()));
//		
//		es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.Consulta consulta=new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.Consulta();
//		consulta.setDatosTitular(datosTitular);
//
//		es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosEspecificos datosEspecificos=new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosEspecificos();
//		datosEspecificos.setConsulta(consulta);
//		solicitud.setDatosEspecificos(datosEspecificos);
//		
//		}else{
//			
//			es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.ConsultaDatosTitular datosTitular=new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.ConsultaDatosTitular();
//
//			SimpleDateFormat format = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
//			datosTitular.setFechaNacimiento(format.format(dTitular.getFechaNacimiento()));
//			
//			es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.Consulta consulta=new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.Consulta();
//			consulta.setDatosTitular(datosTitular);
//
//			es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosEspecificos datosEspecificos=new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosEspecificos();
//			datosEspecificos.setConsulta(consulta);
//			solicitud.setDatosEspecificos(datosEspecificos);
//		}
//												
//		SolicitudTransmision[] transmisiones = new SolicitudTransmision[1];
//		transmisiones[0] = solicitud;
//		
//		Solicitudes solicitudes = new Solicitudes();
//		solicitudes.setSolicitudTransmision(transmisiones);
//		
//		PeticionSincrona peticionSincrona = new PeticionSincrona(atributos, solicitudes);
//		
//		logger.info(STRING_REALIZANDO_CONSULTA_TITULOS);
//		logger.info(new BeanFormatter().format(peticionSincrona));
//		
//		List<IntermediacionTituloBean> titulosList = new ArrayList<IntermediacionTituloBean>();
//		
//		//-------------------------------------------------------------- FIN PETICION ------------------------------------------------------------------------------------------------//
//
//		
//		try{
//			if(parametros.isBuscarTitulosUniv()){
//			//Buscamos titulos universitarios
//				es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.ScspwsProxy proxy = new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.ScspwsProxy(parametros.getUrl());
//				es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.respuesta.Respuesta respuesta = proxy.peticionSincrona(peticionSincrona);							
//				es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosTitulacion[] titulaciones = null;
//				
//				if(null != respuesta.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getListaTitulares()){
//					 titulaciones = respuesta.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getListaTitulares()[0].getListaTitulos();
//				}else{
//					logger.info("No existe el titular con NIF:" + titular.getDocumentacion() + " registrado en base de datos para obtener titulos universitario anteriores a 1990");
//				}
//								
//				logger.info(STRING_TITULOS_OBTENIDOS_NIF+titular.getDocumentacion());
//				if(null!=titulaciones){
//					for(es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosTitulacion datosTitulacion : titulaciones){
//						IntermediacionTituloBean titulo = toIntermediacionTituloBean(datosTitulacion);
//						logger.info(new BeanFormatter().format(titulo));
//						titulosList.add(titulo);
//					}
//				}else{
//					logger.info("Lista de titulaciones universitarias anteriores a 1990 nula.");
//				}			
//																												
//			}else{
//				//Buscamos titulos no universitarios
//				es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.ScspwsProxy proxy=new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.ScspwsProxy(parametros.getUrl());
//				es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.respuesta.Respuesta respuesta = proxy.peticionSincrona(peticionSincrona);
//				es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion[] titulaciones = null;
//				if(null != respuesta.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getListaTitulares()){
//					 titulaciones = respuesta.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getListaTitulares()[0].getListaTitulos();
//				}else{
//					logger.info("No existe el titular con NIF:" + titular.getDocumentacion() + " registrado en base de datos para obtener titulos no universitarios anteriores a 1990");
//				}
//								
//				logger.info(STRING_TITULOS_OBTENIDOS_NIF+titular.getDocumentacion());
//				if(null!=titulaciones){
//					for(es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion datosTitulacion : titulaciones){
//						IntermediacionTituloBean titulo = toIntermediacionTituloBean(datosTitulacion);
//						logger.info(new BeanFormatter().format(titulo));
//						titulosList.add(titulo);
//					}
//				}else{
//					logger.info("Lista de titulaciones universitarias anteriores a 1990 nula.");
//				}		
//			}
//					
//		}catch(RemoteException re){
//			logger.error(" Error DatosTitulosManagerImpl - consultarDatosTitulosOficiales1990 - error conexion :",re);
//			throw new BusinessException(STRING_INTERMEDIACION_SVT_ERRORCONEXION);
//		}catch(BusinessException be){
//			logger.error(" Error DatosTitulosManagerImpl - consultarDatosTitulosOficiales1990 - error conexion :",be);
//			throw be;
//		}
//		
//		return titulosList;
//	}
//	
//	//FIN - cpasculi - IPS-149 - Consulta titulos inferiores a 1990
//	
//	
//	private List<IntermediacionTituloBean> consultarDatosTitulosOficiales
//		(String documentacionTitular, String tipoDocumentacionTitular, String nifFuncionario, 
//				String nombreFuncionario, ParametrosConfiguracion parametros, String numSolicitud) {
//		
//		Atributos atributos = new Atributos();
//		atributos.setCodigoCertificado(parametros.getCodigoCertificado());
//		
//		Funcionario funcionario = new Funcionario();
//		funcionario.setNifFuncionario(nifFuncionario);
//		funcionario.setNombreCompletoFuncionario(nombreFuncionario);
//		
//		Solicitante solicitante = new Solicitante();
//		solicitante.setIdentificadorSolicitante(parametros.getIdentificadorSolicitante());
//		solicitante.setNombreSolicitante(parametros.getNombreSolicitante());
//		solicitante.setUnidadTramitadora(parametros.getUnidadTramitadora());
//		solicitante.setFinalidad(parametros.getFinalidad());
//		solicitante.setConsentimiento(Consentimiento.fromString(parametros.getConsentimiento()));
//		solicitante.setFuncionario(funcionario);
//		
//		if(null!=numSolicitud && !"".equals(numSolicitud)){
//			solicitante.setIdExpediente(numSolicitud);
//		}else{
//			solicitante.setIdExpediente(parametros.getIdExpediente());
//		}
//		
//		Procedimiento procedimiento = new Procedimiento();
//		procedimiento.setCodProcedimiento(parametros.getCodProcedimiento());
//		procedimiento.setNombreProcedimiento(parametros.getNombreProcedimiento());
//		solicitante.setProcedimiento(procedimiento);
//		
//		Titular titular = new Titular();
//		titular.setDocumentacion(documentacionTitular);		
//		titular.setTipoDocumentacion(TipoDocumentacion.fromString(tipoDocumentacionTitular));
//		
//		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
//		datosGenericos.setSolicitante(solicitante);
//		datosGenericos.setTitular(titular);		
//		
//		SolicitudTransmision solicitud = new SolicitudTransmision();
//		solicitud.setDatosGenericos(datosGenericos);
//		
//		SolicitudTransmision[] transmisiones = new SolicitudTransmision[1];
//		transmisiones[0] = solicitud;
//		
//		Solicitudes solicitudes = new Solicitudes();
//		solicitudes.setSolicitudTransmision(transmisiones);
//		
//		PeticionSincrona peticionSincrona = new PeticionSincrona(atributos, solicitudes);
//		
//		logger.info(STRING_REALIZANDO_CONSULTA_TITULOS);
//		logger.info(new BeanFormatter().format(peticionSincrona));
//		
//		List<IntermediacionTituloBean> titulosList = new ArrayList<IntermediacionTituloBean>();
//		
//		try{
//			if(parametros.isBuscarTitulosUniv()){
//				//Buscamos titulos universitarios
//				es.redsara.intermediacion.scsp.esquemas.educacion.ws.ScspwsProxy proxy = new es.redsara.intermediacion.scsp.esquemas.educacion.ws.ScspwsProxy(parametros.getUrl());
//				es.redsara.intermediacion.scsp.esquemas.educacion.ws.respuesta.Respuesta respuesta = proxy.peticionSincrona(peticionSincrona);
//				es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.DatosTitulacion[] titulaciones = 
//						respuesta.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getListaTitulos();
//
//				logger.info(STRING_TITULOS_OBTENIDOS_NIF+titular.getDocumentacion());
//				if(null!=titulaciones){
//					for(es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.DatosTitulacion datosTitulacion : titulaciones){
//						IntermediacionTituloBean titulo = toIntermediacionTituloBean(datosTitulacion);
//						logger.info(new BeanFormatter().format(titulo));
//						titulosList.add(titulo);
//					}
//				}else{
//					logger.info("Lista de titulaciones universitarias nula.");
//				}
//			}else{
//				//Buscamos titulos no universitarios
//				es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.ScspwsProxy proxy = new es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.ScspwsProxy(parametros.getUrl());
//				es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta.Respuesta respuesta = proxy.peticionSincrona(peticionSincrona);
//				es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.DatosTitulacion[] titulaciones = 
//						respuesta.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getListaTitulos();
//
//				logger.info(STRING_TITULOS_OBTENIDOS_NIF+titular.getDocumentacion());			
//				if(null!=titulaciones){	
//					for(es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.DatosTitulacion datosTitulacion : titulaciones){
//						IntermediacionTituloBean titulo = toIntermediacionTituloBean(datosTitulacion);
//						logger.info(new BeanFormatter().format(titulo));
//						titulosList.add(titulo);
//					}
//				}else{
//					logger.info("Lista de titulaciones no universitarias nula.");
//				}
//			}
//					
//		}catch(RemoteException re){
//			logger.error(" Error DatosTitulosManagerImpl - consultarDatosTitulosOficiales - error conexion :",re);
//			throw new BusinessException(STRING_INTERMEDIACION_SVT_ERRORCONEXION);
//		}catch(BusinessException be){
//			logger.error(" Error DatosTitulosManagerImpl - consultarDatosTitulosOficiales - error conexion :",be);
//			throw be;
//		}
//		
//		return titulosList;
//	}
//	
//	// INI - cpasculi - IPS-149 - Consulta titulos inferiores a 1990
//	
//	private IntermediacionTituloBean toIntermediacionTituloBean(Object titulacion){
//		IntermediacionTituloBean titulo = new IntermediacionTituloBean();
//		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
//		SimpleDateFormat sdf2 = new SimpleDateFormat(STRING_SIMPLEDATEFORMATMONTHYEAR);
//		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
//		boolean error = false;
//		Date fechaFin = null;
//		String fechaFinalizacion = null;
//		
//			
//		if(titulacion instanceof DatosTitulacion){
//			
//			if(((DatosTitulacion) titulacion).getDatosCentro()!=null){
//				titulo.setCentro(((DatosTitulacion) titulacion).getDatosCentro().getCentro());
//			}
//			
//			if(((DatosTitulacion) titulacion).getDatosTitulo()!=null && ((DatosTitulacion) titulacion).getDatosTitulo().getFechaFinalizacion() != null){
//				
//				fechaFinalizacion = ((DatosTitulacion) titulacion).getDatosTitulo().getFechaFinalizacion();
//				
//				try{
//					fechaFin = sdf.parse(((DatosTitulacion) titulacion).getDatosTitulo().getFechaFinalizacion());
//				}catch(ParseException pe){
//					logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+fechaFin,pe);
//				}
//				
//				if(error){//Intentamos con el segundo formato de fecha
//					try{
//						fechaFin = sdf2.parse(((DatosTitulacion) titulacion).getDatosTitulo().getFechaFinalizacion());
//						error=false;
//					}catch(ParseException pe){
//						logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+fechaFin,pe);
//						error = true;
//					}
//				}
//				
//				if(error){//Intentamos con el tercer formato de fecha
//					try{
//						fechaFin = sdf3.parse(((DatosTitulacion) titulacion).getDatosTitulo().getFechaFinalizacion());
//					}catch(ParseException pe){
//						//Han fallado los 3 formatos. Lanzamos la excepcion
//						logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+fechaFin,pe);
//						throw new BusinessException(STRING_INTERMEDIACION_SVTO_ERRORFECHAFINALIZACION);
//					}
//				}
//				
//			}
//			
//			titulo.setFechaFinalizacion(fechaFin);
//			logger.info("Fecha Finalización Título NO Universitario " + fechaFinalizacion);
//			
//			if(((DatosTitulacion) titulacion).getDatosTitulo()!=null){
//				
//				logger.info("Fecha Expedición Título NO Universitario " + ((DatosTitulacion) titulacion).getDatosTitulo().getFechaExpedicion());
//
//				//Comprobamos si los campos del título son nulos
//
//				if(((DatosTitulacion) titulacion).getDatosTitulo().getTitulacion() != null){
//					titulo.setNombreCarrera(((DatosTitulacion) titulacion).getDatosTitulo().getTitulacion());
//				}
//				if(((DatosTitulacion) titulacion).getDatosTitulo().getTipoTitulo() != null){
//					titulo.setTipoTitulo(((DatosTitulacion) titulacion).getDatosTitulo().getTipoTitulo());	
//				}
//
//			}
//		
//		}else if(titulacion instanceof es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosTitulacion){
//			
//			es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosTitulacion titulacionUni = 
//					(es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosTitulacion) titulacion;
//			
//			
//			if(titulacionUni.getDatosCentro()!=null){
//				titulo.setCentro(titulacionUni.getDatosCentro().getCentro());
//				titulo.setUniversidad(titulacionUni.getDatosCentro().getUniversidad());
//			}
//			
//			// Incidencia: Controlar que existe fecha de finalización
//			if(titulacionUni.getDatosTitulo()!=null && (titulacionUni.getDatosTitulo().getFechaFinalizacion() != null)){
//			
//				fechaFinalizacion = (titulacionUni.getDatosTitulo().getFechaFinalizacion());
//				
//				try{
//					fechaFin = sdf.parse(titulacionUni.getDatosTitulo().getFechaFinalizacion());
//				}catch(ParseException pe){
//					logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFINALIZACION+ fechaFinalizacion,pe);
//					error = true;
//				}
//				
//				if(error){//Intentamos con el segundo formato de fecha
//					try{
//						fechaFin = sdf2.parse(titulacionUni.getDatosTitulo().getFechaFinalizacion());
//						error=false;
//					}catch(ParseException pe){
//						logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFINALIZACION+ fechaFinalizacion,pe);
//						error = true;
//					}
//				}
//				
//				if(error){//Intentamos con el tercer formato de fecha
//					try{
//						fechaFin = sdf3.parse(titulacionUni.getDatosTitulo().getFechaFinalizacion());
//					}catch(ParseException pe){
//						//Han fallado los 3 formatos. Lanzamos la excepcion
//						logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFINALIZACION+ fechaFinalizacion,pe);
//						throw new BusinessException(STRING_INTERMEDIACION_SVTO_ERRORFECHAFINALIZACION);
//					}
//				}
//			}
//			
//			titulo.setFechaFinalizacion(fechaFin);
//			logger.info("Fecha Finalización Título Universitario " + fechaFinalizacion);	
//				
//			if(titulacionUni.getDatosTitulo()!=null){
//					
//				logger.info("Fecha Expedición Título Universitario " + (titulacionUni.getDatosTitulo().getFechaExpedicion()));
//
//				//Comprobamos si los campos del título son nulos
//
//				if(titulacionUni.getDatosTitulo().getTitulacion() != null){
//					titulo.setNombreCarrera(titulacionUni.getDatosTitulo().getTitulacion());
//				}
//				if(titulacionUni.getDatosTitulo().getTipoTitulo() != null){
//					titulo.setTipoTitulo(titulacionUni.getDatosTitulo().getTipoTitulo());	
//				}
//
//			}
//		}
//		
//		return titulo;		
//	}
//	
//	// FIN - cpasculi - IPS-149 - Consulta titulos inferiores a 1990
//
//	private IntermediacionTituloBean toIntermediacionTituloBean(es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.DatosTitulacion titulacion){
//		IntermediacionTituloBean titulo = new IntermediacionTituloBean();
//		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
//		SimpleDateFormat sdf2 = new SimpleDateFormat(STRING_SIMPLEDATEFORMATMONTHYEAR);
//		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
//		boolean error = false;
//		Date fechaFin = null;
//		String fechaFinalizacion = null;
//		
//		if(titulacion.getDatosCentro()!=null){
//			titulo.setCentro(titulacion.getDatosCentro().getCentro());
//			titulo.setUniversidad(titulacion.getDatosCentro().getUniversidad());
//		}
//		
//		// Incidencia: Controlar que existe fecha de finalización
//		if(titulacion.getDatosTitulo()!=null && titulacion.getDatosTitulo().getFechaFinalizacion() != null){
//			
//			fechaFinalizacion = titulacion.getDatosTitulo().getFechaFinalizacion();
//			
//			try{
//				fechaFin = sdf.parse(titulacion.getDatosTitulo().getFechaFinalizacion());
//			}catch(ParseException pe){
//				logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+ fechaFin,pe);
//				error = true;
//			}
//			
//			if(error){//Intentamos con el segundo formato de fecha
//				try{
//					fechaFin = sdf2.parse(titulacion.getDatosTitulo().getFechaFinalizacion());
//					error=false;
//				}catch(ParseException pe){
//					logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+ fechaFin,pe);
//					error = true;
//				}
//			}
//			
//			if(error){//Intentamos con el tercer formato de fecha
//				try{
//					fechaFin = sdf3.parse(titulacion.getDatosTitulo().getFechaFinalizacion());
//				}catch(ParseException pe){
//					//Han fallado los 3 formatos. Lanzamos la excepcion
//					logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+ fechaFin,pe);
//					throw new BusinessException(STRING_INTERMEDIACION_SVTO_ERRORFECHAFINALIZACION);
//				}
//			}
//			
//		}
//			
//			titulo.setFechaFinalizacion(fechaFin);
//			logger.info("Fecha Finalización Título Universitario " + fechaFinalizacion);
//			
//			if(titulacion.getDatosTitulo()!=null){
//				
//				logger.info("Fecha Expedición Título Universitario " + titulacion.getDatosTitulo().getFechaExpedicion());
//
//				//Comprobamos si los campos del título son nulos
//
//				if(titulacion.getDatosTitulo().getTitulacion() != null){
//					titulo.setNombreCarrera(titulacion.getDatosTitulo().getTitulacion());
//				}
//				if(titulacion.getDatosTitulo().getTipoTitulo() != null){
//					titulo.setTipoTitulo(titulacion.getDatosTitulo().getTipoTitulo());	
//				}
//
//			}
//		
//		return titulo;		
//	}
//	
//	private IntermediacionTituloBean toIntermediacionTituloBean(es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.DatosTitulacion titulacion){
//		IntermediacionTituloBean titulo = new IntermediacionTituloBean();
//		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
//		SimpleDateFormat sdf2 = new SimpleDateFormat(STRING_SIMPLEDATEFORMATMONTHYEAR);
//		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
//		boolean error = false;
//		Date fechaFin = null;
//		String fechaFinalizacion = null;
//		
//		if(titulacion.getDatosCentro()!=null){
//			titulo.setCentro(titulacion.getDatosCentro().getCentro());
//			titulo.setUniversidad(titulacion.getDatosCentro().getCentro());
//		}
//		
//		// Incidencia: Controlar que existe fecha de finalización
//		if(titulacion.getDatosTitulo()!=null && titulacion.getDatosTitulo().getFechaFinalizacion() != null){
//			
//			fechaFinalizacion = titulacion.getDatosTitulo().getFechaFinalizacion();
//			
//			try{
//				fechaFin = sdf.parse(titulacion.getDatosTitulo().getFechaFinalizacion());
//			}catch(ParseException pe){
//				logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+ fechaFin,pe);
//			}
//			
//			if(error){//Intentamos con el segundo formato de fecha
//				try{
//					fechaFin = sdf2.parse(titulacion.getDatosTitulo().getFechaFinalizacion());
//					error=false;
//				}catch(ParseException pe){
//					logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+ fechaFin,pe);
//					error = true;
//				}
//			}
//			
//			if(error){//Intentamos con el tercer formato de fecha
//				try{
//					fechaFin = sdf3.parse(titulacion.getDatosTitulo().getFechaFinalizacion());
//				}catch(ParseException pe){
//					//Han fallado los 3 formatos. Lanzamos la excepcion
//					logger.error(STRING_ERRORDATOSTITULOS_TOINTERMEDIACIONTITULOBEAN_FECHAFIN+ fechaFin,pe);
//					throw new BusinessException(STRING_INTERMEDIACION_SVTO_ERRORFECHAFINALIZACION);
//				}
//			}
//			
//		}
//		
//		titulo.setFechaFinalizacion(fechaFin);
//		logger.info("Fecha Finalización Título no Universitario " + fechaFinalizacion);
//		
//		if(titulacion.getDatosTitulo()!=null){
//			
//			logger.info("Fecha Expedición Título no Universitario " + titulacion.getDatosTitulo().getFechaExpedicion());
//
//			//Comprobamos si los campos del título son nulos
//
//			if(titulacion.getDatosTitulo().getTitulacion() != null){
//				titulo.setNombreCarrera(titulacion.getDatosTitulo().getTitulacion());
//			}
//			if(titulacion.getDatosTitulo().getTipoTitulo() != null){
//				titulo.setTipoTitulo(titulacion.getDatosTitulo().getTipoTitulo());	
//			}
//
//		}
//	
//	return titulo;		
//}
//
//
//
//	@Override
//	public String consultarTitulosUniversitariosAsincrona(Funcionario funcionario, Procedimiento procedimiento,Solicitante solicitantePeticion, List<SolicitudBean> listaSolicitudesTitulos, String codigoCertificado, Boolean isTitulosUniv){
//		
//
//		String idPeticionTitulos = null;
//		ParametrosConfiguracion parametros = new ParametrosConfiguracion(true);
//		
//		Atributos atributos = new Atributos();
//		atributos.setCodigoCertificado(codigoCertificado);
//
//		SolicitudTransmision[] transmisiones = new SolicitudTransmision[listaSolicitudesTitulos.size()];
//		
//		for (int i =0; i<listaSolicitudesTitulos.size(); i++) {
//			Solicitante solicitante = null;
//			try {
//				solicitante = (Solicitante) solicitantePeticion.clone();
//			} catch (CloneNotSupportedException e) {
//				logger.error(" Error DatosTitulosManagerImpl - consultarTitulosUniversitariosAsincrona - solicitante :",e);
//			}
//			
//			if(null!=listaSolicitudesTitulos.get(i).getNumeroSolicitud() && !"".equals(listaSolicitudesTitulos.get(i).getNumeroSolicitud())){
//				solicitante.setIdExpediente(listaSolicitudesTitulos.get(i).getNumeroSolicitud());
//			}else{
//				solicitante.setIdExpediente(parametros.getIdExpediente());
//			}
//			
//
//			Titular titular = new Titular();
//			titular.setDocumentacion(listaSolicitudesTitulos.get(i).getNif());		
//			titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(listaSolicitudesTitulos.get(i).getNif())));
//			
//			SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
//			datosGenericos.setSolicitante(solicitante);
//			datosGenericos.setTitular(titular);		
//			
//			SolicitudTransmision solicitud = new SolicitudTransmision();
//			solicitud.setDatosGenericos(datosGenericos);
//
//			transmisiones[i] = solicitud;
//													
//		}
//		
//		Solicitudes solicitudes = new Solicitudes();
//		solicitudes.setSolicitudTransmision(transmisiones);	
//		
//		PeticionAsincrona peticionAsincrona = new PeticionAsincrona(atributos, solicitudes);
//		
//		logger.info(STRING_REALIZANDO_CONSULTA_TITULOS);
//		logger.info(new BeanFormatter().format(peticionAsincrona));
//		
//		try{
//			if(isTitulosUniv){		
//				es.redsara.intermediacion.scsp.esquemas.educacion.ws.ScspwsProxy proxy = new es.redsara.intermediacion.scsp.esquemas.educacion.ws.ScspwsProxy(parametros.getUrl());
//				es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion confirmacionPeticion = proxy.peticionAsincrona(peticionAsincrona);
//				if(null!= confirmacionPeticion){
//					idPeticionTitulos=confirmacionPeticion.getAtributos().getIdPeticion();
//					
//				}
//
//			}else{
//				es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.ScspwsProxy proxy = new es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.ScspwsProxy(parametros.getUrl());
//				es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion confirmacionPeticion = proxy.peticionAsincrona(peticionAsincrona);
//				if(null!= confirmacionPeticion){
//					idPeticionTitulos=confirmacionPeticion.getAtributos().getIdPeticion();
//				}
//			}
//		}catch(RemoteException re){
//			logger.error(" Error DatosTitulosManagerImpl - consultarTitulosUniversitariosAsincrona - error conexion :",re);
//			throw new BusinessException(STRING_INTERMEDIACION_SVT_ERRORCONEXION);
//		}catch(BusinessException be){
//			logger.error(" Error DatosTitulosManagerImpl - consultarTitulosUniversitariosAsincrona - error conexion :",be);
//			throw be;
//		}
//		
//		return idPeticionTitulos;
//
//	}
//	
//	@Override
//	public String consultarTitulosUniversitarios1990Asincrona(Funcionario funcionario, Procedimiento procedimiento,Solicitante solicitantePeticion, List<SolicitudBean> listaSolicitudesTitulos, String codigoCertificado, Boolean isTitulosUniv){
//
//		ParametrosConfiguracion parametros = new ParametrosConfiguracion(true); 
//		String idPeticion = null;
//		
//		Atributos atributos = new Atributos();
//		atributos.setCodigoCertificado(codigoCertificado);
//
//		SolicitudTransmision[] transmisiones = new SolicitudTransmision[listaSolicitudesTitulos.size()];
//		
//		for (int i =0; i<listaSolicitudesTitulos.size(); i++) {
//			Solicitante solicitante = null;
//			try {
//				solicitante = (Solicitante) solicitantePeticion.clone();
//			} catch (CloneNotSupportedException e) {
//				logger.error(" Error DatosTitulosManagerImpl - cconsultarTitulosUniversitarios1990Asincrona - solicitante :",e);
//			}
//			
//			if(null!=listaSolicitudesTitulos.get(i).getNumeroSolicitud() && !"".equals(listaSolicitudesTitulos.get(i).getNumeroSolicitud())){
//				solicitante.setIdExpediente(listaSolicitudesTitulos.get(i).getNumeroSolicitud());
//			}else{
//				solicitante.setIdExpediente(parametros.getIdExpediente());
//			}
//			
//
//			Titular titular = new Titular();
//			titular.setDocumentacion("");		
//			titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(listaSolicitudesTitulos.get(i).getNif())));
//			titular.setNombre(listaSolicitudesTitulos.get(i).getNombre());
//			titular.setApellido1(listaSolicitudesTitulos.get(i).getPrimerApellido());
//			
//			SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
//			datosGenericos.setSolicitante(solicitante);
//			datosGenericos.setTitular(titular);		
//			
//			SolicitudTransmision solicitud = new SolicitudTransmision();
//			solicitud.setDatosGenericos(datosGenericos);
//			
//			//Formamos los datos específicos en función de si es univeritario o no
//			if(isTitulosUniv){
//				
//			es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.ConsultaDatosTitular datosTitular=new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.ConsultaDatosTitular();
//
//			SimpleDateFormat format = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
//			datosTitular.setFechaNacimiento(format.format(listaSolicitudesTitulos.get(i).getFechaNacimiento()));
//			
//			es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.Consulta consulta=new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.Consulta();
//			consulta.setDatosTitular(datosTitular);
//
//			es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosEspecificos datosEspecificos=new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosEspecificos();
//			datosEspecificos.setConsulta(consulta);
//			solicitud.setDatosEspecificos(datosEspecificos);
//			
//			}else{
//				
//				es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.ConsultaDatosTitular datosTitular=new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.ConsultaDatosTitular();
//
//				SimpleDateFormat format = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
//				datosTitular.setFechaNacimiento(format.format(listaSolicitudesTitulos.get(i).getFechaNacimiento()));
//				
//				es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.Consulta consulta=new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.Consulta();
//				consulta.setDatosTitular(datosTitular);
//
//				es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosEspecificos datosEspecificos=new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosEspecificos();
//				datosEspecificos.setConsulta(consulta);
//				solicitud.setDatosEspecificos(datosEspecificos);
//			}
//
//			transmisiones[i] = solicitud;
//													
//		}
//		
//		Solicitudes solicitudes = new Solicitudes();
//		solicitudes.setSolicitudTransmision(transmisiones);	
//		
//		PeticionAsincrona peticionAsincrona = new PeticionAsincrona(atributos, solicitudes);
//		
//		logger.info(STRING_REALIZANDO_CONSULTA_TITULOS);
//		logger.info(new BeanFormatter().format(peticionAsincrona));
//		
//		try{
//			if(isTitulosUniv){		
//				es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.ScspwsProxy proxy = new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.ScspwsProxy(parametros.getUrl());
//				es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion confirmacionPeticion = proxy.peticionAsincrona(peticionAsincrona);
//				if(null!= confirmacionPeticion){
//					idPeticion=confirmacionPeticion.getAtributos().getIdPeticion();
//				}
//
//			}else{
//				es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.ScspwsProxy proxy = new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.ScspwsProxy(parametros.getUrl());
//				es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion confirmacionPeticion = proxy.peticionAsincrona(peticionAsincrona);
//				if(null!= confirmacionPeticion){
//					idPeticion=confirmacionPeticion.getAtributos().getIdPeticion();
//				}
//			}
//		}catch(RemoteException re){
//			logger.error(" Error DatosTitulosManagerImpl - consultarTitulosUniversitarios1990Asincrona - error conexion :",re);
//			throw new BusinessException(STRING_INTERMEDIACION_SVT_ERRORCONEXION);
//		}catch(BusinessException be){
//			logger.error(" Error DatosTitulosManagerImpl - consultarTitulosUniversitarios1990Asincrona - error conexion :",be);
//			throw be;
//		}	
//		return idPeticion;
//	}
//	
//	public SolicitudComunDAO getSolicitudComunDAO() {
//		return solicitudComunDAO;
//	}
//
//	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
//		this.solicitudComunDAO = solicitudComunDAO;
//	}
//
//
//
//	class ParametrosConfiguracion{
//		private String url;
//		private String identificadorSolicitante;
//		private String nombreSolicitante;
//		private String unidadTramitadora;
//		private String finalidad;
//		private String consentimiento;
//		private String codigoCertificado;
//		private String codProcedimiento;
//		private String nombreProcedimiento;
//		private String idExpediente;
//		private String codCertificado1990;
//
//		private boolean buscarTitulosUniv;
//		
//		public ParametrosConfiguracion(boolean univ){
//			
//			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
//			
//			buscarTitulosUniv			=	univ;
//			url 						= 	properties.getProperty("URL");
//			identificadorSolicitante 	= 	properties.getProperty("SVTO_IDENTIFICADOR_SOLICITANTE");
//			nombreSolicitante 			= 	properties.getProperty("SVTO_NOMBRE_SOLICITANTE");
//			unidadTramitadora 			= 	properties.getProperty("SVTO_UNIDAD_TRAMITADORA");
//			finalidad 					= 	properties.getProperty("SVTO_FINALIDAD");
//			consentimiento 				= 	properties.getProperty("SVTO_CONSENTIMIENTO");
//			codProcedimiento 			=	properties.getProperty("SVTO_COD_PROCEDIMIENTO");
//			nombreProcedimiento 		=	properties.getProperty("SVTO_NOMBRE_PROCEDIMIENTO");
//			idExpediente				=	properties.getProperty("SVTO_ID_EXPEDIENTE");
//			
//			if(univ){
//				codigoCertificado 		= 	properties.getProperty("SVTO_CODIGO_CERTIFICADO_TITULOS_UNIV");
//				codCertificado1990 = properties.getProperty("SVTO_CODIGO_CERTIFICADO_TITULOS_UNIV_1990");
//			}else{
//				codigoCertificado 		= 	properties.getProperty("SVTO_CODIGO_CERTIFICADO_TITULOS_NO_UNIV");
//				codCertificado1990 = properties.getProperty("SVTO_CODIGO_CERTIFICADO_TITULOS_NO_UNIV_1990");
//			}		
//		}
//
//		public String getUrl() {
//			return url;
//		}
//
//		public void setUrl(String url) {
//			this.url = url;
//		}
//
//		public String getIdentificadorSolicitante() {
//			return identificadorSolicitante;
//		}
//
//		public void setIdentificadorSolicitante(String identificadorSolicitante) {
//			this.identificadorSolicitante = identificadorSolicitante;
//		}
//
//		public String getNombreSolicitante() {
//			return nombreSolicitante;
//		}
//
//		public void setNombreSolicitante(String nombreSolicitante) {
//			this.nombreSolicitante = nombreSolicitante;
//		}
//
//		public String getUnidadTramitadora() {
//			return unidadTramitadora;
//		}
//
//		public void setUnidadTramitadora(String unidadTramitadora) {
//			this.unidadTramitadora = unidadTramitadora;
//		}
//
//		public String getFinalidad() {
//			return finalidad;
//		}
//
//		public void setFinalidad(String finalidad) {
//			this.finalidad = finalidad;
//		}
//
//		public String getConsentimiento() {
//			return consentimiento;
//		}
//
//		public void setConsentimiento(String consentimiento) {
//			this.consentimiento = consentimiento;
//		}
//		
//		public String getcodCertificado1990() {
//			return codCertificado1990;
//		}
//
//		public void setcodCertificado1990(String codCertificado1990) {
//			this.codCertificado1990 = codCertificado1990;
//		}
//		
//		public String getCodigoCertificado() {
//			return codigoCertificado;
//		}
//
//		public void setCodigoCertificado(String codigoCertificado) {
//			this.codigoCertificado = codigoCertificado;
//		}
//
//		public boolean isBuscarTitulosUniv() {
//			return buscarTitulosUniv;
//		}
//
//		public void setBuscarTitulosUniv(boolean buscarTitulosUniv) {
//			this.buscarTitulosUniv = buscarTitulosUniv;
//		}
//
//		public String getCodProcedimiento() {
//			return codProcedimiento;
//		}
//
//		public void setCodProcedimiento(String codProcedimiento) {
//			this.codProcedimiento = codProcedimiento;
//		}
//
//		public String getNombreProcedimiento() {
//			return nombreProcedimiento;
//		}
//
//		public void setNombreProcedimiento(String nombreProcedimiento) {
//			this.nombreProcedimiento = nombreProcedimiento;
//		}
//
//		public String getIdExpediente() {
//			return idExpediente;
//		}
//
//		public void setIdExpediente(String idExpediente) {
//			this.idExpediente = idExpediente;
//		}
//		
//		
//	}
//	
//	/** Metodo que evalua el tipo de documento pasado por parametro
//	 * @param documento
//	 * @return String NIF o NIE
//	 */
//	private String evaluarTipoDocumento(String documento){
//		
//		String tipoDoc = Constantes.TIPO_DOCUMENTO_NIF;
//		
//		if(Util.esNie(documento)){
//			tipoDoc = Constantes.TIPO_DOCUMENTO_NIE;
//		}
//		
//		return tipoDoc;
//	}
//
//}