package br.edu.ifsp.encurtador.model.dao;

import java.sql.SQLException;

import br.edu.ifsp.encurtador.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.encurtador.model.entity.Access;
import br.edu.ifsp.encurtador.model.entity.Link;

public class AccessDaoDatabase implements AccessDao {

	private static final String INSERT = "INSERT INTO tb_acess(link,endereco_ip) VALUES (?,?)";
	private static final String SELECT_ALL = "SELECT endereco_ip, COUNT(endereco_ip) AS contagem FROM tb_acess WHERE link = ? GROUP BY endereco_ip";
	
	@Override
	public boolean create(Access acesso) {
		int rows = 0;
		if(acesso!=null) {
			try(var connection = DatabaseConnection.getConnection();
				var statement = connection.prepareStatement(INSERT)){
				
				statement.setString(1, acesso.getLink());
				statement.setString(2, acesso.getIpAddress());
				
				rows = statement.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}

	@Override
	public void retrieve(Link link) {
		link.clearMap();
		try(var connection = DatabaseConnection.getConnection();
			var statement = connection.prepareStatement(SELECT_ALL)){
					
			statement.setString(1, link.getLinkEncurtado());
			var result = statement.executeQuery();
			
			while(result.next()) {
				var acesso = new Access();
				acesso.setLink(link.getLinkEncurtado());
				acesso.setIpAddress(result.getString("endereco_ip"));
				
				link.addValue(acesso, result.getInt("contagem"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
