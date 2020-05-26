<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<script type="text/javascript">
function validaDatos(){
	var pwdNueva = document.getElementById("passwordNueva").value;
	var pwdNuevaConf = document.getElementById("confirmacionPasswordNueva").value;
	
	if(pwdNueva==""){
		alert('<spring:message code="usuario.errores.passwordNuevaRequerido"/>');
		return false;
	}
	if(pwdNuevaConf==""){
		alert('<spring:message code="usuario.errores.confirmacionPasswordNuevaRequerido"/>');
		return false;
	}
	
	return true;
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;" class="background-color-white-ip">


<div class="pae-body2" style="overflow-y:hidden">				
<form:form modelAttribute="cambiarContrasenaUsuariosForm" action="/IPSG/spring/cambiarContrasenaUsuarios" method="post">	
<div class="pd-top-3">	
<div class="pae-layout__item">	
	<form:hidden path="id" value="${param.id}"/>	
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>



<logic:present name="mensajeConfirmacion">
		
	
		<div data-function="accordion-box" class="pae-box">
			<div class="pae-box__header">
				<h3 class="pae-box__header--title">
					<bean:write name="mensajeConfirmacion"/>
				</h3>
			</div>
		</div>
			<div class="pae-box__body2">
				<fieldset>
					<div class="pae-layout">		
						<%-- Boton --%>
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-40">
								<div class="pae-box-buttons">
									<input type="submit" value="Cerrar" onclick="javascript:window.close();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"" id="REC"/>								
								</div>
							</div>
						</div>	
					</div>
				</fieldset>
			</div>
</logic:present>
<logic:notPresent name="mensajeConfirmacion">
<%-- MAQUETACION INI --%>
	<h1 class="pae-title2">
				<spring:message code="field.usuario.tituloCambiarContrasena"/> 
	</h1>
	<div class="pae-box">	
		<div class="pae-box__header2">
			<fieldset>
				<div class="pae-layout">
<!-- 					USER -->
					<div class="pae-layout__item">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.passwordNueva.mayus"/><span class="obligatorio"> *</span>
				
							</span>
							<form:password path="passwordNueva" class="pae-form__input w100" maxlength="15" size="15"/>	
						</div>
					</div>
<!-- 					USER -->
				</div>
				<div class="pae-layout">
<!-- 					PASS -->
					<div class="pae-layout__item">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">								
								<spring:message code="field.usuario.confirmacionPassword.mayus"/><span class="obligatorio"> *</span>
							</span>			
							<form:input type="password" path="confirmacionPasswordNueva" id="confirmacionPasswordNueva" class="pae-form__input w100" maxlength="15"/>
						</div>
					</div>
<!-- 					PASS -->
				</div>		
				<div class="filaBtn w100">
					<div class="pae-box-buttons">
						<input type="submit" value="Guardar" onclick="return validaDatos();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-separation"/>
						<input type="submit" value="Cancelar" onclick="javascript:window.close();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1" id="REC"/>
					</div>
				</div>
											
			</fieldset>
		</div>
	</div>
	
<%-- MAQUETACION FIN --%>
</logic:notPresent>	
</div>
</div>
</form:form>

</div>
</body>
</html>