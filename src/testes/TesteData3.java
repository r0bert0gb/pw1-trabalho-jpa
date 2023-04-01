package testes;

import java.util.Date;
import java.util.TimeZone;

public class TesteData3 {

	public static void main(String[] args) {

		Date agora = new Date();

		// UTC -3
		System.out.println("Data atual em milisegundos: " + agora.getTime());

		// Mostra o instante em 3 time zones diferentes.

		// UTC 0
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
		System.out.println(agora);
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		System.out.println(agora);
		
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println(agora);
		

	}
}
