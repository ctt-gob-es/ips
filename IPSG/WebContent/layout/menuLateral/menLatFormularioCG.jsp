<%-- <%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.mpr.es/ipsg/tags" prefix="ipsg" %>
<!--INI-TRA042-->
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>

<script type="text/javascript">

function llamadaBuscarPlantilla(idCg){
	$("#idCentroGestor").val(idCg);
	var formu = $("#formPadre");
	formu.submit();
}

</script>
<!--FIN-TRA042-->
<html>
<head></head>
<body>

<div id="destBloqeuDer">
	<div id="caja_destacados">
	<!--INI-TRA042-->
	<logic:present name="listaCentrosGestores" scope="request">
		<logic:iterate id="centroGestor" name="listaCentrosGestores" >
			<ul>
				<bean:write name="centroGestor" property="descripcion" />
				<li>
					<p style="cursor:pointer;" onclick="llamadaBuscarPlantilla($('#centroGestor<bean:write name="centroGestor" property="id" />').val())">Formulario Centro Gestor</a>
				</li>
				<input id="centroGestor<bean:write name="centroGestor" property="id" />" type="hidden" value="<bean:write name="centroGestor" property="id" />"/>
			</ul>
		</logic:iterate>
	</logic:present>
	<!--FIN-TRA042-->
</div>
	<div class="flotacion"></div>
	</div>
</body>
</html>