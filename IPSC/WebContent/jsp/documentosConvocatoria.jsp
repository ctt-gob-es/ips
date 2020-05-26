<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
<title></title>
</head>
<body style="margin-left:1em;">
<div style="margin-left:1em; margin-right: 3em;float: left;width: 95%;">
<br>
		<div id="content_tituloNivel1">
		<div id="cuerpo_central_tituloNivel1">
     		<spring:message code="field.documento.descarga.titulo"/>
     	</div>
     	</div>
	
<div class="clear"/>
<br>
<div>

<logic:present name="documentos" scope="request">
		<bean:size id="numResultados" name="documentos" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
			<logic:iterate id="registro" name="documentos" >
				<table class="tabla_resultadosBordeBlanco">
					<tr>
						<td>
							<% String urlDescarga = request.getContextPath()+"/descargarDocumento?ent=Convocatorias"; %>
							<span class="textNegrita"><spring:message code="field.documento.nombre"/>:</span>&nbsp;<bean:write name="registro" property="nombre" /><br>
							<span class="textNegrita"><spring:message code="field.documento.descripcion"/>:</span>&nbsp;<bean:write name="registro" property="descripcion" /><br>
							<span class="enlaceReg">
							<html:link href="<%=urlDescarga %>" titleKey="field.DescargarDocumento" paramId="id" paramName="registro" paramProperty="id" >
							<spring:message code="field.documento.dercargar"/></html:link></span>
							
						</td>
					</tr>
				</table>
			</logic:iterate>
		</logic:greaterThan>	
		<logic:equal name="numResultados" value="0">
			<spring:message code="field.documento.vacio"/>
		</logic:equal>	
</logic:present>
<div class="posBotonDer">
<br><br>
	<input type="button" value="<spring:message code="field.Cerrar"/>" alt="<spring:message code="field.Cerrar"/>" class="boton2" onclick="javascript:window.close();" >	
</div>
</div>
</body>
</html> --%>