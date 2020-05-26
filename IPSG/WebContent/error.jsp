<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	window.onunload = refreshParent;
	function refreshParent() {
	    window.opener.location.reload();
	}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body class="background-color-white-ip">

<style>
@media screen and (min-width: 800px) {
  #responsive {
    display: none;
  }
}

@media screen and (max-width: 800px) {
  #normal {
    display: none;
  }
}
</style>
<div id="responsive">
<div class="pae-body2" style="overflow-y:hidden">
<div class="pae-title2">Error</div>
 <div class="pae-box">
 	<div class="pae-box__header2">
			<div class="sub_titulo">
				<p align="center"><spring:message code="error"/></p>
			</div>
				<bean:write name="descripcionError"/>
				<div class="clear"></div>
			<div class="pae-layout__item">
				<div class="filaBtn">
					<div class="pae-box-buttons">		
						<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
					</div>
				</div>
			</div>
		</div>
 	</div>
</div>
</div>

<div id="normal">
	
	
	<div class="pae-body2" style="overflow-y:hidden">
		<div class="pae-title2">Error</div>
		<div class="pae-box">
	 		<div class="pae-box__header2">
				<div class="sub_titulo">
					<p align="center"><spring:message code="error"/></p>
				</div>
					<bean:write name="descripcionError"/>
					<div class="clear"></div>			
			</div>
	 	</div>
	</div>

</div>			
</body>
</html>