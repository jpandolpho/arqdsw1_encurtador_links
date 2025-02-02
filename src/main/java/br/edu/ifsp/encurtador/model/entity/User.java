package br.edu.ifsp.encurtador.model.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class User {
	private String login;
	private String senha;
	private List<Link> links;
	
	public User(String login, String senha) {
		init(login, hashSHA256(senha));
	}
	
	public User(String login, String senha, boolean fromDB) {
		if(fromDB) {
			init(login,senha);
		}else {
			init(login,hashSHA256(senha));
		}
	}

	public String getLogin() {
		return login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public static boolean authenticate(User fromSystem, String login, String senha) {
		if(fromSystem != null) {
			return hashSHA256(senha).equals(fromSystem.senha) && login.equals(fromSystem.login);
		}
		return false;
	}

	private void init(String login, String senha) {
		this.login = login;
		this.senha = senha;
		this.links = new LinkedList<Link>();
	}
	
	private static String hashSHA256(String senha) {
		try {
			var digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hashBytes = digest.digest(senha.getBytes());
			
			var sb = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					sb.append('0');
				}
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao criptografar.", e);
		}
		
	}
}
