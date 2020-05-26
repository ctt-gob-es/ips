package es.map.ipsg.action.cuadroMando;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DescargaModelo790Query;
import es.map.ipsg.action.convocatoria.BuscarConvocatoriasSpring;
import es.map.ipsg.bean.ConvocatoriasViewBean;
import es.map.ipsg.bean.DescargaModelo790Bean;
import es.map.ipsg.form.ConsultarCuadroMandoForm;
import es.map.ipsg.manager.DescargaModelo790Manager;
import es.map.ipsg.util.UtilesIPSG;


/**
 * El Class ExportarExcelCuadroMandoSpring.
 *
 * @author amartinl
 */
public class ExportarExcelCuadroMandoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarConvocatoriasSpring.class);
	
	/** La constante STRINGTOTAL. */
	private static final String STRINGTOTAL = "TOTAL";
	
	/** el properties. */
	private static Properties properties;
	
	/** el descarga modelo 790 manager. */
	private DescargaModelo790Manager descargaModelo790Manager;

	

	/**
	 * Crea una nueva exportar excel cuadro mando spring.
	 */
	public ExportarExcelCuadroMandoSpring() {
		try {
			setDescargaModelo790Manager((DescargaModelo790Manager) getBean("descargaModelo790Manager"));
			properties = (Properties) getBean("propertiesBean");
			
		} catch (Exception e) {
			logger.error("Error ExportarExcelCuadroMandoSpring: ",e);
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
		//******************************************************************
	try{
		//Cogemos el form del jsp
		ConsultarCuadroMandoForm formulario;
		formulario = (ConsultarCuadroMandoForm) form;

		
		List<ConvocatoriasViewBean> lConvocatoriasViewBean = (List<ConvocatoriasViewBean>) this.getRequest().getSession().getAttribute("consultaExportarCuadroMando");
		
		//Parámetros que muestran u ocultan los campos de la tabla de resultado
		setRequestAttribute("mostrarCentroGestor", formulario.getCkCentroGestor());
		setRequestAttribute("mostrarCuerpoEscala", formulario.getCkCuerpoEscala());
		setRequestAttribute("mostrarEjercicio", formulario.getCkEjercicio());
		setRequestAttribute("mostrarEstado", formulario.getCkEstado());
		setRequestAttribute("mostrarFechaBOE", formulario.getCkFechaBOE());
		setRequestAttribute("mostrarFormaAcceso", formulario.getCkFormaAcceso());
		setRequestAttribute("mostrarNumDescargasManuales", formulario.getCkNumDescargasManuales());
		setRequestAttribute("mostrarNumDescargasManualesTotal", formulario.isCkNumDescargasManualesTotal());
		setRequestAttribute("mostrarNumSolSinIntentoPago", formulario.isCkNumSolSinIntentoPago());
		setRequestAttribute("mostrarNumPagosSinIntentoRegistro", formulario.isCkNumPagosSinIntentoRegistro());
		setRequestAttribute("mostrarNumPlazasDiscap", formulario.getCkNumPlazasDiscap());
		setRequestAttribute("mostrarNumPlazasTotales", formulario.getCkNumPlazasTotales());
		setRequestAttribute("mostrarNumSolIncPagoResuelta", formulario.getCkNumSolIncPagoResuelta());
		setRequestAttribute("mostrarNumSolIncPagoSinResolver", formulario.getCkNumSolIncPagoSinResolver());
		setRequestAttribute("mostrarNumSolIncRegistroResuelta", formulario.getCkNumSolIncRegistroResuelta());
		setRequestAttribute("mostrarNumSolIncRegistroSinResolver", formulario.getCkNumSolIncRegistroSinResolver());
		setRequestAttribute("mostrarNumSolPresenciales", formulario.getCkNumSolPresenciales());
		setRequestAttribute("mostrarNumSolTelematicas", formulario.getCkNumSolTelematicas());
		//Se le pasa todas las convocatorias ordenadas por Ministerios
		setRequestAttribute("lConvocatoriasViewBean", lConvocatoriasViewBean);
		
		setRequestAttribute("sVieneMenu", "N"); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.
		
		//Creación de Excel con las solicitudes recuperadas
		generarExcel(lConvocatoriasViewBean, formulario);
		
		
		formulario.setSubmit("Buscar"); //Vuelve para poder buscar de nuevo.
	
	}catch(Exception eGenerico){
		logger.error("Error ExportarExcelCuadroMandoSpring - doExecute: ",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}

	
	/**
	 * Generación del excel.
	 *
	 * @param lConvocatoriasViewBean ArrayList<ConvocatoriasViewBean>
	 * @param formulario ConsultarCuadroMandoForm
	 */
	private void generarExcel(List<ConvocatoriasViewBean> lConvocatoriasViewBean, ConsultarCuadroMandoForm formulario){
		
		int iFila = 1;
		int iCol = 0;
		UtilesIPSG utilesIPSG=new UtilesIPSG();
		// Proceso la información y genero el xls
		HSSFWorkbook objLibro = new HSSFWorkbook();
		//Establecer el tipo de fuente 
		
		
		HSSFFont fuenteRojo = objLibro.createFont();
		fuenteRojo.setColor(IndexedColors.RED.getIndex());
		fuenteRojo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFFont fuente = objLibro.createFont();
		fuente.setColor(IndexedColors.BLUE.getIndex());
		fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		
		// Luego creamos el objeto que se encargará de aplicar el estilo a la celda
		HSSFCellStyle estiloCelda = objLibro.createCellStyle();
		estiloCelda.setAlignment(HSSFCellStyle. ALIGN_JUSTIFY);
		estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloCelda.setFont(fuente);
		estiloCelda.setWrapText(false);
		
		HSSFCellStyle estiloCeldaRojo = objLibro.createCellStyle();
		estiloCeldaRojo.setAlignment(HSSFCellStyle. ALIGN_JUSTIFY);
		estiloCeldaRojo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloCeldaRojo.setFont(fuenteRojo);
		estiloCeldaRojo.setWrapText(false);
		
		HSSFCellStyle estiloFilaMinisterio = objLibro.createCellStyle();
		estiloFilaMinisterio.setAlignment(HSSFCellStyle. ALIGN_LEFT);
		estiloFilaMinisterio.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloFilaMinisterio.setFont(fuente);
		estiloFilaMinisterio.setWrapText(false);
		estiloFilaMinisterio.setFillBackgroundColor(HSSFColor.WHITE.index);
		
		HSSFCellStyle estiloFilaMinisterioRojo = objLibro.createCellStyle();
		estiloFilaMinisterioRojo.setAlignment(HSSFCellStyle. ALIGN_LEFT);
		estiloFilaMinisterioRojo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloFilaMinisterioRojo.setFont(fuenteRojo);
		estiloFilaMinisterioRojo.setWrapText(false); 
		estiloFilaMinisterioRojo.setFillBackgroundColor(HSSFColor.WHITE.index); 
		

		// Se crea la hoja del Libro de la Excel
		HSSFSheet hoja1 = objLibro.createSheet(properties.getProperty("conf.nombreHojaExcelCuadroMando") + "1"); //Hoja 1
		
		//PRIMERA FILA QUE CONTENDRÁ LOS NOMBRES DE LOS CAMPOS
		// Proceso la información y genero el xls. Número de la Fila
		HSSFRow fila = hoja1.createRow((short)iFila);
		
		//Se usa para ajustar el ancho de las columnas
		Sheet sheet = objLibro.getSheetAt(0);
		HSSFCell celda = null;
		String idMinisterioAux = "0";

		Iterator<ConvocatoriasViewBean> it = lConvocatoriasViewBean.iterator();
		//Recogemos cada solicitud completa para insertar cada fila de la excel 
		while (it.hasNext())
		{
			ConvocatoriasViewBean convocatoriasViewBean = (ConvocatoriasViewBean) it.next();
					
			if(convocatoriasViewBean.getIdMinisterio() != null && !convocatoriasViewBean.getIdMinisterio().toString().equals(idMinisterioAux))
			{
				iCol = 0;	
				idMinisterioAux = convocatoriasViewBean.getIdMinisterio().toString();
				iFila++;
				fila = hoja1.createRow((short)iFila);
				celda = fila.createCell(iCol);	//Número de la columna comenzando por 0
				fila.setRowStyle(estiloFilaMinisterio);
				celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Estilo de la Celda
				celda.setCellStyle(estiloCelda);
				celda.setCellValue(convocatoriasViewBean.getDesMinisterio()); //Valor a incluir
				
				//Creamos nueva Fila para los titulos de cabecera
				iFila++;
				fila = hoja1.createRow((short)iFila);
				//Comenzamos de nuevo en la columna 0
				iCol = 0;
				//TEXTOS DE LA CABECERA 			
				// Creamos la celda, aplicamos el estilo y definimos el tipo de dato que contendrá la celda
				//CENTRO GESTOR //0
				if(formulario.getCkCentroGestor())
				{
					celda = fila.createCell(iCol);	//Número de la columna comenzando por 0
					celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Estilo de la Celda
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.centroGestor.mayus")); //Valor a incluir
					sheet.autoSizeColumn((short)iCol); //ajusta el ancho de la primera columna
					iCol++;
				}
				//EJERCICIO //1
				if(formulario.getCkEjercicio())
				{	
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.ejercicio.mayus"));
					sheet.autoSizeColumn((short)iCol); //ajusta el ancho de la segunda columna
					iCol++;
				}
				//CUERPO O ESCALA //2
				if(formulario.getCkCuerpoEscala())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.cuerpoEscala.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//FORMA DE ACCESO //3
				if(formulario.getCkFormaAcceso())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.formaAcceso.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//FECHA B.O.E. //4
				if(formulario.getCkFechaBOE())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.fechaBOE.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}				
				//ESTADO /8
				if(formulario.getCkEstado())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.estado.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//NÚMERO DE PLAZAS //6
				if(formulario.getCkNumPlazasTotales())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numPlazas.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//NÚMERO PLAZAS DISCAPACITADOS /7
				if(formulario.getCkNumPlazasDiscap())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numPlazasDiscap.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//NÚMERO DE DESCARGAS MANUALES MODELO 790 /9
				if(formulario.getCkNumDescargasManuales() || formulario.isCkNumDescargasManualesTotal())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numDescargasManuales.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//NÚMERO DE SOLICITUDES TELEMÁTICAS /10
				if(formulario.getCkNumSolTelematicas())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numSolTelematicas.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//NÚMERO DE SOLICITUDES PRESENCIALES /11
				if(formulario.getCkNumSolPresenciales())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numSolPresenciales.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN PAGO /12
				if(formulario.getCkNumInscripcionSinPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numInscripcionSinPago.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}

				if(formulario.isCkNumSolSinIntentoPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numSolSinIntentoPago.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//NÚMERO DE PAGOS SIN REGISTRO /13
				if(formulario.getCkNumPagosSinRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numPagosSinRegistro.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				
				if(formulario.isCkNumPagosSinIntentoRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numSolSinIntentoRegistro.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				
				
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO RESUELTAS /14
				if(formulario.getCkNumSolIncPagoResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numSolIncPagoResuelta.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO SIN RESOLVER /15
				if(formulario.getCkNumSolIncPagoSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numSolIncPagoSinResolver.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO RESUELTAS /16
				if(formulario.getCkNumSolIncRegistroResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.incRegistroResuelta.mayus"));
					sheet.autoSizeColumn((short)iCol);
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO SIN RESOLVER /17
				if(formulario.getCkNumSolIncRegistroSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellStyle(estiloCelda);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.incRegistroSinResolver.mayus"));
					sheet.autoSizeColumn((short)iCol);
				}
				//FIN TEXTOS DE LA CABECERA
				
			}
		//MOSTRAMOS RESULTADOS DE LOS CENTROS GESTORES
			//Creamos nueva Fila
			//Se escribirán los registros que NO sean el total por cada ministerio
			if(!convocatoriasViewBean.getDesCentroGestor().equals(STRINGTOTAL))
			{
				iFila++;
				fila = hoja1.createRow((short)iFila);
				iCol=0;
				//CENTRO GESTOR //0
				if(formulario.getCkCentroGestor())
				{
					celda = fila.createCell(iCol);	//Número de la columna comenzando por 0
					celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Tipo de la celda
					if(convocatoriasViewBean.getDesCentroGestor() != null)
						celda.setCellValue(convocatoriasViewBean.getDesCentroGestor()); //Valor a incluir
					iCol++;
				}
				//EJERCICIO //1
				if(formulario.getCkEjercicio())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getEjercicio() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getEjercicio()));
					iCol++;
				}
				//CUERPO O ESCALA //2
				if(formulario.getCkCuerpoEscala())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					if(convocatoriasViewBean.getDesCuerpoEscala() != null)
						celda.setCellValue(convocatoriasViewBean.getDesCuerpoEscala());
					iCol++;
				}
				///FORMA DE ACCESO //3
				if(formulario.getCkFormaAcceso())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					if(convocatoriasViewBean.getDesFormaAcceso() != null)
						celda.setCellValue(convocatoriasViewBean.getDesFormaAcceso());
					iCol++;
				}
				//FECHA B.O.E. //4
				if(formulario.getCkFechaBOE())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					if(convocatoriasViewBean.getFechaBoe() != null)
					{
						celda.setCellValue(convocatoriasViewBean.getFechaBoe());
					}
					iCol++;
				}			
				//ESTADO /8
				if(formulario.getCkEstado())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					if(convocatoriasViewBean.getDesEstadoConvocatoria() != null)
						celda.setCellValue(convocatoriasViewBean.getDesEstadoConvocatoria());
					iCol++;
				}
				//NÚMERO DE PLAZAS //6
				if(formulario.getCkNumPlazasTotales())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getPlazasTotal()!= null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getPlazasTotal()));
					iCol++;
				}
				//NÚMERO PLAZAS DISCAPACITADOS /7
				if(formulario.getCkNumPlazasDiscap())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getPlazasDiscapacitados()!= null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getPlazasDiscapacitados()));
					iCol++;
				}				
				//NÚMERO DE DESCARGAS MANUALES MODELO 790 /9
				if(formulario.getCkNumDescargasManuales() || formulario.isCkNumDescargasManualesTotal())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumDescargasManuales() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumDescargasManuales()));
					iCol++;
				}
				
				//NÚMERO DE SOLICITUDES TELEMÁTICAS /10
				if(formulario.getCkNumSolTelematicas())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumSolTelematica() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumSolTelematica()));
					iCol++;
				}
				//NÚMERO DE SOLICITUDES PRESENCIALES /11
				if(formulario.getCkNumSolPresenciales())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumSolPresencial() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumSolPresencial()));
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN PAGO /12
				if(formulario.getCkNumInscripcionSinPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumInscripcionSinPago() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumInscripcionSinPago()));
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN INTENTO DE PAGO
				if(formulario.isCkNumSolSinIntentoPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumSolSinIntentoPago() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumSolSinIntentoPago()));
					iCol++;
				}
				//NÚMERO DE PAGOS SIN REGISTRO /13
				if(formulario.getCkNumPagosSinRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumPagosSinRegistro() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumPagosSinRegistro()));
					iCol++;
				}
				//NÚMERO DE PAGOS SIN INTENTO DE REGISTRO /13
				if(formulario.isCkNumPagosSinIntentoRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumPagosSinIntentoRegistro() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumPagosSinIntentoRegistro()));
					iCol++;
				}
				
				
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO RESUELTAS /14
				if(formulario.getCkNumSolIncPagoResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumSolIncPagoResuelta() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumSolIncPagoResuelta()));
					iCol++;
				}
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO SIN RESOLVER /15
				if(formulario.getCkNumSolIncPagoSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumSolIncPagoSinResolver() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumSolIncPagoSinResolver()));
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO RESUELTAS /16
				if(formulario.getCkNumSolIncRegistroResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumSolIncRegistroResuelta() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumSolIncRegistroResuelta()));
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO SIN RESOLVER /17
				if(formulario.getCkNumSolIncRegistroSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumSolIncRegistroSinResolver() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumSolIncRegistroSinResolver()));
				}
			}
			//////////////////////////////
			//Se escribiran los totales por cada ministerio
			if(convocatoriasViewBean.getDesCentroGestor().equals(STRINGTOTAL))
			{
				//Creamos nueva Fila para el texto 'Totales Ministerio'
				iFila++;
				fila = hoja1.createRow((short)iFila);
				celda = fila.createCell(0);	//Número de la columna comenzando por 0
				celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Tipo de la celda
				celda.setCellStyle(estiloCelda);
				celda.setCellValue("Total Ministerio");
				
				
				
				//Creamos nueva Fila para los totales
				iFila++;
				fila = hoja1.createRow((short)iFila);
				iCol=0;
				//CENTRO GESTOR //0
				if(formulario.getCkCentroGestor())
				{
					celda = fila.createCell(iCol);	//Número de la columna comenzando por 0
					celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Tipo de la celda
					celda.setCellValue("");
					iCol++;
				}
				//EJERCICIO //1
				if(formulario.getCkEjercicio())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					celda.setCellValue("");
					iCol++;
				}
				//CUERPO O ESCALA //2
				if(formulario.getCkCuerpoEscala())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue("");
					iCol++;
				}
				///FORMA DE ACCESO //3
				if(formulario.getCkFormaAcceso())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue("");
					iCol++;
				}
				//FECHA B.O.E. //4
				if(formulario.getCkFechaBOE())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue("");
					iCol++;
				}				
				//ESTADO /8
				if(formulario.getCkEstado())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue("");
					iCol++;
				}
				//NÚMERO DE PLAZAS //6
				if(formulario.getCkNumPlazasTotales())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					celda.setCellValue(convocatoriasViewBean.getNumTotalPlazasTotales());
					iCol++;
				}
				//NÚMERO PLAZAS DISCAPACITADOS /7
				if(formulario.getCkNumPlazasDiscap())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					celda.setCellValue(convocatoriasViewBean.getNumTotalPlazasDiscapacitados());
					iCol++;
				}
				//NÚMERO DE DESCARGAS MANUALES MODELO 790 /9
				if(formulario.getCkNumDescargasManuales() || formulario.isCkNumDescargasManualesTotal())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalDescargasManuales() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalDescargasManuales()));
					iCol++;
				}
				
				//NÚMERO DE SOLICITUDES TELEMÁTICAS /10
				if(formulario.getCkNumSolTelematicas())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalSolTelematica() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalSolTelematica()));
					iCol++;
				}
				//NÚMERO DE SOLICITUDES PRESENCIALES /11
				if(formulario.getCkNumSolPresenciales())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalSolPresencial() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalSolPresencial()));
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN PAGO /12
				if(formulario.getCkNumInscripcionSinPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalInscripcionSinPago() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalInscripcionSinPago()));
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN INTENTO DE PAGO 
				if(formulario.isCkNumSolSinIntentoPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalInscripcionSinIntentoPago() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalInscripcionSinIntentoPago()));
					iCol++;
				}
				//NÚMERO DE PAGOS SIN REGISTRO /13
				if(formulario.getCkNumPagosSinRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalPagosSinRegistro() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalPagosSinRegistro()));
					iCol++;
				}
				//NÚMERO DE PAGOS SININTENTO DE REGISTRO 
				if(formulario.isCkNumPagosSinIntentoRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalPagosSinIntentoRegistro() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalPagosSinIntentoRegistro()));
					iCol++;
				}
				
				
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO RESUELTAS /14
				if(formulario.getCkNumSolIncPagoResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalSolIncPagoResuelta() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalSolIncPagoResuelta()));
					iCol++;
				}
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO SIN RESOLVER /15
				if(formulario.getCkNumSolIncPagoSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalSolIncPagoSinResolver() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalSolIncPagoSinResolver()));
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO RESUELTAS /16
				if(formulario.getCkNumSolIncRegistroResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalSolIncRegistroResuelta() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalSolIncRegistroResuelta()));
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO SIN RESOLVER /17
				if(formulario.getCkNumSolIncRegistroSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convocatoriasViewBean.getNumTotalSolIncRegistroSinResolver() != null)
						celda.setCellValue(Integer.valueOf(convocatoriasViewBean.getNumTotalSolIncRegistroSinResolver()));
				}
				
			}
		}

		if( formulario.getCkCentroGestor()==false && formulario.getCkCuerpoEscala()==false
				&& formulario.getCkEjercicio()==false && formulario.getCkEstado()==false
				&& formulario.getCkFechaBOE()==false && formulario.getCkFormaAcceso()== false
				&& formulario.getCkNumPlazasDiscap()==false	&& formulario.getCkNumPlazasTotales()==false
				&& formulario.getDsCentroGestor().equals("")
				// Se muestra el total
				&& lConvocatoriasViewBean.size()>0)
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
				for(ConvocatoriasViewBean convAux:lConvocatoriasViewBean)
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
			
			
			// Creacion de las nuevas celdas
			
				//Creamos nueva Fila para el texto Totales
				iFila++;
				fila.setRowStyle(estiloFilaMinisterioRojo);
				fila = hoja1.createRow((short)iFila);
				fila.setRowStyle(estiloFilaMinisterioRojo);
				iCol=0;
				
				//NÚMERO DE DESCARGAS MANUALES MODELO 790 /9
				if(formulario.getCkNumDescargasManuales() || formulario.isCkNumDescargasManualesTotal())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalDescargasManuales"));
					iCol++;
				}
				
				//NÚMERO DE SOLICITUDES TELEMÁTICAS /10
				if(formulario.getCkNumSolTelematicas())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesTelematicas"));
					iCol++;
				}
				//NÚMERO DE SOLICITUDES PRESENCIALES /11
				if(formulario.getCkNumSolPresenciales())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesPresenciales"));
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN PAGO /12
				if(formulario.getCkNumInscripcionSinPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesSinPago"));
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN INTENTO DE PAGO 
				if(formulario.isCkNumSolSinIntentoPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesSinIntentoPago"));
					iCol++;
				}
				//NÚMERO DE PAGOS SIN REGISTRO /13
				if(formulario.getCkNumPagosSinRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesSinRegistro"));
					iCol++;
				}
				//NÚMERO DE PAGOS SIN INTENTO DE REGISTRO
				if(formulario.isCkNumPagosSinIntentoRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesSinIntentoRegistro"));
					iCol++;
				}
				
				
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO RESUELTAS /14
				if(formulario.getCkNumSolIncPagoResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesIncPagoResuelta"));
					iCol++;
				}
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO SIN RESOLVER /15
				if(formulario.getCkNumSolIncPagoSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesIncPagoSinResolver"));
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO RESUELTAS /16
				if(formulario.getCkNumSolIncRegistroResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);
					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesIncRegistroResuelta"));
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO SIN RESOLVER /17
				if(formulario.getCkNumSolIncRegistroSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellStyle(estiloCeldaRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_STRING);

					celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.totalsolicitudesIncRegistroSinResolver"));
				}
				
				
				
				
				
				//Creamos nueva Fila para los totales
				iFila++;
				fila = hoja1.createRow((short)iFila);
				fila.setRowStyle(estiloFilaMinisterioRojo);
				iCol=0;
				
				//NÚMERO DE DESCARGAS MANUALES MODELO 790 /9
				if(formulario.getCkNumDescargasManuales() || formulario.isCkNumDescargasManualesTotal())
				{
					celda = fila.createCell(iCol);
					fila.setRowStyle(estiloFilaMinisterioRojo);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalDescargasManuales() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalDescargasManuales()));
					iCol++;
				}
				
				//NÚMERO DE SOLICITUDES TELEMÁTICAS /10
				if(formulario.getCkNumSolTelematicas())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalSolTelematica() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalSolTelematica()));
					iCol++;
				}
				//NÚMERO DE SOLICITUDES PRESENCIALES /11
				if(formulario.getCkNumSolPresenciales())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalSolPresencial() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalSolPresencial()));
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN PAGO /12
				if(formulario.getCkNumInscripcionSinPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalInscripcionSinPago() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalInscripcionSinPago()));
					iCol++;
				}
				//NÚMERO DE INSCRIPCIONES SIN INTENTO DE PAGO 
				if(formulario.isCkNumSolSinIntentoPago())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalInscripcionSinIntentoPago() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalInscripcionSinIntentoPago()));
					iCol++;
				}
				//NÚMERO DE PAGOS SIN REGISTRO /13
				if(formulario.getCkNumPagosSinRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalPagosSinRegistro() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalPagosSinRegistro()));
					iCol++;
				}
				//NÚMERO DE PAGOS SIN INTENTO DE REGISTRO
				if(formulario.isCkNumPagosSinIntentoRegistro())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalPagosSinIntentoRegistro() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalPagosSinIntentoRegistro()));
					iCol++;
				}
				
				
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO RESUELTAS /14
				if(formulario.getCkNumSolIncPagoResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalSolIncPagoResuelta() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalSolIncPagoResuelta()));
					iCol++;
				}
				//NÚMERO DE SOLICITUDES CON INCIDENCIAS DE PAGO SIN RESOLVER /15
				if(formulario.getCkNumSolIncPagoSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalSolIncPagoSinResolver() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalSolIncPagoSinResolver()));
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO RESUELTAS /16
				if(formulario.getCkNumSolIncRegistroResuelta())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalSolIncRegistroResuelta() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalSolIncRegistroResuelta()));
					iCol++;
				}
				//INCIDENCIAS DE REGISTRO SIN RESOLVER /17
				if(formulario.getCkNumSolIncRegistroSinResolver())
				{
					celda = fila.createCell(iCol);
					celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					if(convTotales.getNumTotalSolIncRegistroSinResolver() != null)
						celda.setCellValue(Integer.valueOf(convTotales.getNumTotalSolIncRegistroSinResolver()));
				}
		}
		
		
		if(formulario.isCkNumDescargasManualesTotal())
		{	
			DescargaModelo790Query descargaModelo790Query = new DescargaModelo790Query();
			descargaModelo790Query.setCalculateNumResults(true);
			descargaModelo790Query.setConvocatoriaIsNull(true);
			descargaModelo790Query.setFechaMin(utilesIPSG.stringToDate(formulario.getFechaDesde()));
			descargaModelo790Query.setFechaMax(utilesIPSG.stringToDate(formulario.getFechaHasta()));
			ArrayList<DescargaModelo790Bean> listaDescargas =descargaModelo790Manager.buscarDescargaModelo790All(descargaModelo790Query);
			int numeroDescargas =listaDescargas.size();
			
			iCol = 0;	
			iFila++;
			fila = hoja1.createRow((short)iFila);
			celda = fila.createCell(iCol);	//Número de la columna comenzando por 0
			fila.setRowStyle(estiloFilaMinisterio);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Estilo de la Celda
			celda.setCellStyle(estiloCelda);
			celda.setCellValue(RESOURCE_MESSAGE.getString("field.cuadroMando.numDescargasManualesBlanco.mayus"));
			
			
			iFila++;
			fila = hoja1.createRow((short)iFila);
			iCol=0;
		
			celda = fila.createCell(iCol);	//Número de la columna comenzando por 0
			celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC); //Tipo de la celda
			celda.setCellValue(numeroDescargas);
			
		}	
		try
		{
			// Volcamos la informacion a un archivo.
			String sNombreFichero= "";
			if(formulario.getAccion()!= null)//Para ponerle al fichero su nombre correcto
			{
				sNombreFichero = properties.getProperty("conf.nombreFicheroExcelCuadroMando");
			}
			String sExtension = properties.getProperty("conf.extensionFichero");
			
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
			logger.error("No se ha encontrado fichero: ",ex);
			
		}catch (IOException ex)
		{
			logger.error("No se ha encontrado el fichero",ex);
			
		}
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
 * Obtiene el descarga modelo 790 manager.
 *
 * @return el descarga modelo 790 manager
 */
public DescargaModelo790Manager getDescargaModelo790Manager() {
	return descargaModelo790Manager;
}

/**
 * Establece el descarga modelo 790 manager.
 *
 * @param descargaModelo790Manager el nuevo descarga modelo 790 manager
 */
public void setDescargaModelo790Manager(
		DescargaModelo790Manager descargaModelo790Manager) {
	this.descargaModelo790Manager = descargaModelo790Manager;
}
		
	

	
}
