package br.edu.ifsp.encurtador.model.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Link {
	private String linkEncurtado;
	private String linkOriginal;
	private Map<Access,Integer> acessos;
	
	private static int tamLink = 5;
	
	public static void atualizarTamanho() {
		if(tamLink<12) {
			tamLink+=1;
		}
	}
	
	public Link() {}
	
	public Link(String original) {
		init(gerarEncurtado(), original);
	}
	
	public Link(String encurtado, String original) {
		init(encurtado,original);
	}
	
	public String getLinkEncurtado() {
		return linkEncurtado;
	}
	
	public String getLinkOriginal() {
		return linkOriginal;
	}
	
	public void setLinkEncurtado(String linkEncurtado) {
		this.linkEncurtado = linkEncurtado;
	}

	public void setLinkOriginal(String linkOriginal) {
		this.linkOriginal = linkOriginal;
	}

	public void clearMap() {
		acessos.clear();
	}
	
	public void addValue(Access acesso, int n) {
		acessos.put(acesso, n);
	}
	
	private void init(String encurtado, String original) {
		this.linkEncurtado = encurtado;
		this.linkOriginal = original;
		this.acessos = new HashMap<Access,Integer>();
	}
	
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
