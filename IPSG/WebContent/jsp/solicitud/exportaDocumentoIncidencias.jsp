<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<script type="text/javascript">

window.onload = function() {
    if(!window.location.hash) {
        window.location = window.location + '#loaded';
        window.location.reload();
    }
}

function window_onload() {
	location.reload();
}

function exporta(){
	var bMarcado = false;
	if (document.getElementById("anexo").checked){
		bMarcado = true;
		document.getElementById("strAnexo").value="S";
	}else{
		document.getElementById("strAnexo").value="N";
	}

// 	if (document.getElementById("justificaPago").checked){
// 		bMarcado = true;
// 		document.getElementById("strJustificaPago").value="S";
// 	}else{
// 		document.getElementById("strJustificaPago").value="N";
// 	}
	if (document.getElementById("registroPDF").checked){
		bMarcado = true;
		document.getElementById("strRegistroPDF").value="S";
	}else{
		document.getElementById("strRegistroPDF").value="N";
	}

	if(bMarcado)
	{
		document.forms[0].action="../spring/descargarDocumentoZipIncidencias?ent=Solicitudes";
		document.getElementById("botonExportar").style.display="none";
		document.getElementById("botonCerrar").style.display="block";
		document.getElementById("mensajeExporta").style.display="block";
		return bMarcado;
	} else
	{
		alert('<spring:message code="field.exporta.errores.elegirDocumento"/>');
		return bMarcado;
	}
}
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="pae-body" style="width:100%; height:100%; min-height: 44em; overflow-y:hidden">
<form:form modelAttribute="solicitudesIncidenciasForm" action="/IPSG/spring/descargarDocumentoZipIncidencias" id="formPadre">
<form:hidden path="strAnexo" id="strAnexo" value="N"/>
<form:hidden path="strJustificaPago" id="strJustificaPago" value="N"/>
<form:hidden path="strRegistroPDF" id="strRegistroPDF" value="N"/>
<form:hidden path="strRegistroXML" id="strRegistroXML" value="N"/>
<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<input type="hidden" name="menu"  id="menu" value="N">	
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="perfilUsuario" id="perfilUsuario"/>	
	<form:hidden path="marcarTodo" id="marcarTodo"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
	<form:hidden path="solicitudesSel" id="solicitudesSel" />
	<form:hidden path="nif" id="nif" />
	<form:hidden path="numSolicitud" id="numSolicitud" />
	<form:hidden path="idMinisterio" id="idMinisterio" />
	<form:hidden path="idEstado" id="idEstado" />
	<form:hidden path="idCentroGestor" id="idCentroGestor" />
	<form:hidden path="cuerpoEscala" id="cuerpoEscala" />
	<form:hidden path="dsCuerpoEscala" id="dsCuerpoEscala" />
	<form:hidden path="idEspecialidad" id="idEspecialidad" />
	<form:hidden path="dsEspecialidad" id="dsEspecialidad" />
	<form:hidden path="idTipoAcceso" id="idTipoAcceso" />
	<form:hidden path="idModelo" id="idModelo" />
	<form:hidden path="solExencion" id="solExencion" />
	<form:hidden path="idEstadoConvoc" id="idEstadoConvoc" />
	<form:hidden path="fechaHasta" id="fechaHasta" />
	<form:hidden path="fechaDesde" id="fechaDesde" />
	<h1 class="pae-title"><spring:message code="field.exporta.titulo"/></h1>	
				<logic:present name="org.apache.spring.ACTION_MESSAGE">
					<logic:messagesPresent message="true">
				  		<html:messages id="mensajeDocumentos" message="true">
				      		<div id="error">
								<div id="rellenarCampos" class="pae-alertbox pae-alertbox--warning">
									<p class="pae-alertbox__text fw-bold">Errores:</p>
									<ul>
										<li>
										<p class="pae-alertbox__text"><bean:write name="mensajeDocumentos"/></p>
										</li>
									</ul>
								</div>
							</div>
				  		</html:messages>
			  		</logic:messagesPresent>
					<div class="clear"></div>
				</logic:present>
	<%-- MAQUETACION INI --%>
	<div class="pae-box">
		<div class="pae-box__header">	
			<fieldset>	
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-7/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<b><spring:message code="field.exporta.elementosExportar"/></b>
					</div>
				</div>
			</div>
			<div class="pae-layout">
				<div class="pae-layout__item">
					<div class="pae-form__cell">
						<input type="checkbox" name="anexo" property="anexo" id="anexo" data-function="checkbox-custom-input" class="pae-form__custom-check">
						<label for="anexo" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"><spring:message code="field.exporta.anexo"/></label>						
					</div>
				</div>	
			</div>							
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<input type="checkbox" name="registroPDF" property="registroPDF" id="registroPDF" data-function="checkbox-custom-input" class="pae-form__custom-check">
						<label for="registroPDF" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"><spring:message code="field.exporta.justificarRegistroPDF"/></label>
					</div>
				</div>	
			</div>
			<div class="pae-layout">
				<div class="pae-layout__item">
					<div class="pae-form__cell">
						<b><spring:message code="field.exporta.campoOrdenacion"/></b>
					</div>
				</div>
			</div>
			<div class="pae-layout">
				<div class="pae-layout__item">
					<div class="pae-form__cell">
						<input type="radio" name="campoOrdenacion" property="campoOrdenacion" id="campoOrdenacion" class="pae-form__custom-radio" value="N">
						<label for="anexo" class="pae-form__custom-radio-label pae-form__custom-radio-label--option"><spring:message code="field.exporta.campoOrdenacion.nombre"/></label>
					</div>
					<div class="pae-form__cell">
						<input type="radio" name="campoOrdenacion" property="campoOrdenacion" id="campoOrdenacion" class="pae-form__custom-radio" value="A">
						<label for="anexo" class="pae-form__custom-radio-label pae-form__custom-radio-label--option"><spring:message code="field.exporta.campoOrdenacion.apellido"/></label>
					</div>
					<div class="pae-form__cell">
						<input type="radio" name="campoOrdenacion" property="campoOrdenacion" id="campoOrdenacion" class="pae-form__custom-radio" value="D">
						<label for="anexo" class="pae-form__custom-radio-label pae-form__custom-radio-label--option"><spring:message code="field.exporta.campoOrdenacion.dni"/></label>
					</div>
					<div class="pae-form__cell">
						<input type="radio" name="campoOrdenacion" property="campoOrdenacion" id="campoOrdenacion" class="pae-form__custom-radio" value="J" checked="true">
						<label for="anexo" class="pae-form__custom-radio-label pae-form__custom-radio-label--option"><spring:message code="field.exporta.campoOrdenacion.justificante"/></label>
					</div>
				</div>
			</div>
			<%-- Boton --%>
			<div class="filaBtn">
				<div class="pae-box-buttons">
					<input type="submit" name="submit" value="Exportar" id="botonExportar" onclick="if(!exporta()){return false;}" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>			
					<input type="button" value="Cerrar" id="botonCerrar" name="submit" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>
				</div>
			</div>
			<%-- Boton --%>
		<br><br>
		<div id="mensajeExporta" style="display: none;width: 90%;" >
			<font color="#c33400"><spring:message code="field.exporta.mensajeExportando"/></font>
		</div>
		<br>
	</div>			
	<%-- MAQUETACION FIN --%>

</form:form>

	
</div>
</body>
</html>