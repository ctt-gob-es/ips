<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.PlantillaBean" %>
<%@ page import="es.map.ipsg.util.Constantes" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script>
<%
	String tipoPagoPresencial =  (request.getAttribute("tipoPagoPresencial") != null)?request.getAttribute("tipoPagoPresencial").toString():"";
%>
<%
String user = (String)request.getAttribute("user");
String visibilidadNif = "none";
String visibilidadPrimerApellido = "none";
String visibilidadSegundoApellido = "none";
String visibilidadNombre = "none";
String visibilidadFechaNacimiento = "none";
String visibilidadSexo = "none";
String visibilidadNacionalidad = "none";
String visibilidadCorreoElectronico  = "none";
String visibilidadProvincia  = "none";
String visibilidadPais  = "none";
String visibilidadTelefono  = "none";
String visibilidadVia = "none";
String visibilidadMunicipio = "none";
String visibilidadEspecialidad = "none";
String visibilidadProvinciaExamen = "none";
String visibilidadTipoDiscapacidad = "none";
String visibilidadPorcentajeDiscapacidad = "none";
String visibilidadReservaDiscapacidad = "none";
String visibilidadDetalleDiscapacidad = "none";
String visibilidadTitulosExigidos = "none";
String visibilidadOtrosTitulos = "none";
String visibilidadDatosA = "none";
String visibilidadDatosB = "none";
String visibilidadDatosC = "none";
String visibilidadEntidadFinanciera = "none";





String isEntidadBancaria = (String)request.getParameter("isVisibleEntidadBancaria");

%>

<script type="text/javascript">

var nav4 = window.Event ? true : false;
function IsNumber(evt){
	// Backspace = 8, Enter = 13, '0' = 48, '9' = 57, '.' = 46
	var key = nav4 ? evt.which : evt.keyCode;
	return ((key <= 13) || (key >= 48 && key <= 57) || key == 46 || key == 37);
}

function cargaCamposObligatorios(){
	<% 
		PlantillaBean plantilla = (PlantillaBean)request.getAttribute("plantilla");
		if(plantilla != null)
		{
			if(plantilla.getNif().equals(new Character('S'))){	
				visibilidadNif="block";
			}else{
				visibilidadNif="none";
			}	
			
			if(plantilla.getPrimerApellido().equals(new Character('S'))){			
				visibilidadPrimerApellido="block";
			}else{
				visibilidadPrimerApellido="none";
			}
			
			if(plantilla.getSegundoApellido().equals(new Character('S'))){			
				visibilidadSegundoApellido="block";
			}else{
				visibilidadSegundoApellido="none";
			}
			
			if(plantilla.getNombre().equals(new Character('S'))){			
				visibilidadNombre="block";
			}else{
				visibilidadNombre="none";
			}
			
			if(plantilla.getFechaNacimiento().equals(new Character('S'))){			
				visibilidadFechaNacimiento="block";
			}else{
				visibilidadFechaNacimiento="none";
			}
			
			if(plantilla.getSexo().equals(new Character('S'))){			
				visibilidadSexo="block";
			}else{
				visibilidadSexo="none";
			}
			
			if(plantilla.getNacionalidad().equals(new Character('S'))){			
				visibilidadNacionalidad="block";
			}else{
				visibilidadNacionalidad="none";
			}
			
			if(plantilla.getCorreoElectronico().equals(new Character('S'))){			
				visibilidadCorreoElectronico="block";
			}else{
				visibilidadCorreoElectronico="none";
			}
			
			if(plantilla.getProvincia().equals(new Character('S'))){			
				visibilidadProvincia="block";
			}else{
				visibilidadProvincia="none";
			}
			
			if(plantilla.getPais().equals(new Character('S'))){			
				visibilidadPais="block";
			}else{
				visibilidadPais="none";
			}
			
			if(plantilla.getTelefono().equals(new Character('S'))){			
				visibilidadTelefono="block";
			}else{
				visibilidadTelefono="none";
			}
			

			if(plantilla.getVia().equals(new Character('S'))){			
				visibilidadVia="block";
			}else{
				visibilidadVia="none";
			}
			
			if(plantilla.getMunicipio().equals(new Character('S'))){			
				visibilidadMunicipio="block";
			}else{
				visibilidadMunicipio="none";
			}
			
			if(plantilla.getEspecialidad().equals(new Character('S'))){			
				visibilidadEspecialidad="block";
			}else{
				visibilidadEspecialidad="none";
			}
			

			if(plantilla.getProvinciaExamen().equals(new Character('S'))){			
				visibilidadProvinciaExamen="block";
			}else{
				visibilidadProvinciaExamen="none";
			}
			
			if(plantilla.getTipoDiscapacidad().equals(new Character('S'))){			
				visibilidadTipoDiscapacidad="block";
			}else{
				visibilidadTipoDiscapacidad="none";
			}
			
			if(plantilla.getPorcentajeDiscapacidad().equals(new Character('S'))){			
				visibilidadPorcentajeDiscapacidad="block";
			}else{
				visibilidadPorcentajeDiscapacidad="none";
			}
			

			if(plantilla.getReservaDiscapacidad().equals(new Character('S'))){			
				visibilidadReservaDiscapacidad="block";
			}else{
				visibilidadReservaDiscapacidad="none";
			}
			
			if(plantilla.getDetalleDiscapacidad().equals(new Character('S'))){			
				visibilidadDetalleDiscapacidad="block";
			}else{
				visibilidadDetalleDiscapacidad="none";
			}
			
			if(plantilla.getTitulosExigidos().equals(new Character('S'))){			
				visibilidadTitulosExigidos="block";
			}else{
				visibilidadTitulosExigidos="none";
			}
			
			if(plantilla.getOtrosTitulos().equals(new Character('S'))){			
				visibilidadOtrosTitulos="block";
			}else{
				visibilidadOtrosTitulos="none";
			}
			
			if(plantilla.getDatosA().equals(new Character('S'))){			
				visibilidadDatosA="block";
			}else{
				visibilidadDatosA="none";
			}
			
			if(plantilla.getDatosB().equals(new Character('S'))){			
				visibilidadDatosB="block";
			}else{
				visibilidadDatosB="none";
			}
			
			if(plantilla.getDatosC().equals(new Character('S'))){			
				visibilidadDatosC="block";
			}else{
				visibilidadDatosC="none";
			}
		}	
	
	%>
	
}	
			
	
	
