package br.edu.ifsp.encurtador.model.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class User {
	private String login;
	private String senha;
	private List<Link> links;
	
	public static boolean authenticate(User fromSystem, String login, String senha) {
		if(fromSystem != null) {
			return hashSHA256(senha).equals(fromSystem.senha) && login.equals(fromSystem.login);
		}
		return false;
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
