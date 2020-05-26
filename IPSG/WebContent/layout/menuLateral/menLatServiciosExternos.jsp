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
				<ipsg:submenu-authorize key="field.menuLateral.serviciosExternos.pruebaServicio"/>
				<ipsg:submenu-authorize key="field.menuLateral.serviciosExternos.pruebaInscripcion"/>
				<ipsg:submenu-authorize key="field.menuLateral.serviciosExternos.consultarEstadistica"/>
				<ipsg:submenu-authorize key="field.menuLateral.serviciosExternos.configurarServicio"/>
			</ul>
		</div>
		<div class="flotacion"></div>
	</div>	
</body>
</html>