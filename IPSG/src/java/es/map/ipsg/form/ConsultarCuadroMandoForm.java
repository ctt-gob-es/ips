package es.map.ipsg.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.util.UtilesIPSG;
import es.map.ipsg.util.Validacion;

/**
 * ConsultarCuadroMandoForm.
 *
 * @author amartinl
 */
public class ConsultarCuadroMandoForm extends SpringForm {
	
	
    /** el id estado. */
    private String idEstado;
	
	/** el estado. */
	private String estado;
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el ds centro gestor. */
	private String dsCentroGestor;
	
	/** el fecha desde. */
	private String fechaDesde;
	
	/** el fecha hasta. */
	private String fechaHasta;
	
	/** el perfil usuario. */
	private String perfilUsuario; //Perfil Usuario
	
	/** el accion. */
	private String accion;
	
	/** el ck centro gestor. */
	private boolean ckCentroGestor;
	
	/** el ck ejercicio. */
	private boolean ckEjercicio;
	
	/** el ck cuerpo escala. */
	private boolean ckCuerpoEscala;
	
	/** el ck estado. */
	private boolean ckEstado;
	
	/** el ck forma acceso. */
	private boolean ckFormaAcceso;
	
	/** el ck fecha BOE. */
	private boolean ckFechaBOE;
	
	/** el ck num plazas totales. */
	private boolean ckNumPlazasTotales;	
	
	/** el ck num plazas discap. */
	private boolean ckNumPlazasDiscap;
	
	/** el ck num inscripcion sin pago. */
	private boolean ckNumInscripcionSinPago;
	
	/** el ck num pagos sin registro. */
	private boolean ckNumPagosSinRegistro;
	
	/** el ck num descargas manuales. */
	private boolean ckNumDescargasManuales;
	
	/** el ck num sol telematicas. */
	private boolean ckNumSolTelematicas;
	
	/** el ck num sol presenciales. */
	private boolean ckNumSolPresenciales;
	
	/** el ck num sol inc pago resuelta. */
	private boolean ckNumSolIncPagoResuelta;
	
	/** el ck num sol inc pago sin resolver. */
	private boolean ckNumSolIncPagoSinResolver;
	
	/** el ck num sol inc registro resuelta. */
	private boolean ckNumSolIncRegistroResuelta;
	
	/** el ck num sol inc registro sin resolver. */
	private boolean ckNumSolIncRegistroSinResolver;
	
	/** el ck num pagos sin intento registro. */
	// Nuevos
	private boolean ckNumPagosSinIntentoRegistro;	
	
	/** el ck num sol sin intento pago. */
	private boolean ckNumSolSinIntentoPago;
	
	/** el ck num descargas manuales total. */
	private boolean ckNumDescargasManualesTotal;
	
	
	
	/** el cambios. */
	private String cambios;
	
	/** el submit. */
	private String submit;
	
	/** el menu. */
	private String menu;

	/*INI-TRA042*/
	/** el lista centros gestores. */
	private List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
	/*FIN-TRA042*/
	
	/**
	 * Obtiene el id estado.
	 *
	 * @return idEstado Integer
	 */
	public String getIdEstado() {
		return idEstado;
	}
	
	/**
	 * Establece el id estado.
	 *
	 * @param idEstado Integer
	 */
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	/** 
	 * @return perfilUsuario String
	 */
	public String getPerfilUsuario() {
		return perfilUsuario;
	}
	
	/**
	 * Establece el perfil usuario.
	 *
	 * @param perfilUsuario String
	 */
	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return estado String
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado String
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return idCentroGestor String
	 */
	public String getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor String
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el fecha desde.
	 *
	 * @return fechaDesde String
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	
	/**
	 * Establece el fecha desde.
	 *
	 * @param fechaDesde String
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	/**
	 * Obtiene el fecha hasta.
	 *
	 * @return fechaHasta String
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	
	/**
	 * Establece el fecha hasta.
	 *
	 * @param fechaHasta String
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	/**
	 * Obtiene el accion.
	 *
	 * @return  accion String
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Establece el accion.
	 *
	 * @param accion String
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	/**
	 * Obtiene el cambios.
	 *
	 * @return  cambios String
	 */
	public String getCambios() {
		return cambios;
	}
	
	/**
	 * Establece el cambios.
	 *
	 * @param cambios String
	 */ 
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return  submit String
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el submit.
	 *
	 * @param submit String
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	/**
	 * Obtiene el menu.
	 *
	 * @return menu String
	 */
	public String getMenu() {
		return menu;
	}
	
