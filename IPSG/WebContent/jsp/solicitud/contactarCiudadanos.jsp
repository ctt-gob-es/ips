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


  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>


<%
String campo = (String)request.getAttribute("campo");
%>
<html>
<script>
function adjuntar()
{

    ventanaPopup = window.open("../spring/adjuntarDocumentos", 'ventanaAdjuntar', 'width=550, height=420,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	 
	document.forms[0].action = "../spring/verContactarCiudadano?menu=N";
	document.forms[0].submit();
	
}
function enviarCorreo()
{
	document.getElementById("accion").value = "enviar";
	document.forms[0].action = "../spring/contactarCiudadano";
	document.forms[0].submit();
	
}

function cerrar() {
	window.close();
}
</script>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>
<div class="pae-body" style="width:100%; height:100%; min-height: 44em; overflow-y:hidden">

<form:form modelAttribute="contactarCiudadanoForm" action="/IPSG/spring/contactarCiudadano" id="formPadre" 
method="post" class="pae-body" enctype="multipart/form-data">
	<div class="pd-top-3">	
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
	<form:hidden path="idSolicitud" class="input_texto" id="idSolicitud" value="${idSolicitud}"/>
	<form:hidden path="entorno" class="input_texto" id="entorno" value="${entorno}"/>
	<form:hidden path="campo" class="input_texto" id="campo" value="${campo}"/>
	<form:hidden path="accion" class="input_texto" id="accion" />
	<h1 class="pae-title"><spring:message code="field.correo.enviarCorreoCiudadano" /></h1>
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
<%-- MAQUETACION INI --%>
	<div class="pae-box">
		<div class="pae-box__header2">	
			<fieldset style="border: none;">
				<div class="pae-layout">
			<h3 class="pae-box__header--title">
				<logic:present name="mensajeConfirmacion" scope="request">
					<strong><bean:write name="mensajeConfirmacion" scope="request"/></strong>
				</logic:present>
				<logic:present name="errorEmail" scope="request">
					<strong><bean:write name="errorEmail" scope="request"/></strong>
				</logic:present>
			</h3>
			<fieldset style="border: none;">
				<div class="pae-layout">
					<%-- De --%>
					<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.correo.de" />
							</span>
							<form:input path="de" size="50" class="pae-form__input" id="de" maxlength="50"/>					
						</div>
					</div>
					<%-- De --%>
					</div>
					<div class="pae-layout">
					<%-- Para --%>
					<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.correo.para" />
							</span>
							<form:input path="para" size="50" id="para" class="pae-form__input pae-box--transparent" readonly="true" value="${destinatarios}" maxlength="50"/>						
						</div>
					</div>
					<%-- Para --%>
					</div>
					<div class="pae-layout">
					<%-- Asunto --%>
					<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.correo.asunto" />
							</span>
							<form:input path="asunto" size="50" class="pae-form__input" id="asunto" maxlength="50"/>						
						</div>
					</div>
					<%-- Asunto --%>					
					</div>
					<div class="pae-layout">
					<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">
					<table cellspacing="2px" class="tabla_resultados">
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
												<br><a href="${pageContext.request.contextPath}/spring/descargarDocumentoAdjunto?ent=Correos&id=${registro.id}">								
										 		    <font color="#c33400"><spring:message code="field.documento.descargaDocumento"/></font>
										 		   	</a>
										 		   	<a href="${pageContext.request.contextPath}/spring/borrarDocumentoAdjunto?ent=Correos&doc=${registro.id}">|						 		   
										 		   	<font color="#c33400"><spring:message code="field.documento.borrarDocumento"/></font>
										 		   	</a>
											</td>
									</tr>
								</logic:iterate>
							</logic:greaterThan>
					</logic:present>
					</table>
					</div>
					</div>
					<div class="pae-layout">
					<%-- Mensaje --%>
					<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.correo.mensaje" />
							</span>
							<form:textarea path="cuerpoMensaje" class="pae-form__input" id="cuerpoMensaje" rows="10" cols="80" maxlength="350"/>				
						</div>
					</div>
					<%-- Mensaje --%>
					</div>
					<%-- Boton --%>
					<div class="pae-layout">
						<div class="filaBtn">
						<div class="pae-box-buttons pd-right-1">
								<logic:notPresent name="mensajeConfirmacion" scope="request">				
										<input type="button" value="Adjuntar" name="accion" onclick="adjuntar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
										<input type="button" value="Enviar" name="accion" onclick="enviarCorreo()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
										<input type="button" value="Cerrar" name="accion" onclick="cerrar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">						
								</logic:notPresent>	
								<logic:present name="mensajeConfirmacion" scope="request">
									<input type="button" value="Cerrar" name="accion" onclick="cerrar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">	
								</logic:present>		
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			</div>
		</div>
	</div>
<%-- MAQUETACION FIN --%>
</div>
</div>
</form:form>
</body>
</html>