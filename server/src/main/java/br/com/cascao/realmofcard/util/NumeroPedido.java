package br.com.cascao.realmofcard.util;

import java.util.Random;

public class NumeroPedido {

	public static String gerar() {
		Random random = new Random();
		String codigo = String.valueOf(random.nextInt(89)+10) + "-" + String.valueOf(random.nextInt(8999)+1000);
		return codigo;
	}
}