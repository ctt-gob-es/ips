<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script>
<script type="text/javascript">
function guardar(){
	if(document.getElementById("siglas").value == "" || document.getElementById("descripcion").value == ""
		|| document.getElementById("url").value == "" || document.getElementById("codigo").value == "" ||
		document.getElementById("siglas").value == null || document.getElementById("descripcion").value == null
		|| document.getElementById("url").value == null || document.getElementById("codigo").value == null){
		alert("Debe rellenar todos los campos");
		document.getElementById("submit").value = "Error";
	}else{
		document.getElementById("submit").value = "Guardar";
	}
}
/*
function limpiar(){
	document.getElementById("siglas").value = "";
	document.getElementById("url").value = "";
	document.getElementById("descripcion").value = "";
	document.getElementById("codigo").value = "";
	document.getElementById("fechaSustitucion").value = "";
	document.getElementById("idMinisterioPrevio").selectedIndex = 0;
}*/

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };
function valida() {
    if (document.getElementById("codigo").value.trim().length == 0) {
    	document.getElementById("codigo").value= "";
    }
    if (document.getElementById("siglas").value.trim().length == 0) {
    	document.getElementById("siglas").value= "";
    }
    if (document.getElementById("url").value.trim().length == 0) {
    	document.getElementById("url").value= "";
    }
    if (document.getElementById("descripcion").value.trim().length == 0) {
    	document.getElementById("descripcion").value= "";
    }
    document.getElementById("submit").value= "Guardar";
}
function cancelar(){
	window.location.href = "../spring/buscarMinisterios";
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
<body style="margin-left:0.4em;" onload="marcar()">

<div class="capaAll">

 <h1 class="pae-title"> <spring:message code="field.ministerio.tituloAlta"/> </h1>
    

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->


<form:form commandName="ministerioForm" action="/IPSG/spring/altaMinisterio" id="formPadre" method="post">	
<form:hidden path="submit" id="submit"/>


	
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
		
	<!-- Ini Siglas-->
<div data-function="accordion-box" class="pae-box">   
<div class="pae-box__body">
	<fieldset id="formulario">
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.ministerio.siglas"/><span class="obligatorio"> *</span>
					</span>
					<form:input type="text" path="siglas" class="pae-form__input"  maxlength="5"/>
				</div>
			</div>
			<!-- Fin Siglas-->
			<!-- Ini Descripción -->
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.ministerio.descripcion"/><span class="obligatorio"> *</span>
					</span>
					<form:input type="text" path="descripcion" id="descripcion" class="pae-form__input"  maxlength="100"/>
				</div>
			</div>
			<!-- Fin Descripción-->	
			<!-- Ini URL-->
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.ministerio.url"/><span class="obligatorio"> *</span>
				</span>
				<form:input type="text" path="url" id="url" class="pae-form__input" maxlength="200"/>
			</div>
			</div>
			<!-- Fin URL-->
		</div>
		<div class="pae-layout">
		<!-- Ini Código-->
		<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.ministerio.codigo"/><span class="obligatorio"> *</span>
			</span>
			<form:input type="text" path="codigo" id="codigo" class="pae-form__input" maxlength="5"/>
		</div>
		</div>
		<!--  Fin Código-->	
		<!-- Ini Ministerio Sustituido-->
		<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label pae-ancho">
				<spring:message code="field.ministerio.ministerioSutMay"/>
			</span>
		
			<form:select path="idMinisterioPrevio" class="pae-form__input" id="idMinisterioPrevio" >
				<option value="">         </option>
				<logic:present name="ministerioLista" scope="request">
					<bean:size id="numMinisterio" name="ministerioLista" scope="request"/>
						<logic:greaterThan name="numMinisterio" value="0">
							<form:options items="${ministerioLista}"  itemLabel="descripcion" itemValue="id"/>
						</logic:greaterThan>
				</logic:present>
			</form:select>
		</div>
		</div>
	<!-- Fin Ministerio Sustituido-->
	<!-- Ini Fecha Sustitución-->
	<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label pae-ancho">
				<spring:message code="field.fechaSustitucionMay"/>
			</span>
			<form:input type="text" path="fechaSustitucion" id="fechaSustitucion"  data-function="function-datepicker" 
						placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>		
	<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="javascript: popUpCalendar(this, document.getElementById('fechaSustitucion'), 'dd/mm/yyyy',2); "/> -->
	<!-- 					<script type="text/javascript"> -->
	<%-- 							fncCalendario('fechaSustitucion', '<%=request.getContextPath()%>'); --%>
	<!-- 					</script> -->
		</div>
	</div>
	<!--  Fin Fecha Sustitución-->	
	<!-- Ini Visibilidad-->
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-check">
		<div class="pae-form__cell">
			<input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="yes">
			<label for="visibilidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.general.visibilidadTitulo"/> 
				</span>
			</label>	
		</div>
		</div>
	<!-- Fin Visibilidad-->	
	</div>
	
<div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" value="Guardar" onclick="valida()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
			<input type="button" value="Cancelar" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
		</div>
	</div>


</fieldset>	
</div>
</div>	
	
</form:form>
	
</div>
</body>
</html>


