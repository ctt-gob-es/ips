<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
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
<div class="pae-body" style="width:100%; height:100%; min-height: 44em; overflow-y:hidden">


<form:form modelAttribute="solicitudPresencialForm" action="/IPSG/spring/subirDocumentoSolicitudPresencial" id="formPadre" method="post" enctype="multipart/form-data">
	<div class="pd-top-3">	
	<form:hidden path="idSolicitud" class="input_texto" id="accion"/>
	
	<h1 class="pae-title"><spring:message code="field.documento.documentosSolicitud"/></h1>
	
	<div class="capaAll mg-top-5">	
		<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
					<div class="pae-box">
					<div class="pae-box__header">	
						<fieldset style="border: none;">
							<div class="pae-layout">
	
	<table class="tabla_resultados">
	<logic:present name="documentos" scope="request">
			<bean:size id="numResultados" name="documentos" scope="request"/>
			<logic:greaterThan name="numResultados" value="0">
				<logic:iterate id="registro" name="documentos" >
					<tr>
						<th>
							<td>
								${registro.nombre}: ${registro.descripcion}: ${registro.desTipoDocumento}
								<br>
								<a href="${pageContext.request.contextPath}/spring/descargarDocumentoSolicitudPresencial?ent=Solicitudes&id=${registro.idSolicitud}&doc=${registro.id}" id="doc" name="registro" >
						 		<font color="#c33400"><spring:message code="field.documento.descargaDocumento"/></font>
						 		</a>	
						 		
						 		<logic:equal property="soloLectura" name="registro" value="false">				 		   	 | 
							 		<a href="${pageContext.request.contextPath}/spring/borrarDocumentoSolicitudPresencial?ent=Solicitudes&id=${registro.idSolicitud}&doc=${registro.id}" id="doc" name="registro" >
							 			<font color="#c33400"><spring:message code="field.documento.borrarDocumento"/></font>
							 		</a>
							 	</logic:equal>
							</td>					
					</tr>
				</logic:iterate>
			</logic:greaterThan>	
			<logic:equal name="numResultados" value="0">
			<span class="mensajeWarning">
				<spring:message code="field.documento.noExistenDocumentos"/>
			</span>
				<br>
			</logic:equal>	
	</logic:present>
	</table>
	<br><br>
	</div>

							<div class="pae-layout">
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.documento.nombre"/>
			</span>
			<form:input type="text" path="nombre" size="50" id="nombre" class="pae-form__input"/>					
		</div>	
									</div>
							<div class="pae-layout">
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.documento.descripcion"/>
			</span>
			<form:input type="text" path="descripcion" size="50" id="descripcion" class="pae-form__input"/>
		</div>
							</div>
							<div class="pae-layout">
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.documento.tipoDocumento"/>
			</span>
			
			<form:select path="idTipoDocumento" class="pae-form__input" id="idTipoDocumento" >
				<logic:present name="tipoDocumento" scope="request">
					<bean:size id="numTipoDocumento" name="tipoDocumento" scope="request"/>
						<logic:greaterThan name="numTipoDocumento" value="0">
							<form:options items="${tipoDocumento}"  itemLabel="descripcion" itemValue="id" />
						</logic:greaterThan>
				</logic:present>
			</form:select>
			
		</div>
		</div>
		<div class="clear"/><br>
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.documento.fichero"/>
			</span>
				
			<input type="file" name="file" class="text_1" size="20"  id="nombre"/>				
		</div>
		<div class="clear"/><br>
		<div class="filaBtn">
			<div class="pae-box-buttons pd-right-1">
				<input type="submit" name="submit" value="Subir" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
				<input type="button" name="submit" value="Cerrar" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>							
			</div>
		</div>	
	</fieldset>
	</div>		
	</div></div>
</form:form>
</div>
</body>
</html>