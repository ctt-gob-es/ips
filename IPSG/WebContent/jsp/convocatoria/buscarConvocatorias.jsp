<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bean" uri="http://www.mpr.es/ipsg/bean-tags" %>
<%@ taglib prefix="logic" uri="http://www.mpr.es/ipsg/logic-tags" %>
<%@ taglib prefix="html" uri="http://www.mpr.es/ipsg/html-tags" %>

<%@ page import="es.map.ipsg.bean.*"%>
<%@ page import="java.util.*"%>
<%@	page import="es.map.ipsg.util.Constantes"%>

<%
	String numPag = (String) request.getAttribute("paginaActual");
	String perfilUsuario = request.getAttribute("perfilUsuario")
			.toString();
	String mostrar = (String) request.getAttribute("mostrarAlertas");
%>
<script type="text/javascript">
function soloNumeros(evento){
    evento = (evento) ? evento : window.event;
    var charCode = (evento.which) ? evento.which : evento.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
       return false;
    }
    return true;
}

function irPagina(pagTotales)
{
	var actual = document.getElementById("numeroPaginaIr").value;
	var actualInt = parseInt(actual,10);
	
	if(isNaN(actualInt) || actualInt<=0){
		actualInt = 1;
	}
	
	document.getElementById("pulsaIr").value = true;	
	document.getElementById("accion").value="Paginar";
	document.getElementById("numeroPaginaIr").value = actualInt;
	document.getElementById("paginaActual").value=actualInt;
	document.getElementById("paginasTotales").value=pagTotales;
	document.forms[0].action = "../spring/buscarConvocatorias";
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
	if(value==15){
		document.getElementById("campo").value ="8";
		document.getElementById("direccion").value="up";
	}
	if(value==16){
		document.getElementById("campo").value ="8";
		document.getElementById("direccion").value="down";
	}
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("accion").value="Ordenar";
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function busqueda(){
	document.getElementById("accion").value="Buscar";
	document.getElementById("cambios").value="Correcto";
	//desMarcarCheckMarcado();

}

function openWindowCentroGestor() {
	document.getElementById("cuerpoEscala").value = "";
	document.getElementById("dsCuerpoEscala").value = "";
	var param = "centroGestor";
	var param2 = "dsCentroGestor";
	var extract = new Object();
		ventanaPopup = window.open("listarCentroGestor?parametro="+param+"&parametro2="+param2+"&listarTodos=N", 'miventana', 'width=550, height=800,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
}

function comprobar(){
	document.getElementById("cuerpoEscala").value = "";
}

function openWindowCuerpoEscala() {
	var centro = document.getElementById("centroGestor").value;
	if(centro != null && centro != ""){
		var param = "cuerpoEscala";
		var param2 = "dsCuerpoEscala";
		var extract = new Object();
		ventanaPopup = window.open("listarCuerpoEscala?centro="+centro+"&parametro="+param+"&parametro2="+param2+"&listarTodos=N", 'miventana', 'width=550, height=800,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
		ventanaPopup.focus();
		return false;
	//document.getElementById("accion").value="alta";
	}else{
		alert("Tiene que seleccionar antes un centro gestor");
	}
}

function comprobarCuerpoEscala(){
	var centro = document.getElementById("centroGestor").value;
	if(centro == null || centro == ""){
		alert("Tiene que seleccionar antes un centro gestor");
		document.getElementById("cuerpoEscala").value = "";
	}
}

function paginaActual(paginaActual,paginasTotales){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=paginaActual;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=paginaActual;
	}
}

function paginaSiguiente(pag,paginasTotales){
	var next=pag+1;
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=next;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.getElementById("cambios").value = "Correcto";
	}
}

function paginaAnterior(pag,paginasTotales){
	var next=pag-1;
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){		
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=next;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.getElementById("cambios").value = "Correcto";
	}
}

function ultimaPagina(paginasTotales,pag){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=paginasTotales;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.getElementById("cambios").value = "Correcto";
	}
}

function primeraPagina(paginasTotales,pag){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value="1";
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.getElementById("cambios").value = "Correcto";
	}
}

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}

function comprobarRegistros(){
	document.getElementById("accion").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarConvocatorias?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
}

function marcarCheck(activa)
{
	var formulario = document.forms[1];
	var i;
	for (i=0; i < formulario.length; i++)
    {
		if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='convocatoriasSel')
		{
			formulario.elements[i].checked = activa;
		}
	}
}

