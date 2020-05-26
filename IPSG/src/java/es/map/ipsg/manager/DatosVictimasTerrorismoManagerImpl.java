package es.map.ipsg.manager;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.dao.SolicitudComunAuxiliarDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ipsg.bean.IntermediacionVictimasTerrorismoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Util;
import es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificos;
import es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosConsulta;
import es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado;
import es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.ScspwsProxy;
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
 * 
 * @author everis
 *
 */
public class DatosVictimasTerrorismoManagerImpl implements DatosVictimasTerrorismoManager {
	
	
	private Properties properties;
	
	private static final Logger logger = Logger.getLogger(DatosVictimasTerrorismoManagerImpl.class);
	
	private static final String STRING_SIMPLEDATEFORMAT= "yy-dd-MM";
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
	

	@Override
	public IntermediacionVictimasTerrorismoBean consultaVictimaTerrorismo(SolicitudBean solicitudBean, String nifFuncionario, String nombreFuncionario, String unidadTramitadora) {
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		IntermediacionVictimasTerrorismoBean victimasBean = null;    		
		
		String url = 						properties.getProperty("URL");
		String codigoCertificado = 			properties.getProperty("VICTIMAS_CODIGO_CERTIFICADO");
		String identificadorSolicitante = 	properties.getProperty("VICTIMAS_IDENTIFICADOR_SOLICITANTE");
		String nombreSolicitante = 			properties.getProperty("VICTIMAS_NOMBRE_SOLICITANTE");
		String finalidad = 					properties.getProperty("VICTIMAS_FINALIDAD");
		String unidadTramit = 			    properties.getProperty("VICTIMAS_UNIDAD_TRAMITADORA");
		String consentimiento = 			properties.getProperty("VICTIMAS_CONSENTIMIENTO");
		String idExpediente =				properties.getProperty("VICTIMAS_ID_EXPEDIENTE");
		String codProcedimiento =			properties.getProperty("VICTIMAS_COD_PROCEDIMIENTO");
		String nombreProcedimiento =		properties.getProperty("VICTIMAS_NOMBRE_PROCEDIMIENTO");
		String codUnidadTramitadora =		properties.getProperty("VICTIMAS_COD_UNIDAD_TRAMITADORA");
		
		Atributos atributosVictimas = new Atributos();
		atributosVictimas.setCodigoCertificado(codigoCertificado);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNifFuncionario(nifFuncionario);
		funcionario.setNombreCompletoFuncionario(nombreFuncionario);
		
		Solicitante solicitante = new Solicitante();
		solicitante.setIdentificadorSolicitante(identificadorSolicitante);
		solicitante.setNombreSolicitante(nombreSolicitante);

		solicitante.setFinalidad(finalidad);
		solicitante.setConsentimiento(Consentimiento.fromString(consentimiento));
		solicitante.setCodigoUnidadTramitadora(codUnidadTramitadora);
		solicitante.setUnidadTramitadora(unidadTramit);
		
		if(null!=unidadTramitadora)
		solicitante.setUnidadTramitadora(unidadTramitadora);
		else
		solicitante.setUnidadTramitadora(unidadTramit);	
		
		solicitante.setFuncionario(funcionario);
		
		if(null!=solicitudBean.getNumeroSolicitud() && !"".equals(solicitudBean.getNumeroSolicitud())){
			solicitante.setIdExpediente(solicitudBean.getNumeroSolicitud());
		}else{
			solicitante.setIdExpediente(idExpediente);
		}
		
		Procedimiento procedimiento = new Procedimiento();
		procedimiento.setCodProcedimiento(codProcedimiento);
		procedimiento.setNombreProcedimiento(nombreProcedimiento);
		solicitante.setProcedimiento(procedimiento);
		
		Titular titular = new Titular();
		titular.setDocumentacion(solicitudBean.getNif());		
		titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(solicitudBean.getNif())));
		titular.setNombre(solicitudBean.getNombre());
		titular.setApellido1(solicitudBean.getPrimerApellido());
		
		//Pruebas
