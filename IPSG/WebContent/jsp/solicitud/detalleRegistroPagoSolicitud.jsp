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



<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">
<div class="pae-title"> <spring:message code="field.solicitud.detalleRegistroPago.titulo"/></div>
	<br>
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	<br>
	
	<div>
		<b><spring:message code="field.solicitud.detalleRegistroPago.pagos"/></b>
	</div>
	<logic:present name="pagos" scope="request">
		<bean:size id="numResultados" name="pagos" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla pago</caption>
				<thead class="pae-table__header">	
				<tr class="pae-table__row">	<!-- Nombres de cabecera de la tabla de resultados -->
					
					<th data-col="col3" class="pae-table__cell-header"><!-- Universidad -->
						<span class="pae-table__txt-title">
							<spring:message code="field.solicitud.detalleRegistroPago.pagos.fecha"/>
						</span>
					</th>
					<th data-col="col3" class="pae-table__cell-header"><!-- Nombre titulo-->
						<span class="pae-table__txt-title">
							<spring:message code="field.solicitud.detalleRegistroPago.pagos.resultado"/>
						</span>
					</th>
					<th data-col="col3" class="pae-table__cell-header"><!-- Causa Fallo-->
						<span class="pae-table__txt-title">
							<spring:message code="field.solicitud.detalleRegistroPago.pagos.causa"/>
						</span>
					</th>
					<th data-col="col3" class="pae-table__cell-header"><!-- Tipo Pago-->
						<span class="pae-table__txt-title">
							<spring:message code="field.solicitud.detalleRegistroPago.pagos.tipo"/>
						</span>
					</th>
					<th data-col="col3" class="pae-table__cell-header"><!-- Entidad Financiera-->
						<span class="pae-table__txt-title">
							<spring:message code="field.solicitud.detalleRegistroPago.pagos.entidad"/>
						</span>
					</th>	
								
				</tr> <!-- Resultados de la consulta -->
				</thead>
				<logic:iterate id="registro" name="pagos" >
					<tr>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
							<center>												
								<bean:write name="registro" property="fechaIntento" format="dd/MM/yyyy HH:mm"/>
							</center>
						</td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
							<center>
								<bean:write name="registro" property="resultado" />
							</center>
						</td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
							<center>
								<logic:equal name="registro" property="resultado" value="ER" >
									<bean:write name="registro" property="codError" /> - 
									<bean:write name="registro" property="desError" />
								</logic:equal>
							</center>
						</td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
							<center>
								<logic:equal name="registro" property="tipo" value="C" >
									Cuenta
								</logic:equal>
								<logic:equal name="registro" property="tipo" value="T" >
									Tarjeta
								</logic:equal>
								<logic:equal name="registro" property="tipo" value="E" >
									Exento
								</logic:equal>
							</center>
						</td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
							<center>
								<bean:write name="registro" property="desEntidadFinanciera" />
							</center>
						</td>											
		   			</tr>
				</logic:iterate>
			</table>
		</logic:greaterThan>	
		<logic:equal name="numResultados" value="0">
				<spring:message code="field.solicitud.detalleRegistroPago.pagos.noExisten"/>
		</logic:equal>
	</logic:present>	
	<logic:notPresent name="pagos" scope="request">
		<spring:message code="field.solicitud.detalleRegistroPago.pagos.noExisten"/>
	</logic:notPresent>
	
	<div class="clear"></div><br><br>
	
	<div>
		<b><spring:message code="field.solicitud.detalleRegistroPago.registros"/></b>
	</div>	
	<logic:present name="registros" scope="request">
		<bean:size id="numResultados" name="registros" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla registros</caption>
				<thead class="pae-table__header">	
					<tr class="pae-table__row">	<!-- Nombres de cabecera de la tabla de resultados -->
						
						<th data-col="col3" class="pae-table__cell-header"><!-- Fecha Registo -->
							<spring:message code="field.solicitud.detalleRegistroPago.registros.fecha"/>
						</th>
						<th data-col="col3" class="pae-table__cell-header"><!-- Resultado Registro-->
							<spring:message code="field.solicitud.detalleRegistroPago.registros.resultado"/>
						</th>
						<th data-col="col3" class="pae-table__cell-header"><!-- Causa Fallo-->
							<spring:message code="field.solicitud.detalleRegistroPago.registros.causa"/>
						</th>			
					</tr> <!-- Resultados de la consulta -->
				</thead>
				<logic:iterate id="registro" name="registros" >
					<tr>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
							<center>												
								<bean:write name="registro" property="fechaIntento" format="dd/MM/yyyy HH:mm"/>
							</center>
						</td>
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
							<center>
								<bean:write name="registro" property="resultado" />
							</center>
						</td>					
						<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
							<center>
								<bean:write name="registro" property="descripcionError" />
							</center>
						</td>							
		   			</tr>
				</logic:iterate>
			</table>
		</logic:greaterThan>	
		<logic:equal name="numResultados" value="0">
			<spring:message code="field.solicitud.detalleRegistroPago.registros.noExisten"/>
		</logic:equal>
	</logic:present>
	<logic:notPresent name="registros" scope="request">
		<spring:message code="field.solicitud.detalleRegistroPago.registros.noExisten"/>
	</logic:notPresent>

	<div class="clear"></div><br><br>
	<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="filaBtn">
			<div class="pae-box-buttons">	
				<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>
			</div>
		</div>
	</div>

</body>
</html>