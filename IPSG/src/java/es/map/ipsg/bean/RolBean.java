package es.map.ipsg.bean;

/**
 * El Class RolBean.
 */
public class RolBean {
	
	/** el id. */
	private Integer id;
    
    /** el login. */
    private String login;
    
    /** el authority. */
    private String authority;
    
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * Obtiene el authority.
	 *
	 * @return el authority
	 */
	public String getAuthority() {
		return authority;
	}
	
	/**
	 * Establece el authority.
	 *
	 * @param authority el nuevo authority
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
