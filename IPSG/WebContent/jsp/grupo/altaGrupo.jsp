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

function limpiar(){
	document.getElementById("codigo").value = "";
	document.getElementById("descripcion").value = "";	
	document.getElementById("siglas").value = "";	
}

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };
function valida() {
    if (document.getElementById("codigo").value.trim().length == 0) {
    	document.getElementById("codigo").value= "";
    }
    if (document.getElementById("descripcion").value.trim().length == 0) {
    	document.getElementById("descripcion").value= "";
    }
    if (document.getElementById("siglas").value.trim().length == 0) {
    	document.getElementById("siglas").value= "";
    }    
    document.getElementById("submit").value= "Guardar";
}
function cancelar(){
	window.location.href = "../spring/buscarGrupo";
}

function marcar(){
	document.getElementById("visibilidad").checked= true;
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language='javascript' src="../javascript/utils.js"></script> 
<title></title>
</head>
<body style="margin-left:0.4em;" onload= "marcar()">

<div class="capaAll">

 <h1 class="pae-title">  <spring:message code="field.grupo.mantenimiento.titulo"/> </h1>
  

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>


<form:form modelAttribute="grupoForm" action="/IPSG/spring/altaGrupo" method="post">
	<form:hidden path="submit" id="submit"/>

<div data-function="accordion-box" class="pae-box">


<div class="pae-box__body">
  <fieldset>
	<div class="pae-layout">
	<!-- Ini Siglas -->
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.grupo.siglasMay"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="siglas" id="siglas" class="pae-form__input" maxlength="5"/>
		</div>		
	</div>
	<!-- Fin Siglas -->
	<!-- Ini Código -->
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.grupo.codigo"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="codigo" id="codigo" class="pae-form__input" maxlength="10"/>
		</div>
	</div>
	<!-- Fin Código -->
	<!-- Ini Descripción -->
	<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.grupo.descripcion"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="descripcion" id="descripcion" class="pae-form__input" maxlength="100"/>
		</div>
	</div>
	<!-- Fin Descripción -->
	<!-- Ini Visibilidad-->
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
		<div class="pae-form__cell">
			<input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="yes">
			<label for="visibilidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.general.visibilidadTitulo"/> 
				</span>
			</label>
		</div>
	</div>
</div>
	<!-- Fin Visibilidad-->
	<br><br>

	<div class="clear"></div>
	<fieldset>

	  <div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" value="Guardar" onclick="valida()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline ">
			<input type="button" value="Cancelar" name="submit" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
		</div>
	</div>
	
		</fieldset>
<!-- </div> -->
<!-- </div> -->
</fieldset>
</div>
</div>
</form:form>
</div>
</body>
</html>