	/**
	 * Establece el menu.
	 *
	 * @param menu String
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	/**
	 * Obtiene el ck centro gestor.
	 *
	 * @return ckCentroGestor String
	 */
	public boolean getCkCentroGestor() {
		return ckCentroGestor;
	}
	
	/**
	 * Establece el ck centro gestor.
	 *
	 * @param ckCentroGestor String
	 */
	public void setCkCentroGestor(boolean ckCentroGestor) {
		this.ckCentroGestor = ckCentroGestor;
	}
	
	/**
	 * Obtiene el ck cuerpo escala.
	 *
	 * @return ckCuerpoEscala boolean
	 */
	public boolean getCkCuerpoEscala() {
		return ckCuerpoEscala;
	}
	
	/**
	 * Establece el ck cuerpo escala.
	 *
	 * @param ckCuerpoEscala boolean
	 */
	public void setCkCuerpoEscala(boolean ckCuerpoEscala) {
		this.ckCuerpoEscala = ckCuerpoEscala;
	}
	
	/**
	 * Obtiene el ck ejercicio.
	 *
	 * @return   ckEjercicio boolean
	 */
	public boolean getCkEjercicio() {
		return ckEjercicio;
	}
	
	/**
	 * Establece el ck ejercicio.
	 *
	 * @param ckEjercicio boolean
	 */
	public void setCkEjercicio(boolean ckEjercicio) {
		this.ckEjercicio = ckEjercicio;
	}
	
	/**
	 * Obtiene el ck estado.
	 *
	 * @return ckEstado boolean
	 */
	public boolean getCkEstado() {
		return ckEstado;
	}
	
	/**
	 * Establece el ck estado.
	 *
	 * @param ckEstado boolean
	 */
	public void setCkEstado(boolean ckEstado) {
		this.ckEstado = ckEstado;
	}
	
	/**
	 * Obtiene el ck forma acceso.
	 *
	 * @return ckFormaAcceso boolean
	 */
	public boolean getCkFormaAcceso() {
		return ckFormaAcceso;
	}
	
	/**
	 * Establece el ck forma acceso.
	 *
	 * @param ckFormaAcceso boolean
	 */
	public void setCkFormaAcceso(boolean ckFormaAcceso) {
		this.ckFormaAcceso = ckFormaAcceso;
	}
	
	/**
	 * Obtiene el ck fecha BOE.
	 *
	 * @return ckFechaBOE boolean
	 */
	public boolean getCkFechaBOE() {
		return ckFechaBOE;
	}
	
	/**
	 * Establece el ck fecha BOE.
	 *
	 * @param ckFechaBOE boolean
	 */
	public void setCkFechaBOE(boolean ckFechaBOE) {
		this.ckFechaBOE = ckFechaBOE;
	}
	
	/**
	 * Obtiene el ck num plazas totales.
	 *
	 * @return ckNumPlazasTotales boolean
	 */
	public boolean getCkNumPlazasTotales() {
		return ckNumPlazasTotales;
	}
	
	/**
	 * Establece el ck num plazas totales.
	 *
	 * @param ckNumPlazasTotales boolean
	 */
	public void setCkNumPlazasTotales(boolean ckNumPlazasTotales) {
		this.ckNumPlazasTotales = ckNumPlazasTotales;
	}

	/**
	 * Obtiene el ck num plazas discap.
	 *
	 * @return ckNumPlazasDiscap boolean
	 */
	public boolean getCkNumPlazasDiscap() {
		return ckNumPlazasDiscap;
	}
	
	/**
	 * Establece el ck num plazas discap.
	 *
	 * @param ckNumPlazasDiscap boolean
	 */
	public void setCkNumPlazasDiscap(boolean ckNumPlazasDiscap) {
		this.ckNumPlazasDiscap = ckNumPlazasDiscap;
	}
	
	/**
	 * Obtiene el ck num inscripcion sin pago.
	 *
	 * @return ckNumInscripcionSinPago boolean
	 */
	public boolean getCkNumInscripcionSinPago() {
		return ckNumInscripcionSinPago;
	}
	
	/**
	 * Establece el ck num inscripcion sin pago.
	 *
	 * @param ckNumInscripcionSinPago boolean
	 */
	public void setCkNumInscripcionSinPago(boolean ckNumInscripcionSinPago) {
		this.ckNumInscripcionSinPago = ckNumInscripcionSinPago;
	}
	
	/**
	 * Obtiene el ck num pagos sin registro.
	 *
	 * @return ckNumPagosSinRegistro boolean
	 */
	public boolean getCkNumPagosSinRegistro() {
		return ckNumPagosSinRegistro;
	}
	
