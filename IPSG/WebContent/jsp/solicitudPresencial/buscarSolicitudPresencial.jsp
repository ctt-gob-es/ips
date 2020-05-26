<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
<%@ page import="es.map.ipsg.util.Constantes"%>
<%
String sVieneMenu = (String)request.getAttribute("sVieneMenu"); 
String numPag = (String)request.getAttribute("paginaActual");
String sPerfilUsuario =  request.getAttribute("sPerfilUsuario").toString();
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script>
<script type="text/javascript">

function irPagina(pagTotales)
{
	var actual = document.getElementById("numeroPaginaIr").value;
	var actualInt = parseInt(actual,10);
	
	if(isNaN(actualInt) || actualInt<=0){
		actualInt = 1;
	}
	
	if (document.getElementById("checkTodo").checked) {
		document.getElementById("marcarTodo").value="TodoOk";
	}
	
	document.getElementById("pulsaIr").value = true;	
	document.getElementById("submit").value="Paginar";
	document.getElementById("numeroPaginaIr").value = actualInt;
	document.getElementById("paginaActual").value=actualInt;
	document.getElementById("paginasTotales").value=pagTotales;
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
}

function ordena(value,numPag){
	if(value==1){
		document.getElementById("campo").value = "1";
		document.getElementById("direccion").value="up";
	}
	if(value==2){
		document.getElementById("campo").value ="1";
		document.getElementById("direccion").value="down";
	}
	if(value==3){
		document.getElementById("campo").value ="2";
		document.getElementById("direccion").value="up";
	}
	if(value==4){
		document.getElementById("campo").value ="2";
		document.getElementById("direccion").value="down";
	}
	if(value==5){
		document.getElementById("campo").value ="3";
		document.getElementById("direccion").value="up";
	}
	if(value==6	){
		document.getElementById("campo").value ="3";
		document.getElementById("direccion").value="down";
	}
	if(value==7){
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="up";
	}
	if(value==8){
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="down";
	}
	if(value==9){
		document.getElementById("campo").value ="5";
		document.getElementById("direccion").value="up";
	}
	if(value==10){
		document.getElementById("campo").value ="5";
		document.getElementById("direccion").value="down";
	}
	if(value==11){
		document.getElementById("campo").value ="6";
		document.getElementById("direccion").value="up";
	}
	if(value==12){
		document.getElementById("campo").value ="6";
		document.getElementById("direccion").value="down";
	}
	if(value==13){
		document.getElementById("campo").value ="7";
		document.getElementById("direccion").value="up";
	}
	if(value==14){
		document.getElementById("campo").value ="7";
		document.getElementById("direccion").value="down";
	}

	document.getElementById("paginaActual").value=numPag;
	document.getElementById("submit").value="Ordenar";
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function busqueda(){
	var identificacion = document.getElementById("nif").value;
	if(identificacion != ""){
		result = nifValidate(identificacion);
		if (result == false) {		
			alert(unescape("El NIF es incorrecto."));
			return false;
		}
	}
	
	document.getElementById("paginaActual").value="1";
	document.getElementById("accion").value="BUSCAR";
	document.getElementById("submit").value="Buscar";
	$("#formPadre").attr("action", "../spring/buscarSolicitudPresencial");
	$("#formPadre").trigger("submit");	
}

function validarNif(){
	var identificacion = document.getElementById("nif").value;
	if(identificacion != ""){
		result = nifValidate(identificacion);
		if (result == false) {		
			alert(unescape("El NIF es incorrecto."));
			return false;
		}
	}
}

function nifValidate(nif) {
	if (!nif)
		return false;

	if (nif.length == 9) {
		nif = nif.toUpperCase();
		if (/[0-9]{8}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)) {
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
			var posicion_letra = nif.substring(0, 8) % 23;
			if (nif.charAt(8) != temp.charAt(posicion_letra)) {
				return false;
			}
		} else {
			if (/[KML][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)){
				var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
				var posicion_letra = nif.substring(1, 8) % 23;
				if (nif.charAt(8) != temp.charAt(posicion_letra)) {
					return false;
				}
			} else {
				return false;
			}
		}
	} else {
		return false;
	}
	return true;
}

