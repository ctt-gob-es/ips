<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
function guardar(){
	if(document.getElementById("siglas").value == "" || document.getElementById("descripcion").value == ""
		|| document.getElementById("url").value == "" || document.getElementById("codigo").value == "" ||
		document.getElementById("siglas").value == null || document.getElementById("descripcion").value == null
		|| document.getElementById("url").value == null || document.getElementById("codigo").value == null){
		document.getElementById("submit").value = "Error";
	}else{
		document.getElementById("submit").value = "Guardar";		
	}
}

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };

function valida() {
    document.getElementById("submit").value= "Guardar";
    document.forms[1].action="../spring/ActualizarAlerta?menu=N";

}
function pulsar(e) { 
    tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; 
    patron =/s/; 
    te = String.fromCharCode(tecla); 
    return !patron.test(te); 
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
			sortSelect(obj2);	
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
			sortSelect(obj2);			
		}
	}  
}


//Se ordena los usuarios
function sortSelect(selectToSort) {
    var arrOptions = [];

    for (var i = 0; i < selectToSort.options.length; i++)  {
        arrOptions[i] = [];
        arrOptions[i][0] = selectToSort.options[i].text;
        arrOptions[i][1] = selectToSort.options[i].value;        
        arrOptions[i][2] = selectToSort.options[i].selected;
    }

    arrOptions.sort();

    for (var i = 0; i < selectToSort.options.length; i++) {
    	selectToSort.options[i].text = arrOptions[i][0];
		selectToSort.options[i].value = arrOptions[i][1];
		selectToSort.options[i].selected = arrOptions[i][2];
	}
}

//Marcamos todos los usuarios selecionados para guardarlos.
function seleccionar_todo() {

		obj3 = document.getElementById('idUsuarioSeleccionados');
		if (obj3.options.length > 0) {
			for (j = 0; j < obj3.options.length; j++) {
				obj3.options[j].selected = true;
			}
		}

}

function volver(){
	window.location.href = "<%=request.getContextPath()%>/spring/buscarAlerta";	
	
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;" onload="comprobarBusqueda();sortSelect(document.getElementById('idUsuario'));sortSelect(document.getElementById('idUsuarioSeleccionados'))">

<div class="">

 <h1 class="pae-title">  <spring:message code="field.alerta.tituloModificar"/> </h1>
    

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form modelAttribute="alertaForm" action="/IPSG/spring/verActualizarAlerta" method="post">
	<form:hidden path="idPerfil" id="idPerfil"/>
	<form:hidden path="submit" id="submit"/>



	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>



<div data-function="accordion-box" class="pae-box">
<div class="pae-box__body">
	<fieldset>
		<div class="pae-layout pd-top-1_7">
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.alerta.idMay"/>
					</span>
					<form:input path="id" id="id" onchange="comprobarBusqueda()" class="pae-form__input pae-box--transparent" maxlength="5"  readonly="true" ></form:input>
				</div>
			</div>
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.centroMay"/>
					</span>
					<form:input path="idCentroGestor" class="pae-form__input pae-box--transparent" id="idCentroGestor" readonly="true"></form:input>			
			</div>
	</div>
	<logic:present name="modosAlerta" scope="request">
			<bean:size id="IdModo" name="modosAlerta" scope="request"/>
			<logic:greaterThan name="IdModo" value="0">
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.alerta.modoAlertaMay"/><span class="obligatorio"> *</span>	
						</span>
					<form:select path="idModo" id="idModo" class="pae-form__input" onchange="comprobarBusqueda()">
							<form:options items="${modosAlerta}" itemLabel="descripcion" itemValue="id"/>	
					</form:select>
				</div>
				</div>
			</logic:greaterThan>
		</logic:present>
	
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.alerta.tipoAlertaMay"/><span class="obligatorio"> *</span>
			</span>
		<form:select path="idTipo" id="idTipo" class="pae-form__input" onchange="comprobarBusqueda()">
		<form:options items="${tiposAlerta}" itemLabel="descripcion" itemValue="id"/>	
		</form:select>
		</div>
	</div>
	<div class="clear"></div>
	</div>
<div class="pae-layout">
	<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
		<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.alerta.usuariosMay"/><span class="obligatorio"> *</span>
		</span>
			<form:select path="idUsuario" size="5" id="idUsuario" class="pae-form__input" multiple="true">
								<form:options items="${usuarioList}" itemLabel="nombreCompleto" itemValue="id"/>	
			</form:select>	
		</div>
		<div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7">	
			<div class="pae-box-buttons">
				<input type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" value="<-" onClick="pasar2('idUsuarioSeleccionados','idUsuario')"> 
				<input type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption"  value="->" onClick="pasar('idUsuario','idUsuarioSeleccionados')">
			</div>
		</div>
 		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7">	
			<form:select path="idUsuarioSeleccionados" id="idUsuarioSeleccionados" class="pae-form__input" multiple="true" size="5" >
								<form:options items="${usuariosSel}" itemLabel="nombreCompleto" itemValue="id"/>	
			</form:select>		
		</div>	
	</div>
	<br><br>
		<div class="filaBtn">
				<div class="pae-box-buttons pd-right-1">
					<input type="submit" value="Modificar" onclick="javascript:seleccionar_todo();valida();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
					<input type="button" value="Cancelar" name="submit" onclick="volver()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
				</div>
			</div>
			
		</div>
			
</fieldset>	
</div>
</div>	
	
</form:form>
	
</div>
</body>
</html>



