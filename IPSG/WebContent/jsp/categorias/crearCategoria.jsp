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
function cancelar(){
	window.location.href = "../spring/buscarCategoria";
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
<body style="margin-left:0.4em;" onload="marcar()">

<div class="capaAll">
	
 <h1 class="pae-title"> <spring:message code="field.categoria.crearTituloCategoria"/> </h1>

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
 <div data-function="accordion-box" class="pae-box">   

<form:form modelAttribute="categoriaForm" action="/IPSG/spring/crearCategoria" id="formPadre" method="post">

<logic:present name="formasAcceso" scope="request">
	<form:hidden path="id" name="formasAcceso" />
</logic:present>

	<div class="pae-box__body">
		<fieldset>
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
					<div class="pae-form__cell">
						<!-- Ini Descripción -->
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.descripcionMay"/><span class="obligatorio"> *</span>
						</span>
						<form:input path="descripcion" class="pae-form__input" maxlength="100"/>
		
						<div class="clear"></div>
					</div>
				</div>
	<!-- Fin Descripción -->
	<!-- Ini Código -->
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.codigoMay"/>
							</span>
							<form:input path="codigo" class="pae-form__input" maxlength="10"/>
						</div>
					</div>
		<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
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
	<div class="clear"></div>
	<!-- Fin Visibilidad-->
	<br><br>
	

<div class="clear"></div> 

 		<div class="filaBtn">
		<div class="pae-box-buttons">
				<input type="submit" name="accion" value="Guardar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
				<input type="button" value="Cancelar" name="submit" onclick="cancelar()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>		
			</div>
		</div>

<!-- 	<div class="clear"></div> -->

	
</fieldset>	
</div>

</form:form>

</div>
</div>
</body>
</html>


