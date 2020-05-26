package es.map.ipsg.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Validacion;

/**
 * El Class UsuarioForm.
 */
public class UsuarioForm extends SpringForm {

	/** el id. */
	private String id;	
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el ds centro gestor. */
	private String dsCentroGestor;
	
	/** el id perfil. */
	private String idPerfil;
	
	/** el nif. */
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el telefono. */
	private String telefono;
	
	/** el email. */
	private String email;
	
	/** el recibe correos incidencias. */
	private boolean recibeCorreosIncidencias;
	
	/** el publica convocatorias. */
	private boolean publicaConvocatorias;
	
	/** el login. */
	private String login;
	
	/** el password. */
	private String password;
	
	/** el confirmacion password. */
	private String confirmacionPassword;
	
	/** el estado. */
	private String estado;
	
	/** el accion. */
	private String accion;
	
	/** el submit. */
	private String submit;
	
	/** el cambios. */
	private String cambios;
	
	/** el correcto. */
	private String correcto;
	
	/*INI-TRA042*/
	private String idCg;
	
	/** el lista centros gestores. */
	private List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
	/*FIN-TRA042*/
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(UsuarioForm.class);
	
	/** La constante STRING_ERRORPRIMERAPELLIDO. */
	private static final String STRING_ERRORPRIMERAPELLIDO = "errorPrimerApellido";
	
	/** La constante STRING_ERRORTELEFONO. */
	private static final String STRING_ERRORTELEFONO = "errorTelefono";
	
	/** La constante STRING_ERROREMAIL. */
	private static final String STRING_ERROREMAIL = "errorEmail";
	 
	/**
	 * Obtiene el correcto.
	 *
	 * @return el correcto
	 */
	public String getCorrecto() {
		return correcto;
	}
	
	/**
	 * Establece el correcto.
	 *
	 * @param correcto el nuevo correcto
	 */
	public void setCorrecto(String correcto) {
		this.correcto = correcto;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return el submit
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el submit.
	 *
	 * @param submit el nuevo submit
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return el id centro gestor
	 */
	public String getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor el nuevo id centro gestor
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el id perfil.
	 *
	 * @return el id perfil
	 */
	public String getIdPerfil() {
		return idPerfil;
	}
	
	/**
	 * Establece el id perfil.
	 *
	 * @param idPerfil el nuevo id perfil
	 */
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
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
	 * Obtiene el primer apellido.
	 *
	 * @return el primer apellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}
	
	/**
	 * Establece el primer apellido.
	 *
	 * @param primerApellido el nuevo primer apellido
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	/**
	 * Obtiene el segundo apellido.
	 *
	 * @return el segundo apellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	/**
	 * Establece el segundo apellido.
	 *
	 * @param segundoApellido el nuevo segundo apellido
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	/**
	 * Obtiene el telefono.
	 *
	 * @return el telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Establece el telefono.
	 *
	 * @param telefono el nuevo telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Obtiene el email.
	 *
	 * @return el email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Establece el email.
	 *
	 * @param email el nuevo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Obtiene el recibe correos incidencias.
	 *
	 * @return el recibe correos incidencias
	 */
	public boolean getRecibeCorreosIncidencias() {
		return recibeCorreosIncidencias;
	}
	
	/**
	 * Establece el recibe correos incidencias.
	 *
	 * @param recibeCorreosIncidencias el nuevo recibe correos incidencias
	 */
	public void setRecibeCorreosIncidencias(boolean recibeCorreosIncidencias) {
		this.recibeCorreosIncidencias = recibeCorreosIncidencias;
	}
	
	/**
	 * Obtiene el publica convocatorias.
	 *
	 * @return el publica convocatorias
	 */
	public boolean getPublicaConvocatorias() {
		return publicaConvocatorias;
	}
	
	/**
	 * Establece el publica convocatorias.
	 *
	 * @param publicaConvocatorias el nuevo publica convocatorias
	 */
	public void setPublicaConvocatorias(boolean publicaConvocatorias) {
		this.publicaConvocatorias = publicaConvocatorias;
	}
	
	/**
	 * Obtiene el login.
	 *
	 * @return el login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Establece el login.
	 *
	 * @param login el nuevo login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Obtiene el password.
	 *
	 * @return el password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Establece el password.
	 *
	 * @param password el nuevo password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Obtiene el confirmacion password.
	 *
	 * @return el confirmacion password
	 */
	public String getConfirmacionPassword() {
		return confirmacionPassword;
	}
	
