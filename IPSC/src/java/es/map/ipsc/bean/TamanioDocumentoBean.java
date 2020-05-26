package es.map.ipsc.bean;


/**
 * El Class TamanioDocumentoBean.
 */
public class TamanioDocumentoBean {
	
	/** el tamanio total. */
	private Long tamanioTotal;
	
	/** el tamanio maximo. */
	private String tamanioMaximo;
	
	
	/**
	 * Obtiene el tamanio total.
	 *
	 * @return el tamanio total
	 */
	public Long getTamanioTotal() {
		return tamanioTotal;
	}
	
	/**
	 * Establece el tamanio total.
	 *
	 * @param tamanioTotal el nuevo tamanio total
	 */
	public void setTamanioTotal(Long tamanioTotal) {
		this.tamanioTotal = tamanioTotal;
	}
	
	/**
	 * Obtiene el tamanio maximo.
	 *
	 * @return el tamanio maximo
	 */
	public String getTamanioMaximo() {
		return tamanioMaximo;
	}
	
	/**
	 * Establece el tamanio maximo.
	 *
	 * @param tamanioMaximo el nuevo tamanio maximo
	 */
	public void setTamanioMaximo(String tamanioMaximo) {
		this.tamanioMaximo = tamanioMaximo;
	}
	
	
	
}
