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
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.fomularioBaseAdministrador" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.ministerio" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.centroGestor" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.gestionModelos"/>
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.cuerpoEscala" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.categoria" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.especialidad" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.formaAcceso" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.grupo" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.motivoReduccion" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.tituloOficial" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.otrosTitulos" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.tarifa" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.provincia" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.provinciaExamen" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.entidadFinanciera" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.actualizarParametrosConfiguracion" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.discapacidad" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.usuario" />
		<ipsg:submenu-authorize key="field.menuLateral.tablasBase.configuracionErrores" />
	</ul> 
</div>
	<div class="flotacion"></div>
	</div>
</body>
</html>