<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

<%
String idSolicitud = (String)request.getAttribute("idSolicitud");
System.out.println("Id: "+idSolicitud);
%>

<script type="text/javascript">

var idSol = "<%=idSolicitud%>";

function pagarSolicitud(){
	alert("entra en javascript");
	window.location.href = "<%=request.getContextPath()%>/secure/verPagarSolicitud?id="+idSol;	
}

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<br>
		<div class="tituloPag">
	     	<spring:message code="error.titulo"/>
	    </div> 
	    <br><br>
	
		<div id="error">
			<bean:write name="errorDescripcion"/>
			<br>
		</div>
	<div class="clear"></div>
	<br>
<form:form modelAttribute="pagoSolicitudForm" action="IPSC/secure/ConsultaPago" method="post" id="form">
	<div>
       	<button type="submit" name="action" id="action" titleKey="field.consultarPago" class="botonSmall103" >
       		<spring:message code="field.consultarPago"/>
       	</button>
       	<button type="submit" name="action" id="action" titleKey="field.volverPago"  class="botonSmall103" onclick="pagarSolicitud();">
       		<spring:message code="field.volverPago "/>
       	</button>
	</div>
</form:form>
</body>
</html>