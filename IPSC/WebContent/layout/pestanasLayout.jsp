<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>

<%-- Tenplate Basico
  Template en el que sea crea una cabecera, un menu a la izquierda, el
  cuerpo y un pie

  @param title Titulo de la pagina
  @param header Cabecera
  @param menu Menu
  @param body Body
  @param footer Pie
--%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv='pragma' content='no-cache'>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<title><tiles:getAsString name="title" /></title>

<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilos.css' type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilo_060.css' type="text/css">
</head>
<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td><tiles:insert attribute="header" /></td>
		</tr>
		<tr>		
			<td valign="top" align="left">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<table width="100%" cellspacing="0" cellpadding="0"
								style="margin-left: 0px">
								<tr>
									<td><tiles:insert attribute="menu" /></td>
								</tr>
								<tr>
									<td><html:errors /></td>
								</tr>
								<tr>
									<td><tiles:insert attribute="subMenu" /></td>
								</tr>								
								<tr>
									<td style="margin: 25px;"><tiles:insert attribute='body' /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><tiles:insert attribute="footer" /></td>
		</tr>
	</table>

</body>
</html:html>
