<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
function ocultarErrores(){
	
}
</script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>
<body style="margin-left:0.4em;">

<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form commandName="solicitudPresencialForm" action="/IPSG/spring/generarModelo790Masivo" id="formPadre" method="post">

<div class="">
	
	
	<h1 class="pae-title"><spring:message code="field.modelo790Masivo.titulo"/></h1>    
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
<div data-function="accordion-box" class="pae-box">
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.modelo790Masivo.subTitulo"/>
		</h3>
	</div>
	<div class="pae-box__body">
		<fieldset>
			<!-- INICIO MODELO -->
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.modeloMay" />
						</span>
						 <form:select path="modelo" class="pae-form__input" onchange="comprobarBusqueda()">
							  <form:option value=""></form:option>
							  <form:options items="${listaModelos}" itemLabel="descripcion" itemValue="id"/>
	 					</form:select>			
 					</div>
				</div>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.modelo790Masivo.numModelos"/>
						</span>
						<form:input type="text" path="numModelos" class="pae-form__input" maxlength="2"/>
					</div>
				</div>
			</div>
	
		
	<span class="mensajeWarning">
		<b><spring:message code="field.modelo790Masivo.aviso"/></b>
	</span>
	<div class="filaBtn">
		<div class="pae-box-buttons pd-right-1">
			<input type="submit" name="accion" value="Generar" onclick="ocultarErrores()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">							
		</div>
	</div>
	
	<div class="clear"></div>
	</fieldset>
	</div>
	
</div>
		
</div>	
</form:form>
	
</div>
</body>
</html>


