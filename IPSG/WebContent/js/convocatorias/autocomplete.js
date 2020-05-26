  //Autocomplete
  $( function() { 
	var accentMap = {"\u00C1": "A","\u00D3": "O","\u00C9": "E","\u00CD": "I","\u00DA": "U"};
    var normalize = function( term ) {
        var ret = "";
        for ( var i = 0; i < term.length; i++ ) {
          ret += accentMap[ term.charAt(i) ] || term.charAt(i);
        }
        return ret;
      };
     $( "#titulosAutocomplete" ).autocomplete({
         source: function( request, response ) {
           var matcher = new RegExp( $.ui.autocomplete.escapeRegex( request.term ), "i" );
           response( $.grep( listaTituloJs, function( value ) {
             value = value.label || value.value || value;
             return matcher.test( value ) || matcher.test( normalize( value ) );
           }) );
         },minLength: 3
      });	  
  } );
  
  function pasarOptions(formulario1, formulario2) {
		var optionsSeleccionados = "";
		var ok = false;
		
		if (formulario1 == "titulos") {
		    $('#'+formulario1).find('option').each(function() {
		        if($(this).text() == $("#titulosAutocomplete").val()) {
		        	optionsSeleccionados = $(this).prop('selected', true);
		        	ok = true;
		        }
		    });
		    if (!ok) {
		    	alert("El t\u00EDtulo seleccionado no se encuentra en el listado.");
		    }
		} else if (formulario1 == "titulosSeleccionados") {
			if (listaTituloJs.indexOf($('#'+formulario1).find(":selected").text()) == -1) {
				listaTituloJs.push($('#'+formulario1).find(":selected").text());
			}
			optionsSeleccionados = $('#'+formulario1).find(":selected");
		} else {
			optionsSeleccionados = $('#'+formulario1).find(":selected");
		}
		
		$('#'+formulario2).append(optionsSeleccionados);
	}