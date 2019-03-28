		
		
		<div data-function="accordion-box" class="pae-box" id="boxConvocatoria">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.DatosConvocatoria"/></h3>
              </div>
              <div class="pae-box__body">
			  	<fieldset>
                  <legend class="pae-form__legend-text hiddenAccesible"><spring:message code="field.DatosConvocatoria"/></legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                    	<div class="pae-form__cell">
                        <div class="control-group">
                          <label for="scala" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.convocatoria.cuerpoEscalaAux"/></span></label>
                          		<logic:present name="cuerposEscala" scope="request">
									<bean:size id="numCuerposEscala" name="cuerposEscala" scope="request"/>
										<logic:greaterThan name="numCuerposEscala" value="0">
											<form:select path="cuerpoEscala" id="cuerpoEscala" class="pae-form__input" onchange="cambiarMayusculas(this);comprobarCuerpoEscala();buscaCodigoCuerpoEscala();">
												<option value="0"><spring:message code="field.solicitud.selecciona"/></option>
												<form:option value="-1"><spring:message code="field.formulario790.convocatoria.introduzcaValorCuerpoEscala"/></form:option>
												<form:options items="${cuerposEscala}" itemValue="id" itemLabel="descripcion" />
											</form:select>	
										</logic:greaterThan>
											</logic:present>
											<logic:present name="convocatoria" scope="request">
											     <div class="pae-form__input">
                          							<bean:write name="convocatoria" property="desCuerpoEscala" />
                          						</div>
											</logic:present>
											<logic:notPresent name="convocatoria" scope="request">
					                    		<input type="text" name="cuerpoEscalaManual" id="cuerpoEscalaManual" maxlength="80" class="pae-form__input"
				                        				 onchange="return cambiarMayusculasText(this);"/>
				                        	</logic:notPresent>	
                        </div>
                      </div>
                    </div>
               		<form:input path="codigoCuerpoEscala" id="codigoCuerpoEscala" tabindex="-1" size="3" maxlength="4" 
               									class="pae-hidden" readonly="true" />
                  <div class="pae-layout__item pae-u-8/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <div class="control-group">
                        	<label for="area" class="pae-form__label">
                        		<span class="pae-form__label--text">
                        			<spring:message code="field.formulario790.convocatoria.especialidad"/>
                        			<logic:equal name="plantilla" property="especialidad" value="S">
                        				<input type="hidden" id="obl_especialidad" value="true">
                        				<span class="obligatorio">*</span>
                        			</logic:equal>
                        		</span>
                        	</label>
                        	<logic:present	name="especialidades" scope="request">
								<bean:size id="numEspecialidades" name="especialidades"	scope="request" />
									<logic:greaterThan name="numEspecialidades" value="0">
										<form:select path="especialidad" id="especialidad"	class="pae-form__input" style="text-transform: uppercase;" onchange="comprobarEspecialidad();buscaCodigoEspecialidad();">
											<option value="0">
												<spring:message code="field.solicitud.selecciona"/>
											</option>
											<form:option value="-1">
												<spring:message code="field.formulario790.convocatoria.introduzcaValorEspecialidad" />
											</form:option>
											<form:options items="${especialidades}" itemValue="id"	itemLabel="descripcion" />
										</form:select>
										<span class="pae-form__text-error">
											<spring:message code="field.solicitud.campoObligatorio" />
										</span>
									</logic:greaterThan>
							</logic:present> 
							<logic:present name="convocatoria" scope="request">
								<form:select path="especialidad" id="especialidad" class="pae-form__input" onchange="buscaCodigoEspecialidadConvocatoria();" style="text-transform: uppercase;">
									<option value="0"><spring:message code="field.solicitud.selecciona"/></option>
									<form:options items="${convocatoria.especialidads}" itemValue="id" itemLabel="descripcion" />
								</form:select><span class="pae-form__text-error"><spring:message code="field.solicitud.campoObligatorio" />
							</logic:present>
							<logic:notPresent name="convocatoria" scope="request">
								<input name="especialidadManual" id="especialidadManual" maxlength="80" class="pae-form__input"	style="text-transform: uppercase;"/>
							</logic:notPresent>
               				<form:input path="codigoEspecialidad" id="codigoEspecialidad" tabindex="-1" size="3" maxlength="4" 
               								class="pae-hidden" readonly="true" />
                        </div>
                      </div>
                    </div>
                    </div>
                    <div class="pae-layout">
                    <div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <div class="control-group">
                          <label for="access" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.convocatoria.formaAcceso"/></span></label>
                          <logic:present name="formasAcceso" scope="request">
								<bean:size id="numFormasAcceso" name="formasAcceso" scope="request"/>
									<logic:greaterThan name="numFormasAcceso" value="0">
										<form:select path="formaAcceso" id="formaAcceso" class="pae-form__input" onchange="comprobarFormaAcceso();" style="text-transform: uppercase;">
												<option value="0"><spring:message code="field.solicitud.selecciona"/></option>
														<form:option value="-1"><spring:message code="field.formulario790.convocatoria.introduzcaValorAcceso"/></form:option>
														<form:options items="${formasAcceso}" itemValue="id" itemLabel="codigo" />
													</form:select>
												</logic:greaterThan>
											</logic:present>
											<logic:present name="convocatoria" scope="request">
												<form:input path="codFormaAcceso" id="codFormaAcceso" class="pae-form__input pae-form__input--disabled__2" onchange="cambiarMayusculas(this)" readonly="true"/>
											</logic:present>
											<logic:notPresent name="convocatoria" scope="request">
				                     			<input type="text" name="formaAccesoManual" id="formaAccesoManual" maxlength="4" style="text-transform: uppercase;"/>
			              					</logic:notPresent>	
                        </div>
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-8/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <div class="control-group">
                          <label for="entity" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.convocatoria.ministerioAux" /></span></label>
                          <logic:notPresent name="convocatoria" scope="request">
				                   <input type="text" name="ministerioAutomatico" id="ministerioAutomatico" maxlength="80"/>
			              </logic:notPresent>	
							<logic:present name="convocatoria" scope="request">
								<form:input path="desMinisterioConvocante" value="${convocatoria.desMinisterioConvocante}" class="pae-form__input pae-form__input--disabled__2" maxlength="80" readonly="true" onchange="cambiarMayusculas(this);"/>
							</logic:present>
	               			<form:input path="codigoMinisterio" id="codigoMinisterio" tabindex="-1" size="4" maxlength="5" 
	               							class="pae-hidden" readonly="true" />
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="pae-layout">
                  <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <label for="date" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.convocatoria.fechaBOE"/></span></label>
                        <logic:notPresent name="convocatoria" scope="request">
                        <div class="pae-form__box-datepicker">
                          <input type="text" id="fechaBOE" name="fechaBOE" data-function="function-datepicker" placeholder="dd/mm/aaaa" value="" class="pae-form__input">
                        </div>
                        </logic:notPresent>
                        <logic:present name="convocatoria" scope="request">
                        		<input class="pae-form__input pae-form__input--disabled__2" name="FechaBoe" id="FechaBoe" disabled="true" />
    					<script type="text/javascript">
    						document.getElementById("FechaBoe").value = $("#diaFechaBoe").val()+'/'+$("#mesFechaBoe").val()+'/'+$("#anioFechaBoe").val();
    					</script>
                        </logic:present>
                      </div>
                    </div>
                  <div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <div class="control-group">
                          <label for="provinciaExamen" class="pae-form__label">
                          	<span class="pae-form__label--text">
                          		<spring:message code="field.formulario790.convocatoria.provinciaExamen"/>
                          		<logic:equal name="plantilla" property="provinciaExamen" value="S">
									<input type="hidden" id="obl_provinciaExamen" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
                          	</span>
                          </label>
						  	<logic:present name="provinciasExamen" scope="request">
								<bean:size id="numProvincias" name="provinciasExamen" scope="request"/>
									<logic:greaterThan name="numProvincias" value="0">
										<form:select path="provinciaExamen" id="provinciaExamen" size="1" onchange="buscaProvinciaExamen();" style="text-transform: uppercase;">
											<option value="0"><spring:message code="field.solicitud.selecciona"/></option>
											<form:options items="${provinciasExamen}" itemValue="id" itemLabel="descripcion" />
										</form:select><span class="pae-form__text-error"><spring:message code="field.solicitud.campoObligatorio" />	
									</logic:greaterThan>
							</logic:present>
							<logic:present name="convocatoria" scope="request">
									<form:select path="provinciaExamen" size="1" class="pae-form__input" id="provinciaExamen" onchange="buscaProvinciaExamenConvocatoria();" style="text-transform: uppercase;">
										<option value="0"><spring:message code="field.solicitud.selecciona"/></option>
										<form:options items="${convocatoria.provinciasExamen}" itemValue="id" itemLabel="descripcion"/>
									</form:select><span class="pae-form__text-error"><spring:message code="field.solicitud.campoObligatorio" />
							</logic:present>
	               			<form:input path="codigoProvinciaExamen" id="codigoProvinciaExamen" tabindex="-1" size="1" maxlength="2" 
	               							class="pae-hidden" readonly="true" />
                   	 	</div>
                    	</div>
                    </div>
                    </div>
                    
                    
                </fieldset>
              </div>
            </div>