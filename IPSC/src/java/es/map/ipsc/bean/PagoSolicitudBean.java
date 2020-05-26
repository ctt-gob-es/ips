package es.map.ipsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.LogServicio;

import es.map.ips.model.SolicitudComun;


/**
 * El Class PagoSolicitudBean.
 */
public class PagoSolicitudBean {
	
	/** el numero solicitud. */
	private String numeroSolicitud;
	
	/** el nif. */
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el nombre parcial. */
	private String nombreParcial;
	
	/** el apellido 1. */
	private String apellido1;
	
	/** el apellido 2. */
	private String apellido2;
	
	/** el forma pago. */
	private String formaPago;
	
	/** el nrc. */
	private String nrc;
	
	/** el nrc ciudadano. */
	private String nrcCiudadano;
	
	/** el motivo. */
	private String motivo;
	
	/** el codigo entidad. */
	private String codigoEntidad;
	
	/** el reduccion pago. */
	private String reduccionPago;
	
	/** el banco. */
	private String banco;
	
	/** el oficina. */
	private String oficina;
	
	/** el dc. */
	private String dc;
	
	/** el cuenta. */
	private String cuenta;
	
	/** el entidad. */
	private String entidad;
	
	/** el id. */
	private Long id;
	
	/** el solicitud. */
	private SolicitudComun solicitud;
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el log servicio. */
	private LogServicio logServicio;
	
	/** el fecha intento. */
	private Date fechaIntento;
	
	/** el tipo. */
	private char tipo;
	
	/** el importe. */
	private Float importe;
	
	/** el resultado. */
	private String resultado;
	
	/** el motivo reduccion tasas. */
	private Set motivoReduccionTasas = new HashSet(0);
	
	/** el detalle motivo. */
	private String detalleMotivo;
	
	/** el id log servicio. */
	private int idLogServicio;
	
	/** el fecha pago. */
	private Date fechaPago;
	
		
	/**
	 * Obtiene el detalle motivo.
	 *
	 * @return el detalle motivo
	 */
	public String getDetalleMotivo() {
		return detalleMotivo;
	}
	
	/**
	 * Establece el detalle motivo.
	 *
	 * @param detalleMotivo el nuevo detalle motivo
	 */
	public void setDetalleMotivo(String detalleMotivo) {
		this.detalleMotivo = detalleMotivo;
	}
	
	/**
	 * Obtiene el nrc.
	 *
	 * @return el nrc
	 */
	public String getNrc() {
		return nrc;
	}
	
	/**
	 * Establece el nrc.
	 *
	 * @param nrc el nuevo nrc
	 */
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	
	/**
	 * Obtiene el nrc ciudadano.
	 *
	 * @return el nrc ciudadano
	 */
	public String getNrcCiudadano() {
		return nrcCiudadano;
	}
	
	/**
	 * Establece el nrc ciudadano.
	 *
	 * @param nrcCiudadano el nuevo nrc ciudadano
	 */
	public void setNrcCiudadano(String nrcCiudadano) {
		this.nrcCiudadano = nrcCiudadano;
	}
	
	/**
	 * Obtiene el entidad.
	 *
	 * @return el entidad
	 */
	public String getEntidad() {
		return entidad;
	}
	
	/**
	 * Establece el entidad.
	 *
	 * @param entidad el nuevo entidad
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	
	/**
	 * Obtiene el id solicitud.
	 *
	 * @return el id solicitud
	 */
	public String getIdSolicitud() {
		return idSolicitud;
	}
	
	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public String getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	
	/**
	 * Obtiene el importe.
	 *
	 * @return el importe
	 */
	public Float getImporte() {
		return importe;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el solicitud.
	 *
	 * @return el solicitud
	 */
	public SolicitudComun getSolicitud() {
		return solicitud;
	}
	
	/**
	 * Establece el solicitud.
	 *
	 * @param solicitud el nuevo solicitud
	 */
	public void setSolicitud(SolicitudComun solicitud) {
		this.solicitud = solicitud;
	}
	
	/**
	 * Obtiene el log servicio.
	 *
	 * @return el log servicio
	 */
	public LogServicio getLogServicio() {
		return logServicio;
	}
	
	/**
	 * Establece el log servicio.
	 *
	 * @param logServicio el nuevo log servicio
	 */
	public void setLogServicio(LogServicio logServicio) {
		this.logServicio = logServicio;
	}
	
	/**
	 * Obtiene el fecha intento.
	 *
	 * @return el fecha intento
	 */
	public Date getFechaIntento() {
		return fechaIntento;
	}
	
	/**
	 * Establece el fecha intento.
	 *
	 * @param fechaIntento el nuevo fecha intento
	 */
	public void setFechaIntento(Date fechaIntento) {
		this.fechaIntento = fechaIntento;
	}
	
	/**
	 * Obtiene el tipo.
	 *
	 * @return el tipo
	 */
	public char getTipo() {
		return tipo;
	}
	
	/**
	 * Establece el tipo.
	 *
	 * @param tipo el nuevo tipo
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Obtiene el resultado.
	 *
	 * @return el resultado
	 */
	public String getResultado() {
		return resultado;
	}
	
	/**
	 * Establece el resultado.
	 *
	 * @param resultado el nuevo resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	/**
	 * Obtiene el motivo reduccion tasas.
	 *
	 * @return el motivo reduccion tasas
	 */
	public Set getMotivoReduccionTasas() {
		return motivoReduccionTasas;
	}
	
	/**
	 * Establece el motivo reduccion tasas.
	 *
	 * @param motivoReduccionTasas el nuevo motivo reduccion tasas
	 */
	public void setMotivoReduccionTasas(Set motivoReduccionTasas) {
		this.motivoReduccionTasas = motivoReduccionTasas;
	}
	
	/**
	 * Establece el importe.
	 *
	 * @param importe el nuevo importe
	 */
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	
	/**
	 * Obtiene el numero solicitud.
	 *
	 * @return el numero solicitud
	 */
	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}
	
