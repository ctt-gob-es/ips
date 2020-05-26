<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.util.Constantes" %>

<%String gestor = Constantes.PERFIL_GESTOR_INT+"";%>

<script language='javascript'>

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}

function limpiarCentroGestor(){
	var desCentro = document.getElementById("dsCentroGestor").value;
		if(desCentro != ""){
			document.getElementById("centroGestor").value = "";
			document.getElementById("dsCentroGestor").value = "";
		}
}

function openWindowCentroGestor() {
	var param = "centroGestor";
	var param2 = "dsCentroGestor";
	var extract = new Object();
		ventanaPopup = window.open("../spring/listarCentroGestor?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
}
function cancelar(){
	window.location.href = "../spring/buscarDiscapacidad";
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
<div class="">
    <h1 class="pae-title">  <spring:message code="field.discapacidad.tituloMantenimientoDiscapacidad"/> </h1>


<!--<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">-->


<form:form modelAttribute="discapacidadForm" action="/IPSG/spring/altaDiscapacidad" method="post">

<logic:present name="discapacidad" scope="request"><form:hidden path="id" name="discapacidad" /></logic:present>

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
	 <div data-function="accordion-box" class="pae-box">  
	 	<div class="pae-box__body">
			<fieldset>
			<div class="pae-layout">
	<!-- INI - cpasculi - IPS-148 - Campo CentroGestor-->
				<logic:present name="rol" scope="request">
					<logic:equal name="rol" value="<%=gestor%>">		
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.centroMay"/><span class="obligatorio"> *</span>
									</span>
									<!--INI-TRA042-->
									<logic:present name="listaCentrosGestores" scope="request">
										<div class="pae-form__cell pae-u-9/12">
											<form:select path="centroGestor" class="pae-form__input" onchange="javascript: if(this.value!=''){comprobar();this.form.submit();}">
												<option value=""></option>
												<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
											</form:select>
										</div>
									</logic:present>
									<!--FIN-TRA042-->
								</div>
							</div>			
					</logic:equal>
				</logic:present>
		
				<logic:present name="rol" scope="request">
					<logic:notEqual name="rol" value="<%=gestor%>">	
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.centroMay"/><span class="obligatorio"> *</span>
									</span>
									<form:input path="centroGestor" class="pae-form__input" id="centroGestor" maxlength="5" readonly="true" onchange="comprobarBusqueda()" onclick="limpiarCentroGestor()"></form:input>
									<form:input path="dsCentroGestor" id="dsCentroGestor" class="input_texto_border0 em100" readonly="true"></form:input>
									</div>
								</div>
		<!-- 						<input type="image" src="../images/lupa.png" alt="Buscar Centro Gestor" onclick="return openWindowCentroGestor()"> -->
								<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn pd-left-2">
									<br>
									<input type="button"
									class="pae-form__btn-search" alt="Buscar Centro Gestor"
									onclick="return openWindowCentroGestor()" style="margin-left:0em;"> 
									
								</div>						
						</logic:notEqual>
					</logic:present>
					<!-- FIN - cpasculi - IPS-148 - Campo CentroGestor-->
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
					  <div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.discapacidad.descripcion"/><span class="obligatorio"> *</span>
						</span>
						<form:input path="descripcion" class="pae-form__input" maxlength="100"/>
					  </div>
					</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
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
 
	<div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" name="accion" value="Guardar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium">
			<input type="button" value="Cancelar" name="submit " onclick="cancelar()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>
		</div>
	</div>	

		
	
	<div class="clear"></div>
	
</fieldset>	
</div>
</div>
</div>
</div>
</form:form>
 </div>  <!--cierre capaAll -->
</body>
</html>	
