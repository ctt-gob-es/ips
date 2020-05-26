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

function volver(){
	window.location.href = "../spring/buscarMotivoReduccionTasa"; 
}


</script>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="">

  <h1 class="pae-title"> <spring:message code="field.motivoReduccionTasa.tituloMantenimientoMotivo"/> </h1>
    

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form commandName="motivoReduccionTasaForm" action="/IPSG/spring/modificarMotivoReduccionTasa" id="formPadre" method="post">

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
	
	<!-- Ini ID -->
	<div data-function="accordion-box" class="pae-box">   
	<div class="pae-box__body">
	<fieldset id="formulario">
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.cuerpoEscala.id"/>
				</span>
			<logic:present name="motivo" scope="request">
				<form:input type="text" path="id" name="motivo" class="pae-form__input pae-box--transparent" maxlength="3" readonly="true"/>
			</logic:present>
			<logic:notPresent name="motivo" scope="request">
				<form:input type="text" path="id" class="pae-form__input pae-box--transparent" maxlength="3" readonly="true"/>
			</logic:notPresent>
			</div>
			</div>
	<!-- Fin ID -->
	<!-- Ini Código -->
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.codigoMay"/><span class="obligatorio"> *</span>
				</span>
			<logic:present name="motivo" scope="request">
				<form:input type="text" path="codigo" name="motivo" class="pae-form__input" maxlength="10"/>
			</logic:present>
			<logic:notPresent name="motivo" scope="request">
				<form:input type="text" path="codigo" class="pae-form__input" maxlength="10"/>
			</logic:notPresent>
		</div>
	</div>
		<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label pae-ancho3">
				<spring:message code="field.porcentajeDescuento"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="motivo" scope="request">
				<form:input type="text" path="porcentajeDescuento" name="motivo" class="pae-form__input"  maxlength="3"/>
			</logic:present>
			<logic:notPresent name="motivo" scope="request">
				<form:input type="text" path="porcentajeDescuento" class="pae-form__input" maxlength="3"/>
			</logic:notPresent>
		</div>
		</div>
	<!-- Fin Porcentaje Descuento -->
	<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm"></div>
	<!-- Ini Descricpicón -->
	<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.cuerpoEscala.descripcion"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="motivo" scope="request">
				<form:input type="text" path="descripcion" name="motivo" class="pae-form__input" maxlength="100"/>
			</logic:present>
			<logic:notPresent name="motivo" scope="request">
				<form:input type="text" path="descripcion" class="pae-form__input" maxlength="100"/>
			</logic:notPresent>
		</div>
	</div>
	</div>
	<!-- Fin Descripción -->
	<!-- Ini Texto Explicativo -->
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-8/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.textoExplicativo"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="motivo" scope="request">
				<form:textarea path="textoExplicativo" name="motivo" class="pae-form__input" style="overflow:hidden" rows="10" cols="200" maxlength="2000"/>
			</logic:present>
			<br>
			<logic:notPresent name="motivo" scope="request">
				<form:textarea path="textoExplicativo" style="overflow:hidden"  class="pae-form__input" rows="10" cols="200" maxlength="2000"/>
			</logic:notPresent>
		</div>
		</div>
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
				<c:choose>
				    <c:when test="${motivoReduccionTasaForm.visibilidad == true }">
				        <input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="checked">
				    </c:when>    
				    <c:otherwise>
						<input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check">
				    </c:otherwise>
				</c:choose>	
				<label for="visibilidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.general.visibilidadTitulo"/> 
					</span>
				</label>					
		</div>
		</div>
	<!-- Fin Visibilidad-->

</div>

	<div class="clear"></div>
	<div class="filaBtn">
	<div class="pae-box-buttons pd-right-1">
		<input type="submit" name="accion" value="Modificar" onclick="return validaDatos();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium"/>
		<input type="button" name="accion" value="Cancelar" onclick="volver()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>
	</div>
	</div>
	<div class="clear"></div>
	<br><br><br>
	
	</fieldset>	
</div>
</div>	
	
</form:form>
	
</div>
</body>
</html>


