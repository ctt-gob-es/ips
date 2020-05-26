package es.map.ipsg.bean;

import java.util.List;

/**
 * El Class DetalleRegistroPagoBean.
 */
public class DetalleRegistroPagoBean {
	
	/** el pagos. */
	List<PagoSolicitudBean> pagos;
	
	/** el registros. */
	List<RegistroSolicitudBean> registros;
	
	/**
	 * Obtiene el pagos.
	 *
	 * @return el pagos
	 */
	public List<PagoSolicitudBean> getPagos() {
		return pagos;
	}
	
	/**
	 * Establece el pagos.
	 *
	 * @param pagos el nuevo pagos
	 */
	public void setPagos(List<PagoSolicitudBean> pagos) {
		this.pagos = pagos;
	}
	
	/**
	 * Obtiene el registros.
	 *
	 * @return el registros
	 */
	public List<RegistroSolicitudBean> getRegistros() {
		return registros;
	}
	
	/**
	 * Establece el registros.
	 *
	 * @param registros el nuevo registros
	 */
	public void setRegistros(List<RegistroSolicitudBean> registros) {
		this.registros = registros;
	}
	
	
}
