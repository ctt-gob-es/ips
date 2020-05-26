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
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language='javascript' src="../javascript/utils.js"></script> 
<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="capaAll">
<h1 class="pae-title"> <spring:message code="field.tarifa.tituloMantenimiento"/> </h1>
   

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form modelAttribute="tarifaForm" action="/IPSG/spring/modificarTarifa" method="post">
<div data-function="accordion-box" class="pae-box"> 


<div class="capaAll"><br>
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
<div class="pae-box__body">
<fieldset id="formulario">
<div class="pae-layout">
	<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.cuerpoEscala.id"/>
		</span>
			<logic:present name="tarifa" scope="request">
				<form:input path="id" name="tarifa" class="pae-form__input pae-box--transparent" readonly="true"/>
			</logic:present>
			<logic:notPresent name="tarifa" scope="request">
				<form:input path="id" class="pae-form__input pae-box--transparent" readonly="true"/>
			</logic:notPresent>
		</div>
	</div>
	
	<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.descripcionMay"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="tarifa" scope="request">
				<input type="text" name="descripcion" maxlength="50" value="${tarifa.descripcion }"  class="pae-form__input">
			</logic:present>
			<logic:notPresent name="tarifa" scope="request">
				<form:input path="descripcion"  class="pae-form__input" maxlength="50"/>
			</logic:notPresent>
		</div>
	</div>
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.ejercicioMay"/><span class="obligatorio"> *</span>
		</span>
			<logic:present name="tarifa" scope="request">
				<input type="text" name="ejercicio" maxlength="4" value="${tarifa.ejercicio }" class="pae-form__input" onkeypress="return isNumber(event)">
			</logic:present>
			<logic:notPresent name="tarifa" scope="request">
				<form:input path="ejercicio" class="pae-form__input" maxlength="4"/>
			</logic:notPresent>
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
					<bean:size id="numGrupo" name="grupo" scope="request"/>
						<logic:greaterThan name="numGrupo" value="0">
							<form:options items="${grupo}" itemLabel="descripcion" itemValue="id"/>
						</logic:greaterThan>
				</logic:present>
			</form:select>
		</div>
	</div>
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label pae-ancho3">
			<spring:message code="field.tipoAccesoMay"/><span class="obligatorio"> *</span>
			</span>
			<form:select path="idTipoAcceso" class="pae-form__input" id="idTipoAcceso" >
				<option value="">         </option>
				<logic:present name="tipoAcceso" scope="request">
					<bean:size id="numTipoAcceso" name="tipoAcceso" scope="request"/>
						<logic:greaterThan name="numTipoAcceso" value="0">
							<form:options items="${tipoAcceso}" itemLabel="codigo" itemValue="id"/>	
						</logic:greaterThan>
				</logic:present>
			</form:select>
		</div>	
	</div>
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label pae-ancho3">
			<spring:message code="field.importeMay"/><span class="obligatorio"> *</span>
		</span>
			<logic:present name="tarifa" scope="request">
				<input type="text" name="importe" maxlength="6" value="${tarifa.importe }"  class="pae-form__input">
			</logic:present>
			<logic:notPresent name="tarifa" scope="request">
				<form:input path="importe"  class="pae-form__input" maxlength="6"/>
			</logic:notPresent>
		</div>
	</div>
</div>	
</div>
	

 	<div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" name="accion" value="Modificar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
			<input type="button" value="Cancelar" name="submit" onclick="volver('../spring/verBuscarTarifa')"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1">
	</div>
	</div>

	<br>

</form:form>
	
</div>
</fieldset>
</div>
</body>
</html>


