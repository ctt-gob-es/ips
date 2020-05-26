<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>

<script type="text/javascript">
function openWindow(action){
	ventanaPopup = window.open(action);
	ventanaPopup.focus();
}
</script>

<html>
<head></head>
<body>
		<div id="menu_participacion" style="width: 100%;background: url(../images/participacion/fondo_participacion_der.gif) right no-repeat #d10544;margin-bottom: 10px;margin-top: 10px;">
			<ul>
		 		<li class="menu_part_1">
		 			<a href="action/secure/buscarConvocatorias">&nbsp;<bean:message key="field.menu.listaConvoc"/></a>
		 		</li>
		 		<li class="menu_part_1">
		 			<a href="buscarSolicitudes?form=L">&nbsp;<bean:message key="field.menu.listaSolic"/></a>
		 		</li>
		 		<li class="menu_part_1">
		 			<a href="javascript: openWindow('<%=request.getContextPath()%>/descargarFormularios');">&nbsp;<bean:message key="field.descargarFormulario"/></a>		 			
		 		</li>
		 		<li class="menu_part_1">
		 			<a><bean:message key="field.validarRequisitos"/></a>		 			
		 		</li>
			</ul>	
		</div>
</body>
</html>