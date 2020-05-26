package es.map.ipsc.manager.solicitudes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.SolicitudComunAuxiliarDAO;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.Especialidad;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.Pais;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudPropiosAuxiliarQuery;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TituloOficial;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.bean.Formulario790Bean;
import es.map.ipsc.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsc.bean.SolicitudPropiosBean;

/**
 * El Class SolicitudComunAuxiliarManagerImpl.
 *
 * @author mromerve
 */
public class SolicitudComunAuxiliarManagerImpl implements SolicitudComunAuxiliarManager {

	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
	
	/** el convocatoria DAO. */
	private ConvocatoriaDAO convocatoriaDAO;
	
	/** el solicitud propio auxiliar manager. */
	private SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager;
	
	/** el solicitud ccaa auxiliar manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudComunAuxiliarManagerImpl.class);

	/**
	 * Guarda una los datos de una solicitud en la tabla de Solicitud Comun Auxiliar.
	 *
	 * @param formulario790Bean el formulario 790 bean
	 * @return idSolicitud Long
	 */
	public Long guardarSolicitudFormulario790(Formulario790Bean formulario790Bean) {
		
		SolicitudComunAuxiliar solicitudComunAuxiliar = toSolicitudComunAuxiliar(formulario790Bean);		
		Long id=solicitudComunAuxiliarDAO.insert(solicitudComunAuxiliar);
		return id;


	}

	
	/**
	 * Método de transformación de Formulario790Bean a SolicitudComunAuxiliar.
	 *
	 * @param formulario790Bean el formulario 790 bean
	 * @return SolicitudComunAuxiliar
	 */
	private SolicitudComunAuxiliar toSolicitudComunAuxiliar(Formulario790Bean formulario790Bean){
		
		SolicitudComunAuxiliar solicitudComunAuxiliar= new SolicitudComunAuxiliar();
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		
		//Número de Justificante
		if(formulario790Bean.getNumeroJustificante()!=null && !"".equals(formulario790Bean.getNumeroJustificante())){
			solicitudComunAuxiliar.setNumeroSolicitud(formulario790Bean.getNumeroJustificante());			
		}
		//Nif
		if(formulario790Bean.getNif()!= " "){
			solicitudComunAuxiliar.setNif(formulario790Bean.getNif());			
		}
		//Primer Apellido
		if(formulario790Bean.getPrimerApellido()!=" "){
			solicitudComunAuxiliar.setPrimerApellido(formulario790Bean.getPrimerApellido());			
		}
		//Segundo Apellido
		if(formulario790Bean.getSegundoApellido()!=" "){
			solicitudComunAuxiliar.setSegundoApellido(formulario790Bean.getSegundoApellido());			
		}
		
		//Nombre
		if(formulario790Bean.getNombre()!=" "){
			solicitudComunAuxiliar.setNombre(formulario790Bean.getNombre());			
		}
		
		//Fecha Nacimiento
		if(formulario790Bean.getFechaNacimiento()!=null){
			try {
				solicitudComunAuxiliar.setFechaNacimiento(formatoFecha.parse(formulario790Bean.getFechaNacimiento()));
			} catch (ParseException e) {
				logger.error("Error parsear fecha nacimiento",e);
			}			
		}
		
		// Sexo
		if(formulario790Bean.getSexoHombre() != " "){
			solicitudComunAuxiliar.setSexo(Constantes.SEXO_HOMBRE);
		}
		if(formulario790Bean.getSexoMujer() != " "){
			solicitudComunAuxiliar.setSexo(Constantes.SEXO_MUJER);
		}
		
		//Nacionalidad
		if(formulario790Bean.getNacionalidad()!=" "){
			solicitudComunAuxiliar.setNacionalidad(formulario790Bean.getNacionalidad());			
		}		
		//Correo
		if(formulario790Bean.getCorreoElectronicos()!=" "){
			solicitudComunAuxiliar.setEmail(formulario790Bean.getCorreoElectronicos());			
		}
		//Telefono	
		if(formulario790Bean.getTelefono()!=" "){
			String telefono;
			if(formulario790Bean.getTelefonoAux() != null && formulario790Bean.getTelefonoAux()!= ""){
				telefono = formulario790Bean.getTelefono() + "/"
						+ formulario790Bean.getTelefonoAux();
			}else{
				telefono = formulario790Bean.getTelefono();
			}
			solicitudComunAuxiliar.setTelefono(telefono);			
		}
		
		//Calle Domicilio
		if(formulario790Bean.getCalleDomicilio()!=" "){
			solicitudComunAuxiliar.setCalleDomicilio(formulario790Bean.getCalleDomicilio());			
		}		
		//Código Postal
		if(formulario790Bean.getCodigoPostalDomicilio()!=" "){
			solicitudComunAuxiliar.setCodigoPostalDomicilio(formulario790Bean.getCodigoPostalDomicilio());			
		}
		
		//Municipio
		if(formulario790Bean.getMunicipioDomicilio()!=" "){
			solicitudComunAuxiliar.setMunicipioDomicilio(formulario790Bean.getMunicipioDomicilio());			
		}
		
		//Provincia Domicilio			
		if(formulario790Bean.getIdProvinciaDomicilio()!=null){
			Provincia provinciaDomicilio = new Provincia();
			provinciaDomicilio.setId(formulario790Bean.getIdProvinciaDomicilio().intValue());
			solicitudComunAuxiliar.setProvincia(provinciaDomicilio);			
		}
		
		//Pais Domicilio				
		if(formulario790Bean.getIdPais()!=null){
			Pais paisDomicilio = new Pais();
			paisDomicilio.setId(formulario790Bean.getIdPais().intValue());
			solicitudComunAuxiliar.setPais(paisDomicilio);			
		}
		
		//Convocatoria				
		if(formulario790Bean.getIdConvocatoria()!=null && formulario790Bean.getIdConvocatoria()!=0){
			Convocatoria convocatoria = new Convocatoria();
			convocatoria.setId(formulario790Bean.getIdConvocatoria());
			solicitudComunAuxiliar.setConvocatoria(convocatoria);			
			
		}
		
		//Especialidad				
		if(formulario790Bean.getIdEspecialidad()!=null && formulario790Bean.getIdEspecialidad()!=0 ){
			Especialidad especialidad = new Especialidad();
			especialidad.setId(formulario790Bean.getIdEspecialidad().intValue());
			solicitudComunAuxiliar.setEspecialidad(especialidad);			
		}
		
		//Tipo Discapacidad	y Reserva Discapacidad		
		if(formulario790Bean.getIdTipoDiscapacidad()!=null && formulario790Bean.getIdTipoDiscapacidad()!=0){
			TipoDiscapacidad tipoDiscapacidad = new TipoDiscapacidad();
			tipoDiscapacidad.setId(formulario790Bean.getIdTipoDiscapacidad().intValue());
			solicitudComunAuxiliar.setTipoDiscapacidad(tipoDiscapacidad);	
			solicitudComunAuxiliar.setReservaDiscapacidad(Constantes.RESERVA_DISCAPACIDAD_SI);	
		}else{
			solicitudComunAuxiliar.setReservaDiscapacidad(Constantes.RESERVA_DISCAPACIDAD_NO);
		}
		
		//Porcentaje Discapacidad
		if(formulario790Bean.getPorcentajeDiscapacidad()!= " "){
			solicitudComunAuxiliar.setPorcentajeDiscapacidad(Short.valueOf(formulario790Bean.getPorcentajeDiscapacidad()));			
		}else{
			solicitudComunAuxiliar.setPorcentajeDiscapacidad(Short.valueOf("0"));
		}
						
		//Detalle Discapacidad
		if(formulario790Bean.getDetalleDiscapacidad()!= " "){
			solicitudComunAuxiliar.setDetalleDiscapacidad(formulario790Bean.getDetalleDiscapacidad());			
		}
		
		//Provincia Examen		
		if(formulario790Bean.getIdProvinciaExamen()!=null && formulario790Bean.getIdProvinciaExamen()!=0){
			ProvinciaExamen provinciaExamen = new ProvinciaExamen();
			provinciaExamen.setId(formulario790Bean.getIdProvinciaExamen().intValue());
			solicitudComunAuxiliar.setProvinciaExamen(provinciaExamen);			
		}
		
		//Título Oficial				
		if(formulario790Bean.getIdTituloOficial()!=null && formulario790Bean.getIdTituloOficial()!=0){
			TituloOficial tituloOficial = new TituloOficial();
			tituloOficial.setId(formulario790Bean.getIdTituloOficial().intValue());
			solicitudComunAuxiliar.setTituloOficial(tituloOficial);			
		}
		
		//Otros Titulos
		if(formulario790Bean.getOtrosTitulos()!=null && !"".equals(formulario790Bean.getOtrosTitulos())){
			solicitudComunAuxiliar.setOtrosTitulos(formulario790Bean.getOtrosTitulos());			
		}
		
		// Importe
		if(formulario790Bean.getImporteSolicitud() != null && !formulario790Bean.getImporteSolicitud().equals("")){
			String importe = null;
			importe = formulario790Bean.getImporteSolicitud();
			if(formulario790Bean.getImporteSolicitudDecimal() != null && !formulario790Bean.getImporteSolicitudDecimal().equals("")){
				importe = importe  + "." + formulario790Bean.getImporteSolicitudDecimal();
				solicitudComunAuxiliar.setImporte(Float.parseFloat(importe));
			}
		}
		
		//Tipo Pago Cuenta
		if(formulario790Bean.getFormaPagoAdeudo()!= " "){
			solicitudComunAuxiliar.setTipoPago(Constantes.FORMA_PAGO_ADEUDO);			
		}
		//Tipo Pago Efectivo
		if(formulario790Bean.getFormaPagoEfectivo()!= " "){
			solicitudComunAuxiliar.setTipoPago(Constantes.FORMA_PAGO_EXENTO);			
		}
		//Tipo Pago Ninguno
		if(formulario790Bean.getFormaPagoNinguno()!= " "){
			solicitudComunAuxiliar.setTipoPago(Constantes.FORMA_PAGO_NINGUNO);			
		}
		
		//Discapacidad
		if(formulario790Bean.getDiscapacidad() != null && formulario790Bean.getDiscapacidad()!= " "){
			MotivoReduccionTasa motivoReduccionTasa = new MotivoReduccionTasa();
			motivoReduccionTasa.setId(Integer.valueOf(Constantes.MOTIVO_DISCAPACIDAD));
			solicitudComunAuxiliar.setMotivoReduccionTasa(motivoReduccionTasa);			
		}
		//Desempleo
		if(formulario790Bean.getDemandanteEmpleo() != null && formulario790Bean.getDemandanteEmpleo()!= " "){
			MotivoReduccionTasa motivoReduccionTasa = new MotivoReduccionTasa();
			motivoReduccionTasa.setId(Integer.valueOf(Constantes.MOTIVO_DESEMPLEO));
			solicitudComunAuxiliar.setMotivoReduccionTasa(motivoReduccionTasa);			
		}
		// Familia Numerosa General
		if(formulario790Bean.getFamiliaNumerosaGeneral() != null && formulario790Bean.getFamiliaNumerosaGeneral() != " "){
			MotivoReduccionTasa motivoReduccionTasa = new MotivoReduccionTasa();
			motivoReduccionTasa.setId(Integer.valueOf(Constantes.MOTIVO_FNUMEROSA));
			solicitudComunAuxiliar.setMotivoReduccionTasa(motivoReduccionTasa);			
		}
		// Familia Numerosa Especial
		if(formulario790Bean.getFamiliaNumerosaEspecial() != null && formulario790Bean.getFamiliaNumerosaEspecial() != " "){
			MotivoReduccionTasa motivoReduccionTasa = new MotivoReduccionTasa();
			motivoReduccionTasa.setId(Integer.valueOf(Constantes.MOTIVO_FNUMEROSAESPECIAL));
			solicitudComunAuxiliar.setMotivoReduccionTasa(motivoReduccionTasa);			
		}
		// Victima Terrorismo
		if(formulario790Bean.getVictimaTerrorismo() != null && formulario790Bean.getVictimaTerrorismo()!= " "){
			MotivoReduccionTasa motivoReduccionTasa = new MotivoReduccionTasa();
			motivoReduccionTasa.setId(Integer.valueOf(Constantes.MOTIVO_VICTIMATERRORISMO));
			solicitudComunAuxiliar.setMotivoReduccionTasa(motivoReduccionTasa);			
		}

		
		//Consentimiento
		if(formulario790Bean.getConsentimiento()== ""){
			solicitudComunAuxiliar.setIdConsentimiento(true);			
		}else{
			solicitudComunAuxiliar.setIdConsentimiento(false);
		}
		
		if(formulario790Bean.getAutorizar()== ""){
			solicitudComunAuxiliar.setIdConsentimientoAEAT(false);			
		}else{
			solicitudComunAuxiliar.setIdConsentimientoAEAT(true);
		}
		
		//Motivo Oposicion
		if(formulario790Bean.getMotivoOposicion()!= " "){
			solicitudComunAuxiliar.setMotivoOposicion(formulario790Bean.getMotivoOposicion());			
		}
		
		//Fecha Solicitud
		solicitudComunAuxiliar.setFechaSolicitud(new Date());
		
		
		return solicitudComunAuxiliar;
		
	}
	
 
	 /* (non-Javadoc)
 	 * @see es.map.ipsc.manager.solicitudes.SolicitudComunAuxiliarManager#detalleSolicitudesBean(es.map.ips.model.SolicitudComunAuxiliarQuery)
 	 */
 	public List<DetalleSolicitudBean> detalleSolicitudesBean(SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery) {
		 SearchResult<SolicitudComunAuxiliar> solicitudesComunesAuxiliares = null;
		 List<DetalleSolicitudBean> detalleSolicitudesComunesAuxiliaresBean = new ArrayList<DetalleSolicitudBean>();	
		
		try {
			// busqueda en la tabla SOLICITUD_COMUN_AUXILIAR
			solicitudesComunesAuxiliares =  solicitudComunAuxiliarDAO.search(solicitudComunAuxiliarQuery);
					
			if (solicitudesComunesAuxiliares!=null && solicitudesComunesAuxiliares.getResults().size()>0) {
				
				// recorro los registros encontrados
				for (int i = 0; i < solicitudesComunesAuxiliares.getResults().size(); i++) {
					// tomo la solicitud comun auxiliar de los resultados de la busqueda
					SolicitudComunAuxiliar solicitudComunAuxiliar;
					solicitudComunAuxiliar = solicitudesComunesAuxiliares.getResults().get(i);
					
					// la transformo a DetalleSolicitudBean
					DetalleSolicitudBean detalleSolicitudBean = toDetalleSolicitudBean(solicitudComunAuxiliar);
					
					// obtengo los campos propios
					SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery = new SolicitudPropiosAuxiliarQuery();
					solicitudPropiosAuxiliarQuery.addSolicitudComunAuxiliarIn(solicitudComunAuxiliar.getIdSolicitudAuxiliar());
					solicitudPropiosAuxiliarQuery.addOrder(SolicitudPropiosAuxiliarQuery.CAMPOSPROPIOS,OrderType.ASC);
					ArrayList<SolicitudPropiosBean> solicitudPropiosAuxiliar = solicitudPropioAuxiliarManager.obtenerSolicitudPropiosByIdSolicitudAuxiliar(solicitudPropiosAuxiliarQuery);
			
					if (solicitudPropiosAuxiliar != null && !solicitudPropiosAuxiliar.isEmpty()) {
						detalleSolicitudBean.setSolicitudPropiosBean(solicitudPropiosAuxiliar);
						
					}
					// añado a la lista de detalles de solicitudes
					detalleSolicitudesComunesAuxiliaresBean.add(detalleSolicitudBean);
				}
				
			}
			
		} catch (HibernateException he){
			logger.error("Error detalle solicitud",he);
		} catch(Exception e) {
			logger.error("Error detalle solicitud",e);
		}
		return detalleSolicitudesComunesAuxiliaresBean;
	 }
	
	 
	 /**
 	 * To detalle solicitud bean.
 	 *
 	 * @param solicitudComunAuxiliar el solicitud comun auxiliar
 	 * @return el detalle solicitud bean
 	 */
	private DetalleSolicitudBean toDetalleSolicitudBean(SolicitudComunAuxiliar solicitudComunAuxiliar) {
			
		//Formato de fechas
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		//Datos de la convocatoria
		String centroGestor = "";
			
		if(solicitudComunAuxiliar.getConvocatoria() != null){
			centroGestor = solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala().getCentroGestor().getDescripcion().toUpperCase();
		}
		String numJustificante = solicitudComunAuxiliar.getNumeroSolicitud();
		String ejercicioSolicitud = solicitudComunAuxiliar.getConvocatoria().getEjercicio();
		String registro = String.valueOf(solicitudComunAuxiliar.getIdSolicitudAuxiliar());
		String nacionalidad = "";
		if(solicitudComunAuxiliar.getNacionalidad() != null){
			nacionalidad = solicitudComunAuxiliar.getNacionalidad().toUpperCase();
		}
		String correoElectronico = "";
		if(solicitudComunAuxiliar.getEmail() != null){
			correoElectronico = solicitudComunAuxiliar.getEmail();
		}
			
		//Datos del usuario
		String nombre = "";
		String primerApellido ="";
		String segundoApellido = "";

		if(solicitudComunAuxiliar.getNombre() != null){
			nombre = solicitudComunAuxiliar.getNombre().toUpperCase();
		}if(solicitudComunAuxiliar.getPrimerApellido() != null){
			primerApellido = solicitudComunAuxiliar.getPrimerApellido().toUpperCase();
		}if(solicitudComunAuxiliar.getSegundoApellido() != null){
			segundoApellido = solicitudComunAuxiliar.getSegundoApellido().toUpperCase();
		}

		String fechaNacimiento = "";
		if(solicitudComunAuxiliar.getFechaNacimiento() != null){
			fechaNacimiento = formatoFecha.format(solicitudComunAuxiliar.getFechaNacimiento());
		}
		String nif = solicitudComunAuxiliar.getNif();
		char sexo = ' ';
		if(solicitudComunAuxiliar.getSexo() != null && !"".equals(solicitudComunAuxiliar.getSexo())){
			sexo = solicitudComunAuxiliar.getSexo();
		}
			
		//Datos del domicilio
		String calleDomicilio = "";
		if(solicitudComunAuxiliar.getCalleDomicilio() != null && !"".equals(solicitudComunAuxiliar.getCalleDomicilio())){
			calleDomicilio= solicitudComunAuxiliar.getCalleDomicilio().toUpperCase();
		}
		String codPostalDomicilio = "";
		if(solicitudComunAuxiliar.getCodigoPostalDomicilio() != null && !"".equals(solicitudComunAuxiliar.getCodigoPostalDomicilio())){
			codPostalDomicilio= solicitudComunAuxiliar.getCodigoPostalDomicilio();
		}

		String municipioDomicilio = "";
		if(solicitudComunAuxiliar.getMunicipioDomicilio() != null && !"".equals(solicitudComunAuxiliar.getMunicipioDomicilio())){
			municipioDomicilio= solicitudComunAuxiliar.getMunicipioDomicilio().toUpperCase();
		}

		String provinciaDomicilio = "";
		if(solicitudComunAuxiliar.getProvincia() != null && !"".equals(solicitudComunAuxiliar.getProvincia())){
			provinciaDomicilio = solicitudComunAuxiliar.getProvincia().getDescripcion().toUpperCase();
		}

		String nacionDomicilio = "";
		if(solicitudComunAuxiliar.getPais() != null && !"".equals(solicitudComunAuxiliar.getPais().getDescripcion())){
			nacionDomicilio= solicitudComunAuxiliar.getPais().getDescripcion().toUpperCase();
		}

		String telefono = "";
		String telefono1 = "";
		String telefonoAux = "";
		if(solicitudComunAuxiliar.getTelefono() != null && !"".equals(solicitudComunAuxiliar.getTelefono())){
			telefono= solicitudComunAuxiliar.getTelefono();
			telefono1 = telefono.substring(0, 9);
			if(telefono.length()>10)
				telefonoAux = telefono.substring(10);
		}
		//Datos de la convocatoria
		String cuerpo= "";
		if(solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala() != null && !"".equals(solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala())){
			cuerpo= solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala().getDescripcion().toUpperCase();
		}
		String grupo = "";
		if(solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala().getGrupo() != null){
			grupo = String.valueOf(solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala().getGrupo().getDescripcion().toUpperCase());
		}
		String especialidad = "";
		if(solicitudComunAuxiliar.getEspecialidad() != null && !"".equals(solicitudComunAuxiliar.getEspecialidad())){
			especialidad = solicitudComunAuxiliar.getEspecialidad().getDescripcion().toUpperCase();
		}
		String formaAcceso = "";
		if(solicitudComunAuxiliar.getConvocatoria().getFormaAcceso() != null && !"".equals(solicitudComunAuxiliar.getConvocatoria().getFormaAcceso())){
			formaAcceso = solicitudComunAuxiliar.getConvocatoria().getFormaAcceso().getDescripcion().toUpperCase();
		}
			
		String ministerio = "";
		if(solicitudComunAuxiliar.getConvocatoria().getMinisterioConvocante() != null 
				&& !"".equals(solicitudComunAuxiliar.getConvocatoria().getMinisterioConvocante())){
			ministerio = solicitudComunAuxiliar.getConvocatoria().getMinisterioConvocante().getDescripcion().toUpperCase();
		}else{
			if(solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio() != null 
					&& !"".equals(solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio())){
				ministerio = solicitudComunAuxiliar.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion().toUpperCase();
			}
		}
					
		String fechaBOE = "";
		if(solicitudComunAuxiliar.getConvocatoria().getFechaBoe() != null && !"".equals(solicitudComunAuxiliar.getConvocatoria().getFechaBoe())){
			fechaBOE = formatoFecha.format(solicitudComunAuxiliar.getConvocatoria().getFechaBoe());
		}
		String provinciaExamen = "";
		if(solicitudComunAuxiliar.getProvinciaExamen() != null && !"".equals(solicitudComunAuxiliar.getProvinciaExamen())){
			provinciaExamen = solicitudComunAuxiliar.getProvinciaExamen().getDescripcion().toUpperCase();
		}
		String discapacidad = "";
		if(solicitudComunAuxiliar.getTipoDiscapacidad() != null && !"".equals(solicitudComunAuxiliar.getTipoDiscapacidad())){
			discapacidad = solicitudComunAuxiliar.getTipoDiscapacidad().getDescripcion().toUpperCase();
		}
		char reservaDiscapacitados = ' ';
		if(solicitudComunAuxiliar.getReservaDiscapacidad() != null && !"".equals(solicitudComunAuxiliar.getReservaDiscapacidad())){
			reservaDiscapacitados = solicitudComunAuxiliar.getReservaDiscapacidad();
		}
		String porcentajeDiscapacidad = "";
			if(solicitudComunAuxiliar.getPorcentajeDiscapacidad() != null){
				porcentajeDiscapacidad = String.valueOf(solicitudComunAuxiliar.getPorcentajeDiscapacidad());
		}
		String auxAdaptacion = "0";
		if(solicitudComunAuxiliar.getPorcentajeDiscapacidad() != null){
			auxAdaptacion = String.valueOf(solicitudComunAuxiliar.getPorcentajeDiscapacidad());
		}
			
		Float importe = 0f;
		if(solicitudComunAuxiliar.getConvocatoria().getImporte() != null){
			importe = solicitudComunAuxiliar.getConvocatoria().getImporte();
		}
		String dirigidoA = "";
		if(solicitudComunAuxiliar.getConvocatoria().getDirigidoA() != null){
			dirigidoA = solicitudComunAuxiliar.getConvocatoria().getDirigidoA().toUpperCase();
		}

		Long adaptacionDiscapacidad = 0L;
		if(auxAdaptacion != null){
			adaptacionDiscapacidad = Long.parseLong(auxAdaptacion);
		}

		String motivoDiscapacidad = "";
		if(solicitudComunAuxiliar.getDetalleDiscapacidad() != null && !"".equals(solicitudComunAuxiliar.getDetalleDiscapacidad())){
			motivoDiscapacidad= solicitudComunAuxiliar.getDetalleDiscapacidad().toUpperCase();
		}

		//Titulos academicos oficiales
		String titulos = "";
		if(solicitudComunAuxiliar.getTituloOficial() != null){
			titulos= solicitudComunAuxiliar.getTituloOficial().getDescripcion().toUpperCase();
		}
			
		String otrosTitulos = "";
		if(solicitudComunAuxiliar.getOtrosTitulos() != null){
			otrosTitulos= solicitudComunAuxiliar.getOtrosTitulos().toUpperCase();
		}
		Integer idMotivoReduccionTasa = 0;
		if(solicitudComunAuxiliar.getMotivoReduccionTasa() != null){
			idMotivoReduccionTasa = solicitudComunAuxiliar.getMotivoReduccionTasa().getId();
		}
		
		
		// busqueda comunidad para la reduccion de tasa
		// se busca en la tabla SOLICITUD_CC.AA_AUXILIAR sus datos y se relaciona con la tipologia de exencion
		// de esta manera podemos precargar correctamente
		
		SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery = new SolicitudCcaaAuxiliarQuery();
		SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
		solicitudComunAuxiliarQuery.setIdSolicitudAuxiliar(solicitudComunAuxiliar.getIdSolicitudAuxiliar());
		solicitudCcaaAuxiliarQuery.setSolicitudComunAuxiliar(solicitudComunAuxiliarQuery);
		
		Integer idComunidadDD = 0;
		Integer idComunidadFN = 0;
		String tituloFamiliaNumerosa = "";
		SolicitudCcaaAuxiliarBean solicitudCccaaAuxiliarBean = solicitudCcaaAuxiliarManager.obtenerSolicitudCcaaAuxiliar(solicitudCcaaAuxiliarQuery);
		if (solicitudCccaaAuxiliarBean!=null) {
			if (idMotivoReduccionTasa == Integer.parseInt(Constantes.MOTIVO_DISCAPACIDAD)) {
				if (solicitudCccaaAuxiliarBean.getComunidad()!= null && solicitudCccaaAuxiliarBean.getComunidad().getIdComunidad()!=null) {
					idComunidadDD = solicitudCccaaAuxiliarBean.getComunidad().getIdComunidad();
				}
			} else if (idMotivoReduccionTasa == Integer.parseInt(Constantes.MOTIVO_FNUMEROSA) 
					|| idMotivoReduccionTasa == Integer.parseInt(Constantes.MOTIVO_FNUMEROSAESPECIAL))  {
				if (solicitudCccaaAuxiliarBean.getComunidad() != null && solicitudCccaaAuxiliarBean.getComunidad().getIdComunidad()!=null) {
					idComunidadFN = solicitudCccaaAuxiliarBean.getComunidad().getIdComunidad();
				}
				if (solicitudCccaaAuxiliarBean.getTituloFamnumerosa()!=null && !solicitudCccaaAuxiliarBean.getTituloFamnumerosa().equals(" ")) {
					tituloFamiliaNumerosa = solicitudCccaaAuxiliarBean.getTituloFamnumerosa();
				}
			}
		}
		

		//CONVERTIR EL MODEL AL BEAN
		DetalleSolicitudBean solicitudComunAuxiliarBean = new DetalleSolicitudBean();

		//Datos de la convocatoria
		solicitudComunAuxiliarBean.setCentroGestor(centroGestor);
		solicitudComunAuxiliarBean.setNumJustificante(numJustificante);
		solicitudComunAuxiliarBean.setEjercicioSolicitud(ejercicioSolicitud);
		solicitudComunAuxiliarBean.setRegistro(registro);
		solicitudComunAuxiliarBean.setNacionalidad(nacionalidad);
		solicitudComunAuxiliarBean.setCorreoElectronico(correoElectronico);
		solicitudComunAuxiliarBean.setDirigidoA(dirigidoA);
		//Datos del usuario
		solicitudComunAuxiliarBean.setNombre(nombre);
		solicitudComunAuxiliarBean.setPrimerApellido(primerApellido);
		solicitudComunAuxiliarBean.setSegundoApellido(segundoApellido);
		solicitudComunAuxiliarBean.setFechaNacimiento(fechaNacimiento);
		String nombreCompleto = nombre + " " + primerApellido +  " " + segundoApellido;
		solicitudComunAuxiliarBean.setNombreCompleto(nombreCompleto);
		solicitudComunAuxiliarBean.setNif(nif);
		solicitudComunAuxiliarBean.setSexo(sexo);
			
		if(solicitudComunAuxiliar.getIdConsentimiento()!= null)
		{
			if(solicitudComunAuxiliar.getIdConsentimiento() == true)
			{
				solicitudComunAuxiliarBean.setConsentimiento(Constantes.NO);
			}else if(solicitudComunAuxiliar.getIdConsentimiento() == false){
				solicitudComunAuxiliarBean.setConsentimiento(Constantes.SI);
			}
		}else{
			solicitudComunAuxiliarBean.setConsentimiento("");
		}	

		String motivoOposicion = "";
		if(solicitudComunAuxiliar.getMotivoOposicion() != null){
			motivoOposicion = solicitudComunAuxiliar.getMotivoOposicion().toUpperCase();
		}
		solicitudComunAuxiliarBean.setMotivoOposicion(motivoOposicion);	
		
		//Datos del domicilio
		solicitudComunAuxiliarBean.setCalleDomicilio(calleDomicilio);
		solicitudComunAuxiliarBean.setCodPostalDomicilio(codPostalDomicilio);
		solicitudComunAuxiliarBean.setMunicipioDomicilio(municipioDomicilio);
		solicitudComunAuxiliarBean.setProvinciaDomicilio(provinciaDomicilio);
		solicitudComunAuxiliarBean.setNacionDomicilio(nacionDomicilio);
		solicitudComunAuxiliarBean.setTelefono(telefono);
		solicitudComunAuxiliarBean.setTelefono1(telefono1);
		solicitudComunAuxiliarBean.setTelefonoAux(telefonoAux);
		//Datos de la convocatoria
		solicitudComunAuxiliarBean.setCuerpo(cuerpo);
		solicitudComunAuxiliarBean.setGrupo(grupo);
		solicitudComunAuxiliarBean.setEspecialidad(especialidad);
		solicitudComunAuxiliarBean.setFormaAcceso(formaAcceso);
		solicitudComunAuxiliarBean.setMinisterio(ministerio);
		solicitudComunAuxiliarBean.setFechaBOE(fechaBOE);
		solicitudComunAuxiliarBean.setProvinciaExamen(provinciaExamen);
		solicitudComunAuxiliarBean.setPorcentajeDiscapacidad(porcentajeDiscapacidad);
		solicitudComunAuxiliarBean.setDiscapacidad(discapacidad);		
		solicitudComunAuxiliarBean.setReservaDiscapacitados(reservaDiscapacitados);
		solicitudComunAuxiliarBean.setAdaptacionDiscapacidad(adaptacionDiscapacidad);
		solicitudComunAuxiliarBean.setMotivoDiscapacidad(motivoDiscapacidad);
		solicitudComunAuxiliarBean.setIdMotivoReduccionTasa(idMotivoReduccionTasa);
		solicitudComunAuxiliarBean.setImporte(importe);
		//Titulos academicos oficiales
		solicitudComunAuxiliarBean.setTitulos(titulos);
		solicitudComunAuxiliarBean.setOtrosTitulos(otrosTitulos);
		// datos comunidad de exencion
		solicitudComunAuxiliarBean.setIdComunidadDD(idComunidadDD);
		solicitudComunAuxiliarBean.setIdComunidadFN(idComunidadFN);
		solicitudComunAuxiliarBean.setTituloFamiliaNumerosa(tituloFamiliaNumerosa);
		
		return solicitudComunAuxiliarBean;
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
	public void setSolicitudComunAuxiliarDAO(
			SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO) {
		this.solicitudComunAuxiliarDAO = solicitudComunAuxiliarDAO;
	}

	
	/**
	 * Obtiene el convocatoria DAO.
	 *
	 * @return el convocatoria DAO
	 */
	public ConvocatoriaDAO getConvocatoriaDAO() {
		return convocatoriaDAO;
	}


	/**
	 * Establece el convocatoria DAO.
	 *
	 * @param convocatoriaDAO el nuevo convocatoria DAO
	 */
	public void setConvocatoriaDAO(ConvocatoriaDAO convocatoriaDAO) {
		this.convocatoriaDAO = convocatoriaDAO;
	}


	/**
	 * Obtiene el solicitud propio auxiliar manager.
	 *
	 * @return el solicitud propio auxiliar manager
	 */
	public SolicitudPropioAuxiliarManager getSolicitudPropioAuxiliarManager() {
		return solicitudPropioAuxiliarManager;
	}


	/**
	 * Establece el solicitud propio auxiliar manager.
	 *
	 * @param solicitudPropioAuxiliarManager el nuevo solicitud propio auxiliar manager
	 */
	public void setSolicitudPropioAuxiliarManager(SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager) {
		this.solicitudPropioAuxiliarManager = solicitudPropioAuxiliarManager;
	}


	/**
	 * Obtiene el solicitud ccaa auxiliar manager.
	 *
	 * @return el solicitud ccaa auxiliar manager
	 */
	public SolicitudCcaaAuxiliarManager getSolicitudCcaaAuxiliarManager() {
		return solicitudCcaaAuxiliarManager;
	}


	/**
	 * Establece el solicitud ccaa auxiliar manager.
	 *
	 * @param solicitudCcaaAuxiliarManager el nuevo solicitud ccaa auxiliar manager
	 */
	public void setSolicitudCcaaAuxiliarManager(SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager) {
		this.solicitudCcaaAuxiliarManager = solicitudCcaaAuxiliarManager;
	}


	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudComunAuxiliarManager#buscarSolicitudBean(es.map.ips.model.SolicitudComunAuxiliarQuery)
	 */
	@Override
	public DetalleSolicitudBean buscarSolicitudBean(SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery) {
		SearchResult<SolicitudComunAuxiliar> solicitudesComunesAuxiliares = null;
		DetalleSolicitudBean detalleSolicitudBean = null;
		// busqueda en la tabla SOLICITUD_COMUN_AUXILIAR
		solicitudesComunesAuxiliares =  solicitudComunAuxiliarDAO.search(solicitudComunAuxiliarQuery);
		
		if (solicitudesComunesAuxiliares != null && solicitudesComunesAuxiliares.size() > 0) {
			// la transformo a DetalleSolicitudBean
			detalleSolicitudBean = toDetalleSolicitudBean(solicitudesComunesAuxiliares.getResults().get(0));
		}
		

		return detalleSolicitudBean;
	}

}
