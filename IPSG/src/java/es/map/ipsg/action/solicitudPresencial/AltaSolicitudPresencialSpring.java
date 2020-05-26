package es.map.ipsg.action.solicitudPresencial;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.Pais;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TipoSolicitud;
import es.map.ips.model.TituloOficial;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.LogSolicitudBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudComunAuxiliarBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudPresencialManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Util;
import es.map.ipsg.util.UtilesIPSG;

/**
 * Acción AltaSolicitudPresencialAction.
 *
 * @author amartinl
 */
public class AltaSolicitudPresencialSpring extends AbstractSpring {

	/** el solicitud presencial manager. */
	private SolicitudPresencialManager solicitudPresencialManager;
	
	/** el solicitud propios manager. */
	private SolicitudesPropiosManager solicitudPropiosManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager  registroSolicitudManager;
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** solicitud auxiliar manager**/
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaSolicitudPresencialSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
		
	/**
	 * Acción AltaSolicitudPresencialAction.
	 */
	public AltaSolicitudPresencialSpring() {
		try {
			setSolicitudPresencialManager((SolicitudPresencialManager) getBean("solicitudPresencialManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			setSolicitudPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
			setCamposPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error AltaSolicitudPresencialSpring ", e);
		}
	}

	/**
	 * Método doExecute de AltaSolicitudPresencialAction.
	 *
	 * @param form SpringForm Pasa los campos del formulario
	 * @return resultado String Si todo va bien devuelve success
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		String resultado = "";

	try{
		AltaSolicitudPresencialForm formulario = (AltaSolicitudPresencialForm) form;
		//extraemos los datos de los campos propios introducidos para modificar los campos propios de la solicitud
		ArrayList<CamposPropiosBean> listaCamposPropiosBean=formulario.getListaCamposPropiosBean();
		ArrayList<String> listaCamposAreaText = new ArrayList<String>();
		if(listaCamposPropiosBean!=null){
			for(int i=0;i<listaCamposPropiosBean.size();i++){
				Long idCampo = listaCamposPropiosBean.get(i).getId();
				String listaTextArea = this.getRequest().getParameter("listaTextAreas_"+idCampo);
				listaCamposAreaText.add(idCampo+"_"+listaTextArea);//formato de la lista: idCampo_ValorCampo
			}
		}
		SolicitudBean solicitudBean = new SolicitudBean();
		SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
		Provincia provincia = new Provincia();
		ProvinciaExamen provinciaExamen;
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		
		logger.info("-------Entramos en AltaSolicitud Presencial");
		
		
		if(formulario.getAccion().equals("VOLVER")){
			resultado = "list";
		}else{
		
			Date dHoy = new Date();
			//Campos Fijos:
			//Tipo: Presencial
			TipoSolicitud tipoSolicitud = new TipoSolicitud();
			tipoSolicitud.setId(Constantes.TIPO_SOLICITUD_PRESENCIAL);
			solicitudBean.setTipo(tipoSolicitud);
			//Estado: Registrada
			EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
			estadoSolicitud.setId(Constantes.ESTADO_SOLICITUD_REGISTRADO);
			solicitudBean.setEstadoSolicitud(estadoSolicitud);
			//IDModelo: Recuperado de los campos propios
			if (listaCamposPropiosBean != null && listaCamposPropiosBean.get(0) != null && !StringUtils.isEmpty(listaCamposPropiosBean.get(0).getIdModelo())) {
				solicitudBean.setIdModelo(listaCamposPropiosBean.get(0).getIdModelo());
			}
			//Fecha Última Actualización: Hoy
			solicitudBean.setFechaUtlActualizacion(dHoy);
			
			//Campos del FORMULARIO
			//Se recuperan todos los datos del formulario
			//Datos de Convocatoria
			solicitudBean.setNumeroSolicitud(formulario.getNumeroSolicitud());
			
			if(!StringUtils.isEmpty(formulario.getIdConvocatoria())){
				Convocatoria convocatoria = new Convocatoria();
				convocatoria.setId(Long.valueOf(formulario.getIdConvocatoria()));
				solicitudBean.setConvocatoria(convocatoria);
				solicitudBean.setIdConvocatoria(Long.valueOf(formulario.getIdConvocatoria()));
			}
			//Datos de Usuario
			String[] result = Util.comprobarAppellidoCompuesto(formulario.getNombre(),formulario.getPrimerApellido());
			solicitudBean.setNif(formulario.getNif());
			solicitudBean.setNombre(result[0]);
			solicitudBean.setPrimerApellido(result[1]);
			solicitudBean.setSegundoApellido(formulario.getSegundoApellido());
			solicitudBean.setIdConsentimiento(formulario.getCkConsentimiento());
			solicitudBean.setFechaNacimiento(utilesIPSG.stringToDate(formulario.getFechaNacimiento()));
			solicitudBean.setLocalidadNacimiento(formulario.getLocalidadNacimiento());
			solicitudBean.setNacionalidad(formulario.getNacionalidad());
			solicitudBean.setCodigoPostalDomicilio(formulario.getCodigoPostal());
			
			if(formulario.getIdProvinciaNacimiento() != null && !formulario.getIdProvinciaNacimiento().equals("")){
				
				provincia.setId(Integer.valueOf(formulario.getIdProvinciaNacimiento()));
				solicitudBean.setProvinciaByIdProvinciaNacimiento(provincia);
			}
			
			solicitudBean.setSexo(utilesIPSG.stringToCharPos(formulario.getSexo(), 0));
			solicitudBean.setEmail(formulario.getEmail());
			//Datos Domicilio
			solicitudBean.setCalleDomicilio(formulario.getCalleDomicilio());
			solicitudBean.setMunicipioDomicilio(formulario.getMunicipioDomicilio());
			
			if(formulario.getIdPais() != null && !formulario.getIdPais().equals("")){
				Pais pais = new Pais();
				pais.setId(Integer.valueOf(formulario.getIdPais()));
				solicitudBean.setPais(pais);
			}
			
			if(formulario.getIdProvinciaDomicilio() != null && !formulario.getIdProvinciaDomicilio().equals("")){
				provincia = new Provincia();
				provincia.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
				solicitudBean.setProvinciaByIdProvinciaDomicilio(provincia);
			}
			
			String telefono = null;
			if(formulario.getTelefono1()!=null && !formulario.getTelefono1().trim().equals("")){
				telefono = formulario.getTelefono1();
			}
			
			if(formulario.getTelefono2()!=null && !formulario.getTelefono2().trim().equals("")){
				if(telefono==null){
					//Telefono 1 no se ha informado
					telefono = formulario.getTelefono2(); 
				}else{
					//Telefono 1 se ha informado
					telefono = telefono+"-"+formulario.getTelefono2();
				}
			}
			solicitudBean.setTelefono(telefono);
			
			//Datos Solicitud
			if(formulario.getIdEspecialidad() != null && !formulario.getIdEspecialidad().equals("")){
				Especialidad especialidad = new Especialidad();
				especialidad.setId(Integer.valueOf(formulario.getIdEspecialidad()));
				solicitudBean.setEspecialidad(especialidad);
			}
			
			if(formulario.getIdProvinciaExamen() != null && !formulario.getIdProvinciaExamen().equals("")){
				provinciaExamen = new ProvinciaExamen();
				provinciaExamen.setId(Integer.valueOf(formulario.getIdProvinciaExamen()));
				solicitudBean.setProvinciaByIdProvinciaExamen(provinciaExamen);
			}
						
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String dHoyString = sdf.format(dHoy);
				solicitudBean.setFechaSolicitud(dHoyString);
			
			solicitudBean.setEjercicio(formulario.getEjercicio());
			
			boolean solicitaExencion = false;
			
			if(formulario.getIdTipoDiscapacidad() != null && !formulario.getIdTipoDiscapacidad().equals("")){
				TipoDiscapacidad tipoDiscapacidad = new TipoDiscapacidad();
				tipoDiscapacidad.setId(Integer.valueOf(formulario.getIdTipoDiscapacidad()));
				solicitudBean.setTipoDiscapacidad(tipoDiscapacidad);
				//solicitaExencion = true;
			}
			
			if(formulario.getPorcentajeDiscapacidad() != null && !formulario.getPorcentajeDiscapacidad().equals("")){
				solicitudBean.setPorcentajeDiscapacidad(Integer.valueOf(formulario.getPorcentajeDiscapacidad()));
			}else{
				solicitudBean.setPorcentajeDiscapacidad(0);
			}
			
			if(formulario.getCkReservaDiscapacidad() !=null && formulario.isCkReservaDiscapacidad()){
				solicitudBean.setReservaDiscapacidad('S'); 
			}else{
				solicitudBean.setReservaDiscapacidad('N');
			}
			
			solicitudBean.setDetalleDiscapacidad(formulario.getAdaptacionDiscapacidad());
			if(formulario.getIdTitulo() != null && !formulario.getIdTitulo().equals("")){
				TituloOficial tituloOficial = new TituloOficial();
				tituloOficial.setId(Integer.valueOf(formulario.getIdTitulo()));
				solicitudBean.setTituloOficial(tituloOficial);
			}
			
			solicitudBean.setOtrosTitulos(formulario.getOtrosTitulos());
			
			//Datos a Consignar
			solicitudBean.setDatosA(formulario.getDatosA());
			solicitudBean.setDatosB(formulario.getDatosB());
			solicitudBean.setDatosC(formulario.getDatosC());
			
			//Datos de Pago
			PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
		
			if(formulario.getIdTipoPago() != null){
				pagoSolicitudBean.setTipo(utilesIPSG.stringToCharPos(formulario.getIdTipoPago(), 0));
			}
			
			//Si no ha informado la fecha de Pago, se le incluye el día de Hoy.
			if(formulario.getFechaPago() != null && !formulario.getFechaPago().equals("")){
				pagoSolicitudBean.setFechaIntento(utilesIPSG.stringToDate(formulario.getFechaPago()));
			}else				
				pagoSolicitudBean.setFechaIntento(new Date());
			
			if(formulario.getImporte() != null && !formulario.getImporte().equals("")){
				String importe = null;
				importe = formulario.getImporte();
				if(formulario.getImporteDecimal() != null && !formulario.getImporteDecimal().equals("")){
					importe = importe  + "." + formulario.getImporteDecimal();
					pagoSolicitudBean.setImporte(Float.parseFloat(importe));
				}
			}
			
            // Obtenemos el identificador de los motivos de exención de tasa
			if(formulario.getIdMotivosEx() != null && !formulario.getIdMotivosEx().equals("")){
                MotivoReduccionTasa motivoReduccionTasa;
				MotivoReduccionTasaBean motivoReduccionTasaBean;
				motivoReduccionTasaBean = motivoReduccionTasaManager.obtenerMotivoReduccionTasa(Integer.valueOf(formulario.getIdMotivosEx()));
				motivoReduccionTasa=motivoReduccionTasaManager.toMotivoReduccionTasa(motivoReduccionTasaBean);
				pagoSolicitudBean.setMotivoReduccionTasa(motivoReduccionTasa);	
				solicitaExencion = true;
			}
			
            // Obtenemos el identificador de los motivos de reducción
			if(formulario.getIdMotivosRed() != null && !formulario.getIdMotivosRed().equals("")){
                MotivoReduccionTasa motivoReduccionTasa;
				MotivoReduccionTasaBean motivoReduccionTasaBean;
				motivoReduccionTasaBean = motivoReduccionTasaManager.obtenerMotivoReduccionTasa(Integer.valueOf(formulario.getIdMotivosRed()));
				motivoReduccionTasa=motivoReduccionTasaManager.toMotivoReduccionTasa(motivoReduccionTasaBean);
				pagoSolicitudBean.setMotivoReduccionTasa(motivoReduccionTasa);		
				solicitaExencion = true;
			}
																	
			if(formulario.getIdEntidadBancaria() != null && !formulario.getIdEntidadBancaria().equals("")){
				EntidadFinanciera entidadFinanciera = new EntidadFinanciera();
				entidadFinanciera.setId(Integer.valueOf(formulario.getIdEntidadBancaria()));
				pagoSolicitudBean.setEntidadFinanciera(entidadFinanciera);
			}
			
			pagoSolicitudBean.setSolicitaReduccion(solicitaExencion ? 'S' : 'N');	
			pagoSolicitudBean.setNrc(formulario.getNrcPago());
			ArrayList<PagoSolicitudBean> arrPagoSolicitudBean = new ArrayList<PagoSolicitudBean>();
			arrPagoSolicitudBean.add(pagoSolicitudBean);
			solicitudBean.setPagoSolicitudes(arrPagoSolicitudBean);
			
			// Guardamos los datos de la Comunidad Autónoma 
			
			// Comunidad Autónoma debido a discapacidad
			if(formulario.getComunidadDD() !=null && !formulario.getComunidadDD().equals("")){
				solicitudCcaaBean.setIdComunidadDD(formulario.getComunidadDD());
				if(formulario.getIdProvinciaDomicilio()!=null && !formulario.getIdProvinciaDomicilio().equals("")){
					provincia = new Provincia();
					provincia.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
					solicitudCcaaBean.setProvincia(provincia);
				}
			}
			//Comunidad Autónoma Debido a Familia Numerosa
			if(formulario.getComunidadFN() !=null && !formulario.getComunidadFN().equals("")){
				solicitudCcaaBean.setIdComunidadFN(formulario.getComunidadFN());
			}
			// Número de Título de Familia Numerosa
			if(formulario.getNumeroTituloFN() !=null && !formulario.getNumeroTituloFN().equals("")){
				solicitudCcaaBean.setTituloFamnumerosa(formulario.getNumeroTituloFN());
			}
			//Datos de Registro
			RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();
			registroSolicitudBean.setFechaIntento(utilesIPSG.stringToDate(formulario.getFechaRegistro()));
			if(formulario.getNumeroRegistro() != null && !formulario.getNumeroRegistro().equals("")){	
				registroSolicitudBean.setNumeroRegistro(formulario.getNumeroRegistro());
			}else{
				// si no viene informado el numero de registro,se coge por defecto
				registroSolicitudBean.setNumeroRegistro(properties.getProperty("numeroRegistro"));
			}
			
			registroSolicitudBean.setFechaRegistro(utilesIPSG.stringToDate(formulario.getFechaRegistro()));
			registroSolicitudBean.setSolicitante(Constantes.CIUDADANO);
			
			if(formulario.getOficinaRegistro() != null && !formulario.getOficinaRegistro().equals("")){	
				registroSolicitudBean.setOficinaRegistro(formulario.getOficinaRegistro());
			}else{
				// si la oficina de registro no viene informada, se coge por defecto
				registroSolicitudBean.setOficinaRegistro(properties.getProperty("cdOrOrigen"));
			}	
			
			ArrayList<RegistroSolicitudBean> arrRegistroSolicitudes = new ArrayList<RegistroSolicitudBean>();
			arrRegistroSolicitudes.add(registroSolicitudBean);
			solicitudBean.setRegistroSolicitudes(arrRegistroSolicitudes);
			solicitudBean.setEdadVerificada(Constantes.EDAD_NO_COMPROBADA);
			solicitudBean.setFechaNacimientoVerificada(Constantes.EDAD_NO_COMPROBADA);
			solicitudBean.setTituloVerificado(Constantes.EDAD_NO_COMPROBADA);
			
			solicitudBean.setDesempleoVerificado(Constantes.EDAD_NO_COMPROBADA);
			solicitudBean.setFnumerosaVerificado(Constantes.EDAD_NO_COMPROBADA);
			solicitudBean.setDiscapacidadVerificado(Constantes.EDAD_NO_COMPROBADA);
			
			String mensaje;
			
			//Comprobamos que la solicitud no existe
			boolean existeSolicitud = solicitudPresencialManager.existeSolicitudPresencial(solicitudBean);
			
			if(existeSolicitud){
				mensaje = RESOURCE_BUNDLE.getString("field.solicitudPresencial.mensaje.altaPresencialSolicitudExiste");
				String titulo = RESOURCE_BUNDLE.getString("field.solicitudPresencial.tituloAlta");
				setRequestAttribute("titulo", titulo);
				setRequestAttribute("mensaje", mensaje);
				setRequestAttribute("accion", "/spring/verCrearSolicitudPresencial");
				logger.info("La solicitud presencial ya existe.");
				
				resultado="error";
				
			}else{
				
				SolicitudComunAuxiliarQuery query = new SolicitudComunAuxiliarQuery();
				query.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
				SolicitudComunAuxiliarBean solAux = solicitudComunAuxiliarManager.buscarSolicitudComunAuxiliarById(query);
				if(solAux != null) {
					solicitudBean.setIdConsentimientoAeat(solAux.getIdConsentimientoAEAT());
					solicitudBean.setMotivoOposicion(solAux.getMotivoOposicion());
				}
				
				Integer idSolicitud = 0;
				//Se guarda la Solicitud
				if (solicitudBean != null && solicitudBean.getConvocatoria() != null && solicitudBean.getConvocatoria().getId() != null) {
					idSolicitud = solicitudPresencialManager.guardarSolicitudPresencial(solicitudBean);
				}
				
				
				//Se dan de alta en Pago_Solicitud y Registro Solicitud
				if(idSolicitud != null && !idSolicitud.equals(0))
				{
					SolicitudComun solicitud = new SolicitudComun();
					solicitud.setIdSolicitud(idSolicitud.longValue());
					UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					generarRegistroLogSolicitud(formulario,usuarioBean.getLogin(), idSolicitud.longValue() );

					// 	PAGO_SOLICITUD
					if(formulario.getIdTipoPago() != null && !formulario.getIdTipoPago().equals("") 							
							&& (formulario.getIdTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_INT)))
							&& formulario.getFechaPago() != null && !formulario.getFechaPago().equals("")
							&& formulario.getImporte() != null && !formulario.getImporte().equals("")){
						pagoSolicitudBean.setSolicitud(solicitud);
						pagoSolicitudBean.setCodEntidadFinanciera(formulario.getIdEntidadBancaria());
						pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
						pagoSolicitudManager.guardarPagoSolicitudBean(pagoSolicitudBean);
					}else if(formulario.getIdTipoPago() != null && !formulario.getIdTipoPago().equals("") 							
							&& formulario.getIdTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EXENTO_INT))
							&& formulario.getImporte() != null && !formulario.getImporte().equals("")){
						pagoSolicitudBean.setSolicitud(solicitud);
						pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
						pagoSolicitudManager.guardarPagoSolicitudBean(pagoSolicitudBean);
					}else if(formulario.getIdTipoPago() != null && !formulario.getIdTipoPago().equals("")
							&& formulario.getFechaPago() != null && !formulario.getFechaPago().equals("")
			 				&& formulario.getImporte() != null && !formulario.getImporte().equals("")
							&& formulario.getIdEntidadBancaria() != null && !formulario.getIdEntidadBancaria().equals("")){
						pagoSolicitudBean.setSolicitud(solicitud);
						pagoSolicitudBean.setCodEntidadFinanciera(formulario.getIdEntidadBancaria());
						pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
						pagoSolicitudManager.guardarPagoSolicitudBean(pagoSolicitudBean);
					}else if(formulario.getIdTipoPago() != null && !formulario.getIdTipoPago().equals("") 							
							&& formulario.getIdTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_NINGUNO_INT))
							&& formulario.getImporte() != null && !formulario.getImporte().equals("")){
						pagoSolicitudBean.setSolicitud(solicitud);
						pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
						pagoSolicitudManager.guardarPagoSolicitudBean(pagoSolicitudBean);
					}		
					
					// COMUNIDAD AUTÓNOMA
					
					solicitudCcaaBean.setIdSolicitud(idSolicitud.longValue());
					solicitudCcaaManager.guardarSolicitudCcaa(solicitudCcaaBean);
					
					// REGISTRO_SOLICITUD
					if(formulario.getFechaRegistro() != null && !formulario.getFechaRegistro().equals("")){
						registroSolicitudBean.setSolicitud(solicitud);
						registroSolicitudBean.setResultado(Constantes.RESULTADO_OK);
						registroSolicitudManager.guardarRegistroSolicitud(registroSolicitudBean);
					}
					
					if (formulario.getListaTextAreas() != null && formulario.getListaTextAreas().length > 0) {
						for (CamposPropiosBean campoPropioBean : formulario.getListaTextAreas()) {
							if (campoPropioBean != null && campoPropioBean.getId() != null 
									&& !StringUtils.isEmpty(campoPropioBean.getCampo())) {
								//formato de la lista: idCampo_ValorCampo
								listaCamposAreaText.add(campoPropioBean.getId()+"_"+campoPropioBean.getCampo());
							}
						} 
					}
					//guardamos tb el correspondiente registro en solicitud_propios
					for(int i=0;i<listaCamposAreaText.size();i++){
						SolicitudPropiosBean solicitudPropiosBean = new SolicitudPropiosBean();
						String listaAreaText = listaCamposAreaText.get(i);
						int tam = listaAreaText.length();
						int position=listaAreaText.indexOf('_');
						String idCampo=listaAreaText.substring(0, position);
						String valorCampoPropio=listaAreaText.substring(position+1,tam);
						solicitudPropiosBean.setIdCampo(Long.valueOf(idCampo));
						solicitudPropiosBean.setValor(valorCampoPropio);
						solicitudPropiosBean.setIdSolicitud(Long.valueOf(idSolicitud));
						CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
						camposPropiosQuery.setIdCampo(Integer.parseInt(idCampo));
						CamposPropiosBean camposPropiosBean = camposPropiosManager.buscarCamposPropiosUnico(camposPropiosQuery);
						solicitudPropiosBean.setCamposPropiosBean(camposPropiosBean);
						solicitudPropiosManager.guardarSolicitudPropiosBean(solicitudPropiosBean);
					}
				}
				
				if(idSolicitud.intValue() > 0){
					resultado="success";
					mensaje = RESOURCE_BUNDLE.getString("field.solicitudPresencial.mensaje.confirmacionAltaPresencial");
				}else{
					resultado="error";
					mensaje = RESOURCE_BUNDLE.getString("field.solicitudPresencial.mensaje.altaPresencialError");
				}
				
				String titulo = RESOURCE_BUNDLE.getString("field.solicitudPresencial.tituloAlta");
				
				setRequestAttribute("titulo", titulo);
				setRequestAttribute("mensaje", mensaje);
				
				
				
				String username = recuperarUsuario();
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				usuarioQuery.setLogin(username);
				
				UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
				if (usuarioBean.getDesPerfil() != null && usuarioBean.getDesPerfil().equals(Constantes.DENOM_PERFIL_REGISTRO)){
					// para funcionario registro
					setRequestAttribute("accion", "/spring/verCrearSolicitudPresencial?accion=Alta");
				}else{
					// para el resto de roles
					setRequestAttribute("accion", "/spring/verBuscarSolicitudPresencial?accion=Alta");
				}
			}		
			
			logger.info("-------Salimos de AltaSolicitud Presencial " + resultado);
		}
		
	}catch(Exception eGenerico){
		logger.error("Error AltaSolicitudPresencialSpring - doExecute", eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
			return resultado;
		
	}

	/**
	 * Generar registro log solicitud.
	 *
	 * @param formulario el formulario
	 * @param username el username
	 * @param idSolicitud el id solicitud
	 */
	public void generarRegistroLogSolicitud(AltaSolicitudPresencialForm formulario,String username, Long idSolicitud){
		
		//Cargo los datos en el bean del log solicitudes que se usara para crear el registro en la tabla
		LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
		
		logSolicitudBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logSolicitudBean.setTipoActor(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_USUARIO);
		logSolicitudBean.setActor(username);
		logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.solicitudPresencial.logAlta") + idSolicitud);
		logSolicitudBean.setIdSolicitud(idSolicitud);
		//añadimos los estados de solicitud
		logSolicitudBean.setId_estado_actual(Constantes.ESTADO_SOLICITUD_REGISTRADO);
		
		logSolicitudManager.generarRegistroLogSolicitud(logSolicitudBean);		
	}

	/* RECUPERA EL USUARIO DEL CONTEXTO DE SPRING */
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	/* ****************************************** */
	private String recuperarUsuario() {
		try{
			SecurityContext context = (SecurityContext) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
			
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			return usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error en recuperarUsuarioSesion:", e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return logger Logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el solicitud presencial manager.
	 *
	 * @return  solicitudPresencialManager SolicitudPresencialManager
	 */
	public SolicitudPresencialManager getSolicitudPresencialManager() {
		return solicitudPresencialManager;
	}

	/**
	 * Establece el solicitud presencial manager.
	 *
	 * @param solicitudPresencialManager SolicitudPresencialManager
	 */
	public void setSolicitudPresencialManager(
			SolicitudPresencialManager solicitudPresencialManager) {
		this.solicitudPresencialManager = solicitudPresencialManager;
	}

	/**
	 * Obtiene el message resource.
	 *
	 * @return MESSAGE_RESOURCE
	 */
	public static String getMESSAGE_RESOURCE() {
		return MESSAGE_RESOURCE;
	}

	/**
	 * Obtiene el resource bundle.
	 *
	 * @return RESOURCE_BUNDLE
	 */
	public static ResourceBundle getRESOURCE_BUNDLE() {
		return RESOURCE_BUNDLE;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return pagoSolicitudManager PagoSolicitudManager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager PagoSolicitudManager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return registroSolicitudManager RegistroSolicitudManager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager RegistroSolicitudManager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	/**
	 * Obtiene el log solicitud manager.
	 *
	 * @return el log solicitud manager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager el nuevo log solicitud manager
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
	}

	/**
	 * Obtiene el solicitud propios manager.
	 *
	 * @return el solicitud propios manager
	 */
	public SolicitudesPropiosManager getSolicitudPropiosManager() {
		return solicitudPropiosManager;
	}

	/**
	 * Establece el solicitud propios manager.
	 *
	 * @param solicitudPropiosManager el nuevo solicitud propios manager
	 */
	public void setSolicitudPropiosManager(
			SolicitudesPropiosManager solicitudPropiosManager) {
		this.solicitudPropiosManager = solicitudPropiosManager;
	}

	/**
	 * Obtiene el campos propios manager.
	 *
	 * @return el campos propios manager
	 */
	public CamposPropiosManager getCamposPropiosManager() {
		return camposPropiosManager;
	}

	/**
	 * Establece el campos propios manager.
	 *
	 * @param camposPropiosManager el nuevo campos propios manager
	 */
	public void setCamposPropiosManager(CamposPropiosManager camposPropiosManager) {
		this.camposPropiosManager = camposPropiosManager;
	}

	/**
	 * Obtiene el motivo reduccion tasa manager.
	 *
	 * @return el motivo reduccion tasa manager
	 */
	public MotivoReduccionTasaManager getMotivoReduccionTasaManager() {
		return motivoReduccionTasaManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoReduccionTasaManager el nuevo motivo reduccion tasa manager
	 */
	public void setMotivoReduccionTasaManager(
			MotivoReduccionTasaManager motivoReduccionTasaManager) {
		this.motivoReduccionTasaManager = motivoReduccionTasaManager;
	}

	/**
	 * Obtiene el solicitud ccaa manager.
	 *
	 * @return el solicitud ccaa manager
	 */
	public SolicitudCcaaManager getSolicitudCcaaManager() {
		return solicitudCcaaManager;
	}

	/**
	 * Establece el solicitud ccaa manager.
	 *
	 * @param solicitudCcaaManager el nuevo solicitud ccaa manager
	 */
	public void setSolicitudCcaaManager(SolicitudCcaaManager solicitudCcaaManager) {
		this.solicitudCcaaManager = solicitudCcaaManager;
	}
	
	
	/**
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * @return the solicitudComunAuxiliarManager
	 */
	public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
		return solicitudComunAuxiliarManager;
	}

	/**
	 * @param solicitudComunAuxiliarManager the solicitudComunAuxiliarManager to set
	 */
	public void setSolicitudComunAuxiliarManager(SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
	}

}
