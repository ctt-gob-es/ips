package es.map.ipsg.action.solicitudPresencial;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.PaisQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.ProvinciaQuery;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ips.model.TipoPagoQuery;
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ComunidadesBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.MotivoExencionTasaBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.PaisBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.bean.TipoDiscapacidadBean;
import es.map.ipsg.bean.TipoPagoBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.manager.ComunidadesManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PaisManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.manager.TipoDiscapacidadManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.UtilesIPSG;

/**
 * Acción AltaSolicitudPresencialAction.
 *
 * @author amartinl
 */
public class CargarDatosSolicitudPresencialSpring extends AbstractSpring {

	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el provincia examen manager. */
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el pais manager. */
	private PaisManager paisManager;
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el tipo discapacidad manager. */
	private TipoDiscapacidadManager tipoDiscapacidadManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el comunidades manager. */
	private ComunidadesManager comunidadesManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;

	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaSolicitudPresencialSpring.class);
	
	/** La constante STRING_ERRORGENERICO. */
	private static final String STRING_ERRORGENERICO = "errorGenerico";
	
	
		
	/**
	 * Acción AltaSolicitudPresencialAction.
	 */
	public CargarDatosSolicitudPresencialSpring() {
		try {
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
			setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
			setPaisManager((PaisManager) getBean("paisManager"));
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setTipoDiscapacidadManager((TipoDiscapacidadManager) getBean("tipoDiscapacidadManager"));
			setTituloOficialManager( (TituloOficialManager) getBean("tituloOficialManager"));
			setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setComunidadesManager((ComunidadesManager) getBean("comunidadesManager"));
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
			
		} catch (Exception e) {
			logger.error("Error CargarDatosSolicitudPresencialSpring: ", e);
		}
	}

