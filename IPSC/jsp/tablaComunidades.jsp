<link rel="shortcut icon" type="image/ico" href="../../images/ipsFavicon.ico" type="image/x-icon"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.*"%>
<%@ page import="es.map.ipsc.bean.*"%>
<%@ page import="es.map.ipsc.bean.BuscarConvocatoriasBean"%>
<%@ page import="es.map.ipsc.Constantes" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="cache-control" content="max-age=0" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- Compatibilidad con Windows 8 -->
		<meta http-equiv="X-UA-Compatible" content="requiresActiveX=true"/>
		<title><tiles:getAsString name="title" /></title>
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/estilos.css" type="text/css">
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/estilo_060.css" type="text/css">
		<link rel="stylesheet"	href="<%=request.getContextPath()%>/css/jquery-ui-1.10.4.custom.css" type="text/css">
	</head>
	<body>

	<div id="cabecera">
		<div id="cabecera_menu">
			<h2>
				<span class="titleBigGray"> INSCRIPCI&Oacute;N EN</span>
				<span class="titleBigRed"> PRUEBAS SELECTIVAS</span>
			</h2>
			<div>
				<div id="cabecera_menu_superior">
					<a href="http://administracion.gob.es" target="_blank"	title="Punto de Acceso General (administracion.gob.es)">
						<img src="<%=request.getContextPath()%>/images/logo_admon.png" alt="Punto de Acceso General" />
					</a>
				</div>
			</div>
		</div>
	</div>

	<div style="margin-left:2em; margin-right:2em;width: 98%;">
		<div id="content_tituloNivel1">
			<div id="cuerpo_central_tituloNivel1">
				<spring:message code="field.comunidadestitulo" />
			</div>
		</div>
		<div class="clear"></div>
		<br>
		</div>
		<div class="wrapper_tabla_resultados">
			<logic:present name="listcomunidades" scope="request">
			<bean:size id="numResultados" name="listcomunidades" scope="request"/>
				<logic:greaterThan name="numResultados" value="0">
					<table class="tabla_resultados" aling>
						<tr>
							<th>
							<spring:message code="field.comunidades"/>				
							</th>
							<th>
							<spring:message code="field.comunidades.discapacidad"/>
							</th>
							<th>
							<spring:message code="field.comunidades.familiaNumerosa"/>
							</th>			
						</tr> 
						<!-- Resultados de la consulta -->
						<logic:iterate id="registro" name="listcomunidades" >
								<tr>
									<td><bean:write name="registro" property="descripcion" /></td>
									<logic:equal name="registro" property="servicioDD" value="true">
										<td>SI</td>
									</logic:equal>
									<logic:notEqual name="registro" property="servicioDD" value="true">
										<td>NO</td>
									</logic:notEqual>
									<logic:equal name="registro" property="servicioFN" value="true">
										<td>SI</td>
									</logic:equal>
									<logic:notEqual name="registro" property="servicioFN" value="true">
										<td>NO</td>
									</logic:notEqual>										
				    			</tr>
							</logic:iterate>
						</table>
					</logic:greaterThan>
				</logic:present>
		</div>		
		<div class="clear"></div>
		<br>
		<div id="pie">
		<img src="<%=request.getContextPath()%>/images/logo_eu.png" alt="logoEu" class="logoEu"/>
		<ul>
		     <li><a href="http://www.060.es/060_Home/Utilidades/MenuAvisoLegal/AvisoLegal.html" target="_new"><spring:message code="field.pie.avisoLegal" /></a> |</li>
			 <li><a href="http://www.060.es/060_Home/Utilidades/MenuPoliticaPrivacidad/PoliticaPrivacidad.html" target="_new"><spring:message code="field.pie.politicaPrivacidad" /></a> |</li>
			 <li><a href="http://www.060.es/060_Home/Utilidades/MenuAccesibilidad/Accesibilidad.html" target="_new"><spring:message code="field.pie.accesibilidad" /></a> |</li>
			 <li><img src="<%=request.getContextPath()%>/images/xml.PNG" alt="xml_inside" width="46" height="20" /> |</li>
			 <li><img src="<%=request.getContextPath()%>/images/w3c.PNG" alt="w3c" width="57" height="20" /></li>			 
		</ul>
		</div>	
	</body>
</html>