	/**
	 * Establece el numero solicitud.
	 *
	 * @param numeroSolicitud el nuevo numero solicitud
	 */
	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	
	/**
	 * Obtiene el nif.
	 *
	 * @return el nif
	 */
	public String getNif() {
		return nif;
	}
	
	/**
	 * Establece el nif.
	 *
	 * @param nif el nuevo nif
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el forma pago.
	 *
	 * @return el forma pago
	 */
	public String getFormaPago() {
		return formaPago;
	}
	
	/**
	 * Establece el forma pago.
	 *
	 * @param formaPago el nuevo forma pago
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	/**
	 * Obtiene el motivo.
	 *
	 * @return el motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	
	/**
	 * Establece el motivo.
	 *
	 * @param motivo el nuevo motivo
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	/**
	 * Obtiene el codigo entidad.
	 *
	 * @return el codigo entidad
	 */
	public String getCodigoEntidad() {
		return codigoEntidad;
	}
	
	/**
	 * Establece el codigo entidad.
	 *
	 * @param codigoEntidad el nuevo codigo entidad
	 */
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}
	
	/**
	 * Obtiene el reduccion pago.
	 *
	 * @return el reduccion pago
	 */
	public String getReduccionPago() {
		return reduccionPago;
	}
	
	/**
	 * Establece el reduccion pago.
	 *
	 * @param reduccionPago el nuevo reduccion pago
	 */
	public void setReduccionPago(String reduccionPago) {
		this.reduccionPago = reduccionPago;
	}
	
	/**
	 * Obtiene el banco.
	 *
	 * @return el banco
	 */
	public String getBanco() {
		return banco;
	}
	
	/**
	 * Establece el banco.
	 *
	 * @param banco el nuevo banco
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	/**
	 * Obtiene el oficina.
	 *
	 * @return el oficina
	 */
	public String getOficina() {
		return oficina;
	}
	
	/**
	 * Establece el oficina.
	 *
	 * @param oficina el nuevo oficina
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	
	/**
	 * Obtiene el dc.
	 *
	 * @return el dc
	 */
	public String getDc() {
		return dc;
	}
	
	/**
	 * Establece el dc.
	 *
	 * @param dc el nuevo dc
	 */
	public void setDc(String dc) {
		this.dc = dc;
	}
	
	/**
	 * Obtiene el cuenta.
	 *
	 * @return el cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}
	
	/**
	 * Establece el cuenta.
	 *
	 * @param cuenta el nuevo cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	/**
	 * Obtiene el apellido 1.
	 *
	 * @return el apellido 1
	 */
	public String getApellido1() {
		return apellido1;
	}
	
	/**
	 * Establece el apellido 1.
	 *
	 * @param apellido1 el nuevo apellido 1
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	/**
	 * Obtiene el apellido 2.
	 *
	 * @return el apellido 2
	 */
	public String getApellido2() {
		return apellido2;
	}
	
	/**
	 * Establece el apellido 2.
	 *
	 * @param apellido2 el nuevo apellido 2
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	/**
	 * Obtiene el nombre parcial.
	 *
	 * @return el nombre parcial
	 */
	public String getNombreParcial() {
		return nombreParcial;
	}
	
	/**
	 * Establece el nombre parcial.
	 *
	 * @param nombreParcial el nuevo nombre parcial
	 */
	public void setNombreParcial(String nombreParcial) {
		this.nombreParcial = nombreParcial;
	}
	
	/**
	 * Obtiene el id log servicio.
	 *
	 * @return el id log servicio
	 */
	public int getIdLogServicio() {
		return idLogServicio;
	}
	
	/**
	 * Establece el id log servicio.
	 *
	 * @param idLogServicio el nuevo id log servicio
	 */
	public void setIdLogServicio(int idLogServicio) {
		this.idLogServicio = idLogServicio;
	}
	
	/**
	 * Obtiene el fecha pago.
	 *
	 * @return el fecha pago
	 */
	public Date getFechaPago() {
		return fechaPago;
	}
	
	/**
	 * Establece el fecha pago.
	 *
	 * @param fechaPago el nuevo fecha pago
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	
}
