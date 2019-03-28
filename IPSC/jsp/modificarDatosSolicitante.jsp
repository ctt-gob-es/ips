


<body onload="seleccionarEspaña();muestraPagos();mascara();	<% if(tipoPersona.equals("FH")) { %>generaFormularioFuncionario();<%  } %>">
	<div id="boxSolicitante" data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.DatosSolicitante" />
			</h3>
		</div>
		<div class="pae-box__body">
			
		<logic:equal name="ciudadanoBean" property="tipoPersona" value="FH">
			<fieldset>
				<legend class="pae-form__legend-text">
					<spring:message code="field.solicitudPresencial" />
				</legend>
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label" style="width: 105%;">
							<spring:message code="field.numJustificante" />
						</span>
						<span class="pae-form__text-readonly">
							<input type="text" onkeyup="cargaPistola(${idConvocatoria}, event)" id="numJustInput" name="numJustificantePresencial"   style="text-transform: uppercase;width: 121%;" class="pae-form__input">
							<!-- input que oculta la ristra de caracteres que genera la pistola en la lectura -->
							<input type="text" id="numJustInputHide"   style="text-transform: uppercase;width: 121%;display:none;" class="pae-form__input" disabled>
						</span>
					</div>
				</div>
				<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm" style="margin-left:15px; margin-top:23px;">
					<div class="pae-form__cell">
						<span class="pae-form__text-readonly">
							<div class="pae-box-buttons">
								<button type="button" id="botonBuscador" onclick="cargaJustificantesAjax(${idConvocatoria});" class="pae-buttons pae-buttons--default pae-buttons--medium pae-buttons--text-size-normal">Buscar</button>
							</div>
						</span>
					</div>
					
				</div>
				<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm" style="display:none;">
					<div  id="dialogTablaJustificantes" title="dialog tabla justificantes">
						<div class="pae-modal__col pae-modal__col--left">
							<h1 style="margin:20px 0 0 9px">
								Nº DE JUSTIFICANTE:
								<span id="numJustTexto"></span>
							</h1>
	 						<table id="tablaJustificantes" class="pae-table pae-table--margin tablesorter" >
	 							<thead class="pae-table__header">
	                      			<tr class="pae-table__row">
	                      				<th class="pae-table__cell-header header"><span class="pae-table__txt-title">NIF</span></th>
										<th class="pae-table__cell-header header"><span class="pae-table__txt-title">NOMBRE</span></th>
										<th class="pae-table__cell-header header"><span class="pae-table__txt-title">1er APELLIDO</span></th>
										<th class="pae-table__cell-header header"><span class="pae-table__txt-title">2º APELLIDO</span></th>
										<th class="pae-table__cell-header header"></th> 
	                      			</tr>
	                    		</thead>
							</table>
									
						
						
						</div>
						<div class="pae-login__button-container">
							<div class="pae-box-buttons">
							   	<button type="button" onclick="rellenaSolicitud()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--arrow-right outline">ACEPTAR</button>
							</div>
						</div>
					</div>
				</div>
			</div>
				</fieldset>
		</logic:equal>	
			
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
							<span id="nif" class="pae-form__text-readonly">
							<logic:equal name="ciudadanoBean" property="tipoPersona" value="C">
								<bean:write name="altaSolicitud" property="nif" />
							</logic:equal>
							<logic:equal name="ciudadanoBean" property="tipoPersona" value="FH">
								<input type="text" maxlength="9" id="nifCiudadano" name="nifCiudadano"   style="text-transform: uppercase;" class="pae-form__input" />
							</logic:equal>
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
							<span id="primerApellido" name="primerApellido" class="pae-form__text-readonly">
							<logic:equal name="ciudadanoBean" property="tipoPersona" value="C">
								<bean:write name="altaSolicitud" property="primerApellido" />
							</logic:equal>
							<logic:equal name="ciudadanoBean" property="tipoPersona" value="FH">
								<input type="text"  maxlength="44" id="primerApellido" name="primerApellido"   style="text-transform: uppercase;" 
									class="pae-form__input" onkeypress="soloLetras(event)" onpaste="comprobarPasteCaracteresEspeciales(this);">
							</logic:equal>
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
							<span id="segundoApellido" name="segundoApellido" class="pae-form__text-readonly">
						<logic:equal name="ciudadanoBean" property="tipoPersona" value="C">
								<bean:write name="altaSolicitud" property="segundoApellido" />
						</logic:equal>
						<logic:equal name="ciudadanoBean" property="tipoPersona" value="FH">
							<input type="text"  maxlength="46" id="segundoApellido" name="segundoApellido"   style="text-transform: uppercase;" 
								class="pae-form__input" onkeypress="soloLetras(event)" onpaste="comprobarPasteCaracteresEspeciales(this);"/>
						</logic:equal>
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
							<span id="nombre" name="nombre" class="pae-form__text-readonly">
						<logic:equal name="ciudadanoBean" property="tipoPersona" value="C">
								<bean:write name="altaSolicitud" property="nombre"/>
						</logic:equal>
						<logic:equal name="ciudadanoBean" property="tipoPersona" value="FH">
							<input type="text"  maxlength="38" id="nombre" name="nombre"    style="text-transform: uppercase;" 
								class="pae-form__input" onkeypress="soloLetras(event)" onpaste="comprobarPasteCaracteresEspeciales(this);"/>
						</logic:equal>
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
									name="fechaNacimiento" data-function="function-datepicker" readOnly="true" value="${altaSolicitud.fechaNacimiento }"
									placeholder="dd/mm/aaaa" class="pae-form__input" path="fechaNacimiento">
							</div>
							<span id="fechaNacimientoError" class="pae-form__text-error__2 hiddenAccesible">
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
								<c:if test="${altaSolicitud.sexo eq 'M' }">
									<input type="radio" name="sexo" id="mujer" checked="checked" class="pae-form__custom-rb" property="sexo" value="M" />
								</c:if>
								<c:if test="${altaSolicitud.sexo ne 'M' }">
									<input type="radio" name="sexo" id="mujer" class="pae-form__custom-rb" property="sexo" value="M" />
								</c:if>
								<label for="mujer" class="pae-form__custom-rb-label medium">
									<span class="pae-form__custom-rb-label--text">
										<spring:message code="field.mujer" />
									</span>
								</label>
								<c:if test="${altaSolicitud.sexo eq 'H' }">
									<input type="radio" name="sexo" id="hombre" checked="checked" value="H" class="pae-form__custom-rb" />
								</c:if>
								<c:if test="${altaSolicitud.sexo ne 'H' }">
									<input type="radio" name="sexo" id="hombre"  value="H" class="pae-form__custom-rb" />
								</c:if>
								<label for="hombre" class="pae-form__custom-rb-label medium">
									<span class="pae-form__custom-rb-label--text">
										<spring:message code="field.hombre"/>
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
								<input type="text" value="${altaSolicitud.nacionalidad }"
									style="text-transform: uppercase;" name="nacionalidad" id="nacionalidad"
									class="pae-form__input"  maxlength="25" onpaste="comprobarPasteCaracteresEspeciales(this);"/>
								<span class="pae-form__text-error">
									<spring:message code="field.solicitud.campoObligatorio" />
								</span>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend class="pae-form__legend-text">
					<spring:message code="field.DatosDomicilio" />	*
				</legend>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label for="email" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.formulario790.correosElectronicos" />
									<logic:equal name="plantilla" property="correoElectronico" value="S">
										<input type="hidden" id="obl_inputEmail" value="true">
										<span class="obligatorio">*</span>
									</logic:equal>
								</span>
							</label>
							<input type="text" id="email" name="email" class="pae-form__input" property="mail" maxlength="50" value="${altaSolicitud.email }">
							<span class="pae-form__text-error">
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
							<input type="text" id="telefono1" name="telefono" property="telefono" value="${altaSolicitud.telefono }"
								class="pae-form__input" maxlength="9" onkeypress="return soloNumeros(event);" onpaste="comprobarPasteCaracteresNumericos(this);">
								<span class="pae-form__text-error">
									<spring:message code="field.solicitud.telefonoError"/>
								</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-5/12 pae-u-5/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<a href="#" title="Añadir otro teléfono" data-function="function-click-add-phone" class="pae-form__icon-link--add">
								<span class="pae-form__icon-link-text">
									<spring:message code="field.solicitud.otroTelefono" />
								</span>
							</a>
						</div>
						<div class="pae-form__cell pae-form__cell--hide">
							<label for="telefono2" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.formulario790.telefonoAux2" />
								</span>
							</label>
							<input type="text" id="telefono2" name="telefonoAux" property="telefonoAux" value="${altaSolicitud.telefonoAux }"
								  class="pae-form__input pae-form__input--medium"
								maxlength="9" onkeypress="return soloNumeros(event);" onpaste="comprobarPasteCaracteresNumericos(this);">
							<div class="pae-form__icon-contain">
								<a href="#" title="Eliminar Campo" data-function="function-click-remove-phone" class="pae-form__icon-link--remove">
								<span class="pae-form__icon-link-text">
									<spring:message code="field.solicitud.botonEliminar" />
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
									<spring:message code="field.formulario790.domicilioAux"/>
									<logic:equal name="plantilla" property="via" value="S">
										<input type="hidden" id="obl_inputCalleDomicilio" value="true">
										<span class="obligatorio">*</span>
									</logic:equal>
								</span>
							</label>
							<input type="text" id="calleDomicilio" style="text-transform: uppercase;" value="${altaSolicitud.calleDomicilio }"
								name="calleDomicilio" property="calleDomicilio"   class="pae-form__input" maxlength="120">
							<span class="pae-form__text-error">
								<spring:message code="field.solicitud.callePlazaError" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-5/12 pae-u-5/12-lap pae-u-1/3-palm">
						<div class="pae-form__cell">
							<label for="codigoPostalDomicilio" class="pae-form__label">
								<span class="pae-form__label--text pae-form__label--mobile">
									<spring:message code="field.solicitud.CP" />
									
								</span>
								<span class="pae-form__label--text pae-form__label--desktop">
									<spring:message code="field.formulario790.codigoPostal" />
									<logic:equal name="plantilla" property="codigoPostal" value="S">
										<input type="hidden" id="obl_inputCodigoPostalDomicilio" value="true">
										<span class="obligatorio" id="idCpDom">*</span>
									</logic:equal>
								</span>
							</label>
							<input type="text" id="codigoPostalDomicilio" name="codigoPostalDomicilio"
								property="codigoPostalDomicilio" value="${altaSolicitud.codigoPostalDomicilio }" class="pae-form__input pae-form__input--small  w45"
								onkeypress="return soloNumeros(event);" maxlength="5">
							<span class="pae-form__text-error">
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
	
							<input type="text" id="municipioDomicilio" style="text-transform: uppercase;" name="municipioDomicilio"
								property="municipioDomicilio" value="${altaSolicitud.municipioDomicilio }" class="pae-form__input" maxlength="50" onpaste="comprobarPasteCaracteresEspeciales(this);">
							<span class="pae-form__text-error">
								<spring:message code="field.solicitud.campoObligatorio" />
							</span>
						</div>
					</div>
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
								<form:select path="provinciaDomicilio" class="pae-form__input" id="provinciaDomicilio" onchange="cambiarMayusculas(this)">
									<option value="0" class="pae-form__option">
										<spring:message code="field.solicitud.selecciona" />
									</option>
									<form:options items="${provincias}" itemLabel="descripcion" itemValue="id" style="text-transform: uppercase;"/>	
								</form:select>
								<span class="pae-form__text-error">
									<spring:message code="field.solicitud.campoObligatorio" />
								</span>
							</div>
						</div>
					</div>
					<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<div class="control-group">
								<label for="pais" class="pae-form__label">
									<span class="pae-form__label--text">
										<spring:message code="field.formulario790.domicilioPaisAux" />
