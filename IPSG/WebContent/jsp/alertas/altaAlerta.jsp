<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="es.map.ipsg.util.Constantes"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
	String sPerfilUsuario =  request.getAttribute("perfilUsuario").toString();
%>

<script type="text/javascript">

function limpiar(){
	document.getElementById("idCentroGestor").value = "";
	document.getElementById("idModo").value = "";
	document.getElementById("idTipo").value = "";
	document.getElementById("idPerfil").value = "";
	document.getElementById("submit").value = "Limpiar";
	document.getElementById("menu").value="S"; 
}
String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };

function valida() {
    document.getElementById("submit").value= "Guardar";
    document.forms[1].action="../spring/AltaAlerta?menu=N"
}   
function  comprobarBusqueda()  {
	if (document.getElementById("idModo").value=="2"){
		document.getElementById("divUsuarios").style.display='block';
        
    }else{
    	document.getElementById("divUsuarios").style.display='none';
    }
    if (document.getElementById("idPerfil").value=="3" ){
    	document.getElementById("idCentroGestor").readOnly='true';
    }
 } 
function openWindowCentroGestor() {
	var param = "idCentroGestor";
	var param2 = "dsCentroGestor";
	var extract = new Object();
		ventanaPopup = window.open("../spring/listarCentroGestor?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
} 
//Añadimos Usuario
function pasar(formulario1,formulario2) { 
	
	obj=document.getElementById(formulario1); 
    if (obj.selectedIndex==-1) return;
	var lista = obj.options;
	for(var i=0; i<lista.length; i++) {
		var opcion = lista[i];
		if (opcion.selected) {
			valor = opcion.value;
			txt = opcion.text;
			obj.options[i] = null;
			obj2=document.getElementById(formulario2);
		 	opc = new Option(txt,valor); 
		    eval(obj2.options[obj2.options.length]=opc);   
			i--;
			sortSelectByName(obj2);	
			
		}
	}  
} 
//Eliminamos Usuruario
function pasar2(formulario1,formulario2) { 

	 obj=document.getElementById(formulario1); 
	    if (obj.selectedIndex==-1) return;
	    var lista = obj.options;
	    for(var i=0; i<lista.length; i++) {
			var opcion = lista[i];
			if (opcion.selected) {
				valor = opcion.value;
				txt = opcion.text;
				obj.options[i] = null;
				obj2=document.getElementById(formulario2);
			 	opc = new Option(txt,valor); 
			    eval(obj2.options[obj2.options.length]=opc);   
				i--;
				sortSelectByName(obj2);			
			}
		}  
 
}

function sortSelectByName(selectToSort) {
    var arrOptions = [];

    for (var i = 0; i < selectToSort.options.length; i++)  {
        arrOptions[i] = [];
        arrOptions[i][0] = selectToSort.options[i].text;
        arrOptions[i][1] = selectToSort.options[i].value;
        arrOptions[i][2] = selectToSort.options[i].selected;
    }

    arrOptions.sort();

    for (var i = 0; i < selectToSort.options.length; i++)  {
    	selectToSort.options[i].text = arrOptions[i][0];
        selectToSort.options[i].value = arrOptions[i][1];        
        selectToSort.options[i].selected = arrOptions[i][2];
    }
}

function sortSelect(selectToSort) {
    var arrOptions = [];

    for (var i = 0; i < selectToSort.options.length; i++)  {
        arrOptions[i] = [];
        arrOptions[i][0] = selectToSort.options[i].value;
        arrOptions[i][1] = selectToSort.options[i].text;
        arrOptions[i][2] = selectToSort.options[i].selected;
    }

    arrOptions.sort();

    for (var i = 0; i < selectToSort.options.length; i++)  {
        selectToSort.options[i].value = arrOptions[i][0];
        selectToSort.options[i].text = arrOptions[i][1];
        selectToSort.options[i].selected = arrOptions[i][2];
    }
}


function seleccionar_todo(){ 
	
	
	obj3=document.getElementById('idUsuarioSeleccionados'); 
	if(obj3.options.length>0){
	   for (j=0;j<obj3.options.length;j++){ 
	      obj3.options[j].selected=true;  
	   }
	}
	
} 

function limpiarCentroGestor(){
	document.getElementById("idCentroGestor").value = "";
	document.getElementById("dsCentroGestor").value = "";
}

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;" onload="comprobarBusqueda();sortSelectByName(document.getElementById('idUsuario'))">

<div class="">

 <h1 class="pae-title"> <spring:message code="field.alerta.tituloAlta"/> </h1>
     

<!--<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">-->

<form:form modelAttribute="alertaForm" action="/IPSG/spring/verAltaAlerta" method="post">
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="idPerfil" id="idPerfil"/>
	<input type="hidden" id="menu" value="N"/>

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
	<!-- Ini Centro Gestor-->
	<div data-function="accordion-box" class="pae-box">   
	<div class="pae-box__body">
	<fieldset id="formulario">
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.alerta.centroGestorMay"/><span class="obligatorio"> *</span>	
				</span>
			<%if( sPerfilUsuario.equals(Constantes.PERFIL_GESTOR)) { %>
				<!--INI-TRA042-->
				<logic:present name="listaCentrosGestores" scope="request">
					<div class="pae-form__cell">
						<form:select path="idCentroGestor" class="pae-form__input">
							<option value=""></option>
							<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
						</form:select>
					</div>
				</logic:present>
				<!--FIN-TRA042-->
			<%} else {%>
				<form:input path="idCentroGestor" id="idCentroGestor" onchange="comprobarBusqueda()" class="pae-form__input" maxlength="5" readonly="true" onclick="limpiarCentroGestor()"></form:input>
<!-- 				<input type="image" src="../images/lupa.png" alt="Buscar Centro Gestor" onclick="return openWindowCentroGestor()"> -->
			<%} %>
			<form:input path="dsCentroGestor" id="dsCentroGestor" class="input_texto_border0 em100" readonly="true"></form:input>
		</div>
		</div>
		<%if(!sPerfilUsuario.equals(Constantes.PERFIL_GESTOR)) { %>
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn2">
			<input type="button"
			class="pae-form__btn-search" alt="Buscar Centro Gestor"
			onclick="return openWindowCentroGestor()"> 
		</div>
		<%}%>
	<logic:present name="modosAlerta" scope="request">
			<bean:size id="IdModo" name="modosAlerta" scope="request"/>
			<logic:greaterThan name="IdModo" value="0">
			
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.alerta.modoAlertaMay"/><span class="obligatorio"> *</span>	
					</span>
					<form:select path="idModo" id="idModo" class="pae-form__input" onchange="comprobarBusqueda()">
							<option value=""> </option>
							<form:options items="${modosAlerta}" itemValue="id" itemLabel="descripcion" />
					</form:select>
				</div>	
				</div>			
			</logic:greaterThan>
		</logic:present>
	
	<!-- Fin Modo Alerta-->
	<!-- Ini Tipo Alerta -->
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.alerta.tipoAlertaMay"/><span class="obligatorio"> *</span>
			</span>
			<form:select path="idTipo" id="idTipo" class="pae-form__input" onchange="comprobarBusqueda()">
				<option value=""> </option>
				<form:options items="${tiposAlerta}" itemValue="id" itemLabel="descripcion" />
			</form:select>
		</div>
	</div>
	</div>
	<!-- Fin Tipo Alerta-->	
	<!-- Ini Lista Usuarios -->
	<div class="pae-layout" id="divUsuarios" style="display: none"> 
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
		<span class="pae-form__label pae-form__span-label"> 
			<spring:message code="field.alerta.usuariosMay"/><span class="obligatorio"> *</span>
		</span> 
		<form:select path="idUsuario" id="idUsuario" class="pae-form__input" multiple="true" size="5" >
			<form:options items="${usuarioList}" itemValue="id" itemLabel="nombreCompleto" />				
		</form:select>	
		</div> 
  <div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7">	
	<div class="pae-box-buttons">
			<input type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" value="<-" onClick="pasar2('idUsuarioSeleccionados','idUsuario')"> 
		<input type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" value="->" onClick="pasar('idUsuario','idUsuarioSeleccionados')"> 
	</div> 
	</div> 
 	<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7">
			<form:select path="idUsuarioSeleccionados" id="idUsuarioSeleccionados" class="pae-form__input" multiple="true" size="5" >
			<form:options items="${usuariosSel}" itemValue="id" itemLabel="nombreCompleto" />
								
			</form:select>		
 		</div> 
 	</div> 
 	<!-- Fin Lista Usuarios -->	
	<div class="clear"></div>
	
	<br><br>
	<div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" value="Guardar" onclick="javascript:seleccionar_todo();valida();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
			<input type="submit" value="Limpiar" onclick="limpiar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1">
		</div>
	</div>
</div>
	
	<div class="clear"></div>
	
	</fieldset>	
</div>
</div>	
	
</form:form>
	
<!--</div>-->
</body>
</html>


