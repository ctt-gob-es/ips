<%-- <%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mpr.es/ipsg/tags" prefix="ipsg" %>

<html>
<head></head>
<body>
<div id="destBloqeuDer">
	<div id="caja_destacados">
	<ul>
		<ipsg:submenu-authorize key="field.menuLateral.alertas.buscar" />
		<ipsg:submenu-authorize key="field.menuLateral.alertas.alta" />			
	</ul>
</div>
	<div class="flotacion"></div>
</div>
</body>
</html>