	/**
	 * Establece el ck num pagos sin registro.
	 *
	 * @param ckNumPagosSinRegistro boolean
	 */
	public void setCkNumPagosSinRegistro(boolean ckNumPagosSinRegistro) {
		this.ckNumPagosSinRegistro = ckNumPagosSinRegistro;
	}
	
	/**
	 * Obtiene el ck num descargas manuales.
	 *
	 * @return ckNumDescargasManuales boolean
	 */
	public boolean getCkNumDescargasManuales() {
		return ckNumDescargasManuales;
	}
	
	/**
	 * Establece el ck num descargas manuales.
	 *
	 * @param ckNumDescargasManuales boolean
	 */
	public void setCkNumDescargasManuales(boolean ckNumDescargasManuales) {
		this.ckNumDescargasManuales = ckNumDescargasManuales;
	}
	
	/**
	 * Obtiene el ck num sol telematicas.
	 *
	 * @return ckNumSolTelematicas boolean
	 */
	public boolean getCkNumSolTelematicas() {
		return ckNumSolTelematicas;
	}
	
	/**
	 * Establece el ck num sol telematicas.
	 *
	 * @param ckNumSolTelematicas boolean
	 */
	public void setCkNumSolTelematicas(boolean ckNumSolTelematicas) {
		this.ckNumSolTelematicas = ckNumSolTelematicas;
	}
	
	/**
	 * Obtiene el ck num sol presenciales.
	 *
	 * @return  ckNumSolPresenciales boolean
	 */
	public boolean getCkNumSolPresenciales() {
		return ckNumSolPresenciales;
	}
	
	/**
	 * Establece el ck num sol presenciales.
	 *
	 * @param ckNumSolPresenciales boolean
	 */
	public void setCkNumSolPresenciales(boolean ckNumSolPresenciales) {
		this.ckNumSolPresenciales = ckNumSolPresenciales;
	}
	
	/**
	 * Obtiene el ck num sol inc pago resuelta.
	 *
	 * @return  ckNumSolIncPagoResuelta boolean
	 */
	public boolean getCkNumSolIncPagoResuelta() {
		return ckNumSolIncPagoResuelta;
	}
	
	/**
	 * Establece el ck num sol inc pago resuelta.
	 *
	 * @param ckNumSolIncPagoResuelta boolean
	 */
	public void setCkNumSolIncPagoResuelta(boolean ckNumSolIncPagoResuelta) {
		this.ckNumSolIncPagoResuelta = ckNumSolIncPagoResuelta;
	}
	
	/**
	 * Obtiene el ck num sol inc pago sin resolver.
	 *
	 * @return ckNumSolIncPagoSinResolver boolean
	 */
	public boolean getCkNumSolIncPagoSinResolver() {
		return ckNumSolIncPagoSinResolver;
	}
	
	/**
	 * Establece el ck num sol inc pago sin resolver.
	 *
	 * @param ckNumSolIncPagoSinResolver boolean
	 */
	public void setCkNumSolIncPagoSinResolver(boolean ckNumSolIncPagoSinResolver) {
		this.ckNumSolIncPagoSinResolver = ckNumSolIncPagoSinResolver;
	}
	
	/**
	 * Obtiene el ck num sol inc registro resuelta.
	 *
	 * @return ckNumSolIncRegistroResuelta boolean
	 */
	public boolean getCkNumSolIncRegistroResuelta() {
		return ckNumSolIncRegistroResuelta;
	}
	
	/**
	 * Establece el ck num sol inc registro resuelta.
	 *
	 * @param ckNumSolIncRegistroResuelta boolean
	 */
	public void setCkNumSolIncRegistroResuelta(boolean ckNumSolIncRegistroResuelta) {
		this.ckNumSolIncRegistroResuelta = ckNumSolIncRegistroResuelta;
	}
	
	/**
	 * Obtiene el ck num sol inc registro sin resolver.
	 *
	 * @return ckNumSolIncRegistroSinResolver boolean
	 */
	public boolean getCkNumSolIncRegistroSinResolver() {
		return ckNumSolIncRegistroSinResolver;
	}
	
	/**
	 * Establece el ck num sol inc registro sin resolver.
	 *
	 * @param ckNumSolIncRegistroSinResolver boolean
	 */
	public void setCkNumSolIncRegistroSinResolver(
			boolean ckNumSolIncRegistroSinResolver) {
		this.ckNumSolIncRegistroSinResolver = ckNumSolIncRegistroSinResolver;
	}
	
