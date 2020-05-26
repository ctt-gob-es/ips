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

<body style="margin-left:1em; margin-right:1em;" class="background-color-white-ip">

<div class="pae-title2"><spring:message code="svdsepe.titulo.verificarDesempleo"/></div>
<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form modelAttribute="verificarDesempleoForm" action="/IPSG/spring/verificarDesempleo?auxiliar=false" >
	<form:hidden path="idSolicitud" />
		
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	
	
<%-- MAQUETACION INI --%>
<div class="">
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__body">	
	
	<logic:notPresent name="desempleo" scope="request">
		<spring:message code="svdsepe.error.desempleo.noRecuperada"/>
	</logic:notPresent>
		
	<div class="clear"></div><br><br>
		
		<logic:present name="desempleo" scope="request">			
			<logic:equal name="desempleo" property="resultadoVerificacionDias" value="true">
				<div class="verificacionOK">
					<spring:message code="svdsepe.titulos.dias.encontrados"/>
				</div>	
				
				<bean:define id="diasDesempleo" name="desempleo" property="diasDemandanteEmpleo" type="java.lang.Integer"/>
				<spring:message code="svdsepe.field.mensajeDiasDesempleoValido.a"/>${diasDesempleo}
				<spring:message code="svdsepe.field.mensajeDiasDesempleoValido.b"/>
				
			</logic:equal>
			<logic:notEqual name="desempleo" property="resultadoVerificacionDias" value="true">	
				<div class="verificacionKO">
					<spring:message code="svdsepe.titulos.dias.encontrados"/>
				</div>	
				<br>	
				<logic:notEmpty name="desempleo" property="diasDemandanteEmpleo" >
					<bean:define id="diasDesempleo" name="desempleo" property="diasDemandanteEmpleo" type="java.lang.Integer"/>
					<spring:message code="svdsepe.field.mensajeDiasDesempleoInvalido.a" />${diasDesempleo}
					<spring:message code="svdsepe.field.mensajeDiasDesempleoInvalido.b" />
					<br>
				</logic:notEmpty>
				<logic:empty name="desempleo" property="diasDemandanteEmpleo" >
					<spring:message code="svdsepe.field.NoDiasDesempleoSEPE"/><br>
				</logic:empty>	
			</logic:notEqual>
		</logic:present>
	
	<div class="clear"></div><br><br>
		
		<logic:present name="desempleo" scope="request">			
			<logic:equal name="desempleo" property="resultadoVerificacionSalario" value="true">
				<div class="verificacionOK">
					<spring:message code="svdsepe.titulos.salario.encontrados"/>
				</div>	
				<br>
				<logic:notEmpty name="desempleo" property="salarioDemandanteEmpleo" >
					<bean:define id="salarioDesempleo" name="desempleo" property="salarioDemandanteEmpleo" type="java.lang.Float"/>
					<spring:message code="svdsepe.field.mensajeSalarioValido.a"/>${salarioDesempleo}
					<spring:message code="svdsepe.field.mensajeSalarioValido.b"/>
					<br>
				</logic:notEmpty>
				<logic:empty name="desempleo" property="salarioDemandanteEmpleo" >
					<spring:message code="svdsepe.field.NoSalarioDesempleoSEPE"/><br>
				</logic:empty>
			</logic:equal>
			<logic:notEqual name="desempleo" property="resultadoVerificacionSalario" value="true">
				<div class="verificacionKO">
					<spring:message code="svdsepe.titulos.salario.encontrados"/>
				</div>	
				<br>			
				<logic:notEmpty name="desempleo" property="salarioDemandanteEmpleo" >
					<bean:define id="salarioDesempleo" name="desempleo" property="salarioDemandanteEmpleo" type="java.lang.Float"/>
					<c:choose>
					    <c:when test="${salarioDesempleo > 0}">
							<bean:define id="ejercicio" name="desempleo" property="ejercicio" type="java.lang.String"/>
							<spring:message code="svdsepe.field.mensajeSalarioInvalido.a1"/>${ejercicio}
							<spring:message code="svdsepe.field.mensajeSalarioInvalido.a2"/>${salarioDesempleo}
					<spring:message code="svdsepe.field.mensajeSalarioInvalido.b"/>
					<br>
					    </c:when>    
					    <c:otherwise>
							<spring:message code="svdsepe.field.mensajeSalarioInvalido.noDatos"/>
					    </c:otherwise>
					</c:choose>					
				</logic:notEmpty>
			</logic:notEqual>
			<div class="clear"></div><br><br>
			<logic:equal name="desempleo" property="tipoIM" value="true">
				<spring:message code="svdsepe.field.mensajeImputaciones"/>
				<table id="imputacionesTable" class="pae-table pae-table--margin">
					<thead class="pae-table__header">
						<tr class="pae-table__row">
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="svdsepe.field.cabecera.1"/>
													</span>	
												</div>
											</th>								
										</tr>
									</tbody>
								</table>
							</th>
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
															<spring:message code="svdsepe.field.cabecera.2"/>
													</span>	
												</div>
											</th>								
										</tr>
									</tbody>
								</table>
							</th>
						</tr>
					</thead>
					<logic:iterate id="imputacion" name="desempleo" property="listaImputaciones" indexId="index">
						<tr class="pae-table__row" name="row">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
								<span class="pae-table__txt-base pae-table__span-head">
									<bean:write name="imputacion" property="literal" />
								</span>
							</td>
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
								<span class="pae-table__txt-base pae-table__span-head">
									<bean:write name="imputacion" property="enteros" />,<bean:write name="imputacion" property="decimales" />
								</span>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</logic:equal>
			<div class="clear"></div><br><br>
		</logic:present>

	<%-- Boton --%>
	<div class="filaBtn">		
		<div class="pae-box-buttons">	
			<input type="submit" name="accion" value="Aprobar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
			<input type="submit" name="accion" value="Rechazar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
			<input type="submit" name="accion" value="Cerrar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation" onclick="javascript: window.close()">	
		</div>				
	</div>
	
	</div>
</div>
</div>	
<%-- MAQUETACION FIN --%>
</form:form>	
</body>
</html>

