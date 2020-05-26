package es.map.ipsg.manager;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.dao.SolicitudComunAuxiliarDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionFNumerosaBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Util;
import es.map.ipsg.util.UtilesIPSG;
import es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma;
import es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Consulta;
import es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.DatosEspecificos;
import es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosa;
import es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.ScspwsProxy;
import es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.Respuesta;
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
 * El Class DatosFNumerosaManagerImpl.
 *
 * @author everis
 */
public class DatosFNumerosaManagerImpl implements DatosFNumerosaManager {
	
	
	/** el properties. */
	private Properties properties;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DatosFNumerosaManagerImpl.class);
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
		
	/**
	 * Consultar F numerosa auxiliar.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param nifFuncionario el nif funcionario
	 * @param nombreFuncionario el nombre funcionario
	 * @param unidadTramitadora el unidad tramitadora
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 * @return el intermediacion F numerosa bean
	 */
	private IntermediacionFNumerosaBean consultarFNumerosaAuxiliar( ConvocatoriasBean convocatoriaBean, SolicitudBean solicitudBean, String nifFuncionario, String nombreFuncionario, String unidadTramitadora,SolicitudCcaaAuxiliarBean solicitudCcaaBean) {
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		IntermediacionFNumerosaBean fnumerosaBean = null;    		
		
		String url = 						properties.getProperty("URL");
		String codigoCertificado = 			properties.getProperty("CCAAFN_CODIGO_CERTIFICADO");
		String identificadorSolicitante = 	properties.getProperty("CCAAFN_IDENTIFICADOR_SOLICITANTE");
		String nombreSolicitante = 			properties.getProperty("CCAAFN_NOMBRE_SOLICITANTE");
		String finalidad = 					properties.getProperty("CCAAFN_FINALIDAD");
		String unidadTramit = 			    properties.getProperty("CCAAFN_UNIDAD_TRAMITADORA");
		String consentimiento = 			properties.getProperty("CCAAFN_CONSENTIMIENTO");
		String idExpediente =				properties.getProperty("CCAAFN_ID_EXPEDIENTE");
		String codProcedimiento =			properties.getProperty("CCAAFN_COD_PROCEDIMIENTO");
		String nombreProcedimiento =		properties.getProperty("CCAAFN_NOMBRE_PROCEDIMIENTO");

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
		titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(solicitudBean.getNif())));
		titular.setNombre(solicitudBean.getNombre());
		titular.setApellido1(solicitudBean.getPrimerApellido());
		titular.setApellido2(solicitudBean.getSegundoApellido());
		
		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
		datosGenericos.setSolicitante(solicitante);
		datosGenericos.setTitular(titular);		
		
		DatosEspecificos datosEspecificos=new DatosEspecificos();
		Consulta consulta = new Consulta();
		TituloFamiliaNumerosa tituloFamiliaNumerosa = new TituloFamiliaNumerosa();
		
		// Incidencia: Número título familia numerosa como espacio en blanco		
		if(solicitudCcaaBean.getTituloFamnumerosa()!=null){
			String numeroTitulo = solicitudCcaaBean.getTituloFamnumerosa().replaceAll("\\s+","");		
			if (!numeroTitulo.equals("")){		
				// Mandamos en la consulta el número del título una vez eliminados todos los espacios
				tituloFamiliaNumerosa.setNumeroTitulo(numeroTitulo); 
			}
		}
		
		tituloFamiliaNumerosa.setCodigoComunidadAutonoma(CodigoComunidadAutonoma.fromString(solicitudCcaaBean.getCodigoComunidad()));	
		consulta.setTituloFamiliaNumerosa(tituloFamiliaNumerosa);
		datosEspecificos.setConsulta(consulta);
		
		//INI - TRA-060
		//Tanto la comunidad de EXTREMADURA como CATALUÑA la fecha de consulta debe ser la actual.
		String fechaAux = convocatoriaBean.getFechaBoe();
		if (Constantes.CODIGO_PROV_CATALUNA.equals(tituloFamiliaNumerosa.getCodigoComunidadAutonoma().getValue()) || 
				Constantes.CODIGO_PROV_EXTREMADURA.equals(tituloFamiliaNumerosa.getCodigoComunidadAutonoma().getValue())) {
			fechaAux = UtilesIPSG.getFechaActual();
		}
		tituloFamiliaNumerosa.setFechaConsulta(fechaAux);
		//FIN - TRA-060
		
		SolicitudTransmision solicitud = new SolicitudTransmision();
		solicitud.setDatosGenericos(datosGenericos);
		solicitud.setDatosEspecificos(datosEspecificos);
		
		SolicitudTransmision[] transmisiones = new SolicitudTransmision[1];
		transmisiones[0] = solicitud;
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);
		
		PeticionSincrona peticionSincrona = new PeticionSincrona(atributos, solicitudes);
		
		logger.info("Realizando Consulta de Datos Familia Numerosa con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionSincrona));
	
		ScspwsProxy proxy = new ScspwsProxy(url);
		Respuesta respuesta = null;
		
		
		try{
			respuesta = proxy.peticionSincrona(peticionSincrona);	
			fnumerosaBean= toIntermediacionFNumerosaBean(respuesta);
			logger.info("Datos de Identidad Obtenidos para el NIF: "+titular.getDocumentacion());
			logger.info(new BeanFormatter().format(fnumerosaBean));
			
		}catch(RemoteException re){
			logger.error(" Error DatosFNumerosaManagerImpl - respuesta :",re);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			//throw new BusinessException(re);
			
		}catch(BusinessException be){
			logger.error(" Error DatosFNumerosaManagerImpl - respuesta :",be);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			//throw be;
		}
		
		return fnumerosaBean;
	}
	
