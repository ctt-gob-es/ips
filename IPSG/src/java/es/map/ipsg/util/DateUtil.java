package es.map.ipsg.util;

/**
 * El Class DateUtil.
 */
public class DateUtil {
	
	/**
	 * Obtiene el mes.
	 *
	 * @param mes el mes
	 * @return el mes
	 */
	public static String getMes(int mes){
		String result = "";
		switch (mes) {
			case 0:{
				result = "enero";
				break;
			}
			case 1:{
				result = "febrero";
				break;
			}
			case 2:{
				result = "marzo";
				break;
			}
			case 3:{
				result = "abril";
				break;
			}
			case 4:{
				result = "mayo";
				break;
			}
			case 5:{
				result = "junio";
				break;
			}
			case 6:{
				result = "julio";
				break;
			}
			case 7:{
				result = "agosto";
				break;
			}
			case 8:{
				result = "septiembre";
				break;
			}
			case 9:{
				result = "octubre";
				break;
			}
			case 10:{
				result = "noviembre";
				break;
			}
			case 11:{
				result = "diciembre";
				break;
			}
			default:
				break;
		}
		return result;
	}

}
