package es.map.ipsg.bean;

/**
 * El Class IntermediacionDiscapacidadBean.
 */
public class IntermediacionDiscapacidadBean {

		/** el grado discapacidad. */
		private Integer gradoDiscapacidad;
		
		/** el resultado verificacion. */
		private boolean resultadoVerificacion;
		
		/** el descripcion error. */
		private String descripcionError;
		
		
		/**
		 * Obtiene el grado discapacidad.
		 *
		 * @return el grado discapacidad
		 */
		public Integer getGradoDiscapacidad() {
			return gradoDiscapacidad;
		}
		
		/**
		 * Establece el grado discapacidad.
		 *
		 * @param gradoDiscapacidad el nuevo grado discapacidad
		 */
		public void setGradoDiscapacidad(Integer gradoDiscapacidad) {
			this.gradoDiscapacidad = gradoDiscapacidad;
		}
		
		/**
		 * Comprueba si es resultado verificacion.
		 *
		 * @return verdadero, si es resultado verificacion
		 */
		public boolean isResultadoVerificacion() {
			return resultadoVerificacion;
		}
		
		/**
		 * Establece el resultado verificacion.
		 *
		 * @param resultadoVerificacion el nuevo resultado verificacion
		 */
		public void setResultadoVerificacion(boolean resultadoVerificacion) {
			this.resultadoVerificacion = resultadoVerificacion;
		}
		
		/**
		 * Obtiene el descripcion error.
		 *
		 * @return el descripcion error
		 */
		public String getDescripcionError() {
			return descripcionError;
		}
		
		/**
		 * Establece el descripcion error.
		 *
		 * @param descripcionError el nuevo descripcion error
		 */
		public void setDescripcionError(String descripcionError) {
			this.descripcionError = descripcionError;
		}
}
