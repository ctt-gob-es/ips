package es.map.ipsc.bean;

import java.util.Date;

/**
 * El Class InteresadoBean.
 */
public class InteresadoBean implements java.io.Serializable {
	
	/** el cd interesado asiento. */
	private Integer cdInteresadoAsiento;
	
	/** el ds tipo asiento. */
	private String dsTipoAsiento;
	
	/** el preferencia. */
	private String preferencia;
	
	/** el cd tipo identificador. */
	private String cdTipoIdentificador;
	
	/** el ds tipo identificador. */
	private String dsTipoIdentificador;
	
	/** el ds num identificador. */
	private String dsNumIdentificador;
	
	/** el ds nombre. */
	private String dsNombre;
	
	/** el ds apellido 1. */
	private String dsApellido1;
	
	/** el ds apellido 2. */
	private String dsApellido2;
	
	/** el ds razon social. */
	private String dsRazonSocial;
	
	/** el ds otro interesado. */
	private String dsOtroInteresado;
	
	/** el cd usuario alta. */
	private Integer cdUsuarioAlta;
	
	/** el fe alta. */
	private Date feAlta;
	
	/** el cd usuario modificacion. */
	private Integer cdUsuarioModificacion;
	
	/** el fe modificacion. */
	private Date feModificacion;
	
	/** el nu modificaciones. */
	private Integer nuModificaciones;
	
	/** el fe baja. */
	private Date feBaja;
	
	/** el cd pais. */
	private String cdPais;
    
    /** el ds pais. */
    private String dsPais;
    
    /** el cd com autonoma. */
    private String cdComAutonoma;
    
    /** el ds com autonoma. */
    private String dsComAutonoma;
    
    /** el cd provincia. */
    private String cdProvincia;
    
    /** el ds provincia. */
    private String dsProvincia;
    
    /** el cd localidad. */
    private String cdLocalidad;
    
    /** el ds localidad. */
    private String dsLocalidad;
    
    /** el cd tipo via. */
    private String cdTipoVia;
    
    /** el ds tipo via. */
    private String dsTipoVia;
    
    /** el ds nombre via. */
    private String dsNombreVia;
    
    /** el ds num via. */
    private String dsNumVia;
    
    /** el ds bloque. */
    private String dsBloque;
    
    /** el ds escalera. */
    private String dsEscalera;
    
    /** el ds piso. */
    private String dsPiso;
    
    /** el ds puerta. */
    private String dsPuerta;
    
    /** el ds codigo postal. */
    private String dsCodigoPostal;
    
    /** el ds telefono contacto. */
    private String dsTelefonoContacto;
    
    /** el ds correo electronico. */
    private String dsCorreoElectronico;
    
    /** el ds telefono movil. */
    private String dsTelefonoMovil;
    
    /** el b alerta telefono. */
    private boolean bAlertaTelefono;
    
    /** el b alerta email. */
    private boolean bAlertaEmail;
    
    /** el es persona fisica. */
    private boolean esPersonaFisica;
    
    /** el alerta email. */
    private String alertaEmail;
    
    /** el alerta telefono. */
    private String alertaTelefono;
    
    
    
    
	/**
	 * Obtiene el ds telefono contacto.
	 *
	 * @return el ds telefono contacto
	 */
	public String getDsTelefonoContacto() {
		return dsTelefonoContacto;
	}
	
	/**
	 * Establece el ds telefono contacto.
	 *
	 * @param dsTelefonoContacto el nuevo ds telefono contacto
	 */
	public void setDsTelefonoContacto(String dsTelefonoContacto) {
		this.dsTelefonoContacto = dsTelefonoContacto;
	}
	
	/**
	 * Obtiene el alerta email.
	 *
	 * @return el alerta email
	 */
	public String getAlertaEmail() {
		return alertaEmail;
	}
	
	/**
	 * Establece el alerta email.
	 *
	 * @param alertaEmail el nuevo alerta email
	 */
	public void setAlertaEmail(String alertaEmail) {
		this.alertaEmail = alertaEmail;
	}
	
	/**
	 * Obtiene el alerta telefono.
	 *
	 * @return el alerta telefono
	 */
	public String getAlertaTelefono() {
		return alertaTelefono;
	}
	