/**
 * Consultar F numerosa.
 *
 * @param convocatoriaBean el convocatoria bean
 * @param solicitudBean el solicitud bean
 * @param nifFuncionario el nif funcionario
 * @param nombreFuncionario el nombre funcionario
 * @param unidadTramitadora el unidad tramitadora
 * @param solicitudCcaaBean el solicitud ccaa bean
 * @return el intermediacion F numerosa bean
 */
private IntermediacionFNumerosaBean consultarFNumerosa( ConvocatoriasBean convocatoriaBean, SolicitudBean solicitudBean, String nifFuncionario, String nombreFuncionario, String unidadTramitadora,SolicitudCcaaBean solicitudCcaaBean) {
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		IntermediacionFNumerosaBean fnumerosaBean = null;    		
		
		String url = 						properties.getProperty("URL");
		String codigoCertificado = 			properties.getProperty("CCAAFN_CODIGO_CERTIFICADO");
		String identificadorSolicitante = 	properties.getProperty("CCAAFN_IDENTIFICADOR_SOLICITANTE");
		String nombreSolicitante = 			properties.getProperty("CCAAFN_NOMBRE_SOLICITANTE");
		String finalidad = 					properties.getProperty("CCAAFN_FINALIDAD");
		String unidadTramit = 			    properties.getProperty("CCAAFN_UNIDAD_TRAMITADORA");
		String consentimiento = 			properties.getProperty("CCAAFN_CONSENTIMIENTO");
		String idExpediente =				properties.getProperty("CCAAFN_ID_EXPEDIENTE");
		String codProcedimiento =			properties.getProperty("CCAAFN_COD_PROCEDIMIENTO");
		String nombreProcedimiento =		properties.getProperty("CCAAFN_NOMBRE_PROCEDIMIENTO");

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
		titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(solicitudBean.getNif())));
		titular.setNombre(solicitudBean.getNombre());
		titular.setApellido1(solicitudBean.getPrimerApellido());
		titular.setApellido2(solicitudBean.getSegundoApellido());
		
		SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
		datosGenericos.setSolicitante(solicitante);
		datosGenericos.setTitular(titular);		
		
		DatosEspecificos datosEspecificos=new DatosEspecificos();
		Consulta consulta = new Consulta();
		TituloFamiliaNumerosa tituloFamiliaNumerosa = new TituloFamiliaNumerosa();
		
		// Incidencia: Número título familia numerosa como espacio en blanco		
		if(solicitudCcaaBean.getTituloFamnumerosa()!=null){
			String numeroTitulo = solicitudCcaaBean.getTituloFamnumerosa().replaceAll("\\s+","");		
			if (!numeroTitulo.equals("")){		
				// Mandamos en la consulta el número del título una vez eliminados todos los espacios
				tituloFamiliaNumerosa.setNumeroTitulo(numeroTitulo); 
			}
		}
		
		tituloFamiliaNumerosa.setCodigoComunidadAutonoma(CodigoComunidadAutonoma.fromString(solicitudCcaaBean.getCodigoComunidad()));	
		consulta.setTituloFamiliaNumerosa(tituloFamiliaNumerosa);
		datosEspecificos.setConsulta(consulta);
		//INI - TRA-060
		//Tanto la comunidad de EXTREMADURA como CATALUÑA la fecha de consulta debe ser la actual.
		String fechaAux = convocatoriaBean.getFechaBoe();
		if (Constantes.CODIGO_PROV_CATALUNA.equals(tituloFamiliaNumerosa.getCodigoComunidadAutonoma().getValue()) || 
				Constantes.CODIGO_PROV_EXTREMADURA.equals(tituloFamiliaNumerosa.getCodigoComunidadAutonoma().getValue())) {
			fechaAux = UtilesIPSG.getFechaActual();
		}
		tituloFamiliaNumerosa.setFechaConsulta(fechaAux);
		//FIN - TRA-060
		
		SolicitudTransmision solicitud = new SolicitudTransmision();
		solicitud.setDatosGenericos(datosGenericos);
		solicitud.setDatosEspecificos(datosEspecificos);
		
		SolicitudTransmision[] transmisiones = new SolicitudTransmision[1];
		transmisiones[0] = solicitud;
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);
		
		PeticionSincrona peticionSincrona = new PeticionSincrona(atributos, solicitudes);
		
		logger.info("Realizando Consulta de Datos Familia Numerosa con los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionSincrona));
	
		ScspwsProxy proxy = new ScspwsProxy(url);
		Respuesta respuesta = null;
		
		
		try{
			respuesta = proxy.peticionSincrona(peticionSincrona);	
			fnumerosaBean= toIntermediacionFNumerosaBean(respuesta);
			logger.info("Datos de Identidad Obtenidos para el NIF: "+titular.getDocumentacion());
			logger.info(new BeanFormatter().format(fnumerosaBean));
			
		}catch(RemoteException re){
			logger.error(" Error DatosFNumerosaManagerImpl - respuesta :",re);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			//throw new BusinessException(re);
			
		}catch(BusinessException be){
			logger.error(" Error DatosFNumerosaManagerImpl - respuesta :",be);
			//guardarEstadoFalloWsSolicitud(solicitudBean);
			//throw be;
		}
		
		return fnumerosaBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#validarFNumerosa(es.map.ipsg.bean.ConvocatoriasBean, es.map.ipsg.bean.SolicitudBean, es.map.ipsg.bean.UsuarioBean, es.map.ipsg.bean.SolicitudCcaaBean)
	 */
	public IntermediacionFNumerosaBean validarFNumerosa(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean,SolicitudCcaaBean solicitudCcaaBean){


		String nifFuncionario = usuarioBean.getNif();
		String nombreFuncionario = usuarioBean.getNombreCompleto();
		/*INI-TRA042*/
		String unidadTramitadora = solicitudBean.getCentroGestor();
		/*FIN-TRA042*/
		
		IntermediacionFNumerosaBean fnumerosaBean = consultarFNumerosa(convocatoriaBean, solicitudBean, nifFuncionario, nombreFuncionario, unidadTramitadora, solicitudCcaaBean);
				
		return fnumerosaBean;
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#validarFNumerosaAuxiliar(es.map.ipsg.bean.ConvocatoriasBean, es.map.ipsg.bean.SolicitudBean, es.map.ipsg.bean.UsuarioBean, es.map.ipsg.bean.SolicitudCcaaAuxiliarBean)
	 */
	public IntermediacionFNumerosaBean validarFNumerosaAuxiliar(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean,SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean){


		String nifFuncionario = usuarioBean.getNif();
		String nombreFuncionario = usuarioBean.getNombreCompleto();
		/*INI-TRA042*/
		String unidadTramitadora = solicitudBean.getCentroGestor();
		/*FIN-TRA042*/
		
		IntermediacionFNumerosaBean fnumerosaBean = consultarFNumerosaAuxiliar(convocatoriaBean, solicitudBean, nifFuncionario, nombreFuncionario, unidadTramitadora, solicitudCcaaAuxiliarBean);
				
		return fnumerosaBean;
		
	}
	
	/**
	 * To intermediacion F numerosa bean.
	 *
	 * @param respuesta el respuesta
	 * @return el intermediacion F numerosa bean
	 */
	private IntermediacionFNumerosaBean toIntermediacionFNumerosaBean(Respuesta respuesta){
				
		IntermediacionFNumerosaBean fnumerosa = new IntermediacionFNumerosaBean();
		String tituloVigente = null;
		String categoria= null;
		
		if( null!=respuesta.getTransmisiones().getTransmisionDatos(0).
				getDatosEspecificos().getRetorno().getTituloFamiliaNumerosaRetorno()){
			
		tituloVigente= respuesta.getTransmisiones().getTransmisionDatos(0).
		getDatosEspecificos().getRetorno().getTituloFamiliaNumerosaRetorno().getTituloVigente().getValue();
		
		categoria = respuesta.getTransmisiones().getTransmisionDatos(0).
				getDatosEspecificos().getRetorno().getTituloFamiliaNumerosaRetorno().getCategoria().getValue();
		
		}else{
			logger.error("Error: titulo familia numerosa nulo.");
			fnumerosa.setDescripcionError((respuesta.getTransmisiones()
					.getTransmisionDatos(0).getDatosEspecificos().getRetorno().getEstado().getLiteralError()));
			}
			
		fnumerosa.setCategoriaFNumerosa(categoria);
		fnumerosa.setVigenciaTitulo(tituloVigente);

		return fnumerosa;
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#consultarFNumerosaAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario, es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento, es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante, java.util.List, java.util.List, java.lang.String)
	 */
	public String consultarFNumerosaAsincrona(Funcionario funcionario, Procedimiento procedimiento,Solicitante solicitantePeticion, List<SolicitudBean> listaSolicitudesFNumerosa, List<SolicitudCcaaBean> listaSolicitudesCcaaFNumerosa, String codigoCertificado){
		
		String idPeticionFNumerosa = null;
		
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		Atributos atributos = new Atributos();
		atributos.setCodigoCertificado(codigoCertificado);

		SolicitudTransmision[] transmisiones = new SolicitudTransmision[listaSolicitudesFNumerosa.size()];
		
		for (int i =0; i<listaSolicitudesFNumerosa.size(); i++) {
			Solicitante solicitante = null;
			try {
				solicitante = (Solicitante) solicitantePeticion.clone();
			} catch (CloneNotSupportedException e) {
				logger.error(" Error DatosFNumerosaManagerImpl - consultarFNumerosaAsincrona - solicitante:",e);
			}
			
			if(null!=listaSolicitudesFNumerosa.get(i).getNumeroSolicitud() && !"".equals(listaSolicitudesFNumerosa.get(i).getNumeroSolicitud())){
				solicitante.setIdExpediente(listaSolicitudesFNumerosa.get(i).getNumeroSolicitud());
			}else{
				solicitante.setIdExpediente(properties.getProperty("CCAAFN_ID_EXPEDIENTE"));
			}
			
			Titular titular = new Titular();
			titular.setDocumentacion(listaSolicitudesFNumerosa.get(i).getNif());		
			titular.setTipoDocumentacion(TipoDocumentacion.fromString(evaluarTipoDocumento(listaSolicitudesFNumerosa.get(i).getNif())));
			titular.setNombre(listaSolicitudesFNumerosa.get(i).getNombre());
			titular.setApellido1(listaSolicitudesFNumerosa.get(i).getPrimerApellido());
			titular.setApellido2(listaSolicitudesFNumerosa.get(i).getSegundoApellido());
									
			SolicitudTransmisionDatosGenericos datosGenericos = new SolicitudTransmisionDatosGenericos ();
			datosGenericos.setSolicitante(solicitante);
			datosGenericos.setTitular(titular);		
			
			DatosEspecificos datosEspecificos=new DatosEspecificos();
			Consulta consulta = new Consulta();
			TituloFamiliaNumerosa tituloFamiliaNumerosa = new TituloFamiliaNumerosa();
			
			// Incidencia: Número título familia numerosa como espacio en blanco		
			if(listaSolicitudesCcaaFNumerosa.get(i).getTituloFamnumerosa()!=null){
				String numeroTitulo = listaSolicitudesCcaaFNumerosa.get(i).getTituloFamnumerosa().replaceAll("\\s+","");		
				if (!numeroTitulo.equals("")){		
					// Mandamos en la consulta el número del título una vez eliminados todos los espacios
					tituloFamiliaNumerosa.setNumeroTitulo(numeroTitulo); 
				}
			}
			
			tituloFamiliaNumerosa.setCodigoComunidadAutonoma(CodigoComunidadAutonoma.fromString(listaSolicitudesCcaaFNumerosa.get(i).getCodigoComunidad()));	
			consulta.setTituloFamiliaNumerosa(tituloFamiliaNumerosa);
			datosEspecificos.setConsulta(consulta);
			
			//INI - TRA-060
			//Tanto la comunidad de EXTREMADURA como CATALUÑA la fecha de consulta debe ser la actual.
			String fechaAux = listaSolicitudesFNumerosa.get(i).getFechaBoe();
			if (Constantes.CODIGO_PROV_CATALUNA.equals(listaSolicitudesCcaaFNumerosa.get(i).getCodigoComunidad()) || 
					Constantes.CODIGO_PROV_EXTREMADURA.equals(listaSolicitudesCcaaFNumerosa.get(i).getCodigoComunidad())) {
				fechaAux = UtilesIPSG.getFechaActual();
			}
			tituloFamiliaNumerosa.setFechaConsulta(fechaAux);
			//FIN - TRA-060
			
			SolicitudTransmision solicitud = new SolicitudTransmision();
			solicitud.setDatosGenericos(datosGenericos);
			solicitud.setDatosEspecificos(datosEspecificos);

			transmisiones[i] = solicitud;
													
		}
		
		Solicitudes solicitudes = new Solicitudes();
		solicitudes.setSolicitudTransmision(transmisiones);	
		
		PeticionAsincrona peticionAsincrona = new PeticionAsincrona(atributos, solicitudes);
						
		logger.info("Realizando Consulta de Familia Numerosacon los siguientes datos:");
		logger.info(new BeanFormatter().format(peticionAsincrona));
		
		
		try{
			ScspwsProxy proxy = new ScspwsProxy(properties.getProperty("URL"));
			es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion confirmacionPeticion = proxy.peticionAsincrona(peticionAsincrona);
			if(null!= confirmacionPeticion){
				idPeticionFNumerosa=confirmacionPeticion.getAtributos().getIdPeticion();
			}
			
		}catch(RemoteException re){
			logger.error(" Error DatosFNumerosaManagerImpl - consultarFNumerosaAsincrona - confirmacionPeticion:",re);
			//guardarEstadoFalloWsListado(listaSolicitudesFNumerosa);
			//throw new BusinessException(re);
			
		}catch(BusinessException be){
			logger.error(" Error DatosFNumerosaManagerImpl - consultarFNumerosaAsincrona - confirmacionPeticion:",be);
			//guardarEstadoFalloWsListado(listaSolicitudesFNumerosa);
			//throw be;
		}
		
		return idPeticionFNumerosa;
		
	}
	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#guardarEstadoFalloWsListado(java.util.List)
	 */
	public void guardarEstadoFalloWsListado(List<SolicitudBean> listaSolicitudesFNumerosa) {
		for (SolicitudBean solicitudFNumerosa : listaSolicitudesFNumerosa) {
			if (solicitudFNumerosa != null && solicitudFNumerosa.getId() != null && solicitudFNumerosa.getId() > 0) {
				errorWsFNumerosaVerificado(solicitudFNumerosa.getId());
			}	
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#guardarEstadoFalloWsSolicitud(es.map.ipsg.bean.SolicitudBean)
	 */
	public void guardarEstadoFalloWsSolicitud(SolicitudBean solicitudBean) {
		if (solicitudBean != null && solicitudBean.getId() != null) {
			errorWsFNumerosaVerificado(solicitudBean.getId());
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#guardarEstadoFalloWsSolicitudAuxiliar(es.map.ipsg.bean.SolicitudBean)
	 */
	public void guardarEstadoFalloWsSolicitudAuxiliar(SolicitudBean solicitudBean) {
		if (solicitudBean != null && solicitudBean.getId() != null) {
			errorWsFNumerosaVerificadoAuxiliar(solicitudBean.getId());
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#aprobarFNumerosaVerificado(java.lang.Long)
	 */
	public void aprobarFNumerosaVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_VERIFICADO);
		solicitudComunDAO.update(solicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#aprobarFNumerosaVerificadoAuxiliar(java.lang.Long)
	 */
	public void aprobarFNumerosaVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_VERIFICADO);
		solicitudComunAuxiliarDAO.update(solicitud);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#rechazarFNumerosaVerificado(java.lang.Long)
	 */
	public void rechazarFNumerosaVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_VERIFICADO);
		solicitudComunDAO.update(solicitud);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#rechazarFNumerosaVerificadoAuxiliar(java.lang.Long)
	 */
	public void rechazarFNumerosaVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_VERIFICADO);
		solicitudComunAuxiliarDAO.update(solicitud);		
	}
	
	/**
	 * Error ws F numerosa verificado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void errorWsFNumerosaVerificado(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_CONEXION_WS);
		solicitudComunDAO.update(solicitud);		
	}
	
	/**
	 * Error ws F numerosa verificado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void errorWsFNumerosaVerificadoAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_CONEXION_WS);
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

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#guardarEstadoPendiente(java.lang.Long)
	 */
	@Override
	public void guardarEstadoPendiente(Long idSolicitud) {
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_COMPROBADO);
		solicitudComunDAO.update(solicitud);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#guardarEstadoPendienteAuxiliar(java.lang.Long)
	 */
	@Override
	public void guardarEstadoPendienteAuxiliar(Long idSolicitud) {
		SolicitudComunAuxiliar solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_COMPROBADO);
		solicitudComunAuxiliarDAO.update(solicitud);		
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#guardarEstadoPendienteList(java.util.List)
	 */
	@Override
	public void guardarEstadoPendienteList(List<SolicitudBean> listaSolicitudesFNumerosa) {
		for (SolicitudBean solicitud : listaSolicitudesFNumerosa) {
			if (solicitud != null && solicitud.getId() != null && solicitud.getId() > 0) {
				guardarEstadoPendiente(solicitud.getId());
			}	
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#guardarEstadoPendienteListAux(java.util.List)
	 */
	@Override
	public void guardarEstadoPendienteListAux(List<SolicitudBean> listaSolicitudesFNumerosa) {
		for (SolicitudBean solicitud : listaSolicitudesFNumerosa) {
			if (solicitud != null && solicitud.getId() != null && solicitud.getId() > 0) {
				guardarEstadoPendienteAuxiliar(solicitud.getId());
			}	
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosFNumerosaManager#guardarEstadoFalloWsAuxListado(java.util.List)
	 */
	public void guardarEstadoFalloWsAuxListado(List<SolicitudBean> listaSolicitudesFNumerosa) {
		for (SolicitudBean solicitudFNumerosa : listaSolicitudesFNumerosa) {
			if (solicitudFNumerosa != null && solicitudFNumerosa.getId() != null && solicitudFNumerosa.getId() > 0) {
				errorWsFNumerosaVerificadoAuxiliar(solicitudFNumerosa.getId());
			}	
		}
	}
}
