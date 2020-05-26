// Datepicker - Calendario JQuery
      
//Configuracion del Datepicker para que se muestre en castellano (configuracion del idioma regional espanol por defecto)

jQuery(function($) {
$.datepicker.regional['es'] = 
  { 
    clearText: 'Borra', 
    clearStatus: 'Borra fecha actual', 
    closeText: 'Cerrar', 
    closeStatus: 'Cerrar sin guardar', 
    prevStatus: 'Mostrar mes anterior', 
    prevBigStatus: 'Mostrar año anterior', 
    nextStatus: 'Mostrar mes siguiente', 
    nextBigStatus: 'Mostrar año siguiente', 
    currentText: 'Hoy', 
    currentStatus: 'Mostrar mes actual', 
    monthNames:  ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'], 
    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'], 
    monthStatus: 'Seleccionar otro mes', 
    yearStatus: 'Seleccionar otro año', 
    weekHeader: 'Sm', 
    weekStatus: 'Semana del año', 
    dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'], 
    dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'], 
    dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'], 
    dayStatus: 'Set DD as first week day', 
    dateStatus: 'Select D, M d', 
    dateFormat: 'dd/mm/yy', 
    firstDay: 1, 
    initStatus: 'Seleccionar fecha', 
    isRTL: false 
  }; 

  $.datepicker.setDefaults($.datepicker.regional['es']);
});    



$(function(){
/*$('#datepicker').datepicker({
    inline: true
});*/
  //hover states on the static widgets
  $('#dialog_link, ul#icons li').hover(
    function() { $(this).addClass('ui-state-hover'); }, 
    function() { $(this).removeClass('ui-state-hover'); }
  );
  
});


function getArrayValuesFromString(strLabels, strSeparador) {
	return strLabels.split(strSeparador);
}

function fncCalendario(lblCalendario, entorno) {
	objLblDiasSemana = getArrayValuesFromString('L,M,X,J,V,S,D',',');
	objLblMeses = getArrayValuesFromString('Enero,Febrero,Marzo,Abril,Mayo,Junio,Julio,Agosto,Setiembre,Octubre,Noviembre,Diciembre',',');

	var intMinYear = parseInt(new Date().getFullYear())-10; 
	var intMaxYear = parseInt(new Date().getFullYear())+5;
	
	var objLblDiasSemanaTrans = new Array(7);

	objLblDiasSemanaTrans[0] = objLblDiasSemana[6];
	var intDias = 0;
	for (intDias = 0; intDias < 6; intDias++) {
		objLblDiasSemanaTrans[intDias + 1] = objLblDiasSemana[intDias];
	}

	$(function() {
		$( '#' + lblCalendario ).datepicker({
			showOn: 'button',
			buttonImage: entorno + '/images/calendar/boton-calendario.gif',
			buttonImageOnly: true,
			firstDay: 1,
			monthNames: objLblMeses,
			monthNamesShort: objLblMeses,
			dayNames: objLblDiasSemanaTrans,
			dayNamesMin: objLblDiasSemanaTrans,
			dayNamesShort: objLblDiasSemanaTrans,
			yearRange: intMinYear + ':' + intMaxYear,
			changeMonth: true,
			changeYear: true,
			nextText: '>',
			prevText: '<',
			onSelect: function(dateText, inst) {
				document.getElementById(lblCalendario).value = dateText;
			},
			
			beforeShow: function(input, inst) {
				document.getElementById(lblCalendario).disabled = true;
			},

			onClose: function(dateText, inst) {
				document.getElementById(lblCalendario).disabled = false;
				document.getElementById(lblCalendario).focus();	
			}
		});
	});
	
}


function fncCalendarioPresenciales(lblCalendario, entorno) {
	objLblDiasSemana = getArrayValuesFromString('L,M,X,J,V,S,D',',');
	objLblMeses = getArrayValuesFromString('Enero,Febrero,Marzo,Abril,Mayo,Junio,Julio,Agosto,Setiembre,Octubre,Noviembre,Diciembre',',');

	var intMinYear = parseInt(new Date().getFullYear())-80; 
	var intMaxYear = parseInt(new Date().getFullYear())+5;
	
	var objLblDiasSemanaTrans = new Array(7);

	objLblDiasSemanaTrans[0] = objLblDiasSemana[6];
	var intDias = 0;
	for (intDias = 0; intDias < 6; intDias++) {
		objLblDiasSemanaTrans[intDias + 1] = objLblDiasSemana[intDias];
	}

	$(function() {
		$( '#' + lblCalendario ).datepicker({
			showOn: 'button',
			buttonImage: entorno + '/images/calendar/boton-calendario.gif',
			buttonImageOnly: true,
			firstDay: 1,
			monthNames: objLblMeses,
			monthNamesShort: objLblMeses,
			dayNames: objLblDiasSemanaTrans,
			dayNamesMin: objLblDiasSemanaTrans,
			dayNamesShort: objLblDiasSemanaTrans,
			yearRange: intMinYear + ':' + intMaxYear,
			changeMonth: true,
			changeYear: true,
			nextText: '>',
			prevText: '<',
			onSelect: function(dateText, inst) {
				document.getElementById(lblCalendario).value = dateText;
			},
			
			beforeShow: function(input, inst) {
				document.getElementById(lblCalendario).disabled = true;
			},

			onClose: function(dateText, inst) {
				document.getElementById(lblCalendario).disabled = false;
				document.getElementById(lblCalendario).focus();	
			}
		});
	});
	
}