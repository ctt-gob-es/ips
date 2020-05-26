<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

function cancelar(){
	window.location.href = "../spring/buscarConfiguracionErrores";
}

function marcar(){
	document.getElementById("visibilidad").checked= true;
}
</script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>
<body style="margin-left:0.4em;" onload = "marcar()">
<div class="capaAll">
	<h1 class="pae-title"> <spring:message code="field.configuracionErrores.tituloAltaConfiguracionErrores"/> </h1>
	


<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form commandName="configuracionErroresForm" action="/IPSG/spring/crearConfiguracionErrores" id="formPadre" method="post">

<logic:present name="configuracionErrores" scope="request">
	<form:hidden path="id" name="configuracionErrores"/>
</logic:present>

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>	
<div data-function="accordion-box" class="pae-box">   
<div class="pae-box__body">
<fieldset id="formulario">
<div class="pae-layout">
	
	<div class="pae-layout__item pae-u-8/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.configuracionErrores.descripcion.mayus"/><span class="obligatorio"> *</span>
			</span>
			<form:input type="text" path="descripcion" class="pae-form__input"  maxlength="200"/>
		</div>
	</div>	
<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
			<div class="pae-form__cell">
				<input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="yes">
				<label for="visibilidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.configuracionErrores.visibilidad.mayus"/> 
					</span>
				</label>	
			</div>
		</div>
</div>	

 	<div class="filaBtn">
		<div class="pae-box-buttons pd-right-1">
			<input type="submit" value="Guardar" name="accion" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium"/>
			<input type="button" value="Cancelar" name="submit" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>
		</div>
	</div>

</fieldset>	
</div>
</div>
	
</form:form>
	
 </div>  <!--cierre capaAll -->
</body>
</html>


