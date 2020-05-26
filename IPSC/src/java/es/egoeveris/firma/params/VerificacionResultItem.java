package es.egoeveris.firma.params;

import java.io.Serializable;
import java.util.Date;

/**
 * El Class VerificacionResultItem.
 */
public class VerificacionResultItem implements Serializable{
		
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = -6169473174215437559L;
	
	/** el level. */
	private int level;
	
	/** el estado verifica. */
	private String estadoVerifica;
	
	/** el error. */
	private boolean error;	
	
	/** el subject. */
	private String subject;
	
	/** el issuer. */
	private String issuer;
	
	/** el full name. */
	private String fullName;
	
	/** el sign date. */
	private Date signDate;
	
	/** el serial number. */
	private String serialNumber;
	
	/** el nombre. */
	private String nombre;
	
	/** el apellidos. */
	private String apellidos;
	
	/** el num documentacion. */
	private String numDocumentacion;
	
	/** el razon social. */
	private String razonSocial;
	
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
	 * Obtiene el apellidos.
	 *
	 * @return el apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece el apellidos.
	 *
	 * @param apellidos el nuevo apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Obtiene el num documentacion.
	 *
	 * @return el num documentacion
	 */
	public String getNumDocumentacion() {
		return numDocumentacion;
	}

	/**
	 * Establece el num documentacion.
	 *
	 * @param numDocumentacion el nuevo num documentacion
	 */
	public void setNumDocumentacion(String numDocumentacion) {
		this.numDocumentacion = numDocumentacion;
	}

	/**
	 * Crea una nueva verificacion result item.
	 */
	public VerificacionResultItem(){
		super();
	}
	
	/**
	 * Crea una nueva verificacion result item.
	 *
	 * @param error el error
	 * @param level el level
	 * @param estadoVerifica el estado verifica
	 */
	public VerificacionResultItem(boolean error, int level, String estadoVerifica){
		this.error = error;
		this.level = level;
		this.estadoVerifica = estadoVerifica;
	}

	/**
	 * Obtiene el estado verifica.
	 *
	 * @return el estado verifica
	 */
	public String getEstadoVerifica() {
		return estadoVerifica;
	}

	/**
	 * Obtiene el level.
	 *
	 * @return el level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Obtiene el error.
	 *
	 * @return el error
	 */
	public boolean getError(){
		return error;
	}

	/**
	 * Obtiene el issuer.
	 *
	 * @return el issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * Establece el issuer.
	 *
	 * @param issuer el nuevo issuer
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * Obtiene el subject.
	 *
	 * @return el subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Establece el subject.
	 *
	 * @param subject el nuevo subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Obtiene el serial number.
	 *
	 * @return el serial number
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Establece el serial number.
	 *
	 * @param serialNumber el nuevo serial number
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Obtiene el full name.
	 *
	 * @return el full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Establece el full name.
	 *
	 * @param fullName el nuevo full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Obtiene el sign date.
	 *
	 * @return el sign date
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * Establece el sign date.
	 *
	 * @param signDate el nuevo sign date
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/**
	 * Establece el error.
	 *
	 * @param error el nuevo error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Establece el estado verifica.
	 *
	 * @param estadoVerifica el nuevo estado verifica
	 */
	public void setEstadoVerifica(String estadoVerifica) {
		this.estadoVerifica = estadoVerifica;
	}

	/**
	 * Establece el level.
	 *
	 * @param level el nuevo level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Obtiene el razon social.
	 *
	 * @return el razon social
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Establece el razon social.
	 *
	 * @param razonSocial el nuevo razon social
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	
}	