	/**
	 * Establece el confirmacion password.
	 *
	 * @param confirmacionPassword el nuevo confirmacion password
	 */
	public void setConfirmacionPassword(String confirmacionPassword) {
		this.confirmacionPassword = confirmacionPassword;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el accion.
	 *
	 * @return el accion
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Establece el accion.
	 *
	 * @param accion el nuevo accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	/**
	 * Obtiene el cambios.
	 *
	 * @return el cambios
	 */
	public String getCambios() {
		return cambios;
	}
	
	/**
	 * Establece el cambios.
	 *
	 * @param cambios el nuevo cambios
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		logger.info("Validando");
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		

		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){
			if(nif == null || nif.equalsIgnoreCase("")){
				request.setAttribute("errorNif", "errorNif");
				SpringErrors.add("dsErrorNif", new SpringMessage("field.usuario.errores.nifRequerido"));
			}
			
			if(nombre == null || nombre.equalsIgnoreCase("")){
				request.setAttribute("errorNombre", "errorNombre");
				SpringErrors.add("dsErrorNombre", new SpringMessage("field.usuario.errores.nombreRequerido"));
			}
			
			if(primerApellido == null || primerApellido.equalsIgnoreCase("")){
				request.setAttribute(STRING_ERRORPRIMERAPELLIDO, STRING_ERRORPRIMERAPELLIDO);
				SpringErrors.add("dsErrorPrimerApellido", new SpringMessage("field.usuario.errores.primerApellidoRequerido"));
			}else{
				if(validacion.validateApellido(primerApellido)){
					request.setAttribute(STRING_ERRORPRIMERAPELLIDO, STRING_ERRORPRIMERAPELLIDO);
					SpringErrors.add("dsErrorPrimerApellido", new SpringMessage("field.usuario.errores.apellidoFormato"));
				}
			}
			
			//segundoApellido no es obligatorio, puede ser vacío
		
			if(validacion.validateApellido(segundoApellido)){
				request.setAttribute("errorSegundoApellido", "errorSegundoApellido");
				SpringErrors.add("dsErrorSegundoApellido", new SpringMessage("field.usuario.errores.apellidoFormato"));
			}
			
			
			if(telefono == null || telefono.equalsIgnoreCase("")){
				request.setAttribute(STRING_ERRORTELEFONO, STRING_ERRORTELEFONO);
				SpringErrors.add("dsErrorTelefono", new SpringMessage("field.usuario.errores.telefonoRequerido"));
			}else{
				if(validacion.validateTelefono(telefono)){
					request.setAttribute(STRING_ERRORTELEFONO, STRING_ERRORTELEFONO);
					SpringErrors.add("dsErrorTelefono", new SpringMessage("field.usuario.errores.telefonoValorNumerico"));
				}
				if(validacion.validateTelefonoFijoMovil(telefono)){
					request.setAttribute(STRING_ERRORTELEFONO, STRING_ERRORTELEFONO);
					SpringErrors.add("dsErrorTelefono", new SpringMessage("field.usuario.errores.telefonoFijoMovil"));
				}
				
			}
			
			if(email == null || email.equalsIgnoreCase("")){
				request.setAttribute(STRING_ERROREMAIL, STRING_ERROREMAIL);
				SpringErrors.add("dsErrorEmail", new SpringMessage("field.usuario.errores.emailRequerido"));
			}else{
				if(validacion.validateEmail(email)){
					request.setAttribute(STRING_ERROREMAIL, STRING_ERROREMAIL);
					SpringErrors.add("dsErrorEmail", new SpringMessage("field.usuario.errores.emailFormato"));
				}
			}
	
			if(idPerfil == null || idPerfil.equalsIgnoreCase("")){
				request.setAttribute("errorPerfil", "errorPerfil");
				SpringErrors.add("dsErrorPerfil", new SpringMessage("field.usuario.errores.perfilRequerido"));
			}
			
			/*INI-TRA042*/
			if(idCg == null || idCg.equals("")){
				if(idPerfil.equals(Constantes.PERFIL_CONSULTOR) ||
					idPerfil.equals(Constantes.PERFIL_GESTOR) ||
					idPerfil.equals(Constantes.PERFIL_RECEPTOR) ||
					idPerfil.equals(Constantes.PERFIL_OPERADOR)){
					//Si el Perfil es Consultor, Gestor, Receptor u Operador debe tener un centro gestor
					request.setAttribute("errorCentro", "errorCentro");
					SpringErrors.add("dsErrorCentro", new SpringMessage("field.usuario.errores.centroGestorRequerido"));
				}
			}
			/*FIN-TRA042*/
			
			if(!"MODIFICAR".equalsIgnoreCase(getAccion()) && !idPerfil.equals("5")){//En modificar usuario no se verifica el login y password
				
					if(login == null || login.equalsIgnoreCase("")){
						request.setAttribute("errorLogin", "errorLogin");
						SpringErrors.add("dsErrorLogin", new SpringMessage("field.usuario.errores.loginRequerido"));
					}
					
					if(password == null || password.equalsIgnoreCase("")){
						request.setAttribute("errorPassword", "errorPassword");
						SpringErrors.add("dsErrorPassword", new SpringMessage("field.usuario.errores.passwordRequerido"));
					}
					
					if(confirmacionPassword == null || confirmacionPassword.equalsIgnoreCase("")){
						request.setAttribute("errorConfirmacionPassword", "errorConfirmacionPassword");
						SpringErrors.add("dsErrorConfirmacionPassword", new SpringMessage("field.usuario.errores.confirmacionPasswordRequerido"));
					}
					if(password == null || confirmacionPassword == null || !password.equals(confirmacionPassword)){
						request.setAttribute("errorCompararPassword", "errorCompararPassword");
						SpringErrors.add("dsErrorCompararPassword", new SpringMessage("field.usuario.errores.repetirPassword"));
					}
				
			}
		}
		
		return SpringErrors;
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * Obtiene el ds centro gestor.
	 *
	 * @return el ds centro gestor
	 */
	public String getDsCentroGestor() {
		return dsCentroGestor;
	}
	
	/**
	 * Establece el ds centro gestor.
	 *
	 * @param dsCentroGestor el nuevo ds centro gestor
	 */
	public void setDsCentroGestor(String dsCentroGestor) {
		this.dsCentroGestor = dsCentroGestor;
	}

	/*INI-TRA042*/
	/**
	 * @return the idCg
	 */
	public String getIdCg() {
		return idCg;
	}

	/**
	 * @param idCg the idCg to set
	 */
	public void setIdCg(String idCg) {
		this.idCg = idCg;
	}

	/**
	 * @return the listaCentrosGestores
	 */
	public List<CentroGestorBean> getListaCentrosGestores() {
		return listaCentrosGestores;
	}

	/**
	 * @param listaCentrosGestores the listaCentrosGestores to set
	 */
	public void setListaCentrosGestores(List<CentroGestorBean> listaCentrosGestores) {
		this.listaCentrosGestores = listaCentrosGestores;
	}
	/*FIN-TRA042*/
}
