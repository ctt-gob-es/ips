package es.map.ipsg.action;


import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ValidarUsuarioSpringForm;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.CryptUtil;

/**
 * El Class CompruebaUsuarioSpring.
 */
public class CompruebaUsuarioSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CompruebaUsuarioSpring.class);
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/**
	 * Crea una nueva comprueba usuario spring.
	 */
	public CompruebaUsuarioSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error en  CompruebaUsuario al realizar setUsuarioManager:", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Llega a CompruebaUsuarioAction");
		ValidarUsuarioSpringForm formulario = (ValidarUsuarioSpringForm) form;
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(formulario.getUsuario());
		List<UsuarioBean> usuarioBeanList;
		usuarioBeanList = usuarioManager.buscarUsuarios(usuarioQuery);
		
		if(usuarioBeanList != null && usuarioBeanList.size()>0)
		{	
			UsuarioBean usuarioBean =usuarioBeanList.get(0);
			String token = usuarioBean.getToken();
			logger.info("1.Security-token del usuario: "+token);
			String password = formulario.getPassword();
			if(token != null)
			{	
				String passSalt = password.concat(token);
						
				String passwordMD5 = new CryptUtil().cifrar(passSalt);
				logger.info("2.Security-passwordMD5: "+passwordMD5);
				
				if (usuarioBean.getEstado() == '0') {
					this.getRequest().getSession().setAttribute("login_error", 2);
				} else if (usuarioBean.getLogin().equals(formulario.getUsuario()) && usuarioBean.getPassword().equals(passwordMD5)) {
					this.getRequest().getSession().setAttribute("NIF", usuarioBean.getNif());
					this.getRequest().getSession().setAttribute("LOGIN", usuarioBean.getLogin());
				}
				
				return STRING_SUCCESS;
			}
			else
			{
				logger.info("3.Security-Error Token: "+token);
				String passwordMD5 = new CryptUtil().cifrar(password);
				setRequestAttribute("u",formulario.getUsuario());
				setRequestAttribute("p",passwordMD5);
				this.getRequest().getSession().setAttribute("login_error", 1);
				return STRING_SUCCESS;
			}	
		}
		else
		{
			logger.info("4.Security-Error usuario: "+usuarioBeanList);
			String passwordMD5 = new CryptUtil().cifrar(formulario.getPassword());
			setRequestAttribute("u",formulario.getUsuario());
			setRequestAttribute("p",passwordMD5);
			this.getRequest().getSession().setAttribute("login_error", 1);
			return STRING_SUCCESS;
		}	
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
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}
	
	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
	
}
