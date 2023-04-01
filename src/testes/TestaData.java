package testes;

import java.time.LocalDateTime;

public class TestaData {

	public static void main(String[] args) {

		var data = LocalDateTime.now();
		
		
		System.out.println("""
				Hoje: %s
				Amanhã: %s
				""".formatted(data, data.plusDays(1L)));
		

//		 System.out.println(data.withDayOfMonth(10));
		//
		// System.out.println(data);

		// var ontem = data.minusDays(1L);

		// funciona aqui
		// System.out.println(data.getDayOfWeek());

		// System.out.println("""
		//
		// ontem: %s
		// hoje: %s
		//
		// """.formatted(data.minusDays(1L), data));

	}

}
