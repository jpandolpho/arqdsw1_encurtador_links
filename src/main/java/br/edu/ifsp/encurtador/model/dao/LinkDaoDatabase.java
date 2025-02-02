package br.edu.ifsp.encurtador.model.dao;

import java.util.List;

import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;

public class LinkDaoDatabase implements LinkDao {
	
	private static final String INSERT = "INSERT INTO tb_link(curto,original,user_login) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE tb_link SET curto = ?, original = ? WHERE curto = ?";
	private static final String DELETE = "DELETE FROM tb_link WHERE curto = ?";
	private static final String SELECT_ALL = "SELECT * FROM tb_link WHERE user_login = ?";
	
	@Override
	public boolean create(User user, Link link) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Link updatedLink, String oldShort) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Link link) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Link> retrieve(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
