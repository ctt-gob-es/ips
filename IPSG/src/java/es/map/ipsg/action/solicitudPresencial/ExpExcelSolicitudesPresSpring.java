package es.map.ipsg.action.solicitudPresencial;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.form.BuscarSolicitudesPresencialesForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;


/**
 * El Class ExpExcelSolicitudesPresSpring.
 *
 * @author amartinl
 */
public class ExpExcelSolicitudesPresSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ExpExcelSolicitudesPresSpring.class);
	
	/** el properties. */
	private static Properties properties;

	/** el convocatoria manager. */
	//Declaracion de Manager
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	

	/**
	 * Crea una nueva exp excel solicitudes pres spring.
	 */
	public ExpExcelSolicitudesPresSpring() {
		try {
				setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
				properties = (Properties) getBean("propertiesBean");
			} catch (Exception e) {
				logger.error("Error ExpExcelSolicitudesPresSpring - cargar constructor ",e);
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
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(MESSAGE_RESOUCE);	
		String menu_solicitudes = RESOURCE_BUNDLE_2.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		//******************************************************************
	try{
		//Cogemos el form del jsp
		BuscarSolicitudesPresencialesForm formulario;
		formulario = (BuscarSolicitudesPresencialesForm) form;
		
		//Recuperamos las solicitudes que ha marcado
		String[] aSolicitudesSel =  formulario.getSolicitudesSel();
		
		ArrayList<SolicitudBean> aSolicitudes;
		ArrayList<SolicitudBean> aSolicitudesDatosCompletos = new ArrayList();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		for(int i=0; i < aSolicitudesSel.length; i++)
		{
			Long idSolicitudSel = Long.parseLong(aSolicitudesSel[i]);
			solicitudQuery.addIdSolicitudIn(idSolicitudSel);
		}
		//Recuperamos todos los datos de las solicitudes seleccionadas
		aSolicitudes = solicitudesRegistradasManager.buscarSolicitudAll(solicitudQuery);
		
		Iterator<SolicitudBean> it = aSolicitudes.iterator();
		//Recogemos cada solicitud completa para insertar en la excel 
		//Variables para insertar en la Solicitud
		ArrayList<PagoSolicitudBean> aPagoSolicitud; 
		ArrayList<RegistroSolicitudBean> aRegistrosolicitud;
		while (it.hasNext())
		{
			SolicitudBean solicitudBean = (SolicitudBean) it.next();
			solicitudQuery.setIdSolicitud(solicitudBean.getId());
			//Completamos los datos de PagoSolicitud
			pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
			pagoSolicitudQuery.setResultado("OK");
			aPagoSolicitud = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
			Iterator<PagoSolicitudBean> itPago = aPagoSolicitud.iterator();
			while (itPago.hasNext())
			{
				PagoSolicitudBean pagoSolicitud = (PagoSolicitudBean) itPago.next();
				if(pagoSolicitud.getResultado().equals("OK"))
				{
					//Tipo de Pago
					solicitudBean.setDescripcionTipoPago(pagoSolicitud.getTipo().toString());
					//Fecha de Pago
					SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
					String fechaPago= "";
					if (pagoSolicitud.getFechaIntento() != null) {
						fechaPago = formatoFecha.format(pagoSolicitud.getFechaIntento());
						if(fechaPago != null){
							solicitudBean.setFechaPago(fechaPago);
						}
					}
					solicitudBean.setNrc(pagoSolicitud.getNrc());
					solicitudBean.setImporte(pagoSolicitud.getImporte());
				}
			}
			solicitudBean.setPagoSolicitudes(aPagoSolicitud);
			//Completamos los datos de RegistroSolicitud
			registroSolicitudQuery.setSolicitudComun(solicitudQuery);
			registroSolicitudQuery.setResultado("OK");
			aRegistrosolicitud = registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);
			Iterator<RegistroSolicitudBean> itRegistroSolicitud = aRegistrosolicitud.iterator();
			while(itRegistroSolicitud.hasNext())
			{
				RegistroSolicitudBean registroSolicitudBean = (RegistroSolicitudBean) itRegistroSolicitud.next();
				if(registroSolicitudBean.getResultado().equals("OK"))
				{
					//Fecha de Registro
					SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
					String fechaRegistro= "";
					if (registroSolicitudBean.getFechaRegistro() != null) {
						fechaRegistro = formatoFecha.format(registroSolicitudBean.getFechaRegistro());
						if(fechaRegistro != null){
							solicitudBean.setFechaRegistro(fechaRegistro);
						}
					}
					//Número de Registro
					if(registroSolicitudBean.getNumeroRegistro() != null)
					{
						solicitudBean.setNumeroRegistro(registroSolicitudBean.getNumeroRegistro());
					}
				}
			}
			solicitudBean.setRegistroSolicitudes(aRegistrosolicitud);

			//Introducimos el Bean de Solicitud al Array con las solicitudes completas
			aSolicitudesDatosCompletos.add(solicitudBean);
		}

		//Creación de Excel con las solicitudes recuperadas
		generarExcel(aSolicitudesDatosCompletos, formulario);
		formulario.setSubmit("Buscar"); //Vuelve para poder buscar de nuevo.
		
	}catch(Exception eGenerico){
		logger.error("Error ExpExcelSolicitudesPresSpring - doExecute ",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}

	/**
	 * Generación del excel.
	 *
	 * @param aSolicitudesDatosCompletos ArrayList<SolicitudBean>
	 * @param formulario el formulario
	 */
	public void generarExcel(ArrayList aSolicitudesDatosCompletos,BuscarSolicitudesPresencialesForm formulario){
		
		int iFila = 1;
		int iCol = 0;
		// Proceso la información y genero el xls
		HSSFWorkbook objLibro = new HSSFWorkbook();
		//Establecer el tipo de fuente 
		HSSFFont fuente = objLibro.createFont();
		fuente.setColor(IndexedColors.BLUE.getIndex());
		fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// Luego creamos el objeto que se encargará de aplicar el estilo a la celda
		HSSFCellStyle estiloCelda = objLibro.createCellStyle();
		estiloCelda.setAlignment(HSSFCellStyle. ALIGN_JUSTIFY);
		estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloCelda.setFont(fuente);
		estiloCelda.setWrapText(false);

		// Se crea la hoja del Libro de la Excel
		HSSFSheet hoja1 = objLibro.createSheet(properties.getProperty("conf.nombreHojaExcelSolicitudes") + "1"); //Hoja 1
		
		//PRIMERA FILA QUE CONTENDRÁ LOS NOMBRES DE LOS CAMPOS
		// Proceso la información y genero el xls. Número de la Fila
		HSSFRow fila = hoja1.createRow((short)iFila);
		
		//Se usa para ajustar el ancho de las columnas
		Sheet sheet = objLibro.getSheetAt(0);
	
		// Creamos la celda, aplicamos el estilo y definimos el tipo de dato que contendrá la celda
		//ID //0
		HSSFCell celda = fila.createCell(iCol);	//Número de la columna comenzando por 0
		celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Estilo de la Celda
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.id")); //Valor a incluir
		sheet.autoSizeColumn((short)iCol); //ajusta el ancho de la primera columna
		iCol++;
		//NUM.SOLICITUD //1
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.numeroSolicitud"));
		sheet.autoSizeColumn((short)iCol); //ajusta el ancho de la segunda columna
		iCol++;
		//TIPO SOLICITUD //2
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoSolicitud"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//ESPECIALIDAD //3
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.especialidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CONVOCATORIA //4
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.convocatoria"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NIF //5
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nif"));
		
		iCol++;
		//NOMBRE //6
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nombre"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//PRIMER APELLIDO /7
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.primerApellido"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//SEGUNDO APELLIDO /8
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.segundoApellido"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//FECHA NACIMIENTO /9
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fechaNacimiento"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//SEXO /10
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.sexo"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//PROVINCIA NACIMIENTO /11
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.provinciaNacimiento"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//LOCALIDAD NACIMIENTO /12
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.localidadNacimiento"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NACIONALIDAD /13
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nacionalidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TELEFONO /14
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.telefono"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CORREO ELECTRÓNICO /15
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.correoElectronico"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CALLE /16
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.calle"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CÓDIGO POSTAL /17
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.codigoPostal"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//MUNICIPIO DOMICILIO /18
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.municipioDomicilio"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//PROVINCIA DOMICILIO //19
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.provinciaDomicilio"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NACION DOMICILIO //20
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nacionDomicilio"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//PROVINCIA DE EXAMEN /21
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.provinciaExamen"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TIPO DISCAPACIDAD /22
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoDiscapacidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//% DISCAPACIDAD /23
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.porcentajeDiscapacidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//ADAPTACION /24
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.adaptacionDiscapacidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//RESERVA PLAZA DISCAPACIDAD /25
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.reservaPlazaDiscapacidad"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//OBSERVACIONES /26
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.observaciones"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TITULO /27
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.titulo"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//OTROS TITULOS /28
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.otrosTitulos"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//Datos A /29
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.datosA"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//Datos B /30
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.datosB"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//Datos C /31
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.datosC"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//IMPORTE /32
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.importe"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//EJERCICIO /33
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.ejercicio"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CENTRO GESTOR /34
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.centroGestor"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TIPO PAGO /35
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.tipoPago"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//FECHA PAGO /36
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fechaPago"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NRC /37
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.nrc"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//FECHA REGISTRO /38
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.fechaRegistro"));
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//NUMERO REGISTRO /39
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(RESOURCE_MESSAGE.getString("field.exportarSolicitud.numeroRegistro"));
		sheet.autoSizeColumn((short)iCol);
		
		
		
		Iterator<SolicitudBean> it = aSolicitudesDatosCompletos.iterator();
		//Recogemos cada solicitud completa para insertar cada fila de la excel 
		while (it.hasNext())
		{
			//Creamos nueva Fila
			iFila++;
			fila = hoja1.createRow((short)iFila);
			
			
			SolicitudBean solicitudBean = (SolicitudBean) it.next();
			//ID
			celda = fila.createCell(0);	//Número de la columna comenzando por 0
			celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Tipo de la celda
			if(solicitudBean.getId() != null)
				celda.setCellValue(solicitudBean.getId()); //Valor a incluir
			//NUM.SOLICITUD
			celda = fila.createCell(1);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNumeroSolicitud() != null)
				celda.setCellValue(solicitudBean.getNumeroSolicitud());
			//TIPO SOLICITUD
			celda = fila.createCell(2);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getTipoDescripcion() != null)
				celda.setCellValue(solicitudBean.getTipoDescripcion());
			//ESPECIALIDAD
			celda = fila.createCell(3);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionEspecialidad() != null)
				celda.setCellValue(solicitudBean.getDescripcionEspecialidad());
			//CONVOCATORIA
			celda = fila.createCell(4);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getIdConvocatoria() != null)
				celda.setCellValue(solicitudBean.getIdConvocatoria());
			//NIF
			celda = fila.createCell(5);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNif() != null)
				celda.setCellValue(solicitudBean.getNif());
			//NOMBRE
			celda = fila.createCell(6);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNombre() != null)
				celda.setCellValue(solicitudBean.getNombre());
			//PRIMER APELLIDO
			celda = fila.createCell(7);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getPrimerApellido()!= null)
				celda.setCellValue(solicitudBean.getPrimerApellido());
			//SEGUNDO APELLIDO
			celda = fila.createCell(8);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getSegundoApellido() != null)
				celda.setCellValue(solicitudBean.getSegundoApellido());
			//FECHA NACIMIENTO
			celda = fila.createCell(9);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			
			if(solicitudBean.getFechaNacimiento() != null)
			{
				//Fecha de Nacimiento Date a String
				SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				String fechaNacimiento= "";
				fechaNacimiento = formatoFecha.format(solicitudBean.getFechaNacimiento());
				celda.setCellValue(fechaNacimiento);
			}
			//SEXO
			celda = fila.createCell(10);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getSexo() != null)
				celda.setCellValue(solicitudBean.getSexo().toString());
			//PROVINCIA NACIMIENTO
			celda = fila.createCell(11);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionIdProvinciaNacimiento() != null)
				celda.setCellValue(solicitudBean.getDescripcionIdProvinciaNacimiento());
			//LOCALIDAD NACIMIENTO
			celda = fila.createCell(12);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getLocalidadNacimiento() != null)
				celda.setCellValue(solicitudBean.getLocalidadNacimiento());
			//NACIONALIDAD
			celda = fila.createCell(13);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNacionalidad() != null)
				celda.setCellValue(solicitudBean.getNacionalidad());
			//TELEFONO
			celda = fila.createCell(14);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getTelefono() != null)
				celda.setCellValue(solicitudBean.getTelefono());
			//EMAIL
			celda = fila.createCell(15);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getEmail() != null)
				celda.setCellValue(solicitudBean.getEmail()); 
			//CALLE
			celda = fila.createCell(16);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getCalleDomicilio() != null)
				celda.setCellValue(solicitudBean.getCalleDomicilio());
			//CÓDIGO POSTAL
			celda = fila.createCell(17);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getCodigoPostalDomicilio() != null)
				celda.setCellValue(solicitudBean.getCodigoPostalDomicilio());
			//MUNICIPIO DOMICILIO
			celda = fila.createCell(18);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getMunicipioDomicilio() != null)
				celda.setCellValue(solicitudBean.getMunicipioDomicilio());
			//PROVINCIA DOMICILIO
			celda = fila.createCell(19);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionIdProvinciaDomicilio() != null)
				celda.setCellValue(solicitudBean.getDescripcionIdProvinciaDomicilio());
			//NACION DOMICILIO
			celda = fila.createCell(20);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNacionalidad() != null) 
			celda.setCellValue(solicitudBean.getNacionPaisDomicilio()); 
			//PROVINCIA DE EXAMEN
			celda = fila.createCell(21);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionIdProvinciaExamen() != null)
				celda.setCellValue(solicitudBean.getDescripcionIdProvinciaExamen());
			//TIPO DISCAPACIDAD
			celda = fila.createCell(22);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionTipoDiscapacidad()!= null)
				celda.setCellValue(solicitudBean.getDescripcionTipoDiscapacidad());
			//% DISCAPACIDAD
			celda = fila.createCell(23);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getPorcentajeDiscapacidad() != null)
				celda.setCellValue(solicitudBean.getPorcentajeDiscapacidad());
			//ADAPTACION
			celda = fila.createCell(24);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDetalleDiscapacidad()!=null)
				celda.setCellValue(solicitudBean.getDetalleDiscapacidad());
			//RESERVA PLAZA
			celda = fila.createCell(25);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getReservaDiscapacidad()!=null)
				celda.setCellValue(solicitudBean.getReservaDiscapacidad());
			//OBSERVACIONES / COMENTARIOS
			celda = fila.createCell(26);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getComentarios() != null) 
				celda.setCellValue(solicitudBean.getComentarios());
			//TITULO
			celda = fila.createCell(27);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionTituloOficial() != null)
				celda.setCellValue(solicitudBean.getDescripcionTituloOficial());
			//OTROS TITULOS
			celda = fila.createCell(28);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getOtrosTitulos() != null)
				celda.setCellValue(solicitudBean.getOtrosTitulos());
			//Datos A
			celda = fila.createCell(29);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDatosA() != null)
				celda.setCellValue(solicitudBean.getDatosA());
			//Datos B
			celda = fila.createCell(30);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDatosB() != null)
				celda.setCellValue(solicitudBean.getDatosB());
			//Datos C
			celda = fila.createCell(31);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDatosC() != null)
				celda.setCellValue(solicitudBean.getDatosC());
			//IMPORTE
			celda = fila.createCell(32);
			celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			if(solicitudBean.getImporte() != null)
				celda.setCellValue(solicitudBean.getImporte());
			//EJERCICIO
			celda = fila.createCell(33);
			celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			if(solicitudBean.getEjercicio() != null)	
				celda.setCellValue(solicitudBean.getEjercicio());
			//CENTRO GESTOR
			celda = fila.createCell(34);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getCentroGestor() != null)
				celda.setCellValue(solicitudBean.getCentroGestor());
			//TIPO PAGO
			celda = fila.createCell(35);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getDescripcionTipoPago() != null)
				celda.setCellValue(solicitudBean.getDescripcionTipoPago());
			//FECHA PAGO
			celda = fila.createCell(36);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getFechaPago() != null)
				celda.setCellValue(solicitudBean.getFechaPago());
			//NRC
			celda = fila.createCell(37);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNrc() != null)
				celda.setCellValue(solicitudBean.getNrc());
			//FECHA REGISTRO
			celda = fila.createCell(38);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getFechaRegistro() != null)
				celda.setCellValue(solicitudBean.getFechaRegistro()); 
			//NUMERO REGISTRO
			celda = fila.createCell(39);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(solicitudBean.getNumeroRegistro() != null)
					celda.setCellValue(solicitudBean.getNumeroRegistro()); 
		}

		try
		{
			// Volcamos la informacion a un archivo.
			String sNombreFichero= "";
			if(formulario.getAccion()!= null && formulario.getAccion().equals("ExportarConIncidencias"))//Para ponerle al fichero su nombre correcto
			{
				sNombreFichero = properties.getProperty("conf.nombreFicheroExcelSolicitudesConIncidencias");
			}else 
			{
				if(formulario.getAccion().equals("ExportarPresenciales")){
					sNombreFichero = properties.getProperty("conf.nombreFicheroExcelSolicitudesPresenciales");
				}else{//Viene de Solicitudes Registradas
					sNombreFichero = properties.getProperty("conf.nombreFicheroExcelSolicitudes");
				}
				 
			}
			String sExtension = properties.getProperty("conf.extensionFicheroExcelSolicitudes");
			
			javax.servlet.ServletOutputStream stream;
			stream = this.getResponse().getOutputStream();
			
			String contentDisposition = "attachment; filename=\"" + sNombreFichero + sExtension + "\"";

			HttpServletResponse resp = this.getResponse();
	
			resp.setContentType("application/vnd.ms-excel");
			
			resp.setHeader("Content-Disposition", contentDisposition);
			
			objLibro.write(stream);
			
			stream.flush();
			stream.close();

		}catch (FileNotFoundException ex)
		{
			logger.error("Error ExpExcelSolicitudesPresSpring - No se ha encontrado el fichero ",ex);
			
			
		}catch (IOException ex)
		{
			logger.error("Error ExpExcelSolicitudesPresSpring - No se ha encontrado el fichero ",ex);
			
		}
	}
		
	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return convocatoriaManager ConvocatoriasManager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager ConvocatoriasManager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public void setSolicitudesRegistradasManager(
			SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
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
	 * @return  registroSolicitudManager RegistroSolicitudManager
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
			final RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	
}
