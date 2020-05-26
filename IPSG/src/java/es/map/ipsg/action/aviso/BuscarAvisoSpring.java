package es.map.ipsg.action.aviso;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.AvisoQuery;
import es.map.ips.model.EstadoAvisoQuery;
import es.map.ipsg.bean.AvisoBean;
import es.map.ipsg.bean.EstadoAvisoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AvisoForm;
import es.map.ipsg.manager.AvisoManager;
import es.map.ipsg.manager.EstadoAvisoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarAvisoSpring.
 */
public class BuscarAvisoSpring extends AbstractSpring {
	
	/** el aviso manager. */
	private AvisoManager avisoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el estado aviso manager. */
	private EstadoAvisoManager estadoAvisoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarAvisoSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ID_ESTADO_AVISO. */
	private static final String STRING_ID_ESTADO_AVISO = "idEstadoAviso";
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/**
	 * Crea una nueva buscar aviso spring.
	 */
	public BuscarAvisoSpring() {
		try {
			setAvisoManager((AvisoManager) getBean("avisoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setEstadoAvisoManager((EstadoAvisoManager) getBean("estadoAvisoManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarAvisoSpring():", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.avisos");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_aviso = RESOURCE_BUNDLE.getString("field.menuLateral.avisos.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_aviso);
		//****************************************************************** 
		logger.info("BuscarAvisosSpring -start");
	try{
		String campo = null;
		String direccion = null;
		
		String numReg = null;
		
		AvisoForm formulario;
		
		formulario = (AvisoForm) form;
		
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		String cambios = this.getRequest().getParameter("cambios");
		if(cambios == null || "".equals(cambios)){
			cambios="Correcto";
			formulario.setCambios(cambios);
		}
		if(this.getRequest().getParameter(STRING_ID_ESTADO_AVISO)!=null){
			formulario.setIdEstadoAviso(Integer.valueOf(this.getRequest().getParameter(STRING_ID_ESTADO_AVISO)));	
		}
		
		
		numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: "+numReg);
		String menu=this.getRequest().getParameter("menu");
		
		String busqueda = formulario.getSubmit();
		
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
		}
		if(menu!=null && menu.equals("N")){
			
				busqueda="Paginar";	
			
		}
		
		if(formulario.isPulsaIr() == true)
		{
			paginaActual = formulario.getNumeroPaginaIr().toString();
			logger.info("PaginaActualIr: "+ paginaActual);
			formulario.setPulsaIr(false);
			formulario.setNumeroPaginaIr(null);
		}
		else
		{
			formulario.setNumeroPaginaIr(null);
			formulario.setPulsaIr(false);
		}
		
		if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
			int pagActu = Integer.parseInt(paginaActual);		
			int pagTotales = Integer.parseInt(paginaTotal);

			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagTotales);
			}
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
		
		//Rellenamos el combo de estados.
		EstadoAvisoQuery estadoAvisoQuery= new EstadoAvisoQuery();
		
		estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_ACTIVO);
		estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_INACTIVO);
		estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_PUBLICADO);
		ArrayList <EstadoAvisoBean> estadosList;
		
		estadosList=estadoAvisoManager.buscarEstadoAvisoCombo(estadoAvisoQuery);
		
		//Creamos la consulta
		AvisoQuery avisoQuery;
		
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		if(formulario.getCampo()!= null){
			try{
				if (formulario.getCampo()==null ||formulario.getCampo().equals("")){
					campo=cargarCampos("1");
					direccion="up";
				}else{
					campo=cargarCampos(formulario.getCampo());
					direccion=formulario.getDireccion();
				}
			}catch(Exception e){
				logger.error("Error al seleccionar un campo de ordenación" + campo, e);
				formulario.setCampo(null);
			}			
		}
		
		avisoQuery=crearQueryAviso(formulario,busqueda,numReg,paginaActual,campo,direccion);
		List<AvisoBean> avisos = null;
		if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
			avisos = avisoManager.buscarAvisoAll(avisoQuery);
			//Error sin resultados
			if (avisos != null && avisos.size() == 0) {
				SpringMessages errors = new SpringMessages();
				errors.add("errorSinResultados", new SpringMessage("field.cuerpoEscala.error"));
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
			}				
		}
		int pagTotal = 0;
		int numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			numRegistros = comprobarEntero(numReg);
		}else{
			numRegistros = comprobarEntero(formulario.getNumRegistro());
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		//Recorrer la lista, para activar los enlaces
		if(avisos != null && avisos.size()!= 0){
			pagTotal= avisos.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(avisos.size()>numRegistros){
				avisos.remove(avisos.size()-1);
			}			
		}
		
		if (formulario.getIdEstadoAviso()!=null && !"".equals(formulario.getIdEstadoAviso().toString())){
			setRequestAttribute(STRING_ID_ESTADO_AVISO, formulario.getIdEstadoAviso());
		}else{
			
			setRequestAttribute(STRING_ID_ESTADO_AVISO,"");
		}
		
		
		if(paginaActual==null || "".equals(paginaActual))
			paginaActual="1";
		
		setRequestAttribute("estados", estadosList);
		setRequestAttribute("avisos", avisos);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);	
		setRequestAttribute("numRegistro", numRegistros);	
		setRequestAttribute("submit", busqueda);
		setRequestAttribute("cambios", cambios);
		
		logger.info("BuscarAvisosSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error buscarAviso  - doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String sUsernameLogin =  "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin=usuarioBean.getLogin();
			logger.info("sUsernameLogin: "+sUsernameLogin);
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error recuperarUsuarioSesion"+ sUsernameLogin , e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Comprobar entero.
	 *
	 * @param entero el entero
	 * @return el int
	 */
	private int comprobarEntero(String entero) {
		int numero = 0;
		try{
			numero = Integer.parseInt(entero);
		}catch(Exception e){
			logger.error("Error numero de registros: " + numero ,e);
			numero = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return numero;	
	}

	/**
	 * Cargar campos.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCampos(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){
			logger.error("Error BuscarAviso -  codCampo:" + codCampo ,e );
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "texto";break;
						case 3:auxCampo = "estadoAviso";break;
						case 4:auxCampo = "fechaInicio";break;
						case 5:auxCampo = "fechaFin";break;
						default:break;
					}
				
			}
		return auxCampo;
	}
	
	/**
	 * Crear query aviso.
	 *
	 * @param formulario el formulario
	 * @param busqueda el busqueda
	 * @param numReg el num reg
	 * @param paginaActual el pagina actual
	 * @param campo el campo
	 * @param direccion el direccion
	 * @return el aviso query
	 */
	private AvisoQuery crearQueryAviso(AvisoForm formulario, String busqueda, String numReg, String paginaActual, String campo, String direccion) {
		Integer estadoAviso = null;
		
		if (formulario.getIdEstadoAviso()!=null){
			estadoAviso=formulario.getIdEstadoAviso();
		}

		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarAviso -  numero de  registros:" + numRegistros ,e );
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error  Buscaraviso - numero de  registros:" + numRegistros ,e );
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		//Si la pagina actual no esta definida, se establece a 1
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		AvisoQuery avisoQueryAux = new AvisoQuery();
		EstadoAvisoQuery estadoAvisoQuery = new EstadoAvisoQuery();
		
		crearQueryAviso2(estadoAvisoQuery,estadoAviso,avisoQueryAux,campo,direccion);
		
		avisoQueryAux.setCalculateNumResults(true);
		
		if(paginaActual != null){
			avisoQueryAux.setMaxResults(tamanyoPaginacion);
			avisoQueryAux.setFirstResult(primerRegistro);
		}
		return avisoQueryAux;
	}
	
	/**
	 * Crear query aviso 2.
	 *
	 * @param estadoAvisoQuery el estado aviso query
	 * @param estadoAviso el estado aviso
	 * @param avisoQueryAux el aviso query aux
	 * @param campo el campo
	 * @param direccion el direccion
	 */
	private void crearQueryAviso2(EstadoAvisoQuery estadoAvisoQuery, Integer estadoAviso, AvisoQuery avisoQueryAux, String campo, String direccion ) {
		
		if (estadoAviso!=null && !estadoAviso.equals(0)){
			estadoAvisoQuery.setId(Integer.valueOf(estadoAviso));
			
		}else{
			estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_ACTIVO);
			estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_INACTIVO);
			estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_PUBLICADO);
			
		}
		avisoQueryAux.setEstadoAviso(estadoAvisoQuery);
		
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			if("up".equals(direccion)){
				avisoQueryAux.addOrder(campo,OrderType.ASC);
			}else{
				avisoQueryAux.addOrder(campo,OrderType.DESC);
			}
		}
	}

	/**
	 * Obtiene el aviso manager.
	 *
	 * @return the avisoManager
	 */
	public AvisoManager getAvisoManager() {
		return avisoManager;
	}

	/**
	 * Establece el aviso manager.
	 *
	 * @param avisoManager the avisoManager to set
	 */
	public void setAvisoManager(AvisoManager avisoManager) {
		this.avisoManager = avisoManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el estado aviso manager.
	 *
	 * @return the estadoAvisoManager
	 */
	public EstadoAvisoManager getEstadoAvisoManager() {
		return estadoAvisoManager;
	}

	/**
	 * Establece el estado aviso manager.
	 *
	 * @param estadoAvisoManager the estadoAvisoManager to set
	 */
	public void setEstadoAvisoManager(EstadoAvisoManager estadoAvisoManager) {
		this.estadoAvisoManager = estadoAvisoManager;
	}
	
	
}
