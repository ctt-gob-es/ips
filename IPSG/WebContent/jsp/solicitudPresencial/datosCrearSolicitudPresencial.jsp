<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.map.ipsg.util.Constantes" %>

<script>

function aceptar(){
	datos = document.getElementById("datosSolicitud").value;
	numero = document.getElementById("numeroSolicitud").value;
	if((numero == "" && datos != "") || (numero != "" && datos == "")){
		document.getElementById("accion").value="ALTA";
		document.forms[1].action = "../spring/verAltaSolicitudPresencial";
		return true;
	}else if((numero == "" && datos == "")){
		$("#error2").html("<div id='rellenarCampos2' class='pae-alertbox pae-alertbox--warning'><p class='pae-alertbox__text fw-bold'>Errores:</p><ul><li><p class='pae-alertbox__text'>Inserta algún campo a buscar.</p></li></ul></div>");
		$("#error2").show();
		$("#error").hide();
		return false;
	}else if(numero != "" && datos != ""){
		$("#error2").html("<div id='rellenarCampos2' class='pae-alertbox pae-alertbox--warning'><p class='pae-alertbox__text fw-bold'>Errores:</p><ul><li><p class='pae-alertbox__text'>Solo puede haber un campo insertado.</p></li></ul></div>");
		$("#error2").show();
		$("#error").hide();
		return false;
	}
}

function cancelar()
{
	document.getElementById("accion").value="VOLVER";
	document.forms[0].action = "../spring/verCrearSolicitudPresencial"; //Va a busqueda de solicitudes
}
function setFocus(){
	document.getElementById("datosSolicitud").focus();
}
</script>

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>
<div id="">
<body onload="setFocus();">
<!-- <div style="margin-left: 1em; margin-right: 1em; float: left; width: 95%;"> -->


<form:form commandName="altaSolicitudPresencialForm" action="/IPSG/spring/verCrearSolicitudPresencial" id="formPadre" method="post">
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="menu" id="menu" value="S"/>

<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
</logic:present>

	<div id="error2" style="display:none">
		<div id="rellenarCampos2" class="pae-alertbox pae-alertbox--warning"><p class="pae-alertbox__text fw-bold">Errores:</p><ul><li><p class="pae-alertbox__text">No se encuentra la solicitud asociada al número de justificante gfgfdg.</p></li></ul></div>
	</div>
	<div class="clear"></div>

<h1 class="pae-title"><spring:message code="field.solicitudPresencial.tituloAlta" /></h1>

<div data-function="accordion-box" class="pae-box">
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.solicitudPresencial.explicacion"/>
		</h3>
	</div>
	<div class="pae-box__body">
		<fieldset>
			<!-- INICIO CAMPO BLANCO -->
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-8/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<form:textarea path="datosSolicitud" rows="8" cols="100" style="width: 85%;overflow-x:hidden; overflow-y:scroll;"></form:textarea>
 					</div>
				</div>
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.menuLateral.solicitudesPresenciales.NumJustificante"/>
						</span>
						<form:input path="numeroSolicitud" value="" class="pae-form__input" maxlength="13"/>		
 					</div>
				</div>
	    	</div>
    	<!-- Fin Número Solicitud -->
		
		<div class="filaBtn">
			<div class="pae-box-buttons pd-right-1">
				<input type="submit" value="Aceptar" onclick="if(!aceptar()){return false;}" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
				<logic:notEqual name="usuario" property="desPerfil" value="<%=Constantes.DENOM_PERFIL_REGISTRO%>">
					<input type="submit" value="Cancelar" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>		
				</logic:notEqual>									
			</div>
		</div>
		
		<div class="clear"></div>
		</fieldset>
	</div>	
</form:form>

</body>
</div>
</html>