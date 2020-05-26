package es.map.ipsg.manager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.Formulario790Bean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.res.ResourceLocator;
import es.map.ipsg.util.Barcode;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.DateUtil;
import es.map.ipsg.util.Jasper;
import es.map.ipsg.util.Util;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * El Class Modelo790ManagerImpl.
 */
public class Modelo790ManagerImpl implements Modelo790Manager {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(Modelo790ManagerImpl.class);
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** el properties. */
	private Properties properties;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.Modelo790Manager#generarModelo790Masivo(int, es.map.ipsg.bean.ModeloBean)
	 */
	public byte[] generarModelo790Masivo(int numModelos, ModeloBean modeloBean) {
		ByteArrayOutputStream pdfCompuesto = new ByteArrayOutputStream();
		try{
			PdfCopyFields copy = new PdfCopyFields(pdfCompuesto);
			
			for(int i=0;i<numModelos;i++){
				byte[] modeloAsBytes = generarModelo790(modeloBean);
				PdfReader reader = new PdfReader(modeloAsBytes);		
				copy.addDocument(reader);
				
			}
			copy.close();
		}catch(Exception e){
			logger.error("Error  Modelo790ManagerImpl -generarModelo790Masivo.",e);
		}
		return pdfCompuesto.toByteArray();
	}

	/**
	 * Generar modelo 790.
	 *
	 * @param modeloBean el modelo bean
	 * @return el byte[]
	 */
	private byte[] generarModelo790(ModeloBean modeloBean) {
		logger.info("GenerarFormulario790Action - start");
		
		Formulario790Bean formulario790Bean = null;
		File sourceFile = null;
		Jasper jasper = new Jasper();
		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;
		ArrayList<Formulario790Bean> aList = new ArrayList<Formulario790Bean>();
		
		byte[] pdfasbytes = null;
		byte[] pdfasbytes2 = null;
		byte[] pdfasbytes3 = null;
		byte[] pdfasbytes4 = null;
		byte[] pdfSalida = null;		

		try {
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			// Obtengo el fichero de recursos y todos los parametros necesarios
			// para generar el pdf
			String rutaReporte = 				properties.getProperty("jasper.rutaReport");
			String imagenReporte = 				properties.getProperty("jasper.imagen");
			String imagerNumeroJustificante = 	properties.getProperty("jasper.imagenNumeroJustificante");
			String extension = 					properties.getProperty("jasper.extension");
			String ficheroReporteC = 			properties.getProperty("jasper.modelo790C");
			String ficheroReporteA = 			properties.getProperty("jasper.modelo790A");
			String ficheroReporteE = 			properties.getProperty("jasper.modelo790E");
			String rutaLogo = 					properties.getProperty("jasper.rutaLogo");
			String encabezado =					Constantes.ENCABEZADO_GENERICO;
			String ccaaFN = 					properties.getProperty("formulario790.ccaa");
			String numerotituloFN = 			properties.getProperty("formulario790.numerotituloFN");
			String numerotituloFN2 = 			properties.getProperty("formulario790.numerotituloFN2");
			String ccaaFN2 = 					properties.getProperty("formulario790.ccaa2");
			String autorizar = 					properties.getProperty("formulario790.autorizar");
			String cumplimiento = 				properties.getProperty("formulario790.cumplimiento") + properties.getProperty("formulario790.cumplimiento2");
			String ejerce = 					properties.getProperty("formulario790.ejerce");
			String ejerce2 = 					properties.getProperty("formulario790.ejerce2");
			String consiente2 = 				properties.getProperty("formulario790.presencial.consiente2");
			String solicita = 					properties.getProperty("formulario790.solicita");
			String declara = 					properties.getProperty("formulario790.declara");
			String manifiesta = 				properties.getProperty("formulario790.manifiesta");
			String opone = 						properties.getProperty("formulario790.opone");
			String autoriza = 					properties.getProperty("formulario790.autoriza");
			String noAutoriza = 				properties.getProperty("formulario790.noAutorizar");

			if(modeloBean.getNumModelo().equals(Constantes.MODELO79007)){
				rutaLogo = properties.getProperty("jasper.rutaLogo.justicia");
				encabezado = Constantes.ENCABEZADO_JUSTICIA;
			}
			
			// Cargo el formulario Bean con los datos tratados que me llegan
			// desde la jsp
			String nombreBarcodeAux = String.valueOf(System.currentTimeMillis());
			StringBuffer nombreBarcode = new StringBuffer();
			nombreBarcode.append(imagenReporte).append(nombreBarcodeAux).append(extension);
			StringBuffer nombreBarcodeNJustificante = new StringBuffer();
			nombreBarcodeNJustificante.append(imagerNumeroJustificante).append(nombreBarcodeAux).append(extension);
			
			formulario790Bean = cargarBeanVacio(modeloBean);
			
			// Comprueba si el directorio existe. Sino, lo crea.
			Util.verificarDirectorio(rutaReporte);
			
			formulario790Bean.setRutaFicheros(rutaReporte);
			formulario790Bean.setNombreBarcode(nombreBarcode.toString());
			formulario790Bean.setCodigoPuntos(rutaReporte+'/'+nombreBarcode);
			
			
			formulario790Bean.setNombreBarcodeNJustificante(nombreBarcodeNJustificante.toString());
			formulario790Bean.setRutaLogo(rutaLogo);
			formulario790Bean.setEncabezado(encabezado);
			formulario790Bean.setCcaaFN(ccaaFN);
			formulario790Bean.setNumeroTitulo(numerotituloFN);
			formulario790Bean.setNumeroTitulo2(numerotituloFN2);
			formulario790Bean.setCcaaFN2(ccaaFN2);
			formulario790Bean.setAutoriza(autorizar);
			formulario790Bean.setCumplimiento(cumplimiento);
			formulario790Bean.setEjerce(ejerce);
			formulario790Bean.setEjerce2(ejerce2);
			formulario790Bean.setNoConsentimiento(consiente2);
			StringBuffer declaracion = new StringBuffer();
			declaracion.append(solicita);
			declaracion.append("\n");
			declaracion.append(declara);
			declaracion.append("\n");
			declaracion.append(manifiesta.replaceAll("<br>",""));
//			declaracion.append("\n");
//			declaracion.append(opone.replaceAll("<br>",""));
//			declaracion.append("\n");
//			declaracion.append(autoriza);
			formulario790Bean.setDeclaracion(declaracion.toString());
			formulario790Bean.setNoAutorizar(noAutoriza);
			
			
			
			// Compongo la cadena que se va a codificar y genero el barcode
			String datosCodificar = componerDatosCodigoVacio(formulario790Bean);
			logger.debug("Generando Codigo de Puntos: " + rutaReporte + "/"+ imagenReporte);
			String ruta = rutaReporte + "/" + nombreBarcode;
			String rutaImagenNumeroJustificante = rutaReporte + "/" + nombreBarcodeNJustificante;
			
			Barcode.generarBarcodeDatosSolicitud(ruta,datosCodificar);
			Barcode.generarBarcodeNumeroJustificante(rutaImagenNumeroJustificante, formulario790Bean.getNumeroJustificante());

			// Preparo el Bean para pasarlo al generador de formularios de jasperreport
			aList.add(formulario790Bean);

			jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection) aList);

