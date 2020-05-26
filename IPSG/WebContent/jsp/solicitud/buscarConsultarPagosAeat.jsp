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

<%
String sVieneMenu = (String)request.getAttribute("sVieneMenu"); 

String sPerfilUsuario =  request.getAttribute("sPerfilUsuario").toString();
%>


<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 
	<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="pae-title"><spring:message code="field.consultarPagoAeat.consultarBuscar"/></div>
<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form modelAttribute="solicitudesIncidenciasForm" action="/IPSG/spring/buscarConsultarPagosAeat" id="formPadre">

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
	
	<%-- MAQUETACION INI --%>
	<div class="">
		<div class="pae-box__body">
			<fieldset>
				<div class="pae-layout">
					<%-- Ini Num. Justificante --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.consultarRegistrosRec.nJustificante.mayus"/>
							</span>
							<form:input path="numJustificante" id="numJustificante" class="pae-form__input"/>			
						</div>
					</div>
					<%-- Ini Num. Justificante --%>
					<%-- NIF --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudPresencial.nif.mayus"/>
							</span>
							<form:input path="nif" id="nif" class="pae-form__input" maxlength="9"/>					
						</div>
					</div>
					<%-- NIF --%>	
					<%-- Boton --%>
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
							<div class="pae-box-buttons">
								<input type="submit" name="submit" value="Buscar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>		
							</div>
						</div>
					</div>											
				</div>
			</fieldset>
		</div>
	</div>	
	<%-- MAQUETACION FIN --%>
	
	<logic:present name="errorConsulta" scope="request">
		<logic:equal name="errorConsulta" value="true">
			<bean:write name="mensaje"/><br><br>
			
			<logic:present name="codigoError" scope="request">
				<table id="paetable" class="pae-table pae-table--margin">
				<caption class="hiddenAccesible">Tabla de Pagos Aeat</caption>
					<thead class="pae-table__header">	
					<!-- Nombres de cabecera de la tabla de resultados -->	
					<tr class="pae-table__row">
						<th data-col="col1" class="pae-table__cell-header"><!-- Numero justificante-->
							<span class="pae-table__txt-title">
								<bean:message key="field.consultarPagoAeat.codigoError"/>		
							</span>		
						</th>
						<th data-col="col2" class="pae-table__cell-header"><!-- Fecha registro-->
							<span class="pae-table__txt-title">
								<bean:message key="field.consultarPagoAeat.descripcionError"/>	
							</span>			
						</th>
					</tr>
					</thead>
					<tr>
						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="codigoError"/></td>
						<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="descripcionError"/></td>
					</tr>
				</table>
			</logic:present>
		</logic:equal>
	</logic:present>
	<logic:present name="registros" scope="request">
		<bean:size id="numResultados" name="registros" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
				<table id="paetable" class="pae-table pae-table--margin">
				<caption class="hiddenAccesible">Tabla de Pagos Aeat resultados</caption>
				<thead class="pae-table__header">	
				<!-- Nombres de cabecera de la tabla de resultados -->	
				<tr class="pae-table__row">
					<th data-col="col3" class="pae-table__cell-header"><!-- Numero justificante-->
						<span class="pae-table__txt-title">
							<bean:message key="field.consultarPagoAeat.numJustificante.mayus"/>	
						</span>			
					</th>
					<th data-col="col3" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<bean:message key="field.consultarPagoAeat.nif.mayus"/>	
						</span>			
					</th>
					<th data-col="col3" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<bean:message key="field.consultarPagoAeat.codTasa.mayus"/>
						</span>				
					</th>
					<th data-col="col3" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<bean:message key="field.consultarPagoAeat.codBanco.mayus"/>
						</span>				
					</th>
					<th data-col="col3" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<bean:message key="field.consultarPagoAeat.nrc.mayus"/>	
						</span>			
					</th>
					<th data-col="col3" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<bean:message key="field.consultarPagoAeat.importe.mayus"/>
						</span>				
					</th>
					<th data-col="col3" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<bean:message key="field.consultarPagoAeat.fechaOperacion.mayus"/>		
						</span>		
					</th>					
				</tr>
				</thead>
				<logic:iterate id="DatosPagoIm" name="registros">
					<tr>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="DatosPagoIm" property="justificante"/></td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="DatosPagoIm" property="documentoObligado"/></td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="DatosPagoIm" property="codTasa"/></td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="DatosPagoIm" property="codigoBanco"/></td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="DatosPagoIm" property="nrc"/></td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="DatosPagoIm" property="importe"/></td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><fmt:formatDate value="${DatosPagoIm.fechaOperacion.time}" pattern="dd/MM/yyyy HH:mm" /></td>		
					</tr>				
				</logic:iterate>
			</table>
		</logic:greaterThan>	
	</logic:present>
	
</form:form>

	
</div>
</body>
</html>