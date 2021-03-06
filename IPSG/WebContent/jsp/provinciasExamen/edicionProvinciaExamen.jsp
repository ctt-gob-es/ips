<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
function cancelar(){
	window.location.href = "../spring/buscarProvinciasExamen";
}

</script>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title></title>
	</head>

	<body style="margin-left:0.4em;">

	<div class=" ">
      <h1 class="pae-title"> <spring:message code="field.provinciaExamen.modificacion.titulo"/> </h1>
    	


<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

	<div data-function="accordion-box" class="pae-box">   
<form:form commandName="modificarProvinciasExamenForm" action="/IPSG/spring/modificarProvinciaExamen" id="formPadre" method="post">


	<form:hidden path="estado" class="input_texto" id="estado"/>
	<form:hidden path="id" class="input_texto" id="id"/>
	<form:hidden path="submit" id="submit"/>


	
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
					<spring:message code="field.idProv"/>
				</span>
				<form:input type="text" path="id" class="pae-form__input pae-box--transparent"  id="id" maxlength="3" readonly="true"/>
			</div>
			</div>		
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.codigoProv"/><span class="obligatorio"> *</span>
				</span>
				<form:input type="text" path="codigo"  class="pae-form__input" id="codigo" maxlength="2"/>
			</div>
		</div>		
		<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label pae-ancho3">
					<spring:message code="field.descripcionProv"/><span class="obligatorio"> *</span>
				</span>
				<form:input type="text" path="descripcion" id="descripcion" maxlength="50"  class="pae-form__input"/>
			</div>
		</div>
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
			<div class="pae-form__cell">
					<c:choose>
				    <c:when test="${modificarProvinciasExamenForm.visibilidad == true }">
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
	</div>
		<!-- Fin Visibilidad-->
		

	<div class="clear"></div>
<div class="filaBtn">
	<div class="pae-box-buttons">
		<input type="submit" value="Modificar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
		<input type="button" value="Cancelar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1" onclick="cancelar()"/>
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