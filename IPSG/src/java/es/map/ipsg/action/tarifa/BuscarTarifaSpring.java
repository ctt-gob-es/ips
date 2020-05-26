package es.map.ipsg.action.tarifa;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.TarifaQuery;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.TarifaBean;
import es.map.ipsg.bean.TipoAccesoBean;
import es.map.ipsg.form.TarifaForm;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarTarifaSpring.
 */
public class BuscarTarifaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarTarifaSpring.class);

	/** el usuario manager. */
	//Declaracion de manager
	private UsuarioManager usuarioManager;
	
	/** el tarifa manager. */
	private TarifaManager tarifaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el tipo acceso. */
	private TipoAccesoManager tipoAcceso;
	
	/** el num reg. */
	//Declaracion de variables
	private String  ejercicio,  descripcion, grupo, paginaActual,campo,direccion,numRegistros,estado,numReg;
	
	
	/**
	 * Crea una nueva buscar tarifa spring.
	 */
	public BuscarTarifaSpring() {
		try {
			
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setTarifaManager((TarifaManager) getBean("tarifaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarTarifaSpring- crear constructor",e);
		}
		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarCuerposEscalaSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_cuerposEscala = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_cuerposEscala);
		String subMenu_tarifa = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.tarifa");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tarifa);
		//****************************************************************** 
	try{
		//Cojo el lugar de la llamada
		
		//Coger el form del jsp
		TarifaForm formulario;
		formulario = (TarifaForm) form;
		String busqueda = this.getRequest().getParameter("submit");
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
		}
		numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: "+numReg);
		String cambios = this.getRequest().getParameter("cambios");
		
		//Comprobar si viene del menu
		String menu = this.getRequest().getParameter("menu");
		if(menu != null && menu.equals("S")){
			formulario.setDescripcion("");
			busqueda = "";
		}
		
		//Creamos la Query
		TarifaQuery tarifaQuery;	
		int numTotal=1;
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		if(StringUtils.isNotEmpty(formulario.getCampo()) && !"Paginar".equals(busqueda)){
			try{
				
				String campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);
			}catch(Exception e){
				logger.error("Error BuscarTarifaSpring - campo"+ formulario.getCampo(),e);
			}			
		}
		//Llama a la funcion para crear la Query pasandole el formulario
		
		paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		
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
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
		}
		tarifaQuery=crearQueryTarifa(formulario);
			
		
		//Coger la lista de convocatorias a mostrar
	
		List<TarifaBean> lTarifa = null;
		if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
			lTarifa = tarifaManager.buscarTarifaAllNumTotal(tarifaQuery);
			//Error sin resultados
			if (lTarifa != null && lTarifa.size() == 0) {
				SpringMessages errors = new SpringMessages();
				errors.add("errorSinResultados", new SpringMessage("field.cuerpoEscala.error"));
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
			}			
			cambios = "Correcto";
		}
		int pagTotal = 0, numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarTarifaSpring- numRegistros"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarTarifaSpring- numRegistros"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
		if(lTarifa != null && lTarifa.size()!= 0){
			pagTotal= lTarifa.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(lTarifa.size()>numRegistros){
				lTarifa.remove(lTarifa.size()-1);
			}
			
		}
		
		//Cargamos los combos
		cargarCombosFormulario();
		//Pasar la convocatoria y la pagina al jsp
		setRequestAttribute("tarifa", lTarifa);
		
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);
		setRequestAttribute("cambios", cambios);
		getLogger().debug("BuscarTarifaSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarTarifaSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		
		return "success";
	}

/**
 * Cargar combos formulario.
 */
