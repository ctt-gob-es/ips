package es.map.ipsg.manager;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.dao.SolicitudComunAuxiliarDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ipsg.bean.IntermediacionIdentidadBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.util.Constantes;
import es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.DatosTitular;
import es.redsara.intermediacion.scsp.esquemas.svdi.ws.ScspwsProxy;
import es.redsara.intermediacion.scsp.esquemas.svdi.ws.respuesta.Respuesta;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Atributos;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.SolicitudTransmision;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.SolicitudTransmisionDatosGenericos;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitudes;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.TipoDocumentacion;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Titular;

/**
 * El Class DatosPersonalesManagerImpl.
 *
 * @author everis
 */
public class DatosPersonalesManagerImpl implements DatosPersonalesManager {
	
	/** el properties. */
	private Properties properties;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DatosPersonalesManagerImpl.class);
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
	
	/**
	 * Consultar datos fecha nacimiento.
	 *
	 * @param solicitudBean el solicitud bean
	 * @param nifFuncionario el nif funcionario
	 * @param nombreFuncionario el nombre funcionario
	 * @param unidadTramitadora el unidad tramitadora
	 * @return el intermediacion identidad bean
	 */
	private IntermediacionIdentidadBean consultarDatosFechaNacimiento(SolicitudBean solicitudBean, String nifFuncionario, String nombreFuncionario,String unidadTramitadora) {
		
		IntermediacionIdentidadBean identidadBean = null;
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		String url = 						properties.getProperty("URL");
		String codigoCertificado = 			properties.getProperty("SVDI_CODIGO_CERTIFICADO");
		String identificadorSolicitante = 	properties.getProperty("SVDI_IDENTIFICADOR_SOLICITANTE");
		String nombreSolicitante = 			properties.getProperty("SVDI_NOMBRE_SOLICITANTE");
		String unidadTramit = 			    properties.getProperty("SVDI_UNIDAD_TRAMITADORA");
		String finalidad = 					properties.getProperty("SVDI_FINALIDAD");
		String consentimiento = 			properties.getProperty("SVDI_CONSENTIMIENTO");
		String idExpediente =				properties.getProperty("SVDI_ID_EXPEDIENTE");
		String codProcedimiento =			properties.getProperty("SVDI_COD_PROCEDIMIENTO");
		String nombreProcedimiento =		properties.getProperty("SVDI_NOMBRE_PROCEDIMIENTO");

		Atributos atributos = new Atributos();
		atributos.setCodigoCertificado(codigoCertificado);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNifFuncionario(nifFuncionario);
		funcionario.setNombreCompletoFuncionario(nombreFuncionario);
		
		Solicitante solicitante = new Solicitante();
		solicitante.setIdentificadorSolicitante(identificadorSolicitante);
		solicitante.setNombreSolicitante(nombreSolicitante);
		if(null!=unidadTramitadora)
		solicitante.setUnidadTramitadora(unidadTramitadora);
		else
		solicitante.setUnidadTramitadora(unidadTramit);	
		solicitante.setFinalidad(finalidad);
		solicitante.setConsentimiento(Consentimiento.fromString(consentimiento));
		solicitante.setFuncionario(funcionario);
		
		// Nuevos campos obligatorios para versión SVDDGPCIWS02
		if(null!=solicitudBean.getNumeroSolicitud() && !"".equals(solicitudBean.getNumeroSolicitud())){
			solicitante.setIdExpediente(solicitudBean.getNumeroSolicitud());
		}else{
			solicitante.setIdExpediente(idExpediente);
		}
		
		Procedimiento procedimiento = new Procedimiento();
		procedimiento.setCodProcedimiento(codProcedimiento);
		procedimiento.setNombreProcedimiento(nombreProcedimiento);
		solicitante.setProcedimiento(procedimiento);
		// Fin nuevos campos
		
		Titular titular = new Titular();
		titular.setDocumentacion(solicitudBean.getNif());		
		titular.setTipoDocumentacion(TipoDocumentacion.fromString(Constantes.TIPO_DOCUMENTO_DNI));
		titular.setApellido1(solicitudBean.getPrimerApellido());
		
		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
		datosGenericos.setSolicitante(solicitante);
		datosGenericos.setTitular(titular);		
		
		SolicitudTransmision solicitud = new SolicitudTransmision();
		solicitud.setDatosGenericos(datosGenericos);
		
		SolicitudTransmision[] transmisiones = new SolicitudTransmision[1];
		transmisiones[0] = solicitud;
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);
		
		PeticionSincrona peticionSincrona = new PeticionSincrona(atributos, solicitudes);
		
		logger.info("Realizando Consulta de Datos Nacimiento con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionSincrona));
	
		ScspwsProxy proxy = new ScspwsProxy(url);
		Respuesta respuesta = null;
		
		try{
			respuesta = proxy.peticionSincrona(peticionSincrona);
			identidadBean = toIntermediacionIdentidadBean(respuesta);
			logger.info("Datos de Identidad Obtenidos para el NIF: "+titular.getDocumentacion());
			logger.info(new BeanFormatter().format(identidadBean));
			
		}catch(RemoteException re){
			logger.error(" Error consultarDatosFechaNacimiento - error de conexion  respuesta :",re);
			throw new BusinessException("intermediacion.svdi.errorConexion");
		}catch(BusinessException be){
			logger.error(" Error consultarDatosFechaNacimiento - error de conexion  respuesta :",be);
		}
		
		return identidadBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosPersonalesManager#validarFechaNacimiento(es.map.ipsg.bean.SolicitudBean, es.map.ipsg.bean.UsuarioBean)
	 */
	public IntermediacionIdentidadBean validarFechaNacimiento(SolicitudBean solicitudBean, UsuarioBean usuarioBean){

		String nifFuncionario = usuarioBean.getNif();
		String nombreFuncionario = usuarioBean.getNombreCompleto();
		Date fechaNacimiento = solicitudBean.getFechaNacimiento();
		Long idSolicitud = solicitudBean.getId();
		IntermediacionIdentidadBean identidadBean = null;
		boolean errorWS = false;
		
		try {
			/*INI-TRA042*/
			identidadBean = consultarDatosFechaNacimiento(solicitudBean, nifFuncionario, nombreFuncionario, null);
			/*FIN-TRA042*/
		} catch (Exception e) {
			logger.error("Error al conectar con el WS.",e);
			errorWS = true;
		}
		
		
		// Error DNI no encontrado
		if(identidadBean!=null && identidadBean.getDescripcionError()!=null){
			identidadBean = null;
		}else if(identidadBean!=null){
			Date fechaNacimientoPolicia = identidadBean.getFechaNacimiento();
			SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
			
			if(fechaNacimiento != null){
				if(fechaNacimiento.equals(fechaNacimientoPolicia)){
					logger.info("Las fechas de nacimiento coinciden");
					identidadBean.setResultadoVerificacion(true);
					
					//Actualizar Solicitud, campo Fecha_Nacimiento_Verificada = 'A'
					solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_VERIFICADA);
					solicitud.setFechaNacimientoSvdi(null);
				}else{
					logger.info("Las fechas de nacimiento NO coinciden. FechaSolicitud = "+fechaNacimiento+". FechaPolicia = "+fechaNacimientoPolicia);
					//Actualizar Solicitud, campo Fecha_Nacimiento_Verificada = 'R'
					solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_NO_VERIFICADA);
					solicitud.setFechaNacimientoSvdi(fechaNacimientoPolicia);
				}				
			}else{
				logger.info("La Fecha de Nacimiento de la Solicitud es null. La Fecha de Nacimiento de la Policia = "+fechaNacimientoPolicia);
				//Actualizar Solicitud, campo Fecha_Nacimiento_Verificada = 'R'
				solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_NO_VERIFICADA);
			}
			solicitudComunDAO.update(solicitud);
		}
		
		if (errorWS) {
			SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
			//Se actualiza la fecha a ptd. Error en la comprobacion
			solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_NO_CONEXION_WS);
			solicitudComunDAO.update(solicitud);
			//throw new BusinessException("intermediacion.svdi.errorConexion");
		}
		
		return identidadBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosPersonalesManager#validarFechaNacimientoAuxiliar(es.map.ipsg.bean.SolicitudBean, es.map.ipsg.bean.UsuarioBean)
	 */
	public IntermediacionIdentidadBean validarFechaNacimientoAuxiliar(SolicitudBean solicitudBean, UsuarioBean usuarioBean){

		String nifFuncionario = usuarioBean.getNif();
		String nombreFuncionario = usuarioBean.getNombreCompleto();
		Date fechaNacimiento = solicitudBean.getFechaNacimiento();
		Long idSolicitud = solicitudBean.getId();
		IntermediacionIdentidadBean identidadBean = null;
		boolean errorWS = false;
		
		try {
			/*INI-TRA042*/
			identidadBean = consultarDatosFechaNacimiento(solicitudBean, nifFuncionario, nombreFuncionario, null);
			/*FIN-TRA042*/
		} catch (Exception e) {
			logger.error("Error al conectar con el WS.",e);
			errorWS = true;
		}
		
		
		// Error DNI no encontrado
		if(identidadBean!=null && identidadBean.getDescripcionError()!=null){
			identidadBean = null;
		}else if(identidadBean!=null){
			Date fechaNacimientoPolicia = identidadBean.getFechaNacimiento();
			SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
			
			if(fechaNacimiento != null){
				if(fechaNacimiento.equals(fechaNacimientoPolicia)){
					logger.info("Las fechas de nacimiento coinciden");
					identidadBean.setResultadoVerificacion(true);
					
					//Actualizar Solicitud, campo Fecha_Nacimiento_Verificada = 'A'
					solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_VERIFICADA);
					solicitud.setFechaNacimientoSvdi(null);
				}else{
					logger.info("Las fechas de nacimiento NO coinciden. FechaSolicitud = "+fechaNacimiento+". FechaPolicia = "+fechaNacimientoPolicia);
					//Actualizar Solicitud, campo Fecha_Nacimiento_Verificada = 'R'
					solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_NO_VERIFICADA);
					solicitud.setFechaNacimientoSvdi(fechaNacimientoPolicia);
				}				
			}else{
				logger.info("La Fecha de Nacimiento de la Solicitud es null. La Fecha de Nacimiento de la Policia = "+fechaNacimientoPolicia);
				//Actualizar Solicitud, campo Fecha_Nacimiento_Verificada = 'R'
				solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_NO_VERIFICADA);
			}
			solicitudComunAuxiliarDAO.update(solicitud);
		}
		
		if (errorWS) {
			SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
			//Se actualiza la fecha a ptd. Error en la comprobacion
			solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_NO_CONEXION_WS);
			solicitudComunAuxiliarDAO.update(solicitud);
			//throw new BusinessException("intermediacion.svdi.errorConexion");
		}
		
		return identidadBean;
	}
	
	/**
	 * To intermediacion identidad bean.
	 *
	 * @param respuesta el respuesta
	 * @return el intermediacion identidad bean
	 */
	private IntermediacionIdentidadBean toIntermediacionIdentidadBean(Respuesta respuesta){
		DatosTitular titular = respuesta.getTransmisiones().
				getTransmisionDatos(0).getDatosEspecificos().
				getRetorno().getDatosTitular();
		
		IntermediacionIdentidadBean identidad = new IntermediacionIdentidadBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		Date fechaNac = null;
		
		if(null!=titular.getDatosNacimiento()){
			try{
				fechaNac = sdf.parse(titular.getDatosNacimiento().getFecha());
			}catch(ParseException pe){
				logger.error(" Error consultarDatosFechaNacimiento - toIntermediacionIdentidadBean fechaNacimiento :"+ fechaNac,pe);
				throw new BusinessException("intermediacion.svdi.errorFechaNacimiento");
			}
		}else{
			identidad.setDescripcionError(respuesta.getTransmisiones()
					.getTransmisionDatos(0).getDatosEspecificos()
					.getRetorno().getEstado().getLiteralError());
		}
			
		identidad.setFechaNacimiento(fechaNac);
		
		return identidad;
		
	}

	/**
	 * Obtiene el solicitud comun DAO.
	 *
	 * @return el solicitud comun DAO
	 */
	public SolicitudComunDAO getSolicitudComunDAO() {
		return solicitudComunDAO;
	}

	/**
	 * Establece el solicitud comun DAO.
	 *
	 * @param solicitudComunDAO el nuevo solicitud comun DAO
	 */
	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
		this.solicitudComunDAO = solicitudComunDAO;
	}
	
	/**
	 * Obtiene el solicitud comun auxiliar DAO.
	 *
	 * @return el solicitud comun auxiliar DAO
	 */
	public SolicitudComunAuxiliarDAO getSolicitudComunAuxiliarDAO() {
		return solicitudComunAuxiliarDAO;
	}

	/**
	 * Establece el solicitud comun auxiliar DAO.
	 *
	 * @param solicitudComunAuxiliarDAO el nuevo solicitud comun auxiliar DAO
	 */
	public void setSolicitudComunAuxiliarDAO(SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO) {
		this.solicitudComunAuxiliarDAO = solicitudComunAuxiliarDAO;
	}


}
