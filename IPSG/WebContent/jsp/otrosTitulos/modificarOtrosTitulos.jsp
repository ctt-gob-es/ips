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

</script>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language='javascript' src="../javascript/utils.js"></script> 
<title></title>
</head>

<body style="margin-left:0.4em;" >


<!--<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">-->

<form:form modelAttribute="otrosTitulosForm" action="/IPSG/spring/modificarOtrosTitulos" method="post">

<div class="capaAll">

	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	
	 <h1 class="pae-title"> <spring:message code="field.otrosTitulos.tituloMantenimientoOtrosTitulos"/> </h1>

<div data-function="accordion-box" class="pae-box">   
   <div class="pae-box__body">
	  <fieldset id="formulario">
	<!-- Ini ID -->
	 <div class="pae-layout">
	     <div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		   <div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.otrosTitulos.id"/>
				</span>
				<logic:present name="otrosTitulos" scope="request">
				<form:input path="id" name="otrosTitulos" class="pae-form__input pae-box--transparent" maxlength="10" readonly="true"/>
				</logic:present>
				<logic:notPresent name="otrosTitulos" scope="request">
				<form:input path="id" class="pae-form__input pae-box--transparent" maxlength="10" readonly="true"/>
				</logic:notPresent>
			</div>
		</div>
		<logic:present name="rol" scope="request">
			<logic:equal name="rol" value="<%=gestor%>">
				<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			</logic:equal>
			<logic:notEqual name="rol" value="<%=gestor%>">
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			</logic:notEqual>
		</logic:present>
		   <div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.centroMay"/><span class="obligatorio"> *</span>
			</span>
				<logic:present name="rol" scope="request">
					<logic:equal name="rol" value="<%=gestor%>">	
						<!--INI-TRA042-->
						<logic:present name="listaCentrosGestores" scope="request">
							<div class="pae-form__cell pae-u-9/12">
								<form:select path="centroGestor" class="pae-form__input">
									<option value=""></option>
									<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
								</form:select>
							</div>
						</logic:present>
						<!--FIN-TRA042-->
					</logic:equal>
					<logic:notEqual name="rol" value="<%=gestor%>">	
						<form:input path="centroGestor" class="pae-form__input" id="centroGestor" maxlength="5" readonly="true" onchange="comprobarBusqueda()" onclick="limpiarCentroGestor()"></form:input>
					</logic:notEqual>
				</logic:present>
				<form:input path="dsCentroGestor" id="dsCentroGestor" class="input_texto_border0 em100" readonly="true"></form:input>
			</div>
		</div>	
		
		<logic:present name="rol" scope="request">
		<logic:notEqual name="rol" value="<%=gestor%>">
		<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm search_btn2">
			<div class="pae-form__cell">
				<input type="button"
				class="pae-form__btn-search" alt="Buscar Centro Gestor"
				onclick="return openWindowCentroGestor()"> 
			</div>
		</div>
		</logic:notEqual>
		</logic:present>


	     <div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		   <div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.otrosTitulos.descripcion"/><span class="obligatorio"> *</span>
				</span>
			<logic:present name="otrosTitulos" scope="request">
				<form:input path="descripcion" name="otrosTitulos" class="pae-form__input" maxlength="100"/>
			</logic:present>
			<logic:notPresent name="otrosTitulos" scope="request">
				<form:input path="descripcion"  class="pae-form__input" maxlength="100"/>
			</logic:notPresent>
			</div>
		</div>	
		<!-- Fin Descripción -->
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
			<!-- Ini Visibilidad-->
			<logic:present name="otrosTitulos" scope="request">
		     <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-check">
			   <div class="pae-form__cell">
			   		<c:choose>
					    <c:when test="${otrosTitulos.visibilidad == true }">
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
			</logic:present>
		</div>
	</div>

	</div>
						
	 <!-- cierre class="pae-layout" -->	
	<!-- Fin Visibilidad-->
	<br><br><br>
	<!--  Fin Grupo -->
	<div class="clear"></div> 
	
 		<div class="filaBtn">
			<div class="pae-box-buttons">
				<input type="submit" name="accion" value="Modificar" onclick="return validaDatos();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">		
				<input type="button" value="Cancelar " name="submit" onclick="volver('../spring/buscarOtrosTitulos')" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
			</div>
		</div>

<div class="clear"></div> 
	<br>
	
</form:form>
</fieldset>
</div> <!-- cierre class="pae-box__body" -->
</div> <!-- cierre class="pae-box"-->
</div> <!-- cierre capaAll -->
</body>
</html>


