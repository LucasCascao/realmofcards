package br.edu.les.realmofcard.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GeradorCodigo {

	public static String gerarCodigoPedido() {
		Random random = new Random();
		String codigo = String.valueOf(random.nextInt(89)+10) + "-" + String.valueOf(random.nextInt(8999)+1000);
		return codigo;
	}
	
	public static String gerarCodigoCupom() {
		Random random = new Random();
		LocalDateTime localDateTime = LocalDateTime.now();
		random.setSeed(localDateTime.getHour() + localDateTime.getDayOfYear());
		String codigo = String.valueOf(random.nextInt(899999)+100000) + "-" + String.valueOf(random.nextInt(89)+10);
		return codigo;
	}

	public static String gerarCodigoRastreio() {
		Random random = new Random();
		LocalDateTime localDateTime = LocalDateTime.now();
		random.setSeed(localDateTime.getHour() + localDateTime.getDayOfYear());
		String codigo = "BR" + String.valueOf(random.nextInt(899999999)+100000000) + "AA";
		return codigo;
	}
}