function paginaActual(paginaActual){
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	document.getElementById("paginaActual").value=paginaActual;
	document.getElementById("submit").value="Paginar";
}

function paginaSiguiente(pag,paginasTotales){
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	var next=pag+1;
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=next;
	document.getElementById("paginasTotales").value=paginasTotales;

	if (document.getElementById("checkTodo").checked) {
		document.getElementById("marcarTodo").value="TodoOk";
	}
}

function paginaAnterior(pag,paginasTotales){
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	var next=pag-1;	
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=next;
	document.getElementById("paginasTotales").value=paginasTotales;

	if (document.getElementById("checkTodo").checked) {
		document.getElementById("marcarTodo").value="TodoOk";
	}
}

function ultimaPagina(paginasTotales){
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=paginasTotales;
	document.getElementById("paginasTotales").value=paginasTotales;
}

function primeraPagina(paginasTotales){
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value="1";
	document.getElementById("paginasTotales").value=paginasTotales;
}

function openWindowCentroGestor() {
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	document.getElementById("cuerpoEscala").value = "";
	document.getElementById("dsCuerpoEscala").value = "";
	var param = "idCentroGestor";
	var param2 = "dsCentroGestor";
	var extract = new Object();
		ventanaPopup = window.open("../spring/listarCentroGestor?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
}

function openWindowCuerpoEscala() {
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	var centro = document.getElementById("idCentroGestor").value;
	if(centro != null && centro != ""){
		var param = "cuerpoEscala";
		var param2 = "dsCuerpoEscala";
		var extract = new Object();
		ventanaPopup = window.open("../spring/listarCuerpoEscala?centro="+centro+"&parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    		
	}else{
		alert("Tiene que seleccionar antes un centro gestor");
	}
	return false;
	
}

function comprobarCuerpoEscala(){
	var centro = document.getElementById("idCentroGestor").value;
	if(centro == null || centro == ""){
		alert("Tiene que seleccionar antes un centro gestor");
		document.getElementById("cuerpoEscala").value = "";
	}
}
function comprobarBusqueda(){
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	document.getElementById("cambios").value="Incorrecto";
}

//Al cambiar por el número de Registros por página  
function comprobarRegistros(){
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	document.getElementById("submit").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	var marcar = document.getElementById("marcarTodo").value;
	window.location.href = "../spring/buscarSolicitudPresencial?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&numRegistro="+numRegistro+"&marcarTodo="+marcar; 
}

function cambiarCombo ()
{
	document.getElementById("paginaActual").value="1";
}

function comprobarCheckMarcado()
{
	document.forms[1].action = "../spring/buscarSolicitudPresencial";
	var bSeleccionado = false;
	var formulario = document.forms[1];
	var i;
	for (i=0; i < formulario.length; i++)
    {
		if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
		{	
			if (formulario.elements[i].checked)
			{
				bSeleccionado = true;
			}
		}
	}
	return bSeleccionado;
}
function exportarVerificar(opcion)
{
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
	//Se comprueba que exista algún check marcado
	if(comprobarCheckMarcado())
	{	
		if(opcion == 'Verificar')
		{	
			document.getElementById("submit").value="Verificar";
			document.forms[1].action = "../spring/verVerificarEdadSolicitantes";
		}
		if(opcion == 'Exportar')
		{
			document.getElementById("submit").value="Exportar";
			document.forms[1].action = "../spring/exportarExcelSolicitudes";
		}
	}else
	{
		alert('<spring:message code="field.solicitudes.mensajeVerificarCheck"/>');
		return false;
	}
	
}
function exportaDocumentos()
{
	if(comprobarCheckMarcado())
	{		
		document.getElementById("submit").value="Exportar";
		ventanaPopup = window.open("../spring/verExportaDocumento", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
		document.forms[0].action = "../spring/buscarSolicitudPresencial";
	}else
	{
		alert('<spring:message code="field.solicitudes.mensajeVerificarCheck"/>');
		return false;
	}
		
}

function openWindowDocumentos(id) {
	var navegador = navigator.appName;
	var extract = new Object();
	var ventanaPopup;
	ventanaPopup = window.open("../spring/documentosSolicitudPresencial?id="+id, 'miventana', 'width=550, height=680,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');		
	ventanaPopup.focus();
}

function deshabilitar()
{

	if (document.getElementById("checkTodo") != null) {
		//Marcar todos los check
		if (document.getElementById("marcarTodo").value == 'TodoOk') {
			document.getElementById("checkTodo").checked = true;
	
			
			var formulario = document.forms[0];
			var i;
			document.getElementById("marcarTodo").value="TodoOk";
			for (i=0; i < formulario.length; i++)
		    {
				if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
				{
					formulario.elements[i].checked = true;
				}			
			}
		}
	
		//desmarcar todos los check
		if (document.getElementById("marcarTodo").value == 'TodoNo') {
			document.getElementById("checkTodo").checked = false;
	
			
			var formul = document.forms[0];
			var j;
			document.getElementById("marcarTodo").value="TodoNo";
			for (j=0; j < formul.length; j++)
		    {
				if (formul.elements[j].type=='checkbox' && formul.elements[j].name =='solicitudesSel')
				{
					formul.elements[j].checked = false;
				}			
			}
		}
	}
}

function exportarExcel()
{
	//Se comprueba que exista algún check marcado
	if(comprobarCheckMarcado())
	{
		if (document.getElementById("marcarTodo").value == 'TodoOk') {
			var pagTotal = document.getElementById("paginasTotales").value;				
			var numRegPagina = document.getElementById("numRegistro").value;				
			var numRegs = pagTotal * numRegPagina;
			
			if (numRegs<=50){
				document.getElementById("submit").value="Exportar";
				document.getElementById("accion").value="ExportarPresenciales";
				document.forms[1].action = "../spring/exportarExcelSolicitudes";
				return true;
			}else if(confirm("Se va a proceder a la exportación de aproximadamente " + numRegs + " registros. \nEsta operación puede tardar un poco. ¿Desea continuar?")) {
				
				document.getElementById("submit").value="Exportar";
				document.getElementById("accion").value="ExportarPresenciales";
				document.forms[1].action = "../spring/exportarExcelSolicitudes";
				return true;
			}
		}else{
			document.getElementById("submit").value="Exportar";
			document.getElementById("accion").value="ExportarPresenciales";
			document.forms[1].action = "../spring/exportarExcelSolicitudes";
			return true;
		}
	}else
	{
		alert('<spring:message code="field.solicitudes.mensajeVerificarCheck"/>');
		return false;
	}
	
}

function asignarForm(){
	alert("cambiar form");
	document.forms[0].action = "../spring/buscarSolicitudPresencial";
}

function marcarCheck(activa)
{
	document.forms[1].action = "../spring/buscarSolicitudPresencial";
	var formulario = document.forms[1];
	var i;
	document.getElementById("marcarTodo").value="TodoOk";
	for (i=0; i < formulario.length; i++)
    {
		if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
		{
			formulario.elements[i].checked = activa;
		}
		if (!activa) {
			document.getElementById("marcarTodo").value="TodoNo";
		}
	}
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function limpiarCentroGestor(){
var desCentro = document.getElementById("dsCentroGestor").value;
	if(desCentro != ""){
		document.getElementById("idCentroGestor").value = "";
		document.getElementById("dsCentroGestor").value = "";
		document.getElementById("cuerpoEscala").value = "";
		document.getElementById("dsCuerpoEscala").value = "";
	}
}

function limpiarCuerpoEscala(){
	document.getElementById("cuerpoEscala").value = "";
	document.getElementById("dsCuerpoEscala").value = "";
}

function resetCheck() {
	document.getElementById("marcarTodo").value="TodoNo";
	document.getElementById("checkTodo").checked = false;
	
}

function marcarCheckPaginacion(pag,paginasTotales) {
	
	if (document.getElementById("checkTodo").checked) {
		document.getElementById("marcarTodo").value="TodoOk";
	}
	
	var marcar = document.getElementById("marcarTodo").value;
	
	window.location.href = "../spring/buscarSolicitudPresencial?paginaActual="+pag +"&paginasTotales="+paginasTotales +"&llamada="+"Paginar" +"&marcarTodo="+marcar;
}
</script>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:0.4em;" onload="deshabilitar();marcarCheck(document.getElementById('checkTodo').checked);">

<!--<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">-->

<form:form commandName="buscarSolicitudesPresencialesForm" action="/IPSG/spring/buscarSolicitudPresencial" id="formPadre" method="post">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="menu" id="menu" value="N"/>
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="perfilUsuario" id="perfilUsuario"/>
	<form:hidden path="marcarTodo" id="marcarTodo"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />




<h1 class="pae-title"><spring:message code="field.solicitudPresencial.solicitudBuscar"/></h1>   
	<logic:present name="org.apache.spring.ERROR" scope="request">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present> 
	 <div data-function="accordion-box" class="pae-box"> 
    	<div class="pae-box__body">   	
			<fieldset>
				<!-- Ini Centro Gestor -->
				<div class="pae-layout">
					<!--INI-TRA042-->	
					<%if( !sPerfilUsuario.equals(Constantes.ROL_GESTOR) && !sPerfilUsuario.equals(Constantes.ROL_OPERADOR)) { %>
					<!--FIN-TRA042-->
						<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.centroMay"/>
								</span>			
									<form:input path="idCentroGestor" class="pae-form__input" id="idCentroGestor" maxlength="5" readonly="true" onchange="comprobarBusqueda()" onclick="limpiarCentroGestor()"></form:input>
									<form:input path="dsCentroGestor" id="dsCentroGestor" class="input_texto_border0 em100" readonly="true"></form:input>
							</div>
						</div>
									
									<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
									<br>
										<input type="button"
												class="pae-form__btn-search" alt="Buscar Centro Gestor"
												onclick="return openWindowCentroGestor()"> 	
									</div>								
								<%}else{ %>
								<!--INI-TRA042-->
								<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">
											<spring:message code="field.centroMay"/>
										</span>	
											<logic:present name="listaCentrosGestores" scope="request">
												<div class="pae-form__cell">
													<form:select path="idCentroGestor" class="pae-form__input">
														<option value=""></option>
														<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
													</form:select>
												</div>
											</logic:present>
								<!--FIN-TRA042-->
										</div>
									</div>
									
								<%}%>
								
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.cuerpoMay"/>
							</span>
							<form:input path="cuerpoEscala" class="pae-form__input" id="cuerpoEscala"  readonly="true" maxlength="5" onchange="comprobarBusqueda(); return comprobarCuerpoEscala()" onclick="limpiarCuerpoEscala()"></form:input>
							<form:input path="dsCuerpoEscala" id="dsCuerpoEscala" class="input_texto_border0 em100" readonly="true"></form:input>
						</div>
					</div>
							<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn2">
								<input type="button"
													class="pae-form__btn-search" alt="Buscar Centro Gestor"
													onclick="return openWindowCuerpoEscala()"> 	
							</div>							
													
				</div>
				<!-- Fin Cuerpo y Escala --> 	
 	
				<!-- Ini Ministerio -->
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">	
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.ministerioMay"/>
								</span>
								<form:select path="idMinisterio" class="pae-form__input" id="idMinisterio" >
										<form:option value=""></form:option>
										<logic:present name="ministerio" scope="request">
											<bean:size id="numMinisterio" name="ministerio" scope="request"/>
												<logic:greaterThan name="ministerio" value="0">
													<form:options items="${ministerio}"  itemLabel="descripcion" itemValue="id"/>
												</logic:greaterThan>
										</logic:present>		
								</form:select>
						</div>
					</div>
 				<!-- Fin Ministerio -->
				<!-- Ini Número Solicitud -->
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.solicitudRegistrada.numeroSolicitud.mayus"/>
						</span>
						<logic:present name="numSolicitud" scope="request"></logic:present>
						<form:input path="numSolicitud" class="pae-form__input" onkeypress="return isNumber(event)" maxlength="20"/>							
					</div>
				</div>
			</div>
				<!-- Fin Número Solicitud -->		
				<!-- Ini  NIF -->
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">	
								<spring:message code="field.solicitudRegistrada.nif.mayus"/>
							</span>
							<form:input id="nif" path="nif" class="pae-form__input" maxlength="9"/>		
						</div>
					</div>				
 				<!-- Fin NIF -->
 				<!-- Ini Modelos -->
 					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">	
 								<spring:message code="field.modeloMay"/>
							</span>
 							<form:select path="idModelo" class="pae-form__input" id="idModelo" >
 								<form:option value=""></form:option>		
 								<logic:present name="listaModelosBean" scope="request">
 									<bean:size id="numModelos" name="listaModelosBean" scope="request"/>
									<logic:greaterThan name="listaModelosBean" value="0">
										<form:options items="${listaModelosBean}" itemLabel="numModelo" itemValue="id"/>
									</logic:greaterThan>
 								</logic:present>
			 				</form:select>
		 				</div>
 					</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho3">	
								<spring:message code="field.solicitudRegistrada.fechaDesde"/>
							</span>
							<form:input type="text" path="fechaDesde" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>								
			<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaDesde'), 'dd/mm/yyyy',2);"> -->
<!-- 							<script type="text/javascript"> -->
<%-- 														fncCalendario('fechaDesde', '<%=request.getContextPath()%>'); --%>
<!-- 												</script> -->
						</div>
					</div>
				<!-- Fin Fecha Desde -->
				<!-- Ini Fecha Hasta -->
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho3">
								<spring:message code="field.solicitudRegistrada.fechaHasta"/>
							</span>	
							<form:input type="text" path="fechaHasta"  data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaHasta" maxlength="10"/>
<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaHasta'), 'dd/mm/yyyy',2);"> -->
<!-- <script type="text/javascript"> -->
<%-- 							fncCalendario('fechaHasta', '<%=request.getContextPath()%>'); --%>
<!-- 					</script> -->
						</div>
				</div>
				<!-- Solicita Exención -->
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudRegistrada.solicitaExcencion.mayus"/>
							</span>
							<form:select path="solExencion" class="pae-form__input" id="solExencion" >
									<form:option value=""></form:option>
									<form:option value="N">No</form:option>		
									<form:option value="S">Sí</form:option>											
							</form:select>
						</div>
					</div>
					<!-- Fin Solicita Exención -->
			</div>
				<!-- Fin Fecha Hasta -->
		<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-89">
						<div class="pae-box-buttons">
							<input type="submit" name="buscar" value="Buscar" onclick="return busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"></input>							
						</div>
					</div>
				</div>
				<div class="clear"></div>				
		</fieldset>	
	</div>
	</div>
		
	<%		
	if (sVieneMenu.equals("N"))
	{
	%>
	<logic:present name="solicitudes" scope="request">	
		<bean:size id="numResultados" name="solicitudes" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<div class="scrollmenu">
			<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla de busqueda de solicitudes presenciales</caption>
			<thead class="pae-table__header">
			
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-85">
						<div class="pae-box-buttons">
							<input type="submit" value="Exportar Excel" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline" id="exportar" onclick="return exportarExcel();"/>							
						</div>				
					</div>
				</div>
				
				<tr class="pae-table__row">
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<input type="checkbox" name="check" property="check" id="checkTodo" data-function="checkbox-custom-input" class="pae-form__custom-check" onclick="marcarCheck(this.checked)">
										<label for="checkTodo" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
									</th>										
								</tr>
							</tbody>
						</table>
					</th>
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.solicitudPresencial.justificante.mayus"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="submit" alt="Submit" 
												onclick="ordena(1,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(2,<%=numPag %>)">
										</div>
									</th>
								</tr>
							</tbody>
						</table>
					</th>
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.solicitudPresencial.nif.mayus"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="submit" alt="Submit" 
												onclick="ordena(3,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(4,<%=numPag %>)">
										</div>
									</th>
								</tr>
							</tbody>
						</table>
					</th>
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.solicitudPresencial.nombre.mayus"/> 
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="submit" alt="Submit" 
												onclick="ordena(5,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(6,<%=numPag %>)">
										</div>
									</th>
								</tr>
							</tbody>
						</table>
					</th>				
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.solicitudPresencial.primerApellido.mayus"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="submit" alt="Submit" 
												onclick="ordena(7,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(8,<%=numPag %>)">
										</div>
									</th>
								</tr>
							</tbody>
						</table>
					</th>
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.solicitudPresencial.segundoApellido.mayus"/> 
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="submit" alt="Submit" 
												onclick="ordena(9,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(10,<%=numPag %>)">
										</div>
									</th>
								</tr>
							</tbody>
						</table>
					</th>				
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.solicitudPresencial.ejercicio.mayus"/> 
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="submit" alt="Submit" 
												onclick="ordena(11,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(12,<%=numPag %>)">
										</div>
									</th>
								</tr>
							</tbody>
						</table>
					</th>
					<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.solicitudPresencial.centroGestor.mayus"/> 
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Submit" 
													onclick="ordena(13,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(14,<%=numPag %>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>
						<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.solicitudPresencial.solicitaExcencion.mayus"/>
												</span>	
											</div>
										</th>										
									</tr>
								</tbody>
							</table>
						</th>
						<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.solicitudPresencial.documentos.mayus"/>
												</span>	
											</div>
										</th>										
									</tr>
								</tbody>
							</table>
						</th>
						<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.solicitudPresencial.acciones"/> 
												</span>	
											</div>
										</th>									
									</tr>
								</tbody>
							</table>
						</th>		
					
											
			</tr> <!-- Resultados de la consulta -->
			</thead>
			<tbody class="pae-table__body">
				<c:forEach var="registro" items="${solicitudes}" varStatus="loop">
					<tr class="pae-table__row" name="row">
						<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               				<input type="checkbox" name="solicitudesSel" onclick="resetCheck()" property="solicitudesSel" id="solicitudesSel${loop.index+1}" value="${registro.id}" data-function="checkbox-custom-input" class="pae-form__custom-check" onclick="marcarCheck(this.checked)">
							<label for="solicitudesSel${loop.index+1}" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
               			</td>					
						 <td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
	               					<span class="pae-table__txt-base pae-table__span-head">${registro.numeroSolicitud}</span>
               			 </td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head">${registro.nif}</span>
               			</td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head">${registro.nombre}</span>
               			</td>
               			<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head">${registro.primerApellido}</span>
               			</td>					
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head">${registro.segundoApellido}</span>
               			</td>
               			<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head">${registro.ejercicio}</span>
               			</td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head">${registro.siglasCentroGestor}</span>
               			</td>
<!--                			<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"> -->
<%--                					<span class="pae-table__txt-base pae-table__span-head">${registro.desSolExencion}</span> --%>
<!--                			</td> -->
					<c:if test="${registro.solExencion eq true }" >	
               			<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<input type="checkbox" id="checkRegEx" value="${registro.solExencion}" data-function="checkbox-custom-input" class="pae-form__custom-check" disabled="true" checked>
							<label for="checkRegEx" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
						</span>
					</td>
					</c:if>
					<c:if test="${registro.solExencion eq false}" >	
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<input type="checkbox" id="checkRegEx" value="${registro.solExencion}" data-function="checkbox-custom-input" class="pae-form__custom-check" disabled="true">
							<label for="checkRegEx" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
						</span>
               			</td>
					</c:if>
               			<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><!-- Enlace: Descarga documentos -->
				    	<a href="#" onclick="openWindowDocumentos(${registro.id});" >
			 		    	<font color="#c33400"><spring:message code="field.solicitudPresencial.descargaDocumentacion.mayus"/></font>
			 		   	</a></span>
               			</td>
               			<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><a href="${pageContext.request.contextPath}/spring/verModificarSolicitudPresencial?id=${registro.id}" style="color: #c33400;" ><spring:message code="field.modificar"/></a> |
						<a href="${pageContext.request.contextPath}/spring/eliminarSolicitudPresencial?registro=${registro.id}" style="color: #c33400;"  onclick="return comprobarEliminar()" ><spring:message code="field.eliminar"/></a></span>
               			</td>			
    				</tr>
	 			</c:forEach>
 			</tbody>
		</table>
		</div>
			
		
			<%
			Object numPat = request.getAttribute("paginasTotales");
			Object pagActual = request.getAttribute("paginaActual");
			int numeroPagina = Integer.parseInt(numPat.toString());
			int paginaActual = Integer.parseInt(pagActual.toString());
			String paginar = "Paginar", enlacePagina;
			%>
			<div class="capaPaginacion" id="cap">
				<span class="pae-form__span-label-regxp">
					<spring:message code="field.numRegistros"/>
				</span>					
						<form:select path="numRegistro" id="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
							<form:option value="10"><spring:message code="field.10Registros"/></form:option>
							<form:option value="20"><spring:message code="field.20Registros"/></form:option>
							<form:option value="50"><spring:message code="field.50Registros"/></form:option>
						</form:select>
					</span>
				
				<div class="pagination">
				<%if(paginaActual != 1) { %>
					<strong><a href="#" class="" onclick="return marcarCheckPaginacion(<%=paginaActual-1%>,<%=numeroPagina %>)">&laquo;</a></strong>
				<% } else{  %>	
			 		<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
				<% }   
			
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					enlacePagina = "buscarSolicitudPresencial?paginaActual="+x +"&paginasTotales="+numeroPagina +"&llamada="+paginar;
					if(x==paginaActual){%>
						<strong><a href="#" class="active" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
					<%}else{%>
						<strong><a href="#" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
					<%}%>
				<%}%>
			<%}
			else
			{
				for(int x = 1 ;x<numeroPagina+1; x++){
					enlacePagina = "buscarSolicitudPresencial?paginaActual="+x +"&paginasTotales="+numeroPagina +"&llamada="+paginar;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<strong><a href="#" class="active" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
						<div><spring:message code="field.puntosPaginacion"/></div>
					<%}else if (x<= 3 || x+3>numeroPagina){
						if(x==paginaActual){%>	
							<strong><a href="#" class="active" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								<div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>						
						<%}
						else
						{%>	
							<strong><a href="#" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								<div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>							
						<%}%>	
					<%}%>
				<%}%>
			<%}%>							
			<%if(paginaActual != numeroPagina) { %>
				<strong><a href="#" class="" onclick="return marcarCheckPaginacion(<%=paginaActual+1%>,<%=numeroPagina %>)">&raquo;</a></strong>
			<% } else{  %>	
			 <a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }  %> 	
			</div>			
			<span class="pae-form__span-label-regxp">
				<spring:message code="field.general.irAPagina"/>
			</span>
			<form:input path="numeroPaginaIr" maxlength="4" class="pae-form__input w6"/>
			
			<div class="pae-box-buttons w10 margin-button-ir">
				<input type="submit" value="Ir" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"  onclick="irPagina(<%=numeroPagina %>)">	
			</div>
						
		</div>	 
				
		<!--  Selección Paginación de Registros -->
		
		</logic:greaterThan>		
	</logic:present>
<%}%>
</form:form>
	
<!--</div>-->
</body>
</html>