			// Obtengo los ficheros implicados en la generacion del pdf
			URL url = ResourceLocator.getInstance().getJasperTemplate(ficheroReporteC);			
			String rutaFicheroJasper = "";
			
			if(url!=null){
				rutaFicheroJasper = url.getFile();
			}

			sourceFile = new File(rutaFicheroJasper);

			pdfasbytes = jasper.generarPDF(jrBeanCollectionDataSource,	sourceFile, "");

			// Genero el segundo pdf

			// Recargo la coleccion de datos que se le pasa al informe
			jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection) aList);
			
			// Obtengo los ficheros implicados en la generacion del pdf
			url = ResourceLocator.getInstance().getJasperTemplate(ficheroReporteA);			
			rutaFicheroJasper = "";
			
			if(url!=null){
				rutaFicheroJasper = url.getFile();
			}

			sourceFile = new File(rutaFicheroJasper);

			// Cargo el informe compilado en el objeto jasperreport
			pdfasbytes2 = jasper.generarPDF(jrBeanCollectionDataSource, sourceFile, "");

			// Genero el tercer pdf

			// Recargo la coleccion de datos que se le pasa al informe
			jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection) aList);
			// Obtengo los ficheros implicados en la generacion del pdf
			url = ResourceLocator.getInstance().getJasperTemplate(ficheroReporteE);			
			rutaFicheroJasper = "";
			
			if(url != null){
				rutaFicheroJasper = url.getFile();
			}

			sourceFile = new File(rutaFicheroJasper);

			// Cargo el informe compilado en el objeto jasperreport
			pdfasbytes3 = jasper.generarPDF(jrBeanCollectionDataSource, sourceFile, "");

			ByteArrayOutputStream pdfCompuesto = new ByteArrayOutputStream();
			PdfReader reader1 = new PdfReader(pdfasbytes);
			PdfReader reader2 = new PdfReader(pdfasbytes2);
			PdfReader reader3 = new PdfReader(pdfasbytes3);
			PdfCopyFields copy = new PdfCopyFields(pdfCompuesto);
			copy.addDocument(reader2);
			copy.addDocument(reader1);
			copy.addDocument(reader3);
			copy.close();
			reader1.close();
			reader2.close();
			reader3.close();

			pdfSalida = pdfCompuesto.toByteArray();
			pdfCompuesto.close();
			
			//borramos los ficheros de codigo de barras y codigo de puntos
			File fPng = new File(ruta);
			fPng.delete();
			 
			File fPng2 = new File(rutaImagenNumeroJustificante);
			fPng2.delete();
		}catch(Exception e){
			logger.error("Error  Modelo790ManagerImpl -generarModelo790.",e);
		}
		return pdfSalida;
	}
	
	/**
	 * Cargar bean vacio.
	 *
	 * @param modeloBean el modelo bean
	 * @return el formulario 790 bean
	 */
	private Formulario790Bean cargarBeanVacio(ModeloBean modeloBean){
		Formulario790Bean formulario = new Formulario790Bean();

		ResourceBundle RESOURCE_MESSAGE_ES = ResourceBundle.getBundle("MessageResources");
		
		// Campos exencion
		formulario.setPorcentajeDiscapacidad(" ");
		formulario.setReservaDiscapacidad(" ");
		formulario.setDetalleDiscapacidad(" ");
		formulario.setFamiliaNumerosa(" ");	    
	    formulario.setFamiliaNumerosaGeneral(" ");
	    formulario.setVictimaTerrorismo(" ");
		formulario.setComunidadAutonoma(" ");
		formulario.setDiscapacidad(" ");
		formulario.setDemandanteEmpleo(" ");
		formulario.setNumeroTituloFN(" ");
		formulario.setCcaa(" ");
		formulario.setFnumerosa(RESOURCE_MESSAGE_ES.getString("formulario790.tipoFamiliaNumerosa"));
		formulario.setDatosA(" ");
		formulario.setDatosB(" ");
		formulario.setDatosC(" ");
		
		
		
		StringBuffer numeroTituloTexto = new StringBuffer();
		
		numeroTituloTexto.append(RESOURCE_MESSAGE_ES.getString("formulario790.numerotituloFN"));
		formulario.setNumeroTitulo(numeroTituloTexto.toString());
		
		// Obtención de la tasa
		formulario.setCodigoTasa(modeloBean.getNumModelo().substring(3));
		boolean modJusticia = formulario.getCodigoTasa().equals(Constantes.CODIGO_TASA_JUSTICIA);
		formulario.setNumeroJustificante(solicitudesManager.obtenerNumeroSolicitud(modeloBean.getNumModelo()));
		
		// Si es modelo 790007-Justicia, recuperamos valor por defecto a incluir en
		// campos no necesarios.
		ParametrosConfiguracionBean paramBean = new ParametrosConfiguracionBean();
		if(formulario.getCodigoTasa() != null && modJusticia){
			ParametrosConfiguracionQuery paramQuery = new ParametrosConfiguracionQuery();
			paramQuery.setId(Constantes.PARAMETRO_CONFIGURACION_DEFAULT_TEXT_007);
			paramBean = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramQuery);
		}
		String valorDefectoJusticia = "";
		if(paramBean != null && paramBean.getValorCampo() != null){
			valorDefectoJusticia = paramBean.getValorCampo();
		}

		//Anio Convocatoria
		formulario.setAnioConvocatoria1(' ');
		formulario.setAnioConvocatoria2(' ');
		formulario.setAnioConvocatoria3(' ');
		formulario.setAnioConvocatoria4(' ');
		
		//Ministerio
		formulario.setMinisterio(" ");
		//Ministerio Convocatoria
		formulario.setMinisterioConvocatoria(" ");
		
		//Centro Gestor
		formulario.setCentroGestor(" ");
		formulario.setCodCentroGestor(" ");
		
		// Cuerpo Escala, Especialidad y Provincia Examen
		// Para Secretarios Judiciales deben generarse en blanco.
		if(modJusticia && modeloBean.getId()!=Constantes.COD_SECRETARIO_JUDICIAL){
			formulario.setCuerpoEscala(valorDefectoJusticia);
			formulario.setEspecialidad(valorDefectoJusticia);
			formulario.setProvinciaExamen(valorDefectoJusticia);
		}else{
			formulario.setCuerpoEscala(" ");
			formulario.setEspecialidad(" ");
			formulario.setProvinciaExamen(" ");
		}
		
		formulario.setCodigoEspecialidad(" ");
		formulario.setCodigoProvinciaExamen(" ");
		formulario.setCodProvinciaExamen1(" ");
		formulario.setCodProvinciaExamen2(" ");
		
		//Ciudadano
		formulario.setNif(" ");
		formulario.setNombre(" ");
		formulario.setPrimerApellido(" ");
		formulario.setSegundoApellido(" ");
		formulario.setDiaNacimiento1(' ');
		formulario.setDiaNacimiento2(' ');
		formulario.setMesNacimiento1(' ');
		formulario.setMesNacimiento2(' ');
		formulario.setAnioNacimiento1(' ');
		formulario.setAnioNacimiento2(' ');
		formulario.setSexoHombre(" ");
		formulario.setSexoMujer(" ");
		formulario.setNacionalidad(" ");
		formulario.setCodigoNacionalidad1(" ");
		formulario.setCodigoNacionalidad2(" ");
		formulario.setCorreoElectronicos(" ");
		formulario.setTelefono(" ");
		formulario.setTelefonoAux(" ");
		
		//Domicilio Ciudadano
		formulario.setCalleDomicilio(" ");
		formulario.setCodigoPostalDomicilio(" ");
		formulario.setPais(" ");
		formulario.setCodigoPais(" ");
		formulario.setCodPais1(" ");
		formulario.setCodPais2(" ");
		formulario.setMunicipioDomicilio(" ");
		formulario.setCodigoMunicipioDomicilio1(" ");
		formulario.setCodigoMunicipioDomicilio2(" ");
		formulario.setCodigoMunicipioDomicilio3(" ");
		formulario.setProvinciaDomicilio(" ");
		formulario.setCodProvinciaDomicilio1(" ");
		formulario.setCodProvinciaDomicilio2(" ");

		//Fecha BOE
		formulario.setDiaFechaBoe1(' ');
		formulario.setDiaFechaBoe2(' ');
		formulario.setMesFechaBoe1(' ');
		formulario.setMesFechaBoe2(' ');
		formulario.setAnioFechaBoe1(' ');
		formulario.setAnioFechaBoe2(' ');

		//Forma Acceso
		formulario.setFormaAcceso(" ");
		formulario.setCodFormaAcceso(" ");
		
		
		//Discapacidad
		formulario.setPorcentajeDiscapacidad(" ");
		formulario.setReservaDiscapacidad(" ");
		formulario.setDetalleDiscapacidad(" ");
		formulario.setTipoDiscapacidad(" ");
		formulario.setCodigoTipoDiscapacidad(' ');

		//Titulo Oficial
		formulario.setTituloOficial(" ");
		formulario.setCodTitutoOficial(" ");
		formulario.setCodTitulo1(' ');
		formulario.setCodTitulo2(' ');
		formulario.setCodTitulo3(' ');
		formulario.setCodTitulo4(' ');
		formulario.setCodTitulo5(' ');
		
		formulario.setOtrosTitulos(" ");
		formulario.setCodigoOtrosTitulos1(" ");
		formulario.setCodigoOtrosTitulos2(" ");
		formulario.setCodigoOtrosTitulos3(" ");
		formulario.setCodigoOtrosTitulos4(" ");
		formulario.setCodigoOtrosTitulos5(" ");

		//Declarante
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sDate = sdf.format(date);
		String[] partes = sDate.split("/");
		
		String dia = partes[0];
		String mes = partes[1];
		String anio = partes[2];
		
		String desMes = DateUtil.getMes(Integer.parseInt(mes)-1);
		
		formulario.setPersonaFirmante(" ");
		StringBuffer declarante = new StringBuffer("");
		declarante.append("En                              ");
		declarante.append(" ");
		declarante.append(dia).append(" de ").append(desMes).append(" de ").append(anio);
		formulario.setDeclarante(declarante.toString());
		
		formulario.setImporteSolicitud(" ");
		formulario.setImporteSolicitudDecimal(" ");

		//Forma de Pago
		formulario.setFormaPagoEfectivo(" ");
		formulario.setFormaPagoAdeudo(" ");

		formulario.setEntidad(" ");
		formulario.setOficina(" ");
		formulario.setDigitoConttrol(" ");
		formulario.setNumeroCuenta(" ");

		//entidad
		formulario.setCodigoEntidad1(' ');
		formulario.setCodigoEntidad2(' ');
		formulario.setCodigoEntidad3(' ');
		formulario.setCodigoEntidad4(' ');

		// oficina
		formulario.setCodigoOficina1(' ');
		formulario.setCodigoOficina2(' ');
		formulario.setCodigoOficina3(' ');
		formulario.setCodigoOficina4(' ');

		// digito de control
		formulario.setCodigoDigitoControl1(' ');
		formulario.setCodigoDigitoControl2(' ');

		// numero de cuenta
		formulario.setCodigoNumeroCuenta1(' ');
		formulario.setCodigoNumeroCuenta2(' ');
		formulario.setCodigoNumeroCuenta3(' ');
		formulario.setCodigoNumeroCuenta4(' ');
		formulario.setCodigoNumeroCuenta5(' ');
		formulario.setCodigoNumeroCuenta6(' ');
		formulario.setCodigoNumeroCuenta7(' ');
		formulario.setCodigoNumeroCuenta8(' ');
		formulario.setCodigoNumeroCuenta9(' ');
		formulario.setCodigoNumeroCuenta10(' ');
		
		formulario.setConsentimiento(" ");
		
		// Recuperar campos propios según el modelo
		ModeloQuery modeloQuery = new ModeloQuery();
		modeloQuery.setNumModelo(modeloBean.getNumModelo());
		CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
		camposPropiosQuery.setModelo(modeloQuery);
		camposPropiosQuery.addOrder("id", OrderType.ASC);
		
		// Obtención de campos propios del modelo
		ArrayList<CamposPropiosBean> listaCamposPropios;
		listaCamposPropios = camposPropiosManager.buscarCamposPropiosbyModelo(camposPropiosQuery);
		
		// Declaración según modelo y casilla de consentimiento
		if(formulario.getCodigoTasa() != null && formulario.getCodigoTasa().equals(Constantes.CODIGO_TASA_JUSTICIA)){
			StringBuffer declaracion = new StringBuffer();
			declaracion.append(RESOURCE_MESSAGE.getString("declaracion.790007.solicita"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE.getString("declaracion.790007.declara"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE.getString("declaracion.790007.manifiesta"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE.getString("declaracion.790007.consiente"));
			formulario.setDeclaracion(declaracion.toString());
			StringBuffer noConsiente = new StringBuffer();
			noConsiente.append(RESOURCE_MESSAGE.getString("declaracion.790007.noConsiente"));
			formulario.setNoConsentimiento(noConsiente.toString());
			
			// Si es para secretarios Judiciales, incluir solo Datos A y Datos B
			// En caso de Gestión Procesal, Prueba Optativa de idioma y Prueba Optativa de Dcho Foral.
			if(modeloBean.getId()==Constantes.COD_SECRETARIO_JUDICIAL){
				ArrayList<CamposPropiosBean> listaAux = new ArrayList<CamposPropiosBean>();
				listaAux.add(listaCamposPropios.get(2));
				listaAux.add(listaCamposPropios.get(3));
				formulario.setListaCamposPropios(listaAux);
			}else{
				ArrayList<CamposPropiosBean> listaAux = new ArrayList<CamposPropiosBean>();
				listaAux.add(listaCamposPropios.get(0));
				listaAux.add(listaCamposPropios.get(1));
				formulario.setListaCamposPropios(listaAux);
			}
			
		}else{
			StringBuffer declaracion = new StringBuffer();
			declaracion.append(RESOURCE_MESSAGE.getString("declaracion.790001.solicita"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE.getString("declaracion.790001.declara"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE.getString("declaracion.790001.manifiesta"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE.getString("declaracion.790001.consiente"));
			formulario.setDeclaracion(declaracion.toString());
			
			StringBuffer noConsiente = new StringBuffer();
			noConsiente.append(RESOURCE_MESSAGE.getString("declaracion.790001.noConsiente"));
			formulario.setNoConsentimiento(noConsiente.toString());
			
			formulario.setListaCamposPropios(listaCamposPropios);
		}
		
		return formulario;
	}

	/**
	 * Componer datos codigo vacio.
	 *
	 * @param formulario el formulario
	 * @return el string
	 */
	private String componerDatosCodigoVacio(Formulario790Bean formulario) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaSolicitud = sdf.format(new Date());
		
		StringBuffer cadena = new StringBuffer();
		cadena.append(" * * * *").append(formulario.getNumeroJustificante())
			.append("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ")
			.append(fechaSolicitud).append("* * * ");
		
		
		
		logger.info("Cadena: "+cadena);
		
		return cadena.toString();
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudesManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el campos propios manager.
	 *
	 * @return el campos propios manager
	 */
	public CamposPropiosManager getCamposPropiosManager() {
		return camposPropiosManager;
	}

	/**
	 * Establece el campos propios manager.
	 *
	 * @param camposPropiosManager el nuevo campos propios manager
	 */
	public void setCamposPropiosManager(CamposPropiosManager camposPropiosManager) {
		this.camposPropiosManager = camposPropiosManager;
	}

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

}
