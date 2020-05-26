package es.map.ipsg.action.logs;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.LogSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.LogSolicitudesForm;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class DetalleLogSolicitudesSpring.
 */
public class DetalleLogSolicitudesSpring extends AbstractSpring {

	//Declarar los resource
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";	
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	

	//Declaracion de manager
	/**
	 * Para recuperar los Errores se utiliza la tabla y ls manager LogServicio pero con el Resultado de error (ER).
	 */
	private LogSolicitudManager logSolicitudManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el solicitud manager. */
	private SolicitudesManager solicitudManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogServicioManager.class);
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
		
	/**
	 * Crea una nueva detalle log solicitudes spring.
	 */
	public DetalleLogSolicitudesSpring() {
		try {
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setSolicitudManager((SolicitudesManager) getBean("solicitudesManager"));
		} catch (Exception e) {
			logger.error("Error DetalleLogSolicitudesSpring :",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		try{
			//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
			String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.consultas");
			this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
			String subMenu_log = RESOURCE_BUNDLE.getString("field.menuLateral.consultas.logSolicitudes");
			this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_log);
			//****************************************************************** 
			getLogger().debug("DetalleLogSolicitudesSpring -start");
			LogSolicitudesForm formulario;
			formulario = (LogSolicitudesForm) form;
			
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			String cambios = this.getRequest().getParameter("cambios");
			
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			String busqueda="";
			if (formulario.getSubmit()!=null&& !"".equals(formulario.getSubmit())){
				busqueda=formulario.getSubmit();
			}else{
				busqueda=STRING_BUSCAR;
			}
			
			if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
				int pagActu = Integer.parseInt(paginaActual);		
				int pagTotales = Integer.parseInt(paginaTotal);
	
				if(pagActu > pagTotales){
					paginaActual = String.valueOf(pagActu-1);
				}
			}
			
			if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
				paginaActual = Constantes.PAGINA_PRINCIPAL;		
			}
			
			
			LogSolicitudQuery logSolicitudQuery;
			String campo="";
			//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
			if(formulario.getCampo()!= null){
				try{
					if (formulario.getCampo()==null ||formulario.getCampo().equals("")){
						campo=cargarCampos("1");
					}else{
						campo=cargarCampos(formulario.getCampo());
					}
					
				}catch(Exception e){
					logger.error("Error DetalleLogSolicitudesSpring - campo :"+ campo ,e);
				}			
			}
			logSolicitudQuery=crearQueryDetalleSolicitudes(formulario,paginaActual,numReg,campo);
			List<LogSolicitudBean> detalle = null;
			
			if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				detalle = logSolicitudManager.buscarLogSolicitudAll(logSolicitudQuery);
			}
			
			int pagTotal = 0;
			int  numPaginas=0;
			if(formulario.getNumRegistro() == null && numReg == null){
				//Para el primer paso para el action, se establece que los registros por pagina son 5
				formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
			}
			int numRegistros = 0;
			if(numReg != null){ 
				numRegistros = Integer.parseInt(numReg);
			}else{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}
			//Recorrer la lista, para recuperar el nombre del Responsable del cambio
			LogSolicitudBean detalleAux;
			
			for(int i=0;i<detalle.size();i++){
				detalleAux=detalle.get(i);
				if(detalleAux.getTipoActor().equals(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_USUARIO)){
					UsuarioBean usuarioBean;
					UsuarioQuery usuarioQuery=new UsuarioQuery();
					usuarioQuery.setLogin(detalleAux.getActor());
					usuarioBean=usuarioManager.buscarUsuario(usuarioQuery);
					
						if(usuarioBean!=null && usuarioBean.getNombreCompleto()!=null &&! "".equals(usuarioBean.getNombreCompleto())){
							detalleAux.setActor(usuarioBean.getNombreCompleto());
						}
					
					detalle.set(i, detalleAux);
				}else{
					if(detalleAux.getTipoActor().equals(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_CIUDADANO)){
						
						SolicitudBean solicitudBean = solicitudManager.obtenerSolicitud(detalleAux.getIdSolicitud());
						
						if(solicitudBean!=null){
							String nombreCompleto = solicitudBean.getNombre() + " " + solicitudBean.getPrimerApellido();
							detalleAux.setActor(nombreCompleto);
						}
	
						detalle.set(i, detalleAux);
					}
				}
			}
			if(detalle != null && detalle.size()!= 0){
				pagTotal= detalle.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(detalle.size()>numRegistros){
					detalle.remove(detalle.size()-1);
				}			
			}
			
			if(paginaActual==null||"".equals(paginaActual))
				paginaActual="1";
			
			setRequestAttribute("numeroJustificante", formulario.getNumeroJustificante());
			setRequestAttribute("idConvocatoria", formulario.getIdConvocatoria());
			setRequestAttribute("detalle", detalle);
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);	
			setRequestAttribute("submit", busqueda);
			setRequestAttribute("cambios", cambios);
	
			getLogger().debug("DetalleLogSolicitudesSpring -end");
			return "success";
		}catch(Exception e){
			logger.error("Error DetalleLogSolicitudesSpring -Error al Consultar el Detalle de la Solicitud:",e);			
			setRequestAttribute("descripcionError", RESOURCE_BUNDLE.getString("aplicacion.error.generico"));
			return "nosuccess";
		}
	}

	/**
	 * Cargar campos.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCampos(String campo) {
		String auxCampo = null;
		int codCampo = 0;
		try {
			codCampo = Integer.parseInt(campo);
		} catch (Exception e) {
			logger.error("Error DetalleLogSolicitudesSpring - cargarCampos- campo:" + codCampo,e);
		}
		if (campo != null && !"".equals(campo) && !campo.equals("null")) {
			
				switch (codCampo) {
				// Los campos de ordenacion del jsp
				case 3:auxCampo = "fecha";break;
				case 4:auxCampo = "actor";break;
				default:break;
				}
			
		}
		logger.info("CampoOrdenacion: "+auxCampo);
		return auxCampo;
	}

	/**
	 * Crear query detalle solicitudes.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param campo el campo
	 * @return el log solicitud query
	 */
	private LogSolicitudQuery crearQueryDetalleSolicitudes(
			LogSolicitudesForm formulario, String paginaActual, String numReg, String campo) {
		// Recoger los datos del formulario

		String direccion = formulario.getDireccion();

		if (formulario.getNumRegistro() == null && numReg == null) {
			// Para el primer paso para el action, se establece que los
			// registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = Integer.parseInt(formulario.getNumRegistro());
		// Si la pagina actual no esta definida, se establece a 1
		if ("0".equals(paginaActual) || paginaActual == null
				|| "".equals(paginaActual)) {
			paginaActual = "1";
		}
		// Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal + 1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina * tamanyoPaginacionReal)
				- tamanyoPaginacionReal;

		LogSolicitudQuery logSolicitudDetalleQuery = new LogSolicitudQuery();
		
		logSolicitudDetalleQuery.setMaxResults(tamanyoPaginacion);
		logSolicitudDetalleQuery.setFirstResult(primerRegistro);


		logSolicitudDetalleQuery.setCalculateNumResults(true);
		if (formulario.getNumeroJustificante() != null && !"".equals(formulario.getNumeroJustificante())) {
			SolicitudComunQuery solicitudDetalleQuery = new SolicitudComunQuery();
			solicitudDetalleQuery.setNumeroSolicitud(formulario.getNumeroJustificante());
			solicitudDetalleQuery.addConvocatoriaIn(Long.valueOf(formulario.getIdConvocatoria()));
			
			logSolicitudDetalleQuery.setSolicitudComun(solicitudDetalleQuery);
		}

		logSolicitudDetalleQuery.addTipoOperacionIn(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD);
		logSolicitudDetalleQuery.addTipoOperacionIn(Constantes.OPERACION_ALTA);
		logSolicitudDetalleQuery.addTipoOperacionIn(Constantes.OPERACION_BAJA);

		// Añadimos el order
		OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC
				: OrderType.DESC);
		if (campo != null && !"0".equals(campo) && !"".equals(campo)) {
			logSolicitudDetalleQuery.addOrder(campo,orden);
			logger.info("CampoOrdenacionOrden: "+campo);
			logger.info("DireccionOrdenacionOrden: "+direccion);
		}else{
			campo = "fecha";
			logSolicitudDetalleQuery.addOrder(campo,OrderType.DESC);
			logger.info("CampoOrdenacion: "+campo);
			logger.info("DireccionOrdenacion: "+OrderType.DESC);
		}
		
		

		// Devulelve los errores con los filtros
		return logSolicitudDetalleQuery;

	}

	/**
	 * Obtiene el log solicitud manager.
	 *
	 * @return the logErroresManager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager el nuevo log solicitud manager
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
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
	 * Obtiene el solicitud manager.
	 *
	 * @return the ciudadanoManager
	 */
	public SolicitudesManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager el nuevo solicitud manager
	 */
	public void setSolicitudManager(SolicitudesManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}


}
