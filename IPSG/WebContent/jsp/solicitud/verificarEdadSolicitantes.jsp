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
<jsp:useBean id="convocatorias" class="java.util.ArrayList" scope="request"></jsp:useBean> 

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

 <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery/jquery.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/core.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/widget.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/mouse.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/position.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/draggable.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/resizable.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/menu.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/selectmenu.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/tabs.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/button.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/datepicker.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/dialog.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/progressbar.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/accordion.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/tooltip.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/tablesorter/jquery.tablesorter.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/bootstrap-sass/assets/javascripts/bootstrap.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/autocomplete.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/selectize/dist/js/standalone/selectize.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-placeholder/jquery.placeholder.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/scripts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script> 
  
<script type="text/javascript">
window.onunload = refreshParent;
function refreshParent() {
    window.opener.location.reload();
}

function verificarEdades()
{
	if(confirm('<spring:message code="field.verificarEdad.mensajeConfirma"/>'))
	{
			document.getElementById("accion").value="VERIFICAR";
			document.getElementById("submit").value="Verificar";
			document.forms[0].action = "../spring/verificarEdadSolicitantes";
			return true;
	}
	else
	{
		return false;
	}
}
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;" class="background-color-white-ip">

<div class="pae-body2" style="overflow-y:hidden">				
<form:form modelAttribute="buscarSolicitudesForm" action="/IPSG/spring/verificarEdadSolicitantes" id="formPadre" >
<div class="pd-top-3">	
<div class="pae-layout__item">	

	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<input type="hidden" name="menu"  id="menu" value="N"/>	
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="cambios" id="cambios"/>
	
	
	

	
	<%-- MAQUETACION INI --%>
	<h1 class="pae-title2"><spring:message code="field.verificarEdad.titulo"/></h1>
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	<div data-function="" class="pae-box">
	<div class="pae-box__header2">
		<h3 class="pae-box__header--title"><spring:message code="field.verificarEdad.explicacion"/></h3>
		
		<fieldset>
			<div class="pae-layout">
			<!-- Ini Fecha Desde -->
			<div class="pae-layout__item">
				<div class="pae-form__cell">
					<label for="fechaNacimiento" class="pae-form__label">
						<span class="pae-form__label--text">
							<spring:message code="field.verificarEdad.fechamin.mayus"/><span class="obligatorio"> *</span>
						</span>
					</label>
					<div class="pae-form__box-datepicker">
						<form:input type="text" path="fechaMin" value="${verificarEdadSolicitantesForm.fechaMin }"
							name="fechaMin" data-function="function-datepicker"
							placeholder="dd/mm/aaaa"  class="pae-form__input w100"/>
					</div>
				</div>
			</div>	
			</div>				
			<!-- Fin Fecha Desde -->
			<!-- Ini Fecha Hasta -->	
			<div class="pae-layout">			
			<div class="pae-layout__item">
				<div class="pae-form__cell">
					<label for="fechaNacimiento" class="pae-form__label">
						<span class="pae-form__label--text">
							<spring:message code="field.verificarEdad.fechamax.mayus"/><span class="obligatorio"> *</span>
						</span>
					</label>
					<div class="pae-form__box-datepicker">
						<form:input type="text" path="fechaMax" value="${verificarEdadSolicitantesForm.fechaMax }"
							name="fechaMax" data-function="function-datepicker"
							placeholder="dd/mm/aaaa" class="pae-form__input w100"/>
					</div>
				</div>
			</div>		
			</div>			
			<!-- Fin Fecha Hasta -->
			<%-- Boton --%>
			<div class="filaBtn">
					<div class="pae-box-buttons">
						<input type="submit" name="verificar" value="Verificar edades" onclick="return verificarEdades()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>			
						<input type="button" value="Cerrar" name="volver" onclick="window.close();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
					</div>
			</div>
			<%-- Boton --%>
		</fieldset>
	</div>			
	<%-- MAQUETACION FIN --%>
	
	
</div>	
	
	
	
<!-- Parte del listado de convocatorias -->
	<logic:present name="solicitudes" scope="request">	
		<bean:size id="numResultados" name="solicitudes" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		
		<table id="paetable" class="pae-table2" style="width: 100%;">
			<caption class="hiddenAccesible">Tabla edad Solicitantes</caption>
			<thead class="pae-table__header">	
			<tr class="pae-table__row" style="width: 100%;"><!-- Nombres de cabecera de la tabla de resultados -->
				<th data-col="col3" class="pae-table__cell-header">
					<span class="pae-table__txt-title">
						<spring:message code="field.verificarEdad.numSolicitud"/>
					</span>
				</th>
				<th data-col="col3" class="pae-table__cell-header">
					<span class="pae-table__txt-title">
						<spring:message code="field.verificarEdad.nif"/>
					</span>
				</th>
				<th data-col="col3" class="pae-table__cell-header">
					<span class="pae-table__txt-title">
						<spring:message code="field.verificarEdad.idConvocatoria"/>
					</span>
				</th>
				<th data-col="col3" class="pae-table__cell-header">
					<span class="pae-table__txt-title">
						<spring:message code="field.verificarEdad.tipoSolicitud"/>
					</span>
				</th>
			</tr>
			</thead>
			<!-- Resultados de la consulta -->
			<c:forEach var="solicitud" items="${solicitudes}">
			<tr>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">${solicitud.numeroSolicitud}</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">${solicitud.nif}</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">${solicitud.idConvocatoria}</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">${solicitud.tipoDescripcion}</td>
			</tr>		
    				<form:hidden path="idConvocatoria" value="${solicitud.idConvocatoria}" />
    		</c:forEach>
		</table>
		</logic:greaterThan>		
		<logic:lessThan name="numResultados" value="1">
		<div data-function="accordion-box" class="pae-box">
			<div class="pae-box__header">
				<h3 class="pae-box__header--title">
					<spring:message code="field.verificarEdad.error"/>
				</h3>
			</div>
		</div>
<!-- 				<div id="error"> -->
<%-- 					<spring:message code="field.verificarEdad.error"/> --%>
<!-- 				</div> -->
		</logic:lessThan>
	</logic:present>
	
</div>
</div>
</form:form>
</div>

</body>
</html>