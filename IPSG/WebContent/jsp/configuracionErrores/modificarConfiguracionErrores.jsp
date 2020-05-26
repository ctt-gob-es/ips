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

</script>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language='javascript' src="../javascript/utils.js"></script> 
<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="capaAll">

  <h1 class="pae-title"> <spring:message code="field.configuracionErrores.MantenimientoTitulo"/> </h1>
     
<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form commandName="configuracionErroresForm" action="/IPSG/spring/modificarConfiguracionErrores" id="formPadre" method="post">


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
					<spring:message code="field.configuracionErrores.id.mayus"/>
				</span>
			<logic:present name="configuracionErrores" scope="request">
				<form:input type="text" path="id" name="configuracionErrores"  class="pae-form__input pae-box--transparent" readonly="true" maxlength="5"/>
			</logic:present>
			<logic:notPresent name="configuracionErrores" scope="request">
				<form:input type="text" path="id" class="pae-form__input pae-box--transparent" readonly="true" maxlength="5"/>
			</logic:notPresent>
			</div>
			</div>
		
	<!-- Fin ID -->
	
		<div class="pae-layout__item pae-u-8/12 pae-u-4/12-lap pae-u-1/1-palm pd-top-1_7">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.descripcionMay"/><span class="obligatorio"> *</span>
			</span>
			<form:input type="text" path="descripcion" class="pae-form__input" maxlength="200"/>
		</div>
		</div>
	<!-- Fin Descripción -->
							
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
		<div class="pae-form__cell">
				<c:choose>
				    <c:when test="${configuracionErroresForm.visibilidad == true }">
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


	<div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" value="Modificar" name="accion" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
			<input type="button" value="Cancelar" name="accion" onclick="volver('../spring/buscarConfiguracionErrores')" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>
		</div>
		</div>


</fieldset>	
</div>
</div>	
	
</form:form>
	
</div>  <!--cierre capaAll -->
</body>
</html>