/**
 * Método doExecute de AltaSolicitudPresencialAction.
 *
 * @param form SpringForm Pasa los campos del formulario
 * @return resultado String Si todo va bien devuelve success
 * @throws Exception Exception
 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		String resultado = "";
	try{	
		AltaSolicitudPresencialForm formulario = (AltaSolicitudPresencialForm) form;
		SolicitudBean solicitudBean;
		UtilesIPSG utilesIPSG;
		
		logger.info("CargarDatosSolicitudPresencialAction - inicio");
		
		cargarCombos();
		
		String idConvocatoria =this.getRequest().getParameter("idConv");
		ConvocatoriasBean convocatoriaBean;
		if(idConvocatoria != null && !idConvocatoria.equals(""))
		{	
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			convocatoriaQuery.setId(new Long(idConvocatoria));
			convocatoriaBean = convocatoriaManager.buscarConvocatoriaById(convocatoriaQuery);
			
			if ( convocatoriaBean != null)
			{
								
				PlantillaQuery plantillaQuery = new PlantillaQuery();
				plantillaQuery.setId(convocatoriaBean.getIdPlantilla());
				PlantillaBean plantilla = plantillaManager.buscarPlantilla(plantillaQuery);
				PlantillaPropiosQuery plantillasPropiosQuery = new PlantillaPropiosQuery();
				convocatoriaQuery.setId(Long.parseLong(convocatoriaBean.getIdConvocatoria()));
				plantillasPropiosQuery.setConvocatoria(convocatoriaQuery);
				plantillasPropiosQuery.addOrder("id", OrderType.ASC);
				ArrayList<PlantillaPropiosBean> plantillasPropias = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillasPropiosQuery);
				ArrayList<CamposPropiosBean> listaCamposPropiosBean = new ArrayList<CamposPropiosBean>();
				for (PlantillaPropiosBean campo: plantillasPropias){
					listaCamposPropiosBean.add(campo.getCampoPropioBean());
				}
				formulario.setListaCamposPropiosBean(listaCamposPropiosBean);
				setRequestAttribute("listaCamposPropiosBean", listaCamposPropiosBean);

				if(plantilla != null){
					formulario.setIdConvocatoria(idConvocatoria);	
					formulario.setDsConvocatoria(convocatoriaBean.getCuerpoEscala()+"-"+convocatoriaBean.getEjercicio());
					formulario.setObCorreoElectronico(plantilla.getCorreoElectronico());
					formulario.setObDatosA(plantilla.getDatosA());
					formulario.setObDatosB(plantilla.getDatosB());
					formulario.setObDatosC(plantilla.getDatosC());
					formulario.setObDetalleDiscapacidad(plantilla.getDetalleDiscapacidad());
					formulario.setObEspecialidad(plantilla.getEspecialidad());
					formulario.setObFechaNacimiento(plantilla.getFechaNacimiento());
					formulario.setObMunicipio(plantilla.getMunicipio());
					formulario.setObNacionalidad(plantilla.getNacionalidad());
					formulario.setObNif(plantilla.getNif());
					formulario.setObNombre(plantilla.getNombre());
					formulario.setObOtrosTitulos(plantilla.getOtrosTitulos());
					formulario.setObPais(plantilla.getPais());
					formulario.setObPorcentajeDiscapacidad(plantilla.getPorcentajeDiscapacidad());
					formulario.setObPrimerApellido(plantilla.getPrimerApellido());
					formulario.setObProvinciaExamen(plantilla.getProvinciaExamen());
					formulario.setObProvincia(plantilla.getProvincia());
					formulario.setObReservaDiscapacidad(plantilla.getReservaDiscapacidad());
					formulario.setObSegundoApellido(plantilla.getSegundoApellido());
					formulario.setObSexo(plantilla.getSexo());
					formulario.setObTelefono(plantilla.getTelefono());
					formulario.setObTipoDiscapacidad(plantilla.getTipoDiscapacidad());
					formulario.setObTitulosExigidos(plantilla.getTitulosExigidos());
					formulario.setObVia(plantilla.getVia());
					
					setRequestAttribute("plantilla",plantilla);
					
					form = formulario; 
					
					//añadimos el valor si los tiene de los campos propios asociados a la convocatoria
					ArrayList<SolicitudPropiosBean>listaSolicitudPropiosBean=formulario.getListaSolicitudPropiosBean();
					if(listaSolicitudPropiosBean!=null){
						formulario.setListaSolicitudPropiosBean(listaSolicitudPropiosBean);
						setRequestAttribute("listaSolicitudPropiosBean", listaSolicitudPropiosBean);
						  resultado = "successModificar";
					}else{
						resultado = "success";
					}

				}
                // Obtenemos los motivos de reducción de tasa y de exención y lo enviamos por la request para el jsp
				
				this.setRequestAttribute("altaSolicitudPresencialForm", formulario);
				if(convocatoriaBean.getMotivoReduccionTasasIncompleto() != null){
					this.setRequestAttribute("motivos", convocatoriaBean.getMotivoReduccionTasasIncompleto());
					
				}else{
					ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
					this.setRequestAttribute("motivos", arrayTasas);
				}				
				ArrayList<MotivoExencionTasaBean> arrayExenBean;
				arrayExenBean=cargarMotivosExencion(convocatoriaBean.getMotivoReduccionTasasCompleto());
				
				if(arrayExenBean != null){						
					this.setRequestAttribute("motivosCompletos", arrayExenBean);

				}else{
					ArrayList<MotivoExencionTasaBean> arrayExenBean2 = new ArrayList<MotivoExencionTasaBean>();
					this.setRequestAttribute("motivosCompletos", arrayExenBean2);
				}

			}
			else
			{
				resultado = STRING_ERRORGENERICO;
			}
		}else
		{
			resultado = STRING_ERRORGENERICO;
		}		
		
		
		logger.info("CargarDatosSolicitudPresencialAction - fin");
		return resultado;
		
	}catch(Exception eGenerico){
		logger.error("Error CargarDatosSolicitudPresencialSpring - doExecute: ", eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return STRING_ERRORGENERICO;
	}
}

	/**
	 * Método que transforma una lista que contiene los motivos de reduccion completos en una lista de motivos de exención.
	 *
	 * @param motivoReduccionTasasCompleto el motivo reduccion tasas completo
	 * @return ArrayList<MotivoExencionTasaBean>
	 */
	private ArrayList<MotivoExencionTasaBean> cargarMotivosExencion(ArrayList<MotivoReduccionTasa> motivoReduccionTasasCompleto) {
	
		ArrayList<MotivoExencionTasaBean> arrayExenBean = new ArrayList<MotivoExencionTasaBean>();
		Boolean tieneDiscapacidad=false;
		if(motivoReduccionTasasCompleto != null){
			for(int i=0;i<motivoReduccionTasasCompleto.size();i++){
				if(motivoReduccionTasasCompleto.get(i).getId().toString().equals(Constantes.MOTIVO_DISCAPACIDAD)){
					tieneDiscapacidad=true;
				}
				MotivoExencionTasaBean motivoExencionTasaBean= toMotivoExencionTasaBean(motivoReduccionTasasCompleto.get(i));
				arrayExenBean.add(motivoExencionTasaBean);			
				}
		}
		//Si no se encuentra la discapacidad en los motivos de exención en la convocatoria o no tiene motivos de exención lo volcamos por defecto
		if(motivoReduccionTasasCompleto == null || tieneDiscapacidad==false){
			MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
			motivoReduccionTasaQuery.setId(Integer.valueOf(Constantes.MOTIVO_DISCAPACIDAD));
			ArrayList<MotivoReduccionTasaBean> arrayMotivoTasaBean = motivoReduccionTasaManager.buscarMotivoReduccionTasaAll(motivoReduccionTasaQuery);
			MotivoReduccionTasa motivoBean=motivoReduccionTasaManager.toMotivoReduccionTasa(arrayMotivoTasaBean.get(0));
			MotivoExencionTasaBean motivoExencionTasaBean= toMotivoExencionTasaBean(motivoBean);
			arrayExenBean.add(motivoExencionTasaBean);
			
		}
						
		return arrayExenBean;
	
}
	
	/**
	 * To motivo exencion tasa bean.
	 *
	 * @param motivoBean el motivo bean
	 * @return el motivo exencion tasa bean
	 */
	private MotivoExencionTasaBean toMotivoExencionTasaBean(MotivoReduccionTasa motivoBean) {
		
		MotivoExencionTasaBean motivoExencionTasaBean =new MotivoExencionTasaBean();
		motivoExencionTasaBean.setIdMotivoEx(motivoBean.getId());
		motivoExencionTasaBean.setDescripcionMoEx(motivoBean.getDescripcion());
		motivoExencionTasaBean.setTextoExplicativo(motivoBean.getTextoExplicativo());
		motivoExencionTasaBean.setPorcentajeDescuento(motivoBean.getPorcentajeDescuento());
		motivoExencionTasaBean.setEstadoMoEx(motivoBean.getEstado());
		motivoExencionTasaBean.setCodigoMoEx(motivoBean.getCodigo());
		motivoExencionTasaBean.setConvocatoriasMoEx(motivoBean.getConvocatorias());
		motivoExencionTasaBean.setPagoSolicitudsMoEx(motivoBean.getPagoSolicituds());
		
		return motivoExencionTasaBean;
	}	
	
	
	
	
	/**
	 * Cargar combos.
	 */
	private void cargarCombos ()
	{
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		ArrayList<ProvinciaBean> lProvinciaBean;
		ArrayList<ProvinciaExamenBean> lProvinciaExamenBean;
		provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		provinciaExamenQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		lProvinciaBean = provinciaManager.buscarProvinciaCombo(provinciaQuery);
		lProvinciaExamenBean = provinciaExamenManager.buscarProvinciaExamenCombo(provinciaExamenQuery);
		
		PaisQuery paisQuery = new PaisQuery(); 
		ArrayList<PaisBean> lPaisBean;
		paisQuery.addOrder(PaisQuery.DESCRIPCION, OrderType.ASC);
		lPaisBean = paisManager.buscarPaisCombo2(paisQuery);
		
		EspecialidadQuery especialidadQuery = new EspecialidadQuery();
		ArrayList<EspecialidadBean> lEspecialidadBean;
		especialidadQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
		especialidadQuery.addOrder(EspecialidadQuery.DESCRIPCION, OrderType.ASC);
		lEspecialidadBean = especialidadManager.buscarEspecialidadCombo(especialidadQuery);
		
		TipoDiscapacidadQuery tipoDiscapacidadQuery = new TipoDiscapacidadQuery();
     	ArrayList<TipoDiscapacidadBean> lTipoDiscapacidadBean;
		lTipoDiscapacidadBean = tipoDiscapacidadManager.buscarTipoDiscapacidadCombo(tipoDiscapacidadQuery);
		
		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		ArrayList<TituloOficialBean> lTituloOficialBean;
		tituloOficialQuery.setEstado(Constantes.TITULOOFICIAL_ESTADO_ACTIVO);
		tituloOficialQuery.addOrder(TituloOficialQuery.DESCRIPCION, OrderType.ASC);
		lTituloOficialBean = tituloOficialManager.buscarTituloOficialCombo(tituloOficialQuery);
		 
		TipoPagoQuery tipoPagoQuery = new TipoPagoQuery();
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_CUENTA_INTEGER);
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_TARJETA_INTEGER);
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_EFECTIVO_INTEGER);
		
		ArrayList<TipoPagoBean> lTipoPagoBean;
		lTipoPagoBean = tipoPagoManager.buscarTipoPagoCombo(tipoPagoQuery);
		// Petición incluir Tipo de pago
		TipoPagoBean tipoPagoExento = new TipoPagoBean();
		tipoPagoExento.setId(0);
		tipoPagoExento.setDescripcion("EXENTO");
		tipoPagoExento.setCodigo('X');
		lTipoPagoBean.add(tipoPagoExento);
		setRequestAttribute("tipoPagos", lTipoPagoBean);
		
		ArrayList<ComunidadesBean> comunidadesFN;
		ComunidadesQuery comunidadesQueryFN = new ComunidadesQuery();
		comunidadesQueryFN.setServicioFamnumerosa(true);
		comunidadesFN = comunidadesManager.buscarComunidadesCombo(comunidadesQueryFN);
		setRequestAttribute("listcomunidadesFN", comunidadesFN);
		
		// Pasamos al formulario la lista de comunidades autónomas que requieren el número de titulo familia numerosa		
		ArrayList<ComunidadesBean> comunidadesReqTitulo;
		ComunidadesQuery comunidadesQueryReqTitulo = new ComunidadesQuery();
		comunidadesQueryReqTitulo.setTituloRequerido(true);
		comunidadesReqTitulo = comunidadesManager.buscarComunidadesCombo(comunidadesQueryReqTitulo);

		String comuReqTitulo="##";
		for (ComunidadesBean comunidad : comunidadesReqTitulo) {
			comuReqTitulo=comuReqTitulo+comunidad.getIdComunidad()+"##";
		} 
		this.setRequestAttribute("comunidadesReqTitulo", comuReqTitulo);

		ArrayList<ComunidadesBean> comunidadesDiscapacidad;
		ComunidadesQuery comunidadesQueryDD = new ComunidadesQuery();
		comunidadesQueryDD.setServicioDiscapacidad(true);
		comunidadesDiscapacidad = comunidadesManager.buscarComunidadesCombo(comunidadesQueryDD);
		setRequestAttribute("listcomunidadesDiscapacidad", comunidadesDiscapacidad);
		
		
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		ArrayList<EntidadFinancieraBean> lEntidadFinancieraBean;
		entidadFinancieraQuery.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		entidadFinancieraQuery.addOrder(EntidadFinancieraQuery.DESCRIPCION, OrderType.ASC);
		lEntidadFinancieraBean = entidadFinancieraManager.buscarEntidadFinancieraCombo(entidadFinancieraQuery);
		
		setRequestAttribute("entidadesBancarias", lEntidadFinancieraBean);
		setRequestAttribute("tipoPagos", lTipoPagoBean);
		setRequestAttribute("titulos", lTituloOficialBean);
		setRequestAttribute("tipoDiscapacidades", lTipoDiscapacidadBean);
		setRequestAttribute("especialidades", lEspecialidadBean);
		setRequestAttribute("provincias", lProvinciaBean);
		setRequestAttribute("provinciasExamen", lProvinciaExamenBean);
		setRequestAttribute("paises", lPaisBean);
		
	}
	

