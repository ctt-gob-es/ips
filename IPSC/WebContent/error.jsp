<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<%=request.getContextPath()%>/css/estilos.css' type="text/css">
<%-- <link rel="stylesheet" href='<%=request.getContextPath()%>/css/estilo_060.css' type="text/css"> --%>
<title>IPS - ERROR</title>
</head>
<body>
<br>
	
	<br><br>
	<div id="error" class="pae-alertbox pae-alertbox--warning">
	<p class="pae-alertbox__text"><bean:write name="errorDescripcion"/></p></div>
	<br>
	<br>
	<br>
	<br>
</body>
</html>