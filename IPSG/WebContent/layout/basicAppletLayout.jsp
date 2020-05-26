<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="bean" uri="http://www.mpr.es/ipsg/bean-tags" %>
<%@ taglib prefix="logic" uri="http://www.mpr.es/ipsg/logic-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- <%@ page errorPage="errorpage.jsp"%> --%>

<%-- Tenplate Basico
  Template en el que sea crea una cabecera, un menu a la izquierda, el
  cuerpo y un pie

  @param title Titulo de la pagina
  @param header Cabecera
  @param menu Menu
  @param body Body
  @param footer Pie
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv='pragma' content='no-cache'>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><tiles:getAsString name="title" /></title>

<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilos.css' type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilo_060.css' type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jquery-ui-1.10.4.custom.css" type="text/css">
	
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/constantes.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/miniapplet.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/afirmaIPS.js"></script>
	
</head>
<body>
	<script type="text/javascript">
		//cargarAppletFirma('MEDIA');
	</script>
	<table border="0" width="99%" cellspacing="0" cellpadding="0">
		<tr>
			<td><tiles:insertAttribute name="header" />
				<tiles:insertAttribute name="menu" /></td>
		</tr>
		<tr>		
			<td valign="top" align="left">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top">
							<table width="100%" cellspacing="0" cellpadding="0"
								style="margin-left: 0px">
								<tr>
									<td class="borde_menu_izq" style="margin: 5px;width: 15%;" valign="top"><tiles:insertAttribute name="menu_lateral" /></td>
									<td style="margin: 25px;;width: 84%;" valign="top"><tiles:insertAttribute name="body" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="footer" /></td>
		</tr>
	</table>

</body>
</html>