/**
 * Obtiene el logger.
 *
 * @return logger Logger
 */
public static Logger getLogger() {
	return logger;
}

/**
 * Obtiene el message resource.
 *
 * @return el message resource
 */
/*
/**
 * @return MESSAGE_RESOURCE
 */
public static String getMESSAGE_RESOURCE() {
	return MESSAGE_RESOURCE;
}

/**
 * Obtiene el resource bundle.
 *
 * @return RESOURCE_BUNDLE
 */
public static ResourceBundle getRESOURCE_BUNDLE() {
	return RESOURCE_BUNDLE;
}


/**
 * Obtiene el plantilla manager.
 *
 * @return el plantilla manager
 */
public PlantillaManager getPlantillaManager() {
	return plantillaManager;
}

/**
 * Establece el plantilla manager.
 *
 * @param plantillaManager el nuevo plantilla manager
 */
public void setPlantillaManager(PlantillaManager plantillaManager) {
	this.plantillaManager = plantillaManager;
}

/**
 * Obtiene el convocatoria manager.
 *
 * @return el convocatoria manager
 */
public ConvocatoriasManager getConvocatoriaManager() {
	return convocatoriaManager;
}

/**
 * Establece el convocatoria manager.
 *
 * @param convocatoriaManager el nuevo convocatoria manager
 */
