/**
 * 
 */
package es.map.ipsg.action.logs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CodigoAccesoQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.LogAccesoQuery;
import es.map.ips.model.LogConvocatoriaQuery;
import es.map.ips.model.LogGenericoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.LogAccesoBean;
import es.map.ipsg.bean.LogConvocatoriaBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.form.LogOperacionesForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogAccesoManager;
import es.map.ipsg.manager.LogConvocatoriaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarLogOperacionesSpring.
 *
 * @author jalonson
 */
public class BuscarLogOperacionesSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarLogOperacionesSpring.class);
	
	/** el log generico manager. */
	//Declaracion de manager
	private LogGenericoManager logGenericoManager;
	
	/** el log convocatoria manager. */
	private LogConvocatoriaManager  logConvocatoriaManager;
	
	/** el log acceso manager. */
	private LogAccesoManager logAccesoManager;
	
	/** el centros gestores manager. */
	private CentroGestorManager  centrosGestoresManager;
	
	/** La constante STRING_BUSCARLOGOPERACIONESSPRING. */
	private static final String STRING_BUSCARLOGOPERACIONESSPRING = "Error BuscarLogOperacionesSpring - campo:";
	
	/** La constante STRING_PAGINAACTUAL. */
	private static final String STRING_PAGINAACTUAL = "paginaActual";
	
	/** La constante STRING_PAGINASTOTALES. */
	private static final String STRING_PAGINASTOTALES = "paginasTotales";
	
	/** La constante STRING_PAGINAACTUALIR. */
	private static final String STRING_PAGINAACTUALIR = "PaginaActualIr: ";
	
	/** La constante STRING_ORDENAR. */
	private static final String STRING_ORDENAR = "Ordenar";
	
	/** La constante STRING_PAGINAR. */
	private static final String STRING_PAGINAR = "Paginar";
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/** La constante STRING_BUSCARLOGOPERACIONESNUMREGISTROS. */
	private static final String STRING_BUSCARLOGOPERACIONESNUMREGISTROS = "Error BuscarLogOperacionesSpring - numRegistros:";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** La constante STRING_FECHA. */
	private static final String STRING_FECHA = "fecha";
	
	/** La constante STRING_USUARIO. */
	private static final String STRING_USUARIO = "usuario";	
	
	
	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}


	/**
	 * Obtiene el log generico manager.
	 *
	 * @return the logGenericoManager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}


	/**
	 * Obtiene el log convocatoria manager.
	 *
	 * @return the logConvocatoriaManager
	 */
	public LogConvocatoriaManager getLogConvocatoriaManager() {
		return logConvocatoriaManager;
	}


	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager the logGenericoManager to set
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}


	/**
	 * Establece el log convocatoria manager.
	 *
	 * @param logConvocatoriaManager the logConvocatoriaManager to set
	 */
	public void setLogConvocatoriaManager(
			LogConvocatoriaManager logConvocatoriaManager) {
		this.logConvocatoriaManager = logConvocatoriaManager;
	}

	
	/**
	 * Obtiene el log acceso manager.
	 *
	 * @return the logAccesoManager
	 */
	public LogAccesoManager getLogAccesoManager() {
		return logAccesoManager;
	}

		
	/**
	 * Obtiene el centros gestores manager.
	 *
	 * @return the centrosGestoresManager
	 */
	public CentroGestorManager getCentrosGestoresManager() {
		return centrosGestoresManager;
	}


	/**
	 * Establece el centros gestores manager.
	 *
	 * @param centrosGestoresManager the centrosGestoresManager to set
	 */
	public void setCentrosGestoresManager(CentroGestorManager centrosGestoresManager) {
		this.centrosGestoresManager = centrosGestoresManager;
	}


	/**
	 * Establece el log acceso manager.
	 *
	 * @param logAccesoManager the logAccesoManager to set
	 */
	public void setLogAccesoManager(LogAccesoManager logAccesoManager) {
		this.logAccesoManager = logAccesoManager;
	}

	/**
	 * Crea una nueva buscar log operaciones spring.
	 */
	public BuscarLogOperacionesSpring() {
		try {			
			setLogConvocatoriaManager((LogConvocatoriaManager) getBean("logConvocatoriaManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setLogAccesoManager((LogAccesoManager) getBean("logAccesoManager"));
			setCentrosGestoresManager((CentroGestorManager) getBean("centrosGestoresManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarLogOperacionesSpring():",e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		getLogger().debug("BuscarLogOperacionesSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(MESSAGE_RESOUCE);	
		String menu_consultas = RESOURCE_BUNDLE_2.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_log = RESOURCE_MESSAGE.getString("field.menuLateral.consultas.logOperaciones");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_log);
		//****************************************************************** 
		
	try{
		//Coger el form del jsp
		String numReg = recuperarNumReg(); 
		
		
		LogOperacionesForm formulario = null;
		formulario = (LogOperacionesForm) form;
		if (numReg != null) {
			formulario.setNumRegistro(numReg);	
		}
		
		
		String auxCampo = formulario.getCampo();
		String busqueda = formulario.getSubmit();
		String tipoOperacion = formulario.getTipoOperacion();
		
		String menu = this.getRequest().getParameter("menu");
		
		if(menu!=null && menu.equals("S")){//Viene del menu principal
			tipoOperacion = null;
		}
		
		if(tipoOperacion!=null ){
			
			if(tipoOperacion.equals("A")){
								
				LogAccesoQuery logAccesoQuery;
				//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
				if(formulario.getCampo()!= null){
					try{
						String campo=cargarCampos(formulario.getCampo(),tipoOperacion);
						formulario.setCampo(campo);
					}catch(Exception e){
						logger.error(STRING_BUSCARLOGOPERACIONESSPRING+ formulario.getCampo() ,e);
					}			
				}
				
				String paginaActual = this.getRequest().getParameter(STRING_PAGINAACTUAL);
				String paginaTotal = this.getRequest().getParameter(STRING_PAGINASTOTALES);
				
				if(formulario.isPulsaIr() == true)
				{
					paginaActual = formulario.getNumeroPaginaIr().toString();
					logger.info(STRING_PAGINAACTUALIR+ paginaActual);
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
					if(pagActu == 0){
						pagActu = 1;
					}
					if(pagActu > pagTotales){
						paginaActual = String.valueOf(pagTotales);
					}
				}
				if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
					paginaActual = Constantes.PAGINA_PRINCIPAL;
					formulario.setPaginaActual(paginaActual);
				}
				
				logAccesoQuery=crearQueryLogAcceso(formulario,paginaActual);	
				
				List<LogAccesoBean> logAcceso= null;
				if(STRING_BUSCAR.equals(busqueda) || STRING_ORDENAR.equals(busqueda) || STRING_PAGINAR.equals(busqueda)){
					logAcceso= logAccesoManager.buscarAccesosAll(logAccesoQuery);				
				}
				int pagTotal = 0;
				int numPaginas=0;
				if(formulario.getNumRegistro() == null){
					//Para el primer paso para el action, se establece que los registros por pagina son 5
					formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
				}
				int numRegistros=0;
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
				//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
				if(logAcceso != null && logAcceso.size()!= 0){
					pagTotal= logAcceso.get(0).getNumTotal();
					numPaginas = pagTotal / numRegistros;
					int resto = pagTotal % numRegistros;
					if(resto > 0){
						numPaginas++;
					}
					if(logAcceso.size()>numRegistros){
						logAcceso.remove(logAcceso.size()-1);
					}
					
				} 
				
				//Pasar la convocatoria y la pagina al jsp
				if(paginaActual==null || paginaActual.equals(""))
					paginaActual="1";
				
				setRequestAttribute("logAcceso", logAcceso);				
				setRequestAttribute(STRING_PAGINAACTUAL, paginaActual);
				setRequestAttribute(STRING_PAGINASTOTALES, numPaginas);	
				
			}
			else if(tipoOperacion.equals("C")){
							
				LogGenericoQuery logGenericoQuery;

				//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
				if(formulario.getCampo()!= null){
					try{
						String campo=cargarCampos(formulario.getCampo(),tipoOperacion);
						formulario.setCampo(campo);
					}catch(Exception e){
						logger.error(STRING_BUSCARLOGOPERACIONESSPRING+ formulario.getCampo() ,e);
					}			
				}
				
				String paginaActual = this.getRequest().getParameter(STRING_PAGINAACTUAL);
				String paginaTotal = this.getRequest().getParameter(STRING_PAGINASTOTALES);
				
				if(formulario.isPulsaIr() == true)
				{
					paginaActual = formulario.getNumeroPaginaIr().toString();
					logger.info(STRING_PAGINAACTUALIR+ paginaActual);
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
					if(pagActu == 0){
						pagActu = 1;
					}
					if(pagActu > pagTotales){
						paginaActual = String.valueOf(pagTotales);
					}
				}
				if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
					paginaActual = "1";
					formulario.setPaginaActual(paginaActual);
				}
								
				logGenericoQuery=crearQueryLogGenerico(formulario,paginaActual,numReg);				

								
				List<LogGenericoBean> logGenerico= null;
				if(STRING_BUSCAR.equals(busqueda) || STRING_ORDENAR.equals(busqueda) || STRING_PAGINAR.equals(busqueda)){
					logGenerico=logGenericoManager.buscarLogGenericoCierreReapertura(logGenericoQuery);					
				}
											
				//Meter la descripcion del centro Gestor				
				for (LogGenericoBean logGenericoBean : logGenerico) {
					Long idTablaOrigen = logGenericoBean.getIdTablaOrigen();
					CentroGestorBean centroGestor = centrosGestoresManager.obtenerCentroGestor(idTablaOrigen.intValue());
					logGenericoBean.setCentroGestor(centroGestor.getDescripcion());					
				}
				
				
				int pagTotal = 0;
				int numPaginas=0;
				if(formulario.getNumRegistro() == null){
					//Para el primer paso para el action, se establece que los registros por pagina son 5
					formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
				}
				int numRegistros=0;
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
				//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
				if(logGenerico != null && logGenerico.size()!= 0){
					pagTotal= logGenerico.get(0).getNumTotal();
					numPaginas = pagTotal / numRegistros;
					int resto = pagTotal % numRegistros;
					if(resto > 0){
						numPaginas++;
					}
					if(logGenerico.size()>numRegistros){
						logGenerico.remove(logGenerico.size()-1);
					}					
				} 
				
				//Pasar la convocatoria y la pagina al jsp
				setRequestAttribute("logGenerico", logGenerico);				
				setRequestAttribute(STRING_PAGINAACTUAL, paginaActual);
				setRequestAttribute(STRING_PAGINASTOTALES, numPaginas);	
				
			}
			else if(tipoOperacion.equals("E")){										
				LogConvocatoriaQuery logConvocatoriaQuery;

				//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
				if(formulario.getCampo()!= null){
					try{
						String campo=cargarCampos(formulario.getCampo(),tipoOperacion);
						formulario.setCampo(campo);
					}catch(Exception e){
						logger.error(STRING_BUSCARLOGOPERACIONESSPRING+ formulario.getCampo() ,e);
					}			
				}
				
				
				String paginaActual = this.getRequest().getParameter(STRING_PAGINAACTUAL);
				String paginaTotal = this.getRequest().getParameter(STRING_PAGINASTOTALES);
				
				if(formulario.isPulsaIr() == true)
				{
					paginaActual = formulario.getNumeroPaginaIr().toString();
					logger.info(STRING_PAGINAACTUALIR+ paginaActual);
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
					if(pagActu == 0){
						pagActu = 1;
					}
					if(pagActu > pagTotales){
						paginaActual = String.valueOf(pagTotales);
					}
				}
				if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
					paginaActual = "1";
					formulario.setPaginaActual(paginaActual);
				}

				logConvocatoriaQuery=crearQueryLogConvocatoria(formulario,paginaActual,numReg);					
				
				
				//Coger la lista de convocatorias a mostrar				
				List<LogConvocatoriaBean> logConvocatoria= null;
				if(STRING_BUSCAR.equals(busqueda) || STRING_ORDENAR.equals(busqueda) || STRING_PAGINAR.equals(busqueda)){
					logConvocatoria=logConvocatoriaManager.buscarConvocatoriaAll(logConvocatoriaQuery);
					
				}
				int pagTotal = 0;
				int numPaginas=0;
				if(formulario.getNumRegistro() == null){
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
				//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
				if(logConvocatoria != null && logConvocatoria.size()!= 0){
					pagTotal= logConvocatoria.get(0).getNumTotal();
					numPaginas = pagTotal / numRegistros;
					int resto = pagTotal % numRegistros;
					if(resto > 0){
						numPaginas++;
					}
					if(logConvocatoria.size()>numRegistros){
						logConvocatoria.remove(logConvocatoria.size()-1);
					}
					
				} 
				
				//Pasar la convocatoria y la pagina al jsp
				setRequestAttribute("logConvocatoria", logConvocatoria);				
				setRequestAttribute(STRING_PAGINAACTUAL, paginaActual);
				setRequestAttribute(STRING_PAGINASTOTALES, numPaginas);		
			}					
			
		}
		else {
			// Cargamos en los campos fecha, la fecha actual
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
			formulario.setFechaDesde(dateFormat.format(date));
			formulario.setFechaHasta(dateFormat.format(date));
		}
		
	
		formulario.setCampo(String.valueOf(auxCampo));
		if(formulario != null && formulario.getPaginaActual() != null && formulario.getPaginaActual().equals("0")){

			formulario.setPaginaActual("1");
				
			
		}
		getLogger().debug("BuscarLogOperacionesSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarLogOperacionesSpring - doExecute:" ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
		
	}
	
	/**
	 * Recuperar num reg.
	 *
	 * @return el string
	 */
	private String recuperarNumReg() {
		String numReg = "";
		try{
			numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
		}catch(Exception e){
			logger.error("Error BuscarLogOperacionesSpring - numReg:"+ numReg ,e);
		}
		return numReg;
	}
	
	/**
	 * Comprobar entero.
	 *
	 * @param numero el numero
	 * @return el int
	 */
	private int comprobarEntero(String numero) {
		int resultado = 0;
		try{
			resultado = Integer.parseInt(numero);
		}catch(Exception e){
			logger.error(STRING_BUSCARLOGOPERACIONESNUMREGISTROS+ resultado ,e);
			resultado = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return resultado;
	}
	
	/**
	 * Cargar campos.
	 *
	 * @param campo el campo
	 * @param tipoOperacion el tipo operacion
	 * @return el string
	 */
	private String cargarCampos(String campo,String tipoOperacion) {
		String auxCampo=null;
		int codCampo = 0;
		codCampo = cargarCodCampo(campo,codCampo);
		
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					if(tipoOperacion.equals("A")){//Log de Accesos
						switch(codCampo){
						//Los campos de ordenacion del jsp
							case 1:auxCampo = "id";break;
							case 2:auxCampo = STRING_FECHA;break;
							case 3:auxCampo = "loginUsuario";break;
							case 9:auxCampo = "resultado";break;
							default:break;
						}
						
					}else if(tipoOperacion.equals("C")){//Cierre y Apertura de Ejercicios
						switch(codCampo){
						//Los campos de ordenacion del jsp
							case 1:auxCampo = "id";break;
							case 2:auxCampo = STRING_FECHA;break;
							case 4:auxCampo = STRING_USUARIO;break;
							case 5:auxCampo = "tipoOperacion";break;
							case 10:auxCampo = "centroGestor";break;
							default:break;
						}
						
					}else{//tipoOperacion = "E" -> Cambio de Estados de Convocaotoria
						switch(codCampo){
						//Los campos de ordenacion del jsp
							case 1:auxCampo = "id";break;
							case 2:auxCampo = STRING_FECHA;break;
							case 4:auxCampo = STRING_USUARIO;break;
							case 5:auxCampo = "tipoOperacion";break;	
							case 6:auxCampo = "convocatoria";break;
							case 7:auxCampo = "estadoConvocatoriaByIdEstadoAnterior";break;
							case 8:auxCampo = "estadoConvocatoriaByIdEstadoActual";break;
							default:break;
						}
					}
					
				
			}
			return auxCampo;
		}

	/**
	 * Cargar cod campo.
	 *
	 * @param campo el campo
	 * @param codCampo el cod campo
	 * @return el int
	 */
	private int cargarCodCampo(String campo,int codCampo) {
		try{
			codCampo = Integer.parseInt(campo);			
		}catch(Exception e){
			logger.error("Error BuscarLogOperacionesSpring - cargarcampos- campo:"+ codCampo ,e);
		}
		return codCampo;
	}
	
	/**
	 * Crear query log convocatoria.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el log convocatoria query
	 */
	private LogConvocatoriaQuery crearQueryLogConvocatoria(LogOperacionesForm formulario,String paginaActual, String numReg) {
		//Recoger los datos del formulario
		
		
		String usuario=formulario.getUsuario();
		String fechaDesde=formulario.getFechaDesde();
		String fechaHasta=formulario.getFechaHasta();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		
		if(formulario.getNumRegistro() == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarLogOperacionesSpring  - crearQueryLogConvocatoria - numRegistros:"+ numRegistros ,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarLogOperacionesSpring  - crearQueryLogConvocatoria - numRegistros:"+ numRegistros ,e);
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
		
		LogConvocatoriaQuery logConvocatoriaQuery = new LogConvocatoriaQuery();
		logConvocatoriaQuery.setTipoOperacion(Constantes.OPERACION_CAMBIO_ESTADO);
		
		if(paginaActual != null){
			logConvocatoriaQuery.setMaxResults(tamanyoPaginacion);
			logConvocatoriaQuery.setFirstResult(primerRegistro);
		}		
		logConvocatoriaQuery.setCalculateNumResults(true);
		
		//Comprobar los filtros para realizar la busqueda
		
	
		
			
		//BUSQUEDA POR EL USUARIO
		UsuarioQuery usuarioQuery= new UsuarioQuery();
		if (usuario!= null  && !"".equals(usuario)){
			usuarioQuery.setLogin(usuario);
			usuarioQuery.setLoginComparator(Comparator.LIKE_LEFT_RIGHT);			
		}
		logConvocatoriaQuery.setUsuario(usuarioQuery);
		
		//Compruebo si las fechas están rellenas
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		
		if (fechaDesde !=null && !"".equals(fechaDesde)){
			try{	
				Date dFechad=formatoDelTexto.parse(fechaDesde);
				logConvocatoriaQuery. setFechaMin(dFechad);
				}
			catch(Exception e){
				logger.error("Error BuscarLogOperacionesSpring  - parsear fechaDesde:"+ fechaDesde ,e);
				
			}
		}
		if (fechaHasta !=null && !"".equals(fechaHasta)){
			try{
				Date dFechah=formatoDelTexto.parse(fechaHasta);
				logConvocatoriaQuery.setFechaMax(dFechah);
				}
			catch(Exception e){	
				logger.error("Error BuscarLogOperacionesSpring  - parsear fechaHasta:"+ fechaHasta ,e);
			}
		}
			
			
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden =("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);	
			if(STRING_USUARIO.equals(campo)){
				UsuarioQuery usuarioQueryOrdenacion =  new UsuarioQuery();
				usuarioQueryOrdenacion.addOrder("login", orden);
				logConvocatoriaQuery.setUsuario(usuarioQueryOrdenacion);
			}else{
				if("estadoConvocatoriaByIdEstadoAnterior".equals(campo)){
					EstadoConvocatoriaQuery estadoConvocatoriaQueryAnterior = new EstadoConvocatoriaQuery();
					estadoConvocatoriaQueryAnterior.addOrder("descripcion", orden);
					logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoAnterior(estadoConvocatoriaQueryAnterior);
				}else{
					if("estadoConvocatoriaByIdEstadoActual".equals(campo)){
						EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
						estadoConvocatoriaQuery.addOrder("descripcion", orden);
						logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoActual(estadoConvocatoriaQuery);
					}else{
						if("up".equals(direccion)){
							logConvocatoriaQuery.addOrder(campo,OrderType.ASC);
						}else{
							logConvocatoriaQuery.addOrder(campo,OrderType.DESC);
						}
					}
				}
			}
		}
		else{ //Por defecto ordenamos por el primer campo ascendente
			logConvocatoriaQuery.addOrder("id",OrderType.ASC);
		}
		
				
		//Devulelve la convocatoria con los filtros
		return logConvocatoriaQuery;
		
	}
	
	
	/**
	 * Crear query log generico.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el log generico query
	 */
	private LogGenericoQuery crearQueryLogGenerico(LogOperacionesForm formulario,String paginaActual,String numReg) {

		//Recoger los datos del formulario		
		String usuario=formulario.getUsuario();
		String fechaDesde=formulario.getFechaDesde();
		String fechaHasta=formulario.getFechaHasta();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
			
		if(formulario.getNumRegistro() == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarLogOperacionesSpring  - crearQueryLogGenerico - numRegistros:"+ numRegistros ,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarLogOperacionesSpring  - crearQueryLogGenerico - numRegistros:"+ numRegistros ,e);
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
		
		LogGenericoQuery logGenericoQuery = new LogGenericoQuery();
		if(paginaActual != null){
			logGenericoQuery.setMaxResults(tamanyoPaginacion);
			logGenericoQuery.setFirstResult(primerRegistro);
		}		
		logGenericoQuery.setCalculateNumResults(true);
				
		//BUSQUEDA POR EL USUARIO
		UsuarioQuery usuarioQuery= new UsuarioQuery();
		if (usuario!= null  && !"".equals(usuario)){
			usuarioQuery.setLogin(usuario);
			usuarioQuery.setLoginComparator(Comparator.LIKE_LEFT_RIGHT);			
		}
		logGenericoQuery.setUsuario(usuarioQuery);
		
		
		
		//Compruebo si las fechas están rellenas
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);		
		if (fechaDesde !=null && !"".equals(fechaDesde)){
			try{	
				Date dFechad=formatoDelTexto.parse(fechaDesde);
				logGenericoQuery.setFechaMin(dFechad);
				}
			catch(Exception e){
				logger.error("Error BuscarLogOperacionesSpring  - crearQueryLogGenerico -parsear fechaDesde:"+ fechaDesde ,e);
				
			}
		}
		if (fechaHasta !=null && !"".equals(fechaHasta)){
			try{
				Date dFechah=formatoDelTexto.parse(fechaHasta);
				logGenericoQuery.setFechaMax(dFechah);
				}
			catch(Exception e){	
				logger.error("Error BuscarLogOperacionesSpring  - crearQueryLogGenerico -parsear fechaHasta:"+ fechaHasta ,e);
			}
		}
			
	
		logGenericoQuery.addTipoOperacionIn(Constantes.OPERACION_CIERRE_EJERCICIO);
		logGenericoQuery.addTipoOperacionIn(Constantes.OPERACION_REAPERTURA_EJERCICIO);
		
		logGenericoQuery.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CENTRO_GESTOR);	
		
		
	
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden =("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);	
			if(STRING_USUARIO.equals(campo)){
				UsuarioQuery usuarioQueryOrdenacion =  new UsuarioQuery();
				usuarioQueryOrdenacion.addOrder("login", orden);
				logGenericoQuery.setUsuario(usuarioQueryOrdenacion);
			}else{
				if("centroGestor".equals(campo)){
					logGenericoQuery.addOrder("idTablaOrigen", orden);
				}else{
					if("up".equals(direccion)){
						logGenericoQuery.addOrder(campo,OrderType.ASC);
					}else{
						logGenericoQuery.addOrder(campo,OrderType.DESC);
					}
				}
			}
			
		}
		else{ //Por defecto ordenamos por el primer campo ascendente
			logGenericoQuery.addOrder("id",OrderType.ASC);
		}
		
				
		//Devulelve la convocatoria con los filtros
		return logGenericoQuery;
		
	}
	
	
	
	/**
	 * Crear query log acceso.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @return el log acceso query
	 */
	private LogAccesoQuery crearQueryLogAcceso(LogOperacionesForm formulario,String paginaActual) {

		//Recoger los datos del formulario		
		String usuario=formulario.getUsuario();
		String fechaDesde=formulario.getFechaDesde();
		String fechaHasta=formulario.getFechaHasta();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
				
		if(formulario.getNumRegistro() == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = Integer.parseInt(formulario.getNumRegistro());
		//Si la pagina actual no esta definida, se establece a 1
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
			paginaActual = "1";
		}
		//Calcular el numero de lineas de cada paginacion
		LogAccesoQuery logAccesoQuery = new LogAccesoQuery();
		
		calcularNumeroLineas(numRegistros,paginaActual,logAccesoQuery,usuario);
				
		
		//Compruebo si las fechas están rellenas
		
		comprobarFechasRellenas(fechaDesde,fechaHasta,logAccesoQuery);
	
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			comprobarOrden(campo,direccion,logAccesoQuery);
		}
		else{ //Por defecto ordenamos por el primer campo ascendente
			logAccesoQuery.addOrder("id",OrderType.ASC);
		}
		
				
		//Devulelve la convocatoria con los filtros
		return logAccesoQuery;		
	}
	
	/**
	 * Comprobar orden.
	 *
	 * @param campo el campo
	 * @param direccion el direccion
	 * @param logAccesoQuery el log acceso query
	 */
	private void comprobarOrden(String campo, String direccion, LogAccesoQuery logAccesoQuery) {
		
			OrderType orden =("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if("resultado".equals(campo)){
				CodigoAccesoQuery codigoAccesoQuery =  new CodigoAccesoQuery();
				codigoAccesoQuery.addOrder("id", orden);
				logAccesoQuery.setCodigoAcceso(codigoAccesoQuery);
			}else{
				if("up".equals(direccion)){
					logAccesoQuery.addOrder(campo,OrderType.ASC);
				}else{
					logAccesoQuery.addOrder(campo,OrderType.DESC);
				}
			}
	}
	
	/**
	 * Comprobar fechas rellenas.
	 *
	 * @param fechaDesde el fecha desde
	 * @param fechaHasta el fecha hasta
	 * @param logAccesoQuery el log acceso query
	 */
	private void comprobarFechasRellenas(String fechaDesde, String fechaHasta, LogAccesoQuery logAccesoQuery) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);	
		
		if (fechaDesde !=null && !"".equals(fechaDesde)){
			try{	
				Date dFechad=formatoDelTexto.parse(fechaDesde);
				logAccesoQuery.setFechaMin(dFechad);
				}
			catch(Exception e){
				logger.error("Error BuscarLogOperacionesSpring  - crearQueryLogAcceso -parsear fechaDesde:"+ fechaDesde ,e);
			}
		}
		if (fechaHasta !=null && !"".equals(fechaHasta)){
			try{
				Date dFechah=formatoDelTexto.parse(fechaHasta);
				logAccesoQuery.setFechaMax(dFechah);
				}
			catch(Exception e){		
				logger.error("Error BuscarLogOperacionesSpring  - crearQueryLogAcceso -parsear fechaHasta:"+ fechaHasta ,e);
			}
		}
			
	
	}
	
	/**
	 * Calcular numero lineas.
	 *
	 * @param numRegistros el num registros
	 * @param paginaActual el pagina actual
	 * @param logAccesoQuery el log acceso query
	 * @param usuario el usuario
	 */
	private void calcularNumeroLineas(int numRegistros, String paginaActual, LogAccesoQuery logAccesoQuery, String usuario) {
		
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		
		if(paginaActual != null){
			logAccesoQuery.setMaxResults(tamanyoPaginacion);
			logAccesoQuery.setFirstResult(primerRegistro);
		}		
		logAccesoQuery.setCalculateNumResults(true);
		
		logAccesoQuery.setLoginUsuario(usuario);
		logAccesoQuery.setLoginUsuarioComparator(Comparator.LIKE_LEFT_RIGHT);
	}	
	
}