	/**
	 * Establece el alerta telefono.
	 *
	 * @param alertaTelefono el nuevo alerta telefono
	 */
	public void setAlertaTelefono(String alertaTelefono) {
		this.alertaTelefono = alertaTelefono;
	}
	
	/**
	 * Obtiene el cd interesado asiento.
	 *
	 * @return el cd interesado asiento
	 */
	public Integer getCdInteresadoAsiento() {
		return cdInteresadoAsiento;
	}
	
	/**
	 * Establece el cd interesado asiento.
	 *
	 * @param cdInteresadoAsiento el nuevo cd interesado asiento
	 */
	public void setCdInteresadoAsiento(Integer cdInteresadoAsiento) {
		this.cdInteresadoAsiento = cdInteresadoAsiento;
	}
	
	/**
	 * Obtiene el ds tipo asiento.
	 *
	 * @return el ds tipo asiento
	 */
	public String getDsTipoAsiento() {
		return dsTipoAsiento;
	}
	
	/**
	 * Establece el ds tipo asiento.
	 *
	 * @param dsTipoAsiento el nuevo ds tipo asiento
	 */
	public void setDsTipoAsiento(String dsTipoAsiento) {
		this.dsTipoAsiento = dsTipoAsiento;
	}
	
	/**
	 * Obtiene el preferencia.
	 *
	 * @return el preferencia
	 */
	public String getPreferencia() {
		return preferencia;
	}
	
