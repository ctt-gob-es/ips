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
<body style="margin-left:0.4em;">
<div style="margin-left:1em; margin-right: 1em;float: left;width: 95%;">
<div class="pae-title"><spring:message code="field.documento.documentosAdjuntos"/></div>	

<table id="paetable" class="pae-table pae-table--margin">
<caption class="hiddenAccesible">Tabla adjuntos</caption>
<logic:present name="documentos" scope="request">
		<bean:size id="numResultados" name="documentos" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
			<logic:iterate id="registro" name="documentos" >
				<tr>
					<th>
						<td>
							<bean:write name="registro" property="nombre" />: <bean:write name="registro" property="descripcion" />							
							<br><a href="../spring/descargarDocumentoAdjunto?ent=Correos?id=${registro.id}"">
					 		   <font color="#c33400">
					 		   			<spring:message code="field.documento.descargaDocumento"/>
					 		   		</font>
					 		   	</a>
						</td>
					
				</tr>
			</logic:iterate>
		</logic:greaterThan>	
		<logic:equal name="numResultados" value="0">
			<spring:message code="field.documento.noExistenDocumentos"/>
		</logic:equal>	
</logic:present>
</table>

<hr>

</div>
</body>
</html>