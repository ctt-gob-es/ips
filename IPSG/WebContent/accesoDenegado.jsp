<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="title.accesoDenegado"/></title>
</head>
<body>
	<div id="cuerpo">
		<br/>
		<div align="center">
			<h1>
				<font color="#c33400">
					<spring:message code="field.accesoDenegado"/>
				</font>
			</h1>
			<br><br>
		</div>
		<div align="center" class="texto_introduccion">
			<p align="center"><spring:message code="field.accesoDenegado.explicacion"/></p>
		</div>
	</div>
</body>
</html>
