package br.edu.ifsp.encurtador.model.dao;

import java.util.List;

import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;

public interface LinkDao {
	
	boolean create(User user, Link link);
	
	boolean update(Link updatedLink, String oldShort);
	
	boolean delete(Link link);
	
	void retrieve(User user);
	
	Link retrieve(String original);
	
	String retrieve_original(String curto);
	
	boolean hasUser(String curto);

	List<Link> retrieveLinksByUser(User user);
	
	Link retrieve(String original, User user);
}
