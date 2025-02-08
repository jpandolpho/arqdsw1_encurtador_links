package br.edu.ifsp.encurtador.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.encurtador.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.encurtador.model.entity.Link;
import br.edu.ifsp.encurtador.model.entity.User;

class LinkDaoDatabase implements LinkDao {
	
	private static final String INSERT = "INSERT INTO tb_link(curto,original,user_login) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE tb_link SET curto = ?, original = ? WHERE curto = ?";
	private static final String DELETE = "DELETE FROM tb_link WHERE curto = ?";
	private static final String SELECT_ALL = "SELECT * FROM tb_link WHERE user_login = ?";
	private static final String SELECT_BY_ORIGINAL_NO_USER = "SELECT * FROM tb_link WHERE original = ? AND user_login IS NULL";
	private static final String SELECT_BY_CURTO = "SELECT * FROM tb_link WHERE curto = ?";
	private static final String SELECT_BY_ORIGINAL_HAS_USER = "SELECT * FROM tb_link WHERE original = ? AND user_login = ?";
	
	@Override
	public boolean create(User user, Link link) {
		int rows = 0;
		if(link!=null) {
			try(var connection = DatabaseConnection.getConnection();
				var statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, link.getLinkEncurtado());
				statement.setString(2, link.getLinkOriginal());
				if(user!=null) {
					statement.setString(3, user.getLogin());
				}else {
					statement.setString(3, null);
				}
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean update(Link updatedLink, String oldShort) {
		int rows = 0;
		if(updatedLink!=null && !oldShort.isEmpty()) {
			try(var connection = DatabaseConnection.getConnection();
				var statement = connection.prepareStatement(UPDATE)){
				
				statement.setString(1, updatedLink.getLinkEncurtado());
				statement.setString(2, updatedLink.getLinkOriginal());
				statement.setString(3, oldShort);
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public boolean delete(String curto) {
		if(!curto.isEmpty()) {
			int rows = -1;
			try(var connection = DatabaseConnection.getConnection();
				var statement = connection.prepareStatement(DELETE)){
					
				statement.setString(1, curto);
					
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return rows > 0;
		}
		return false;
	}

	@Override
	public void retrieve(User user) {
		user.clearList();
		try(var connection = DatabaseConnection.getConnection();
			var statement = connection.prepareStatement(SELECT_ALL)){
					
			statement.setString(1, user.getLogin());
			var result = statement.executeQuery();
			
			while(result.next()) {
				var link = new Link(result.getString("curto"),result.getString("original"));
				
				AccessDao dao = new AccessDaoFactory().factory();
				dao.retrieve(link);
				
				user.addLink(link);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Link retrieve(String original) {
		Link link = null;
		try(var connection = DatabaseConnection.getConnection();
			var statement = connection.prepareStatement(SELECT_BY_ORIGINAL_NO_USER)){
			
			statement.setString(1, original);
			var resultSet = statement.executeQuery();
			if(resultSet.next()) {
				link = new Link(resultSet.getString("curto"),resultSet.getString("original"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return link;
	}

	@Override
	public String retrieve_original(String curto) {
		String original = null;
		try(var connection = DatabaseConnection.getConnection();
			var statement = connection.prepareStatement(SELECT_BY_CURTO)){
			
			statement.setString(1, curto);
			var resultSet = statement.executeQuery();
			if(resultSet.next()) {
				original = resultSet.getString("original");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return original;
	}

	@Override
	public boolean hasUser(String curto) {
		boolean hasUser = false;
		try(var connection = DatabaseConnection.getConnection();
			var statement = connection.prepareStatement(SELECT_BY_CURTO)){
			
			statement.setString(1, curto);
			var resultSet = statement.executeQuery();
			if(resultSet.next()) {
				String user = resultSet.getString("user_login"); 
				hasUser = (user!=null);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return hasUser;
	}

	
	@Override
	public List<Link> retrieveLinksByUser(User user) {
	    List<Link> links = new ArrayList<>();
	    try (var connection = DatabaseConnection.getConnection();
	         var statement = connection.prepareStatement(SELECT_ALL)) {

	        statement.setString(1, user.getLogin());
	        var result = statement.executeQuery();

	        while (result.next()) {
	            Link link = new Link();
	            link.setLinkEncurtado(result.getString("curto"));
	            link.setLinkOriginal(result.getString("original"));
	            
	            AccessDao dao = new AccessDaoFactory().factory();
	            dao.retrieve(link);

	            links.add(link);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return links;
	}

	@Override
	public Link retrieve(String original, User user) {
		Link link = null;
		try(var connection = DatabaseConnection.getConnection();
			var statement = connection.prepareStatement(SELECT_BY_ORIGINAL_HAS_USER)){
			
			statement.setString(1, original);
			statement.setString(2, user.getLogin());
			var resultSet = statement.executeQuery();
			if(resultSet.next()) {
				link = new Link(resultSet.getString("curto"),resultSet.getString("original"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return link;
	}

}