public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
	this.convocatoriaManager = convocatoriaManager;
}

/**
 * Obtiene el provincia manager.
 *
 * @return el provincia manager
 */
public ProvinciaManager getProvinciaManager() {
	return provinciaManager;
}

/**
 * Establece el provincia manager.
 *
 * @param provinciaManager el nuevo provincia manager
 */
public void setProvinciaManager(ProvinciaManager provinciaManager) {
	this.provinciaManager = provinciaManager;
}

/**
 * Obtiene el provincia examen manager.
 *
 * @return el provincia examen manager
 */
public ProvinciaExamenManager getProvinciaExamenManager() {
	return provinciaExamenManager;
}

/**
 * Establece el provincia examen manager.
 *
 * @param provinciaExamenManager el nuevo provincia examen manager
 */
public void setProvinciaExamenManager(
		ProvinciaExamenManager provinciaExamenManager) {
	this.provinciaExamenManager = provinciaExamenManager;
}

/**
 * Obtiene el pais manager.
 *
 * @return el pais manager
 */
public PaisManager getPaisManager() {
	return paisManager;
}

/**
 * Establece el pais manager.
 *
 * @param paisManager el nuevo pais manager
 */
public void setPaisManager(PaisManager paisManager) {
	this.paisManager = paisManager;
}

