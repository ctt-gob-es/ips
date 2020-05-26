<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*" %>
<%@ page import="es.map.ipsc.Constantes" %>
<%@ page import="es.map.ipsc.bean.*" %>
<%@ page import="es.map.ipsc.action.*" %>
<%@ page import="es.map.ips.common.spring.ApplicationContextProvider"%>

<%--
	CiudadanoBean usuActual = (CiudadanoBean) request.getSession().getAttribute("usuario");
	String numSerie = "";
	
	// Recuperamos el serial number del certificado del ciudadano
	if(null!=usuActual && usuActual.getNumeroSerie()!=null){
		numSerie = usuActual.getNumeroSerie();
	}
--%>
 
<%
	Properties properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
	String tamanioFichero = properties.getProperty("conf.tamanioFichero");
	String tamanioTotalDocumento = properties.getProperty("conf.tamanioTotalDocumento");
	String totalDocumentosJS = properties.getProperty("conf.numDocsAdjuntos");
	String motivoTipificado= (String)request.getAttribute("motivoTipificado");
	String op= (String)request.getAttribute("formPago");
	Float importe= (Float)request.getAttribute("importeOriginal");
	String  siglasCentroGestor = (String)request.getAttribute("siglasCentroGestor");  
	String  siglasCentroGestorJusticia = (String)request.getAttribute("siglasCentroGestorJusticia");  
	String  detalleRegistro = (String)request.getAttribute("detalleRegistro");  
	String  registrarSolicitud = (String)request.getAttribute("registrarSolicitud");  
	String  detalleRegistrado = (String)request.getAttribute("detalleRegistrado");  
	String tipoPersona = (String) request.getAttribute("tipoPersonaSolicitudFormGeneral");
	String continuar = (String) request.getAttribute("continuar");
	String modificar = (String) request.getAttribute("modificar");
	Boolean subsanarInscripcion = (Boolean) request.getAttribute("subsanarInscripcion");
%>

