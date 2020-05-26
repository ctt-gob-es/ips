<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%
String user = (String)request.getAttribute("u");
String pass = (String)request.getAttribute("p");
System.out.println("User: "+user);
System.out.println("Pass: "+pass);

%>
<script type="text/javascript" src="../javascript/md5.js"></script>
<script type="text/javascript">

var v_user = "<%=user%>";
var v_pass = "<%=pass%>";

function cargarLogin(){
	document.getElementById("login").style.display = "none";
	//alert("Entra en el onload del certificado");
	document.getElementById("usernameField").value =v_user;
	document.getElementById("passwordField").value = v_pass;
	//alert("username: "+ document.getElementById("usernameField").value);
	//alert("passwordField: "+ document.getElementById("passwordField").value);
	
	document.forms[0].submit();
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body onload="cargarLogin();">
	<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
		<div id="login" style="display: none">
		<input id="usernameField" type="text" name="j_username"  
			value="" maxlength="15" size="40"/>
		<input id="passwordField" type="password" name="j_password" 
			maxlength="15" size="40"/>
		</div>
	</form>
</body>
</html>