<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
<script type="text/javascript">

function cancelar(){
	window.location.href = "../spring/buscarSolicitudPresencial";
}
</script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>
<body style="margin-left:0.4em;">

<h1 class="pae-title"><spring:message code="field.solicitudPresencial.tituloAlta"/></h1>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form modelAttribute="altaSolicitudPresencialForm" action="/IPSG/spring/crearSolicitudPresencial" id="formPadre" method="post">

<logic:present name="solicitudPresencial" scope="request">
	<form:hidden path="id" name="solicitudPresencial" />
</logic:present>

<div class="capaAll"><br>
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
		<div class="clear"></div>
	</div>
	</logic:present>
	<br>
	<div class="capaIzq"> <!-- inicio datos de convocatoria -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.datosConvocatoria.mayus"/>
		</div>
	</div>	
	<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"></div>
	<div class="capaIzq">	<!-- ini justificante -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.justificante.mayus"/>
		</div>
		<div class="contenidoDer">
			<form:input type="text" path="numeroSolicitud" size="20" maxlength="20"/>
		</div>
	</div>
	<div class="capaIzq">  <!-- ini convocatoria -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.convocatoria.mayus"/>
		</div>
		<div class="contenidoDer">
			<form:input type="text" path="idConvocatoria" size="20" maxlength="20"/>
		</div>
	</div>
	<br><br><br>
	<div class="capaIzq"> <!-- inicio datos de convocatoria -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.datosConvocatoria.mayus"/>
		</div>
	</div>	
	<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"></div>
	<div class="capaIzq">	<!-- ini nif -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.nif.mayus"/>
		</div>
		<div class="contenidoDer">
			<form:input type="text" path="nif" class="text_4" maxlength="10"/>
		</div>
	</div>
	<br>
	<div class="capaIzq">	<!-- ini nombre -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.nombre.mayus"/>
		</div>
		<div class="contenidoDer">
			<form:input type="text" path="nif" size="20" maxlength="50"/>
		</div>
	</div>
	<div class="capaDer">	<!-- ini primer apellido -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.primerApellido.mayus"/>
		</div>
		<div class="contenidoDer">
			<form:input type="text" path="primerApellido" size="20" maxlength="50"/>
		</div>
	</div>
	<br><br><br>
	<div class="capaIzq">	<!-- ini segundo apellido -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.segundoApellido.mayus"/>
		</div>
		<div class="contenidoDer">
			<form:input type="text" path="segundoApellido" size="20" maxlength="50"/>
		</div>
	</div>
	<br><br><br>
	<div class="capaIzq">	<!-- ini fecha nacimiento -->
		<div class="labelIzq">
			<spring:message code="field.solicitudPresencial.fechaNacimiento.mayus"/>
		</div>
		<div class="contenidoDer">
			<form:input type="text" path="fechaNacimiento" class="text_fecha" maxlength="10"/>
		</div>
	</div>	
	
		
	<div class="capaDer">
		<div class="labelIzq">
			<spring:message code="field.entidadFinanciera.tipoPago.mayus"/><span class="obligatorio"> *</span>
		</div>
		<div class="contenidoDer">
			<form:select path="idTipoPago" class="select_1" id="idTipoPago" >
				<logic:present name="tipoPago" scope="request">
					<bean:size id="numTipoPago" name="tipoPago" scope="request"/>
						<logic:greaterThan name="numTipoPago" value="0">
							<form:options items="${tipoPago}"  itemLabel="descripcion" itemValue="id"/>
						</logic:greaterThan>
				</logic:present>
					
		</div>
	</div>
	<div class="clear"></div><br>
	<div class="capaIzq">
		<div class="labelIzq">
			<spring:message code="field.entidadFinanciera.descripcion.mayus"/><span class="obligatorio"> *</span>
		</div>
		<div class="contenidoDer">
			<form:input type="text" path="descripcion" size="40" maxlength="100"/>
		</div>
	</div>	
	<div class="capaDer">
		<div class="labelIzq">
			<spring:message code="field.entidadFinanciera.estado.mayus"/><span class="obligatorio"> *</span>
		</div>
		<div class="contenidoDer">
			<form:select path="estado" class="select_1" id="estado" >
				<form:option value="A"><spring:message code="field.estado.activo.mayus"/></form:option>
				<form:option value="D"><spring:message code="field.estado.inactivo.mayus"/></form:option>
			</form:select>
		</div>
	</div>	
	<div class="clear"></div><br><br><br>
	<div class="filaBtn">
		<input type="submit" name="accion" value="Guardar" class="boton"/>
		<input type="button" name="submit" value="Cancelar" class="boton" onclick="cancelar()"/>
	</div>
	<div class="clear"></div>
	<br><br><br>		
</div>	
</form:form>
	
</div>
</body>
</html>


