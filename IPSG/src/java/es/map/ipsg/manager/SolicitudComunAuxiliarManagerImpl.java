package es.map.ipsg.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolComunAuxiliarViewDAO;
import es.map.ips.dao.SolicitudComunAuxiliarDAO;
import es.map.ips.model.Modelo;
import es.map.ips.model.SolComunAuxiliarView;
import es.map.ips.model.SolComunAuxiliarViewQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.TipoPago;
import es.map.ips.model.TipoPagoQuery;
import es.map.ipsg.action.solicitud.ExportarExcelSolicitudesSpring;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudComunAuxiliarBean;
import es.map.ipsg.util.UtilesIPSG;

/**
 * El Class SolicitudComunAuxiliarManagerImpl.
 *
 * @author sbriones
 */
public class SolicitudComunAuxiliarManagerImpl implements SolicitudComunAuxiliarManager{

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudComunAuxiliarManagerImpl.class);

	/** el solicitud comun auxiliar DAO. */
	private SolicitudComunAuxiliarDAO solicitudComunAuxiliarDAO;
	
	/** el sol comun auxiliar view DAO. */
	private SolComunAuxiliarViewDAO solComunAuxiliarViewDAO;
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** La constante STRING_FECHASTRING. */
	private static final String STRING_FECHASTRING = "FechaString: ";
	
	/** La constante STRING_ERRORPARSEARFECHASOLICITUD. */
	private static final String STRING_ERRORPARSEARFECHASOLICITUD = "No se ha podido parsear la fecha de solicitud";
	
	/**
	 * 	Obtiene la solicitud de la tabla Solicitud Comun Auxiliar según los parámetros de la query.
	 *
	 * @param solicitudComunAuxiliarQuery el solicitud comun auxiliar query
	 * @return SolicitudComunAuxiliarBean
	 */
	public SolicitudComunAuxiliarBean buscarSolicitudComunAuxiliarById(
			SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery) {
		
		SolicitudComunAuxiliar solicitudComunAuxiliar = solicitudComunAuxiliarDAO.searchUnique(solicitudComunAuxiliarQuery);
		if(solicitudComunAuxiliar != null){
			return tosolicitudComunAuxiliarBean(solicitudComunAuxiliar);
		}
		return null;

	}
	
	/**
	 * Obtiene el ID y la descripción de un  de Solicitud pasándole el ID.
	 * @param idSolicitud  Integer El ID del título que se desea obtener
	 * @return SolicitudBean Solicitud
	 */
	public SolicitudBean obtenerSolicitud (Long idSolicitud) {
		SolicitudComunAuxiliar Solicitud = solicitudComunAuxiliarDAO.get(idSolicitud);
		SolicitudBean SolicitudBean = this.toSolicitudBean(Solicitud,0);
		
		return SolicitudBean;
	}
	
	/**
	 * Obtiene el ID y la descripción de un  de Solicitud pasándole el ID.
	 * @param idSolicitud  Integer El ID del título que se desea obtener
	 * @return SolicitudBean Solicitud
	 */
	public SolicitudBean obtenerSolicitudQuery (SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery) {
		SolicitudComunAuxiliar Solicitud = solicitudComunAuxiliarDAO.searchUnique(solicitudComunAuxiliarQuery);
		SolicitudBean SolicitudBean = this.toSolicitudBean(Solicitud,0);
		
		return SolicitudBean;
	}
	
	/**
	 * Pasa de Solicitud a SolicitudBean.
	 *
	 * @param solicitud el solicitud
	 * @param numTotal int
	 * @return auxSolicitud SolicitudBean
	 */
	private SolicitudBean toSolicitudBean(SolicitudComunAuxiliar solicitud, int numTotal) {
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		UtilesIPSG utilesIPSG = new UtilesIPSG();

		SolicitudBean auxSolicitud = new SolicitudBean();
		if(solicitud.getIdSolicitudAuxiliar() != null){
			auxSolicitud.setId(solicitud.getIdSolicitudAuxiliar());
		}
		
		auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		auxSolicitud.setNif(solicitud.getNif());
		auxSolicitud.setNombre(solicitud.getNombre());
		auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		auxSolicitud.setFechaNacimiento(solicitud.getFechaNacimiento());
		
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}	
		if(solicitud.getDesempleoVerificado() != null){
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		if(solicitud.getFnumerosaVerificado() != null){
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		
		if(solicitud.getDiscapacidadVerificado() != null){
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado() );
		}
		
		auxSolicitud.setId(solicitud.getIdSolicitudAuxiliar());
		
		if (solicitud.getFechaSolicitud() != null) {
			try{
				logger.info(STRING_FECHASTRING+ sdf.format(solicitud.getFechaSolicitud()));
				auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARFECHASOLICITUD+ solicitud.getFechaSolicitud() ,e);			
			}
		}
		
		auxSolicitud.setNumTotal(numTotal);
		
		auxSolicitud.setEmail(solicitud.getEmail());
		auxSolicitud.setTelefono(solicitud.getTelefono());
		
		//Exportar Excel
		if(solicitud.getEspecialidad() != null){
			auxSolicitud.setEspecialidad(solicitud.getEspecialidad());
			
			if(solicitud.getEspecialidad().getDescripcion() != null){
				auxSolicitud.setDescripcionEspecialidad(solicitud.getEspecialidad().getDescripcion());
			}
		}
		
		auxSolicitud.setSexo(solicitud.getSexo());

		auxSolicitud.setNacionalidad(solicitud.getNacionalidad());
		auxSolicitud.setCalleDomicilio(solicitud.getCalleDomicilio());
		auxSolicitud.setCodigoPostalDomicilio(solicitud.getCodigoPostalDomicilio());
		auxSolicitud.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		
		if(solicitud.getPais() != null){
			auxSolicitud.setPais(solicitud.getPais());
		}
		
		
		if(solicitud.getTipoDiscapacidad() != null){
			auxSolicitud.setTipoDiscapacidad(solicitud.getTipoDiscapacidad());
			
			if(solicitud.getTipoDiscapacidad().getDescripcion() != null){
				auxSolicitud.setDescripcionTipoDiscapacidad(solicitud.getTipoDiscapacidad().getDescripcion());
			}
		}
		
		auxSolicitud.setPorcentajeDiscapacidad((int)solicitud.getPorcentajeDiscapacidad());
		auxSolicitud.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
		auxSolicitud.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		
		if(solicitud.getTituloOficial() != null){
			auxSolicitud.setTituloOficial(solicitud.getTituloOficial());
			if(solicitud.getTituloOficial().getDescripcion() != null){	
				auxSolicitud.setDescripcionTituloOficial(solicitud.getTituloOficial().getDescripcion());
			}
		}
		
		auxSolicitud.setOtrosTitulos(solicitud.getOtrosTitulos());
		auxSolicitud.setIdConsentimiento(solicitud.getIdConsentimiento());
	
		if(solicitud.getConvocatoria().getId() != null) {
			auxSolicitud.setIdConvocatoria(solicitud.getConvocatoria().getId());
			auxSolicitud.setConvocatoria(solicitud.getConvocatoria());
		}
		
		Date fechaBoe = solicitud.getConvocatoria().getFechaBoe();
		if(null != fechaBoe){
		auxSolicitud.setFechaBoe(utilesIPSG.dateToString(fechaBoe));
		}
		
		//Motivo Reducción/Exención Tasa
		
		if(solicitud.getMotivoReduccionTasa()!=null && solicitud.getMotivoReduccionTasa().getId() != null){
			auxSolicitud.setMotivoReduccion(solicitud.getMotivoReduccionTasa().getId());			
		}
		
		return auxSolicitud;
	}

	/**
	 * Pasa de SolicitudComunAuxiliar a SolicitudComunAuxiliarBean.
	 *
	 * @param solicitudComunAuxiliar el solicitud comun auxiliar
	 * @return SolicitudComunAuxiliarBean tosolicitudComunAuxiliarBean
	 */
	
	private SolicitudComunAuxiliarBean tosolicitudComunAuxiliarBean(
			SolicitudComunAuxiliar solicitudComunAuxiliar) {
		
		SolicitudComunAuxiliarBean solicitudComunAuxiliarBean= new SolicitudComunAuxiliarBean();
		
		//Número Solicitud
		if(solicitudComunAuxiliar.getIdSolicitudAuxiliar()!=null){
			solicitudComunAuxiliarBean.setIdSolicitud(solicitudComunAuxiliar.getIdSolicitudAuxiliar());			
		}
		//Número de Justificante
		if(solicitudComunAuxiliar.getNumeroSolicitud()!=null && !"".equals(solicitudComunAuxiliar.getNumeroSolicitud())){
			solicitudComunAuxiliarBean.setNumeroSolicitud(solicitudComunAuxiliar.getNumeroSolicitud());			
		}
		//Nif
		if(solicitudComunAuxiliar.getNif()!=null){
			solicitudComunAuxiliarBean.setNif(solicitudComunAuxiliar.getNif());			
		}
		//Primer Apellido
		if(solicitudComunAuxiliar.getPrimerApellido()!=null){
			solicitudComunAuxiliarBean.setPrimerApellido(solicitudComunAuxiliar.getPrimerApellido());			
		}
		//Segundo Apellido
		if(solicitudComunAuxiliar.getSegundoApellido()!=null){
			solicitudComunAuxiliarBean.setSegundoApellido(solicitudComunAuxiliar.getSegundoApellido());			
		}
		
		//Nombre
		if(solicitudComunAuxiliar.getNombre()!=null){
			solicitudComunAuxiliarBean.setNombre(solicitudComunAuxiliar.getNombre());			
		}
		
		//Fecha Nacimiento
		if(solicitudComunAuxiliar.getFechaNacimiento()!=null){
			solicitudComunAuxiliarBean.setFechaNacimiento(solicitudComunAuxiliar.getFechaNacimiento());
			
		}		
		// Sexo
		if(solicitudComunAuxiliar.getSexo() != null){
			solicitudComunAuxiliarBean.setSexo(solicitudComunAuxiliar.getSexo());
		}
		
		//Nacionalidad
		if(solicitudComunAuxiliar.getNacionalidad()!=null){
			solicitudComunAuxiliarBean.setNacionalidad(solicitudComunAuxiliar.getNacionalidad());			
		}		
		//Correo
		if(solicitudComunAuxiliar.getEmail()!=null){
			solicitudComunAuxiliarBean.setEmail(solicitudComunAuxiliar.getEmail());			
		}
		//Telefono	
		if(solicitudComunAuxiliar.getTelefono()!=null){
			if(solicitudComunAuxiliar.getTelefono().contains("/")){
				solicitudComunAuxiliarBean.setTelefono(solicitudComunAuxiliar.getTelefono().substring(0,solicitudComunAuxiliar.getTelefono().lastIndexOf("/")));
				solicitudComunAuxiliarBean.setTelefonoAux(solicitudComunAuxiliar.getTelefono().substring(solicitudComunAuxiliar.getTelefono().lastIndexOf("/")+1));

			}else{
				solicitudComunAuxiliarBean.setTelefono(solicitudComunAuxiliar.getTelefono());
				solicitudComunAuxiliarBean.setTelefonoAux(null);
			}
						
		}
		
		if(solicitudComunAuxiliar.getIdConsentimientoAEAT() != null) {
			solicitudComunAuxiliarBean.setIdConsentimientoAEAT(solicitudComunAuxiliar.getIdConsentimientoAEAT());
		}
		
		if(solicitudComunAuxiliar.getMotivoOposicion() != null) {
			solicitudComunAuxiliarBean.setMotivoOposicion(solicitudComunAuxiliar.getMotivoOposicion());
		}
		
		toSolicitudComunAuxiliarBean2(solicitudComunAuxiliar, solicitudComunAuxiliarBean);
		
		toSolicitudComunAuxiliarBean3(solicitudComunAuxiliar, solicitudComunAuxiliarBean);
		
		return solicitudComunAuxiliarBean;		
			
	}
	
	/**
	 * To solicitud comun auxiliar bean 3.
	 *
	 * @param solicitudComunAuxiliar el solicitud comun auxiliar
	 * @param solicitudComunAuxiliarBean el solicitud comun auxiliar bean
	 */
	private void toSolicitudComunAuxiliarBean3(SolicitudComunAuxiliar solicitudComunAuxiliar, SolicitudComunAuxiliarBean solicitudComunAuxiliarBean) {
		//Tipo Pago
		if(solicitudComunAuxiliar.getTipoPago()!= null){			
			TipoPago tipoPago;
			TipoPagoQuery tipoPagoQuery = new TipoPagoQuery();
			tipoPagoQuery.setCodigo(solicitudComunAuxiliar.getTipoPago());
			tipoPago = tipoPagoManager.buscarTipoPagoByTipo(tipoPagoQuery);
			solicitudComunAuxiliarBean.setIdTipoPago(tipoPago.getId().toString());			
		}
		//Motivo Reducción/Exención Tasa
		
		if(solicitudComunAuxiliar.getMotivoReduccionTasa()!=null){
			solicitudComunAuxiliarBean.setMotivoReduccionTasa(solicitudComunAuxiliar.getMotivoReduccionTasa());			
		}
		
		//Consentimiento
		if(solicitudComunAuxiliar.getIdConsentimiento()!=null){
			solicitudComunAuxiliarBean.setIdConsentimiento(solicitudComunAuxiliar.getIdConsentimiento());			
		}
		
		//Fecha Solicitud
		solicitudComunAuxiliarBean.setFechaSolicitud(solicitudComunAuxiliar.getFechaSolicitud());
	}
	
	/**
	 * To solicitud comun auxiliar bean 2.
	 *
	 * @param solicitudComunAuxiliar el solicitud comun auxiliar
	 * @param solicitudComunAuxiliarBean el solicitud comun auxiliar bean
	 */
	private void toSolicitudComunAuxiliarBean2(SolicitudComunAuxiliar solicitudComunAuxiliar, SolicitudComunAuxiliarBean solicitudComunAuxiliarBean) {
		
		//Calle Domicilio
		if(solicitudComunAuxiliar.getCalleDomicilio()!=null){
			solicitudComunAuxiliarBean.setCalleDomicilio(solicitudComunAuxiliar.getCalleDomicilio());			
		}		
		//Código Postal
		if(solicitudComunAuxiliar.getCodigoPostalDomicilio()!=null){
			solicitudComunAuxiliarBean.setCodigoPostalDomicilio(solicitudComunAuxiliar.getCodigoPostalDomicilio());			
		}
		
		//Municipio
		if(solicitudComunAuxiliar.getMunicipioDomicilio()!=null){
			solicitudComunAuxiliarBean.setMunicipioDomicilio(solicitudComunAuxiliar.getMunicipioDomicilio());			
		}
		
		//Provincia Domicilio			
		if(solicitudComunAuxiliar.getProvincia()!=null){
			solicitudComunAuxiliarBean.setProvincia(solicitudComunAuxiliar.getProvincia());			
		}
		
		//Pais Domicilio				
		if(solicitudComunAuxiliar.getPais()!=null){
			solicitudComunAuxiliarBean.setPais(solicitudComunAuxiliar.getPais());			
		}
		
		//Convocatoria				
		if(solicitudComunAuxiliar.getConvocatoria()!=null){
			solicitudComunAuxiliarBean.setConvocatoria(solicitudComunAuxiliar.getConvocatoria());			
		}
		
		//Especialidad				
		if(solicitudComunAuxiliar.getEspecialidad()!=null){
			solicitudComunAuxiliarBean.setEspecialidad(solicitudComunAuxiliar.getEspecialidad());			
		}
		
		//Tipo Discapacidad				
		if(solicitudComunAuxiliar.getTipoDiscapacidad()!=null){
			solicitudComunAuxiliarBean.setTipoDiscapacidad(solicitudComunAuxiliar.getTipoDiscapacidad());			
		}
		
		//Porcentaje Discapacidad
		solicitudComunAuxiliarBean.setPorcentajeDiscapacidad(solicitudComunAuxiliar.getPorcentajeDiscapacidad());			

		
		//Reserva Discapacidad
		solicitudComunAuxiliarBean.setReservaDiscapacidad(solicitudComunAuxiliar.getReservaDiscapacidad());			

		//Detalle Discapacidad
		if(solicitudComunAuxiliar.getDetalleDiscapacidad()!= null){
			solicitudComunAuxiliarBean.setDetalleDiscapacidad(solicitudComunAuxiliar.getDetalleDiscapacidad());			
		}
		
		//Provincia Examen		
		if(solicitudComunAuxiliar.getProvinciaExamen()!=null){
			solicitudComunAuxiliarBean.setProvinciaExamen(solicitudComunAuxiliar.getProvinciaExamen());			
		}
		
		//Título Oficial				
		if(solicitudComunAuxiliar.getTituloOficial()!=null){
			solicitudComunAuxiliarBean.setTituloOficial(solicitudComunAuxiliar.getTituloOficial());			
		}
		
		//Otros Titulos
		if(solicitudComunAuxiliar.getOtrosTitulos()!=null){
			solicitudComunAuxiliarBean.setOtrosTitulos(solicitudComunAuxiliar.getOtrosTitulos());			
		}
		
		// Importe
		if(solicitudComunAuxiliar.getImporte()!=null){
			solicitudComunAuxiliarBean.setImporte(solicitudComunAuxiliar.getImporte().toString());
			
		}		
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudComunAuxiliarManager#buscarSolicitudesAuxiliarVista(es.map.ips.model.SolComunAuxiliarViewQuery)
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesAuxiliarVista (SolComunAuxiliarViewQuery solicitudQuery){		
		
		if(solicitudQuery != null){	
			SearchResult<SolComunAuxiliarView> solicitud = solComunAuxiliarViewDAO.search(solicitudQuery);
			
			int numTotal = 0;
			if(solicitud.getNumResults() != null){
				numTotal = solicitud.getNumResults();
			}
			ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
			for(int i = 0; i < solicitud.getResults().size(); i++){
				SolicitudBean aux;
				aux = SolicitudAuxiliarVistatoSolicitudBean (solicitud.getResults().get(i),numTotal);
				if(aux != null){
					arrSolicitud.add(aux);
				}
			}	
			return arrSolicitud;		
		}else{
			return new ArrayList<SolicitudBean>();
		}	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudComunAuxiliarManager#buscarSolicitudComunAuxiliarByFechaSolicitud(es.map.ips.model.SolicitudComunAuxiliarQuery)
	 */
	@Override
	public ArrayList<Long> buscarSolicitudComunAuxiliarByFechaSolicitud(
			SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery) {
		
		ArrayList<Long> idSolicitudes=new ArrayList<Long>();
		
		SearchResult<SolicitudComunAuxiliar> solicitudAuxiliar= solicitudComunAuxiliarDAO.search(solicitudComunAuxiliarQuery);
		
		if(solicitudAuxiliar != null && solicitudAuxiliar.getResults() != null && solicitudAuxiliar.getResults().size()>0){
			for (int i = 0; i < solicitudAuxiliar.getResults().size(); i++) {
				idSolicitudes.add(solicitudAuxiliar.getResults().get(i).getIdSolicitudAuxiliar());
			}
			
			return idSolicitudes;
		}
				
		// TODO Auto-generated method stub
		return null;
	}

/**
 *  Método que elimina una solicitud auxiliar dado un id de solicitud.
 *
 * @param idSolicitud el id solicitud
 */
	public void borrarSolicitudComunAuxiliar(Long idSolicitud){
	try{
	solicitudComunAuxiliarDAO.delete(idSolicitud);
	}catch(Exception e){
	logger.error("No se puede eliminar solicitud Comun Auxiliar con id" + idSolicitud,e);
	}

	
	}
	
	/**
	 * Pasa de Solicitud a SolicitudBean.
	 *
	 * @param solicitud el solicitud
	 * @param numTotal int
	 * @return auxSolicitud SolicitudBean
	 */
	private SolicitudBean SolicitudAuxiliarVistatoSolicitudBean(SolComunAuxiliarView solicitud, int numTotal) {
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT); 
		SolicitudBean auxSolicitud = new SolicitudBean();
		
		if(solicitud.isIdConsentimiento() != null){
			auxSolicitud.setIdConsentimiento(solicitud.isIdConsentimiento());
		}
		
		if(solicitud.getTipoPago() != null){
			auxSolicitud.setDescripcionTipoPago(solicitud.getTipoPago());
		}
		
		if(solicitud.getIdSolicitud() != null){
			auxSolicitud.setId(solicitud.getIdSolicitud());
		}
		
		if(solicitud.getNumeroSolicitud() != null){	
			auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		}
		
		if(solicitud.getNif() != null){	
			auxSolicitud.setNif(solicitud.getNif());
		}
		
		if(solicitud.getNombre() != null){	
			auxSolicitud.setNombre(solicitud.getNombre());
		}
		
		if(solicitud.getMotivoReduccion() != null){	
			auxSolicitud.setMotivoReduccion(solicitud.getMotivoReduccion());
		}
		
		if(solicitud.getSegundoApellido() != null){	
			auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		}
		
		if(solicitud.getPrimerApellido() != null){	
			auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		}
		
		if(solicitud.getEmail() != null){		
			auxSolicitud.setEmail(solicitud.getEmail());
		}
		
		if(solicitud.getTelefonoSolicitud() != null){	
			auxSolicitud.setTelefono(solicitud.getTelefonoSolicitud());
		}
		
		if(solicitud.getEjercicio() != null){	
			auxSolicitud.setEjercicio(solicitud.getEjercicio());
		}
		
		if(solicitud.getIdConvocatoria() != null){	
			auxSolicitud.setIdConvocatoria(solicitud.getIdConvocatoria());
		}
		
		if(solicitud.getSiglasCentroGestor() != null){	
			auxSolicitud.setSiglasCentroGestor(solicitud.getSiglasCentroGestor());
		}
		
		// Para la exportacion a excel
		if(solicitud.getDesEspecialidad() != null){	
			auxSolicitud.setDescripcionEspecialidad(solicitud.getDesEspecialidad());
		}
		
		if(solicitud.getDesCuerpoEscala() != null){	
			auxSolicitud.setDesCuerpoEscala(solicitud.getDesCuerpoEscala());
		}
		
		if(solicitud.getFechaNacimiento() != null){	
			auxSolicitud.setFechaNacimiento(solicitud.getFechaNacimiento());
		}
		
		if(solicitud.getSexo()!= null){	
			auxSolicitud.setSexo(solicitud.getSexo());
		}
		
		if(solicitud.getNacionalidad() != null){	
			auxSolicitud.setNacionalidad(solicitud.getNacionalidad());
		}
		
		if(solicitud.getCalleDomicilio() != null){	
			auxSolicitud.setCalleDomicilio(solicitud.getCalleDomicilio());
		}
		
		if(solicitud.getCodigoPostal() != null){	
			auxSolicitud.setCodigoPostalDomicilio(solicitud.getCodigoPostal());
		}
		
		if(solicitud.getMunicipioDomicilio() != null){	
			auxSolicitud.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		}
		
		if(solicitud.getProvinciaDomicilio() != null){	
			auxSolicitud.setDescripcionIdProvinciaDomicilio(solicitud.getProvinciaDomicilio());
		}
		
		if(solicitud.getNacionDomicilio() != null){	
			auxSolicitud.setNacionPaisDomicilio(solicitud.getNacionDomicilio());
		}
		
		if(solicitud.getProvinciaExamen() != null){	
			auxSolicitud.setDescripcionIdProvinciaExamen(solicitud.getProvinciaExamen());
		}
		
		if(solicitud.getDesTipoDiscapacidad() != null){	
			auxSolicitud.setDescripcionTipoDiscapacidad(solicitud.getDesTipoDiscapacidad());
		}
		
		if(solicitud.getPorcentajeDiscapacidad() != null){	
			auxSolicitud.setPorcentajeDiscapacidad((int)solicitud.getPorcentajeDiscapacidad());
		}
		
		if(solicitud.getReservaDiscapacidad() != null){	
			auxSolicitud.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		}
		
		//Detalle Discapacidad
		if(solicitud.getDetalleDiscapacidad()!= null){
			auxSolicitud.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());			
		}
		
		// Si es desempleado
		if(solicitud.getMotivoReduccion()!=null && solicitud.getMotivoReduccion()==2){
			
			auxSolicitud.setEsDesempleo(true);
		
		}	
		// Si es familiaNumerosa
		if(solicitud.getMotivoReduccion()!=null && solicitud.getMotivoReduccion()==3){
			
			auxSolicitud.setEsFNumerosa(true);
			auxSolicitud.setFamNumerosa("E");
		
		}
		// Si es familiaNumerosa
		if(solicitud.getMotivoReduccion()!=null && solicitud.getMotivoReduccion()==5){
				
				auxSolicitud.setEsFNumerosa(true);
				auxSolicitud.setFamNumerosa("G");
			
		}
		// Si es Discapacidad
		if(solicitud.getMotivoReduccion()!=null && solicitud.getMotivoReduccion()==1){
			
			auxSolicitud.setEsDiscapacidad(true);
		 
		}
		
		if(solicitud.getTitulo() != null){	
			auxSolicitud.setDescripcionTituloOficial(solicitud.getTitulo());
		}
		
		if(solicitud.getOtrosTitulos() != null){	
			auxSolicitud.setOtrosTitulos(solicitud.getOtrosTitulos());
		}
		
		if(solicitud.getDatosA() != null){	
			auxSolicitud.setDatosA(solicitud.getDatosA());
		}
		
		if(solicitud.getDatosB() != null){	
			auxSolicitud.setDatosB(solicitud.getDatosB());
		}
		
		if(solicitud.getDatosC() != null){	
			auxSolicitud.setDatosC(solicitud.getDatosC());
		}
		
		if(solicitud.getDesCentroGestor() != null){	
			auxSolicitud.setCentroGestor(solicitud.getDesCentroGestor());
		}
		
		if (solicitud.getFechaSolicitud() != null) {
			try{
				logger.info(STRING_FECHASTRING+ sdf.format(solicitud.getFechaSolicitud()));
				auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARFECHASOLICITUD+ solicitud.getFechaSolicitud(),e);			
			}
		}
		
		// ID_CONSENTIMIENTO
		if(solicitud.isIdConsentimiento() != null){
			auxSolicitud.setIdConsentimiento(solicitud.isIdConsentimiento());
		}else{
			// SI NO SE HA ESPECIFICADO VALOR PARA EL CONSENTIMIENTO, POR DEFECTO SE CONSIDERA TRUE
			auxSolicitud.setIdConsentimiento(true);
		}
		
		if(solicitud.getIdModelo()!=null){
			auxSolicitud.setIdModelo(solicitud.getIdModelo().toString());
		}
		
		if(solicitud.getDesempleoVerificado() != null){
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		
		if(solicitud.getDiscapacidadVerificado() != null){
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado());
		}
		
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}	
		
		if(solicitud.getFnumerosaVerificado() != null){
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		
		if(solicitud.getVictimasVerificado() != null){
			auxSolicitud.setVictimasVerificado(solicitud.getVictimasVerificado());
		}
		
		auxSolicitud.setAdmitido(ExportarExcelSolicitudesSpring.obtieneAdmitido(auxSolicitud));
		auxSolicitud.setEstadoPID(ExportarExcelSolicitudesSpring.obtieneEstadoPID(auxSolicitud));
		
		if(solicitud.isIdConsentimientoAeat() != null){
			auxSolicitud.setIdConsentimientoAeat(solicitud.isIdConsentimientoAeat());
		}else{
			auxSolicitud.setIdConsentimientoAeat(true);
		}	
		
		if(solicitud.getMotivoOposicion() != null){	
			auxSolicitud.setMotivoOposicion(solicitud.getMotivoOposicion());
		}
		
		auxSolicitud.setNumTotal(numTotal);
		
		return auxSolicitud;
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
	 * Obtiene el sol comun auxiliar view DAO.
	 *
	 * @return el sol comun auxiliar view DAO
	 */
	public SolComunAuxiliarViewDAO getSolComunAuxiliarViewDAO() {
		return solComunAuxiliarViewDAO;
	}

	/**
	 * Establece el sol comun auxiliar view DAO.
	 *
	 * @param solComunAuxiliarViewDAO el nuevo sol comun auxiliar view DAO
	 */
	public void setSolComunAuxiliarViewDAO(SolComunAuxiliarViewDAO solComunAuxiliarViewDAO) {
		this.solComunAuxiliarViewDAO = solComunAuxiliarViewDAO;
	}	

	/**
	 * Obtiene el tipo pago manager.
	 *
	 * @return el tipo pago manager
	 */
	public TipoPagoManager getTipoPagoManager() {
		return tipoPagoManager;
	}

	/**
	 * Establece el tipo pago manager.
	 *
	 * @param tipoPagoManager el nuevo tipo pago manager
	 */
	public void setTipoPagoManager(TipoPagoManager tipoPagoManager) {
		this.tipoPagoManager = tipoPagoManager;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudComunAuxiliarManager#buscarSolicitudes(java.util.List)
	 */
	@Override
	public List<SolicitudBean> buscarSolicitudes(List<SolicitudCcaaBean> solicitudes) {
    
		SolicitudComunAuxiliarQuery solicitudComunQuery = new SolicitudComunAuxiliarQuery();
		List<SolicitudBean> arrSolicitudes;
		
		for (SolicitudCcaaBean solicitudCcaaBean : solicitudes) {
			if(null != solicitudCcaaBean.getIdSolicitud()){
				solicitudComunQuery.addIdSolicitudAuxiliarIn(solicitudCcaaBean.getIdSolicitud());			
			}			
		}
		
		solicitudComunQuery.addOrder(SolicitudComunAuxiliarQuery.IDSOLICITUDAUXILIAR,OrderType.ASC);
		arrSolicitudes= buscarSolicitudesAll(solicitudComunQuery);
		
		return arrSolicitudes;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudComunAuxiliarManager#buscarSolicitudesAll(es.map.ips.model.SolicitudComunAuxiliarQuery)
	 */
	@Override
	public List<SolicitudBean> buscarSolicitudesAll(SolicitudComunAuxiliarQuery solicitudComunQuery) {
		
		SearchResult<SolicitudComunAuxiliar> solicitudComunAuxiliarList = solicitudComunAuxiliarDAO.search(solicitudComunQuery);

		List<SolicitudBean> listaSolicitudesBean = new ArrayList<SolicitudBean>();
		
		for (SolicitudComunAuxiliar solicitudComunAuxiliar: solicitudComunAuxiliarList.getResults()) {
			listaSolicitudesBean.add(toSolicitudBean(solicitudComunAuxiliar, 1));
		}
		return listaSolicitudesBean;
	}
	
	/**
	 * 
	 * @param solicitudBean
	 * @return
	 */
	public SolicitudComunAuxiliar toSolicitud (SolicitudBean  solicitudBean){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SolicitudComunAuxiliar solicitud = new SolicitudComunAuxiliar();
		
		if (solicitudBean.getId() != null && solicitudBean.getId() > 0) {
			solicitud.setIdSolicitudAuxiliar(solicitudBean.getId());
		}
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());
		solicitud.setIdConsentimiento(solicitudBean.getIdConsentimiento());
		solicitud.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		solicitud.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		//solicitud.setComentarios(solicitudBean.getComentarios());
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());	
		solicitud.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		//solicitud.setEdadVerificada(solicitudBean.getEdadVerificada());
		solicitud.setEmail(solicitudBean.getEmail());
		solicitud.setEspecialidad(solicitudBean.getEspecialidad());
		//solicitud.setEstadoSolicitud(solicitudBean.getEstadoSolicitud());
		solicitud.setFechaNacimiento(solicitudBean.getFechaNacimiento());
		if(solicitudBean.getFechaNacimientoVerificada() != null){
			solicitud.setFechaNacimientoVerificada(solicitudBean.getFechaNacimientoVerificada());
		}
		if (solicitudBean.getFnumerosaVerificado() != null) {
			solicitud.setFnumerosaVerificado(solicitudBean.getFnumerosaVerificado());
		}
		if (solicitudBean.getDiscapacidadVerificado() != null) {
			solicitud.setDiscapacidadVerificado(solicitudBean.getDiscapacidadVerificado());
		}
		if (solicitudBean.getDesempleoVerificado() != null) {
			solicitud.setDesempleoVerificado(solicitudBean.getDesempleoVerificado());
		}

		try {
			if (solicitudBean.getFechaSolicitud() != null) {
				solicitud.setFechaSolicitud(sdf.parse(solicitudBean.getFechaSolicitud()));
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
		//solicitud.setFechaUltActualizacion(solicitudBean.getFechaUtlActualizacion());
		//solicitud.setIdSolicitud(solicitudBean.getId());
		//solicitud.setLocalidadNacimiento(solicitudBean.getLocalidadNacimiento());		
		solicitud.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		solicitud.setNacionalidad(solicitudBean.getNacionalidad());
		solicitud.setNif(solicitudBean.getNif());
		solicitud.setNombre(solicitudBean.getNombre());
		solicitud.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
		
//		if(solicitudBean.getPagoSolicitudes() != null){
//			Set<PagoSolicitudBean> setPagoSolicitudBean = new HashSet<PagoSolicitudBean> (solicitudBean.getPagoSolicitudes());
//			Iterator<PagoSolicitudBean> it = setPagoSolicitudBean.iterator();
//			Set<PagoSolicitud> setPagoSolicitud = new HashSet<PagoSolicitud>();
//			while (it.hasNext()){
//				PagoSolicitudBean pagoSolicitudBean = (PagoSolicitudBean) it.next();
//				PagoSolicitud pagoSolicitud = this.toPagoSolicitud(pagoSolicitudBean);
//				setPagoSolicitud.add(pagoSolicitud);
//			}
//		}
		
		solicitud.setPais(solicitudBean.getPais());
		solicitud.setPorcentajeDiscapacidad(solicitudBean.getPorcentajeDiscapacidad().shortValue());
		solicitud.setPrimerApellido(solicitudBean.getPrimerApellido());
//		solicitud.setProvinciaByIdProvinciaDomicilio(solicitudBean.getProvinciaByIdProvinciaDomicilio());
//		solicitud.setProvinciaByIdProvinciaExamen(solicitudBean.getProvinciaByIdProvinciaExamen());
//		solicitud.setProvinciaByIdProvinciaNacimiento(solicitudBean.getProvinciaByIdProvinciaNacimiento());
//		
//		if(solicitudBean.getRegistroSolicitudes() != null){
//			Set<RegistroSolicitudBean> setRegistroSolicitudBean = new HashSet<RegistroSolicitudBean> (solicitudBean.getRegistroSolicitudes());
//			Iterator<RegistroSolicitudBean> it = setRegistroSolicitudBean.iterator();
//			Set<RegistroSolicitud> setRegistroSolicitud = new HashSet<RegistroSolicitud>();
//			
//			while (it.hasNext()){
//				RegistroSolicitudBean registroSolicitudBean = (RegistroSolicitudBean) it.next();
//				RegistroSolicitud registroSolicitud = this.toRegistroSolicitud(registroSolicitudBean);
//				setRegistroSolicitud.add(registroSolicitud);
//			}
//		}
	
		solicitud.setReservaDiscapacidad(solicitudBean.getReservaDiscapacidad());
		solicitud.setSegundoApellido(solicitudBean.getSegundoApellido());
		solicitud.setSexo(solicitudBean.getSexo());
		solicitud.setTelefono(solicitudBean.getTelefono());
		solicitud.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad());
//		solicitud.setTipoSolicitud(solicitudBean.getTipo());
		solicitud.setTituloOficial(solicitudBean.getTituloOficial());
		
//		if(solicitudBean.getTituloVerificado() != null){
//			solicitud.setTituloVerificado(solicitudBean.getTituloVerificado());
//		}
//		
//		if(solicitudBean.getFunHabilitado() != null) {
//			solicitud.setFunHabilitado(solicitudBean.getFunHabilitado());
//		} else {
//			solicitud.setFunHabilitado(false);
//		}
		
		// TODO Incidencia no figura Modelo en Solicitud
//		Modelo modelo = new Modelo();
//		modelo.setIdModelo(solicitudBean.getConvocatoria().getModelo().getIdModelo());
//		solicitud.setModelo(modelo);
		
		return solicitud;
	}
	
//	/**
//	 * Pasa de PagoSolicitudBean a PagoSolicitud.
//	 *
//	 * @param pagoSolicitudBean el pago solicitud bean
//	 * @return pagoSolicitudBean PagoSolicitudBean
//	 */
//	private PagoSolicitud toPagoSolicitud (PagoSolicitudBean pagoSolicitudBean){
//		
//		PagoSolicitud pagoSolicitud = new PagoSolicitud();
//		pagoSolicitud.setEntidadFinanciera(pagoSolicitudBean.getEntidadFinanciera());
//		pagoSolicitud.setFechaIntento(pagoSolicitudBean.getFechaIntento());
//		pagoSolicitud.setId(pagoSolicitudBean.getIdSolicitud());
//		pagoSolicitud.setLogServicio(pagoSolicitudBean.getLogservicio());
//		pagoSolicitud.setMotivoReduccionTasa(pagoSolicitudBean.getMotivoReduccionTasa());
//		pagoSolicitud.setSolicitudComun(pagoSolicitudBean.getSolicitud());
//		pagoSolicitud.setImporte(pagoSolicitudBean.getImporte());
//		pagoSolicitud.setNrc(pagoSolicitudBean.getNrc());
//		pagoSolicitud.setResultado(pagoSolicitudBean.getResultado());
//		pagoSolicitud.setSolicitaReduccion(pagoSolicitudBean.getSolicitaReduccion());
//		pagoSolicitud.setTipo(pagoSolicitudBean.getTipo());
//		
//		return pagoSolicitud;
//	}
	
	/**
	 * Modifica una solicitud  
	 * @param solicitudBean SolicitudBean
	 */
	public void modificarSolicitud (SolicitudBean  solicitudBean){
		
		SolicitudComunAuxiliar solicitud =  toSolicitud(solicitudBean);
		solicitudComunAuxiliarDAO.update(solicitud);
	}

	@Override
	public SolicitudComun toSolicitudComun(SolicitudBean solicitudBean) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SolicitudComun solicitud = new SolicitudComun();
		
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());
		solicitud.setIdConsentimiento(solicitudBean.getIdConsentimiento());
		solicitud.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		solicitud.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		solicitud.setComentarios(solicitudBean.getComentarios());
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());	
		solicitud.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		solicitud.setEdadVerificada(solicitudBean.getEdadVerificada());
		solicitud.setEmail(solicitudBean.getEmail());
		solicitud.setEspecialidad(solicitudBean.getEspecialidad());
		solicitud.setEstadoSolicitud(solicitudBean.getEstadoSolicitud());
		solicitud.setFechaNacimiento(solicitudBean.getFechaNacimiento());
		if(solicitudBean.getFechaNacimientoVerificada() != null){
			solicitud.setFechaNacimientoVerificada(solicitudBean.getFechaNacimientoVerificada());
		}
		if (solicitudBean.getFnumerosaVerificado() != null) {
			solicitud.setFnumerosaVerificado(solicitudBean.getFnumerosaVerificado());
		}
		if (solicitudBean.getDiscapacidadVerificado() != null) {
			solicitud.setDiscapacidadVerificado(solicitudBean.getDiscapacidadVerificado());
		}
		if (solicitudBean.getDesempleoVerificado() != null) {
			solicitud.setDesempleoVerificado(solicitudBean.getDesempleoVerificado());
		}

		try {
			solicitud.setFechaSolicitud(sdf.parse(solicitudBean.getFechaSolicitud()));
		} catch (ParseException e) {
			logger.error("No se ha podido parserar la fecha de solicitud",e);		
			
		}
		
		solicitud.setFechaUltActualizacion(solicitudBean.getFechaUtlActualizacion());
		solicitud.setIdSolicitud(solicitudBean.getId());
		solicitud.setLocalidadNacimiento(solicitudBean.getLocalidadNacimiento());		
		solicitud.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		solicitud.setNacionalidad(solicitudBean.getNacionalidad());
		solicitud.setNif(solicitudBean.getNif());
		solicitud.setNombre(solicitudBean.getNombre());
		solicitud.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
		
		solicitud.setPais(solicitudBean.getPais());
		solicitud.setPorcentajeDiscapacidad(solicitudBean.getPorcentajeDiscapacidad().shortValue());
		solicitud.setPrimerApellido(solicitudBean.getPrimerApellido());
		solicitud.setProvinciaByIdProvinciaDomicilio(solicitudBean.getProvinciaByIdProvinciaDomicilio());
		solicitud.setProvinciaByIdProvinciaExamen(solicitudBean.getProvinciaByIdProvinciaExamen());
		solicitud.setProvinciaByIdProvinciaNacimiento(solicitudBean.getProvinciaByIdProvinciaNacimiento());
	
		solicitud.setReservaDiscapacidad(solicitudBean.getReservaDiscapacidad());
		solicitud.setSegundoApellido(solicitudBean.getSegundoApellido());
		solicitud.setSexo(solicitudBean.getSexo());
		solicitud.setTelefono(solicitudBean.getTelefono());
		solicitud.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad());
		solicitud.setTipoSolicitud(solicitudBean.getTipo());
		solicitud.setTituloOficial(solicitudBean.getTituloOficial());
		
		if(solicitudBean.getTituloVerificado() != null){
			solicitud.setTituloVerificado(solicitudBean.getTituloVerificado());
		}
		
		if(solicitudBean.getFunHabilitado() != null) {
			solicitud.setFunHabilitado(solicitudBean.getFunHabilitado());
		} else {
			solicitud.setFunHabilitado(false);
		}
		
		// TODO Incidencia no figura Modelo en Solicitud
		Modelo modelo = new Modelo();
		if (solicitudBean.getConvocatoria() != null 
				&& solicitudBean.getConvocatoria().getModelo() != null 
				&& solicitudBean.getConvocatoria().getModelo().getIdModelo() != null) {
			modelo.setIdModelo(solicitudBean.getConvocatoria().getModelo().getIdModelo());
			solicitud.setModelo(modelo);
		}
		
		
		return solicitud;
	}
	

}
