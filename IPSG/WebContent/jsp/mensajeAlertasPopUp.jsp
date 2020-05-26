<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:1em;">

<br>

<h1 class="pae-title"><spring:message code="field.alertas.titulo"/></h1>

<br><br>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 80%;">
	
		<logic:messagesPresent message="true">
			<html:messages id="errorConvocatoria" message="true">
		      	<span class="mensajeWarning">
		      		<bean:write name="errorConvocatoria"/>
		      	</span> 
		      	<br/>
	  		</html:messages>
	  		<div class="clear"></div>
	  	</logic:messagesPresent>
	<div class="clear"></div>
	<br>
		
</div>
</body>
</html>