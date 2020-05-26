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
<%@page import="es.map.ipsg.util.Constantes"%>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<script type="text/javascript">

function generaPDF(idSolicitud)
{
	document.forms[0].action="../spring/generarDocPdf?idRegistro="+idSolicitud+"&accion=Pdf";
}

function generaPDFRec(idSolicitud)
{
	document.forms[0].action="../spring/generarDocPdfRec?idRegistro="+idSolicitud+"&accion=Pdf";
}

/*function generaXML(idSolicitud)
{
	document.forms[0].action="../spring/generarDocPdf?idRegistro="+idSolicitud+"&accion=Xml";
}*/

function Descargar() {

	var idDocumento=new Object();
	if (document.getElementById("idDoc").value=="0" ){
		alert("No se puede descargar el documento seleccionado ya que no existe");
		document.forms[0].action="../spring/documentosJustificanteSolicitud?id="+document.getElementById("idSolicitud").value;
	}
	else{
		
		idDocumento=document.getElementById("idDoc").value;	
		document.forms[0].action="../spring/descargarDocumentoSolicitud?ent=Solicitudes&id="+idDocumento;
	}
}

function actualizaValor(valRadio){
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

<body style="margin-left:0.4em;" class="background-color-white-ip">
<div class="pae-body2" style="overflow-y:hidden">

<form:form modelAttribute="crearDocumentoForm" action="/IPSG/spring/documentosJustificanteSolicitud" id="formPadre">
<form:hidden path="idSolicitud" id="idSolicitud"/>






<h1 class="pae-title2"><spring:message code="field.documento.documentosJustificado"/></h1>
<div class="pae-box">
<div class="pae-box__header2">	
		<fieldset>
		<logic:present name="errorGeneracion">

<%
						String error = request.getAttribute("errorGeneracion").toString();
						
%>
<div id="contenidoIzq">
		<%=error %>
</div>

</logic:present>
			<div class="pae-layout">	
				<div class="pae-layout__item">		
<logic:notPresent name="errorGeneracion" scope="request">

<logic:present name="documentos" scope="request">


<%
String sPerfilUsuario =  request.getAttribute("sPerfilUsuario").toString();
boolean descargar=false;
%>
		<bean:size id="numResultados" name="documentosAux" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table id="paetable" class="pae-table2">
			<caption class="hiddenAccesible">Tabla documento solicitud</caption>
			<thead class="pae-table__header">		
			<logic:iterate id="registro" name="documentosAux" >
<!-- 			Este if es utilizado para descartar los justificantes de las convocatorias antiguas -->
				<c:if test="${registro.dsTipoDocumento =='JUSTIFICANTE DE INSCRIPCIÓN SOLICITUD PDF'}">
					<tr class="pae-table__row">
						<th data-col="col1" class="pae-table__cell-header"></th>
					</tr>
					<tr>
						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
						<bean:define id="tipoDocumento" name="registro"  property="tipoDocumento"  type="es.map.ips.model.TipoDocumento"/>
						<%if(sPerfilUsuario.equals(Constantes.ROL_ADMINISTRADOR) || sPerfilUsuario.equals(Constantes.ROL_GESTOR)) {
							String justificanteREC = request.getAttribute("justificanteREC").toString();
							String justificanteSOLICITUD = request.getAttribute("justificanteSOLICITUD").toString();
						%>
								 
										<bean:write name="registro" property="desTipoDocumento" />
										<form:hidden path="idDocumento"   value="${registro.id}"  id="idDocumento"/>
										<form:hidden path ="idDoc" value="${registro.id}" id="idDoc"/>									
								

							 <c:if test="${tipoDocumento.id eq 4}" >
							 	<%if(!"1".equals(justificanteSOLICITUD)){
						 		  %>
									<div id="contenidoDer">
									<%if(sPerfilUsuario.equals(Constantes.ROL_ADMINISTRADOR)){%>
										<input type="submit" name="submit" value="Generar Pdf" alt="Generar Pdf" onclick="generaPDF(${registro.idSolicitud})"class="boton4">
							 		<%}else if(sPerfilUsuario.equals(Constantes.ROL_GESTOR)){%>
										<br>
										<spring:message code="field.documento.sinJustificanteRegistro"/>
							 		<%}%>
							 		</div> 
							 		<br><br>
							 		<%descargar=false;%>						 	
							 	<%}%> 							 								 	
					 		</c:if>		
				 			<c:if test="${tipoDocumento.id eq 4}" >
						 		<%if("1".equals(justificanteSOLICITUD)){%>
								<!-- Si hay documentos no sacamos el enlace para generarlo -->
										<%descargar=true;%>
									<br>
							 	<%}%>
						 	</c:if>	
						<%}%> 
						</td>
					</tr>
				</c:if>	
			</logic:iterate>
			</thead>
		</table>
				
		</logic:greaterThan>	
		
		<bean:define id="idSolicitud" name="idSolicitud"   type="Long"/>
		
		<logic:greaterThan name="numResultados" value="0">
			<br><br>
			
			<%if(descargar){%> 
				<input type="submit" name="submit" value="Descargar" onclick="Descargar()" class="boton">				
			<%}%>	
		</logic:greaterThan>	
</logic:present>
</logic:notPresent>		
		</div>
<div class="clear">		
<div class="pae-layout__item">

	
	<div class="filaBtn">

		<div class="pae-box-buttons">	
			<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
		</div>
	</div>
</div>
</div>
</fieldset>
</div>
</div>


</form:form>
</div>
</body>
</html>