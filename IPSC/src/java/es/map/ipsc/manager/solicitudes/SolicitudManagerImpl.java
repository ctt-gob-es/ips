package es.map.ipsc.manager.solicitudes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CentroGestorDAO;
import es.map.ips.dao.ContadorNumSolicitudCustomDAO;
import es.map.ips.dao.CuerpoEscalaDAO;
import es.map.ips.dao.FormaAccesoDAO;
import es.map.ips.dao.GrupoDAO;
import es.map.ips.dao.MinisterioDAO;
import es.map.ips.dao.ParametrosConfiguracionDAO;
import es.map.ips.dao.SolComunViewDAO;
import es.map.ips.dao.SolicitudComunAuxiliarDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.Modelo;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.ParametrosConfiguracion;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.SolComunView;
import es.map.ips.model.SolComunViewQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.BuscarSolicitudesBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.bean.SolicitudPropiosBean;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.modelo.ModeloManager;
import es.map.ipsc.utils.NumeroSolicitud;

/**
 * El Class SolicitudManagerImpl.
 */
public class SolicitudManagerImpl implements SolicitudManager {
	
	/** el parametros configuracion DAO. */
	private ParametrosConfiguracionDAO parametrosConfiguracionDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
	
	/** el sol comun view DAO. */
	private SolComunViewDAO solComunViewDAO;
	
	/** el contador num solicitud custom DAO. */
	private ContadorNumSolicitudCustomDAO contadorNumSolicitudCustomDAO;
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitud propio manager. */
	private SolicitudPropioManager solicitudPropioManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el ministerio DAO. */
	private MinisterioDAO ministerioDAO;
	
	/** el centro gestor DAO. */
	private CentroGestorDAO centroGestorDAO;
	
	/** el cuerpo escala DAO. */
	private CuerpoEscalaDAO cuerpoEscalaDAO;
	
	/** el forma acceso DAO. */
	private FormaAccesoDAO formaAccesoDAO;		
	
