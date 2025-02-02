package br.edu.ifsp.encurtador.model.entity;

import java.util.List;
import java.util.Random;

public class Link {
	private String linkEncurtado;
	private String linkOriginal;
	private List<Acess> acessos;
	
	private static int tamLink = 5;
	
	private static String gerarEncurtado() {
		String pool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvxyz_";
		StringBuilder result = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < tamLink; i++) {
			result.append(pool.charAt(rand.nextInt(pool.length())));
		}
		return result.toString();
	}
}
