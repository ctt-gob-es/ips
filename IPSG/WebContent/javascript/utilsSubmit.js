$( document ).ready(function() {
	$("form").append("<input style='display: none' id='buttonSubmit' type='submit'/>");
});

function submitPaginar(paginaActual, paginasTotales, name){
	document.getElementById("paginaActual").value=paginaActual;
	document.getElementById("paginasTotales").value=paginasTotales;
	document.getElementById(name).value="Paginar";
	$("#buttonSubmit").click();
}

function submitDetalle(id, href){
	document.getElementById('formPadre').action = "https://des-local-ips.redsara.es" + href;
	document.getElementById("id").value=id;
	$("#buttonSubmit").click();
}
