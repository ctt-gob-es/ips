/*
 * 
 * Este fichero forma parte del Cliente @firma. 
 * El Cliente @firma es un applet de libre distribuci�n cuyo c�digo fuente puede ser consultado
 * y descargado desde www.ctt.map.es.
 * Copyright 2009,2010 Gobierno de Espa�a
 * Este fichero se distribuye bajo las licencias EUPL versi�n 1.1  y GPL versi�n 3, o superiores, seg�n las
 * condiciones que figuran en el fichero 'LICENSE.txt' que se acompa�a.  Si se   distribuyera este 
 * fichero individualmente, deben incluirse aqu� las condiciones expresadas all�.
 */


/**
 * Funciones de tiempo.
 *
 * whenTry(condicion, comando[, msgErr]) -> Ejecuta el comando cuando se cumpla la condici?n. Si no se cumple transcurrido un tiempo, y se ha especificado msgErr, se muestra un alert con msgErr.
 *
 * waitFor(condicion, millis) -> Espera un tiempo m?ximo a que se cumpla la condici?n
 */

function whenTry(condicion, comando, msgErr, intento)
{
	var whenTry;
	try
	{
		whenTry = eval(condicion);
	}
	catch(e)
	{
		whenTry = false;
	}

	if(whenTry)
	{
		eval(comando);
	}
	else
	{
		if(intento == undefined)
		{
			intento = 1;
		}
		else 
		{
			if(intento > 100)
			{
				if(msgErr != undefined)
				{
					alert(msgErr);
				}
				return false;
			}
			else
			{
				intento = intento + 1;
			}
		}
		
		if(msgErr == undefined)
		{
			setTimeout("whenTry(\""+condicion+"\", \""+comando+"\", undefined, "+intento+")", 100);
		}
		else
		{
			setTimeout("whenTry(\""+condicion+"\", \""+comando+"\", \""+msgErr+"\", "+intento+")", 100);
		}
	}
}

function waitFor(_condition, _millis)
{
	var _aux	= new Date().getTime();
	
	var _dif = new Date().getTime() - _aux;
	while( !eval(_condition) && (_dif < _millis) )
	{
		_dif = new Date().getTime() - _aux;
	}
}