<script type="text/javascript">

	//var autoFirma = inicio();
	var variable = "1905";
	var descErrorPago="0";
	var errorCP=0;
	
	var proceso = 0;
	var errorProceso = 0;
	
	$(document).ready(function(){
		if (!Array.prototype.indexOf){
		  Array.prototype.indexOf = function(elt){
		    var len = this.length >>> 0;
		    var from = Number(arguments[1]) || 0;
		    from = (from < 0)
		         ? Math.ceil(from)
		         : Math.floor(from);
		    if (from < 0)
		      from += len;
		    for (; from < len; from++){
		      if (from in this &&
		          this[from] === elt)
		        return from;
		    }
		    return -1;
		  };
		}
	 });
	
		
	/*function inicio(){
		return isEdge() || !isJavaEnabled(); 
	}*/
		
	function volver(){
		window.location.href = "<%=request.getContextPath()%>/secure/buscarSolicitudes";	
	}
	
	function ventanaInformacion(op){
		ventanaPopup = window.open("<%=request.getContextPath()%>/secure/informacionAdicional?llamada="+op, 'miventana', 'width=650, height=280,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=no,resizable=yes ,modal=yes');
		return false;
	}
	
	function inicializar(){
		if(confirm("<spring:message code='field.registro.documentos.avisoFirma'/>")){ 
			return true;
		}else{
			return false;
		}		
	}
	
	function busqueda(){
		
		if(document.getElementById("conforme").checked==true){
			if(document.getElementById("conforme-db-advisor").className == "pae-form__advisor-tooltip") {
				document.getElementById("conforme-db-advisor").className = "pae-form__advisor-tooltip pae-hidden";
			}
			if(document.getElementById("ck-accept-access-db").checked) {
				$("#idConsentimiento").prop("value",false);
				if(document.getElementById("access-db-advisor").className == "pae-form__advisor-tooltip pae-hidden") {
					document.getElementById("access-db-advisor").className = "pae-form__advisor-tooltip";
				} else {
				
					validarDatosSolicitud();
				}
			} else {
				$("#idConsentimiento").prop("value",true);
				if(document.getElementById("access-db-advisor").className == "pae-form__advisor-tooltip") {
					document.getElementById("access-db-advisor").className = "pae-form__advisor-tooltip pae-hidden";
				}
				  validarDatosSolicitud();
			}
		}
		else{
			if(document.getElementById("conforme-db-advisor").className == "pae-form__advisor-tooltip pae-hidden") {
				document.getElementById("conforme-db-advisor").className = "pae-form__advisor-tooltip";
			}
		}

		if($("#mOposicion") != undefined && $("#motivoOposicion").val() != undefined){
			$("#mOposicion").val($("#motivoOposicion").val());
		} else if($("#mOposicion") != undefined && $("#motivoOposicion2").val() != undefined){
			$("#mOposicion").val($("#motivoOposicion2").val());
			}
		
	}
	function busquedaSinValidar() {
		if(document.getElementById("conforme").checked==true){
			if(document.getElementById("conforme-db-advisor").className == "pae-form__advisor-tooltip") {
				document.getElementById("conforme-db-advisor").className = "pae-form__advisor-tooltip pae-hidden";
			}
			return true;
		}else {
			if(document.getElementById("conforme-db-advisor").className == "pae-form__advisor-tooltip pae-hidden") {
				document.getElementById("conforme-db-advisor").className = "pae-form__advisor-tooltip";
			}
			return false;
		}
	}
		
	function validarDatosSolicitud(){
		var error=0;
		var validaSolicitante = validarDatosSolicitante(error);
		var validaConvocatoria = validarDatosConvocatoria(error);
		var validaDiscapacidad = validarDatosDiscapacidad(error);
		var validaInformacion = validarDatosInformacionAdicional(error);
		var validaExencion = validarDatosExencion(error);
		<% if (!tipoPersona.equals("FH")) { %>
			// Comprobamos si tiene que validar los datos del pago
			// Se validan solo si se han mostrado en la solicitud
			if($('#importeAPagar').is(':visible') || $('#formasDePago').is(':visible') || (($("#formasDePago").attr("class") == null ||$("#formasDePago").attr("class") == "") && ($("#importeAPagar").attr("style") == null || $("#importeAPagar").attr("style") == "display: block;"))){
				var validaPago = validarDatosPago(error);				
			}else{
				var validaPago = true;
				$("#pagoInicialModif").val("N");
				$("#pagoInicialModifPago").val("N");
			}
		<% } else { %>
			if(document.getElementById("importe").value != 0.00 && (document.getElementById("nrcCiudadano").value == null  || document.getElementById("nrcCiudadano").value=="")){
				error=1;
				document.getElementById("nrcCiudadano").className += " pae-form__input--error";
				if($("#setFocus").val() == "")
					$("#setFocus").val('nrcCiudadano');
			} else {
				var validaPago = true;
				$('input[type=text]').prop("disabled",false);
	 	   	 	$('input[type=checkbox]').prop("disabled",false);
		   	 	$('input[type=radio]').prop("disabled",false);
		   	 	$('select').prop("disabled",false);
			}
		<% } %>
			
		
		//Comprobamos si hay algun error
		if(!validaSolicitante || !validaConvocatoria || !validaDiscapacidad || !validaInformacion || !validaPago || !validaExencion) {
			error = 1;
		}
		
		//Si hay un error buscamos donde poner el foco
		if(error == 1) {
			document.getElementById("rellenarCampos").className = "pae-alertbox pae-alertbox--warning";
// 			document.getElementById($("#setFocus").val()).focus();
			$("#setFocus").val("");
			return false;
		}else {
			document.getElementById("rellenarCampos").className = "pae-alertbox pae-alertbox--warning pae-hidden";
		}
		
			if($("#modal").length > 0) {
				if (popupInfo())
				return false;
			} else {
				completar(error);
			}
		

	}

	function calcularImporte2() {
		// Se obtienen todos los checkboxes
		var iteracion = document.getElementsByName("checkbox-exemption");
		
		var porcentajeMin = document.getElementById("porcentajeMinDiscapacidad").value;
		var porcentaje = document.getElementById("porcentajeDiscapacidad").value;
		
		// Si es un número
		if(!isNaN(porcentaje) && porcentaje!=""){
			var numPorcentajeMin = parseInt(porcentajeMin,10);
			var numPorcentaje = parseInt(porcentaje,10);
			
			if(numPorcentaje > 0 && numPorcentaje <= 100){
				if(numPorcentaje >= numPorcentajeMin){//entre 33% y 100%
					// Se establece "CCAA en la que se reconoce su discapacidad" obligatorio
					$("#oblComunidadDDExento").removeAttr("style");

					document.getElementById("importeHTML").innerText = "0";	
					document.getElementById("importe").value = 0;					 
			        document.getElementById("formasDePago").className = "pae-hidden";
			        document.getElementById("formPago").value = "E";		                
						
			        // Se activan todos los campos
	                document.getElementById("idComunidadDDExento").disabled = false;						
					document.getElementById('detalleDiscapacidad').disabled = false;
					$("[for='state'] span").removeClass("pae-form__label--disabled").addClass("pae-form__label--text");						
					//document.getElementById("checkbox-exemption-"+aux).disabled = true;		
				}else{//es menor de 33%
					// Se desactiva el campo comunidad
					$("#oblComunidadDDExento").attr("style","display:none;");
					document.getElementById("reservation-no").checked = true;
					document.getElementById("idComunidadDDExento").value = 0;
					document.getElementById("idComunidadDDExento").disabled = true;

					$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
					document.getElementById("detalleDiscapacidad").disabled = false;
					
				}
			}else{
				//alert('No se puede introducir un valor igual o inferior al 0 % ni superior al 100 %');
				alert("<spring:message code='field.altaSolicitud.gradoDiscapacidadError'/>");
				document.getElementById("porcentajeDiscapacidad").value = "";
				document.getElementById("idComunidadDDExento").value = 0;
				document.getElementById("idComunidadDDExento").disabled = true;
				$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
				document.getElementById("detalleDiscapacidad").disabled = true;
				for(var i=1;i<=iteracion.length;i++) {
					if(document.getElementById("checkbox-exemption-"+i).value == "1") {
						document.getElementById("checkbox-exemption-"+i).checked = false;
					}
				}
				//actualizarImporte("1");
			}
		}
		// Si no es un número o está vacío 
		else {
			
			$("#oblComunidadDDExento").attr("style","display:none;");
			document.getElementById("idComunidadDDExento").value = "0";
			document.getElementById("detalleDiscapacidad").value = "";
			document.getElementById("reservation-no").checked = true;
			
			if (porcentaje != "") {
				// Se desmarca el checkbox correspondiente
				for(var i=1;i<=iteracion.length;i++) {
					if(document.getElementById("checkbox-exemption-"+i).value == 1) {
						document.getElementById("checkbox-exemption-"+i).checked = false;
					}
				}
			}
			
			document.getElementById("idComunidadDDExento").disabled = true;
			$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
			document.getElementById("detalleDiscapacidad").disabled = true;
			document.getElementById("importe_2").value = document.getElementById("importeOriginal").value;
			document.getElementById("importe").value = document.getElementById("importeOriginal").value;
			actualizarImporte("1");
		}
	}
	
	function calcularImporte(){
		
		var iteracion = document.getElementsByName("checkbox-exemption");
		var porcentajeMin = document.getElementById("porcentajeMinDiscapacidad").value;
		var porcentaje = document.getElementById("porcentajeDiscapacidad").value;
		
		if(!isNaN(porcentaje) && porcentaje!=""){
			var numPorcentajeMin = parseInt(porcentajeMin,10);
			var numPorcentaje = parseInt(porcentaje,10);
			
			if(numPorcentaje <= 100){
				if(numPorcentaje >= numPorcentajeMin){//entre 33% y 100%
					var aux = 0;
						for(var i=1;i<=iteracion.length;i++) {
							//Marcamos la casilla de discapacidad
							if(document.getElementById("checkbox-exemption-"+i).value == 1) {
								document.getElementById("checkbox-exemption-"+i).checked = true;
								aux=i;
							}
							// Se establece "CCAA en la que se reconoce su discapacidad" obligatorio
							$("#oblComunidadDDExento").removeAttr("style");
						}

						actualizarImporte("1");//1 = DISCAPACIDAD
						document.getElementById("importeHTML").innerText = "0";	
						document.getElementById("importe").value = 0;					 
			            document.getElementById("formasDePago").className = "pae-hidden";
			            document.getElementById("formPago").value = "E";		                
						
	                    document.getElementById("idComunidadDDExento").disabled = false;						
						document.getElementById('detalleDiscapacidad').disabled = false;
						$("[for='state'] span").removeClass("pae-form__label--disabled").addClass("pae-form__label--text");						
						document.getElementById("checkbox-exemption-"+aux).disabled = true;
						
						
				}else{//es menor de 33%
					$("#oblComunidadDDExento").attr("style","display:none;");
					document.getElementById("reservation-no").checked = true;
					document.getElementById("idComunidadDDExento").value = 0;
					document.getElementById("idComunidadDDExento").disabled = true;
					if(numPorcentaje>0){//entre 1 y 33%
						for(var i=1;i<=iteracion.length;i++) {
							if(document.getElementById("checkbox-exemption-"+i).value == "1") {//DISCAPACIDAD
								document.getElementById("checkbox-exemption-"+i).checked = false;//Deshabilitamos el resto
							}
						}
						actualizarImporte("1");
						$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
						document.getElementById("detalleDiscapacidad").disabled = false;
						
					}else{//0 o negativo
						document.getElementById("porcentajeDiscapacidad").value="";
						
						$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
						document.getElementById("detalleDiscapacidad").disabled = true;
						document.getElementById("detalleDiscapacidad").value="";
						for(var i=1;i<=iteracion.length;i++) {
							if(document.getElementById("checkbox-exemption-"+i).value == "1") {
								document.getElementById("checkbox-exemption-"+i).checked = false;
							}
						}
						actualizarImporte("1");
					}
				}
			}else{
				alert("<spring:message code='field.altaSolicitud.calcularImporteError'/>");
				document.getElementById("porcentajeDiscapacidad").value = "";
				document.getElementById("idComunidadDDExento").disabled = true;
				$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
				document.getElementById("detalleDiscapacidad").disabled = true;
				for(var i=1;i<=iteracion.length;i++) {
					if(document.getElementById("checkbox-exemption-"+i).value == "1") {
						document.getElementById("checkbox-exemption-"+i).checked = false;
					}
				}
				actualizarImporte("1");
			}
		}else{//No se ha metido un numero correcto
			$("#oblComunidadDDExento").attr("style","display:none;");
			document.getElementById("idComunidadDDExento").value = "0";
			document.getElementById("detalleDiscapacidad").value = "";
			document.getElementById("reservation-no").checked = true;
			for(var i=1;i<=iteracion.length;i++) {
							if(document.getElementById("checkbox-exemption-"+i).value == 1) {
								document.getElementById("checkbox-exemption-"+i).checked = false;
							}
						}
			document.getElementById("idComunidadDDExento").disabled = true;
			$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
			document.getElementById("detalleDiscapacidad").disabled = true;
			document.getElementById("importe_2").value = document.getElementById("importeOriginal").value;
			document.getElementById("importe").value = document.getElementById("importeOriginal").value;
			actualizarImporte("1");
		}
	}
	
	function validarCodigoPostal(ctrl){
	
		var codigo = new String(ctrl.value);
		if (codigo.length!=5 && codigo.length!=0){
	    	return false;
		}
	   return true;
			
	}
	
	function validarTelefono(ctrl){
		
		var telefono = new String(ctrl.value);
		if (telefono.length!=9 && telefono.length!=0){
	    	return false;
		}
	   return true;
			
	}
	
	function validaFechaFormato(objeto){
		var fecha = objeto.value; 
		if(fecha!=null){
			if(fecha.length==10){
				if(!/^\d{2}\/\d{2}\/\d{4}$/.test(fecha)){
					alert("<spring:message code='field.solicitud.alta.validate.fechaNacimiento'/>");
					$("#fechaNacimiento").val("");
				}else{
					if(!validarFechaMinima(fecha)){
						alert("<spring:message code='field.altaSolicitud.fechaNoValida'/>");
						$("#fechaNacimiento").val("");
					}else{
						return true;
					}				
				}
			}else{
				alert("<spring:message code='field.solicitud.alta.validate.fechaNacimiento'/>");
				$("#fechaNacimiento").val("");
			}
		}
		return false;
	}
	
	function validarFechaMinima(entrada){
		var fecha = new Date();
		var anyoActual = fecha.getFullYear();
		var anyoMinimo = anyoActual - 70;
		fecha.setFullYear(parseInt(anyoMinimo));
		var fechaEntrada = new Date();
		fechaEntrada.setDate(parseInt(entrada.substring(0,2),10));
		fechaEntrada.setMonth(parseInt(entrada.substring(3,5),10)-1);
		fechaEntrada.setFullYear(parseInt(entrada.substring(6,10),10));
		
		if(fechaEntrada < fecha){
			return false;
		}
		return true;	
	}
	
	function seleccionarEspaña(){
		if(document.getElementById("pais"))
			document.getElementById("pais").value = "1";
		
		var mesTarjeta=document.getElementsByClassName("selectize-input items not-full has-options")[0];
		mesTarjeta.id="mesTarjeta";

		var anoTarjeta=document.getElementsByClassName("selectize-input items not-full has-options")[1];
		anoTarjeta.id="anoTarjeta"; 
	}
	
	function cambiarMayusculas(ctrl){
		ctrl.value=ctrl.value.toUpperCase();
	}
	
	function soloNumeros(evento){
	    evento = (evento) ? evento : window.event;
	    var charCode = (evento.which) ? evento.which : evento.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	       return false;
	    }
	    return true;
	}
	
	function validarCP(provincia, cp){
	
		 $.ajax({
		        url: "<%=request.getContextPath()%>/secure/validarCP",
		    	type: 'POST',
		        data:  "provincia="+provincia+"&CP="+cp,
			    contentType: false,
			    cache: false,
			    processData:false,
			    complete: function(data, textStatus, jqXHR){
			    	var str=data.responseText;
			    	var resto = 16;
			    	var start=str.indexOf("<div id=\"error\">");
			    	
			    	if(start == -1){
			    		start=str.indexOf("<DIV id=error>");
			    		if(start != -1)
						 	resto = 14;
					}
	
					var strFin=str.substr(start);
					var fin=strFin.indexOf("</div>");
					
					if(fin==-1)
					    fin=strFin.indexOf("</DIV>");		
				    
					var errores = data.responseText.substr(start+resto, fin-resto); 
					
					if(errores!=""){
						errorCP=1;
					}else{
						errorCP=0;
					}
	
					error = validaciones2(errorCP);
					return error;
			    }   
		   }); 
		return 1;
	}
	
	function formPagoValue() {
		document.getElementById("boxPago").className = "pae-box";
		if(document.getElementById("credict-card").checked) {
			//Pago con tarjeta
			document.getElementById("formPago").value = "T";
			document.getElementById("divTarjeta").className = "";
			document.getElementById("divAdeudo").className = "pae-hidden";
			document.getElementById("IBANInput").value = "ES";
			document.getElementById("IBANInput").className = "pae-form__input";
			document.getElementById("entidadAdeudo").className = "pae-form__input large";
			document.getElementById("entidadAdeudo").value = "0";
			$("#infoEntidad1").removeClass("hidden");
			$("#infoEntidad2").addClass("hidden");
		}
		else {
			//Pago por cuenta bancaria
			document.getElementById("formPago").value = "C";
			document.getElementById("divTarjeta").className = "pae-hidden";
			document.getElementById("divAdeudo").className = "";
			document.getElementById("numeroTarjetaHTML").value = "";
			document.getElementById("entidadTarjeta").value = "0";
			document.getElementById("caducidadTarjeta1").value = null;
			document.getElementById("caducidadTarjeta2").value = "";
			$("#infoEntidad1").addClass("hidden");
			$("#infoEntidad2").removeClass("hidden");
		};
	}
	
	$(document).ready(function() {
	    document.getElementById("importeHTML").textContent = parseFloat((document.getElementById("importeOriginal").value - document.getElementById("importePagado").value)).toFixed(2);
		formPagoValue();
		cambioMetodoPago();
		eleccionEntidad();
	});
	
	function cambioMetodoPago() {
		var a;
		if(document.getElementById("bank-account").checked) {
			a = $("#bank-account");
		} else {
			a = $("#credict-card");
		}
		
	    var b = $("#" + $(a).attr("data-object"));
	    "bank-account" === $(a).attr("id") ? (b.removeClass("pae-hidden"),
	    $("[data-opcion='tarjeta']").addClass("pae-hidden"),
	    $("[data-opcion='cuenta']").removeClass("pae-hidden")) : (b.hasClass("pae-hidden") || b.addClass("pae-hidden"),
	    $("[data-opcion='cuenta']").addClass("pae-hidden"),
	    $("[data-opcion='tarjeta']").removeClass("pae-hidden"))
	}

