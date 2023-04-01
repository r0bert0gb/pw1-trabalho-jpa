package testes;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TesteData4 {

	public static void main(String[] args) {

		// Instant agora = Instant.now();
		Instant data = Instant.parse("2007-12-03T10:15:30.00Z");

		// O final Z representa o offset da Zona, que � zero.
		// UTC 0 (Greenwich)
		System.out.println("data: " + data);

		// Convertendo para ZonedDateTime para o hor�rio de brasil�a
		// que est� nos computadores no Brasil (.systemDefault())
		var zData = ZonedDateTime.ofInstant(data, ZoneId.systemDefault());

		System.out.println("Data (hor�rio de Bras�lia): " + zData);

	}

}
