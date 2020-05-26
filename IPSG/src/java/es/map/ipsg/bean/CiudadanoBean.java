package es.map.ipsg.bean;

	import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Incidencia;

/**
	 * El Class CiudadanoBean.
	 */
	public class CiudadanoBean {
		
		/** el id. */
		private Long id;
		
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
		
		/** el nombre completo. */
		private String nombreCompleto;
		
		/** el incidencias. */
		private Set<Incidencia> incidencias = new HashSet<Incidencia>(0);
		
		/** el num total. */
		private Integer numTotal;
		
		/**
		 * Obtiene el id.
		 *
		 * @return the id
		 */
		public Long getId() {
			return id;
		}
		
		/**
		 * Establece el id.
		 *
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}
		
		/**
		 * Obtiene el nif.
		 *
		 * @return the nif
		 */
		public String getNif() {
			return nif;
		}
		
		/**
		 * Establece el nif.
		 *
		 * @param nif the nif to set
		 */
		public void setNif(String nif) {
			this.nif = nif;
		}
		
		/**
		 * Obtiene el nombre.
		 *
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}
		
		/**
		 * Establece el nombre.
		 *
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		/**
		 * Obtiene el primer apellido.
		 *
		 * @return the primerApellido
		 */
		public String getPrimerApellido() {
			return primerApellido;
		}
		
		/**
		 * Establece el primer apellido.
		 *
		 * @param primerApellido the primerApellido to set
		 */
		public void setPrimerApellido(String primerApellido) {
			this.primerApellido = primerApellido;
		}
		
		/**
		 * Obtiene el segundo apellido.
		 *
		 * @return the segundoApellido
		 */
		public String getSegundoApellido() {
			return segundoApellido;
		}
		
		/**
		 * Establece el segundo apellido.
		 *
		 * @param segundoApellido the segundoApellido to set
		 */
		public void setSegundoApellido(String segundoApellido) {
			this.segundoApellido = segundoApellido;
		}
		
		/**
		 * Obtiene el telefono.
		 *
		 * @return the telefono
		 */
		public String getTelefono() {
			return telefono;
		}
		
		/**
		 * Establece el telefono.
		 *
		 * @param telefono the telefono to set
		 */
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		
		/**
		 * Obtiene el email.
		 *
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}
		
		/**
		 * Establece el email.
		 *
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		
		/**
		 * Obtiene el nombre completo.
		 *
		 * @return the nombreCompleto
		 */
		public String getNombreCompleto() {
			return nombreCompleto;
		}
		
		/**
		 * Establece el nombre completo.
		 *
		 * @param nombreCompleto the nombreCompleto to set
		 */
		public void setNombreCompleto(String nombreCompleto) {
			this.nombreCompleto = nombreCompleto;
		}
		
		/**
		 * Obtiene el incidencias.
		 *
		 * @return the incidencias
		 */
		public Set<Incidencia> getIncidencias() {
			return incidencias;
		}
		
		/**
		 * Establece el incidencias.
		 *
		 * @param incidencias the incidencias to set
		 */
		public void setIncidencias(Set<Incidencia> incidencias) {
			this.incidencias = incidencias;
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
		
		
	}