function cargaDatos(){
	if(document.getElementById("idConvocatoria").value == null || document.getElementById("idConvocatoria").value == ''){
		return false;
	}
	document.forms[1].action = "../spring/cargarDatosSolicitudPresencial?idConv="+document.getElementById("idConvocatoria").value;
	//document.forms[0].submit();	
	//window.location.href = "../spring/cargarDatosSolicitudPresencial?idConv="+document.getElementById("idConvocatoria").value;
	
}
function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}
function guardar(){


	
		$("#accion").val("GUARDAR");
		document.getElementById("menu").value="N";
		document.forms[1].action = "../spring/altaSolicitudPresencial";

}

	
function registrarREC(){
	var error = false;
	
	// Comprobamos si se han insertado los datos del pago
	if($("#idTipoPago").val() == '0'){
		if($("#id_motivosEx").val() == null || $("#id_motivosEx").val() == ''
				|| $("#importe").val() == null || $("#importe").val() == ''
					|| $("#importeDecimal").val() == null || $("#importeDecimal").val() == ''){
			alert("Se deben completar los datos del pago para el registro en REC.");
			error = true;
		}
	}else if($("#idTipoPago").val() == '1'){
		if($("#id_motivosRed").val() == null || $("#id_motivosRed").val() == ''
			|| $("#fechaPago").val() == null || $("#fechaPago").val() == ''
				|| $("#importe").val() == null || $("#importe").val() == ''
					|| $("#importeDecimal").val() == null || $("#importeDecimal").val() == ''
						|| $("#idEntidadBancaria").val() == null || $("#idEntidadBancaria").val() == ''
							|| $("#idnrcPago").val() == null || $("#idnrcPago").val() == ''){
			alert("Se deben completar los datos del pago para el registro en REC.");
			error = true;
		}
	}else if($("#idTipoPago").val() == '2'){
		if( $("#fechaPago").val() == null || $("#fechaPago").val() == ''
				|| $("#importe").val() == null || $("#importe").val() == ''
					|| $("#importeDecimal").val() == null || $("#importeDecimal").val() == ''
						|| $("#idEntidadBancaria").val() == null || $("#idEntidadBancaria").val() == ''
							|| $("#nrcPago").val() == null || $("#idnrcPago").val() == ''){
			alert("Se deben completar los datos del pago para el registro en REC.");
			error = true;
		}
	}else if($("#idTipoPago").val() == '4'){
		if( $("#fechaPago").val() == null || $("#fechaPago").val() == ''){
			alert("Se deben completar los datos del pago para el registro en REC.");
			error = true;
		}
	}
	
	if($("#fechaRegistro").val() == null || $("#fechaRegistro").val() == ''){
			alert("Se deben completar los datos del registro.");
			error = true;
	}
	
	if($("#datosFichero div").length <=0){
		$("#errorAdjunto").show();
		error = true;
	} else {
		$("#errorAdjunto").hide();
	}
	
	
	if(!error){
		// Eliminamos el fichero que se quedar marcado por defecto, tiene el id sin indice
		var ficheros = document.getElementsByName("file");
		var i = 0;
		for (i; i < ficheros.length; i++){
			if(ficheros[i].id = "file"){
				ficheros[i].remove();
				}
		}
		
		document.forms[1].action = "../spring/verCrearSolicitudPresencial";
		ventanaPopup = window.open("../spring/redireccionadorRegistroSpring?numeroSolicitud="+numeroSolicitud.value +"&llamada=RegistroREC", 'miventana', 'width=550, height=400,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
		
		return true;	
	}

	return false;
}

function nifValidate(nif){ 
	if (!nif) 
		return false;
	
	if(nif.length == 9){ 
		nif=nif.toUpperCase(); 
		if(/[0-9]{8}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)){  
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET'; 
			var posicion_letra = nif.substring(0,8) % 23; 
			if(nif.charAt(8) != temp.charAt(posicion_letra)){
				return false;
			}
		}else{
			return false;
		}
	}else{ 
		return false;
	}
	return true;
}

function nieValidate(nie){ 
	if (!nie) 
		return false;
	
	if(nie.length == 9){ 
		nie=nie.toUpperCase(); 
		if(/[XY][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nie)){ 
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET'; 
			var posicion_letra = nie.substring(1,8) % 23; 
			if(nie.charAt(8) != temp.charAt(posicion_letra)){
				return false;
			}
		}else{
			return false;
		}
	}else{ 
		return false;
	}
	return true;
}


function validaDatos(){
	var identificacion = document.getElementById("nif").value;
	var letranie = identificacion.substring(0,1);
	var result;
	if(identificacion != null && identificacion != ""){
		if(letranie == "X" || letranie == "Y"){
			result = nieValidate(identificacion);
			if(result == false){				
				alert('<spring:message code="usuario.errors.dni1"/>');
				return false;
			}else{
				return true;	
			}
		}else{
			result = nifValidate(identificacion);
			if(result == false){
				alert('<spring:message code="usuario.errors.dni2"/>');
				return false;
			}else{
				return true;	
			}
		}
	}else{
		return true;
	}
}

//Validar los campos DatosA,DatosB y DatosC
	
	
	function ContadorCaracteres(obj){ 
		var max_caracteres = 100; 
		var clr=0; 
	var len = 0; 
	len+=obj.value.length; 
	 
		  if (len > max_caracteres){ 
			    alert("El máximo de caracteres permitido es " + max_caracteres + "."); 
			    obj.value=obj.value.substring(0,obj.value.length+max_caracteres-len); 
			    obj.focus(); 
			    } 
	    
	} 


//Fin validar campos DatosA,DatosB y DatosC



//Validar los campos OtrosTitulos, DetalleDiscapacidad
	
	
	function ContadorCaracteres200(obj){ 
		var max_caracteres200 = 200; 
		var clr=0; 
	var len = 0; 
	len+=obj.value.length; 
	 
		  if (len > max_caracteres200){ 
			    alert("El máximo de caracteres permitido es " + max_caracteres200 + "."); 
			    obj.value=obj.value.substring(0,obj.value.length+max_caracteres200-len); 
			    obj.focus(); 
			    } 
	    
	} 


//Fin validar campos OtrosTitulos, DetalleDiscapacidad
	
	
function cancelar(user)
{
	if(user != "9"){
	document.getElementById("menu").value="S";
	document.getElementById("accion").value="VOLVER";
	document.forms[1].action = "../spring/verCrearSolicitudPresencial"; 
	 }else{
		document.forms[1].action = "../spring/verCrearSolicitudPresencial?menu=S#Sol.&nbspPresenciales"; //Va a alta de solicitudes
		} 
}