	/**
	 * Establece el preferencia.
	 *
	 * @param preferencia el nuevo preferencia
	 */
	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}
	
	/**
	 * Obtiene el cd tipo identificador.
	 *
	 * @return el cd tipo identificador
	 */
	public String getCdTipoIdentificador() {
		return cdTipoIdentificador;
	}
	
	/**
	 * Establece el cd tipo identificador.
	 *
	 * @param cdTipoIdentificador el nuevo cd tipo identificador
	 */
	public void setCdTipoIdentificador(String cdTipoIdentificador) {
		this.cdTipoIdentificador = cdTipoIdentificador;
	}
	
	/**
	 * Obtiene el ds tipo identificador.
	 *
	 * @return el ds tipo identificador
	 */
	public String getDsTipoIdentificador() {
		return dsTipoIdentificador;
	}
	
	/**
	 * Establece el ds tipo identificador.
	 *
	 * @param dsTipoIdentificador el nuevo ds tipo identificador
	 */
	public void setDsTipoIdentificador(String dsTipoIdentificador) {
		this.dsTipoIdentificador = dsTipoIdentificador;
	}
	
	/**
	 * Obtiene el ds num identificador.
	 *
	 * @return el ds num identificador
	 */
	public String getDsNumIdentificador() {
		return dsNumIdentificador;
	}
	
	/**
	 * Establece el ds num identificador.
	 *
	 * @param dsNumIdentificador el nuevo ds num identificador
	 */
	public void setDsNumIdentificador(String dsNumIdentificador) {
		this.dsNumIdentificador = dsNumIdentificador;
	}
	
	/**
	 * Obtiene el ds nombre.
	 *
	 * @return el ds nombre
	 */
	public String getDsNombre() {
		return dsNombre;
	}
	
	/**
	 * Establece el ds nombre.
	 *
	 * @param dsNombre el nuevo ds nombre
	 */
	public void setDsNombre(String dsNombre) {
		this.dsNombre = dsNombre;
	}
	
	/**
	 * Obtiene el ds apellido 1.
	 *
	 * @return el ds apellido 1
	 */
	public String getDsApellido1() {
		return dsApellido1;
	}
	
	/**
	 * Establece el ds apellido 1.
	 *
	 * @param dsApellido1 el nuevo ds apellido 1
	 */
	public void setDsApellido1(String dsApellido1) {
		this.dsApellido1 = dsApellido1;
	}
	
	/**
	 * Obtiene el ds apellido 2.
	 *
	 * @return el ds apellido 2
	 */
	public String getDsApellido2() {
		return dsApellido2;
	}
	
	/**
	 * Establece el ds apellido 2.
	 *
	 * @param dsApellido2 el nuevo ds apellido 2
	 */
	public void setDsApellido2(String dsApellido2) {
		this.dsApellido2 = dsApellido2;
	}
	
	/**
	 * Obtiene el ds razon social.
	 *
	 * @return el ds razon social
	 */
	public String getDsRazonSocial() {
		return dsRazonSocial;
	}
	
	/**
	 * Establece el ds razon social.
	 *
	 * @param dsRazonSocial el nuevo ds razon social
	 */
	public void setDsRazonSocial(String dsRazonSocial) {
		this.dsRazonSocial = dsRazonSocial;
	}
	
	/**
	 * Obtiene el ds otro interesado.
	 *
	 * @return el ds otro interesado
	 */
	public String getDsOtroInteresado() {
		return dsOtroInteresado;
	}
	
	/**
	 * Establece el ds otro interesado.
	 *
	 * @param dsOtroInteresado el nuevo ds otro interesado
	 */
	public void setDsOtroInteresado(String dsOtroInteresado) {
		this.dsOtroInteresado = dsOtroInteresado;
	}
	
	/**
	 * Obtiene el cd usuario alta.
	 *
	 * @return el cd usuario alta
	 */
	public Integer getCdUsuarioAlta() {
		return cdUsuarioAlta;
	}
	
	/**
	 * Establece el cd usuario alta.
	 *
	 * @param cdUsuarioAlta el nuevo cd usuario alta
	 */
	public void setCdUsuarioAlta(Integer cdUsuarioAlta) {
		this.cdUsuarioAlta = cdUsuarioAlta;
	}
	
	/**
	 * Obtiene el fe alta.
	 *
	 * @return el fe alta
	 */
	public Date getFeAlta() {
		return feAlta;
	}
	
	/**
	 * Establece el fe alta.
	 *
	 * @param feAlta el nuevo fe alta
	 */
	public void setFeAlta(Date feAlta) {
		this.feAlta = feAlta;
	}
	
	/**
	 * Obtiene el cd usuario modificacion.
	 *
	 * @return el cd usuario modificacion
	 */
	public Integer getCdUsuarioModificacion() {
		return cdUsuarioModificacion;
	}
	
	/**
	 * Establece el cd usuario modificacion.
	 *
	 * @param cdUsuarioModificacion el nuevo cd usuario modificacion
	 */
	public void setCdUsuarioModificacion(Integer cdUsuarioModificacion) {
		this.cdUsuarioModificacion = cdUsuarioModificacion;
	}
	
	/**
	 * Obtiene el fe modificacion.
	 *
	 * @return el fe modificacion
	 */
	public Date getFeModificacion() {
		return feModificacion;
	}
	
	/**
	 * Establece el fe modificacion.
	 *
	 * @param feModificacion el nuevo fe modificacion
	 */
	public void setFeModificacion(Date feModificacion) {
		this.feModificacion = feModificacion;
	}
	
	/**
	 * Obtiene el nu modificaciones.
	 *
	 * @return el nu modificaciones
	 */
	public Integer getNuModificaciones() {
		return nuModificaciones;
	}
	
	/**
	 * Establece el nu modificaciones.
	 *
	 * @param nuModificaciones el nuevo nu modificaciones
	 */
	public void setNuModificaciones(Integer nuModificaciones) {
		this.nuModificaciones = nuModificaciones;
	}
	
	/**
	 * Obtiene el fe baja.
	 *
	 * @return el fe baja
	 */
	public Date getFeBaja() {
		return feBaja;
	}
	
	/**
	 * Establece el fe baja.
	 *
	 * @param feBaja el nuevo fe baja
	 */
	public void setFeBaja(Date feBaja) {
		this.feBaja = feBaja;
	}
	
	/**
	 * Obtiene el cd pais.
	 *
	 * @return el cd pais
	 */
	public String getCdPais() {
		return cdPais;
	}
	
	/**
	 * Establece el cd pais.
	 *
	 * @param cdPais el nuevo cd pais
	 */
	public void setCdPais(String cdPais) {
		this.cdPais = cdPais;
	}
	
	/**
	 * Obtiene el ds pais.
	 *
	 * @return el ds pais
	 */
	public String getDsPais() {
		return dsPais;
	}
	
	/**
	 * Establece el ds pais.
	 *
	 * @param dsPais el nuevo ds pais
	 */
	public void setDsPais(String dsPais) {
		this.dsPais = dsPais;
	}
	
	/**
	 * Obtiene el cd com autonoma.
	 *
	 * @return el cd com autonoma
	 */
	public String getCdComAutonoma() {
		return cdComAutonoma;
	}
	
	/**
	 * Establece el cd com autonoma.
	 *
	 * @param cdComAutonoma el nuevo cd com autonoma
	 */
	public void setCdComAutonoma(String cdComAutonoma) {
		this.cdComAutonoma = cdComAutonoma;
	}
	
	/**
	 * Obtiene el ds com autonoma.
	 *
	 * @return el ds com autonoma
	 */
	public String getDsComAutonoma() {
		return dsComAutonoma;
	}
	
	/**
	 * Establece el ds com autonoma.
	 *
	 * @param dsComAutonoma el nuevo ds com autonoma
	 */
	public void setDsComAutonoma(String dsComAutonoma) {
		this.dsComAutonoma = dsComAutonoma;
	}
	
	/**
	 * Obtiene el cd provincia.
	 *
	 * @return el cd provincia
	 */
	public String getCdProvincia() {
		return cdProvincia;
	}
	
	/**
	 * Establece el cd provincia.
	 *
	 * @param cdProvincia el nuevo cd provincia
	 */
	public void setCdProvincia(String cdProvincia) {
		this.cdProvincia = cdProvincia;
	}
	
	/**
	 * Obtiene el ds provincia.
	 *
	 * @return el ds provincia
	 */
	public String getDsProvincia() {
		return dsProvincia;
	}
	
	/**
	 * Establece el ds provincia.
	 *
	 * @param dsProvincia el nuevo ds provincia
	 */
	public void setDsProvincia(String dsProvincia) {
		this.dsProvincia = dsProvincia;
	}
	
	/**
	 * Obtiene el cd localidad.
	 *
	 * @return el cd localidad
	 */
	public String getCdLocalidad() {
		return cdLocalidad;
	}
	
	/**
	 * Establece el cd localidad.
	 *
	 * @param cdLocalidad el nuevo cd localidad
	 */
	public void setCdLocalidad(String cdLocalidad) {
		this.cdLocalidad = cdLocalidad;
	}
	
	/**
	 * Obtiene el ds localidad.
	 *
	 * @return el ds localidad
	 */
	public String getDsLocalidad() {
		return dsLocalidad;
	}
	
	/**
	 * Establece el ds localidad.
	 *
	 * @param dsLocalidad el nuevo ds localidad
	 */
	public void setDsLocalidad(String dsLocalidad) {
		this.dsLocalidad = dsLocalidad;
	}
	
	/**
	 * Obtiene el cd tipo via.
	 *
	 * @return el cd tipo via
	 */
	public String getCdTipoVia() {
		return cdTipoVia;
	}
	
	/**
	 * Establece el cd tipo via.
	 *
	 * @param cdTipoVia el nuevo cd tipo via
	 */
	public void setCdTipoVia(String cdTipoVia) {
		this.cdTipoVia = cdTipoVia;
	}
	
	/**
	 * Obtiene el ds tipo via.
	 *
	 * @return el ds tipo via
	 */
	public String getDsTipoVia() {
		return dsTipoVia;
	}
	
	/**
	 * Establece el ds tipo via.
	 *
	 * @param dsTipoVia el nuevo ds tipo via
	 */
	public void setDsTipoVia(String dsTipoVia) {
		this.dsTipoVia = dsTipoVia;
	}
	
	/**
	 * Obtiene el ds nombre via.
	 *
	 * @return el ds nombre via
	 */
	public String getDsNombreVia() {
		return dsNombreVia;
	}
	
	/**
	 * Establece el ds nombre via.
	 *
	 * @param dsNombreVia el nuevo ds nombre via
	 */
	public void setDsNombreVia(String dsNombreVia) {
		this.dsNombreVia = dsNombreVia;
	}
	
	/**
	 * Obtiene el ds num via.
	 *
	 * @return el ds num via
	 */
	public String getDsNumVia() {
		return dsNumVia;
	}
	
	/**
	 * Establece el ds num via.
	 *
	 * @param dsNumVia el nuevo ds num via
	 */
	public void setDsNumVia(String dsNumVia) {
		this.dsNumVia = dsNumVia;
	}
	
	/**
	 * Obtiene el ds bloque.
	 *
	 * @return el ds bloque
	 */
	public String getDsBloque() {
		return dsBloque;
	}
	
	/**
	 * Establece el ds bloque.
	 *
	 * @param dsBloque el nuevo ds bloque
	 */
	public void setDsBloque(String dsBloque) {
		this.dsBloque = dsBloque;
	}
	
	/**
	 * Obtiene el ds escalera.
	 *
	 * @return el ds escalera
	 */
	public String getDsEscalera() {
		return dsEscalera;
	}
	
	/**
	 * Establece el ds escalera.
	 *
	 * @param dsEscalera el nuevo ds escalera
	 */
	public void setDsEscalera(String dsEscalera) {
		this.dsEscalera = dsEscalera;
	}
	
	/**
	 * Obtiene el ds piso.
	 *
	 * @return el ds piso
	 */
	public String getDsPiso() {
		return dsPiso;
	}
	
	/**
	 * Establece el ds piso.
	 *
	 * @param dsPiso el nuevo ds piso
	 */
	public void setDsPiso(String dsPiso) {
		this.dsPiso = dsPiso;
	}
	
	/**
	 * Obtiene el ds puerta.
	 *
	 * @return el ds puerta
	 */
	public String getDsPuerta() {
		return dsPuerta;
	}
	
	/**
	 * Establece el ds puerta.
	 *
	 * @param dsPuerta el nuevo ds puerta
	 */
	public void setDsPuerta(String dsPuerta) {
		this.dsPuerta = dsPuerta;
	}
	
	/**
	 * Obtiene el ds codigo postal.
	 *
	 * @return el ds codigo postal
	 */
	public String getDsCodigoPostal() {
		return dsCodigoPostal;
	}
	
	/**
	 * Establece el ds codigo postal.
	 *
	 * @param dsCodigoPostal el nuevo ds codigo postal
	 */
	public void setDsCodigoPostal(String dsCodigoPostal) {
		this.dsCodigoPostal = dsCodigoPostal;
	}	
	
	/**
	 * Obtiene el ds correo electronico.
	 *
	 * @return el ds correo electronico
	 */
	public String getDsCorreoElectronico() {
		return dsCorreoElectronico;
	}
	
	/**
	 * Establece el ds correo electronico.
	 *
	 * @param dsCorreoElectronico el nuevo ds correo electronico
	 */
	public void setDsCorreoElectronico(String dsCorreoElectronico) {
		this.dsCorreoElectronico = dsCorreoElectronico;
	}
	
	/**
	 * Obtiene el ds telefono movil.
	 *
	 * @return el ds telefono movil
	 */
	public String getDsTelefonoMovil() {
		return dsTelefonoMovil;
	}
	
	/**
	 * Establece el ds telefono movil.
	 *
	 * @param dsTelefonoMovil el nuevo ds telefono movil
	 */
	public void setDsTelefonoMovil(String dsTelefonoMovil) {
		this.dsTelefonoMovil = dsTelefonoMovil;
	}
	
	/**
	 * Comprueba si es b alerta telefono.
	 *
	 * @return verdadero, si es b alerta telefono
	 */
	public boolean isBAlertaTelefono() {
		return bAlertaTelefono;
	}
	
	/**
	 * Establece el b alerta telefono.
	 *
	 * @param alertaTelefono el nuevo b alerta telefono
	 */
	public void setBAlertaTelefono(boolean alertaTelefono) {
		bAlertaTelefono = alertaTelefono;
	}
	
	/**
	 * Comprueba si es b alerta email.
	 *
	 * @return verdadero, si es b alerta email
	 */
	public boolean isBAlertaEmail() {
		return bAlertaEmail;
	}
	
	/**
	 * Establece el b alerta email.
	 *
	 * @param alertaEmail el nuevo b alerta email
	 */
	public void setBAlertaEmail(boolean alertaEmail) {
		bAlertaEmail = alertaEmail;
	}
	
	/**
	 * Comprueba si es es persona fisica.
	 *
	 * @return verdadero, si es es persona fisica
	 */
	public boolean isEsPersonaFisica() {
		return esPersonaFisica;
	}
	
	/**
	 * Establece el es persona fisica.
	 *
	 * @param esPersonaFisica el nuevo es persona fisica
	 */
	public void setEsPersonaFisica(boolean esPersonaFisica) {
		this.esPersonaFisica = esPersonaFisica;
	}
    
   
	
}
