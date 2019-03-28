
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.*"%>
<%@ page import="es.map.ipsc.bean.*"%>
<%@ page import="es.map.ipsc.bean.BuscarConvocatoriasBean"%>
<%@ page import="es.map.ipsc.Constantes" %>

<script>
	$("#idFavicon").attr("href","../../images/ipsFavicon.ico");
</script>

<%
/* 	final ResourceBundle IPSC_RESOURCE_BUNDLE = ResourceBundle.getBundle("ipsc-config");
	final String urlJusticia = IPSC_RESOURCE_BUNDLE.getString("url.externa.modelo.justicia");
	//final String urlJusticiaSSJJ = IPSC_RESOURCE_BUNDLE.getString("url.externa.modelo.justicia.ssjj");
	final String ssjj = Constantes.COD_SECRETARIO_JUDICIAL;
	final String modelo790007 = Constantes.MODELO79007; */
%>

	<div
		style="margin-left: 1px; margin-right: 1px; float: left; width: 99%; margin-top:25;">
		<br>
		<div id="content_tituloNivel1">
			<div id="cuerpo_central_tituloNivel1">
				<spring:message code="field.descargarFormulario" />
			</div>
		</div>
		<form action="<%=request.getContextPath()%>/descargarFormulario790" method="post" name="formDescarga" id="formDescarga" target="_blank">
			<div id="capaAll">
				<table class="tabla_resultados w41 no_margin">
					<tr>
						<th class="w15"><spring:message code="field.form.modelo" /></th>
						<th class="w7"><spring:message code="field.form.formulario" /></th>
						<th><spring:message code="field.form.descripcion" /></th>					
					</tr>
					<logic:iterate id="modeloFormulario" name="listaModelos">
					<tr>
						<td class="w15">
							<bean:write name="modeloFormulario" property="numModelo" />
						</td>						
						<td class="w7">
							<center>
								<a href="javascript:mostrarModelo(<bean:write name="modeloFormulario" property="numModelo" />, <bean:write name="modeloFormulario" property="id" />);"  ontitleKey="field.convocatoria.iniciarSolicitud" id="idVinculo">
									<img type="image" src="/IPSC/images/icon_print.png" style="cursor: pointer;" alt="Descargar modelo" />
								</a>
							</center>
						</td>
						<td>
							<bean:write name="modeloFormulario" property="descripcion" />
							<input type="hidden" id="numModelo" name="numModelo" />
							<input type="hidden" id="idModelo" name="idModelo" />
						</td>
					</tr>
					</logic:iterate>
				</table>
			</div>
		<form>

<script type="text/javascript">
function mostrarModelo(numModelo, idModelo){
	document.getElementById("numModelo").value = numModelo;
	document.getElementById("idModelo").value = idModelo;
	document.getElementById("formDescarga").submit();	
}

</script>

