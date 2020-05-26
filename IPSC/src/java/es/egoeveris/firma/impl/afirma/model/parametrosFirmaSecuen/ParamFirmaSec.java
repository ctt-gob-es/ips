package es.egoeveris.firma.impl.afirma.model.parametrosFirmaSecuen;

import java.io.Serializable;


/**
 * El Class ParamFirmaSec.
 */
public class ParamFirmaSec implements Serializable {

		/** La constante serialVersionUID. */
	private static final long serialVersionUID = -4438397990170166635L;

		/** el id aplicacion. */
		private String idAplicacion;
		
		/** el id transaccion. */
		private String idTransaccion;
		
		/** el firmante. */
		private String firmante;
		
		/** el id referencia. */
		private String idReferencia;
		
		/** el algoritmo hash. */
		private String algoritmoHash;
		
		/** el firmante objetivo. */
		private String firmanteObjetivo;

	
		/**
		 * Obtiene el id referencia.
		 *
		 * @return the idReferencia
		 */
		public String getIdReferencia() {
			return idReferencia;
		}

		/**
		 * Establece el id referencia.
		 *
		 * @param idReferencia the idReferencia to set
		 */
		public void setIdReferencia(String idReferencia) {
			this.idReferencia = idReferencia;
		}

		/**
		 * Obtiene el id aplicacion.
		 *
		 * @return the idAplicacion
		 */
		public String getIdAplicacion() {
			return idAplicacion;
		}

		/**
		 * Establece el id aplicacion.
		 *
		 * @param idAplicacion the idAplicacion to set
		 */
		public void setIdAplicacion(String idAplicacion) {
			this.idAplicacion = idAplicacion;
		}

		/**
		 * Obtiene el algoritmo hash.
		 *
		 * @return the algoritmoHash
		 */
		public String getAlgoritmoHash() {
			return algoritmoHash;
		}

		/**
		 * Establece el algoritmo hash.
		 *
		 * @param algoritmoHash the algoritmoHash to set
		 */
		public void setAlgoritmoHash(String algoritmoHash) {
			this.algoritmoHash = algoritmoHash;
		}

		/**
		 * Obtiene el firmante.
		 *
		 * @return the firmante
		 */
		public String getFirmante() {
			return firmante;
		}

		/**
		 * Establece el firmante.
		 *
		 * @param firmante the firmante to set
		 */
		public void setFirmante(String firmante) {
			this.firmante = firmante;
		}

		/**
		 * Obtiene el id transaccion.
		 *
		 * @return the idTransaccion
		 */
		public String getIdTransaccion() {
			return idTransaccion;
		}

		/**
		 * Establece el id transaccion.
		 *
		 * @param idTransaccion the idTransaccion to set
		 */
		public void setIdTransaccion(String idTransaccion) {
			this.idTransaccion = idTransaccion;
		}

		/**
		 * Obtiene el firmante objetivo.
		 *
		 * @return the firmanteObjetivo
		 */
		public String getFirmanteObjetivo() {
			return firmanteObjetivo;
		}

		/**
		 * Establece el firmante objetivo.
		 *
		 * @param firmanteObjetivo the firmanteObjetivo to set
		 */
		public void setFirmanteObjetivo(String firmanteObjetivo) {
			this.firmanteObjetivo = firmanteObjetivo;
		}

	}
