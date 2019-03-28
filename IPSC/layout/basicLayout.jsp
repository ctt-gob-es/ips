
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- Template Basico
  Template en el que sea crea una cabecera, un menu a la izquierda, el
  cuerpo y un pie

  @param title Titulo de la pagina
  @param header Cabecera
  @param menu Menu
  @param body Body
  @param footer Pie
--%>
<!DOCTYPE html>
<form:form>
<head>
<title><tiles:getAsString name="title" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Compatibilidad con Windows 8 -->
<meta http-equiv="X-UA-Compatible" content="requiresActiveX=true"/>
<link rel="shortcut icon" type="image/ico" href="../images/ipsFavicon.ico" type="image/x-icon" id="idFavicon"/>
</head>
<body>
	<td><tiles:insertAttribute name="header" /></td>
	<td><tiles:insertAttribute name="body" /></td>
	<td><tiles:insertAttribute name="footer" /></td>

</body>
</form:form>