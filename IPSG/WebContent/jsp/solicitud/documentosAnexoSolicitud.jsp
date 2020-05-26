<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>
<body style="margin-left:0.4em;" class="background-color-white-ip">
<div class="pae-body2" style="overflow-y:hidden">


<h1 class="pae-title2"><spring:message code="field.documento.documentosAnexados"/></h1>
<div class="capaAll">	
<div class="pae-box">
<div class="pae-box__header2">	
	<fieldset>
		<div class="pae-layout">
<div class="pae-layout__item">		
<table class="tabla_resultados">
<logic:present name="documentos" scope="request">
		<bean:size id="numResultados" name="documentos" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
			<logic:iterate id="registro" name="documentos" >
			<table id="paetable" class="pae-table pae-table--margin">
				<caption class="hiddenAccesible">Tabla documentos anexos</caption>
				<thead class="pae-table__header">				
				<tr class="pae-table__row"><!-- Nombres de cabecera de la tabla de resultados -->
					<th data-col="col1" class="pae-table__cell-header"></th>
				</tr>
				<tr>
					<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
						<bean:write name="registro" property="nombre" />: <bean:write name="registro" property="descripcion" />
						<br>						
						<a href="${pageContext.request.contextPath}/spring/descargarDocumentoSolicitud?ent=Solicitudes&id=${registro.id}" style="color: #c33400;">
				 			<font color="#c33400">
		 		   				<spring:message code="field.documento.descargaDocumento"/>
					 		</font>
					 	</a>
					</td>
				</tr>
			</thead>	
			</logic:iterate>
		</logic:greaterThan>	
		<logic:equal name="numResultados" value="0">
			<spring:message code="field.documento.noExistenDocumentos"/>
		</logic:equal>	
</logic:present>
</table>
</div>
<div class="pae-layout__item">
	<div class="filaBtn">
		<div class="pae-box-buttons">	
		<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
	</div>
</div>	
</div>	
	
</div>
</div>
</body>
</html>