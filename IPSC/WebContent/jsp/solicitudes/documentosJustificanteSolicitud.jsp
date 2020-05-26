<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="es.map.ipsc.Constantes"%>
<%@ page import="es.map.ipsc.bean.*"%>
<script type="text/javascript">

function Descargar() {
	
	var idDocumento=new Object();
	if (document.getElementById("idDoc").value=="0" ){
		alert("<spring:message code='field.documento.errorFile'/>");
		document.forms[0].action="<%=request.getContextPath()%>/secure/documentosJustificanteSolicitud?id="+document.getElementById("idSolicitud").value;
	}else{		
		idDocumento=document.getElementById("idDoc").value;
		document.forms[0].action="<%=request.getContextPath()%>/secure/descargarDocumentoSolicitud?ent=Solicitudes&id="+idDocumento;
	}
}


function actualizaValor(id){
	var valRadio = document.getElementById("idDocumento").value;
	document.getElementById("idDoc").value=valRadio;
}

</script>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>

<body style="margin-left:1em;">
<center>

<div id="enviar" 
	style="display:none;
		   position:absolute;
	       background-color:#ffffff;
	       filter:Alpha(Opacity=70);
	       top:0px;
	       left:0px;
	       width:100%;
	       height:50em">
	<div style="margin-top:4%; margin-left:10%">
	
		<img alt="<spring:message code="field.extracto.esperar"/>" src="../../images/PleaseWait.gif">
	</div>
	<div style="margin-top:8%; margin-left:8%; font-size:1.5em">
		<spring:message code="gestorDocumental.esperando"/>
	</div>
</div>
</center>
<div style="margin-left:1em; margin-right: 1em;float: left;width: 95%;">
<br>

<div id="content_tituloNivel1">
		<div id="cuerpo_central_tituloNivel1">
     		<spring:message code="field.documento.documentosJustificado"/>
       </div>
</div>
<br>
<br>
<form:form modelAttribute="crearDocumentoForm" action="/IPSC/secure/documentosJustificanteSolicitud" id="formPadre" method="post">
<input type="hidden" name="idDoc"  id="idDoc" value="0">
<input type="hidden" name="idSolicitud"  id="idSolicitud">
<table class="tabla_resultados">

<logic:present name="documentos" scope="request">
		<bean:size id="numResultados" name="documentos" scope="request"/>
		<jsp:useBean id="documentos" scope="request" class="java.util.ArrayList" />
		<%
		int i = 0;
		%>
		<logic:greaterThan name="numResultados" value="0">
			<logic:iterate id="registro" name="documentos" >
			<%
			
			DocumentoBean documento = (DocumentoBean) documentos.get(i);
			String id = String.valueOf(documento.getId());
			System.out.println("idDocumento:" +id);
			String enlacePagina = request.getContextPath()+"/secure/descargarDocumentoSolicitud?ent=Solicitudes&id="+id;
			%>
<%-- 			<c:if test="${registro.dsTipoDocumento =='JUSTIFICANTE DE INSCRIPCIÓN SOLICITUD PDF' || registro.dsTipoDocumento =='JUSTIFICANTE DE PAGO DE SOLICITUD'}"> --%>
				<tr>
					<td>
						<img src="<%=request.getContextPath()%>/images/icono_guardar.gif">
						<a href="${enlacePagina}" titleKey="field.DescargarDocumento">
							<bean:write name="registro" property="dsTipoDocumento" />
						</a>
					</td>
				</tr>
<%-- 			</c:if> --%>
			<%
			i++;
			%>
			</logic:iterate>
		</logic:greaterThan>	
		<logic:equal name="numResultados" value="0">
			<spring:message code="field.documento.noExistenDocumentos"/>
		</logic:equal>	
</logic:present>
<logic:present name="error">
	<spring:message code="field.documento.error.descarga"/>
</logic:present>
<logic:present name="errorUsuario">
	<spring:message code="field.documento.errorUsuario.descarga"/>
</logic:present>

</table>
</form:form>
<div class="posBotonDer">
<br><br>
	<input type="button" value="<spring:message code="field.Cerrar"/>" title="<spring:message code="field.Cerrar"/>" class="boton2" onclick="javascript:window.close();" >	
</div>
</div>
</body>
</html>