<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="es.map.ipsg.util.Constantes"%>

<script type="text/javascript">

function comprobarInput(){
	document.getElementById("cambios").value = "Incorrecto";
}
function cancelar(){
	window.location.href = "../spring/buscarUsuariosAplicacion";
}
</script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="capaAll">

	<h1 class="pae-title"> <spring:message code="field.usuario.tituloMantenimientoUsuario.actualizar"/> </h1>
 	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present> 

<div data-function="accordion-box" class="pae-box">   
	
<form:form modelAttribute="usuariosAplicacionForm" action="/IPSG/spring/modificarUsuarioAplicacion" id="formPadre" method="post">
	<div class="pae-box__body">
	<fieldset id="formulario">
		 <div class="pae-layout">
		 	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
		   		<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.usuariosaplicacion.id"/>
					</span>
					<logic:present name="usuario" scope="request">
						<form:input path="id" name="cuerpoEscala" class="pae-form__input pae-box--transparent" maxlength="10" readonly="true"/>
					</logic:present>
					<logic:notPresent name="usuario" scope="request">
						<form:input path="id" class="pae-form__input pae-box--transparent" maxlength="10" readonly="true"/>
					</logic:notPresent>
				</div>
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.usuariosaplicacion.usuario"/><span class="obligatorio"> *</span>
					</span>
				<logic:present name="usuario" scope="request"> </logic:present>
				<form:input type="text" path="usuario" class="pae-form__input" id="usuario" maxlength="10"
					pattern="[a-zA-ZnÑ0-9]+" 
					title="No se admiten tildes, ni caracteres especiales."/>
				</div>
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.usuariosaplicacion.nombre"/><span class="obligatorio"> *</span>
					</span>
				<logic:present name="nombre" scope="request"> </logic:present>
				<form:input type="text" style="text-transform: uppercase;" path="nombre" class="pae-form__input" id="nombre" maxlength="50"/>
				</div>
			</div>
			<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.usuariosaplicacion.descripcion"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="descripcion" scope="request"></logic:present>
			<form:input type="text" style="text-transform: uppercase;" path="descripcion" class="pae-form__input" id="descripcion" maxlength="50"/>
			</div>
			</div>
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.estadoMay"/><span class="obligatorio"> *</span>
					</span>
					<form:select path="estado" class="pae-form__input" onchange="comprobarInput()">
						<form:option value="1"><spring:message code="field.estado.activo.mayus"/></form:option>
						<form:option value="0"><spring:message code="field.estado.inactivo.mayus"/></form:option>
					</form:select>
				</div>
			</div>
	</div>
	
	<div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" name="accion" value="Modificar" onclick="return validaDatos();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
			<input type="button" value="Cancelar" property="accion" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>										
		</div>
	</div>
</div>
</fieldset>
</form:form>
</div>  <!-- cierre class="pae-box__body" -->
</div>
</body>
</html>