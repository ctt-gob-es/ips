<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:0.4em;">
<div class="pae-title"><spring:message code="field.consultaLotes"/></div>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">
<div class="pae-title"><spring:message code="field.consultaLotes.peticiones"/></div>
	
	<logic:present name="informacionLotesBean" scope="request">
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla lotes</caption>
				<thead class="pae-table__header">	
					<tr class="pae-table__row">	
						<th data-col="col1" class="pae-table__cell-header"><!-- Identificador Petición -->
							<span class="pae-table__txt-title">
								<spring:message code="field.consultaLotes.idPeticion"/>
							</span>
						</th>
						<th data-col="col2" class="pae-table__cell-header"><!-- Consulta realiazada -->
							<span class="pae-table__txt-title">
								<spring:message code="field.consultaLotes.consulta"/>
							</span>
						</th>					
					</tr> <!-- Resultados de la consulta -->
				</thead>
				<logic:iterate id="registro" name="informacionLotesBean" >
					<tr>
						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
							<bean:write name="registro" property="idPeticion"/>
						</td>
						<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
							<bean:write name="registro" property="descripcionConsulta" />
						</td>					
												
		   			</tr>
				</logic:iterate>
			</table>
	</logic:present>
	
	<div class="clear"></div><br><br>

	<div class="clear"></div><br><br>
	<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="filaBtn">
			<div class="pae-box-buttons">		
				<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>
			</div>
		</div>
	</div>	
	</div>	
</body>
</html>