</script>




<form action="<%=request.getContextPath()%>/secure/verSolicitudUnificado?llamada=Buscar" method=post id="formulario" class="pae-form"> 
	<input type="hidden" id="certificado" name="certificado" value="certificado"> 
	<input type="hidden" id="id" name="id" value="id"> 
</form>
	
	<input type="hidden" id="add-modal" data-function="add-modal-paso1">
	<input type="hidden" id="setFocus" value=""/>
	
	<div class="pae-wrapper">
    <div class="pae-head-return">
      <div class="pae-layout">
		  <% if(subsanarInscripcion != null && subsanarInscripcion  ){ %>
		    	<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm"><a href="buscarConvocatoriasSubsanar#convocatoriasSubRef" title="Volver al listado de convocatoriasa subsanar" class="pae-head-return__link fa fa-chevron-left">Volver al listado de convocatorias a subsanar</a></div>
		 <% } else { %>
        <div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm"><a href="buscarConvocatorias?form=L" title="Volver al listado de convocatorias" class="pae-head-return__link fa fa-chevron-left">Volver al listado de convocatorias</a></div>
		 <% } %>  
      </div>
    </div>
  </div>
  <div class="pae-body pae-body--without-border pae-body--without-padding-mobile">
    <div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
        
        <%String click="";
			if(modificar != null && modificar.equals("true")  ){
				click="busqueda()";
			%>
			<%@ include file="/jsp/modificarSolicitudGeneral.jsp" %>
		<%} else if (detalleRegistro==null || (tipoPersona.equals("FH") && !continuar.equals("true"))){
			click="busqueda()";
			%>
		
			<%@ include file="/jsp/altaSolicitudGeneral.jsp" %>
			
		<%} else{
				click="firmaryPagar()";
		%>
		<%@ include file="/jsp/detalleSolicitudesGeneral.jsp" %>
		<%}%>	
	
		<%
			if(modificar != null && modificar.equals("true")  ){
		%>
		
			<%@ include file="/jsp/modificarPagoSolicitudGeneral.jsp" %>
		<%}else if(registrarSolicitud==null || (tipoPersona.equals("FH") && !continuar.equals("true"))){
		%>	
		
			<%@ include file="/jsp/pagoSolicitudGeneral.jsp" %>
		<%} else{
				click="firmaryRegistrar()";
		%>
			<%@ include file="/jsp/detallePagoSolicitudesGeneral.jsp" %>
			<%@ include file="/jsp/detalleDatosDiscapacidad.jsp" %>
		<%}%>

		<%	if(detalleRegistrado==null || (tipoPersona.equals("FH") && !continuar.equals("true"))){
 		%>
 		
 			<%@ include file="/jsp/registroSolicitudGeneral.jsp" %> 
 		
 		<% } else{
				click=null;
		%>
 			<%@ include file="/jsp/detalleRegistroSolicitudesGeneral.jsp" %> 
 		<%}	%>
            
        </div>
      </div>
    </div>
  </div>
  
  <script type="text/javascript">

  	var pagoCompletadoAux = false;
	var completeSolicitud=false;
	var erroresPago=false;
	var erroresSolicitud=false;
	
	function firmaryPagar(){
		if(busquedaSinValidar()) {
		
			<% if(!tipoPersona.equals("FH")) { %>
			var error = 0;
		if(!validarDatosPago(error))
			return false;

		   document.getElementById("estaEnPago").value = true;	
		   proceso = 1;
		
		if($("#modal").length > 0) {
			if (popupInfo())
			return false;
		} else {
			completarPagar();
		}	
			
			<%  } else { %>

				if(document.getElementById("nrcCiudadano").value == null  || document.getElementById("nrcCiudadano").value==""){
					document.getElementById("nrcCiudadano").className += " pae-form__input--error";
					if($("#setFocus").val() == "") {
						$("#setFocus").val('nrcCiudadano');
					}
					return false;
				} else {
					document.getElementById("estaEnPago").value = true;	
					if(!firmar())
					{
						/* if(erroresPago==0){
							//activarBotonContinuar();
						} */ 
					}
					proceso = 1;
					document.getElementById("add-modal").click();
				}
				
			<%  }  %>

		}else {
			return false;
		}
	}
	
	function firmaryRegistrar(){
		
		if(busquedaSinValidar()) {	
			if($("#modal").length > 0) {
				if (popupInfo())
				return false;
			} else {
				completarRegistrar();
			}
		}else {
			return false;
		}
	}
	
	function getDoc(frame) {
	    var doc = null;
	    // IE8 cascading access check
	    try {
	        if (frame.contentWindow) {
	            doc = frame.contentWindow.document;
	        }
	    } catch (err) {}
	    if (doc) { // successful getting content
	        return doc;
	    }
	    try { // simply checking may throw in ie8 under ssl or mismatched protocol
	        doc = frame.contentDocument ? frame.contentDocument : frame.document;
	    } catch (err) {
	        // last attempt
	        doc = frame.document;
	    }
	    return doc;
	}
	
		$("#formSolicitud").submit(function(e){
			
		    var formObj = $(this);
			var formURL = formObj.attr("action");
		    
			if(typeof(FormData) == 'undefined'){
			// CODIGO AJAX SOPORTADO POR INTERNET EXPLORER
	
		      if(completeSolicitud==false){
				  //generate a random id
			        var iframeId = 'unique' + (new Date().getTime());
			        //create an empty iframe
			        var iframe = $('<iframe src="javascript:false;" name="' + iframeId + '" id="iframeSolicitud" />');
			        //hide it
			        iframe.hide();
			        //set form target to iframe
			        formObj.attr('target', iframeId);
			        //Add iframe to body
			        iframe.appendTo('body');
			        antesFormSolicitud();
			        iframe.load(function (e) {
			            var doc = getDoc(iframe[0]);
			            var docRoot = doc.body ? doc.body : doc.documentElement;
			            var data = docRoot.innerHTML;
			            despuesFormSolicitud(data);		
			            //data is returned from server.
			        });
		      }
			  	
			}else{
			  var formData = new FormData(this);
			  formData.append("ajax", "1");
		      
			  if(completeSolicitud==false){
			    $.ajax({
			        url: formURL,
			   		type: 'POST',
			        data:  formData,
			        contentType: false,
			        cache: false,
			        processData:false,
				    beforeSend: function(data, textStatus, jqXHR)
				    {
				    	antesFormSolicitud();
				    },
				    complete: function(data, textStatus, jqXHR)
				    {
				    	despuesFormSolicitud(data.responseText);		    	
			 			
				    },error: function(jqXHR, textStatus, errorThrown) 
				    {
				    	erroresSolicitud=true;
				    }       
			    });
			    e.preventDefault(); //Prevent Default action. 
		      }
			}	
		}); 
	
		$("#formularioPago").submit(function(i){
		    var formObj = $(this);
		   	var formURL = formObj.attr("action"); 
		    
		    if(typeof(FormData) == 'undefined'){
				// CODIGO AJAX SOPORTADO POR INTERNET EXPLORER
			 
				  //generate a random id
			        var iframeId = 'unique' + (new Date().getTime());
			        //create an empty iframe
			        var iframe = $('<iframe src="javascript:false;" name="' + iframeId + '" id="iframePago" />');
			        //hide it
			        iframe.hide();
			        //set form target to iframe
			        formObj.attr('target', iframeId);
			        //Add iframe to body
			        iframe.appendTo('body');
			        inicioFormularioPago();
			        
			        iframe.load(function (e) {
			            try{
				            var doc = getDoc(iframe[0]);
				            var docRoot = doc.body ? doc.body : doc.documentElement;
				            var data = docRoot.innerHTML;
				            despuesFormularioPago(data);
				            //data is returned from server.						
					    }catch(error){
							erroresPago=true;
							errorProceso = 2; //ERROR EN EL PROCESO DE PAGO
							pagoCompletadoAux = true;
					    }				            
			        });  
				  	
				}else{	    
				    var formData = new FormData(this);
				    $.ajax({
				        url: formURL,
				    	type: 'POST',
				        data:  formData,
					    mimeType:"multipart/form-data",
					    contentType: false,
					    cache: false,
					    processData:false,
					    beforeSend: function(data, textStatus, jqXHR){
					    	inicioFormularioPago();
					    },
					    complete: function(data, textStatus, jqXHR){
					    	despuesFormularioPago(data.responseText);
			 				
					    },error: function(jqXHR, textStatus, errorThrown){
							erroresPago=true;
							errorProceso = 2; //ERROR DE PAGO
							pagoCompletadoAux = true;
					    }       
				    }); 
				    i.preventDefault(); //Prevent Default action. 
			}		
		}); 
	
		
		$("#formReg").submit(function(o){
			    var formObj = $(this);
			    var formURL = formObj.attr("action");
			    
			    if(typeof(FormData) == 'undefined' || navigator.appVersion.toUpperCase().indexOf("MSIE")!=-1){
					// CODIGO AJAX SOPORTADO POR INTERNET EXPLORER
	
					  //generate a random id
				        var iframeId = 'unique' + (new Date().getTime());
				        //create an empty iframe
				        var iframe = $('<iframe src="javascript:false;" name="' + iframeId + '" />');
				        //hide it
				        iframe.hide();
				        //set form target to iframe
				        formObj.attr('target', iframeId);
				        //Add iframe to body
				        iframe.appendTo('body');
				        inicioFormularioRegistro();
				        iframe.load(function (e) {
							    try{
						            var doc = getDoc(iframe[0]);
						            var docRoot = doc.body ? doc.body : doc.documentElement;
						            var data = docRoot.innerHTML;
						            finFormularioRegistro(data);
						            //data is returned from server.
							    }catch (error){
							    	errorProceso = 3; //ERROR EN EL REGISTRO
								}
				        });
					  	
					}else{	 
					    var formData = new FormData(this);
						formData.append("ajax", "1");
					    $.ajax({
					        url: formURL,
					    	type: 'POST',
					        data:  formData,
						    mimeType:"multipart/form-data",
						    contentType: false,
						    cache: false,
						    processData:false,
						    beforeSend: function(data, textStatus, jqXHR){
								inicioFormularioRegistro(data);
						    },
						    complete: function(data, textStatus, jqXHR){
						    	finFormularioRegistro(data.responseText);
						    	
						    },error: function(jqXHR, textStatus, errorThrown){
						    	errorProceso = 3; //ERROR EN EL REGISTRO
						    }       
					   }); 
					 	o.preventDefault(); //Prevent Default action. 
					}
		}); 
	
		//INICIO FUNCIONES ACCIONES DE AJAX CON SERVIDOR
		
		function antesFormSolicitud(){
			proceso = 1; //Comienzo de recogida de datos
			document.getElementById("add-modal").click();
		}
		
		function despuesFormSolicitud(data){
					
			var index=0;
			var resto = 16;
			var appVer = navigator.appVersion;
			var nav = appVer.toUpperCase().indexOf("MSIE 8.0");
			var nav2 = appVer.toUpperCase().indexOf("MSIE 7.0");
			
			if(nav!= -1 || nav2 != -1)
			    resto = 14;
			
			if(data){
			    if(data.indexOf("#REANUDARSOLICITUDGENERAL#")!=0){ 
		    		index=data.indexOf("<div id=\"error\">");
	
					if(index==-1 && (nav!=-1 || nav!=-2)){
						var indexIE=data.indexOf("INICIOERRORESSOLICITUD");
						var strFinIE=data.substr(indexIE);
						var finIE=strFinIE.indexOf("FINERRORESSOLICITUD");
						var erroresInscripcionIE = data.substr(indexIE+31, finIE-59);
	
						index = 0;
						data = erroresInscripcionIE;
					}
					
			    }
			}
			if (index==-1 || index>50000 || data == ""){
				//cambiarEstado(2, 0);
	
	 			completeSolicitud = true;
	    		document.getElementById("estaEnPago").value = true;
	
				document.getElementById("botonFirma").onclick= firmar();
			}else{
				errorProceso = 1; //ERROR GUARDANDO LOS DATOS
	
				var strFin=data.substr(index);
				var fin=strFin.indexOf("</div>");
				
				if(fin==-1){
					 fin=strFin.indexOf("</DIV>");
		     	}
				var erroresInscripcion = data.substr(index+resto, fin-4);
				//escribirErroresServer(0, erroresInscripcion);
				$('#errorSolicitudDescripcion').html(erroresInscripcion);
				$("#botonProceso7").show();
		    	erroresSolicitud=true;
			};
		}
	
		function inicioFormularioPago(){
			setTimeout(function(){	
			var exento = document.getElementById("formPago").value;
			
			if(exento != "E"){
				if(erroresSolicitud==false){
					proceso = 2;//Comienzo proceso de pago
					progress2();
				};
			};
	    							
			}, 2000);
			erroresPago=false;
		}
	
		function despuesFormularioPago(data){
			pagoCompletadoAux = true;
			var str=data;
			var resto = 60;
			var exento = document.getElementById("formPago").value;
			
			if(data){
				if(str.length>5){
					var start=str.indexOf("<div id=\"error\" class=\"pae-alertbox pae-alertbox--warning\">");
					if(start == -1){
						start=str.indexOf("<DIV id=error>");
						if(start != -1)
						 	resto = 58;
					}
					var strFin=str.substr(start);
					
					var fin=strFin.indexOf("</div>");
					if(fin==-1)
					    fin=strFin.indexOf("</DIV>");		
				    
					var errores = data.substr(start+resto, fin);
					if(errores.length>5){
						descErrorPago="0";
						erroresPago=true;
						erroresDescripcion=jQuery('<p>' + errores + '</p>').text(); //Eliminar los tags que a veces nos devuelve la pasarela de pagos						
						$('#errorPagoDescripcion').text(erroresDescripcion); //Mostramos por pantalla el tipo de error que devuelve la pasarela de pagos
					}
					
				}else{
		 			if(exento=="E"){
		 				proceso = 2; //Inicia proceso de pago
		 				progress2();
		 			}
		 		} 
			}
	
			if(exento=="E"){
				//Comienzo proceso de pago
				proceso = 2; 
				progress2();
			}
			
			if(erroresPago==true){
				errorProceso = 2; //ERROR EN EL PAGO
				progress2();
			}
				
			setTimeout(function(){firmarArchivos();}, 1000);	
		}
		
		function inicioFormularioRegistro(){
			if(erroresPago==false){
				//Comienzo proceso de registro
				proceso = 3;
				progress3();
			}
		}
	
		function finFormularioRegistro(data){
			var errores="";
			var resto = 16;
			var appVer = navigator.appVersion;
			var nav = appVer.toUpperCase().indexOf("MSIE 8.0");
			var nav2 = appVer.toUpperCase().indexOf("MSIE 7.0");
			
			if(nav!= -1 || nav2 != -1)
			    resto = 14;
		    
			if(data){
				var str=data;
			    var index=data.indexOf("<div id=\"error\">");
	
				if(index==-1 && (nav!=-1 || nav!=-2)){
					var indexIE=data.indexOf("INICIOERRORESSOLICITUD");
					var strFinIE=data.substr(indexIE);
					var finIE=strFinIE.indexOf("FINERRORESSOLICITUD");
					var erroresInscripcionIE = data.substr(indexIE+31, finIE-59);
					index = 0;
					
					if(erroresInscripcionIE!="")
						str = erroresInscripcionIE;
				}
						     
				var start=str.indexOf("<div id=\"error\" class=\"pae-alertbox pae-alertbox--warning\">");
				
				if(start == -1 && (nav!= -1 || nav2 != -1))
					start=str.indexOf("<DIV id=error>");
				
				var strFin=str.substr(start);
				var fin=strFin.indexOf("</div>");
				
				if(fin==-1 && (nav!= -1 || nav2 != -1))
				    fin=strFin.indexOf("</DIV>");		
			    
				errores = data.substr(start+resto, fin-resto); 
				
				if(errores.length>5){
					if(erroresPago!=true)
						errorProceso = 3; //ERROR EN EL REGISTRO 
				}
			}
				if(!erroresPago){ 
					if(errores.length<5){
						proceso = 4;
						progress4();
						//INSCRIPCION COMPLETADA
					}else{

						errorProceso = 3;
						}
					} 
	 			}
	
		//FIN FUNCIONES ACCIONES DE AJAX CON SERVIDOR
		 
		function recargarPagina(){
				var variable = document.getElementById("idConvocatoria").value;		 
				document.getElementById("id").value = variable;
				document.getElementById("formulario").submit(); 
		}
		
</script>