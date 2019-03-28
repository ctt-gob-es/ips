<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


						<div id="${param.comunidadesFN}" style="display: none; ">
								<div class="clear"></div>
								<div class="capaDer" style="width: 99%">
								<div class="labelIzqDet_Rojo">
								<spring:message code="field.pagoSolicitud.Comunidades"/>
								<input type="image"
								src="<%=request.getContextPath()%>/images/icono_informacion.JPG"
								alt="Informacion Comunidades Autónomas Familia Numerosa"
								title="<spring:message code="field.comunidadAutonoma.FNumerosa"/>"
								onclick="return ventanaInformacion('FN')">
								</div>								
								<div class="labelDrc" align="left" >
									<form:select path="${param.idComunidadFN}" class="select_1" id="${param.idComunidadFN}" onchange="javascript:comunidadFNChecked()">
											<option value="">         </option>
											<form:options items="${listcomunidadesFN}" itemValue="idComunidad" itemLabel="descripcion" />
									</form:select>			
								</div>
							</div>
						</div>	
					   <div id="${param.tituloFN}" style="display: none; ">
								<div class="clear"></div>
								<div class="capaDer" style="width: 99%">
								<div class="labelIzqDet_Rojo">
								<spring:message code="field.pagoSolicitud.TituloFN"/>
								<span class="obligatorio">*</span>
								</div>
								<div class="labelDrc" align="left" >	
								<form:input path="${param.idtituloFN}" id="${param.idtituloFN}" maxlength="20"/>
								</div>
							</div>
						</div>	
	