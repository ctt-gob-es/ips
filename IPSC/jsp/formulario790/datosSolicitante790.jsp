<body onload="seleccionarEspaña();buscaCodigoPais();mascara();">
	<div id="boxSolicitante" data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.DatosSolicitante" />
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<legend class="pae-form__legend-text">
					<spring:message code="field.DatosPersonales" />
				</legend>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.nif" />
								<logic:equal name="plantilla" property="nif" value="S">
									<input type="hidden" id="obl_inputNif" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
							</span>
							<input type="text" id="nif" name="nif" value="" class="pae-form__input" onchange="return cambiarMayusculasText(this);"
								style="text-transform: uppercase;" maxlength="9">
							 <span class="pae-form__text-error" id="nif790Error">
							 	<spring:message code="field.solicitud.campoObligatorio" />
							 </span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.primerApellido" />
								<logic:equal name="plantilla" property="primerApellido" value="S">
									<input type="hidden" id="obl_inputPrimerApellido" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
							</span>
							<input type="text" id="primerApellido" name="primerApellido" value="" 
								onchange="return cambiarMayusculasText(this);" onPaste="comprobarPasteCaracteresEspeciales(this);comprobarPasteNumeros(this);"
								onkeypress="return soloLetras(event);"
								class="pae-form__input" style="text-transform: uppercase;" maxlength="50">
							<span class="pae-form__text-error" id="primerApellido790Error">
								<spring:message code="field.solicitud.campoObligatorio" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.segundoApellido" />
								<logic:equal name="plantilla" property="segundoApellido" value="S">
									<input type="hidden" id="obl_inputSegundoApellido" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
							</span>
								<input type="text" id="segundoApellido" name="segundoApellido" value="" class="pae-form__input" style="text-transform: uppercase;" 
								onkeypress="return soloLetras(event);" 
									onchange="return cambiarMayusculasText(this);" onPaste="comprobarPasteCaracteresEspeciales(this);comprobarPasteNumeros(this);" maxlength="50">
							<span class="pae-form__text-error" id="segundoApellido790Error">
								<spring:message code="field.solicitud.campoObligatorio" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.nombre" />
								<logic:equal name="plantilla" property="nombre" value="S">
									<input type="hidden" id="obl_inputNombre" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
							</span>
							<input type="text" id="nombre" name="nombre" value="" class="pae-form__input" style="text-transform: uppercase;" 
							onkeypress="return soloLetras(event);"
								onchange="return cambiarMayusculasText(this);" onPaste="comprobarPasteCaracteresEspeciales(this);comprobarPasteNumeros(this);" maxlength="50">
							<span class="pae-form__text-error" id="nombre790Error">
								<spring:message code="field.solicitud.campoObligatorio" />
							</span>
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-4/12-lap pae-u-2/3-palm">
						<div class="pae-form__cell">
							<label for="fechaNacimiento" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.formulario790.fechaNacimiento" />
									<logic:equal name="plantilla" property="fechaNacimiento" value="S">
										<input type="hidden" id="obl_fechaNacimiento" value="true">
										<span class="obligatorio">*</span>
									</logic:equal>
								</span>
							</label>
							<div class="pae-form__box-datepicker">
								<input type="text" id="fechaNacimiento" onchange="return validaFechaFormato(document.getElementById('fechaNacimiento'))"
									name="fechaNacimiento" data-function="function-datepicker" readonly='true'
									placeholder="dd/mm/aaaa" value="" class="pae-form__input">
							</div>
							<span id="fechaNacimiento790Error" class="pae-form__text-error__2 hiddenAccesible">
								<spring:message code="field.solicitud.campoObligatorio" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.sexo" />
								<logic:equal name="plantilla" property="sexo" value="S">
									<input type="hidden" id="obl_sexo" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
							</span>
							<div class="pae-form__custom-rb-box">
								<input type="radio" name="sexo" id="mujer" checked="checked"
									class="pae-form__custom-rb" property="sexo" value="M" />
							<label for="mujer" class="pae-form__custom-rb-label medium">
								<span class="pae-form__custom-rb-label--text">
									<spring:message code="field.mujer" />
								</span>
							</label>
								<input type="radio" name="sexo" id="hombre" value="V" class="pae-form__custom-rb" />
							<label for="hombre" class="pae-form__custom-rb-label medium">
								<span class="pae-form__custom-rb-label--text">
									<spring:message code="field.hombre" />
								</span>
							</label>
							</div>
						</div>
					</div>
					<div class="pae-layout__item pae-u-5/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<div class="control-group">
								<label for="nacionalidad" class="pae-form__label">
									<span class="pae-form__label--text">
										<spring:message code="field.formulario790.nacionalidad" />
										<logic:equal name="plantilla" property="nacionalidad" value="S">
												<input type="hidden" id="obl_inputNacionalidad" value="true">
												<span class="obligatorio">*</span>
										</logic:equal>
									</span>
								</label>
								<input type="text" name="nacionalidad" id="nacionalidad" style="text-transform: uppercase;" value="" onchange="return cambiarMayusculasText(this);"
								onkeypress="return soloLetras(event);"
									class="pae-form__input" property="nacionalidad" maxlength="25" onPaste="comprobarPasteCaracteresEspeciales(this);comprobarPasteNumeros(this);"/>
								<span class="pae-form__text-error" id="nacionalidad790Error">
									<spring:message code="field.solicitud.campoObligatorio" />
								</span>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend class="pae-form__legend-text">
					<spring:message code="field.DatosDomicilio" />
					*
				</legend>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="correoElectronicos" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.formulario790.correosElectronicos" />
									<logic:equal name="plantilla" property="correoElectronico" value="S">
										<input type="hidden" id="obl_inputEmail" value="true">
										<span class="obligatorio">*</span>
									</logic:equal>
								</span>
							</label> 
							<input type="text" id="correoElectronicos" name="correoElectronicos" value=""
								class="pae-form__input" property="mail" maxlength="50">
							<span class="pae-form__text-error" id="email790Error">
								<spring:message code="field.solicitud.emailError" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="telefono1" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.formulario790.telefonoAux" />
									<logic:equal name="plantilla" property="telefono" value="S">
										<input type="hidden" id="obl_inputTelefono" value="true">
										<span class="obligatorio">*</span>
									</logic:equal>
								</span>
							</label>
							<input type="text" id="telefono1" name="telefono" property="telefono" value=""
								class="pae-form__input" maxlength="9"
								onkeypress="return soloNumeros(event);" onPaste="comprobarPasteCaracteresNumericos(this);">
							<span class="pae-form__text-error" id="telefono790Error">
							<spring:message code="field.solicitud.telefonoError" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-5/12 pae-u-5/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<a href="#" title="Añadir otro teléfono" data-function="function-click-add-phone"
								class="pae-form__icon-link--add">
								<span class="pae-form__icon-link-text">
									<spring:message code="field.solicitud.otroTelefono"/>
								</span>
							</a>
						</div>
						<div class="pae-form__cell pae-form__cell--hide">
							<label for="telefono2" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.formulario790.telefonoAux2" />
								</span>
							</label>
							<input type="text" id="telefono2" name="telefonoAux" property="telefonoAux"
								value="" class="pae-form__input pae-form__input--medium"
								maxlength="9" onkeypress="return soloNumeros(event);">
							<div class="pae-form__icon-contain">
								<a href="#" title="Eliminar Campo" data-function="function-click-remove-phone"
									class="pae-form__icon-link--remove">
									<span class="pae-form__icon-link-text">
										<spring:message code="field.solicitud.botonEliminar"/>
									</span>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-7/12 pae-u-7/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="calleDomicilio" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.formulario790.domicilioAux" />
									<logic:equal name="plantilla" property="via" value="S">
										<input type="hidden" id="obl_inputCalleDomicilio" value="true">
										<span class="obligatorio">*</span>
									</logic:equal>
								</span>
							</label>
							<input type="text" id="calleDomicilio" style="text-transform: uppercase;"
								name="calleDomicilio" property="calleDomicilio" value="" onchange="return cambiarMayusculasText(this);"
								class="pae-form__input" maxlength="200"> 
							<span class="pae-form__text-error" id="calleDomicilio790Error">
								<spring:message code="field.solicitud.callePlazaError" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-5/12 pae-u-5/12-lap pae-u-1/3-palm">
						<div class="pae-form__cell">
							<label for="codigoPostalDomicilio" class="pae-form__label">
								<span class="pae-form__label--text pae-form__label--mobile">
									<spring:message code="field.solicitud.CP" />*</span>
								<span class="pae-form__label--text pae-form__label--desktop">
									<spring:message code="field.formulario790.codigoPostal" />
									<logic:equal name="plantilla" property="codigoPostal" value="S">
										<input type="hidden" id="obl_inputCodigoPostalDomicilio" value="true">
										<span class="obligatorio" id="idCpDom">*</span>
									</logic:equal>
								</span>
							</label>
							<input type="text" id="codigoPostalDomicilio" name="codigoPostalDomicilio"
								property="codigoPostalDomicilio" value=""
								class="pae-form__input pae-form__input--small w45"
								onkeypress="return soloNumeros(event);" maxlength="5">
							<span class="pae-form__text-error" id="codigoPostalDomicilio790Error">
								<spring:message code="field.solicitud.codigoPostalError" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-4/12 pae-u-6/12-lap pae-u-2/3-palm">
						<div class="pae-form__cell">
							<label for="municipioDomicilio" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.formulario790.domicilioMunicipioAux" />
									<logic:equal name="plantilla" property="municipio" value="S">
										<input type="hidden" id="obl_inputMunicipioDomicilio" value="true">
										<span class="obligatorio">*</span>
									</logic:equal>
								</span>
							</label>
							<input type="text" id="municipioDomicilio" style="text-transform: uppercase;" name="municipioDomicilio" onchange="return cambiarMayusculasText(this);"
							onkeypress="return soloLetras(event);"
								property="municipioDomicilio" value="" class="pae-form__input" onPaste="comprobarPasteCaracteresEspeciales(this);comprobarPasteNumeros(this);"
								maxlength="50">
							<span class="pae-form__text-error" id="municipioDomicilio790Error">
								<spring:message code="field.solicitud.campoObligatorio" />
							</span>
						</div>
					</div>
					<input type="hidden" name="codigoPaisDomicilio" id="codigoPaisDomicilio"/>
					<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<div class="control-group">
								<label for="provinciaDomicilio" class="pae-form__label">
									<span class="pae-form__label--text">
										<spring:message code="field.formulario790.domicilioProvinciaAux" />
										<logic:equal name="plantilla" property="provincia" value="S">
											<input type="hidden" id="obl_inputProvinciaDomicilio" value="true">
											<span class="obligatorio">*</span>
										</logic:equal>
									</span>
								</label>
								<form:select path="provinciaDomicilio"
									class="pae-form__input" id="provinciaDomicilio"
									onchange="cambiarMayusculas(this);buscaCodigoProvincia();">
									<option value="0" class="pae-form__option">
										<spring:message code="field.solicitud.selecciona" />
									</option>
									<form:options items="${provincias}" itemValue="id"
										itemLabel="descripcion" style="text-transform: uppercase;" />
								</form:select>
								<span class="pae-form__text-error" id="provinciaDomicilio790Error">
									<spring:message code="field.solicitud.campoObligatorio" />
								</span>
							</div>
						</div>
					</div>
			        <form:input path="codigoProvinciaDomicilio" id="codigoProvinciaDomicilio" tabindex="-1" size="2" maxlength="2" 
			               					class="pae-hidden" readonly="true" />
					<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<div class="control-group">
								<label for="pais" class="pae-form__label">
									<span class="pae-form__label--text">
										<spring:message code="field.formulario790.domicilioPaisAux" />
										<logic:equal name="plantilla" property="pais" value="S">
											<input type="hidden" id="obl_pais" value="true">
											<span class="obligatorio">*</span>
										</logic:equal>
									</span>
								</label>
								<form:select path="pais" id="pais"
									class="pae-form__input" onchange="bloquearProvincia();buscaCodigoPais();quitarCPObligatorio();"> 
									<form:options items="${paises}" itemValue="id"
										itemLabel="descripcion" style="text-transform: uppercase;" />
								</form:select>
								<span class="pae-form__text-error" id="paisDomicilio790Error">
									<spring:message code="field.solicitud.campoObligatorio" /></span>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</body>

<script type="text/javascript">
     function bloquearProvincia() {
     	if(document.getElementById("pais").value == 1) {
        	document.getElementById("provinciaDomicilio").disabled = false;
        }else {
        	document.getElementById("provinciaDomicilio").value = 0;
        	document.getElementById("provinciaDomicilio").disabled = true;
        }
	}
     function quitarCPObligatorio() {
       	if(document.getElementById("pais").value != 1) {
       		$("#idCpDom").attr("style","display:none");
       	}else{
       		$("#idCpDom").attr("style","");
          }
       }
</script>
