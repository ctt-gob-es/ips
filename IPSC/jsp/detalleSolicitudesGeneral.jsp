<jsp:useBean id="solicitud" scope="request" class="es.map.ipsc.bean.DetalleSolicitudBean" />
	<form:hidden path="registro" id="re" name="solicitud"/>
	<form:hidden name="altaSolicitud" path="importeOriginal" id="importeOriginal" value="${altaSolicitud.importeOriginal}"/>
	<input type="hidden" name="importe" id="importe"/>
	<input type="hidden" name="idConsentimiento" id="idConsentimiento" value="false"/>
	
<%@ include file="/jsp/informacionConvocatoria.jsp" %>
<%@ include file="/jsp/datosFuncionarioHabilitado.jsp" %>
<%@ include file="/jsp/detalleDatosSolicitante.jsp" %>
<%@ include file="/jsp/detallesDatosConvocatoria.jsp" %>
<%@ include file="/jsp/detalleInformacionAdicional.jsp" %>