<!-- 										<logic:equal name="plantilla" property="pais" value="S"> -->
											<input type="hidden" id="obl_pais" value="true">
											<span class="obligatorio">*</span>
<!-- 										</logic:equal> -->
									</span>
								</label>
								<form:select path="pais" id="pais" class="pae-form__input" onchange="bloquearProvincia();quitarCPObligatorio();">
									<form:options items="${paises}" itemLabel="descripcion" itemValue="id" style="text-transform: uppercase;"/>	
								</form:select>
								<span class="pae-form__text-error">
									<spring:message code="field.solicitud.campoObligatorio" />
								</span>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</body>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/validaciones.js"></script>
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
	function muestraPagos(){
		$('#formasDePago').show();
	}
	
     function generaFormularioFuncionario() {
         	// formulario cerrado
 	   	 	$('input[type=text]').prop("disabled",true);
 	   	 	$('input[type=checkbox]').prop("disabled",true);
	   	 	$('input[type=radio]').prop("disabled",true);
	   	 	$('select').prop("disabled",true);
	   	 	$('#formasDePago').hide();
	   	 	$("#adjDocumentos").hide() 	 	
	   	 	
	   	 	// formulario abierto
	   	 	$('#numJustInput').prop("disabled",false);
	   	 	$('#numJustInput').focus();	
	 }



            </script>