function openWindowConvocatoria() {
	var param = "idConvocatoria";
	var param2 = "dsConvocatoria";
	var extract = new Object();
		ventanaPopup = window.open("../spring/listarConvocatoria?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
		ventanaPopup.focus();
	return false;
}



function limpiar(importem,importeDecimalm){
	document.getElementById("importe").value=importem;
	document.getElementById("importeDecimal").value=importeDecimalm;
}

function quitarProvinciaOb(){
	if($("#idPais").val() != "1"){
		$("#oblProvincia").attr("style","display:none");
		// Se deshabilita el combo
	}else{
		$("#oblProvincia").removeAttr("style");
		// Se habilita el combo
	}
}

$( document ).ready(function() {
	if($("#idPais").val() != "1"){
		$("#oblProvincia").attr("style","display:none");
	}else{
		$("#oblProvincia").removeAttr("style");
		}
});
</script>


<%@page import="es.map.ipsg.util.Constantes"%><html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>
<body style="margin-left: 0.4em;" onload="cargaCamposObligatorios();comprobarDatosCargados()">
<!--<div style="margin-left: 1em; margin-right: 1em; float: left; width: 95%;"-->

<form:form modelAttribute="altaSolicitudPresencialForm" action="/IPSG/spring/verAltaSolicitudPresencial" id="formPadre" method="post"  enctype="multipart/form-data">



<h1 class="pae-title"><spring:message code="field.solicitudPresencial.tituloAlta" /></h1>
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	<form:hidden path="cambios" id="cambios"/>
<%-- 	<form:hidden path="accion" id="accion"/> --%>
	<form:hidden path="requiereTitulo" id="requiereTitulo" value="false" />
	<form:hidden path="accion" />
	<input type="hidden" id="comunidadesReqTitulo" value="${comunidadesReqTitulo}">
	
    <input type="hidden" name="isVisibleEntidadBancaria" value="<%=isEntidadBancaria != null ? isEntidadBancaria: "true"%>"/>
    
	<!--    <form:hidden path="submit" id="submit"/>-->
	<form:hidden path="menu" id="menu" value="S"/>
	<form:hidden path="codigoPostal" />

	<div class="">
		<div data-function="accordion-box" class="pae-box"> 
    		<div class="pae-box__body">
    			<fieldset>    			
				<!-- DATOS DE LA CONVOCATORIA -->
				<br><br>
				<b><spring:message code="field.solicitudPresencial.datosDeConvocatoria" /></b>
				<hr>
				<br>
		
				<!-- Ini Convocatoria -->
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudPresencial.convocatoria.mayus"/><span class="obligatorio"> *</span>
							</span>			
							<form:input type="text" path="idConvocatoria" id="idConvocatoria" class="pae-form__input" readonly="true" onfocus="comprobarBusqueda();cargaDatos();this.form.submit();"></form:input>							
							<form:input type="text" path="dsConvocatoria" id="dsConvocatoria" class="input_texto_border0 em100" readonly="true"></form:input>							
						</div>
					</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
						<input type="button"
										class="pae-form__btn-search" alt="Buscar Convocatoria"
										onclick="return openWindowConvocatoria()">					
					</div>
				</div>
				<!-- Fin Convocatoria -->
				<!-- ****************************** -->
				<br><br>
				<!-- ****************************** -->
				<!-- DATOS DE USUARIO -->
				<b><spring:message code="field.solicitudPresencial.datosDeUsuario" /></b>
				<hr>
				<br>
				<!-- Ini Nif -->
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">			
								<spring:message code="field.solicitudPresencial.nifnie.mayus"/><%if (visibilidadNif.equals("block")){%> <span class="obligatorio"> *</span><%}%>
								<form:input type="text" path="nif" id="nif" maxlength="9" class="pae-form__input"/>
						</div>
					</div>
 				<!-- Fin Nif -->
				<!-- ****************************** -->
				<!-- Ini Nombre -->
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho">
								<spring:message code="field.solicitudPresencial.nombre.mayus"/><%if (visibilidadNombre.equals("block")){%> <span class="obligatorio"> *</span><%}%>
							</span>
							<form:input type="text" path="nombre" id="nombre" maxlength="50" class="pae-form__input" />
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">			
								<spring:message code="field.solicitudPresencial.primerApellido.mayus"/><%if (visibilidadPrimerApellido.equals("block")){%> <span class="obligatorio"> *</span><%}%>
							</span>
							<form:input type="text" path="primerApellido" id="primerApellido" maxlength="50" class="pae-form__input"/>
						</div>
					</div>		
				<!-- Fin PrimerApellido-->
				<!-- ****************************** -->
				<!-- Ini Segundo Apellido -->				
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho">
								<spring:message code="field.solicitudPresencial.segundoApellido.mayus"/><%if(visibilidadSegundoApellido.equals("block")){%><span class="obligatorio">*</span><%}%>
							</span>
							<form:input type="text" path="segundoApellido" id="segundoApellido" maxlength="50" class="pae-form__input"/>
						</div>
					</div>				
				</div>
 				<!-- Fin Segundo Apellido -->
				<!-- ****************************** -->		
				<!-- Ini Fecha de Nacimiento -->
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">		
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho">
								<spring:message code="field.solicitudPresencial.fechaNacimiento.mayus"/><%if (visibilidadFechaNacimiento.equals("block")){%> <span class="obligatorio">*</span><%}%>
							</span>
								<form:input type="text" path="fechaNacimiento" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input"  maxlength="10"/>							
<!-- 				<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaNacimiento'), 'dd/mm/yyyy',1);"> -->
<!-- 							<script type="text/javascript"> -->
<%-- 							fncCalendarioPresenciales('fechaNacimiento', '<%=request.getContextPath()%>'); --%>
<!-- 							</script> -->
						</div>
					</div>
 				<!-- Fin Fecha de Nacimiento -->
				<!-- Ini Localidad de Nacimiento -->
				<!-- 
		<div class="capaDer">
			<div class="labelIzq">
				<spring:message code="field.solicitudPresencial.localidadNacimiento.mayus"/>
			</div>
			<div class="contenidoDer" align="left" >
				<form:input path="localidadNacimiento" id="localidadNacimiento" maxlength="50" class="text_1" />
			</div>
			<div class="clear"></div>
		</div>
		 -->
		<!-- Fin Localidad de Nacimiento-->
		<!-- ****************************** -->
			<!-- Ini Nacionalidad -->		
				<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">			
							<spring:message code="field.solicitudPresencial.nacionalidad.mayus"/><%if (visibilidadNacionalidad.equals("block")){%> <span class="obligatorio"> *</span><%}%>
						</span>				
						<form:input type="text" path="nacionalidad"  id="nacionalidad" maxlength="50" class="pae-form__input"/>
					</div>
				</div>
		<!-- Ini Correo Electrónico -->
				<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.correoElectronico.mayus"/><%if (visibilidadCorreoElectronico.equals("block")){%> <span class="obligatorio">*</span><%}%>
						</span>						
						<form:input type="text" path="email"  id="email" maxlength="50" class="pae-form__input" />
					</div>
				</div>
		</div>
		<div class="pae-layout">	
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.consentimiento.mayus"/>
					</span>
					<form:select path="ckConsentimiento" class="pae-form__input" id="ckConsentimiento" style="width: 100">
						<form:option value=""></form:option>
						<form:option value="true"><spring:message code="field.siMayus"/></form:option>
						<form:option value="false"><spring:message code="field.noMayus"/></form:option>
					</form:select>
				</div>
			</div>
			<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm">	</div>
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">			
							<spring:message code="field.solicitudPresencial.sexo.mayus"/><%if (visibilidadSexo.equals("block")){%> <span class="obligatorio"> *</span><%}%>
						</span>
						<form:select path="sexo" class="pae-form__input" id="sexo" >
							<form:option value=""></form:option>
							<form:option value="<%=Constantes.SEXO_HOMBRE%>"><spring:message code="field.hombreMay"/></form:option>
							<form:option value="<%=Constantes.SEXO_MUJER%>"><spring:message code="field.mujerMay"/></form:option>
						</form:select>
					</div>
				</div>
 		<!-- Fin Sexo -->
		</div>
 		<!-- Fin Consentimiento -->
		<!-- ****************************** -->		
		<!-- ****************************** -->
		
		<br><br>
		<!-- DATOS DE DOMICILIO -->
		<b><spring:message code="field.solicitudPresencial.datosDeDomicilio" /></b>
		<hr>
		<br>
		<!-- ****************************** -->
		
		<!-- Ini Calle -->
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-7/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.domicilio.mayus"/><%if (visibilidadVia.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>			
					<form:input type="text" path="calleDomicilio" id="calleDomicilio" maxlength="200" class="pae-form__input" />
				</div>
			</div>				
			<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.municipio.mayus"/><%if (visibilidadMunicipio.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>
					<form:input type="text" path="municipioDomicilio" id="municipioDomicilio" maxlength="50" class="pae-form__input"/>
				</div>
			</div>		
 		<!-- Fin Municipio-->
 		<!-- Ini Teléfono-->		
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.telefono.mayus"/><%if (visibilidadTelefono.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>
					<form:input type="text" path="telefono1" id="telefono1" maxlength="10" class="pae-form__input"/>					
				</div>
			</div>
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pl15">		
				<div class="pae-form__cell">
					<br>
					<form:input type="text" path="telefono2" id="telefono2" maxlength="10" class="pae-form__input"/>
				</div>
			</div>	
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.pais.mayus"/><%if (visibilidadPais.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>
					<form:select path="idPais" class="pae-form__input" id="idPais" onchange="quitarProvinciaOb();">
						<option value="">         </option>
						<logic:present name="paises" scope="request">
							<bean:size id="numPais" name="paises" scope="request"/>
								<logic:greaterThan name="numPais" value="0">
									<form:options items="${paises}" itemLabel="descripcion" itemValue="id"/>	
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>
 		<!-- Fin País -->
		<!-- Ini Provincia Domicilio-->				
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.provincia.mayus"/><%if (visibilidadProvincia.equals("block")){%> <span class="obligatorio"  id="oblProvincia"> *</span><%}%>
					</span>
					<form:select path="idProvinciaDomicilio" class="pae-form__input" id="idProvinciaDomicilio" >
						<option value="">         </option>
						<logic:present name="provincias" scope="request">
							<bean:size id="numProvincia" name="provincias" scope="request"/>
								<logic:greaterThan name="numProvincia" value="0">
									<form:options items="${provincias}" itemLabel="descripcion" itemValue="id"/>	
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>			
			</div>
		</div>
		<!-- Fin Provincia Domicilio-->
		<!-- ****************************** -->		
		<!-- ****************************** -->
		<br><br>
		<!-- DATOS DE SOLICITUD -->
			<B><spring:message code="field.solicitudPresencial.datosDeSolicitud" /></B>
		<hr>
		<br>
		
		<!-- ****************************** -->
		<!-- Ini Justificante -->
		<div class="pae-layout">		
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.justificantePago"/><span class="obligatorio"> *</span>
					</span>
					<form:input type="text" path="numeroSolicitud" id="numeroSolicitud" maxlength="20" class="pae-form__input"/>
				</div>
			</div>
 		<!-- Fin Justificante -->		
		<!-- Ini Especialidad -->		
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">			
						<spring:message code="field.solicitudPresencial.especialidad.mayus"/><%if (visibilidadEspecialidad.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>			
					<form:select path="idEspecialidad" class="pae-form__input" id="idEspecialidad" >
						<option value="">         </option>
						<logic:present name="especialidades" scope="request">
							<bean:size id="numEspecialidad" name="especialidades" scope="request"/>
								<logic:greaterThan name="numEspecialidad" value="0">
									<form:options items="${especialidades}" itemLabel="descripcion" itemValue="id"/>	
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>	
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.provinciaExamen.mayus"/><%if (visibilidadProvinciaExamen.equals("block")){%> <span class="obligatorio">*</span><%}%>
					</span>
					<form:select path="idProvinciaExamen" class="pae-form__input" id="idProvinciaExamen" >
						<option value="">         </option>
						<logic:present name="provinciasExamen" scope="request">
							<bean:size id="numProvincia" name="provinciasExamen" scope="request"/>
								<logic:greaterThan name="numProvincia" value="0">
									<form:options items="${provinciasExamen}" itemLabel="descripcion" itemValue="id"/>
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>		
		<!-- Fin Provincia de Examen-->
		<!-- ****************************** -->
		<!-- Ini Fecha de Solicitud -->		
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.fechaSolicitud.mayus"/>
					</span>
					<form:input type="text" path="fechaSolicitud" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>
<!-- 				<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaSolicitud'), 'dd/mm/yyyy',2);"> -->
<!-- 				<script type="text/javascript"> -->
<%-- 							fncCalendario('fechaSolicitud', '<%=request.getContextPath()%>'); --%>
<!-- 					</script> -->
				</div>
			</div>	
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label pae-ancho3">
						<spring:message code="field.solicitudPresencial.discapacidad.mayus"/><%if (visibilidadTipoDiscapacidad.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>
					<form:select path="idTipoDiscapacidad" class="pae-form__input" id="idTipoDiscapacidad" >
						<option value="">         </option>
						<logic:present name="tipoDiscapacidades" scope="request">
							<bean:size id="numDiscapacidad" name="tipoDiscapacidades" scope="request"/>
								<logic:greaterThan name="numDiscapacidad" value="0">
									<form:options items="${tipoDiscapacidades}" itemLabel="descripcion" itemValue="id"/>	
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>		
 		<!-- Fin Discapacidad -->
		<!-- Ini % Discapacidad -->
			<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label pae-ancho11">
						<spring:message code="field.solicitudPresencial.porcentajeDiscapacidad.mayus"/><%if (visibilidadPorcentajeDiscapacidad.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>
					<form:input type="text" path="porcentajeDiscapacidad"  id="porcentajeDiscapacidad" maxlength="3" class="pae-form__input" onkeypress="return IsNumber(event);"/>
				</div>
			</div>		
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
				<div class="pae-form__cell">
					<input type="checkbox" name="ckReservaDiscapacidad" property="ckReservaDiscapacidad" id="ckReservaDiscapacidad" data-function="checkbox-custom-input" class="pae-form__custom-check">
					<label for="ckReservaDiscapacidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.reservaDiscapacidad.mayus"/><%if (visibilidadReservaDiscapacidad.equals("block")){%> <span class="obligatorio"> *</span><%}%>
						</span>
					</label>
				</div>
			</div>
		</div>
		<!-- Fin Reserva de Plaza por Discapacidad-->
		<!-- ****************************** -->
		<!-- Ini Adaptación por Discapacidad-->
		<div class="pae-layout">		
			<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.adaptacionPorDiscapacidad.mayus"/><%if (visibilidadDetalleDiscapacidad.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>
					<form:textarea path="adaptacionDiscapacidad" id="adaptacionDiscapacidad"  class="pae-form__input" rows="5" cols="120"   onkeyup="ContadorCaracteres200(this)" onkeydown="ContadorCaracteres200(this)" /> 
				</div>
			</div>
		</div>
		<!-- Fin Adaptación de Plaza por Discapacidad-->
		
		<!-- ****************************** -->
		<br><br>
		<!-- ****************************** -->

		<!-- DATOS DE TÍTULOS -->
			<B><spring:message code="field.solicitudPresencial.datosDeTitulos" /></B>
		<hr>
		<br>
		<!-- ****************************** -->
		<!-- Ini Título -->
		<div class="pae-layout">		
			<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm w66">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.titulo.mayus"/><%if (visibilidadTitulosExigidos.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>
					<form:select path="idTitulo" class="pae-form__input" id="idTitulo">
						<form:option value=""></form:option>
						<logic:present name="titulos" scope="request">
							<bean:size id="numTitulo" name="titulos" scope="request"/>
								<logic:greaterThan name="numTitulo" value="0">
									<form:options items="${titulos}" itemLabel="descripcion" itemValue="id"/>
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>			
		</div>
 		<!-- Fin Título -->
		<!-- ****************************** -->
		<!-- Ini  Otros Títulos -->
		<div class="pae-layout">		
			<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.otrosTitulo.mayus"/><%if (visibilidadOtrosTitulos.equals("block")){%> <span class="obligatorio"> *</span><%}%>
					</span>				 
					<form:textarea path="otrosTitulos" id="otrosTitulos" rows="5" cols="120"  class="pae-form__input" onkeyup="ContadorCaracteres200(this)" onkeydown="ContadorCaracteres200(this)"/> 	
				</div>
			</div>
		</div>
 		<!-- Fin Otros Títulos -->
		<!-- ****************************** -->		
		<!-- ****************************** -->
		<br><br>
		
		<!-- DATOS A CONSIGNAR -->
		<b><spring:message code="field.solicitudPresencial.datosAConsignar" /></b>
		<hr>
		<br>
		<logic:present name="listaCamposPropiosBean" scope="request">
			<div class="pae-layout">	
			<logic:iterate id="listaCamposPropiosBean" name="listaCamposPropiosBean" indexId="index">
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<bean:write name="listaCamposPropiosBean" property="campo" />
							</span>				
							<form:input type="text" size="140" class="pae-form__input"  path="listaTextAreas[${index}].campo" value="${listaCamposPropiosBean.valorVista}"/>
							<form:input type="hidden" path="listaTextAreas[${index}].id" value="${listaCamposPropiosBean.id}"/>
							<form:input type="hidden" path="listaTextAreas[${index}].idModelo" value="${listaCamposPropiosBean.idModelo}"/>
						</div>
					</div>
			</logic:iterate>
			</div>
		</logic:present>
		
		<!-- ****************************** -->
		<!-- ****************************** -->
		<br><br>
		<!-- DATOS DE PAGO -->
			<B><spring:message code="field.solicitudPresencial.datosDePago" /></B>
		<hr>
		<br>
		<!-- Ini Tipo de Pago-->
		<div class="pae-layout">		
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.tipoPago.mayus"/>
					</span>
					<form:select path="idTipoPago" class="pae-form__input" id="idTipoPago" onchange="visibilidadTipoPago('borrar');">
						<option value="">         </option>
						<logic:present name="tipoPagos" scope="request">
							<bean:size id="numTipoPago" name="tipoPagos" scope="request"/>
								<logic:greaterThan name="numTipoPago" value="0">
									<form:options items="${tipoPagos}" itemLabel="descripcion" itemValue="id"/>	
								</logic:greaterThan>
						</logic:present>
					</form:select>								
				</div>
			</div>
 		<!-- Fin  Tipo de Pago-->
		<!-- ****************************** -->
		<!-- Ini Motivo reduccion-->
		<% if(tipoPagoPresencial != null && !(tipoPagoPresencial.equals("5"))){ %>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<div id="motivos" style="display: block;">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.motivosReduccion"/> <span class="obligatorio">*</span>
						</span>
						<form:select path="idMotivosRed" class="pae-form__input" id="id_motivosRed" onchange="motivoChecked(this)">
							<option value="">         </option>
							<logic:present name="motivos" scope="request">
								<bean:size id="numTotal" name="motivos" scope="request"/>
									<logic:greaterThan name="numTotal" value="0">
										<form:options items="${motivos}" itemLabel="descripcion" itemValue="id"/>	
									</logic:greaterThan>
							</logic:present>
						</form:select>
					</div>
					<!-- Ini Motivo exención-->			
					<div id="motivosCompletos" style="display: none;">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.motivosExencion"/>
							<span class="obligatorio">*</span>
						</span>
						<form:select path="idMotivosEx" class="pae-form__input" id="id_motivosEx" onchange="limpiar(0,0);motivoChecked(this)">
							<option value="">         </option>
							<logic:present name="motivosCompletos" scope="request">
								<bean:size id="numTotalMoEx" name="motivosCompletos" scope="request"/>
									<logic:greaterThan name="numTotalMoEx" value="0">
										<form:options items="${motivosCompletos}" itemLabel="descripcionMoEx" itemValue="idMotivoEx"/>	
									</logic:greaterThan>
							</logic:present>
						</form:select>
					</div>
					<!-- Fin Motivo exencion-->			
				</div>
			</div>		
			<% } %>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">		
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.fechaPago.mayus"/> <span class="obligatorio">*</span>
					</span>
					<form:input type="text" path="fechaPago" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>
<!-- 				<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaPago'), 'dd/mm/yyyy',2);"> -->
<!-- 				<script type="text/javascript"> -->
<%-- 							fncCalendario('fechaPago', '<%=request.getContextPath()%>'); --%>
<!-- 					</script> -->
				</div>
			</div>
 		<!-- Fin Fecha de Pago -->
 		<!-- ****************************** -->
 		<!-- Ini Comunidad Autónoma Familia Numerosa-->		
 			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
					<div class="pae-form__cell">
						<div id="comunidadAutonomaFN" style="display: none;">	
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudPresencial.ComunidadAutonoma"/>
							</span>
							<form:select path="comunidadFN" class="pae-form__input" id="comunidadFN" onchange="comunidadFNChecked()">
								<form:option value=""></form:option>
								<form:options items="${listcomunidadesFN}" path="idComunidad" itemLabel="descripcion" itemValue="idComunidad"/>
							</form:select>							
						</div>
						<!-- Ini Comunidad Autónoma Discapacidad-->
						<div id="comunidadAutonomaDD" style="display: none;">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudPresencial.ComunidadAutonoma"/>
							</span>
							<form:select path="comunidadDD" class="pae-form__input" id="comunidadDD">
								<form:option value=""></form:option>
								<form:options items="${listcomunidadesDiscapacidad}" path="idComunidad" itemLabel="descripcion" itemValue="idComunidad"/>
							</form:select>
						</div>
						<!-- Fin Comunidad Autónoma Discapacidad-->
						<!-- Ini Número Título Familia Numerosa-->
						<div id="numTituloFN" style="display: none;">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudPresencial.NumeroTituloFN"/>
								<span class="obligatorio">*</span>
							</span>
							<form:input type="text" path="numeroTituloFN" id="numeroTituloFN" maxlength="8"/>
						</div>	
						<!-- Fin Número Título Familia Numerosa-->
				</div>
			</div>
		</div>
 		<!-- Fin Comunidad Autónoma Familia Numerosa-->
		<!-- ****************************** -->		
		<!-- Ini Importe -->
			<div class="pae-layout">		
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.importe.mayus"/> <span class="obligatorio">*</span>
						</span>
						<div class="pae-box-buttons">
									<form:input type="text" path="importe" id="importe" maxlength="4" class="pae-form__input w50" onkeypress="return IsNumber(event);" />
									<span class="pae-form__label pae-form__span-label rel bot disunset">&nbsp;,&nbsp; </span>
									<form:input type="text" path="importeDecimal" id="importeDecimal" maxlength="2" class="pae-form__input w35" onkeypress="return IsNumber(event);" />
						</div>
					</div>
				</div>
		          <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	</div> 
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<div id="entidadBancaria" style="display: <%out.print((isEntidadBancaria == null ||  !isEntidadBancaria.equals("false")  || isEntidadBancaria == "" ) ? "block" : "none");%>">	
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.entidadBancaria.mayus"/> <span class="obligatorio">*</span>
						</span>						
						<form:select path="idEntidadBancaria" class="pae-form__input" id="idEntidadBancaria" >
							<option value="">         </option>
							<logic:present name="entidadesBancarias" scope="request">
								<bean:size id="numEntidad" name="entidadesBancarias" scope="request"/>
									<logic:greaterThan name="numEntidad" value="0">
										<form:options items="${entidadesBancarias}" itemLabel="descripcion" itemValue="id"/>	
									</logic:greaterThan>
							</logic:present>
						</form:select>						
					</div>
				</div>
			</div>
 		<!-- Fin Entidad Bancaria -->

		<!-- Ini NRC Pago -->
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<div id="nrcPago" style="display: block;">	
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.nrcPago.mayus"/> <span class="obligatorio">*</span>
						</span>
						<form:input type="text" path="nrcPago"  id="idnrcPago" maxlength="22" class="pae-form__input"/>
					</div>
				</div>
			</div>
			
		</div>
		<logic:equal name="usuario" property="desPerfil" value="<%=Constantes.DENOM_PERFIL_REGISTRO%>">
			<div class="pae-layout">	
				<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">	
					<font color="#c33400"> <spring:message code="field.solicitudPresencial.error.pago.registroREC"/> </font>
				</div>
			</div>
		</logic:equal>
		
		<!-- Fin NRC Pago-->
		<!-- ****************************** -->		
		<!-- ****************************** -->

		<!-- DATOS DE REGISTRO -->
		<br>
 		<br>
		<B><spring:message code="field.solicitudPresencial.datosDeRegistro" /></B>
		<hr>
		<br>
		<!-- Ini Número de Registro-->
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.numRegistro.mayus"/>
						</span>
						<form:input type="text" path="numeroRegistro" id="numeroRegistro" maxlength="20" class="pae-form__input"/>
				</div>
			</div>			
 		<!-- Fin Número de Registro-->
 		<!-- Ini Oficina de Registro -->
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.oficinaRegistro.mayus"/>
						</span>
						<form:input type="text" path="oficinaRegistro" id="oficinaRegistro" maxlength="20" class="pae-form__input"/>
				</div>			
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.solicitudPresencial.fechaRegistro.mayus"/><span class="obligatorio"> *</span>
					</span>
					<form:input type="text" path="fechaRegistro" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>
					<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<font color="#c33400"> <spring:message code="field.solicitudPresencial.error.registro.guardar"/> </font>
					</div>			
				</div>
			</div>
		</div>
 		<!-- Fin Fecha de Registro -->
 		<!-- DOCUMENTACION, SE MUESTRA SOLO SI EL ROL ES ROLE_REGISTRO -->
 		<logic:equal name="usuario" property="desPerfil" value="<%=Constantes.DENOM_PERFIL_REGISTRO%>">
 		<br>
 		<br>
			<B><spring:message code="field.solicitudPresencial.documentacion" /></B>
		<hr>
		<br>
		
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.documento.nombre"/> <span class="obligatorio"> *</span>
				</span>
				<input type="text" size="50" id="nombreDocumento" class="pae-form__input"/>					
			</div>
		
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.documento.descripcion"/> <span class="obligatorio"> *</span>
				</span>
				<input type="text" size="50" id="descripcionDocumento" class="pae-form__input"/>
			</div>
		
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.documento.tipoDocumento"/> <span class="obligatorio"> *</span>
				</span>
				<select class="pae-form__input" id="idTipoDocumento" >
					<logic:present name="tipoDocumento" scope="request">
						<bean:size id="numTipoDocumento" name="tipoDocumento" scope="request"/>
							<logic:greaterThan name="numTipoDocumento" value="0">
								<logic:iterate collection="${requestScope.tipoDocumento}" id="registro">
									<option value="${registro.id }">${registro.descripcion }</option>
								</logic:iterate>
							</logic:greaterThan>
					</logic:present>
				</select>
				
			</div>
		</div>
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.documento.fichero"/> <span class="obligatorio"> *</span>
				</span>
					
				<input type="file" name="file" id="file" class="text_1" size="20" />				
			</div>	
			<div id="errorAdjunto" class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm pae-pv1" style="display:none">
				<div class="pae-form__cell">
					<font color="#c33400">
						<spring:message code="field.solicitudPresencial.error.registro.adjunto"/>
					</font>
				</div>
			</div>
		</div>
		
		<br>
		
		<div class="pae-layout">
			<div class="pae-layout__item">
				<div class="pae-box-buttons pd-right-1">
					<input type="button" name="submit" data-function="fc-add-document" value="Añadir" id="agregarFichero" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
				</div>
			</div>
		</div>
		<br>

		<div class="pae-layout" id="documentosAnadidos">
			<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
				<span class="pae-form__label" id="datosFichero">
				</span>
				<div id="eliminarFichero"></div>
			</div>
		</div>
		
		</logic:equal>
		<!-- ****************************** -->						
		<div class="filaBtn">
			<div class="pae-box-buttons pd-right-1">
				<logic:equal name="usuario" property="desPerfil" value="<%=Constantes.DENOM_PERFIL_REGISTRO%>">
					<input type="submit" value="Registrar REC" onclick="return registrarREC()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
				</logic:equal>
				<input type="submit" value="Guardar" onclick="guardar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
				<input type="submit" name="acciones" value="Cancelar" onclick="cancelar(<%=user %>)" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>																				
			</div>
		</div>	
		
		</div>
		</fieldset>
	</div>
</div>
				
	<script type="text/javascript">
						var importe=document.getElementById("importe").value;
						var importeDecimal=document.getElementById("importeDecimal").value;
	                    visibilidadTipoPago();
	                    
	                    function comprobarDatosCargados(){
		                    if(document.getElementById("id_motivosEx").value!=""){
		                    	motivoChecked(document.getElementById("id_motivosEx"));
		                    		if(document.getElementById("comunidadFN").value!=""){
		                    			comunidadFNChecked();
		                    		}
		                    }else if (document.getElementById("id_motivosRed").value!=""){
		                    	motivoChecked(document.getElementById("id_motivosRed"));
	                    		if(document.getElementById("comunidadFN").value!=""){
	                    			comunidadFNChecked();
	                    		}
		                    }
	                    }
	                                        
	                    function visibilidadTipoPago(valor){
	            			
	                		var idPago = document.getElementById("idTipoPago").value;
	                		var entidadBancanaria = document.getElementById("entidadBancaria").style.display = "block";	
	                		if(document.getElementById("error")==null){
	                			limpiar(importe,importeDecimal);	
	                		}				
	                		switch (idPago) {
	                		
	                		<%-- Caso de idPago vacio, no se ha seleccionado ningún tipo de pago  --%>
	                		   case "":
	                			    document.getElementById("nrcPago").style.display = "block";
	                			    document.getElementById("motivos").style.display = "block";
	                			    document.getElementById("motivosCompletos").style.display = "none";
	                			    document.getElementById("entidadBancaria").style.display = "block";	
	                				document.getElementById("comunidadAutonomaFN").style.display="none";
	                				document.getElementById("comunidadAutonomaDD").style.display="none";
	                				document.getElementById("numTituloFN").style.display="none";
	                			    if(document.getElementById("isVisibleEntidadBancaria")){
	                			    	document.getElementById("isVisibleEntidadBancaria").value = "true";	
	                			    }
	                			    
	                		      break;
	                		 <%-- 	Se ha seleccionado el tipo de pago: Exento  --%>   
	                		   case "0":
	                			    document.getElementById("nrcPago").style.display = "none";
	                			    document.getElementById("motivos").style.display = "none";
	                			    document.getElementById("motivosCompletos").style.display = "block";
	                			    document.getElementById("entidadBancaria").style.display = "none";	
	                				document.getElementById("comunidadAutonomaFN").style.display="none";
	                				document.getElementById("comunidadAutonomaDD").style.display="none";
	                				document.getElementById("numTituloFN").style.display="none";
	                			    if(document.getElementById("isVisibleEntidadBancaria")){
	                			    document.getElementById("isVisibleEntidadBancaria").value = "false";
	                			    }
	                		      break; 
	                		  <%-- 	Se ha seleccionado el tipo de pago: Efectivo  --%>    
	                		   case "4":
	                			    document.getElementById("nrcPago").style.display = "none";
	                			    document.getElementById("motivos").style.display = "block";
	                			    document.getElementById("motivosCompletos").style.display = "none";
	                			    document.getElementById("entidadBancaria").style.display = "none";	
	                				document.getElementById("comunidadAutonomaFN").style.display="none";
	                				document.getElementById("comunidadAutonomaDD").style.display="none";
	                				document.getElementById("numTituloFN").style.display="none";
	                			    if(document.getElementById("isVisibleEntidadBancaria")){
	                			    document.getElementById("isVisibleEntidadBancaria").value = "false";
	                			    }
	                		      break;
		                		  <%-- 	Se ha seleccionado el tipo de pago: Ninguno  --%>    
	                		   case "5":
	                			    document.getElementById("nrcPago").style.display = "none";
	                			    document.getElementById("entidadBancaria").style.display = "none";	
	                				document.getElementById("comunidadAutonomaFN").style.display="none";
	                				document.getElementById("comunidadAutonomaDD").style.display="none";
	                				document.getElementById("numTituloFN").style.display="none";
	                			    if(document.getElementById("isVisibleEntidadBancaria")){
	                			    document.getElementById("isVisibleEntidadBancaria").value = "false";
	                			    }
	                		      break;
	                		   <%-- Se ha seleccionado el tipo de pago: Pago con Tarjeta  --%>
	                		   case "1":
	                			    document.getElementById("nrcPago").style.display = "block";
	                			    document.getElementById("motivos").style.display = "block";
	                			    document.getElementById("motivosCompletos").style.display = "none";
	                			    document.getElementById("entidadBancaria").style.display = "block";	
	                				document.getElementById("comunidadAutonomaFN").style.display="none";
	                				document.getElementById("comunidadAutonomaDD").style.display="none";
	                				document.getElementById("numTituloFN").style.display="none";
	                			    if(document.getElementById("isVisibleEntidadBancaria")){
	                			    document.getElementById("isVisibleEntidadBancaria").value = "true";		
	                			    }
	                		      break;
	                		   <%-- Se ha seleccionado el tipo de pago: Pago con Cuenta Bancaria --%>   
	                		   case "2":
	                			    document.getElementById("nrcPago").style.display = "block";
	                			    document.getElementById("motivos").style.display = "block";
	                			    document.getElementById("motivosCompletos").style.display = "none";
	                			    document.getElementById("entidadBancaria").style.display = "block";	
	                				document.getElementById("comunidadAutonomaFN").style.display="none";
	                				document.getElementById("comunidadAutonomaDD").style.display="none";
	                				document.getElementById("numTituloFN").style.display="none";
	                			    if(document.getElementById("isVisibleEntidadBancaria")){
	                			    	document.getElementById("isVisibleEntidadBancaria").value = "true";	
	                			    }
	                		      break;   
	                		} 
	                		
	                		if(valor=="borrar"){
	                    		document.getElementById("comunidadFN").value="";
	                    		document.getElementById("comunidadDD").value="";
	                    		document.getElementById("numeroTituloFN").value="";
	                    		document.getElementById("id_motivosEx").value="";
	                    		document.getElementById("id_motivosRed").value="";
	                    		document.getElementById("idnrcPago").value="";
	                    		document.getElementById("idEntidadBancaria").value="";
	                		}
	                    }
	                    
	                    function motivoChecked(ctrl){
	                    	
	                    	// Si el motivo de reducción o exención es familia numerosa habilitamos el combo de comunidades autónomas 
	                    	if(ctrl.value=="3" || ctrl.value=="5"){
	                    		document.getElementById("comunidadAutonomaFN").style.display="block";
	                    		document.getElementById("comunidadAutonomaDD").style.display="none";
	                    		document.getElementById("numTituloFN").style.display="none";
	                    		//document.getElementById("numeroTituloFN").value="";
	                    		document.getElementById("comunidadDD").value="";
	                    	// Si el motivo de exención es discapacidad habilitamos el combo de comunidades autónomas 
	                    	}else if(ctrl.value=="1"){
	                    			document.getElementById("comunidadAutonomaFN").style.display="none";
	                    			document.getElementById("comunidadAutonomaDD").style.display="block";
	                    			document.getElementById("numTituloFN").style.display="none";
	                    			document.getElementById("comunidadFN").value="";
	                    			document.getElementById("numeroTituloFN").value="";
	                    	}else{
	                    	// Si es cualquier otro motivo no habilitamos los combos
	                    		document.getElementById("comunidadAutonomaFN").style.display="none";
	                    		document.getElementById("comunidadAutonomaDD").style.display="none";
	                    		document.getElementById("numTituloFN").style.display="none";
	                    		document.getElementById("comunidadFN").value="";
	                    		document.getElementById("comunidadDD").value="";
	                    		document.getElementById("numeroTituloFN").value="";

	                    	}
	                    	
	                    }
	                    
	                    function comunidadFNChecked(){
	                    	
	                    	document.getElementById("numTituloFN").style.display = "none";
	                    	//document.getElementById("numeroTituloFN").value="";
	                    	numTituloRequerido();
	                    }
	                    	
	                    function numTituloRequerido(){
	                    	var listaComunidadesReqTitulo = document.getElementById("comunidadesReqTitulo").value;
	                    	var encontrado = false;
	                    	var i=0;
	                    	//Comprobamos si la comunidad autónoma seleccionada cuando es exento 
	                    	if(document.getElementById("comunidadAutonomaFN").style.display == "block"){
	                    		var comunidadSelect= document.getElementById("comunidadFN").value;
	                    		var comunidadesRequierenTitulo = listaComunidadesReqTitulo.split('##');
	                    		
	                    		
	                    		while(i<=comunidadesRequierenTitulo.length && encontrado==false){
	                    			
	                    			if(comunidadesRequierenTitulo[i]!=""){
	                    			//Comprobamos si la comunidad autónoma seleccionada requiere que se inserte en número de título de FN 
	                    				if(comunidadSelect==comunidadesRequierenTitulo[i]){	
	                    					document.getElementById("numTituloFN").style.display = "block";
	                    					encontrado=true;
	                    				}else{
	                    					i++;
	                    				}
	                    			}else{
	                    				i++;
	                    			}
	                    		}
	                    	}
	                    	document.getElementById("requiereTitulo").value=encontrado;
	                    	return encontrado;		
	                    }


				var contadorFicheros = 0;
				var numFicheros = 0;
	      		jQuery("#agregarFichero").click(function(){
              	if(numFicheros < 10){
                	++numFicheros;
                  	var fichero = contadorFicheros;
                          
                   	var nombre = $("#nombreDocumento").val();
					var descripcion = $("#descripcionDocumento").val();
					var tipoDocumento = $("#idTipoDocumento").val();
					var file = $("#file");

					if(nombre == null || nombre == '' || descripcion == null || descripcion == '' || tipoDocumento == null || tipoDocumento == '' || file == null){
						alert("Debe rellenar los campos obligatorios");
						return false;
					}
				
					var itm = document.getElementById("file");
					var cln = itm.cloneNode(true);
					cln.setAttribute("id", "fichero"+fichero);
					cln.setAttribute("name", "documentoSolicitudPresencialBean["+fichero+"].file");
					cln.setAttribute("style", "display:none;");
					document.getElementById("documentosAnadidos").appendChild(cln);

					// Se obtiene la descripcion del tipo de documento marcado
					var tipoDocumentoDescripcion = '';
					var listDocumento = $("#idTipoDocumento")[0];
					var j = 0;
					for(j ; j<listDocumento.length ; j++){
						if(listDocumento[j].value == tipoDocumento){
							tipoDocumentoDescripcion = listDocumento[j].innerHTML;
						}
					}
					
					
					// Nombre
					jQuery("#datosFichero").append("<div id='fichero"+fichero+"'> " + nombre + " : " + descripcion + " : " + tipoDocumentoDescripcion + " <a href='javascript:eliminarFichero(fichero"+fichero+")' title='Eliminar'><font color='#c33400'>Eliminar</font></a> <input type='hidden' name='documentoSolicitudPresencialBean["+fichero+"].nombre' value='"+ nombre + "' /><input type='hidden' name='documentoSolicitudPresencialBean["+fichero+"].descripcion' value='"+ descripcion + "' /><input type='hidden' name='documentoSolicitudPresencialBean["+fichero+"].idTipoDocumento' value='"+ tipoDocumento + "' /></div><br>");
					
				 	jQuery("#numFicheros").val(numFicheros);
				 	
				 	file.val('');
				 	$("#nombreDocumento").val('');
				 	$("#descripcionDocumento").val('');
				 	++contadorFicheros;
                  	return false;
              	} else {
                    return false;
              	}
			});

	      		function eliminarFichero(indice){
	      			jQuery(indice).remove();
	                numFicheros--;
	                jQuery("#numFicheros").val(numFicheros);
				}
					
          	
   </script>
   
   <form:hidden path="numFicheros" /> 
</form:form>
<!--</div>-->
</body>
</html>
