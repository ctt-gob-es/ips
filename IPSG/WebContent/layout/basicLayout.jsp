<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="bean" uri="http://www.mpr.es/ipsg/bean-tags" %>
<%@ taglib prefix="logic" uri="http://www.mpr.es/ipsg/logic-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv='pragma' content='no-cache'>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><tiles:getAsString name="title" /></title>

</head>
<body>
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="menu" />
			<div class="pae-body" id="pae-body">
				<section class="contenido_left">
					<nav class="nav_sec">
						<tiles:insertAttribute name="menu_lateral" />
					</nav>
				</section>
				<section class="main">
					<tiles:insertAttribute name="body" />
				</section>
			</div>
			<tiles:insertAttribute name="footer" />

</body>
</html>
