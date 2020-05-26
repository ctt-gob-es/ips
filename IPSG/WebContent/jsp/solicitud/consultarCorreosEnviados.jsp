<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
<%@page import="es.map.ipsg.util.Constantes"%>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<script>
function openWindowVerEmail(idCorreo){	
	ventanaPopup = window.open("../spring/verCorreo?correo=" + idCorreo, 'ventanaVerCorreo', 'width=700, height=570,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
}
</script>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:0.4em;" class="background-color-white-ip">


<div class="pae-body2" style="overflow-y:hidden">
<div class="pae-title2"><spring:message code="field.correo.consultarEnviados"/></div>

<form:form modelAttribute="correos" action="/IPSG/spring/buscarSolicitudesIncidencias" id="formPadre" >
		
	<input type="hidden" path="paginaActual" id="paginaActual"/>
	<input type="hidden" path="paginasTotales" id="paginasTotales"/>
	<input type="hidden" path="submit" id="submit"/>
	<input type="hidden" path="campo" id="campo"/>
	<input type="hidden" path="direccion" id="direccion"/>
	<input type="hidden" path="menu" id="menu" value="N"/>
	<input type="hidden" path="accion" id="accion"/>
	<input type="hidden" path="cambios" id="cambios"/>
	<input type="hidden" path="perfilUsuario" id="perfilUsuario"/>
	
<div class="pae-box">
 	<div class="pae-box__header2">
<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
<br>
	
	<table id="paetable" class="pae-table pae-table--margin">
	<caption class="hiddenAccesible">Tabla de correos enviados</caption>
	<logic:present name="correos" scope="request">
		<bean:size id="numResultados" name="correos" scope="request"/>
			<logic:greaterThan name="numResultados" value="0">
				<thead class="pae-table__header">	
					<tr class="pae-table__row"><!-- Nombres de cabecera de la tabla de resultados -->		
						<th data-col="col1" class="pae-table__cell-header"><!-- Fecha Envio Correo -->
							<spring:message code="field.correo.fechaEnvio"/>
						</th>
						<th data-col="col2" class="pae-table__cell-header"><!-- Asunto-->
							<spring:message code="field.correo.descripcionAsunto"/>
						</th>						
					</tr> <!-- Resultados de la consulta -->
				</thead>
				<logic:iterate id="registro" name="correos" >
					<tr>
						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body"><center>												
							<a href="#" style="color: #c33400;"  onclick="return openWindowVerEmail(${registro.id})">
								<bean:write name="registro" property="fechaEnvio" format="dd/MM/yyyy"/>
							</a>
						</center></td>			
						<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body"><center><bean:write name="registro" property="asunto" /></center></td>												
		   			</tr>
				</logic:iterate> 
			</logic:greaterThan>
			<logic:equal name="numResultados" value="0">
				<spring:message code="field.documento.noExistenCorreos"/>
		</logic:equal>
	</logic:present>
	</table>


<%-- Boton --%>
		<div class="pae-layout">
			<div class="filaBtn">
				<div class="pae-box-buttons">	
					<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</form:form>
	
</div>
</body>
</html>