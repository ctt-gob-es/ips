<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>

<%
byte[] pdf = (byte[])request.getAttribute("contenido");
%>

<script type="text/javascript">

function imprimirPagina(){
	
	var contenidoPdf= '<%=pdf%>';
	document.write(contenidoPdf);
	window.print( );
}
</script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>
<body style="margin-left:0.4em;" onload="imprimirPagina();">
<br>
<div id="content_tituloNivel1">

</div>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">



 
	
</div>
</body>
</html>


