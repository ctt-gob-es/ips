<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="es.map.ipsg.bean.*" %>

<%
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
%>

<script type="text/javascript">

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
	document.forms[1].submit();	
	//window.location.href = "../spring/cargarDatosSolicitudPresencial?idConv="+document.getElementById("idConvocatoria").value;
	
	
}

function comprobarBusqueda()
{
	document.getElementById("cambios").value="Incorrecto";
}
function guardar()
{
	var resultado = validaDatos();
	if(resultado == true){
		document.getElementById("accion").value="MODIFICAR";
		return true;
	}else{
		return false;
	}
	alert("se va a hacer el submit"+document.forms[0].ObNif+document.forms[0].ObNombre);
	document.forms[0].submit();

}
function registrarREC()
{
	var resultado = validaDatos();
	if(resultado == true){
		document.getElementById("accion").value="REGISTRARREC";
		ventanaPopup = window.open("../spring/redireccionadorModificacionRegistroSpring?numeroSolicitud="+numeroSolicitud.value +"&llamada=ModificacionRegistroREC", 'miventana', 'width=550, height=400,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
		document.forms[1].action = "../spring/verCrearSolicitudPresencial";
		return true;
	}else{
		return false;
	}
	alert("se va a hacer el submit"+document.forms[0].ObNif+document.forms[0].ObNombre);
	document.forms[0].submit();

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
	if (!nie){
		return false;
	} 
		
	if(nie.length == 9){ 
		nie=nie.toUpperCase(); 
		
		if(/[XYZ][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nie)){ 
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET'; 
			var posicion_letra = 0;
			
      		if((nie).charAt(0)=='X'){
        		posicion_letra = nie.substring(1,8) % 23; 
     		}else if((nie).charAt(0)=='Y'){
       			nie = nie.replace('Y',1);
        		posicion_letra = nie.substring(0,8) % 23; 
      		}else if((nie).charAt(0)=='Z'){
       			nie = nie.replace('Z',2);
        		posicion_letra = nie.substring(0,8) % 23; 
      		}
			
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
		if(letranie == "X" || letranie == "Y" || letranie == "Z"){
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

function cancelar()
{
	document.getElementById("accion").value="VOLVER";
	document.forms[1].action = "../spring/buscarSolicitudPresencial?llamada=Buscar&menu=S"; //Va a busqueda de solicitudes
	
}

function openWindowConvocatoria() {
	var param = "idConvocatoria";
	var param2 = "dsConvocatoria";
	ventanaPopup = window.open("../spring/listarConvocatoria?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
}

function limpiar(importem){
	document.getElementById("importe").value=importem;
}

function quitarProvinciaOb(){
	if($("#idPais").val() != "1"){
		$("#oblProvincia").attr("style","display:none");
	}else{
		$("#oblProvincia").removeAttr("style");
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
<body style="margin-left: 0.4em;" onload="cargaCamposObligatorios();comprobarDatosCargados();">
<!--<div style="margin-left: 1em; margin-right: 1em; float: left; width: 95%;"-->

<form:form modelAttribute="altaSolicitudPresencialForm" action="/IPSG/spring/modificarSolicitudPresencial" id="formPadre" method="post">

<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="requiereTitulo" id="requiereTitulo" value="false" />
	<input type="hidden" id="comunidadesReqTitulo" value="${comunidadesReqTitulo}" >

	 
	<!--    <form:hidden path="submit" id="submit"/>-->
	<form:hidden path="menu" id="menu" value="N"/>
	<form:hidden path="codigoPostal" />

	<h1 class="pae-title"><spring:message code="field.solicitudPresencial.tituloModificacion" /></h1>
	
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
									<spring:message code="field.solicitudPresencial.convocatoria.mayus"/>
								</span>								
								<form:input path="idConvocatoria" id="idConvocatoria" class="pae-form__input"  maxlength="5" readonly="true"  onfocus="comprobarBusqueda();cargaDatos();"></form:input>
								<form:input path="dsConvocatoria" id="dsConvocatoria" class="input_texto_border0 em100" readonly="true"></form:input>
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
		<!-- ****************************** -->
		<!-- DATOS DE USUARIO -->
		
		<br><br>
		<b><spring:message code="field.solicitudPresencial.datosDeUsuario" /></b>
		<hr>
		<br>	
		
			<!-- Ini Nif -->
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.nifnie.mayus"/>
							<c:if test="${visibilidadNif eq 'block' }" >
								<span class="obligatorio"> *</span>
							</c:if>	
						</span>
						<form:input type="text" path="nif" class="pae-form__input" maxlength="10"/>
					</div>
				</div>
 			<!-- Fin Nif -->
			<!-- Ini Nombre -->
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.solicitudPresencial.nombre.mayus"/>
						<c:if test="${visibilidadNombre eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:input type="text" path="nombre" maxlength="50" class="pae-form__input" />
				</div>
			</div>
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.primerApellido.mayus"/>
						<c:if test="${visibilidadPrimerApellido eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:input type="text" path="primerApellido" id="primerApellido" maxlength="50" class="pae-form__input"/>
				</div>
			</div>
		<!-- Fin PrimerApellido-->
		<!-- Ini Segundo Apellido -->
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label pae-ancho3">
						<spring:message code="field.solicitudPresencial.segundoApellido.mayus"/>
						<c:if test="${visibilidadSegundoApellido eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:input type="text" path="segundoApellido" id="segundoApellido" maxlength="50" class="pae-form__input" />
				</div>
			</div>
		</div>
 		<!-- Fin Segundo Apellido -->		
		<!-- Ini Fecha de Nacimiento -->
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label pae-ancho3">
						<spring:message code="field.solicitudPresencial.fechaNacimiento.mayus"/>
						<c:if test="${visibilidadFechaNacimiento eq 'block' }" >
							<span class="obligatorio">*</span>
						</c:if>
					</span>
					<form:input type="text" path="fechaNacimiento" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input"  maxlength="10"/>				
<!-- 				<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaNacimiento'), 'dd/mm/yyyy',1);"> -->
<!-- 				<script type="text/javascript"> -->
<%-- 							fncCalendario('fechaNacimiento', '<%=request.getContextPath()%>'); --%>
<!-- 					</script> -->
				</div>
			</div>
 		<!-- Fin Fecha de Nacimiento -->
		<!-- Ini Nacionalidad -->
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label pae-ancho3">			
						<spring:message code="field.solicitudPresencial.nacionalidad.mayus"/>
						<c:if test="${visibilidadNacionalidad eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:input type="text" path="nacionalidad"  id="nacionalidad" maxlength="50" class="pae-form__input" />
				</div>			
			</div>
		<!-- Ini Correo Electrónico -->
			<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.solicitudPresencial.correoElectronico.mayus"/>
						<c:if test="${visibilidadCorreoElectronico eq 'block' }" >
							<span class="obligatorio">*</span>
						</c:if>
					</span>
					<form:input type="text" path="email"  id="email" maxlength="50" class="pae-form__input" />			
				</div>
			</div>			
		</div>
		<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm ">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.consentimiento.mayus"/>
					</span>
					<form:select path="ckConsentimiento" class="pae-form__input" id="ckConsentimiento">
						<form:option value=""></form:option>
						<form:option value="true"><spring:message code="field.siMayus"/></form:option>
						<form:option value="false"><spring:message code="field.noMayus"/></form:option>
					</form:select> 
				</div>
			</div>
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.solicitudPresencial.sexo.mayus"/>
						<c:if test="${visibilidadNif eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
						<%if (visibilidadSexo.equals("block")){%> <%}%>
					</span>
					<form:select path="sexo" class="pae-form__input" id="sexo">
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
		<br><br>
		
		<!-- ****************************** -->	
		<!-- DATOS DE DOMICILIO -->
		<b><spring:message code="field.solicitudPresencial.datosDeDomicilio" /></b>
		<hr>
		<br>		
		<!-- Ini Calle -->
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-8/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.solicitudPresencial.domicilio.mayus"/>
						<c:if test="${visibilidadVia eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:input type="text" path="calleDomicilio" id="calleDomicilio" maxlength="200" class="pae-form__input" />
				</div>
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.municipio.mayus"/>
						<c:if test="${visibilidadMunicipio eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:input type="text" path="municipioDomicilio" id="municipioDomicilio" maxlength="50" class="pae-form__input" />
				</div>
			</div>		
 		<!-- Fin Municipio-->
 		</div>
 		<div class="pae-layout">
 		<!-- Ini Teléfono-->
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.telefono.mayus"/>
						<c:if test="${visibilidadTelefono eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
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
						<spring:message code="field.solicitudPresencial.pais.mayus"/>
						<c:if test="${visibilidadPais eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:select path="idPais" class="pae-form__input" id="idPais" onchange="quitarProvinciaOb();">
						<form:option value=""></form:option>
						<logic:present name="paises" scope="request">
							<bean:size id="numPais" name="paises" scope="request"/>
								<logic:greaterThan name="numPais" value="0">
									<form:options items="${paises}" path="id" itemLabel="descripcion" itemValue="id"/>
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
						<spring:message code="field.solicitudPresencial.provincia.mayus"/>
						<c:if test="${visibilidadProvincia eq 'block' }" >
							<span class="obligatorio" id="oblProvincia"> *</span>
						</c:if>
					</span>
					<form:select path="idProvinciaDomicilio" class="pae-form__input" id="idProvinciaDomicilio" >
						<form:option value=""></form:option>
						<logic:present name="provincias" scope="request">
							<bean:size id="numProvincia" name="provincias" scope="request"/>
								<logic:greaterThan name="numProvincia" value="0">
									<form:options items="${provincias}" path="id" itemLabel="descripcion" itemValue="id"/>
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>
		</div>
		<!-- Fin Provincia Domicilio-->
		
		<!-- ****************************** -->

		<!-- DATOS DE SOLICITUD -->
		<br><br>
		<b><spring:message code="field.solicitudPresencial.datosDeSolicitud" /></b>
		<hr>
		<br>	
		
		<!-- ****************************** -->
		
		<!-- Ini Justificante -->
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.justificante.mayus"/><span class="obligatorio"> *</span>
					</span>
					<form:input type="text" path="numeroSolicitud" id="numeroSolicitud" maxlength="20" class="pae-form__input"/>
				</div>
			</div>		
 		<!-- Fin Justificante -->		
		<!-- Ini Especialidad -->
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.especialidad.mayus"/>
						<c:if test="${visibilidadEspecialidad eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:select path="idEspecialidad" class="pae-form__input" id="idEspecialidad" >
						<form:option value=""></form:option>
						<logic:present name="especialidades" scope="request">
							<bean:size id="numEspecialidad" name="especialidades" scope="request"/>
								<logic:greaterThan name="numEspecialidad" value="0">
									<form:options items="${especialidades}" path="id" itemLabel="descripcion" itemValue="id"/>
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>			
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.provinciaExamen.mayus"/>
						<c:if test="${visibilidadProvinciaExamen eq 'block' }" >
							<span class="obligatorio">*</span>
						</c:if>
					</span>
					<form:select path="idProvinciaExamen" class="pae-form__input" id="idProvinciaExamen" >
						<option value=""></option>
						<logic:present name="provinciasExamen" scope="request">
							<bean:size id="numProvincia" name="provinciasExamen" scope="request"/>
							<logic:greaterThan name="numProvincia" value="0">
								<form:options items="${provinciasExamen}" path="id" itemLabel="descripcion" itemValue="id"/>
							</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>
		<!-- Fin Provincia de Examen-->
	</div>
	<div class="pae-layout">		
		<!-- ****************************** -->
		<!-- Ini Fecha de Solicitud -->
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label pae-ancho3">
						<spring:message code="field.solicitudPresencial.fechaSolicitud.mayus"/>
					</span>
					<form:input type="text" path="fechaSolicitud" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>					
<!-- 				<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaSolicitud'), 'dd/mm/yyyy',2);"> -->
<!-- 					<script type="text/javascript"> -->
<%-- 							fncCalendario('fechaSolicitud', '<%=request.getContextPath()%>'); --%>
<!-- 					</script> -->
				</div>
			</div>
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label pae-ancho3">
						<spring:message code="field.solicitudPresencial.discapacidad.mayus"/>
						<c:if test="${visibilidadTipoDiscapacidad eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>			
					<form:select path="idTipoDiscapacidad" class="pae-form__input" id="idTipoDiscapacidad" >
						<form:option value=""></form:option>
						<logic:present name="tipoDiscapacidades" scope="request">
							<bean:size id="numDiscapacidad" name="tipoDiscapacidades" scope="request"/>
								<logic:greaterThan name="numDiscapacidad" value="0">
									<form:options items="${tipoDiscapacidades}" path="id" itemLabel="descripcion" itemValue="id"/>
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>
 		<!-- Fin Discapacidad -->
		<!-- Ini % Discapacidad -->
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label pae-ancho3">
						<spring:message code="field.solicitudPresencial.porcentajeDiscapacidad.mayus"/>
						<c:if test="${visibilidadPorcentajeDiscapacidad eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:input type="text" path="porcentajeDiscapacidad"  id="porcentajeDiscapacidad" maxlength="3" class="pae-form__input"/>
				</div>
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
				<div class="pae-form__cell">			
					<c:choose>
					    <c:when test="${altaSolicitudPresencialForm.ckReservaDiscapacidad == true }">
					        <input type="checkbox" name="ckReservaDiscapacidad" property="ckReservaDiscapacidad" id="ckReservaDiscapacidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="checked">
					    </c:when>    
					    <c:otherwise>
							<input type="checkbox" name="ckReservaDiscapacidad" property="ckReservaDiscapacidad" id="ckReservaDiscapacidad" data-function="checkbox-custom-input" class="pae-form__custom-check">
					    </c:otherwise>
					</c:choose>	
					<label for="ckReservaDiscapacidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.reservaDiscapacidad.mayus"/> 
							<c:if test="${visibilidadReservaDiscapacidad eq 'block' }" >
								<span class="obligatorio"> *</span>
							</c:if>
						</span>
					</label>												
				</div>
			</div>
			<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.solicitudPresencial.adaptacionPorDiscapacidad.mayus"/>
						<c:if test="${visibilidadDetalleDiscapacidad eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:textarea path="adaptacionDiscapacidad" class="pae-form__input" id="adaptacionDiscapacidad" rows="5" cols="140" maxlength="1700"/> 
				</div>
			</div>
		</div>
		<!-- Fin Adaptación de Plaza por Discapacidad-->
		
		<!-- ****************************** -->
		
		<!-- ****************************** -->

		<!-- DATOS DE TÍTULOS -->
		<br><br>
		<b><spring:message code="field.solicitudPresencial.datosDeTitulos" /></b>
		<hr>
		<br>
		
		<!-- ****************************** -->
		<!-- Ini Título -->
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm w66">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.titulo.mayus"/>
						<c:if test="${visibilidadTitulosExigidos eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:select path="idTitulo" class="pae-form__input" id="idTitulo"  style="width: 600">
						<form:option value=""></form:option>
						<logic:present name="titulos" scope="request">
							<bean:size id="numTitulo" name="titulos" scope="request"/>
								<logic:greaterThan name="numTitulo" value="0">
									<form:options items="${titulos}" path="id" itemLabel="descripcion" itemValue="id"/>
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
						<spring:message code="field.solicitudPresencial.otrosTitulo.mayus"/>
						<c:if test="${visibilidadOtrosTitulos eq 'block' }" >
							<span class="obligatorio"> *</span>
						</c:if>
					</span>
					<form:textarea path="otrosTitulos" class="pae-form__input" id="otrosTitulos" rows="2" cols="140" maxlength="1700"/>
				</div>
			</div> 
		</div>
 		<!-- Fin Otros Títulos -->
		<!-- ****************************** -->		
		<!-- ****************************** -->
				
		<!-- DATOS A CONSIGNAR -->
		<br><br>
				<b><spring:message code="field.solicitudPresencial.datosAConsignar" /></b>
		<hr>
		<br>	
		<logic:present name="listaCamposPropiosBean" scope="request">
			<logic:iterate id="listaCamposPropiosBean" name="listaCamposPropiosBean" indexId="counter">
				<div class="labelIzqCentroGestor">
					<bean:write name="listaCamposPropiosBean" property="campo" />
				</div>
				<logic:present name="listaSolicitudPropiosBean" scope="request">
					<textarea name="listaTextAreas[${counter}].campo" class="pae-form__input" rows="2" cols="140" maxlength="1700">${listaSolicitudPropiosBean[counter].valor }</textarea>
					<input type="hidden" name="listaTextAreas[${counter}].id" value="${listaCamposPropiosBean.id}"/>
					<input type="hidden" name="listaTextAreas[${counter}].idModelo" value="${listaCamposPropiosBean.idModelo}"/>
				</logic:present>		
				<br><br>
				<div class="clear"></div>
			</logic:iterate>
		</logic:present>
				
		<!-- DATOS DE PAGO -->
		<br><br>
				<b><spring:message code="field.solicitudPresencial.datosDePago" /></b>
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
						<form:option value=""></form:option>
						<logic:present name="tipoPagos" scope="request">
							<bean:size id="numTipoPago" name="tipoPagos" scope="request"/>
								<logic:greaterThan name="numTipoPago" value="0">
									<form:options items="${tipoPagos}" path="id" itemLabel="descripcion" itemValue="id"/>
								</logic:greaterThan>
						</logic:present>
					</form:select>
				</div>
			</div>
 		<!-- Fin  Tipo de Pago-->
		<!-- ****************************** -->
		<!-- Ini Motivo reduccion-->
			
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
						<div id="motivos" style="display: block;">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudPresencial.motivosReduccion"/>
							</span>
							<form:select path="idMotivosRed" class="pae-form__input" id="id_motivosRed" onchange="motivoChecked(this)">
								<form:option value=""></form:option>
								<logic:present name="motivos" scope="request">
									<bean:size id="numTotal" name="motivos" scope="request"/>
										<logic:greaterThan name="numTotal" value="0">
											<form:options items="${motivos}" path="id" itemLabel="descripcion" itemValue="id"/>
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
					
						<form:select path="idMotivosEx" class="pae-form__input" id="id_motivosEx" onchange="limpiar(0);motivoChecked(this)">
							<form:option value=""></form:option>
							<logic:present name="motivosCompletos" scope="request">
								<bean:size id="numTotalMoEx" name="motivosCompletos" scope="request"/>
									<logic:greaterThan name="numTotalMoEx" value="0">
										<form:options items="${motivosCompletos}" path="idMotivoEx" itemLabel="descripcionMoEx" itemValue="idMotivoEx"/>
									</logic:greaterThan>
							</logic:present>
						</form:select>
					</div>		
					<!-- Fin Motivo exencion-->
				</div>	
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.fechaPago.mayus"/>
					</span>	
					<form:input type="text" path="fechaPago" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>
<!-- 				<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaPago'), 'dd/mm/yyyy',2);"> -->
<!-- <script type="text/javascript"> -->
<%-- 							fncCalendario('fechaPago', '<%=request.getContextPath()%>'); --%>
<!-- 					</script> -->
				</div>
			</div>
 		<!-- Fin Fecha de Pago -->
		</div>
		<div class="pae-layout">
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
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.importe.mayus"/>
					</span>
					<form:input type="text" path="importe" id="importe" maxlength="6" class="pae-form__input" />
			
				</div>
			</div>
		</div>
		
		<!-- Fin Importe -->
		<!-- ****************************** -->
		
		<!-- Ini Entidad Bancaria -->
		<div class="pae-layout">
			<div id="entidadBancaria" style="display: block">
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudPresencial.entidadBancaria.mayus"/>
						</span>
						<form:select path="idEntidadBancaria" class="pae-form__input" id="idEntidadBancaria" >
							<form:option value=""></form:option>
							<logic:present name="entidadesBancarias" scope="request">
								<bean:size id="numEntidad" name="entidadesBancarias" scope="request"/>
									<logic:greaterThan name="numEntidad" value="0">
										<form:options items="${entidadesBancarias}" path="id" itemLabel="descripcion" itemValue="id"/>
									</logic:greaterThan>
							</logic:present>
						</form:select>						
					</div>			
				</div>
			</div>
		
 		<!-- Fin Entidad Bancaria -->
		<!-- Ini NRC Pago -->		
				<div id="nrcPago" style="display: block;">
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudPresencial.nrcPago.mayus"/>
							</span>
							<form:input type="text" path="nrcPago"  id="idnrcPago" maxlength="22" class="pae-form__input"/>			
						</div>
					</div>
				</div>
			</div>
		<!-- ****************************** -->
		
		<!-- ****************************** -->
		
		<!-- DATOS DE REGISTRO -->
		<br><br>
				<b><spring:message code="field.solicitudPresencial.datosDeRegistro" /></b>
		<hr>
		<br>
		<!-- Ini Número de Registro-->
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.numRegistro.mayus"/>
					</span>
					<form:input type="text" path="numeroRegistro" id="numeroRegistro" maxlength="20" class="pae-form__input" />
				</div>
			</div>
 		<!-- Fin Número de Registro-->
 		<!-- Ini Oficina de Registro -->
 			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.oficinaRegistro.mayus"/>
					</span>
					<form:input type="text" path="oficinaRegistro" id="oficinaRegistro" maxlength="20" class="pae-form__input" />
				</div>
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.solicitudPresencial.fechaRegistro.mayus"/><span class="obligatorio"> *</span>
					</span>
					<form:input type="text" path="fechaRegistro" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>					
<!-- 				<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaRegistro'),'dd/mm/yyyy',2);"> -->
<!-- <script type="text/javascript"> -->
<%-- 							fncCalendario('fechaRegistro', '<%=request.getContextPath()%>'); --%>
<!-- 					</script> -->
				</div>
			</div>
		</div>
 		<!-- Fin Fecha de Registro -->
		<!-- ****************************** -->
			
				<div class="filaBtn">
					<div class="pae-box-buttons pd-right-1">
							<input type="submit" value="Registrar REC" onclick="return registrarREC()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
							<input type="submit" value="Modificar" onclick="return guardar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
							<input type="submit" name="accion" value="Cancelar" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>													
					</div>
				</div>		
		</div>
		</fieldset>
		</div>
		</div>
		
		<div class="clear"></div>
		<form:hidden path="id"/>
		<form:hidden path="idPagoSolicitud"/>
		<form:hidden path="idRegistroSolicitud"/>
		<br>
		<br>
		
			<script type="text/javascript">
			
			var importe=document.getElementById("importe").value;
			var oficinaRegistro=document.getElementById("oficinaRegistro").value;
			var inicio ='1';
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
            	if(document.getElementById("error")==null && inicio==0){
            		document.getElementById("id_motivosRed").value="";
            		document.getElementById("id_motivosEx").value="";
            		limpiar(importe);	
            	}	
            	inicio=0;
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
            	// Si el motivo de exención es discapacidad habilitamos el combo de comunidades autónomas 
            	}else if(ctrl.value=="1"){
            			document.getElementById("comunidadAutonomaFN").style.display="none";
            			document.getElementById("comunidadAutonomaDD").style.display="block";
            			document.getElementById("numTituloFN").style.display="none";
            	}else{
            	// Si es cualquier otro motivo no habilitamos los combos
            		document.getElementById("comunidadAutonomaFN").style.display="none";
            		document.getElementById("comunidadAutonomaDD").style.display="none";
            		document.getElementById("numTituloFN").style.display="none";

            	}
            	
            }
            
            function comunidadFNChecked(){
            	
            	document.getElementById("numTituloFN").style.display = "none";
            	document.getElementById("numeroTituloFN").value="";
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
   </script>
	</div>
</form:form>
<!--</div>-->
</body>
</html>