function comprobarCheckMarcado()
{
	var bSeleccionado = false;
	var formulario = document.forms[1];
	for (i=0; i < formulario.length; i++)
    {
		if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='convocatoriasSel')
		{	
			if (formulario.elements[i].checked)
			{
				bSeleccionado = true;
			}
		}
	}
	return bSeleccionado;
}

function desMarcarCheckMarcado()
{
	var bSeleccionado = false;
	var formulario = document.forms[0];
	for (i=0; i < formulario.length; i++)
    {
		if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='convocatoriasSel')
		{	
			if (formulario.elements[i].checked)
			{
				formulario.elements[i].notchecked;
			}
		}
	}
	return bSeleccionado;
}

function aprobarConvocatorias()
{
	if(comprobarCheckMarcado()){			
		
		var parametros = "";
		var formulario = document.forms[1];
		var contChecked = 0;

		for (var i=0; i < formulario.length; i++){
			if(formulario.elements[i].type=='checkbox' && formulario.elements[i].name=='convocatoriasSel'){
				if (formulario.elements[i].checked)
				{
					if(contChecked == 0){
						parametros+="?conv="+formulario.elements[i].value;
					}else{
						parametros+="&conv="+formulario.elements[i].value;
					}
					contChecked++;
				}
			}
		}

		document.getElementById("accion").value="Aprobar Convocatorias";
		//document.forms[0].submit();	
		ventanaPopup = window.open("../spring/aprobarConvocatoriaSeleccionada"+parametros,'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
			
	}else
	{
		alert('<spring:message code="field.solicitudes.mensajeVerificarCheck"/>');
		return false;
	}

}

function desactivarConvocatorias()
{
	if(comprobarCheckMarcado())
	{			
		var parametros = "";
		var formulario = document.forms[1];
		var contChecked = 0;

		for (var i=0; i < formulario.length; i++){
			if(formulario.elements[i].type=='checkbox' && formulario.elements[i].name=='convocatoriasSel'){
				if (formulario.elements[i].checked)
				{
					if(contChecked == 0){
						parametros+="?conv="+formulario.elements[i].value;
					}else{
						parametros+="&conv="+formulario.elements[i].value;
					}
					contChecked++;
				}
			}
		}	

		
		document.getElementById("accion").value="Desactivar Convocatorias";
		document.forms[0].submit();
		ventanaPopup = window.open("../spring/desactivarConvocatoriasSeleccionadas"+parametros,'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
				
	}else
	{
		alert('<spring:message code="field.solicitudes.mensajeVerificarCheck"/>');
		return false;
	}
}

function buscaConfiguracion(){
	if(document.getElementById("estadoConvocatoria").value = "CONFIGURACION")
		return true;
	else
		return false;
}

function alertaspopUp(parametro){
	
	if(parametro == "mostrarAlertas"){
		ventanaPopup = window.open("../spring/verAlertas", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	}
	
}

function limpiarCentroGestor(){
var desCentro = document.getElementById("dsCentroGestor").value;
	if(desCentro != ""){
		document.getElementById("centroGestor").value = "";
		document.getElementById("dsCentroGestor").value = "";
		document.getElementById("cuerpoEscala").value = "";
		document.getElementById("dsCuerpoEscala").value = "";
	}
}

function limpiarCuerpoEscala(){
	document.getElementById("cuerpoEscala").value = "";
	document.getElementById("dsCuerpoEscala").value = "";
}

</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left: 0.4em;" onload="alertaspopUp('<%=mostrar%>')">







<%-- <logic2:present name="variablePagina">DENTRO</logic2:present> --%>

<%-- <logic:notEqual name="variablePagina" value="3"> --%>
<!-- no es igual -->
<%-- </logic:notEqual> --%>
<%-- <logic:equal name="variablePagina" value="3"> --%>
<!-- es igual -->
<%-- </logic:equal> --%>

<form:form modelAttribute="buscaConvocatoriasForm" action="/IPSG/spring/buscarConvocatorias" id="formPadre" method="post">


<%-- <c:set var="variablePagina" value="99"/>  --%>
<%-- HOLA....${variablePagina} --%>
<%-- <logic:greaterThan name="variablePagina" value="3"> --%>
<!-- es mayor -->
<%-- </logic:greaterThan> --%>


<%-- <c:set var="variablePagina" value="1"/> --%>
<%-- HOLA....${variablePagina} --%>
<%-- <logic:lessThan name="variablePagina" value="3"> --%>
<!-- es menor que 3 -->
<%-- </logic:lessThan> --%>
<%-- <logic:greaterThan name="variablePagina" value="3"> --%>
<!-- es mayor que 3 -->
<%-- </logic:greaterThan> --%>

<%-- <bean:size id="numEstados" name="estados" scope="request"/> --%>
<%-- estados --- ${estados } --%>
<!--  -->
<%-- size --- ${numEstados } --%>



	<form:hidden path="paginaActual" styleId="paginaActual" />
	<form:hidden path="paginasTotales" styleId="paginasTotales" />
	<form:hidden path="campo" styleId="campo" />
	<form:hidden path="direccion" styleId="direccion" />
	<form:hidden path="accion" styleId="accion" />
	<form:hidden path="cambios" styleId="cambios" />
	<form:hidden path="pulsaIr" styleId="pulsaIr" />
	
<div class="">
	
	<logic:present name="org.apache.spring.ERROR">
		<html:errors/>
	<div class="clear"></div>
	</logic:present>
	
	<h1 class="pae-title"><spring:message code="field.solicitud.tituloConvocatorias"/></h1>


<div data-function="accordion-box" class="pae-box">
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.textosIntroduccion.convocatorias" />
		</h3>
	</div>
	<div class="pae-box__body">
		<fieldset>
			<div class="pae-layout">
				<!-- Ini Centro Gestor --> 
				<logic:present name="rol" scope="request">
					<logic:equal name="rol" value="3">
					<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.centroMay" />
							</span>
							<!--INI-TRA042-->
							<logic:present name="listaCentrosGestores" scope="request">
								<div class="pae-form__cell">
									<form:select path="centroGestor" class="pae-form__input">
										<option value=""></option>
										<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
									</form:select>
								</div>
							</logic:present>
							<!--FIN-TRA042-->
						</div>
					</div>
					</logic:equal>
				</logic:present>
				<logic:present name="rol" scope="request">
					<logic:notEqual name="rol" value="3">
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.centroMay" />
							</span>
							<form:input
				 				path="centroGestor" class="pae-form__input"
				 				readonly="true" onchange="comprobarBusqueda();comprobar()" 
				 				onclick="limpiarCentroGestor();"></form:input> 
				 				<form:input 
								path="dsCentroGestor" readonly="true" 
				 				class="input_texto_border0 em100"></form:input>
				 				
								
						</div>
					</div>	
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
						<input type="button"
								class="pae-form__btn-search" alt="Buscar Centro Gestor"
								onclick="return openWindowCentroGestor()"> 
					</div>
					</logic:notEqual>
				</logic:present>
				<!-- Ini Cuerpo y Escala-->
				
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.cuerpoMay" />
							</span>
							<form:input 
						 		path="cuerpoEscala" class="pae-form__input" 
						 		readonly="true" 
						 		onchange="comprobarBusqueda(); return comprobarCuerpoEscala()" 
						 		onclick="limpiarCuerpoEscala()"></form:input> 
						 		<form:input path="dsCuerpoEscala"
								maxlength="100" readonly="true" class="input_texto_border0 em100"></form:input>
								
							
						</div>
					</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
						<input type="button"
								class="pae-form__btn-search" style="cursor: pointer;"
								alt="Buscar Cuerpo Escala" onclick="return openWindowCuerpoEscala()">
					</div>
				
				</div>
				<!-- Fin Cuerpo y Escala-->
				
				
				<div class="pae-layout">
					<!-- Ini Estado-->
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.estadoMay" />
							</span>
							<form:select
								path="estadoConvocatoria" class="pae-form__input"
								onchange="comprobarBusqueda()">
								<option value="0"></option>
								<form:options items="${estados}" itemLabel="descripcion" itemValue="id"/>				
							</form:select>
						</div>
					</div>
					<!-- Fin Estado-->
					<!-- Ini Grupo-->
					<logic:present name="grupos" scope="request">
						<bean:size id="numGrupos" name="grupos" scope="request" />
						<logic:greaterThan name="numGrupos" value="0">
							<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.grupoMay" />
									</span>
									<form:select path="grupo"
										class="pae-form__input" onchange="comprobarBusqueda()">
										<option value="0"></option>
										<form:options items="${grupos}" itemLabel="descripcion" itemValue="id"/>	
									</form:select>				
								</div>
							</div>
						</logic:greaterThan>
					</logic:present>
					<!-- Fin Grupo-->
					<!-- Ini Modelos-->
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.modeloMay" />
							</span>
							<form:select path="idModelo" class="pae-form__input">		
								<logic:present name="listaModelosBean" scope="request">
									<bean:size id="numModelos" name="listaModelosBean" scope="request"/>
										<logic:greaterThan name="numModelos" value="0">
											<option value="0">   </option>
											<form:options items="${listaModelosBean}" itemLabel="numModelo" itemValue="id"/>
										</logic:greaterThan>
								</logic:present>
							</form:select>
						</div>
					</div>
					<!-- Fin Modelos -->
					
				</div>
				
				<div class="pae-layout">
					
 					<!-- Ini Forma Acceso -->
 					<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formaAccesoMay" />
							</span>
							<logic:present name="formasAcceso"
								scope="request">
								<bean:size id="numFormas" name="formasAcceso" scope="request" />
								<logic:greaterThan name="numFormas" value="0">
									
									<form:select path="formaAcceso" class="pae-form__input" onchange="comprobarBusqueda()">
											<option value="0"></option>
											<form:options items="${formasAcceso}" itemLabel="descripcion" itemValue="id"/>
										</form:select>
									
								</logic:greaterThan>
							</logic:present>
						</div>
					</div>
					<!-- Fin Forma Acceso--> 
				
				<!-- Ini Ejercicio-->
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.ejercicioMay" />
							</span>
							<form:input path="ejercicio" class="pae-form__input" onchange="comprobarBusqueda()" onkeypress="return soloNumeros(event);" maxlength="4"></form:input>
						</div>
					</div>
				</div>
				<!-- Fin Ejercicio-->
				
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
						<div class="pae-box-buttons">
							
							
							<input type="submit" name="buscarConvocatorias" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
				</div>
				
		</fieldset>	
	</div>
	

	
	</div>	
	
	
			<jsp:useBean id="convocatorias" scope="request"
		class="java.util.ArrayList" />
	<%
		int i = 0;
	%>
	<logic:present name="convocatorias" scope="request">
		<bean:size id="numResultados" name="convocatorias" scope="request" />
		<logic:greaterThan name="numResultados" value="0">
		
		<logic:equal name="buscaConvocatoriasForm"
				property="estadoConvocatoria" value="4">
				
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-box-buttons">
							<input type="submit" name="aprobar" value="Aprobar Convocatorias" onclick="return aprobarConvocatorias();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
				</div>
				
			</logic:equal>

			<%
				if (perfilUsuario.equals(Constantes.PERFIL_ADMINISTRADOR) || perfilUsuario.equals(Constantes.PERFIL_GESTOR)) {
			%>
			<logic:equal name="buscaConvocatoriasForm"
				property="estadoConvocatoria" value="5">								
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-box-buttons">
							<input type="submit" name="desactivar" value="Desactivar Convocatorias" onclick="return desactivarConvocatorias();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
				</div>
			</logic:equal>
			<%
				}
			%>
			<div class="scrollmenu">
			<table id="paetable" class="pae-table pae-table--margin">
				<caption class="hiddenAccesible">Tabla de convocatorias abiertas</caption>
				<thead class="pae-table__header">
             		<tr class="pae-table__row">
	             		<logic:equal name="buscaConvocatoriasForm" property="estadoConvocatoria" value="4">
							<th data-col="col5" class="pae-table__cell-header">
								<span class="pae-table__txt-title"><input type="checkbox" name="check" onclick="marcarCheck(this.checked)" /></span>
							</th>
						</logic:equal>
						<%
						if (perfilUsuario.equals(Constantes.PERFIL_ADMINISTRADOR) || perfilUsuario.equals(Constantes.PERFIL_GESTOR)) {
						%>
						<logic:equal name="buscaConvocatoriasForm" property="estadoConvocatoria" value="5">
							<th data-col="col5" class="pae-table__cell-header">
								<span class="pae-table__txt-title"><input type="checkbox" name="check" onclick="marcarCheck(this.checked)" /></span>
							</th>
						</logic:equal>
						<%
						}
						%>
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.idMay" /> 
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image"
													src="../images/order_desc.png" value="submit" alt="Submit"
													onclick="ordena(1,<%=numPag%>)"><input type="image"
													src="../images/order_asc.png" value="Submit" alt="Submit"
													onclick="ordena(2,<%=numPag%>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>						
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.ejercicioMay" /> 
												</span>	
											</div>
										</th>
										<th>
											<div class="titulo_der_tabla">
												<input type="image" src="../images/order_desc.png" value="Submit" alt="Submit"
												onclick="ordena(3,<%=numPag%>)"><input type="image"
												src="../images/order_asc.png" value="Submit" alt="Submit"
												onclick="ordena(4,<%=numPag%>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>
						
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>
										<th >
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.centroMay" /> 
												</span>	
										</th>
										<th>
											<div class="titulo_der_tabla">
												<input type="image"
													src="../images/order_desc.png" value="Submit" alt="Submit"
													onclick="ordena(15,<%=numPag%>)"><input type="image"
													src="../images/order_asc.png" value="Submit" alt="Submit"
													onclick="ordena(16,<%=numPag%>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>	
								
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>
										<th >	
											<div class="titulo_izq_tabla">								
												<span class="pae-table__txt-title">
													<spring:message code="field.cuerpoMay" />  
												</span>	
										</th>
										<th>
											<div class="titulo_der_tabla">
												<input type="image"
													src="../images/order_desc.png" value="Submit" alt="Submit"
													onclick="ordena(5,<%=numPag%>)"><input type="image"
													src="../images/order_asc.png" value="Submit" alt="Submit"
													onclick="ordena(6,<%=numPag%>)">
											</div>								
										</th>
									</tr>
								</tbody>
							</table>
						</th>
						
						<th class="pae-table__cell-header wauto">
							<table>
								<tbody>
									<tr>
										<th >		
											<div class="titulo_izq_tabla">							
												<span class="pae-table__txt-title">
													<spring:message code="field.fechaInicioMay" />  
												</span>	
										</th>
										<th>
											<div class="titulo_der_tabla">
												<input
													type="image" src="../images/order_desc.png" value="Submit" alt="Submit"
													onclick="ordena(7,<%=numPag%>)"><input type="image"
													src="../images/order_asc.png" value="Submit" alt="Submit"
													onclick="ordena(8,<%=numPag%>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>	
						
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>
										<th >		
											<div class="titulo_izq_tabla">							
												<span class="pae-table__txt-title">
													<spring:message code="field.fechaFinMay" /> 
												</span>	
										</th>
										<th>
											<div class="titulo_der_tabla">
												<input type="image" src="../images/order_desc.png" value="Submit" alt="Submit"
													onclick="ordena(9,<%=numPag%>)"><input type="image"
													src="../images/order_asc.png" value="Submit" alt="Submit"
													onclick="ordena(10,<%=numPag%>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>	
						
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>
										<th >					
											<div class="titulo_izq_tabla">				
												<span class="pae-table__txt-title">
													<spring:message code="field.formaAccesoMay" /> 
												</span>	
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" src="../images/order_desc.png" value="Submit" alt="Submit"
													onclick="ordena(11,<%=numPag%>)"><input type="image"
													src="../images/order_asc.png" value="Submit" alt="Submit"
													onclick="ordena(12,<%=numPag%>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>	
						
						<th class="pae-table__cell-header wauto">
							<table>
								<tbody>
									<tr>
										<th >								
											<span class="pae-table__txt-title">
												<spring:message code="field.estadoMay" /> 
											</span>	
										</th>
										<th>
											<input type="image"
							src="../images/order_desc.png" value="Submit" alt="Submit"
							onclick="ordena(13,<%=numPag%>)"><input type="image"
							src="../images/order_asc.png" value="Submit" alt="Submit"
							onclick="ordena(14,<%=numPag%>)">
										</th>
									</tr>
								</tbody>
							</table>
						</th>	
						
						<th data-col="col2" class="pae-table__cell-header wauto">
							<span class="pae-table__txt-title">
								<spring:message code="field.eliminarMay" /> 
							</span>
						</th>
						
						<th data-col="col2" class="pae-table__cell-header wauto">
							<span class="pae-table__txt-title">
								<spring:message code="field.enlacesMay" /> 
							</span>
						</th>
              		</tr>
                </thead>
                <tbody class="pae-table__body">
                	<c:forEach var="registro" items="${convocatorias }">
                		<tr class="pae-table__row" name="row">
                			<logic:equal name="buscaConvocatoriasForm" property="estadoConvocatoria" value="4">
                				<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body">
                					<span class="pae-table__txt-base pae-table__span-head"><form:checkbox path="convocatoriasSel" value="${registro.idConvocatoria}"/></span>
                				</td>
                			</logic:equal>
                			<%
							if (perfilUsuario.equals(Constantes.PERFIL_ADMINISTRADOR) || perfilUsuario.equals(Constantes.PERFIL_GESTOR)) {
							%>
							<logic:equal name="buscaConvocatoriasForm" property="estadoConvocatoria" value="5">
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body">
                					<span class="pae-table__txt-base pae-table__span-head"><form:checkbox path="convocatoriasSel" value="${registro.idConvocatoria}"/></span>
                				</td>
							</logic:equal>
							<%
							}
							%>
							<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="idConvocatoria" /></span>
               				</td>
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="ejercicio" /></span>
               				</td>
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="siglasCentroGestor" /></span>
               				</td>
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="cuerpoEscala" /></span>
               				</td>
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="fechaInicio" /></span>
               				</td>
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="fechaFin" /></span>
               				</td>
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="formaAcceso" /></span>
               				</td>
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="estadoConvocatoria" /></span>
               				</td>
               				
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               				<span class="pae-table__txt-base pae-table__span-head">
               				<%
							ConvocatoriasBean convocatoria = (ConvocatoriasBean) convocatorias.get(i);
							i++;
							String enlaceActualizarEstadoConvocatoria = "ActualizarEstadoConvocatoria?registro="+ convocatoria.getIdConvocatoria();
							if (convocatoria.isEliminar() == true) {
							%> 
							
               						<a href="<%=enlaceActualizarEstadoConvocatoria%>"
										style="color: #c33400;" onclick="return comprobarEliminar()">
									<spring:message code="field.eliminar" />
									</a> 
               					
							<% 	} %>
							</span>
               				</td>
               				
							<%
							String enlaceDetalleConvocatoria = "detalleConvocatoria?registro="
														+ convocatoria.getIdConvocatoria();
							%>
							<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head">
               						<a href="<%=enlaceDetalleConvocatoria%>" style="color: #c33400;">
									<spring:message code="field.verDetalle" />
									</a>
               					</span>
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
								int paginaActual = Integer.parseInt(pagActual
										.toString());
								String paginar = "Paginar";
				%>
				
				<div class="capaPaginacion" id="cap">

					<span class="pae-form__span-label-regxp">
						<spring:message code="field.numRegistros"/>
					</span>
					<form:select path="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
						<form:option value="10"><spring:message code="field.10Registros"/></form:option>
						<form:option value="20"><spring:message code="field.20Registros"/></form:option>
						<form:option value="50"><spring:message code="field.50Registros"/></form:option>
					</form:select>
					
			
				<div class="pagination">
				<%if(paginaActual != 1) { %>
					<a href="buscarConvocatorias?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&laquo;</a>
				<% } else{  %>	
					 <a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
				<% }
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarConvocatorias?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar;
					if(x==paginaActual){%>
						<a href="<%= enlacePagina %>" class="active" ><%=x %></a></strong>
						 
					<%}else{%>
						<a href="<%= enlacePagina %>" ><%=x %></a>
						 
					<%}%>
				<%}%>
			<%}
			else
			{
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarConvocatorias?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<a href="<%= enlacePagina %>" class="active" ><%=x %></a>
						<div><spring:message code="field.puntosPaginacion"/></div>
					<%}else if (x<= 3 || x+3>numeroPagina){
						if(x==paginaActual){%>	
							<a href="<%= enlacePagina %>" class="active" ><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								 <a href="#"><div><spring:message code="field.puntosPaginacion"/></div></a>
							<%}%>
						 
						<%}
						else
						{%>	
							<a href="<%= enlacePagina %>"  ><%=x %></a>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								 <div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>
							 
						<%}%>	
					<%}%>
				<%}%>
			<%}%>
			<%if(paginaActual != numeroPagina) { %>
				<a href="buscarConvocatorias?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&raquo;</a>
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
		

		<div class="clear"></div>	
		</logic:greaterThan>
		<logic:present name="accion" scope="request">
			<logic:lessThan name="numResultados" value="1">
				<div id="error"><spring:message
					code="field.convocatoria.error" /></div>
			</logic:lessThan>
		</logic:present>
	</logic:present>	
	
	
</div>


</form:form>
</body>
</html>