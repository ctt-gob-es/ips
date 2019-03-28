<logic:equal name="ciudadanoBean" property="tipoPersona" value="FH">
	<div id="boxSolicitante" data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.DatosFuncionarioHabilitado" />
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<legend class="pae-form__legend-text">
					
				</legend>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.funcionarioHabilitado.nif" />
							</span>
							<span id="nifFunHabilitado" name="nifFunHabilitado" class="pae-form__text-readonly">
								<bean:write name="ciudadanoBean" property="nif" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.funcionarioHabilitado.primerApellido" />
							</span>
							<span id="primerApellidoFunHabilitado" name="primerApellidoFunHabilitado" class="pae-form__text-readonly">
								<bean:write name="ciudadanoBean" property="primerApellido" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.funcionarioHabilitado.segundoApellido" />
							</span>
							<span id="segundoApellidoFunHabilitado" name="segundoApellidoFunHabilitado" class="pae-form__text-readonly">
								<bean:write name="ciudadanoBean" property="segundoApellido" />
							</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formulario790.funcionarioHabilitado.nombre" />
							</span>
							<span id="nombreFunHabilitado" name="nombreFunHabilitado" class="pae-form__text-readonly">
								<bean:write name="ciudadanoBean" property="nombre" />
							</span>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</logic:equal>
