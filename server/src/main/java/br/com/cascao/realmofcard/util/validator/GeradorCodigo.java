package br.com.cascao.realmofcard.util.validator;

import java.util.Random;

public class GeradorCodigo {

	public static String gerarCodigoPedido() {
		Random random = new Random();
		String codigo = String.valueOf(random.nextInt(89)+10) + "-" + String.valueOf(random.nextInt(8999)+1000);
		return codigo;
	}
	
	public static String gerarCodigoCupom() {
		Random random = new Random();
		String codigo = String.valueOf(random.nextInt(89)+10) + "-" + String.valueOf(random.nextInt(899)+100);
		return codigo;
	}
}