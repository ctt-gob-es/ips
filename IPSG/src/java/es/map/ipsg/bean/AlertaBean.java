package es.map.ipsg.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import es.map.ips.model.Usuario;

/**
 * El Class AlertaBean.
 */
public class AlertaBean {
	
	/** el id. */
	private Integer id;
	
	/** el id tipo. */
	private Integer idTipo;
	
	/** el id modo. */
	private Integer idModo;
	
	/** el id centro gestor. */
	private Integer idCentroGestor;
	
	/** el id perfil. */
	private Integer idPerfil;
	
	/** el estado. */
	private Character estado;
	
	/** el id peticion. */
	private String idPeticion; 
	
	/** el num total. */
	private Integer numTotal;
	
	/** el des tipo alerta. */
	private String desTipoAlerta;
	
	/** el des modo alerta. */
	private String desModoAlerta;
	
	/** el codigo centro gestor. */
	private String codigoCentroGestor;
	
	/** el des centro gestor. */
	private String desCentroGestor;
	
	/** el des tipo usuario. */
	private String desTipoUsuario;
	
	/** el submit. */
	private Integer submit;
	
	/** el bcc. */
	private InternetAddress [] bcc = new InternetAddress[0];
	
	/** el usuarios. */
	private Set<Usuario> usuarios=new HashSet<Usuario>();
	
	/** el usuarios list. */
	private List<String> usuariosList=new ArrayList();
	
	
	
	/**
	 * Obtiene el des centro gestor.
	 *
	 * @return el des centro gestor
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}
	
	/**
	 * Establece el des centro gestor.
	 *
	 * @param desCentroGestor el nuevo des centro gestor
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}
	
	/**
	 * Método get de  id.
	 *
	 * @return id Integer
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el valor de id.
	 *
	 * @param id Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Método get de  idTipo.
	 *
	 * @return idTipo Integer
	 */
	public Integer getIdTipo() {
		return idTipo;
	}
	
	/**
	 * Establece el valor de idTipo.
	 *
	 * @param idTipo Integer
	 */
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	
	/**
	 * Método get de  idModo.
	 *
	 * @return idModo Integer
	 */
	public Integer getIdModo() {
		return idModo;
	}
	
	/**
	 * Establece el valor de.
	 *
	 * @param idModo Integer
	 */ 
	public void setIdModo(Integer idModo) {
		this.idModo = idModo;
	}
	
	/**
	 *  
	 * Método get de  idCentroGestor.
	 *
	 * @return idCentroGestor Integer
	 */
	public Integer getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el valor de idCentroGestor.
	 *
	 * @param idCentroGestor Integer
	 */
	public void setIdCentroGestor(Integer idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Método get de  idPerfil.
	 *
	 * @return idPerfil Integer
	 */
	public Integer getIdPerfil() {
		return idPerfil;
	}
	
	/**
	 * Establece el valor de.
	 *
	 * @param idPerfil Integer
	 */
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	/**
	 * Método get de estado A / D.
	 *
	 * @return estado
	 */
	public Character getEstado() {
		return estado;
	}
	
	/**
	 * Establece el valor de estado A / D.
	 *
	 * @param estado Character
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}
	
    /**
     * Obtiene el id peticion.
     *
     * @return el id peticion
     */
    public String getIdPeticion() {
        return this.idPeticion;
    }
    
    /**
     * Establece el id peticion.
     *
     * @param idPeticion el nuevo id peticion
     */
    public void setIdPeticion(String idPeticion) {
        this.idPeticion = idPeticion;
    }
	
	/**
	 * Obtiene el num total.
	 *
	 * @return the numTotal
	 */
	public Integer getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal the numTotal to set
	 */
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
	
	/**
	 * Obtiene el des tipo alerta.
	 *
	 * @return the desTipoAlerta
	 */
	public String getDesTipoAlerta() {
		return desTipoAlerta;
	}
	
	/**
	 * Establece el des tipo alerta.
	 *
	 * @param desTipoAlerta the desTipoAlerta to set
	 */
	public void setDesTipoAlerta(String desTipoAlerta) {
		this.desTipoAlerta = desTipoAlerta;
	}
	
	/**
	 * Obtiene el codigo centro gestor.
	 *
	 * @return the codigoCentroGestor
	 */
	public String getCodigoCentroGestor() {
		return codigoCentroGestor;
	}
	
	/**
	 * Establece el codigo centro gestor.
	 *
	 * @param codigoCentroGestor the codigoCentroGestor to set
	 */
	public void setCodigoCentroGestor(String codigoCentroGestor) {
		this.codigoCentroGestor = codigoCentroGestor;
	}
	
	/**
	 * Obtiene el des tipo usuario.
	 *
	 * @return the desTipoUsuario
	 */
	public String getDesTipoUsuario() {
		return desTipoUsuario;
	}
	
	/**
	 * Establece el des tipo usuario.
	 *
	 * @param desTipoUsuario the desTipoUsuario to set
	 */
	public void setDesTipoUsuario(String desTipoUsuario) {
		this.desTipoUsuario = desTipoUsuario;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return the submit
	 */
	public Integer getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el submit.
	 *
	 * @param submit the submit to set
	 */
	public void setSubmit(Integer submit) {
		this.submit = submit;
	}
	
	/**
	 * Obtiene el des modo alerta.
	 *
	 * @return the desModoAlerta
	 */
	public String getDesModoAlerta() {
		return desModoAlerta;
	}
	
	/**
	 * Establece el des modo alerta.
	 *
	 * @param desModoAlerta the desModoAlerta to set
	 */
	public void setDesModoAlerta(String desModoAlerta) {
		this.desModoAlerta = desModoAlerta;
	}
	
	/**
	 * Obtiene el usuarios.
	 *
	 * @return the usuarios
	 */
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	
	/**
	 * Establece el usuarios.
	 *
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	/**
	 * Obtiene el usuarios list.
	 *
	 * @return the usuariosList
	 */
	/**
	 * @return the usuariosList
	 */
	public List<String> getUsuariosList() {
		return usuariosList;
	}
	
	/**
	 * Establece el usuarios list.
	 *
	 * @param usuariosList the usuariosList to set
	 */
	public void setUsuariosList(List<String> usuariosList) {
		this.usuariosList = usuariosList;
	}
	
	/**
	 * Obtiene el bcc.
	 *
	 * @return the bcc
	 */
	public InternetAddress[] getBcc() {
		return bcc;
	}
	
	/**
	 * Establece el bcc.
	 *
	 * @param bcc the bcc to set
	 */
	public void setBcc(InternetAddress[] bcc) {
		this.bcc = bcc;
	}
}
