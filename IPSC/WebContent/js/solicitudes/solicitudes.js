function popupInfo(){
	$("#modal").dialog({
	    title: "Por favor revise los siguientes mensajes",
	    draggable: !1,
	    modal: !0,
	    closeOnEscape: !0,
	    resizable: !0,
	    dialogClass: "",
	    height: "auto",
	    width: 700
	});
}

function completar(error) {
	$('#modal').dialog('close');
	if(!inicializar())
		return false;
	
	// Se copian los valores en el primer formulario para poder realizar
	// el casteo posterior en SolicitudSpring
	$("#tipoDisc").val($('input[name=tipoDiscapacidad]:checked').val());
	$("#porcentajeDisc").val($("#porcentajeDiscapacidad").val());
	$("#detalleDisc").val($("#detalleDiscapacidad").val());
	$("#idComunidadDisc").val($("#idComunidadDDExento").val());
	$(".motivoOposicionHidden").val($(".motivoOposicion").val());
	$("#formSolicitud").submit();

	if(error==0){
		return true;
	} else {
		return false;
	}	
}

function completarPagar() {
	$('#modal').dialog('close');
	if(!inicializar())
		return false;
	
	document.getElementById("estaEnPago").value = true;	
	
		if(!firmar())
		{
			/* if(erroresPago==0){
				//activarBotonContinuar();
			} */ 
		}
	proceso = 1;
	document.getElementById("add-modal").click();
}

function completarRegistrar() {
	$('#modal').dialog('close');
	if(!inicializar()){
		return false;
	}
	document.getElementById("add-modal").click();
	proceso = 2; //INICIO PROCESO DE PAGO
	progress2();
	
	setTimeout(function(){firmarArchivos();}, 2000);
}

function comprobarAvisoHoraBanco(entidad) {
	var horas=$( "#"+entidad+" option:selected" ).text().substring($("#"+entidad+" option:selected" )
			.text().indexOf("(")+1,$( "#"+entidad+" option:selected" ).text().indexOf(")"))
			.replace(" ","").replace(" ","").split("-");
	var horaActual = ((new Date().getHours() < 10)?'0'+new Date().getHours():new Date().getHours())+":"+((new Date().getMinutes() < 10)?'0'+new Date().getMinutes():new Date().getMinutes());

	if (horas != null && horas != ""){
		if(horaActual > horas[0] && horaActual < horas[1] || horas[0]==horas[1]) {
			$("#avisoHora").attr("class","pae-form__cell pae-hidden");
			return false;
		} else {
			$("#avisoHora").attr("class","pae-form__cell");
			return true;
		}
	}
}