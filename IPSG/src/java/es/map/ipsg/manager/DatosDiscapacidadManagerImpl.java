package es.map.ipsg.manager;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.dao.ParametrosConfiguracionDAO;
import es.map.ips.dao.SolicitudComunAuxiliarDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.ParametrosConfiguracion;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionDiscapacidadBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Util;
import es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma;
import es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Consulta;
import es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosEspecificos;
import es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado;
import es.redsara.intermediacion.scsp.esquemas.discapacidad.ws.ScspwsProxy;
import es.redsara.intermediacion.scsp.esquemas.discapacidad.ws.respuesta.Respuesta;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Atributos;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.SolicitudTransmision;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.SolicitudTransmisionDatosGenericos;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitudes;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.TipoDocumentacion;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Titular;


/**
 * El Class DatosDiscapacidadManagerImpl.
 *
 * @author everis
 */
public class DatosDiscapacidadManagerImpl implements DatosDiscapacidadManager {
	
	/** el properties. */
	private Properties properties;

	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DatosDiscapacidadManagerImpl.class);
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
	
	/** el parametros configuracion DAO. */
	private ParametrosConfiguracionDAO parametrosConfiguracionDAO;
	
	/**
	 * Consultar discapacidad.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param nifFuncionario el nif funcionario
	 * @param nombreFuncionario el nombre funcionario
	 * @param unidadTramitadora el unidad tramitadora
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 * @return el intermediacion discapacidad bean
	 */
	private IntermediacionDiscapacidadBean consultarDiscapacidad( ConvocatoriasBean convocatoriaBean, SolicitudBean solicitudBean, String nifFuncionario, String nombreFuncionario, String unidadTramitadora,SolicitudCcaaBean solicitudCcaaBean) {
		
		IntermediacionDiscapacidadBean discapacidadBean = null;
	    
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		String url = 						properties.getProperty("URL");
		String codigoCertificado = 			properties.getProperty("CCAADD_CODIGO_CERTIFICADO");
		String identificadorSolicitante = 	properties.getProperty("CCAADD_IDENTIFICADOR_SOLICITANTE");
		String nombreSolicitante = 			properties.getProperty("CCAADD_NOMBRE_SOLICITANTE");
		String finalidad = 					properties.getProperty("CCAADD_FINALIDAD");
		String unidadTramit = 			    properties.getProperty("CCAADD_UNIDAD_TRAMITADORA");
		String consentimiento = 			properties.getProperty("CCAADD_CONSENTIMIENTO");
		String idExpediente =				properties.getProperty("CCAADD_ID_EXPEDIENTE");
		String codProcedimiento =			properties.getProperty("CCAADD_COD_PROCEDIMIENTO");
		String nombreProcedimiento =		properties.getProperty("CCAADD_NOMBRE_PROCEDIMIENTO");

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
		
		// Nuevos campos obligatorios para version SVDDGPCIWS02
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
		titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(solicitudBean.getNif())));
		titular.setNombre(solicitudBean.getNombre());
		titular.setApellido1(solicitudBean.getPrimerApellido());
		titular.setApellido2(solicitudBean.getSegundoApellido());
		
		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
		datosGenericos.setSolicitante(solicitante);
		datosGenericos.setTitular(titular);		
		
		DatosEspecificos datosEspecificos=new DatosEspecificos();
		Consulta consulta = new Consulta();
		consulta.setCodigoProvincia(solicitudCcaaBean.getCodigoProvincia());
		consulta.setFechaConsulta(convocatoriaBean.getFechaBoe());
		consulta.setCodigoComunidadAutonoma(CodigoComunidadAutonoma.fromString(solicitudCcaaBean.getCodigoComunidad()));
		consulta.setConsentimientoTiposDiscapacidad(Resultado.S);
		datosEspecificos.setConsulta(consulta);
		
		SolicitudTransmision solicitud = new SolicitudTransmision();
		solicitud.setDatosGenericos(datosGenericos);
		solicitud.setDatosEspecificos(datosEspecificos);
		
		SolicitudTransmision[] transmisiones = new SolicitudTransmision[1];
		transmisiones[0] = solicitud;
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);
		
		PeticionSincrona peticionSincrona = new PeticionSincrona(atributos, solicitudes);
		
		logger.info("Realizando Consulta de Discapacidad con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionSincrona));
	
		ScspwsProxy proxy = new ScspwsProxy(url);
		Respuesta respuesta = null;
		
		
		try{
			respuesta = proxy.peticionSincrona(peticionSincrona);	
			discapacidadBean= toIntermediacionDiscapacidadBean(respuesta);
			logger.info("Datos de Identidad Obtenidos para el NIF: "+titular.getDocumentacion());
			logger.info(new BeanFormatter().format(discapacidadBean));
			
		}catch(RemoteException re){
			logger.error(" Error DatosDiscapacidadManagerImpl - consultarDiscapacidad - error conexion  respuesta:",re);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			//throw new BusinessException("intermediacion.svdi.errorConexion");
			
		}catch(BusinessException be){
			logger.error(" Error DatosDiscapacidadManagerImpl - consultarDiscapacidad - error conexion  respuesta:",be);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			//throw be;
		}
		
		return discapacidadBean;
	}

	/**
	 * Consultar discapacidad auxiliar.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param nifFuncionario el nif funcionario
	 * @param nombreFuncionario el nombre funcionario
	 * @param unidadTramitadora el unidad tramitadora
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 * @return el intermediacion discapacidad bean
	 */
	private IntermediacionDiscapacidadBean consultarDiscapacidadAuxiliar ( ConvocatoriasBean convocatoriaBean, SolicitudBean solicitudBean, String nifFuncionario, String nombreFuncionario, String unidadTramitadora,SolicitudCcaaAuxiliarBean solicitudCcaaBean) {
		
		IntermediacionDiscapacidadBean discapacidadBean = null;
	    
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		String url = 						properties.getProperty("URL");
		String codigoCertificado = 			properties.getProperty("CCAADD_CODIGO_CERTIFICADO");
		String identificadorSolicitante = 	properties.getProperty("CCAADD_IDENTIFICADOR_SOLICITANTE");
		String nombreSolicitante = 			properties.getProperty("CCAADD_NOMBRE_SOLICITANTE");
		String finalidad = 					properties.getProperty("CCAADD_FINALIDAD");
		String unidadTramit = 			    properties.getProperty("CCAADD_UNIDAD_TRAMITADORA");
		String consentimiento = 			properties.getProperty("CCAADD_CONSENTIMIENTO");
		String idExpediente =				properties.getProperty("CCAADD_ID_EXPEDIENTE");
		String codProcedimiento =			properties.getProperty("CCAADD_COD_PROCEDIMIENTO");
		String nombreProcedimiento =		properties.getProperty("CCAADD_NOMBRE_PROCEDIMIENTO");

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
		
		// Nuevos campos obligatorios para version SVDDGPCIWS02
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
		titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(solicitudBean.getNif())));
		titular.setNombre(solicitudBean.getNombre());
		titular.setApellido1(solicitudBean.getPrimerApellido());
		titular.setApellido2(solicitudBean.getSegundoApellido());
		
		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
		datosGenericos.setSolicitante(solicitante);
		datosGenericos.setTitular(titular);		
		
		DatosEspecificos datosEspecificos=new DatosEspecificos();
		Consulta consulta = new Consulta();
		consulta.setCodigoProvincia(solicitudCcaaBean.getCodigoProvincia());
		consulta.setFechaConsulta(convocatoriaBean.getFechaBoe());
		consulta.setCodigoComunidadAutonoma(CodigoComunidadAutonoma.fromString(solicitudCcaaBean.getCodigoComunidad()));
		consulta.setConsentimientoTiposDiscapacidad(Resultado.S);
		datosEspecificos.setConsulta(consulta);
		
		SolicitudTransmision solicitud = new SolicitudTransmision();
		solicitud.setDatosGenericos(datosGenericos);
		solicitud.setDatosEspecificos(datosEspecificos);
		
		SolicitudTransmision[] transmisiones = new SolicitudTransmision[1];
		transmisiones[0] = solicitud;
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);
		
		PeticionSincrona peticionSincrona = new PeticionSincrona(atributos, solicitudes);
		
		logger.info("Realizando Consulta de Discapacidad con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionSincrona));
	
		ScspwsProxy proxy = new ScspwsProxy(url);
		Respuesta respuesta = null;
		
		
		try{
			respuesta = proxy.peticionSincrona(peticionSincrona);	
			discapacidadBean= toIntermediacionDiscapacidadBean(respuesta);
			logger.info("Datos de Identidad Obtenidos para el NIF: "+titular.getDocumentacion());
			logger.info(new BeanFormatter().format(discapacidadBean));
			
		}catch(RemoteException re){
			logger.error(" Error DatosDiscapacidadManagerImpl - consultarDiscapacidad - error conexion  respuesta:",re);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			//throw new BusinessException("intermediacion.svdi.errorConexion");
			
		}catch(BusinessException be){
			logger.error(" Error DatosDiscapacidadManagerImpl - consultarDiscapacidad - error conexion  respuesta:",be);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			//throw be;
		}
		
		return discapacidadBean;
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#guardarEstadoFalloWsSolicitud(es.map.ipsg.bean.SolicitudBean)
	 */
	public void guardarEstadoFalloWsSolicitud(SolicitudBean solicitudBean) {
		if (solicitudBean != null && solicitudBean.getId() != null) {
			errorWsFNumerosaVerificado(solicitudBean.getId());
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#guardarEstadoFalloWsSolicitudAuxiliar(es.map.ipsg.bean.SolicitudBean)
	 */
	public void guardarEstadoFalloWsSolicitudAuxiliar(SolicitudBean solicitudBean) {
		if (solicitudBean != null && solicitudBean.getId() != null) {
			errorWsFNumerosaVerificadoAuxiliar(solicitudBean.getId());
		}
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#validarDiscapacidad(es.map.ipsg.bean.ConvocatoriasBean, es.map.ipsg.bean.SolicitudBean, es.map.ipsg.bean.UsuarioBean, es.map.ipsg.bean.SolicitudCcaaBean)
	 */
	/*INI-TRA042*/
	public IntermediacionDiscapacidadBean validarDiscapacidad(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean,SolicitudCcaaBean solicitudCcaaBean, String unidadTramitadora){
	/*FIN-TRA042*/

		String nifFuncionario = usuarioBean.getNif();
		String nombreFuncionario = usuarioBean.getNombreCompleto();
		
		IntermediacionDiscapacidadBean discapacidadBean = consultarDiscapacidad(convocatoriaBean, solicitudBean, nifFuncionario, nombreFuncionario, unidadTramitadora, solicitudCcaaBean);
		
		if(discapacidadBean != null && discapacidadBean.getGradoDiscapacidad() != null){
			ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
			parametrosConfiguracionQuery.addIdIn(Constantes.PARAMETRO_CONFIGURACION_ID_PORCENTAJE_EXENTO);
			ParametrosConfiguracion gradoDiscapacidad = parametrosConfiguracionDAO.searchUnique(parametrosConfiguracionQuery);
			if((discapacidadBean.getGradoDiscapacidad())>=(Integer.valueOf(gradoDiscapacidad.getValorCampo()))){
				discapacidadBean.setResultadoVerificacion(true);
			}else
				discapacidadBean.setResultadoVerificacion(false);
		}
				
		return discapacidadBean;
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#validarDiscapacidadAuxiliar(es.map.ipsg.bean.ConvocatoriasBean, es.map.ipsg.bean.SolicitudBean, es.map.ipsg.bean.UsuarioBean, es.map.ipsg.bean.SolicitudCcaaAuxiliarBean)
	 */
	/*INI-TRA042*/
	public IntermediacionDiscapacidadBean validarDiscapacidadAuxiliar(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean,SolicitudCcaaAuxiliarBean solicitudCcaaBean, String unidadTramitadora){
	/*FIN-TRA042*/

		String nifFuncionario = usuarioBean.getNif();
		String nombreFuncionario = usuarioBean.getNombreCompleto();
		
		IntermediacionDiscapacidadBean discapacidadBean = consultarDiscapacidadAuxiliar(convocatoriaBean, solicitudBean, nifFuncionario, nombreFuncionario, unidadTramitadora, solicitudCcaaBean);
		
		if(discapacidadBean != null && discapacidadBean.getGradoDiscapacidad() != null){
			ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
			parametrosConfiguracionQuery.addIdIn(Constantes.PARAMETRO_CONFIGURACION_ID_PORCENTAJE_EXENTO);
			ParametrosConfiguracion gradoDiscapacidad = parametrosConfiguracionDAO.searchUnique(parametrosConfiguracionQuery);
			if((discapacidadBean.getGradoDiscapacidad())>=(Integer.valueOf(gradoDiscapacidad.getValorCampo()))){
				discapacidadBean.setResultadoVerificacion(true);
			}else
				discapacidadBean.setResultadoVerificacion(false);
		}
				
		return discapacidadBean;
		
	}
	
	/**
	 * To intermediacion discapacidad bean.
	 *
	 * @param respuesta el respuesta
	 * @return el intermediacion discapacidad bean
	 */
	private IntermediacionDiscapacidadBean toIntermediacionDiscapacidadBean(Respuesta respuesta){
				
		IntermediacionDiscapacidadBean discapacidad = new IntermediacionDiscapacidadBean();
		Integer gradoDiscapacidad=null;
        
		if(null!= respuesta.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getCertificadoDatosDiscapacidad())
			
			gradoDiscapacidad=(Integer)respuesta.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().
								getRetorno().getCertificadoDatosDiscapacidad().getGradoDiscapacidad();
		else{
			
			logger.error("Error: grado de Discapacidad nulo.");
			discapacidad.setDescripcionError((respuesta.getTransmisiones()
				.getTransmisionDatos(0).getDatosEspecificos().getRetorno().getEstado().getLiteralError()));
		}
		discapacidad.setGradoDiscapacidad(gradoDiscapacidad);
		return discapacidad;
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#aprobarDiscapacidadVerificado(java.lang.Long)
	 */
	public void aprobarDiscapacidadVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_VERIFICADO);
		solicitudComunDAO.update(solicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#aprobarDiscapacidadVerificadoAuxiliar(java.lang.Long)
	 */
	public void aprobarDiscapacidadVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_VERIFICADO);
		solicitudComunAuxiliarDAO.update(solicitud);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#rechazarDiscapacidadVerificado(java.lang.Long)
	 */
	public void rechazarDiscapacidadVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_VERIFICADO);
		solicitudComunDAO.update(solicitud);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#rechazarDiscapacidadVerificadoAuxiliar(java.lang.Long)
	 */
	public void rechazarDiscapacidadVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_VERIFICADO);
		solicitudComunAuxiliarDAO.update(solicitud);		
	}
	
	/**
	 *  Metodo que evalua el tipo de documento pasado por parametro.
	 *
	 * @param documento el documento
	 * @return String NIF o NIE
	 */
	private String evaluarTipoDocumento(String documento){
		
		String tipoDoc = Constantes.TIPO_DOCUMENTO_NIF;
		
		if(Util.esNie(documento)){
			tipoDoc = Constantes.TIPO_DOCUMENTO_NIE;
		}
		
		return tipoDoc;
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

	/**
	 * Obtiene el parametros configuracion DAO.
	 *
	 * @return el parametros configuracion DAO
	 */
	public ParametrosConfiguracionDAO getParametrosConfiguracionDAO() {
		return parametrosConfiguracionDAO;
	}


	/**
	 * Establece el parametros configuracion DAO.
	 *
	 * @param parametrosConfiguracionDAO el nuevo parametros configuracion DAO
	 */
	public void setParametrosConfiguracionDAO(
			ParametrosConfiguracionDAO parametrosConfiguracionDAO) {
		this.parametrosConfiguracionDAO = parametrosConfiguracionDAO;
	}


	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#consultarDiscapacidadAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario, es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento, es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante, java.util.List, java.util.List, java.lang.String)
	 */
	@Override
	public String consultarDiscapacidadAsincrona(Funcionario funcionario,Procedimiento procedimiento, Solicitante solicitantePeticion,List<SolicitudBean> listaSolicitudesDiscapacidad,List<SolicitudCcaaBean> listaSolicitudesCcaaDiscapacidad,String codigoCertificado) {
 
		String idPeticionDiscapacidad = null;
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		Atributos atributos = new Atributos();
		atributos.setCodigoCertificado(codigoCertificado);

		SolicitudTransmision[] transmisiones = new SolicitudTransmision[listaSolicitudesDiscapacidad.size()];
		
		for (int i =0; i<listaSolicitudesDiscapacidad.size(); i++) {
			Solicitante solicitante = null;
			try {
				solicitante = (Solicitante) solicitantePeticion.clone();
			} catch (CloneNotSupportedException e) {
				logger.error(" Error DatosDiscapacidadManagerImpl - consultarDiscapacidadAsincrona - solicitante:",e);
			}
			
			if(null!=listaSolicitudesDiscapacidad.get(i).getNumeroSolicitud() && !"".equals(listaSolicitudesDiscapacidad.get(i).getNumeroSolicitud())){
				solicitante.setIdExpediente(listaSolicitudesDiscapacidad.get(i).getNumeroSolicitud());
			}else{
				solicitante.setIdExpediente(properties.getProperty("CCAADD_ID_EXPEDIENTE"));
			}
			
			Titular titular = new Titular();
			titular.setDocumentacion(listaSolicitudesDiscapacidad.get(i).getNif());		
			titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(listaSolicitudesDiscapacidad.get(i).getNif())));
			titular.setNombre(listaSolicitudesDiscapacidad.get(i).getNombre());
			titular.setApellido1(listaSolicitudesDiscapacidad.get(i).getPrimerApellido());
			titular.setApellido2(listaSolicitudesDiscapacidad.get(i).getSegundoApellido());
			
			SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
			datosGenericos.setSolicitante(solicitante);
			datosGenericos.setTitular(titular);		
			
			DatosEspecificos datosEspecificos=new DatosEspecificos();
			Consulta consulta = new Consulta();
			consulta.setCodigoProvincia(listaSolicitudesCcaaDiscapacidad.get(i).getCodigoProvincia());
			consulta.setFechaConsulta(listaSolicitudesDiscapacidad.get(i).getFechaBoe());
			consulta.setCodigoComunidadAutonoma(CodigoComunidadAutonoma.fromString(listaSolicitudesCcaaDiscapacidad.get(i).getCodigoComunidad()));
			consulta.setConsentimientoTiposDiscapacidad(Resultado.S);
			datosEspecificos.setConsulta(consulta);
			
			SolicitudTransmision solicitud = new SolicitudTransmision();
			solicitud.setDatosGenericos(datosGenericos);
			solicitud.setDatosEspecificos(datosEspecificos);
			
			transmisiones[i] = solicitud;
													
		}
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);	
		
		PeticionAsincrona peticionAsincrona = new PeticionAsincrona(atributos, solicitudes);
						
		logger.info("Realizando Consulta de Discapacidad con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionAsincrona));
		
		
		try{
			ScspwsProxy proxy = new ScspwsProxy(properties.getProperty("URL"));
			es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion confirmacionPeticion = proxy.peticionAsincrona(peticionAsincrona);
			if(null!= confirmacionPeticion){
				idPeticionDiscapacidad=confirmacionPeticion.getAtributos().getIdPeticion();
			}
			
		}catch(RemoteException re){
			logger.error(" Error DatosDiscapacidadManagerImpl - consultarDiscapacidadAsincrona - confirmacion Peticion:",re);
			//guardarEstadoFalloWsLista(listaSolicitudesDiscapacidad);
			//throw new BusinessException(re);
			
		}catch(BusinessException be){
			logger.error(" Error DatosDiscapacidadManagerImpl - consultarDiscapacidadAsincrona - confirmacion Peticion:",be);
			//guardarEstadoFalloWsLista(listaSolicitudesDiscapacidad);
			//throw be;
		}
		
		return idPeticionDiscapacidad;
		
	}

	/**
	 * Error ws F numerosa verificado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void errorWsFNumerosaVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_CONEXION_WS);
		solicitudComunDAO.update(solicitud);		
	}
	
	/**
	 * Error ws F numerosa verificado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void errorWsFNumerosaVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_CONEXION_WS);
		solicitudComunAuxiliarDAO.update(solicitud);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#guardarEstadoPendiente(java.lang.Long)
	 */
	@Override
	public void guardarEstadoPendiente(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_COMPROBADO);
		solicitudComunDAO.update(solicitud);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#guardarEstadoPendienteAuxiliar(java.lang.Long)
	 */
	@Override
	public void guardarEstadoPendienteAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_COMPROBADO);
		solicitudComunAuxiliarDAO.update(solicitud);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#guardarEstadoFalloWsLista(java.util.List)
	 */
	public void guardarEstadoFalloWsLista(List<SolicitudBean> listaSolicitudesDiscapacidad) {
		for (SolicitudBean SolicitudDiscapacidad : listaSolicitudesDiscapacidad) {
			if (SolicitudDiscapacidad != null && SolicitudDiscapacidad.getId() != null && SolicitudDiscapacidad.getId() > 0) {
				errorWsFNumerosaVerificado(SolicitudDiscapacidad.getId());
			}	
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#guardarEstadoPendienteList(java.util.List)
	 */
	@Override
	public void guardarEstadoPendienteList(List<SolicitudBean> listaSolicitudesDiscapacidad) {
		for (SolicitudBean SolicitudDiscapacidad : listaSolicitudesDiscapacidad) {
			if (SolicitudDiscapacidad != null && SolicitudDiscapacidad.getId() != null && SolicitudDiscapacidad.getId() > 0) {
				guardarEstadoPendiente(SolicitudDiscapacidad.getId());
			}	
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#guardarEstadoPendienteListAux(java.util.List)
	 */
	@Override
	public void guardarEstadoPendienteListAux(List<SolicitudBean> listaSolicitudesDiscapacidad) {
		for (SolicitudBean SolicitudDiscapacidad : listaSolicitudesDiscapacidad) {
			if (SolicitudDiscapacidad != null && SolicitudDiscapacidad.getId() != null && SolicitudDiscapacidad.getId() > 0) {
				guardarEstadoPendienteAuxiliar(SolicitudDiscapacidad.getId());
			}	
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDiscapacidadManager#guardarEstadoFalloWsAuxLista(java.util.List)
	 */
	@Override
	public void guardarEstadoFalloWsAuxLista(List<SolicitudBean> listaSolicitudesDiscapacidad) {
		for (SolicitudBean SolicitudDiscapacidad : listaSolicitudesDiscapacidad) {
			if (SolicitudDiscapacidad != null && SolicitudDiscapacidad.getId() != null && SolicitudDiscapacidad.getId() > 0) {
				errorWsFNumerosaVerificadoAuxiliar(SolicitudDiscapacidad.getId());
			}	
		}
	}

}
