<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>


<%-- Template Basico
  Template en el que sea crea una cabecera, un menu a la izquierda, el
  cuerpo y un pie

  @param title Titulo de la pagina
  @param header Cabecera
  @param menu Menu
  @param body Body
  @param footer Pie
--%>

<!doctype html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="shortcut icon" type="image/ico" href="../../images/ipsFavicon.ico" type="image/x-icon" id="idFavicon"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv='pragma' content='no-cache'>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<title><tiles:getAsString name="title" /></title>

	
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/constantes.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<span style="display: none">INICIOERRORESSOLICITUD</span><html:errors /><span style="display: none">FINERRORESSOLICITUD</span>
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</body>
</html>