 package es.map.ipsc.spring.solicitudes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.FuncionarioHabilitadoQuery;
import es.map.ips.model.Ministerio;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolComunViewQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.BuscarSolicitudesBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.EstadoSolicitudBean;
import es.map.ipsc.bean.MinisterioBean;
import es.map.ipsc.bean.PagoSolicitudBean;
import es.map.ipsc.form.BuscarSolicitudesForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class BuscarSolicitudesSpring.
 */
public class BuscarSolicitudesSpring extends AbstractSecureSpring {

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarSolicitudesSpring.class);
	
	/** La constante STRING_ERRORPARSEARNUMREGISTROS. */
	private static final String STRING_ERRORPARSEARNUMREGISTROS= "Error parsear numRegistros";
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el tabla base manager. */
	private TablaBaseManager tablaBaseManager;
	
	/** el pago solicitud managar. */
	private PagoSolicitudManager pagoSolicitudManagar;
	
	/** el properties. */
	private static Properties properties;
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/**
	 * Crea una nueva buscar solicitudes spring.
	 */
	public BuscarSolicitudesSpring() {
		try {
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setPagoSolicitudManagar((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			properties = (Properties) getBean("propertiesBean");
			
		} catch (Exception e) {
			logger.error("Error buscar solicitudes",e);
			
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String llamada=null;
		String busqueda = this.getRequest().getParameter("submit");
		String numReg = this.getRequest().getParameter("numRegistro");
		String cambios = this.getRequest().getParameter("cambios");		
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_solicitudes = RESOURCE_MESSAGE.getString("field.menu.listaSolic");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudes);
		//****************************************************************** 
		
		BuscarSolicitudesForm formulario = null;
		formulario = (BuscarSolicitudesForm) form;
		
		if(this.getRequest().getParameter("llamada") != null){
			llamada = this.getRequest().getParameter("llamada");
		}
		
		String formRequest = this.getRequest().getParameter("form");
		logger.info("FormRequest: "+formRequest);
		if(formRequest != null){
			formulario.setNumJustificante("");
			formulario.setMinisterio("");
			formulario.setEstadoInscripcion("");
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
		}
		
		CiudadanoBean usuActual=null;
		
		//Comprobar si el usuario esta en la sesion
		 usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
		if(usuActual == null){
			this.getRequest().setAttribute("errorDescripcion",RESOURCE_MESSAGE.getString("solicitud.error.usuario"));
			return "errorUsuario";
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute("errorDescripcion",RESOURCE_MESSAGE.getString("solicitud.error.usuario"));
				return "errorUsuario";
			}
		}
		
		cargarCombos();
		
		//int numTotal=1;
		int aux=0;
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		if(formulario.getCampo()!= null && !"0".equals(formulario.getCampo())){
			try{
				aux = Integer.parseInt(formulario.getCampo());
				String campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);
			}catch(Exception e){
				logger.error("Error parsear campo"+ formulario.getCampo(),e);
			}			
		}
		
		
		ArrayList<BuscarSolicitudesBean> solicitudes = null;
		if (!usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
			SolComunViewQuery solicitudComunViewQuery = crearViewQuerySolicitud(formulario,numReg,busqueda);
			SolicitudComunQuery solicitudComunQuery = crearQuerySolicitud(formulario,numReg,busqueda);
		
			Calendar fechaActual= Calendar.getInstance();
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			fechaActual.add(Calendar.YEAR, -1);
			convocatoriaQuery.setFechaFinMin(fechaActual.getTime());
//			convocatoriaQuery.setFechaFinMax(fechaActual.getTime());
//			convocatoriaQuery.setFechaFinSubsanacionMin(fechaActual.getTime());
//			convocatoriaQuery.setFechaFinSubsanacionMax(fechaActual.getTime());
			
			solicitudComunQuery.setNif(usuActual.getNif());
			if(solicitudComunQuery.getConvocatoria() != null){
//				solicitudComunQuery.getConvocatoria().setFechaFinMax(fechaActual.getTime());
				solicitudComunQuery.getConvocatoria().setFechaFinMin(fechaActual.getTime());
//				solicitudComunQuery.getConvocatoria().setFechaFinSubsanacionMin(fechaActual.getTime());
//				solicitudComunQuery.getConvocatoria().setFechaFinSubsanacionMax(fechaActual.getTime());
			}else{
				solicitudComunQuery.setConvocatoria(convocatoriaQuery);
				}
			
			solicitudComunViewQuery.setNifSolicitud(usuActual.getNif());
//			solicitudes = solicitudesManager.buscarSolicitudesAll(solicitudComunViewQuery);
			
//			Set<BuscarSolicitudesBean> conjunto = new HashSet<BuscarSolicitudesBean>();
//			conjunto.addAll(solicitudes);
			
			solicitudes = solicitudesManager.buscarSolicitudesAll(solicitudComunQuery);
//			
//			conjunto.addAll(solicitudes);
//			solicitudes.clear();
//			solicitudes.addAll(conjunto);
//			ordenarSolicitudes(solicitudes, solicitudes.size());
			
			logger.info("NumSolicitudesIf: "+solicitudes.size());
			
		} else {	// se trata de una sesion de F.H
						
			String nifCiudadano = ""; 
			String ultimoCiudadanoInscrito = (String) this.getRequest().getSession().getAttribute("ultimoCiudadanoInscrito");
						
			if (formulario.getBuscadorNifInput() != null && !formulario.getBuscadorNifInput().isEmpty()) {
				// tomo el nif que procede del input del buscador
				nifCiudadano = formulario.getBuscadorNifInput();
			} else {
				// el nif proviene de la inscripcion de un ciudadano por parte de un F.H
				// cuando finaliza la inscripcion, al pulsar 'volver al formulario' carga las inscripciones del F.H para este nif
				nifCiudadano = ultimoCiudadanoInscrito;
			}
			setSessionAttribute("nifCiudadanoBuscado", nifCiudadano);
						
			int numeroSolicitudesEncontradas = 0;
			if (nifCiudadano!=null && !nifCiudadano.isEmpty()) {
				SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
				FuncionarioHabilitadoQuery funcionarioHabilitadoQuery = new FuncionarioHabilitadoQuery();
				
				solicitudComunQuery.setNif(nifCiudadano.toUpperCase());
				solicitudComunQuery.setFunHabilitado(true);
				funcionarioHabilitadoQuery.setNif(usuActual.getNif());
				solicitudComunQuery.setFuncionarioHabilitado(funcionarioHabilitadoQuery);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOPAGADO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOREGISTRADO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_PAGO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_REGISTRO);
				
				solicitudes = solicitudesManager.buscarSolicitudesAll(solicitudComunQuery);
				if (solicitudes!=null) {
					numeroSolicitudesEncontradas = solicitudes.size();
				}
				
			}
			logger.info("NumSolicitudes: "+ numeroSolicitudesEncontradas);
			setRequestAttribute("numSolicitudes", numeroSolicitudesEncontradas);
		}
		
			cambios = Constantes.ESTADO_BUSQUEDA;
			PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
			if(solicitudes != null && solicitudes.size()>0){
				for(int i=0;i<solicitudes.size();i++){
					int idEstadoSolicitud = solicitudes.get(i).getIdEstadoInscripcion();
					
					// Se obtiene la convocatoria asociada a la solicitud
					ConvocatoriaBean convocatoria = new ConvocatoriaBean();
					if (solicitudes.get(i) != null && solicitudes.get(i).getIdConvocatoria() != null) {
						ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
						convocatoriaQuery.setId(solicitudes.get(i).getIdConvocatoria());
						convocatoria = convocatoriasManager.buscarConvocatoriaId(convocatoriaQuery);
					}

					boolean subsanar = (convocatoria.getIdEstado() != null && convocatoria.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA))?true:false;
					
					if(Constantes.SOLICITUD_ID_NOPAGADO == idEstadoSolicitud && !subsanar){
						solicitudes.get(i).setEliminar(true);
						solicitudes.get(i).setReanudar(true);
					}else if(Constantes.SOLICITUD_ID_NOREGISTRADO == idEstadoSolicitud && !subsanar){
						SolicitudComunQuery solicitudQueryAux = new SolicitudComunQuery();
						solicitudQueryAux.setIdSolicitud(solicitudes.get(i).getIdSolicitud());
						pagoSolicitudQuery.setSolicitudComun(solicitudQueryAux);
						solicitudes.get(i).setReanudar(true);
						ArrayList<PagoSolicitudBean> pagos;
						pagos = pagoSolicitudManagar.buscarPagoSolicitud(pagoSolicitudQuery);
						if(pagos != null && pagos.size()>0){
							for(int x=0;x<pagos.size();x++){
								if(Constantes.ESTADO_PAGO_SOLICITUD_EXENTO_CHAR == pagos.get(x).getTipo()
									&& Constantes.RESULTADO_PAGO_SOLICITUD_OK.equals(pagos.get(x).getResultado())){
									solicitudes.get(i).setEliminar(true);
									x=pagos.size();
								}else{
									solicitudes.get(i).setEliminar(false);
								}
							}
						}else{
							solicitudes.get(i).setEliminar(false);
						}
					}else if(Constantes.SOLICITUD_ID_PROCESO_PAGO == idEstadoSolicitud && !subsanar){
						solicitudes.get(i).setReanudar(true);
					}else if(Constantes.SOLICITUD_ID_PROCESO_REGISTRO == idEstadoSolicitud && !subsanar){
						solicitudes.get(i).setReanudar(true);
					}else if(Constantes.SOLICITUD_ID_REGISTRADO  == idEstadoSolicitud || subsanar){
						// se comprueba la fecha actual con la fecha fin de convocatoria						
						
						// Se obtiene la fecha fin
						Date fechaFin = convocatoria.getFechaFin();
						// Se obtiene la fecha actual
						Date fechaActual = new Date();
						fechaActual = addDays (fechaActual,-1);
						// Si la fecha fin es mayor o igual que la fecha actual se muestra el boton modificar
						if(fechaFin.compareTo(fechaActual) >= 0){
							solicitudes.get(i).setModificar(true);
						}else if (fechaFin.compareTo(fechaActual) < 0) {
							solicitudes.get(i).setModificar(false);
						}
						
						
						fechaActual = new Date();
						// Si la fecha actual esta dentro de la fecha ini y fin de subsanacion se muestra el boton subsanar
						if(convocatoria.getFechaFinSub() != null && convocatoria.getFechaIniSub() != null 
								&& fechaActual.before(convocatoria.getFechaFinSub())
								&& fechaActual.after(convocatoria.getFechaIniSub())){
							solicitudes.get(i).setSubsanar(true);
						}else {
							solicitudes.get(i).setSubsanar(false);
						}
						
					}	
				}
		}
			
		//Descarga de Justificante de Registro			
			if(solicitudes != null && !"".equals(solicitudes)){
				for(int contadorSolicitudes = 0; contadorSolicitudes < solicitudes.size(); contadorSolicitudes++) {
					boolean find = false;
					DocumentoQuery documentosQuery = new DocumentoQuery();
					SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
					solicitudQuery.setIdSolicitud(solicitudes.get(contadorSolicitudes).getIdSolicitud());
					documentosQuery.setSolicitudComun(solicitudQuery);
					//Busca los documentos asignados a la convocatoria
					ArrayList<DocumentoBean> documentos = documentoConvocatoriasManager.buscarDocumentosConvocatoria(documentosQuery);
					ArrayList<DocumentoBean> justificante = new ArrayList<DocumentoBean>();
					ArrayList<DocumentoBean> anexos = new ArrayList<DocumentoBean>();
					Long tamanioTotal = 0L;
					if(documentos != null){
						for(int i=0;i<documentos.size();i++){
						if(documentos.get(i).getDsTipoDocumento().equals("ANEXO DE SOLICITUD")
								|| documentos.get(i).getDsTipoDocumento().equals("JUSTIFICANTE DE DISCAPACIDAD")
								|| documentos.get(i).getDsTipoDocumento().equals("JUSTIFICANTE DE REDUCCION DE TASA")
								|| documentos.get(i).getDsTipoDocumento().equals("AUTORIZACION DE CONSENTIMIENTO")){
								anexos.add(documentos.get(i));
								tamanioTotal = tamanioTotal + documentos.get(i).getTamano();
							}
							else if ((documentos.get(i).getDsTipoDocumento().equals(Constantes.JUSTIFICANTE_REGISTRO) 
									|| documentos.get(i).getDsTipoDocumento().equals(Constantes.JUSTIFICANTE_REGISTRO_GL)
									|| documentos.get(i).getDsTipoDocumento().equals(Constantes.JUSTIFICANTE_REGISTRO_CAT)
									|| documentos.get(i).getDsTipoDocumento().equals(Constantes.JUSTIFICANTE_REGISTRO_VL)
									|| documentos.get(i).getDsTipoDocumento().equals(Constantes.JUSTIFICANTE_REGISTRO_EU)) && !find){
									justificante.add(documentos.get(i));
									find = true;
							}
						}
					}	
					
				solicitudes.get(contadorSolicitudes).setNumDocumentos(documentos.size());
					solicitudes.get(contadorSolicitudes).setDocumentos(anexos);
					if (!(justificante != null && justificante.size() > 0)) {
						justificante.add(new DocumentoBean());
					}
					solicitudes.get(contadorSolicitudes).setJustificante(justificante);
				}
			}
		int numPaginas=0;
		if (!usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
			int pagTotal = 0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 10
			formulario.setNumRegistro(properties.getProperty("conf.numRegistrosListados"));
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		if(solicitudes != null && solicitudes.size()!= 0){
			pagTotal= solicitudes.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(solicitudes.size()>numRegistros){
				solicitudes.remove(solicitudes.size()-1);
			}
		}
		if(solicitudes != null){
			logger.info("NumSolicitudes: "+solicitudes.size());
		}
		}
		
				
		setRequestAttribute("solicitudes", solicitudes);
		setRequestAttribute("paginasTotales", numPaginas);
		setRequestAttribute("submit", busqueda);
		setRequestAttribute("cambios", cambios);
		setRequestAttribute("usuActual", usuActual);
		formulario.setCampo(String.valueOf(aux));
		
		formulario.setBuscadorNifInput(null);
		
		return "success";
		
	}
	
	/**
	 * M?todo para Ordenar las solicitudes.
	 *
	 * @param solicitudes el solicitudes
	 * @param length el length
	 * @return el array list
	 * @throws ParseException el parse exception
	 */
	private ArrayList<BuscarSolicitudesBean> ordenarSolicitudes(ArrayList<BuscarSolicitudesBean> solicitudes, int length) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHoras = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        Date date2 = null;
		int j, k;
		for (int i = 0; i < length - 1; i++) {
				for (k = i, j = i + 1; j < length; j++) {
						//Convertimos la fecha de tipo String a Date
						try {
							date1 = formato.parse(solicitudes.get(j).getFecha());
						} catch (ParseException e) {
							logger.error("Error al parsear la fecha de la solicitud al formato dd/MM/yyyy");
						}
						
						try {
							date2 = formato.parse(solicitudes.get(k).getFecha());
						} catch (ParseException e) {
							logger.error("Error al parsear la fecha de la solicitud al formato dd/MM/yyyy");
						}
						if (date1 == null) {
							date1 = formatoHoras.parse(solicitudes.get(j).getFecha());
						}
						
						if (date2 == null) {
							date2 = formatoHoras.parse(solicitudes.get(k).getFecha());
						}
				            //Comparamos cual es mayor(1)
				            int aux = date1.compareTo(date2);
						if (aux>0)
								k = j;
						else if(aux == 0)
							comprobarEstados(solicitudes,i, j, k);
						if (k != i)
							intercambiar (solicitudes, i, k);
				}
		}
		
		return solicitudes;
	}
	
	/**
	 * Comprobar estados.
	 *
	 * @param solicitudes el solicitudes
	 * @param i el i
	 * @param j el j
	 * @param k el k
	 */
	private void comprobarEstados(ArrayList<BuscarSolicitudesBean> solicitudes,int i, int j, int k) {
		//Comprobamos si la primera esta completada
		if((solicitudes.get(k).getIdEstadoInscripcion().intValue() == 3 || solicitudes.get(k).getIdEstadoInscripcion().intValue() == 5 ) && 
				(solicitudes.get(j).getIdEstadoInscripcion().intValue() != 3 || solicitudes.get(j).getIdEstadoInscripcion().intValue() != 5 )){
			//Comprobamos si la segunda esta completada
		
				//Cambiamos orden si solo la segunda no esta completada
				k = j;
				if(k!=i) {
					BuscarSolicitudesBean solicitud = solicitudes.get(i);
					solicitudes.set(i, solicitudes.get(k));
					solicitudes.set(k,solicitud);
				}
			
		}
	}
	
	/**
	 * Intercambiar.
	 *
	 * @param solicitudes el solicitudes
	 * @param i el i
	 * @param k el k
	 */
	private void intercambiar(ArrayList<BuscarSolicitudesBean> solicitudes, int i, int k) {
		BuscarSolicitudesBean solicitud = solicitudes.get(i);
		solicitudes.set(i, solicitudes.get(k));
		solicitudes.set(k,solicitud);
	}
		
	
	/**
	 * Crear query solicitud.
	 *
	 * @param formulario el formulario
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el solicitud comun query
	 */
	private SolicitudComunQuery crearQuerySolicitud(BuscarSolicitudesForm formulario,String numReg,String busqueda) {
		String numeroJustificante = formulario.getNumJustificante();
		String ministerio = formulario.getMinisterio();
		String estado = formulario.getEstadoInscripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		int numRegistros = 0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
			}
		}
		if(numReg != null && !"".equals(numReg)){
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}
		
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		int primerRegistro = 0; //(numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setMaxResults(numRegistros);
		solicitudComunQuery.setFirstResult(primerRegistro);
		solicitudComunQuery.setCalculateNumResults(true);

		
		if(numeroJustificante != null && !"".equals(numeroJustificante) && !"0".equals(numeroJustificante)){
			solicitudComunQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudComunQuery.setNumeroSolicitud(numeroJustificante);
		}
		if(ministerio != null && !"".equals(ministerio) && !"0".equals(ministerio)){
			ConvocatoriaQuery convoQuery = new ConvocatoriaQuery();
			Ministerio ministerioBean = new Ministerio();
			ministerioBean.setId(Integer.parseInt(ministerio));
			convoQuery.setMinisterioConvocanteIdIn(Arrays.asList(ministerioBean));
			solicitudComunQuery.setConvocatoria(convoQuery);
		}
		if(estado != null && !"".equals(estado) && !"0".equals(estado)){
			EstadoSolicitudQuery estadoQuery = new EstadoSolicitudQuery();
			estadoQuery.setId(Integer.parseInt(estado));
			solicitudComunQuery.setEstadoSolicitud(estadoQuery);

		}
				
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			if("up".equals(direccion)){
				solicitudComunQuery.addOrder(campo,OrderType.ASC);
			}else{
				solicitudComunQuery.addOrder(campo,OrderType.DESC);
			}
		}
		
		return solicitudComunQuery;
	}
	
	/**
	 * Crear view query solicitud.
	 *
	 * @param formulario el formulario
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el sol comun view query
	 */
	private SolComunViewQuery crearViewQuerySolicitud(BuscarSolicitudesForm formulario,String numReg,String busqueda) {
		String numeroJustificante = formulario.getNumJustificante();
		String ministerio = formulario.getMinisterio();
		String estado = formulario.getEstadoInscripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		int numRegistros = 0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
			}
		}
		if(numReg != null && !"".equals(numReg)){
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}
		
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		int primerRegistro = 0; //(numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		SolComunViewQuery solicitudComunQuery = new SolComunViewQuery();
		solicitudComunQuery.setMaxResults(numRegistros);
		solicitudComunQuery.setFirstResult(primerRegistro);
		solicitudComunQuery.setCalculateNumResults(true);
		solicitudComunQuery.addIdEstadoConvocatoriaIn(5);
		solicitudComunQuery.addIdEstadoConvocatoriaIn(9);
		
		if(numeroJustificante != null && !"".equals(numeroJustificante) && !"0".equals(numeroJustificante)){
			solicitudComunQuery.setNumueroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudComunQuery.setNumueroSolicitud(numeroJustificante);
		}
		if(ministerio != null && !"".equals(ministerio) && !"0".equals(ministerio)){
			solicitudComunQuery.setIdMinisterio(Integer.parseInt(ministerio));
		}
		if(estado != null && !"".equals(estado) && !"0".equals(estado)){
			solicitudComunQuery.setIdEstadoSoliciud(Integer.parseInt(estado));
		}
				
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			if("up".equals(direccion)){
				solicitudComunQuery.addOrder(campo,OrderType.ASC);
			}else{
				solicitudComunQuery.addOrder(campo,OrderType.DESC);
			}
		}
		
		return solicitudComunQuery;
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
			try{
				codCampo = Integer.parseInt(campo);
			}catch(Exception e){
				logger.error("Error parsear cargarCampos campo"+ codCampo,e);
			}
		}catch(Exception e){
			logger.error("Error parsear cargarcampos campo"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "numueroSolicitud";break;
						case 2:auxCampo = "descripcionMinisterio";break;
						case 3:auxCampo = "desCentroGestor";break;
						case 4:auxCampo = "desFormaAcceso";break;
						case 5:auxCampo = "descripcionCuerpoEscala";break;
						case 6:auxCampo = "desGrupo";break;
						case 7:auxCampo = "fechaSolicitud";break;
						case 8:auxCampo = "desEstadoSolicitud";break;
						default:break;
					}
				
			}
		return auxCampo;
	}
	
	/**
	 * Cargar combos.
	 */
	public void cargarCombos() {
		
		ArrayList<MinisterioBean> ministerios;
		ministerios = tablaBaseManager.buscarMinisteriosCombo();
		ArrayList<EstadoSolicitudBean> estados;
		estados = tablaBaseManager.buscarEstadoSolicitudCombo();
		estados = actualizarDescripcionEstados(estados);
		setRequestAttribute("ministerios",ministerios);
		setRequestAttribute("estados", estados);
		
	}
	
	/**
	 * Actualizar descripcion estados.
	 *
	 * @param estados el estados
	 * @return el array list
	 */
	public ArrayList actualizarDescripcionEstados(ArrayList<EstadoSolicitudBean> estados){
		for(EstadoSolicitudBean estado: estados){
			int idEstadoSolicitud = estado.getId();
			switch(idEstadoSolicitud){
				case 1: estado.setDescripcion(Constantes.SOLICITUD_ESTADO_NOPAGADO_DES);break;
				case 2: estado.setDescripcion(Constantes.SOLICITUD_ESTADO_NOREGISTRADO_DES);break;
				case 3: estado.setDescripcion(Constantes.SOLICITUD_ESTADO_REGISTRADO_DES);break;
				case 5: estado.setDescripcion(Constantes.SOLICITUD_ESTADO_PENDIENTEREGISTRO_DES);break;
				default: break;
			}
		}
		return estados;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el tabla base manager.
	 *
	 * @return el tabla base manager
	 */
	public TablaBaseManager getTablaBaseManager() {
		return tablaBaseManager;
	}

	/**
	 * Establece el tabla base manager.
	 *
	 * @param tablaBaseManager el nuevo tabla base manager
	 */
	public void setTablaBaseManager(TablaBaseManager tablaBaseManager) {
		this.tablaBaseManager = tablaBaseManager;
	}

	/**
	 * Obtiene el pago solicitud managar.
	 *
	 * @return el pago solicitud managar
	 */
	public PagoSolicitudManager getPagoSolicitudManagar() {
		return pagoSolicitudManagar;
	}

	/**
	 * Establece el pago solicitud managar.
	 *
	 * @param pagoSolicitudManagar el nuevo pago solicitud managar
	 */
	public void setPagoSolicitudManagar(PagoSolicitudManager pagoSolicitudManagar) {
		this.pagoSolicitudManagar = pagoSolicitudManagar;
	}
	
	/**
	 * Establece el documento convocatorias manager.
	 *
	 * @param documentoConvocatoriasManager el nuevo documento convocatorias manager
	 */
	public void setDocumentoConvocatoriasManager(
			DocumentosConvocatoriaManager documentoConvocatoriasManager) {
		this.documentoConvocatoriasManager = documentoConvocatoriasManager;
	}
	
	/**
	 * Obtiene el documentos convocatorias manager.
	 *
	 * @return el documentos convocatorias manager
	 */
	public  DocumentosConvocatoriaManager getDocumentosConvocatoriasManager() {
		return documentoConvocatoriasManager;
	}

	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}
	
    /**
     * Agrega el days.
     *
     * @param date el date
     * @param days el days
     * @return el date
     */
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
}
