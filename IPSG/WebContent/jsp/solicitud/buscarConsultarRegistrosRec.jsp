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


<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form modelAttribute="consultarRegistrosRecForm" action="/IPSG/spring/buscarConsultarRegistrosRec">

	
	<%-- MAQUETACION INI --%>
	<div class="">
		<h1 class="pae-title"><spring:message code="field.consultarRegistrosRec.consultarBuscar"/></h1>
		
		<logic:present name="org.apache.spring.ERROR">
		<div>
			<html:errors/>
		</div>
		<div class="clear"></div>
		</logic:present>	
		
		<div data-function="accordion-box" class="pae-box">
			<div class="pae-box__header">
				<h3 class="pae-box__header--title">
					<spring:message code="field.consultarRegistrosRec.consultarBuscar.detalle"/>
				</h3>
			</div>
			<div class="pae-box__body">
				<fieldset>
					<div class="pae-layout">
						<%-- NIF --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.consultarRegistrosRec.nJustificante.mayus"/>
								</span>
								<form:input path="numJustificante" id="numJustificante" class="pae-form__input" maxlength="100"/>				
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
						<%-- Boton --%>
					</div>
				</fieldset>
			</div>
		</div>
	</div>				
	<%-- MAQUETACION FIN --%>
	
	<logic:present name="registros" scope="request">
		<bean:size id="numResultados" name="registros" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
			<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla de registros del REC</caption>
			<thead class="pae-table__header">	
				<!-- Nombres de cabecera de la tabla de resultados -->	
				<tr class="pae-table__row">
					<%-- -- <th>Numero justificante
						<bean:message key="field.consultarRegistrosRec.numJustificante.mayus"/>				
					</th>--%>

					<th data-col="col4" style="width: 43.6% !important;" class="pae-table__cell-header"><!-- Numero registro-->
						<span class="pae-table__txt-title">
							<spring:message code="field.consultarRegistrosRec.numRegistro.mayus"/>
						</span>					
					</th>
					<th data-col="col4" style="width: 43.6% !important;" class="pae-table__cell-header"><!-- Codigo oficina-->
						<span class="pae-table__txt-title">
							<spring:message code="field.consultarRegistrosRec.cdOficina.mayus"/>	
						</span>				
					</th>
					<th data-col="col4" style="width: 40.6% !important;" class="pae-table__cell-header"><!-- Fecha registro-->
						<span class="pae-table__txt-title">
							<spring:message code="field.consultarRegistrosRec.fechaRegistro.mayus"/>	
						</span>			
					</th>
				</tr>
				
				<logic:iterate id="tupla" name="registros">
					<tr>
						<!--<td><bean:write name="tupla" property="nomDocumento"/></td>-->
						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="tupla" property="numRegistro"/></td>
						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="tupla" property="cdOficinaOrigen"/></td>
						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body"><bean:write name="tupla" property="feRegistro"/></td>						
					</tr>				
				</logic:iterate>
			</thead>
			</table>
		</logic:greaterThan>	
	</logic:present>
	
</form:form>

	
</div>
</body>
</html>