//		titular.setDocumentacion("47246238Y");	
//		titular.setNombre("RAMÓN");
//		titular.setApellido1("PIQUERAS");
//		titular.setApellido2("NIETO");
		
		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
		datosGenericos.setSolicitante(solicitante);
		datosGenericos.setTitular(titular);	
		
		Solicitudes solicitudesVictimas = new Solicitudes();
		
		SolicitudTransmision solicitudVictimas = new SolicitudTransmision();
		solicitudVictimas.setDatosGenericos(datosGenericos);
		
		//Datos Especificos
		DatosEspecificos datosEspecificosVictimas = new DatosEspecificos();
		DatosEspecificosConsulta datosEspecificosConsulta = new DatosEspecificosConsulta();
		SimpleDateFormat format = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		try {
			datosEspecificosConsulta.setFechaNacimiento(format.parse("1959-09-13"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		datosEspecificosConsulta.setPoblacionNacimiento("28092");
		datosEspecificosConsulta.setDerechoReclamado(DerechoReclamado.value1);
		//Datos provisionales
		datosEspecificosVictimas.setConsulta(datosEspecificosConsulta);
		solicitudVictimas.setDatosEspecificos(datosEspecificosVictimas);
		
		SolicitudTransmision[] transmisionesVictimas = new SolicitudTransmision[1];
		transmisionesVictimas[0] = solicitudVictimas;
		
		solicitudesVictimas.setSolicitudTransmision(transmisionesVictimas);
		
		PeticionSincrona peticionSincronaVictimas = new PeticionSincrona(atributosVictimas, solicitudesVictimas);
		
		logger.info("Realizando Consulta de Victimas Terrorismo con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionSincronaVictimas));
		
		es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.ScspwsProxy proxyVictimas = new ScspwsProxy(url);
		es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.Respuesta respuestaVictimas = null;
		
		try{
			respuestaVictimas = proxyVictimas.peticionSincrona(peticionSincronaVictimas);	
			victimasBean= toIntermediacionVictimasTerrorismoBean(respuestaVictimas);
			logger.info("Datos de Identidad Obtenidos para el NIF: ");
			logger.info(new BeanFormatter().format(victimasBean));
			
		}catch(RemoteException re){
			logger.error(" Error DatosVictimasTerrorismoManagerImpl -  consultarVictimasTerrorismo - error conexion",re);
			logger.error(re.getMessage());
			throw new BusinessException("intermediacion.svdi.errorConexion");
			
		}catch(BusinessException be){
			logger.error(" Error DatosVictimasTerrorismoManagerImpl -  consultarVictimasTerrorismo - error conexion",be);
			throw be;
		}
		
		return victimasBean;
	}

	private IntermediacionVictimasTerrorismoBean toIntermediacionVictimasTerrorismoBean(es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.Respuesta respuestaVictimas){
		
		IntermediacionVictimasTerrorismoBean victimasTerrorismo = new IntermediacionVictimasTerrorismoBean();

		
		if(respuestaVictimas.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno() !=null && 
				!StringUtils.isEmpty(respuestaVictimas.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getDescripcion()) &&
				!StringUtils.isEmpty(respuestaVictimas.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getCodigoEstado())){
		
			victimasTerrorismo.setCodigoEstado(respuestaVictimas.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getCodigoEstado());
			victimasTerrorismo.setDescripcion(respuestaVictimas.getTransmisiones().getTransmisionDatos(0).getDatosEspecificos().getRetorno().getDescripcion());
		}
		
		return victimasTerrorismo;
		
	}
		
	/** Metodo que evalua el tipo de documento pasado por parametro
	 * @param documento
	 * @return String DNI o NIE
	 */
	private String evaluarTipoDocumento(String documento){
		
		String tipoDoc = Constantes.TIPO_DOCUMENTO_DNI;
		
		if(Util.esNie(documento)){
			tipoDoc = Constantes.TIPO_DOCUMENTO_NIE;
		}
		
		return tipoDoc;
	}
	

	public String consultarVictimasAsincrona(Procedimiento procedimiento,Solicitante solicitantePeticion, List<SolicitudBean> listaSolicitudesVictimas, String codigoCertificado) {
		
		String idPeticionVictimas = null;
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		Atributos atributos = new Atributos();
		atributos.setCodigoCertificado(codigoCertificado);

		SolicitudTransmision[] transmisiones = new SolicitudTransmision[listaSolicitudesVictimas.size()];
		
		for (int i =0; i<listaSolicitudesVictimas.size(); i++) {
			Solicitante solicitante = null;
			try {
				solicitante = (Solicitante) solicitantePeticion.clone();
			} catch (CloneNotSupportedException e) {
				logger.error(" Error DatosFNumerosaManagerImpl - consultarVictimasAsincrona - solicitante:",e);
			}
			
			if(null!=listaSolicitudesVictimas.get(i).getNumeroSolicitud() && !"".equals(listaSolicitudesVictimas.get(i).getNumeroSolicitud())){
				solicitante.setIdExpediente(listaSolicitudesVictimas.get(i).getNumeroSolicitud());
			}else{
				solicitante.setIdExpediente(properties.getProperty("VICTIMAS_ID_EXPEDIENTE"));
			}
			solicitante.setCodigoUnidadTramitadora(properties.getProperty("VICTIMAS_COD_UNIDAD_TRAMITADORA"));
			
			Titular titular = new Titular();
			titular.setDocumentacion(listaSolicitudesVictimas.get(i).getNif());		
			titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(listaSolicitudesVictimas.get(i).getNif())));
			titular.setNombre(listaSolicitudesVictimas.get(i).getNombre());
			titular.setApellido1(listaSolicitudesVictimas.get(i).getPrimerApellido());
			titular.setApellido2(listaSolicitudesVictimas.get(i).getSegundoApellido());
									
			SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
			datosGenericos.setSolicitante(solicitante);
			datosGenericos.setTitular(titular);		
			
			DatosEspecificos datosEspecificos=new DatosEspecificos();
			DatosEspecificosConsulta datosConsultaVictimas = new DatosEspecificosConsulta();
				
			if (listaSolicitudesVictimas.get(i).getFechaNacimiento() != null) {
				datosConsultaVictimas.setFechaNacimiento(listaSolicitudesVictimas.get(i).getFechaNacimiento());
				datosConsultaVictimas.setPoblacionNacimiento("28092");
				datosConsultaVictimas.setDerechoReclamado(DerechoReclamado.value1);
			}
			
			datosEspecificos.setConsulta(datosConsultaVictimas);
						
			
			SolicitudTransmision solicitud = new SolicitudTransmision();
			solicitud.setDatosGenericos(datosGenericos);
			solicitud.setDatosEspecificos(datosEspecificos);

			transmisiones[i] = solicitud;
													
		}
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);	
		
		PeticionAsincrona peticionAsincrona = new PeticionAsincrona(atributos, solicitudes);
						
		logger.info("Realizando Consulta de Victimas Terr los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionAsincrona));
		
		
		try{
			ScspwsProxy proxy = new ScspwsProxy(properties.getProperty("URL"));
			es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion confirmacionPeticion = proxy.peticionAsincrona(peticionAsincrona);
			if(null!= confirmacionPeticion){
				idPeticionVictimas=confirmacionPeticion.getAtributos().getIdPeticion();
			}
			
		}catch(RemoteException re){
			logger.error(" Error DatosFNumerosaManagerImpl - consultarFNumerosaAsincrona - confirmacionPeticion:",re);
			
		}catch(BusinessException be){
			logger.error(" Error DatosFNumerosaManagerImpl - consultarFNumerosaAsincrona - confirmacionPeticion:",be);
		}
		
		return idPeticionVictimas;
		
	}
	
	public SolicitudComunDAO getSolicitudComunDAO() {
		return solicitudComunDAO;
	}

	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
		this.solicitudComunDAO = solicitudComunDAO;
	}

	public SolicitudComunAuxiliarDAO getSolicitudComunAuxiliarDAO() {
		return solicitudComunAuxiliarDAO;
	}

	public void setSolicitudComunAuxiliarDAO(SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO) {
		this.solicitudComunAuxiliarDAO = solicitudComunAuxiliarDAO;
	}

	public void aprobarVictimasVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setVictimasVerificado(Constantes.VICTIMA_VERIFICADO);
		solicitudComunDAO.update(solicitud);
	}
	
	public void rechazarVictimasVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setVictimasVerificado(Constantes.VICTIMA_NO_VERIFICADO);
		solicitudComunDAO.update(solicitud);		
	}
	
	public void aprobarVictimasVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setVictimasVerificado(Constantes.VICTIMA_VERIFICADO);
		solicitudComunAuxiliarDAO.update(solicitud);
	}
	public void rechazarVictimasVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setVictimasVerificado(Constantes.VICTIMA_NO_VERIFICADO);
		solicitudComunAuxiliarDAO.update(solicitud);		
	}
	
	@Override
	public void guardarEstadoPendienteList(List<SolicitudBean> listaSolicitudesVictimas) {
		for (SolicitudBean solicitudVictimas : listaSolicitudesVictimas) {
			if (solicitudVictimas != null && solicitudVictimas.getId() != null && solicitudVictimas.getId() > 0) {
				guardarEstadoPendiente(solicitudVictimas.getId());
			}	
		}
	}
	
	public void guardarEstadoFalloWsLista(List<SolicitudBean> listaSolicitudesVictima) {
		for (SolicitudBean solicitudVictima : listaSolicitudesVictima) {
			if (solicitudVictima != null && solicitudVictima.getId() != null && solicitudVictima.getId() > 0) {
				errorWsVictimasVerificado(solicitudVictima.getId());
			}	
		}
	}
	
	public void errorWsVictimasVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.VICTIMA_NO_CONEXION_WS);
		solicitudComunDAO.update(solicitud);		
	}


	@Override
	public void guardarEstadoPendienteListAux(List<SolicitudBean> listaSolicitudesVictimas) {
		for (SolicitudBean solicitudVictimas : listaSolicitudesVictimas) {
			if (solicitudVictimas != null && solicitudVictimas.getId() != null && solicitudVictimas.getId() > 0) {
				guardarEstadoPendienteAuxiliar(solicitudVictimas.getId());
			}	
		}
	}
	
	@Override
	public void guardarEstadoPendiente(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.VICTIMA_NO_COMPROBADO);
		solicitudComunDAO.update(solicitud);		
	}
	
	@Override
	public void guardarEstadoPendienteAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setDiscapacidadVerificado(Constantes.VICTIMA_NO_COMPROBADO);
		solicitudComunAuxiliarDAO.update(solicitud);		
	}

}
