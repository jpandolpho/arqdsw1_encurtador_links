package br.edu.ifsp.encurtador.model.dao;

public class LinkDaoFactory {
	public static LinkDao factory() {
		return new LinkDaoDatabase();
	}
}