	/**
	 * Validaciones del formulario de solicitudes.
	 *
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return SpringErrors SpringErrors
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		Date dFechaDesde = null;
		Date dFechaHasta = null;
		
		if("BUSCAR".equals(accion))
		{
			if(fechaDesde != null && !fechaDesde.equalsIgnoreCase("") && !validacion.isFechaValida(fechaDesde))
			{

				request.setAttribute("errorFechaDesde", "errorFechaDesde");
				SpringErrors.add("dsErrorFechaDesde", new SpringMessage("field.solicitud.errores.fechaDesdeNoValido"));
			}	
			if(fechaHasta != null && !fechaHasta.equalsIgnoreCase("") && !validacion.isFechaValida(fechaHasta))
			{

				request.setAttribute("errorFechaHasta", "errorFechaHasta");
				SpringErrors.add("dsErrorFechaHasta", new SpringMessage("field.solicitud.errores.fechaHastaNoValido"));
			}
			
			if(fechaDesde != null && fechaHasta!= null && validacion.isFechaValida(fechaDesde) && validacion.isFechaValida(fechaHasta))
			{
				dFechaDesde = utilesIPSG.stringToDate(fechaDesde);
				dFechaHasta = utilesIPSG.stringToDate(fechaHasta);
				if (dFechaDesde !=null && dFechaHasta !=null && dFechaDesde.after(dFechaHasta))
				{
					
						request.setAttribute("errorFechaMayorDesde", "errorFechaMayorDesde");
						SpringErrors.add("dsErrorFechaMayorDesde", new SpringMessage(
								"field.solicitudRegistrada.errores.fechaMayorDesde"));
					
				}
				
			}
		}
		return SpringErrors;
	}
	
	/**
	 * Obtiene el ds centro gestor.
	 *
	 * @return el ds centro gestor
	 */
	public String getDsCentroGestor() {
		return dsCentroGestor;
	}
	
	/**
	 * Establece el ds centro gestor.
	 *
	 * @param dsCentroGestor el nuevo ds centro gestor
	 */
	public void setDsCentroGestor(String dsCentroGestor) {
		this.dsCentroGestor = dsCentroGestor;
	}
	
	/**
	 * Comprueba si es ck num pagos sin intento registro.
	 *
	 * @return verdadero, si es ck num pagos sin intento registro
	 */
	public boolean isCkNumPagosSinIntentoRegistro() {
		return ckNumPagosSinIntentoRegistro;
	}
	
	/**
	 * Establece el ck num pagos sin intento registro.
	 *
	 * @param ckNumPagosSinIntentoRegistro el nuevo ck num pagos sin intento registro
	 */
	public void setCkNumPagosSinIntentoRegistro(boolean ckNumPagosSinIntentoRegistro) {
		this.ckNumPagosSinIntentoRegistro = ckNumPagosSinIntentoRegistro;
	}
	
	/**
	 * Comprueba si es ck num sol sin intento pago.
	 *
	 * @return verdadero, si es ck num sol sin intento pago
	 */
	public boolean isCkNumSolSinIntentoPago() {
		return ckNumSolSinIntentoPago;
	}
	
	/**
	 * Establece el ck num sol sin intento pago.
	 *
	 * @param ckNumSolSinIntentoPago el nuevo ck num sol sin intento pago
	 */
	public void setCkNumSolSinIntentoPago(boolean ckNumSolSinIntentoPago) {
		this.ckNumSolSinIntentoPago = ckNumSolSinIntentoPago;
	}
	
	/**
	 * Comprueba si es ck num descargas manuales total.
	 *
	 * @return verdadero, si es ck num descargas manuales total
	 */
	public boolean isCkNumDescargasManualesTotal() {
		return ckNumDescargasManualesTotal;
	}
	
	/**
	 * Establece el ck num descargas manuales total.
	 *
	 * @param ckNumDescargasManualesTotal el nuevo ck num descargas manuales total
	 */
	public void setCkNumDescargasManualesTotal(boolean ckNumDescargasManualesTotal) {
		this.ckNumDescargasManualesTotal = ckNumDescargasManualesTotal;
	}
	
	/*INI-TRA042*/
	/**
	 * @return the listaCentrosGestores
	 */
	public List<CentroGestorBean> getListaCentrosGestores() {
		return listaCentrosGestores;
	}

	/**
	 * @param listaCentrosGestores the listaCentrosGestores to set
	 */
	public void setListaCentrosGestores(List<CentroGestorBean> listaCentrosGestores) {
		this.listaCentrosGestores = listaCentrosGestores;
	}
	/*FIN-TRA042*/
	
}
