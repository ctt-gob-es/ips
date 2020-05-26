<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bean" uri="http://www.mpr.es/ipsg/bean-tags" %>
<%@ taglib prefix="logic" uri="http://www.mpr.es/ipsg/logic-tags" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>
<%@ taglib prefix="html" uri="http://www.mpr.es/ipsg/html-tags" %>

<%@ page import="es.map.ipsg.bean.*"%>
<%
	String resultado = (String) request.getAttribute("resultado");
%>
<script type="text/javascript">

var result = "<%=resultado%>";
	function limpiar() {
		var resultado = document.getElementById("resultado").value;
		if (result == "Correcto") {
			document.getElementById("nombre").value = "";
			document.getElementById("descripcion").value = "";
		}
	}

	function comprobarEliminar() {
		return confirm('<spring:message code="field.mensajeEliminarDocumento"/>');
	}

	function comprobarCampos() {
		var nombre = document.getElementById("nombre").value;
		var descripcion = document.getElementById("descripcion").value;

		if (nombre != null && nombre != "" && descripcion != null
				&& descripcion != "") {
			return true;
		} else {
			if ((document.getElementById("descripcion").value == null || document
					.getElementById("descripcion").value == "")
					&& (document.getElementById("nombre").value == null || document
							.getElementById("nombre").value == "")) {
				alert("Los campos nombre y descripción deben estar informados");
			} else {
				if (document.getElementById("descripcion").value == null
						|| document.getElementById("descripcion").value == "") {
					alert("El campo descripción debe estar informado");
				}
				if (document.getElementById("nombre").value == null
						|| document.getElementById("nombre").value == "") {
					alert("El campo nombre debe estar informado");
				}
			}

		}
		return false;

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
<body style="margin-left: 0.4em;" onload="limpiar()">

<div class="pae-body" style="width:100%; height:100%; min-height: 44em; overflow-y:hidden">
	<h1 class="pae-title"><spring:message
	code="field.documento.documentosAnexados" /></h1>
	
	<div class="pae-box">
	<div class="pae-box__header">	
		<fieldset style="border: none;">
			<div class="pae-layout">
		
		
<table class="tabla_resultados">
	<jsp:useBean id="documentos" scope="request"
		class="java.util.ArrayList" />
	<%
		int i = 0;
	%>
	<logic:present name="eliminado" scope="request">
		<font color="#c33400"><spring:message
			code="field.documento.documentoEliminado" /></font>
		
		
	</logic:present>
	<logic:present name="documentos" scope="request">
		<bean:size id="numResultados" name="documentos" scope="request" />
		<logic:greaterThan name="numResultados" value="0">
			<logic:iterate id="registro" name="documentos">
				<tr>
					<th>
					<td>
							<bean:write name="registro" property="nombre" />: <bean:write name="registro" property="descripcion" /><br>
							<a href="${pageContext.request.contextPath}/spring/descargarDocumentoAdjunto?ent=Convocatorias&id=${registro.id}" class="font-color-ips">
					 		   			<spring:message code="field.documento.descargaDocumento"/>
					 		   	</a>
					 		   	
					 		   	<logic:notEqual name="finalizada" scope="request" value="true">
						 		   	 | 
									<a href="${pageContext.request.contextPath}/spring/borrarDocumento?ent=Convocatorias&doc=${registro.id}" onclick="return comprobarEliminar()" class="font-color-ips">
						 		   			<spring:message code="field.documento.borrarDocumento"/>
						 		   	</a>
						 		</logic:notEqual>
						</td>

				</tr>
			</logic:iterate>
		</logic:greaterThan>
		<logic:equal name="numResultados" value="0">
			<span class="mensajeWarning"><spring:message
				code="field.documento.noExistenDocumentos" /></span>
		</logic:equal>
	</logic:present>
</table>		
			

	
<logic:notEqual name="finalizada" scope="request" value="true">
	<form:form modelAttribute="crearDocumentoForm" action="/IPSG/spring/subirDocumento" id="formPadre" method="post" enctype="multipart/form-data">
		<form:hidden path="idConvocatoria" class="input_texto"
			id="accion" />
		<form:hidden path="resultado" id="resultado" />
		
	
		
		<div class="capaAll mg-top-5">
		
		<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></d	
		</logic:present>	
		
		
		
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.documento.nombre" />
					</span>
					<input id="nombre" name="nombre" class="pae-form__input" maxlength="100" type="text"> 		
				</div>
			</div>
		</div>
		
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.documento.descripcion" />
					</span>
					<input id="descripcion" name="descripcion" class="pae-form__input" maxlength="200" type="text"> 		
				</div>
			</div>
		</div>
		
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.documento.fichero" />
					</span>
					<input type="file" name="file" id="file" class="text_1">
				</div>	
			</div>
		</div>
		
		<div class="filaBtn">
			<div class="pae-box-buttons pd-right-1">
					<input type="submit" name="submit" value="Subir" onclick="javascript: return comprobarCampos()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
					<input type="button" name="submit" value="Cerrar" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1">
				</div>	
		</div>
		
	</form:form>
</logic:notEqual>
</div>
		</fieldset>
		</div>
		</div></div>
</div>
</body>
</html>