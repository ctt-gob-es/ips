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
function borraDescripcion() //Para no devolver el par�metro al volver
{
	document.getElementById("codigo").value = '';
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

<body style="margin-left:0.4em;" onload="">

<div class="capaAll">
    <h1 class="pae-title">  <spring:message code="field.titulo.tituloMantenimientoTitulo"/> </h1>

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form modelAttribute="titulo" action="/IPSG/spring/modificarTituloOficial" method="post">

<logic:present name="titulo" scope="request">
	<form:hidden path="id" name="titulo" />	
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
	     <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		   <div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.titulo.id.mayus"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="titulo" scope="request">
				<form:input path="id" name="titulo" class="pae-form__input pae-box--transparent" readonly="true"/>
			</logic:present>
			<logic:notPresent name="titulo" scope="request">
				<form:input path="id" class="pae-form__input pae-box--transparent" readonly="true"/>
			</logic:notPresent>
		</div>	
		</div>

	     <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		   <div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.codigoMay"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="titulo" scope="request">
				<form:input name="titulo" path="codigo" id="codigo" class="pae-form__input" maxlength="5"/>
			</logic:present>
			<logic:notPresent name="titulo" scope="request">
				<form:input path="codigo" class="pae-form__input" maxlength="5" />
			</logic:notPresent>
		</div>
		</div>
	     <div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		   <div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.tituloMay"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="titulo" scope="request">
				<form:input path="descripcion" name="titulo" id="descripcion" class="pae-form__input" maxlength="100"/>
			</logic:present>
			<logic:notPresent name="titulo" scope="request">
				<form:input path="descripcion" class="pae-form__input" maxlength="100" />
			</logic:notPresent>
		</div>
		</div>
	     <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
		   <div class="pae-form__cell">
				<logic:present name="titulo" scope="request">
					<c:choose>
					    <c:when test="${titulo.visibilidad == true }">
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
				</logic:present>		
			</div>
		</div>
	</div>
	<!-- Fin Visibilidad-->	
	
 		<div class="filaBtn">
		<div class="pae-box-buttons">
				<input type="submit" name="accion" value="Modificar"class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
				<input type="button" value="Cancelar" name="accion" onclick="volver('../spring/buscarTituloOficial');"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
			</div>
		</div>

	
</form:form>
</fieldset>
</div>		

</body>
</html>


