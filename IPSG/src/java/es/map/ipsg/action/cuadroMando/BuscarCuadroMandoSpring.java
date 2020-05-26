package es.map.ipsg.action.cuadroMando;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.ConvocatoriasViewQuery;
import es.map.ips.model.DescargaModelo790Query;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolComunIncidenciasViewQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoSolicitudQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.formaAcceso.BuscarFormasAccesoSpring;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ConvocatoriasViewBean;
import es.map.ipsg.bean.DescargaModelo790Bean;
import es.map.ipsg.bean.EstadoConvocatoriaBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ConsultarCuadroMandoForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasViewManager;
import es.map.ipsg.manager.DescargaModelo790Manager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.UtilesIPSG;

/**
 * BuscarCuadroMandoAction.
 *
 * @author amartinl
 */
@SuppressWarnings("rawtypes")
public class BuscarCuadroMandoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarFormasAccesoSpring.class);
	
	/** La constante STRINGTOTAL. */
	private static final String STRINGTOTAL = "TOTAL";
	
	/** La constante STRINGSOLICITUDCOMUNID. */
	private static final String STRINGSOLICITUDCOMUNID = "solicitudComun.id";
	
	/** La constante STRINGFECHAINTENTO. */
	private static final String STRINGFECHAINTENTO = "fechaIntento";
	
	/** La constante STRINGIDSOLICITUDACTUAL. */
	private static final String STRINGIDSOLICITUDACTUAL = "idSolicitudActual: ";
	
	/** La constante STRINGIDSOLICITUDANTERIOR. */
	private static final String STRINGIDSOLICITUDANTERIOR = " idSolicitudAnterior ";
	
	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el estados convocatoria manager. */
	private EstadoConvocatoriaManager estadosConvocatoriaManager;
	
	/** el convocatorias view manager. */
	private ConvocatoriasViewManager  convocatoriasViewManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el descarga modelo 790 manager. */
	private DescargaModelo790Manager descargaModelo790Manager;
	
	/** el ministerios manager. */
	private MinisterioManager ministeriosManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva buscar cuadro mando spring.
	 */
	public BuscarCuadroMandoSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setEstadosConvocatoriaManager((EstadoConvocatoriaManager) getBean("estadosConvocatoriaManager"));
				setConvocatoriasViewManager((ConvocatoriasViewManager) getBean("convocatoriasViewManager"));
				setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setRegistroSolicitudManager((RegistroSolicitudManager) getBean ("registroSolicitudManager"));
				setDescargaModelo790Manager((DescargaModelo790Manager) getBean("descargaModelo790Manager"));
				setMinisteriosManager((MinisterioManager) getBean("ministeriosManager"));
				setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			} catch (Exception e) {
				logger.error("Error BuscarCuadroMandoSpring(): ",e);
		}
	}

	/**
	 * Do execute.
	 *
	 * @param form  SpringForm
	 * @return resultado
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_consultas = RESOURCE_MESSAGE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_cuadroMando = RESOURCE_MESSAGE.getString("field.menuLateral.consultas.CdM");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_cuadroMando);
		//******************************************************************
	try{
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
		
		UtilesIPSG utilesIPSG=new UtilesIPSG();
		
		//Obtenemos el Usuario que esta logueado en la aplicacion para insertarlo en el log
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que esta logueado

		/*INI-TRA042*/
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		setRequestAttribute("rol", usuarioBean.getIdPerfil());

		//Cogemos el form del jsp
		ConsultarCuadroMandoForm formulario;
		formulario = (ConsultarCuadroMandoForm) form;

		//Comprobamos que tipo de perfil tiene el usuario para cargar el Centro Gestor
		String sPerfilUsuario = "";

		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
		sPerfilUsuario = getRequest().getParameter("perfilUsuario");
		formulario.setPerfilUsuario(sPerfilUsuario);
		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR) || sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_CONSULTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			
		}	
		//Carga el Combo de los Centros Gestores
		cargarCombos();
		//Indicamos si viene desde el enlace del menu para mostrar los resultados de busqueda o no 
		//y guardar la descripcion para el formulario de busqueda
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		if (sVieneMenu != null && sVieneMenu.equals("S"))
		{
			//Poner todos los campos del formulario en blanco
			formulario.setListaCentrosGestores(listaCentrosGestores);
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");
			formulario.setEstado("");
			
		}else //Si viene a null es que viene de las demas paginas que no son del menu principal
		{
			//Poner todos los campos con los datos de la busqueda
			formulario.setListaCentrosGestores(listaCentrosGestores);
			formulario.setFechaDesde(formulario.getFechaDesde());
			formulario.setFechaHasta(formulario.getFechaHasta());
			formulario.setEstado(formulario.getEstado());
			
			sVieneMenu = "N";
		}
		
		setRequestAttribute("listaCentrosGestores", listaCentrosGestores);
		
		//Creamos la Query
		ConvocatoriasViewQuery convocatoriasViewQuery = new ConvocatoriasViewQuery();
		convocatoriasViewQuery = crearConvocatoriasViewQueryDatosFormulario(convocatoriasViewQuery, formulario, listaCentrosGestores);
		/*FIN-TRA042*/
			
		//Buscamos todas las convocatorias
		List<ConvocatoriasViewBean> lConvocatoriasViewBean = buscarConvocatoriasView(convocatoriasViewQuery);
		
		//Completamos los datos para mostrar en la tabla resultado
		if(lConvocatoriasViewBean!=null && lConvocatoriasViewBean.size()>0){
			lConvocatoriasViewBean = completaDatosConvocatoriasView(lConvocatoriasViewBean, formulario);		
		
			List<ConvocatoriasViewBean> lConvocatoriasViewBeanFinal = agrupaConvocatorias(lConvocatoriasViewBean, formulario);
			
			lConvocatoriasViewBeanFinal = calculaResto(lConvocatoriasViewBeanFinal, formulario);
		
			if(lConvocatoriasViewBeanFinal.size()>0){
				setRequestAttribute("irAnclaje", "S");
				//Se le pasa todas las convocatorias ordenadas por Ministerios
				setRequestAttribute("lConvocatoriasViewBean", lConvocatoriasViewBeanFinal);
				this.getRequest().getSession().setAttribute("consultaExportarCuadroMando", lConvocatoriasViewBeanFinal);
			}
			// si no se ha seleccionado ningun campo de la comvocatoria,se muestra el total
			if( formulario.getCkCentroGestor()==false && formulario.getCkCuerpoEscala()==false
					&& formulario.getCkEjercicio()==false && formulario.getCkEstado()==false
					&& formulario.getCkFechaBOE()==false && formulario.getCkFormaAcceso()== false
					&& formulario.getCkNumPlazasDiscap()==false	&& formulario.getCkNumPlazasTotales()==false
					&& formulario.getDsCentroGestor().equals("")
					&& lConvocatoriasViewBeanFinal.size()>0)
				{
					int numTotalInscripcionSinPago =0;	
					int numTotalInscripcionSinIntentoPago =0;
					int numTotalSolPresencial=0;
					int numTotalSolTelematica =0;
					int numTotalSolIncPagoResuelta =0;
					int numTotalSolIncPagoSinResolver =0;
					int numTotalSolIncRegistroResuelta =0;
					int numTotalSolIncRegistroSinResolver =0;
					int numTotalPagosSinRegistro =0;
					int numTotalPagosSinIntentoRegistro =0;
					int numTotalDescargasManuales=0;
					int numTotalPlazasTotales =0;
					int  numTotalPlazasDiscapacitados =0;
					for(ConvocatoriasViewBean convAux:lConvocatoriasViewBeanFinal)
					{
						if(convAux.getDesCentroGestor().equals(STRINGTOTAL))
						{
							 numTotalInscripcionSinPago += Integer.valueOf(convAux.getNumTotalInscripcionSinPago());	
							    numTotalInscripcionSinIntentoPago += Integer.valueOf(convAux.getNumTotalInscripcionSinIntentoPago ());
								numTotalSolPresencial += Integer.valueOf(convAux.getNumTotalSolPresencial ());
							    numTotalSolTelematica += Integer.valueOf(convAux.getNumTotalSolTelematica ());
							    numTotalSolIncPagoResuelta += Integer.valueOf(convAux.getNumTotalSolIncPagoResuelta ());
							    numTotalSolIncPagoSinResolver += Integer.valueOf(convAux.getNumTotalSolIncPagoSinResolver ());
								numTotalSolIncRegistroResuelta += Integer.valueOf(convAux.getNumTotalSolIncRegistroResuelta ());
								numTotalSolIncRegistroSinResolver += Integer.valueOf(convAux.getNumTotalSolIncRegistroSinResolver ());
								numTotalPagosSinRegistro += Integer.valueOf(convAux.getNumTotalPagosSinRegistro ());
							    numTotalPagosSinIntentoRegistro += Integer.valueOf(convAux.getNumTotalPagosSinIntentoRegistro ());
							    numTotalDescargasManuales += Integer.valueOf(convAux.getNumTotalDescargasManuales ());
							    numTotalPlazasTotales += Integer.valueOf(convAux.getNumTotalPlazasTotales ());
							    numTotalPlazasDiscapacitados += Integer.valueOf(convAux.getNumTotalPlazasDiscapacitados ());
						}	
					}
					ConvocatoriasViewBean convTotales= new ConvocatoriasViewBean();
					convTotales.setNumTotalInscripcionSinPago(String.valueOf(numTotalInscripcionSinPago));
					convTotales.setNumTotalInscripcionSinIntentoPago(String.valueOf(numTotalInscripcionSinIntentoPago));
					convTotales.setNumTotalSolPresencial(String.valueOf(numTotalSolPresencial));
					convTotales.setNumTotalSolTelematica(String.valueOf(numTotalSolTelematica));
					convTotales.setNumTotalSolIncPagoResuelta(String.valueOf(numTotalSolIncPagoResuelta));
					convTotales.setNumTotalSolIncPagoSinResolver(String.valueOf(numTotalSolIncPagoSinResolver));
					convTotales.setNumTotalSolIncRegistroResuelta(String.valueOf(numTotalSolIncRegistroResuelta));
					convTotales.setNumTotalSolIncRegistroSinResolver(String.valueOf(numTotalSolIncRegistroSinResolver));
					convTotales.setNumTotalPagosSinRegistro(String.valueOf(numTotalPagosSinRegistro));
					convTotales.setNumTotalPagosSinIntentoRegistro(String.valueOf(numTotalPagosSinIntentoRegistro));
					convTotales.setNumTotalDescargasManuales(String.valueOf(numTotalDescargasManuales));
					convTotales.setNumTotalPlazasTotales(numTotalPlazasTotales);
					convTotales.setNumTotalPlazasDiscapacitados(numTotalPlazasDiscapacitados);
					
					setRequestAttribute("totales",convTotales);	
			}	
		}
		
		if(formulario.getAccion().equals("BUSCAR") || formulario.getAccion().equals("EXPORTAR"))
		{
			formulario.setCkCentroGestor(formulario.getCkCentroGestor());
			formulario.setCkCuerpoEscala(formulario.getCkCuerpoEscala());
			formulario.setCkEjercicio(formulario.getCkEjercicio());
			formulario.setCkEstado(formulario.getCkEstado());
			formulario.setCkFechaBOE(formulario.getCkFechaBOE());
			formulario.setCkFormaAcceso(formulario.getCkFormaAcceso());
			formulario.setCkNumDescargasManuales(formulario.getCkNumDescargasManuales());
			formulario.setCkNumDescargasManualesTotal(formulario.isCkNumDescargasManualesTotal());
			formulario.setCkNumPagosSinIntentoRegistro(formulario.isCkNumPagosSinIntentoRegistro());
			formulario.setCkNumSolSinIntentoPago(formulario.isCkNumSolSinIntentoPago());
			formulario.setCkNumInscripcionSinPago(formulario.getCkNumInscripcionSinPago());			
			formulario.setCkNumPagosSinRegistro(formulario.getCkNumPagosSinRegistro());
			formulario.setCkNumPlazasDiscap(formulario.getCkNumPlazasDiscap());
			formulario.setCkNumPlazasTotales(formulario.getCkNumPlazasTotales());
			formulario.setCkNumSolIncPagoResuelta(formulario.getCkNumSolIncPagoResuelta());
			formulario.setCkNumSolIncPagoSinResolver(formulario.getCkNumSolIncPagoSinResolver());
			formulario.setCkNumSolIncRegistroResuelta(formulario.getCkNumSolIncRegistroResuelta());
			formulario.setCkNumSolIncRegistroSinResolver(formulario.getCkNumSolIncRegistroSinResolver());
			formulario.setCkNumSolPresenciales(formulario.getCkNumSolPresenciales());
			formulario.setCkNumSolTelematicas(formulario.getCkNumSolTelematicas());
			
		}
		// se calcula el numero de descargas del formulario en blanco
		if(formulario.isCkNumDescargasManualesTotal())
		{	
			DescargaModelo790Query descargaModelo790Query = new DescargaModelo790Query();
			descargaModelo790Query.setCalculateNumResults(true);
			descargaModelo790Query.setConvocatoriaIsNull(true);
			descargaModelo790Query.setFechaMin(utilesIPSG.stringToDate(formulario.getFechaDesde()));
			descargaModelo790Query.setFechaMax(utilesIPSG.stringToDate(formulario.getFechaHasta()));
			ArrayList<DescargaModelo790Bean> listaDescargas =descargaModelo790Manager.buscarDescargaModelo790All(descargaModelo790Query);
			int numeroDescargas =listaDescargas.size();
			setRequestAttribute("numeroDescargasModeloBlanco", numeroDescargas);
		}	
		
		//Parametros que muestran u ocultan los campos de la tabla de resultado
		setRequestAttribute("mostrarCentroGestor", formulario.getCkCentroGestor());
		setRequestAttribute("mostrarCuerpoEscala", formulario.getCkCuerpoEscala());
		setRequestAttribute("mostrarEjercicio", formulario.getCkEjercicio());
		setRequestAttribute("mostrarEstado", formulario.getCkEstado());
		setRequestAttribute("mostrarFechaBOE", formulario.getCkFechaBOE());
		setRequestAttribute("mostrarFormaAcceso", formulario.getCkFormaAcceso());
		setRequestAttribute("mostrarNumDescargasManuales", formulario.getCkNumDescargasManuales());
		setRequestAttribute("mostrarNumDescargasManualesTotal", formulario.isCkNumDescargasManualesTotal());
		setRequestAttribute("mostrarNumInscripcionSinPago", formulario.getCkNumInscripcionSinPago());		
		setRequestAttribute("mostrarNumPagosSinRegistro", formulario.getCkNumPagosSinRegistro());
		setRequestAttribute("mostrarNumPagosSinIntentoRegistro", formulario.isCkNumPagosSinIntentoRegistro());
		setRequestAttribute("mostrarNumSolSinIntentoPago", formulario.isCkNumSolSinIntentoPago());
		setRequestAttribute("mostrarNumPlazasDiscap", formulario.getCkNumPlazasDiscap());
		setRequestAttribute("mostrarNumPlazasTotales", formulario.getCkNumPlazasTotales());
		setRequestAttribute("mostrarNumSolIncPagoResuelta", formulario.getCkNumSolIncPagoResuelta());
		setRequestAttribute("mostrarNumSolIncPagoSinResolver", formulario.getCkNumSolIncPagoSinResolver());
		setRequestAttribute("mostrarNumSolIncRegistroResuelta", formulario.getCkNumSolIncRegistroResuelta());
		setRequestAttribute("mostrarNumSolIncRegistroSinResolver", formulario.getCkNumSolIncRegistroSinResolver());
		setRequestAttribute("mostrarNumSolPresenciales", formulario.getCkNumSolPresenciales());
		setRequestAttribute("mostrarNumSolTelematicas", formulario.getCkNumSolTelematicas());
		setRequestAttribute("sVieneMenu", sVieneMenu); //S = Viene del menu principal / N = Viene de cualquier otra pantalla.
	
	}catch(Exception eGenerico) {
		logger.error("Error BuscarCuadroMandoSpring - doExecute "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
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
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error BuscarCuadroMandoSpring()- recuperar UsuarioSesion: " + sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}

	/**
	 * Agrupa convocatorias.
	 *
	 * @param convocatoriasViewBean el convocatorias view bean
	 * @param formulario el formulario
	 * @return el list
	 */
	private List<ConvocatoriasViewBean> agrupaConvocatorias(
			List<ConvocatoriasViewBean> convocatoriasViewBean,
			ConsultarCuadroMandoForm formulario) {
		
				
		List<ConvocatoriasViewBean> resultado = new ArrayList<ConvocatoriasViewBean>();
		
		//Se crea la lista de visualizacion en orden inverso al mostrado en la tabla de resultados
		ArrayList<String> visualizacion = new ArrayList<String>();
		
		if(formulario.getCkEstado()){
			visualizacion.add(Constantes.CM_ESTADO_CONVOCATORIA);
		}
		
		if(formulario.getCkFechaBOE()){
			visualizacion.add(Constantes.CM_FECHA_BOE);
		}
		
		if(formulario.getCkFormaAcceso()){
			visualizacion.add(Constantes.CM_FORMA_ACCESO);
		}
		
		if(formulario.getCkCuerpoEscala()){
			visualizacion.add(Constantes.CM_CUERPO_ESCALA);
		}
		if(formulario.getCkEjercicio()){
			visualizacion.add(Constantes.CM_EJERCICIO);
		}
		if(formulario.getCkCentroGestor()){
			visualizacion.add(Constantes.CM_CENTRO_GESTOR);
		}
		
		visualizacion.add(Constantes.CM_MINISTERIO);
		
	
		boolean distinto= false;
		ConvocatoriasViewBean candidato = convocatoriasViewBean.get(0);
		
		int numTotalInscripcionSinPago = 0;
		int numTotalInscripcionSinIntentoPago = 0;
		int numTotalSolPresencial = 0;
	    int numTotalSolTelematica = 0;
	    int numTotalSolIncPagoResuelta = 0;
	    int numTotalSolIncPagoSinResolver = 0;
		int numTotalSolIncRegistroResuelta = 0;
		int numTotalSolIncRegistroSinResolver = 0;
		 int numTotalPagosSinRegistro = 0;
	    int numTotalPagosSinIntentoRegistro = 0;
	    int numTotalDescargasManuales = 0;
	    int numTotalPlazasTotales = 0;
	    int numTotalPlazasDiscapacitados = 0;
	  
		
	    int distanciaCandidato = 0; 
		for (ConvocatoriasViewBean convocatoria : convocatoriasViewBean) {
			
			distinto= false;
						
		    for (String vis : visualizacion) {
				//si son iguales continuamos
				if (!esIgual(vis, candidato, convocatoria)){
					distinto = true;
					break;
				}
			}
			//si es distinto
			if(distinto){
				//Comprobamos que candidato no sea convocatoria.
				if(distanciaCandidato > 0){
					//Anyadimos bean al listado resultado con los campos calculados
					if (formulario.isCkNumSolSinIntentoPago()){
						candidato.setNumSolSinIntentoPago(String.valueOf(numTotalInscripcionSinIntentoPago));
					}
					
					if (formulario.isCkNumPagosSinIntentoRegistro()){
						//Numero de Pagos Sin Registro
						candidato.setNumPagosSinIntentoRegistro(String.valueOf(numTotalPagosSinIntentoRegistro));
					}
					if (formulario.getCkNumInscripcionSinPago()){
						candidato.setNumInscripcionSinPago(String.valueOf(numTotalInscripcionSinPago));
					}
					
					if (formulario.getCkNumPagosSinRegistro()){
						//Numero de Pagos Sin Registro
						candidato.setNumPagosSinRegistro(String.valueOf(numTotalPagosSinRegistro));
					}
					
					if(formulario.getCkNumDescargasManuales() || formulario.isCkNumDescargasManualesTotal()){
						//Numero de Descargas Manuales Modelo 790
						candidato.setNumDescargasManuales(String.valueOf(numTotalDescargasManuales));
					}
					
					if (formulario.getCkNumSolTelematicas()){
						//Numero de solicitudes Telematicas
						candidato.setNumSolTelematica(String.valueOf(numTotalSolTelematica));
					}
					
					if(formulario.getCkNumSolPresenciales()){
						//Numero de solicitudes Presenciales
						candidato.setNumSolPresencial(String.valueOf(numTotalSolPresencial));
					}
					
					if(formulario.getCkNumSolIncPagoResuelta()){
						//Numero de Solicitudes con incidencias de Pago Resueltas
						candidato.setNumSolIncPagoResuelta(String.valueOf(numTotalSolIncPagoResuelta));
					}
					
					if(formulario.getCkNumSolIncPagoSinResolver()){
						//Numero de Solicitudes con incidencias de Pago Sin Resolver
						candidato.setNumSolIncPagoSinResolver(String.valueOf(numTotalSolIncPagoSinResolver));
					}
					
					if(formulario.getCkNumSolIncRegistroResuelta()){
						//Numero de Solicitudes con Incidencias de Registro Resuelta
						candidato.setNumSolIncRegistroResuelta(String.valueOf(numTotalSolIncRegistroResuelta));
					}
					
					if(formulario.getCkNumSolIncRegistroSinResolver()){
						//Numero de Solicitudes con Incidencias de Registro Sin Resolver
						candidato.setNumSolIncRegistroSinResolver(String.valueOf(numTotalSolIncRegistroSinResolver));
					}
					if(formulario.getCkNumPlazasTotales()){
						candidato.setPlazasTotal(numTotalPlazasTotales);
					}
					
					if(formulario.getCkNumPlazasDiscap()){
						candidato.setPlazasDiscapacitados(numTotalPlazasDiscapacitados);
					}
				}
				
				
				resultado.add(candidato);
				//inicializamos los campos calculados
				numTotalInscripcionSinPago = 0;
				numTotalInscripcionSinIntentoPago = 0;
				numTotalSolPresencial = 0;
			    numTotalSolTelematica = 0;
			    numTotalSolIncPagoResuelta = 0;
			    numTotalSolIncPagoSinResolver = 0;
				numTotalSolIncRegistroResuelta = 0;
				numTotalSolIncRegistroSinResolver = 0;
				numTotalPagosSinRegistro = 0;
			    numTotalPagosSinIntentoRegistro = 0;
			    numTotalDescargasManuales = 0;
			    numTotalPlazasTotales = 0;
			    numTotalPlazasDiscapacitados = 0;
			    candidato = convocatoria;
			    
			    //Sumamos los valores del candidato
			    int numInscripcionSinPago = 0;
			    int numInscripcionSinIntentoPago = 0;
				if (candidato.getNumInscripcionSinPago()!=null && !"".equals( candidato.getNumInscripcionSinPago())){
					numInscripcionSinPago = Integer.valueOf(candidato.getNumInscripcionSinPago()).intValue();
				}
				
				if (candidato.getNumSolSinIntentoPago()!=null && !"".equals(candidato.getNumSolSinIntentoPago())){
					numInscripcionSinIntentoPago = Integer.valueOf(candidato.getNumSolSinIntentoPago()).intValue();
				}
				int numSolPresencial = 0;
				if (candidato.getNumSolPresencial()!=null && !"".equals( candidato.getNumSolPresencial())){
					numSolPresencial = Integer.valueOf(candidato.getNumSolPresencial()).intValue();
				}
			    int numSolTelematica = 0;
			    if (candidato.getNumSolTelematica()!=null && !"".equals( candidato.getNumSolTelematica())){
			    	numSolTelematica = Integer.valueOf(candidato.getNumSolTelematica()).intValue();
				}
			    
			    int numSolIncPagoResuelta = 0;
			    if (candidato.getNumSolIncPagoResuelta()!=null && !"".equals( candidato.getNumSolIncPagoResuelta())){
			    	numSolIncPagoResuelta = Integer.valueOf(candidato.getNumSolIncPagoResuelta()).intValue();
				}
			    int numSolIncPagoSinResolver = 0;
			    if (candidato.getNumSolIncPagoSinResolver()!=null && !"".equals( candidato.getNumSolIncPagoSinResolver())){
			    	numSolIncPagoSinResolver = Integer.valueOf(candidato.getNumSolIncPagoSinResolver()).intValue();
				}
				int numSolIncRegistroResuelta = 0;
				if (candidato.getNumSolIncRegistroResuelta()!=null && !"".equals( candidato.getNumSolIncRegistroResuelta())){
					numSolIncRegistroResuelta = Integer.valueOf(candidato.getNumSolIncRegistroResuelta()).intValue();
				}
				int numSolIncRegistroSinResolver = 0;
				if (candidato.getNumSolIncRegistroSinResolver()!=null && !"".equals( candidato.getNumSolIncRegistroSinResolver())){
					numSolIncRegistroSinResolver = Integer.valueOf(candidato.getNumSolIncRegistroSinResolver()).intValue();
				}
				int numPagosSinRegistro = 0;
			    int numPagosSinIntentoRegistro = 0;
			    if (candidato.getNumPagosSinRegistro()!=null && !"".equals( candidato.getNumPagosSinRegistro())){
			    	numPagosSinRegistro = Integer.valueOf(candidato.getNumPagosSinRegistro()).intValue();
				}
			    if (candidato.getNumPagosSinIntentoRegistro()!=null && !"".equals( candidato.getNumPagosSinIntentoRegistro())){
			    	numPagosSinIntentoRegistro = Integer.valueOf(candidato.getNumPagosSinIntentoRegistro()).intValue();
				}
			    int numDescargasManuales = 0;
			    if (candidato.getNumDescargasManuales()!=null && !"".equals( candidato.getNumDescargasManuales())){
			    	numDescargasManuales = Integer.valueOf(candidato.getNumDescargasManuales()).intValue();
				}
			    int numPlazasTotales = candidato.getPlazasTotal();

			    int numPlazasDiscapacitados = candidato.getPlazasDiscapacitados();
			    
			    numTotalInscripcionSinPago += numInscripcionSinPago;	
			    numTotalInscripcionSinIntentoPago += numInscripcionSinIntentoPago;
				numTotalSolPresencial += numSolPresencial;
			    numTotalSolTelematica += numSolTelematica;
			    numTotalSolIncPagoResuelta += numSolIncPagoResuelta;
			    numTotalSolIncPagoSinResolver += numSolIncPagoSinResolver;
				numTotalSolIncRegistroResuelta += numSolIncRegistroResuelta;
				numTotalSolIncRegistroSinResolver += numSolIncRegistroSinResolver;
				 numTotalPagosSinRegistro += numPagosSinRegistro;
			    numTotalPagosSinIntentoRegistro += numPagosSinIntentoRegistro;
			    numTotalDescargasManuales += numDescargasManuales;
			    numTotalPlazasTotales += numPlazasTotales;
			    numTotalPlazasDiscapacitados += numPlazasDiscapacitados;
			    			    
			    distanciaCandidato = 0;
			    
			}else{ //Sumamos y seguimos con otra convocatoria
			
				
				int numInscripcionSinPago = 0;
				if (convocatoria.getNumInscripcionSinPago()!=null && !"".equals( convocatoria.getNumInscripcionSinPago())){
					numInscripcionSinPago = Integer.valueOf(convocatoria.getNumInscripcionSinPago()).intValue();
				}
				
				int numInscripcionSinIntentoPago = 0;
				if (convocatoria.getNumSolSinIntentoPago()!=null && !"".equals( convocatoria.getNumSolSinIntentoPago())){
					numInscripcionSinIntentoPago = Integer.valueOf(convocatoria.getNumSolSinIntentoPago()).intValue();
				}
				
				int numSolPresencial = 0;
				if (convocatoria.getNumSolPresencial()!=null && !"".equals( convocatoria.getNumSolPresencial())){
					numSolPresencial = Integer.valueOf(convocatoria.getNumSolPresencial()).intValue();
				}
			    int numSolTelematica = 0;
			    if (convocatoria.getNumSolTelematica()!=null && !"".equals( convocatoria.getNumSolTelematica())){
			    	numSolTelematica = Integer.valueOf(convocatoria.getNumSolTelematica()).intValue();
				}
			    
			    int numSolIncPagoResuelta = 0;
			    if (convocatoria.getNumSolIncPagoResuelta()!=null && !"".equals( convocatoria.getNumSolIncPagoResuelta())){
			    	numSolIncPagoResuelta = Integer.valueOf(convocatoria.getNumSolIncPagoResuelta()).intValue();
				}
			    int numSolIncPagoSinResolver = 0;
			    if (convocatoria.getNumSolIncPagoSinResolver()!=null && !"".equals( convocatoria.getNumSolIncPagoSinResolver())){
			    	numSolIncPagoSinResolver = Integer.valueOf(convocatoria.getNumSolIncPagoSinResolver()).intValue();
				}
				int numSolIncRegistroResuelta = 0;
				if (convocatoria.getNumSolIncRegistroResuelta()!=null && !"".equals( convocatoria.getNumSolIncRegistroResuelta())){
					numSolIncRegistroResuelta = Integer.valueOf(convocatoria.getNumSolIncRegistroResuelta()).intValue();
				}
				int numSolIncRegistroSinResolver = 0;
				if (convocatoria.getNumSolIncRegistroSinResolver()!=null && !"".equals( convocatoria.getNumSolIncRegistroSinResolver())){
					numSolIncRegistroSinResolver = Integer.valueOf(convocatoria.getNumSolIncRegistroSinResolver()).intValue();
				}
			    int numPagosSinRegistro = 0;
			    if (convocatoria.getNumPagosSinRegistro()!=null && !"".equals( convocatoria.getNumPagosSinRegistro())){
			    	numPagosSinRegistro = Integer.valueOf(convocatoria.getNumPagosSinRegistro()).intValue();
				}
				
				 int numPagosSinIntentoRegistro = 0;
			    if (convocatoria.getNumPagosSinIntentoRegistro()!=null && !"".equals( convocatoria.getNumPagosSinIntentoRegistro())){
			    	numPagosSinIntentoRegistro = Integer.valueOf(convocatoria.getNumPagosSinIntentoRegistro()).intValue();
				}
			    int numDescargasManuales = 0;
			    if (convocatoria.getNumDescargasManuales()!=null && !"".equals( convocatoria.getNumDescargasManuales())){
			    	numDescargasManuales = Integer.valueOf(convocatoria.getNumDescargasManuales()).intValue();
				}
			    int numPlazasTotales = convocatoria.getPlazasTotal();
			    int numPlazasDiscapacitados = convocatoria.getPlazasDiscapacitados();
				
				numTotalInscripcionSinPago += numInscripcionSinPago;
			    numTotalInscripcionSinIntentoPago += numInscripcionSinIntentoPago;
				numTotalSolPresencial += numSolPresencial;
			    numTotalSolTelematica += numSolTelematica;
			    numTotalSolIncPagoResuelta += numSolIncPagoResuelta;
			    numTotalSolIncPagoSinResolver += numSolIncPagoSinResolver;
				numTotalSolIncRegistroResuelta += numSolIncRegistroResuelta;
				numTotalSolIncRegistroSinResolver += numSolIncRegistroSinResolver;
				numTotalPagosSinRegistro += numPagosSinRegistro;
			    numTotalPagosSinIntentoRegistro += numPagosSinIntentoRegistro;
			    numTotalDescargasManuales += numDescargasManuales;
			    numTotalPlazasTotales += numPlazasTotales;
			    numTotalPlazasDiscapacitados += numPlazasDiscapacitados;
				
			    distanciaCandidato++;			    
				
			}
			
		}
		
		//Anyadimos el ultimo registro
		
		
			//Comprobamos que candidato no sea convocatoria.
			if(distanciaCandidato > 0){
				//Anyadimos bean al listado resultado con los campos calculados
				if (formulario.getCkNumInscripcionSinPago()){
					candidato.setNumInscripcionSinPago(String.valueOf(numTotalInscripcionSinPago));
				}
				
				if (formulario.isCkNumSolSinIntentoPago()){
					candidato.setNumSolSinIntentoPago(String.valueOf(numTotalInscripcionSinIntentoPago));
				}
				
				if (formulario.getCkNumPagosSinRegistro()){
					//Numero de Pagos Sin Registro
					candidato.setNumPagosSinRegistro(String.valueOf(numTotalPagosSinRegistro));
				}
				
				if (formulario.isCkNumPagosSinIntentoRegistro()){
					//Numero de Pagos Sin Registro
					candidato.setNumPagosSinIntentoRegistro(String.valueOf(numTotalPagosSinIntentoRegistro));
				}
				
				if(formulario.getCkNumDescargasManuales() || formulario.isCkNumDescargasManualesTotal() ){
					//Numero de Descargas Manuales Modelo 790
					candidato.setNumDescargasManuales(String.valueOf(numTotalDescargasManuales));
				}
				
				if (formulario.getCkNumSolTelematicas()){
					//Numero de solicitudes Telematicas
					candidato.setNumSolTelematica(String.valueOf(numTotalSolTelematica));
				}
				
				if(formulario.getCkNumSolPresenciales()){
					//Numero de solicitudes Presenciales
					candidato.setNumSolPresencial(String.valueOf(numTotalSolPresencial));
				}
				
				if(formulario.getCkNumSolIncPagoResuelta()){
					//Numero de Solicitudes con incidencias de Pago Resueltas
					candidato.setNumSolIncPagoResuelta(String.valueOf(numTotalSolIncPagoResuelta));
				}
				
				if(formulario.getCkNumSolIncPagoSinResolver()){
					//Numero de Solicitudes con incidencias de Pago Sin Resolver
					candidato.setNumSolIncPagoSinResolver(String.valueOf(numTotalSolIncPagoSinResolver));
				}
				
				if(formulario.getCkNumSolIncRegistroResuelta()){
					//Numero de Solicitudes con Incidencias de Registro Resuelta
					candidato.setNumSolIncRegistroResuelta(String.valueOf(numTotalSolIncRegistroResuelta));
				}
				
				if(formulario.getCkNumSolIncRegistroSinResolver()){
					//Numero de Solicitudes con Incidencias de Registro Sin Resolver
					candidato.setNumSolIncRegistroSinResolver(String.valueOf(numTotalSolIncRegistroSinResolver));
				}
				
				if(formulario.getCkNumPlazasTotales()){
					//Numero de Plazas Totales de la convocatoria
					candidato.setPlazasTotal(numTotalPlazasTotales);
				}
				
				if(formulario.getCkNumPlazasDiscap()){
					//Numero de Plazas Discapacitados de la convocatoria
					candidato.setPlazasDiscapacitados(numTotalPlazasDiscapacitados);
				}
			}
			
			
			resultado.add(candidato);
			
	
		
		
		
		return resultado;
	}
	
	
	/**
	 * Calcula resto.
	 *
	 * @param convocatoriasViewBean el convocatorias view bean
	 * @param formulario el formulario
	 * @return el list
	 */
	private List<ConvocatoriasViewBean> calculaResto(
			List<ConvocatoriasViewBean> convocatoriasViewBean,
			ConsultarCuadroMandoForm formulario) {
		
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		
		List<ConvocatoriasViewBean> resultado = new ArrayList<ConvocatoriasViewBean>();
		
		
		ConvocatoriasViewBean convocatoriaAnt = convocatoriasViewBean.get(0);
		int numTotalInscripcionSinPago = 0;
		int numTotalInscripcionSinIntentoPago = 0;
		int numTotalSolPresencial = 0;
	    int numTotalSolTelematica = 0;
	    int numTotalSolIncPagoResuelta = 0;
	    int numTotalSolIncPagoSinResolver = 0;
		int numTotalSolIncRegistroResuelta = 0;
		int numTotalSolIncRegistroSinResolver = 0;
		int numTotalPagosSinRegistro = 0;
	    int numTotalPagosSinIntentoRegistro = 0;
	    int numTotalDescargasManuales = 0;
	    int numTotalPlazasTotales = 0;
	    int numTotalPlazasDiscapacitados = 0;
	    int contador = 0;
	    
	    
		for (ConvocatoriasViewBean convocatoria : convocatoriasViewBean) {
			
			contador++;
			
		//INI CAMBIOS NUEVOS	
			if(contador > 1){
				int numInscripcionSinPago = 0;
				if (convocatoriaAnt.getNumInscripcionSinPago()!=null && !"".equals( convocatoriaAnt.getNumInscripcionSinPago())){
					numInscripcionSinPago = Integer.valueOf(convocatoriaAnt.getNumInscripcionSinPago()).intValue();
				}
				
				int numInscripcionSinIntentoPago = 0;
				if (convocatoriaAnt.getNumSolSinIntentoPago()!=null && !"".equals( convocatoriaAnt.getNumSolSinIntentoPago())){
					numInscripcionSinIntentoPago = Integer.valueOf(convocatoriaAnt.getNumSolSinIntentoPago()).intValue();
				}
				int numSolPresencial = 0;
				if (convocatoriaAnt.getNumSolPresencial()!=null && !"".equals( convocatoriaAnt.getNumSolPresencial())){
					numSolPresencial = Integer.valueOf(convocatoriaAnt.getNumSolPresencial()).intValue();
				}
			    int numSolTelematica = 0;
			    if (convocatoriaAnt.getNumSolTelematica()!=null && !"".equals( convocatoriaAnt.getNumSolTelematica())){
			    	numSolTelematica = Integer.valueOf(convocatoriaAnt.getNumSolTelematica()).intValue();
				}
			    
			    int numSolIncPagoResuelta = 0;
			    if (convocatoriaAnt.getNumSolIncPagoResuelta()!=null && !"".equals( convocatoriaAnt.getNumSolIncPagoResuelta())){
			    	numSolIncPagoResuelta = Integer.valueOf(convocatoriaAnt.getNumSolIncPagoResuelta()).intValue();
				}
			    int numSolIncPagoSinResolver = 0;
			    if (convocatoriaAnt.getNumSolIncPagoSinResolver()!=null && !"".equals( convocatoriaAnt.getNumSolIncPagoSinResolver())){
			    	numSolIncPagoSinResolver = Integer.valueOf(convocatoriaAnt.getNumSolIncPagoSinResolver()).intValue();
				}
				int numSolIncRegistroResuelta = 0;
				if (convocatoriaAnt.getNumSolIncRegistroResuelta()!=null && !"".equals( convocatoriaAnt.getNumSolIncRegistroResuelta())){
					numSolIncRegistroResuelta = Integer.valueOf(convocatoriaAnt.getNumSolIncRegistroResuelta()).intValue();
				}
				int numSolIncRegistroSinResolver = 0;
				if (convocatoriaAnt.getNumSolIncRegistroSinResolver()!=null && !"".equals( convocatoriaAnt.getNumSolIncRegistroSinResolver())){
					numSolIncRegistroSinResolver = Integer.valueOf(convocatoriaAnt.getNumSolIncRegistroSinResolver()).intValue();
				}
			    int numPagosSinRegistro = 0;
			    if (convocatoriaAnt.getNumPagosSinRegistro()!=null && !"".equals( convocatoriaAnt.getNumPagosSinRegistro())){
			    	numPagosSinRegistro = Integer.valueOf(convocatoriaAnt.getNumPagosSinRegistro()).intValue();
				}
			int numPagosSinIntentoRegistro = 0;	
			    if (convocatoriaAnt.getNumPagosSinIntentoRegistro()!=null && !"".equals( convocatoriaAnt.getNumPagosSinIntentoRegistro())){
			    	numPagosSinIntentoRegistro = Integer.valueOf(convocatoriaAnt.getNumPagosSinIntentoRegistro()).intValue();
				}
			    int numDescargasManuales = 0;
			    if (convocatoriaAnt.getNumDescargasManuales()!=null && !"".equals( convocatoriaAnt.getNumDescargasManuales())){
			    	numDescargasManuales = Integer.valueOf(convocatoriaAnt.getNumDescargasManuales()).intValue();
				}
			    
			    int numPlazasTotales = convocatoriaAnt.getPlazasTotal();
			    int numPlazasDiscapacitados = convocatoriaAnt.getPlazasDiscapacitados();
			
			numTotalInscripcionSinPago += numInscripcionSinPago;	
			    numTotalInscripcionSinIntentoPago += numInscripcionSinIntentoPago;
				numTotalSolPresencial += numSolPresencial;
			    numTotalSolTelematica += numSolTelematica;
			    numTotalSolIncPagoResuelta += numSolIncPagoResuelta;
			    numTotalSolIncPagoSinResolver += numSolIncPagoSinResolver;
				numTotalSolIncRegistroResuelta += numSolIncRegistroResuelta;
				numTotalSolIncRegistroSinResolver += numSolIncRegistroSinResolver;
			   numTotalPagosSinRegistro += numPagosSinRegistro;
			    numTotalPagosSinIntentoRegistro += numPagosSinIntentoRegistro;
			    numTotalDescargasManuales += numDescargasManuales;
			    numTotalPlazasTotales += numPlazasTotales;
			    numTotalPlazasDiscapacitados += numPlazasDiscapacitados;
			}
			
			
		//FIN CAMBIOS NUEVOS	
			
			
			//si es distino anyade registro con los totales.
			if(convocatoriaAnt.getIdMinisterio().intValue() != convocatoria.getIdMinisterio().intValue()  ){
				ConvocatoriasViewBean convocatoriasViewBeanTotales = new ConvocatoriasViewBean();
				convocatoriasViewBeanTotales.setDesCentroGestor(STRINGTOTAL);
				//Comprobamos que candidato no sea convocatoria.
			
					
					
					//recuperamos el registro anterior para introducir los totales
					
					convocatoriasViewBeanTotales.setNumTotalInscripcionSinPago(String.valueOf(numTotalInscripcionSinPago));
					convocatoriasViewBeanTotales.setNumTotalInscripcionSinIntentoPago(String.valueOf(numTotalInscripcionSinIntentoPago));
					convocatoriasViewBeanTotales.setNumTotalSolPresencial(String.valueOf(numTotalSolPresencial));
					convocatoriasViewBeanTotales.setNumTotalSolTelematica(String.valueOf(numTotalSolTelematica));
					convocatoriasViewBeanTotales.setNumTotalSolIncPagoResuelta(String.valueOf(numTotalSolIncPagoResuelta));
					convocatoriasViewBeanTotales.setNumTotalSolIncPagoSinResolver(String.valueOf(numTotalSolIncPagoSinResolver));
					convocatoriasViewBeanTotales.setNumTotalSolIncRegistroResuelta(String.valueOf(numTotalSolIncRegistroResuelta));
					convocatoriasViewBeanTotales.setNumTotalSolIncRegistroSinResolver(String.valueOf(numTotalSolIncRegistroSinResolver));
					convocatoriasViewBeanTotales.setNumTotalPagosSinRegistro(String.valueOf(numTotalPagosSinRegistro));
					convocatoriasViewBeanTotales.setNumTotalPagosSinIntentoRegistro(String.valueOf(numTotalPagosSinIntentoRegistro));
					convocatoriasViewBeanTotales.setNumTotalDescargasManuales(String.valueOf(numTotalDescargasManuales));
					convocatoriasViewBeanTotales.setNumTotalPlazasTotales(numTotalPlazasTotales);
					convocatoriasViewBeanTotales.setNumTotalPlazasDiscapacitados(numTotalPlazasDiscapacitados);
					
					
	
				//Validamos "Si ha sustituido a otro Ministerio"
			    //Si existe Fecha de Sustitucion existe y 'Fecha Desde' nula o cumple la condicion:
			    //Calculamos si la Fecha de Sustitucion es posterior a la Fecha Desde introducida en la busqueda
			    //Mostraria el mensaje "Puede consultar los datos de las convocatorias previas a la fecha de sustitucion consultando los datos del Ministerio al que sustituye"
			    MinisterioQuery ministerioQuery = new  MinisterioQuery();
			    MinisterioBean ministerioBean;
			    ministerioQuery.setId(convocatoriaAnt.getIdMinisterio());
			    ministerioBean = ministeriosManager.buscarMinisterio(ministerioQuery);
			    
			    
			   Date dFechaSustitucion =  ministerioBean.getFechaSustitucion();
			   String sFechaSustitucion = utilesIPSG.dateToString(ministerioBean.getFechaSustitucion());
			   String sFechaDesde = formulario.getFechaDesde();
			   String sFechaHasta = formulario.getFechaHasta();
			   Date dFechaDesde = utilesIPSG.stringToDate(formulario.getFechaDesde());
			   Date dFechaHasta = utilesIPSG.stringToDate(formulario.getFechaHasta());
			MinisterioBean ministerioBeanSust = null;
			 if(ministerioBean.getIdMinisterioPrevio() != null && !"".equals(ministerioBean.getIdMinisterioPrevio().toString())){
			   MinisterioQuery ministerioQuerySust = new MinisterioQuery();
			   ministerioQuerySust.setId(ministerioBean.getIdMinisterioPrevio());
			   ministerioBeanSust = ministeriosManager.buscarMinisterio(ministerioQuerySust);
			 }
			   if (sFechaSustitucion != null && !sFechaSustitucion.equals(""))
			   {
				   	if(sFechaDesde == null || sFechaDesde.equals(""))
				   	{
				   		convocatoriasViewBeanTotales.setSustituidoAOtro("S");
				   		if(ministerioBeanSust!= null){
				   			convocatoriasViewBeanTotales.setDesMinisterioSust(ministerioBeanSust.getDescripcion());
				   		}
				   		convocatoriasViewBeanTotales.setFechaSustitucion(sFechaSustitucion);
				   	}else
				   	{
						
						if(dFechaDesde.before(dFechaSustitucion))
						{
							convocatoriasViewBeanTotales.setSustituidoAOtro("S");
							if(ministerioBeanSust!= null){
								convocatoriasViewBeanTotales.setDesMinisterioSust(ministerioBeanSust.getDescripcion());
							}
							convocatoriasViewBeanTotales.setFechaSustitucion(sFechaSustitucion);							
						}
				   	}
				   	
				    //Validamos "Si ha sido sustituido por otro Ministerio"
				    //Si existe la Fecha de Sustitucion existe y 'Fecha Hasta' nula o cumple la condicion:
				    //Fecha de Sustitucion es anterior a la Fecha Hasta introducida en la busqueda.
				    //Mostraria el mensaje: "Puede consultar los datos de las convocatorias posteriores a la fecha de sustitucion consultando los datos del Ministerio que lo ha sustituido"
				   	
				   	if(sFechaHasta == null || sFechaHasta.equals(""))
				   	{
				   		
				   		MinisterioQuery ministerioQuerySustituto = new MinisterioQuery();
						MinisterioBean ministerioBeanSustituto = null;
						ministerioQuerySustituto.setIdMinisterioPrevio(ministerioBean.getId());
						ministerioBeanSustituto = ministeriosManager.buscarMinisterio(ministerioQuerySustituto);
						String sFechaSustitucion2 = utilesIPSG.dateToString(ministerioBeanSustituto.getFechaSustitucion());
						convocatoriasViewBeanTotales.setSustituidoPorOtro("S");
						convocatoriasViewBeanTotales.setDesMinisterioSustitutivo(ministerioBeanSustituto.getDescripcion());
						convocatoriasViewBeanTotales.setFechaSustitucion2(sFechaSustitucion2);
				   	}else
				   	{
						
						if(dFechaHasta.after(dFechaSustitucion))
						{
							MinisterioQuery ministerioQuerySustituto = new MinisterioQuery();
							MinisterioBean ministerioBeanSustituto = null;
							ministerioQuerySustituto.setIdMinisterioPrevio(ministerioBean.getId());
							ministerioBeanSustituto = ministeriosManager.buscarMinisterio(ministerioQuerySustituto);
							String sFechaSustitucion2 = utilesIPSG.dateToString(ministerioBeanSustituto.getFechaSustitucion());
							convocatoriasViewBeanTotales.setSustituidoPorOtro("S");
							convocatoriasViewBeanTotales.setDesMinisterioSustitutivo(ministerioBeanSustituto.getDescripcion());
							convocatoriasViewBeanTotales.setFechaSustitucion2(sFechaSustitucion2);
						}
				   	}
				   	
				}
				
				
				resultado.add(convocatoriasViewBeanTotales);
				//inicializamos los campos calculados
				numTotalInscripcionSinPago = 0;
				numTotalInscripcionSinIntentoPago = 0;
				numTotalSolPresencial = 0;
			    numTotalSolTelematica = 0;
			    numTotalSolIncPagoResuelta = 0;
			    numTotalSolIncPagoSinResolver = 0;
				numTotalSolIncRegistroResuelta = 0;
				numTotalSolIncRegistroSinResolver = 0;
			    numTotalPagosSinRegistro = 0;
			    numTotalPagosSinIntentoRegistro = 0;
			    numTotalDescargasManuales = 0;
			    numTotalPlazasTotales = 0;
			    numTotalPlazasDiscapacitados = 0;
			    
			}else{ //Si es el mismo ministerio seguimos sumando
				
				
				
			}
			convocatoriaAnt = convocatoria;
			resultado.add(convocatoria);
			
		}
		
		
		//Ponemos los totales del ultimo ministerio
		
		int numInscripcionSinPago = 0;
		if (convocatoriaAnt.getNumInscripcionSinPago()!=null && !"".equals( convocatoriaAnt.getNumInscripcionSinPago())){
			numInscripcionSinPago = Integer.valueOf(convocatoriaAnt.getNumInscripcionSinPago()).intValue();
		}
		int numInscripcionSinIntentoPago = 0;
		if (convocatoriaAnt.getNumSolSinIntentoPago()!=null && !"".equals( convocatoriaAnt.getNumSolSinIntentoPago())){
			numInscripcionSinIntentoPago = Integer.valueOf(convocatoriaAnt.getNumSolSinIntentoPago()).intValue();
		}
		int numSolPresencial = 0;
		if (convocatoriaAnt.getNumSolPresencial()!=null && !"".equals( convocatoriaAnt.getNumSolPresencial())){
			numSolPresencial = Integer.valueOf(convocatoriaAnt.getNumSolPresencial()).intValue();
		}
	    int numSolTelematica = 0;
	    if (convocatoriaAnt.getNumSolTelematica()!=null && !"".equals( convocatoriaAnt.getNumSolTelematica())){
	    	numSolTelematica = Integer.valueOf(convocatoriaAnt.getNumSolTelematica()).intValue();
		}
	    
	    int numSolIncPagoResuelta = 0;
	    if (convocatoriaAnt.getNumSolIncPagoResuelta()!=null && !"".equals( convocatoriaAnt.getNumSolIncPagoResuelta())){
	    	numSolIncPagoResuelta = Integer.valueOf(convocatoriaAnt.getNumSolIncPagoResuelta()).intValue();
		}
	    int numSolIncPagoSinResolver = 0;
	    if (convocatoriaAnt.getNumSolIncPagoSinResolver()!=null && !"".equals( convocatoriaAnt.getNumSolIncPagoSinResolver())){
	    	numSolIncPagoSinResolver = Integer.valueOf(convocatoriaAnt.getNumSolIncPagoSinResolver()).intValue();
		}
		int numSolIncRegistroResuelta = 0;
		if (convocatoriaAnt.getNumSolIncRegistroResuelta()!=null && !"".equals( convocatoriaAnt.getNumSolIncRegistroResuelta())){
			numSolIncRegistroResuelta = Integer.valueOf(convocatoriaAnt.getNumSolIncRegistroResuelta()).intValue();
		}
		int numSolIncRegistroSinResolver = 0;
		if (convocatoriaAnt.getNumSolIncRegistroSinResolver()!=null && !"".equals( convocatoriaAnt.getNumSolIncRegistroSinResolver())){
			numSolIncRegistroSinResolver = Integer.valueOf(convocatoriaAnt.getNumSolIncRegistroSinResolver()).intValue();
		}
	    int numPagosSinRegistro = 0;
	    if (convocatoriaAnt.getNumPagosSinRegistro()!=null && !"".equals( convocatoriaAnt.getNumPagosSinRegistro())){
	    	numPagosSinRegistro = Integer.valueOf(convocatoriaAnt.getNumPagosSinRegistro()).intValue();
		}
		int numPagosSinIntentoRegistro = 0;	
	    if (convocatoriaAnt.getNumPagosSinIntentoRegistro()!=null && !"".equals( convocatoriaAnt.getNumPagosSinIntentoRegistro())){
	    	numPagosSinIntentoRegistro = Integer.valueOf(convocatoriaAnt.getNumPagosSinIntentoRegistro()).intValue();
		}
	    int numDescargasManuales = 0;
	    if (convocatoriaAnt.getNumDescargasManuales()!=null && !"".equals( convocatoriaAnt.getNumDescargasManuales())){
	    	numDescargasManuales = Integer.valueOf(convocatoriaAnt.getNumDescargasManuales()).intValue();
		}
	    
	    int numPlazasTotales = convocatoriaAnt.getPlazasTotal();	    
	    int numPlazasDiscapacitados = convocatoriaAnt.getPlazasDiscapacitados();
		
	    numTotalInscripcionSinPago += numInscripcionSinPago;	
	    numTotalInscripcionSinIntentoPago += numInscripcionSinIntentoPago;
		numTotalSolPresencial += numSolPresencial;
	    numTotalSolTelematica += numSolTelematica;
	    numTotalSolIncPagoResuelta += numSolIncPagoResuelta;
	    numTotalSolIncPagoSinResolver += numSolIncPagoSinResolver;
		numTotalSolIncRegistroResuelta += numSolIncRegistroResuelta;
		numTotalSolIncRegistroSinResolver += numSolIncRegistroSinResolver;
	    numTotalPagosSinRegistro += numPagosSinRegistro;
	    numTotalPagosSinIntentoRegistro += numPagosSinIntentoRegistro;
	    numTotalDescargasManuales += numDescargasManuales;
	    numTotalPlazasTotales += numPlazasTotales;
	    numTotalPlazasDiscapacitados += numPlazasDiscapacitados;
		
		
		
		ConvocatoriasViewBean convocatoriasViewBeanTotales = new ConvocatoriasViewBean();
		convocatoriasViewBeanTotales.setDesCentroGestor(STRINGTOTAL);
		//Comprobamos que candidato no sea convocatoria.
	
			
			
			//recuperamos el registro anterior para introducir los totales
				convocatoriasViewBeanTotales.setNumTotalInscripcionSinIntentoPago(String.valueOf(numTotalInscripcionSinIntentoPago));
			convocatoriasViewBeanTotales.setNumTotalInscripcionSinPago(String.valueOf(numTotalInscripcionSinPago));
			convocatoriasViewBeanTotales.setNumTotalSolPresencial(String.valueOf(numTotalSolPresencial));
			convocatoriasViewBeanTotales.setNumTotalSolTelematica(String.valueOf(numTotalSolTelematica));
			convocatoriasViewBeanTotales.setNumTotalSolIncPagoResuelta(String.valueOf(numTotalSolIncPagoResuelta));
			convocatoriasViewBeanTotales.setNumTotalSolIncPagoSinResolver(String.valueOf(numTotalSolIncPagoSinResolver));
			convocatoriasViewBeanTotales.setNumTotalSolIncRegistroResuelta(String.valueOf(numTotalSolIncRegistroResuelta));
			convocatoriasViewBeanTotales.setNumTotalSolIncRegistroSinResolver(String.valueOf(numTotalSolIncRegistroSinResolver));
			convocatoriasViewBeanTotales.setNumTotalPagosSinRegistro(String.valueOf(numTotalPagosSinRegistro));
			convocatoriasViewBeanTotales.setNumTotalPagosSinIntentoRegistro(String.valueOf(numTotalPagosSinIntentoRegistro));
			convocatoriasViewBeanTotales.setNumTotalDescargasManuales(String.valueOf(numTotalDescargasManuales));
			convocatoriasViewBeanTotales.setNumTotalPlazasTotales(numTotalPlazasTotales);
			convocatoriasViewBeanTotales.setNumTotalPlazasDiscapacitados(numTotalPlazasDiscapacitados);
			
	
		//Validamos "Si ha sustituido a otro Ministerio"
	    //Si existe Fecha de Sustitucion existe y 'Fecha Desde' nula o cumple la condicion:
	    //Calculamos si la Fecha de Sustitucion es posterior a la Fecha Desde introducida en la busqueda
	    //Mostraria el mensaje "Puede consultar los datos de las convocatorias previas a la fecha de sustitucion consultando los datos del Ministerio al que sustituye"
	    MinisterioQuery ministerioQuery = new  MinisterioQuery();
	    MinisterioBean ministerioBean;
	    ministerioQuery.setId(convocatoriaAnt.getIdMinisterio());
	    ministerioBean = ministeriosManager.buscarMinisterio(ministerioQuery);
	    
	    
	   Date dFechaSustitucion =  ministerioBean.getFechaSustitucion();
	   String sFechaSustitucion = utilesIPSG.dateToString(ministerioBean.getFechaSustitucion());
	   String sFechaDesde = formulario.getFechaDesde();
	   String sFechaHasta = formulario.getFechaHasta();
	   Date dFechaDesde = utilesIPSG.stringToDate(formulario.getFechaDesde());
	   Date dFechaHasta = utilesIPSG.stringToDate(formulario.getFechaHasta());
	   
	   MinisterioBean ministerioBeanSust = null;
		 if(ministerioBean.getIdMinisterioPrevio() != null && !"".equals(ministerioBean.getIdMinisterioPrevio().toString())){
		   MinisterioQuery ministerioQuerySust = new MinisterioQuery();
		   ministerioQuerySust.setId(ministerioBean.getIdMinisterioPrevio());
		   ministerioBeanSust = ministeriosManager.buscarMinisterio(ministerioQuerySust);
		 }
		 
	   if (sFechaSustitucion != null && !sFechaSustitucion.equals(""))
	   {
		   	if(sFechaDesde == null || sFechaDesde.equals(""))
		   	{
		   		convocatoriasViewBeanTotales.setSustituidoAOtro("S");
		   		if(ministerioBeanSust!= null){
		   			convocatoriasViewBeanTotales.setDesMinisterioSust(ministerioBeanSust.getDescripcion());
		   		}
		   		convocatoriasViewBeanTotales.setFechaSustitucion(sFechaSustitucion);
		   	}else
		   	{
				//dFechaDesde < dFechaSustitucion
				if(dFechaDesde.before(dFechaSustitucion))
				{
					convocatoriasViewBeanTotales.setSustituidoAOtro("S");
					if(ministerioBeanSust!= null){
			   			convocatoriasViewBeanTotales.setDesMinisterioSust(ministerioBeanSust.getDescripcion());
			   		}
			   		convocatoriasViewBeanTotales.setFechaSustitucion(sFechaSustitucion);
				}
		   	}
		   	
		    //Validamos "Si ha sido sustituido por otro Ministerio"
		    //Si existe la Fecha de Sustitucion existe y 'Fecha Hasta' nula o cumple la condicion:
		    //Fecha de Sustitucion es anterior a la Fecha Hasta introducida en la busqueda.
		    //Mostraria el mensaje: "Puede consultar los datos de las convocatorias posteriores a la fecha de sustitucion consultando los datos del Ministerio que lo ha sustituido"   	
		   	if(sFechaHasta == null || sFechaHasta.equals(""))
		   	{
		   		MinisterioQuery ministerioQuerySustituto = new MinisterioQuery();
				MinisterioBean ministerioBeanSustituto = null;
				ministerioQuerySustituto.setIdMinisterioPrevio(ministerioBean.getId());
				ministerioBeanSustituto = ministeriosManager.buscarMinisterio(ministerioQuerySustituto);
				String sFechaSustitucion2 = utilesIPSG.dateToString(ministerioBeanSustituto.getFechaSustitucion());
				convocatoriasViewBeanTotales.setSustituidoPorOtro("S");
				convocatoriasViewBeanTotales.setDesMinisterioSustitutivo(ministerioBeanSustituto.getDescripcion());
				convocatoriasViewBeanTotales.setFechaSustitucion2(sFechaSustitucion2);
		   		
		   	}else
		   	{
				//dFechaHasta > dFechaSustitucion
				if(dFechaHasta.after(dFechaSustitucion))
				{
					MinisterioQuery ministerioQuerySustituto = new MinisterioQuery();
					MinisterioBean ministerioBeanSustituto = null;
					ministerioQuerySustituto.setIdMinisterioPrevio(ministerioBean.getId());
					ministerioBeanSustituto = ministeriosManager.buscarMinisterio(ministerioQuerySustituto);
					String sFechaSustitucion2 = utilesIPSG.dateToString(ministerioBeanSustituto.getFechaSustitucion());
					convocatoriasViewBeanTotales.setSustituidoPorOtro("S");
					convocatoriasViewBeanTotales.setDesMinisterioSustitutivo(ministerioBeanSustituto.getDescripcion());
					convocatoriasViewBeanTotales.setFechaSustitucion2(sFechaSustitucion2);
				}
		   	}
		   	
		}
		
		
		resultado.add(convocatoriasViewBeanTotales);
		
		return resultado;
	}
	

	/**
	 * Es igual.
	 *
	 * @param vis el vis
	 * @param candidato el candidato
	 * @param convocatoria el convocatoria
	 * @return verdadero, si tiene exito
	 */
	private boolean esIgual(String vis, ConvocatoriasViewBean candidato, ConvocatoriasViewBean convocatoria) {
		
		boolean resultado = true;
		
		
		if (vis.equals(Constantes.CM_ESTADO_CONVOCATORIA)){
			
			if ((candidato.getIdEstadoConvocatoria()==null && 
					 convocatoria.getIdEstadoConvocatoria()==null)||
					(candidato.getIdEstadoConvocatoria()!=null && 
					convocatoria.getIdEstadoConvocatoria()!=null && 
					candidato.getIdEstadoConvocatoria().intValue() == convocatoria.getIdEstadoConvocatoria().intValue())){
					resultado = true; 
				}else{
					resultado = false; 
				}
			
		}
	
			
		if (vis.equals(Constantes.CM_FECHA_BOE)){
			
			if ((candidato.getFechaBoe()==null && 
				 convocatoria.getFechaBoe()==null)||
				(candidato.getFechaBoe()!=null && 
				convocatoria.getFechaBoe()!=null && 
				candidato.getFechaBoe().equals(convocatoria.getFechaBoe()))){
				resultado = true; 
			}else{
				resultado = false; 
			}
			
		}
		
		if (vis.equals(Constantes.CM_FORMA_ACCESO)){
			
			if ((candidato.getIdFormaAcceso()==null && 
				 convocatoria.getIdFormaAcceso()==null)||
				(candidato.getIdFormaAcceso()!=null && 
				convocatoria.getIdFormaAcceso()!=null && 
				candidato.getIdFormaAcceso().intValue() == convocatoria.getIdFormaAcceso().intValue())){
				resultado = true; 
			}else{
				resultado = false; 
			}
			
		}
		
		if (vis.equals(Constantes.CM_CUERPO_ESCALA)){
			
			if ((candidato.getIdCuerpoEscala()==null && 
				 convocatoria.getIdCuerpoEscala()==null)||
				(candidato.getIdCuerpoEscala()!=null && 
				convocatoria.getIdCuerpoEscala()!=null && 
				candidato.getIdCuerpoEscala().intValue() == convocatoria.getIdCuerpoEscala().intValue())){
				resultado = true; 
			}else{
				resultado = false; 
			}
			
		}
		if (vis.equals(Constantes.CM_EJERCICIO)){
			
			if ((candidato.getEjercicio()==null && 
					 convocatoria.getEjercicio()==null)||
					(candidato.getEjercicio()!=null && 
					convocatoria.getEjercicio()!=null && 
					candidato.getEjercicio().equals(convocatoria.getEjercicio()))){
					resultado = true; 
			}else{
					resultado = false; 
			}
			
		}
		if (vis.equals(Constantes.CM_CENTRO_GESTOR)){
			if ((candidato.getIdCentroGestor()==null && 
					 convocatoria.getIdCentroGestor()==null)||
					(candidato.getIdCentroGestor()!=null && 
					convocatoria.getIdCentroGestor()!=null && 
					candidato.getIdCentroGestor().intValue() == convocatoria.getIdCentroGestor().intValue())){
					resultado = true; 
			}else{
					resultado = false; 
			}
		}
		if (vis.equals(Constantes.CM_MINISTERIO)){
			if ((candidato.getIdMinisterio()==null && 
					 convocatoria.getIdMinisterio()==null)||
					(candidato.getIdMinisterio()!=null && 
					convocatoria.getIdMinisterio()!=null && 
					candidato.getIdMinisterio().intValue() == convocatoria.getIdMinisterio().intValue())){
					resultado = true; 
			}else{
					resultado = false; 
			}
		}
		
		return resultado;
	}

	/**
	 * Creamos la ConvocatoriasViewQuery con los datos del formulario.
	 *
	 * @param convocatoriasViewQuery ConvocatoriasViewQuery
	 * @param formulario el formulario
	 * @param listaCentrosGestores 
	 * @return convocatoriasViewQuery ConvocatoriasViewQuery
	 */
 	/*INI-TRA042*/
	@SuppressWarnings("static-access")
	private ConvocatoriasViewQuery crearConvocatoriasViewQueryDatosFormulario (ConvocatoriasViewQuery convocatoriasViewQuery, ConsultarCuadroMandoForm formulario, List<CentroGestorBean> listaCentrosGestores)
	{
		
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		
		
		if(formulario.getIdCentroGestor() != null && !formulario.getIdCentroGestor().equals(""))
		{
			convocatoriasViewQuery.setIdCentroGestor(Integer.valueOf(formulario.getIdCentroGestor()));
		} else {
			if(listaCentrosGestores != null){
				for(CentroGestorBean cg: listaCentrosGestores) {
					convocatoriasViewQuery.addIdCentroGestorIn(cg.getId());
				}
			}
		}
		if(formulario.getIdEstado()!= null && !formulario.getIdEstado().equals(""))
		{
			convocatoriasViewQuery.setIdEstadoConvocatoria(Integer.valueOf(formulario.getIdEstado()));
		}else{
			convocatoriasViewQuery.addIdEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_CERRADO);
			convocatoriasViewQuery.addIdEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);
			convocatoriasViewQuery.addIdEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
			}

		Calendar calendario = Calendar.getInstance();
		Integer annioD=null;
		Integer annioH =null; 
		
		// SE TIENE EN CUENTA SoLO LAS CONVOCATORIAS INCLUIDAS EN LOS EJERCICIOS INCLUIDOS EN LAS
		// FECHAS FILTRADAS
		if(formulario.getFechaDesde()!= null && !formulario.getFechaDesde().equals(""))
		{
			Date dFechaD = utilesIPSG.stringToDate(formulario.getFechaDesde());
			calendario.setTime(dFechaD);
			annioD = calendario.get(Calendar.YEAR);
		}
		if(formulario.getFechaHasta() != null && !formulario.getFechaHasta().equals(""))
		{
			Date dFechaH = utilesIPSG.stringToDate(formulario.getFechaHasta());
			calendario.setTime(dFechaH);
			annioH = calendario.get(Calendar.YEAR);
		}
		
		// si se ha fijado fecha de inicio y de fin
		if((annioD != null) && (annioH != null))
		{
			if (annioD.intValue() == annioH.intValue())
			{
				convocatoriasViewQuery.addEjercicioIn(String.valueOf(annioD));
			}	
			else if (annioD != annioH)
			{
				for(int i=annioD.intValue(); i<=annioH.intValue(); i++)
				{
					convocatoriasViewQuery.addEjercicioIn(String.valueOf(i));
				}	
			}
			
		}	
		
		// si se ha fijado solo fecha de inicio
		if((annioD != null) && (annioH == null))
		{
			calendario = Calendar.getInstance();
			Integer annioActual = calendario.get(Calendar.YEAR);
			
			for(int i=annioD.intValue(); i<=annioActual.intValue(); i++)
			{
				convocatoriasViewQuery.addEjercicioIn(String.valueOf(i));
			}	
		}	
		
		// si se ha fijado solo fecha de fin
		if((annioD == null) && (annioH != null))
		{
			// se realiza una busqueda para saber cual es el ejercicio menor
			ConvocatoriasViewQuery convocAux = new ConvocatoriasViewQuery();
			convocAux.addOrder(ConvocatoriaQuery.EJERCICIO, OrderType.ASC);
			convocAux.setMaxResults(1);
			ArrayList<ConvocatoriasViewBean> resultado = convocatoriasViewManager.buscarConvocatoriasViewAll(convocAux);
			String ejercicioPrimero = resultado.get(0).getEjercicio();
			Integer annioPrimero =Integer.valueOf(ejercicioPrimero);
			
			for(int i=annioPrimero.intValue(); i<=annioH.intValue(); i++)
			{
				convocatoriasViewQuery.addEjercicioIn(String.valueOf(i));
			}	
		}
		
		// FIN de filtro de convocatorias por fecha
		
		//Se ordena por IdMinisterio para que te salga el salto de ministerio correcto
		convocatoriasViewQuery.addOrder(convocatoriasViewQuery.IDMINISTERIO, OrderType.ASC);
		
		if (formulario.getCkCentroGestor()){
			convocatoriasViewQuery.addOrder(convocatoriasViewQuery.IDCENTROGESTOR, OrderType.ASC);
		}
		if (formulario.getCkEjercicio()){
			convocatoriasViewQuery.addOrder(convocatoriasViewQuery.EJERCICIO, OrderType.ASC);
		}
		if (formulario.getCkCuerpoEscala()){
			convocatoriasViewQuery.addOrder(convocatoriasViewQuery.IDCUERPOESCALA, OrderType.ASC);
		}
		if (formulario.getCkFormaAcceso()){
			convocatoriasViewQuery.addOrder(convocatoriasViewQuery.IDFORMAACCESO, OrderType.ASC);
		}
		if (formulario.getCkFechaBOE()){
			convocatoriasViewQuery.addOrder(convocatoriasViewQuery.FECHABOE, OrderType.ASC);
		}	
		if (formulario.getCkEstado()){
			convocatoriasViewQuery.addOrder(convocatoriasViewQuery.IDESTADOCONVOCATORIA, OrderType.ASC);
		}

		return convocatoriasViewQuery;
		
	}
	/*FIN-TRA042*/
	
	/**
	 * Realiza los calculos de los campos numericos.
	 *
	 * @param lConvocatoriasViewBean List<ConvocatoriasViewBean>
	 * @param formulario ConsultarCuadroMandoForm
	 * @return lConvocatoriasViewBeanCompletada List<ConvocatoriasViewBean>
	 */
	private List<ConvocatoriasViewBean> completaDatosConvocatoriasView (List<ConvocatoriasViewBean> lConvocatoriasViewBean, ConsultarCuadroMandoForm formulario)
	{
		List<ConvocatoriasViewBean> lConvocatoriasViewBeanCompletada = new ArrayList<ConvocatoriasViewBean>();
		Iterator<ConvocatoriasViewBean> it = lConvocatoriasViewBean.iterator();

 
		while (it.hasNext())
		{
			ConvocatoriasViewBean convocatoriasViewBean = (ConvocatoriasViewBean) it.next();
		

			logger.info("ENTRA EN el WHILE de completaDatosConvocatoriasView Ministerio:" + convocatoriasViewBean.getDesMinisterio());
			Long idConvocatoria = convocatoriasViewBean.getIdConvocatoria();
			
			
			int numInscripcionSinPago =0;
			
			if (formulario.getCkNumInscripcionSinPago()){
			
				//Numero de Inscripciones Sin Pago = Solicitudes con incidencia de pago sin resolver
				numInscripcionSinPago = calcularNumSolIncPagoSinResolver(idConvocatoria, formulario);
				convocatoriasViewBean.setNumInscripcionSinPago(String.valueOf(numInscripcionSinPago));
			
			}
			int numInscripcionSinIntentoPago =0;
			if (formulario.isCkNumSolSinIntentoPago()){
				
				//Numero de Inscripciones Sin Intento de Pago
				numInscripcionSinIntentoPago = calcularNumInscripcionSinIntentoPago(idConvocatoria, formulario);
				convocatoriasViewBean.setNumSolSinIntentoPago(String.valueOf(numInscripcionSinIntentoPago));
			
			}
			int numPagosSinRegistro = 0;
			if (formulario.getCkNumPagosSinRegistro()){
				//Numero de Pagos Sin Registro
				numPagosSinRegistro = calcularNumSolIncRegistroSinResolver(idConvocatoria, formulario); 
				convocatoriasViewBean.setNumPagosSinRegistro(String.valueOf(numPagosSinRegistro));
			}
			int numPagosSinIntentoRegistro = 0;
			if (formulario.isCkNumPagosSinIntentoRegistro()){
				//Numero de Pagos Sin Registro
				numPagosSinIntentoRegistro = calcularNumPagosSinIntentoRegistro(idConvocatoria, formulario);
				convocatoriasViewBean.setNumPagosSinIntentoRegistro(String.valueOf(numPagosSinIntentoRegistro));
			}
			
			int numDescargasManuales = 0;
			if(formulario.getCkNumDescargasManuales() || formulario.isCkNumDescargasManualesTotal()){
			
				//Numero de Descargas Manuales Modelo 790
				numDescargasManuales = calcularNumDescargasManuales(convocatoriasViewBean.getIdConvocatoria(), formulario);
				convocatoriasViewBean.setNumDescargasManuales(String.valueOf(numDescargasManuales));
			
			}
			
			int numSolTelematica = 0;
			if (formulario.getCkNumSolTelematicas()){
				//Numero de solicitudes Telematicas
				numSolTelematica = calcularNumSolTelematica (idConvocatoria, formulario);
				convocatoriasViewBean.setNumSolTelematica(String.valueOf(numSolTelematica));
			}
			
			int numSolPresencial = 0;
			if(formulario.getCkNumSolPresenciales()){
				//Numero de solicitudes Presenciales
				numSolPresencial = calcularNumSolPresencial(idConvocatoria, formulario);
				convocatoriasViewBean.setNumSolPresencial(String.valueOf(numSolPresencial));
			}
			
			int numSolIncPagoResuelta = 0;
			if(formulario.getCkNumSolIncPagoResuelta()){
			
				//Numero de Solicitudes con incidencias de Pago Resueltas
				numSolIncPagoResuelta = calcularNumSolIncPagoResuelta(idConvocatoria, formulario);
				convocatoriasViewBean.setNumSolIncPagoResuelta(String.valueOf(numSolIncPagoResuelta));
			}
			
			int numSolIncPagoSinResolver = 0;
			if(formulario.getCkNumSolIncPagoSinResolver() || formulario.getCkNumInscripcionSinPago()){
				//Numero de Solicitudes con incidencias de Pago Sin Resolver
				numSolIncPagoSinResolver = calcularNumSolIncPagoSinResolver(idConvocatoria, formulario);
				convocatoriasViewBean.setNumSolIncPagoSinResolver(String.valueOf(numSolIncPagoSinResolver));
			}
			
			
			int numSolIncRegistroResuelta = 0;
			if(formulario.getCkNumSolIncRegistroResuelta()){
				//Numero de Solicitudes con Incidencias de Registro Resuelta
				numSolIncRegistroResuelta = calcularNumSolIncRegistroResuelta(idConvocatoria, formulario); 
				convocatoriasViewBean.setNumSolIncRegistroResuelta(String.valueOf(numSolIncRegistroResuelta));
			}
			
			
			int  numSolIncRegistroSinResolver = 0;
			if(formulario.getCkNumSolIncRegistroSinResolver()){
			
			//Numero de Solicitudes con Incidencias de Registro Sin Resolver
			numSolIncRegistroSinResolver = calcularNumSolIncRegistroSinResolver(idConvocatoria, formulario); 
			convocatoriasViewBean.setNumSolIncRegistroSinResolver(String.valueOf(numSolIncRegistroSinResolver));
			}
			
			//Listado que se enviara a la pantalla con todos los datos rellenos
			lConvocatoriasViewBeanCompletada.add(convocatoriasViewBean);

			logger.info("FIN del WHILE de completaDatosConvocatoriasView con Ministerio:" + convocatoriasViewBean.getDesMinisterio());
			logger.info("---------------------------------------------------------------------------------------");
		}
	
		
		return lConvocatoriasViewBeanCompletada;
	}
	

	
	/**
	 * Calcular num inscripcion sin intento pago.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @param formulario el formulario
	 * @return el int
	 */
	private int calcularNumInscripcionSinIntentoPago(Long idConvocatoria, ConsultarCuadroMandoForm formulario)
	{
		int iNumInscripcionSinIntentoPago = 0;
		SolComunIncidenciasViewQuery soliIncidenciasQuery = new SolComunIncidenciasViewQuery();
		
		
		// Solicitudes en estado NO PAGADO con 0 intentos de pago
		soliIncidenciasQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO.byteValue());
		soliIncidenciasQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO.byteValue());
		soliIncidenciasQuery.setIntentosPagoSolicitud(new BigDecimal(0));
		
		soliIncidenciasQuery.addIdConvocatoriaIn(idConvocatoria);
	
		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		soliIncidenciasQuery.setFechaSolicitudMin(dFechaDesde);
		soliIncidenciasQuery.setFechaSolicitudMax(dFechaHasta);
		//Fecha Desde y Hasta
		
		List<SolicitudBean> lSolicitudBean = solicitudesRegistradasManager.buscarSolicitudesIncidenciasVista(soliIncidenciasQuery);

		iNumInscripcionSinIntentoPago = lSolicitudBean.size();
		logger.info("TOTAL calcularNumInscripcionSinIntentoPago iNumInscripcionSinIntentoPago: " + iNumInscripcionSinIntentoPago);
		
		return iNumInscripcionSinIntentoPago;
	}
	
	/**
	 * Calcular num pagos sin intento registro.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @param formulario el formulario
	 * @return el int
	 */
	private int calcularNumPagosSinIntentoRegistro (Long idConvocatoria, ConsultarCuadroMandoForm formulario)
	{
		int iNumPagosSinIntentoRegistro = 0;
		
		SolComunIncidenciasViewQuery solIncidenciasQuery = new SolComunIncidenciasViewQuery();
		
		solIncidenciasQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO.byteValue());
		solIncidenciasQuery.addIdEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO.byteValue());
		solIncidenciasQuery.setIntentosRegistroSolicitud(new BigDecimal(0));
		solIncidenciasQuery.addIdConvocatoriaIn(idConvocatoria);

		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		solIncidenciasQuery.setFechaSolicitudMin(dFechaDesde);
		solIncidenciasQuery.setFechaSolicitudMax(dFechaHasta);
		//Fecha Desde y Hasta
		List<SolicitudBean> lSolicitudBean = solicitudesRegistradasManager.buscarSolicitudesIncidenciasVista(solIncidenciasQuery);

		iNumPagosSinIntentoRegistro = lSolicitudBean.size();		

		logger.info("TOTAL calcularNumPagosSinRegistro iNumPagosSinRegistro " + iNumPagosSinIntentoRegistro);
		return  iNumPagosSinIntentoRegistro;
	}
	
	/**
	 * Calcula el numero de Descargas Manuales
	 * Descarga_Modelo_790.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @param formulario ConsultarCuadroMandoForm
	 * @return iNumDescargasManuales int
	 */
	private int calcularNumDescargasManuales (Long idConvocatoria, ConsultarCuadroMandoForm formulario)
	{
		int iNumDescargasManuales =0;
		DescargaModelo790Query descargaModelo790Query = new DescargaModelo790Query();

		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		//Fecha Desde y Hasta
		descargaModelo790Query.setFechaMin(dFechaDesde); 
		descargaModelo790Query.setFechaMax(dFechaHasta);
		descargaModelo790Query.addConvocatoriaIn(idConvocatoria);

		iNumDescargasManuales = descargaModelo790Manager.buscarDescargaModelo790All(descargaModelo790Query).size();

		logger.info("TOTAL calcularNumDescargasManuales iNumDescargasManuales " + iNumDescargasManuales);
		return iNumDescargasManuales;
	}
	
	/**
	 * Calcula el numero de Solicitudes Presenciales.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @param formulario el formulario
	 * @return iNumSolPresencial int
	 */
	private int calcularNumSolPresencial (Long idConvocatoria, ConsultarCuadroMandoForm formulario)
	{		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.addConvocatoriaIn(idConvocatoria);
		TipoSolicitudQuery tipoSolicitudQuery = new TipoSolicitudQuery();
		tipoSolicitudQuery.setId(Constantes.TIPO_SOLICITUD_PRESENCIAL); 
		solicitudQuery.setTipoSolicitud(tipoSolicitudQuery);
		
		// TODO Incidencia diferencia de comportamiento respecto a solicitudes telematicas,
		// donde si se filtra por estado.
		EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
		estadoSolicitudQuery.setId(Constantes.ESTADO_SOLICITUD_REGISTRADO);
		solicitudQuery.setEstadoSolicitud(estadoSolicitudQuery);
		
		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		solicitudQuery.setFechaSolicitudMin(dFechaDesde);
		solicitudQuery.setFechaSolicitudMax(dFechaHasta);
		//Fecha Desde y Hasta
		int iNumSolPresencial = solicitudesManager.buscarSolicitudAll(solicitudQuery).size();
		
		logger.info("TOTAL calcularNumSolPresencial iNumSolPresencial " + iNumSolPresencial);
		return iNumSolPresencial;
	}
	
	/**
	 * Calcula el numero de Solicitudes Telematicas .
	 *
	 * @param idConvocatoria el id convocatoria
	 * @param formulario el formulario
	 * @return iNumSolTelematica int
	 */
	private int calcularNumSolTelematica (Long idConvocatoria, ConsultarCuadroMandoForm formulario)
	{
		EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
		estadoSolicitudQuery.setId(Constantes.ESTADO_SOLICITUD_REGISTRADO);
		
		TipoSolicitudQuery tipoSolicitudQuery = new TipoSolicitudQuery();
		tipoSolicitudQuery.setId(Constantes.TIPO_SOLICITUD_TELEMATICA);
		
		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.addConvocatoriaIn(idConvocatoria);
		solicitudQuery.setEstadoSolicitud(estadoSolicitudQuery);		
		solicitudQuery.setTipoSolicitud(tipoSolicitudQuery);
		solicitudQuery.setFechaSolicitudMin(dFechaDesde);
		solicitudQuery.setFechaSolicitudMax(dFechaHasta);
		
		int iNumSolTelematica = solicitudesManager.buscarSolicitudAll(solicitudQuery).size();
		logger.info("TOTAL calcularNumSolTelematica iNumSolTelematica " + iNumSolTelematica);
		return iNumSolTelematica;
	}
	
	/**
	 * Calcular el Numero de Solicitudes con incidencias de Pago Resueltas
	 * Estado No registrada o Pen.Registro o Proceso Registro o Registrada
	 * ultimo registro de la Solicitud este OK 
	 * y al menos un registro anterior Resultado= ER
	 *
	 * @param idConvocatoria el id convocatoria
	 * @param formulario el formulario
	 * @return iNumSolIncPagoResuelta int
	 */
	private int calcularNumSolIncPagoResuelta (Long idConvocatoria, ConsultarCuadroMandoForm formulario)
	{
		int iNumSolIncPagoResuelta = 0;
		boolean bResultadoOK = false;
		boolean bResultadoER = false;
		boolean bPrimerRegistroSolicitud = true;
		boolean bNuevaSolicitud = true;
		
		String sResultado = "";
		
		PagoSolicitudQuery 	pagoSolicitudQuery = new PagoSolicitudQuery();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.addConvocatoriaIn(idConvocatoria);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_REGISTRADO);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO);
		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		solicitudQuery.setFechaSolicitudMin(dFechaDesde);
		solicitudQuery.setFechaSolicitudMax(dFechaHasta);
		//Fecha Desde y Hasta
		//Ordenamos por Solicitud y Fecha de Intento para comprobar el ultimo OK y el anterior ER
		pagoSolicitudQuery.addOrder(STRINGSOLICITUDCOMUNID, OrderType.ASC);
		pagoSolicitudQuery.addOrder(STRINGFECHAINTENTO, OrderType.DESC);
		
		pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
		
		ArrayList<PagoSolicitudBean> aPagoSolicitud = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
		
		Iterator<PagoSolicitudBean> it = aPagoSolicitud.iterator();
		Long idSolicitudAnterior = null;
		Long idSolicitudActual;
		
		//Iteramos la primera vez para recoger el primer valor de la lista
		if(it.hasNext())
		{
			PagoSolicitudBean pagoSolicitud = (PagoSolicitudBean) it.next();
			idSolicitudAnterior = pagoSolicitud.getSolicitud().getIdSolicitud();
		}
		//Se inicializa de nuevo el Iterador para coger desde el principio el valor
		it = aPagoSolicitud.iterator();
		while (it.hasNext())
		{
			PagoSolicitudBean pagoSolicitud = (PagoSolicitudBean) it.next();
			
			if (pagoSolicitud.getResultado() != null)
				sResultado = pagoSolicitud.getResultado();
			else
				sResultado = "";
		
			idSolicitudActual = pagoSolicitud.getSolicitud().getIdSolicitud();
			logger.info(STRINGIDSOLICITUDACTUAL + idSolicitudActual + STRINGIDSOLICITUDANTERIOR + idSolicitudAnterior);
			if(!idSolicitudActual.equals(idSolicitudAnterior))
			{
				bPrimerRegistroSolicitud = true;
				bNuevaSolicitud = true;
				bResultadoER = false;
				bResultadoOK = false;
			}
			
			//Para los registros que no sean el Primero se comprueba que exista un ER
			if(!bPrimerRegistroSolicitud && !bResultadoER && sResultado.equals(Constantes.RESULTADO_ER))
			{
				bResultadoER = true;
			}
			//Comprobamos que sea el primer registro y OK.	
			if( bPrimerRegistroSolicitud && sResultado.equals(Constantes.RESULTADO_OK))
			{
				bResultadoOK = true;
				bPrimerRegistroSolicitud = false;
			}else
			{
				//Ya no seria el primer registro que tenga OK si no entra en el if anterior.
				bPrimerRegistroSolicitud = false;
			}
			
			//Contamos una solicitud si es una nueva no contada
			if(bResultadoER && bResultadoOK && bNuevaSolicitud)
			{
				iNumSolIncPagoResuelta++;
				bNuevaSolicitud = false;
			}
			idSolicitudAnterior = idSolicitudActual;
		}

		logger.info("TOTAL calcularNumSolIncPagoResuelta iNumSolIncPagoResuelta " + iNumSolIncPagoResuelta);
		return iNumSolIncPagoResuelta;
	}
	
	/**
	 * Calcular el Numero de Solicitudes con incidencias de Pago Sin Resolver 
	 * 
	 * Estado No pagada o Proceso de pago
	 * No exista ningun pago OK. o resultado de busqueda por OK sea 0.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @param formulario el formulario
	 * @return iNumSolIncPagoSinResolver int
	 */
	private int calcularNumSolIncPagoSinResolver (Long idConvocatoria,ConsultarCuadroMandoForm formulario)
	{
		int iNumSolIncPagoSinResolver = 0;
		boolean bResultadoOK = false;
		String sResultado = "";
		
		PagoSolicitudQuery 	pagoSolicitudQuery = new PagoSolicitudQuery();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.addConvocatoriaIn(idConvocatoria);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_PAGADO);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_DE_PAGO);
		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		solicitudQuery.setFechaSolicitudMin(dFechaDesde);
		solicitudQuery.setFechaSolicitudMax(dFechaHasta);
		//Fecha Desde y Hasta
		//Ordenamos por Solicitud y Fecha de Intento para comprobar el ultimo OK y el anterior ER
		pagoSolicitudQuery.addOrder(STRINGSOLICITUDCOMUNID, OrderType.ASC);
		pagoSolicitudQuery.addOrder(STRINGFECHAINTENTO, OrderType.DESC);
		pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
		
		ArrayList<PagoSolicitudBean> aPagoSolicitud = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
		
		Iterator<PagoSolicitudBean> it = aPagoSolicitud.iterator();
		Long idSolicitudAnterior = null;
		Long idSolicitudActual;
		
		//Iteramos la primera vez para recoger el primer valor de la lista para que vaya cogiendo por Solicitud
		if(it.hasNext())
		{
			PagoSolicitudBean pagoSolicitud = (PagoSolicitudBean) it.next();
			idSolicitudAnterior = pagoSolicitud.getSolicitud().getIdSolicitud();
		}
		//Se inicializa de nuevo el Iterador para coger desde el principio el valor
		it = aPagoSolicitud.iterator();
		while (it.hasNext())
		{
			PagoSolicitudBean pagoSolicitud = (PagoSolicitudBean) it.next();
			
			if (pagoSolicitud.getResultado() != null)
				sResultado = pagoSolicitud.getResultado();
			else
				sResultado = "";
		
			//Comprobamos sea OK.	
			if(sResultado.equals(Constantes.RESULTADO_OK))
			{
				bResultadoOK = true;
			}
			
			idSolicitudActual = pagoSolicitud.getSolicitud().getIdSolicitud();
			logger.info(STRINGIDSOLICITUDACTUAL + idSolicitudActual + STRINGIDSOLICITUDANTERIOR + idSolicitudAnterior);
			if(!idSolicitudActual.equals(idSolicitudAnterior) && !bResultadoOK)
			{
				
					iNumSolIncPagoSinResolver++;
					bResultadoOK = false;
				
			}
			idSolicitudAnterior = idSolicitudActual;
		}
		//Si solo existe un registro o para recoger el ultimo 
		if(!bResultadoOK && aPagoSolicitud.size() > 0)
		{
			iNumSolIncPagoSinResolver++;
		}
		
		logger.info("TOTAL calcularNumSolIncPagoSinResolver iNumSolIncPagoSinResolver " + iNumSolIncPagoSinResolver);
		return iNumSolIncPagoSinResolver;
	}
	
	/**
	 * Calcular el Numero de Solicitudes con Incidencias de Registro Resuelta
	 * Estado Registrada
	 * El ultimo registro de la SOlicitud este OK y al menos un registro anterior Resultado= ER.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @param formulario el formulario
	 * @return iNumSolIncRegistroResuelta int
	 */
	private int calcularNumSolIncRegistroResuelta (Long idConvocatoria, ConsultarCuadroMandoForm formulario)
	{
		
		int iNumSolIncRegistroResuelta = 0;
		boolean bResultadoOK = false;
		boolean bResultadoER = false;
		boolean bPrimerRegistroSolicitud = true;
		boolean bNuevaSolicitud = true;
		
		String sResultado = "";
		
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.addConvocatoriaIn(idConvocatoria);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_REGISTRADO);

		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		solicitudQuery.setFechaSolicitudMin(dFechaDesde);
		solicitudQuery.setFechaSolicitudMax(dFechaHasta);
		//Fecha Desde y Hasta
		//Ordenamos por Solicitud y Fecha de Intento para comprobar el ultimo OK y el anterior ER
		registroSolicitudQuery.addOrder(STRINGSOLICITUDCOMUNID, OrderType.ASC);
		registroSolicitudQuery.addOrder(STRINGFECHAINTENTO, OrderType.DESC);
				registroSolicitudQuery.setSolicitudComun(solicitudQuery);
		
		ArrayList<RegistroSolicitudBean> aRegistroSolicitudBean = registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);
		
		Iterator<RegistroSolicitudBean> it = aRegistroSolicitudBean.iterator();
		Long idSolicitudAnterior = null;
		Long idSolicitudActual;
		
		//Iteramos la primera vez para recoger el primer valor de la lista
		if(it.hasNext())
		{
			RegistroSolicitudBean registroSolicitudBean = (RegistroSolicitudBean) it.next();
			idSolicitudAnterior = registroSolicitudBean.getSolicitud().getIdSolicitud();
		}
		//Se inicializa de nuevo el Iterador para coger desde el principio el valor
		it = aRegistroSolicitudBean.iterator();
		while (it.hasNext())
		{
			RegistroSolicitudBean registroSolicitudBean = (RegistroSolicitudBean) it.next();
			
			if (registroSolicitudBean.getResultado() != null)
				sResultado = registroSolicitudBean.getResultado();
			else
				sResultado = "";
		
			idSolicitudActual = registroSolicitudBean.getSolicitud().getIdSolicitud();
			logger.info(STRINGIDSOLICITUDACTUAL + idSolicitudActual + STRINGIDSOLICITUDANTERIOR + idSolicitudAnterior);
			if(!idSolicitudActual.equals(idSolicitudAnterior))
			{
				bPrimerRegistroSolicitud = true;
				bNuevaSolicitud = true;
				bResultadoOK = false;
				bResultadoER = false;
			}
			
			//Para los registros que no sean el Primero se comprueba que exista un ER
			if(!bPrimerRegistroSolicitud && !bResultadoER && sResultado.equals(Constantes.RESULTADO_ER))
			{
				bResultadoER = true;
			}
			//Comprobamos que sea el primer registro y OK.	
			if( bPrimerRegistroSolicitud && sResultado.equals(Constantes.RESULTADO_OK))
			{
				bResultadoOK = true;
				bPrimerRegistroSolicitud = false;
			}else
			{
				//Ya no seria el primer registro que tenga OK si no entra en el if anterior.
				bPrimerRegistroSolicitud = false;
			}
			
			//Contamos una solicitud si es una nueva no contada
			if(bResultadoER && bResultadoOK && bNuevaSolicitud)
			{
				iNumSolIncRegistroResuelta++;
				bNuevaSolicitud = false;
			}
			idSolicitudAnterior = idSolicitudActual;
		}
		logger.info("TOTAL calcularNumSolIncRegistroResuelta iNumSolIncRegistroResuelta " + iNumSolIncRegistroResuelta);
		return iNumSolIncRegistroResuelta;
	}

	/**
	 * Calcula las Solicitudes con Incidencias Sin Resolver
	 * Estado No registrada o Pen.Registro o Proceso Registro
	 * No exista ningun pago OK. o resultado de busqueda por OK sea 0.
	 * @param idConvocatoria Long
	 * @param formulario ConsultarCuadroMandoForm
	 * @return iNumSolIncRegistroSinResolver int
	 */
	private int calcularNumSolIncRegistroSinResolver (Long idConvocatoria, ConsultarCuadroMandoForm formulario)
	{
		int iNumSolIncRegistroSinResolver = 0;
		boolean bResultadoOK = false;
		String sResultado = "";
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.addConvocatoriaIn(idConvocatoria);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PENDIENTE_REGISTRO);
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_PROCESO_REGISTRO);
		//Fecha Desde y Hasta
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde =  utilesIPSG.stringToDate(formulario.getFechaDesde());
		Date dFechaHasta =  utilesIPSG.stringToDate(formulario.getFechaHasta());
		solicitudQuery.setFechaSolicitudMin(dFechaDesde);
		solicitudQuery.setFechaSolicitudMax(dFechaHasta);
		//Fecha Desde y Hasta
		//Ordenamos por Solicitud y Fecha de Intento para comprobar el ultimo OK y el anterior ER
		registroSolicitudQuery.addOrder(STRINGSOLICITUDCOMUNID, OrderType.ASC);
		registroSolicitudQuery.addOrder(STRINGFECHAINTENTO, OrderType.DESC);
		
		registroSolicitudQuery.setSolicitudComun(solicitudQuery);
		
		ArrayList<RegistroSolicitudBean> aRegistroSolicitudBean = registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);
		
		Iterator<RegistroSolicitudBean> it = aRegistroSolicitudBean.iterator();
		Long idSolicitudAnterior = null;
		Long idSolicitudActual;
		
		//Iteramos la primera vez para recoger el primer valor de la lista para que vaya cogiendo por Solicitud
		if(it.hasNext())
		{
			RegistroSolicitudBean registroSolicitudBean = (RegistroSolicitudBean) it.next();
			idSolicitudAnterior = registroSolicitudBean.getSolicitud().getIdSolicitud();
		}
		//Se inicializa de nuevo el Iterador para coger desde el principio el valor
		it = aRegistroSolicitudBean.iterator();
		while (it.hasNext())
		{
			RegistroSolicitudBean registroSolicitudBean = (RegistroSolicitudBean) it.next();
			
			if (registroSolicitudBean.getResultado() != null)
				sResultado = registroSolicitudBean.getResultado();
			else
				sResultado = "";
		
			//Comprobamos sea OK.	
			if(sResultado.equals(Constantes.RESULTADO_OK))
			{
				bResultadoOK = true;
			}
			
			idSolicitudActual = registroSolicitudBean.getSolicitud().getIdSolicitud();
			logger.info(STRINGIDSOLICITUDACTUAL + idSolicitudActual + STRINGIDSOLICITUDANTERIOR + idSolicitudAnterior);
			if(!idSolicitudActual.equals(idSolicitudAnterior) && !bResultadoOK)
			{
			
				
					iNumSolIncRegistroSinResolver++;
					bResultadoOK = false;
				
			}
			idSolicitudAnterior = idSolicitudActual;
		}
		//Si solo existe un registro o para recoger el ultimo 
		if(!bResultadoOK && aRegistroSolicitudBean.size() > 0)
		{
			iNumSolIncRegistroSinResolver++;
		}
		
		logger.info("TOTAL calcularNumSolIncRegistroSinResolver iNumSolIncRegistroSinResolver " + iNumSolIncRegistroSinResolver);
		return iNumSolIncRegistroSinResolver;
	}

	/**
	 * Busca todas las convocatoriasView.
	 *
	 * @param convocatoriasViewQuery ConvocatoriasViewQuery
	 * @return lConvocatoriasViewBean List<ConvocatoriasViewBean>
	 */
	private List<ConvocatoriasViewBean> buscarConvocatoriasView(ConvocatoriasViewQuery convocatoriasViewQuery)
	{
		List<ConvocatoriasViewBean>  lConvocatoriasViewBean;
		lConvocatoriasViewBean = convocatoriasViewManager.buscarConvocatoriasViewAll(convocatoriasViewQuery);
		
		return lConvocatoriasViewBean;
	}
	
	/**
	 * Carga el combo del Estado.
	 */
	public void cargarCombos() {	
		
		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);
		estadoConvocatoriaQuery.addIdIn(Constantes.ESTADO_CONVOCATORIA_CERRADO);
		List<EstadoConvocatoriaBean> lEstadoConvocatoriaBean;
		lEstadoConvocatoriaBean = estadosConvocatoriaManager.buscarEstadoConvocatoriaCombo(estadoConvocatoriaQuery);
		
		setRequestAttribute("estadosConvocatoria", lEstadoConvocatoriaBean);
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
	 * @param usuarioManager UsuarioManager
	 */ 
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return centroGestorManager CentroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager CentroGestorManager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el estados convocatoria manager.
	 *
	 * @return estadosConvocatoriaManager EstadoConvocatoriaManager
	 */
	public EstadoConvocatoriaManager getEstadosConvocatoriaManager() {
		return estadosConvocatoriaManager;
	}

	/**
	 * Establece el estados convocatoria manager.
	 *
	 * @param estadosConvocatoriaManager EstadoConvocatoriaManager
	 */
	public void setEstadosConvocatoriaManager(
			EstadoConvocatoriaManager estadosConvocatoriaManager) {
		this.estadosConvocatoriaManager = estadosConvocatoriaManager;
	}

	/**
	 * Obtiene el convocatorias view manager.
	 *
	 * @return convocatoriasViewManager ConvocatoriasViewManager
	 */
	public ConvocatoriasViewManager getConvocatoriasViewManager() {
		return convocatoriasViewManager;
	}

	/**
	 * Establece el convocatorias view manager.
	 *
	 * @param convocatoriasViewManager ConvocatoriasViewManager
	 */
	public void setConvocatoriasViewManager(
			ConvocatoriasViewManager convocatoriasViewManager) {
		this.convocatoriasViewManager = convocatoriasViewManager;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return solicitudesManager SolicitudesManager
	 */
	public SolicitudesManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager SolicitudesManager
	 */ 
	public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return pagoSolicitudManager PagoSolicitudManager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager PagoSolicitudManager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return registroSolicitudManager RegistroSolicitudManager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager RegistroSolicitudManager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	/**
	 * Obtiene el descarga modelo 790 manager.
	 *
	 * @return  descargaModelo790Manager DescargaModelo790Manager
	 */
	public DescargaModelo790Manager getDescargaModelo790Manager() {
		return descargaModelo790Manager;
	}

	/**
	 * Establece el descarga modelo 790 manager.
	 *
	 * @param descargaModelo790Manager DescargaModelo790Manager
	 */
	public void setDescargaModelo790Manager(
			DescargaModelo790Manager descargaModelo790Manager) {
		this.descargaModelo790Manager = descargaModelo790Manager;
	}

	/**
	 * Obtiene el ministerios manager.
	 *
	 * @return el ministerios manager
	 */
	public MinisterioManager getMinisteriosManager() {
		return ministeriosManager;
	}

	/**
	 * Establece el ministerios manager.
	 *
	 * @param ministeriosManager el nuevo ministerios manager
	 */
	public void setMinisteriosManager(MinisterioManager ministeriosManager) {
		this.ministeriosManager = ministeriosManager;
	}

	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return el solicitudes registradas manager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager el nuevo solicitudes registradas manager
	 */
	public void setSolicitudesRegistradasManager(
			SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}

	/*INI-TRA042*/
	/**
	 * @return the usuarioCentrogestorManager
	 */
	public UsuarioCentrogestorManager getUsuarioCentrogestorManager() {
		return usuarioCentrogestorManager;
	}

	/**
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/
	
}