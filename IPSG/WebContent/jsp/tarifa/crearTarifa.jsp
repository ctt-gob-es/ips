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
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function cancelar(){
	window.location.href = "../spring/verBuscarTarifa";
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;">
<div class="capaAll">

<h1 class="pae-title"> <spring:message code="field.tarifa.tituloAltaTarifa"/> </h1>
<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
<div class="clear"></div>
</logic:present>
 <div data-function="accordion-box" class="pae-box">   



<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form modelAttribute="tarifaForm" action="/IPSG/spring/crearTarifa" method="post">

<logic:present name="tarifa" scope="request"><form:hidden path="id" name="tarifa"/></logic:present>


<div class="pae-box__body">
<fieldset>
<div class="pae-layout">
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.descripcionMay"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="descripcion" id="descripcion" class="pae-form__input" maxlength="50" />
		</div>	
	</div>
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.ejercicioMay"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="ejercicio" scope="request">
			</logic:present>
				<form:input path="ejercicio" class="pae-form__input" maxlength="4" onkeypress="return isNumber(event)"/>
		</div>
	</div>
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.grupoMay"/><span class="obligatorio"> *</span>
			</span>
			<form:select path="idGrupo" class="pae-form__input" id="idGrupo" >
					<option value="">         </option>
					<logic:present name="grupo" scope="request">
						<bean:size id="numgrupo" name="grupo" scope="request"/>
							<logic:greaterThan name="grupo" value="0">
								<form:options items="${grupo}" itemLabel="descripcion" itemValue="id"/>
							</logic:greaterThan>
					</logic:present>
					
			</form:select>
		</div>
	</div>
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">	
				<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.tipoAccesoMay"/><span class="obligatorio"> *</span>
			</span>
			<form:select path="idTipoAcceso" class="pae-form__input" id="idTipoAcceso" >
					<option value="">         </option>
					<logic:present name="tipoAcceso" scope="request">
						<bean:size id="numtipoAcceso" name="tipoAcceso" scope="request"/>
							<logic:greaterThan name="tipoAcceso" value="0">
								<form:options items="${tipoAcceso}" itemLabel="codigo" itemValue="id"/>	
							</logic:greaterThan>
					</logic:present>
			</form:select>
		</div>
	</div>
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.importeMay"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="importe" id="importe" class="pae-form__input" maxlength="6"/>
		</div>
	</div>	
</div>
</fieldset>
<fieldset>
	
 	<div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" name="accion" value="Guardar" onclick="return validaDatos()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
			<input type="button" value="Cancelar" name="submit" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
		</div>
	</div>

		
	<div class="clear"></div>	
</fieldset>
</div>
</form:form>
</div>
</div>
</body>
</html>


