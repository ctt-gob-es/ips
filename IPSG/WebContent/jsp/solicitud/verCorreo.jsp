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

<script>
function openWindowVerDocumentosAdjunto() {		
	var idCorreo = document.getElementById("idCorreo").value;
	
	ventanaPopup = window.open("../spring/verDocumentosAdjuntos?correo="+idCorreo, 'ventanaDocumentosAdjuntos', 'width=650,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	 
}

function cerrarVerDocumentoAdjunto(){
	window.close();
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
<body style="margin-left: 0.4em;">
<div style="margin-left: 1em; margin-right: 1em; float: left; width: 95%;">
<div class="pae-title"><spring:message code="field.correo.enviarCorreoCiudadano" /></div>

<form:form modelAttribute="contactarCiudadanoFOrm" action="/IPSG/spring/verCorreo" id="formPadre" >
	<form:hidden path="idCorreo" class="input_texto" styleId="idCorreo" value="${idCorreo}"/>
	<form:hidden path="entorno" class="input_texto" styleId="entorno" value="${entorno}"/>
	

	
	<%-- MAQUETACION INI --%>
	<div class="">	
		<div data-function="accordion-box" class="pae-box">
			<div class="pae-box__body">
				<fieldset>
					<div class="pae-layout">
						<%-- DE --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.correo.de" />
								</span>
								<form:input path="de" size="50" class="pae-form__input" id="de" readonly="true"/>						
							</div>
						</div>
						<%-- DE --%>
						<%-- Para --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.correo.para" />
								</span>
								<form:input path="para" size="50" class="pae-form__input" id="para" readonly="true">					
							</div>
						</div>
						<%-- Para --%>
						<%-- Asunto --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.correo.asunto" />
								</span>
								<form:input path="asunto" size="50" class="pae-form__input" id="asunto" readonly="true"/>					
							</div>
						</div>
						<%-- Asunto --%>
						<%-- Tabla adjuntos --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<table id="paetable" class="pae-table pae-table--margin">
							<caption class="hiddenAccesible">Tabla adjuntos</caption>
							<logic:present name="documentos" scope="request">
									<bean:size id="numResultados" name="documentos" scope="request"/>
									<logic:greaterThan name="numResultados" value="0">
										<spring:message code="field.documento.documentosAdjuntos"/>
										<br/>				
										<logic:iterate id="registro" name="documentos" >
											<tr>
												<th>
													<td>
														<bean:write name="registro" property="nombre" />: <bean:write name="registro" property="descripcion" />									
														<br><a href="../spring/descargarDocumentoAdjunto?ent=Correos?id=${registro.id}" >
												 		   <font color="#c33400">
												 		   			<spring:message code="field.documento.descargaDocumento"/>
												 		   		</font>
												 		   	</a>	 		
													</td>
												
											</tr>
										</logic:iterate>
										<br/>
									</logic:greaterThan>
							</logic:present>
							</table>					
						</div>
						<%-- Tabla adjuntos --%>
						<%-- Mensaje --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.correo.mensaje" />
								</span>
								<form:textarea path="cuerpoMensaje" id="cuerpoMensaje" rows="10" cols="80" class="pae-form__input pae-box--transparent" readonly="true"/>				
							</div>
						</div>					
						<%-- Mensaje --%>
						<%-- Boton --%>
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
								<div class="pae-box-buttons">
									<input type="button" value="Cancelar" name="volver" onclick="cerrarVerDocumentoAdjunto()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>	
								</div>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<%-- MAQUETACION FIN --%>
	
</html:form>
</div>
</body>
</html>