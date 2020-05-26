<div data-function="fc-call-add-document" class="pae-modal">
	<div class="pae-modal__body pae-modal__body--form">
		<fieldset>
			<legend class="pae-form__legend-text hiddenAccesible">
				<spring:message code="field.convocatoria.mensajeDocumentosAdjuntos__1" />
			</legend>
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="selectDocumento" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.convocatoria.mensajeDocumentosAdjuntos__2" /></span></label> <select
							id="selectDocumento" name="selectDocumento"
							data-function="function-selectize-modal"
							data-placeholder="Selecciona..." data-files="document-type"
							class="pae-form__select">
							<option value="0"><spring:message code="field.solicitud.selecciona"/></option>
							<option value="1">
								<spring:message code="field.registro.documentos.adjuntoSolicitud" />
							</option>
							<option value="6">
								<spring:message
									code="field.registro.documentos.justificanteExencion" />
							</option>
							<option value="10">
								<spring:message
									code="field.registro.documentos.justificanteDiscapacidad" />
							</option>
							<% if(tipoPersona.equals("FH")){ %>
								<option value="29">
									<spring:message
										code="field.registro.documentos.autorizacionConsentimiento" />
								</option>
							<% } %>
						</select>
					</div>
				</div>
				<%
					for (int i = 0; i < numDocsAdjuntos; i++) {
				%>
				<input type="hidden" id="rutaArchivo<%=i%>" value="" />
				<%
					}
				%>
				<form name="fileUploadForm"
					action="<%=request.getContextPath()%>/fail"
					method="POST"
					class="pae-form fc-form-modal"
					enctype="multipart/form-data" id="formArchivo">
					<input type="hidden" name="numSolicitudFile" id="numSolicitudFile"/>
					<%
						for (int i = 0; i < numDocsAdjuntos; i++) {
					%>
					<input type="hidden" id="tamanioArchivo[<%=i%>]" value="0">
					<%
						}
					%>
					<input type="hidden" id="numeroDeArchivo" value=""/>
					<div class="fc-wrap-files-container"></div>
			 		<%
					for (int i = 0; i < numDocsAdjuntos; i++) {
						pageContext.setAttribute("i", Integer.valueOf(i));			
						String nameDocFile = "documentoFile[" + i + "]";
						String nameTipoDoc = "tipoDocAdjunto[" + i + "]";
						//String nameDocFileFirefox = "documentoFile[" + i + "]";
						String docFic = "documentosFicheros[" + i + "]";
						String nameExtractoFirefox = "extracto[" + i + "]";
						String tipoDocumento = "tipoDocumento[" + i + "] + maxlength=\"200\"";
					%>
						
						<input type="hidden" name="<%=nameExtractoFirefox%>" class="text_Largo" maxlength="100"  id="<%=nameExtractoFirefox%>" />
						<input type="hidden" name="<%=nameTipoDoc%>" class="text_Largo" maxlength="100"  id="<%=nameTipoDoc%>" />
						<input type="hidden" name="<%=tipoDocumento%>" class="text_Largo" maxlength="100"  id="<%=tipoDocumento%>" />
						<input type="hidden" name="<%=nameDocFile%>" class="text_Largo" maxlength="100"  id="<%=nameDocFile%>" />
						<input type="hidden" name="<%=docFic%>" cols="80" rows="3" id="<%=docFic%>" /> <br />
					<%
					}
					%>
				</form>
			</div>
			<div
				class="pae-modal__footer pae-modal__footer--right pae-modal__footer--padding pae-modal__footer--general">
				<div class="pae-box-buttons pae-box-buttons--two-buttons">
					<button type="button" data-function="button-close"
						class="pae-buttons pae-buttons--default pae-buttons--white pae-buttons--medium outline pae-buttons--half">
						<spring:message
							code="field.convocatoria.mensajeDocumentosAdjuntos__5" />
					</button>
					<button type="button" data-function="button-submit-files"
						class="pae-buttons pae-buttons--default pae-buttons--medium outline pae-buttons--half">
						<spring:message
							code="field.convocatoria.mensajeDocumentosAdjuntos__1" />
					</button>
				</div>
			</div>
		</fieldset>
	</div>
</div>