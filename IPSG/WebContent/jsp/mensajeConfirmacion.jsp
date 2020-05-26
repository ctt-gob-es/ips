<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function volver(url){
	window.location.href = url ; 
}

</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:1em;">

<br>

<h1 class="pae-title"><bean:write name="titulo"/></h1>	
<br><br>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 75%;">
<bean:define id="accion" name="accion"/>

<form:form  action="/IPSG${accion }" method="get">
	<div class="capaAll" align="center">
		<bean:write name="mensaje"/>
		<div class="clear"></div>
		<br><br>
		<div class="pae-box-buttons pd-right-1">		
			<input type="button" value="OK" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"" onclick="volver('/IPSG${accion }');"/>
		</div>
	</div>
	<div class="clear"></div>
	<br>
</form:form>	
	
</div>
</body>
</html>