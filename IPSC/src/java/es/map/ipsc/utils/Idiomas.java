package es.map.ipsc.utils;

/**
 * El Enum Idiomas.
 */
public enum Idiomas {
	
	/** el castellano. */
	CASTELLANO("Catellano"), /** el catalan. */
 CATALAN("Catalan"), /** el euskera. */
 EUSKERA("Euskera"), /** el gallego. */
 GALLEGO("Gallego"), /** el valenciano. */
 VALENCIANO("Valenciano");

	/** el id. */
	String id;

	/**
	 * Crea una nueva idiomas.
	 *
	 * @param id el id
	 */
	private Idiomas(String id) {
		this.id = id;
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
	
	
	
	
}
