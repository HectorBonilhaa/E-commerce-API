package br.com.serratec.eCommerce.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDeData {
	
//		dd = dia do mes
//		DD = dia do ano
//		MM = mes
//		mm = minuto
//		YYYY = ano formato 2022
//		YY = ano formato 22
//		HH = hora formato 24 hrs
//		hh = hora formato 12 hrs
//		ss = segundos
	
	
	public static String converterDateParaDataEHoraISO(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("YYYY-MM-dd");
		return formatador.format(data) + "T" + converterDateParaHora(data);
	}
	
	public static String converterDateParaDataEHora(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		return formatador.format(data);
	}
	
	public static String converterDateParaData(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/YYYY");
		return formatador.format(data);
	}
	
	public static String converterDateParaHora(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
		return formatador.format(data);
	}
}