public void cargarCombosFormulario() {
		
		GrupoQuery  grupoQuery = new GrupoQuery();
		List<GrupoBean> lGrupoBean;
		lGrupoBean = grupoManager.buscarGrupoCombo(grupoQuery);
	
		TipoAccesoQuery tipoAccesoQuery = new TipoAccesoQuery();
		List<TipoAccesoBean> lTipoAccesoBean;
		lTipoAccesoBean = 	tipoAcceso.buscarTipoAccesoCombo(tipoAccesoQuery);
				
		setRequestAttribute("grupo", lGrupoBean);
		setRequestAttribute("tipoAcceso", lTipoAccesoBean);
		
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
			logger.error("Error BuscarTarifaSpring  -cargarCampos - campo"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "grupo";break;
						case 3:auxCampo = "tipoAcceso";break;
						case 4:auxCampo = "descripcion";break;
						case 5:auxCampo = "importe";break;
						case 6:auxCampo = "ejercicio";break;	
						default:break;
					}
			
			}
			return auxCampo;
			}
			
	

	/**
	 * Crear query tarifa.
	 *
	 * @param formulario el formulario
	 * @return el tarifa query
	 */
	private TarifaQuery crearQueryTarifa(TarifaForm formulario) {
		//Recoger los datos del formulario
		
		Integer idGrupo=0;
		Integer idTipoAcceso=0; 
		
		descripcion = formulario.getDescripcion();
		ejercicio=formulario.getEjercicio();
		if(!"".equals(formulario.getIdGrupo()) && formulario.getIdGrupo()!=null){
		idGrupo=Integer.parseInt(formulario.getIdGrupo());
		}
		if (formulario.getIdTipoAcceso()!=""){
			idTipoAcceso=Integer.valueOf(formulario.getIdTipoAcceso());
		}
		campo = formulario.getCampo();
		direccion = formulario.getDireccion();
		
		
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarTarifaSpring - crearQueryTarifa - numeroRegistros"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarTarifaSpring  - crearQueryTarifa - numeroRegistros"+ numRegistros,e);
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
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		TarifaQuery tarifaQuery = new TarifaQuery();
		GrupoQuery grupoQuery= new GrupoQuery();
		TipoAccesoQuery tipoAccesoQuery= new TipoAccesoQuery();
		
		
		if(paginaActual != null){
			tarifaQuery.setMaxResults(tamanyoPaginacion);
			tarifaQuery.setFirstResult(primerRegistro);
		}
		tarifaQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(descripcion!= null  && !"".equals(descripcion)){
			try	{
				tarifaQuery.setDescripcion(descripcion);
				tarifaQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);		
			}catch(Exception e){
				logger.error("Error BuscarTarifaSpring - crearQueryTarifa - descripcion",e);
			}
		}
		estado= RESOURCE_MESSAGE.getString("field.estado.activo");						
		tarifaQuery.setEstado(estado.charAt(0));
		
		
		if(ejercicio!= null  && !"".equals(ejercicio)){
			try	{
				tarifaQuery.setEjercicio(ejercicio);
				}catch(Exception e){
					logger.error("Error BuscarTarifaSpring - crearQueryTarifa - ejercicio",e);
				}
		}
		
		if(idGrupo!= null && idGrupo!=0 && !"".equals(idGrupo)){
			try	{
				grupoQuery.setId(idGrupo);
				tarifaQuery.setGrupo(grupoQuery);
				}catch(Exception e){
					logger.error("Error BuscarTarifaSpring - crearQueryTarifa - grupo",e);
				}
		}
		
		if(idTipoAcceso!=0 && idTipoAcceso!=null  && !"".equals(idTipoAcceso)){
			try	{
				tipoAccesoQuery.setId(idTipoAcceso);
				tarifaQuery.setTipoAcceso(tipoAccesoQuery);
				}catch(Exception e){
					logger.error("Error BuscarTarifaSpring crearQueryTarifa - tipoAcceso",e);
				}
		}
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			
			OrderType orden=("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if(campo.equals("grupo")) {
				grupoQuery.addOrder("descripcion",orden);
				tarifaQuery.setGrupo(grupoQuery);
			}
			else {
				tarifaQuery.addOrder(campo,orden);
			}
			
		}
		
		//Devulelve la convocatoria con los filtros
		return tarifaQuery;
		
	}
	
	/**
	 * Obtiene el usuario manager.
	 *
	 * @return usuarioManager UsuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager UsuarioManager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el tarifa manager.
	 *
	 * @return the tarifaManager
	 */
	public TarifaManager getTarifaManager() {
		return tarifaManager;
	}

	/**
	 * Obtiene el grupo manager.
	 *
	 * @return the grupoManager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Obtiene el tipo acceso.
	 *
	 * @return the tipoAcceso
	 */
	public TipoAccesoManager getTipoAcceso() {
		return tipoAcceso;
	}

	/**
	 * Establece el tarifa manager.
	 *
	 * @param tarifaManager the tarifaManager to set
	 */
	public void setTarifaManager(TarifaManager tarifaManager) {
		this.tarifaManager = tarifaManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager the grupoManager to set
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAcceso the tipoAcceso to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	

}