	/** el grupo DAO. */
	private GrupoDAO grupoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudManagerImpl.class);
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#obtenerNumeroSolicitud(boolean)
	 */
	public String obtenerNumeroSolicitud(boolean is007){
		String nSolicitud;
		NumeroSolicitud numeroSolicitud = new NumeroSolicitud();
		ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
		parametrosConfiguracionQuery.setNombreCampo(Constantes.SOLICITUD_PARAMETROS_CONFIG);
		ParametrosConfiguracion parametrosConfiguracion = parametrosConfiguracionDAO.searchUnique(parametrosConfiguracionQuery);

		if(!is007 && parametrosConfiguracion != null && parametrosConfiguracion.getValorCampo().equals(Constantes.SOLICITUD_VALOR_PARAMETROS_CONFIG)){
			nSolicitud = numeroSolicitud.obtenerNumeroJustificanteEJB();
		}else{
			String idModelo = "790";
			String idTasa = "001";
			Integer contador = contadorNumSolicitudCustomDAO.obtenerNumContador(idTasa, idModelo);
			nSolicitud = numeroSolicitud.obtenerNumeroJustificanteLocal(contador,is007);
		}

		logger.info("numero de solicitud generado: "+nSolicitud);
		return nSolicitud;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#detalleSolicitud(es.map.ips.model.SolicitudComunQuery)
	 */
	public DetalleSolicitudBean detalleSolicitud(SolicitudComunQuery solicitudQuery) {
		
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
		if(solicitud != null){
			DetalleSolicitudBean detalleSolicitudBean = toDetalleSolicitudBean(solicitud);
			SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();

			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(solicitud.getIdSolicitud());
			solicitudPropiosQuery.setSolicitudComun(solicitudComunQuery);
			
			solicitudPropiosQuery.addOrder(SolicitudPropiosQuery.CAMPOSPROPIOS,OrderType.ASC);
			ArrayList<SolicitudPropiosBean> solicitudPropios = solicitudPropioManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);

			if(detalleSolicitudBean == null){
				return null;
			}else{
				detalleSolicitudBean.setSolicitudPropiosBean(solicitudPropios);
				return detalleSolicitudBean;
			}
		}else{
			return null;
		}


	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#buscarSolicitudesAll(es.map.ips.model.SolComunViewQuery)
	 */
	public ArrayList<BuscarSolicitudesBean> buscarSolicitudesAll(
			SolComunViewQuery solicitudQuery) {
		solicitudQuery.addIdEstadoSoliciudIn(Constantes.SOLICITUD_ID_NOPAGADO);
		solicitudQuery.addIdEstadoSoliciudIn(Constantes.SOLICITUD_ID_NOREGISTRADO);
		solicitudQuery.addIdEstadoSoliciudIn(Constantes.SOLICITUD_ID_REGISTRADO);
		solicitudQuery.addIdEstadoSoliciudIn(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO);	
		solicitudQuery.addIdEstadoSoliciudIn(Constantes.SOLICITUD_ID_PROCESO_PAGO);
		solicitudQuery.addIdEstadoSoliciudIn(Constantes.SOLICITUD_ID_PROCESO_REGISTRO);
		ArrayList<BuscarSolicitudesBean> solicitudesBean = new ArrayList<BuscarSolicitudesBean>();
		SearchResult<SolComunView> solicitudes=null;
		try{
			solicitudes = solComunViewDAO.search(solicitudQuery);
		}catch(Exception e){
			logger.error("Error Buscar solicitudes",e);
		}
		if(solicitudes == null){
			return null;
		}
		int numTotal = 0;
		if (solicitudes.getNumResults() != null) {
			numTotal = solicitudes.getNumResults();
		}
		for(int i=0;i<solicitudes.getResults().size();i++){
			BuscarSolicitudesBean aux;
			aux = toSolicitudBean(solicitudes.getResults().get(i),numTotal);
			if(aux != null){
				solicitudesBean.add(aux);
			}
		}
		return solicitudesBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#buscarSolicitudesAll(es.map.ips.model.SolicitudComunQuery)
	 */
	public ArrayList<BuscarSolicitudesBean> buscarSolicitudesAll(
			SolicitudComunQuery solicitudQuery) {
		
		solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_REGISTRADO);
		
		ArrayList<BuscarSolicitudesBean> solicitudesBean = new ArrayList<BuscarSolicitudesBean>();
		SearchResult<SolicitudComun> solicitudes=null;
		try{
			/*INI-TRA022*/
			solicitudQuery.addOrder(SolicitudComunQuery.FECHASOLICITUD, OrderType.DESC);
			/*FIN-TRA022*/
			solicitudes = solicitudComunDAO.search(solicitudQuery);
		}catch(Exception e){
			logger.error("Error Buscar solicitudes",e);
		}
		if(solicitudes == null){
			return null;
		}
		int numTotal = 0;
		if (solicitudes.getNumResults() != null) {
			numTotal = solicitudes.getNumResults();
		}
		for(int i=0;i<solicitudes.getResults().size();i++){
			BuscarSolicitudesBean aux;
			aux = toSolicitudBean(solicitudes.getResults().get(i),numTotal);
			if(aux != null){
				solicitudesBean.add(aux);
			}
		}
		return solicitudesBean;
	}
	
	/**
	 * To solicitud bean.
	 *
	 * @param solicitudView el solicitud view
	 * @param numTotal el num total
	 * @return el buscar solicitudes bean
	 */
	private BuscarSolicitudesBean toSolicitudBean(SolicitudComun solicitudView,
			int numTotal) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		// Se muestran sólo campos visibles
		BuscarSolicitudesBean aux = new BuscarSolicitudesBean();
		aux.setIdSolicitud(solicitudView.getIdSolicitud());
		aux.setIdConvocatoria(solicitudView.getConvocatoria().getId());
		aux.setCuerpoEscala(solicitudView.getConvocatoria().getCuerpoEscala().getDescripcion());
		aux.setEjercicioSolicitud(solicitudView.getConvocatoria().getEjercicio());
		aux.setIdEstadoInscripcion(solicitudView.getEstadoSolicitud().getId());
		aux.setEstadoInscripcion(solicitudView.getEstadoSolicitud().getDescripcion());
		
		if(solicitudView.getFechaSolicitud() != null){
			aux.setFecha(formatoFecha.format(solicitudView.getFechaSolicitud()));
		}
		
		aux.setCentroGestor(solicitudView.getConvocatoria().getCuerpoEscala().getCentroGestor().getDescripcion());
		aux.setSiglasCentroGestor(solicitudView.getConvocatoria().getCuerpoEscala().getCentroGestor().getSiglas());
		aux.setFormaAcceso(solicitudView.getConvocatoria().getFormaAcceso().getDescripcion());
		aux.setGrupo(solicitudView.getConvocatoria().getCuerpoEscala().getGrupo().getDescripcion());
		aux.setMinisterio(solicitudView.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion());
		aux.setNumJustificante(solicitudView.getNumeroSolicitud());
		aux.setNumTotal(numTotal);

		return aux;
	}

	/**
	 * To solicitud bean.
	 *
	 * @param solicitudView el solicitud view
	 * @param numTotal el num total
	 * @return el buscar solicitudes bean
	 */
	private BuscarSolicitudesBean toSolicitudBean(SolComunView solicitudView,
			int numTotal) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		// Se muestran sólo campos visibles
		BuscarSolicitudesBean aux = new BuscarSolicitudesBean();
		aux.setIdSolicitud(solicitudView.getIdSolicitud());
		aux.setIdConvocatoria(solicitudView.getIdConvocatoria());
		aux.setCuerpoEscala(solicitudView.getDescripcionCuerpoEscala());
		aux.setEjercicioSolicitud(solicitudView.getEjercicioConvocatoria());
		aux.setIdEstadoInscripcion(solicitudView.getIdEstadoSoliciud());
		aux.setEstadoInscripcion(solicitudView.getDesEstadoSolicitud());
		
		if(solicitudView.getFechaSolicitud() != null){
			aux.setFecha(formatoFecha.format(solicitudView.getFechaSolicitud()));
		}
		
		aux.setCentroGestor(solicitudView.getDesCentroGestor());
		aux.setSiglasCentroGestor(solicitudView.getSigCentroGestor());
		aux.setFormaAcceso(solicitudView.getDesFormaAcceso());	
		aux.setGrupo(solicitudView.getDesGrupo());
		aux.setMinisterio(solicitudView.getDescripcionMinisterio());
		aux.setNumJustificante(solicitudView.getNumueroSolicitud());
		aux.setNumTotal(numTotal);

		return aux;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#buscarRegistroSolicitud(es.map.ips.model.SolicitudComunQuery)
	 */
	public SolicitudBean buscarRegistroSolicitud(SolicitudComunQuery solicitudQuery) {
		SearchResult<SolicitudComun> solicitudes = null;
		solicitudes = solicitudComunDAO.search(solicitudQuery);
		if(solicitudes != null){
			for(int i=0;i<solicitudes.size();i++){
				return toSolicitudBean(solicitudes.getResults().get(0));
			}				
		}
		return null;
	}

	/**
	 * To detalle solicitud bean.
	 *
	 * @param solicitud el solicitud
	 * @return el detalle solicitud bean
	 */
	private DetalleSolicitudBean toDetalleSolicitudBean(SolicitudComun solicitud) {
		//Formato de fechas
		SimpleDateFormat formatoFecha= new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		//Datos de la convocatoria
		String centroGestor = "";
		if(solicitud.getConvocatoria() != null){
			centroGestor = solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getDescripcion().toUpperCase();
		}
		String numJustificante = solicitud.getNumeroSolicitud();
		String ejercicioSolicitud = solicitud.getConvocatoria().getEjercicio();
		String registro = String.valueOf(solicitud.getIdSolicitud());
		String nacionalidad = "";
		if(solicitud.getNacionalidad() != null){
			nacionalidad = solicitud.getNacionalidad().toUpperCase();
		}
		String correoElectronico = "";
		if(solicitud.getEmail() != null){
			correoElectronico = solicitud.getEmail();
		}
		String estadoSolicitud = "";
		Integer idEstadoSolicitud = 0;
		if(solicitud.getEstadoSolicitud() != null){
			
			estadoSolicitud = solicitud.getEstadoSolicitud().getDescripcion().toUpperCase();
		}

		//Datos del usuario
		String nombre = "";
		String primerApellido ="";
		String segundoApellido = "";

		if(solicitud.getNombre() != null){
			nombre = solicitud.getNombre().toUpperCase();
		}if(solicitud.getPrimerApellido() != null){
			primerApellido = solicitud.getPrimerApellido().toUpperCase();
		}if(solicitud.getSegundoApellido() != null){
			segundoApellido = solicitud.getSegundoApellido().toUpperCase();
		}


		String fechaNacimiento = "";
		if(solicitud.getFechaNacimiento() != null){
			fechaNacimiento = formatoFecha.format(solicitud.getFechaNacimiento());
		}
		String nif = solicitud.getNif();
		char sexo = ' ';
		if(solicitud.getSexo() != null && !"".equals(solicitud.getSexo())){
			sexo = solicitud.getSexo();
		}

		String provinciaNacimiento = "";
		if(solicitud.getProvinciaByIdProvinciaNacimiento() != null && 
				!"".equals(solicitud.getProvinciaByIdProvinciaNacimiento().getDescripcion())){
			provinciaNacimiento = solicitud.getProvinciaByIdProvinciaNacimiento().getDescripcion().toUpperCase();
		}

		String localidadNacimiento = "";
		if(solicitud.getLocalidadNacimiento() != null &&
				!"".equals(solicitud.getLocalidadNacimiento())){
			localidadNacimiento = solicitud.getLocalidadNacimiento().toUpperCase();
		}
		//Datos del domicilio

		String calleDomicilio = "";
		if(solicitud.getCalleDomicilio() != null && !"".equals(solicitud.getCalleDomicilio())){
			calleDomicilio= solicitud.getCalleDomicilio().toUpperCase();
		}

		String codPostalDomicilio = "";
		if(solicitud.getCodigoPostalDomicilio() != null && !"".equals(solicitud.getCodigoPostalDomicilio())){
			codPostalDomicilio= solicitud.getCodigoPostalDomicilio();
		}

		String municipioDomicilio = "";
		if(solicitud.getMunicipioDomicilio() != null && !"".equals(solicitud.getMunicipioDomicilio())){
			municipioDomicilio= solicitud.getMunicipioDomicilio().toUpperCase();
		}

		String provinciaDomicilio = "";
		if(solicitud.getProvinciaByIdProvinciaDomicilio() != null && !"".equals(solicitud.getProvinciaByIdProvinciaDomicilio())){
			provinciaDomicilio = solicitud.getProvinciaByIdProvinciaDomicilio().getDescripcion().toUpperCase();
		}

		String nacionDomicilio = "";
		if(solicitud.getPais() != null && !"".equals(solicitud.getPais().getDescripcion())){
			nacionDomicilio= solicitud.getPais().getDescripcion().toUpperCase();
		}

		String telefono = "";
		String telefono1 = "";
		String telefonoAux = "";
		if(!StringUtils.isEmpty(solicitud.getTelefono()) && solicitud.getTelefono().length() >= 9){
			telefono= solicitud.getTelefono();
			telefono1 = telefono.substring(0, 9);
			if(telefono.length()>10)
				telefonoAux = telefono.substring(10);
		}

		//Datos de la convocatoria

		String cuerpo= "";
		if(solicitud.getConvocatoria().getCuerpoEscala() != null && !"".equals(solicitud.getConvocatoria().getCuerpoEscala())){
			cuerpo= solicitud.getConvocatoria().getCuerpoEscala().getDescripcion().toUpperCase();
		}
		String grupo = "";
		if(solicitud.getConvocatoria().getCuerpoEscala().getGrupo() != null){
			grupo = String.valueOf(solicitud.getConvocatoria().getCuerpoEscala().getGrupo().getDescripcion().toUpperCase());
		}
		String especialidad = "";
		if(solicitud.getEspecialidad() != null && !"".equals(solicitud.getEspecialidad())){
			especialidad = solicitud.getEspecialidad().getDescripcion().toUpperCase();
		}
		String formaAcceso = "";
		if(solicitud.getConvocatoria().getFormaAcceso() != null && !"".equals(solicitud.getConvocatoria().getFormaAcceso())){
			formaAcceso = solicitud.getConvocatoria().getFormaAcceso().getDescripcion().toUpperCase();
		}
		
		String ministerio = "";
		if(solicitud.getConvocatoria().getMinisterioConvocante() != null 
				&& !"".equals(solicitud.getConvocatoria().getMinisterioConvocante())){
			ministerio = solicitud.getConvocatoria().getMinisterioConvocante().getDescripcion().toUpperCase();
		}else{
			if(solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio() != null 
					&& !"".equals(solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio())){
				ministerio = solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion().toUpperCase();
			}
		}
				
		String fechaBOE = "";
		if(solicitud.getConvocatoria().getFechaBoe() != null && !"".equals(solicitud.getConvocatoria().getFechaBoe())){
			fechaBOE = formatoFecha.format(solicitud.getConvocatoria().getFechaBoe());
		}
		String provinciaExamen = "";
		if(solicitud.getProvinciaByIdProvinciaExamen() != null && !"".equals(solicitud.getProvinciaByIdProvinciaExamen())){
			provinciaExamen = solicitud.getProvinciaByIdProvinciaExamen().getDescripcion().toUpperCase();
		}
		String discapacidad = "";
		if(solicitud.getTipoDiscapacidad() != null && !"".equals(solicitud.getTipoDiscapacidad())){
			discapacidad = solicitud.getTipoDiscapacidad().getDescripcion().toUpperCase();
		}
		char reservaDiscapacitados = ' ';
		if(solicitud.getReservaDiscapacidad() != null && !"".equals(solicitud.getReservaDiscapacidad())){
			reservaDiscapacitados = solicitud.getReservaDiscapacidad();
		}
		String auxAdaptacion = "0";
		if(solicitud.getPorcentajeDiscapacidad() != null){
			auxAdaptacion = String.valueOf(solicitud.getPorcentajeDiscapacidad());
		}
		Float importe = 0f;
		if(solicitud.getConvocatoria().getImporte() != null){
			importe = solicitud.getConvocatoria().getImporte();
		}
		String dirigidoA = "";
		if(solicitud.getConvocatoria().getDirigidoA() != null){
			dirigidoA = solicitud.getConvocatoria().getDirigidoA().toUpperCase();
		}

		Long adaptacionDiscapacidad = 0L;
		if(auxAdaptacion != null){
			adaptacionDiscapacidad = Long.parseLong(auxAdaptacion);
		}

		String motivoDiscapacidad = "";
		if(solicitud.getDetalleDiscapacidad() != null && !"".equals(solicitud.getDetalleDiscapacidad())){
			motivoDiscapacidad= solicitud.getDetalleDiscapacidad().toUpperCase();
		}

		//Titulos academicos oficiales

		String titulos = "";
		if(solicitud.getTituloOficial() != null){
			titulos= solicitud.getTituloOficial().getDescripcion().toUpperCase();
		}
		
		String otrosTitulos = "";
		if(solicitud.getOtrosTitulos() != null){
			otrosTitulos= solicitud.getOtrosTitulos().toUpperCase();
		}

		//CONVERTIR EL MODEL AL BEAN
		DetalleSolicitudBean solicitudBean = new DetalleSolicitudBean();

		//Datos de la convocatoria
		solicitudBean.setCentroGestor(centroGestor);
		solicitudBean.setNumJustificante(numJustificante);
		solicitudBean.setEjercicioSolicitud(ejercicioSolicitud);
		solicitudBean.setRegistro(registro);
		solicitudBean.setNacionalidad(nacionalidad);
		solicitudBean.setCorreoElectronico(correoElectronico);
		solicitudBean.setDirigidoA(dirigidoA);
		//Datos del usuario
		solicitudBean.setNombre(nombre);
		solicitudBean.setPrimerApellido(primerApellido);
		solicitudBean.setSegundoApellido(segundoApellido);
		solicitudBean.setFechaNacimiento(fechaNacimiento);
		String nombreCompleto = nombre + " " + primerApellido +  " " + segundoApellido;
		solicitudBean.setNombreCompleto(nombreCompleto);
		solicitudBean.setNif(nif);
		solicitudBean.setSexo(sexo);
		solicitudBean.setProvinciaNacimiento(provinciaNacimiento);
		solicitudBean.setLocalidadNacimiento(localidadNacimiento);
		
		if(solicitud.getIdConsentimiento()!= null)
		{
			if(solicitud.getIdConsentimiento() == true)
			{
				solicitudBean.setConsentimiento(Constantes.NO);
			}else if(solicitud.getIdConsentimiento() == false){
				solicitudBean.setConsentimiento(Constantes.SI);
			}
		}else{
			solicitudBean.setConsentimiento("");
		}	

		//Motivo Oposicion
		String motivoOposicion = "";
		if(solicitud.getMotivoOposicion() != null && !"".equals(solicitud.getMotivoOposicion())){
			motivoOposicion= solicitud.getMotivoOposicion().toUpperCase();
		}	
		
//		if(solicitud.getMotivoOposicion()!= " "){
//			solicitudBean.setMotivoOposicion(solicitud.getMotivoOposicion());			
//		}
//		
//		String motivoOposicion = "";
//		if(solicitudBean.getMotivoOposicion() != null){
//			motivoOposicion = solicitudBean.getMotivoOposicion().toUpperCase();
//		}
//		solicitudBean.setMotivoOposicion(motivoOposicion);	
		
		//Motivo Oposicion
//		if(solicitud.getMotivoOposicion() != null && solicitud.getMotivoOposicion()!= " "){
//			solicitudBean.setMotivoOposicion(solicitud.getMotivoOposicion());			
//		}
		
		//Datos del domicilio
		solicitudBean.setCalleDomicilio(calleDomicilio);
		solicitudBean.setCodPostalDomicilio(codPostalDomicilio);
		solicitudBean.setMunicipioDomicilio(municipioDomicilio);
		solicitudBean.setProvinciaDomicilio(provinciaDomicilio);
		solicitudBean.setNacionDomicilio(nacionDomicilio);
		solicitudBean.setTelefono(telefono);
		solicitudBean.setTelefono1(telefono1);
		solicitudBean.setTelefonoAux(telefonoAux);
		//Datos de la convocatoria
		solicitudBean.setCuerpo(cuerpo);
		solicitudBean.setGrupo(grupo);
		solicitudBean.setEspecialidad(especialidad);
		solicitudBean.setFormaAcceso(formaAcceso);
		solicitudBean.setMinisterio(ministerio);
		solicitudBean.setFechaBOE(fechaBOE);
		solicitudBean.setProvinciaExamen(provinciaExamen);
		solicitudBean.setDiscapacidad(discapacidad);		
		solicitudBean.setReservaDiscapacitados(reservaDiscapacitados);
		solicitudBean.setAdaptacionDiscapacidad(adaptacionDiscapacidad);
		solicitudBean.setMotivoDiscapacidad(motivoDiscapacidad);
		solicitudBean.setImporte(importe);
		//Titulos academicos oficiales
		solicitudBean.setTitulos(titulos);
		solicitudBean.setOtrosTitulos(otrosTitulos);

		//Otros datos
		solicitudBean.setEstadoSolicitud(estadoSolicitud);
		solicitudBean.setIdEstadoSolicitud(idEstadoSolicitud);
		return solicitudBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#actualizarEstadoSolicitud(es.map.ips.model.SolicitudComunQuery, es.map.ips.model.EstadoSolicitudQuery)
	 */
	public Long actualizarEstadoSolicitud(SolicitudComunQuery solicitudQuery,EstadoSolicitudQuery estadoSolicitudQuery) {
		//Busca la solicitud
		SolicitudComun auxSolicitud = solicitudComunDAO.searchUnique(solicitudQuery);
		if(auxSolicitud == null){
			return 0L;
		}	
		Long result;
		Date fechaActualizacion = new Date();
		//Busca el estado por el id de estado
		EstadoSolicitud estadoSolicitud = estadoSolicitudManager.buscarIdEstadoSolicitudModel(estadoSolicitudQuery); 
		if(estadoSolicitud != null){
			//Si encuentra el estado se lo asigna a la query
			logger.info("1.SolicitudManagerImpl-Estado: "+estadoSolicitud.getDescripcion());
			auxSolicitud.setEstadoSolicitud(estadoSolicitud);
			auxSolicitud.setFechaUltActualizacion(fechaActualizacion);
		}else{
			result = 0L;
			logger.info("2.SolicitudManagerImpl-Estado-ERROR");
		}	
		try{
			//Actualiza la solicitud con el estado nuevo
			solicitudComunDAO.update(auxSolicitud);
			result = 1L;
		}catch(Exception e){
			logger.warn(e);
			result = 0L;
			logger.error("Error actualizar estado solicitud",e);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#buscarUltimaSolicitud(es.map.ipsc.bean.CiudadanoBean)
	 */
	public SolicitudBean buscarUltimaSolicitud(CiudadanoBean ciudadanoBean) {
		SearchResult<SolicitudComun> solicitud = null;
		if(ciudadanoBean!=null){
			//Coge el nif del ciudadano
			String nif = ciudadanoBean.getNif();
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			solicitudQuery.setNif(nif);
			//Ordena las solicitudes por fecha para coger la ultima realizada
			solicitudQuery.addOrder("fechaSolicitud",OrderType.DESC );
			//Realiza la busqueda
			solicitud = solicitudComunDAO.search(solicitudQuery);
		}
		if(solicitud != null && solicitud.size()>0){
			for(int i=0;i<solicitud.getResults().size();i++){
				if(solicitud.getResults().get(i).getFechaSolicitud() != null && !"".equals(solicitud.getResults().get(0).getFechaSolicitud())){
					return toSolicitudBean(solicitud.getResults().get(i));
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#buscarUltimaSolicitud2(es.map.ipsc.bean.CiudadanoBean)
	 */
	public SolicitudBean buscarUltimaSolicitud2(CiudadanoBean ciudadanoBean) {
		if(ciudadanoBean!=null){
			//Coge el nif del ciudadano
			String nif = ciudadanoBean.getNif();
			logger.info("search UltSolicitud");
			Date date1 = new Date();
			SolicitudComun solicitud = solicitudComunDAO.buscarUltimaSolicitud(nif);
			Date date2 = new Date();
			logger.info("search UltSolicitud end: "+(date2.getTime()-date1.getTime()));
			if(solicitud!=null){
				return toSolicitudBean(solicitud);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#buscarSolicitudById(es.map.ips.model.SolicitudComunQuery)
	 */
	public SolicitudBean buscarSolicitudById(SolicitudComunQuery solicitudQuery) {
		SearchResult<SolicitudComun> solicitud = null;
		solicitud = solicitudComunDAO.search(solicitudQuery);

		if(solicitud != null && solicitud.size()>0){
			for(int i=0;i<solicitud.getResults().size();i++){
				if(solicitud.getResults().get(i).getEstadoSolicitud().getId()!=Constantes.SOLICITUD_ID_ELIMINADO){
					return toSolicitudBean(solicitud.getResults().get(i));
				}
			}
		}
		return null;
	}


	/**
	 * To solicitud bean.
	 *
	 * @param solicitud el solicitud
	 * @return el solicitud bean
	 */
	private SolicitudBean toSolicitudBean(SolicitudComun solicitud) {
		SolicitudBean result = new SolicitudBean();
		result.setId(solicitud.getIdSolicitud().longValue());
		result.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		result.setIdConvocatoria(solicitud.getConvocatoria().getId());
		if(solicitud.getFechaSolicitud() != null){
			result.setFechaSolicitud(solicitud.getFechaSolicitud());
		}
		if(solicitud.getNif() != null){
			result.setNif(solicitud.getNif());
		}
		if(solicitud.getIdConsentimiento() != null){
			result.setIdConsentimiento(solicitud.getIdConsentimiento());
		}
		if(solicitud.getFechaUltActualizacion() != null){
			result.setFechaUtlActualizacion(solicitud.getFechaUltActualizacion());
		}
		if(solicitud.getNombre() != null){
			result.setNombre(solicitud.getNombre());
		}
		if(solicitud.getPrimerApellido() != null){
			result.setPrimerApellido(solicitud.getPrimerApellido());
		}
		if(solicitud.getSegundoApellido() != null){
			result.setSegundoApellido(solicitud.getSegundoApellido());
		}
		if(solicitud.getFechaNacimiento() != null){
			result.setFechaNacimiento(solicitud.getFechaNacimiento());
		}
		if(solicitud.getSexo() != null){
			result.setSexo(solicitud.getSexo());
		}
		if(solicitud.getNacionalidad() != null){
			result.setNacionalidad(solicitud.getNacionalidad());
		}
		if(solicitud.getLocalidadNacimiento() != null){
			result.setLocalidadNacimiento(solicitud.getLocalidadNacimiento());
		}
		if(solicitud.getTelefono() != null){
			result.setTelefono(solicitud.getTelefono());
		}
		if(solicitud.getCalleDomicilio() != null){
			result.setCalleDomicilio(solicitud.getCalleDomicilio());
		}
		if(solicitud.getCodigoPostalDomicilio() != null){
			result.setCodigoPostalDomicilio(solicitud.getCodigoPostalDomicilio());
		}
		if(solicitud.getMunicipioDomicilio() != null){
			result.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		}
		if(solicitud.getProvinciaByIdProvinciaDomicilio() != null){
			result.setProvinciaNacimiento(solicitud.getProvinciaByIdProvinciaDomicilio());
		}
		if(solicitud.getProvinciaByIdProvinciaDomicilio() != null){
			result.setProvinciaDomicilio(solicitud.getProvinciaByIdProvinciaDomicilio());
		}
		if(solicitud.getProvinciaByIdProvinciaDomicilio() != null){
			result.setProvinciaDomicilioDes(solicitud.getProvinciaByIdProvinciaDomicilio().getDescripcion());
		}
		if(solicitud.getProvinciaByIdProvinciaExamen() != null){
			result.setProvinciaExamen(solicitud.getProvinciaByIdProvinciaExamen());
			result.setDesProvinciaExamen(solicitud.getProvinciaByIdProvinciaExamen().getDescripcion());
		}
		if(solicitud.getPais() != null){
			result.setPaisDomicilio(solicitud.getPais());
		}
		if(solicitud.getPais() != null){
			result.setPaisDomicilioDes(solicitud.getPais().getDescripcion());
		}
		if(solicitud.getComentarios() != null){
			result.setComentarios(solicitud.getComentarios());
		}
		if(solicitud.getEmail() != null){
			result.setEmail(solicitud.getEmail());
		}
		if(solicitud.getEdadVerificada() != null){
			result.setEdadVerificada(solicitud.getEdadVerificada());
		}
		if(solicitud.getTituloVerificado() != null){
			result.setTituloVerificado(solicitud.getTituloVerificado());
		}
		if(solicitud.getFechaNacimientoVerificada() != null){
			result.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}
		result.setEspecialidad(solicitud.getEspecialidad());
		if(solicitud.getEspecialidad() != null){
			result.setDesEspecialidad(solicitud.getEspecialidad().getDescripcion());
		}
		result.setTituloOficial(solicitud.getTituloOficial());
		if(solicitud.getTituloOficial() != null){
			result.setDesTituloOficial(solicitud.getTituloOficial().getDescripcion());
		}
		if(solicitud.getOtrosTitulos() != null){
			result.setOtrosTitulos(solicitud.getOtrosTitulos());
		}
		
		result.setTipoSolicitud(solicitud.getTipoSolicitud());
		result.setEstadoSolicitud(solicitud.getEstadoSolicitud());
		if(solicitud.getEstadoSolicitud() != null){
			result.setDesEstadoSolicitud(solicitud.getEstadoSolicitud().getDescripcion());
			result.setIdEstadoSolicitud(String.valueOf(solicitud.getEstadoSolicitud().getId()));
		}
		result.setTipoDiscapacidad(solicitud.getTipoDiscapacidad());
		if(solicitud.getTipoDiscapacidad() != null){
			result.setTipoDiscapacidadDes(solicitud.getTipoDiscapacidad().getDescripcion());
		}
		String porcentajeDiscapacidad = String.valueOf(solicitud.getPorcentajeDiscapacidad());
		if(porcentajeDiscapacidad != null && !"".equals(porcentajeDiscapacidad) && !"null".equals(porcentajeDiscapacidad)){
			try{
				result.setPorcentajeDiscapacidad(Long.parseLong(porcentajeDiscapacidad));
			}catch(Exception e){
				logger.info("PorcentajeDiscapacidad: "+ porcentajeDiscapacidad);
				logger.warn(e);
				logger.error("Error porcentaje discapacidad" + porcentajeDiscapacidad,e);
			}
		}
		if(solicitud.getReservaDiscapacidad() != null){
			result.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		}
		if(solicitud.getDetalleDiscapacidad() != null){
			result.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
		}

		if(solicitud.getConvocatoria() != null){
			result.setConvocatoria(solicitud.getConvocatoria());
		}

		if(solicitud.getIdConsentimiento() != null){
			result.setIdConsentimiento(solicitud.getIdConsentimiento());
		}

		if(solicitud.getIdConsentimientoAEAT() != null){
			result.setIdConsentimientoAEAT(solicitud.getIdConsentimientoAEAT());
		}
		
		if(solicitud.getMotivoOposicion() != null){
			result.setMotivoOposicion(solicitud.getMotivoOposicion());
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#guardarSolicitud(es.map.ipsc.bean.SolicitudBean)
	 */
	public Long guardarSolicitud(SolicitudBean solicitudBean) {
		EstadoSolicitud estadoSolicitud;
		EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
		estadoSolicitudQuery.setId(Constantes.SOLICITUD_ID_NOPAGADO);
		estadoSolicitud = estadoSolicitudManager.buscarIdEstadoSolicitudModel(estadoSolicitudQuery);
		
		Convocatoria convocatoria;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(solicitudBean.getIdConvocatoria());
		convocatoria = convocatoriaManager.buscarConvocatoriaIdModel(convocatoriaQuery);
		solicitudBean.setEstadoSolicitud(estadoSolicitud);
		solicitudBean.setConvocatoria(convocatoria);
		
		Modelo modelo;
		ModeloQuery modeloQuery = new ModeloQuery();
		modeloQuery.setNumModelo(solicitudBean.getModelo().getNumModelo());
		modelo = modeloManager.buscarModeloByNumModelo(modeloQuery);
		solicitudBean.setModelo(modelo);
		
		if(null!=modelo && !"".equals(modelo.getNumModelo())){
			logger.info("3.SolicitudManagerImpl-Modelo: "+modelo.getNumModelo());
		}else{
			logger.error("4.SolicitudManagerImpl-Modelo-ERROR");
		}

		Long id =solicitudComunDAO.insert(toSolicitud(solicitudBean));

		return id;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#buscarSolicitudesCerradasAll(es.map.ips.model.SolComunViewQuery)
	 */
	public List<BuscarSolicitudesBean> buscarSolicitudesCerradasAll(
			SolComunViewQuery solicitudQuery) {
		solicitudQuery.addIdEstadoSoliciudIn(Constantes.SOLICITUD_ID_REGISTRADO);
		ArrayList<BuscarSolicitudesBean> solicitudesBean = new ArrayList<BuscarSolicitudesBean>();
		SearchResult<SolComunView> solicitudes=null;
		try{
			solicitudes = solComunViewDAO.search(solicitudQuery);
		}catch(Exception e){
			logger.error("Error Buscar solicitudes cerradas",e);
		}
		if(solicitudes == null){
			return null;
		}
		int numTotal = 0;
		if (solicitudes.getNumResults() != null) {
			numTotal = solicitudes.getNumResults();
		}
		for(int i=0;i<solicitudes.getResults().size();i++){
			BuscarSolicitudesBean aux;
			aux = toSolicitudBean(solicitudes.getResults().get(i),numTotal);
			if(aux != null){
				solicitudesBean.add(aux);
			}
		}
		return solicitudesBean;
	}


	/**
	 * To solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el solicitud comun
	 */
	private SolicitudComun toSolicitud(SolicitudBean solicitudBean) {
		SolicitudComun result = new SolicitudComun();
		if(solicitudBean.getNumeroSolicitud() != null){
			result.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
		}
		if(solicitudBean.getFechaNacimiento() != null){
			result.setFechaNacimiento(solicitudBean.getFechaNacimiento());
		}
		if(solicitudBean.getNif() != null){
			result.setNif(solicitudBean.getNif());
		}
		if(solicitudBean.getSexo() != ' '){
			result.setSexo(solicitudBean.getSexo());
		}
		if(solicitudBean.getProvinciaNacimiento() != null){
			result.setProvinciaByIdProvinciaNacimiento(solicitudBean.getProvinciaNacimiento());
		}
		if(solicitudBean.getLocalidadNacimiento() != null){
			result.setLocalidadNacimiento(solicitudBean.getLocalidadNacimiento());
		}
		if(solicitudBean.getNacionalidad() != null){
			result.setNacionalidad(solicitudBean.getNacionalidad());
		}
		if(solicitudBean.getTelefono() != null){
			result.setTelefono(solicitudBean.getTelefono());
		}
		if(solicitudBean.getEmail() != null){
			result.setEmail(solicitudBean.getEmail());
		}
		if(solicitudBean.getCalleDomicilio() != null){
			result.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		}
		if(solicitudBean.getCodigoPostalDomicilio() != null){
			result.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		}
		if(solicitudBean.getMunicipioDomicilio() != null){
			result.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		}
		if(solicitudBean.getProvinciaDomicilio() != null){
			result.setProvinciaByIdProvinciaDomicilio(solicitudBean.getProvinciaDomicilio());
		}
		if(solicitudBean.getPaisDomicilio() != null){
			result.setPais(solicitudBean.getPaisDomicilio());
		}
		if(solicitudBean.getConvocatoria() != null){
			result.setConvocatoria(solicitudBean.getConvocatoria());
		}
		if(solicitudBean.getEspecialidad() != null){
			result.setEspecialidad(solicitudBean.getEspecialidad());
		}
		if(solicitudBean.getProvinciaExamen() != null){
			result.setProvinciaByIdProvinciaExamen(solicitudBean.getProvinciaExamen());
		}
		if(solicitudBean.getTipoDiscapacidad() != null){
			result.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad());
		}
		if(solicitudBean.getIdConsentimiento() != null){
			result.setIdConsentimiento(solicitudBean.getIdConsentimiento());
		}
		
		if(solicitudBean.getMotivoOposicion() != null){
			result.setMotivoOposicion(solicitudBean.getMotivoOposicion());
		}
		
		String auxPorcentaje = String.valueOf(solicitudBean.getPorcentajeDiscapacidad());
		Short auxPorcentajeLong = 0;
		
		try{
			if(!"null".equals(auxPorcentaje)){
				auxPorcentajeLong = Short.parseShort(auxPorcentaje);
			}
		}catch(Exception e){
			logger.error("Error parsear porcentaje de discapacidad",e);
		}
		
		if(auxPorcentajeLong != null){
			result.setPorcentajeDiscapacidad(auxPorcentajeLong);
		}
		if(solicitudBean.getReservaDiscapacidad() != ' '){
			result.setReservaDiscapacidad(solicitudBean.getReservaDiscapacidad());
		}
		if(solicitudBean.getDetalleDiscapacidad() != null){
			result.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		}
		if(solicitudBean.getTituloOficial() != null){
			result.setTituloOficial(solicitudBean.getTituloOficial());
		}
		if(solicitudBean.getEstadoSolicitud() != null){
			result.setEstadoSolicitud(solicitudBean.getEstadoSolicitud());
		}
		if(solicitudBean.getFechaUtlActualizacion() != null){
			result.setFechaUltActualizacion(solicitudBean.getFechaUtlActualizacion());
		}
		if(solicitudBean.getTipoSolicitud() != null){
			result.setTipoSolicitud(solicitudBean.getTipoSolicitud());
		}
		if(solicitudBean.getOtrosTitulos()!=null){
			result.setOtrosTitulos(solicitudBean.getOtrosTitulos());
		}
		
		result.setFechaNacimientoVerificada(Constantes.PLANTILLA_NO);
		result.setTituloVerificado(Constantes.PLANTILLA_NO);
		result.setEdadVerificada(Constantes.PLANTILLA_NO);
		result.setDesempleoVerificado(Constantes.PLANTILLA_NO);
		result.setFnumerosaVerificado(Constantes.PLANTILLA_NO);
		result.setDiscapacidadVerificado(Constantes.PLANTILLA_NO);
		result.setVictimasVerificado(Constantes.PLANTILLA_NO);
		
		if(solicitudBean.getFechaSolicitud() != null){
			result.setFechaSolicitud(solicitudBean.getFechaSolicitud());
		}
		if(solicitudBean.getComentarios() != null){
			result.setComentarios(solicitudBean.getComentarios());
		}
		if(solicitudBean.getNombre() != null){
			result.setNombre(solicitudBean.getNombre());
		}
		if(solicitudBean.getPrimerApellido() != null){
			result.setPrimerApellido(solicitudBean.getPrimerApellido());
		}
		if(solicitudBean.getSegundoApellido() != null){
			result.setSegundoApellido(solicitudBean.getSegundoApellido());
		}
		if(solicitudBean.getModelo() != null){
			result.setModelo(solicitudBean.getModelo());
		}
		if(solicitudBean.getFunHabilitado() != null){
			result.setFunHabilitado(solicitudBean.getFunHabilitado());
		}
		if(solicitudBean.getFuncionarioHabilitado() != null){
			result.setFuncionarioHabilitado(solicitudBean.getFuncionarioHabilitado());
		}
		
		if(solicitudBean.getIdConsentimientoAEAT() != null){
			result.setIdConsentimientoAEAT(solicitudBean.getIdConsentimientoAEAT());
		}
		
		return result;
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
	 * Obtiene el sol comun view DAO.
	 *
	 * @return el sol comun view DAO
	 */
	public SolComunViewDAO getSolComunViewDAO() {
		return solComunViewDAO;
	}

	/**
	 * Establece el sol comun view DAO.
	 *
	 * @param solComunViewDAO el nuevo sol comun view DAO
	 */
	public void setSolComunViewDAO(SolComunViewDAO solComunViewDAO) {
		this.solComunViewDAO = solComunViewDAO;
	}

	/**
	 * Obtiene el estado solicitud manager.
	 *
	 * @return el estado solicitud manager
	 */
	public EstadoSolicitudManager getEstadoSolicitudManager() {
		return estadoSolicitudManager;
	}

	/**
	 * Establece el estado solicitud manager.
	 *
	 * @param estadoSolicitudManager el nuevo estado solicitud manager
	 */
	public void setEstadoSolicitudManager(
			EstadoSolicitudManager estadoSolicitudManager) {
		this.estadoSolicitudManager = estadoSolicitudManager;
	}

	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return el convocatoria manager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager el nuevo convocatoria manager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

	/**
	 * Obtiene el contador num solicitud custom DAO.
	 *
	 * @return el contador num solicitud custom DAO
	 */
	public ContadorNumSolicitudCustomDAO getContadorNumSolicitudCustomDAO() {
		return contadorNumSolicitudCustomDAO;
	}

	/**
	 * Establece el contador num solicitud custom DAO.
	 *
	 * @param contadorNumSolicitudCustomDAO el nuevo contador num solicitud custom DAO
	 */
	public void setContadorNumSolicitudCustomDAO(
			ContadorNumSolicitudCustomDAO contadorNumSolicitudCustomDAO) {
		this.contadorNumSolicitudCustomDAO = contadorNumSolicitudCustomDAO;
	}

	/**
	 * Obtiene el ministerio DAO.
	 *
	 * @return el ministerio DAO
	 */
	public MinisterioDAO getMinisterioDAO() {
		return ministerioDAO;
	}

	/**
	 * Establece el ministerio DAO.
	 *
	 * @param ministerioDAO el nuevo ministerio DAO
	 */
	public void setMinisterioDAO(MinisterioDAO ministerioDAO) {
		this.ministerioDAO = ministerioDAO;
	}

	/**
	 * Obtiene el centro gestor DAO.
	 *
	 * @return el centro gestor DAO
	 */
	public CentroGestorDAO getCentroGestorDAO() {
		return centroGestorDAO;
	}

	/**
	 * Establece el centro gestor DAO.
	 *
	 * @param centroGestorDAO el nuevo centro gestor DAO
	 */
	public void setCentroGestorDAO(CentroGestorDAO centroGestorDAO) {
		this.centroGestorDAO = centroGestorDAO;
	}

	/**
	 * Obtiene el forma acceso DAO.
	 *
	 * @return el forma acceso DAO
	 */
	public FormaAccesoDAO getFormaAccesoDAO() {
		return formaAccesoDAO;
	}

	/**
	 * Establece el forma acceso DAO.
	 *
	 * @param formaAccesoDAO el nuevo forma acceso DAO
	 */
	public void setFormaAccesoDAO(FormaAccesoDAO formaAccesoDAO) {
		this.formaAccesoDAO = formaAccesoDAO;
	}

	/**
	 * Obtiene el grupo DAO.
	 *
	 * @return el grupo DAO
	 */
	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	/**
	 * Establece el grupo DAO.
	 *
	 * @param grupoDAO el nuevo grupo DAO
	 */
	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}

	/**
	 * Obtiene el cuerpo escala DAO.
	 *
	 * @return el cuerpo escala DAO
	 */
	public CuerpoEscalaDAO getCuerpoEscalaDAO() {
		return cuerpoEscalaDAO;
	}

	/**
	 * Establece el cuerpo escala DAO.
	 *
	 * @param cuerpoEscalaDAO el nuevo cuerpo escala DAO
	 */
	public void setCuerpoEscalaDAO(CuerpoEscalaDAO cuerpoEscalaDAO) {
		this.cuerpoEscalaDAO = cuerpoEscalaDAO;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#obtenerNumeroSolicitud(boolean, java.lang.String)
	 */
	public String obtenerNumeroSolicitud(boolean is007, String modelo){
		String nSolicitud;
		NumeroSolicitud numeroSolicitud = new NumeroSolicitud();
		ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
		parametrosConfiguracionQuery.setNombreCampo(Constantes.SOLICITUD_PARAMETROS_CONFIG);
		ParametrosConfiguracion parametrosConfiguracion = parametrosConfiguracionDAO.searchUnique(parametrosConfiguracionQuery);

		if(!is007 && parametrosConfiguracion != null && parametrosConfiguracion.getValorCampo().equals(Constantes.SOLICITUD_VALOR_PARAMETROS_CONFIG)){
			nSolicitud = numeroSolicitud.obtenerNumeroJustificanteEJB();
		}else{
			String idModelo = "790";
			String idTasa = "001";
			Integer contador = contadorNumSolicitudCustomDAO.obtenerNumContador(idTasa, idModelo);
			nSolicitud = numeroSolicitud.obtenerNumeroJustificanteLocal(contador,modelo);
		}

		logger.info("numero de solicitud generado: "+nSolicitud);
		return nSolicitud;
	}

	/**
	 * Obtiene el solicitud propio manager.
	 *
	 * @return el solicitud propio manager
	 */
	public SolicitudPropioManager getSolicitudPropioManager() {
		return solicitudPropioManager;
	}

	/**
	 * Establece el solicitud propio manager.
	 *
	 * @param solicitudPropioManager el nuevo solicitud propio manager
	 */
	public void setSolicitudPropioManager(SolicitudPropioManager solicitudPropioManager) {
		this.solicitudPropioManager = solicitudPropioManager;
	}

	/**
	 * Obtiene el modelo manager.
	 *
	 * @return el modelo manager
	 */
	public ModeloManager getModeloManager() {
		return modeloManager;
	}

	/**
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudManager#existeNumeroSolicitud(java.lang.String)
	 */
	public boolean existeNumeroSolicitud(String nSolicitud) {
		
		boolean existeSolicitud = false, existeSolicitudAuxiliar  =false;
		
		SolicitudComunQuery solicitudQueryValidarJustificante = new SolicitudComunQuery();
		solicitudQueryValidarJustificante.setNumeroSolicitud(nSolicitud);
		
		SearchResult<SolicitudComun> solicitudes = null;
		solicitudes = solicitudComunDAO.search(solicitudQueryValidarJustificante);
		if(solicitudes != null && solicitudes.size() > 0){
			existeSolicitud = true;				
		}
		
		SolicitudComunAuxiliarQuery solicitudAuxiliarQueryValidarJustificante = new SolicitudComunAuxiliarQuery();
		solicitudAuxiliarQueryValidarJustificante.setNumeroSolicitud(nSolicitud);
		
		SearchResult<SolicitudComunAuxiliar> solicitudesAux = null;
		solicitudesAux = solicitudComunAuxiliarDAO.search(solicitudAuxiliarQueryValidarJustificante);
		if(solicitudesAux != null && solicitudesAux.size() > 0){
			existeSolicitudAuxiliar = true;				
		}
		
		return existeSolicitud || existeSolicitudAuxiliar;
		
	}
	
}
