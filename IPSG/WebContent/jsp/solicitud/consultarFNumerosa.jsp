<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="es.map.ipsg.util.Constantes"%>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>
<script type="text/javascript">
	window.onunload = refreshParent;
	function refreshParent() {
	    window.opener.location.reload();
	}
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:1em; margin-right:1em;">

<div class="pae-title"><spring:message code="svdccaafn.titulo.verificarFNumerosa"/></div>
<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form modelAttribute="verificarFNumerosaForm" action="/IPSG/spring/verificarFNumerosa?auxiliar=false" id="Form" >
	<form:hidden path="idSolicitud" />
	
	<br>
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	<br>

<c:choose>
    <c:when test="${formulario.vigenciaTitulo!=null}"> 	  
		<logic:equal name="formulario" property="vigenciaTitulo" value="<%=Constantes.VERIFICACION_FAMILIANUMEROSA_S%>">
				<c:choose>
					<c:when test="${formulario.categoriaFNumerosa!=null}"> 
			    		<logic:equal name="formulario" property="categoriaFNumerosa" value="<%=Constantes.CATEGORIA_FAMILIANUMEROSA_G%>">
			    			<logic:equal name="formulario" property="solicitanteFNumerosa" value="<%=Constantes.CATEGORIA_FAMILIANUMEROSA_G%>">
								<div class="verificacionOK">
								<spring:message code="svdccaafn.titulo.familiaNumerosa"/>
								</div>	
									<br>
								<spring:message code="svdccaafn.field.mensajeFNGeneralOK"/><br>
							</logic:equal>
							<logic:equal name="formulario" property="solicitanteFNumerosa" value="<%=Constantes.CATEGORIA_FAMILIANUMEROSA_E%>">
								<div class="verificacionKO">
								<spring:message code="svdccaafn.titulo.familiaNumerosa"/>
								</div>	
									<br>
								<spring:message code="svdccaafn.field.mensajeErrorGeneral"/><br>
							</logic:equal>						
			            </logic:equal>
			            <logic:equal name="formulario" property="categoriaFNumerosa" value="<%=Constantes.CATEGORIA_FAMILIANUMEROSA_E%>">
			            	<logic:equal name="formulario" property="solicitanteFNumerosa" value="<%=Constantes.CATEGORIA_FAMILIANUMEROSA_E%>">
								<div class="verificacionOK">
								<spring:message code="svdccaafn.titulo.familiaNumerosa"/>
								</div>	
									<br>
								<spring:message code="svdccaafn.field.mensajeFNEspecialOK"/><br>
							</logic:equal>
							<logic:equal name="formulario" property="solicitanteFNumerosa" value="<%=Constantes.CATEGORIA_FAMILIANUMEROSA_G%>">
							<div class="verificacionOK">
								<spring:message code="svdccaafn.titulo.familiaNumerosa"/>
								</div>	
									<br>
								<spring:message code="svdccaafn.field.mensajeEspecial"/><br>
							</logic:equal>
					    </logic:equal>
					    </c:when>
					    </c:choose>
			</logic:equal>
		

			<logic:equal name="formulario" property="vigenciaTitulo" value="<%=Constantes.VERIFICACION_FAMILIANUMEROSA_N%>">
				<c:choose>
					<c:when test="${formulario.categoriaFNumerosa!=null}"> 
			    		<logic:equal name="formulario" property="categoriaFNumerosa" value="<%=Constantes.CATEGORIA_FAMILIANUMEROSA_G%>">
								<div class="verificacionKO">
								<spring:message code="svdccaafn.titulo.familiaNumerosa"/>
								</div>	
								<br>
								<spring:message code="svdccaafn.field.mensajeFNGeneralKO"/><br>
						</logic:equal>
						<logic:equal name="formulario" property="categoriaFNumerosa" value="<%=Constantes.CATEGORIA_FAMILIANUMEROSA_E%>">
								<div class="verificacionKO">
								<spring:message code="svdccaafn.titulo.familiaNumerosa"/>
								</div>	
								<br>
								<spring:message code="svdccaafn.field.mensajeFNEspecialKO"/><br>
						</logic:equal>
					</c:when>
				</c:choose>
			</logic:equal>
	</c:when>
					

	<c:otherwise>
       	<div class="verificacionKO">
			<spring:message code="svdccaafn.titulo.familiaNumerosa"/>
		</div>	
		<br>
		<spring:message code="svdccaafn.field.mensajeError"/><br>
  	</c:otherwise>
</c:choose>
	
	<div class="clear"></div><br><br>
	<div class="filaBtn">
 		<div class="class="pae-box-buttons">
			<div class="pae-box-buttons">	
				<input type="submit" name="accion" value="Aprobar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>		
				<input type="submit" name="accion" value="Rechazar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>			
				<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
			</div>
		</div>	
	</div>
	
</form:form>
</body>
</html>

