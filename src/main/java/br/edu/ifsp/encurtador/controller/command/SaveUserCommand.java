package br.edu.ifsp.encurtador.controller.command;

import java.io.IOException;

import br.edu.ifsp.encurtador.model.dao.UserDaoFactory;
import br.edu.ifsp.encurtador.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var login = request.getParameter("textLogin");
		var senha = request.getParameter("textPassword");
		
		var dao = new UserDaoFactory().factory();
		var user = new User(login, senha);
		var saved = dao.insert(user);
		
		if(saved) {
			request.setAttribute("msg", "Usuário salvo com sucesso.");
		}else {
			request.setAttribute("msg", "Erro ao criar usuário.");
		}
		
		return "front.do?action=formUser&login=false";
	}

}
