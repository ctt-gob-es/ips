<form:hidden path="registroSolicitud.idSolicitud" id="idSolicitud" />

<logic:present name="errorDescripcion">
	<spring:message code="field.solicitud.registroSolicitudError" />
</logic:present>

<logic:present name="registrosolicitud" property="numJustificante" scope="request">
	<div data-function="accordion-box" class="pae-box" id="boxPago">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.registroSolicitud.datosRegistro" />
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<legend class="pae-form__legend-text hiddenAccesible">
					<spring:message code="field.registroSolicitud.datosRegistro" />
				</legend>
				<div class="pae-layout">
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="formaPago" class="pae-form__label"><span
								class="pae-form__label--text"><spring:message
										code="field.registroSolicitud.numeroRegistro" /></span></label> <span
								class="pae-form__text-readonly"><bean:write
									name="registrosolicitud" property="numeroRegistro"
									scope="request" /></span>
						</div>
					</div>
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="motivoReduccion" class="pae-form__label"><span
								class="pae-form__label--text"><spring:message
										code="field.registroSolicitud.fechaRegistro" /></span></label> <span
								class="pae-form__text-readonly"><bean:write name="registrosolicitud" property="fechaRegistro" scope="request" /></span>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
	<div class="pae-box pae-box--transparent pae-box--align-right pae-box--force-align-center">
  		<div class="pae-box__body--big">
			<div class="pae-box-buttons">
				<button type="button" title="field.documentosAnexados" id="botonMisInscripciones" class="pae-buttons pae-buttons--default pae-buttons--medium pae-buttons--text-size-normal pae-separation" 
						onclick="javascript: llamadaBuscarIncripciones();return false;"><spring:message code="field.menu.misInscripciones"/></button>
				<a href="buscarConvocatorias?form=L" title="Volver al listado de convocatorias" class="">
					<button type="button" title="field.Volver" id="botonVolver2" class="pae-buttons pae-buttons--default pae-buttons--medium pae-buttons--text-size-normal"><spring:message code="field.Volver" /></button>
				</a>
			</div>
		</div>
	</div>
</logic:present>

<script type="text/javascript">
function volver(){
	window.location.href = "<%=request.getContextPath()%>/secure/buscarSolicitudes?llamada=<%=request.getParameter("llamada")%>&form=L"; 
}
</script>