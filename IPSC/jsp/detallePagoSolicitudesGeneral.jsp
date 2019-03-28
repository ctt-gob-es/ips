<div data-function="accordion-box" class="pae-box" id="boxPago">
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.pagoSolicitud.datosPago" />
		</h3>
	</div>
	<logic:iterate id="pago" name="listaPagosAnteriores" scope="request">
	<c:if test="${pago.idPagoSolicitud ne  pagosolicitudBean.idPagoSolicitud}">
		<div class="pae-box__body">
		<fieldset class="boder_botom_gray">
			<legend class="pae-form__legend-text hiddenAccesible">
				<spring:message code="field.pagoSolicitud.datosPago" />
			</legend>
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="formaPago" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.registroSolicitud.formaPago" /></span></label> <span
							class="pae-form__text-readonly"><bean:write
								name="pago" property="tipo" scope="request" /></span>
					</div>
				</div>
				<logic:notEmpty name="pagosolicitudBean"
					property="desMotivoReduccion">
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="motivoReduccion" class="pae-form__label"><span
								class="pae-form__label--text"><spring:message
										code="field.extracto.motivoReduccion" /></span></label> <span
								class="pae-form__text-readonly" id="motivoReduccion"><bean:write
									name="pago" property="desMotivoReduccion"
									scope="request" /></span>
						</div>
					</div>
				</logic:notEmpty>
			</div>
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="importe" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.registroSolicitud.importe" /></span></label> <span
							class="pae-form__text-readonly" id="importe"><bean:write
								name="pago" property="importe" scope="request" /></span>
					</div>
				</div>
				<logic:notEmpty name="pago" property="comunidad">
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="comunidadExencion" class="pae-form__label"><span
								class="pae-form__label--text"><spring:message
										code="field.extracto.comunidadAuntonoma" /></span></label> <span
								class="pae-form__text-readonly" id="comunidadExencion"><bean:write
									name="pago" property="comunidad" scope="request" /></span>
						</div>
					</div>
				</logic:notEmpty>
			</div>
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="fechaPago" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.registroSolicitud.fechaPago" /></span></label> <span
							class="pae-form__text-readonly" id="fechaPago"><bean:write
								name="pago" property="fechaIntento" scope="request" /></span>
					</div>
				</div>
				<logic:notEmpty name="pago" property="numeroTitulo">
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="numeroTitulo" class="pae-form__label"><span
								class="pae-form__label--text"><spring:message
										code="field.extracto.numeroTitulo" /></span></label> <span
								class="pae-form__text-readonly" id="numeroTitulo"><bean:write
									name="pago" property="numeroTitulo"
									scope="request" /></span>
						</div>
					</div>
				</logic:notEmpty>
			</div>
			<logic:notEmpty name="pago" property="nrc">
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="numeroReferencia" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.registroSolicitud.numeroReferencia" /></span></label> <span
							class="pae-form__text-readonly" id="numeroReferencia"><bean:write
								name="pago" property="nrc" scope="request" /></span>
					</div>
				</div>
			</div>
			</logic:notEmpty>

		</fieldset>
	</div>
	</c:if>
	</logic:iterate>
	
	
	<div class="pae-box__body">
		<fieldset>
			<legend class="pae-form__legend-text hiddenAccesible">
				<spring:message code="field.pagoSolicitud.datosPago" />
			</legend>
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="formaPago" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.registroSolicitud.formaPago" /></span></label> <span
							class="pae-form__text-readonly"><bean:write
								name="pagosolicitudBean" property="tipo" scope="request" /></span>
					</div>
				</div>
				<logic:notEmpty name="pagosolicitudBean"
					property="desMotivoReduccion">
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="motivoReduccion" class="pae-form__label"><span
								class="pae-form__label--text"><spring:message
										code="field.extracto.motivoReduccion" /></span></label> <span
								class="pae-form__text-readonly" id="motivoReduccion"><bean:write
									name="pagosolicitudBean" property="desMotivoReduccion"
									scope="request" /></span>
						</div>
					</div>
				</logic:notEmpty>
			</div>
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="importe" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.registroSolicitud.importe" /></span></label> <span
							class="pae-form__text-readonly" id="importe"><bean:write
								name="pagosolicitudBean" property="importe" scope="request" /></span>
					</div>
				</div>
				<logic:notEmpty name="pagosolicitudBean" property="comunidad">
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="comunidadExencion" class="pae-form__label"><span
								class="pae-form__label--text"><spring:message
										code="field.extracto.comunidadAuntonoma" /></span></label> <span
								class="pae-form__text-readonly" id="comunidadExencion"><bean:write
									name="pagosolicitudBean" property="comunidad" scope="request" /></span>
						</div>
					</div>
				</logic:notEmpty>
			</div>
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="fechaPago" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.registroSolicitud.fechaPago" /></span></label> <span
							class="pae-form__text-readonly" id="fechaPago"><bean:write
								name="pagosolicitudBean" property="fechaIntento" scope="request" /></span>
					</div>
				</div>
				<logic:notEmpty name="pagosolicitudBean" property="numeroTitulo">
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="numeroTitulo" class="pae-form__label"><span
								class="pae-form__label--text"><spring:message
										code="field.extracto.numeroTitulo" /></span></label> <span
								class="pae-form__text-readonly" id="numeroTitulo"><bean:write
									name="pagosolicitudBean" property="numeroTitulo"
									scope="request" /></span>
						</div>
					</div>
				</logic:notEmpty>
			</div>
			<logic:notEmpty name="pagosolicitudBean" property="nrc">
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label for="numeroReferencia" class="pae-form__label"><span
							class="pae-form__label--text"><spring:message
									code="field.registroSolicitud.numeroReferencia" /></span></label> <span
							class="pae-form__text-readonly" id="numeroReferencia"><bean:write
								name="pagosolicitudBean" property="nrc" scope="request" /></span>
					</div>
				</div>
			</div>
			</logic:notEmpty>
			<%if(detalleRegistrado==null){	
 			%>
			<div class="pae-layout">
                  <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                      <logic:present name="registroSolicitud" scope="request">
					<logic:equal value="SI" name="registroSolicitud" property="aniadirDocumentos">
						<logic:present name="documentos" scope="request">
						<bean:size id="numResultados" name="documentos" scope="request"/>
						<logic:greaterThan name="numResultados" value="0">
									<legend id="DocumentosAdjuntados" class="pae-form__legend-text"><spring:message code="field.solicitud.documentosAdjuntados"/></legend>
										<div class="pae-form-files pae-form-files--active hiddenAccesible" id="tablaDocumentos4">
											<div class="fc-pae-form__document--files pae-form__document">
												<span class="pae-form__doc-list-title"><spring:message code="field.registro.documentos.adjuntoSolicitud"/></span>
												<logic:iterate id="registro" name="documentos" >
													<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumento?ent=Solicitudes";%>
													<logic:equal value="ANEXO DE SOLICITUD" name="registro" property="dsTipoDocumento">
													<script>
													$("#tablaDocumentos4").removeClass("hiddenAccesible");
													</script>
													<input type="hidden" id="documentoEncontrado4" value="1"/>
													<ul class="fc-load-files pae-form__list--document" id="table${registro.id}">
														<li class="pae-form__list-item--document">
															<a href="<%=urlDescarga %>&id=${registro.id}">
															<span class="pae-form__list-item-text"><bean:write name="registro" property="nombre" />
															</a></span>
															<span class="pae-form__list-item-text pae-form__list-item-text--size">(${registro.tamano}MB)</span>
															<button type="button" class="pae-form__input-button" onclick="eliminarDocumentoSubido(${registro.id},4)" name="documentos4">
															<span class="hiddenAccesible"><spring:message code="field.solicitud.botonEliminar"/></span></button></li>
													</ul>
													</logic:equal>
												</logic:iterate>
											</div>
										</div>
										<div class="pae-form-files pae-form-files--active hiddenAccesible" id="tablaDocumentos5">
											<div class="fc-pae-form__document--files pae-form__document">
												<span class="pae-form__doc-list-title"><spring:message code="field.registro.documentos.justificanteDiscapacidad"/></span>
												<logic:iterate id="registro" name="documentos" >
													<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumento?ent=Solicitudes";%>
													<logic:equal value="JUSTIFICANTE DE DISCAPACIDAD" name="registro" property="dsTipoDocumento">
													<script>
													$("#tablaDocumentos5").removeClass("hiddenAccesible");
													</script>
													<input type="hidden" id="documentoEncontrado5" value="1"/>
													<ul class="fc-load-files pae-form__list--document" id="table${registro.id}">
														<li class="pae-form__list-item--document">
															<a href="<%=urlDescarga %>&id=${registro.id}">
															<span class="pae-form__list-item-text"><bean:write name="registro" property="nombre" />
															</a></span>
															<span class="pae-form__list-item-text pae-form__list-item-text--size">(${registro.tamano}MB)</span>
															<button type="button" class="pae-form__input-button" onclick="eliminarDocumentoSubido(${registro.id},5)" name="documentos5">
															<span class="hiddenAccesible"><spring:message code="field.solicitud.botonEliminar"/></span></button></li>
													</ul>
													</logic:equal>
												</logic:iterate>
											</div>
										</div>
										<div class="pae-form-files pae-form-files--active hiddenAccesible" id="tablaDocumentos6">
											<div class="fc-pae-form__document--files pae-form__document">
												<span class="pae-form__doc-list-title"><spring:message code="field.registro.documentos.justificanteExencion2"/></span>
												<logic:iterate id="registro" name="documentos" >
													<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumento?ent=Solicitudes";%>
													<logic:equal value="JUSTIFICANTE DE REDUCCION DE TASA" name="registro" property="dsTipoDocumento">
													<script>
													$("#tablaDocumentos6").removeClass("hiddenAccesible");
													</script>
													<input type="hidden" id="documentoEncontrado6" value="1"/>
													<ul class="fc-load-files pae-form__list--document" id="table${registro.id}">
														<li class="pae-form__list-item--document">
															<a href="<%=urlDescarga %>&id=${registro.id}">
															<span class="pae-form__list-item-text"><bean:write name="registro" property="nombre" />
															</a></span>
															<span class="pae-form__list-item-text pae-form__list-item-text--size">(${registro.tamano}MB)</span>
															<button type="button" class="pae-form__input-button" onclick="eliminarDocumentoSubido(${registro.id},6)" name="documentos6" >
															<span class="hiddenAccesible"><spring:message code="field.solicitud.botonEliminar"/></span></button></li>
													</ul>
													</logic:equal>
												</logic:iterate>
											</div>
										</div>
						</logic:greaterThan>
						</logic:present>
					</logic:equal>
				</logic:present>
                      <legend class="pae-form__legend-text"><spring:message code="field.convocatoria.documentos"/></legend>
                        <div class="fc-form-files pae-form-files"></div><a href="#" title="Adjuntar documentos" data-function="fc-add-document" class="pae-form__icon-link--add-nomargin">
                        	<span class="pae-form__icon-link-text"><spring:message code="field.convocatoria.adjuntarDocumento"/></span></a>
                      </div>
                    </div>
             <% } %>
		</fieldset>
	</div>
</div>