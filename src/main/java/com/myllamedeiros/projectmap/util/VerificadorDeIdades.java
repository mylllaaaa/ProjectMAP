package com.myllamedeiros.projectmap.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VerificadorDeIdades {
	public static Integer retornarIdade(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String[] dataDeHoje = sdf.format(new Date()).split("/");
		String[] dataDeReferencia = sdf.format(data).split("/");
		
		if(Integer.parseInt(dataDeHoje[1])>= Integer.parseInt(dataDeReferencia[1])) {
			if(Integer.parseInt(dataDeHoje[0])>= Integer.parseInt(dataDeReferencia[0])){
				return Integer.parseInt(dataDeHoje[2]) - Integer.parseInt(dataDeReferencia[2]);
			}	
		}
		return Integer.parseInt(dataDeHoje[2]) - Integer.parseInt(dataDeReferencia[2]) - 1;
	}
}
