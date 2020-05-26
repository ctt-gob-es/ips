package es.map.ipsg.manager;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

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
import es.map.ipsg.bean.ImputacionesBean;
import es.map.ipsg.bean.IntermediacionDesempleoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.IpsUtils;
import es.map.ipsg.util.Util;
import es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputacionesDatosEconomicos;
import es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Consulta;
import es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.DatosEspecificos;
import es.redsara.intermediacion.scsp.esquemas.sepe.ws.ScspwsProxy;
import es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta;
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
 * El Class DatosDesempleoManagerImpl.
 *
 * @author everis
 */
public class DatosDesempleoManagerImpl implements DatosDesempleoManager {
	
	private static final String TIPO_RESPUESTA_IR = "IR";

	private static final String TIPO_RESPUESTA_IM = "IM";

	private Properties properties;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DatosDesempleoManagerImpl.class);
	
	/** La constante STRING_PROPERTIESBEAN. */
	private static final String STRING_PROPERTIESBEAN = "propertiesBean";
	
	/** La constante STRING_SVDSEPE_ID_EXPEDIENTE. */
	private static final String STRING_SVDSEPE_ID_EXPEDIENTE = "SVDSEPE_ID_EXPEDIENTE";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT= "dd/MM/yyyy";
	
	/** La constante STRING_SIMPLEDATEFORMATMONTHYEAR. */
	private static final String STRING_SIMPLEDATEFORMATMONTHYEAR = "MM/yyyy";
	
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
	
	/** el parametros configuracion DAO. */
	private ParametrosConfiguracionDAO parametrosConfiguracionDAO;
	
	private IntermediacionDesempleoBean consultarDesempleo( ConvocatoriasBean convocatoriaBean, SolicitudBean solicitudBean, String nifFuncionario, String nombreFuncionario, String unidadTramitadora, boolean soloImporte) {
		
		IntermediacionDesempleoBean desempleoBean = null;
	    String fechaConsulta = "";
//	    String fechafinal = "";
//	    String fechainicio = "";
	    
	    properties = (Properties) ApplicationContextProvider.getInstance().getBean(STRING_PROPERTIESBEAN);
	    
		String url = 						properties.getProperty("URL");
		String codigoCertificado = 			properties.getProperty("SVDSEPE_CODIGO_CERTIFICADO");
		String codCertificado = 			properties.getProperty("SVDSEPEIMP_CODIGO_CERTIFICADO");
		String identificadorSolicitante = 	properties.getProperty("SVDSEPE_IDENTIFICADOR_SOLICITANTE");
		String nombreSolicitante = 			properties.getProperty("SVDSEPE_NOMBRE_SOLICITANTE");
		String finalidad = 					properties.getProperty("SVDSEPE_FINALIDAD");
		String unidadTramit = 			    properties.getProperty("SVDSEPE_UNIDAD_TRAMITADORA");
		String consentimiento = 			properties.getProperty("SVDSEPE_CONSENTIMIENTO");
		String idExpediente =				properties.getProperty(STRING_SVDSEPE_ID_EXPEDIENTE);
		String codProcedimiento =			properties.getProperty("SVDSEPE_COD_PROCEDIMIENTO");
		String codProcedimientoImporte =	properties.getProperty("SVDSEPE_COD_PROCEDIMIENTO_IMP");
		String nombreProcedimiento =		properties.getProperty("SVDSEPE_NOMBRE_PROCEDIMIENTO");

		Atributos atributosDias = new Atributos();
		atributosDias.setCodigoCertificado(codigoCertificado);
		
		Atributos atributosImporte = new Atributos();
		atributosImporte.setCodigoCertificado(codCertificado);
		
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
		titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(solicitudBean.getNif())));
		titular.setNombre(solicitudBean.getNombre());
		titular.setApellido1(solicitudBean.getPrimerApellido());
		
		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
		datosGenericos.setSolicitante(solicitante);
		datosGenericos.setTitular(titular);	
				
		es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.DatosEspecificos datosEspecificosDias = new DatosEspecificos();
		Consulta consultaFechaConcreta = new Consulta();
	    try {
	    	Date date = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT).parse(convocatoriaBean.getFechaBoe());
	    	fechaConsulta = new SimpleDateFormat("yyyyMMdd").format(date);
	    	consultaFechaConcreta.setFechaConsulta(fechaConsulta);
			datosEspecificosDias.setConsulta(consultaFechaConcreta);
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	    	logger.error(" Error DatosDesempleoManagerImpl -  consultarDesempleo - parsear fecha consulta",e);
	    }
	    
	    es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificos datosEspecificosImporte = new es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificos();

		///-----Peticion para consultar los dias del demandante de empleo-------///
		SolicitudTransmision solicitudDias = new SolicitudTransmision();
		solicitudDias.setDatosGenericos(datosGenericos);
		solicitudDias.setDatosEspecificos(datosEspecificosDias);
		
		SolicitudTransmision[] transmisionesDias = new SolicitudTransmision[1];
		transmisionesDias[0] = solicitudDias;
		
		Solicitudes solicitudesDias = new Solicitudes();
		solicitudesDias.setSolicitudTransmision(transmisionesDias);
		
		PeticionSincrona peticionSincronaDias = new PeticionSincrona(atributosDias, solicitudesDias);
		
		logger.info("Realizando Consulta de Desempleo con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionSincronaDias));
		
		es.redsara.intermediacion.scsp.esquemas.sepe.ws.ScspwsProxy proxyDias = new ScspwsProxy(url);
		es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta respuestaDias = null;
		
		es.redsara.intermediacion.scsp.esquemas.importe.ws.ScspwsProxy proxyImporte = new es.redsara.intermediacion.scsp.esquemas.importe.ws.ScspwsProxy(url);
		es.redsara.intermediacion.scsp.esquemas.importe.ws.respuesta.Respuesta respuestaImporte = null;
		
		try{
			//RESPUESTA DIAS DESEMPLEO
			if (!soloImporte) {
				respuestaDias = proxyDias.peticionSincrona(peticionSincronaDias);	
			}
			
		///-----Peticion para consultar el salario del demandante de empleo-------///
			//Datos genericos IMPORTE INI
		
			titular = new Titular();
			//titular.setDocumentacion("99999357A");//DATO DE PRUEBA
			titular.setDocumentacion(solicitudBean.getNif());
			titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(solicitudBean.getNif())));
			datosGenericos.setTitular(titular);
		
			datosGenericos.getSolicitante().getProcedimiento().setCodProcedimiento(codProcedimientoImporte);
			
			//Datos genericos IMPORTE FIN
			//Datos especificos IMPORTE INI
			//INI - TRA-059 - Ejercicio renta
			String renta = recuperarEjercicioRenta();
			try {
				datosEspecificosImporte.setEjercicio(Integer.parseInt(renta));
				//FIN - TRA-059 - Ejercicio renta
			} catch (NumberFormatException e) {
				logger.error("Error al obtener el ejercicio de la convocatoria.");
			}	
			SolicitudTransmision solicitudImporte = new SolicitudTransmision();
			solicitudImporte.setDatosGenericos(datosGenericos);
			solicitudImporte.setDatosEspecificos(datosEspecificosImporte);
			
			SolicitudTransmision[] transmisionesImporte = new SolicitudTransmision[1];
			transmisionesImporte[0] = solicitudImporte;
			
			Solicitudes solicitudesImporte = new Solicitudes();
			solicitudesImporte.setSolicitudTransmision(transmisionesImporte);
			//Datos especificos IMPORTE FIN
						
			PeticionSincrona peticionSincronaImporte = new PeticionSincrona(atributosImporte, solicitudesImporte);
					
			logger.info("Realizando Consulta de Nivel Renta con los siguientes datos:");
			logger.info(new BeanFormatter().format(peticionSincronaImporte));
			//RESPUESTA IMPORTE
			respuestaImporte = proxyImporte.peticionSincrona(peticionSincronaImporte);
			desempleoBean= toIntermediacionDesempleoBean(respuestaDias, respuestaImporte, convocatoriaBean, renta);
			logger.info("Datos de Identidad Obtenidos para el NIF: "+titular.getDocumentacion());			logger.info(new BeanFormatter().format(desempleoBean));
				
		}catch(RemoteException re){
			logger.error(" Error DatosDesempleoManagerImpl -  consultarDesempleo - error conexion",re);
			logger.error(re.getMessage());
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			throw new BusinessException("intermediacion.svdi.errorConexion");
			
		}catch(BusinessException be){
			logger.error(" Error DatosDesempleoManagerImpl -  consultarDesempleo - error conexion",be);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			throw be;
		}
		
		return desempleoBean;
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#guardarEstadoFalloWsSolicitud(es.map.ipsg.bean.SolicitudBean)
	 */
	public void guardarEstadoFalloWsSolicitud(SolicitudBean solicitudBean) {
		if (solicitudBean != null && solicitudBean.getId() != null) {
			errorWsDesempleado(solicitudBean.getId());
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#guardarEstadoFalloWsSolicitudAuxiliar(es.map.ipsg.bean.SolicitudBean)
	 */
	public void guardarEstadoFalloWsSolicitudAuxiliar(SolicitudBean solicitudBean) {
		if (solicitudBean != null && solicitudBean.getId() != null) {
			errorWsDesempleadoAuxiliar(solicitudBean.getId());
		}
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#validarDesempleoSalario(es.map.ipsg.bean.ConvocatoriasBean, es.map.ipsg.bean.SolicitudBean, es.map.ipsg.bean.UsuarioBean)
	 */
	/*INI-TRA042*/
	public IntermediacionDesempleoBean validarDesempleoSalario(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean, boolean soloImporte, String unidadTramitadora){
	/*FIN-TRA042*/

		String nifFuncionario = usuarioBean.getNif();
		String nombreFuncionario = usuarioBean.getNombreCompleto();
		
		IntermediacionDesempleoBean desempleoBean = consultarDesempleo(convocatoriaBean, solicitudBean, nifFuncionario, nombreFuncionario, unidadTramitadora, soloImporte);
		
		if (desempleoBean.getDiasDemandanteEmpleo()!=null){
			if(desempleoBean.getDiasDemandanteEmpleo()>=30){
				logger.info("Demandante de empleo al menos un mes anterior a la fecha de la convocatoria");
				desempleoBean.setResultadoVerificacionDias(true);
			}else if (desempleoBean.getDiasDemandanteEmpleo().toString()==""){
				desempleoBean.setResultadoVerificacionDias(false);
				desempleoBean.setDiasDemandanteEmpleo(0);
			}
			else{
				logger.info("Demandante de empleo inferior a un mes anterior a la fecha de la convocatoria");
				desempleoBean.setResultadoVerificacionDias(false);
			}
		}else{
			logger.info("No tiene dias de demandante de empleo procedente del SEPE");
			desempleoBean.setResultadoVerificacionDias(false);
			desempleoBean.setDiasDemandanteEmpleo(0);
		}
		
		if (desempleoBean.getSalarioDemandanteEmpleo()!=null){
			ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
			parametrosConfiguracionQuery.addIdIn(Constantes.PARAMETRO_CONFIGURACION_ID_SALARIO_MINIMO);
			ParametrosConfiguracion parametros = parametrosConfiguracionDAO.searchUnique(parametrosConfiguracionQuery);
			if (desempleoBean.getSalarioDemandanteEmpleo() == 0f || desempleoBean.getSalarioDemandanteEmpleo().toString()==""){
			desempleoBean.setResultadoVerificacionSalario(false);
			desempleoBean.setSalarioDemandanteEmpleo(Float.parseFloat("0"));
			} else if(desempleoBean.getSalarioDemandanteEmpleo()>Float.valueOf(parametros.getValorCampo())){
				logger.info("Salario percibido superior al Salario Mínimo Interprofesional");
				desempleoBean.setResultadoVerificacionSalario(false);
			}else{
				logger.info("Salario percibido inferior o igual al Salario Mínimo Interprofesional");
				desempleoBean.setResultadoVerificacionSalario(true);
			}
		}else{
			logger.info("No tiene salario procedente del SEPE");
			desempleoBean.setResultadoVerificacionSalario(true);
		}
		
		return desempleoBean;
		
	}
	
	/**
	 * To intermediacion desempleo bean.
	 *
	 * @param respuestaDias el respuesta dias
	 * @param respuestaImporte el respuesta importe
	 * @param fechaBoe el fecha boe
	 * @return el intermediacion desempleo bean
	 */
	private IntermediacionDesempleoBean toIntermediacionDesempleoBean(Respuesta respuestaDias, es.redsara.intermediacion.scsp.esquemas.importe.ws.respuesta.Respuesta respuestaImporte, ConvocatoriasBean convocatoriasBean, String renta){
				
		IntermediacionDesempleoBean desempleo = new IntermediacionDesempleoBean();
		String fechaUltimaInscripcion = "";
		Integer diasDemandanteEmpleoTotales = null;
		Float salarioEmpleo = 0f;

		if (respuestaDias != null) {
			if(respuestaDias.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos()!= null && 
				respuestaDias.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno() != null && 
				respuestaDias.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getDemandanteEmpleo()!=null && 
				respuestaDias.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getDemandanteEmpleo().getInscrito() != null &&
				respuestaDias.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getDemandanteEmpleo().getInscrito().getValue().equals("S")){
		
				fechaUltimaInscripcion = respuestaDias.getTransmisiones().getTransmisionDatos(0).
						getDatosEspecificos().getRetorno().getDemandanteEmpleo().getFechaUltimaInscripcion();
				//Se sacan los dias entre la fecha de la ultima inscripcion y la fecha de la convocatoria
				try {
					Date fechaConvocatoria = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT).parse(convocatoriasBean.getFechaBoe());
					if (!StringUtils.isEmpty(fechaUltimaInscripcion)) {
						Date fechaInscripcion = new SimpleDateFormat("yyyyMMdd").parse(fechaUltimaInscripcion);
						
						if (fechaInscripcion.before(fechaConvocatoria)) {
							int diasDemandante = 0;
							diasDemandante = respuestaDias.getTransmisiones().getTransmisionDatos(0).
									getDatosEspecificos().getRetorno().getDemandanteEmpleo().getLargaDuracion().getDiasDemandanteEmpleo();
							
							Date fechaFinInscripcion = IpsUtils.sumaDias(fechaInscripcion, diasDemandante);
							
							if (fechaFinInscripcion.before(fechaConvocatoria)) {
								diasDemandanteEmpleoTotales = diasDemandante;
							} else {
								diasDemandanteEmpleoTotales =(int) ((fechaConvocatoria.getTime()-fechaInscripcion.getTime())/86400000);
							}
						}
					}
				} catch (ParseException e) {
					logger.error("Error al obtener los dias de desempleo del demandante.", e);
				}
			} else{
				desempleo.setDescripcionErrorDias(respuestaDias.getTransmisiones()
						.getTransmisionDatos(0).getDatosEspecificos()
						.getRetorno().getEstado().getLiteralError());
			}
		}	
		
		//RESPUESTA NIVEL RENTA INI
		if(respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getIrpf() != null
		&&	respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getIrpf().getNivelRenta() != null
		&& respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getIrpf().getNivelRenta().getNREnteros() > 0){
	
			if (respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera() != null 
					&& !StringUtils.isEmpty(respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera().getTipoRespuesta()) 
					&& respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera().getTipoRespuesta().equals(TIPO_RESPUESTA_IR))	{
	
					String result=Long.toString(respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getIrpf().getNivelRenta().getNREnteros());
					
					if (respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getIrpf().getNivelRenta().getNRDecimales() > 0) {
						result+="."+Long.toString(respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getIrpf().getNivelRenta().getNRDecimales());
					}
					
					salarioEmpleo = Float.valueOf(result);	
				  
			}
			desempleo.setSalarioDemandanteEmpleo(salarioEmpleo);
		} else if(respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera() != null 
				&& !StringUtils.isEmpty(respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera().getTipoRespuesta()) 
				&& respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera().getTipoRespuesta().equals(TIPO_RESPUESTA_IM)) {
			List<ImputacionesBean> listaImputaciones = new ArrayList<ImputacionesBean>();
			
			for(DatosEspecificosImputacionesDatosEconomicos imputacion: respuestaImporte.getTransmisiones().getTransmisionDatos()[0].getDatosEspecificos().getImputaciones().getDatosEconomicos()) {
				ImputacionesBean imputacionBean = new ImputacionesBean();
				imputacionBean.setLiteral(imputacion.getLiteral());
				imputacionBean.setEnteros(imputacion.getEnteros());
				imputacionBean.setDecimales(imputacion.getDecimales());
				listaImputaciones.add(imputacionBean);
			}
			desempleo.setListaImputaciones(listaImputaciones);
			desempleo.setTipoIM(true);
		} else {
			if (respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos() != null 
					&& respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera() != null
					&& !StringUtils.isEmpty(respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera().getDescripcionError()))
			desempleo.setDescripcionErrorImporte(respuestaImporte.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getCabecera().getDescripcionError());
			
		}
		//RESPUESTA NIVEL RENTA FIN
		
		desempleo.setSalarioDemandanteEmpleo(salarioEmpleo);
		desempleo.setDiasDemandanteEmpleo((diasDemandanteEmpleoTotales != null && diasDemandanteEmpleoTotales > 0)?diasDemandanteEmpleoTotales:0);
		//INI - TRA-059 - ejercicio de la renta
		desempleo.setEjercicio(renta);
		//FIN - TRA-059 - ejercicio de la renta

		return desempleo;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#aprobarDesempleoVerificado(java.lang.Long)
	 */
	public void aprobarDesempleoVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDesempleoVerificado(Constantes.DESEMPLEO_VERIFICADO);
		solicitudComunDAO.update(solicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#aprobarDesempleoVerificadoAuxiliar(java.lang.Long)
	 */
	public void aprobarDesempleoVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDesempleoVerificado(Constantes.DESEMPLEO_VERIFICADO);
		solicitudComunAuxiliarDAO.update(solicitud);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#rechazarDesempleoVerificado(java.lang.Long)
	 */
	public void rechazarDesempleoVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDesempleoVerificado(Constantes.DESEMPLEO_NO_VERIFICADO);
		solicitudComunDAO.update(solicitud);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#rechazarDesempleoVerificadoAuxiliar(java.lang.Long)
	 */
	public void rechazarDesempleoVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDesempleoVerificado(Constantes.DESEMPLEO_NO_VERIFICADO);
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
	 * @see es.map.ipsg.manager.DatosDesempleoManager#consultarDesempleoDiasAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario, es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento, es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante, java.util.List, java.lang.String)
	 */
	@Override
	public String consultarDesempleoDiasAsincrona(Funcionario funcionario,Procedimiento procedimiento, Solicitante solicitantePeticion,List<SolicitudBean> listaSolicitudesDesempleo,String codigoCertificado) {

		
		String idPeticionDesempleoDias = null;
		String fechaConsulta = null;
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean(STRING_PROPERTIESBEAN);
		
		Atributos atributos = new Atributos();
		atributos.setCodigoCertificado(codigoCertificado);

		SolicitudTransmision[] transmisiones = new SolicitudTransmision[listaSolicitudesDesempleo.size()];
		
		for (int i =0; i<listaSolicitudesDesempleo.size(); i++) {
			Solicitante solicitante = null;
			try {
				solicitante = (Solicitante) solicitantePeticion.clone();
			} catch (CloneNotSupportedException e) {
				logger.error(" Error DatosDesempleoManagerImpl - consultarDesempleoDiasAsincrona - solicitante",e);
			}
			
			if(null!=listaSolicitudesDesempleo.get(i).getNumeroSolicitud() && !"".equals(listaSolicitudesDesempleo.get(i).getNumeroSolicitud())){
				solicitante.setIdExpediente(listaSolicitudesDesempleo.get(i).getNumeroSolicitud());
			}else{
				solicitante.setIdExpediente(properties.getProperty(STRING_SVDSEPE_ID_EXPEDIENTE));
			}
			
			Titular titular = new Titular();
			titular.setDocumentacion(listaSolicitudesDesempleo.get(i).getNif());		
			titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(listaSolicitudesDesempleo.get(i).getNif())));
			titular.setNombre(listaSolicitudesDesempleo.get(i).getNombre());
			titular.setApellido1(listaSolicitudesDesempleo.get(i).getPrimerApellido());
			
			SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
			datosGenericos.setSolicitante(solicitante);
			datosGenericos.setTitular(titular);	
			
					
			es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.DatosEspecificos datosEspecificosDias = new DatosEspecificos();
			Consulta consultaFechaConcreta = new Consulta();
		    try {
		    	Date date = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT).parse(listaSolicitudesDesempleo.get(i).getFechaBoe());
		        fechaConsulta = new SimpleDateFormat("yyyyMMdd").format(date);
		    	consultaFechaConcreta.setFechaConsulta(fechaConsulta);
				datosEspecificosDias.setConsulta(consultaFechaConcreta);
		    } catch (ParseException e) {
		        // TODO Auto-generated catch block
		    	logger.error(" Error DatosDesempleoManagerImpl - consultarDesempleoDiasAsincrona - parseo fecha",e);
		    	
		    }
		    
		    SolicitudTransmision solicitudDias = new SolicitudTransmision();
			solicitudDias.setDatosGenericos(datosGenericos);
			solicitudDias.setDatosEspecificos(datosEspecificosDias);
			
			transmisiones[i] = solicitudDias;
			
		}
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);	
		
		PeticionAsincrona peticionAsincrona = new PeticionAsincrona(atributos, solicitudes);
						
		logger.info("Realizando Consulta de Desempleo dias con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionAsincrona));
		
		try{
			es.redsara.intermediacion.scsp.esquemas.sepe.ws.ScspwsProxy proxyDias = new ScspwsProxy(properties.getProperty("URL"));
			es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion confirmacionPeticion = proxyDias.peticionAsincrona(peticionAsincrona);
			if(null!= confirmacionPeticion){
				idPeticionDesempleoDias=confirmacionPeticion.getAtributos().getIdPeticion();
			}
			
		}catch(RemoteException re){
			logger.error(" Error DatosDesempleoManagerImpl - consultarDesempleoDiasAsincrona - confirmacionPeticion",re);
			//guardarEstadoFalloWs(listaSolicitudesDesempleo);
			//throw new BusinessException(re);
			
		}catch(BusinessException be){
			logger.error(" Error DatosDesempleoManagerImpl - consultarDesempleoDiasAsincrona - confirmacionPeticion",be);
			//guardarEstadoFalloWs(listaSolicitudesDesempleo);
			//throw be;
		}
		
		
		return idPeticionDesempleoDias;
	}


	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#consultarDesempleoImporteAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario, es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento, es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante, java.util.List, java.lang.String)
	 */
	@Override
	public String consultarDesempleoImporteAsincrona(Funcionario funcionario,
			Procedimiento procedimiento, Solicitante solicitantePeticion,
			List<SolicitudBean> listaSolicitudesDesempleo,
			String codigoCertificado) {
		// TODO Auto-generated method stub
		String idPeticionDesempleoImporte = null;
//		String fechafinal = null;
//		String fechainicio = null;
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean(STRING_PROPERTIESBEAN);
		
		Atributos atributos = new Atributos();
		atributos.setCodigoCertificado(codigoCertificado);

		SolicitudTransmision[] transmisiones = new SolicitudTransmision[listaSolicitudesDesempleo.size()];
		
		//INI - TRA-059 - Ejercicio renta
		String renta = recuperarEjercicioRenta();
		//FIN - TRA-059 - Ejercicio renta
		
		
		for (int i =0; i<listaSolicitudesDesempleo.size(); i++) {
			Solicitante solicitante = null;
			try {
				solicitante = (Solicitante) solicitantePeticion.clone();
			} catch (CloneNotSupportedException e) {
				logger.error(" Error DatosDesempleoManagerImpl - consultarDesempleoImporteAsincrona - solicitante",e);
			}
			
			if(null!=listaSolicitudesDesempleo.get(i).getNumeroSolicitud() && !"".equals(listaSolicitudesDesempleo.get(i).getNumeroSolicitud())){
				solicitante.setIdExpediente(listaSolicitudesDesempleo.get(i).getNumeroSolicitud());
			}else{
				solicitante.setIdExpediente(properties.getProperty(STRING_SVDSEPE_ID_EXPEDIENTE));
			}
			
			SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
			
					
			es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificos datosEspecificosImporte = new es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificos();
//ANTERIOR INI		  
//			DatosImportePeriodo datosImportePeriodo = new DatosImportePeriodo();    
//		    try {
//				Calendar cal = Calendar.getInstance();			
//		    	Date datefinal = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT).parse(listaSolicitudesDesempleo.get(i).getFechaBoe());
//		    	cal.setTime(datefinal);
//		    	cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)- 1);
//		    	Date dateinicio = cal.getTime();
//		        fechafinal = new SimpleDateFormat(STRING_SIMPLEDATEFORMATMONTHYEAR).format(datefinal);
//		        fechainicio = new SimpleDateFormat(STRING_SIMPLEDATEFORMATMONTHYEAR).format(dateinicio);
//		        datosImportePeriodo.setFXFINAL(fechafinal);
//		        datosImportePeriodo.setFXINICIO(fechainicio);
//				datosEspecificosImporte.setDatosImportePeriodo(datosImportePeriodo);
//		    } catch (ParseException e) {
//		        // TODO Auto-generated catch block
//		    	logger.error(" Error DatosDesempleoManagerImpl - consultarDesempleoImporteAsincrona - parseo fecha",e);
//		    }
//ANTERIOR FIN

			//Datos genericos IMPORTE INI
			
			Titular titular = new Titular();
			titular.setDocumentacion("99999357A");//DATO DE PRUEBA
//			titular.setDocumentacion(listaSolicitudesDesempleo.get(i).getNif());		
			titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(listaSolicitudesDesempleo.get(i).getNif())));
//			titular.setNombre(listaSolicitudesDesempleo.get(i).getNombre());
//			titular.setApellido1(listaSolicitudesDesempleo.get(i).getPrimerApellido());
			datosGenericos.setTitular(titular);
			
			procedimiento.setCodProcedimiento(properties.getProperty("SVDSEPE_COD_PROCEDIMIENTO_IMP"));
			solicitante.setProcedimiento(procedimiento);
			datosGenericos.setSolicitante(solicitante);
		    
			//Datos genericos IMPORTE FIN
			//Datos especificos IMPORTE INI
		    try {
				//datosEspecificosImporte.setEjercicio(2015);//DATO DE PRUEBA
		    	//INI - TRA-059 - ejercicio de la renta
		    	datosEspecificosImporte.setEjercicio(Integer.parseInt(renta));
		    	//FIN - TRA-059
				//datosEspecificosImporte.setEjercicio(Integer.parseInt(convocatoriaBean.getEjercicio()));
			} catch (NumberFormatException e) {
				logger.error("Error al obtener el ejercicio de la convocatoria.");
		    }
		    SolicitudTransmision solicitudImporte = new SolicitudTransmision();
			solicitudImporte.setDatosGenericos(datosGenericos);
			solicitudImporte.setDatosEspecificos(datosEspecificosImporte);
			
			transmisiones[i] = solicitudImporte;
			//Datos especificos IMPORTE FIN
			
		}
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);	
		
		PeticionSincrona peticionSincrona = new PeticionSincrona(atributos, solicitudes);
						
		logger.info("Realizando Consulta de Desempleo dias con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionSincrona));
		
		try{
			es.redsara.intermediacion.scsp.esquemas.importe.ws.ScspwsProxy proxyImporte = new es.redsara.intermediacion.scsp.esquemas.importe.ws.ScspwsProxy(properties.getProperty("URL"));
			es.redsara.intermediacion.scsp.esquemas.importe.ws.respuesta.Respuesta confirmacionPeticion = proxyImporte.peticionSincrona(peticionSincrona);
			if(null!= confirmacionPeticion){
				idPeticionDesempleoImporte=confirmacionPeticion.getAtributos().getIdPeticion();
			}
			
		}catch(RemoteException re){
			logger.error(" Error DatosDesempleoManagerImpl - consultarDesempleoImporteAsincrona - confirmacionPeticion",re);
			//throw new BusinessException(re);
			
		}catch(BusinessException be){
			logger.error(" Error DatosDesempleoManagerImpl - consultarDesempleoImporteAsincrona - confirmacionPeticion",be);
			//throw be;
		}
		
		
		return idPeticionDesempleoImporte;
	}
	

	/**
	 * Error ws desempleado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void errorWsDesempleado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDesempleoVerificado(Constantes.DESEMPLEO_NO_CONEXION_WS);
		solicitudComunDAO.update(solicitud);		
	}
	
	/**
	 * Error ws desempleado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void errorWsDesempleadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDesempleoVerificado(Constantes.FNUMEROSA_NO_CONEXION_WS);
		solicitudComunAuxiliarDAO.update(solicitud);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#guardarEstadoFalloWs(java.util.List)
	 */
	public void guardarEstadoFalloWs(List<SolicitudBean> listaSolicitudesDesempleo) {
		for (SolicitudBean SolicitudDesempleado : listaSolicitudesDesempleo) {
			if (SolicitudDesempleado != null && SolicitudDesempleado.getId() != null && SolicitudDesempleado.getId() > 0) {
				errorWsDesempleado(SolicitudDesempleado.getId());
			}	
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#guardarEstadoPendiente(java.lang.Long)
	 */
	@Override
	public void guardarEstadoPendiente(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		if (solicitud.getDesempleoVerificado().equals(Constantes.DESEMPLEO_NO_CONEXION_WS)) {
			solicitud.setDesempleoVerificado(Constantes.DESEMPLEO_NO_COMPROBADO);
	}
		solicitudComunDAO.update(solicitud);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#guardarEstadoPendienteAuxiliar(java.lang.Long)
	 */
	@Override
	public void guardarEstadoPendienteAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDesempleoVerificado(Constantes.FNUMEROSA_NO_COMPROBADO);
		solicitudComunAuxiliarDAO.update(solicitud);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#guardarEstadoPendienteList(java.util.List)
	 */
	@Override
	public void guardarEstadoPendienteList(List<SolicitudBean> listaSolicitudesDesempleo) {
		for (SolicitudBean SolicitudDesempleado : listaSolicitudesDesempleo) {
			if (SolicitudDesempleado != null && SolicitudDesempleado.getId() != null && SolicitudDesempleado.getId() > 0) {
				guardarEstadoPendiente(SolicitudDesempleado.getId());
			}	
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#guardarEstadoFalloWsAux(java.util.List)
	 */
	@Override
	public void guardarEstadoFalloWsAux(List<SolicitudBean> listaSolicitudesDesempleo) {
		for (SolicitudBean SolicitudDesempleado : listaSolicitudesDesempleo) {
			if (SolicitudDesempleado != null && SolicitudDesempleado.getId() != null && SolicitudDesempleado.getId() > 0) {
				errorWsDesempleadoAuxiliar(SolicitudDesempleado.getId());
			}	
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosDesempleoManager#guardarEstadoPendienteListAux(java.util.List)
	 */
	@Override
	public void guardarEstadoPendienteListAux(List<SolicitudBean> listaSolicitudesDesempleo) {
		for (SolicitudBean SolicitudDesempleado : listaSolicitudesDesempleo) {
			if (SolicitudDesempleado != null && SolicitudDesempleado.getId() != null && SolicitudDesempleado.getId() > 0) {
				guardarEstadoPendienteAuxiliar(SolicitudDesempleado.getId());
			}	
		}
	}
	
	
	//INI - TRA-059 - ejercicio de la renta
	/**
	 * 
	 * @return el ejercicio de la renta actual
	 */
	private String recuperarEjercicioRenta() {
		String result = "";
		
		ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
		parametrosConfiguracionQuery.addIdIn(Constantes.PARAMETRO_CONFIGURACION_ID_EJERCICIO_RENTA);
		
		ParametrosConfiguracion parametros = parametrosConfiguracionDAO.searchUnique(parametrosConfiguracionQuery);
		
		if (parametros.getValorCampo()!= null){
			result = parametros.getValorCampo();
			logger.info("Ejercicio renta: " + result);
		}
		
		return result;
	}
	//FIN - TRA-059
}
