<div data-function="modal-paso1" class="pae-modal" >
	<div class="pae-modal__body">
		<span class="pae-modal__icon pae-modal__icon--clock"><span
			class="hiddenAccesible"><spring:message code="field.modal.progressBar"/></span></span>
		<div id="progressbar" data-function="function-progressbar"
			class="pae-modal__progress-bar pae-modal__contain--1"></div>
		<div id="textoProceso1"
			class="pae-modal__text-contain pae-modal__contain--1">
			<p class="pae-modal__text pae-modal__text--info"><spring:message code="field.modal.guardarDatos"/></p>
		</div>
		<div id="textoProceso2"
			class="pae-modal__text-contain pae-modal__text-contain--mheight pae-modal__contain--2">
			<p class="pae-modal__text pae-modal__text--info"><spring:message code="field.modal.errorDatos"/></p>
			<p class="pae-modal__text pae-modal__text--rule"><spring:message code="field.modal.comprobarDatos"/></p>
		</div>
		<div id="botonProceso2" class="pae-modal__footer pae-modal__contain--2">
			<div class="pae-box-buttons pae-box-buttons--modal">
				<button type="button" onclick="recargarPagina();"
					class="pae-buttons pae-buttons--default pae-buttons--big"><spring:message code="field.modal.volverFormulario"/></button>
			</div>
		</div>
		<div id="textoProceso3" class="pae-modal__text-contain pae-modal__contain--3">
			<p class="pae-modal__text pae-modal__text--info"><spring:message code="field.modal.procesandoPago"/></p>
		</div>
		<div id="textoProceso4"
			class="pae-modal__text-contain pae-modal__text-contain--mheight pae-modal__contain--4">
			<p class="pae-modal__text pae-modal__text--info"><spring:message code="field.modal.errorPago"/></p>
			<p id="errorPagoDescripcion" class="pae-modal__text pae-modal__text--rule"><spring:message code="field.modal.comprobarDatos"/></p>
		</div>
		<div id="botonProceso4" class="pae-modal__footer pae-modal__contain--4">
			<div class="pae-box-buttons pae-box-buttons--modal">
				<button type="button" onclick="recargarPagina();"
					class="pae-buttons pae-buttons--default pae-buttons--big"><spring:message code="field.modal.volverFormulario"/></button>
			</div>
		</div>
		<div id="textoProceso5" class="pae-modal__text-contain pae-modal__contain--5">
			<p class="pae-modal__text pae-modal__text--info"><spring:message code="field.modal.procesandoRegistro"/></p>
		</div>
		<div id="textoProceso6"
			class="pae-modal__text-contain pae-modal__text-contain--mheight pae-modal__contain--6">
			<p class="pae-modal__text pae-modal__text--info"><spring:message code="field.modal.errorRegistro"/></p>
			<p class="pae-modal__text pae-modal__text--rule"><spring:message code="field.modal.mensajeErrorRegistro"/></p>
		</div>
		<div id="botonProceso6" class="pae-modal__footer pae-modal__contain--6">
			<div class="pae-box-buttons pae-box-buttons--modal">
				<button type="button" onclick="recargarPagina();"
					class="pae-buttons pae-buttons--default pae-buttons--big"><spring:message code="field.modal.volverFormulario"/></button>
			</div>
		</div>
		<div id="textoProceso7"
			class="pae-modal__text-contain pae-modal__contain--7">
			<p class="pae-modal__text pae-modal__text--info"><spring:message code="field.modal.procesoCompletado"/></p>
		</div>
		<div id="botonProceso7"
			class="pae-modal__footer pae-modal__footer--separation pae-modal__contain--7">
			<div class="pae-box-buttons pae-box-buttons--modal">
				<button type="button" id="botonVolver" onclick="javascript: llamadaBuscarIncripciones();return false;"
					class="pae-buttons pae-buttons--default pae-buttons--big"
					><spring:message code="field.modal.volverFormulario"/></button>
			</div>
		</div>
	</div>
</div>