/**
 * Obtiene el especialidad manager.
 *
 * @return el especialidad manager
 */
public EspecialidadManager getEspecialidadManager() {
	return especialidadManager;
}

/**
 * Establece el especialidad manager.
 *
 * @param especialidadManager el nuevo especialidad manager
 */
public void setEspecialidadManager(EspecialidadManager especialidadManager) {
	this.especialidadManager = especialidadManager;
}

/**
 * Obtiene el tipo discapacidad manager.
 *
 * @return el tipo discapacidad manager
 */
public TipoDiscapacidadManager getTipoDiscapacidadManager() {
	return tipoDiscapacidadManager;
}

/**
 * Establece el tipo discapacidad manager.
 *
 * @param tipoDiscapacidadManager el nuevo tipo discapacidad manager
 */
public void setTipoDiscapacidadManager(
		TipoDiscapacidadManager tipoDiscapacidadManager) {
	this.tipoDiscapacidadManager = tipoDiscapacidadManager;
}

/**
 * Obtiene el titulo oficial manager.
 *
 * @return el titulo oficial manager
 */
public TituloOficialManager getTituloOficialManager() {
	return tituloOficialManager;
}

/**
 * Establece el titulo oficial manager.
 *
 * @param tituloOficialManager el nuevo titulo oficial manager
 */
public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
	this.tituloOficialManager = tituloOficialManager;
}

/**
 * Obtiene el tipo pago manager.
 *
 * @return el tipo pago manager
 */
public TipoPagoManager getTipoPagoManager() {
	return tipoPagoManager;
}

/**
 * Establece el tipo pago manager.
 *
 * @param tipoPagoManager el nuevo tipo pago manager
 */
public void setTipoPagoManager(TipoPagoManager tipoPagoManager) {
	this.tipoPagoManager = tipoPagoManager;
}

/**
 * Obtiene el entidad financiera manager.
 *
 * @return el entidad financiera manager
 */
public EntidadFinancieraManager getEntidadFinancieraManager() {
	return entidadFinancieraManager;
}

/**
 * Establece el entidad financiera manager.
 *
 * @param entidadFinancieraManager el nuevo entidad financiera manager
 */
public void setEntidadFinancieraManager(
		EntidadFinancieraManager entidadFinancieraManager) {
	this.entidadFinancieraManager = entidadFinancieraManager;
}

/**
 * Obtiene el comunidades manager.
 *
 * @return el comunidades manager
 */
public ComunidadesManager getComunidadesManager() {
	return comunidadesManager;
}

/**
 * Establece el comunidades manager.
 *
 * @param comunidadesManager el nuevo comunidades manager
 */
public void setComunidadesManager(ComunidadesManager comunidadesManager) {
	this.comunidadesManager = comunidadesManager;
}

/**
 * Obtiene el motivo reduccion tasa manager.
 *
 * @return el motivo reduccion tasa manager
 */
public MotivoReduccionTasaManager getMotivoReduccionTasaManager() {
	return motivoReduccionTasaManager;
}

/**
 * Establece el motivo reduccion tasa manager.
 *
 * @param motivoReduccionTasaManager el nuevo motivo reduccion tasa manager
 */
public void setMotivoReduccionTasaManager(
		MotivoReduccionTasaManager motivoReduccionTasaManager) {
	this.motivoReduccionTasaManager = motivoReduccionTasaManager;
}

/**
 * Obtiene el plantilla propios manager.
 *
 * @return el plantilla propios manager
 */
public PlantillaPropiosManager getPlantillaPropiosManager() {
	return plantillaPropiosManager;
}

/**
 * Establece el plantilla propios manager.
 *
 * @param plantillaPropiosManager el nuevo plantilla propios manager
 */
public void setPlantillaPropiosManager(PlantillaPropiosManager plantillaPropiosManager) {
	this.plantillaPropiosManager = plantillaPropiosManager;
}

}
