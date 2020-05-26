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
function borraDescripcion() //Para no devolver el parámetro al volver
{
	document.getElementById("descripcion").value = '';

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
	
  <h1 class="pae-title"> <spring:message code="field.categoria.MantenimientoTitulo"/> </h1>
    
<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<div data-function="accordion-box" class="pae-box">
<form:form modelAttribute="categoria" action="/IPSG/spring/modificarCategoria">
 
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
		<div class="clear"></div><br>
	</div>
	</logic:present>
	<div class="clear"></div>
	<br>
	<div class="pae-box__body">
		<fieldset>
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
					<!-- Ini ID -->
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.categoria.idMay"/>
						</span>
						<logic:present name="categoria" scope="request">
							<form:input path="id" name="categoria" 
							 class="pae-form__input pae-box--transparent" readonly="true"/>
						</logic:present>
						<logic:notPresent name="categoria" scope="request">
							<form:input path="id" 
							class="pae-form__input pae-box--transparent" readonly="true"/>
						</logic:notPresent>
					</div>
				</div>
				
	
				<!-- Fin ID -->
				<!-- Ini Descripción -->
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.descripcionMay"/><span class="obligatorio"> *</span>
						</span>
					<logic:present name="categoria" scope="request">
						<form:input path="descripcion" name="categoria" id="descripcion"  class="pae-form__input" maxlength="100"/>
					</logic:present>
					<logic:notPresent name="categoria" scope="request">
						<form:input path="descripcion" id="descripcion"  class="pae-form__input" maxlength="100"/>
					</logic:notPresent>
					</div>				
				</div>
		</div>
	<!-- Fin Descripción -->	
	<!-- Ini Código -->
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.codigoMay"/>
				</span>
			<logic:present name="categoria" scope="request">
				<form:input path="codigo" name="categoria" class="pae-form__input" maxlength="10"/>
			</logic:present>
			<logic:notPresent name="categoria" scope="request">
				<form:input path="codigo" class="pae-form__input" maxlength="10"/>
			</logic:notPresent>
			</div>
		</div>
	</div>
	<!-- Fin Código -->
	
	<!-- Ini Visibilidad-->
	<logic:present name="categoria" scope="request">	     
		   <div class="pae-form__cell">
		   		<c:choose>
				    <c:when test="${categoria.visibilidad == true }">
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
		</logic:present>
	<!-- Fin Visibilidad-->

<div class="filaBtn">
		<div class="pae-box-buttons">	
				<input type="submit" name="submit" value="Modificar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
				<input type="button" value="Cancelar" name="accion" onclick="volver('buscarCategoria')" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1	"/>
			</div>
		</div>
	
</fieldset>	
</div>
	
</form:form>
	
</div>
</div>
</body>
</html>


