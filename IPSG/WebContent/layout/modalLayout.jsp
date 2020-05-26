<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="bean" uri="http://www.mpr.es/ipsg/bean-tags" %>
<%@ taglib prefix="html" uri="http://www.mpr.es/ipsg/html-tags" %>
<%@ taglib prefix="logic" uri="http://www.mpr.es/ipsg/logic-tags" %>

<%-- <%@ page errorPage=errorpage.jsp"%> --%>

<%-- Tenplate Basico
  Template en el que sea crea una cabecera, un menu a la izquierda, el
  cuerpo y un pie

  @param title Titulo de la pagina
  @param header Cabecera
  @param menu Menu
  @param body Body
  @param footer Pie
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv='pragma' content='no-cache'>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><tiles:getAsString name="title" /></title>

<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilos.css' type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilo_060.css' type="text/css">
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="0" style="margin-left: 0px">
		<tr>
			<td style="margin: 25px;"><tiles:insertAttribute name="body" /></td>
		</tr>
	</table>
</body>
</html>
