package testes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestaData2 {

	public static void main(String[] args) {

		var dataTeste = LocalDateTime.now();

		dataTeste.getDayOfMonth();

		System.out.println(dataTeste.getDayOfWeek());

		System.out.println("""
				---- dataTeste ----
				Sem formatação: %s
				Basic ISO date: %s
				ISO date: %s
				ISO ordinal date: %s
				""".formatted(dataTeste, dataTeste.format(DateTimeFormatter.BASIC_ISO_DATE),
				dataTeste.format(DateTimeFormatter.ISO_DATE), dataTeste.format(DateTimeFormatter.ISO_ORDINAL_DATE)));

		// DateTimeFormatter.

		// var formatadorData =

	}

}
